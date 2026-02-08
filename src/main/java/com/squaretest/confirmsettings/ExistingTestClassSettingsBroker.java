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
package com.squaretest.confirmsettings;

import com.squaretest.confirmsettings.v1.ExistingTestClassSettingsConfirmerV1;
import com.squaretest.confirmsettings.v2.ExistingTestClassSettingsConfirmerV2;

import java.util.Map;

public class ExistingTestClassSettingsBroker implements SettingsBroker {
    private final ExistingTestClassSettingsConfirmerV1 existingTestClassSettingsConfirmerV1;
    private final ExistingTestClassSettingsConfirmerV2 existingTestClassSettingsConfirmerV2;

    public ExistingTestClassSettingsBroker(
            final ExistingTestClassSettingsConfirmerV1 existingTestClassSettingsConfirmerV1,
            final ExistingTestClassSettingsConfirmerV2 existingTestClassSettingsConfirmerV2) {
        this.existingTestClassSettingsConfirmerV1 = existingTestClassSettingsConfirmerV1;
        this.existingTestClassSettingsConfirmerV2 = existingTestClassSettingsConfirmerV2;
    }

    @Override
    public Map<String, Object> confirmSettingsV1(final Map<String, Object> settingsToConfirm) {
        return existingTestClassSettingsConfirmerV1.confirmSettings(settingsToConfirm);
    }

    @Override
    public Map<String, Object> confirmSettingsV2(final Map<String, Object> settingsToConfirm) {
        return existingTestClassSettingsConfirmerV2.confirmSettings(settingsToConfirm);
    }
}
