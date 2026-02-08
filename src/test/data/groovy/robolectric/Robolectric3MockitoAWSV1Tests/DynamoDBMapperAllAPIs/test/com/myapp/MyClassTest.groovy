package com.myapp

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.amazonaws.services.dynamodbv2.model.*
import com.amazonaws.services.s3.model.Region
import com.myapp.orders.LicenseType
import com.myapp.orders.StoredOrder
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockDynamoDBMapper)
    }

    @Test
    void testTryGetTableModel() {
        // Setup
        when(mockDynamoDBMapper.getTableModel(eq(StoredOrder.class), any(DynamoDBMapperConfig.class))).thenReturn(null)

        // Run the test
        myClassUnderTest.tryGetTableModel()

        // Verify the results
    }

    @Test
    void testTryLoad() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def keyObject = new StoredOrder()
        keyObject.setPurchaseId("purchaseId")
        keyObject.setNameOnTheLicense("nameOnTheLicense")
        keyObject.setLicenseType(LicenseType.SQT1_IND)
        keyObject.setNumberOfUsers(0)
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class))).thenReturn(storedOrder)

        // Run the test
        myClassUnderTest.tryLoad()

        // Verify the results
    }

    @Test
    void testTryLoad_DynamoDBMapperReturnsNull() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def keyObject = new StoredOrder()
        keyObject.setPurchaseId("purchaseId")
        keyObject.setNameOnTheLicense("nameOnTheLicense")
        keyObject.setLicenseType(LicenseType.SQT1_IND)
        keyObject.setNumberOfUsers(0)
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class))).thenReturn(null)

        // Run the test
        myClassUnderTest.tryLoad()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryLoad_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def keyObject = new StoredOrder()
        keyObject.setPurchaseId("purchaseId")
        keyObject.setNameOnTheLicense("nameOnTheLicense")
        keyObject.setLicenseType(LicenseType.SQT1_IND)
        keyObject.setNumberOfUsers(0)
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryLoad()
    }

    @Test
    void testTryLoad1() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrder)

        // Run the test
        myClassUnderTest.tryLoad1()

        // Verify the results
    }

    @Test
    void testTryLoad1_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenReturn(null)

        // Run the test
        myClassUnderTest.tryLoad1()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryLoad1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryLoad1()
    }

    @Test
    void testTryMarshallIntoObject() {
        // Setup
        // Configure DynamoDBMapper.marshallIntoObject(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        when(mockDynamoDBMapper.marshallIntoObject(eq(StoredOrder.class), eq(["value": new AttributeValue("s")]),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrder)

        // Run the test
        myClassUnderTest.tryMarshallIntoObject()

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects() {
        // Setup
        // Configure DynamoDBMapper.marshallIntoObjects(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def storedOrders = [storedOrder]
        when(mockDynamoDBMapper.marshallIntoObjects(eq(StoredOrder.class), eq([["value": new AttributeValue("s")]]),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrders)

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects()

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.marshallIntoObjects(eq(StoredOrder.class), eq([["value": new AttributeValue("s")]]),
                any(DynamoDBMapperConfig.class))).thenReturn([])

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects()

        // Verify the results
    }

    @Test
    void testTrySave() {
        // Setup
        // Run the test
        myClassUnderTest.trySave()

        // Verify the results
        // Confirm DynamoDBMapper.save(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        verify(mockDynamoDBMapper).save(eq(object), any(DynamoDBSaveExpression.class), any(DynamoDBMapperConfig.class))
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTrySave_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.save(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(eq(object),
                any(DynamoDBSaveExpression.class), any(DynamoDBMapperConfig.class))

        // Run the test
        myClassUnderTest.trySave()
    }

    @Test
    void testTryDelete() {
        // Setup
        // Run the test
        myClassUnderTest.tryDelete()

        // Verify the results
        // Confirm DynamoDBMapper.delete(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        verify(mockDynamoDBMapper).delete(eq(object), any(DynamoDBDeleteExpression.class),
                any(DynamoDBMapperConfig.class))
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryDelete_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure DynamoDBMapper.delete(...).
        def object = new StoredOrder()
        object.setPurchaseId("purchaseId")
        object.setNameOnTheLicense("nameOnTheLicense")
        object.setLicenseType(LicenseType.SQT1_IND)
        object.setNumberOfUsers(0)
        object.setDeliveryEmailAddress("deliveryEmailAddress")
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq(object),
                any(DynamoDBDeleteExpression.class), any(DynamoDBMapperConfig.class))

        // Run the test
        myClassUnderTest.tryDelete()
    }

    @Test
    void testTryTransactionWrite() {
        // Setup
        // Run the test
        myClassUnderTest.tryTransactionWrite()

        // Verify the results
        verify(mockDynamoDBMapper).transactionWrite(any(TransactionWriteRequest.class),
                any(DynamoDBMapperConfig.class))
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryTransactionWrite_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).transactionWrite(
                any(TransactionWriteRequest.class), any(DynamoDBMapperConfig.class))

        // Run the test
        myClassUnderTest.tryTransactionWrite()
    }

    @Test
    void testTryTransactionLoad() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenReturn(["value"])

        // Run the test
        myClassUnderTest.tryTransactionLoad()

        // Verify the results
    }

    @Test
    void testTryTransactionLoad_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenReturn([])

        // Run the test
        myClassUnderTest.tryTransactionLoad()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryTransactionLoad_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryTransactionLoad()
    }

    @Test
    void testTryBatchWrite() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenReturn([])

        // Run the test
        myClassUnderTest.tryBatchWrite()

        // Verify the results
    }

    @Test
    void testTryBatchWrite_DynamoDBMapperReturnsFailure() {
        // Setup
        // Configure DynamoDBMapper.batchWrite(...).
        def failedBatch = new DynamoDBMapper.FailedBatch()
        def failedBatches = [failedBatch]
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenReturn(failedBatches)

        // Run the test
        myClassUnderTest.tryBatchWrite()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryBatchWrite_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryBatchWrite()
    }

    @Test
    void testTryBatchLoad() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class), any(DynamoDBMapperConfig.class)))
                .thenReturn(["value": ["value"]])

        // Run the test
        myClassUnderTest.tryBatchLoad()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryBatchLoad_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryBatchLoad()
    }

    @Test
    void testTryBatchLoad1() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(eq([String.class: [new KeyPair()
                                                                     .withHashKey("hashKey")
                                                                     .withRangeKey("rangeKey")]]),
                any(DynamoDBMapperConfig.class))).thenReturn(["value": ["value"]])

        // Run the test
        myClassUnderTest.tryBatchLoad1()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryBatchLoad1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(eq([String.class: [new KeyPair()
                                                                     .withHashKey("hashKey")
                                                                     .withRangeKey("rangeKey")]]),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryBatchLoad1()
    }

    @Test
    void testTryScan() {
        // Setup
        // Configure DynamoDBMapper.scan(...).
        def mockPaginatedScanList = mock(PaginatedScanList.class)
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedScanList)

        // Run the test
        myClassUnderTest.tryScan()

        // Verify the results
    }

    @Test
    void testTryScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.scan(...).
        def mockPaginatedScanList = mock(PaginatedScanList.class)
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedScanList)

        // Run the test
        myClassUnderTest.tryScan()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryScan()
    }

    @Test
    void testTryParallelScan() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        def mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class)
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedParallelScanList)

        // Run the test
        myClassUnderTest.tryParallelScan()

        // Verify the results
    }

    @Test
    void testTryParallelScan_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        def mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class)
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedParallelScanList)

        // Run the test
        myClassUnderTest.tryParallelScan()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryParallelScan_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryParallelScan()
    }

    @Test
    void testTryScanPage() {
        // Setup
        // Configure DynamoDBMapper.scanPage(...).
        def storedOrderScanResultPage = new ScanResultPage<>()
        storedOrderScanResultPage.setResults([])
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrderScanResultPage)

        // Run the test
        myClassUnderTest.tryScanPage()

        // Verify the results
    }

    @Test
    void testTryScanPage_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new ScanResultPage<>())

        // Run the test
        myClassUnderTest.tryScanPage()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryScanPage_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryScanPage()
    }

    @Test
    void testTryQuery() {
        // Setup
        // Configure DynamoDBMapper.query(...).
        def mockPaginatedQueryList = mock(PaginatedQueryList.class)
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList)

        // Run the test
        myClassUnderTest.tryQuery()

        // Verify the results
    }

    @Test
    void testTryQuery_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.query(...).
        def mockPaginatedQueryList = mock(PaginatedQueryList.class)
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList)

        // Run the test
        myClassUnderTest.tryQuery()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryQuery_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryQuery()
    }

    @Test
    void testTryQueryPage() {
        // Setup
        // Configure DynamoDBMapper.queryPage(...).
        def storedOrderQueryResultPage = new QueryResultPage<>()
        storedOrderQueryResultPage.setResults([])
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrderQueryResultPage)

        // Run the test
        myClassUnderTest.tryQueryPage()

        // Verify the results
    }

    @Test
    void testTryQueryPage_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new QueryResultPage<>())

        // Run the test
        myClassUnderTest.tryQueryPage()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryQueryPage_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryQueryPage()
    }

    @Test
    void testTryCount() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(0)

        // Run the test
        myClassUnderTest.tryCount()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryCount_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryCount()
    }

    @Test
    void testTryCount1() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(0)

        // Run the test
        myClassUnderTest.tryCount1()

        // Verify the results
    }

    @Test(expected = AmazonDynamoDBException.class)
    void testTryCount1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.tryCount1()
    }

    @Test
    void testTryGetS3ClientCache() {
        // Setup
        when(mockDynamoDBMapper.getS3ClientCache()).thenReturn(null)

        // Run the test
        myClassUnderTest.tryGetS3ClientCache()

        // Verify the results
    }

    @Test
    void testTryCreateS3Link() {
        // Setup
        when(mockDynamoDBMapper.createS3Link(Region.US_Standard, "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"))

        // Run the test
        myClassUnderTest.tryCreateS3Link()

        // Verify the results
    }

    @Test
    void testTryCreateS3Link1() {
        // Setup
        when(mockDynamoDBMapper.createS3Link("s3region", "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"))

        // Run the test
        myClassUnderTest.tryCreateS3Link1()

        // Verify the results
    }

    @Test
    void testTryGenerateCreateTableRequest() {
        // Setup
        // Configure DynamoDBMapper.generateCreateTableRequest(...).
        def createTableRequest = new CreateTableRequest("tableName",
                [new KeySchemaElement("attributeName", "keyType")])
        when(mockDynamoDBMapper.generateCreateTableRequest(eq(StoredOrder.class),
                any(DynamoDBMapperConfig.class))).thenReturn(createTableRequest)

        // Run the test
        myClassUnderTest.tryGenerateCreateTableRequest()

        // Verify the results
    }

    @Test
    void testTryGenerateDeleteTableRequest() {
        // Setup
        when(mockDynamoDBMapper.generateDeleteTableRequest(eq(StoredOrder.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new DeleteTableRequest("tableName"))

        // Run the test
        myClassUnderTest.tryGenerateDeleteTableRequest()

        // Verify the results
    }

    @Test
    void testTryNewTableMapper() {
        // Setup
        when(mockDynamoDBMapper.newTableMapper(StoredOrder.class)).thenReturn(null)

        // Run the test
        myClassUnderTest.tryNewTableMapper()

        // Verify the results
    }
}
