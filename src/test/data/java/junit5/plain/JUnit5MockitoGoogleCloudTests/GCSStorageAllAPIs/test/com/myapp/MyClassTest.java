package com.myapp;

import com.google.api.gax.paging.Page;
import com.google.auth.ServiceAccountSigner;
import com.google.cloud.Policy;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Storage mockStorage;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockStorage);
    }

    @Test
    void testTryCreate1() {
        // Setup
        // Configure Storage.create(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockStorage.create(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryCreate1();

        // Verify the results
    }

    @Test
    void testTryCreate1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.create(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate1());
    }

    @Test
    void testTryCreate2() {
        // Setup
        // Configure Storage.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.create(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate2();

        // Verify the results
    }

    @Test
    void testTryCreate2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.create(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate2());
    }

    @Test
    void testTryCreate3() {
        // Setup
        // Configure Storage.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(byte[].class),
                eq(Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate3();

        // Verify the results
    }

    @Test
    void testTryCreate3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(byte[].class),
                eq(Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate3());
    }

    @Test
    void testTryCreate4() {
        // Setup
        // Configure Storage.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(byte[].class), eq(0),
                eq(0),
                eq(Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate4();

        // Verify the results
    }

    @Test
    void testTryCreate4_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(byte[].class), eq(0),
                eq(0), eq(Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate4());
    }

    @Test
    void testTryCreate5() {
        // Setup
        // Configure Storage.create(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(InputStream.class),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreate5();

        // Verify the results
    }

    @Test
    void testTryCreate5_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.create(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), any(InputStream.class),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreate5());
    }

    @Test
    void testTryCreateFrom1() throws Exception {
        // Setup
        // Configure Storage.createFrom(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.createFrom(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(), Paths.get("filename.txt"),
                Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreateFrom1();

        // Verify the results
    }

    @Test
    void testTryCreateFrom1_StorageThrowsIOException() throws Exception {
        // Setup
        when(mockStorage.createFrom(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(), Paths.get("filename.txt"),
                Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.tryCreateFrom1());
    }

    @Test
    void testTryCreateFrom2() throws Exception {
        // Setup
        // Configure Storage.createFrom(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.createFrom(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(), Paths.get("filename.txt"),
                0, Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreateFrom2();

        // Verify the results
    }

    @Test
    void testTryCreateFrom2_StorageThrowsIOException() throws Exception {
        // Setup
        when(mockStorage.createFrom(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(), Paths.get("filename.txt"),
                0, Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.tryCreateFrom2());
    }

    @Test
    void testTryCreateFrom3() throws Exception {
        // Setup
        // Configure Storage.createFrom(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.createFrom(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                any(InputStream.class),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreateFrom3();

        // Verify the results
    }

    @Test
    void testTryCreateFrom3_StorageThrowsIOException() throws Exception {
        // Setup
        when(mockStorage.createFrom(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                any(InputStream.class),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.tryCreateFrom3());
    }

    @Test
    void testTryCreateFrom4() throws Exception {
        // Setup
        // Configure Storage.createFrom(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.createFrom(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                any(InputStream.class), eq(0),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCreateFrom4();

        // Verify the results
    }

    @Test
    void testTryCreateFrom4_StorageThrowsIOException() throws Exception {
        // Setup
        when(mockStorage.createFrom(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                any(InputStream.class), eq(0),
                eq(Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))))
                .thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.tryCreateFrom4());
    }

    @Test
    void testTryGet1() {
        // Setup
        // Configure Storage.get(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockStorage.get("bucket", Storage.BucketGetOption.metagenerationMatch(0L))).thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryGet1();

        // Verify the results
    }

    @Test
    void testTryGet1_StorageReturnsNull() {
        // Setup
        when(mockStorage.get("bucket", Storage.BucketGetOption.metagenerationMatch(0L))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet1();

        // Verify the results
    }

    @Test
    void testTryGet1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get("bucket", Storage.BucketGetOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet1());
    }

    @Test
    void testTryGet2() {
        // Setup
        // Configure Storage.get(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.get("bucket", "blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK)))
                .thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryGet2();

        // Verify the results
    }

    @Test
    void testTryGet2_StorageReturnsNull() {
        // Setup
        when(mockStorage.get("bucket", "blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK)))
                .thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet2();

        // Verify the results
    }

    @Test
    void testTryGet2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get("bucket", "blob", Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet2());
    }

    @Test
    void testTryGet3() {
        // Setup
        // Configure Storage.get(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.get(BlobId.of("bucket", "name"),
                Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryGet3();

        // Verify the results
    }

    @Test
    void testTryGet3_StorageReturnsNull() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"),
                Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet3();

        // Verify the results
    }

    @Test
    void testTryGet3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"),
                Storage.BlobGetOption.fields(Storage.BlobField.MEDIA_LINK))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet3());
    }

    @Test
    void testTryGet4() {
        // Setup
        // Configure Storage.get(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryGet4();

        // Verify the results
    }

    @Test
    void testTryGet4_StorageReturnsNull() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet4();

        // Verify the results
    }

    @Test
    void testTryGet4_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet4());
    }

    @Test
    void testTryList1() {
        // Setup
        // Configure Storage.list(...).
        final Page<Bucket> mockPage = mock(Page.class);
        when(mockStorage.list(Storage.BucketListOption.pageSize(0L))).thenReturn(mockPage);

        // Run the test
        myClassUnderTest.tryList1();

        // Verify the results
    }

    @Test
    void testTryList1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.list(Storage.BucketListOption.pageSize(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryList1());
    }

    @Test
    void testTryList2() {
        // Setup
        // Configure Storage.list(...).
        final Page<Blob> mockPage = mock(Page.class);
        when(mockStorage.list("bucket", Storage.BlobListOption.prefix("prefix"))).thenReturn(mockPage);

        // Run the test
        myClassUnderTest.tryList2();

        // Verify the results
    }

    @Test
    void testTryList2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.list("bucket", Storage.BlobListOption.prefix("prefix"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryList2());
    }

    @Test
    void testTryUpdate1() {
        // Setup
        // Configure Storage.update(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockStorage.update(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryUpdate1();

        // Verify the results
    }

    @Test
    void testTryUpdate1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.update(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate1());
    }

    @Test
    void testTryUpdate2() {
        // Setup
        // Configure Storage.update(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryUpdate2();

        // Verify the results
    }

    @Test
    void testTryUpdate2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate2());
    }

    @Test
    void testTryUpdate3() {
        // Setup
        // Configure Storage.update(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build())).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryUpdate3();

        // Verify the results
    }

    @Test
    void testTryUpdate3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate3());
    }

    @Test
    void testTryDelete1() {
        // Setup
        when(mockStorage.delete("bucket", Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete1();

        // Verify the results
    }

    @Test
    void testTryDelete1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete("bucket", Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete1());
    }

    @Test
    void testTryDelete2() {
        // Setup
        when(mockStorage.delete("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete2();

        // Verify the results
    }

    @Test
    void testTryDelete2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete2());
    }

    @Test
    void testTryDelete3() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"), Storage.BlobSourceOption.generationMatch(0L)))
                .thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete3();

        // Verify the results
    }

    @Test
    void testTryDelete3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"), Storage.BlobSourceOption.generationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete3());
    }

    @Test
    void testTryDelete4() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete4();

        // Verify the results
    }

    @Test
    void testTryDelete4_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete4());
    }

    @Test
    void testTryCompose() {
        // Setup
        // Configure Storage.compose(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.compose(any(Storage.ComposeRequest.class))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryCompose();

        // Verify the results
    }

    @Test
    void testTryCompose_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.compose(any(Storage.ComposeRequest.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCompose());
    }

    @Test
    void testTryCopy() {
        // Setup
        // Configure Storage.copy(...).
        final CopyWriter mockCopyWriter = mock(CopyWriter.class);
        when(mockStorage.copy(any(Storage.CopyRequest.class))).thenReturn(mockCopyWriter);

        // Run the test
        myClassUnderTest.tryCopy();

        // Verify the results
    }

    @Test
    void testTryCopy_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.copy(any(Storage.CopyRequest.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCopy());
    }

    @Test
    void testTryReadAllBytes1() {
        // Setup
        when(mockStorage.readAllBytes("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L)))
                .thenReturn("content".getBytes());

        // Run the test
        myClassUnderTest.tryReadAllBytes1();

        // Verify the results
    }

    @Test
    void testTryReadAllBytes1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.readAllBytes("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryReadAllBytes1());
    }

    @Test
    void testTryReadAllBytes2() {
        // Setup
        when(mockStorage.readAllBytes(BlobId.of("bucket", "name"),
                Storage.BlobSourceOption.generationMatch(0L))).thenReturn("content".getBytes());

        // Run the test
        myClassUnderTest.tryReadAllBytes2();

        // Verify the results
    }

    @Test
    void testTryReadAllBytes2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.readAllBytes(BlobId.of("bucket", "name"),
                Storage.BlobSourceOption.generationMatch(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryReadAllBytes2());
    }

    @Test
    void testTryBatch() {
        // Setup
        when(mockStorage.batch()).thenReturn(null);

        // Run the test
        myClassUnderTest.tryBatch();

        // Verify the results
    }

    @Test
    void testTryReader1() {
        // Setup
        // Configure Storage.reader(...).
        final ReadChannel mockReadChannel = mock(ReadChannel.class);
        when(mockStorage.reader("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L)))
                .thenReturn(mockReadChannel);

        // Run the test
        myClassUnderTest.tryReader1();

        // Verify the results
        verify(mockReadChannel).close();
    }

    @Test
    void testTryReader1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.reader("bucket", "blob", Storage.BlobSourceOption.generationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryReader1());
    }

    @Test
    void testTryReader2() {
        // Setup
        // Configure Storage.reader(...).
        final ReadChannel mockReadChannel = mock(ReadChannel.class);
        when(mockStorage.reader(BlobId.of("bucket", "name"), Storage.BlobSourceOption.generationMatch(0L)))
                .thenReturn(mockReadChannel);

        // Run the test
        myClassUnderTest.tryReader2();

        // Verify the results
        verify(mockReadChannel).close();
    }

    @Test
    void testTryReader2_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.reader(BlobId.of("bucket", "name"), Storage.BlobSourceOption.generationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryReader2());
    }

    @Test
    void testTryWriter1() throws Exception {
        // Setup
        // Configure Storage.writer(...).
        final WriteChannel mockWriteChannel = mock(WriteChannel.class);
        when(mockStorage.writer(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockWriteChannel);

        // Run the test
        myClassUnderTest.tryWriter1();

        // Verify the results
        verify(mockWriteChannel).close();
    }

    @Test
    void testTryWriter1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.writer(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build(),
                Storage.BlobWriteOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryWriter1());
    }

    @Test
    void testTryWriter2() throws Exception {
        // Setup
        // Configure Storage.writer(...).
        final WriteChannel mockWriteChannel = mock(WriteChannel.class);
        when(mockStorage.writer(new URL("https://example.com/"))).thenReturn(mockWriteChannel);

        // Run the test
        myClassUnderTest.tryWriter2();

        // Verify the results
        verify(mockWriteChannel).close();
    }

    @Test
    void testTryWriter2_StorageThrowsStorageException() throws Exception {
        // Setup
        when(mockStorage.writer(new URL("https://example.com/"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryWriter2());
    }

    @Test
    void testTrySignUrl() throws Exception {
        // Setup
        when(mockStorage.signUrl(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), eq(0L),
                eq(TimeUnit.MILLISECONDS), any(Storage.SignUrlOption.class)))
                .thenReturn(new URL("https://example.com/"));

        // Run the test
        myClassUnderTest.trySignUrl();

        // Verify the results
    }

    @Test
    void testTrySignUrl_StorageThrowsSigningException() {
        // Setup
        when(mockStorage.signUrl(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()), eq(0L),
                eq(TimeUnit.MILLISECONDS), any(Storage.SignUrlOption.class)))
                .thenThrow(ServiceAccountSigner.SigningException.class);

        // Run the test
        assertThrows(ServiceAccountSigner.SigningException.class, () -> myClassUnderTest.trySignUrl());
    }

    @Test
    void testTryGenerateSignedPostPolicyV41() {
        // Setup
        when(mockStorage.generateSignedPostPolicyV4(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                eq(0L), eq(TimeUnit.MILLISECONDS), any(PostPolicyV4.PostFieldsV4.class),
                any(PostPolicyV4.PostConditionsV4.class), any(Storage.PostPolicyV4Option.class)))
                .thenReturn(PostPolicyV4.of("url", new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGenerateSignedPostPolicyV41();

        // Verify the results
    }

    @Test
    void testTryGenerateSignedPostPolicyV42() {
        // Setup
        when(mockStorage.generateSignedPostPolicyV4(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                eq(0L), eq(TimeUnit.MILLISECONDS), any(PostPolicyV4.PostFieldsV4.class),
                any(Storage.PostPolicyV4Option.class))).thenReturn(PostPolicyV4.of("url", new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGenerateSignedPostPolicyV42();

        // Verify the results
    }

    @Test
    void testTryGenerateSignedPostPolicyV43() {
        // Setup
        when(mockStorage.generateSignedPostPolicyV4(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                eq(0L), eq(TimeUnit.MILLISECONDS), any(PostPolicyV4.PostConditionsV4.class),
                any(Storage.PostPolicyV4Option.class))).thenReturn(PostPolicyV4.of("url", new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGenerateSignedPostPolicyV43();

        // Verify the results
    }

    @Test
    void testTryGenerateSignedPostPolicyV44() {
        // Setup
        when(mockStorage.generateSignedPostPolicyV4(eq(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()),
                eq(0L), eq(TimeUnit.MILLISECONDS), any(Storage.PostPolicyV4Option.class)))
                .thenReturn(PostPolicyV4.of("url", new HashMap<>()));

        // Run the test
        myClassUnderTest.tryGenerateSignedPostPolicyV44();

        // Verify the results
    }

    @Test
    void testTryGet5() {
        // Setup
        // Configure Storage.get(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryGet5();

        // Verify the results
    }

    @Test
    void testTryGet5_StorageReturnsNull() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGet5();

        // Verify the results
    }

    @Test
    void testTryGet5_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get(BlobId.of("bucket", "name"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet5());
    }

    @Test
    void testTryGet6() {
        // Setup
        when(mockStorage.get(any(Iterable.class))).thenReturn(Arrays.asList());

        // Run the test
        myClassUnderTest.tryGet6();

        // Verify the results
    }

    @Test
    void testTryGet6_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.get(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryGet6();

        // Verify the results
    }

    @Test
    void testTryGet6_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.get(any(Iterable.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGet6());
    }

    @Test
    void testTryUpdate4() {
        // Setup
        // Configure Storage.update(...).
        final Blob mockBlob = mock(Blob.class);
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build())).thenReturn(mockBlob);

        // Run the test
        myClassUnderTest.tryUpdate4();

        // Verify the results
    }

    @Test
    void testTryUpdate4_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.update(BlobInfo.newBuilder(BlobId.of("bucket", "name")).build()))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate4());
    }

    @Test
    void testTryUpdate5() {
        // Setup
        when(mockStorage.update(any(Iterable.class))).thenReturn(Arrays.asList());

        // Run the test
        myClassUnderTest.tryUpdate5();

        // Verify the results
    }

    @Test
    void testTryUpdate5_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.update(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryUpdate5();

        // Verify the results
    }

    @Test
    void testTryUpdate5_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.update(any(Iterable.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdate5());
    }

    @Test
    void testTryDelete5() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDelete5();

        // Verify the results
    }

    @Test
    void testTryDelete5_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete(BlobId.of("bucket", "name"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete5());
    }

    @Test
    void testTryDelete6() {
        // Setup
        when(mockStorage.delete(any(Iterable.class))).thenReturn(Arrays.asList(false));

        // Run the test
        myClassUnderTest.tryDelete6();

        // Verify the results
    }

    @Test
    void testTryDelete6_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.delete(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryDelete6();

        // Verify the results
    }

    @Test
    void testTryDelete6_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.delete(any(Iterable.class))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDelete6());
    }

    @Test
    void testTryGetAcl1() {
        // Setup
        // Configure Storage.getAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.getAcl("bucket", null, Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetAcl1();

        // Verify the results
    }

    @Test
    void testTryGetAcl1_StorageReturnsNull() {
        // Setup
        when(mockStorage.getAcl("bucket", null, Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetAcl1();

        // Verify the results
    }

    @Test
    void testTryGetAcl1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getAcl("bucket", null, Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetAcl1());
    }

    @Test
    void testTryGetAcl2() {
        // Setup
        // Configure Storage.getAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.getAcl("bucket", null)).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetAcl2();

        // Verify the results
    }

    @Test
    void testTryDeleteAcl1() {
        // Setup
        when(mockStorage.deleteAcl("bucket", null, Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteAcl1();

        // Verify the results
    }

    @Test
    void testTryDeleteAcl1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.deleteAcl("bucket", null, Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteAcl1());
    }

    @Test
    void testTryDeleteAcl2() {
        // Setup
        when(mockStorage.deleteAcl("bucket", null)).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteAcl2();

        // Verify the results
    }

    @Test
    void testTryCreateAcl1() {
        // Setup
        // Configure Storage.createAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.createAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateAcl1();

        // Verify the results
    }

    @Test
    void testTryCreateAcl1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.createAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateAcl1());
    }

    @Test
    void testTryCreateAcl2() {
        // Setup
        // Configure Storage.createAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.createAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateAcl2();

        // Verify the results
    }

    @Test
    void testTryUpdateAcl1() {
        // Setup
        // Configure Storage.updateAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.updateAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateAcl1();

        // Verify the results
    }

    @Test
    void testTryUpdateAcl1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.updateAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateAcl1());
    }

    @Test
    void testTryUpdateAcl2() {
        // Setup
        // Configure Storage.updateAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.updateAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateAcl2();

        // Verify the results
    }

    @Test
    void testTryListAcls1() {
        // Setup
        // Configure Storage.listAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockStorage.listAcls("bucket", Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListAcls1();

        // Verify the results
    }

    @Test
    void testTryListAcls1_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.listAcls("bucket", Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListAcls1();

        // Verify the results
    }

    @Test
    void testTryListAcls1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.listAcls("bucket", Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListAcls1());
    }

    @Test
    void testTryListAcls2() {
        // Setup
        // Configure Storage.listAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockStorage.listAcls("bucket")).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListAcls2();

        // Verify the results
    }

    @Test
    void testTryListAcls2_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.listAcls("bucket")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListAcls2();

        // Verify the results
    }

    @Test
    void testTryGetDefaultAcl() {
        // Setup
        // Configure Storage.getDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.getDefaultAcl("bucket", null)).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryGetDefaultAcl_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getDefaultAcl("bucket", null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetDefaultAcl());
    }

    @Test
    void testTryDeleteDefaultAcl() {
        // Setup
        when(mockStorage.deleteDefaultAcl("bucket", null)).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryDeleteDefaultAcl_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.deleteDefaultAcl("bucket", null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteDefaultAcl());
    }

    @Test
    void testTryCreateDefaultAcl() {
        // Setup
        // Configure Storage.createDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.createDefaultAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryCreateDefaultAcl_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.createDefaultAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant"))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateDefaultAcl());
    }

    @Test
    void testTryUpdateDefaultAcl() {
        // Setup
        // Configure Storage.updateDefaultAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.updateDefaultAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateDefaultAcl();

        // Verify the results
    }

    @Test
    void testTryUpdateDefaultAcl_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.updateDefaultAcl("bucket", Acl.of(null, Acl.Role.valueOfStrict("constant"))))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateDefaultAcl());
    }

    @Test
    void testTryListDefaultAcls() {
        // Setup
        // Configure Storage.listDefaultAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockStorage.listDefaultAcls("bucket")).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListDefaultAcls();

        // Verify the results
    }

    @Test
    void testTryListDefaultAcls_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.listDefaultAcls("bucket")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListDefaultAcls();

        // Verify the results
    }

    @Test
    void testTryListDefaultAcls_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.listDefaultAcls("bucket")).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListDefaultAcls());
    }

    @Test
    void testTryGetAcl3() {
        // Setup
        // Configure Storage.getAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.getAcl(BlobId.of("bucket", "name"), null)).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryGetAcl3();

        // Verify the results
    }

    @Test
    void testTryGetAcl3_StorageReturnsNull() {
        // Setup
        when(mockStorage.getAcl(BlobId.of("bucket", "name"), null)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetAcl3();

        // Verify the results
    }

    @Test
    void testTryGetAcl3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getAcl(BlobId.of("bucket", "name"), null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetAcl3());
    }

    @Test
    void testTryDeleteAcl3() {
        // Setup
        when(mockStorage.deleteAcl(BlobId.of("bucket", "name"), null)).thenReturn(false);

        // Run the test
        myClassUnderTest.tryDeleteAcl3();

        // Verify the results
    }

    @Test
    void testTryDeleteAcl3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.deleteAcl(BlobId.of("bucket", "name"), null)).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteAcl3());
    }

    @Test
    void testTryCreateAcl3() {
        // Setup
        // Configure Storage.createAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.createAcl(BlobId.of("bucket", "name"),
                Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryCreateAcl3();

        // Verify the results
    }

    @Test
    void testTryCreateAcl3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.createAcl(BlobId.of("bucket", "name"),
                Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateAcl3());
    }

    @Test
    void testTryUpdateAcl3() {
        // Setup
        // Configure Storage.updateAcl(...).
        final Acl acl = Acl.of(null, Acl.Role.valueOfStrict("constant"));
        when(mockStorage.updateAcl(BlobId.of("bucket", "name"),
                Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenReturn(acl);

        // Run the test
        myClassUnderTest.tryUpdateAcl3();

        // Verify the results
    }

    @Test
    void testTryUpdateAcl3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.updateAcl(BlobId.of("bucket", "name"),
                Acl.of(null, Acl.Role.valueOfStrict("constant")))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateAcl3());
    }

    @Test
    void testTryListAcls3() {
        // Setup
        // Configure Storage.listAcls(...).
        final List<Acl> acls = Arrays.asList(Acl.of(null, Acl.Role.valueOfStrict("constant")));
        when(mockStorage.listAcls(BlobId.of("bucket", "name"))).thenReturn(acls);

        // Run the test
        myClassUnderTest.tryListAcls3();

        // Verify the results
    }

    @Test
    void testTryListAcls3_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.listAcls(BlobId.of("bucket", "name"))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryListAcls3();

        // Verify the results
    }

    @Test
    void testTryListAcls3_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.listAcls(BlobId.of("bucket", "name"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListAcls3());
    }

    @Test
    void testTryCreateHmacKey() {
        // Setup
        when(mockStorage.createHmacKey(ServiceAccount.of("email"),
                Storage.CreateHmacKeyOption.userProject("userProject"))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryCreateHmacKey();

        // Verify the results
    }

    @Test
    void testTryCreateHmacKey_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.createHmacKey(ServiceAccount.of("email"),
                Storage.CreateHmacKeyOption.userProject("userProject"))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryCreateHmacKey());
    }

    @Test
    void testTryListHmacKeys1() {
        // Setup
        // Configure Storage.listHmacKeys(...).
        final Page<HmacKey.HmacKeyMetadata> mockPage = mock(Page.class);
        when(mockStorage.listHmacKeys(Storage.ListHmacKeysOption.maxResults(0L))).thenReturn(mockPage);

        // Run the test
        myClassUnderTest.tryListHmacKeys1();

        // Verify the results
    }

    @Test
    void testTryListHmacKeys1_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.listHmacKeys(Storage.ListHmacKeysOption.maxResults(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryListHmacKeys1());
    }

    @Test
    void testTryGetHmacKey() {
        // Setup
        // Configure Storage.getHmacKey(...).
        final HmacKey.HmacKeyMetadata hmacKeyMetadata = HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"),
                "accessId", "projectId");
        when(mockStorage.getHmacKey("accessId", Storage.GetHmacKeyOption.userProject("userProject")))
                .thenReturn(hmacKeyMetadata);

        // Run the test
        myClassUnderTest.tryGetHmacKey();

        // Verify the results
    }

    @Test
    void testTryGetHmacKey_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getHmacKey("accessId", Storage.GetHmacKeyOption.userProject("userProject")))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetHmacKey());
    }

    @Test
    void testTryUpdateHmacKeyState() {
        // Setup
        // Configure Storage.updateHmacKeyState(...).
        final HmacKey.HmacKeyMetadata hmacKeyMetadata = HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"),
                "accessId", "projectId");
        when(mockStorage.updateHmacKeyState(
                HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId"),
                HmacKey.HmacKeyState.ACTIVE, Storage.UpdateHmacKeyOption.userProject("userProject")))
                .thenReturn(hmacKeyMetadata);

        // Run the test
        myClassUnderTest.tryUpdateHmacKeyState();

        // Verify the results
    }

    @Test
    void testTryUpdateHmacKeyState_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.updateHmacKeyState(
                HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId"),
                HmacKey.HmacKeyState.ACTIVE, Storage.UpdateHmacKeyOption.userProject("userProject")))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryUpdateHmacKeyState());
    }

    @Test
    void testTryDeleteHmacKey() {
        // Setup
        // Run the test
        myClassUnderTest.tryDeleteHmacKey();

        // Verify the results
        verify(mockStorage).deleteHmacKey(
                HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId"),
                Storage.DeleteHmacKeyOption.userProject("userProject"));
    }

    @Test
    void testTryDeleteHmacKey_StorageThrowsStorageException() {
        // Setup
        doThrow(StorageException.class).when(mockStorage).deleteHmacKey(
                HmacKey.HmacKeyMetadata.of(ServiceAccount.of("email"), "accessId", "projectId"),
                Storage.DeleteHmacKeyOption.userProject("userProject"));

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryDeleteHmacKey());
    }

    @Test
    void testTryGetIamPolicy() {
        // Setup
        when(mockStorage.getIamPolicy("bucket", Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenReturn(Policy.newBuilder().build());

        // Run the test
        myClassUnderTest.tryGetIamPolicy();

        // Verify the results
    }

    @Test
    void testTryGetIamPolicy_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getIamPolicy("bucket", Storage.BucketSourceOption.metagenerationMatch(0L)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetIamPolicy());
    }

    @Test
    void testTrySetIamPolicy() {
        // Setup
        when(mockStorage.setIamPolicy("bucket", Policy.newBuilder().build(),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(Policy.newBuilder().build());

        // Run the test
        myClassUnderTest.trySetIamPolicy();

        // Verify the results
    }

    @Test
    void testTrySetIamPolicy_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.setIamPolicy("bucket", Policy.newBuilder().build(),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.trySetIamPolicy());
    }

    @Test
    void testTryTestIamPermissions() {
        // Setup
        when(mockStorage.testIamPermissions("bucket", Arrays.asList("value"),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(Arrays.asList(false));

        // Run the test
        myClassUnderTest.tryTestIamPermissions();

        // Verify the results
    }

    @Test
    void testTryTestIamPermissions_StorageReturnsNoItems() {
        // Setup
        when(mockStorage.testIamPermissions("bucket", Arrays.asList("value"),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryTestIamPermissions();

        // Verify the results
    }

    @Test
    void testTryTestIamPermissions_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.testIamPermissions("bucket", Arrays.asList("value"),
                Storage.BucketSourceOption.metagenerationMatch(0L))).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryTestIamPermissions());
    }

    @Test
    void testTryLockRetentionPolicy() {
        // Setup
        // Configure Storage.lockRetentionPolicy(...).
        final Bucket mockBucket = mock(Bucket.class);
        when(mockStorage.lockRetentionPolicy(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenReturn(mockBucket);

        // Run the test
        myClassUnderTest.tryLockRetentionPolicy();

        // Verify the results
    }

    @Test
    void testTryLockRetentionPolicy_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.lockRetentionPolicy(BucketInfo.newBuilder("bucketName").build(),
                Storage.BucketTargetOption.predefinedAcl(Storage.PredefinedAcl.AUTHENTICATED_READ)))
                .thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryLockRetentionPolicy());
    }

    @Test
    void testTryGetServiceAccount() {
        // Setup
        when(mockStorage.getServiceAccount("projectId")).thenReturn(ServiceAccount.of("email"));

        // Run the test
        myClassUnderTest.tryGetServiceAccount();

        // Verify the results
    }

    @Test
    void testTryGetServiceAccount_StorageThrowsStorageException() {
        // Setup
        when(mockStorage.getServiceAccount("projectId")).thenThrow(StorageException.class);

        // Run the test
        assertThrows(StorageException.class, () -> myClassUnderTest.tryGetServiceAccount());
    }
}
