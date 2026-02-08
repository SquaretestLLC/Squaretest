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

import com.intellij.ide.impl.ProjectUtil;

import java.awt.*;
import java.util.function.Supplier;
import javax.swing.*;

public class JITEditorProvider implements Supplier<JPanel> {
    private static final int StandardEditorWidth = 900;
    private static final int StandardEditorHeight = 500;

    @Override
    public JPanel get() {
        final int minHeight = determineHeightFromMonitorSize();
        return new BaseEditorPanel(StandardEditorWidth, minHeight, 10, 10);
    }

    private int determineHeightFromMonitorSize() {
        // Get the dimensions of the screen (monitor) the top level window is in.
        final Window window = ProjectUtil.getActiveFrameOrWelcomeScreen();
        if(window == null) {
            return StandardEditorHeight;
        }
        final GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
        final Rectangle bounds = graphicsConfiguration.getBounds();
        final int displayHeight = bounds.height;
        if(displayHeight <= 700) {
            // Handle large font setting on mac; 1024x640.
            return 220;
        } else if(displayHeight <= 800) {
            return 350;
        } else if(displayHeight <= 1000) {
            return 450;
        } else {
            return StandardEditorHeight;
        }
    }

}
