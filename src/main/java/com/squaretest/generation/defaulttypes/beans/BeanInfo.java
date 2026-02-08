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
package com.squaretest.generation.defaulttypes.beans;

import org.jetbrains.annotations.Nullable;

public class BeanInfo {

    private final boolean dtoBean;
    private final boolean dtoBeanWithInputIoProperty;
    @Nullable
    private final BeanMethodsInfo beanMethodsInfo;

    public BeanInfo(final boolean dtoBean, final boolean dtoBeanWithInputIoProperty) {
        this(dtoBean, dtoBeanWithInputIoProperty, null);
    }

    public BeanInfo(
            final boolean dtoBean, final boolean dtoBeanWithInputIoProperty, @Nullable final BeanMethodsInfo beanMethodsInfo) {
        this.dtoBean = dtoBean;
        this.dtoBeanWithInputIoProperty = dtoBeanWithInputIoProperty;
        this.beanMethodsInfo = beanMethodsInfo;
    }

    @Nullable
    public BeanMethodsInfo getBeanMethodsInfo() {
        return beanMethodsInfo;
    }

    public boolean hasBeanMethodsInfo() {
        return beanMethodsInfo != null;
    }

    public boolean isDtoBean() {
        return dtoBean;
    }

    public boolean isDtoBeanWithInputIoProperty() {
        return dtoBeanWithInputIoProperty;
    }
}
