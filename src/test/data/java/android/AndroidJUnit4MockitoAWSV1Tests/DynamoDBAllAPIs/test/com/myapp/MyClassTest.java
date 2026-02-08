package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.waiters.AmazonDynamoDBWaiters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private AmazonDynamoDBClient mockAmazonDynamoDBClient;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonDynamoDBClient);
    }

    @Test
    public void testTryBatchGetItem() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        final BatchGetItemResult batchGetItemResult = new BatchGetItemResult().withResponses(
                new HashMap<String, List<Map<String, AttributeValue>>>() {{
                    put("TableName", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey1", new AttributeValue().withS("PrimaryKeyValue1"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                    put("TableName2", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                }}
        );
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenReturn(batchGetItemResult);

        // Run the test
        myClassUnderTest.tryBatchGetItem();

        // Verify the results
    }

    @Test
    public void testTryBatchGetItem_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenReturn(new BatchGetItemResult());

        // Run the test
        myClassUnderTest.tryBatchGetItem();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryBatchGetItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryBatchGetItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryBatchGetItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryBatchGetItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new BatchGetItemRequest().withRequestItems(
                new HashMap<String, KeysAndAttributes>() {{
                    put("TableName1", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey", new AttributeValue().withS("PrimaryKeyValue1"));
                            }}
                    ));
                    put("TableName2", new KeysAndAttributes().withKeys(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                            }}
                    ));
                }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem();
    }

    @Test
    public void testTryBatchGetItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        final BatchGetItemResult batchGetItemResult = new BatchGetItemResult().withResponses(
                new HashMap<String, List<Map<String, AttributeValue>>>() {{
                    put("TableName", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey1", new AttributeValue().withS("PrimaryKeyValue1"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                    put("TableName2", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                }}
        );
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenReturn(batchGetItemResult);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test
    public void testTryBatchGetItem1_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenReturn(new BatchGetItemResult());

        // Run the test
        myClassUnderTest.tryBatchGetItem1();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryBatchGetItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryBatchGetItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryBatchGetItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryBatchGetItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>(), "returnConsumedCapacity"))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem1();
    }

    @Test
    public void testTryBatchGetItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.batchGetItem(...).
        final BatchGetItemResult batchGetItemResult = new BatchGetItemResult().withResponses(
                new HashMap<String, List<Map<String, AttributeValue>>>() {{
                    put("TableName", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey1", new AttributeValue().withS("PrimaryKeyValue1"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                    put("TableName2", Arrays.asList(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKey2", new AttributeValue().withS("PrimaryKeyValue2"));
                                put("KeyName2", new AttributeValue().withS("Value2"));
                            }}
                    ));
                }}
        );
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>())).thenReturn(batchGetItemResult);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();

        // Verify the results
    }

    @Test
    public void testTryBatchGetItem2_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>())).thenReturn(new BatchGetItemResult());

        // Run the test
        myClassUnderTest.tryBatchGetItem2();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryBatchGetItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryBatchGetItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryBatchGetItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryBatchGetItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchGetItem(new HashMap<>())).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryBatchGetItem2();
    }

    @Test
    public void testTryBatchWriteItem() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenReturn(new BatchWriteItemResult());

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test
    public void testTryBatchWriteItem_AmazonDynamoDBClientReturnsFailure() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }})))
                .thenReturn(
                        new BatchWriteItemResult().addUnprocessedItemsEntry("Key", Arrays.asList(new WriteRequest())));

        // Run the test
        myClassUnderTest.tryBatchWriteItem();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryBatchWriteItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryBatchWriteItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryBatchWriteItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryBatchWriteItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryBatchWriteItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(
                new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
                    put("TableName", Arrays.asList(
                            new WriteRequest().withPutRequest(new PutRequest().withItem(
                                    new HashMap<String, AttributeValue>() {{
                                        put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                                    }}
                            ))
                    ));
                }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem();
    }

    @Test
    public void testTryBatchWriteItem1() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>())).thenReturn(new BatchWriteItemResult());

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test
    public void testTryBatchWriteItem1_AmazonDynamoDBClientReturnsFailure() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>()))
                .thenReturn(
                        new BatchWriteItemResult().addUnprocessedItemsEntry("Key", Arrays.asList(new WriteRequest())));

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>()))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>())).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryBatchWriteItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.batchWriteItem(new HashMap<>())).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryBatchWriteItem1();
    }

    @Test
    public void testTryCreateBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupResult createBackupResult = new CreateBackupResult();
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenReturn(createBackupResult);

        // Run the test
        myClassUnderTest.tryCreateBackup();

        // Verify the results
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test(expected = TableInUseException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(TableInUseException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test(expected = ContinuousBackupsUnavailableException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test(expected = BackupInUseException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(BackupInUseException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryCreateBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.createBackup(...).
        final CreateBackupRequest request = new CreateBackupRequest();
        request.setTableName("tableName");
        request.setBackupName("backupName");
        when(mockAmazonDynamoDBClient.createBackup(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryCreateBackup();
    }

    @Test
    public void testTryCreateGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        final CreateGlobalTableResult createGlobalTableResult = new CreateGlobalTableResult();
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final Replica replica = new Replica();
        request.setReplicationGroup(Arrays.asList(replica));
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenReturn(createGlobalTableResult);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();

        // Verify the results
    }

    @Test(expected = LimitExceededException.class)
    public void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final Replica replica = new Replica();
        request.setReplicationGroup(Arrays.asList(replica));
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final Replica replica = new Replica();
        request.setReplicationGroup(Arrays.asList(replica));
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();
    }

    @Test(expected = GlobalTableAlreadyExistsException.class)
    public void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsGlobalTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final Replica replica = new Replica();
        request.setReplicationGroup(Arrays.asList(replica));
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(GlobalTableAlreadyExistsException.class);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryCreateGlobalTable_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.createGlobalTable(...).
        final CreateGlobalTableRequest request = new CreateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final Replica replica = new Replica();
        request.setReplicationGroup(Arrays.asList(replica));
        when(mockAmazonDynamoDBClient.createGlobalTable(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryCreateGlobalTable();
    }

    @Test
    public void testTryCreateTable() {
        // Setup
        // Configure AmazonDynamoDBClient.createTable(...).
        final CreateTableResult createTableResult = new CreateTableResult();
        when(mockAmazonDynamoDBClient.createTable(new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType"))))).thenReturn(createTableResult);

        // Run the test
        myClassUnderTest.tryCreateTable();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryCreateTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")))))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryCreateTable();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryCreateTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")))))
                .thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryCreateTable();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryCreateTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.createTable(new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")))))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryCreateTable();
    }

    @Test
    public void testTryCreateTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.createTable(...).
        final CreateTableResult createTableResult = new CreateTableResult();
        when(mockAmazonDynamoDBClient.createTable(
                Arrays.asList(new AttributeDefinition("attributeName", "attributeType")), "tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")),
                new ProvisionedThroughput(0L, 0L))).thenReturn(createTableResult);

        // Run the test
        myClassUnderTest.tryCreateTable1();

        // Verify the results
    }

    @Test
    public void testTryDeleteBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        final DeleteBackupResult deleteBackupResult = new DeleteBackupResult();
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenReturn(deleteBackupResult);

        // Run the test
        myClassUnderTest.tryDeleteBackup();

        // Verify the results
    }

    @Test(expected = BackupNotFoundException.class)
    public void testTryDeleteBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(BackupNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteBackup();
    }

    @Test(expected = BackupInUseException.class)
    public void testTryDeleteBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(BackupInUseException.class);

        // Run the test
        myClassUnderTest.tryDeleteBackup();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryDeleteBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteBackup();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteBackup(...).
        final DeleteBackupRequest request = new DeleteBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.deleteBackup(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteBackup();
    }

    @Test
    public void testTryDeleteItem() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        final DeleteItemResult deleteItemResult = new DeleteItemResult();
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenReturn(deleteItemResult);

        // Run the test
        myClassUnderTest.tryDeleteItem();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem(new DeleteItemRequest("TableName",
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue("PrimaryKeyValue"));
                }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem();
    }

    @Test
    public void testTryDeleteItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        final DeleteItemResult deleteItemResult = new DeleteItemResult();
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>())).thenReturn(deleteItemResult);

        // Run the test
        myClassUnderTest.tryDeleteItem1();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem1();
    }

    @Test
    public void testTryDeleteItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteItem(...).
        final DeleteItemResult deleteItemResult = new DeleteItemResult();
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenReturn(deleteItemResult);

        // Run the test
        myClassUnderTest.tryDeleteItem2();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteItem2();
    }

    @Test
    public void testTryDeleteTable() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteTable(...).
        final DeleteTableResult deleteTableResult = new DeleteTableResult();
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName"))).thenReturn(deleteTableResult);

        // Run the test
        myClassUnderTest.tryDeleteTable();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryDeleteTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDeleteTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryDeleteTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable(new DeleteTableRequest("tableName")))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable();
    }

    @Test
    public void testTryDeleteTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.deleteTable(...).
        final DeleteTableResult deleteTableResult = new DeleteTableResult();
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenReturn(deleteTableResult);

        // Run the test
        myClassUnderTest.tryDeleteTable1();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryDeleteTable1_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDeleteTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable1();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryDeleteTable1_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDeleteTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.deleteTable("tableName")).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDeleteTable1();
    }

    @Test
    public void testTryDescribeBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        final DescribeBackupResult describeBackupResult = new DescribeBackupResult();
        final DescribeBackupRequest request = new DescribeBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenReturn(describeBackupResult);

        // Run the test
        myClassUnderTest.tryDescribeBackup();

        // Verify the results
    }

    @Test(expected = BackupNotFoundException.class)
    public void testTryDescribeBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        final DescribeBackupRequest request = new DescribeBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenThrow(BackupNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeBackup();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeBackup(...).
        final DescribeBackupRequest request = new DescribeBackupRequest();
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.describeBackup(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeBackup();
    }

    @Test
    public void testTryDescribeContinuousBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsResult describeContinuousBackupsResult = new DescribeContinuousBackupsResult();
        final DescribeContinuousBackupsRequest request = new DescribeContinuousBackupsRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenReturn(describeContinuousBackupsResult);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups();

        // Verify the results
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryDescribeContinuousBackups_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsRequest request = new DescribeContinuousBackupsRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeContinuousBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeContinuousBackups(...).
        final DescribeContinuousBackupsRequest request = new DescribeContinuousBackupsRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeContinuousBackups(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeContinuousBackups();
    }

    @Test
    public void testTryDescribeEndpoints() {
        // Setup
        // Configure AmazonDynamoDBClient.describeEndpoints(...).
        final DescribeEndpointsResult describeEndpointsResult = new DescribeEndpointsResult();
        when(mockAmazonDynamoDBClient.describeEndpoints(new DescribeEndpointsRequest()))
                .thenReturn(describeEndpointsResult);

        // Run the test
        myClassUnderTest.tryDescribeEndpoints();

        // Verify the results
    }

    @Test
    public void testTryDescribeGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        final DescribeGlobalTableResult describeGlobalTableResult = new DescribeGlobalTableResult();
        final DescribeGlobalTableRequest request = new DescribeGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenReturn(describeGlobalTableResult);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        final DescribeGlobalTableRequest request = new DescribeGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable();
    }

    @Test(expected = GlobalTableNotFoundException.class)
    public void testTryDescribeGlobalTable_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTable(...).
        final DescribeGlobalTableRequest request = new DescribeGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTable(request)).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTable();
    }

    @Test
    public void testTryDescribeGlobalTableSettings() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsResult describeGlobalTableSettingsResult = new DescribeGlobalTableSettingsResult();
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenReturn(describeGlobalTableSettingsResult);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings();

        // Verify the results
    }

    @Test(expected = GlobalTableNotFoundException.class)
    public void testTryDescribeGlobalTableSettings_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeGlobalTableSettings_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeGlobalTableSettings(...).
        final DescribeGlobalTableSettingsRequest request = new DescribeGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        when(mockAmazonDynamoDBClient.describeGlobalTableSettings(request))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeGlobalTableSettings();
    }

    @Test
    public void testTryDescribeLimits() {
        // Setup
        // Configure AmazonDynamoDBClient.describeLimits(...).
        final DescribeLimitsResult describeLimitsResult = new DescribeLimitsResult();
        when(mockAmazonDynamoDBClient.describeLimits(new DescribeLimitsRequest())).thenReturn(describeLimitsResult);

        // Run the test
        myClassUnderTest.tryDescribeLimits();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeLimits_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeLimits(new DescribeLimitsRequest()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeLimits();
    }

    @Test
    public void testTryDescribeTable() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTable(...).
        final DescribeTableResult describeTableResult = new DescribeTableResult();
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenReturn(describeTableResult);

        // Run the test
        myClassUnderTest.tryDescribeTable();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDescribeTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeTable();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable(new DescribeTableRequest("tableName")))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeTable();
    }

    @Test
    public void testTryDescribeTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTable(...).
        final DescribeTableResult describeTableResult = new DescribeTableResult();
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenReturn(describeTableResult);

        // Run the test
        myClassUnderTest.tryDescribeTable1();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDescribeTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeTable1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.describeTable("tableName")).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeTable1();
    }

    @Test
    public void testTryDescribeTimeToLive() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        final DescribeTimeToLiveResult describeTimeToLiveResult = new DescribeTimeToLiveResult();
        final DescribeTimeToLiveRequest request = new DescribeTimeToLiveRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenReturn(describeTimeToLiveResult);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryDescribeTimeToLive_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        final DescribeTimeToLiveRequest request = new DescribeTimeToLiveRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryDescribeTimeToLive_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.describeTimeToLive(...).
        final DescribeTimeToLiveRequest request = new DescribeTimeToLiveRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.describeTimeToLive(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryDescribeTimeToLive();
    }

    @Test
    public void testTryGetItem() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        final GetItemResult getItemResult = new GetItemResult().withItem(new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }});
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenReturn(getItemResult);

        // Run the test
        myClassUnderTest.tryGetItem();

        // Verify the results
    }

    @Test
    public void testTryGetItem_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenReturn(new GetItemResult());

        // Run the test
        myClassUnderTest.tryGetItem();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryGetItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryGetItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryGetItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryGetItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryGetItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem(new GetItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
        }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryGetItem();
    }

    @Test
    public void testTryGetItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        final GetItemResult getItemResult = new GetItemResult().withItem(new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }});
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>())).thenReturn(getItemResult);

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    public void testTryGetItem1_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>())).thenReturn(new GetItemResult());

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryGetItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryGetItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryGetItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryGetItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryGetItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryGetItem1();
    }

    @Test
    public void testTryGetItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.getItem(...).
        final GetItemResult getItemResult = new GetItemResult().withItem(new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }});
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false)).thenReturn(getItemResult);

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test
    public void testTryGetItem2_AmazonDynamoDBClientReturnsNoItem() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false)).thenReturn(new GetItemResult());

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryGetItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryGetItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryGetItem2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryGetItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryGetItem2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryGetItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.getItem("tableName", new HashMap<>(), false))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryGetItem2();
    }

    @Test
    public void testTryListBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.listBackups(...).
        final ListBackupsResult listBackupsResult = new ListBackupsResult();
        final ListBackupsRequest request = new ListBackupsRequest();
        request.setTableName("tableName");
        request.setLimit(0);
        request.setTimeRangeLowerBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeRangeUpperBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExclusiveStartBackupArn("exclusiveStartBackupArn");
        request.setBackupType("backupType");
        when(mockAmazonDynamoDBClient.listBackups(request)).thenReturn(listBackupsResult);

        // Run the test
        myClassUnderTest.tryListBackups();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listBackups(...).
        final ListBackupsRequest request = new ListBackupsRequest();
        request.setTableName("tableName");
        request.setLimit(0);
        request.setTimeRangeLowerBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeRangeUpperBound(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExclusiveStartBackupArn("exclusiveStartBackupArn");
        request.setBackupType("backupType");
        when(mockAmazonDynamoDBClient.listBackups(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListBackups();
    }

    @Test
    public void testTryListGlobalTables() {
        // Setup
        // Configure AmazonDynamoDBClient.listGlobalTables(...).
        final ListGlobalTablesResult listGlobalTablesResult = new ListGlobalTablesResult();
        final ListGlobalTablesRequest request = new ListGlobalTablesRequest();
        request.setExclusiveStartGlobalTableName("exclusiveStartGlobalTableName");
        request.setLimit(0);
        request.setRegionName("regionName");
        when(mockAmazonDynamoDBClient.listGlobalTables(request)).thenReturn(listGlobalTablesResult);

        // Run the test
        myClassUnderTest.tryListGlobalTables();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListGlobalTables_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listGlobalTables(...).
        final ListGlobalTablesRequest request = new ListGlobalTablesRequest();
        request.setExclusiveStartGlobalTableName("exclusiveStartGlobalTableName");
        request.setLimit(0);
        request.setRegionName("regionName");
        when(mockAmazonDynamoDBClient.listGlobalTables(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListGlobalTables();
    }

    @Test
    public void testTryListTables() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        final ListTablesResult listTablesResult = new ListTablesResult();
        when(mockAmazonDynamoDBClient.listTables(new ListTablesRequest("exclusiveStartTableName", 0)))
                .thenReturn(listTablesResult);

        // Run the test
        myClassUnderTest.tryListTables();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTables_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables(new ListTablesRequest("exclusiveStartTableName", 0)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTables();
    }

    @Test
    public void testTryListTables1() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        final ListTablesResult listTablesResult = new ListTablesResult();
        when(mockAmazonDynamoDBClient.listTables()).thenReturn(listTablesResult);

        // Run the test
        myClassUnderTest.tryListTables1();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTables1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables()).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTables1();
    }

    @Test
    public void testTryListTables2() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        final ListTablesResult listTablesResult = new ListTablesResult();
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName")).thenReturn(listTablesResult);

        // Run the test
        myClassUnderTest.tryListTables2();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTables2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName"))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTables2();
    }

    @Test
    public void testTryListTables3() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        final ListTablesResult listTablesResult = new ListTablesResult();
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName", 0)).thenReturn(listTablesResult);

        // Run the test
        myClassUnderTest.tryListTables3();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTables3_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables("exclusiveStartTableName", 0))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTables3();
    }

    @Test
    public void testTryListTables4() {
        // Setup
        // Configure AmazonDynamoDBClient.listTables(...).
        final ListTablesResult listTablesResult = new ListTablesResult();
        when(mockAmazonDynamoDBClient.listTables(0)).thenReturn(listTablesResult);

        // Run the test
        myClassUnderTest.tryListTables4();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTables4_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.listTables(0)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTables4();
    }

    @Test
    public void testTryListTagsOfResource() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        final ListTagsOfResourceResult listTagsOfResourceResult = new ListTagsOfResourceResult();
        final ListTagsOfResourceRequest request = new ListTagsOfResourceRequest();
        request.setResourceArn("resourceArn");
        request.setNextToken("nextToken");
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenReturn(listTagsOfResourceResult);

        // Run the test
        myClassUnderTest.tryListTagsOfResource();

        // Verify the results
    }

    @Test
    public void testTryListTagsOfResource_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        final ListTagsOfResourceRequest request = new ListTagsOfResourceRequest();
        request.setResourceArn("resourceArn");
        request.setNextToken("nextToken");
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenReturn(new ListTagsOfResourceResult());

        // Run the test
        myClassUnderTest.tryListTagsOfResource();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryListTagsOfResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        final ListTagsOfResourceRequest request = new ListTagsOfResourceRequest();
        request.setResourceArn("resourceArn");
        request.setNextToken("nextToken");
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryListTagsOfResource();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryListTagsOfResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.listTagsOfResource(...).
        final ListTagsOfResourceRequest request = new ListTagsOfResourceRequest();
        request.setResourceArn("resourceArn");
        request.setNextToken("nextToken");
        when(mockAmazonDynamoDBClient.listTagsOfResource(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryListTagsOfResource();
    }

    @Test
    public void testTryPutItem() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        final PutItemResult putItemResult = new PutItemResult();
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenReturn(putItemResult);

        // Run the test
        myClassUnderTest.tryPutItem();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryPutItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem(new PutItemRequest("TableName", new HashMap<String, AttributeValue>() {{
            put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
            put("KeyName2", new AttributeValue().withS("Value2"));
        }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryPutItem();
    }

    @Test
    public void testTryPutItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        final PutItemResult putItemResult = new PutItemResult();
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>())).thenReturn(putItemResult);

        // Run the test
        myClassUnderTest.tryPutItem1();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryPutItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryPutItem1();
    }

    @Test
    public void testTryPutItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.putItem(...).
        final PutItemResult putItemResult = new PutItemResult();
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues")).thenReturn(putItemResult);

        // Run the test
        myClassUnderTest.tryPutItem2();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryPutItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.putItem("tableName", new HashMap<>(), "returnValues"))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryPutItem2();
    }

    @Test
    public void testTryQuery() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryResult queryResult = new QueryResult();
        queryResult.setItems(Arrays.asList(new HashMap<>()));
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenReturn(queryResult);

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test
    public void testTryQuery_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenReturn(new QueryResult());

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryQuery_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryQuery();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryQuery_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryQuery();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryQuery_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryQuery();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryQuery_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.query(...).
        final QueryRequest request = new QueryRequest();
        request.setTableName("tableName");
        when(mockAmazonDynamoDBClient.query(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryQuery();
    }

    @Test
    public void testTryRestoreTableFromBackup() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupResult restoreTableFromBackupResult = new RestoreTableFromBackupResult();
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenReturn(restoreTableFromBackupResult);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();

        // Verify the results
    }

    @Test(expected = TableAlreadyExistsException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(TableAlreadyExistsException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test(expected = TableInUseException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(TableInUseException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test(expected = BackupNotFoundException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsBackupNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(BackupNotFoundException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test(expected = BackupInUseException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsBackupInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(BackupInUseException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryRestoreTableFromBackup_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableFromBackup(...).
        final RestoreTableFromBackupRequest request = new RestoreTableFromBackupRequest();
        request.setTargetTableName("targetTableName");
        request.setBackupArn("backupArn");
        when(mockAmazonDynamoDBClient.restoreTableFromBackup(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableFromBackup();
    }

    @Test
    public void testTryRestoreTableToPointInTime() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeResult restoreTableToPointInTimeResult = new RestoreTableToPointInTimeResult();
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenReturn(restoreTableToPointInTimeResult);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();

        // Verify the results
    }

    @Test(expected = TableAlreadyExistsException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableAlreadyExistsException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = TableInUseException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsTableInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(TableInUseException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = InvalidRestoreTimeException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsInvalidRestoreTimeException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(InvalidRestoreTimeException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = PointInTimeRecoveryUnavailableException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsPointInTimeRecoveryUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request))
                .thenThrow(PointInTimeRecoveryUnavailableException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryRestoreTableToPointInTime_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.restoreTableToPointInTime(...).
        final RestoreTableToPointInTimeRequest request = new RestoreTableToPointInTimeRequest();
        when(mockAmazonDynamoDBClient.restoreTableToPointInTime(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryRestoreTableToPointInTime();
    }

    @Test
    public void testTryScan() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        final ScanResult scanResult = new ScanResult().withItems(
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue1"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }},
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue2"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }}
        );
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenReturn(scanResult);

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test
    public void testTryScan_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenReturn(new ScanResult());

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryScan_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName")))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryScan();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryScan_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryScan();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryScan_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName")))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryScan();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryScan_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan(new ScanRequest("tableName"))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryScan();
    }

    @Test
    public void testTryScan1() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        final ScanResult scanResult = new ScanResult().withItems(
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue1"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }},
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue2"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }}
        );
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"))).thenReturn(scanResult);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    public void testTryScan1_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"))).thenReturn(new ScanResult());

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryScan1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value")))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryScan1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryScan1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value")))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryScan1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryScan1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value")))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryScan1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryScan1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value")))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryScan1();
    }

    @Test
    public void testTryScan2() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        final ScanResult scanResult = new ScanResult().withItems(
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue1"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }},
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue2"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }}
        );
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>())).thenReturn(scanResult);

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test
    public void testTryScan2_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>())).thenReturn(new ScanResult());

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryScan2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryScan2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryScan2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>())).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryScan2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryScan2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryScan2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryScan2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", new HashMap<>())).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryScan2();
    }

    @Test
    public void testTryScan3() {
        // Setup
        // Configure AmazonDynamoDBClient.scan(...).
        final ScanResult scanResult = new ScanResult().withItems(
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue1"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }},
                new HashMap<String, AttributeValue>() {{
                    put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue2"));
                    put("KeyName2", new AttributeValue().withS("Value2"));
                }}
        );
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenReturn(scanResult);

        // Run the test
        myClassUnderTest.tryScan3();

        // Verify the results
    }

    @Test
    public void testTryScan3_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenReturn(new ScanResult());

        // Run the test
        myClassUnderTest.tryScan3();

        // Verify the results
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryScan3_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryScan3();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryScan3_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryScan3();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryScan3_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryScan3();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryScan3_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.scan("tableName", Arrays.asList("value"), new HashMap<>()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryScan3();
    }

    @Test
    public void testTryTagResource() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(Arrays.asList(tag));
        when(mockAmazonDynamoDBClient.tagResource(request)).thenReturn(new TagResourceResult());

        // Run the test
        myClassUnderTest.tryTagResource();

        // Verify the results
    }

    @Test(expected = LimitExceededException.class)
    public void testTryTagResource_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(Arrays.asList(tag));
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryTagResource();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryTagResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(Arrays.asList(tag));
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryTagResource();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryTagResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(Arrays.asList(tag));
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryTagResource();
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryTagResource_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.tagResource(...).
        final TagResourceRequest request = new TagResourceRequest();
        request.setResourceArn("resourceArn");
        final Tag tag = new Tag();
        request.setTags(Arrays.asList(tag));
        when(mockAmazonDynamoDBClient.tagResource(request)).thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryTagResource();
    }

    @Test
    public void testTryTransactGetItems() {
        // Setup
        // Configure AmazonDynamoDBClient.transactGetItems(...).
        final TransactGetItemsResult transactGetItemsResult = new TransactGetItemsResult();
        final ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItem(new HashMap<>());
        transactGetItemsResult.setResponses(Arrays.asList(itemResponse));
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenReturn(transactGetItemsResult);

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test
    public void testTryTransactGetItems_AmazonDynamoDBClientReturnsNoItems() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenReturn(new TransactGetItemsResult());

        // Run the test
        myClassUnderTest.tryTransactGetItems();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryTransactGetItems_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryTransactGetItems();
    }

    @Test(expected = TransactionCanceledException.class)
    public void testTryTransactGetItems_AmazonDynamoDBClientThrowsTransactionCanceledException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenThrow(TransactionCanceledException.class);

        // Run the test
        myClassUnderTest.tryTransactGetItems();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryTransactGetItems_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryTransactGetItems();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryTransactGetItems_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryTransactGetItems();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryTransactGetItems_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactGetItems(new TransactGetItemsRequest().withTransactItems(
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName")
                        .withKey(new HashMap<>())
                ),
                new TransactGetItem().withGet(new Get()
                        .withTableName("TableName2")
                        .withKey(new HashMap<>())
                )
        ))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryTransactGetItems();
    }

    @Test
    public void testTryTransactWriteItems() {
        // Setup
        // Configure AmazonDynamoDBClient.transactWriteItems(...).
        final TransactWriteItemsResult transactWriteItemsResult = new TransactWriteItemsResult();
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenReturn(transactWriteItemsResult);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();

        // Verify the results
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = TransactionCanceledException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsTransactionCanceledException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(TransactionCanceledException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = TransactionInProgressException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsTransactionInProgressException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(TransactionInProgressException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = IdempotentParameterMismatchException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsIdempotentParameterMismatchException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(IdempotentParameterMismatchException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryTransactWriteItems_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.transactWriteItems(new TransactWriteItemsRequest().withTransactItems(
                new TransactWriteItem().withPut(new Put()
                        .withTableName("TableName")
                        .withItem(new HashMap<>())
                )))).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryTransactWriteItems();
    }

    @Test
    public void testTryUntagResource() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList("value"));
        when(mockAmazonDynamoDBClient.untagResource(request)).thenReturn(new UntagResourceResult());

        // Run the test
        myClassUnderTest.tryUntagResource();

        // Verify the results
    }

    @Test(expected = LimitExceededException.class)
    public void testTryUntagResource_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList("value"));
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUntagResource();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUntagResource_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList("value"));
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUntagResource();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUntagResource_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList("value"));
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUntagResource();
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryUntagResource_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.untagResource(...).
        final UntagResourceRequest request = new UntagResourceRequest();
        request.setResourceArn("resourceArn");
        request.setTagKeys(Arrays.asList("value"));
        when(mockAmazonDynamoDBClient.untagResource(request)).thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryUntagResource();
    }

    @Test
    public void testTryUpdateContinuousBackups() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsResult updateContinuousBackupsResult = new UpdateContinuousBackupsResult();
        final UpdateContinuousBackupsRequest request = new UpdateContinuousBackupsRequest();
        request.setTableName("tableName");
        final PointInTimeRecoverySpecification pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification();
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification);
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenReturn(updateContinuousBackupsResult);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups();

        // Verify the results
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsRequest request = new UpdateContinuousBackupsRequest();
        request.setTableName("tableName");
        final PointInTimeRecoverySpecification pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification();
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification);
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups();
    }

    @Test(expected = ContinuousBackupsUnavailableException.class)
    public void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsContinuousBackupsUnavailableException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsRequest request = new UpdateContinuousBackupsRequest();
        request.setTableName("tableName");
        final PointInTimeRecoverySpecification pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification();
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification);
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request))
                .thenThrow(ContinuousBackupsUnavailableException.class);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateContinuousBackups_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateContinuousBackups(...).
        final UpdateContinuousBackupsRequest request = new UpdateContinuousBackupsRequest();
        request.setTableName("tableName");
        final PointInTimeRecoverySpecification pointInTimeRecoverySpecification = new PointInTimeRecoverySpecification();
        request.setPointInTimeRecoverySpecification(pointInTimeRecoverySpecification);
        when(mockAmazonDynamoDBClient.updateContinuousBackups(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateContinuousBackups();
    }

    @Test
    public void testTryUpdateGlobalTable() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableResult updateGlobalTableResult = new UpdateGlobalTableResult();
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenReturn(updateGlobalTableResult);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();

        // Verify the results
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();
    }

    @Test(expected = GlobalTableNotFoundException.class)
    public void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();
    }

    @Test(expected = ReplicaAlreadyExistsException.class)
    public void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsReplicaAlreadyExistsException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(ReplicaAlreadyExistsException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();
    }

    @Test(expected = ReplicaNotFoundException.class)
    public void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsReplicaNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();
    }

    @Test(expected = TableNotFoundException.class)
    public void testTryUpdateGlobalTable_AmazonDynamoDBClientThrowsTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTable(...).
        final UpdateGlobalTableRequest request = new UpdateGlobalTableRequest();
        request.setGlobalTableName("globalTableName");
        final ReplicaUpdate replicaUpdate = new ReplicaUpdate();
        request.setReplicaUpdates(Arrays.asList(replicaUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTable(request)).thenThrow(TableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTable();
    }

    @Test
    public void testTryUpdateGlobalTableSettings() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsResult updateGlobalTableSettingsResult = new UpdateGlobalTableSettingsResult();
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenReturn(updateGlobalTableSettingsResult);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();

        // Verify the results
    }

    @Test(expected = GlobalTableNotFoundException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsGlobalTableNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(GlobalTableNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test(expected = ReplicaNotFoundException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsReplicaNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(ReplicaNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test(expected = IndexNotFoundException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsIndexNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(IndexNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateGlobalTableSettings_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateGlobalTableSettings(...).
        final UpdateGlobalTableSettingsRequest request = new UpdateGlobalTableSettingsRequest();
        request.setGlobalTableName("globalTableName");
        request.setGlobalTableBillingMode("globalTableBillingMode");
        request.setGlobalTableProvisionedWriteCapacityUnits(0L);
        final AutoScalingSettingsUpdate globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate = new AutoScalingSettingsUpdate();
        request.setGlobalTableProvisionedWriteCapacityAutoScalingSettingsUpdate(
                globalTableProvisionedWriteCapacityAutoScalingSettingsUpdate);
        final GlobalTableGlobalSecondaryIndexSettingsUpdate globalTableGlobalSecondaryIndexSettingsUpdate = new GlobalTableGlobalSecondaryIndexSettingsUpdate();
        request.setGlobalTableGlobalSecondaryIndexSettingsUpdate(
                Arrays.asList(globalTableGlobalSecondaryIndexSettingsUpdate));
        final ReplicaSettingsUpdate replicaSettingsUpdate = new ReplicaSettingsUpdate();
        request.setReplicaSettingsUpdate(Arrays.asList(replicaSettingsUpdate));
        when(mockAmazonDynamoDBClient.updateGlobalTableSettings(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateGlobalTableSettings();
    }

    @Test
    public void testTryUpdateItem() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        final UpdateItemResult updateItemResult = new UpdateItemResult();
        updateItemResult.setAttributes(new HashMap<>());
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenReturn(updateItemResult);

        // Run the test
        myClassUnderTest.tryUpdateItem();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateItem_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem(
                new UpdateItemRequest("tableName", new HashMap<>(), new HashMap<>(), "returnValues")))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem();
    }

    @Test
    public void testTryUpdateItem1() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        final UpdateItemResult updateItemResult = new UpdateItemResult();
        updateItemResult.setAttributes(new HashMap<>());
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenReturn(updateItemResult);

        // Run the test
        myClassUnderTest.tryUpdateItem1();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateItem1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>()))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem1();
    }

    @Test
    public void testTryUpdateItem2() {
        // Setup
        // Configure AmazonDynamoDBClient.updateItem(...).
        final UpdateItemResult updateItemResult = new UpdateItemResult();
        updateItemResult.setAttributes(new HashMap<>());
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenReturn(updateItemResult);

        // Run the test
        myClassUnderTest.tryUpdateItem2();

        // Verify the results
    }

    @Test(expected = ConditionalCheckFailedException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsConditionalCheckFailedException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(ConditionalCheckFailedException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = ProvisionedThroughputExceededException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsProvisionedThroughputExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = ItemCollectionSizeLimitExceededException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = TransactionConflictException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsTransactionConflictException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(TransactionConflictException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = RequestLimitExceededException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsRequestLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(RequestLimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateItem2_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateItem("tableName", new HashMap<>(), new HashMap<>(),
                "returnValues")).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateItem2();
    }

    @Test
    public void testTryUpdateTable() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTable(...).
        final UpdateTableResult updateTableResult = new UpdateTableResult();
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L)))).thenReturn(updateTableResult);

        // Run the test
        myClassUnderTest.tryUpdateTable();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryUpdateTable_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateTable_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryUpdateTable_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateTable_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable(
                new UpdateTableRequest("tableName", new ProvisionedThroughput(0L, 0L))))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable();
    }

    @Test
    public void testTryUpdateTable1() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTable(...).
        final UpdateTableResult updateTableResult = new UpdateTableResult();
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenReturn(updateTableResult);

        // Run the test
        myClassUnderTest.tryUpdateTable1();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryUpdateTable1_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable1();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateTable1_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable1();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryUpdateTable1_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable1();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateTable1_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        when(mockAmazonDynamoDBClient.updateTable("tableName", new ProvisionedThroughput(0L, 0L)))
                .thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateTable1();
    }

    @Test
    public void testTryUpdateTimeToLive() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        final UpdateTimeToLiveResult updateTimeToLiveResult = new UpdateTimeToLiveResult();
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        final TimeToLiveSpecification timeToLiveSpecification = new TimeToLiveSpecification();
        request.setTimeToLiveSpecification(timeToLiveSpecification);
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenReturn(updateTimeToLiveResult);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();

        // Verify the results
    }

    @Test(expected = ResourceInUseException.class)
    public void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsResourceInUseException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        final TimeToLiveSpecification timeToLiveSpecification = new TimeToLiveSpecification();
        request.setTimeToLiveSpecification(timeToLiveSpecification);
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(ResourceInUseException.class);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsResourceNotFoundException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        final TimeToLiveSpecification timeToLiveSpecification = new TimeToLiveSpecification();
        request.setTimeToLiveSpecification(timeToLiveSpecification);
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(ResourceNotFoundException.class);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();
    }

    @Test(expected = LimitExceededException.class)
    public void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsLimitExceededException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        final TimeToLiveSpecification timeToLiveSpecification = new TimeToLiveSpecification();
        request.setTimeToLiveSpecification(timeToLiveSpecification);
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(LimitExceededException.class);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testTryUpdateTimeToLive_AmazonDynamoDBClientThrowsInternalServerErrorException() {
        // Setup
        // Configure AmazonDynamoDBClient.updateTimeToLive(...).
        final UpdateTimeToLiveRequest request = new UpdateTimeToLiveRequest();
        request.setTableName("tableName");
        final TimeToLiveSpecification timeToLiveSpecification = new TimeToLiveSpecification();
        request.setTimeToLiveSpecification(timeToLiveSpecification);
        when(mockAmazonDynamoDBClient.updateTimeToLive(request)).thenThrow(InternalServerErrorException.class);

        // Run the test
        myClassUnderTest.tryUpdateTimeToLive();
    }

    @Test
    public void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonDynamoDBClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(new ResponseMetadata(new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    public void testTryGetCachedResponseMetadata_AmazonDynamoDBClientReturnsNull() {
        // Setup
        when(mockAmazonDynamoDBClient.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    public void testTryWaiters() {
        // Setup
        when(mockAmazonDynamoDBClient.waiters()).thenReturn(new AmazonDynamoDBWaiters(null));

        // Run the test
        myClassUnderTest.tryWaiters();

        // Verify the results
    }

    @Test
    public void testTryShutdown() {
        // Setup
        // Run the test
        myClassUnderTest.tryShutdown();

        // Verify the results
        verify(mockAmazonDynamoDBClient).shutdown();
    }

    @Test
    public void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
