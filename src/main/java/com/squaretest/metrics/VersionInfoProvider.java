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
package com.squaretest.metrics;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.extensions.PluginId;

public class VersionInfoProvider {

    /**
     * The IDE version format string.
     * Part 1: the version name; e.g. IntelliJ IDEA.
     * Part 2: the full version name; e.g. 2017.3.
     * Part 3: The build number; e.g. IC-173.3727.127.
     */
    private static final String IdeVersionFormat = "%s-%s-%s";

    public boolean isAndroidStudio() {
        return getIDEVersion().contains("Android Studio");
    }

    public String getIDEVersion() {
        final ApplicationInfo info = ApplicationInfo.getInstance();
        return String.format(IdeVersionFormat, info.getVersionName(), info.getFullVersion(), info.getBuild().asString());
    }

    public String getPluginVersion() {
        final IdeaPluginDescriptor ideaPluginDescriptor = PluginManagerCore.getPlugin(PluginId.getId("com.squaretest.Squaretest"));
        return ideaPluginDescriptor == null ? "Unknown" : ideaPluginDescriptor.getVersion();
    }
}
