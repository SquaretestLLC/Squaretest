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
package com.squaretest.settings.displaywrappers;

import com.squaretest.settings.store.ModuleAutoConfigSetting;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DisplayModuleAutoConfigSetting {

    @NotNull
    private final ModuleAutoConfigSetting moduleAutoConfigSetting;

    private DisplayModuleAutoConfigSetting(@NotNull final ModuleAutoConfigSetting moduleAutoConfigSetting) {
        this.moduleAutoConfigSetting = moduleAutoConfigSetting;
    }

    public static DisplayModuleAutoConfigSetting fromModuleAutoConfigSetting(
            @NotNull final ModuleAutoConfigSetting moduleAutoConfigSetting) {
        return new DisplayModuleAutoConfigSetting(moduleAutoConfigSetting);
    }

    @Override
    public String toString() {
        return switch(moduleAutoConfigSetting) {
            case ALWAYS_SHOW_CONFIG -> "Always ask to configure module settings";
            case AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE -> "Automatically configure when possible (recommended)";
        };
    }

    @NotNull
    public ModuleAutoConfigSetting getModuleAutoConfigSetting() {
        return moduleAutoConfigSetting;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final DisplayModuleAutoConfigSetting that = (DisplayModuleAutoConfigSetting) o;
        return moduleAutoConfigSetting == that.moduleAutoConfigSetting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleAutoConfigSetting);
    }
}
