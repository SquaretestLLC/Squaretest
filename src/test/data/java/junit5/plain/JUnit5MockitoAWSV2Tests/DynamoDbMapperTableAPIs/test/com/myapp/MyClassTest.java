package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.Projection;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDbTable mockDynamoDbTable;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDbTable);
    }

    @Test
    void testTryIndex() {
        // Setup
        // Configure DynamoDbTable.index(...).
        final DynamoDbIndex<StoredOrder> mockDynamoDbIndex = mock(DynamoDbIndex.class);
        when(mockDynamoDbTable.index("indexName")).thenReturn(mockDynamoDbIndex);

        // Run the test
        myClassUnderTest.tryIndex();

        // Verify the results
    }

    @Test
    void testTryCreateTable1() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreateTable1();

        // Verify the results
        verify(mockDynamoDbTable).createTable(CreateTableEnhancedRequest.builder()
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .localSecondaryIndices(EnhancedLocalSecondaryIndex.builder().build())
                .globalSecondaryIndices(EnhancedGlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .build());
    }

    @Test
    void testTryCreateTable1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockDynamoDbTable).createTable(CreateTableEnhancedRequest.builder()
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .localSecondaryIndices(EnhancedLocalSecondaryIndex.builder().build())
                .globalSecondaryIndices(EnhancedGlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .build());

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).createTable(CreateTableEnhancedRequest.builder()
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(0L)
                        .writeCapacityUnits(0L)
                        .build())
                .localSecondaryIndices(EnhancedLocalSecondaryIndex.builder().build())
                .globalSecondaryIndices(EnhancedGlobalSecondaryIndex.builder()
                        .indexName("indexName")
                        .projection(Projection.builder()
                                .projectionType(ProjectionType.ALL)
                                .nonKeyAttributes("nonKeyAttributes")
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(0L)
                                .writeCapacityUnits(0L)
                                .build())
                        .build())
                .build());

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable1());
    }

    @Test
    void testTryCreateTable2() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreateTable2();

        // Verify the results
        verify(mockDynamoDbTable).createTable(any(Consumer.class));
    }

    @Test
    void testTryCreateTable2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockDynamoDbTable).createTable(any(Consumer.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).createTable(any(Consumer.class));

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable2());
    }

    @Test
    void testTryCreateTable3() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreateTable3();

        // Verify the results
        verify(mockDynamoDbTable).createTable();
    }

    @Test
    void testTryCreateTable3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockDynamoDbTable).createTable();

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateTable3());
    }

    @Test
    void testTryCreateTable3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).createTable();

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryCreateTable3());
    }

    @Test
    void testTryDeleteItem1() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(DeleteItemEnhancedRequest.builder()
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .conditionExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .build())).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryDeleteItem1();

        // Verify the results
    }

    @Test
    void testTryDeleteItem1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(DeleteItemEnhancedRequest.builder()
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .conditionExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(DeleteItemEnhancedRequest.builder()
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .conditionExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem1());
    }

    @Test
    void testTryDeleteItem2() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(any(Consumer.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryDeleteItem2();

        // Verify the results
    }

    @Test
    void testTryDeleteItem2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem2());
    }

    @Test
    void testTryDeleteItem3() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryDeleteItem3();

        // Verify the results
    }

    @Test
    void testTryDeleteItem3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem3());
    }

    @Test
    void testTryDeleteItem3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.deleteItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem3());
    }

    @Test
    void testTryDeleteItem4() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(keyItem)).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryDeleteItem4();

        // Verify the results
    }

    @Test
    void testTryDeleteItem4_DynamoDbTableThrowsSdkClientException() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(keyItem)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteItem4());
    }

    @Test
    void testTryDeleteItem4_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        // Configure DynamoDbTable.deleteItem(...).
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.deleteItem(keyItem)).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryDeleteItem4());
    }

    @Test
    void testTryGetItem1() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(GetItemEnhancedRequest.builder()
                .consistentRead(false)
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .build())).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbTableReturnsNull() {
        // Setup
        when(mockDynamoDbTable.getItem(GetItemEnhancedRequest.builder()
                .consistentRead(false)
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .build())).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetItem1();

        // Verify the results
    }

    @Test
    void testTryGetItem1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.getItem(GetItemEnhancedRequest.builder()
                .consistentRead(false)
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.getItem(GetItemEnhancedRequest.builder()
                .consistentRead(false)
                .key(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build())
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem1());
    }

    @Test
    void testTryGetItem2() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(any(Consumer.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test
    void testTryGetItem2_DynamoDbTableReturnsNull() {
        // Setup
        when(mockDynamoDbTable.getItem(any(Consumer.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetItem2();

        // Verify the results
    }

    @Test
    void testTryGetItem2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.getItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.getItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem2());
    }

    @Test
    void testTryGetItem3() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryGetItem3();

        // Verify the results
    }

    @Test
    void testTryGetItem3_DynamoDbTableReturnsNull() {
        // Setup
        when(mockDynamoDbTable.getItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetItem3();

        // Verify the results
    }

    @Test
    void testTryGetItem3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.getItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem3());
    }

    @Test
    void testTryGetItem3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.getItem(Key.builder()
                .partitionValue("partitionKey")
                .sortValue("sortKey")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem3());
    }

    @Test
    void testTryGetItem4() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(keyItem)).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryGetItem4();

        // Verify the results
    }

    @Test
    void testTryGetItem4_DynamoDbTableReturnsNull() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(keyItem)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetItem4();

        // Verify the results
    }

    @Test
    void testTryGetItem4_DynamoDbTableThrowsSdkClientException() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(keyItem)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetItem4());
    }

    @Test
    void testTryGetItem4_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        // Configure DynamoDbTable.getItem(...).
        final StoredOrder keyItem = new StoredOrder();
        keyItem.setPurchaseId("purchaseId");
        keyItem.setNameOnTheLicense("nameOnTheLicense");
        keyItem.setLicenseType(LicenseType.SQT1_IND);
        keyItem.setNumberOfUsers(0);
        keyItem.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.getItem(keyItem)).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryGetItem4());
    }

    @Test
    void testTryQuery1() {
        // Setup
        // Configure DynamoDbTable.query(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.query(QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build()))
                .scanIndexForward(false)
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.query(QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build()))
                .scanIndexForward(false)
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.query(QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build()))
                .scanIndexForward(false)
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.query(QueryEnhancedRequest.builder()
                .queryConditional(QueryConditional.keyEqualTo(Key.builder()
                        .partitionValue("partitionKey")
                        .sortValue("sortKey")
                        .build()))
                .scanIndexForward(false)
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQuery2() {
        // Setup
        // Configure DynamoDbTable.query(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.query(any(Consumer.class))).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryQuery2();

        // Verify the results
    }

    @Test
    void testTryQuery2_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.query(any(Consumer.class))).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryQuery2();

        // Verify the results
    }

    @Test
    void testTryQuery2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.query(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.query(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery2());
    }

    @Test
    void testTryQuery3() {
        // Setup
        // Configure DynamoDbTable.query(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.query(any(QueryConditional.class))).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryQuery3();

        // Verify the results
    }

    @Test
    void testTryQuery3_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.query(any(QueryConditional.class))).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryQuery3();

        // Verify the results
    }

    @Test
    void testTryQuery3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.query(any(QueryConditional.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryQuery3());
    }

    @Test
    void testTryQuery3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.query(any(QueryConditional.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryQuery3());
    }

    @Test
    void testTryPutItem1() {
        // Setup
        // Run the test
        myClassUnderTest.tryPutItem1();

        // Verify the results
        verify(mockDynamoDbTable).putItem(any(Consumer.class));
    }

    @Test
    void testTryPutItem1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockDynamoDbTable).putItem(any(Consumer.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).putItem(any(Consumer.class));

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem1());
    }

    @Test
    void testTryPutItem2() {
        // Setup
        // Run the test
        myClassUnderTest.tryPutItem2();

        // Verify the results
        verify(mockDynamoDbTable).putItem(any(Consumer.class));
    }

    @Test
    void testTryPutItem2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockDynamoDbTable).putItem(any(Consumer.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).putItem(any(Consumer.class));

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem2());
    }

    @Test
    void testTryPutItem3() {
        // Setup
        // Run the test
        myClassUnderTest.tryPutItem3();

        // Verify the results
        // Confirm DynamoDbTable.putItem(...).
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDbTable).putItem(item);
    }

    @Test
    void testTryPutItem3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        // Configure DynamoDbTable.putItem(...).
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(SdkClientException.class).when(mockDynamoDbTable).putItem(item);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutItem3());
    }

    @Test
    void testTryPutItem3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        // Configure DynamoDbTable.putItem(...).
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(DynamoDbException.class).when(mockDynamoDbTable).putItem(item);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryPutItem3());
    }

    @Test
    void testTryScan1() {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.scan(ScanEnhancedRequest.builder()
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.scan(ScanEnhancedRequest.builder()
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.scan(ScanEnhancedRequest.builder()
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.scan(ScanEnhancedRequest.builder()
                .exclusiveStartKey(new HashMap<>())
                .limit(0)
                .consistentRead(false)
                .filterExpression(Expression.builder()
                        .expression("expression")
                        .expressionValues(new HashMap<>())
                        .expressionNames(new HashMap<>())
                        .build())
                .attributesToProject("attributesToProject")
                .build())).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryScan2() {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.scan(any(Consumer.class))).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test
    void testTryScan2_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.scan(any(Consumer.class))).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryScan2();

        // Verify the results
    }

    @Test
    void testTryScan2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.scan(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.scan(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan2());
    }

    @Test
    void testTryScan3() {
        // Setup
        // Configure DynamoDbTable.scan(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final PageIterable<StoredOrder> pages = PageIterable.create(
                () -> Arrays.asList(Page.create(Arrays.asList(storedOrder))).iterator());
        when(mockDynamoDbTable.scan()).thenReturn(pages);

        // Run the test
        myClassUnderTest.tryScan3();

        // Verify the results
    }

    @Test
    void testTryScan3_DynamoDbTableReturnsNoItems() {
        // Setup
        when(mockDynamoDbTable.scan()).thenReturn(Collections::emptyIterator);

        // Run the test
        myClassUnderTest.tryScan3();

        // Verify the results
    }

    @Test
    void testTryScan3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.scan()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryScan3());
    }

    @Test
    void testTryScan3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.scan()).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryScan3());
    }

    @Test
    void testTryUpdateItem1() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder storedOrder1 = new StoredOrder();
        storedOrder1.setPurchaseId("purchaseId");
        storedOrder1.setNameOnTheLicense("nameOnTheLicense");
        storedOrder1.setLicenseType(LicenseType.SQT1_IND);
        storedOrder1.setNumberOfUsers(0);
        storedOrder1.setDeliveryEmailAddress("deliveryEmailAddress");
        final UpdateItemEnhancedRequest<StoredOrder> request = UpdateItemEnhancedRequest.builder(StoredOrder.class)
                .item(storedOrder1)
                .build();
        when(mockDynamoDbTable.updateItem(request)).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryUpdateItem1();

        // Verify the results
    }

    @Test
    void testTryUpdateItem1_DynamoDbTableThrowsSdkClientException() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final UpdateItemEnhancedRequest<StoredOrder> request = UpdateItemEnhancedRequest.builder(StoredOrder.class)
                .item(storedOrder)
                .build();
        when(mockDynamoDbTable.updateItem(request)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem1_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final UpdateItemEnhancedRequest<StoredOrder> request = UpdateItemEnhancedRequest.builder(StoredOrder.class)
                .item(storedOrder)
                .build();
        when(mockDynamoDbTable.updateItem(request)).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem1());
    }

    @Test
    void testTryUpdateItem2() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.updateItem(any(Consumer.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryUpdateItem2();

        // Verify the results
    }

    @Test
    void testTryUpdateItem2_DynamoDbTableThrowsSdkClientException() {
        // Setup
        when(mockDynamoDbTable.updateItem(any(Consumer.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem2_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        when(mockDynamoDbTable.updateItem(any(Consumer.class))).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem2());
    }

    @Test
    void testTryUpdateItem3() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.updateItem(item)).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryUpdateItem3();

        // Verify the results
    }

    @Test
    void testTryUpdateItem3_DynamoDbTableThrowsSdkClientException() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.updateItem(item)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUpdateItem3());
    }

    @Test
    void testTryUpdateItem3_DynamoDbTableThrowsDynamoDbException() {
        // Setup
        // Configure DynamoDbTable.updateItem(...).
        final StoredOrder item = new StoredOrder();
        item.setPurchaseId("purchaseId");
        item.setNameOnTheLicense("nameOnTheLicense");
        item.setLicenseType(LicenseType.SQT1_IND);
        item.setNumberOfUsers(0);
        item.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDbTable.updateItem(item)).thenThrow(DynamoDbException.class);

        // Run the test
        assertThrows(DynamoDbException.class, () -> myClassUnderTest.tryUpdateItem3());
    }
}
