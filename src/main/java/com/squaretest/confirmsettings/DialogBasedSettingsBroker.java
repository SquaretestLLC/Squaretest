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

import com.squaretest.confirmsettings.v1.DialogBasedSettingsConfirmerV1;
import com.squaretest.confirmsettings.v2.DialogBasedSettingsConfirmerV2;
import com.squaretest.template.api.Api;

import java.util.Map;

public class DialogBasedSettingsBroker implements SettingsBroker {
    private final DialogBasedSettingsConfirmerV1 dialogBasedSettingsConfirmerV1;
    private final DialogBasedSettingsConfirmerV2 dialogBasedSettingsConfirmerV2;

    public DialogBasedSettingsBroker(
            final DialogBasedSettingsConfirmerV1 dialogBasedSettingsConfirmerV1,
            final DialogBasedSettingsConfirmerV2 dialogBasedSettingsConfirmerV2) {
        this.dialogBasedSettingsConfirmerV1 = dialogBasedSettingsConfirmerV1;
        this.dialogBasedSettingsConfirmerV2 = dialogBasedSettingsConfirmerV2;
    }

    @Override
    public Map<String, Object> confirmSettingsV1(final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        return dialogBasedSettingsConfirmerV1.confirmSettings(settingsToConfirm);
    }

    @Override
    public Map<String, Object> confirmSettingsV2(final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        return dialogBasedSettingsConfirmerV2.confirmSettings(settingsToConfirm);
    }
}
