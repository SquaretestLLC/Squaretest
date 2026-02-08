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

    private final BeanFixer beanConverter;

    public MyClass(BeanFixer beanConverter) {
        this.beanConverter = beanConverter;
    }

    public OtherBean convertBean(final Bean theBean) {
        return beanConverter.convertBean(theBean);
    }

    public void storeBeanWithSameFieldName(final BeanWithSameMethodFieldNames beanWithSameMethodFieldNames) {
        System.out.println(beanWithSameMethodFieldNames);
    }

    public void storeBeanWithObjectMethods(final BeanWithObjectMethods beanWithObjectMethods) {
        System.out.println(beanWithObjectMethods);
    }

    public void storeBean3(final BeanWithDefaultConstructorInCode bean) {
        System.out.println(bean);
    }

    public void storeBean4(final BeanWithOneField bean) {
        System.out.println(bean);
    }

    public void storeBean5(final OuterBean.InnerBean innerBean) {
        System.out.println(bean);
    }

    public void storeNotBean1(final NotBeanExtraConstructor bean) {
        System.out.println(bean);
    }

    public void storeNotBean2(final NotBeanGetterOnly bean) {
        System.out.println(bean);
    }

    public void storeNotBean3(final NotBeanWithExtraMethod bean) {
        System.out.println(bean);
    }

    public void storeNotBean4(final NotBeanWithNoFields bean) {
        System.out.println(bean);
    }

}
