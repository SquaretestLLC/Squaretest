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

import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static CreateBackupRequest getCreateBackupRequest(final String backupNameParam) {
        final CreateBackupRequest createBackupRequest = CreateBackupRequest.builder()
                .tableName("theTableName")
                // Squaretest won't use the correct placeholder for this. It only runs the "light dataflow" algorithm
                // on setter calls in external static methods.
                .backupName(backupNameParam)
                .build();
        return createBackupRequest;
    }

    public static CreateGlobalTableRequest getCreateGlobalTableRequest(final String tableNameParam, final String regionNameParam) {
        final CreateGlobalTableRequest createGlobalTableRequest = CreateGlobalTableRequest.builder()
                .globalTableName(tableNameParam)
                .replicationGroup(Replica.builder()
                        .regionName(regionNameParam)
                        .build())
                .build();
        return createGlobalTableRequest;
    }

    public static void printUnprocessedItems(final BatchWriteItemResponse result) {
        final Map<String, List<WriteRequest>> unprocessedItems = result.unprocessedItems();
        for(final Map.Entry<String, List<WriteRequest>> item : unprocessedItems.entrySet()) {
            final List<WriteRequest> requests = item.getValue();
            for(final WriteRequest request : requests) {
                final Map<String, AttributeValue> item1 = request.putRequest().item();
                System.out.println(item1);
            }
        }
    }
}
