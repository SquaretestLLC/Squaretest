package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.myapp.metrics.MetricAdapter;
import com.myapp.metrics.MetricEvent;
import com.myapp.orders.LicenseType;
import com.myapp.orders.StoredOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;
    @Mock
    private AmazonS3Client mockS3Client;
    @Mock
    private MetricAdapter mockMetricAdapter;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockDynamoDBMapper, mockS3Client, mockMetricAdapter, "bucketName");
    }

    @Test
    public void testGetOrder() throws Exception {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setLicenseType(LicenseType.SQT1_IND);
        expectedResult.setLoanDocumentS3Path("path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setLoanDocumentS3Path("path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoOrderFound);
        verify(spyS3Object).close();
    }

    @Test(expected = OrderStoreException.class)
    public void testGetOrder_DynamoDBMapperReturnsNull() throws Exception {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(null);

        // Run the test
        myClassUnderTest.getOrder("orderId");
    }

    @Test(expected = OrderStoreException.class)
    public void testGetOrder_DynamoDBMapperThrowsAmazonDynamoDBException() throws Exception {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrder("orderId");
    }

    @Test
    public void testGetOrder_AmazonS3ClientReturnsNoContent() throws Exception {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setLicenseType(LicenseType.SQT1_IND);
        expectedResult.setLoanDocumentS3Path("path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setLoanDocumentS3Path("path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.NoOrderFound);
        verify(spyS3Object).close();
    }

    @Test(expected = OrderStoreException.class)
    public void testGetOrder_AmazonS3ClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setLoanDocumentS3Path("path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new InputStream() {
            private final IOException exception = new IOException("Error");

            @Override
            public int read() throws IOException {
                throw exception;
            }

            @Override
            public int available() throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        });
        when(mockS3Client.getObject("bucketName", "path")).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.getOrder("orderId");
    }

    @Test(expected = OrderStoreException.class)
    public void testGetOrder_AmazonS3ClientThrowsSdkClientException() throws Exception {
        // Setup
        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setLoanDocumentS3Path("path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        when(mockS3Client.getObject("bucketName", "path")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.getOrder("orderId");
    }

    @Test(expected = OrderStoreException.class)
    public void testGetOrder_AmazonS3ClientThrowsAmazonServiceException() throws Exception {
        // Setup
        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setLoanDocumentS3Path("path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        when(mockS3Client.getObject("bucketName", "path")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.getOrder("orderId");
    }
}
