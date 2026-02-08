/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package helpers;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.LanguageLevelModuleExtension;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SquaretestProjectDescriptor extends DefaultLightProjectDescriptor {

    @NotNull
    private final Set<TestLib> testLibs;
    @NotNull
    private final LanguageLevel languageLevel;

    @SafeVarargs
    public SquaretestProjectDescriptor(@NotNull final List<TestLib>... testLibGroups) {
        this(LanguageLevel.JDK_1_8, testLibGroups);
    }

    @SafeVarargs
    public SquaretestProjectDescriptor(@NotNull final LanguageLevel languageLevel, @NotNull final List<TestLib>... testLibGroups) {
        testLibs = new HashSet<>();
        for(final List<TestLib> libGroup : testLibGroups) {
            testLibs.addAll(libGroup);
        }
        this.languageLevel = languageLevel;
    }

    @Override
    public void configureModule(@NotNull final Module module, @NotNull final ModifiableRootModel model, @NotNull final ContentEntry contentEntry) {
        addJarsToModule(module, model);
        super.configureModule(module, model, contentEntry);
        LanguageLevelModuleExtension extension = model.getModuleExtension(LanguageLevelModuleExtension.class);
        if(extension != null) {
            extension.setLanguageLevel(languageLevel);
        }
    }

    /**
     * Copied loosely from AbstractJavaFXTestCase.addJavaFxJarAsLibrary. This adds the jars in {@link #testLibs} as libraries to the
     * given module and model.
     *
     * @param module the module
     * @param model  the model
     */
    private void addJarsToModule(@NotNull Module module, @Nullable ModifiableRootModel model) {
        for(final TestLib testLib : testLibs) {
            if(model != null) {
                SQPsiTestUtil.addProjectLibrary(model, testLib.getLibName(), testLib.getLibVirtualFiles(), testLib.getSourcesVirtualFiles(), Collections.emptyList(), Collections.emptyList());
            } else {
                PsiTestUtil.addLibrary(module, testLib.getLibName(), testLib.getContainingFolder(), testLib.getLibFileName());
            }
        }
    }

    @Override
    public Sdk getSdk() {
        if(this.languageLevel.isAtLeast(LanguageLevel.JDK_18)) {
            final String java21Home = System.getenv("JAVA_21_HOME");
            return JavaSdk.getInstance().createJdk("java 21", java21Home, false);
        }
        if(this.languageLevel.isAtLeast(LanguageLevel.JDK_12)) {
            final String java17Home = System.getenv("JAVA_17_HOME");
            return JavaSdk.getInstance().createJdk("java 17", java17Home, false);
        }
        final String java11Home = System.getenv("JAVA_11_HOME");
        return JavaSdk.getInstance().createJdk("java 11", java11Home, false);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        SquaretestProjectDescriptor that = (SquaretestProjectDescriptor) o;
        return testLibs.equals(that.testLibs) && languageLevel == that.languageLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testLibs, languageLevel);
    }
}
