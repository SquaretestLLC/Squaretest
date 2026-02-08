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

import java.io.*;

public enum MyClass {
    INSTANCE;

    public JsonDto parseInputStream(final InputStream is) throws IOException {
        is.read();
        return null;
    }
    public JsonDto parseReader(final Reader reader) throws IOException {
        reader.read();
        return null;
    }

    public JsonDto safeParseInputStream(final InputStream is) {
        is.read();
        return null;
    }
    public JsonDto safeParseReader(final Reader reader) {
        reader.read();
        return null;
    }

    public JsonDto safeParseInputStream(final InputStream is, final long offset) {
        is.read();
        return null;
    }
    public JsonDto safeParseReader(final Reader reader, final long offset) {
        reader.read();
        return null;
    }

    public void writeToOs(final JsonDto jsonDto, OutputStream outputStream) throws IOException {
        outputStream.write(5);
    }

    public void writeToWriter(final JsonDto jsonDto, Writer writer) throws IOException {
        writer.write("Hello");
    }

    public void safeWriteToOs(final JsonDto jsonDto, OutputStream outputStream) {
        System.out.println(jsonDto);
        System.out.println(outputStream);
    }

    public void safeWriteToWriter(final JsonDto jsonDto, Writer writer) {
        System.out.println(jsonDto);
        System.out.println(writer);
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        outputStream.write(5);
    }
}
