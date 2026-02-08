package com.myapp;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.S3ResponseMetadata;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.analytics.AnalyticsConfiguration;
import com.amazonaws.services.s3.model.inventory.InventoryConfiguration;
import com.amazonaws.services.s3.model.metrics.MetricsConfiguration;
import com.amazonaws.services.s3.waiters.AmazonS3Waiters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonS3Client mockAmazonS3Client;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonS3Client);
    }

    @Test
    void testTrySetEndpoint() {
        // Setup
        // Run the test
        myClassUnderTest.trySetEndpoint();

        // Verify the results
        verify(mockAmazonS3Client).setEndpoint("endpoint");
    }

    @Test
    void testTrySetRegion() {
        // Setup
        // Run the test
        myClassUnderTest.trySetRegion();

        // Verify the results
        verify(mockAmazonS3Client).setRegion(new Region(null));
    }

    @Test
    void testTrySetS3ClientOptions() {
        // Setup
        // Run the test
        myClassUnderTest.trySetS3ClientOptions();

        // Verify the results
        verify(mockAmazonS3Client).setS3ClientOptions(any(S3ClientOptions.class));
    }

    @Test
    void testTryListNextBatchOfVersions() {
        // Setup
        // Configure AmazonS3Client.listNextBatchOfVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setCommonPrefixes(Arrays.asList("value"));
        versionListing.setBucketName("bucketName");
        versionListing.setPrefix("prefix");
        versionListing.setKeyMarker("keyMarker");
        versionListing.setVersionIdMarker("versionIdMarker");
        versionListing.setMaxKeys(0);
        versionListing.setDelimiter("delimiter");
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class))).thenReturn(versionListing);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfVersions_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class))).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfVersions_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNextBatchOfVersions());
    }

    @Test
    void testTryListNextBatchOfVersions_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListNextBatchOfVersions());
    }

    @Test
    void testTryListNextBatchOfVersions1() {
        // Setup
        // Configure AmazonS3Client.listNextBatchOfVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setCommonPrefixes(Arrays.asList("value"));
        versionListing.setBucketName("bucketName");
        versionListing.setPrefix("prefix");
        versionListing.setKeyMarker("keyMarker");
        versionListing.setVersionIdMarker("versionIdMarker");
        versionListing.setMaxKeys(0);
        versionListing.setDelimiter("delimiter");
        when(mockAmazonS3Client.listNextBatchOfVersions(any(ListNextBatchOfVersionsRequest.class)))
                .thenReturn(versionListing);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions1();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfVersions1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(ListNextBatchOfVersionsRequest.class)))
                .thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions1();

        // Verify the results
    }

    @Test
    void testTryListVersions() {
        // Setup
        // Configure AmazonS3Client.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setCommonPrefixes(Arrays.asList("value"));
        versionListing.setBucketName("bucketName");
        versionListing.setPrefix("prefix");
        versionListing.setKeyMarker("keyMarker");
        versionListing.setVersionIdMarker("versionIdMarker");
        versionListing.setMaxKeys(0);
        versionListing.setDelimiter("delimiter");
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenReturn(versionListing);

        // Run the test
        myClassUnderTest.tryListVersions();

        // Verify the results
    }

    @Test
    void testTryListVersions_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions();

        // Verify the results
    }

    @Test
    void testTryListVersions_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListVersions());
    }

    @Test
    void testTryListVersions_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListVersions());
    }

    @Test
    void testTryListVersions1() {
        // Setup
        // Configure AmazonS3Client.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setCommonPrefixes(Arrays.asList("value"));
        versionListing.setBucketName("bucketName");
        versionListing.setPrefix("prefix");
        versionListing.setKeyMarker("keyMarker");
        versionListing.setVersionIdMarker("versionIdMarker");
        versionListing.setMaxKeys(0);
        versionListing.setDelimiter("delimiter");
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenReturn(versionListing);

        // Run the test
        myClassUnderTest.tryListVersions1();

        // Verify the results
    }

    @Test
    void testTryListVersions1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions1();

        // Verify the results
    }

    @Test
    void testTryListVersions1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListVersions1());
    }

    @Test
    void testTryListVersions1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListVersions1());
    }

    @Test
    void testTryListVersions2() {
        // Setup
        // Configure AmazonS3Client.listVersions(...).
        final VersionListing versionListing = new VersionListing();
        final S3VersionSummary s3VersionSummary = new S3VersionSummary();
        versionListing.setVersionSummaries(Arrays.asList(s3VersionSummary));
        versionListing.setCommonPrefixes(Arrays.asList("value"));
        versionListing.setBucketName("bucketName");
        versionListing.setPrefix("prefix");
        versionListing.setKeyMarker("keyMarker");
        versionListing.setVersionIdMarker("versionIdMarker");
        versionListing.setMaxKeys(0);
        versionListing.setDelimiter("delimiter");
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(versionListing);

        // Run the test
        myClassUnderTest.tryListVersions2();

        // Verify the results
    }

    @Test
    void testTryListVersions2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions2();

        // Verify the results
    }

    @Test
    void testTryListVersions2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListVersions2());
    }

    @Test
    void testTryListVersions2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListVersions2());
    }

    @Test
    void testTryListObjects() {
        // Setup
        // Configure AmazonS3Client.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjects("bucketName")).thenReturn(objectListing);

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test
    void testTryListObjects_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test
    void testTryListObjects_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjects());
    }

    @Test
    void testTryListObjects_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjects());
    }

    @Test
    void testTryListObjects1() {
        // Setup
        // Configure AmazonS3Client.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenReturn(objectListing);

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test
    void testTryListObjects1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjects1());
    }

    @Test
    void testTryListObjects2() {
        // Setup
        // Configure AmazonS3Client.listObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(objectListing);

        // Run the test
        myClassUnderTest.tryListObjects2();

        // Verify the results
    }

    @Test
    void testTryListObjects2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects2();

        // Verify the results
    }

    @Test
    void testTryListObjects2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjects2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjects2());
    }

    @Test
    void testTryListObjectsV2() {
        // Setup
        // Configure AmazonS3Client.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenReturn(listObjectsV2Result);

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test
    void testTryListObjectsV2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV2());
    }

    @Test
    void testTryListObjectsV2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjectsV2());
    }

    @Test
    void testTryListObjectsV21() {
        // Setup
        // Configure AmazonS3Client.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenReturn(listObjectsV2Result);

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test
    void testTryListObjectsV21_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV21_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjectsV21());
    }

    @Test
    void testTryListObjectsV22() {
        // Setup
        // Configure AmazonS3Client.listObjectsV2(...).
        final ListObjectsV2Result listObjectsV2Result = new ListObjectsV2Result();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        listObjectsV2Result.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(listObjectsV2Result);

        // Run the test
        myClassUnderTest.tryListObjectsV22();

        // Verify the results
    }

    @Test
    void testTryListObjectsV22_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV22();

        // Verify the results
    }

    @Test
    void testTryListObjectsV22_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListObjectsV22_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListObjectsV22());
    }

    @Test
    void testTryListNextBatchOfObjects() {
        // Setup
        // Configure AmazonS3Client.listNextBatchOfObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class))).thenReturn(objectListing);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfObjects_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class))).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfObjects_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNextBatchOfObjects());
    }

    @Test
    void testTryListNextBatchOfObjects_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListNextBatchOfObjects());
    }

    @Test
    void testTryListNextBatchOfObjects1() {
        // Setup
        // Configure AmazonS3Client.listNextBatchOfObjects(...).
        final ObjectListing objectListing = new ObjectListing();
        final S3ObjectSummary s3ObjectSummary = new S3ObjectSummary();
        s3ObjectSummary.setBucketName("bucketName");
        s3ObjectSummary.setKey("key");
        objectListing.getObjectSummaries().addAll(Arrays.asList(s3ObjectSummary));
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenReturn(objectListing);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects1();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfObjects1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects1();

        // Verify the results
    }

    @Test
    void testTryListNextBatchOfObjects1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListNextBatchOfObjects1());
    }

    @Test
    void testTryListNextBatchOfObjects1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListNextBatchOfObjects1());
    }

    @Test
    void testTryGetS3AccountOwner() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenReturn(new Owner("id", "displayName"));

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner();

        // Verify the results
    }

    @Test
    void testTryGetS3AccountOwner_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetS3AccountOwner());
    }

    @Test
    void testTryGetS3AccountOwner_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetS3AccountOwner());
    }

    @Test
    void testTryGetS3AccountOwner1() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenReturn(new Owner("id", "displayName"));

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner1();

        // Verify the results
    }

    @Test
    void testTryGetS3AccountOwner1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetS3AccountOwner1());
    }

    @Test
    void testTryGetS3AccountOwner1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetS3AccountOwner1());
    }

    @Test
    void testTryListBuckets() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class)))
                .thenReturn(Arrays.asList(new Bucket("name")));

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test
    void testTryListBuckets_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test
    void testTryListBuckets_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBuckets());
    }

    @Test
    void testTryListBuckets_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBuckets());
    }

    @Test
    void testTryListBuckets1() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenReturn(Arrays.asList(new Bucket("name")));

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    void testTryListBuckets1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    void testTryListBuckets1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBuckets1());
    }

    @Test
    void testTryListBuckets1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBuckets1());
    }

    @Test
    void testTryGetBucketLocation() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class))).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetBucketLocation();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLocation());
    }

    @Test
    void testTryGetBucketLocation_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketLocation());
    }

    @Test
    void testTryGetBucketLocation1() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLocation1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLocation1());
    }

    @Test
    void testTryGetBucketLocation1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketLocation1());
    }

    @Test
    void testTryCreateBucket() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket();

        // Verify the results
    }

    @Test
    void testTryCreateBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket());
    }

    @Test
    void testTryCreateBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCreateBucket());
    }

    @Test
    void testTryCreateBucket1() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket1();

        // Verify the results
    }

    @Test
    void testTryCreateBucket1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCreateBucket1());
    }

    @Test
    void testTryCreateBucket2() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket2();

        // Verify the results
    }

    @Test
    void testTryCreateBucket2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCreateBucket2());
    }

    @Test
    void testTryCreateBucket3() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket3();

        // Verify the results
    }

    @Test
    void testTryCreateBucket3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCreateBucket3());
    }

    @Test
    void testTryCreateBucket3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCreateBucket3());
    }

    @Test
    void testTryGetObjectAcl() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAcl());
    }

    @Test
    void testTryGetObjectAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObjectAcl());
    }

    @Test
    void testTryGetObjectAcl1() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();

        // Verify the results
    }

    @Test
    void testTryGetObjectAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObjectAcl1());
    }

    @Test
    void testTryGetObjectAcl2() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl(any(GetObjectAclRequest.class))).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl2();

        // Verify the results
    }

    @Test
    void testTrySetObjectAcl() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);
    }

    @Test
    void testTrySetObjectAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl());
    }

    @Test
    void testTrySetObjectAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl());
    }

    @Test
    void testTrySetObjectAcl1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl1();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", CannedAccessControlList.Private);
    }

    @Test
    void testTrySetObjectAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl1());
    }

    @Test
    void testTrySetObjectAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl1());
    }

    @Test
    void testTrySetObjectAcl2() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl2();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", acl);
    }

    @Test
    void testTrySetObjectAcl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", acl);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl2());
    }

    @Test
    void testTrySetObjectAcl2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                acl);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl2());
    }

    @Test
    void testTrySetObjectAcl3() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl3();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"), eq("versionId"), eq(acl),
                any(RequestMetricCollector.class));
    }

    @Test
    void testTrySetObjectAcl3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"),
                eq("versionId"), eq(acl), any(RequestMetricCollector.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl3());
    }

    @Test
    void testTrySetObjectAcl3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"),
                eq("versionId"), eq(acl), any(RequestMetricCollector.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl3());
    }

    @Test
    void testTrySetObjectAcl4() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl4();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", CannedAccessControlList.Private);
    }

    @Test
    void testTrySetObjectAcl4_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl4());
    }

    @Test
    void testTrySetObjectAcl4_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl4());
    }

    @Test
    void testTrySetObjectAcl5() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl5();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"), eq("versionId"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));
    }

    @Test
    void testTrySetObjectAcl6() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl6();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));
    }

    @Test
    void testTrySetObjectAcl6_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectAcl6());
    }

    @Test
    void testTrySetObjectAcl6_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectAcl6());
    }

    @Test
    void testTryGetBucketAcl() {
        // Setup
        // Configure AmazonS3Client.getBucketAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAcl());
    }

    @Test
    void testTryGetBucketAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAcl());
    }

    @Test
    void testTryGetBucketAcl1() {
        // Setup
        // Configure AmazonS3Client.getBucketAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAcl1());
    }

    @Test
    void testTryGetBucketAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAcl1());
    }

    @Test
    void testTrySetBucketAcl() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl();

        // Verify the results
        // Confirm AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setBucketAcl("bucketName", acl);
    }

    @Test
    void testTrySetBucketAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl("bucketName", acl);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAcl());
    }

    @Test
    void testTrySetBucketAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl("bucketName", acl);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAcl());
    }

    @Test
    void testTrySetBucketAcl1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl1();

        // Verify the results
        // Confirm AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setBucketAcl(eq("bucketName"), eq(acl), any(RequestMetricCollector.class));
    }

    @Test
    void testTrySetBucketAcl2() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl2();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl("bucketName", CannedAccessControlList.Private);
    }

    @Test
    void testTrySetBucketAcl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl("bucketName",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAcl2());
    }

    @Test
    void testTrySetBucketAcl2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl("bucketName",
                CannedAccessControlList.Private);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAcl2());
    }

    @Test
    void testTrySetBucketAcl3() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl3();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl(eq("bucketName"), eq(CannedAccessControlList.Private),
                any(RequestMetricCollector.class));
    }

    @Test
    void testTrySetBucketAcl3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl(eq("bucketName"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAcl3());
    }

    @Test
    void testTrySetBucketAcl3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl(eq("bucketName"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAcl3());
    }

    @Test
    void testTrySetBucketAcl4() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl4();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));
    }

    @Test
    void testTrySetBucketAcl4_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAcl4());
    }

    @Test
    void testTrySetBucketAcl4_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAcl4());
    }

    @Test
    void testTryGetObjectMetadata() {
        // Setup
        // Configure AmazonS3Client.getObjectMetadata(...).
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenReturn(objectMetadata);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata();

        // Verify the results
    }

    @Test
    void testTryGetObjectMetadata_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectMetadata());
    }

    @Test
    void testTryGetObjectMetadata_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObjectMetadata());
    }

    @Test
    void testTryGetObjectMetadata1() {
        // Setup
        // Configure AmazonS3Client.getObjectMetadata(...).
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class))).thenReturn(objectMetadata);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata1();

        // Verify the results
    }

    @Test
    void testTryGetObjectMetadata1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectMetadata1());
    }

    @Test
    void testTryGetObjectMetadata1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObjectMetadata1());
    }

    @Test
    void testTryGetObject() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject_AmazonS3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject_AmazonS3ClientReturnsBrokenIo() throws Exception {
        // Setup
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
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject());
    }

    @Test
    void testTryGetObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObject());
    }

    @Test
    void testTryDoesBucketExist() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesBucketExist();

        // Verify the results
    }

    @Test
    void testTryDoesBucketExist_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDoesBucketExist());
    }

    @Test
    void testTryDoesBucketExist_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDoesBucketExist());
    }

    @Test
    void testTryDoesBucketExistV2() {
        // Setup
        when(mockAmazonS3Client.doesBucketExistV2("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesBucketExistV2();

        // Verify the results
    }

    @Test
    void testTryDoesBucketExistV2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExistV2("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDoesBucketExistV2());
    }

    @Test
    void testTryDoesObjectExist() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesObjectExist();

        // Verify the results
    }

    @Test
    void testTryDoesObjectExist_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDoesObjectExist());
    }

    @Test
    void testTryDoesObjectExist_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDoesObjectExist());
    }

    @Test
    void testTryHeadBucket() {
        // Setup
        // Configure AmazonS3Client.headBucket(...).
        final HeadBucketResult headBucketResult = new HeadBucketResult();
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName"))).thenReturn(headBucketResult);

        // Run the test
        myClassUnderTest.tryHeadBucket();

        // Verify the results
    }

    @Test
    void testTryHeadBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryHeadBucket());
    }

    @Test
    void testTryHeadBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryHeadBucket());
    }

    @Test
    void testTryChangeObjectStorageClass() {
        // Setup
        // Run the test
        myClassUnderTest.tryChangeObjectStorageClass();

        // Verify the results
        verify(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key", StorageClass.Standard);
    }

    @Test
    void testTryChangeObjectStorageClass_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key",
                StorageClass.Standard);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryChangeObjectStorageClass());
    }

    @Test
    void testTryChangeObjectStorageClass_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key",
                StorageClass.Standard);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryChangeObjectStorageClass());
    }

    @Test
    void testTrySetObjectRedirectLocation() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectRedirectLocation();

        // Verify the results
        verify(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key", "newRedirectLocation");
    }

    @Test
    void testTrySetObjectRedirectLocation_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key",
                "newRedirectLocation");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetObjectRedirectLocation());
    }

    @Test
    void testTrySetObjectRedirectLocation_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key",
                "newRedirectLocation");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetObjectRedirectLocation());
    }

    @Test
    void testTryGetObject1() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("content".getBytes()));
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
    }

    @Test
    void testTryGetObject1_AmazonS3ClientReturnsNoContent() throws Exception {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream(new byte[]{}));
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject1_AmazonS3ClientReturnsBrokenIo() throws Exception {
        // Setup
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
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(spyS3Object);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryGetObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key")))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject1());
    }

    @Test
    void testTryGetObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObject1());
    }

    @Test
    void testTryGetObject2() {
        // Setup
        // Configure AmazonS3Client.getObject(...).
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenReturn(objectMetadata);

        // Run the test
        myClassUnderTest.tryGetObject2();

        // Verify the results
    }

    @Test
    void testTryGetObject2_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetObject2();

        // Verify the results
    }

    @Test
    void testTryGetObject2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObject2());
    }

    @Test
    void testTryGetObject2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObject2());
    }

    @Test
    void testTryGetObjectAsString() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetObjectAsString();

        // Verify the results
    }

    @Test
    void testTryGetObjectAsString_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetObjectAsString());
    }

    @Test
    void testTryGetObjectAsString_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetObjectAsString());
    }

    @Test
    void testTryGetObjectTagging() {
        // Setup
        // Configure AmazonS3Client.getObjectTagging(...).
        final GetObjectTaggingResult getObjectTaggingResult = new GetObjectTaggingResult(
                Arrays.asList(new Tag("key", "value")));
        when(mockAmazonS3Client.getObjectTagging(any(GetObjectTaggingRequest.class)))
                .thenReturn(getObjectTaggingResult);

        // Run the test
        myClassUnderTest.tryGetObjectTagging();

        // Verify the results
    }

    @Test
    void testTrySetObjectTagging() {
        // Setup
        // Configure AmazonS3Client.setObjectTagging(...).
        final SetObjectTaggingResult setObjectTaggingResult = new SetObjectTaggingResult();
        when(mockAmazonS3Client.setObjectTagging(any(SetObjectTaggingRequest.class)))
                .thenReturn(setObjectTaggingResult);

        // Run the test
        myClassUnderTest.trySetObjectTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteObjectTagging() {
        // Setup
        // Configure AmazonS3Client.deleteObjectTagging(...).
        final DeleteObjectTaggingResult deleteObjectTaggingResult = new DeleteObjectTaggingResult();
        when(mockAmazonS3Client.deleteObjectTagging(any(DeleteObjectTaggingRequest.class)))
                .thenReturn(deleteObjectTaggingResult);

        // Run the test
        myClassUnderTest.tryDeleteObjectTagging();

        // Verify the results
    }

    @Test
    void testTryDeleteBucket() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucket();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucket("bucketName");
    }

    @Test
    void testTryDeleteBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucket("bucketName");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucket());
    }

    @Test
    void testTryDeleteBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucket("bucketName");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucket());
    }

    @Test
    void testTryDeleteBucket1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucket1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));
    }

    @Test
    void testTryDeleteBucket1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucket1());
    }

    @Test
    void testTryDeleteBucket1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucket1());
    }

    @Test
    void testTryPutObject() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt"))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject();

        // Verify the results
    }

    @Test
    void testTryPutObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt")))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject());
    }

    @Test
    void testTryPutObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryPutObject());
    }

    @Test
    void testTryPutObject1() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject1();

        // Verify the results
    }

    @Test
    void testTryPutObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject1());
    }

    @Test
    void testTryPutObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryPutObject1());
    }

    @Test
    void testTryPutObject2() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject2();

        // Verify the results
    }

    @Test
    void testTryPutObject2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject2());
    }

    @Test
    void testTryPutObject2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryPutObject2());
    }

    @Test
    void testTryCopyObject() {
        // Setup
        // Configure AmazonS3Client.copyObject(...).
        final CopyObjectResult copyObjectResult = new CopyObjectResult();
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenReturn(copyObjectResult);

        // Run the test
        myClassUnderTest.tryCopyObject();

        // Verify the results
    }

    @Test
    void testTryCopyObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCopyObject());
    }

    @Test
    void testTryCopyObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCopyObject());
    }

    @Test
    void testTryCopyObject1() {
        // Setup
        // Configure AmazonS3Client.copyObject(...).
        final CopyObjectResult copyObjectResult = new CopyObjectResult();
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenReturn(copyObjectResult);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    void testTryCopyObject1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    void testTryCopyObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCopyObject1());
    }

    @Test
    void testTryCopyPart() {
        // Setup
        // Configure AmazonS3Client.copyPart(...).
        final CopyPartResult copyPartResult = new CopyPartResult();
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenReturn(copyPartResult);

        // Run the test
        myClassUnderTest.tryCopyPart();

        // Verify the results
    }

    @Test
    void testTryCopyPart_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryCopyPart();

        // Verify the results
    }

    @Test
    void testTryDeleteObject() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteObject();

        // Verify the results
        verify(mockAmazonS3Client).deleteObject("bucketName", "key");
    }

    @Test
    void testTryDeleteObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteObject("bucketName", "key");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObject());
    }

    @Test
    void testTryDeleteObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteObject("bucketName", "key");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteObject());
    }

    @Test
    void testTryDeleteObject1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteObject1();

        // Verify the results
        verify(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));
    }

    @Test
    void testTryDeleteObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteObject1());
    }

    @Test
    void testTryDeleteObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteObject1());
    }

    @Test
    void testTryDeleteObjects() {
        // Setup
        // Configure AmazonS3Client.deleteObjects(...).
        final DeleteObjectsResult.DeletedObject deletedObject = new DeleteObjectsResult.DeletedObject();
        final DeleteObjectsResult deleteObjectsResult = new DeleteObjectsResult(Arrays.asList(deletedObject), false);
        when(mockAmazonS3Client.deleteObjects(any(DeleteObjectsRequest.class))).thenReturn(deleteObjectsResult);

        // Run the test
        myClassUnderTest.tryDeleteObjects();

        // Verify the results
    }

    @Test
    void testTryDeleteVersion() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteVersion();

        // Verify the results
        verify(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");
    }

    @Test
    void testTryDeleteVersion_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteVersion());
    }

    @Test
    void testTryDeleteVersion_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteVersion());
    }

    @Test
    void testTryDeleteVersion1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteVersion1();

        // Verify the results
        verify(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));
    }

    @Test
    void testTryDeleteVersion1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteVersion1());
    }

    @Test
    void testTryDeleteVersion1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteVersion1());
    }

    @Test
    void testTrySetBucketVersioningConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketVersioningConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketVersioningConfiguration(any(SetBucketVersioningConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketVersioningConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketVersioningConfiguration(
                any(SetBucketVersioningConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketVersioningConfiguration());
    }

    @Test
    void testTrySetBucketVersioningConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketVersioningConfiguration(
                any(SetBucketVersioningConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketVersioningConfiguration());
    }

    @Test
    void testTryGetBucketVersioningConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketVersioningConfiguration(...).
        final BucketVersioningConfiguration bucketVersioningConfiguration = new BucketVersioningConfiguration("status");
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName"))
                .thenReturn(bucketVersioningConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioningConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketVersioningConfiguration());
    }

    @Test
    void testTryGetBucketVersioningConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketVersioningConfiguration());
    }

    @Test
    void testTryGetBucketVersioningConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketVersioningConfiguration(...).
        final BucketVersioningConfiguration bucketVersioningConfiguration = new BucketVersioningConfiguration("status");
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenReturn(bucketVersioningConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketVersioningConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketVersioningConfiguration1());
    }

    @Test
    void testTryGetBucketVersioningConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketVersioningConfiguration1());
    }

    @Test
    void testTryGetBucketWebsiteConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketWebsiteConfiguration(...).
        final BucketWebsiteConfiguration bucketWebsiteConfiguration = new BucketWebsiteConfiguration(
                "indexDocumentSuffix", "errorDocument");
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenReturn(bucketWebsiteConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsiteConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketWebsiteConfiguration());
    }

    @Test
    void testTryGetBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketWebsiteConfiguration());
    }

    @Test
    void testTryGetBucketWebsiteConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketWebsiteConfiguration(...).
        final BucketWebsiteConfiguration bucketWebsiteConfiguration = new BucketWebsiteConfiguration(
                "indexDocumentSuffix", "errorDocument");
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenReturn(bucketWebsiteConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketWebsiteConfiguration1());
    }

    @Test
    void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketWebsiteConfiguration1());
    }

    @Test
    void testTryGetBucketLifecycleConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketLifecycleConfiguration(...).
        final BucketLifecycleConfiguration.Rule rule = new BucketLifecycleConfiguration.Rule();
        final BucketLifecycleConfiguration bucketLifecycleConfiguration = new BucketLifecycleConfiguration(
                Arrays.asList(rule));
        when(mockAmazonS3Client.getBucketLifecycleConfiguration("bucketName")).thenReturn(bucketLifecycleConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketLifecycleConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketLifecycleConfiguration(...).
        final BucketLifecycleConfiguration.Rule rule = new BucketLifecycleConfiguration.Rule();
        final BucketLifecycleConfiguration bucketLifecycleConfiguration = new BucketLifecycleConfiguration(
                Arrays.asList(rule));
        when(mockAmazonS3Client.getBucketLifecycleConfiguration(
                any(GetBucketLifecycleConfigurationRequest.class))).thenReturn(bucketLifecycleConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLifecycleConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketLifecycleConfiguration(
                any(GetBucketLifecycleConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketLifecycleConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLifecycleConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLifecycleConfiguration(eq("bucketName"),
                any(BucketLifecycleConfiguration.class));
    }

    @Test
    void testTrySetBucketLifecycleConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLifecycleConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLifecycleConfiguration(any(SetBucketLifecycleConfigurationRequest.class));
    }

    @Test
    void testTryDeleteBucketLifecycleConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycleConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketLifecycleConfiguration("bucketName");
    }

    @Test
    void testTryDeleteBucketLifecycleConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycleConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketLifecycleConfiguration(
                any(DeleteBucketLifecycleConfigurationRequest.class));
    }

    @Test
    void testTryGetBucketCrossOriginConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketCrossOriginConfiguration(...).
        final CORSRule corsRule = new CORSRule();
        final BucketCrossOriginConfiguration bucketCrossOriginConfiguration = new BucketCrossOriginConfiguration(
                Arrays.asList(corsRule));
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration("bucketName"))
                .thenReturn(bucketCrossOriginConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketCrossOriginConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketCrossOriginConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketCrossOriginConfiguration(...).
        final CORSRule corsRule = new CORSRule();
        final BucketCrossOriginConfiguration bucketCrossOriginConfiguration = new BucketCrossOriginConfiguration(
                Arrays.asList(corsRule));
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration(
                any(GetBucketCrossOriginConfigurationRequest.class))).thenReturn(bucketCrossOriginConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketCrossOriginConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration(
                any(GetBucketCrossOriginConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketCrossOriginConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketCrossOriginConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketCrossOriginConfiguration(eq("bucketName"),
                any(BucketCrossOriginConfiguration.class));
    }

    @Test
    void testTrySetBucketCrossOriginConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketCrossOriginConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketCrossOriginConfiguration(
                any(SetBucketCrossOriginConfigurationRequest.class));
    }

    @Test
    void testTryDeleteBucketCrossOriginConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketCrossOriginConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketCrossOriginConfiguration("bucketName");
    }

    @Test
    void testTryDeleteBucketCrossOriginConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketCrossOriginConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketCrossOriginConfiguration(
                any(DeleteBucketCrossOriginConfigurationRequest.class));
    }

    @Test
    void testTryGetBucketTaggingConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketTaggingConfiguration(...).
        final BucketTaggingConfiguration bucketTaggingConfiguration = new BucketTaggingConfiguration(
                Arrays.asList(new TagSet(new HashMap<>())));
        when(mockAmazonS3Client.getBucketTaggingConfiguration("bucketName")).thenReturn(bucketTaggingConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketTaggingConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketTaggingConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketTaggingConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketTaggingConfiguration(...).
        final BucketTaggingConfiguration bucketTaggingConfiguration = new BucketTaggingConfiguration(
                Arrays.asList(new TagSet(new HashMap<>())));
        when(mockAmazonS3Client.getBucketTaggingConfiguration(
                any(GetBucketTaggingConfigurationRequest.class))).thenReturn(bucketTaggingConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketTaggingConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketTaggingConfiguration(
                any(GetBucketTaggingConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketTaggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketTaggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketTaggingConfiguration(eq("bucketName"),
                any(BucketTaggingConfiguration.class));
    }

    @Test
    void testTrySetBucketTaggingConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketTaggingConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketTaggingConfiguration(any(SetBucketTaggingConfigurationRequest.class));
    }

    @Test
    void testTryDeleteBucketTaggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketTaggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketTaggingConfiguration("bucketName");
    }

    @Test
    void testTryDeleteBucketTaggingConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketTaggingConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketTaggingConfiguration(any(DeleteBucketTaggingConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketWebsiteConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));
    }

    @Test
    void testTrySetBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketWebsiteConfiguration());
    }

    @Test
    void testTrySetBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketWebsiteConfiguration());
    }

    @Test
    void testTrySetBucketWebsiteConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketWebsiteConfiguration(any(SetBucketWebsiteConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(
                any(SetBucketWebsiteConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketWebsiteConfiguration1());
    }

    @Test
    void testTrySetBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(
                any(SetBucketWebsiteConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketWebsiteConfiguration1());
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketWebsiteConfiguration());
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketWebsiteConfiguration());
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketWebsiteConfiguration(any(DeleteBucketWebsiteConfigurationRequest.class));
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration(
                any(DeleteBucketWebsiteConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketWebsiteConfiguration1());
    }

    @Test
    void testTryDeleteBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration(
                any(DeleteBucketWebsiteConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketWebsiteConfiguration1());
    }

    @Test
    void testTrySetBucketNotificationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketNotificationConfiguration(eq("bucketName"),
                any(BucketNotificationConfiguration.class));
    }

    @Test
    void testTrySetBucketNotificationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(eq("bucketName"),
                any(BucketNotificationConfiguration.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketNotificationConfiguration());
    }

    @Test
    void testTrySetBucketNotificationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                eq("bucketName"), any(BucketNotificationConfiguration.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketNotificationConfiguration());
    }

    @Test
    void testTrySetBucketNotificationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketNotificationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketNotificationConfiguration1());
    }

    @Test
    void testTrySetBucketNotificationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketNotificationConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketNotificationConfiguration(...).
        final BucketNotificationConfiguration bucketNotificationConfiguration = new BucketNotificationConfiguration(
                "name", null);
        when(mockAmazonS3Client.getBucketNotificationConfiguration("bucketName"))
                .thenReturn(bucketNotificationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration());
    }

    @Test
    void testTryGetBucketNotificationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration("bucketName"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration());
    }

    @Test
    void testTryGetBucketNotificationConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketNotificationConfiguration(...).
        final BucketNotificationConfiguration bucketNotificationConfiguration = new BucketNotificationConfiguration(
                "name", null);
        when(mockAmazonS3Client.getBucketNotificationConfiguration(
                any(GetBucketNotificationConfigurationRequest.class))).thenReturn(bucketNotificationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration(
                any(GetBucketNotificationConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketNotificationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration(
                any(GetBucketNotificationConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketNotificationConfiguration1());
    }

    @Test
    void testTryGetBucketLoggingConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketLoggingConfiguration(...).
        final BucketLoggingConfiguration bucketLoggingConfiguration = new BucketLoggingConfiguration(
                "destinationBucketName", "logFilePrefix");
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenReturn(bucketLoggingConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketLoggingConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLoggingConfiguration());
    }

    @Test
    void testTryGetBucketLoggingConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketLoggingConfiguration());
    }

    @Test
    void testTryGetBucketLoggingConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketLoggingConfiguration(...).
        final BucketLoggingConfiguration bucketLoggingConfiguration = new BucketLoggingConfiguration(
                "destinationBucketName", "logFilePrefix");
        when(mockAmazonS3Client.getBucketLoggingConfiguration(
                any(GetBucketLoggingConfigurationRequest.class))).thenReturn(bucketLoggingConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketLoggingConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration(
                any(GetBucketLoggingConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketLoggingConfiguration1());
    }

    @Test
    void testTryGetBucketLoggingConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration(
                any(GetBucketLoggingConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketLoggingConfiguration1());
    }

    @Test
    void testTrySetBucketLoggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLoggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLoggingConfiguration(any(SetBucketLoggingConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketLoggingConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketLoggingConfiguration(
                any(SetBucketLoggingConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketLoggingConfiguration());
    }

    @Test
    void testTrySetBucketLoggingConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketLoggingConfiguration(
                any(SetBucketLoggingConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketLoggingConfiguration());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketAccelerateConfiguration(...).
        final BucketAccelerateConfiguration bucketAccelerateConfiguration = new BucketAccelerateConfiguration("status");
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName"))
                .thenReturn(bucketAccelerateConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketAccelerateConfiguration(...).
        final BucketAccelerateConfiguration bucketAccelerateConfiguration = new BucketAccelerateConfiguration("status");
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenReturn(bucketAccelerateConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration1());
    }

    @Test
    void testTryGetBucketAccelerateConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAccelerateConfiguration1());
    }

    @Test
    void testTrySetBucketAccelerateConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAccelerateConfiguration(eq("bucketName"),
                any(BucketAccelerateConfiguration.class));
    }

    @Test
    void testTrySetBucketAccelerateConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                eq("bucketName"), any(BucketAccelerateConfiguration.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAccelerateConfiguration());
    }

    @Test
    void testTrySetBucketAccelerateConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(eq("bucketName"),
                any(BucketAccelerateConfiguration.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAccelerateConfiguration());
    }

    @Test
    void testTrySetBucketAccelerateConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAccelerateConfiguration(any(SetBucketAccelerateConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketAccelerateConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                any(SetBucketAccelerateConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAccelerateConfiguration1());
    }

    @Test
    void testTrySetBucketAccelerateConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                any(SetBucketAccelerateConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAccelerateConfiguration1());
    }

    @Test
    void testTryGetBucketPolicy() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicy(...).
        final BucketPolicy bucketPolicy = new BucketPolicy();
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenReturn(bucketPolicy);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicy());
    }

    @Test
    void testTryGetBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketPolicy());
    }

    @Test
    void testTryDeleteBucketPolicy() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketPolicy("bucketName");
    }

    @Test
    void testTryDeleteBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketPolicy("bucketName");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketPolicy());
    }

    @Test
    void testTryDeleteBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketPolicy("bucketName");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketPolicy());
    }

    @Test
    void testTryGetBucketPolicy1() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicy(...).
        final BucketPolicy bucketPolicy = new BucketPolicy();
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class))).thenReturn(bucketPolicy);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketPolicy1());
    }

    @Test
    void testTryGetBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketPolicy1());
    }

    @Test
    void testTrySetBucketPolicy() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketPolicy();

        // Verify the results
        verify(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");
    }

    @Test
    void testTrySetBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketPolicy());
    }

    @Test
    void testTrySetBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketPolicy());
    }

    @Test
    void testTrySetBucketPolicy1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketPolicy1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketPolicy(any(SetBucketPolicyRequest.class));
    }

    @Test
    void testTrySetBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketPolicy(any(SetBucketPolicyRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketPolicy1());
    }

    @Test
    void testTrySetBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketPolicy(
                any(SetBucketPolicyRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketPolicy1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketPolicy(any(DeleteBucketPolicyRequest.class));
    }

    @Test
    void testTryDeleteBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketPolicy(
                any(DeleteBucketPolicyRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketPolicy(
                any(DeleteBucketPolicyRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketPolicy1());
    }

    @Test
    void testTryDeleteBucketEncryption() {
        // Setup
        when(mockAmazonS3Client.deleteBucketEncryption("bucketName")).thenReturn(new DeleteBucketEncryptionResult());

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketEncryption("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketEncryption());
    }

    @Test
    void testTryDeleteBucketEncryption1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = new DeleteBucketEncryptionRequest();
        when(mockAmazonS3Client.deleteBucketEncryption(deleteBucketEncryptionRequest))
                .thenReturn(new DeleteBucketEncryptionResult());

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketEncryption1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = new DeleteBucketEncryptionRequest();
        when(mockAmazonS3Client.deleteBucketEncryption(deleteBucketEncryptionRequest))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketEncryption1());
    }

    @Test
    void testTryGetBucketEncryption() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionResult getBucketEncryptionResult = new GetBucketEncryptionResult();
        when(mockAmazonS3Client.getBucketEncryption("bucketName")).thenReturn(getBucketEncryptionResult);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketEncryption("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketEncryption());
    }

    @Test
    void testTryGetBucketEncryption1() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionResult getBucketEncryptionResult = new GetBucketEncryptionResult();
        final GetBucketEncryptionRequest getBucketEncryptionRequest = new GetBucketEncryptionRequest();
        when(mockAmazonS3Client.getBucketEncryption(getBucketEncryptionRequest)).thenReturn(getBucketEncryptionResult);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();

        // Verify the results
    }

    @Test
    void testTryGetBucketEncryption1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionRequest getBucketEncryptionRequest = new GetBucketEncryptionRequest();
        when(mockAmazonS3Client.getBucketEncryption(getBucketEncryptionRequest)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketEncryption1());
    }

    @Test
    void testTrySetBucketEncryption() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest))
                .thenReturn(new SetBucketEncryptionResult());

        // Run the test
        myClassUnderTest.trySetBucketEncryption();

        // Verify the results
    }

    @Test
    void testTrySetBucketEncryption_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketEncryption());
    }

    @Test
    void testTrySetBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest)).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketEncryption());
    }

    @Test
    void testTrySetPublicAccessBlock() {
        // Setup
        // Configure AmazonS3Client.setPublicAccessBlock(...).
        final SetPublicAccessBlockRequest setPublicAccessBlockRequest = new SetPublicAccessBlockRequest();
        when(mockAmazonS3Client.setPublicAccessBlock(setPublicAccessBlockRequest))
                .thenReturn(new SetPublicAccessBlockResult());

        // Run the test
        myClassUnderTest.trySetPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryGetPublicAccessBlock() {
        // Setup
        // Configure AmazonS3Client.getPublicAccessBlock(...).
        final GetPublicAccessBlockResult getPublicAccessBlockResult = new GetPublicAccessBlockResult();
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = new GetPublicAccessBlockRequest();
        when(mockAmazonS3Client.getPublicAccessBlock(getPublicAccessBlockRequest))
                .thenReturn(getPublicAccessBlockResult);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryDeletePublicAccessBlock() {
        // Setup
        // Configure AmazonS3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = new DeletePublicAccessBlockRequest();
        when(mockAmazonS3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest))
                .thenReturn(new DeletePublicAccessBlockResult());

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();

        // Verify the results
    }

    @Test
    void testTryGetBucketPolicyStatus() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicyStatus(...).
        final GetBucketPolicyStatusResult getBucketPolicyStatusResult = new GetBucketPolicyStatusResult();
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = new GetBucketPolicyStatusRequest();
        when(mockAmazonS3Client.getBucketPolicyStatus(getBucketPolicyStatusRequest))
                .thenReturn(getBucketPolicyStatusResult);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus();

        // Verify the results
    }

    @Test
    void testTrySelectObjectContent() throws Exception {
        // Setup
        // Configure AmazonS3Client.selectObjectContent(...).
        final SelectObjectContentResult spySelectObjectContentResult = spy(new SelectObjectContentResult());
        when(mockAmazonS3Client.selectObjectContent(any(SelectObjectContentRequest.class)))
                .thenReturn(spySelectObjectContentResult);

        // Run the test
        myClassUnderTest.trySelectObjectContent();

        // Verify the results
        verify(spySelectObjectContentResult).close();
    }

    @Test
    void testTrySelectObjectContent_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.selectObjectContent(any(SelectObjectContentRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySelectObjectContent());
    }

    @Test
    void testTrySelectObjectContent_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.selectObjectContent(any(SelectObjectContentRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySelectObjectContent());
    }

    @Test
    void testTrySetObjectLegalHold() {
        // Setup
        // Configure AmazonS3Client.setObjectLegalHold(...).
        final SetObjectLegalHoldResult setObjectLegalHoldResult = new SetObjectLegalHoldResult();
        when(mockAmazonS3Client.setObjectLegalHold(any(SetObjectLegalHoldRequest.class)))
                .thenReturn(setObjectLegalHoldResult);

        // Run the test
        myClassUnderTest.trySetObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTryGetObjectLegalHold() {
        // Setup
        // Configure AmazonS3Client.getObjectLegalHold(...).
        final GetObjectLegalHoldResult getObjectLegalHoldResult = new GetObjectLegalHoldResult();
        when(mockAmazonS3Client.getObjectLegalHold(any(GetObjectLegalHoldRequest.class)))
                .thenReturn(getObjectLegalHoldResult);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();

        // Verify the results
    }

    @Test
    void testTrySetObjectLockConfiguration() {
        // Setup
        // Configure AmazonS3Client.setObjectLockConfiguration(...).
        final SetObjectLockConfigurationResult setObjectLockConfigurationResult = new SetObjectLockConfigurationResult();
        when(mockAmazonS3Client.setObjectLockConfiguration(any(SetObjectLockConfigurationRequest.class)))
                .thenReturn(setObjectLockConfigurationResult);

        // Run the test
        myClassUnderTest.trySetObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetObjectLockConfiguration() {
        // Setup
        // Configure AmazonS3Client.getObjectLockConfiguration(...).
        final GetObjectLockConfigurationResult getObjectLockConfigurationResult = new GetObjectLockConfigurationResult();
        when(mockAmazonS3Client.getObjectLockConfiguration(any(GetObjectLockConfigurationRequest.class)))
                .thenReturn(getObjectLockConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();

        // Verify the results
    }

    @Test
    void testTrySetObjectRetention() {
        // Setup
        // Configure AmazonS3Client.setObjectRetention(...).
        final SetObjectRetentionResult setObjectRetentionResult = new SetObjectRetentionResult();
        when(mockAmazonS3Client.setObjectRetention(any(SetObjectRetentionRequest.class)))
                .thenReturn(setObjectRetentionResult);

        // Run the test
        myClassUnderTest.trySetObjectRetention();

        // Verify the results
    }

    @Test
    void testTryGetObjectRetention() {
        // Setup
        // Configure AmazonS3Client.getObjectRetention(...).
        final GetObjectRetentionResult getObjectRetentionResult = new GetObjectRetentionResult();
        when(mockAmazonS3Client.getObjectRetention(any(GetObjectRetentionRequest.class)))
                .thenReturn(getObjectRetentionResult);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();

        // Verify the results
    }

    @Test
    void testTryGeneratePresignedUrl() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl();

        // Verify the results
    }

    @Test
    void testTryGeneratePresignedUrl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGeneratePresignedUrl());
    }

    @Test
    void testTryGeneratePresignedUrl1() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), HttpMethod.GET))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl1();

        // Verify the results
    }

    @Test
    void testTryGeneratePresignedUrl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), HttpMethod.GET))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGeneratePresignedUrl1());
    }

    @Test
    void testTryGeneratePresignedUrl2() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl2();

        // Verify the results
    }

    @Test
    void testTryAbortMultipartUpload() {
        // Setup
        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();

        // Verify the results
        verify(mockAmazonS3Client).abortMultipartUpload(any(AbortMultipartUploadRequest.class));
    }

    @Test
    void testTryAbortMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).abortMultipartUpload(
                any(AbortMultipartUploadRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryAbortMultipartUpload());
    }

    @Test
    void testTryAbortMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).abortMultipartUpload(
                any(AbortMultipartUploadRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryAbortMultipartUpload());
    }

    @Test
    void testTryCompleteMultipartUpload() {
        // Setup
        // Configure AmazonS3Client.completeMultipartUpload(...).
        final CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenReturn(completeMultipartUploadResult);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryCompleteMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryCompleteMultipartUpload());
    }

    @Test
    void testTryCompleteMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryCompleteMultipartUpload());
    }

    @Test
    void testTryInitiateMultipartUpload() {
        // Setup
        // Configure AmazonS3Client.initiateMultipartUpload(...).
        final InitiateMultipartUploadResult initiateMultipartUploadResult = new InitiateMultipartUploadResult();
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenReturn(initiateMultipartUploadResult);

        // Run the test
        myClassUnderTest.tryInitiateMultipartUpload();

        // Verify the results
    }

    @Test
    void testTryInitiateMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryInitiateMultipartUpload());
    }

    @Test
    void testTryInitiateMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryInitiateMultipartUpload());
    }

    @Test
    void testTryListMultipartUploads() {
        // Setup
        // Configure AmazonS3Client.listMultipartUploads(...).
        final MultipartUploadListing multipartUploadListing = new MultipartUploadListing();
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenReturn(multipartUploadListing);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();

        // Verify the results
    }

    @Test
    void testTryListMultipartUploads_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListMultipartUploads());
    }

    @Test
    void testTryListMultipartUploads_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListMultipartUploads());
    }

    @Test
    void testTryListParts() {
        // Setup
        // Configure AmazonS3Client.listParts(...).
        final PartListing partListing = new PartListing();
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenReturn(partListing);

        // Run the test
        myClassUnderTest.tryListParts();

        // Verify the results
    }

    @Test
    void testTryListParts_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListParts());
    }

    @Test
    void testTryListParts_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListParts());
    }

    @Test
    void testTryUploadPart() {
        // Setup
        // Configure AmazonS3Client.uploadPart(...).
        final UploadPartResult uploadPartResult = new UploadPartResult();
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenReturn(uploadPartResult);

        // Run the test
        myClassUnderTest.tryUploadPart();

        // Verify the results
    }

    @Test
    void testTryUploadPart_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryUploadPart());
    }

    @Test
    void testTryUploadPart_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryUploadPart());
    }

    @Test
    void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonS3Client.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(new S3ResponseMetadata(new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryGetCachedResponseMetadata_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    void testTryRestoreObject() {
        // Setup
        // Run the test
        myClassUnderTest.tryRestoreObject();

        // Verify the results
        verify(mockAmazonS3Client).restoreObject(new RestoreObjectRequest("bucketName", "key", 0));
    }

    @Test
    void testTryRestoreObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).restoreObject(
                new RestoreObjectRequest("bucketName", "key", 0));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryRestoreObject());
    }

    @Test
    void testTryRestoreObjectV2() {
        // Setup
        // Configure AmazonS3Client.restoreObjectV2(...).
        final RestoreObjectResult restoreObjectResult = new RestoreObjectResult();
        when(mockAmazonS3Client.restoreObjectV2(new RestoreObjectRequest("bucketName", "key", 0)))
                .thenReturn(restoreObjectResult);

        // Run the test
        myClassUnderTest.tryRestoreObjectV2();

        // Verify the results
    }

    @Test
    void testTryRestoreObjectV2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.restoreObjectV2(new RestoreObjectRequest("bucketName", "key", 0)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryRestoreObjectV2());
    }

    @Test
    void testTryRestoreObject1() {
        // Setup
        // Run the test
        myClassUnderTest.tryRestoreObject1();

        // Verify the results
        verify(mockAmazonS3Client).restoreObject("bucketName", "key", 0);
    }

    @Test
    void testTryRestoreObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).restoreObject("bucketName", "key", 0);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryRestoreObject1());
    }

    @Test
    void testTryPutObject3() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject3();

        // Verify the results
    }

    @Test
    void testTryPutObject3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryPutObject3());
    }

    @Test
    void testTryPutObject3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryPutObject3());
    }

    @Test
    void testTryGetResourceUrl() {
        // Setup
        when(mockAmazonS3Client.getResourceUrl("bucketName", "key")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetResourceUrl();

        // Verify the results
    }

    @Test
    void testTryGetResourceUrl_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getResourceUrl("bucketName", "key")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetResourceUrl();

        // Verify the results
    }

    @Test
    void testTryGetUrl() throws Exception {
        // Setup
        when(mockAmazonS3Client.getUrl("bucketName", "key")).thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGetUrl();

        // Verify the results
    }

    @Test
    void testTryGetRegion() {
        // Setup
        when(mockAmazonS3Client.getRegion()).thenReturn(com.amazonaws.services.s3.model.Region.US_Standard);

        // Run the test
        myClassUnderTest.tryGetRegion();

        // Verify the results
    }

    @Test
    void testTryGetRegionName() {
        // Setup
        when(mockAmazonS3Client.getRegionName()).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetRegionName();

        // Verify the results
    }

    @Test
    void testTryDownload() throws Exception {
        // Setup
        // Configure AmazonS3Client.download(...).
        final PresignedUrlDownloadResult presignedUrlDownloadResult = new PresignedUrlDownloadResult();
        final S3Object spyS3Object = spy(new S3Object());
        spyS3Object.setBucketName("bucketName");
        spyS3Object.setKey("key");
        spyS3Object.setObjectContent(new ByteArrayInputStream("objectContent".getBytes()));
        presignedUrlDownloadResult.setS3Object(spyS3Object);
        when(mockAmazonS3Client.download(any(PresignedUrlDownloadRequest.class)))
                .thenReturn(presignedUrlDownloadResult);

        // Run the test
        myClassUnderTest.tryDownload();

        // Verify the results
        verify(spyS3Object).close();
    }

    @Test
    void testTryDownload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.download(any(PresignedUrlDownloadRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDownload());
    }

    @Test
    void testTryDownload1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDownload1();

        // Verify the results
        verify(mockAmazonS3Client).download(any(PresignedUrlDownloadRequest.class), eq(new File("filename.txt")));
    }

    @Test
    void testTryDownload1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).download(any(PresignedUrlDownloadRequest.class),
                eq(new File("filename.txt")));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDownload1());
    }

    @Test
    void testTryUpload() {
        // Setup
        // Configure AmazonS3Client.upload(...).
        final PresignedUrlUploadResult presignedUrlUploadResult = new PresignedUrlUploadResult();
        when(mockAmazonS3Client.upload(any(PresignedUrlUploadRequest.class))).thenReturn(presignedUrlUploadResult);

        // Run the test
        myClassUnderTest.tryUpload();

        // Verify the results
    }

    @Test
    void testTryEnableRequesterPays() {
        // Setup
        // Run the test
        myClassUnderTest.tryEnableRequesterPays();

        // Verify the results
        verify(mockAmazonS3Client).enableRequesterPays("bucketName");
    }

    @Test
    void testTryDisableRequesterPays() {
        // Setup
        // Run the test
        myClassUnderTest.tryDisableRequesterPays();

        // Verify the results
        verify(mockAmazonS3Client).disableRequesterPays("bucketName");
    }

    @Test
    void testTryIsRequesterPaysEnabled() {
        // Setup
        when(mockAmazonS3Client.isRequesterPaysEnabled("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryIsRequesterPaysEnabled();

        // Verify the results
    }

    @Test
    void testTrySetBucketReplicationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketReplicationConfiguration(eq("bucketName"),
                any(BucketReplicationConfiguration.class));
    }

    @Test
    void testTrySetBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                eq("bucketName"), any(BucketReplicationConfiguration.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketReplicationConfiguration());
    }

    @Test
    void testTrySetBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(eq("bucketName"),
                any(BucketReplicationConfiguration.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketReplicationConfiguration());
    }

    @Test
    void testTrySetBucketReplicationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));
    }

    @Test
    void testTrySetBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketReplicationConfiguration1());
    }

    @Test
    void testTrySetBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketReplicationConfiguration1());
    }

    @Test
    void testTryGetBucketReplicationConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketReplicationConfiguration(...).
        final BucketReplicationConfiguration bucketReplicationConfiguration = new BucketReplicationConfiguration();
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName"))
                .thenReturn(bucketReplicationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketReplicationConfiguration());
    }

    @Test
    void testTryGetBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketReplicationConfiguration());
    }

    @Test
    void testTryGetBucketReplicationConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketReplicationConfiguration(...).
        final BucketReplicationConfiguration bucketReplicationConfiguration = new BucketReplicationConfiguration();
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenReturn(bucketReplicationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketReplicationConfiguration1());
    }

    @Test
    void testTryGetBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketReplicationConfiguration1());
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketReplicationConfiguration("bucketName");
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                "bucketName");

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketReplicationConfiguration());
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration("bucketName");

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketReplicationConfiguration());
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketReplicationConfiguration1());
    }

    @Test
    void testTryDeleteBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketReplicationConfiguration1());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResult deleteBucketMetricsConfigurationResult = new DeleteBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResult deleteBucketMetricsConfigurationResult = new DeleteBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenReturn(deleteBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration1());
    }

    @Test
    void testTryDeleteBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketMetricsConfiguration1());
    }

    @Test
    void testTryGetBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResult getBucketMetricsConfigurationResult = new GetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id"))
                .thenReturn(getBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration());
    }

    @Test
    void testTryGetBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id")).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration());
    }

    @Test
    void testTryGetBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResult getBucketMetricsConfigurationResult = new GetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenReturn(getBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration1());
    }

    @Test
    void testTryGetBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketMetricsConfiguration1());
    }

    @Test
    void testTrySetBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketMetricsConfiguration(...).
        final SetBucketMetricsConfigurationResult setBucketMetricsConfigurationResult = new SetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenReturn(setBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test
    void testTrySetBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketMetricsConfiguration());
    }

    @Test
    void testTrySetBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketMetricsConfiguration());
    }

    @Test
    void testTrySetBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketMetricsConfiguration(...).
        final SetBucketMetricsConfigurationResult setBucketMetricsConfigurationResult = new SetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenReturn(setBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketMetricsConfiguration1());
    }

    @Test
    void testTrySetBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketMetricsConfiguration1());
    }

    @Test
    void testTryListBucketMetricsConfigurations() {
        // Setup
        // Configure AmazonS3Client.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = new ListBucketMetricsConfigurationsResult();
        final MetricsConfiguration metricsConfiguration = new MetricsConfiguration();
        listBucketMetricsConfigurationsResult.setMetricsConfigurationList(Arrays.asList(metricsConfiguration));
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenReturn(listBucketMetricsConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations_AmazonS3ClientReturnsNoItems() {
        // Setup
        // Configure AmazonS3Client.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = new ListBucketMetricsConfigurationsResult();
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenReturn(listBucketMetricsConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketMetricsConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations());
    }

    @Test
    void testTryListBucketMetricsConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketMetricsConfigurations());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketAnalyticsConfiguration(...).
        final DeleteBucketAnalyticsConfigurationResult deleteBucketAnalyticsConfigurationResult = new DeleteBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketAnalyticsConfiguration(...).
        final DeleteBucketAnalyticsConfigurationResult deleteBucketAnalyticsConfigurationResult = new DeleteBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration(
                any(DeleteBucketAnalyticsConfigurationRequest.class)))
                .thenReturn(deleteBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration(
                any(DeleteBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryDeleteBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration(
                any(DeleteBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResult getBucketAnalyticsConfigurationResult = new GetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenReturn(getBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResult getBucketAnalyticsConfigurationResult = new GetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenReturn(getBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryGetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketAnalyticsConfiguration(...).
        final SetBucketAnalyticsConfigurationResult setBucketAnalyticsConfigurationResult = new SetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenReturn(setBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAnalyticsConfiguration());
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAnalyticsConfiguration());
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketAnalyticsConfiguration(...).
        final SetBucketAnalyticsConfigurationResult setBucketAnalyticsConfigurationResult = new SetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenReturn(setBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTrySetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketAnalyticsConfiguration1());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations() {
        // Setup
        // Configure AmazonS3Client.listBucketAnalyticsConfigurations(...).
        final ListBucketAnalyticsConfigurationsResult listBucketAnalyticsConfigurationsResult = new ListBucketAnalyticsConfigurationsResult();
        final AnalyticsConfiguration analyticsConfiguration = new AnalyticsConfiguration();
        listBucketAnalyticsConfigurationsResult.setAnalyticsConfigurationList(Arrays.asList(analyticsConfiguration));
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class)))
                .thenReturn(listBucketAnalyticsConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations_AmazonS3ClientReturnsNoItems() {
        // Setup
        // Configure AmazonS3Client.listBucketAnalyticsConfigurations(...).
        final ListBucketAnalyticsConfigurationsResult listBucketAnalyticsConfigurationsResult = new ListBucketAnalyticsConfigurationsResult();
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class)))
                .thenReturn(listBucketAnalyticsConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketAnalyticsConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations());
    }

    @Test
    void testTryListBucketAnalyticsConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketAnalyticsConfigurations());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketInventoryConfiguration(...).
        final DeleteBucketInventoryConfigurationResult deleteBucketInventoryConfigurationResult = new DeleteBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketInventoryConfiguration(...).
        final DeleteBucketInventoryConfigurationResult deleteBucketInventoryConfigurationResult = new DeleteBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration(
                any(DeleteBucketInventoryConfigurationRequest.class)))
                .thenReturn(deleteBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration(
                any(DeleteBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration1());
    }

    @Test
    void testTryDeleteBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration(
                any(DeleteBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryDeleteBucketInventoryConfiguration1());
    }

    @Test
    void testTryGetBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResult getBucketInventoryConfigurationResult = new GetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenReturn(getBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration());
    }

    @Test
    void testTryGetBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration());
    }

    @Test
    void testTryGetBucketInventoryConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResult getBucketInventoryConfigurationResult = new GetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenReturn(getBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration1());
    }

    @Test
    void testTryGetBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryGetBucketInventoryConfiguration1());
    }

    @Test
    void testTrySetBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketInventoryConfiguration(...).
        final SetBucketInventoryConfigurationResult setBucketInventoryConfigurationResult = new SetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenReturn(setBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test
    void testTrySetBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketInventoryConfiguration());
    }

    @Test
    void testTrySetBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketInventoryConfiguration());
    }

    @Test
    void testTrySetBucketInventoryConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketInventoryConfiguration(...).
        final SetBucketInventoryConfigurationResult setBucketInventoryConfigurationResult = new SetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenReturn(setBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test
    void testTrySetBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.trySetBucketInventoryConfiguration1());
    }

    @Test
    void testTrySetBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.trySetBucketInventoryConfiguration1());
    }

    @Test
    void testTryListBucketInventoryConfigurations() {
        // Setup
        // Configure AmazonS3Client.listBucketInventoryConfigurations(...).
        final ListBucketInventoryConfigurationsResult listBucketInventoryConfigurationsResult = new ListBucketInventoryConfigurationsResult();
        final InventoryConfiguration inventoryConfiguration = new InventoryConfiguration();
        listBucketInventoryConfigurationsResult.setInventoryConfigurationList(Arrays.asList(inventoryConfiguration));
        when(mockAmazonS3Client.listBucketInventoryConfigurations(
                any(ListBucketInventoryConfigurationsRequest.class)))
                .thenReturn(listBucketInventoryConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations();

        // Verify the results
    }

    @Test
    void testTryListBucketInventoryConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketInventoryConfigurations(
                any(ListBucketInventoryConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        assertThrows(AmazonServiceException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations());
    }

    @Test
    void testTryListBucketInventoryConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketInventoryConfigurations(
                any(ListBucketInventoryConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        assertThrows(SdkClientException.class, () -> myClassUnderTest.tryListBucketInventoryConfigurations());
    }

    @Test
    void testTryWaiters() {
        // Setup
        when(mockAmazonS3Client.waiters()).thenReturn(new AmazonS3Waiters(null));

        // Run the test
        myClassUnderTest.tryWaiters();

        // Verify the results
    }

    @Test
    void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
