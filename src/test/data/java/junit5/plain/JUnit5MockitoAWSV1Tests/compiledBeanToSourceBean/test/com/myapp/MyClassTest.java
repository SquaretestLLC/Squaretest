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
        final ResultDTO expectedResult = new ResultDTO();
        expectedResult.setTheTableName("theTableName");
        final ReplicaSettingsDescriptionDTO replicaSettingsDescriptionDTO = new ReplicaSettingsDescriptionDTO();
        replicaSettingsDescriptionDTO.setTheRegionName("theRegionName");
        replicaSettingsDescriptionDTO.setTheReplicaStatus("theReplicaStatus");
        final BillingModeSummaryDTO theBillingModeSummary = new BillingModeSummaryDTO();
        theBillingModeSummary.setTheBillingMode("theBillingMode");
        theBillingModeSummary.setLastUpdateToPayPerRequestDateTime(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        replicaSettingsDescriptionDTO.setTheBillingModeSummary(theBillingModeSummary);
        replicaSettingsDescriptionDTO.setTheReplicaProvisionedReadCapacityUnits(0L);
        final AutoScalingSettingsDescriptionDTO theAutoScalingSettingsDescriptionDTO = new AutoScalingSettingsDescriptionDTO();
        theAutoScalingSettingsDescriptionDTO.setTheMinimumUnits(0L);
        theAutoScalingSettingsDescriptionDTO.setTheMaximumUnits(0L);
        theAutoScalingSettingsDescriptionDTO.setTheAutoScalingDisabled(false);
        theAutoScalingSettingsDescriptionDTO.setTheAutoScalingRoleArn("theAutoScalingRoleArn");
        final AutoScalingPolicyDescriptionDTO autoScalingPolicyDescriptionDTO = new AutoScalingPolicyDescriptionDTO();
        autoScalingPolicyDescriptionDTO.setThePolicyName("thePolicyName");
        final AutoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO theTargetTrackingScalingPolicyConfiguration = new AutoScalingTargetTrackingScalingPolicyConfigurationDescriptionDTO();
        theTargetTrackingScalingPolicyConfiguration.setTheDisableScaleIn(false);
        theTargetTrackingScalingPolicyConfiguration.setTheScaleInCooldown(0);
        theTargetTrackingScalingPolicyConfiguration.setTheScaleOutCooldown(0);
        theTargetTrackingScalingPolicyConfiguration.setTheTargetValue(0.0);
        autoScalingPolicyDescriptionDTO.setTheTargetTrackingScalingPolicyConfiguration(
                theTargetTrackingScalingPolicyConfiguration);
        theAutoScalingSettingsDescriptionDTO.setTheScalingPolicies(Arrays.asList(autoScalingPolicyDescriptionDTO));
        replicaSettingsDescriptionDTO.setTheAutoScalingSettingsDescriptionDTO(theAutoScalingSettingsDescriptionDTO);
        replicaSettingsDescriptionDTO.setTheReplicaProvisionedWriteCapacityUnits(0L);
        expectedResult.setTheReplicaSettings(Arrays.asList(replicaSettingsDescriptionDTO));

        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResult describeGlobalTableSettingsResult = new DescribeGlobalTableSettingsResult();
        describeGlobalTableSettingsResult.setGlobalTableName("theTableName");
        final ReplicaSettingsDescription replicaSettingsDescription = new ReplicaSettingsDescription();
        replicaSettingsDescription.setRegionName("theRegionName");
        replicaSettingsDescription.setReplicaStatus("theReplicaStatus");
        final BillingModeSummary replicaBillingModeSummary = new BillingModeSummary();
        replicaBillingModeSummary.setBillingMode("theBillingMode");
        replicaBillingModeSummary.setLastUpdateToPayPerRequestDateTime(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        replicaSettingsDescription.setReplicaBillingModeSummary(replicaBillingModeSummary);
        replicaSettingsDescription.setReplicaProvisionedReadCapacityUnits(0L);
        final AutoScalingSettingsDescription replicaProvisionedReadCapacityAutoScalingSettings = new AutoScalingSettingsDescription();
        replicaProvisionedReadCapacityAutoScalingSettings.setMinimumUnits(0L);
        replicaProvisionedReadCapacityAutoScalingSettings.setMaximumUnits(0L);
        replicaProvisionedReadCapacityAutoScalingSettings.setAutoScalingDisabled(false);
        replicaProvisionedReadCapacityAutoScalingSettings.setAutoScalingRoleArn("theAutoScalingRoleArn");
        final AutoScalingPolicyDescription autoScalingPolicyDescription = new AutoScalingPolicyDescription();
        autoScalingPolicyDescription.setPolicyName("thePolicyName");
        final AutoScalingTargetTrackingScalingPolicyConfigurationDescription targetTrackingScalingPolicyConfiguration = new AutoScalingTargetTrackingScalingPolicyConfigurationDescription();
        targetTrackingScalingPolicyConfiguration.setDisableScaleIn(false);
        targetTrackingScalingPolicyConfiguration.setScaleInCooldown(0);
        targetTrackingScalingPolicyConfiguration.setScaleOutCooldown(0);
        targetTrackingScalingPolicyConfiguration.setTargetValue(0.0);
        autoScalingPolicyDescription.setTargetTrackingScalingPolicyConfiguration(
                targetTrackingScalingPolicyConfiguration);
        replicaProvisionedReadCapacityAutoScalingSettings.setScalingPolicies(
                Arrays.asList(autoScalingPolicyDescription));
        replicaSettingsDescription.setReplicaProvisionedReadCapacityAutoScalingSettings(
                replicaProvisionedReadCapacityAutoScalingSettings);
        replicaSettingsDescription.setReplicaProvisionedWriteCapacityUnits(0L);
        describeGlobalTableSettingsResult.setReplicaSettings(Arrays.asList(replicaSettingsDescription));
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("tableName");
        when(mockDynamoDB.describeGlobalTableSettings(request)).thenReturn(describeGlobalTableSettingsResult);

        // Run the test
        final ResultDTO result = myClassUnderTest.getTableInfo();

        // Verify the results
        assertEquals(expectedResult, result);
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
