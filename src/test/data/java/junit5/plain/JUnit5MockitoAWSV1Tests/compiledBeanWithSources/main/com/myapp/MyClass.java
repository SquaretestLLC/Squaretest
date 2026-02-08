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

import java.util.List;

public class MyClass {
    private final String tableName;
    private final AmazonDynamoDBClient dynamoDB;


    public MyClass(final String tableName, final AmazonDynamoDBClient dynamoDB) {
        this.tableName = tableName;
        this.dynamoDB = dynamoDB;
    }

    public String getTableInfo() {
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName(tableName);
        final DescribeGlobalTableSettingsResult result = dynamoDB.describeGlobalTableSettings(request);
        final StringBuilder ret = new StringBuilder();
        ret.append(result.getGlobalTableName());
        final List<ReplicaSettingsDescription> replicaSettings = result.getReplicaSettings();
        for(final ReplicaSettingsDescription replicaSettingsDescription : replicaSettings) {
            ret.append(replicaSettingsDescription.getReplicaStatus());
            final BillingModeSummary replicaBillingModeSummary = replicaSettingsDescription.getReplicaBillingModeSummary();
            ret.append(replicaBillingModeSummary.getBillingMode());
            ret.append(replicaBillingModeSummary.getLastUpdateToPayPerRequestDateTime());
            final List<ReplicaGlobalSecondaryIndexSettingsDescription> replicaGlobalSecondaryIndexSettings = replicaSettingsDescription.getReplicaGlobalSecondaryIndexSettings();
            for(final ReplicaGlobalSecondaryIndexSettingsDescription secondaryIndexSettingsDescription : replicaGlobalSecondaryIndexSettings) {
                ret.append(secondaryIndexSettingsDescription.getIndexStatus());
                ret.append(secondaryIndexSettingsDescription.getIndexName());
                ret.append(secondaryIndexSettingsDescription.getProvisionedReadCapacityUnits());
                ret.append(secondaryIndexSettingsDescription.getProvisionedWriteCapacityUnits());
                final AutoScalingSettingsDescription provisionedReadCapacityAutoScalingSettings = secondaryIndexSettingsDescription.getProvisionedReadCapacityAutoScalingSettings();
                ret.append(provisionedReadCapacityAutoScalingSettings.getMaximumUnits());
                ret.append(provisionedReadCapacityAutoScalingSettings.getAutoScalingDisabled());
                ret.append(provisionedReadCapacityAutoScalingSettings.getMinimumUnits());
                final List<AutoScalingPolicyDescription> scalingPolicies = provisionedReadCapacityAutoScalingSettings.getScalingPolicies();
                for(final AutoScalingPolicyDescription autoScalingPolicyDescription : scalingPolicies) {
                    ret.append(autoScalingPolicyDescription.getPolicyName());
                    final AutoScalingTargetTrackingScalingPolicyConfigurationDescription targetTrackingScalingPolicyConfiguration = autoScalingPolicyDescription.getTargetTrackingScalingPolicyConfiguration();
                    ret.append(targetTrackingScalingPolicyConfiguration.getTargetValue());
                    ret.append(targetTrackingScalingPolicyConfiguration.getDisableScaleIn());
                    ret.append(targetTrackingScalingPolicyConfiguration.getScaleInCooldown());
                    ret.append(targetTrackingScalingPolicyConfiguration.getScaleOutCooldown());
                }
            }
        }
        return ret.toString();
    }


}
