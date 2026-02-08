package com.myapp

import com.amazonaws.AmazonWebServiceRequest
import com.amazonaws.ResponseMetadata
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.model.*
import com.amazonaws.services.dynamodbv2.waiters.AmazonDynamoDBWaiters
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private AmazonDynamoDBClient mockAmazonDynamoDBClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockAmazonDynamoDBClient)
    }

    @Test
    void testTryBatchGetItem() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        def batchGetItemResult = new BatchGetItemResult().withResponses(
                ["TableName1": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")
                                ]],
                 "TableName2": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")]
                 ]])
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenReturn(batchGetItemResult)

        // Run the test
        myClassUnderTest.tryBatchGetItem()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenReturn(new BatchGetItemResult())

        // Run the test
        myClassUnderTest.tryBatchGetItem()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryBatchGetItem()
        })
    }

    @Test
    void testTryBatchGetItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryBatchGetItem()
        })
    }

    @Test
    void testTryBatchGetItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryBatchGetItem()
        })
    }

    @Test
    void testTryBatchGetItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                ["TableName1": new KeysAndAttributes().withKeys(
                        ["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1")]),
                 "TableName2": new KeysAndAttributes().withKeys(
                         ["PrimaryKey2": new AttributeValue().withS("PrimaryKeyValue2")])
                ]))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryBatchGetItem()
        })
    }

    @Test
    void testTryBatchGetItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        def batchGetItemResult = new BatchGetItemResult().withResponses(
                ["TableName1": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")
                                ]],
                 "TableName2": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")]
                 ]])
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenReturn(batchGetItemResult)

        // Run the test
        myClassUnderTest.tryBatchGetItem1()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenReturn(new BatchGetItemResult())

        // Run the test
        myClassUnderTest.tryBatchGetItem1()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryBatchGetItem1()
        })
    }

    @Test
    void testTryBatchGetItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryBatchGetItem1()
        })
    }

    @Test
    void testTryBatchGetItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryBatchGetItem1()
        })
    }

    @Test
    void testTryBatchGetItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])], "returnConsumedCapacity"))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryBatchGetItem1()
        })
    }

    @Test
    void testTryBatchGetItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        def batchGetItemResult = new BatchGetItemResult().withResponses(
                ["TableName1": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")
                                ]],
                 "TableName2": [["PrimaryKey1": new AttributeValue().withS("PrimaryKeyValue1"),
                                 "KeyName2"   : new AttributeValue().withS("Value2")]
                 ]])
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])])).thenReturn(batchGetItemResult)

        // Run the test
        myClassUnderTest.tryBatchGetItem2()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem2_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])])).thenReturn(new BatchGetItemResult())

        // Run the test
        myClassUnderTest.tryBatchGetItem2()

        // Verify the results
    }

    @Test
    void testTryBatchGetItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])]))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryBatchGetItem2()
        })
    }

    @Test
    void testTryBatchGetItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])]))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryBatchGetItem2()
        })
    }

    @Test
    void testTryBatchGetItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])]))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryBatchGetItem2()
        })
    }

    @Test
    void testTryBatchGetItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(["value": new KeysAndAttributes().withKeys(
                ["PrimaryKey": new AttributeValue().withS("PrimaryKeyValue1")])]))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryBatchGetItem2()
        })
    }

    @Test
    void testTryBatchWriteItem() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenReturn(new BatchWriteItemResult())

        // Run the test
        myClassUnderTest.tryBatchWriteItem()

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientReturnsFailure() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenReturn(new BatchWriteItemResult().addUnprocessedItemsEntry("Key", Arrays.asList(new WriteRequest())))

        // Run the test
        myClassUnderTest.tryBatchWriteItem()

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryBatchWriteItem()
        })
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryBatchWriteItem()
        })
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryBatchWriteItem()
        })
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryBatchWriteItem()
        })
    }

    @Test
    void testTryBatchWriteItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new BatchWriteItemRequest(["TableName": [
                new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))
        ]]))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryBatchWriteItem()
        })
    }

    @Test
    void testTryBatchWriteItem1() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenReturn(new BatchWriteItemResult())

        // Run the test
        myClassUnderTest.tryBatchWriteItem1()

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientReturnsFailure() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]]))
                .thenReturn(
                        new BatchWriteItemResult().addUnprocessedItemsEntry("Key", Arrays.asList(new WriteRequest())))

        // Run the test
        myClassUnderTest.tryBatchWriteItem1()

        // Verify the results
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryBatchWriteItem1()
        })
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryBatchWriteItem1()
        })
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryBatchWriteItem1()
        })
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryBatchWriteItem1()
        })
    }

    @Test
    void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                ["value": [new WriteRequest().withPutRequest(new PutRequest().withItem(
                        ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
                ))]])).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryBatchWriteItem1()
        })
    }

    @Test
    void testTryCreateBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def createBackupResult = new CreateBackupResult()
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenReturn(createBackupResult)

        // Run the test
        myClassUnderTest.tryCreateBackup()

        // Verify the results
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(TableInUseException.class)

        // Run the test
        assertThrows(TableInUseException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(ContinuousBackupsUnavailableException.class)

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(BackupInUseException.class)

        // Run the test
        assertThrows(BackupInUseException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        def request = new CreateBackupRequest()
        request.setTableName("tableName")
        request.setBackupName("backupName")
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryCreateBackup()
        })
    }

    @Test
    void testTryCreateGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        def createGlobalTableResult = new CreateGlobalTableResult()
        def request = new CreateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replica = new Replica()
        request.setReplicationGroup([replica])
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenReturn(createGlobalTableResult)

        // Run the test
        myClassUnderTest.tryCreateGlobalTable()

        // Verify the results
    }

    @Test
    void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        def request = new CreateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replica = new Replica()
        request.setReplicationGroup([replica])
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryCreateGlobalTable()
        })
    }

    @Test
    void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        def request = new CreateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replica = new Replica()
        request.setReplicationGroup([replica])
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryCreateGlobalTable()
        })
    }

    @Test
    void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        def request = new CreateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replica = new Replica()
        request.setReplicationGroup([replica])
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(GlobalTableAlreadyExistsException.class)

        // Run the test
        assertThrows(GlobalTableAlreadyExistsException.class, {
            myClassUnderTest.tryCreateGlobalTable()
        })
    }

    @Test
    void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        def request = new CreateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replica = new Replica()
        request.setReplicationGroup([replica])
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryCreateGlobalTable()
        })
    }

    @Test
    void testTryCreateTable() {
        // Setup
        // Configure AmazonDynamoDBClient.createTable(...).
        def createTableResult = new CreateTableResult()
        when(mockAmazonDynamoDBClient.createTable(
                new CreateTableRequest("tableName", [new KeySchemaElement("attributeName", "keyType")])))
                .thenReturn(createTableResult)

        // Run the test
        myClassUnderTest.tryCreateTable()

        // Verify the results
    }

    @Test
    void testTryCreateTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(
                new CreateTableRequest("tableName", [new KeySchemaElement("attributeName", "keyType")])))
                .thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryCreateTable()
        })
    }

    @Test
    void testTryCreateTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(
                new CreateTableRequest("tableName", [new KeySchemaElement("attributeName", "keyType")])))
                .thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryCreateTable()
        })
    }

    @Test
    void testTryCreateTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(
                new CreateTableRequest("tableName", [new KeySchemaElement("attributeName", "keyType")])))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryCreateTable()
        })
    }

    @Test
    void testTryCreateTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.createTable(...).
        def createTableResult = new CreateTableResult()
        when(mockAmazonDynamoDBClient.createTable([new AttributeDefinition("attributeName", "attributeType")],
                "tableName", [new KeySchemaElement("attributeName", "keyType")],
                new ProvisionedThroughput(0L, 0L))).thenReturn(createTableResult)

        // Run the test
        myClassUnderTest.tryCreateTable1()

        // Verify the results
    }

    @Test
    void testTryDeleteBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        def deleteBackupResult = new DeleteBackupResult()
        def request = new DeleteBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenReturn(deleteBackupResult)

        // Run the test
        myClassUnderTest.tryDeleteBackup()

        // Verify the results
    }

    @Test
    void testTryDeleteBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        def request = new DeleteBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(BackupNotFoundException.class)

        // Run the test
        assertThrows(BackupNotFoundException.class, {
            myClassUnderTest.tryDeleteBackup()
        })
    }

    @Test
    void testTryDeleteBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        def request = new DeleteBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(BackupInUseException.class)

        // Run the test
        assertThrows(BackupInUseException.class, {
            myClassUnderTest.tryDeleteBackup()
        })
    }

    @Test
    void testTryDeleteBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        def request = new DeleteBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryDeleteBackup()
        })
    }

    @Test
    void testTryDeleteBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        def request = new DeleteBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteBackup()
        })
    }

    @Test
    void testTryDeleteItem() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        def deleteItemResult = new DeleteItemResult()
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")]))).thenReturn(deleteItemResult)

        // Run the test
        myClassUnderTest.tryDeleteItem()

        // Verify the results
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")]))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                ["PrimaryKeyName", new AttributeValue("PrimaryKeyValue")])))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteItem()
        })
    }

    @Test
    void testTryDeleteItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        def deleteItemResult = new DeleteItemResult()
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenReturn(deleteItemResult)

        // Run the test
        myClassUnderTest.tryDeleteItem1()

        // Verify the results
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteItem1()
        })
    }

    @Test
    void testTryDeleteItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        def deleteItemResult = new DeleteItemResult()
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenReturn(deleteItemResult)

        // Run the test
        myClassUnderTest.tryDeleteItem2()

        // Verify the results
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteItem2()
        })
    }

    @Test
    void testTryDeleteTable() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteTable(...).
        def deleteTableResult = new DeleteTableResult()
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName"))).thenReturn(deleteTableResult)

        // Run the test
        myClassUnderTest.tryDeleteTable()

        // Verify the results
    }

    @Test
    void testTryDeleteTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryDeleteTable()
        })
    }

    @Test
    void testTryDeleteTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDeleteTable()
        })
    }

    @Test
    void testTryDeleteTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryDeleteTable()
        })
    }

    @Test
    void testTryDeleteTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteTable()
        })
    }

    @Test
    void testTryDeleteTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteTable(...).
        def deleteTableResult = new DeleteTableResult()
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenReturn(deleteTableResult)

        // Run the test
        myClassUnderTest.tryDeleteTable1()

        // Verify the results
    }

    @Test
    void testTryDeleteTable1_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryDeleteTable1()
        })
    }

    @Test
    void testTryDeleteTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDeleteTable1()
        })
    }

    @Test
    void testTryDeleteTable1_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryDeleteTable1()
        })
    }

    @Test
    void testTryDeleteTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDeleteTable1()
        })
    }

    @Test
    void testTryDescribeBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        def describeBackupResult = new DescribeBackupResult()
        def request = new DescribeBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenReturn(describeBackupResult)

        // Run the test
        myClassUnderTest.tryDescribeBackup()

        // Verify the results
    }

    @Test
    void testTryDescribeBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        def request = new DescribeBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenThrow(BackupNotFoundException.class)

        // Run the test
        assertThrows(BackupNotFoundException.class, {
            myClassUnderTest.tryDescribeBackup()
        })
    }

    @Test
    void testTryDescribeBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        def request = new DescribeBackupRequest()
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeBackup()
        })
    }

    @Test
    void testTryDescribeContinuousBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        def describeContinuousBackupsResult = new DescribeContinuousBackupsResult()
        def request = new DescribeContinuousBackupsRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenReturn(describeContinuousBackupsResult)

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups()

        // Verify the results
    }

    @Test
    void testTryDescribeContinuousBackups_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        def request = new DescribeContinuousBackupsRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryDescribeContinuousBackups()
        })
    }

    @Test
    void testTryDescribeContinuousBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        def request = new DescribeContinuousBackupsRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeContinuousBackups()
        })
    }

    @Test
    void testTryDescribeEndpoints() {
        // Setup
        // Configure AmazonDynamoDBClient.describeEndpoints(...).
        def describeEndpointsResult = new DescribeEndpointsResult()
        when(mockAmazonDynamoDBClient.describeEndpoints(new DescribeEndpointsRequest()))
                .thenReturn(describeEndpointsResult)

        // Run the test
        myClassUnderTest.tryDescribeEndpoints()

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        def describeGlobalTableResult = new DescribeGlobalTableResult()
        def request = new DescribeGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenReturn(describeGlobalTableResult)

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable()

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        def request = new DescribeGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeGlobalTable()
        })
    }

    @Test
    void testTryDescribeGlobalTable_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        def request = new DescribeGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenThrow(GlobalTableNotFoundException.class)

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, {
            myClassUnderTest.tryDescribeGlobalTable()
        })
    }

    @Test
    void testTryDescribeGlobalTableSettings() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        def describeGlobalTableSettingsResult = new DescribeGlobalTableSettingsResult()
        def request = new DescribeGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenReturn(describeGlobalTableSettingsResult)

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings()

        // Verify the results
    }

    @Test
    void testTryDescribeGlobalTableSettings_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        def request = new DescribeGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenThrow(GlobalTableNotFoundException.class)

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, {
            myClassUnderTest.tryDescribeGlobalTableSettings()
        })
    }

    @Test
    void testTryDescribeGlobalTableSettings_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        def request = new DescribeGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeGlobalTableSettings()
        })
    }

    @Test
    void testTryDescribeLimits() {
        // Setup
        // Configure AmazonDynamoDBClient.describeLimits(...).
        def describeLimitsResult = new DescribeLimitsResult()
        when(mockAmazonDynamoDBClient.describeLimits(new DescribeLimitsRequest())).thenReturn(describeLimitsResult)

        // Run the test
        myClassUnderTest.tryDescribeLimits()

        // Verify the results
    }

    @Test
    void testTryDescribeLimits_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeLimits(new DescribeLimitsRequest()))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeLimits()
        })
    }

    @Test
    void testTryDescribeTable() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTable(...).
        def describeTableResult = new DescribeTableResult()
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenReturn(describeTableResult)

        // Run the test
        myClassUnderTest.tryDescribeTable()

        // Verify the results
    }

    @Test
    void testTryDescribeTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDescribeTable()
        })
    }

    @Test
    void testTryDescribeTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeTable()
        })
    }

    @Test
    void testTryDescribeTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTable(...).
        def describeTableResult = new DescribeTableResult()
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenReturn(describeTableResult)

        // Run the test
        myClassUnderTest.tryDescribeTable1()

        // Verify the results
    }

    @Test
    void testTryDescribeTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDescribeTable1()
        })
    }

    @Test
    void testTryDescribeTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeTable1()
        })
    }

    @Test
    void testTryDescribeTimeToLive() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        def describeTimeToLiveResult = new DescribeTimeToLiveResult()
        def request = new DescribeTimeToLiveRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenReturn(describeTimeToLiveResult)

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive()

        // Verify the results
    }

    @Test
    void testTryDescribeTimeToLive_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        def request = new DescribeTimeToLiveRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryDescribeTimeToLive()
        })
    }

    @Test
    void testTryDescribeTimeToLive_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        def request = new DescribeTimeToLiveRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryDescribeTimeToLive()
        })
    }

    @Test
    void testTryGetItem() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        def getItemResult = new GetItemResult().withItem([
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ])
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenReturn(getItemResult)

        // Run the test
        myClassUnderTest.tryGetItem()

        // Verify the results
    }

    @Test
    void testTryGetItem_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenReturn(new GetItemResult())

        // Run the test
        myClassUnderTest.tryGetItem()

        // Verify the results
    }

    @Test
    void testTryGetItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryGetItem()
        })
    }

    @Test
    void testTryGetItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryGetItem()
        })
    }

    @Test
    void testTryGetItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryGetItem()
        })
    }

    @Test
    void testTryGetItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName",
                ["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")]
        ))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryGetItem()
        })
    }

    @Test
    void testTryGetItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        def getItemResult = new GetItemResult().withItem([
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ])
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenReturn(getItemResult)

        // Run the test
        myClassUnderTest.tryGetItem1()

        // Verify the results
    }

    @Test
    void testTryGetItem1_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenReturn(new GetItemResult())

        // Run the test
        myClassUnderTest.tryGetItem1()

        // Verify the results
    }

    @Test
    void testTryGetItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryGetItem1()
        })
    }

    @Test
    void testTryGetItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryGetItem1()
        })
    }

    @Test
    void testTryGetItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryGetItem1()
        })
    }

    @Test
    void testTryGetItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryGetItem1()
        })
    }

    @Test
    void testTryGetItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        def getItemResult = new GetItemResult().withItem([
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ])
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenReturn(getItemResult)

        // Run the test
        myClassUnderTest.tryGetItem2()

        // Verify the results
    }

    @Test
    void testTryGetItem2_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenReturn(new GetItemResult())

        // Run the test
        myClassUnderTest.tryGetItem2()

        // Verify the results
    }

    @Test
    void testTryGetItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryGetItem2()
        })
    }

    @Test
    void testTryGetItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryGetItem2()
        })
    }

    @Test
    void testTryGetItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryGetItem2()
        })
    }

    @Test
    void testTryGetItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", ["value": new AttributeValue("s")], false))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryGetItem2()
        })
    }

    @Test
    void testTryListBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.listBackups(...).
        def listBackupsResult = new ListBackupsResult()
        def request = new ListBackupsRequest()
        request.setTableName("tableName")
        request.setLimit(0)
        request.setTimeRangeLowerBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        request.setTimeRangeUpperBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        request.setExclusiveStartBackupArn("exclusiveStartBackupArn")
        request.setBackupType("backupType")
        when(mockAmazonDynamoDBClient.listBackups(request)).thenReturn(listBackupsResult)

        // Run the test
        myClassUnderTest.tryListBackups()

        // Verify the results
    }

    @Test
    void testTryListBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listBackups(...).
        def request = new ListBackupsRequest()
        request.setTableName("tableName")
        request.setLimit(0)
        request.setTimeRangeLowerBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        request.setTimeRangeUpperBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
        request.setExclusiveStartBackupArn("exclusiveStartBackupArn")
        request.setBackupType("backupType")
        when(mockAmazonDynamoDBClient.listBackups(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListBackups()
        })
    }

    @Test
    void testTryListGlobalTables() {
        // Setup
        // Configure AmazonDynamoDBClient.listGlobalTables(...).
        def listGlobalTablesResult = new ListGlobalTablesResult()
        def request = new ListGlobalTablesRequest()
        request.setExclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
        request.setLimit(0)
        request.setRegionName("regionName")
        when(mockAmazonDynamoDBClient.listGlobalTables(request)).thenReturn(listGlobalTablesResult)

        // Run the test
        myClassUnderTest.tryListGlobalTables()

        // Verify the results
    }

    @Test
    void testTryListGlobalTables_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listGlobalTables(...).
        def request = new ListGlobalTablesRequest()
        request.setExclusiveStartGlobalTableName("exclusiveStartGlobalTableName")
        request.setLimit(0)
        request.setRegionName("regionName")
        when(mockAmazonDynamoDBClient.listGlobalTables(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListGlobalTables()
        })
    }

    @Test
    void testTryListTables() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        def listTablesResult = new ListTablesResult()
        when(mockAmazonDynamoDBClient.listTables(new ListTablesRequest("exclusiveStartTableName", 0)))
                .thenReturn(listTablesResult)

        // Run the test
        myClassUnderTest.tryListTables()

        // Verify the results
    }

    @Test
    void testTryListTables_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables(new ListTablesRequest("exclusiveStartTableName", 0)))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTables()
        })
    }

    @Test
    void testTryListTables1() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        def listTablesResult = new ListTablesResult()
        when(mockAmazonDynamoDBClient.listTables()).thenReturn(listTablesResult)

        // Run the test
        myClassUnderTest.tryListTables1()

        // Verify the results
    }

    @Test
    void testTryListTables1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables()).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTables1()
        })
    }

    @Test
    void testTryListTables2() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        def listTablesResult = new ListTablesResult()
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName")).thenReturn(listTablesResult)

        // Run the test
        myClassUnderTest.tryListTables2()

        // Verify the results
    }

    @Test
    void testTryListTables2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName"))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTables2()
        })
    }

    @Test
    void testTryListTables3() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        def listTablesResult = new ListTablesResult()
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName", 0)).thenReturn(listTablesResult)

        // Run the test
        myClassUnderTest.tryListTables3()

        // Verify the results
    }

    @Test
    void testTryListTables3_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName", 0))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTables3()
        })
    }

    @Test
    void testTryListTables4() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        def listTablesResult = new ListTablesResult()
        when(mockAmazonDynamoDBClient.listTables(0)).thenReturn(listTablesResult)

        // Run the test
        myClassUnderTest.tryListTables4()

        // Verify the results
    }

    @Test
    void testTryListTables4_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables(0)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTables4()
        })
    }

    @Test
    void testTryListTagsOfResource() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        def listTagsOfResourceResult = new ListTagsOfResourceResult()
        def request = new ListTagsOfResourceRequest()
        request.setResourceArn("resourceArn")
        request.setNextToken("nextToken")
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenReturn(listTagsOfResourceResult)

        // Run the test
        myClassUnderTest.tryListTagsOfResource()

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        def request = new ListTagsOfResourceRequest()
        request.setResourceArn("resourceArn")
        request.setNextToken("nextToken")
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenReturn(new ListTagsOfResourceResult())

        // Run the test
        myClassUnderTest.tryListTagsOfResource()

        // Verify the results
    }

    @Test
    void testTryListTagsOfResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        def request = new ListTagsOfResourceRequest()
        request.setResourceArn("resourceArn")
        request.setNextToken("nextToken")
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryListTagsOfResource()
        })
    }

    @Test
    void testTryListTagsOfResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        def request = new ListTagsOfResourceRequest()
        request.setResourceArn("resourceArn")
        request.setNextToken("nextToken")
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryListTagsOfResource()
        })
    }

    @Test
    void testTryPutItem() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        def putItemResult = new PutItemResult()
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenReturn(putItemResult)

        // Run the test
        myClassUnderTest.tryPutItem()

        // Verify the results
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", [
                "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue"),
                "KeyName2"      : new AttributeValue().withS("Value2")
        ]))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryPutItem()
        })
    }

    @Test
    void testTryPutItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        def putItemResult = new PutItemResult()
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenReturn(putItemResult)

        // Run the test
        myClassUnderTest.tryPutItem1()

        // Verify the results
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")]))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryPutItem1()
        })
    }

    @Test
    void testTryPutItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        def putItemResult = new PutItemResult()
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenReturn(putItemResult)

        // Run the test
        myClassUnderTest.tryPutItem2()

        // Verify the results
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryPutItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", ["value": new AttributeValue("s")],
                "returnValues")).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryPutItem2()
        })
    }

    @Test
    void testTryQuery() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def queryResult = new QueryResult()
        queryResult.setItems([["value": new AttributeValue("s")]])
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenReturn(queryResult)

        // Run the test
        myClassUnderTest.tryQuery()

        // Verify the results
    }

    @Test
    void testTryQuery_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenReturn(new QueryResult())

        // Run the test
        myClassUnderTest.tryQuery()

        // Verify the results
    }

    @Test
    void testTryQuery_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryQuery()
        })
    }

    @Test
    void testTryQuery_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryQuery()
        })
    }

    @Test
    void testTryQuery_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryQuery()
        })
    }

    @Test
    void testTryQuery_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        def request = new QueryRequest()
        request.setTableName("tableName")
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryQuery()
        })
    }

    @Test
    void testTryRestoreTableFromBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def restoreTableFromBackupResult = new RestoreTableFromBackupResult()
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenReturn(restoreTableFromBackupResult)

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup()

        // Verify the results
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(TableAlreadyExistsException.class)

        // Run the test
        assertThrows(TableAlreadyExistsException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(TableInUseException.class)

        // Run the test
        assertThrows(TableInUseException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(BackupNotFoundException.class)

        // Run the test
        assertThrows(BackupNotFoundException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(BackupInUseException.class)

        // Run the test
        assertThrows(BackupInUseException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        def request = new RestoreTableFromBackupRequest()
        request.setTargetTableName("targetTableName")
        request.setBackupArn("backupArn")
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryRestoreTableFromBackup()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def restoreTableToPointInTimeResult = new RestoreTableToPointInTimeResult()
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenReturn(restoreTableToPointInTimeResult)

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime()

        // Verify the results
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableAlreadyExistsException.class)

        // Run the test
        assertThrows(TableAlreadyExistsException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableInUseException.class)

        // Run the test
        assertThrows(TableInUseException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsInvalidRestoreTimeException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(InvalidRestoreTimeException.class)

        // Run the test
        assertThrows(InvalidRestoreTimeException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request))
                .thenThrow(PointInTimeRecoveryUnavailableException.class)

        // Run the test
        assertThrows(PointInTimeRecoveryUnavailableException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        def request = new RestoreTableToPointInTimeRequest()
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryRestoreTableToPointInTime()
        })
    }

    @Test
    void testTryScan() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def scanResult = new ScanResult().withItems(
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue1"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue2"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
        )
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenReturn(scanResult)

        // Run the test
        myClassUnderTest.tryScan()

        // Verify the results
    }

    @Test
    void testTryScan_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenReturn(new ScanResult())

        // Run the test
        myClassUnderTest.tryScan()

        // Verify the results
    }

    @Test
    void testTryScan_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName")))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryScan()
        })
    }

    @Test
    void testTryScan_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryScan()
        })
    }

    @Test
    void testTryScan_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName")))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryScan()
        })
    }

    @Test
    void testTryScan_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryScan()
        })
    }

    @Test
    void testTryScan1() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def scanResult = new ScanResult().withItems(
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue1"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue2"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
        )
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"])).thenReturn(scanResult)

        // Run the test
        myClassUnderTest.tryScan1()

        // Verify the results
    }

    @Test
    void testTryScan1_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"])).thenReturn(new ScanResult())

        // Run the test
        myClassUnderTest.tryScan1()

        // Verify the results
    }

    @Test
    void testTryScan1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"]))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryScan1()
        })
    }

    @Test
    void testTryScan1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"])).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryScan1()
        })
    }

    @Test
    void testTryScan1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"])).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryScan1()
        })
    }

    @Test
    void testTryScan1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"])).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryScan1()
        })
    }

    @Test
    void testTryScan2() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def scanResult = new ScanResult().withItems(
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue1"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue2"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
        )
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter)).thenReturn(scanResult)

        // Run the test
        myClassUnderTest.tryScan2()

        // Verify the results
    }

    @Test
    void testTryScan2_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter)).thenReturn(new ScanResult())

        // Run the test
        myClassUnderTest.tryScan2()

        // Verify the results
    }

    @Test
    void testTryScan2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryScan2()
        })
    }

    @Test
    void testTryScan2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryScan2()
        })
    }

    @Test
    void testTryScan2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter)).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryScan2()
        })
    }

    @Test
    void testTryScan2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", scanFilter)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryScan2()
        })
    }

    @Test
    void testTryScan3() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def scanResult = new ScanResult().withItems(
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue1"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
                [
                        "PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue2"),
                        "KeyName2"      : new AttributeValue().withS("Value2")
                ],
        )
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter)).thenReturn(scanResult)

        // Run the test
        myClassUnderTest.tryScan3()

        // Verify the results
    }

    @Test
    void testTryScan3_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter)).thenReturn(new ScanResult())

        // Run the test
        myClassUnderTest.tryScan3()

        // Verify the results
    }

    @Test
    void testTryScan3_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryScan3()
        })
    }

    @Test
    void testTryScan3_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryScan3()
        })
    }

    @Test
    void testTryScan3_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter))
                .thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryScan3()
        })
    }

    @Test
    void testTryScan3_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        def condition = new Condition()
        def scanFilter = ["value": condition]
        when(mockAmazonDynamoDBClient.scan("tableName", ["value"], scanFilter))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryScan3()
        })
    }

    @Test
    void testTryTagResource() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        def request = new TagResourceRequest()
        request.setResourceArn("resourceArn")
        def tag = new Tag()
        request.setTags([tag])
        when(mockAmazonDynamoDBClient.tagResource(request)).thenReturn(new TagResourceResult())

        // Run the test
        myClassUnderTest.tryTagResource()

        // Verify the results
    }

    @Test
    void testTryTagResource_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        def request = new TagResourceRequest()
        request.setResourceArn("resourceArn")
        def tag = new Tag()
        request.setTags([tag])
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryTagResource()
        })
    }

    @Test
    void testTryTagResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        def request = new TagResourceRequest()
        request.setResourceArn("resourceArn")
        def tag = new Tag()
        request.setTags([tag])
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryTagResource()
        })
    }

    @Test
    void testTryTagResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        def request = new TagResourceRequest()
        request.setResourceArn("resourceArn")
        def tag = new Tag()
        request.setTags([tag])
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryTagResource()
        })
    }

    @Test
    void testTryTagResource_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        def request = new TagResourceRequest()
        request.setResourceArn("resourceArn")
        def tag = new Tag()
        request.setTags([tag])
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryTagResource()
        })
    }

    @Test
    void testTryTransactGetItems() {
        // Setup
        // Configure AmazonDynamoDBClient.transactGetItems(...).
        def transactGetItemsResult = new TransactGetItemsResult()
        def itemResponse = new ItemResponse()
        itemResponse.setItem(["value": new AttributeValue("s")])
        transactGetItemsResult.setResponses([itemResponse])
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenReturn(transactGetItemsResult)

        // Run the test
        myClassUnderTest.tryTransactGetItems()

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenReturn(new TransactGetItemsResult())

        // Run the test
        myClassUnderTest.tryTransactGetItems()

        // Verify the results
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryTransactGetItems()
        })
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientThrowsTransactionCanceledException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenThrow(TransactionCanceledException.class)

        // Run the test
        assertThrows(TransactionCanceledException.class, {
            myClassUnderTest.tryTransactGetItems()
        })
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryTransactGetItems()
        })
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryTransactGetItems()
        })
    }

    @Test
    void testTryTransactGetItems_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(["PrimaryKeyName2": new AttributeValue().withS("PrimaryKeyValue2")])
                )
        ))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryTransactGetItems()
        })
    }

    @Test
    void testTryTransactWriteItems() {
        // Setup
        // Configure AmazonDynamoDBClient.transactWriteItems(...).
        def transactWriteItemsResult = new TransactWriteItemsResult()
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenReturn(transactWriteItemsResult)

        // Run the test
        myClassUnderTest.tryTransactWriteItems()

        // Verify the results
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsTransactionCanceledException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(TransactionCanceledException.class)

        // Run the test
        assertThrows(TransactionCanceledException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsTransactionInProgressException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(TransactionInProgressException.class)

        // Run the test
        assertThrows(TransactionInProgressException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(IdempotentParameterMismatchException.class)

        // Run the test
        assertThrows(IdempotentParameterMismatchException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryTransactWriteItems_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(["PrimaryKeyName": new AttributeValue().withS("PrimaryKeyValue")])
                )))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryTransactWriteItems()
        })
    }

    @Test
    void testTryUntagResource() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        def request = new UntagResourceRequest()
        request.setResourceArn("resourceArn")
        request.setTagKeys(["value"])
        when(mockAmazonDynamoDBClient.untagResource(request)).thenReturn(new UntagResourceResult())

        // Run the test
        myClassUnderTest.tryUntagResource()

        // Verify the results
    }

    @Test
    void testTryUntagResource_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        def request = new UntagResourceRequest()
        request.setResourceArn("resourceArn")
        request.setTagKeys(["value"])
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryUntagResource()
        })
    }

    @Test
    void testTryUntagResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        def request = new UntagResourceRequest()
        request.setResourceArn("resourceArn")
        request.setTagKeys(["value"])
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUntagResource()
        })
    }

    @Test
    void testTryUntagResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        def request = new UntagResourceRequest()
        request.setResourceArn("resourceArn")
        request.setTagKeys(["value"])
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUntagResource()
        })
    }

    @Test
    void testTryUntagResource_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        def request = new UntagResourceRequest()
        request.setResourceArn("resourceArn")
        request.setTagKeys(["value"])
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryUntagResource()
        })
    }

    @Test
    void testTryUpdateContinuousBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        def updateContinuousBackupsResult = new UpdateContinuousBackupsResult()
        def request = new UpdateContinuousBackupsRequest()
        request.setTableName("tableName")
        def pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification()
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification)
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenReturn(updateContinuousBackupsResult)

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups()

        // Verify the results
    }

    @Test
    void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        def request = new UpdateContinuousBackupsRequest()
        request.setTableName("tableName")
        def pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification()
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification)
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryUpdateContinuousBackups()
        })
    }

    @Test
    void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        def request = new UpdateContinuousBackupsRequest()
        request.setTableName("tableName")
        def pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification()
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification)
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request))
                .thenThrow(ContinuousBackupsUnavailableException.class)

        // Run the test
        assertThrows(ContinuousBackupsUnavailableException.class, {
            myClassUnderTest.tryUpdateContinuousBackups()
        })
    }

    @Test
    void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        def request = new UpdateContinuousBackupsRequest()
        request.setTableName("tableName")
        def pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification()
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification)
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateContinuousBackups()
        })
    }

    @Test
    void testTryUpdateGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def updateGlobalTableResult = new UpdateGlobalTableResult()
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenReturn(updateGlobalTableResult)

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable()

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateGlobalTable()
        })
    }

    @Test
    void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(GlobalTableNotFoundException.class)

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTable()
        })
    }

    @Test
    void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsReplicaAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(ReplicaAlreadyExistsException.class)

        // Run the test
        assertThrows(ReplicaAlreadyExistsException.class, {
            myClassUnderTest.tryUpdateGlobalTable()
        })
    }

    @Test
    void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsReplicaNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(ReplicaNotFoundException.class)

        // Run the test
        assertThrows(ReplicaNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTable()
        })
    }

    @Test
    void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        def request = new UpdateGlobalTableRequest()
        request.setGlobalTableName("globalTableName")
        def replicaUpdate = new ReplicaUpdate()
        request.setReplicaUpdates([replicaUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(TableNotFoundException.class)

        // Run the test
        assertThrows(TableNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTable()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def updateGlobalTableSettingsResult = new UpdateGlobalTableSettingsResult()
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenReturn(updateGlobalTableSettingsResult)

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings()

        // Verify the results
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(GlobalTableNotFoundException.class)

        // Run the test
        assertThrows(GlobalTableNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsReplicaNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(ReplicaNotFoundException.class)

        // Run the test
        assertThrows(ReplicaNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsIndexNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(IndexNotFoundException.class)

        // Run the test
        assertThrows(IndexNotFoundException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        def request = new UpdateGlobalTableSettingsRequest()
        request.setGlobalTableName("globalTableName")
        request.setGlobalTableBillingMode("globalTableBillingMode")
        request.setGlobalTableProvisionedWriteCapacityUnits(0L)
        def globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate()
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate)
        def globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate()
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate([globalTableGlobalSecondaryIndexSettingsUpdate])
        def replicaSettingsUpdate = new ReplicaSettingsUpdate()
        request.setReplicaSettingsUpdate([replicaSettingsUpdate])
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateGlobalTableSettings()
        })
    }

    @Test
    void testTryUpdateItem() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        def updateItemResult = new UpdateItemResult()
        updateItemResult.setAttributes(["value": new AttributeValue("s")])
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))).thenReturn(updateItemResult)

        // Run the test
        myClassUnderTest.tryUpdateItem()

        // Verify the results
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")))
                .thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")))
                .thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(new UpdateItemRequest("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateItem()
        })
    }

    @Test
    void testTryUpdateItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        def updateItemResult = new UpdateItemResult()
        updateItemResult.setAttributes(["value": new AttributeValue("s")])
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenReturn(updateItemResult)

        // Run the test
        myClassUnderTest.tryUpdateItem1()

        // Verify the results
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()])).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateItem1()
        })
    }

    @Test
    void testTryUpdateItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        def updateItemResult = new UpdateItemResult()
        updateItemResult.setAttributes(["value": new AttributeValue("s")])
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")).thenReturn(updateItemResult)

        // Run the test
        myClassUnderTest.tryUpdateItem2()

        // Verify the results
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))
                .thenThrow(ConditionalCheckFailedException.class)

        // Run the test
        assertThrows(ConditionalCheckFailedException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))
                .thenThrow(ProvisionedThroughputExceededException.class)

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues"))
                .thenThrow(ItemCollectionSizeLimitExceededException.class)

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")).thenThrow(TransactionConflictException.class)

        // Run the test
        assertThrows(TransactionConflictException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")).thenThrow(RequestLimitExceededException.class)

        // Run the test
        assertThrows(RequestLimitExceededException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", ["value": new AttributeValue("s")],
                ["value": new AttributeValueUpdate()], "returnValues")).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateItem2()
        })
    }

    @Test
    void testTryUpdateTable() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTable(...).
        def updateTableResult = new UpdateTableResult()
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L)))).thenReturn(updateTableResult)

        // Run the test
        myClassUnderTest.tryUpdateTable()

        // Verify the results
    }

    @Test
    void testTryUpdateTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryUpdateTable()
        })
    }

    @Test
    void testTryUpdateTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateTable()
        })
    }

    @Test
    void testTryUpdateTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryUpdateTable()
        })
    }

    @Test
    void testTryUpdateTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateTable()
        })
    }

    @Test
    void testTryUpdateTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTable(...).
        def updateTableResult = new UpdateTableResult()
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenReturn(updateTableResult)

        // Run the test
        myClassUnderTest.tryUpdateTable1()

        // Verify the results
    }

    @Test
    void testTryUpdateTable1_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryUpdateTable1()
        })
    }

    @Test
    void testTryUpdateTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateTable1()
        })
    }

    @Test
    void testTryUpdateTable1_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryUpdateTable1()
        })
    }

    @Test
    void testTryUpdateTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateTable1()
        })
    }

    @Test
    void testTryUpdateTimeToLive() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        def updateTimeToLiveResult = new UpdateTimeToLiveResult()
        def request = new UpdateTimeToLiveRequest()
        request.setTableName("tableName")
        def timeToLiveSpecification = new TimeToLiveSpecification()
        request.setTimeToLiveSpecification(timeToLiveSpecification)
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenReturn(updateTimeToLiveResult)

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive()

        // Verify the results
    }

    @Test
    void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        def request = new UpdateTimeToLiveRequest()
        request.setTableName("tableName")
        def timeToLiveSpecification = new TimeToLiveSpecification()
        request.setTimeToLiveSpecification(timeToLiveSpecification)
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(ResourceInUseException.class)

        // Run the test
        assertThrows(ResourceInUseException.class, {
            myClassUnderTest.tryUpdateTimeToLive()
        })
    }

    @Test
    void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        def request = new UpdateTimeToLiveRequest()
        request.setTableName("tableName")
        def timeToLiveSpecification = new TimeToLiveSpecification()
        request.setTimeToLiveSpecification(timeToLiveSpecification)
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(ResourceNotFoundException.class)

        // Run the test
        assertThrows(ResourceNotFoundException.class, {
            myClassUnderTest.tryUpdateTimeToLive()
        })
    }

    @Test
    void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        def request = new UpdateTimeToLiveRequest()
        request.setTableName("tableName")
        def timeToLiveSpecification = new TimeToLiveSpecification()
        request.setTimeToLiveSpecification(timeToLiveSpecification)
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(LimitExceededException.class)

        // Run the test
        assertThrows(LimitExceededException.class, {
            myClassUnderTest.tryUpdateTimeToLive()
        })
    }

    @Test
    void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        def request = new UpdateTimeToLiveRequest()
        request.setTableName("tableName")
        def timeToLiveSpecification = new TimeToLiveSpecification()
        request.setTimeToLiveSpecification(timeToLiveSpecification)
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(InternalServerErrorException.class)

        // Run the test
        assertThrows(InternalServerErrorException.class, {
            myClassUnderTest.tryUpdateTimeToLive()
        })
    }

    @Test
    void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonDynamoDBClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(new ResponseMetadata(["value": "value"]))

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata()

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata_AmazonDynamoDBClientReturnsNull() {
        // Setup
        when(mockAmazonDynamoDBClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null)

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata()

        // Verify the results
    }

    @Test
    void testTryWaiters() {
        // Setup
        when(mockAmazonDynamoDBClient.waiters()).thenReturn(new AmazonDynamoDBWaiters(null))

        // Run the test
        myClassUnderTest.tryWaiters()

        // Verify the results
    }

    @Test
    void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown()

        // Verify the results
        verify(mockAmazonDynamoDBClient).shutdown()
    }

    @Test
    void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder()

        // Verify the results
    }
}
