package com.myapp;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.SettableApiFuture;
import com.google.api.gax.batching.Batcher;
import com.google.api.gax.rpc.*;
import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.models.*;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private BigtableDataClient mockBigtableDataClient;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockBigtableDataClient);
    }

    @Test
    void testTryExists1() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", "rowKey")).thenReturn(false);

        // Run the test
        myClassUnderTest.tryExists1();

        // Verify the results
    }

    @Test
    void testTryExists2() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", ByteString.copyFrom("content".getBytes()))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryExists2();

        // Verify the results
    }

    @Test
    void testTryExistsAsync1() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", "rowKey")).thenReturn(ApiFutures.immediateFuture(false));

        // Run the test
        myClassUnderTest.tryExistsAsync1();

        // Verify the results
    }

    @Test
    void testTryExistsAsync1_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", "rowKey"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        myClassUnderTest.tryExistsAsync1();

        // Verify the results
    }

    @Test
    void testTryExistsAsync2() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(ApiFutures.immediateFuture(false));

        // Run the test
        myClassUnderTest.tryExistsAsync2();

        // Verify the results
    }

    @Test
    void testTryExistsAsync2_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        myClassUnderTest.tryExistsAsync2();

        // Verify the results
    }

    @Test
    void testTryReadRow1() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        final Row row = Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                        ByteString.copyFrom("content".getBytes()))));
        when(mockBigtableDataClient.readRow("tableId", ByteString.copyFrom("content".getBytes()))).thenReturn(row);

        // Run the test
        myClassUnderTest.tryReadRow1();

        // Verify the results
    }

    @Test
    void testTryReadRow2() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        final Row row = Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                        ByteString.copyFrom("content".getBytes()))));
        when(mockBigtableDataClient.readRow("tableId", "rowKey")).thenReturn(row);

        // Run the test
        myClassUnderTest.tryReadRow2();

        // Verify the results
    }

    @Test
    void testTryReadRow3() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        final Row row = Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                        ByteString.copyFrom("content".getBytes()))));
        when(mockBigtableDataClient.readRow(eq("tableId"), eq("rowKey"), any(Filters.Filter.class))).thenReturn(row);

        // Run the test
        myClassUnderTest.tryReadRow3();

        // Verify the results
    }

    @Test
    void testTryReadRow4() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        final Row row = Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                        ByteString.copyFrom("content".getBytes()))));
        when(mockBigtableDataClient.readRow(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(row);

        // Run the test
        myClassUnderTest.tryReadRow4();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync1() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFuture(
                Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
        when(mockBigtableDataClient.readRowAsync("tableId", "rowKey")).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync1();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync1_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.readRowAsync("tableId", "rowKey")).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync1();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync2() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFuture(
                Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
        when(mockBigtableDataClient.readRowAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync2();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync2_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.readRowAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync2();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync3() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFuture(
                Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq("rowKey"), any(Filters.Filter.class)))
                .thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync3();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync3_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq("rowKey"), any(Filters.Filter.class)))
                .thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync3();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync4() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFuture(
                Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync4();

        // Verify the results
    }

    @Test
    void testTryReadRowAsync4_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadRowAsync4();

        // Verify the results
    }

    @Test
    void testTryReadRowCallable1() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        final UnaryCallable<Query, Row> queryRowUnaryCallable = new UnaryCallable<Query, Row>() {
            @Override
            public ApiFuture<Row> futureCall(final Query request, final ApiCallContext context) {
                final SettableApiFuture<Row> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readRowCallable()).thenReturn(queryRowUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadRowCallable1();

        // Verify the results
    }

    @Test
    void testTryReadRowCallable1_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        final UnaryCallable<Query, Row> queryRowUnaryCallable = new UnaryCallable<Query, Row>() {
            @Override
            public ApiFuture<Row> futureCall(final Query request, final ApiCallContext context) {
                final SettableApiFuture<Row> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readRowCallable()).thenReturn(queryRowUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadRowCallable1();

        // Verify the results
    }

    @Test
    void testTryReadRowCallable2() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        final UnaryCallable<Query, String> queryStringUnaryCallable = new UnaryCallable<Query, String>() {
            @Override
            public ApiFuture<String> futureCall(final Query request, final ApiCallContext context) {
                final SettableApiFuture<String> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set("value");
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readRowCallable(any(RowAdapter.class))).thenReturn(queryStringUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadRowCallable2();

        // Verify the results
    }

    @Test
    void testTryReadRowCallable2_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        final UnaryCallable<Query, String> queryStringUnaryCallable = new UnaryCallable<Query, String>() {
            @Override
            public ApiFuture<String> futureCall(final Query request, final ApiCallContext context) {
                final SettableApiFuture<String> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readRowCallable(any(RowAdapter.class))).thenReturn(queryStringUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadRowCallable2();

        // Verify the results
    }

    @Test
    void testTryReadRows() {
        // Setup
        // Configure BigtableDataClient.readRows(...).
        final ServerStream<Row> mockServerStream = mock(ServerStream.class);
        when(mockBigtableDataClient.readRows(Query.create("tableId"))).thenReturn(mockServerStream);

        // Run the test
        myClassUnderTest.tryReadRows();

        // Verify the results
    }

    @Test
    void testTryReadRows_BigtableDataClientReturnsNoItems() {
        // Setup
        // Configure BigtableDataClient.readRows(...).
        final ServerStream<Row> mockServerStream = mock(ServerStream.class);
        when(mockBigtableDataClient.readRows(Query.create("tableId"))).thenReturn(mockServerStream);

        // Run the test
        myClassUnderTest.tryReadRows();

        // Verify the results
    }

    @Test
    void testTryReadRowsAsync() {
        // Setup
        // Run the test
        myClassUnderTest.tryReadRowsAsync();

        // Verify the results
        verify(mockBigtableDataClient).readRowsAsync(eq(Query.create("tableId")), any(ResponseObserver.class));
    }

    @Test
    void testTryReadRowsCallable1() {
        // Setup
        // Configure BigtableDataClient.readRowsCallable(...).
        final ServerStreamingCallable<Query, Row> mockServerStreamingCallable = mock(ServerStreamingCallable.class);
        when(mockBigtableDataClient.readRowsCallable()).thenReturn(mockServerStreamingCallable);

        // Run the test
        myClassUnderTest.tryReadRowsCallable1();

        // Verify the results
    }

    @Test
    void testTryReadRowsCallable2() {
        // Setup
        // Configure BigtableDataClient.readRowsCallable(...).
        final ServerStreamingCallable<Query, String> mockServerStreamingCallable = mock(ServerStreamingCallable.class);
        when(mockBigtableDataClient.readRowsCallable(any(RowAdapter.class))).thenReturn(mockServerStreamingCallable);

        // Run the test
        myClassUnderTest.tryReadRowsCallable2();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeys() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeys(...).
        final List<KeyOffset> keyOffsets = Arrays.asList(
                KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L));
        when(mockBigtableDataClient.sampleRowKeys("tableId")).thenReturn(keyOffsets);

        // Run the test
        myClassUnderTest.trySampleRowKeys();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeys_BigtableDataClientReturnsNoItems() {
        // Setup
        when(mockBigtableDataClient.sampleRowKeys("tableId")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.trySampleRowKeys();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysAsync() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysAsync(...).
        final ApiFuture<List<KeyOffset>> listApiFuture = ApiFutures.immediateFuture(
                Arrays.asList(KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L)));
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(listApiFuture);

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysAsync_BigtableDataClientReturnsNoItems() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysAsync(...).
        final ApiFuture<List<KeyOffset>> listApiFuture = ApiFutures.immediateFuture(Collections.emptyList());
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(listApiFuture);

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysAsync_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysAsync(...).
        final ApiFuture<List<KeyOffset>> listApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(listApiFuture);

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        final UnaryCallable<String, List<KeyOffset>> stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            public ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                final SettableApiFuture<List<KeyOffset>> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(Arrays.asList(KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L)));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable);

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable_BigtableDataClientReturnsNoItems() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        final UnaryCallable<String, List<KeyOffset>> stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            public ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                final SettableApiFuture<List<KeyOffset>> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(Collections.emptyList());
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable);

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable();

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        final UnaryCallable<String, List<KeyOffset>> stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            public ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                final SettableApiFuture<List<KeyOffset>> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable);

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable();

        // Verify the results
    }

    @Test
    void testTryMutateRow() {
        // Setup
        // Run the test
        myClassUnderTest.tryMutateRow();

        // Verify the results
        verify(mockBigtableDataClient).mutateRow(any(RowMutation.class));
    }

    @Test
    void testTryMutateRowAsync() {
        // Setup
        when(mockBigtableDataClient.mutateRowAsync(any(RowMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(null));

        // Run the test
        myClassUnderTest.tryMutateRowAsync();

        // Verify the results
    }

    @Test
    void testTryMutateRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.mutateRowAsync(any(RowMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        myClassUnderTest.tryMutateRowAsync();

        // Verify the results
    }

    @Test
    void testTryMutateRowCallable() {
        // Setup
        // Configure BigtableDataClient.mutateRowCallable(...).
        final UnaryCallable<RowMutation, Void> rowMutationVoidUnaryCallable = new UnaryCallable<RowMutation, Void>() {
            @Override
            public ApiFuture<Void> futureCall(final RowMutation request, final ApiCallContext context) {
                final SettableApiFuture<Void> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(null);
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.mutateRowCallable()).thenReturn(rowMutationVoidUnaryCallable);

        // Run the test
        myClassUnderTest.tryMutateRowCallable();

        // Verify the results
    }

    @Test
    void testTryMutateRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.mutateRowCallable(...).
        final UnaryCallable<RowMutation, Void> rowMutationVoidUnaryCallable = new UnaryCallable<RowMutation, Void>() {
            @Override
            public ApiFuture<Void> futureCall(final RowMutation request, final ApiCallContext context) {
                final SettableApiFuture<Void> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.mutateRowCallable()).thenReturn(rowMutationVoidUnaryCallable);

        // Run the test
        myClassUnderTest.tryMutateRowCallable();

        // Verify the results
    }

    @Test
    void testTryBulkMutateRows() {
        // Setup
        // Run the test
        myClassUnderTest.tryBulkMutateRows();

        // Verify the results
        verify(mockBigtableDataClient).bulkMutateRows(any(BulkMutation.class));
    }

    @Test
    void testTryNewBulkMutationBatcher() throws Exception {
        // Setup
        // Configure BigtableDataClient.newBulkMutationBatcher(...).
        final Batcher<RowMutationEntry, Void> mockBatcher = mock(Batcher.class);
        when(mockBigtableDataClient.newBulkMutationBatcher("tableId")).thenReturn(mockBatcher);

        // Run the test
        myClassUnderTest.tryNewBulkMutationBatcher();

        // Verify the results
        verify(mockBatcher).close();
    }

    @Test
    void testTryNewBulkReadRowsBatcher1() throws Exception {
        // Setup
        // Configure BigtableDataClient.newBulkReadRowsBatcher(...).
        final Batcher<ByteString, Row> mockBatcher = mock(Batcher.class);
        when(mockBigtableDataClient.newBulkReadRowsBatcher("tableId")).thenReturn(mockBatcher);

        // Run the test
        myClassUnderTest.tryNewBulkReadRowsBatcher1();

        // Verify the results
        verify(mockBatcher).close();
    }

    @Test
    void testTryNewBulkReadRowsBatcher2() throws Exception {
        // Setup
        // Configure BigtableDataClient.newBulkReadRowsBatcher(...).
        final Batcher<ByteString, Row> mockBatcher = mock(Batcher.class);
        when(mockBigtableDataClient.newBulkReadRowsBatcher(eq("tableId"), any(Filters.Filter.class)))
                .thenReturn(mockBatcher);

        // Run the test
        myClassUnderTest.tryNewBulkReadRowsBatcher2();

        // Verify the results
        verify(mockBatcher).close();
    }

    @Test
    void testTryBulkMutateRowsAsync() {
        // Setup
        when(mockBigtableDataClient.bulkMutateRowsAsync(any(BulkMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(null));

        // Run the test
        myClassUnderTest.tryBulkMutateRowsAsync();

        // Verify the results
    }

    @Test
    void testTryBulkMutateRowsAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.bulkMutateRowsAsync(any(BulkMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        myClassUnderTest.tryBulkMutateRowsAsync();

        // Verify the results
    }

    @Test
    void testTryBulkMutationCallable() {
        // Setup
        // Configure BigtableDataClient.bulkMutationCallable(...).
        final UnaryCallable<BulkMutation, Void> bulkMutationVoidUnaryCallable = new UnaryCallable<BulkMutation, Void>() {
            @Override
            public ApiFuture<Void> futureCall(final BulkMutation request, final ApiCallContext context) {
                final SettableApiFuture<Void> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(null);
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.bulkMutationCallable()).thenReturn(bulkMutationVoidUnaryCallable);

        // Run the test
        myClassUnderTest.tryBulkMutationCallable();

        // Verify the results
    }

    @Test
    void testTryBulkMutationCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.bulkMutationCallable(...).
        final UnaryCallable<BulkMutation, Void> bulkMutationVoidUnaryCallable = new UnaryCallable<BulkMutation, Void>() {
            @Override
            public ApiFuture<Void> futureCall(final BulkMutation request, final ApiCallContext context) {
                final SettableApiFuture<Void> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.bulkMutationCallable()).thenReturn(bulkMutationVoidUnaryCallable);

        // Run the test
        myClassUnderTest.tryBulkMutationCallable();

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRow() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRow(any(ConditionalRowMutation.class))).thenReturn(false);

        // Run the test
        myClassUnderTest.tryCheckAndMutateRow();

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowAsync() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRowAsync(any(ConditionalRowMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(false));

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowAsync();

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRowAsync(any(ConditionalRowMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")));

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowAsync();

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowCallable() {
        // Setup
        // Configure BigtableDataClient.checkAndMutateRowCallable(...).
        final UnaryCallable<ConditionalRowMutation, Boolean> conditionalRowMutationBooleanUnaryCallable = new UnaryCallable<ConditionalRowMutation, Boolean>() {
            @Override
            public ApiFuture<Boolean> futureCall(final ConditionalRowMutation request, final ApiCallContext context) {
                final SettableApiFuture<Boolean> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(false);
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.checkAndMutateRowCallable()).thenReturn(conditionalRowMutationBooleanUnaryCallable);

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowCallable();

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.checkAndMutateRowCallable(...).
        final UnaryCallable<ConditionalRowMutation, Boolean> conditionalRowMutationBooleanUnaryCallable = new UnaryCallable<ConditionalRowMutation, Boolean>() {
            @Override
            public ApiFuture<Boolean> futureCall(final ConditionalRowMutation request, final ApiCallContext context) {
                final SettableApiFuture<Boolean> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.checkAndMutateRowCallable()).thenReturn(conditionalRowMutationBooleanUnaryCallable);

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowCallable();

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRow() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRow(...).
        final Row row = Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                        ByteString.copyFrom("content".getBytes()))));
        when(mockBigtableDataClient.readModifyWriteRow(any(ReadModifyWriteRow.class))).thenReturn(row);

        // Run the test
        myClassUnderTest.tryReadModifyWriteRow();

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowAsync() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFuture(
                Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
        when(mockBigtableDataClient.readModifyWriteRowAsync(any(ReadModifyWriteRow.class))).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowAsync();

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowAsync(...).
        final ApiFuture<Row> rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"));
        when(mockBigtableDataClient.readModifyWriteRowAsync(any(ReadModifyWriteRow.class))).thenReturn(rowApiFuture);

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowAsync();

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowCallable() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowCallable(...).
        final UnaryCallable<ReadModifyWriteRow, Row> readModifyWriteRowRowUnaryCallable = new UnaryCallable<ReadModifyWriteRow, Row>() {
            @Override
            public ApiFuture<Row> futureCall(final ReadModifyWriteRow request, final ApiCallContext context) {
                final SettableApiFuture<Row> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.set(Row.create(ByteString.copyFrom("content".getBytes()), Arrays.asList(
                        RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, Arrays.asList("value"),
                                ByteString.copyFrom("content".getBytes())))));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readModifyWriteRowCallable()).thenReturn(readModifyWriteRowRowUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowCallable();

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowCallable(...).
        final UnaryCallable<ReadModifyWriteRow, Row> readModifyWriteRowRowUnaryCallable = new UnaryCallable<ReadModifyWriteRow, Row>() {
            @Override
            public ApiFuture<Row> futureCall(final ReadModifyWriteRow request, final ApiCallContext context) {
                final SettableApiFuture<Row> settableApiFuture = SettableApiFuture.create();
                settableApiFuture.setException(new Exception("Message"));
                return settableApiFuture;
            }
        };
        when(mockBigtableDataClient.readModifyWriteRowCallable()).thenReturn(readModifyWriteRowRowUnaryCallable);

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowCallable();

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose();

        // Verify the results
        verify(mockBigtableDataClient).close();
    }

    @Test
    void testTryCreate1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate1();

        // Verify the results
    }

    @Test
    void testTryCreate1_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, () -> myClassUnderTest.tryCreate1());
    }

    @Test
    void testTryCreate2() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate2();

        // Verify the results
    }

    @Test
    void testTryCreate2_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, () -> myClassUnderTest.tryCreate2());
    }
}
