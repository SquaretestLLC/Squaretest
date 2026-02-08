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

public class ReplicaGlobalSecondaryIndexSettingsDescriptionDTO {
    private String theIndexName;
    private String theIndexStatus;
    private Long theProvisionedReadCapacityUnits;
    private Long theProvisionedWriteCapacityUnits;

    public String getTheIndexName() {
        return theIndexName;
    }

    public void setTheIndexName(final String theIndexName) {
        this.theIndexName = theIndexName;
    }

    public String getTheIndexStatus() {
        return theIndexStatus;
    }

    public void setTheIndexStatus(final String theIndexStatus) {
        this.theIndexStatus = theIndexStatus;
    }

    public Long getTheProvisionedReadCapacityUnits() {
        return theProvisionedReadCapacityUnits;
    }

    public void setTheProvisionedReadCapacityUnits(final Long theProvisionedReadCapacityUnits) {
        this.theProvisionedReadCapacityUnits = theProvisionedReadCapacityUnits;
    }

    public Long getTheProvisionedWriteCapacityUnits() {
        return theProvisionedWriteCapacityUnits;
    }

    public void setTheProvisionedWriteCapacityUnits(final Long theProvisionedWriteCapacityUnits) {
        this.theProvisionedWriteCapacityUnits = theProvisionedWriteCapacityUnits;
    }
}
