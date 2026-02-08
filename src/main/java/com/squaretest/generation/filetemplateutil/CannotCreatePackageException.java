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
package com.squaretest.generation.filetemplateutil;

public class CannotCreatePackageException extends CreateTestCheckedException {
    private final String packageName;
    private final String sourceFolder;

    public CannotCreatePackageException(final String packageName, final String sourceFolder,
                                        final Exception cause) {
        super(cause);
        this.packageName = packageName;
        this.sourceFolder = sourceFolder;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getSourceFolder() {
        return sourceFolder;
    }
}
