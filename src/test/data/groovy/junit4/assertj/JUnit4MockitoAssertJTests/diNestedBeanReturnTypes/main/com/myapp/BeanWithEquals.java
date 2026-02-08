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

import java.util.List;
import java.util.Objects;

public class BeanWithEquals {
    private FooData fooData;
    private String theString;
    private int theInt;
    private List<OtherSubBean> otherSubBeans;
    private boolean isValue;
    private boolean otherValue;
    private SubBean subBean;

    public FooData getFooData() {
        return fooData;
    }

    public void setFooData(FooData fooData) {
        this.fooData = fooData;
    }

    public String getTheString() {
        return theString;
    }

    public void setTheString(String theString) {
        this.theString = theString;
    }

    public int getTheInt() {
        return theInt;
    }

    public void setTheInt(int theInt) {
        this.theInt = theInt;
    }

    public List<OtherSubBean> getOtherSubBeans() {
        return otherSubBeans;
    }

    public void setOtherSubBeans(List<OtherSubBean> otherSubBeans) {
        this.otherSubBeans = otherSubBeans;
    }

    public boolean isValue() {
        return isValue;
    }

    public void setValue(boolean value) {
        isValue = value;
    }

    public boolean isOtherValue() {
        return otherValue;
    }

    public void setOtherValue(boolean otherValue) {
        this.otherValue = otherValue;
    }

    public SubBean getSubBean() {
        return subBean;
    }

    public void setSubBean(SubBean subBean) {
        this.subBean = subBean;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanWithEquals that = (BeanWithEquals) o;
        return theInt == that.theInt &&
                isValue == that.isValue &&
                otherValue == that.otherValue &&
                Objects.equals(fooData, that.fooData) &&
                Objects.equals(theString, that.theString) &&
                Objects.equals(otherSubBeans, that.otherSubBeans) &&
                Objects.equals(subBean, that.subBean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fooData, theString, theInt, otherSubBeans, isValue, otherValue, subBean);
    }
}
