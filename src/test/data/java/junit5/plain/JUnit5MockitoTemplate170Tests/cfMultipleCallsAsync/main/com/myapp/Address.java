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

public class Address {
    private final String addressId;
    private final String name;
    private final String line1;
    private final String line2;
    private final String zipcode;

    public Address(final String addressId, final String name, final String line1, final String line2, final String zipcode) {
        this.addressId = addressId;
        this.name = name;
        this.line1 = line1;
        this.line2 = line2;
        this.zipcode = zipcode;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getName() {
        return name;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getZipcode() {
        return zipcode;
    }
}
