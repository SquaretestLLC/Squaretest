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

import java.time.Instant;
import java.util.List;

public class GlobalTableDTO {
    private String arn;
    private Instant creationDate;
    private String tableName;
    private String tableStatus;
    List<ReplicaDescriptionDTO> replicaDescriptions;

    public GlobalTableDTO() {
    }

    public String getArn() {
        return this.arn;
    }

    public Instant getCreationDate() {
        return this.creationDate;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getTableStatus() {
        return this.tableStatus;
    }

    public List<ReplicaDescriptionDTO> getReplicaDescriptions() {
        return this.replicaDescriptions;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public void setReplicaDescriptions(List<ReplicaDescriptionDTO> replicaDescriptions) {
        this.replicaDescriptions = replicaDescriptions;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GlobalTableDTO)) return false;
        final GlobalTableDTO other = (GlobalTableDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$arn = this.getArn();
        final Object other$arn = other.getArn();
        if (this$arn == null ? other$arn != null : !this$arn.equals(other$arn)) return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
            return false;
        final Object this$tableName = this.getTableName();
        final Object other$tableName = other.getTableName();
        if (this$tableName == null ? other$tableName != null : !this$tableName.equals(other$tableName)) return false;
        final Object this$tableStatus = this.getTableStatus();
        final Object other$tableStatus = other.getTableStatus();
        if (this$tableStatus == null ? other$tableStatus != null : !this$tableStatus.equals(other$tableStatus))
            return false;
        final Object this$replicaDescriptions = this.getReplicaDescriptions();
        final Object other$replicaDescriptions = other.getReplicaDescriptions();
        if (this$replicaDescriptions == null ? other$replicaDescriptions != null : !this$replicaDescriptions.equals(other$replicaDescriptions))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GlobalTableDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $arn = this.getArn();
        result = result * PRIME + ($arn == null ? 43 : $arn.hashCode());
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        final Object $tableName = this.getTableName();
        result = result * PRIME + ($tableName == null ? 43 : $tableName.hashCode());
        final Object $tableStatus = this.getTableStatus();
        result = result * PRIME + ($tableStatus == null ? 43 : $tableStatus.hashCode());
        final Object $replicaDescriptions = this.getReplicaDescriptions();
        result = result * PRIME + ($replicaDescriptions == null ? 43 : $replicaDescriptions.hashCode());
        return result;
    }

    public String toString() {
        return "GlobalTableDTO(arn=" + this.getArn() + ", creationDate=" + this.getCreationDate() + ", tableName=" + this.getTableName() + ", tableStatus=" + this.getTableStatus() + ", replicaDescriptions=" + this.getReplicaDescriptions() + ")";
    }
}
