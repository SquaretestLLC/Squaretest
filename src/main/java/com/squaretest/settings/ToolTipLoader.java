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
package com.squaretest.settings;

import com.squaretest.utils.SQIOUtils;

import java.util.HashMap;
import java.util.Map;

public class ToolTipLoader {

    private static ToolTipLoader instance;

    private final Map<String, String> loadedTooltips;

    private ToolTipLoader() {
        loadedTooltips = new HashMap<>();
    }

    public static synchronized ToolTipLoader getInstance() {
        if(instance == null) {
            instance = new ToolTipLoader();
        }
        return instance;
    }

    public String getOpenFilePreferenceToolTip() {
        return loadTextFromResource("/tooltips/OpenFilePreference.html");
    }

    public String getSelectTemplateToolTip() {
        return loadTextFromResource("/tooltips/SelectTemplate.html");
    }

    public String getModuleAutoConfigToolTip() {
        return loadTextFromResource("/tooltips/ModuleAutoConfig.html");
    }

    public String getPromoteToProjectToolTip() {
        return loadTextFromResource("/tooltips/PromoteSettingsToProject.html");
    }

    public String getTestSourcesRootToolTip() {
        return loadTextFromResource("/tooltips/TestSourcesRoot.html");
    }

    public String getDataConstructorLimitToolTip() {
        return loadTextFromResource("/tooltips/DataConstructorLimit.html");
    }

    public String getBeanConstructorLimitToolTip() {
        return loadTextFromResource("/tooltips/BeanConstructorLimit.html");
    }

    public String getOtherOptionConstructorLimitToolTip() {
        return loadTextFromResource("/tooltips/OtherOptionConstructorLimit.html");
    }

    public String getFinalConstructorLimitToolTip() {
        return loadTextFromResource("/tooltips/FinalConstructorLimit.html");
    }

    public String getMaxNumberOfBuilderMethodsToCall() {
        return loadTextFromResource("/tooltips/MaxNumberOfBuilderMethodsToCall.html");
    }

    private String loadTextFromResource(final String pathToResource) {
        return loadedTooltips.computeIfAbsent(pathToResource,
                SQIOUtils::safeLoadResource);
    }
}
