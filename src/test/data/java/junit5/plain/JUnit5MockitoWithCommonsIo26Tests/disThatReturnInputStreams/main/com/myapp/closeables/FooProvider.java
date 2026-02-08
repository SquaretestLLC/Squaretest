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
package com.myapp.closeables;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.*;
import java.util.concurrent.Future;

public abstract class FooProvider {
    public abstract Future<InputStream> makeInputStreamFuture();
    public abstract Future<CloseableFoo> makeCloseableFooFuture();
    public abstract Future<FooWithIs> makeFooWithIsFuture();

    public abstract ListenableFuture<InputStream> makeInputStreamListenableFuture();
    public abstract ListenableFuture<CloseableFoo> makeCloseableFooListenableFuture();
    public abstract ListenableFuture<FooWithIs> makeFooWithIsListenableFuture();

    public abstract InputStream makeInputStream();
    public abstract CloseableFoo makeCloseableFoo();
    public abstract FooWithIs makeFooWithIs();

    public abstract Reader makeReader();
    public abstract OutputStream makeOutputStream();
    public abstract Writer makeWriter();
    public abstract ByteArrayInputStream makeByteArrayInputStream();
    public abstract ByteArrayOutputStream makeByteArrayOutputStream();
}
