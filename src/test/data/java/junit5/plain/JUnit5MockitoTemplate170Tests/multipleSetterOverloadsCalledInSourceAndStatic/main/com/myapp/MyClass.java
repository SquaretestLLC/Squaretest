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

    public InnerBeanDTO getFooData(final String id) {
        final FooData fooData = fooService.getFooData(id);
        final InnerBeanDTO firstConvert = ConverterUtils.convert(fooData);
        if(firstConvert != null) {
            return firstConvert;
        }
        return convert(fooData);
    }
    static InnerBeanDTO convert(final FooData fooData) {
        final InnerBeanDTO ret = new InnerBeanDTO();
        final InnerBeanData innerFoo = fooData.getFirstInnerBeanData();
        ret.setCreateDate(innerFoo.getCreateDate());
        ret.setId(innerFoo.getId());
        ret.setName(innerFoo.getName());
        ret.setValue1(innerFoo.getValue1());
        ret.setValue2(Value2Enum.valueOf(innerFoo.getValue2()));
        ret.setValue3(innerFoo.getValue3());
        ret.setValue4(innerFoo.getValue4());
        ret.setValue5(innerFoo.getValue5());
        ret.setValue6(innerFoo.getValue6());
        ret.setValue7(innerFoo.getValue7());
        ret.setValue8(innerFoo.getValue8());
        ret.setValue9(innerFoo.getValue9());
        ret.setValue10(innerFoo.getValue10());
        ret.setValue11(innerFoo.getValue11());
        ret.setValue12(innerFoo.getValue12());
        ret.setValue13(innerFoo.getValue13());
        ret.setValue14(innerFoo.getValue14());
        ret.setValue15(innerFoo.getValue15());
        ret.setValue16(innerFoo.getValue16());
        ret.setValue17(innerFoo.getValue17());
        return ret;
    }
}
