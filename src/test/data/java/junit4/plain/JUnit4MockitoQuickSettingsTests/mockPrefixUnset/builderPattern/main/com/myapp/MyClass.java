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

/**
 * Tests the builder pattern
 */
public class MyClass {

    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String salutation;
    private final String suffix;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final boolean isMale;
    private final boolean isEmployed;
    private final boolean isHomeOwner;

    private MyClass(String lastName, String firstName, String middleName,
                    String salutation, String suffix, String streetAddress,
                    String city, String state, boolean isMale, boolean isEmployed,
                    boolean isHomeOwner) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.salutation = salutation;
        this.suffix = suffix;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.isMale = isMale;

        this.isEmployed = isEmployed;
        this.isHomeOwner = isHomeOwner;
    }

    public class Builder {
        private String newLastName;
        private String newFirstName;
        private String newMiddleName;
        private String newSalutation;
        private String newSuffix;
        private String newStreetAddress;
        private String newCity;
        private String newState;
        private boolean newIsFemale;
        private boolean newIsEmployed;
        private boolean newIsHomeOwner;

        public Builder() {
        }

        public Builder setNewLastName(String newLastName) {
            this.newLastName = newLastName;
            return this;
        }

        public Builder setNewFirstName(String newFirstName) {
            this.newFirstName = newFirstName;
            return this;
        }

        public Builder setNewMiddleName(String newMiddleName) {
            this.newMiddleName = newMiddleName;
            return this;
        }

        public Builder setNewSalutation(String newSalutation) {
            this.newSalutation = newSalutation;
            return this;
        }

        public Builder setNewSuffix(String newSuffix) {
            this.newSuffix = newSuffix;
            return this;
        }

        public Builder setNewStreetAddress(String newStreetAddress) {
            this.newStreetAddress = newStreetAddress;
            return this;
        }

        public Builder setNewCity(String newCity) {
            this.newCity = newCity;
            return this;
        }

        public Builder setNewState(String newState) {
            this.newState = newState;
            return this;
        }

        public Builder setNewIsFemale(boolean newIsFemale) {
            this.newIsFemale = newIsFemale;
            return this;
        }

        public Builder setNewIsEmployed(boolean newIsEmployed) {
            this.newIsEmployed = newIsEmployed;
            return this;
        }

        public Builder setNewIsHomeOwner(boolean newIsHomeOwner) {
            this.newIsHomeOwner = newIsHomeOwner;
            return this;
        }

        public MyClass build() {
            return new MyClass(newLastName, newFirstName, newMiddleName, newSalutation, newSuffix, newStreetAddress, newCity, newState, newIsFemale, newIsEmployed, newIsHomeOwner);
        }
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public String getFullNameWithSuffix() {
        return firstName + lastName + suffix;
    }
}
