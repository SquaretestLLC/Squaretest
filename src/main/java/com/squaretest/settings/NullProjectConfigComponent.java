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

import javax.swing.*;

public class NullProjectConfigComponent implements ProjectConfigUi {

    private JPanel rootPanel;

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void initUiComponentsRequiringAWTThread() {
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public void apply() {
    }

    @Override
    public int getLongestLeftLabelSize() {
        return 0;
    }

    @Override
    public void setMinimumLeftLabelWidth(final int minWidth) {
    }

    @Override
    public void disposeUIResources() {
    }

    @Override
    public void initUiComponentsRequiringJPane() {
    }

    @Override
    public void refreshTemplates() {
    }
}
