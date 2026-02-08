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

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ui.BrowserHyperlinkListener;
import com.intellij.ui.awt.RelativePoint;
import com.squaretest.Icons;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Contains the ? icon that shows a tooltip when clicked.
 */
public class SQHelpIcon extends JLabel {

    private static final Logger Log = Logger.getInstance(SQHelpIcon.class);
    /**
     * The click event comes in after the window containing the previous notification is closed.
     * This means, the click event handler does not have a good way to check to see if the previous notification
     * is showing in order to decide if it should show a notification. The only way I can find to detect this is:
     * store the time when the previous notification window was closed; then, compare that to the time the click
     * event was dispatched. If they are less than TipCloseClickProcessedDebounceTimeMs MS apart,
     * assume the click is what caused the window to lose focus and close. Do not show a tooltip.
     */
    private static final long TipCloseClickProcessedDebounceTimeMs = 300;
    private static final long WaitingForTipDismissal = -1;

    private long previousTipNotificationDismissTime;
    private Window tipWindow;
    private CombinedAdapter windowListener;

    /**
     * Constructs the {@link SQHelpIcon}.
     *
     * @param toolTipText the html text to show when the icon is clicked.
     */
    public SQHelpIcon(@NotNull final String toolTipText) {
        super();
        setHorizontalAlignment(LEFT);
        setIcon(Icons.Help);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {

                Log.debug("onClick() received at " + System.currentTimeMillis());
                // Ignore the click if its right after the previous notification was closed.
                final long now = System.currentTimeMillis();
                final long diff = Math.abs(now - previousTipNotificationDismissTime);
                if(diff <= TipCloseClickProcessedDebounceTimeMs
                        || (tipWindow != null && tipWindow.isShowing())) {
                    Log.debug("ignoring click that caused tooltip to close; diff=" + diff);
                    return;
                } else {
                    Log.debug("diff=" + diff);
                }

                if(previousTipNotificationDismissTime == WaitingForTipDismissal) {
                    Log.debug("We haven't received the window-dismissed notification from the previous tip; ignoring click");
                    return;
                }

                final HintManager hintManager = HintManager.getInstance();
                final JTextPane tipComponent = new JTextPane();
                tipComponent.setContentType("text/html");
                tipComponent.setText(toolTipText);
                tipComponent.setOpaque(true);
                tipComponent.setBackground(new Color(255, 255, 204));
                tipComponent.setEditable(false);
                tipComponent.addHyperlinkListener(new BrowserHyperlinkListener());
                addBorderIfNeeded(tipComponent);

                hintManager.showHint(tipComponent, RelativePoint.getNorthEastOf(SQHelpIcon.this),
                        HintManager.HIDE_BY_ANY_KEY | HintManager.HIDE_BY_TEXT_CHANGE | HintManager.HIDE_BY_SCROLLING, 0);

                removeOldWindowListenerIfNeeded();
                tipWindow = SwingUtilities.getWindowAncestor(tipComponent);
                // This should never be null, but its always good to check.
                // Sometimes the listener is not called. Reset the timer here.
                if(tipWindow != null) {
                    previousTipNotificationDismissTime = WaitingForTipDismissal;
                    windowListener = new CombinedAdapter(tipWindow);
                }
            }
        });
    }

    private void removeOldWindowListenerIfNeeded() {
        if(tipWindow != null && windowListener != null) {
            tipWindow.removeWindowListener(windowListener);
            tipWindow.removeComponentListener(windowListener);
            windowListener = null;
        }
    }

    /**
     * Listens for all window-related events necessary to detect when a tooltip we've previously shown is hidden.
     */
    private class CombinedAdapter extends WindowAdapter implements ComponentListener {

        @NotNull
        private final Window originalWindow;

        private CombinedAdapter(@NotNull final Window originalWindow) {
            this.originalWindow = originalWindow;
            this.originalWindow.addComponentListener(this);
            this.originalWindow.addWindowListener(this);
        }

        @Override
        public void componentResized(final ComponentEvent e) {
        }

        @Override
        public void componentMoved(final ComponentEvent e) {
        }

        @Override
        public void componentShown(final ComponentEvent e) {
        }

        @Override
        public void componentHidden(final ComponentEvent e) {
            previousTipNotificationDismissTime = System.currentTimeMillis();
            removeSelfAsListener();
        }

        @Override
        public void windowClosed(final WindowEvent e) {
            super.windowClosed(e);
            previousTipNotificationDismissTime = System.currentTimeMillis();
            removeSelfAsListener();
        }

        private void removeSelfAsListener() {
            originalWindow.removeWindowListener(this);
            originalWindow.removeComponentListener(this);
        }
    }

    /**
     * Some systems (Mac, and possibly others) do not put padding around the edges of JTextPanes by default.
     * The default padding (border) on Windows is 3px, and that looks good, so use that for all systems that don't have
     * margin.
     *
     * @param tipComponent the JTextPane that is the tipComponent.
     */
    private void addBorderIfNeeded(final JTextPane tipComponent) {
        final Border defaultBorder = tipComponent.getBorder();
        if(defaultBorder == null) {
            addDefaultBorder(tipComponent);
            return;
        }
        final Insets insets = defaultBorder.getBorderInsets(tipComponent);
        if(insets == null || insets.left == 0 || insets.right == 0 || insets.top == 0 || insets.bottom == 0) {
            addDefaultBorder(tipComponent);
        }
    }

    private void addDefaultBorder(final JTextPane tipComponent) {
        tipComponent.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    }
}
