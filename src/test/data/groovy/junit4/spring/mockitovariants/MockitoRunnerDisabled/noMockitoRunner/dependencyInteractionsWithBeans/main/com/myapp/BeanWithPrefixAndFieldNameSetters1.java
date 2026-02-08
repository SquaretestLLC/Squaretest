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

import java.util.Date;

public class BeanWithPrefixAndFieldNameSetters1 {
    private long mId;
    private Date mCreationDate;
    private String mName;
    private String mAddress;

    public long id() {
        return mId;
    }

    public void id(long mId) {
        this.mId = mId;
    }

    public Date creationDate() {
        return mCreationDate;
    }

    public void creationDate(Date mCreationDate) {
        this.mCreationDate = mCreationDate;
    }

    public String name() {
        return mName;
    }

    public void name(String mName) {
        this.mName = mName;
    }

    public String address() {
        return mAddress;
    }

    public void address(String mAddress) {
        this.mAddress = mAddress;
    }
}
