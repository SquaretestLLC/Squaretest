package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.s3.model.Region;
import com.myapp.orders.LicenseType;
import com.myapp.orders.StoredOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper);
    }

    @Test
    public void testGetOrderCountQuery() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(0);

        // Run the test
        final int result = myClassUnderTest.getOrderCountQuery();

        // Verify the results
        assertEquals(0, result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderCountQuery_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderCountQuery();
    }

    @Test
    public void testGetOrderCountScan() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(0);

        // Run the test
        final int result = myClassUnderTest.getOrderCountScan();

        // Verify the results
        assertEquals(0, result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderCountScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderCountScan();
    }

    @Test
    public void testCreateS3LinkWithStrings() {
        // Setup
        when(mockDynamoDBMapper.createS3Link("region", "bucketName", "key")).thenReturn(S3Link.fromJson(null, "json"));

        // Run the test
        final S3Link result = myClassUnderTest.createS3LinkWithStrings();

        // Verify the results
    }

    @Test
    public void testCreateS3LinkWithRegion() {
        // Setup
        when(mockDynamoDBMapper.createS3Link(Region.US_West_2, "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"));

        // Run the test
        final S3Link result = myClassUnderTest.createS3LinkWithRegion();

        // Verify the results
    }

    @Test
    public void testDeleteOrders() {
        // Setup
        // Run the test
        myClassUnderTest.deleteOrders();

        // Verify the results
        // Confirm DynamoDBMapper.delete(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).delete(eq(object), any(DynamoDBDeleteExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT));
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testDeleteOrders_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.delete(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq(object),
                any(DynamoDBDeleteExpression.class), eq(DynamoDBMapperConfig.DEFAULT));

        // Run the test
        myClassUnderTest.deleteOrders();
    }

    @Test
    public void testGenerateCreateTableRequest() {
        // Setup
        // Run the test
        myClassUnderTest.generateCreateTableRequest();

        // Verify the results
        verify(mockDynamoDBMapper).generateCreateTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    @Test
    public void testGenerateDeleteTableRequest() {
        // Setup
        // Run the test
        myClassUnderTest.generateDeleteTableRequest();

        // Verify the results
        verify(mockDynamoDBMapper).generateDeleteTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    @Test
    public void testGetTableModel() {
        // Setup
        // Run the test
        myClassUnderTest.getTableModel();

        // Verify the results
        verify(mockDynamoDBMapper).getTableModel(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    @Test
    public void testMarshallIntoObject() {
        // Setup
        // Run the test
        myClassUnderTest.marshallIntoObject();

        // Verify the results
        verify(mockDynamoDBMapper).marshallIntoObject(StoredOrder.class, new HashMap<>(), DynamoDBMapperConfig.DEFAULT);
    }

    @Test
    public void testMarshallIntoList() {
        // Setup
        // Configure DynamoDBMapper.marshallIntoObjects(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> storedOrders = Arrays.asList(storedOrder);
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, Arrays.asList(new HashMap<>()),
                DynamoDBMapperConfig.DEFAULT)).thenReturn(storedOrders);

        // Run the test
        myClassUnderTest.marshallIntoList();

        // Verify the results
    }

    @Test
    public void testMarshallIntoList_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, Arrays.asList(new HashMap<>()),
                DynamoDBMapperConfig.DEFAULT)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.marshallIntoList();

        // Verify the results
    }

    @Test
    public void testGetAllWithQueryOrders() {
        // Setup
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> expectedResult = Arrays.asList(storedOrder);

        // Configure DynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllWithQueryOrders();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllWithQueryOrders_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllWithQueryOrders();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetAllWithQueryOrders_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getAllWithQueryOrders();
    }

    @Test
    public void testGetAllOrdersWithParallelScan() {
        // Setup
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> expectedResult = Arrays.asList(storedOrder);

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersWithParallelScan();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllOrdersWithParallelScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersWithParallelScan();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetAllOrdersWithParallelScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getAllOrdersWithParallelScan();
    }

    @Test
    public void testGetOrdersInPageWithQuery() {
        // Setup
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> expectedResult = Arrays.asList(storedOrder);

        // Configure DynamoDBMapper.queryPage(...).
        final QueryResultPage<StoredOrder> storedOrderQueryResultPage = new QueryResultPage<>();
        storedOrderQueryResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(storedOrderQueryResultPage);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getOrdersInPageWithQuery();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrdersInPageWithQuery_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(new QueryResultPage<>());

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getOrdersInPageWithQuery();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrdersInPageWithQuery_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrdersInPageWithQuery();
    }

    @Test
    public void testGetAllOrdersWithScan() {
        // Setup
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> expectedResult = Arrays.asList(storedOrder);

        // Configure DynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedScanList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersWithScan();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllOrdersWithScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedScanList);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersWithScan();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetAllOrdersWithScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getAllOrdersWithScan();
    }

    @Test
    public void testGetAllOrdersInpageWithScan() {
        // Setup
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> expectedResult = Arrays.asList(storedOrder);

        // Configure DynamoDBMapper.scanPage(...).
        final ScanResultPage<StoredOrder> storedOrderScanResultPage = new ScanResultPage<>();
        storedOrderScanResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(storedOrderScanResultPage);

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersInpageWithScan();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllOrdersInpageWithScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(new ScanResultPage<>());

        // Run the test
        final List<StoredOrder> result = myClassUnderTest.getAllOrdersInpageWithScan();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetAllOrdersInpageWithScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getAllOrdersInpageWithScan();
    }

    @Test
    public void testGetObjectsInTransaction() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(Arrays.asList("value"));

        // Run the test
        myClassUnderTest.getObjectsInTransaction();

        // Verify the results
    }

    @Test
    public void testGetObjectsInTransaction_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.getObjectsInTransaction();

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetObjectsInTransaction_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getObjectsInTransaction();
    }

    @Test
    public void testWriteObjectsInTransaction() {
        // Setup
        // Run the test
        myClassUnderTest.writeObjectsInTransaction();

        // Verify the results
        verify(mockDynamoDBMapper).transactionWrite(any(TransactionWriteRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT));
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testWriteObjectsInTransaction_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).transactionWrite(
                any(TransactionWriteRequest.class), eq(DynamoDBMapperConfig.DEFAULT));

        // Run the test
        myClassUnderTest.writeObjectsInTransaction();
    }

    @Test
    public void testBatchDeleteObjects() {
        // Setup
        // Run the test
        myClassUnderTest.batchDeleteObjects();

        // Verify the results
        // Confirm DynamoDBMapper.batchDelete(...).
        final StoredOrder objectsToDelete = new StoredOrder();
        objectsToDelete.setPurchaseId("purchaseId");
        objectsToDelete.setNameOnTheLicense("nameOnTheLicense");
        objectsToDelete.setLicenseType(LicenseType.SQT1_IND);
        objectsToDelete.setNumberOfUsers(0);
        objectsToDelete.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).batchDelete(objectsToDelete);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testBatchDeleteObjects_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.batchDelete(...).
        final StoredOrder objectsToDelete = new StoredOrder();
        objectsToDelete.setPurchaseId("purchaseId");
        objectsToDelete.setNameOnTheLicense("nameOnTheLicense");
        objectsToDelete.setLicenseType(LicenseType.SQT1_IND);
        objectsToDelete.setNumberOfUsers(0);
        objectsToDelete.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.batchDelete(objectsToDelete)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.batchDeleteObjects();
    }

    @Test
    public void testBatchDeleteObjectsWithList() {
        // Setup
        // Run the test
        myClassUnderTest.batchDeleteObjectsWithList();

        // Verify the results
        // Confirm DynamoDBMapper.batchDelete(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> objectsToDelete = Arrays.asList(storedOrder);
        verify(mockDynamoDBMapper).batchDelete(objectsToDelete);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testBatchDeleteObjectsWithList_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.batchDelete(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> objectsToDelete = Arrays.asList(storedOrder);
        when(mockDynamoDBMapper.batchDelete(objectsToDelete)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.batchDeleteObjectsWithList();
    }
}
