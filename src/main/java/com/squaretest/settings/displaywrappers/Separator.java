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
package com.squaretest.settings.displaywrappers;

import java.awt.*;
import javax.swing.*;

public class Separator {
    private final JSeparator separator;
    private final JPanel containerPanel;

    public Separator() {
        containerPanel = new JPanel(new BorderLayout(0, 0));
        final Color separatorPanelBackgroundColor = UIManager.getColor("ComboBox.background");
        containerPanel.setBackground(separatorPanelBackgroundColor);
        separator = new JSeparator(JSeparator.HORIZONTAL);
    }

    public void setComponent(final Component component) {
        containerPanel.removeAll();
        containerPanel.setBackground(component.getBackground());
        containerPanel.add(separator, BorderLayout.PAGE_END);
        if(component instanceof final JComponent jComponent) {
            // Remove the border from the component the containerPanel is wrapping. The containerPanel already has a
            // border.
            jComponent.setBorder(BorderFactory.createEmptyBorder());
        }
        containerPanel.add(component, BorderLayout.LINE_START);
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
