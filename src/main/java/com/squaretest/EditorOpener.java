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
package com.squaretest;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.squaretest.settings.store.OpenTestFilePreference;
import com.squaretest.settings.store.SettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EditorOpener {

    private static final Logger Log = Logger.getInstance(EditorOpener.class);

    @NotNull
    private final SettingsProvider settingsProvider;
    @NotNull
    private final Project project;
    @Nullable
    private final EditorWindow editorWindow;

    public EditorOpener(
            @NotNull final SettingsProvider settingsProvider,
            @NotNull final Project project,
            @Nullable final EditorWindow editorWindow) {
        this.settingsProvider = settingsProvider;
        this.project = project;
        this.editorWindow = editorWindow;
    }

    public void openEditor(final VirtualFile fileToOpen) {
        final OpenTestFilePreference pref = settingsProvider.getOpenTestFilePreference();
        if(pref == OpenTestFilePreference.ALWAYS_IN_SAME_EDITOR) {
            openFileInSameEditor(fileToOpen);
            return;
        }
        if(editorWindow != null) {
            final EditorWindow[] siblings = editorWindow.findSiblings();
            if(siblings.length > 0 || pref == OpenTestFilePreference.ALWAYS_IN_NEXT_EDITOR) {
                editorWindow.split(SwingConstants.VERTICAL, false, fileToOpen, true);
            } else {
                openFileInSameEditor(fileToOpen);
            }
        } else {
            // If the EditorWindow is null, just open the file in the same editor.
            Log.warn("Unable to obtain EditorWindow; Squaretest cannot respect the user's preference" +
                    "to open the test in a new editor.");
            openFileInSameEditor(fileToOpen);
        }
    }

    private void openFileInSameEditor(final VirtualFile fileToOpen) {
        new OpenFileDescriptor(project, fileToOpen, 0).navigate(true);
    }
}
