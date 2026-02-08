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

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.roots.AnnotationOrderRootType;
import com.intellij.openapi.roots.JavadocOrderRootType;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SQPsiTestUtil {
    @NotNull
    public static Library addProjectLibrary(@NotNull ModifiableRootModel model,
                                            final String libName,
                                            @NotNull List<? extends VirtualFile> classesRoots,
                                            @NotNull List<? extends VirtualFile> sourceRoots,
                                            @NotNull List<? extends VirtualFile> javaDocs,
                                            @NotNull List<? extends VirtualFile> externalAnnotationsRoots) {

        final String libNameToUse = determineLibName(model, libName);

        LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(model.getProject());
        return WriteAction.computeAndWait(() -> {
            Library library = libraryTable.createLibrary(libNameToUse);
            Library.ModifiableModel libraryModel = library.getModifiableModel();
            try {
                for(VirtualFile root : classesRoots) {
                    libraryModel.addRoot(root, OrderRootType.CLASSES);
                }
                for(VirtualFile root : sourceRoots) {
                    libraryModel.addRoot(root, OrderRootType.SOURCES);
                }
                for(VirtualFile root : javaDocs) {
                    libraryModel.addRoot(root, JavadocOrderRootType.getInstance());
                }
                for(VirtualFile root : externalAnnotationsRoots) {
                    libraryModel.addRoot(root, AnnotationOrderRootType.getInstance());
                }
                libraryModel.commit();
            } catch(Throwable t) {
                libraryModel.dispose();
                throw t;
            }

            model.addLibraryEntry(library);
            OrderEntry[] orderEntries = model.getOrderEntries();
            OrderEntry last = orderEntries[orderEntries.length - 1];
            System.arraycopy(orderEntries, 0, orderEntries, 1, orderEntries.length - 1);
            orderEntries[0] = last;
            model.rearrangeOrderEntries(orderEntries);
            return library;
        });
    }

    private static String determineLibName(final ModifiableRootModel model, final String libName) {
        LibraryTable libraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(model.getProject());
        if(libraryTable.getLibraryByName(libName) != null) {
            for(int index = 0; index < 100000; index++) {
                String candidate = libName + "-" + index;
                if(libraryTable.getLibraryByName(candidate) == null) {
                    return candidate;
                }
            }
        }
        return libName;
    }
}
