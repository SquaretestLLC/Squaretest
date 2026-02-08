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

public class ReplicaSettingsDescriptionDTO {
    private String theRegionName;
    private String theReplicaStatus;
    private BillingModeSummaryDTO theBillingModeSummary;
    private long theReplicaProvisionedReadCapacityUnits;
    private AutoScalingSettingsDescriptionDTO theAutoScalingSettingsDescriptionDTO;
    private Long theReplicaProvisionedWriteCapacityUnits;

    public String getTheRegionName() {
        return theRegionName;
    }

    public void setTheRegionName(final String theRegionName) {
        this.theRegionName = theRegionName;
    }

    public String getTheReplicaStatus() {
        return theReplicaStatus;
    }

    public void setTheReplicaStatus(final String theReplicaStatus) {
        this.theReplicaStatus = theReplicaStatus;
    }

    public BillingModeSummaryDTO getTheBillingModeSummary() {
        return theBillingModeSummary;
    }

    public void setTheBillingModeSummary(final BillingModeSummaryDTO theBillingModeSummary) {
        this.theBillingModeSummary = theBillingModeSummary;
    }

    public long getTheReplicaProvisionedReadCapacityUnits() {
        return theReplicaProvisionedReadCapacityUnits;
    }

    public void setTheReplicaProvisionedReadCapacityUnits(final long theReplicaProvisionedReadCapacityUnits) {
        this.theReplicaProvisionedReadCapacityUnits = theReplicaProvisionedReadCapacityUnits;
    }

    public AutoScalingSettingsDescriptionDTO getTheAutoScalingSettingsDescriptionDTO() {
        return theAutoScalingSettingsDescriptionDTO;
    }

    public void setTheAutoScalingSettingsDescriptionDTO(final AutoScalingSettingsDescriptionDTO theAutoScalingSettingsDescriptionDTO) {
        this.theAutoScalingSettingsDescriptionDTO = theAutoScalingSettingsDescriptionDTO;
    }

    public Long getTheReplicaProvisionedWriteCapacityUnits() {
        return theReplicaProvisionedWriteCapacityUnits;
    }

    public void setTheReplicaProvisionedWriteCapacityUnits(final Long theReplicaProvisionedWriteCapacityUnits) {
        this.theReplicaProvisionedWriteCapacityUnits = theReplicaProvisionedWriteCapacityUnits;
    }
}
