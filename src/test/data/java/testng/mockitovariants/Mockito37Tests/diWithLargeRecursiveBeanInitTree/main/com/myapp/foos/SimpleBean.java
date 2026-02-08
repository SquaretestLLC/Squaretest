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
package com.myapp.foos;

import java.time.Instant;
import java.util.Currency;

public class SimpleBean {
    private long myId;
    private String myName;
    private long myOtherId;
    private String myLastName;
    private Instant myCreationDate;
    private String myAddressLine1;
    private String myAddressLine2;
    private String myZipCode;
    private String myCountry;
    private Currency myCurrency;

    public long getMyId() {
        return myId;
    }

    public void setMyId(long myId) {
        this.myId = myId;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public long getMyOtherId() {
        return myOtherId;
    }

    public void setMyOtherId(long myOtherId) {
        this.myOtherId = myOtherId;
    }

    public String getMyLastName() {
        return myLastName;
    }

    public void setMyLastName(String myLastName) {
        this.myLastName = myLastName;
    }

    public Instant getMyCreationDate() {
        return myCreationDate;
    }

    public void setMyCreationDate(Instant myCreationDate) {
        this.myCreationDate = myCreationDate;
    }

    public String getMyAddressLine1() {
        return myAddressLine1;
    }

    public void setMyAddressLine1(String myAddressLine1) {
        this.myAddressLine1 = myAddressLine1;
    }

    public String getMyAddressLine2() {
        return myAddressLine2;
    }

    public void setMyAddressLine2(String myAddressLine2) {
        this.myAddressLine2 = myAddressLine2;
    }

    public String getMyZipCode() {
        return myZipCode;
    }

    public void setMyZipCode(String myZipCode) {
        this.myZipCode = myZipCode;
    }

    public String getMyCountry() {
        return myCountry;
    }

    public void setMyCountry(String myCountry) {
        this.myCountry = myCountry;
    }

    public Currency getMyCurrency() {
        return myCurrency;
    }

    public void setMyCurrency(Currency myCurrency) {
        this.myCurrency = myCurrency;
    }
}
