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
package com.squaretest.template.impl;

import com.squaretest.confirmsettings.SettingsBroker;
import com.squaretest.template.api.Api;

import java.util.Map;

public class UiUtilsImpl implements Api.UiUtils {

    private final SettingsBroker settingsBroker;

    public UiUtilsImpl(final SettingsBroker settingsBroker) {
        this.settingsBroker = settingsBroker;
    }

    @Override
    public Map<String, Object> askUserToConfirmSettings(
            final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        return settingsBroker.confirmSettingsV1(settingsToConfirm);
    }

    @Override
    public Map<String, Object> askUserToConfirmSettingsV2(
            final Map<String, Object> settingsToConfirm) throws Api.UserCancelledGenerationException {
        return settingsBroker.confirmSettingsV2(settingsToConfirm);
    }
}
