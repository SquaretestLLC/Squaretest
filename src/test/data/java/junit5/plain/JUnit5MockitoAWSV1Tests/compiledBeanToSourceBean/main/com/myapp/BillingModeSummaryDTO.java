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

public class BillingModeSummaryDTO {
    private String theBillingMode;
    private Date lastUpdateToPayPerRequestDateTime;

    public String getTheBillingMode() {
        return theBillingMode;
    }

    public void setTheBillingMode(final String theBillingMode) {
        this.theBillingMode = theBillingMode;
    }

    public Date getLastUpdateToPayPerRequestDateTime() {
        return lastUpdateToPayPerRequestDateTime;
    }

    public void setLastUpdateToPayPerRequestDateTime(final Date lastUpdateToPayPerRequestDateTime) {
        this.lastUpdateToPayPerRequestDateTime = lastUpdateToPayPerRequestDateTime;
    }
}
