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
package com.myapp.helpers;

public class ReplicaDescriptionDTO {
    private String theRegionName;
    private String theReplicaStatusDescription;
    private String theReplicaStatusPercentProgress;
    private String theReplicaStatus;

    public ReplicaDescriptionDTO() {
    }

    public String getTheRegionName() {
        return this.theRegionName;
    }

    public String getTheReplicaStatusDescription() {
        return this.theReplicaStatusDescription;
    }

    public String getTheReplicaStatusPercentProgress() {
        return this.theReplicaStatusPercentProgress;
    }

    public String getTheReplicaStatus() {
        return this.theReplicaStatus;
    }

    public void setTheRegionName(String theRegionName) {
        this.theRegionName = theRegionName;
    }

    public void setTheReplicaStatusDescription(String theReplicaStatusDescription) {
        this.theReplicaStatusDescription = theReplicaStatusDescription;
    }

    public void setTheReplicaStatusPercentProgress(String theReplicaStatusPercentProgress) {
        this.theReplicaStatusPercentProgress = theReplicaStatusPercentProgress;
    }

    public void setTheReplicaStatus(String theReplicaStatus) {
        this.theReplicaStatus = theReplicaStatus;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReplicaDescriptionDTO)) return false;
        final ReplicaDescriptionDTO other = (ReplicaDescriptionDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$theRegionName = this.getTheRegionName();
        final Object other$theRegionName = other.getTheRegionName();
        if (this$theRegionName == null ? other$theRegionName != null : !this$theRegionName.equals(other$theRegionName))
            return false;
        final Object this$theReplicaStatusDescription = this.getTheReplicaStatusDescription();
        final Object other$theReplicaStatusDescription = other.getTheReplicaStatusDescription();
        if (this$theReplicaStatusDescription == null ? other$theReplicaStatusDescription != null : !this$theReplicaStatusDescription.equals(other$theReplicaStatusDescription))
            return false;
        final Object this$theReplicaStatusPercentProgress = this.getTheReplicaStatusPercentProgress();
        final Object other$theReplicaStatusPercentProgress = other.getTheReplicaStatusPercentProgress();
        if (this$theReplicaStatusPercentProgress == null ? other$theReplicaStatusPercentProgress != null : !this$theReplicaStatusPercentProgress.equals(other$theReplicaStatusPercentProgress))
            return false;
        final Object this$theReplicaStatus = this.getTheReplicaStatus();
        final Object other$theReplicaStatus = other.getTheReplicaStatus();
        if (this$theReplicaStatus == null ? other$theReplicaStatus != null : !this$theReplicaStatus.equals(other$theReplicaStatus))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReplicaDescriptionDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $theRegionName = this.getTheRegionName();
        result = result * PRIME + ($theRegionName == null ? 43 : $theRegionName.hashCode());
        final Object $theReplicaStatusDescription = this.getTheReplicaStatusDescription();
        result = result * PRIME + ($theReplicaStatusDescription == null ? 43 : $theReplicaStatusDescription.hashCode());
        final Object $theReplicaStatusPercentProgress = this.getTheReplicaStatusPercentProgress();
        result = result * PRIME + ($theReplicaStatusPercentProgress == null ? 43 : $theReplicaStatusPercentProgress.hashCode());
        final Object $theReplicaStatus = this.getTheReplicaStatus();
        result = result * PRIME + ($theReplicaStatus == null ? 43 : $theReplicaStatus.hashCode());
        return result;
    }

    public String toString() {
        return "ReplicaDescriptionDTO(theRegionName=" + this.getTheRegionName() + ", theReplicaStatusDescription=" + this.getTheReplicaStatusDescription() + ", theReplicaStatusPercentProgress=" + this.getTheReplicaStatusPercentProgress() + ", theReplicaStatus=" + this.getTheReplicaStatus() + ")";
    }
}
