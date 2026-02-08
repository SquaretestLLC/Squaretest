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
package com.squaretest.confirmsettings.v1;

import com.squaretest.template.api.Api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Utils {

    public static boolean safeGetShouldSetPackageLocalFields(final Map<String, Object> settingsToConfirm) {
        final Object settingObj = settingsToConfirm.get("shouldSetPackageLocalFields");
        if(settingObj instanceof Boolean) {
            return (Boolean) settingObj;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static List<? extends Api.Variable> safeGetDependencies(final Map<String, Object> settingsToConfirm) {
        try {
            return (List<? extends Api.Variable>) settingsToConfirm.get("dependencies");
        } catch(final ClassCastException ex) {
            return Collections.emptyList();
        }
    }
}
