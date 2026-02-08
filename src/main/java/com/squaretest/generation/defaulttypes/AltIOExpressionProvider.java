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

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;

import java.util.Arrays;

public class AltIOExpressionProvider {
    private static final String BrokenInputStreamCommonsIo = "new org.apache.commons.io.input.BrokenInputStream()";
    private static final String BrokenInputStreamPureJava = """
            new java.io.InputStream() {
                        private final java.io.IOException exception = new java.io.IOException("Error");
            
                        @Override
                        public int read() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public int available() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public long skip(final long n) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public synchronized void reset() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void close() throws java.io.IOException {
                            throw exception;
                        }
                    }""";

    private static final String BrokenReaderCommonsIo = "new org.apache.commons.io.input.BrokenReader()";
    private static final String BrokenReaderPureJava = """
            new java.io.Reader() {
            
                        private final java.io.IOException exception = new IOException("Error");
            
                        @Override
                        public int read(final char[] cbuf, final int off, final int len) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public long skip(final long n) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public boolean ready() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void mark(final int readAheadLimit) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public synchronized void reset() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void close() throws java.io.IOException {
                            throw exception;
                        }
                    }""";

    private static final String BrokenOutputStreamCommonsIo = "new org.apache.commons.io.output.BrokenOutputStream()";
    private static final String BrokenOutputStreamPureJava = """
            new java.io.OutputStream() {
            
                        private final java.io.IOException exception = new java.io.IOException("Error");
            
                        @Override
                        public void write(final int b) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void flush() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void close() throws java.io.IOException {
                            throw exception;
                        }
                    }""";

    private static final String BrokenWriterCommonsIo = "new org.apache.commons.io.output.BrokenWriter()";
    private static final String BrokenWriterPureJava = """
            new java.io.Writer() {
            
                        private final java.io.IOException exception = new java.io.IOException("Error");
            
                        @Override
                        public void write(final char[] cbuf, final int off, final int len) throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void flush() throws java.io.IOException {
                            throw exception;
                        }
            
                        @Override
                        public void close() throws java.io.IOException {
                            throw exception;
                        }
                    }""";

    private static final String NullInputStreamJava11 = "java.io.InputStream.nullInputStream()";
    private static final String NullInputStreamCommonsIo26 = "new org.apache.commons.io.input.NullInputStream(0L)";
    private static final String NullInputStreamCommonsIo27 = "new org.apache.commons.io.input.NullInputStream()";
    private static final String NullInputStreamPureJava = "new java.io.ByteArrayInputStream(new byte[]{})";

    private static final String NullReaderJava11 = "java.io.Reader.nullReader()";
    private static final String NullReaderCommonsIo26 = "new org.apache.commons.io.input.NullReader(0L)";
    private static final String NullReaderCommonsIo27 = "new org.apache.commons.io.input.NullReader()";
    private static final String NullReaderPureJava = "new java.io.StringReader(\"\")";

    private enum CommonsIOVersion {
        Has27,
        Has26,
        None
    }

    private final TestDependencyInfoProvider testDependencyInfoProvider;
    private CommonsIOVersion cachedCommonsIOVersion;

    public AltIOExpressionProvider(final TestDependencyInfoProvider testDependencyInfoProvider) {
        this.testDependencyInfoProvider = testDependencyInfoProvider;
        this.cachedCommonsIOVersion = null;
    }

    public String getBrokenInputStreamInitExpression() {
        if(hasBrokenInputStreamCommonsIo()) {
            return BrokenInputStreamCommonsIo;
        } else {
            return BrokenInputStreamPureJava;
        }
    }

    public String getBrokenReaderInitExpression() {
        if(hasBrokenReaderCommonsIo()) {
            return BrokenReaderCommonsIo;
        } else {
            return BrokenReaderPureJava;
        }
    }

    public String getBrokenOutputStreamInitExpression() {
        if(hasBrokenOutputStreamCommonsIo()) {
            return BrokenOutputStreamCommonsIo;
        } else {
            return BrokenOutputStreamPureJava;
        }
    }

    public String getBrokenWriterInitExpression() {
        if(hasBrokenWriterCommonsIo()) {
            return BrokenWriterCommonsIo;
        } else {
            return BrokenWriterPureJava;
        }
    }

    public String getNullInputStreamInitExpression() {
        if(testDependencyInfoProvider.testPathIsAtLeastJava11()) {
            return NullInputStreamJava11;
        }
        final CommonsIOVersion commonsIOVersion = getOrComputeCommonsIOVersion();
        if(commonsIOVersion == CommonsIOVersion.Has26) {
            return NullInputStreamCommonsIo26;
        }

        if(commonsIOVersion == CommonsIOVersion.Has27) {
            return NullInputStreamCommonsIo27;
        }

        return NullInputStreamPureJava;
    }

    public String getNullReaderInitExpression() {
        if(testDependencyInfoProvider.testPathIsAtLeastJava11()) {
            return NullReaderJava11;
        }
        final CommonsIOVersion commonsIOVersion = getOrComputeCommonsIOVersion();
        if(commonsIOVersion == CommonsIOVersion.Has26) {
            return NullReaderCommonsIo26;
        }
        if(commonsIOVersion == CommonsIOVersion.Has27) {
            return NullReaderCommonsIo27;
        }

        return NullReaderPureJava;
    }

    private boolean hasBrokenInputStreamCommonsIo() {
        return testDependencyInfoProvider.testPathContainsClass("org.apache.commons.io.input.BrokenInputStream");
    }

    private boolean hasBrokenOutputStreamCommonsIo() {
        return testDependencyInfoProvider.testPathContainsClass("org.apache.commons.io.output.BrokenOutputStream");
    }

    private boolean hasBrokenReaderCommonsIo() {
        return testDependencyInfoProvider.testPathContainsClass("org.apache.commons.io.input.BrokenReader");
    }

    private boolean hasBrokenWriterCommonsIo() {
        return testDependencyInfoProvider.testPathContainsClass("org.apache.commons.io.output.BrokenWriter");
    }

    private CommonsIOVersion getOrComputeCommonsIOVersion() {
        if(cachedCommonsIOVersion != null) {
            return cachedCommonsIOVersion;
        }
        final PsiClass psiClass = testDependencyInfoProvider.getPsiClass("org.apache.commons.io.input.NullInputStream");
        if(psiClass == null) {
            cachedCommonsIOVersion = CommonsIOVersion.None;
            return cachedCommonsIOVersion;
        }
        final PsiMethod[] constructors = psiClass.getConstructors();
        if(Arrays.stream(constructors).anyMatch(x -> x.getParameterList().getParameters().length == 0)) {
            cachedCommonsIOVersion = CommonsIOVersion.Has27;
            return cachedCommonsIOVersion;
        }
        cachedCommonsIOVersion = CommonsIOVersion.Has26;
        return cachedCommonsIOVersion;
    }
}
