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
package com.squaretest;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiJavaFile;
import com.squaretest.di.NewTestHandlerComponent;
import com.squaretest.generation.UnitTestGenerator;
import com.squaretest.generation.errorui.RenderingFailedDialog;
import com.squaretest.generation.filetemplateutil.DirectoryNotWritableException;
import com.squaretest.generation.filetemplateutil.FileAlreadyExistsException;
import com.squaretest.generation.filetemplateutil.TemplateRenderingException;
import com.squaretest.generation.runconfig.ConfigInfo;
import com.squaretest.generation.runconfig.ConfigurationObtainer;
import com.squaretest.generation.runconfig.DetermineConfigResult;
import com.squaretest.notifications.NotificationDisplayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewTestHandlerTest {

    private NewTestHandler newTestHandlerUnderTest;

    // Arguments for invoke call.
    @Mock
    private Project project;
    @Mock
    private Editor editor;
    @Mock
    private PsiJavaFile psiJavaFile;

    // Mock the dependency provider constructed with the arguments above.
    @Mock
    private NewTestHandlerComponent.Factory mockNewTestHandlerFactory;
    @Mock
    private NewTestHandlerComponent mockNewTestHandlerComponent;
    @Mock
    private DumbService mockDumbService;
    @Mock
    private NotificationDisplayer mockNotificationDisplayer;
    @Mock
    private EditorOpener mockEditorOpener;
    @Mock
    private Module mockSourceFileModule;
    @Mock
    private ConfigurationObtainer mockConfigurationObtainer;
    @Mock
    private UnitTestGenerator mockUnitTestGenerator;
    @Mock
    private RenderingFailedDialog mockRenderingFailedDialog;

    // Mock value returned by ConfigurationObtainer.
    @Mock
    private DetermineConfigResult mockDetermineConfigResult;

    @Mock
    private VirtualFile mockTestFile;
    @Mock
    private TemplateRenderingException mockTemplateRenderingException;

    @Before
    public void setUp() throws Exception {
        when(mockNewTestHandlerFactory.createComponent(project, psiJavaFile, null)).thenReturn(mockNewTestHandlerComponent);

        // Stub the dependencies.
        when(mockNewTestHandlerComponent.getDumbService()).thenReturn(mockDumbService);
        when(mockNewTestHandlerComponent.getNotificationDisplayer()).thenReturn(mockNotificationDisplayer);
        when(mockNewTestHandlerComponent.getEditorOpener()).thenReturn(mockEditorOpener);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockNewTestHandlerComponent.getConfigurationObtainer()).thenReturn(mockConfigurationObtainer);
        when(mockNewTestHandlerComponent.createUnitTestGenerator(any(ConfigInfo.class), eq(false))).thenReturn(mockUnitTestGenerator);
        when(mockNewTestHandlerComponent.createRenderingFailedDialog(mockTemplateRenderingException)).thenReturn(mockRenderingFailedDialog);
        // Construct the instance.
        newTestHandlerUnderTest = new NewTestHandler(mockNewTestHandlerFactory);
        newTestHandlerUnderTest.setEditorWindow(null);
    }

    @Test
    public void testInvoke_DumbModeActive() {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(true);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockDumbService).showDumbModeNotification(anyString());
        verifyNoInteractions(mockConfigurationObtainer);
    }

    @Test
    public void testInvoke_ModuleNotDetermined() {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(null);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockNotificationDisplayer).showModuleNotDeterminedNotification();
        verifyNoInteractions(mockConfigurationObtainer);
    }

    @Test
    public void testInvoke_ShowConfigDialog_NoConfigDetermined() {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(false);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockNewTestHandlerComponent, never()).createUnitTestGenerator(any(ConfigInfo.class), eq(false));
    }

    @Test
    public void testInvoke_DontShowJIT_CreateNewTestWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenReturn(mockTestFile);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockEditorOpener).openEditor(mockTestFile);
    }

    @Test
    public void testInvoke_ShowJIT_CreateNewTestWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenReturn(mockTestFile);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockEditorOpener).openEditor(mockTestFile);
    }

    @Test
    public void testInvoke_DontShowJIT_OpenExistingTestWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenThrow(new FileAlreadyExistsException(mockTestFile));

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockEditorOpener).openEditor(mockTestFile);
    }

    @Test
    public void testInvoke_DontShowJIT_DirectoryNotWritableTestWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenThrow(new DirectoryNotWritableException(mockTestFile));

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockEditorOpener, never()).openEditor(any(VirtualFile.class));
        verify(mockNotificationDisplayer).showDirectoryNotWritable(mockTestFile);
    }

    @Test
    public void testInvoke_DontShowJIT_TemplateRenderingExceptionWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenThrow(mockTemplateRenderingException);

        // Run the test
        newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);

        // Verify the results
        verify(mockEditorOpener, never()).openEditor(any(VirtualFile.class));
        verify(mockRenderingFailedDialog).showAndGet();
    }

    @Test
    public void testInvoke_DontShowJIT_RuntimeExceptionWithInternalJavaTemplate() throws Exception {
        // Setup
        when(mockDumbService.isDumb()).thenReturn(false);
        when(mockNewTestHandlerComponent.getSourceFileModule()).thenReturn(mockSourceFileModule);
        when(mockConfigurationObtainer.determineConfig(project, mockSourceFileModule, psiJavaFile)).thenReturn(mockDetermineConfigResult);
        when(mockDetermineConfigResult.hasValidConfig()).thenReturn(true);
        when(mockDetermineConfigResult.configInfo()).thenReturn(mock(ConfigInfo.class));
        when(mockUnitTestGenerator.generate()).thenThrow(new RuntimeException());

        // Run the test
        try {
            newTestHandlerUnderTest.invoke(project, editor, psiJavaFile);
            fail("should have thrown a RuntimeException");
        } catch(final RuntimeException ignored) {
        }

        // Verify the results
        verify(mockEditorOpener, never()).openEditor(any(VirtualFile.class));
    }
}
