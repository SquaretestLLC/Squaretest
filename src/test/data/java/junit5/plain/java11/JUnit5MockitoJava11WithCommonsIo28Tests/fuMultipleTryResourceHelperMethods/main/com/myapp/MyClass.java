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
package com.myapp;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClass {
    private final InputProvider inputProvider;
    private final OutputProvider outputProvider;
    private final MetricService metricService;

    public MyClass(final InputProvider inputProvider, final OutputProvider outputProvider, final MetricService metricService) {
        this.inputProvider = inputProvider;
        this.outputProvider = outputProvider;
        this.metricService = metricService;
    }

    public void doCopy1() {
        try (final InputStream inputStream = getInputStream(); final OutputStream outputStream = getOutputStream()) {
            if(inputStream == null || outputStream == null) {
                metricService.recordInputOrOutputEqualsNull();
                throw new NullIOException();
            }
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            metricService.recordIOException(e);
            throw new BrokenIOException();
        }
    }

    private OutputStream getOutputStream() {
        return outputProvider.openOutputStream();
    }

    private InputStream getInputStream() {
        return inputProvider.openInputStream();
    }
}
