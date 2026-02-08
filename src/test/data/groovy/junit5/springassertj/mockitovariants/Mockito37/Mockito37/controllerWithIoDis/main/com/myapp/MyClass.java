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

import com.myapp.dtoio.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.*;

@RestController
@RequestMapping("/fooBeanController")
public class MyClass {
    private final FooProvider fooProvider;

    public MyClass(final FooProvider fooProvider) {
        this.fooProvider = fooProvider;
    }

    @GetMapping("/makeBeanWithIsAndIoArgs")
    public BeanWithIs makeBeanWithIsAndIoArgs(final InputStream requestInputStream, final OutputStream responseOutputStream) {
        return fooProvider.makeBeanWithIs();
    }

    @GetMapping("/makeBeanWithIsAndIoArgsAlt")
    public BeanWithIs makeBeanWithIsAndIoArgsAlt(final Reader requestReader, final Writer responseWriter) {
        return fooProvider.makeBeanWithIs();
    }

    @GetMapping("/makeBeanWithServletRequestResponseArgs")
    public BeanWithIs makeBeanWithServletRequestResponseArgs(final ServletRequest servletRequest, final ServletResponse servletResponse) {
        return fooProvider.makeBeanWithIs();
    }

    @GetMapping("/makeBeanWithIs")
    public BeanWithIs makeBeanWithIs() {
        return fooProvider.makeBeanWithIs();
    }

    @GetMapping("/makeBeanWithIsAndReader")
    public BeanWithIsAndReader makeBeanWithIsAndReader() {
        return fooProvider.makeBeanWithIsAndReader();
    }

    @GetMapping("/makeBeanWithReader")
    public BeanWithReader makeBeanWithReader() {
        return fooProvider.makeBeanWithReader();
    }

    @GetMapping("/makeBeanWithSubIs")
    public BeanWithSubIs makeBeanWithSubIs() {
        return fooProvider.makeBeanWithSubIs();
    }

    @GetMapping("/makeBeanWithSubIsMultipleSetterOverloads")
    public BeanWithSubIsMultipleSetterOverloads makeBeanWithSubIsMultipleSetterOverloads() {
        return fooProvider.makeBeanWithSubIsMultipleSetterOverloads();
    }

    @GetMapping("/makeBeanWithSubReader")
    public BeanWithSubReader makeBeanWithSubReader() {
        return fooProvider.makeBeanWithSubReader();
    }

    @GetMapping("/makeBeanWithSubReaderMultipleSetterOverloads")
    public BeanWithSubReaderMultipleSetterOverloads makeBeanWithSubReaderMultipleSetterOverloads() {
        return fooProvider.makeBeanWithSubReaderMultipleSetterOverloads();
    }

    @GetMapping("/makeCloseableBeanWithIs")
    public CloseableBeanWithIs makeCloseableBeanWithIs() {
        return fooProvider.makeCloseableBeanWithIs();
    }

    @GetMapping("/makeCloseableBeanWithReader")
    public CloseableBeanWithReader makeCloseableBeanWithReader() {
        return fooProvider.makeCloseableBeanWithReader();
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        output.write(input.read());
        return 0;
    }
}
