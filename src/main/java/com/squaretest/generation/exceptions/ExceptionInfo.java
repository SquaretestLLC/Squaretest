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
package com.squaretest.generation.exceptions;

import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ExceptionInfo {
    @NotNull
    private final List<PsiType> declaredExceptions;
    @NotNull
    private final List<PsiType> javadocExceptions;
    @NotNull
    private final List<PsiType> undeclaredExceptions;
    @NotNull
    private final List<PsiType> allExceptions;

    public ExceptionInfo(
            @NotNull final List<PsiType> declaredExceptions,
            @NotNull final List<PsiType> javadocExceptions,
            @NotNull final List<PsiType> undeclaredExceptions) {
        this.declaredExceptions = declaredExceptions;
        this.javadocExceptions = javadocExceptions;
        this.undeclaredExceptions = undeclaredExceptions;
        allExceptions = new ArrayList<>();
        final Set<String> exceptionNames = new HashSet<>();
        Stream.of(declaredExceptions, javadocExceptions, undeclaredExceptions).flatMap(Collection::stream).forEach(psiType -> {
            if(exceptionNames.add(psiType.getCanonicalText())) {
                allExceptions.add(psiType);
            }
        });
    }

    @NotNull
    public List<PsiType> getDeclaredExceptions() {
        return declaredExceptions;
    }

    @NotNull
    public List<PsiType> getJavadocExceptions() {
        return javadocExceptions;
    }

    @NotNull
    public List<PsiType> getUndeclaredExceptions() {
        return undeclaredExceptions;
    }

    @NotNull
    public List<PsiType> getAllExceptions() {
        return allExceptions;
    }
}
