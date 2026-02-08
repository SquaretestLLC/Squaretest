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
package com.squaretest.generation.dependencyinteraction.followup;

import com.intellij.psi.PsiLocalVariable;
import com.squaretest.generation.dependencyinteraction.outcomes.ReturnOutcome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;

public class LocalVariablesInfo {
    @Nullable
    private final LocalVariable primaryLocalVariable;
    @NotNull
    private final LinkedHashMap<PsiLocalVariable, LocalVariable> secondaryLocalVariablesMap;

    public LocalVariablesInfo() {
        this(null);
    }

    public LocalVariablesInfo(@Nullable final LocalVariable primaryLocalVariable) {
        this.primaryLocalVariable = primaryLocalVariable;
        this.secondaryLocalVariablesMap = new LinkedHashMap<>();
    }

    @Nullable
    public LocalVariable getPrimaryLocalVariable() {
        return primaryLocalVariable;
    }

    public LocalVariable getVariable(final PsiLocalVariable psiLocalVariable) {
        if(psiLocalVariable == null) {
            return null;
        }
        if(primaryLocalVariable == null) {
            return null;
        }
        if(primaryLocalVariable.getPsiLocalVariable() == psiLocalVariable) {
            return primaryLocalVariable;
        }
        return secondaryLocalVariablesMap.get(psiLocalVariable);
    }

    public void putVariable(final PsiLocalVariable leftVariable, final ReturnOutcome returnOutcome) {
        if(primaryLocalVariable != null && primaryLocalVariable.getPsiLocalVariable() == leftVariable) {
            primaryLocalVariable.setCurrentValue(returnOutcome);
            return;
        }
        final LocalVariable existingLocalVar = secondaryLocalVariablesMap.get(leftVariable);
        if(existingLocalVar != null) {
            existingLocalVar.setCurrentValue(returnOutcome);
            return;
        }
        secondaryLocalVariablesMap.put(leftVariable, new LocalVariable(leftVariable, returnOutcome));
    }

    public boolean isPrimaryLocalVariable(final PsiLocalVariable leftVariable) {
        return primaryLocalVariable != null && primaryLocalVariable.getPsiLocalVariable() == leftVariable;
    }
}
