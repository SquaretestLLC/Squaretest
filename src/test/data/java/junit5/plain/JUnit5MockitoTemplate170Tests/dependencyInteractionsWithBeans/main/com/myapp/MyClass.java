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

import java.util.function.Supplier;

public class MyClass {

    private final BeanFixer beanConverter;

    public MyClass(BeanFixer beanConverter) {
        this.beanConverter = beanConverter;
    }

    public OtherBean convertBean(final Bean theBean) {
        return beanConverter.convertBean(theBean);
    }

    public OtherBean convertDefaultBeanAnonInnerClass() {
        return beanConverter.convertBean(new Bean(){ });
    }

    public OtherBean convertDefaultObjectAnonInnerClass() {
        return beanConverter.convertObject(new Bean(){ });
    }

    public Bean createGenericBean() {
        return beanConverter.createGenericBean(new Bean());
    }

    public Bean createGenericBeanWithAnonInnerClass() {
        return beanConverter.createGenericBean(new Bean(){ });
    }

    public void createGenericBeanWithLocalClass() {
        class PhoneNumber {
            private String num;
            public String getNum() {
                return this.num;
            }
            public void setNum(final String num) {
                this.num = num;
            }
        }
        beanConverter.createGenericBean(new PhoneNumber());
    }

    public String createGenericBeanWithLocalClass1() {
        class PhoneNumber {
            private String num;
            public String getNum() {
                return this.num;
            }
            public void setNum(final String num) {
                this.num = num;
            }
        }
        final PhoneNumber phoneNumber = beanConverter.createGenericBean(new PhoneNumber());
        return phoneNumber.toString();
    }

    public void createGenericBeanWithAnonLocalClass() {
        class PhoneNumber {
            private String num;
            public String getNum() {
                return this.num;
            }
            public void setNum(final String num) {
                this.num = num;
            }
        }
        beanConverter.createGenericBean(new PhoneNumber(){});
    }

    public String createGenericBeanWithAnonLocalClass1() {
        class PhoneNumber {
            private String num;
            public String getNum() {
                return this.num;
            }
            public void setNum(final String num) {
                this.num = num;
            }
        }
        final PhoneNumber ret = beanConverter.createGenericBean(new PhoneNumber(){});
        return ret.toString();
    }

    public void makeBeanWithClass_LocalClass(final String beanId) {
        class PhoneNumber {
            private String num;
            public String getNum() {
                return this.num;
            }
            public void setNum(final String num) {
                this.num = num;
            }
        }
        final PhoneNumber number = beanConverter.createGenericBeanWithClass(PhoneNumber.class);
    }

    public void makeBeanWithClass_InnerInstanceClass(final PhoneNumberInstance phoneNumberInstance) {
        final PhoneNumberInstance number = beanConverter.createGenericBeanWithClass(PhoneNumberInstance.class);
    }

    public void makeBeanWithClass_InnerStaticClass(final PhoneNumberStatic phoneNumberStatic) {
        final PhoneNumberStatic number = beanConverter.createGenericBeanWithClass(PhoneNumberStatic.class);
    }

    class PhoneNumberInstance {
        private String num;
        public String getNum() {
            return this.num;
        }
        public void setNum(final String num) {
            this.num = num;
        }
    }

    static class PhoneNumberStatic {
        private String num;
        public String getNum() {
            return this.num;
        }
        public void setNum(final String num) {
            this.num = num;
        }
    }

    public void makeBeanWithClass_AnonClass(final String beanId) {
        beanConverter.createGenericBeanWithClass(new Bean(){}.getClass());
    }

    public String makeBeanWithClass_AnonClass1(final String beanId) {
        final Bean ret = beanConverter.createGenericBeanWithClass(new Bean(){}.getClass());
        return ret.toString();
    }

    public void makeBeanWithClass_LambdaClass(final String beanId) {
        beanConverter.createGenericBeanWithClass(((Supplier<String>)() -> "").getClass());
    }

    public String makeBeanWithClass_LambdaClass1(final String beanId) {
        final Supplier<String> ret = beanConverter.createGenericBeanWithClass(((Supplier<String>)() -> "").getClass());
        return ret.get();
    }

    public void storeHungarianNotationBean(final HungarianBean hungarianBean) {
        System.out.println(hungarianBean);
    }

    public void storeBeanWithSameFieldName(final BeanWithSameMethodFieldNames beanWithSameMethodFieldNames) {
        System.out.println(beanWithSameMethodFieldNames);
    }

    public void storeBeanWithObjectMethods(final BeanWithObjectMethods beanWithObjectMethods) {
        System.out.println(beanWithObjectMethods);
    }

    public void storeBeanWithAltBooleans(final BeanWithAltBooleans beanWithAltBooleans) {
        System.out.println(beanWithAltBooleans);
    }

    public void storeBeanWithExtraMethodThatEndsInDto(final BeanWithExtraMethodButEndsInDto bean) {
        System.out.println(bean);
    }

    public void storeBean3(final BeanWithDefaultConstructorInCode bean) {
        System.out.println(bean);
    }

    public void storeBean4(final BeanWithOneField bean) {
        System.out.println(bean);
    }

    public void storeBeanJAXBBeanWithAcronym(final GetAllCountriesMapRequest bean) {
        System.out.println(bean);
    }

    public void storeBeanWithFieldsWithSameLowercaseNames(final BeanWithFieldsWithSameLowercaseNames bean) {
        System.out.println(bean);
    }

    public void storeBeanWithIdsWithPrefixes1(final BeanWithIdsWithPrefixes1 bean) {
        System.out.println(bean);
    }

    public void storeBeanWithIdsWithPrefixes2(final BeanWithIdsWithPrefixes2 bean) {
        System.out.println(bean);
    }

    public void storeBeanWithFieldPrefixInFieldAndGetter(final BeanWithFieldPrefixInFieldAndGetter bean) {
        System.out.println(bean);
    }

    public void storeBeanWithFieldPrefixInFieldAndGetter1(final BeanWithFieldPrefixInFieldAndGetter1 bean) {
        System.out.println(bean);
    }

    public void storeBeanWithFieldPrefixInFieldAndGetter2(final BeanWithFieldPrefixInFieldAndGetter2 bean) {
        System.out.println(bean);
    }

    public void storeBeanWithFieldPrefixInFieldButNotGetter(final BeanWithFieldPrefixInFieldButNotGetter bean) {
        System.out.println(bean);
    }

    public void storeBeanWithPrefixAndFieldNameSetters(final BeanWithPrefixAndFieldNameSetters bean) {
        System.out.println(bean);
    }

    public void storeBeanWithPrefixAndFieldNameSetters1(final BeanWithPrefixAndFieldNameSetters1 bean) {
        System.out.println(bean);
    }

    public void storeBean5(final OuterBean.InnerBean innerBean) {
        System.out.println(innerBean);
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
