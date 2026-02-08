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

public class MyClass {
    private final FooService fooService;

    public MyClass(final FooService fooService) {
        this.fooService = fooService;
    }

    public BeanWithAllPackageLocalMethods getFoo1(final String id) {
        return fooService.getFoo1(id);
    }

    public String putFoo1(final String id) {
        final BeanWithAllPackageLocalMethodsAndWithMethods bean = new BeanWithAllPackageLocalMethodsAndWithMethods()
                .withId(id)
                .withName("HardcodedName")
                .withProp1("prop1Value")
                .withProp2("prop2Value")
                .withProp3("prop3Value")
                .withProp4("prop4Value")
                .withProp5("prop5Value");
        return fooService.putFoo1(bean);
    }

    public DataWithPackageLocalFactoryMethod getFoo2(final String id) {
        return fooService.getFoo2(id);
    }

    public DataWithPackageLocalGenericFactoryMethod<String> getFoo3(final String id) {
        return fooService.getFoo3(id);
    }

    public DataWithPackageLocalConstructor getFoo4(final String id) {
        return fooService.getFoo4(id);
    }

    public DataWithPackageLocalGenericConstructor<String> getFoo5(final String id) {
        return fooService.getFoo5(id);
    }

    public DataWithGenericAndBothConstructors<String> getFoo6(final String id) {
        return fooService.getFoo6(id);
    }

}
