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
package com.squaretest.generation.defaulttypes;

import com.squaretest.template.impl.TypeImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class AltIoExpressionPopulator {

    private static final String[] InputStreamPlaceholderExpressions = new String[]{"new java.io.ByteArrayInputStream(\"content\".getBytes())", "new java.io.ByteArrayInputStream(\"objectContent\".getBytes())"};
    private static final String[] ReaderPlaceholderExpressions = new String[]{"new java.io.StringReader(\"content\")"};
    private static final String[] OutputStreamPlaceholderExpressions = new String[]{"new java.io.ByteArrayOutputStream()"};
    private static final String[] WriterPlaceholderExpressions = new String[]{"new java.io.StringWriter()"};
    public static final String[] IOTypeCanonicalNames = {"java.io.Reader", "java.io.Writer", "java.io.InputStream", "java.io.OutputStream"};
    public static final String[] CloseableTypeCanonicalNames = {"java.io.Closeable", "java.lang.AutoCloseable"};

    @NotNull
    private final AltIOExpressionProvider altIOExpressionProvider;

    public AltIoExpressionPopulator(@NotNull final AltIOExpressionProvider altIOExpressionProvider) {
        this.altIOExpressionProvider = altIOExpressionProvider;
    }

    public void populateAltIoExpressions(@NotNull final TypeImpl typeImpl) {
        if(!typeImpl.isAny(IOTypeCanonicalNames)
                && !StringUtils.equalsAny(typeImpl.getCanonicalName(), CloseableTypeCanonicalNames)) {
            // If the type is not a subclass of one of the I/O types, don't create a test for the broken case.
            return;
        }
        final String initExpression = typeImpl.getInitExpression();
        for(final String isPlaceholder : InputStreamPlaceholderExpressions) {
            if(StringUtils.contains(initExpression, isPlaceholder)) {
                typeImpl.setEmptyIoInitExpression(StringUtils.replaceOnce(initExpression, isPlaceholder, altIOExpressionProvider.getNullInputStreamInitExpression()));
                typeImpl.setBrokenIoInitExpression(StringUtils.replaceOnce(initExpression, isPlaceholder, altIOExpressionProvider.getBrokenInputStreamInitExpression()));
                return;
            }
        }

        for(final String readerPlaceholder : ReaderPlaceholderExpressions) {
            if(StringUtils.contains(initExpression, readerPlaceholder)) {
                typeImpl.setEmptyIoInitExpression(StringUtils.replaceOnce(initExpression, readerPlaceholder, altIOExpressionProvider.getNullReaderInitExpression()));
                typeImpl.setBrokenIoInitExpression(StringUtils.replaceOnce(initExpression, readerPlaceholder, altIOExpressionProvider.getBrokenReaderInitExpression()));
                return;
            }
        }

        for(final String outputStreamPlaceholder : OutputStreamPlaceholderExpressions) {
            if(StringUtils.contains(initExpression, outputStreamPlaceholder)) {
                typeImpl.setBrokenIoInitExpression(StringUtils.replaceOnce(initExpression, outputStreamPlaceholder, altIOExpressionProvider.getBrokenOutputStreamInitExpression()));
                return;
            }
        }

        for(final String writerPlaceholder : WriterPlaceholderExpressions) {
            if(StringUtils.contains(initExpression, writerPlaceholder)) {
                typeImpl.setBrokenIoInitExpression(StringUtils.replaceOnce(initExpression, writerPlaceholder, altIOExpressionProvider.getBrokenWriterInitExpression()));
                return;
            }
        }
    }
}
