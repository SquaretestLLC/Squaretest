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
package helpers.uiUtils;

import com.squaretest.confirmsettings.v2.Utils;
import com.squaretest.template.api.Api;

import java.util.List;
import java.util.Map;

import static com.squaretest.confirmsettings.v2.SelectMocksTable.canMock;

public class SelectNoDepsAndAllMethodsUiUtils implements Api.UiUtils {
    @Override
    public Map<String, Object> askUserToConfirmSettings(final Map<String, Object> settingsToConfirm) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Map<String, Object> askUserToConfirmSettingsV2(final Map<String, Object> settingsToConfirm) {
        final List<? extends Api.Variable> variables = Utils.safeGetDependencies(settingsToConfirm);
        final List<? extends Api.Variable> possibleDependencies = Utils.safeGetPossibleDependencies(settingsToConfirm);
        final List<? extends Api.Method> methods = Utils.safeGetMethodsThatCanBeTested(settingsToConfirm);
        updateVars(variables);
        updateVars(possibleDependencies);
        for(final Api.Method method : methods) {
            method.setShouldTest(true);
        }
        return settingsToConfirm;
    }

    private void updateVars(final List<? extends Api.Variable> variables) {
        for(final Api.Variable variable : variables) {
            if(canMock(variable)) {
                variable.setShouldBeMocked(false);
            }
        }
    }
}
