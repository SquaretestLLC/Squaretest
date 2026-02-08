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
package com.squaretest.completionhelp;

import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.squaretest.settings.UiUtils;
import com.squaretest.utils.SQIOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class HelpDialog extends DialogWrapper {

    private static final int DefaultTutorialImageWidth = 1000;

    private JTextPane txtInstructions;
    private JPanel rootPanel;
    private JLabel lblGifHolder;
    private List<TutorialScreen> tutorialScreens;
    private int tutorialIndex;
    private AbstractAction nextAction;
    private AbstractAction previousAction;

    protected HelpDialog(@Nullable final Project project) {
        super(project);
        // createUiComponents() is called here by the IntelliJ-injected bytecode.
        setTitle("Squaretest Help");
        setResizable(false);
        setCancelButtonText("Close");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        tutorialScreens = loadTutorialScreens();
        tutorialIndex = 0;
        txtInstructions.setText(tutorialScreens.get(tutorialIndex).text());
        lblGifHolder.setIcon(tutorialScreens.get(tutorialIndex).image());
        previousAction.setEnabled(false);

        // Give the text-pane the same background color as the background (needed on OS/X).
        txtInstructions.setBackground(rootPanel.getBackground());
        UiUtils.provisionHtmlTextPane(txtInstructions, UIManager.getFont("Label.font").getSize() + 4);
        return rootPanel;
    }

    @NotNull
    private List<TutorialScreen> loadTutorialScreens() {
        final AnAction generateAction = ActionManager.getInstance().getAction("Generate");
        String generateMenuShortcut = generateAction != null ? KeymapUtil.getFirstKeyboardShortcutText(generateAction) : null;
        if(StringUtils.isEmpty(generateMenuShortcut)) {
            generateMenuShortcut = "Alt | Insert";
        }
        final List<TutorialScreen> ret = new ArrayList<>(2);
        final int imageWidth = determineWidthFromMonitorSize();
        final String themeName = JBColor.isBright() ? "Normal" : "Darcula";
        ret.add(new TutorialScreen(StringUtils.replace(SQIOUtils.safeLoadResource("/GenerateTestClass.html"), "{{Shortcut}}", generateMenuShortcut),
                loadImage(String.format("/icons/GenerateTestClass/GenerateTestClass_%s_%d.gif", themeName, imageWidth))));
        ret.add(new TutorialScreen(StringUtils.replace(SQIOUtils.safeLoadResource("/GenerateTestMethod.html"), "{{Shortcut}}", generateMenuShortcut),
                loadImage(String.format("/icons/GenerateTestMethod/GenerateTestMethod_%s_%d.gif", themeName, imageWidth))));
        return ret;
    }

    private ImageIcon loadImage(final String path) {
        final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
        return new ImageIcon(image);
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        return new Action[]{previousAction, nextAction, getCancelAction()};
    }

    private void createUIComponents() {
        this.previousAction = new SQAbstractAction("Previous") {
            @Override
            public void actionPerformed(final ActionEvent e) {
                onPreviousClicked();
            }
        };
        this.nextAction = new SQAbstractAction("Next") {
            @Override
            public void actionPerformed(final ActionEvent e) {
                onNextClicked();
            }
        };
        lblGifHolder = new JLabel();
    }

    private void onPreviousClicked() {
        if(tutorialIndex <= 0) {
            // This shouldn't happen.
            return;
        }
        tutorialIndex--;
        updateTutorialUi();
    }

    private void onNextClicked() {
        if(tutorialIndex >= tutorialScreens.size() - 1) {
            // This shouldn't happen.
            return;
        }
        tutorialIndex++;
        updateTutorialUi();
    }

    private void updateTutorialUi() {
        txtInstructions.setText(tutorialScreens.get(tutorialIndex).text());
        lblGifHolder.setIcon(tutorialScreens.get(tutorialIndex).image());
        previousAction.setEnabled(tutorialIndex > 0);
        nextAction.setEnabled(tutorialIndex < tutorialScreens.size() - 1);
    }

    private int determineWidthFromMonitorSize() {
        // Get the dimensions of the screen (monitor) the top-level window is in.
        final Window window = ProjectUtil.getActiveFrameOrWelcomeScreen();
        if(window == null) {
            return DefaultTutorialImageWidth;
        }
        final GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
        final Rectangle bounds = graphicsConfiguration.getBounds();
        final int displayWidth = bounds.width;
        if(displayWidth <= 800) {
            return 600;
        } else if(displayWidth <= 1024) {
            // Support Mac with the large text mode.
            return 800;
        } else if(displayWidth <= 1920) {
            return 1000;
        } else {
            return 1400;
        }
    }

    private static abstract class SQAbstractAction extends AbstractAction {
        public SQAbstractAction(final String name) {
            super(name);
        }
    }
}
