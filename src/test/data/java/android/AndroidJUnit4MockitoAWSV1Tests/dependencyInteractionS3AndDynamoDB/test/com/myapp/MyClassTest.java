package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.myapp.metrics.MetricAdapter;
import com.myapp.metrics.MetricEvent;
import com.myapp.orders.StoredOrder;
import com.myapp.other.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;
    @Mock
    private AmazonS3Client mockS3Client;
    @Mock
    private MetricAdapter mockMetricAdapter;
    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper, mockS3Client, "bucketName", mockMetricAdapter,
                mockFooService);
    }

    @Test
    public void testGetTheNull() {
        // Setup
        when(mockDynamoDBMapper.load(any(T.class))).thenReturn("result");

        // Run the test
        final Object result = myClassUnderTest.getTheNull();

        // Verify the results
    }

    @Test
    public void testGetTheNull_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(any(T.class))).thenReturn(null);

        // Run the test
        final Object result = myClassUnderTest.getTheNull();

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetTheNull_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(any(T.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getTheNull();
    }

    @Test
    public void testGetOrder() throws Exception {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
        verify(spyS3Object).close();
    }

    @Test
    public void testGetOrder_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(null);
        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    public void testGetOrder_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    public void testGetOrder_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    public void testGetOrder_AmazonS3ClientReturnsNoContent() throws Exception {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");

        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
        verify(spyS3Object).close();
    }

    @Test
    public void testGetOrder_AmazonS3ClientReturnsBrokenIo() throws Exception {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");

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
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
        verify(spyS3Object).close();
    }

    @Test
    public void testGetOrder_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(SdkClientException.class);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    public void testGetOrder_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("hashKey");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        when(mockFooService.doSomething("purchaseId")).thenReturn("creator");
        when(mockS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenThrow(AmazonServiceException.class);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    public void testGetOrderWithExistingOrder() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject)).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithExistingOrder(order);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithExistingOrder_DynamoDBMapperReturnsNull() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject)).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithExistingOrder(order);

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithExistingOrder_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithExistingOrder(order);
    }

    @Test
    public void testGetOrderWithExistingOrderAndConfig() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject, DynamoDBMapperConfig.DEFAULT)).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithExistingOrderAndConfig(order);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithExistingOrderAndConfig_DynamoDBMapperReturnsNull() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject, DynamoDBMapperConfig.DEFAULT)).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithExistingOrderAndConfig(order);

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithExistingOrderAndConfig_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        final StoredOrder order = new StoredOrder();
        order.setPurchaseId("purchaseId");
        order.setNameOnTheLicense("nameOnTheLicense");
        order.setCreator("creator");
        order.setLoanDocumentS3Path("loanDocumentS3Path");
        order.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setCreator("creator");
        keyObject.setLoanDocumentS3Path("loanDocumentS3Path");
        keyObject.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(keyObject, DynamoDBMapperConfig.DEFAULT)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithExistingOrderAndConfig(order);
    }

    @Test
    public void testGetOrderWithConfig() {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", DynamoDBMapperConfig.DEFAULT))
                .thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithConfig("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithConfig_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", DynamoDBMapperConfig.DEFAULT)).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithConfig("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithConfig_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", DynamoDBMapperConfig.DEFAULT))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithConfig("orderId");
    }

    @Test
    public void testGetOrderWithRangeKey() {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue")).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithRangeKey("orderId", "rangeKeyValue");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithRangeKey_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue")).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithRangeKey("orderId", "rangeKeyValue");

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithRangeKey_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue"))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithRangeKey("orderId", "rangeKeyValue");
    }

    @Test
    public void testGetOrderWithRangeKeyAndConfig() {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue",
                DynamoDBMapperConfig.DEFAULT)).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithRangeKeyAndConfig("orderId", "rangeKeyValue");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithRangeKeyAndConfig_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue",
                DynamoDBMapperConfig.DEFAULT)).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithRangeKeyAndConfig("orderId", "rangeKeyValue");

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithRangeKeyAndConfig_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId", "rangeKeyValue",
                DynamoDBMapperConfig.DEFAULT)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithRangeKeyAndConfig("orderId", "rangeKeyValue");
    }

    @Test
    public void testGetOrderWithWeirdGenerics() {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setCreator("creator");
        expectedResult.setLoanDocumentS3Path("loanDocumentS3Path");
        expectedResult.setLoanDocumentText("loanDocumentText");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setCreator("creator");
        storedOrder.setLoanDocumentS3Path("loanDocumentS3Path");
        storedOrder.setLoanDocumentText("loanDocumentText");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithWeirdGenerics("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetOrderWithWeirdGenerics_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrderWithWeirdGenerics("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test(expected = AmazonDynamoDBException.class)
    public void testGetOrderWithWeirdGenerics_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        myClassUnderTest.getOrderWithWeirdGenerics("orderId");
    }
}
