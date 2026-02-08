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
package com.squaretest.completion.frameworkdetection;

import com.squaretest.completion.TemplateInfo;
import com.squaretest.generation.runconfig.infoprovider.FrameworkInfo;
import org.junit.Before;
import org.junit.Test;

import static helpers.TestUtils.safeLoadFile;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FrameworkInferencerTest {

    private FrameworkInferencer frameworkInferencerUnderTest;

    @Before
    public void setUp() {
        frameworkInferencerUnderTest = new FrameworkInferencer();
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_JavaTestClass_CustomJUnit5Template() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        final String templateText = safeLoadFile("src/test/data/com/squaretest/completion/frameworkdetection/JavaJUnit5Template.java.ft");
        when(templateInfo.isInternalTemplate()).thenReturn(false);
        when(templateInfo.getTemplateText()).thenReturn(templateText);

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit5());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_JavaTestClass_CustomJUnit4Template() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        final String templateText = safeLoadFile("src/test/data/com/squaretest/completion/frameworkdetection/JavaJUnit4Template.java.ft");
        when(templateInfo.isInternalTemplate()).thenReturn(false);
        when(templateInfo.getTemplateText()).thenReturn(templateText);

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit4());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_JavaTestClass_CustomTestNGTemplate() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        final String templateText = safeLoadFile("src/test/data/com/squaretest/completion/frameworkdetection/JavaTestNGTemplate.java.ft");
        when(templateInfo.isInternalTemplate()).thenReturn(false);
        when(templateInfo.getTemplateText()).thenReturn(templateText);

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasTestNG());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_JavaTestClass_CustomJUnit3Template() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        final String templateText = safeLoadFile("src/test/data/com/squaretest/completion/frameworkdetection/JavaJUnit3Template.java.ft");
        when(templateInfo.isInternalTemplate()).thenReturn(false);
        when(templateInfo.getTemplateText()).thenReturn(templateText);

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertFalse(result.hasJUnit4());
        assertFalse(result.hasJUnit5());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_InternalTemplate_JavaJUnit5() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        when(templateInfo.isInternalTemplate()).thenReturn(true);
        when(templateInfo.getInternalTemplateName()).thenReturn("SQTJ-JUnit5Mockito.java");

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit5());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_InternalTemplate_GroovyJUnit5() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        when(templateInfo.isInternalTemplate()).thenReturn(true);
        when(templateInfo.getInternalTemplateName()).thenReturn("SQTJ-JUnit5Mockito.java");

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit5());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_InternalTemplate_JavaJUnit4() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        when(templateInfo.isInternalTemplate()).thenReturn(true);
        when(templateInfo.getInternalTemplateName()).thenReturn("SQTJ-JUnit4Mockito.java");

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit4());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_InternalTemplate_TestNG() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        when(templateInfo.isInternalTemplate()).thenReturn(true);
        when(templateInfo.getInternalTemplateName()).thenReturn("SQTJ-TestNGMockito.java");

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasTestNG());
    }

    @Test
    public void testDetermineFrameworkFromTemplateText_InternalTemplate_JavaAndroid() {
        // Setup
        final TemplateInfo templateInfo = mock(TemplateInfo.class);
        when(templateInfo.isInternalTemplate()).thenReturn(true);
        when(templateInfo.getInternalTemplateName()).thenReturn("SQTJ-AndroidJUnit4Mockito.java");

        // Run the test
        final FrameworkInfo result = frameworkInferencerUnderTest.determineFrameworkInfoFromTemplate(templateInfo);

        // Verify the results
        assertTrue(result.hasJUnit4());
        assertTrue(result.hasAndroidJUnit());
    }
}
