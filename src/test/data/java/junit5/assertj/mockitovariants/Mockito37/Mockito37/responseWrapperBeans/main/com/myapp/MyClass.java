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

import com.myapp.otherbeans.*;
import com.myapp.otherbeans.SimpleBean;

public class MyClass {
    private final FooCreator1 fooCreator;

    public MyClass(final FooCreator1 fooCreator) {
        this.fooCreator = fooCreator;
    }

    public ResponseWrapper<SimpleBean> getFoo1() {
        return fooCreator.getFoo1();
    }

    public ResponseWrapperWithBeanAnnotations<SimpleBean> getFoo2() {
        return fooCreator.getFoo2();
    }

    public ResponseWrapperBeanAnnotationsNoStaticCreator<SimpleBean> getFoo3() {
        return fooCreator.getFoo3();
    }

    public BeanWithBeanAnnotationName getFoo4() {
        return fooCreator.getFoo4();
    }

    public BeanWithBeanAnnotationNameOnField getFoo5() {
        return fooCreator.getFoo5();
    }

    public BeanWithMissingBeanAnnotation getFoo6() {
        return fooCreator.getFoo6();
    }

    public NotBean getFoo7() {
        return fooCreator.getFoo7();
    }

    public BeanWithFieldNameBeanPrefixAnnotation getFoo8() {
        return fooCreator.getFoo8();
    }

    public BeanWithFieldNameBeanAnnotation getFoo9() {
        return fooCreator.getFoo9();
    }
}
