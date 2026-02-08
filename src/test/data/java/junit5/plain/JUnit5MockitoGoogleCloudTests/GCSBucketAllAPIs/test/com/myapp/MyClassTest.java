package com.myapp;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Bucket mockBucket;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockBucket);
    }

    @Test
    void testTryExists() {
        // Setup
        when(mockBucket.exists(Bucket.BucketSourceOption.userProject("userProject"))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryExists();

        // Verify the results
    }

    @Test
    void testTryExists_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.exists(Bucket.BucketSourceOption.userProject("userProject"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryExists());
    }

    @Test
    void testTryReload() {
        // Setup
        // Configure Bucket.reload(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockBucket.reload(Bucket.BucketSourceOption.userProject("userProject"))).thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryReload();

        // Verify the results
    }

    @Test
    void testTryReload_BucketReturnsNull() {
        // Setup
        when(mockBucket.reload(Bucket.BucketSourceOption.userProject("userProject"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryReload();

        // Verify the results
    }

    @Test
    void testTryReload_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.reload(Bucket.BucketSourceOption.userProject("userProject"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryReload());
    }

    @Test
    void testTryUpdate() {
        // Setup
        // Configure Bucket.update(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockBucket.update(
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryUpdate();

        // Verify the results
    }

    @Test
    void testTryUpdate_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.update(
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate());
    }

    @Test
    void testTryDelete() {
        // Setup
        when(mockBucket.delete(Bucket.BucketSourceOption.userProject("userProject"))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete();

        // Verify the results
    }

    @Test
    void testTryDelete_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.delete(Bucket.BucketSourceOption.userProject("userProject"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete());
    }

    @Test
    void testTryList() {
        // Setup
        // Configure Bucket.list(...).
        final Page<Blob> mockPage = mock(Page.class);
        when(mockBucket.list(Storage.BlobListOption.prefix("prefix"))).thenReturn(mockPage);

        // Run the test
        myClassUnderTest.tryList();

        // Verify the results
    }

    @Test
    void testTryList_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.list(Storage.BlobListOption.prefix("prefix"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryList());
    }

    @Test
    void testTryGet1() {
        // Setup
        // Configure Bucket.get(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockBucket.get("blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryGet1();

        // Verify the results
    }

    @Test
    void testTryGet1_BucketReturnsNull() {
        // Setup
        when(mockBucket.get("blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet1();

        // Verify the results
    }

    @Test
    void testTryGet1_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.get("blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet1());
    }

    @Test
    void testTryGet2() {
        // Setup
        when(mockBucket.get("blobName1", "blobName2", "blobNames")).thenReturn(Arrays.asList());

        // Run the test
        myClassUnderTest.tryGet2();

        // Verify the results
    }

    @Test
    void testTryGet2_BucketReturnsNoItems() {
        // Setup
        when(mockBucket.get("blobName1", "blobName2", "blobNames")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryGet2();

        // Verify the results
    }

    @Test
    void testTryGet2_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.get("blobName1", "blobName2", "blobNames")).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet2());
    }

    @Test
    void testTryGet3() {
        // Setup
        when(mockBucket.get(any(Iterable.class))).thenReturn(Arrays.asList());

        // Run the test
        myClassUnderTest.tryGet3();

        // Verify the results
    }

    @Test
    void testTryGet3_BucketReturnsNoItems() {
        // Setup
        when(mockBucket.get(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryGet3();

        // Verify the results
    }

    @Test
    void testTryGet3_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.get(any(Iterable.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet3());
    }

    @Test
    void testTryCreate1() {
        // Setup
        // Configure Bucket.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockBucket.create(eq("blob"), any(byte[].class), eq("contentType"),
                eq(Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate1();

        // Verify the results
    }

    @Test
    void testTryCreate1_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.create(eq("blob"), any(byte[].class), eq("contentType"),
                eq(Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate1());
    }

    @Test
    void testTryCreate2() {
        // Setup
        // Configure Bucket.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockBucket.create(eq("blob"), any(InputStream.class), eq("contentType"),
                eq(Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate2();

        // Verify the results
    }

    @Test
    void testTryCreate2_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.create(eq("blob"), any(InputStream.class), eq("contentType"),
                eq(Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate2());
    }

    @Test
    void testTryCreate3() {
        // Setup
        // Configure Bucket.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockBucket.create(eq("blob"), any(byte[].class),
                eq(Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate3();

        // Verify the results
    }

    @Test
    void testTryCreate3_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.create(eq("blob"), any(byte[].class),
                eq(Bucket.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate3());
    }

    @Test
    void testTryCreate4() {
        // Setup
        // Configure Bucket.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockBucket.create(eq("blob"), any(InputStream.class),
                eq(Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate4();

        // Verify the results
    }

    @Test
    void testTryCreate4_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.create(eq("blob"), any(InputStream.class),
                eq(Bucket.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate4());
    }

    @Test
    void testTryGetAcl() {
        // Setup
        // Configure Bucket.getAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.getAcl(null)).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetAcl();

        // Verify the results
    }

    @Test
    void testTryGetAcl_BucketReturnsNull() {
        // Setup
        when(mockBucket.getAcl(null)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetAcl();

        // Verify the results
    }

    @Test
    void testTryGetAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.getAcl(null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetAcl());
    }

    @Test
    void testTryDeleteAcl() {
        // Setup
        when(mockBucket.deleteAcl(null)).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteAcl();

        // Verify the results
    }

    @Test
    void testTryDeleteAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.deleteAcl(null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteAcl());
    }

    @Test
    void testTryCreateAcl() {
        // Setup
        // Configure Bucket.createAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.createAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateAcl();

        // Verify the results
    }

    @Test
    void testTryCreateAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.createAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateAcl());
    }

    @Test
    void testTryUpdateAcl() {
        // Setup
        // Configure Bucket.updateAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.updateAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateAcl();

        // Verify the results
    }

    @Test
    void testTryUpdateAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.updateAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateAcl());
    }

    @Test
    void testTryListAcls() {
        // Setup
        // Configure Bucket.listAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockBucket.listAcls()).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListAcls();

        // Verify the results
    }

    @Test
    void testTryListAcls_BucketReturnsNoItems() {
        // Setup
        when(mockBucket.listAcls()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListAcls();

        // Verify the results
    }

    @Test
    void testTryListAcls_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.listAcls()).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListAcls());
    }

    @Test
    void testTryGetDefaultAcl() {
        // Setup
        // Configure Bucket.getDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.getDefaultAcl(null)).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryGetDefaultAcl_BucketReturnsNull() {
        // Setup
        when(mockBucket.getDefaultAcl(null)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryGetDefaultAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.getDefaultAcl(null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetDefaultAcl());
    }

    @Test
    void testTryDeleteDefaultAcl() {
        // Setup
        when(mockBucket.deleteDefaultAcl(null)).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryDeleteDefaultAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.deleteDefaultAcl(null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteDefaultAcl());
    }

    @Test
    void testTryCreateDefaultAcl() {
        // Setup
        // Configure Bucket.createDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.createDefaultAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryCreateDefaultAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.createDefaultAcl(Acl.of(null, Acl.Role.valueOfStrict("constant"))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateDefaultAcl());
    }

    @Test
    void testTryUpdateDefaultAcl() {
        // Setup
        // Configure Bucket.updateDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockBucket.updateDefaultAcl(Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryUpdateDefaultAcl_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.updateDefaultAcl(Acl.of(null, Acl.Role.valueOfStrict("constant"))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateDefaultAcl());
    }

    @Test
    void testTryListDefaultAcls() {
        // Setup
        // Configure Bucket.listDefaultAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockBucket.listDefaultAcls()).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListDefaultAcls();

        // Verify the results
    }

    @Test
    void testTryListDefaultAcls_BucketReturnsNoItems() {
        // Setup
        when(mockBucket.listDefaultAcls()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListDefaultAcls();

        // Verify the results
    }

    @Test
    void testTryListDefaultAcls_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.listDefaultAcls()).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListDefaultAcls());
    }

    @Test
    void testTryLockRetentionPolicy() {
        // Setup
        // Configure Bucket.lockRetentionPolicy(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockBucket.lockRetentionPolicy(
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryLockRetentionPolicy();

        // Verify the results
    }

    @Test
    void testTryLockRetentionPolicy_BucketThrowsStorageException() {
        // Setup
        when(mockBucket.lockRetentionPolicy(
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryLockRetentionPolicy());
    }

    @Test
    void testTryGetStorage() {
        // Setup
        when(mockBucket.getStorage()).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetStorage();

        // Verify the results
    }

    @Test
    void testTryToBuilder() {
        // Setup
        when(mockBucket.toBuilder()).thenReturn(null);

        // Run the test
        myClassUnderTest.tryToBuilder();

        // Verify the results
    }

    @Test
    void testTryEquals() {
        // Setup
        when(mockBucket.equals("obj")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryEquals();

        // Verify the results
    }

    @Test
    void testTryHashCode() {
        // Setup
        when(mockBucket.hashCode()).thenReturn(0);

        // Run the test
        myClassUnderTest.tryHashCode();

        // Verify the results
    }
}
