package com.myapp;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderStoreTest {

    @Mock
    private COSClient mockCosClient;
    @Mock
    private MetricAdapter mockMetricAdapter;

    private MyClass purchaseOrderStoreUnderTest;

    @BeforeEach
    void setUp() {
        purchaseOrderStoreUnderTest = new MyClass(mockCosClient, mockMetricAdapter, "bucketName");
    }

    @Test
    void testGetPurchaseOrder() throws Exception {
        // Setup
        // Configure COSClient.getObject(...).
        final COSObject spyCOSObject = spy(new COSObject());
        spyCOSObject.setBucketName("bucketName");
        spyCOSObject.setKey("key");
        when(mockCosClient.getObject("bucketName", "key")).thenReturn(spyCOSObject);

        // Run the test
        final PDDocument result = purchaseOrderStoreUnderTest.getPurchaseOrder("orderId");

        // Verify the results
        verify(spyCOSObject).close();
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientThrowsCosClientException() throws Exception {
        // Setup
        when(mockCosClient.getObject("bucketName", "key")).thenThrow(CosClientException.class);

        // Run the test
        final PDDocument result = purchaseOrderStoreUnderTest.getPurchaseOrder("orderId");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }

    @Test
    void testGetPurchaseOrder_COSClientThrowsCosServiceException() throws Exception {
        // Setup
        when(mockCosClient.getObject("bucketName", "key")).thenThrow(CosServiceException.class);

        // Run the test
        final PDDocument result = purchaseOrderStoreUnderTest.getPurchaseOrder("orderId");

        // Verify the results
        verify(mockMetricAdapter).recordEvent(MetricEvent.Success);
    }
}
