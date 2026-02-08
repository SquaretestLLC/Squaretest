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
package com.squaretest.generation.abstractmethods;

import com.intellij.codeInsight.generation.OverrideImplementExploreUtil;
import com.intellij.codeInsight.generation.PsiMethodMember;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates default implementations for all methods in an abstract class.
 * The default implementations are those you see when you use Idea's quick fix to insert missing methods into an
 * abstract class or interface implementor.
 */
public class AbstractClassInfoProvider {

    private static final Logger Log = Logger.getInstance(AbstractClassInfoProvider.class);

    @NotNull
    public AbstractClassInfo createAbstractClassInfo(@NotNull final PsiClass aClass) {
        if(!aClass.hasModifierProperty(PsiModifier.ABSTRACT)) {
            return new AbstractClassInfo(Collections.emptyList(), null, false);
        }

        final StringBuilder classBodyBuilder = new StringBuilder();
        final Set<String> canonicalNamesToImport = new HashSet<>();
        String classBody;
        final Collection<CandidateInfo> candidateMethodsToImplement = OverrideImplementExploreUtil.getMapToOverrideImplement(aClass, true, false).values();
        Collection<PsiMethodMember> methodsToImplement = candidateMethodsToImplement.stream().map(PsiMethodMember::new).toList();

        try {
            for(PsiMethodMember candidate : methodsToImplement) {
                List<PsiMethod> prototypes =
                        SQOverrideImplementUtil.overrideOrImplementMethod(aClass, candidate.getElement(), candidate.getSubstitutor());
                if(!prototypes.isEmpty()) {
                    final PsiMethod method = prototypes.get(0);
                    classBodyBuilder.append(method.getText());
                    canonicalNamesToImport.addAll(collectRequiredImportsFromMethod(method));
                }
            }
            classBody = addStartingAndEndingBraces(classBodyBuilder);
        } catch(final IncorrectOperationException e) {
            classBody = addStartingAndEndingBraces(classBodyBuilder);
            Log.info("Squaretest encountered unexpected exception while creating method stubs for abstract class", e);
        }
        return new AbstractClassInfo(canonicalNamesToImport, classBody, true);
    }

    private String addStartingAndEndingBraces(final StringBuilder classBodyBuilder) {
        if(classBodyBuilder.length() <= 0) {
            return "{}";
        } else {
            return "{\n" + classBodyBuilder + "}";
        }
    }

    private Collection<? extends String> collectRequiredImportsFromMethod(final PsiMethod method) {
        final List<String> canonicalNamesRequired = new ArrayList<>();
        final PsiClass methodContainingClass = method.getContainingClass();
        if(methodContainingClass != null) {
            final PsiFile methodContainingFile = methodContainingClass.getContainingFile();
            if(methodContainingFile instanceof final PsiJavaFile methodContainingJavaFile) {
                final PsiImportList importList = methodContainingJavaFile.getImportList();
                if(importList != null) {
                    for(final PsiImportStatement importStatement : importList.getImportStatements()) {
                        canonicalNamesRequired.add(importStatement.getQualifiedName());
                    }
                }
            }
        }
        return canonicalNamesRequired;
    }
}
