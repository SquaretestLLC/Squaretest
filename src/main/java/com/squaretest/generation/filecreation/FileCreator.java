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
package com.squaretest.generation.filecreation;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.squaretest.generation.filetemplateutil.CannotCreatePackageException;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface FileCreator {
    @NotNull
    VirtualFile createTestClass(@NotNull final Project project, final FileTemplate template, final PsiDirectory testClassDirectory, final Map<String, Object> templateVars) throws FileAlreadyExistsException, DirectoryNotWritableException, TemplateRenderingException, CannotCreatePackageException, Api.UserCancelledGenerationException;
}
