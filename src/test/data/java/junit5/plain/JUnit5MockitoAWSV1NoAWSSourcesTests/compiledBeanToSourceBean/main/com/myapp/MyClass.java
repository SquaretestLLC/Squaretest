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

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    private final String tableName;
    private final AmazonDynamoDBClient dynamoDB;


    public MyClass(final String tableName, final AmazonDynamoDBClient dynamoDB) {
        this.tableName = tableName;
        this.dynamoDB = dynamoDB;
    }

    public ResultDTO getTableInfo() {
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName(tableName);
        final DescribeGlobalTableSettingsResult result = dynamoDB.describeGlobalTableSettings(request);
        final ResultDTO ret = new ResultDTO();
        ret.setTheTableName(result.getGlobalTableName());
        final List<ReplicaSettingsDescriptionDTO> replicaSettingsDescriptionDTOS = new ArrayList<>();
        final List<ReplicaSettingsDescription> replicaSettings = result.getReplicaSettings();
        for(final ReplicaSettingsDescription replicaSettingsDescription : replicaSettings) {
            final ReplicaSettingsDescriptionDTO replicaSettingsDescriptionDTO = getReplicaSettingsDescriptionDTO(replicaSettingsDescription);
            final AutoScalingSettingsDescriptionDTO autoScalingSettingsDescriptionDTO = getAutoScalingSettingsDescriptionDTO(replicaSettingsDescription);
            replicaSettingsDescriptionDTOS.add(replicaSettingsDescriptionDTO);
            replicaSettingsDescriptionDTO.setTheAutoScalingSettingsDescriptionDTO(autoScalingSettingsDescriptionDTO);
        }
        ret.setTheReplicaSettings(replicaSettingsDescriptionDTOS);
        return ret;
    }

    private AutoScalingSettingsDescriptionDTO getAutoScalingSettingsDescriptionDTO(final ReplicaSettingsDescription replicaSettingsDescription) {
        final AutoScalingSettingsDescriptionDTO autoScalingSettingsDescriptionDTO = new AutoScalingSettingsDescriptionDTO();
        final AutoScalingSettingsDescription autoScalingSettingsDescription = replicaSettingsDescription.getReplicaProvisionedReadCapacityAutoScalingSettings();
        autoScalingSettingsDescriptionDTO.setTheAutoScalingDisabled(autoScalingSettingsDescription.getAutoScalingDisabled());
        autoScalingSettingsDescriptionDTO.setTheAutoScalingRoleArn(autoScalingSettingsDescription.getAutoScalingRoleArn());
        autoScalingSettingsDescriptionDTO.setTheMaximumUnits(autoScalingSettingsDescription.getMaximumUnits());
        autoScalingSettingsDescriptionDTO.setTheMinimumUnits(autoScalingSettingsDescription.getMinimumUnits());
        final List<AutoScalingPolicyDescriptionDTO> autoScalingPolicyDescriptionDTOS = new ArrayList<>();
        for(final AutoScalingPolicyDescription autoScalingPolicyDescription : autoScalingSettingsDescription.getScalingPolicies()) {
            final AutoScalingPolicyDescriptionDTO autoScalingPolicyDescriptionDTO = getAutoScalingPolicyDescriptionDTO(autoScalingPolicyDescription);
            autoScalingPolicyDescriptionDTOS.add(autoScalingPolicyDescriptionDTO);
        }
        autoScalingSettingsDescriptionDTO.setTheScalingPolicies(autoScalingPolicyDescriptionDTOS);
        return autoScalingSettingsDescriptionDTO;
    }

    private AutoScalingPolicyDescriptionDTO getAutoScalingPolicyDescriptionDTO(final AutoScalingPolicyDescription autoScalingPolicyDescription) {
        final AutoScalingPolicyDescriptionDTO autoScalingPolicyDescriptionDTO = new AutoScalingPolicyDescriptionDTO();
        autoScalingPolicyDescriptionDTO.setThePolicyName(autoScalingPolicyDescription.getPolicyName());
        final AutoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO = new AutoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO();
        final AutoScalingTargetTrackingScalingPolicyConfigurationDescription autoScalingTargetTrackingScalingPolicyConfigurationDescription = autoScalingPolicyDescription.getTargetTrackingScalingPolicyConfiguration();
        autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO.setTheDisableScaleIn(autoScalingTargetTrackingScalingPolicyConfigurationDescription.getDisableScaleIn());
        autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO.setTheScaleInCooldown(autoScalingTargetTrackingScalingPolicyConfigurationDescription.getScaleInCooldown());
        autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO.setTheScaleOutCooldown(autoScalingTargetTrackingScalingPolicyConfigurationDescription.getScaleOutCooldown());
        autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO.setTheTargetValue(autoScalingTargetTrackingScalingPolicyConfigurationDescription.getTargetValue());
        autoScalingPolicyDescriptionDTO.setTheTargetTrackingScalingPolicyConfiguration(autoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO);
        return autoScalingPolicyDescriptionDTO;
    }

    private ReplicaSettingsDescriptionDTO getReplicaSettingsDescriptionDTO(final ReplicaSettingsDescription replicaSettingsDescription) {
        final ReplicaSettingsDescriptionDTO replicaSettingsDescriptionDTO = new ReplicaSettingsDescriptionDTO();
        replicaSettingsDescriptionDTO.setTheRegionName(replicaSettingsDescription.getRegionName());
        replicaSettingsDescriptionDTO.setTheReplicaStatus(replicaSettingsDescription.getReplicaStatus());
        replicaSettingsDescriptionDTO.setTheReplicaProvisionedReadCapacityUnits(replicaSettingsDescription.getReplicaProvisionedReadCapacityUnits());
        replicaSettingsDescriptionDTO.setTheReplicaProvisionedWriteCapacityUnits(replicaSettingsDescription.getReplicaProvisionedWriteCapacityUnits());
        final BillingModeSummaryDTO billingModeSummaryDTO = getBillingModeSummaryDTO(replicaSettingsDescription);
        replicaSettingsDescriptionDTO.setTheBillingModeSummary(billingModeSummaryDTO);
        return replicaSettingsDescriptionDTO;
    }

    private BillingModeSummaryDTO getBillingModeSummaryDTO(final ReplicaSettingsDescription replicaSettingsDescription) {
        final BillingModeSummaryDTO billingModeSummaryDTO = new BillingModeSummaryDTO();
        final BillingModeSummary billingModeSummary = replicaSettingsDescription.getReplicaBillingModeSummary();
        billingModeSummaryDTO.setTheBillingMode(billingModeSummary.getBillingMode());
        billingModeSummaryDTO.setLastUpdateToPayPerRequestDateTime(billingModeSummary.getLastUpdateToPayPerRequestDateTime());
        return billingModeSummaryDTO;
    }


}
