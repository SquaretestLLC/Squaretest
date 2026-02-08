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
package com.myapp;

import java.util.Collections;
import java.util.Map;

public abstract class Action {
    private final String actionId;
    private final String actionGroup;
    protected final Map<String, String> params;

    public Action() {
        this("DefaultId", "DefaultGroup", Collections.emptyMap());
    }

    protected Action(final String actionId, final String actionGroup, final Map<String, String> params) {
        this.actionId = actionId;
        this.actionGroup = actionGroup;
        this.params = params;
    }

    protected abstract void doAction();
    public void perform() {
        doAction();
    }
    protected void cleanUp(){}
}
