package com.myapp;

import com.myapp.helpers.GlobalTableDTO;
import com.myapp.helpers.ReplicaDescriptionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbClient mockDynamoDbClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbClient);
    }

    @Test
    void testTryBatchWriteItem1() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenReturn(BatchWriteItemResponse.builder().build());

        // Run the test
        final String result = myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
        assertEquals("tableName", result);
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientReturnsFailure() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenReturn(BatchWriteItemResponse.builder().unprocessedItems(new HashMap<>()).build());

        // Run the test
        final String result = myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
        assertEquals("tableName", result);
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsResourceNotFoundException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryBatchWriteItem1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(
                new HashMap<String, Collection<WriteRequest>>() {{
                    put("TableName1", Arrays.asList(
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue1").build());
                                    }}).build()
                            ).build(),
                            WriteRequest.builder().putRequest(
                                    PutRequest.builder().item(new HashMap<String, AttributeValue>() {{
                                        put("Table1PrimaryKeyName",
                                                AttributeValue.builder().s("Table1PrimaryKeyValue2").build());
                                    }}).build()
                            ).build()
                    ));
                }}
        ).build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryBatchWriteItem1());
    }

    @Test
    void testTryCreateBackup1() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenReturn(CreateBackupResponse.builder().build());

        // Run the test
        myClassUnderTest.tryCreateBackup1("backupNameParam");

        // Verify the results
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsTableInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(TableInUseException.class);

        // Run the test
        assertThrows(TableInUseException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class,
                () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsBackupInUseException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(BackupInUseException.class);

        // Run the test
        assertThrows(BackupInUseException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateBackup1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createBackup(CreateBackupRequest.builder()
                .tableName("theTableName")
                .backupName("backupName")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateBackup1("backupNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1() {
        // Setup
        final GlobalTableDTO expectedResult = new GlobalTableDTO();
        expectedResult.setArn("arn");
        expectedResult.setCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        expectedResult.setTableName("tableName");
        expectedResult.setTableStatus("tableStatus");
        final ReplicaDescriptionDTO replicaDescriptionDTO = new ReplicaDescriptionDTO();
        replicaDescriptionDTO.setTheRegionName("theRegionName");
        replicaDescriptionDTO.setTheReplicaStatusDescription("theReplicaStatusDescription");
        replicaDescriptionDTO.setTheReplicaStatusPercentProgress("theReplicaStatusPercentProgress");
        replicaDescriptionDTO.setTheReplicaStatus("theReplicaStatus");
        expectedResult.setReplicaDescriptions(Arrays.asList(replicaDescriptionDTO));

        // Configure DynamoDbClient.createGlobalTable(...).
        final CreateGlobalTableResponse createGlobalTableResponse = CreateGlobalTableResponse.builder()
                .globalTableDescription(GlobalTableDescription.builder()
                        .replicationGroup(ReplicaDescription.builder()
                                .regionName("theRegionName")
                                .replicaStatus(ReplicaStatus.CREATING)
                                .replicaStatusDescription("theReplicaStatusDescription")
                                .replicaStatusPercentProgress("theReplicaStatusPercentProgress")
                                .build())
                        .globalTableArn("arn")
                        .creationDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                        .globalTableStatus(GlobalTableStatus.CREATING)
                        .globalTableName("tableName")
                        .build())
                .build();
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenReturn(createGlobalTableResponse);

        // Run the test
        final GlobalTableDTO result = myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsLimitExceededException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(LimitExceededException.class);

        // Run the test
        assertThrows(LimitExceededException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsInternalServerErrorException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsTableNotFoundException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(TableNotFoundException.class);

        // Run the test
        assertThrows(TableNotFoundException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsAwsServiceException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(AwsServiceException.class);

        // Run the test
        assertThrows(AwsServiceException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }

    @Test
    void testTryCreateGlobalTable1_DynamoDbClientThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbClient.createGlobalTable(CreateGlobalTableRequest.builder()
                .globalTableName("globalTableName")
                .replicationGroup(Replica.builder()
                        .regionName("regionName")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class,
                () -> myClassUnderTest.tryCreateGlobalTable1("tableNameParam", "regionNameParam"));
    }
}
