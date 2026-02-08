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

import com.google.common.util.concurrent.ListenableFuture;
import com.myapp.closeables.CloseableFoo;
import com.myapp.closeables.FooException;
import com.myapp.closeables.FooProvider;
import com.myapp.closeables.FooWithIs;

import java.io.*;
import java.sql.SQLException;
import java.util.concurrent.Future;

public class MyClass {

    private final FooProvider fooProvider;

    public MyClass(final FooProvider fooProvider) {
        this.fooProvider = fooProvider;
    }

    public Future<InputStream> makeInputStreamFuture() {
        return fooProvider.makeInputStreamFuture();
    }

    public Future<CloseableFoo> makeCloseableFooFuture() {
        return fooProvider.makeCloseableFooFuture();
    }

    public Future<FooWithIs> makeFooWithIsFuture() {
        return fooProvider.makeFooWithIsFuture();
    }

    public ListenableFuture<InputStream> makeInputStreamListenableFuture() {
        return fooProvider.makeInputStreamListenableFuture();
    }

    public ListenableFuture<CloseableFoo> makeCloseableFooListenableFuture() {
        return fooProvider.makeCloseableFooListenableFuture();
    }

    public ListenableFuture<FooWithIs> makeFooWithIsListenableFuture() {
        return fooProvider.makeFooWithIsListenableFuture();
    }

    public InputStream makeInputStream() {
        return fooProvider.makeInputStream();
    }

    public CloseableFoo makeCloseableFoo() {
        return fooProvider.makeCloseableFoo();
    }

    public FooWithIs makeFooWithIs() {
        return fooProvider.makeFooWithIs();
    }

    public Reader makeReader() {
        return fooProvider.makeReader();
    }

    public OutputStream makeOutputStream() {
        return fooProvider.makeOutputStream();
    }

    public Writer makeWriter() {
        return fooProvider.makeWriter();
    }
    
    public InputStream makeInputStreamThatThrows() throws IOException {
        return fooProvider.makeInputStream();
    }

    public CloseableFoo makeCloseableFooThatThrows() throws RuntimeException {
        return fooProvider.makeCloseableFoo();
    }

    public FooWithIs makeFooWithIsThatThrows() throws IOException {
        return fooProvider.makeFooWithIs();
    }

    public Reader makeReaderThatThrows() throws FooException {
        return fooProvider.makeReader();
    }

    public OutputStream makeOutputStreamThatThrows() throws IOException {
        return fooProvider.makeOutputStream();
    }

    public Writer makeWriterThatThrows() throws IOException {
        return fooProvider.makeWriter();
    }

    public InputStream makeInputStreamThatThrowsMultiple() throws IOException, RuntimeException {
        return fooProvider.makeInputStream();
    }

    public InputStream makeInputStreamThatThrowsMultipleNonIo() throws SQLException, FooException {
        return fooProvider.makeInputStream();
    }

    public ByteArrayInputStream makeByteArrayInputStream() {
        return fooProvider.makeByteArrayInputStream();
    }

    public ByteArrayOutputStream makeByteArrayOutputStream() {
        return fooProvider.makeByteArrayOutputStream();
    }
}