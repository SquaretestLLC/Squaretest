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
package com.squaretest.settings.editor;

import com.google.common.base.Supplier;
import com.intellij.ide.impl.ProjectUtil;

import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Window;

public class SettingsEditorProvider implements Supplier<JPanel> {

    public static final int DefaultVerticalMargin = 400;

    @Override
    public JPanel get() {
        return new BaseEditorPanel(400, 220, 250, determineVerticalMarginFromMonitorSize());
    }

    private int determineVerticalMarginFromMonitorSize() {
        // Get the dimensions of the screen (monitor) the top-level window is in.
        final Window window = ProjectUtil.getActiveFrameOrWelcomeScreen();
        if (window == null) {
            return DefaultVerticalMargin;
        }
        final GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
        final Rectangle bounds = graphicsConfiguration.getBounds();
        final int displayHeight = bounds.height;
        if (displayHeight <= 800) {
            // Handle large font setting on Mac; 1024x640.
            return 300;
        } else if (displayHeight <= 1000) {
            // Reduce the margin slightly on smaller screens.
            return 350;
        }  else {
            return DefaultVerticalMargin;
        }
    }
}
