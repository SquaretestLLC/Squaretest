package com.myapp

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.s3.model.Region
import com.myapp.orders.LicenseType
import com.myapp.orders.StoredOrder
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockDynamoDBMapper)
    }

    @Test
    void testGetOrderCountQuery() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(0)

        // Run the test
        def result = myClassUnderTest.getOrderCountQuery()

        // Verify the results
        assert 0 == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetOrderCountQuery_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getOrderCountQuery()
    }

    @Test
    void testGetOrderCountScan() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(0)

        // Run the test
        def result = myClassUnderTest.getOrderCountScan()

        // Verify the results
        assert 0 == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetOrderCountScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getOrderCountScan()
    }

    @Test
    void testCreateS3LinkWithStrings() {
        // Setup
        when(mockDynamoDBMapper.createS3Link("region", "bucketName", "key")).thenReturn(S3Link.fromJson(null, "json"))

        // Run the test
        def result = myClassUnderTest.createS3LinkWithStrings()

        // Verify the results
    }

    @Test
    void testCreateS3LinkWithRegion() {
        // Setup
        when(mockDynamoDBMapper.createS3Link(Region.US_West_2, "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"))

        // Run the test
        def result = myClassUnderTest.createS3LinkWithRegion()

        // Verify the results
    }

    @Test
    void testDeleteOrders() {
        // Setup
        // Run the test
        myClassUnderTest.deleteOrders()

        // Verify the results
        // Confirm DynamoDBMapper.delete(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        verify(mockDynamoDBMapper).delete(eq(object), any(DynamoDBDeleteExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testDeleteOrders_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.delete(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq(object),
                any(DynamoDBDeleteExpression.class), eq(DynamoDBMapperConfig.DEFAULT))

        // Run the test
        myClassUnderTest.deleteOrders()
    }

    @Test
    void testGenerateCreateTableRequest() {
        // Setup
        // Run the test
        myClassUnderTest.generateCreateTableRequest()

        // Verify the results
        verify(mockDynamoDBMapper).generateCreateTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT)
    }

    @Test
    void testGenerateDeleteTableRequest() {
        // Setup
        // Run the test
        myClassUnderTest.generateDeleteTableRequest()

        // Verify the results
        verify(mockDynamoDBMapper).generateDeleteTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT)
    }

    @Test
    void testGetTableModel() {
        // Setup
        // Run the test
        myClassUnderTest.getTableModel()

        // Verify the results
        verify(mockDynamoDBMapper).getTableModel(StoredOrder.class, DynamoDBMapperConfig.DEFAULT)
    }

    @Test
    void testMarshallIntoObject() {
        // Setup
        // Run the test
        myClassUnderTest.marshallIntoObject()

        // Verify the results
        verify(mockDynamoDBMapper).marshallIntoObject(StoredOrder.class, ["value": new AttributeValue("s")],
                DynamoDBMapperConfig.DEFAULT)
    }

    @Test
    void testMarshallIntoList() {
        // Setup
        // Configure DynamoDBMapper.marshallIntoObjects(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def storedOrders = [storedOrder]
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, [["value": new AttributeValue("s")]],
                DynamoDBMapperConfig.DEFAULT)).thenReturn(storedOrders)

        // Run the test
        myClassUnderTest.marshallIntoList()

        // Verify the results
    }

    @Test
    void testMarshallIntoList_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, [["value": new AttributeValue("s")]],
                DynamoDBMapperConfig.DEFAULT)).thenReturn([])

        // Run the test
        myClassUnderTest.marshallIntoList()

        // Verify the results
    }

    @Test
    void testGetAllWithQueryOrders() {
        // Setup
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def expectedResult = [storedOrder]

        // Configure DynamoDBMapper.query(...).
        def mockPaginatedQueryList = mock(PaginatedQueryList.class)
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList)

        // Run the test
        def result = myClassUnderTest.getAllWithQueryOrders()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetAllWithQueryOrders_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.query(...).
        def mockPaginatedQueryList = mock(PaginatedQueryList.class)
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList)

        // Run the test
        def result = myClassUnderTest.getAllWithQueryOrders()

        // Verify the results
        assert [] == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetAllWithQueryOrders_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getAllWithQueryOrders()
    }

    @Test
    void testGetAllOrdersWithParallelScan() {
        // Setup
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def expectedResult = [storedOrder]

        // Configure DynamoDBMapper.parallelScan(...).
        def mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class)
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedParallelScanList)

        // Run the test
        def result = myClassUnderTest.getAllOrdersWithParallelScan()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetAllOrdersWithParallelScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        def mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class)
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedParallelScanList)

        // Run the test
        def result = myClassUnderTest.getAllOrdersWithParallelScan()

        // Verify the results
        assert [] == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetAllOrdersWithParallelScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(100),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getAllOrdersWithParallelScan()
    }

    @Test
    void testGetOrdersInPageWithQuery() {
        // Setup
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def expectedResult = [storedOrder]

        // Configure DynamoDBMapper.queryPage(...).
        def storedOrderQueryResultPage = new QueryResultPage<>()
        storedOrderQueryResultPage.setResults([])
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(storedOrderQueryResultPage)

        // Run the test
        def result = myClassUnderTest.getOrdersInPageWithQuery()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetOrdersInPageWithQuery_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(new QueryResultPage<>())

        // Run the test
        def result = myClassUnderTest.getOrdersInPageWithQuery()

        // Verify the results
        assert [] == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetOrdersInPageWithQuery_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getOrdersInPageWithQuery()
    }

    @Test
    void testGetAllOrdersWithScan() {
        // Setup
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def expectedResult = [storedOrder]

        // Configure DynamoDBMapper.scan(...).
        def mockPaginatedScanList = mock(PaginatedScanList.class)
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedScanList)

        // Run the test
        def result = myClassUnderTest.getAllOrdersWithScan()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetAllOrdersWithScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.scan(...).
        def mockPaginatedScanList = mock(PaginatedScanList.class)
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(mockPaginatedScanList)

        // Run the test
        def result = myClassUnderTest.getAllOrdersWithScan()

        // Verify the results
        assert [] == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetAllOrdersWithScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getAllOrdersWithScan()
    }

    @Test
    void testGetAllOrdersInpageWithScan() {
        // Setup
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def expectedResult = [storedOrder]

        // Configure DynamoDBMapper.scanPage(...).
        def storedOrderScanResultPage = new ScanResultPage<>()
        storedOrderScanResultPage.setResults([])
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(storedOrderScanResultPage)

        // Run the test
        def result = myClassUnderTest.getAllOrdersInpageWithScan()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetAllOrdersInpageWithScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(new ScanResultPage<>())

        // Run the test
        def result = myClassUnderTest.getAllOrdersInpageWithScan()

        // Verify the results
        assert [] == result
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetAllOrdersInpageWithScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getAllOrdersInpageWithScan()
    }

    @Test
    void testGetObjectsInTransaction() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn(["value"])

        // Run the test
        myClassUnderTest.getObjectsInTransaction()

        // Verify the results
    }

    @Test
    void testGetObjectsInTransaction_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenReturn([])

        // Run the test
        myClassUnderTest.getObjectsInTransaction()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testGetObjectsInTransaction_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getObjectsInTransaction()
    }

    @Test
    void testWriteObjectsInTransaction() {
        // Setup
        // Run the test
        myClassUnderTest.writeObjectsInTransaction()

        // Verify the results
        verify(mockDynamoDBMapper).transactionWrite(any(TransactionWriteRequest.class),
                eq(DynamoDBMapperConfig.DEFAULT))
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testWriteObjectsInTransaction_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).transactionWrite(
                any(TransactionWriteRequest.class), eq(DynamoDBMapperConfig.DEFAULT))

        // Run the test
        myClassUnderTest.writeObjectsInTransaction()
    }

    @Test
    void testBatchDeleteObjects() {
        // Setup
        // Run the test
        myClassUnderTest.batchDeleteObjects()

        // Verify the results
        // Confirm DynamoDBMapper.batchDelete(...).
        def objectsToDelete = new StoredOrder()
        objectsToDelete.setPurchaseId("purchaseId")
        objectsToDelete.setNameOnTheLicense("nameOnTheLicense")
        objectsToDelete.setLicenseType(LicenseType.SQT1_IND)
        objectsToDelete.setNumberOfUsers(0)
        objectsToDelete.setDeliveryEmailAddress("deliveryEmailAddress")
        verify(mockDynamoDBMapper).batchDelete(objectsToDelete)
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testBatchDeleteObjects_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.batchDelete(...).
        def objectsToDelete = new StoredOrder()
        objectsToDelete.setPurchaseId("purchaseId")
        objectsToDelete.setNameOnTheLicense("nameOnTheLicense")
        objectsToDelete.setLicenseType(LicenseType.SQT1_IND)
        objectsToDelete.setNumberOfUsers(0)
        objectsToDelete.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.batchDelete(objectsToDelete)).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.batchDeleteObjects()
    }

    @Test
    void testBatchDeleteObjectsWithList() {
        // Setup
        // Run the test
        myClassUnderTest.batchDeleteObjectsWithList()

        // Verify the results
        // Confirm DynamoDBMapper.batchDelete(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def objectsToDelete = [storedOrder]
        verify(mockDynamoDBMapper).batchDelete(objectsToDelete)
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testBatchDeleteObjectsWithList_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.batchDelete(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def objectsToDelete = [storedOrder]
        when(mockDynamoDBMapper.batchDelete(objectsToDelete)).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.batchDeleteObjectsWithList()
    }
}
