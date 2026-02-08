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

import com.myapp.foos.BeanWithAcronymsInPropertyNames;
import com.myapp.foos.BeanWithAcronyms2;
import com.myapp.foos.BeanWithAcronyms3;

public class MyClass {
    public void doSomething(
            BeanWithAcronymsInPropertyNames jaxbListGetter) {
        System.out.println(jaxbListGetter);
    }

    public void doSomething1(final BeanWithAcronyms2 beanWithAcronyms2) {
        System.out.println(beanWithAcronyms2);
    }

    public void doSomething2(final BeanWithAcronyms3 beanWithAcronyms3) {
        System.out.println(beanWithAcronyms3);
    }
}
