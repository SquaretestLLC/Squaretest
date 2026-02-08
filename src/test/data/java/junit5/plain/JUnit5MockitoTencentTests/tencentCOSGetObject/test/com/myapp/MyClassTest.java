package com.myapp;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private COSClient mockCosClient;
    @Mock
    private MetricAdapter mockMetricAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockCosClient, mockMetricAdapter, "bucketName");
    }

    @Test
    void testGetPurchaseOrder() throws Exception {
        // Setup
        // Configure COSClient.getObject(...).
        final COSObject spyCOSObject = spy(new COSObject());
        spyCOSObject.setBucketName("bucketName");
        spyCOSObject.setKey("key");
        spyCOSObject.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockCosClient.getObject("bucketName", "key")).thenReturn(spyCOSObject);

        // Run the test
        final PDDocument result = myClassUnderTest.getPurchaseOrder("orderId");

        // Verify the results
        verify(spyCOSObject).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientReturnsNoContent() throws Exception {
        // Setup
        // Configure COSClient.getObject(...).
        final COSObject spyCOSObject = spy(new COSObject());
        spyCOSObject.setBucketName("bucketName");
        spyCOSObject.setKey("key");
        spyCOSObject.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockCosClient.getObject("bucketName", "key")).thenReturn(spyCOSObject);

        // Run the test
        final PDDocument result = myClassUnderTest.getPurchaseOrder("orderId");

        // Verify the results
        verify(spyCOSObject).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientReturnsBrokenIo() throws Exception {
        // Setup
        // Configure COSClient.getObject(...).
        final COSObject spyCOSObject = spy(new COSObject());
        spyCOSObject.setBucketName("bucketName");
        spyCOSObject.setKey("key");
        spyCOSObject.setObjectContent(new InputStream() {
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
        when(mockCosClient.getObject("bucketName", "key")).thenReturn(spyCOSObject);

        // Run the test
        assertThrows(PurchaseOrderStoreException.class, () -> myClassUnderTest.getPurchaseOrder("orderId"));
        verify(spyCOSObject).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientThrowsCosClientException() {
        // Setup
        when(mockCosClient.getObject("bucketName", "key")).thenThrow(CosClientException.class);

        // Run the test
        assertThrows(PurchaseOrderStoreException.class, () -> myClassUnderTest.getPurchaseOrder("orderId"));
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientThrowsCosServiceException() {
        // Setup
        when(mockCosClient.getObject("bucketName", "key")).thenThrow(CosServiceException.class);

        // Run the test
        assertThrows(PurchaseOrderStoreException.class, () -> myClassUnderTest.getPurchaseOrder("orderId"));
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }
}
