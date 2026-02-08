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
package com.squaretest.velocityglobalvars;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiType;
import com.intellij.psi.impl.FakePsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import com.intellij.velocity.VtlGlobalVariableProvider;
import com.intellij.velocity.java.reference.VtlPsiType;
import com.intellij.velocity.psi.VtlVariable;
import com.intellij.velocity.psi.VtlVariableType;
import com.intellij.velocity.psi.files.VtlFile;
import com.intellij.velocity.psi.files.VtlFileType;
import com.squaretest.TemplateProvider;
import com.squaretest.settings.editor.EditorCreator;
import com.squaretest.template.api.TemplateVariableNames;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.swing.*;

public class SquaretestVelocityVariableProvider extends VtlGlobalVariableProvider {

    private static final Logger Log = Logger.getInstance(SquaretestVelocityVariableProvider.class);

    public SquaretestVelocityVariableProvider() {
    }

    @NotNull
    @Override
    public Collection<VtlVariable> getGlobalVariables(@NotNull final VtlFile vtlFile) {
        Log.debug("getGlobalVariables() called");

        // Avoid providing global variables for velocity files that aren't ours.
        final String name = vtlFile.getName();
        if(!(name.startsWith(EditorCreator.SQTempFileNameWithoutExtension)
                || name.startsWith(TemplateProvider.SQTemplatePrefixJava)
                || name.startsWith(TemplateProvider.SQTemplatePrefixGroovy))) {
            // If the file is not the temp file we create for the settings editor and the user is editing one of our template files that he/she created,
            // return.
            return Collections.emptyList();
        }

        final Collection<VtlVariable> result = new ArrayList<>();
        result.add(new MyVtlVariable(TemplateVariableNames.ClassUnderTest, vtlFile, "Api.SourceClass"));
        result.add(new MyVtlVariable(TemplateVariableNames.ImportList, vtlFile, "java.util.Set<String>"));
        result.add(new MyVtlVariable(TemplateVariableNames.ListUtils, vtlFile, "Api.ListUtils"));
        result.add(new MyVtlVariable(TemplateVariableNames.StringUtils, vtlFile, "Api.StringUtils"));
        result.add(new MyVtlVariable(TemplateVariableNames.CodeStyleUtils, vtlFile, "Api.CodeStyleUtils"));
        result.add(new MyVtlVariable(TemplateVariableNames.ClassUtils, vtlFile, "Api.ClassUtils"));
        result.add(new MyVtlVariable(TemplateVariableNames.MutableInt, vtlFile, "Api.MutableIntFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.MutableBoolean, vtlFile, "Api.MutableBooleanFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.MutableString, vtlFile, "Api.MutableStringFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.MutableObject, vtlFile, "Api.MutableObjectFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.UiUtils, vtlFile, "Api.UiUtils"));
        result.add(new MyVtlVariable(TemplateVariableNames.FluentList, vtlFile, "Api.FluentListFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.AltInfo, vtlFile, "Api.AltInfoFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.TestInfo, vtlFile, "Api.TestInfoFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.BeanContext, vtlFile, "Api.BeanContextFactory"));
        result.add(new MyVtlVariable(TemplateVariableNames.Newline, vtlFile, "java.lang.String"));
        result.add(new MyVtlVariable(TemplateVariableNames.ShouldAskToConfirmMocks, vtlFile, "boolean"));
        result.add(new MyVtlVariable(TemplateVariableNames.IsCreatingNewTest, vtlFile, "boolean"));
        result.add(new MyVtlVariable(TemplateVariableNames.SpringWebMvcTestIncludesSpringExtension, vtlFile, "boolean"));

        return result;
    }

    private static class MyVtlVariable extends FakePsiElement implements VtlVariable {

        private final VtlVariableType myType;
        private final String name;
        private final PsiElement parent;

        private MyVtlVariable(final String name, final PsiElement parent, final String fqnClassName) {
            this.name = name;
            this.parent = parent;
            this.myType = VtlPsiType.of(createType(parent, fqnClassName));
        }

        private PsiType createType(final PsiElement parent, final String fqnClassName) {
            try {
                final PsiFile inMemoryFile = InMemoryFileCreator.getInstance().getOrCreateInMemoryFileForProject(parent.getProject());
                return JavaPsiFacade.getInstance(parent.getProject()).getElementFactory().createTypeFromText(fqnClassName, inMemoryFile);
            } catch(IncorrectOperationException e) {
                Log.error("Got exception creating type for fqn=" + fqnClassName, e);
                return null;
            }
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Icon getIcon(final boolean b) {
            return null;
        }

        @NotNull
        @Override
        public SearchScope getUseScope() {
            return GlobalSearchScope.getScopeRestrictedByFileTypes((GlobalSearchScope) super.getUseScope(), VtlFileType.INSTANCE);
        }

        @NotNull
        @Override
        public PsiElement getNavigationElement() {
            return parent;
        }

        @Nullable
        @Override
        public VtlVariableType getPsiType() {
            return myType;
        }

        @Nullable
        @Override
        public PsiComment getDocComment() {
            return null;
        }

        @Override
        public PsiElement getParent() {
            return parent;
        }

        @Override
        public boolean equals(final Object obj) {
            if(obj instanceof final MyVtlVariable otherVar) {
                return Objects.equals(otherVar.getName(), name) && Objects.equals(otherVar.getParent(), parent);
            }
            return false;
        }
    }
}
