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

import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;

/**
 * Static utils methods for UI Components.
 */
public class UiUtils {
    private UiUtils() {
    }

    /**
     * Computes the width of the given JLabel before it has been rendered (while its width and height are both set
     * to null).
     *
     * @param jLabel the jLabel to compute the width for.
     * @return the computed width.
     */
    public static int computeJLabelWidth(@NotNull final JLabel jLabel) {
        final FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        return fontMetrics.stringWidth(jLabel.getText());
    }

    public static boolean containsItem(final JComboBox<?> component, final Object item) {
        for(int i = 0; i < component.getItemCount(); i++) {
            final Object obj = component.getItemAt(i);
            if(Objects.equals(obj, item)) {
                return true;
            }
        }
        return false;
    }

    public static void provisionHtmlTextPane(final JTextPane htmlTextPane) {
        provisionHtmlTextPane(htmlTextPane, UIManager.getFont("Label.font").getSize());
    }

    public static void provisionHtmlTextPane(final JTextPane htmlTextPane, final int fontSize) {
        final Font font = UIManager.getFont("Label.font");
        final Color fontColor = UIManager.getColor("Label.foreground");
        final String colorHex = ColorUtil.toHex(fontColor);
        final String bodyRule = "body {"
                + "font-family: " + font.getFamily() + "; "
                + "font-size: " + fontSize + "pt; "
                + "color:" + colorHex + ";" + "}";
        final Color color = JBUI.CurrentTheme.Link.Foreground.ENABLED;
        final String anchorColorHex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        final String anchorRule = "a {"
                + "color: " + anchorColorHex + "; "
                + "}";
        final HTMLDocument statisticsHelpTextDoc = (HTMLDocument) htmlTextPane.getDocument();
        statisticsHelpTextDoc.getStyleSheet().addRule(bodyRule);
        statisticsHelpTextDoc.getStyleSheet().addRule(anchorRule);
    }
}
