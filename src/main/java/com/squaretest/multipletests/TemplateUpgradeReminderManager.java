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
package com.squaretest.multipletests;

import com.intellij.ide.BrowserUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.squaretest.utils.SQIOUtils;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;

public class TemplateUpgradeReminderManager {

    private static TemplateUpgradeReminderManager instance;

    private static final String Title = "Template Upgrade Recommended";
    private static final Duration TimeBetweenReminderNotifications = Duration.ofHours(24);
    private static final String TemplateNamePlaceholder = "{{TemplateName}}";

    @Nullable
    private Instant lastNotificationTime = null;

    private TemplateUpgradeReminderManager() {
    }

    /**
     * Returns the singleton instance of {@link TemplateUpgradeReminderManager}.
     *
     * @return the {@link TemplateUpgradeReminderManager} instance.
     */
    public static synchronized TemplateUpgradeReminderManager getInstance() {
        if(instance == null) {
            instance = new TemplateUpgradeReminderManager();
        }
        return instance;
    }

    public boolean showTemplateUpgradeNotificationIfNeeded(final Project project, final String templateName) {
        if(shouldShowNotification()) {
            lastNotificationTime = Instant.now();
            final String message = SQIOUtils.safeLoadResource("/TemplateUpgradeRecommended.html").replace(TemplateNamePlaceholder, templateName);
            final Notification notification = NotificationGroupManager.getInstance()
                    .getNotificationGroup("Squaretest General")
                    .createNotification(Title, message, NotificationType.INFORMATION)
                    .setListener((notification1, hyperlinkEvent) -> {
                        BrowserUtil.open("https://squaretest.com/v1.3upgrade.html");
                        notification1.hideBalloon();
                    });
            notification.notify(project);
            return true;
        }
        return false;
    }

    private boolean shouldShowNotification() {
        if(lastNotificationTime == null) {
            return true;
        }
        return Instant.now().isAfter(lastNotificationTime.plus(TimeBetweenReminderNotifications));
    }
}
