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
package com.squaretest.generation.filetemplateutil.velocity;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.squaretest.org.apache.velocity.app.VelocityEngine;
import com.squaretest.org.apache.velocity.context.Context;
import com.squaretest.org.apache.velocity.exception.ResourceNotFoundException;
import com.squaretest.org.apache.velocity.runtime.RuntimeConstants;
import com.squaretest.org.apache.velocity.runtime.resource.Resource;
import com.squaretest.org.apache.velocity.runtime.resource.loader.ResourceLoader;
import com.squaretest.org.apache.velocity.util.ExtProperties;
import org.jetbrains.annotations.NotNull;
import org.slf4j.helpers.NOPLogger;

import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/**
 * Manages the {@link VelocityEngine} singleton used in the Squaretest plugin.
 */
public class VelocityWrapper {

    private static VelocityWrapper instance;

    @NotNull
    private final VelocityEngine velocityEngine;

    private VelocityWrapper() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RUNTIME_LOG_INSTANCE, NOPLogger.NOP_LOGGER);
        velocityEngine.setProperty(RuntimeConstants.INPUT_ENCODING, FileTemplate.ourEncoding);
        velocityEngine.setProperty(RuntimeConstants.PARSER_POOL_SIZE, 3);
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "includes");
        velocityEngine.setProperty(RuntimeConstants.SPACE_GOBBLING, "lines");
        // Each iteration of the #foreach(..) loop increases the callstack depth. We need to set the limit high to avoid
        // hitting the max callstack depth in normal test generation flows.
        velocityEngine.setProperty(RuntimeConstants.VM_MAX_DEPTH, 100_000);
        // Set this property to allow us to store variables in the macro namespace; e.g. #set($macro.foo = 'bar' ).
        velocityEngine.setProperty("macro.provide.scope.control", true);
        velocityEngine.setProperty("includes.resource.loader.instance", new ResourceLoader() {
            @Override
            public void init(final ExtProperties configuration) {
            }

            @Override
            public Reader getResourceReader(
                    final String source, final String encoding) throws ResourceNotFoundException {
                final FileTemplate include = FileTemplateManager.getDefaultInstance().getPattern(source);
                if(include == null) {
                    throw new ResourceNotFoundException("Template not found: " + source);
                }
                final String text = include.getText();
                return new StringReader(text);
            }

            @Override
            public boolean isSourceModified(Resource resource) {
                return true;
            }

            @Override
            public long getLastModified(Resource resource) {
                return 0L;
            }
        });
        // Set these properties to ensure macros defined in our template files do not persist after rendering a file.
        // Without this, macros from one template will remain in the global velocity engine for all templates;
        // if you generate a class with a groovy template, then generate one with a Java template, the Java template's
        // #renderTestMethods() macro and other macros will run the code from the groovy macros even though they are
        // defined in the Java template.
        velocityEngine.setProperty(RuntimeConstants.VM_PERM_INLINE_LOCAL, true);
        velocityEngine.setProperty(RuntimeConstants.VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL, true);
        velocityEngine.init();
    }

    public static synchronized VelocityWrapper getInstance() {
        if(instance == null) {
            instance = new VelocityWrapper();
        }
        return instance;
    }

    public void evaluate(final Context context, final Writer out, final String logTag, final String instring) {
        velocityEngine.evaluate(context, out, logTag, instring);
    }
}
