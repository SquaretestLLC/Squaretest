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

import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TestLib {
    @NotNull
    private final String libName;
    @NotNull
    private final String pathToLib;
    @Nullable
    private final String pathToSources;

    public TestLib(@NotNull final String libName, @NotNull final String pathToLib, @Nullable final String pathToSources) {
        this.libName = libName;
        this.pathToLib = pathToLib;
        this.pathToSources = pathToSources;
    }

    public TestLib(@NotNull final String libName, @NotNull final String pathToLib) {
        this(libName, pathToLib, null);
    }

    @NotNull
    public String getLibName() {
        return libName;
    }

    public String getContainingFolder() {
        return pathToLib.substring(0, pathToLib.lastIndexOf("/"));
    }

    public String getLibFileName() {
        return pathToLib.substring(pathToLib.lastIndexOf("/") + 1);
    }

    public List<VirtualFile> getLibVirtualFiles() {
        return Collections.singletonList(getLibVirtualFile());
    }

    public List<VirtualFile> getSourcesVirtualFiles() {
        final VirtualFile sourceVirtualFile = getSourcesVirtualFile();
        if(sourceVirtualFile == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(sourceVirtualFile);
    }

    @NotNull
    private VirtualFile getLibVirtualFile() {
        VirtualFile root;
        if(pathToLib.endsWith(".jar")) {
            root = JarFileSystem.getInstance().refreshAndFindFileByPath(pathToLib + "!/");
        } else {
            root = LocalFileSystem.getInstance().refreshAndFindFileByPath(pathToLib);
        }
        assert root != null : "Library root folder not found: " + pathToLib + "!/";
        return root;
    }

    @Nullable
    private VirtualFile getSourcesVirtualFile() {
        if(pathToSources == null) {
            return null;
        }
        VirtualFile root;
        if(pathToSources.endsWith(".jar")) {
            root = JarFileSystem.getInstance().refreshAndFindFileByPath(pathToSources + "!/");
        } else {
            root = LocalFileSystem.getInstance().refreshAndFindFileByPath(pathToSources);
        }
        assert root != null : "Library root folder not found: " + pathToSources + "!/";
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        TestLib testLib = (TestLib) o;
        return libName.equals(testLib.libName) && pathToLib.equals(testLib.pathToLib) && Objects.equals(pathToSources, testLib.pathToSources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libName, pathToLib, pathToSources);
    }
}
