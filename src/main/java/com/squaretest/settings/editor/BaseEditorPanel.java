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

import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;
import javax.swing.*;

public class BaseEditorPanel extends JPanel {

    private static final String RootPanelName = "SQTRootPanel";
    private static final Dimension DefaultDimension = new Dimension(900, 500);

    private final int minWidth;
    private final int minHeight;
    private final Dimension minSize;
    private final int horizontalMargin;
    private final int verticalMargin;
    private JPanel rootPanel;

    public BaseEditorPanel(
            final int minWidth, final int minHeight, final int horizontalMargin, final int verticalMargin) {
        super();
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.minSize = new Dimension(minWidth, minHeight);
        this.horizontalMargin = horizontalMargin;
        this.verticalMargin = verticalMargin;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private Dimension getCustomDimensions() {
        initRootPanelIfNeeded();
        if(rootPanel == null) {
            return DefaultDimension;
        }

        final int rootWidth = rootPanel.getWidth();
        final int rootHeight = rootPanel.getHeight();
        final int newWidth = Math.max(minWidth, rootWidth - horizontalMargin);
        final int newHeight = Math.max(minHeight, rootHeight - verticalMargin);
        return new Dimension(newWidth, newHeight);
    }

    private void initRootPanelIfNeeded() {
        if(rootPanel == null) {
            rootPanel = getRootPanel();
            rootPanel.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(final ComponentEvent e) {
                    revalidate();
                }

                @Override
                public void componentMoved(final ComponentEvent e) {

                }

                @Override
                public void componentShown(final ComponentEvent e) {

                }

                @Override
                public void componentHidden(final ComponentEvent e) {

                }
            });
        }
    }

    @Nullable
    private JPanel getRootPanel() {
        for(Container p = getParent(); p != null; p = p.getParent()) {
            if(Objects.equals(p.getName(), RootPanelName)) {
                return (JPanel) p;
            }
        }
        return null;
    }

    @Override
    public Dimension getMinimumSize() {
        return this.minSize;
    }

    @Override
    public Dimension getMaximumSize() {
        return getCustomDimensions();
    }

    @Override
    public Dimension getPreferredSize() {
        return getCustomDimensions();
    }
}
