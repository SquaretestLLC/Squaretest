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

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.codeInsight.generation.GenerateMembersUtil;
import com.intellij.codeInsight.generation.OverrideImplementUtil;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.ide.fileTemplates.JavaTemplateUtil;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.Consumer;
import com.intellij.util.IncorrectOperationException;
import com.squaretest.generation.dependencyinteraction.followup.SQPsiUtil;
import com.squaretest.generation.filetemplateutil.SQFileTemplateUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Largely copied from {@link OverrideImplementUtil}.
 */
public class SQOverrideImplementUtil {

    private static final String IMPLEMENT_COMMAND_MARKER = "implement";
    private static final String ImplementMethodBodyJavaTemplateText = "#if ( $RETURN_TYPE != \"void\" )return $DEFAULT_RETURN_VALUE;#end";
    private static final String OverriddenMethodBodyJavaTemplateText = "$CALL_SUPER;";

    /**
     * This is the only method we care about.
     */
    static List<PsiMethod> overrideOrImplementMethod(
            PsiClass aClass, PsiMethod method, PsiSubstitutor substitutor) throws IncorrectOperationException {
        if(!method.isValid() || !substitutor.isValid()) {
            return Collections.emptyList();
        }
        if(aClass.getLanguage() != JavaLanguage.INSTANCE) {
            // aClass might be a Kotlin class. This happens when the user is trying to add test methods to a test class
            // that is written in Java for a source class that is written in Kotlin. Surprisingly, Squaretest mostly works
            // when you invoke it on a Kotlin file. This is the one part that fails. Calling GenerateMembersUtil.substituteGenericMethod(...)
            // with a Kotlin class triggers an assert failure in JVMElementFactories.requireFactory(). In this case, just return
            // an empty list. The user will still need to add methods to the abstract class, but that's better than crashing and
            // not generating a test class.
            return Collections.emptyList();
        }
        List<PsiMethod> results = new ArrayList<>();
        final PsiMethod method1 = GenerateMembersUtil.substituteGenericMethod(method, substitutor, aClass);

        final PsiElementFactory factory = JavaPsiFacade.getInstance(method.getProject()).getElementFactory();
        final PsiMethod result = (PsiMethod) factory.createClass("Dummy").add(method1);
        if(PsiUtil.isAnnotationMethod(result)) {
            PsiAnnotationMemberValue defaultValue = ((PsiAnnotationMethod) result).getDefaultValue();
            if(defaultValue != null) {
                PsiElement defaultKeyword = defaultValue;
                while(!(defaultKeyword instanceof PsiKeyword) && defaultKeyword != null) {
                    defaultKeyword = defaultKeyword.getPrevSibling();
                }
                if(defaultKeyword == null) {
                    defaultKeyword = defaultValue;
                }
                defaultValue.getParent().deleteChildRange(defaultKeyword, defaultValue);
            }
        }
        Consumer<PsiMethod> decorator = createDefaultDecorator(aClass, method, false, true);
        decorator.consume(result);
        results.add(result);

        // This is the reason this class exists. OverrideImplementUtil removes methods that exist in the class in question.
        // This makes sense for the cases where the user needs to add missing abstract methods -- we don't want to add
        // stub implementations for methods already in the class, only the missing ones.
        // This does not work for us; though, because the aClass IS the class that contains the abstract methods.
        // We  might need this to avoid generating stubs for abstract methods from a higher-level class that are
        // implemented by aClass.
//        for (Iterator<PsiMethod> iterator = results.iterator(); iterator.hasNext();) {
//            if (aClass.findMethodBySignature(iterator.next(), false) != null) {
//                iterator.remove();
//            }
//        }

        // Another reason this class exists is: we're using Velocity-2.x instead of Velocity-1.x.
        // We need to redirect all calls that invoke velocity to SQFileTemplateUtil instead of FileTemplateUtil.
        // FileTemplateUtil is built against Velocity-1.7.x and will crash when used with velocity-2.x.

        return results;
    }

    private static Consumer<PsiMethod> createDefaultDecorator(
            final PsiClass aClass, final PsiMethod method, final boolean toCopyJavaDoc,
            final boolean insertOverrideIfPossible) {
        return result -> decorateMethod(aClass, method, toCopyJavaDoc, insertOverrideIfPossible, result);
    }

    private static PsiMethod decorateMethod(
            PsiClass aClass, PsiMethod method, boolean toCopyJavaDoc, boolean insertOverrideIfPossible,
            PsiMethod result) {
        PsiUtil.setModifierProperty(result, PsiModifier.ABSTRACT, aClass.isInterface() && method.hasModifierProperty(PsiModifier.ABSTRACT));
        PsiUtil.setModifierProperty(result, PsiModifier.NATIVE, false);

        if(!toCopyJavaDoc) {
            deleteDocComment(result);
        }

        //method type params are not allowed when overriding from raw type
        final PsiTypeParameterList list = result.getTypeParameterList();
        if(list != null) {
            final PsiClass containingClass = method.getContainingClass();
            if(containingClass != null) {
                for(PsiClassType classType : aClass.getSuperTypes()) {
                    if(InheritanceUtil.isInheritorOrSelf(PsiUtil.resolveClassInType(classType), containingClass, true) && classType.isRaw()) {
                        list.replace(JavaPsiFacade.getElementFactory(aClass.getProject()).createTypeParameterList());
                        break;
                    }
                }
            }
        }

        annotateOnOverrideImplement(result, aClass, method, insertOverrideIfPossible);

        if(method.hasModifierProperty(PsiModifier.SYNCHRONIZED)) {
            result.getModifierList().setModifierProperty(PsiModifier.SYNCHRONIZED, true);
        }

        final PsiCodeBlock body = JavaPsiFacade.getInstance(method.getProject()).getElementFactory().createCodeBlockFromText("{}", null);
        PsiCodeBlock oldBody = result.getBody();
        if(oldBody != null) {
            oldBody.replace(body);
        } else {
            result.add(body);
        }

        setupMethodBody(result, method, aClass);
        return result;
    }

    private static void deleteDocComment(PsiMethod result) {
        PsiDocComment comment = result.getDocComment();
        if(comment != null) {
            comment.delete();
        }
    }

    private static void annotateOnOverrideImplement(
            PsiMethod method, PsiClass targetClass, PsiMethod overridden, boolean insertOverride) {
        if(insertOverride && canInsertOverride(overridden, targetClass)) {
            final String overrideAnnotationName = Override.class.getName();
            if(!AnnotationUtil.isAnnotated(method, overrideAnnotationName, AnnotationUtil.CHECK_TYPE)) {
                addPhysicalAnnotation(overrideAnnotationName, PsiNameValuePair.EMPTY_ARRAY, method.getModifierList());
            }
        }
    }

    /**
     * Copied from AddAnnotationPsiFix.addPhysicalAnnotation(...), because that will be removed in 2020.3.
     */
    private static PsiAnnotation addPhysicalAnnotation(final String fqn, final PsiNameValuePair[] pairs, final PsiModifierList modifierList) {
        PsiAnnotation inserted = modifierList.addAnnotation(fqn);
        for(PsiNameValuePair pair : pairs) {
            inserted.setDeclaredAttributeValue(pair.getName(), pair.getValue());
        }
        return inserted;
    }

    private static boolean canInsertOverride(PsiMethod superMethod, PsiClass targetClass) {
        if(superMethod.isConstructor() || superMethod.hasModifierProperty(PsiModifier.STATIC)) {
            return false;
        }
        if(!PsiUtil.isLanguageLevel5OrHigher(targetClass)) {
            return false;
        }
        if(SQPsiUtil.isLanguageLevel6OrHigher(targetClass)) {
            return true;
        }
        PsiClass superClass = superMethod.getContainingClass();
        return superClass != null && !superClass.isInterface();
    }

    @NotNull
    private static String callSuper(PsiMethod superMethod, PsiMethod overriding) {
        @NonNls StringBuilder buffer = new StringBuilder();
        if(!superMethod.isConstructor() && !PsiTypes.voidType().equals(superMethod.getReturnType())) {
            buffer.append("return ");
        }
        buffer.append("super");
        PsiParameter[] parameters = overriding.getParameterList().getParameters();
        if(!superMethod.isConstructor()) {
            buffer.append(".");
            buffer.append(superMethod.getName());
        }
        buffer.append("(");
        for(int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            if(i > 0) {
                buffer.append(",");
            }
            buffer.append(name);
        }
        buffer.append(")");
        return buffer.toString();
    }

    private static void setupMethodBody(
            PsiMethod result, PsiMethod originalMethod, PsiClass targetClass) throws IncorrectOperationException {
        boolean isAbstract = originalMethod.hasModifierProperty(PsiModifier.ABSTRACT) || originalMethod.hasModifierProperty(PsiModifier.DEFAULT);
        String templateText = isAbstract ? ImplementMethodBodyJavaTemplateText : OverriddenMethodBodyJavaTemplateText;
        setupMethodBody(result, originalMethod, targetClass, templateText);
    }

    private static void setupMethodBody(
            final PsiMethod result, final PsiMethod originalMethod, final PsiClass targetClass,
            final String templateText) throws IncorrectOperationException {
        if(targetClass.isInterface()) {
            if(isImplementInterfaceInJava8Interface(targetClass) || originalMethod.hasModifierProperty(PsiModifier.DEFAULT)) {
                PsiUtil.setModifierProperty(result, PsiModifier.DEFAULT, true);
            } else {
                final PsiCodeBlock body = result.getBody();
                if(body != null) {
                    body.delete();
                }
            }
        }
        FileType fileType = JavaFileType.INSTANCE;
        PsiType returnType = result.getReturnType();
        if(returnType == null) {
            returnType = PsiTypes.voidType();
        }
        Properties properties = new Properties();
        properties.setProperty(FileTemplate.ATTRIBUTE_RETURN_TYPE, returnType.getPresentableText());
        properties.setProperty(FileTemplate.ATTRIBUTE_DEFAULT_RETURN_VALUE, PsiTypesUtil.getDefaultValueOfType(returnType));
        properties.setProperty(FileTemplate.ATTRIBUTE_CALL_SUPER, callSuper(originalMethod, result));
        JavaTemplateUtil.setClassAndMethodNameProperties(properties, targetClass, result);

        JVMElementFactory factory = JVMElementFactories.getFactory(targetClass.getLanguage(), originalMethod.getProject());
        if(factory == null) {
            factory = JavaPsiFacade.getInstance(originalMethod.getProject()).getElementFactory();
        }
        @NonNls String methodText;

        try {
            methodText = "void foo () {\n" + SQFileTemplateUtil.mergeTemplate(properties, templateText) + "\n}";
            methodText = FileTemplateUtil.indent(methodText, result.getProject(), fileType);
        } catch(Exception e) {
            throw new IncorrectOperationException("Failed to parse file template", (Throwable) e);
        }
        if(methodText != null) {
            PsiMethod m;
            try {
                m = factory.createMethodFromText(methodText, originalMethod);
            } catch(IncorrectOperationException e) {
                return;
            }
            PsiCodeBlock oldBody = result.getBody();
            if(oldBody != null) {
                oldBody.replace(m.getBody());
            }
        }
    }

    private static boolean isImplementInterfaceInJava8Interface(PsiClass targetClass) {
        if(!PsiUtil.isLanguageLevel8OrHigher(targetClass)) {
            return false;
        }
        String commandName = CommandProcessor.getInstance().getCurrentCommandName();
        return commandName != null && StringUtil.containsIgnoreCase(commandName, IMPLEMENT_COMMAND_MARKER);
    }
}
