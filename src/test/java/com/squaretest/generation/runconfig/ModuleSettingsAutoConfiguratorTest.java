/*
 *
 *  * Copyright 2026 Squaretest LLC.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.squaretest.generation.runconfig;

import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModuleSettingsAutoConfiguratorTest {

    @Test
    public void areCompatibleTests_JUnit5Templates() {
        // (JUnit5, JUnit5)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, false, false, false, false, false, false, false)));

        // (JUnit5, JUnit5 + JUnit4)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, true, false, false, false, false, false, false)));

        // (JUnit5, JUnit5 + JUnit4 + AndroidJUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, true, true, false, false, false, false, false)));

        // (JUnit5, JUnit5 + JUnit4 + AndroidJUnit4 + Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, true, true, true, false, false, false, false)));

        // (JUnit5, JUnit5 + JUnit4 + Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, true, false, true, false, false, false, false)));

        // (JUnit5, JUnit5 + TestNG)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, true, false, false, true, false, false, false)));

        // (JUnit5, JUnit5 + Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, false, false, false, false, true, false, false)));

        // (JUnit5, JUnit5 + Spring + AssertJ)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, false, false, false, false, true, true, false)));

        // (JUnit5, JUnit5 + AssertJ)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, false, false, false, false, false, true, false)));

        // (JUnit5 + AssertJ, JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, true, false),
                new FrameworkInfo(true, false, false, false, false, false, false, false)));

        // (JUnit5, No JUnit 5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(false, false, false, false, false, false, true, false)));

        // (JUnit5Spring, JUnit5 No Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, false, false),
                new FrameworkInfo(true, false, false, false, false, false, true, false)));

        // (JUnit5, JUnit5Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, false, false, false),
                new FrameworkInfo(true, false, false, false, false, true, true, false)));

        // (JUnit5Spring, JUnit5SpringAssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, false, false),
                new FrameworkInfo(true, false, false, false, false, true, true, false)));

        // (JUnit5SpringAssertJ, JUnit5SpringAssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, true, false),
                new FrameworkInfo(true, false, false, false, false, true, true, false)));

        // (JUnit5SpringTruth, JUnit5SpringTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, false, true),
                new FrameworkInfo(true, false, false, false, false, true, false, true)));

        // (JUnit5SpringTruth, JUnit5Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, false, true),
                new FrameworkInfo(true, false, false, false, false, true, false, false)));

        // (JUnit5Spring, JUnit5SpringTruth)
        // Say this is compatible, because we don't have a GoogleTruth template to recommend to the user.
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, false, false),
                new FrameworkInfo(true, false, false, false, false, true, false, true)));

        // (JUnit5SpringAssertJ, JUnit5Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(true, false, false, false, false, true, true, false),
                new FrameworkInfo(true, false, false, false, false, true, false, false)));
    }

    @Test
    public void areCompatibleTests_AndroidJUnit() {
        // (AndroidJUnit4, AndroidJUnit4)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, false, false),
                new FrameworkInfo(false, true, true, false, false, false, false, false)));

        // (AndroidJUnit4, JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, false, false),
                new FrameworkInfo(true, true, true, false, false, false, false, false)));

        // (AndroidJUnit4, JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, false, false),
                new FrameworkInfo(true, true, true, false, false, false, false, false)));

        // (AndroidJUnit4, Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, false, false),
                new FrameworkInfo(false, true, true, true, false, false, false, false)));

        // (AndroidJUnit4 and GoogleTruth, AndroidJUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, false, true),
                new FrameworkInfo(false, true, true, false, false, false, false, false)));

        // (AndroidJUnit4AssertJ, AndroidJUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, true, false, false, false, true, false),
                new FrameworkInfo(false, true, true, false, false, false, false, false)));
    }

    @Test
    public void areCompatibleTests_Robolectric3() {
        // (Robolectric, Robolectric)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, false),
                new FrameworkInfo(false, true, false, true, false, false, false, false)));

        // (Robolectric, NotRobolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, false),
                new FrameworkInfo(false, true, false, false, false, false, false, false)));

        // (Robolectric, Robolectric and JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, false),
                new FrameworkInfo(true, true, false, true, false, false, false, false)));

        // (Robolectric, Robolectric and Android)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, false),
                new FrameworkInfo(false, true, true, true, false, false, false, false)));

        // (Robolectric and GoogleTruth, Robolectric and GoogleTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, true),
                new FrameworkInfo(false, true, false, true, false, false, false, true)));

        // (Robolectric and GoogleTruth, Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, false, true),
                new FrameworkInfo(false, true, false, true, false, false, false, false)));

        // (Robolectric and AssertJ, Robolectric and AssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, true, false),
                new FrameworkInfo(false, true, false, true, false, false, true, false)));

        // (Robolectric and AssertJ, Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, true, false, false, true, false),
                new FrameworkInfo(false, true, false, true, false, false, false, false)));
    }

    @Test
    public void areCompatibleTests_TestNG() {
        // (TestNG, TestNG)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, false, false, false, true, false, false, false)));

        // (TestNG, Not TestNG)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, false, false, false, false, false, false, false)));

        // (TestNG, TestNG and JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(true, false, false, false, true, false, false, false)));

        // (TestNG, TestNG and JUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, true, false, false, true, false, false, false)));

        // (TestNG, TestNG and Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, false, false, false, true, true, false, false)));

        // (TestNG, TestNG and AndroidJUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, true, true, false, true, false, false, false)));

        // (TestNG, TestNG and Robolectric)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, true, false, true, true, false, false, false)));

        // (TestNG and GoogleTruth, TestNG and GoogleTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, true),
                new FrameworkInfo(false, false, false, false, true, false, false, true)));

        // (TestNG, TestNG and GoogleTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, false, false, false, true, false, false, true)));

        // (TestNG and GoogleTruth, TestNG)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, true),
                new FrameworkInfo(false, false, false, false, true, false, false, false)));

        // (TestNG and AssertJ, TestNG and AssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, true, false),
                new FrameworkInfo(false, false, false, false, true, false, true, false)));

        // (TestNG and AssertJ, TestNG)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, true, false),
                new FrameworkInfo(false, false, false, false, true, false, false, false)));

        // (TestNG, TestNG and AssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, false, false, false, true, false, false, false),
                new FrameworkInfo(false, false, false, false, true, false, true, false)));
    }

    @Test
    public void areCompatibleTests_JUnit4Templates() {
        // (JUnit4, JUnit4)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, false),
                new FrameworkInfo(false, true, false, false, false, false, false, false)));

        // (JUnit4, JUnit4 and JUnit5)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, false),
                new FrameworkInfo(true, true, false, false, false, false, false, false)));

        // (JUnit4, JUnit4 and TestNG)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, false),
                new FrameworkInfo(false, true, false, false, true, false, false, false)));

        // (JUnit4Spring, JUnit4Spring)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, false, false),
                new FrameworkInfo(false, true, false, false, false, true, false, false)));

        // (JUnit4, JUnit4Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, false),
                new FrameworkInfo(false, true, false, false, false, true, false, false)));

        // (JUnit4Spring, JUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, false, false),
                new FrameworkInfo(false, true, false, false, false, false, false, false)));

        // (JUnit4SpringAssertJ, JUnit4SpringAssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, true, false),
                new FrameworkInfo(false, true, false, false, false, true, true, false)));

        // (JUnit4SpringAssertJ, JUnit4Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, true, false),
                new FrameworkInfo(false, true, false, false, false, true, false, false)));

        // (JUnit4Spring, JUnit4SpringAssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, false, false),
                new FrameworkInfo(false, true, false, false, false, true, true, false)));

        // (JUnit4SpringTruth, JUnit4SpringTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, false, true),
                new FrameworkInfo(false, true, false, false, false, true, false, true)));

        // (JUnit4SpringTruth, JUnit4Spring)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, true, false, true),
                new FrameworkInfo(false, true, false, false, false, true, false, false)));

        // (JUnit4GoogleTruth, JUnit4GoogleTruth)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, true),
                new FrameworkInfo(false, true, false, false, false, false, false, true)));

        // (JUnit4GoogleTruth, JUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, true),
                new FrameworkInfo(false, true, false, false, false, false, false, false)));

        // (JUnit4AssertJ, JUnit4AssertJ)
        assertTrue(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, true, false),
                new FrameworkInfo(false, true, false, false, false, false, true, false)));

        // (JUnit4AssertJ, JUnit4)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, true, false),
                new FrameworkInfo(false, true, false, false, false, false, false, false)));

        // (JUnit4, JUnit4AssertJ)
        assertFalse(ModuleSettingsAutoConfigurator.areCompatible(
                new FrameworkInfo(false, true, false, false, false, false, false, false),
                new FrameworkInfo(false, true, false, false, false, false, true, false)));
    }
}