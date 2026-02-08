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
        return convert(fooService.getFooData(id));
    }

    private InnerBeanDTO convert(final FooData fooData) {
        final InnerBeanDTO ret = new InnerBeanDTO();
        final InnerBeanData innerFoo = fooData.getFirstInnerBeanData();
        ret.setValue9(innerFoo.getValue9());
        ret.setValue8(innerFoo.getValue8());
        ret.setValue79(innerFoo.getValue79());
        ret.setValue78(innerFoo.getValue78());
        ret.setValue77(innerFoo.getValue77());
        ret.setValue76(innerFoo.getValue76());
        ret.setValue75(innerFoo.getValue75());
        ret.setValue74(innerFoo.getValue74());
        ret.setValue73(innerFoo.getValue73());
        ret.setValue72(innerFoo.getValue72());
        ret.setValue71(innerFoo.getValue71());
        ret.setValue70(innerFoo.getValue70());
        ret.setValue7(innerFoo.getValue7());
        ret.setValue69(innerFoo.getValue69());
        ret.setValue68(innerFoo.getValue68());
        ret.setValue67(innerFoo.getValue67());
        ret.setValue66(innerFoo.getValue66());
        ret.setValue65(innerFoo.getValue65());
        ret.setValue64(innerFoo.getValue64());
        ret.setValue63(innerFoo.getValue63());
        ret.setValue62(innerFoo.getValue62());
        ret.setValue61(innerFoo.getValue61());
        ret.setValue60(innerFoo.getValue60());
        ret.setValue6(innerFoo.getValue6());
        ret.setValue59(innerFoo.getValue59());
        ret.setValue58(innerFoo.getValue58());
        ret.setValue57(innerFoo.getValue57());
        ret.setValue56(innerFoo.getValue56());
        ret.setValue55(innerFoo.getValue55());
        ret.setValue54(innerFoo.getValue54());
        ret.setValue53(innerFoo.getValue53());
        ret.setValue52(innerFoo.getValue52());
        ret.setValue51(innerFoo.getValue51());
        ret.setValue50(innerFoo.getValue50());
        ret.setValue5(innerFoo.getValue5());
        ret.setValue49(innerFoo.getValue49());
        ret.setValue48(innerFoo.getValue48());
        ret.setValue47(innerFoo.getValue47());
        ret.setValue46(innerFoo.getValue46());
        ret.setValue45(innerFoo.getValue45());
        ret.setValue44(innerFoo.getValue44());
        ret.setValue43(innerFoo.getValue43());
        ret.setValue42(innerFoo.getValue42());
        ret.setValue41(innerFoo.getValue41());
        ret.setValue40(innerFoo.getValue40());
        ret.setValue4(innerFoo.getValue4());
        ret.setValue39(innerFoo.getValue39());
        ret.setValue38(innerFoo.getValue38());
        ret.setValue37(innerFoo.getValue37());
        ret.setValue36(innerFoo.getValue36());
        ret.setValue35(innerFoo.getValue35());
        ret.setValue34(innerFoo.getValue34());
        ret.setValue33(innerFoo.getValue33());
        ret.setValue32(innerFoo.getValue32());
        ret.setValue31(innerFoo.getValue31());
        ret.setValue30(innerFoo.getValue30());
        ret.setValue3(innerFoo.getValue3());
        ret.setValue29(innerFoo.getValue29());
        ret.setValue28(innerFoo.getValue28());
        ret.setValue27(innerFoo.getValue27());
        ret.setValue26(innerFoo.getValue26());
        ret.setValue25(innerFoo.getValue25());
        ret.setValue24(innerFoo.getValue24());
        ret.setValue23(innerFoo.getValue23());
        ret.setValue22(innerFoo.getValue22());
        ret.setValue21(innerFoo.getValue21());
        ret.setValue20(innerFoo.getValue20());
        ret.setValue2(innerFoo.getValue2());
        ret.setValue19(innerFoo.getValue19());
        ret.setValue18(innerFoo.getValue18());
        ret.setValue17(innerFoo.getValue17());
        ret.setValue16(innerFoo.getValue16());
        ret.setValue15(innerFoo.getValue15());
        ret.setValue14(innerFoo.getValue14());
        ret.setValue13(innerFoo.getValue13());
        ret.setValue12(innerFoo.getValue12());
        ret.setValue11(innerFoo.getValue11());
        ret.setValue10(innerFoo.getValue10());
        ret.setValue1(innerFoo.getValue1());
        ret.setName(innerFoo.getName());
        ret.setId(innerFoo.getId());
        ret.setCreateDate(innerFoo.getCreateDate());
        return ret;
    }
}
