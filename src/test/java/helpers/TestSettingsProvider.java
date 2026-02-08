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

import com.squaretest.settings.store.SettingsProvider;

public class TestSettingsProvider extends SettingsProvider {
    private final int dataConstructorLimitWhenBeanOptionAvailable;
    private final int constructorLimitWhenBeanOptionAvailable;
    private final int constructorLimitWhenOtherOptionAvailable;
    private final int constructorLimitFinal;
    private final int maxBuilderMethodsToCall;

    public TestSettingsProvider(
            final int dataConstructorLimitWhenBeanOptionAvailable,
            int constructorLimitWhenBeanOptionAvailable,
            int constructorLimitWhenOtherOptionAvailable,
            int constructorLimitFinal, final int maxBuilderMethodsToCall) {
        this.dataConstructorLimitWhenBeanOptionAvailable = dataConstructorLimitWhenBeanOptionAvailable;
        this.constructorLimitWhenBeanOptionAvailable = constructorLimitWhenBeanOptionAvailable;
        this.constructorLimitWhenOtherOptionAvailable = constructorLimitWhenOtherOptionAvailable;
        this.constructorLimitFinal = constructorLimitFinal;
        this.maxBuilderMethodsToCall = maxBuilderMethodsToCall;
    }

    @Override
    public int getConstructorMaxLength() {
        return dataConstructorLimitWhenBeanOptionAvailable;
    }

    @Override
    public Integer getConstructorLimitWhenBeanOptionAvailable() {
        return constructorLimitWhenBeanOptionAvailable;
    }

    @Override
    public Integer getConstructorLimitWhenOtherOptionAvailable() {
        return constructorLimitWhenOtherOptionAvailable;
    }

    @Override
    public Integer getConstructorLimitFinal() {
        return constructorLimitFinal;
    }

    @Override
    public Integer getMaxNumberOfBuilderMethodsToCall() {
        return maxBuilderMethodsToCall;
    }
}
