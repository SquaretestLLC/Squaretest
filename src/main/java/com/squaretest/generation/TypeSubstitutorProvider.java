/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squaretest.generation;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.JavaClassSupers;
import com.intellij.psi.util.PsiUtil;
import com.squaretest.generation.defaulttypes.JavaNames;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;

public class TypeSubstitutorProvider {
    @NotNull
    private final JavaPsiFacade javaPsiFacade;
    private final PsiClass topLevelSourceClass;
    private final Map<PsiClass, PsiSubstitutor> classToSubsMap;
    private final PsiType stringType;
    private final JavaClassSupers javaClassSupers;
    private final GlobalSearchScope sourceClassResolveScope;

    public TypeSubstitutorProvider(@NotNull final JavaPsiFacade javaPsiFacade, @NotNull final PsiClass topLevelSourceClass) {
        this.javaPsiFacade = javaPsiFacade;
        this.classToSubsMap = new IdentityHashMap<>();
        this.sourceClassResolveScope = topLevelSourceClass.getResolveScope();
        this.stringType = PsiType.getJavaLangString(topLevelSourceClass.getManager(), sourceClassResolveScope);
        this.javaClassSupers = JavaClassSupers.getInstance();
        this.topLevelSourceClass = topLevelSourceClass;
        initializeSubsMap();
    }

    public PsiSubstitutor getSubstitutor(final PsiClass psiClass) {
        return classToSubsMap.get(psiClass);
    }

    private void initializeSubsMap() {
        PsiSubstitutor substitutor = PsiSubstitutor.EMPTY;
        for(final PsiTypeParameter typeParam : topLevelSourceClass.getTypeParameters()) {
            final PsiType typeToUse = getTypeToUse(typeParam);
            substitutor = substitutor.put(typeParam, typeToUse);
        }
        for(final PsiMethod psiMethod : topLevelSourceClass.getMethods()) {
            for(PsiTypeParameter typeParam : psiMethod.getTypeParameters()) {
                final PsiType typeToUse = getTypeToUse(typeParam);
                substitutor = substitutor.put(typeParam, typeToUse);
            }
        }
        classToSubsMap.put(topLevelSourceClass, substitutor);
        final PsiClass superClass = CompiledUtils.getClassWithSourceCode(topLevelSourceClass.getSuperClass());
        if(superClass != null) {
            computeSubsMapForSupersRecursively(topLevelSourceClass, substitutor, superClass);
        }
        for(PsiClass interfaceClass : topLevelSourceClass.getInterfaces()) {
            computeSubsMapForSupersRecursively(topLevelSourceClass, substitutor, CompiledUtils.getClassWithSourceCode(interfaceClass));
        }
    }

    private PsiType getTypeToUse(final PsiTypeParameter typeParam) {
        final PsiClassType[] extendsTypes = typeParam.getExtendsList().getReferencedTypes();
        final PsiType typeToUse;
        if(extendsTypes.length != 0) {
            if(isEnum(extendsTypes[0]) && topLevelSourceClass.isEnum()) {
                typeToUse = javaPsiFacade.getElementFactory().createType(topLevelSourceClass, PsiSubstitutor.EMPTY);
            } else if(Arrays.stream(extendsTypes).anyMatch(this::isComparable)) {
                typeToUse = stringType;
            } else {
                typeToUse = extendsTypes[0];
            }
        } else {
            typeToUse = stringType;
        }
        return typeToUse;
    }

    private boolean isEnum(final PsiClassType extendsType) {
        final PsiClass resolvedClass = extendsType.resolve();
        if(resolvedClass == null) {
            return false;
        }
        return StringUtils.equals(resolvedClass.getQualifiedName(), "java.lang.Enum");
    }

    private boolean isComparable(final PsiClassType extendsType) {
        final PsiClass psiClass = PsiUtil.resolveClassInType(extendsType);
        if(psiClass == null) {
            return false;
        }
        return StringUtils.equals(psiClass.getQualifiedName(), JavaNames.JavaLangComparable);
    }

    private void computeSubsMapForSupersRecursively(final PsiClass sourceClass, final PsiSubstitutor sourceClassSubstitutor, final PsiClass superClass) {
        if(classToSubsMap.containsKey(superClass)) {
            return;
        }
        PsiSubstitutor superClassSubstitutor = javaClassSupers.getSuperClassSubstitutor(superClass, sourceClass, sourceClassResolveScope, sourceClassSubstitutor);
        if(superClassSubstitutor == null) {
            // This shouldn't happen. According to the docs getSuperClassSubstitutor() only returns null if the sourceClass does not inherit from superClass.
            classToSubsMap.put(superClass, PsiSubstitutor.EMPTY);
            return;
        }
        for(final PsiTypeParameter typeParam : superClass.getTypeParameters()) {
            if(superClassSubstitutor.getSubstitutionMap().containsKey(typeParam)) {
                continue;
            }
            final PsiType typeToUse = getTypeToUse(typeParam);
            superClassSubstitutor = superClassSubstitutor.put(typeParam, typeToUse);
        }
        for(final PsiMethod psiMethod : superClass.getMethods()) {
            for(PsiTypeParameter typeParam : psiMethod.getTypeParameters()) {
                final PsiType typeToUse = getTypeToUse(typeParam);
                superClassSubstitutor = superClassSubstitutor.put(typeParam, typeToUse);
            }
        }
        classToSubsMap.put(superClass, superClassSubstitutor);
        final PsiClass nextSuperClass = CompiledUtils.getClassWithSourceCode(superClass.getSuperClass());
        if(nextSuperClass != null) {
            computeSubsMapForSupersRecursively(superClass, superClassSubstitutor, nextSuperClass);
        }
        for(final PsiClass interfaceClass : superClass.getInterfaces()) {
            computeSubsMapForSupersRecursively(superClass, superClassSubstitutor, CompiledUtils.getClassWithSourceCode(interfaceClass));
        }
    }
}
