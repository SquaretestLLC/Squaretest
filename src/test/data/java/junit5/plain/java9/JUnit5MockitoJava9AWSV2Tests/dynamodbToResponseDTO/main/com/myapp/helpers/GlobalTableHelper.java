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

import software.amazon.awssdk.services.dynamodb.model.CreateGlobalTableResponse;
import software.amazon.awssdk.services.dynamodb.model.GlobalTableDescription;
import software.amazon.awssdk.services.dynamodb.model.ReplicaDescription;

import java.util.ArrayList;
import java.util.List;

public class GlobalTableHelper {
    public static GlobalTableDTO convert(final CreateGlobalTableResponse globalTableResponse) {
        final GlobalTableDTO ret = new GlobalTableDTO();
        final GlobalTableDescription description = globalTableResponse.globalTableDescription();
        ret.setArn(description.globalTableArn());
        ret.setTableName(description.globalTableName());
        ret.setCreationDate(description.creationDateTime());
        ret.setTableStatus(description.globalTableStatus().toString());
        List<ReplicaDescriptionDTO> replicaDescriptionDTOS = new ArrayList<>();
        for (ReplicaDescription replicaDescription : description.replicationGroup()) {
            final ReplicaDescriptionDTO replicaDescriptionDTO = new ReplicaDescriptionDTO();
            replicaDescriptionDTO.setTheReplicaStatus(replicaDescription.replicaStatusAsString());
            replicaDescriptionDTO.setTheRegionName(replicaDescription.regionName());
            replicaDescriptionDTO.setTheReplicaStatusDescription(replicaDescription.replicaStatusDescription());
            replicaDescriptionDTO.setTheReplicaStatusPercentProgress(replicaDescription.replicaStatusPercentProgress());
            replicaDescriptionDTOS.add(replicaDescriptionDTO);
        }
        ret.setReplicaDescriptions(replicaDescriptionDTOS);
        return ret;
    }
}
