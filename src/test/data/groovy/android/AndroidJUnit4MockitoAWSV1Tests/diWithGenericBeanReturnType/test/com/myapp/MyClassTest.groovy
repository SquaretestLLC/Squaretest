package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.amazonaws.AmazonServiceException
import com.amazonaws.SdkClientException
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.S3Object
import com.myapp.metrics.MetricAdapter
import com.myapp.metrics.MetricEvent
import com.myapp.orders.LicenseType
import com.myapp.orders.StoredOrder
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper
    @Mock
    private AmazonS3Client mockS3Client
    @Mock
    private MetricAdapter mockMetricAdapter

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockDynamoDBMapper, mockS3Client, mockMetricAdapter, "bucketName")
    }

    @Test
    void testGetOrder() {
        // Setup
        def expectedResult = new StoredOrder()
        expectedResult.setPurchaseId("purchaseId")
        expectedResult.setNameOnTheLicense("nameOnTheLicense")
        expectedResult.setLicenseType(LicenseType.SQT1_IND)
        expectedResult.setLoanDocumentS3Path("path")
        expectedResult.setLoanDocumentText("loanDocumentText")

        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setLoanDocumentS3Path("path")
        storedOrder.setLoanDocumentText("loanDocumentText")
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder)

        // Configure AmazonS3Client.getObject(...).
        def spyS3Object = spy(new S3Object())
        spyS3Object.setBucketName("bucketName")
        spyS3Object.setKey("key")
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()))
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object)

        // Run the test
        def result = myClassUnderTest.getOrder("orderId")

        // Verify the results
        assert expectedResult == result
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoOrderFound)
        verify(spyS3Object).close()
    }

    @Test(expected = OrderStoreException.class)
    void testGetOrder_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(null)

        // Run the test
        myClassUnderTest.getOrder("orderId")
    }

    @Test(expected = OrderStoreException.class)
    void testGetOrder_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenThrow(AmazonDynamoDBException.class)

        // Run the test
        myClassUnderTest.getOrder("orderId")
    }

    @Test
    void testGetOrder_AmazonS3ClientReturnsNoContent() {
        // Setup
        def expectedResult = new StoredOrder()
        expectedResult.setPurchaseId("purchaseId")
        expectedResult.setNameOnTheLicense("nameOnTheLicense")
        expectedResult.setLicenseType(LicenseType.SQT1_IND)
        expectedResult.setLoanDocumentS3Path("path")
        expectedResult.setLoanDocumentText("loanDocumentText")

        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setLoanDocumentS3Path("path")
        storedOrder.setLoanDocumentText("loanDocumentText")
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder)

        // Configure AmazonS3Client.getObject(...).
        def spyS3Object = spy(new S3Object())
        spyS3Object.setBucketName("bucketName")
        spyS3Object.setKey("key")
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}))
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object)

        // Run the test
        def result = myClassUnderTest.getOrder("orderId")

        // Verify the results
        assert expectedResult == result
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoOrderFound)
        verify(spyS3Object).close()
    }

    @Test(expected = OrderStoreException.class)
    void testGetOrder_AmazonS3ClientReturnsBrokenIo() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setLoanDocumentS3Path("path")
        storedOrder.setLoanDocumentText("loanDocumentText")
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder)

        // Configure AmazonS3Client.getObject(...).
        def spyS3Object = spy(new S3Object())
        spyS3Object.setBucketName("bucketName")
        spyS3Object.setKey("key")
        spyS3Object.setObjectContent(new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        })
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object)

        // Run the test
        myClassUnderTest.getOrder("orderId")
    }

    @Test(expected = OrderStoreException.class)
    void testGetOrder_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setLoanDocumentS3Path("path")
        storedOrder.setLoanDocumentText("loanDocumentText")
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder)

        when(mockS3Client.getObject("bucketName", "path")).thenThrow(SdkClientException.class)

        // Run the test
        myClassUnderTest.getOrder("orderId")
    }

    @Test(expected = OrderStoreException.class)
    void testGetOrder_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setLoanDocumentS3Path("path")
        storedOrder.setLoanDocumentText("loanDocumentText")
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder)

        when(mockS3Client.getObject("bucketName", "path")).thenThrow(AmazonServiceException.class)

        // Run the test
        myClassUnderTest.getOrder("orderId")
    }
}
