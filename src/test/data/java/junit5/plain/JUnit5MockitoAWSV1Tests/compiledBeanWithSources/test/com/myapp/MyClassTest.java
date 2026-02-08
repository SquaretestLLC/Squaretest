package com.myapp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonDynamoDBClient mockDynamoDB;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass("tableName", mockDynamoDB);
    }

    @Test
    void testGetTableInfo() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResult describeGlobalTableSettingsResult = new DescribeGlobalTableSettingsResult();
        describeGlobalTableSettingsResult.setGlobalTableName("globalTableName");
        final ReplicaSettingsDescription replicaSettingsDescription = new ReplicaSettingsDescription();
        replicaSettingsDescription.setReplicaStatus("replicaStatus");
        final BillingModeSummary replicaBillingModeSummary = new BillingModeSummary();
        replicaBillingModeSummary.setBillingMode("billingMode");
        replicaBillingModeSummary.setLastUpdateToPayPerRequestDateTime(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        replicaSettingsDescription.setReplicaBillingModeSummary(replicaBillingModeSummary);
        final ReplicaGlobalSecondaryIndexSettingsDescription replicaGlobalSecondaryIndexSettingsDescription = new ReplicaGlobalSecondaryIndexSettingsDescription();
        replicaGlobalSecondaryIndexSettingsDescription.setIndexName("indexName");
        replicaGlobalSecondaryIndexSettingsDescription.setIndexStatus("indexStatus");
        replicaGlobalSecondaryIndexSettingsDescription.setProvisionedReadCapacityUnits(0L);
        final AutoScalingSettingsDescription provisionedReadCapacityAutoScalingSettings = new AutoScalingSettingsDescription();
        provisionedReadCapacityAutoScalingSettings.setMinimumUnits(0L);
        provisionedReadCapacityAutoScalingSettings.setMaximumUnits(0L);
        provisionedReadCapacityAutoScalingSettings.setAutoScalingDisabled(false);
        final AutoScalingPolicyDescription autoScalingPolicyDescription = new AutoScalingPolicyDescription();
        autoScalingPolicyDescription.setPolicyName("policyName");
        final AutoScalingTargetTrackingScalingPolicyConfigurationDescription targetTrackingScalingPolicyConfiguration = new AutoScalingTargetTrackingScalingPolicyConfigurationDescription();
        targetTrackingScalingPolicyConfiguration.setDisableScaleIn(false);
        targetTrackingScalingPolicyConfiguration.setScaleInCooldown(0);
        targetTrackingScalingPolicyConfiguration.setScaleOutCooldown(0);
        targetTrackingScalingPolicyConfiguration.setTargetValue(0.0);
        autoScalingPolicyDescription.setTargetTrackingScalingPolicyConfiguration(
                targetTrackingScalingPolicyConfiguration);
        provisionedReadCapacityAutoScalingSettings.setScalingPolicies(Arrays.asList(autoScalingPolicyDescription));
        replicaGlobalSecondaryIndexSettingsDescription.setProvisionedReadCapacityAutoScalingSettings(
                provisionedReadCapacityAutoScalingSettings);
        replicaGlobalSecondaryIndexSettingsDescription.setProvisionedWriteCapacityUnits(0L);
        replicaSettingsDescription.setReplicaGlobalSecondaryIndexSettings(
                Arrays.asList(replicaGlobalSecondaryIndexSettingsDescription));
        describeGlobalTableSettingsResult.setReplicaSettings(Arrays.asList(replicaSettingsDescription));
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("tableName");
        when(mockDynamoDB.describeGlobalTableSettings(request)).thenReturn(describeGlobalTableSettingsResult);

        // Run the test
        final String result = myClassUnderTest.getTableInfo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetTableInfo_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("tableName");
        when(mockDynamoDB.describeGlobalTableSettings(request)).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, () -> myClassUnderTest.getTableInfo());
    }

    @Test
    void testGetTableInfo_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("tableName");
        when(mockDynamoDB.describeGlobalTableSettings(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.getTableInfo());
    }
}
