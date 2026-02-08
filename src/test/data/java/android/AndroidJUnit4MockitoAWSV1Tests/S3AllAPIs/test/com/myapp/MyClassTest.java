package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private AmazonS3Client mockAmazonS3Client;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonS3Client);
    }

    @Test
    public void testTrySetEndpoint() {
        // Setup
        // Run the test
        myClassUnderTest.trySetEndpoint();

        // Verify the results
        verify(mockAmazonS3Client).setEndpoint("endpoint");
    }

    @Test
    public void testTrySetRegion() {
        // Setup
        // Run the test
        myClassUnderTest.trySetRegion();

        // Verify the results
        verify(mockAmazonS3Client).setRegion(new Region(null));
    }

    @Test
    public void testTrySetS3ClientOptions() {
        // Setup
        // Run the test
        myClassUnderTest.trySetS3ClientOptions();

        // Verify the results
        verify(mockAmazonS3Client).setS3ClientOptions(any(S3ClientOptions.class));
    }

    @Test
    public void testTryListNextBatchOfVersions() {
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
    public void testTryListNextBatchOfVersions_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class))).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListNextBatchOfVersions_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListNextBatchOfVersions_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(VersionListing.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions();
    }

    @Test
    public void testTryListNextBatchOfVersions1() {
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
    public void testTryListNextBatchOfVersions1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(ListNextBatchOfVersionsRequest.class)))
                .thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListNextBatchOfVersions1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(ListNextBatchOfVersionsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListNextBatchOfVersions1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfVersions(any(ListNextBatchOfVersionsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfVersions1();
    }

    @Test
    public void testTryListVersions() {
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
    public void testTryListVersions_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListVersions_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListVersions();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListVersions_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListVersions();
    }

    @Test
    public void testTryListVersions1() {
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
    public void testTryListVersions1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListVersions1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListVersions1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListVersions1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions("bucketName", "prefix", "keyMarker", "versionIdMarker", "delimiter",
                0)).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListVersions1();
    }

    @Test
    public void testTryListVersions2() {
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
    public void testTryListVersions2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenReturn(new VersionListing());

        // Run the test
        myClassUnderTest.tryListVersions2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListVersions2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListVersions2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListVersions2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listVersions(any(ListVersionsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListVersions2();
    }

    @Test
    public void testTryListObjects() {
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
    public void testTryListObjects_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjects_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjects();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjects_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjects();
    }

    @Test
    public void testTryListObjects1() {
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
    public void testTryListObjects1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjects1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjects1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjects1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjects1();
    }

    @Test
    public void testTryListObjects2() {
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
    public void testTryListObjects2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListObjects2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjects2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjects2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjects2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjects(any(ListObjectsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjects2();
    }

    @Test
    public void testTryListObjectsV2() {
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
    public void testTryListObjectsV2_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjectsV2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjectsV2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV2();
    }

    @Test
    public void testTryListObjectsV21() {
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
    public void testTryListObjectsV21_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV21();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjectsV21_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV21();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjectsV21_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2("bucketName", "prefix")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV21();
    }

    @Test
    public void testTryListObjectsV22() {
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
    public void testTryListObjectsV22_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenReturn(new ListObjectsV2Result());

        // Run the test
        myClassUnderTest.tryListObjectsV22();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListObjectsV22_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV22();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListObjectsV22_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listObjectsV2(any(ListObjectsV2Request.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListObjectsV22();
    }

    @Test
    public void testTryListNextBatchOfObjects() {
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
    public void testTryListNextBatchOfObjects_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class))).thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListNextBatchOfObjects_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListNextBatchOfObjects_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ObjectListing.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects();
    }

    @Test
    public void testTryListNextBatchOfObjects1() {
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
    public void testTryListNextBatchOfObjects1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenReturn(new ObjectListing());

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListNextBatchOfObjects1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListNextBatchOfObjects1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listNextBatchOfObjects(any(ListNextBatchOfObjectsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListNextBatchOfObjects1();
    }

    @Test
    public void testTryGetS3AccountOwner() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenReturn(new Owner("id", "displayName"));

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetS3AccountOwner_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetS3AccountOwner_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner()).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner();
    }

    @Test
    public void testTryGetS3AccountOwner1() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenReturn(new Owner("id", "displayName"));

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetS3AccountOwner1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetS3AccountOwner1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getS3AccountOwner(any(GetS3AccountOwnerRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetS3AccountOwner1();
    }

    @Test
    public void testTryListBuckets() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class)))
                .thenReturn(Arrays.asList(new Bucket("name")));

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test
    public void testTryListBuckets_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListBuckets();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListBuckets_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListBuckets();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListBuckets_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBuckets(any(ListBucketsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListBuckets();
    }

    @Test
    public void testTryListBuckets1() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenReturn(Arrays.asList(new Bucket("name")));

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test
    public void testTryListBuckets1_AmazonS3ClientReturnsNoItems() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListBuckets1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListBuckets1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListBuckets1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListBuckets1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBuckets()).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListBuckets1();
    }

    @Test
    public void testTryGetBucketLocation() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class))).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetBucketLocation();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketLocation_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLocation();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketLocation_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation(any(GetBucketLocationRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLocation();
    }

    @Test
    public void testTryGetBucketLocation1() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketLocation1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketLocation1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLocation("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLocation1();
    }

    @Test
    public void testTryCreateBucket() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCreateBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCreateBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket();
    }

    @Test
    public void testTryCreateBucket1() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCreateBucket1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCreateBucket1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName",
                com.amazonaws.services.s3.model.Region.US_Standard)).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket1();
    }

    @Test
    public void testTryCreateBucket2() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCreateBucket2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCreateBucket2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket("bucketName", "region")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket2();
    }

    @Test
    public void testTryCreateBucket3() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenReturn(new Bucket("name"));

        // Run the test
        myClassUnderTest.tryCreateBucket3();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCreateBucket3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket3();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCreateBucket3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.createBucket(any(CreateBucketRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCreateBucket3();
    }

    @Test
    public void testTryGetObjectAcl() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl();
    }

    @Test
    public void testTryGetObjectAcl1() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl("bucketName", "key", "versionId")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl1();
    }

    @Test
    public void testTryGetObjectAcl2() {
        // Setup
        // Configure AmazonS3Client.getObjectAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getObjectAcl(any(GetObjectAclRequest.class))).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetObjectAcl2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectAcl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl(any(GetObjectAclRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectAcl2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAcl(any(GetObjectAclRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAcl2();
    }

    @Test
    public void testTrySetObjectAcl() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);

        // Run the test
        myClassUnderTest.trySetObjectAcl();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", acl);

        // Run the test
        myClassUnderTest.trySetObjectAcl();
    }

    @Test
    public void testTrySetObjectAcl1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl1();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", CannedAccessControlList.Private);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetObjectAcl1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetObjectAcl1();
    }

    @Test
    public void testTrySetObjectAcl2() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl2();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", acl);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", acl);

        // Run the test
        myClassUnderTest.trySetObjectAcl2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                acl);

        // Run the test
        myClassUnderTest.trySetObjectAcl2();
    }

    @Test
    public void testTrySetObjectAcl3() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl3();

        // Verify the results
        // Confirm AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"), eq("versionId"), eq(acl),
                any(RequestMetricCollector.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"),
                eq("versionId"), eq(acl), any(RequestMetricCollector.class));

        // Run the test
        myClassUnderTest.trySetObjectAcl3();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setObjectAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"),
                eq("versionId"), eq(acl), any(RequestMetricCollector.class));

        // Run the test
        myClassUnderTest.trySetObjectAcl3();
    }

    @Test
    public void testTrySetObjectAcl4() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl4();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId", CannedAccessControlList.Private);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl4_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetObjectAcl4();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl4_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl("bucketName", "key", "versionId",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetObjectAcl4();
    }

    @Test
    public void testTrySetObjectAcl5() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl5();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl(eq("bucketName"), eq("key"), eq("versionId"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));
    }

    @Test
    public void testTrySetObjectAcl6() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectAcl6();

        // Verify the results
        verify(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectAcl6_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));

        // Run the test
        myClassUnderTest.trySetObjectAcl6();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectAcl6_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectAcl(any(SetObjectAclRequest.class));

        // Run the test
        myClassUnderTest.trySetObjectAcl6();
    }

    @Test
    public void testTryGetBucketAcl() {
        // Setup
        // Configure AmazonS3Client.getBucketAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAcl();
    }

    @Test
    public void testTryGetBucketAcl1() {
        // Setup
        // Configure AmazonS3Client.getBucketAcl(...).
        final AccessControlList accessControlList = new AccessControlList();
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenReturn(accessControlList);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAcl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAcl1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAcl(any(GetBucketAclRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAcl1();
    }

    @Test
    public void testTrySetBucketAcl() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl();

        // Verify the results
        // Confirm AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setBucketAcl("bucketName", acl);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAcl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl("bucketName", acl);

        // Run the test
        myClassUnderTest.trySetBucketAcl();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAcl_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl("bucketName", acl);

        // Run the test
        myClassUnderTest.trySetBucketAcl();
    }

    @Test
    public void testTrySetBucketAcl1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl1();

        // Verify the results
        // Confirm AmazonS3Client.setBucketAcl(...).
        final AccessControlList acl = new AccessControlList();
        verify(mockAmazonS3Client).setBucketAcl(eq("bucketName"), eq(acl), any(RequestMetricCollector.class));
    }

    @Test
    public void testTrySetBucketAcl2() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl2();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl("bucketName", CannedAccessControlList.Private);
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAcl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl("bucketName",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetBucketAcl2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAcl2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl("bucketName",
                CannedAccessControlList.Private);

        // Run the test
        myClassUnderTest.trySetBucketAcl2();
    }

    @Test
    public void testTrySetBucketAcl3() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl3();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl(eq("bucketName"), eq(CannedAccessControlList.Private),
                any(RequestMetricCollector.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAcl3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl(eq("bucketName"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));

        // Run the test
        myClassUnderTest.trySetBucketAcl3();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAcl3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl(eq("bucketName"),
                eq(CannedAccessControlList.Private), any(RequestMetricCollector.class));

        // Run the test
        myClassUnderTest.trySetBucketAcl3();
    }

    @Test
    public void testTrySetBucketAcl4() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAcl4();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAcl4_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketAcl4();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAcl4_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAcl(any(SetBucketAclRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketAcl4();
    }

    @Test
    public void testTryGetObjectMetadata() {
        // Setup
        // Configure AmazonS3Client.getObjectMetadata(...).
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenReturn(objectMetadata);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectMetadata_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectMetadata_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata();
    }

    @Test
    public void testTryGetObjectMetadata1() {
        // Setup
        // Configure AmazonS3Client.getObjectMetadata(...).
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class))).thenReturn(objectMetadata);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectMetadata1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectMetadata1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectMetadata(any(GetObjectMetadataRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectMetadata1();
    }

    @Test
    public void testTryGetObject() throws Exception {
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
    public void testTryGetObject_AmazonS3ClientReturnsNoContent() throws Exception {
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
    public void testTryGetObject_AmazonS3ClientReturnsBrokenIo() throws Exception {
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

    @Test(expected = SdkClientException.class)
    public void testTryGetObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObject();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObject();
    }

    @Test
    public void testTryDoesBucketExist() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesBucketExist();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryDoesBucketExist_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDoesBucketExist();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDoesBucketExist_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExist("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDoesBucketExist();
    }

    @Test
    public void testTryDoesBucketExistV2() {
        // Setup
        when(mockAmazonS3Client.doesBucketExistV2("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesBucketExistV2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryDoesBucketExistV2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesBucketExistV2("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDoesBucketExistV2();
    }

    @Test
    public void testTryDoesObjectExist() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDoesObjectExist();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDoesObjectExist_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDoesObjectExist();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDoesObjectExist_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.doesObjectExist("bucketName", "objectName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDoesObjectExist();
    }

    @Test
    public void testTryHeadBucket() {
        // Setup
        // Configure AmazonS3Client.headBucket(...).
        final HeadBucketResult headBucketResult = new HeadBucketResult();
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName"))).thenReturn(headBucketResult);

        // Run the test
        myClassUnderTest.tryHeadBucket();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryHeadBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName"))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryHeadBucket();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryHeadBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.headBucket(new HeadBucketRequest("bucketName")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryHeadBucket();
    }

    @Test
    public void testTryChangeObjectStorageClass() {
        // Setup
        // Run the test
        myClassUnderTest.tryChangeObjectStorageClass();

        // Verify the results
        verify(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key", StorageClass.Standard);
    }

    @Test(expected = SdkClientException.class)
    public void testTryChangeObjectStorageClass_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key",
                StorageClass.Standard);

        // Run the test
        myClassUnderTest.tryChangeObjectStorageClass();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryChangeObjectStorageClass_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).changeObjectStorageClass("bucketName", "key",
                StorageClass.Standard);

        // Run the test
        myClassUnderTest.tryChangeObjectStorageClass();
    }

    @Test
    public void testTrySetObjectRedirectLocation() {
        // Setup
        // Run the test
        myClassUnderTest.trySetObjectRedirectLocation();

        // Verify the results
        verify(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key", "newRedirectLocation");
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectRedirectLocation_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key",
                "newRedirectLocation");

        // Run the test
        myClassUnderTest.trySetObjectRedirectLocation();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectRedirectLocation_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setObjectRedirectLocation("bucketName", "key",
                "newRedirectLocation");

        // Run the test
        myClassUnderTest.trySetObjectRedirectLocation();
    }

    @Test
    public void testTryGetObject1() throws Exception {
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
    public void testTryGetObject1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetObject1();

        // Verify the results
    }

    @Test
    public void testTryGetObject1_AmazonS3ClientReturnsNoContent() throws Exception {
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
    public void testTryGetObject1_AmazonS3ClientReturnsBrokenIo() throws Exception {
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

    @Test(expected = SdkClientException.class)
    public void testTryGetObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key")))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObject1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObject1();
    }

    @Test
    public void testTryGetObject2() {
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
    public void testTryGetObject2_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetObject2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObject2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObject2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObject2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObject(new GetObjectRequest("bucketName", "key"),
                new File("filename.txt"))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObject2();
    }

    @Test
    public void testTryGetObjectAsString() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetObjectAsString();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectAsString_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAsString();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectAsString_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectAsString("bucketName", "key")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectAsString();
    }

    @Test
    public void testTryGetObjectTagging() {
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
    public void testTrySetObjectTagging() {
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
    public void testTryDeleteObjectTagging() {
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
    public void testTryDeleteBucket() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucket();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucket("bucketName");
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucket_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucket("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucket();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucket_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucket("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucket();
    }

    @Test
    public void testTryDeleteBucket1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucket1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucket1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucket1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucket1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucket(any(DeleteBucketRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucket1();
    }

    @Test
    public void testTryPutObject() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt"))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryPutObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt")))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryPutObject();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryPutObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", new File("filename.txt")))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryPutObject();
    }

    @Test
    public void testTryPutObject1() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryPutObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryPutObject1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryPutObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject(eq("bucketName"), eq("key"), any(InputStream.class),
                any(ObjectMetadata.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryPutObject1();
    }

    @Test
    public void testTryPutObject2() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryPutObject2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryPutObject2();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryPutObject2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject(any(PutObjectRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryPutObject2();
    }

    @Test
    public void testTryCopyObject() {
        // Setup
        // Configure AmazonS3Client.copyObject(...).
        final CopyObjectResult copyObjectResult = new CopyObjectResult();
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenReturn(copyObjectResult);

        // Run the test
        myClassUnderTest.tryCopyObject();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCopyObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCopyObject();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCopyObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.copyObject("sourceBucketName", "sourceKey", "destinationBucketName",
                "destinationKey")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCopyObject();
    }

    @Test
    public void testTryCopyObject1() {
        // Setup
        // Configure AmazonS3Client.copyObject(...).
        final CopyObjectResult copyObjectResult = new CopyObjectResult();
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenReturn(copyObjectResult);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test
    public void testTryCopyObject1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryCopyObject1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCopyObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCopyObject1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCopyObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.copyObject(any(CopyObjectRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCopyObject1();
    }

    @Test
    public void testTryCopyPart() {
        // Setup
        // Configure AmazonS3Client.copyPart(...).
        final CopyPartResult copyPartResult = new CopyPartResult();
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenReturn(copyPartResult);

        // Run the test
        myClassUnderTest.tryCopyPart();

        // Verify the results
    }

    @Test
    public void testTryCopyPart_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryCopyPart();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCopyPart_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCopyPart();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCopyPart_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.copyPart(any(CopyPartRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCopyPart();
    }

    @Test
    public void testTryDeleteObject() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteObject();

        // Verify the results
        verify(mockAmazonS3Client).deleteObject("bucketName", "key");
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteObject_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteObject("bucketName", "key");

        // Run the test
        myClassUnderTest.tryDeleteObject();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteObject("bucketName", "key");

        // Run the test
        myClassUnderTest.tryDeleteObject();
    }

    @Test
    public void testTryDeleteObject1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteObject1();

        // Verify the results
        verify(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteObject1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteObject1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteObject(any(DeleteObjectRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteObject1();
    }

    @Test
    public void testTryDeleteObjects() {
        // Setup
        // Configure AmazonS3Client.deleteObjects(...).
        final DeleteObjectsResult.DeletedObject deletedObject = new DeleteObjectsResult.DeletedObject();
        final DeleteObjectsResult deleteObjectsResult = new DeleteObjectsResult(Arrays.asList(deletedObject), false);
        when(mockAmazonS3Client.deleteObjects(any(DeleteObjectsRequest.class))).thenReturn(deleteObjectsResult);

        // Run the test
        myClassUnderTest.tryDeleteObjects();

        // Verify the results
    }

    @Test(expected = MultiObjectDeleteException.class)
    public void testTryDeleteObjects_AmazonS3ClientThrowsMultiObjectDeleteException() {
        // Setup
        when(mockAmazonS3Client.deleteObjects(any(DeleteObjectsRequest.class)))
                .thenThrow(MultiObjectDeleteException.class);

        // Run the test
        myClassUnderTest.tryDeleteObjects();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteObjects_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteObjects(any(DeleteObjectsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteObjects();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteObjects_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteObjects(any(DeleteObjectsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteObjects();
    }

    @Test
    public void testTryDeleteVersion() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteVersion();

        // Verify the results
        verify(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteVersion_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");

        // Run the test
        myClassUnderTest.tryDeleteVersion();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteVersion_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteVersion("bucketName", "key", "versionId");

        // Run the test
        myClassUnderTest.tryDeleteVersion();
    }

    @Test
    public void testTryDeleteVersion1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteVersion1();

        // Verify the results
        verify(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteVersion1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteVersion1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteVersion1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteVersion(any(DeleteVersionRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteVersion1();
    }

    @Test
    public void testTrySetBucketVersioningConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketVersioningConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketVersioningConfiguration(any(SetBucketVersioningConfigurationRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketVersioningConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketVersioningConfiguration(
                any(SetBucketVersioningConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketVersioningConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketVersioningConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketVersioningConfiguration(
                any(SetBucketVersioningConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketVersioningConfiguration();
    }

    @Test
    public void testTryGetBucketVersioningConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketVersioningConfiguration(...).
        final BucketVersioningConfiguration bucketVersioningConfiguration = new BucketVersioningConfiguration("status");
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName"))
                .thenReturn(bucketVersioningConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketVersioningConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketVersioningConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration();
    }

    @Test
    public void testTryGetBucketVersioningConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketVersioningConfiguration(...).
        final BucketVersioningConfiguration bucketVersioningConfiguration = new BucketVersioningConfiguration("status");
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenReturn(bucketVersioningConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketVersioningConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketVersioningConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketVersioningConfiguration(
                any(GetBucketVersioningConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketVersioningConfiguration1();
    }

    @Test
    public void testTryGetBucketWebsiteConfiguration() {
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
    public void testTryGetBucketWebsiteConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration();
    }

    @Test
    public void testTryGetBucketWebsiteConfiguration1() {
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
    public void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketWebsiteConfiguration(
                any(GetBucketWebsiteConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketWebsiteConfiguration1();
    }

    @Test
    public void testTryGetBucketLifecycleConfiguration() {
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
    public void testTryGetBucketLifecycleConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketLifecycleConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration();

        // Verify the results
    }

    @Test
    public void testTryGetBucketLifecycleConfiguration1() {
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
    public void testTryGetBucketLifecycleConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketLifecycleConfiguration(
                any(GetBucketLifecycleConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketLifecycleConfiguration1();

        // Verify the results
    }

    @Test
    public void testTrySetBucketLifecycleConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLifecycleConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLifecycleConfiguration(eq("bucketName"),
                any(BucketLifecycleConfiguration.class));
    }

    @Test
    public void testTrySetBucketLifecycleConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLifecycleConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLifecycleConfiguration(any(SetBucketLifecycleConfigurationRequest.class));
    }

    @Test
    public void testTryDeleteBucketLifecycleConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycleConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketLifecycleConfiguration("bucketName");
    }

    @Test
    public void testTryDeleteBucketLifecycleConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketLifecycleConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketLifecycleConfiguration(
                any(DeleteBucketLifecycleConfigurationRequest.class));
    }

    @Test
    public void testTryGetBucketCrossOriginConfiguration() {
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
    public void testTryGetBucketCrossOriginConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration();

        // Verify the results
    }

    @Test
    public void testTryGetBucketCrossOriginConfiguration1() {
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
    public void testTryGetBucketCrossOriginConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketCrossOriginConfiguration(
                any(GetBucketCrossOriginConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketCrossOriginConfiguration1();

        // Verify the results
    }

    @Test
    public void testTrySetBucketCrossOriginConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketCrossOriginConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketCrossOriginConfiguration(eq("bucketName"),
                any(BucketCrossOriginConfiguration.class));
    }

    @Test
    public void testTrySetBucketCrossOriginConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketCrossOriginConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketCrossOriginConfiguration(
                any(SetBucketCrossOriginConfigurationRequest.class));
    }

    @Test
    public void testTryDeleteBucketCrossOriginConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketCrossOriginConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketCrossOriginConfiguration("bucketName");
    }

    @Test
    public void testTryDeleteBucketCrossOriginConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketCrossOriginConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketCrossOriginConfiguration(
                any(DeleteBucketCrossOriginConfigurationRequest.class));
    }

    @Test
    public void testTryGetBucketTaggingConfiguration() {
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
    public void testTryGetBucketTaggingConfiguration_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketTaggingConfiguration("bucketName")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration();

        // Verify the results
    }

    @Test
    public void testTryGetBucketTaggingConfiguration1() {
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
    public void testTryGetBucketTaggingConfiguration1_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getBucketTaggingConfiguration(
                any(GetBucketTaggingConfigurationRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetBucketTaggingConfiguration1();

        // Verify the results
    }

    @Test
    public void testTrySetBucketTaggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketTaggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketTaggingConfiguration(eq("bucketName"),
                any(BucketTaggingConfiguration.class));
    }

    @Test
    public void testTrySetBucketTaggingConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketTaggingConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketTaggingConfiguration(any(SetBucketTaggingConfigurationRequest.class));
    }

    @Test
    public void testTryDeleteBucketTaggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketTaggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketTaggingConfiguration("bucketName");
    }

    @Test
    public void testTryDeleteBucketTaggingConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketTaggingConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketTaggingConfiguration(any(DeleteBucketTaggingConfigurationRequest.class));
    }

    @Test
    public void testTrySetBucketWebsiteConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(eq("bucketName"),
                any(BucketWebsiteConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration();
    }

    @Test
    public void testTrySetBucketWebsiteConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketWebsiteConfiguration(any(SetBucketWebsiteConfigurationRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(
                any(SetBucketWebsiteConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketWebsiteConfiguration(
                any(SetBucketWebsiteConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketWebsiteConfiguration1();
    }

    @Test
    public void testTryDeleteBucketWebsiteConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketWebsiteConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketWebsiteConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration();
    }

    @Test
    public void testTryDeleteBucketWebsiteConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketWebsiteConfiguration(any(DeleteBucketWebsiteConfigurationRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketWebsiteConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration(
                any(DeleteBucketWebsiteConfigurationRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketWebsiteConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketWebsiteConfiguration(
                any(DeleteBucketWebsiteConfigurationRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketWebsiteConfiguration1();
    }

    @Test
    public void testTrySetBucketNotificationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketNotificationConfiguration(eq("bucketName"),
                any(BucketNotificationConfiguration.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketNotificationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(eq("bucketName"),
                any(BucketNotificationConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketNotificationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                eq("bucketName"), any(BucketNotificationConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration();
    }

    @Test
    public void testTrySetBucketNotificationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketNotificationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketNotificationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketNotificationConfiguration(
                any(SetBucketNotificationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketNotificationConfiguration1();
    }

    @Test
    public void testTryGetBucketNotificationConfiguration() {
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

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketNotificationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketNotificationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration("bucketName"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration();
    }

    @Test
    public void testTryGetBucketNotificationConfiguration1() {
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

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketNotificationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration(
                any(GetBucketNotificationConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketNotificationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketNotificationConfiguration(
                any(GetBucketNotificationConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketNotificationConfiguration1();
    }

    @Test
    public void testTryGetBucketLoggingConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketLoggingConfiguration(...).
        final BucketLoggingConfiguration bucketLoggingConfiguration = new BucketLoggingConfiguration(
                "destinationBucketName", "logFilePrefix");
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenReturn(bucketLoggingConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketLoggingConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketLoggingConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration();
    }

    @Test
    public void testTryGetBucketLoggingConfiguration1() {
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

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketLoggingConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration(
                any(GetBucketLoggingConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketLoggingConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketLoggingConfiguration(
                any(GetBucketLoggingConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketLoggingConfiguration1();
    }

    @Test
    public void testTrySetBucketLoggingConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketLoggingConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketLoggingConfiguration(any(SetBucketLoggingConfigurationRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketLoggingConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketLoggingConfiguration(
                any(SetBucketLoggingConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketLoggingConfiguration();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketLoggingConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketLoggingConfiguration(
                any(SetBucketLoggingConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketLoggingConfiguration();
    }

    @Test
    public void testTryGetBucketAccelerateConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketAccelerateConfiguration(...).
        final BucketAccelerateConfiguration bucketAccelerateConfiguration = new BucketAccelerateConfiguration("status");
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName"))
                .thenReturn(bucketAccelerateConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAccelerateConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAccelerateConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration();
    }

    @Test
    public void testTryGetBucketAccelerateConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketAccelerateConfiguration(...).
        final BucketAccelerateConfiguration bucketAccelerateConfiguration = new BucketAccelerateConfiguration("status");
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenReturn(bucketAccelerateConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAccelerateConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAccelerateConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAccelerateConfiguration(
                any(GetBucketAccelerateConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAccelerateConfiguration1();
    }

    @Test
    public void testTrySetBucketAccelerateConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAccelerateConfiguration(eq("bucketName"),
                any(BucketAccelerateConfiguration.class));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAccelerateConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                eq("bucketName"), any(BucketAccelerateConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAccelerateConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(eq("bucketName"),
                any(BucketAccelerateConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration();
    }

    @Test
    public void testTrySetBucketAccelerateConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketAccelerateConfiguration(any(SetBucketAccelerateConfigurationRequest.class));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAccelerateConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                any(SetBucketAccelerateConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAccelerateConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketAccelerateConfiguration(
                any(SetBucketAccelerateConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketAccelerateConfiguration1();
    }

    @Test
    public void testTryGetBucketPolicy() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicy(...).
        final BucketPolicy bucketPolicy = new BucketPolicy();
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenReturn(bucketPolicy);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy();
    }

    @Test
    public void testTryDeleteBucketPolicy() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketPolicy("bucketName");
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketPolicy("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketPolicy("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy();
    }

    @Test
    public void testTryGetBucketPolicy1() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicy(...).
        final BucketPolicy bucketPolicy = new BucketPolicy();
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class))).thenReturn(bucketPolicy);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketPolicy(any(GetBucketPolicyRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicy1();
    }

    @Test
    public void testTrySetBucketPolicy() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketPolicy();

        // Verify the results
        verify(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketPolicy_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");

        // Run the test
        myClassUnderTest.trySetBucketPolicy();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketPolicy_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketPolicy("bucketName", "policyText");

        // Run the test
        myClassUnderTest.trySetBucketPolicy();
    }

    @Test
    public void testTrySetBucketPolicy1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketPolicy1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketPolicy(any(SetBucketPolicyRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketPolicy(any(SetBucketPolicyRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketPolicy1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketPolicy(
                any(SetBucketPolicyRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketPolicy1();
    }

    @Test
    public void testTryDeleteBucketPolicy1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketPolicy(any(DeleteBucketPolicyRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketPolicy1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketPolicy(
                any(DeleteBucketPolicyRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketPolicy1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketPolicy(
                any(DeleteBucketPolicyRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketPolicy1();
    }

    @Test
    public void testTryDeleteBucketEncryption() {
        // Setup
        when(mockAmazonS3Client.deleteBucketEncryption("bucketName")).thenReturn(new DeleteBucketEncryptionResult());

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketEncryption("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption();
    }

    @Test
    public void testTryDeleteBucketEncryption1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = new DeleteBucketEncryptionRequest();
        when(mockAmazonS3Client.deleteBucketEncryption(deleteBucketEncryptionRequest))
                .thenReturn(new DeleteBucketEncryptionResult());

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketEncryption1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.deleteBucketEncryption(...).
        final DeleteBucketEncryptionRequest deleteBucketEncryptionRequest = new DeleteBucketEncryptionRequest();
        when(mockAmazonS3Client.deleteBucketEncryption(deleteBucketEncryptionRequest))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketEncryption1();
    }

    @Test
    public void testTryGetBucketEncryption() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionResult getBucketEncryptionResult = new GetBucketEncryptionResult();
        when(mockAmazonS3Client.getBucketEncryption("bucketName")).thenReturn(getBucketEncryptionResult);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketEncryption("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption();
    }

    @Test
    public void testTryGetBucketEncryption1() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionResult getBucketEncryptionResult = new GetBucketEncryptionResult();
        final GetBucketEncryptionRequest getBucketEncryptionRequest = new GetBucketEncryptionRequest();
        when(mockAmazonS3Client.getBucketEncryption(getBucketEncryptionRequest)).thenReturn(getBucketEncryptionResult);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketEncryption1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.getBucketEncryption(...).
        final GetBucketEncryptionRequest getBucketEncryptionRequest = new GetBucketEncryptionRequest();
        when(mockAmazonS3Client.getBucketEncryption(getBucketEncryptionRequest)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketEncryption1();
    }

    @Test
    public void testTrySetBucketEncryption() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest))
                .thenReturn(new SetBucketEncryptionResult());

        // Run the test
        myClassUnderTest.trySetBucketEncryption();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketEncryption_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketEncryption();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketEncryption_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setBucketEncryption(...).
        final SetBucketEncryptionRequest setBucketEncryptionRequest = new SetBucketEncryptionRequest();
        when(mockAmazonS3Client.setBucketEncryption(setBucketEncryptionRequest)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketEncryption();
    }

    @Test
    public void testTrySetPublicAccessBlock() {
        // Setup
        // Configure AmazonS3Client.setPublicAccessBlock(...).
        final SetPublicAccessBlockRequest setPublicAccessBlockRequest = new SetPublicAccessBlockRequest();
        when(mockAmazonS3Client.setPublicAccessBlock(setPublicAccessBlockRequest))
                .thenReturn(new SetPublicAccessBlockResult());

        // Run the test
        myClassUnderTest.trySetPublicAccessBlock();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetPublicAccessBlock_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.setPublicAccessBlock(...).
        final SetPublicAccessBlockRequest setPublicAccessBlockRequest = new SetPublicAccessBlockRequest();
        when(mockAmazonS3Client.setPublicAccessBlock(setPublicAccessBlockRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetPublicAccessBlock();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetPublicAccessBlock_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.setPublicAccessBlock(...).
        final SetPublicAccessBlockRequest setPublicAccessBlockRequest = new SetPublicAccessBlockRequest();
        when(mockAmazonS3Client.setPublicAccessBlock(setPublicAccessBlockRequest)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetPublicAccessBlock();
    }

    @Test
    public void testTryGetPublicAccessBlock() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryGetPublicAccessBlock_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.getPublicAccessBlock(...).
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = new GetPublicAccessBlockRequest();
        when(mockAmazonS3Client.getPublicAccessBlock(getPublicAccessBlockRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetPublicAccessBlock_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.getPublicAccessBlock(...).
        final GetPublicAccessBlockRequest getPublicAccessBlockRequest = new GetPublicAccessBlockRequest();
        when(mockAmazonS3Client.getPublicAccessBlock(getPublicAccessBlockRequest)).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetPublicAccessBlock();
    }

    @Test
    public void testTryDeletePublicAccessBlock() {
        // Setup
        // Configure AmazonS3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = new DeletePublicAccessBlockRequest();
        when(mockAmazonS3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest))
                .thenReturn(new DeletePublicAccessBlockResult());

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeletePublicAccessBlock_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = new DeletePublicAccessBlockRequest();
        when(mockAmazonS3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeletePublicAccessBlock_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.deletePublicAccessBlock(...).
        final DeletePublicAccessBlockRequest deletePublicAccessBlockRequest = new DeletePublicAccessBlockRequest();
        when(mockAmazonS3Client.deletePublicAccessBlock(deletePublicAccessBlockRequest))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeletePublicAccessBlock();
    }

    @Test
    public void testTryGetBucketPolicyStatus() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketPolicyStatus_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicyStatus(...).
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = new GetBucketPolicyStatusRequest();
        when(mockAmazonS3Client.getBucketPolicyStatus(getBucketPolicyStatusRequest))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketPolicyStatus_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        // Configure AmazonS3Client.getBucketPolicyStatus(...).
        final GetBucketPolicyStatusRequest getBucketPolicyStatusRequest = new GetBucketPolicyStatusRequest();
        when(mockAmazonS3Client.getBucketPolicyStatus(getBucketPolicyStatusRequest))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketPolicyStatus();
    }

    @Test
    public void testTrySelectObjectContent() throws Exception {
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

    @Test(expected = AmazonServiceException.class)
    public void testTrySelectObjectContent_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.selectObjectContent(any(SelectObjectContentRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySelectObjectContent();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySelectObjectContent_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.selectObjectContent(any(SelectObjectContentRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySelectObjectContent();
    }

    @Test
    public void testTrySetObjectLegalHold() {
        // Setup
        // Configure AmazonS3Client.setObjectLegalHold(...).
        final SetObjectLegalHoldResult setObjectLegalHoldResult = new SetObjectLegalHoldResult();
        when(mockAmazonS3Client.setObjectLegalHold(any(SetObjectLegalHoldRequest.class)))
                .thenReturn(setObjectLegalHoldResult);

        // Run the test
        myClassUnderTest.trySetObjectLegalHold();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectLegalHold_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setObjectLegalHold(any(SetObjectLegalHoldRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetObjectLegalHold();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectLegalHold_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setObjectLegalHold(any(SetObjectLegalHoldRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetObjectLegalHold();
    }

    @Test
    public void testTryGetObjectLegalHold() {
        // Setup
        // Configure AmazonS3Client.getObjectLegalHold(...).
        final GetObjectLegalHoldResult getObjectLegalHoldResult = new GetObjectLegalHoldResult();
        when(mockAmazonS3Client.getObjectLegalHold(any(GetObjectLegalHoldRequest.class)))
                .thenReturn(getObjectLegalHoldResult);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectLegalHold_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectLegalHold(any(GetObjectLegalHoldRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectLegalHold_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectLegalHold(any(GetObjectLegalHoldRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectLegalHold();
    }

    @Test
    public void testTrySetObjectLockConfiguration() {
        // Setup
        // Configure AmazonS3Client.setObjectLockConfiguration(...).
        final SetObjectLockConfigurationResult setObjectLockConfigurationResult = new SetObjectLockConfigurationResult();
        when(mockAmazonS3Client.setObjectLockConfiguration(any(SetObjectLockConfigurationRequest.class)))
                .thenReturn(setObjectLockConfigurationResult);

        // Run the test
        myClassUnderTest.trySetObjectLockConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectLockConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setObjectLockConfiguration(any(SetObjectLockConfigurationRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetObjectLockConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectLockConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setObjectLockConfiguration(any(SetObjectLockConfigurationRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetObjectLockConfiguration();
    }

    @Test
    public void testTryGetObjectLockConfiguration() {
        // Setup
        // Configure AmazonS3Client.getObjectLockConfiguration(...).
        final GetObjectLockConfigurationResult getObjectLockConfigurationResult = new GetObjectLockConfigurationResult();
        when(mockAmazonS3Client.getObjectLockConfiguration(any(GetObjectLockConfigurationRequest.class)))
                .thenReturn(getObjectLockConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectLockConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectLockConfiguration(any(GetObjectLockConfigurationRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectLockConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectLockConfiguration(any(GetObjectLockConfigurationRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectLockConfiguration();
    }

    @Test
    public void testTrySetObjectRetention() {
        // Setup
        // Configure AmazonS3Client.setObjectRetention(...).
        final SetObjectRetentionResult setObjectRetentionResult = new SetObjectRetentionResult();
        when(mockAmazonS3Client.setObjectRetention(any(SetObjectRetentionRequest.class)))
                .thenReturn(setObjectRetentionResult);

        // Run the test
        myClassUnderTest.trySetObjectRetention();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetObjectRetention_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setObjectRetention(any(SetObjectRetentionRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetObjectRetention();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetObjectRetention_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setObjectRetention(any(SetObjectRetentionRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetObjectRetention();
    }

    @Test
    public void testTryGetObjectRetention() {
        // Setup
        // Configure AmazonS3Client.getObjectRetention(...).
        final GetObjectRetentionResult getObjectRetentionResult = new GetObjectRetentionResult();
        when(mockAmazonS3Client.getObjectRetention(any(GetObjectRetentionRequest.class)))
                .thenReturn(getObjectRetentionResult);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetObjectRetention_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getObjectRetention(any(GetObjectRetentionRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetObjectRetention_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getObjectRetention(any(GetObjectRetentionRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetObjectRetention();
    }

    @Test
    public void testTryGeneratePresignedUrl() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGeneratePresignedUrl_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl();
    }

    @Test
    public void testTryGeneratePresignedUrl1() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), HttpMethod.GET))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl1();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGeneratePresignedUrl1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl("bucketName", "key",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), HttpMethod.GET))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl1();
    }

    @Test
    public void testTryGeneratePresignedUrl2() throws Exception {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl2();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryGeneratePresignedUrl2_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGeneratePresignedUrl2();
    }

    @Test
    public void testTryAbortMultipartUpload() {
        // Setup
        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();

        // Verify the results
        verify(mockAmazonS3Client).abortMultipartUpload(any(AbortMultipartUploadRequest.class));
    }

    @Test(expected = SdkClientException.class)
    public void testTryAbortMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).abortMultipartUpload(
                any(AbortMultipartUploadRequest.class));

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryAbortMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).abortMultipartUpload(
                any(AbortMultipartUploadRequest.class));

        // Run the test
        myClassUnderTest.tryAbortMultipartUpload();
    }

    @Test
    public void testTryCompleteMultipartUpload() {
        // Setup
        // Configure AmazonS3Client.completeMultipartUpload(...).
        final CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenReturn(completeMultipartUploadResult);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryCompleteMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryCompleteMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.completeMultipartUpload(any(CompleteMultipartUploadRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryCompleteMultipartUpload();
    }

    @Test
    public void testTryInitiateMultipartUpload() {
        // Setup
        // Configure AmazonS3Client.initiateMultipartUpload(...).
        final InitiateMultipartUploadResult initiateMultipartUploadResult = new InitiateMultipartUploadResult();
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenReturn(initiateMultipartUploadResult);

        // Run the test
        myClassUnderTest.tryInitiateMultipartUpload();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryInitiateMultipartUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryInitiateMultipartUpload();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryInitiateMultipartUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.initiateMultipartUpload(any(InitiateMultipartUploadRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryInitiateMultipartUpload();
    }

    @Test
    public void testTryListMultipartUploads() {
        // Setup
        // Configure AmazonS3Client.listMultipartUploads(...).
        final MultipartUploadListing multipartUploadListing = new MultipartUploadListing();
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenReturn(multipartUploadListing);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListMultipartUploads_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListMultipartUploads_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listMultipartUploads(any(ListMultipartUploadsRequest.class)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListMultipartUploads();
    }

    @Test
    public void testTryListParts() {
        // Setup
        // Configure AmazonS3Client.listParts(...).
        final PartListing partListing = new PartListing();
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenReturn(partListing);

        // Run the test
        myClassUnderTest.tryListParts();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryListParts_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListParts();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListParts_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listParts(any(ListPartsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListParts();
    }

    @Test
    public void testTryUploadPart() {
        // Setup
        // Configure AmazonS3Client.uploadPart(...).
        final UploadPartResult uploadPartResult = new UploadPartResult();
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenReturn(uploadPartResult);

        // Run the test
        myClassUnderTest.tryUploadPart();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryUploadPart_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryUploadPart();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryUploadPart_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.uploadPart(any(UploadPartRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryUploadPart();
    }

    @Test
    public void testTryGetCachedResponseMetadata() {
        // Setup
        when(mockAmazonS3Client.getCachedResponseMetadata(any(AmazonWebServiceRequest.class)))
                .thenReturn(new S3ResponseMetadata(new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    public void testTryGetCachedResponseMetadata_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getCachedResponseMetadata(any(AmazonWebServiceRequest.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetCachedResponseMetadata();

        // Verify the results
    }

    @Test
    public void testTryRestoreObject() {
        // Setup
        // Run the test
        myClassUnderTest.tryRestoreObject();

        // Verify the results
        verify(mockAmazonS3Client).restoreObject(new RestoreObjectRequest("bucketName", "key", 0));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryRestoreObject_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).restoreObject(
                new RestoreObjectRequest("bucketName", "key", 0));

        // Run the test
        myClassUnderTest.tryRestoreObject();
    }

    @Test
    public void testTryRestoreObjectV2() {
        // Setup
        // Configure AmazonS3Client.restoreObjectV2(...).
        final RestoreObjectResult restoreObjectResult = new RestoreObjectResult();
        when(mockAmazonS3Client.restoreObjectV2(new RestoreObjectRequest("bucketName", "key", 0)))
                .thenReturn(restoreObjectResult);

        // Run the test
        myClassUnderTest.tryRestoreObjectV2();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryRestoreObjectV2_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.restoreObjectV2(new RestoreObjectRequest("bucketName", "key", 0)))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryRestoreObjectV2();
    }

    @Test
    public void testTryRestoreObject1() {
        // Setup
        // Run the test
        myClassUnderTest.tryRestoreObject1();

        // Verify the results
        verify(mockAmazonS3Client).restoreObject("bucketName", "key", 0);
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryRestoreObject1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).restoreObject("bucketName", "key", 0);

        // Run the test
        myClassUnderTest.tryRestoreObject1();
    }

    @Test
    public void testTryPutObject3() {
        // Setup
        // Configure AmazonS3Client.putObject(...).
        final PutObjectResult putObjectResult = new PutObjectResult();
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenReturn(putObjectResult);

        // Run the test
        myClassUnderTest.tryPutObject3();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryPutObject3_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryPutObject3();
    }

    @Test(expected = SdkClientException.class)
    public void testTryPutObject3_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.putObject("bucketName", "key", "content")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryPutObject3();
    }

    @Test
    public void testTryGetResourceUrl() {
        // Setup
        when(mockAmazonS3Client.getResourceUrl("bucketName", "key")).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetResourceUrl();

        // Verify the results
    }

    @Test
    public void testTryGetResourceUrl_AmazonS3ClientReturnsNull() {
        // Setup
        when(mockAmazonS3Client.getResourceUrl("bucketName", "key")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetResourceUrl();

        // Verify the results
    }

    @Test
    public void testTryGetUrl() throws Exception {
        // Setup
        when(mockAmazonS3Client.getUrl("bucketName", "key")).thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.tryGetUrl();

        // Verify the results
    }

    @Test
    public void testTryGetRegion() {
        // Setup
        when(mockAmazonS3Client.getRegion()).thenReturn(com.amazonaws.services.s3.model.Region.US_Standard);

        // Run the test
        myClassUnderTest.tryGetRegion();

        // Verify the results
    }

    @Test
    public void testTryGetRegionName() {
        // Setup
        when(mockAmazonS3Client.getRegionName()).thenReturn("result");

        // Run the test
        myClassUnderTest.tryGetRegionName();

        // Verify the results
    }

    @Test
    public void testTryDownload() throws Exception {
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

    @Test(expected = SdkClientException.class)
    public void testTryDownload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.download(any(PresignedUrlDownloadRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDownload();
    }

    @Test
    public void testTryDownload1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDownload1();

        // Verify the results
        verify(mockAmazonS3Client).download(any(PresignedUrlDownloadRequest.class), eq(new File("filename.txt")));
    }

    @Test(expected = SdkClientException.class)
    public void testTryDownload1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).download(any(PresignedUrlDownloadRequest.class),
                eq(new File("filename.txt")));

        // Run the test
        myClassUnderTest.tryDownload1();
    }

    @Test
    public void testTryUpload() {
        // Setup
        // Configure AmazonS3Client.upload(...).
        final PresignedUrlUploadResult presignedUrlUploadResult = new PresignedUrlUploadResult();
        when(mockAmazonS3Client.upload(any(PresignedUrlUploadRequest.class))).thenReturn(presignedUrlUploadResult);

        // Run the test
        myClassUnderTest.tryUpload();

        // Verify the results
    }

    @Test(expected = SdkClientException.class)
    public void testTryUpload_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.upload(any(PresignedUrlUploadRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryUpload();
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryUpload_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.upload(any(PresignedUrlUploadRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryUpload();
    }

    @Test
    public void testTryEnableRequesterPays() {
        // Setup
        // Run the test
        myClassUnderTest.tryEnableRequesterPays();

        // Verify the results
        verify(mockAmazonS3Client).enableRequesterPays("bucketName");
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryEnableRequesterPays_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).enableRequesterPays("bucketName");

        // Run the test
        myClassUnderTest.tryEnableRequesterPays();
    }

    @Test(expected = SdkClientException.class)
    public void testTryEnableRequesterPays_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).enableRequesterPays("bucketName");

        // Run the test
        myClassUnderTest.tryEnableRequesterPays();
    }

    @Test
    public void testTryDisableRequesterPays() {
        // Setup
        // Run the test
        myClassUnderTest.tryDisableRequesterPays();

        // Verify the results
        verify(mockAmazonS3Client).disableRequesterPays("bucketName");
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDisableRequesterPays_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).disableRequesterPays("bucketName");

        // Run the test
        myClassUnderTest.tryDisableRequesterPays();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDisableRequesterPays_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).disableRequesterPays("bucketName");

        // Run the test
        myClassUnderTest.tryDisableRequesterPays();
    }

    @Test
    public void testTryIsRequesterPaysEnabled() {
        // Setup
        when(mockAmazonS3Client.isRequesterPaysEnabled("bucketName")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryIsRequesterPaysEnabled();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryIsRequesterPaysEnabled_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.isRequesterPaysEnabled("bucketName")).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryIsRequesterPaysEnabled();
    }

    @Test(expected = SdkClientException.class)
    public void testTryIsRequesterPaysEnabled_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.isRequesterPaysEnabled("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryIsRequesterPaysEnabled();
    }

    @Test
    public void testTrySetBucketReplicationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).setBucketReplicationConfiguration(eq("bucketName"),
                any(BucketReplicationConfiguration.class));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                eq("bucketName"), any(BucketReplicationConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(eq("bucketName"),
                any(BucketReplicationConfiguration.class));

        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration();
    }

    @Test
    public void testTrySetBucketReplicationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).setBucketReplicationConfiguration(
                any(SetBucketReplicationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.trySetBucketReplicationConfiguration1();
    }

    @Test
    public void testTryGetBucketReplicationConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketReplicationConfiguration(...).
        final BucketReplicationConfiguration bucketReplicationConfiguration = new BucketReplicationConfiguration();
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName"))
                .thenReturn(bucketReplicationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration("bucketName")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration();
    }

    @Test
    public void testTryGetBucketReplicationConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketReplicationConfiguration(...).
        final BucketReplicationConfiguration bucketReplicationConfiguration = new BucketReplicationConfiguration();
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenReturn(bucketReplicationConfiguration);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketReplicationConfiguration(
                any(GetBucketReplicationConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketReplicationConfiguration1();
    }

    @Test
    public void testTryDeleteBucketReplicationConfiguration() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketReplicationConfiguration("bucketName");
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketReplicationConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                "bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketReplicationConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration("bucketName");

        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration();
    }

    @Test
    public void testTryDeleteBucketReplicationConfiguration1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration1();

        // Verify the results
        verify(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketReplicationConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        doThrow(AmazonServiceException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketReplicationConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        doThrow(SdkClientException.class).when(mockAmazonS3Client).deleteBucketReplicationConfiguration(
                any(DeleteBucketReplicationConfigurationRequest.class));

        // Run the test
        myClassUnderTest.tryDeleteBucketReplicationConfiguration1();
    }

    @Test
    public void testTryDeleteBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResult deleteBucketMetricsConfigurationResult = new DeleteBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration();
    }

    @Test
    public void testTryDeleteBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.deleteBucketMetricsConfiguration(...).
        final DeleteBucketMetricsConfigurationResult deleteBucketMetricsConfigurationResult = new DeleteBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenReturn(deleteBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketMetricsConfiguration(
                any(DeleteBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketMetricsConfiguration1();
    }

    @Test
    public void testTryGetBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResult getBucketMetricsConfigurationResult = new GetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id"))
                .thenReturn(getBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration("bucketName", "id")).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration();
    }

    @Test
    public void testTryGetBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketMetricsConfiguration(...).
        final GetBucketMetricsConfigurationResult getBucketMetricsConfigurationResult = new GetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenReturn(getBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketMetricsConfiguration(
                any(GetBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketMetricsConfiguration1();
    }

    @Test
    public void testTrySetBucketMetricsConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketMetricsConfiguration(...).
        final SetBucketMetricsConfigurationResult setBucketMetricsConfigurationResult = new SetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenReturn(setBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketMetricsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketMetricsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(eq("bucketName"),
                any(MetricsConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration();
    }

    @Test
    public void testTrySetBucketMetricsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketMetricsConfiguration(...).
        final SetBucketMetricsConfigurationResult setBucketMetricsConfigurationResult = new SetBucketMetricsConfigurationResult();
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenReturn(setBucketMetricsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketMetricsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketMetricsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketMetricsConfiguration(
                any(SetBucketMetricsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketMetricsConfiguration1();
    }

    @Test
    public void testTryListBucketMetricsConfigurations() {
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
    public void testTryListBucketMetricsConfigurations_AmazonS3ClientReturnsNoItems() {
        // Setup
        // Configure AmazonS3Client.listBucketMetricsConfigurations(...).
        final ListBucketMetricsConfigurationsResult listBucketMetricsConfigurationsResult = new ListBucketMetricsConfigurationsResult();
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenReturn(listBucketMetricsConfigurationsResult);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryListBucketMetricsConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();
    }

    @Test(expected = SdkClientException.class)
    public void testTryListBucketMetricsConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketMetricsConfigurations(
                any(ListBucketMetricsConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListBucketMetricsConfigurations();
    }

    @Test
    public void testTryDeleteBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketAnalyticsConfiguration(...).
        final DeleteBucketAnalyticsConfigurationResult deleteBucketAnalyticsConfigurationResult = new DeleteBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration();
    }

    @Test
    public void testTryDeleteBucketAnalyticsConfiguration1() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration(
                any(DeleteBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketAnalyticsConfiguration(
                any(DeleteBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketAnalyticsConfiguration1();
    }

    @Test
    public void testTryGetBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResult getBucketAnalyticsConfigurationResult = new GetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenReturn(getBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration();
    }

    @Test
    public void testTryGetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketAnalyticsConfiguration(...).
        final GetBucketAnalyticsConfigurationResult getBucketAnalyticsConfigurationResult = new GetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenReturn(getBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketAnalyticsConfiguration(
                any(GetBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketAnalyticsConfiguration1();
    }

    @Test
    public void testTrySetBucketAnalyticsConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketAnalyticsConfiguration(...).
        final SetBucketAnalyticsConfigurationResult setBucketAnalyticsConfigurationResult = new SetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenReturn(setBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAnalyticsConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAnalyticsConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(eq("bucketName"),
                any(AnalyticsConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration();
    }

    @Test
    public void testTrySetBucketAnalyticsConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketAnalyticsConfiguration(...).
        final SetBucketAnalyticsConfigurationResult setBucketAnalyticsConfigurationResult = new SetBucketAnalyticsConfigurationResult();
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenReturn(setBucketAnalyticsConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketAnalyticsConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketAnalyticsConfiguration(
                any(SetBucketAnalyticsConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketAnalyticsConfiguration1();
    }

    @Test
    public void testTryListBucketAnalyticsConfigurations() {
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
    public void testTryListBucketAnalyticsConfigurations_AmazonS3ClientReturnsNoItems() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryListBucketAnalyticsConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();
    }

    @Test(expected = SdkClientException.class)
    public void testTryListBucketAnalyticsConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketAnalyticsConfigurations(
                any(ListBucketAnalyticsConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListBucketAnalyticsConfigurations();
    }

    @Test
    public void testTryDeleteBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.deleteBucketInventoryConfiguration(...).
        final DeleteBucketInventoryConfigurationResult deleteBucketInventoryConfigurationResult = new DeleteBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenReturn(deleteBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration();
    }

    @Test
    public void testTryDeleteBucketInventoryConfiguration1() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryDeleteBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration(
                any(DeleteBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryDeleteBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.deleteBucketInventoryConfiguration(
                any(DeleteBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryDeleteBucketInventoryConfiguration1();
    }

    @Test
    public void testTryGetBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResult getBucketInventoryConfigurationResult = new GetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenReturn(getBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration("bucketName", "id"))
                .thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration();
    }

    @Test
    public void testTryGetBucketInventoryConfiguration1() {
        // Setup
        // Configure AmazonS3Client.getBucketInventoryConfiguration(...).
        final GetBucketInventoryConfigurationResult getBucketInventoryConfigurationResult = new GetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenReturn(getBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTryGetBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTryGetBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.getBucketInventoryConfiguration(
                any(GetBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryGetBucketInventoryConfiguration1();
    }

    @Test
    public void testTrySetBucketInventoryConfiguration() {
        // Setup
        // Configure AmazonS3Client.setBucketInventoryConfiguration(...).
        final SetBucketInventoryConfigurationResult setBucketInventoryConfigurationResult = new SetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenReturn(setBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketInventoryConfiguration_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketInventoryConfiguration_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(eq("bucketName"),
                any(InventoryConfiguration.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration();
    }

    @Test
    public void testTrySetBucketInventoryConfiguration1() {
        // Setup
        // Configure AmazonS3Client.setBucketInventoryConfiguration(...).
        final SetBucketInventoryConfigurationResult setBucketInventoryConfigurationResult = new SetBucketInventoryConfigurationResult();
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenReturn(setBucketInventoryConfigurationResult);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration1();

        // Verify the results
    }

    @Test(expected = AmazonServiceException.class)
    public void testTrySetBucketInventoryConfiguration1_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration1();
    }

    @Test(expected = SdkClientException.class)
    public void testTrySetBucketInventoryConfiguration1_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.setBucketInventoryConfiguration(
                any(SetBucketInventoryConfigurationRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.trySetBucketInventoryConfiguration1();
    }

    @Test
    public void testTryListBucketInventoryConfigurations() {
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

    @Test(expected = AmazonServiceException.class)
    public void testTryListBucketInventoryConfigurations_AmazonS3ClientThrowsAmazonServiceException() {
        // Setup
        when(mockAmazonS3Client.listBucketInventoryConfigurations(
                any(ListBucketInventoryConfigurationsRequest.class))).thenThrow(AmazonServiceException.class);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations();
    }

    @Test(expected = SdkClientException.class)
    public void testTryListBucketInventoryConfigurations_AmazonS3ClientThrowsSdkClientException() {
        // Setup
        when(mockAmazonS3Client.listBucketInventoryConfigurations(
                any(ListBucketInventoryConfigurationsRequest.class))).thenThrow(SdkClientException.class);

        // Run the test
        myClassUnderTest.tryListBucketInventoryConfigurations();
    }

    @Test
    public void testTryWaiters() {
        // Setup
        when(mockAmazonS3Client.waiters()).thenReturn(new AmazonS3Waiters(null));

        // Run the test
        myClassUnderTest.tryWaiters();

        // Verify the results
    }

    @Test
    public void testTryBuilder() {
        // Setup
        // Run the test
        myClassUnderTest.tryBuilder();

        // Verify the results
    }
}
