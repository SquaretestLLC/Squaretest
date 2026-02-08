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
package com.squaretest.settings.store;

/**
 * Specifies whether we should try to automatically configure settings for modules within a project.
 */
public enum ModuleAutoConfigSetting {
    /*
     * DO NOT CHANGE THESE NAMES!
     * The xmlb API IntelliJ uses to serialize this into the xml prefs file
     * invokes toString to get the value to use. Renaming these will break
     * compatibility with older versions of Squaretest.
     */
    /**
     * Automatically configure modules to use the project template-settings the first-time a test is generated for a
     * class in the module; only show configuration UI if needed; e.g. we cannot find the test-roots for the module
     * or we've found the test-roots, but they contain tests written in a different language (groovy or java)
     * from the project template.
     */
    AUTOMATICALLY_CONFIGURE_WHEN_POSSIBLE,
    /**
     * Always show the module-configuration the first time a test is generated for a class in the module.
     */
    ALWAYS_SHOW_CONFIG;

    @Override
    public String toString() {
        // DO NOT OVERRIDE THIS!
        // The xmlb API IntelliJ uses to serialize this into the xml prefs file
        // invokes toString to get the value to use.
        return super.toString();
    }
}
