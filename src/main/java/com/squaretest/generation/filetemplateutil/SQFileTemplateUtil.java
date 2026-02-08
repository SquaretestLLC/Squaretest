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
package com.squaretest.generation.filetemplateutil;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.squaretest.generation.CreateTestFileChecker;
import com.squaretest.generation.filetemplateutil.velocity.VelocityWrapper;
import com.squaretest.org.apache.velocity.VelocityContext;
import com.squaretest.org.apache.velocity.exception.MethodInvocationException;
import com.squaretest.org.apache.velocity.exception.VelocityException;
import com.squaretest.org.apache.velocity.runtime.RuntimeConstants;
import com.squaretest.template.api.Api;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This is largely based on {@link com.intellij.ide.fileTemplates.FileTemplateUtil}.
 * We can't use FileTemplateUtil, because of the following:
 * <ul>
 * <li>
 * We need to set the following properties on the Velocity engine:
 * {@link RuntimeConstants#VM_PERM_INLINE_LOCAL}, {@link RuntimeConstants#VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL}.
 * This requires us to have our own Velocity engine instance just for the Squaretest plugin.
 * </li>
 * <li>
 * We need to guarantee our {@link com.intellij.ide.fileTemplates.CreateFromTemplateHandler CreateFromTemplateHandlers} run; our Groovy
 * {@link SQGroovyFileCreator} does not require a filename while other
 * implementations might.
 * </li>
 * <li>
 * We ship Squaretest with a Velocity-2.x engine instead of using the default Velocity engine included with IDEA.
 * </li>
 * <li>
 * If we try to generate a file that already exists, we need to throw a checked exception with a path to the
 * file so that we can open it for the user.
 * </li>
 * </ul>
 */
public class SQFileTemplateUtil {
    private static final Logger Log = Logger.getInstance(SQFileTemplateUtil.class);

    // Matches 3 or more newlines with optional whitespace characters between them.
    // The unicode values for the newlines were copied from the \R definition on
    // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#lineending. The \R matcher doesn't work
    // in Java 8 (required for IntelliJ IDEA 2018.x), so just use the value \R is equivalent to.
    private static final Pattern ThreeOrMoreNewlinesWithWhitespaceBetween = Pattern.compile("(\\u000D\\u000A\\h*|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]\\h*){2,}(\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029])", Pattern.DOTALL);
    private static final Pattern CommentFollowedByOneOrMoreBlankLines = Pattern.compile("^([\\h]*\\/\\/.*)([\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]][\\h]*)+(\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029])", Pattern.MULTILINE);
    private static final String NewlineChar = System.lineSeparator();
    private static final String TwoNewlines = NewlineChar + NewlineChar;

    private SQFileTemplateUtil() {
    }

    public static String mergeTemplate(
            Map attributes, String templateText) throws VelocityException {
        VelocityContext context = createVelocityContext();
        for(final Object o : attributes.keySet()) {
            String name = (String) o;
            context.put(name, attributes.get(name));
        }
        return mergeTemplate(templateText, context);
    }

    private static VelocityContext createVelocityContext() {
        return new VelocityContext();
    }

    private static String mergeTemplate(
            String templateContent, final VelocityContext context) throws VelocityException {
        final StringWriter stringWriter = new StringWriter();
        VelocityWrapper.getInstance().evaluate(context, stringWriter, "", templateContent);
        return stringWriter.toString();
    }

    /**
     * Runs the template and returns the in-memory file, or null if there was an error running the template.
     *
     * @param template              the template to run.
     * @param propsMap              the variables to use when running the template.
     * @param createTestFileChecker not used.
     * @return the test class inside the in-memory file generated by invoking this template. Returns null if
     * an exception occurs when running the template.
     */
    @NotNull
    public static PsiClass runTemplateAndReturnInMemoryFile(
            @NotNull final Project project,
            @NotNull final FileTemplate template,
            @NotNull Map<String, Object> propsMap,
            @NotNull final CreateTestFileChecker createTestFileChecker) throws TemplateRenderingException {

        final SQFileCreator fileCreator = createHandlerForTemplate(template.getExtension(), createTestFileChecker);
        String mergedText;
        try {
            mergedText = SQFileTemplateUtil.mergeTemplate(propsMap, template.getText());
            mergedText = ThreeOrMoreNewlinesWithWhitespaceBetween.matcher(mergedText).replaceAll(TwoNewlines);
            mergedText = CommentFollowedByOneOrMoreBlankLines.matcher(mergedText).replaceAll("$1$3");
        } catch(final Exception e) {
            throw new TemplateRenderingException(e, template);
        }
        final String templateText = StringUtil.convertLineSeparators(mergedText);
        return fileCreator.createInMemoryFile(project, templateText);
    }

    @Nullable
    public static VirtualFile runTemplateAndReturnExistingFile(
            @NotNull final FileTemplate template,
            @NotNull Map<String, Object> propsMap, @NotNull final PsiDirectory testSourcesRoot,
            @NotNull final CreateTestFileChecker createTestFileChecker) {
        @NotNull final Project project = testSourcesRoot.getProject();

        final SQFileCreator fileCreator = createHandlerForTemplate(template.getExtension(), createTestFileChecker);
        String mergedText;
        try {
            mergedText = SQFileTemplateUtil.mergeTemplate(propsMap, template.getText());
        } catch(final Exception e) {
            // Catch all exceptions here; the user's template could trigger NPEs and other RuntimeExceptions.
            // We need to catch all of them to report them to the user correctly.
            return null;
        }
        final String templateText = StringUtil.convertLineSeparators(mergedText);
        final VirtualFile[] result = new VirtualFile[1];
        CommandProcessor.getInstance().executeCommand(project,
                () -> ApplicationManager.getApplication().runWriteAction(
                        () -> {
                            result[0] = fileCreator.returnExistingTestClassIfExists(project, testSourcesRoot, templateText);
                        }), "Create Class From Template", null);
        return result[0];
    }

    public static PsiElement createFromTemplate(
            @NotNull final FileTemplate template, @NotNull Map<String, Object> propsMap, @NotNull final PsiDirectory testSourcesRoot,
            @NotNull final CreateTestFileChecker createTestFileChecker) throws TemplateRenderingException, DirectoryNotWritableException, FileAlreadyExistsException, CannotCreatePackageException, Api.UserCancelledGenerationException {
        @NotNull final Project project = testSourcesRoot.getProject();

        String mergedText;
        try {
            mergedText = SQFileTemplateUtil.mergeTemplate(propsMap, template.getText());
        } catch(final MethodInvocationException e) {
            final Throwable cause = e.getCause();
            if(cause instanceof Api.UserCancelledGenerationException) {
                throw (Api.UserCancelledGenerationException) cause;
            }
            throw new TemplateRenderingException(e, template);
        } catch(final Exception e) {
            // Catch all exceptions here; the user's template could trigger NPEs and other RuntimeExceptions.
            // We need to catch all of them to report them to the user correctly.
            throw new TemplateRenderingException(e, template);
        }

        // Replace all groups of three or more newlines (three newlines only separated by whitespace) with two newlines.
        mergedText = ThreeOrMoreNewlinesWithWhitespaceBetween.matcher(mergedText).replaceAll(TwoNewlines);
        mergedText = CommentFollowedByOneOrMoreBlankLines.matcher(mergedText).replaceAll("$1$3");

        final String templateText = StringUtil.convertLineSeparators(mergedText);
        CreateTestCheckedException[] commandException = new CreateTestCheckedException[1];
        final PsiElement[] result = new PsiElement[1];
        Log.debug("Start save generated file: " + LocalDateTime.now());
        CommandProcessor.getInstance().executeCommand(project,
                () -> ApplicationManager.getApplication().runWriteAction(
                        () -> {
                            try {
                                final SQFileCreator fileCreator = createHandlerForTemplate(template.getExtension(), createTestFileChecker);
                                result[0] = fileCreator.saveGeneratedFile(project, testSourcesRoot, templateText);
                            } catch(FileAlreadyExistsException | DirectoryNotWritableException |
                                    CannotCreatePackageException ex) {
                                commandException[0] = ex;
                            }
                        }), "Create Class From Template", null);
        Log.debug("Finish save generated file: " + LocalDateTime.now());
        if(commandException[0] != null) {
            if(commandException[0] instanceof DirectoryNotWritableException) {
                throw (DirectoryNotWritableException) commandException[0];
            } else if(commandException[0] instanceof FileAlreadyExistsException) {
                throw (FileAlreadyExistsException) commandException[0];
            } else if(commandException[0] instanceof CannotCreatePackageException) {
                throw (CannotCreatePackageException) commandException[0];
            }
        }
        return result[0];
    }

    private static SQFileCreator createHandlerForTemplate(
            final String extension, final CreateTestFileChecker createTestFileChecker) {
        // Use our own template handlers
        if(extension.equals("java")) {
            return new SQJavaFileCreator(createTestFileChecker);
        } else if(extension.equals("groovy")) {
            return new SQGroovyFileCreator(createTestFileChecker);
        } else {
            throw new IncorrectOperationException(String.format("Template extension %s is not supported; Squaretest currently" + "only supports java and groovy templates", extension));
        }
    }
}
