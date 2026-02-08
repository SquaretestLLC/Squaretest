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

public class ResponseBean {
    private Bean primaryBean;
    private SubBean subBean;
    private OtherSubBean otherSubBean;
    private List<Bean> secondaryBeans;
    private List<Bean> thirdBeans;

    public Bean getPrimaryBean() {
        return primaryBean;
    }

    public void setPrimaryBean(Bean primaryBean) {
        this.primaryBean = primaryBean;
    }

    public SubBean getSubBean() {
        return subBean;
    }

    public void setSubBean(SubBean subBean) {
        this.subBean = subBean;
    }

    public OtherSubBean getOtherSubBean() {
        return otherSubBean;
    }

    public void setOtherSubBean(OtherSubBean otherSubBean) {
        this.otherSubBean = otherSubBean;
    }

    public List<Bean> getSecondaryBeans() {
        return secondaryBeans;
    }

    public void setSecondaryBeans(List<Bean> secondaryBeans) {
        this.secondaryBeans = secondaryBeans;
    }

    public List<Bean> getThirdBeans() {
        return thirdBeans;
    }

    public void setThirdBeans(List<Bean> thirdBeans) {
        this.thirdBeans = thirdBeans;
    }
}
