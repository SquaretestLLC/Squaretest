package com.myapp

import com.google.api.core.ApiFuture
import com.google.api.core.ApiFutures
import com.google.api.core.SettableApiFuture
import com.google.api.gax.batching.Batcher
import com.google.api.gax.rpc.*
import com.google.cloud.bigtable.data.v2.BigtableDataClient
import com.google.cloud.bigtable.data.v2.models.*
import com.google.protobuf.ByteString
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private BigtableDataClient mockBigtableDataClient

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockBigtableDataClient)
    }

    @Test
    void testTryExists1() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", "rowKey")).thenReturn(false)

        // Run the test
        myClassUnderTest.tryExists1()

        // Verify the results
    }

    @Test
    void testTryExists1_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", "rowKey")).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryExists1()
        })
    }

    @Test
    void testTryExists2() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", ByteString.copyFrom("content".getBytes()))).thenReturn(false)

        // Run the test
        myClassUnderTest.tryExists2()

        // Verify the results
    }

    @Test
    void testTryExists2_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.exists("tableId", ByteString.copyFrom("content".getBytes())))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryExists2()
        })
    }

    @Test
    void testTryExistsAsync1() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", "rowKey")).thenReturn(ApiFutures.immediateFuture(false))

        // Run the test
        myClassUnderTest.tryExistsAsync1()

        // Verify the results
    }

    @Test
    void testTryExistsAsync1_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", "rowKey"))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        myClassUnderTest.tryExistsAsync1()

        // Verify the results
    }

    @Test
    void testTryExistsAsync2() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(ApiFutures.immediateFuture(false))

        // Run the test
        myClassUnderTest.tryExistsAsync2()

        // Verify the results
    }

    @Test
    void testTryExistsAsync2_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.existsAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        myClassUnderTest.tryExistsAsync2()

        // Verify the results
    }

    @Test
    void testTryReadRow1() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        def row = Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))])
        when(mockBigtableDataClient.readRow("tableId", ByteString.copyFrom("content".getBytes()))).thenReturn(row)

        // Run the test
        myClassUnderTest.tryReadRow1()

        // Verify the results
    }

    @Test
    void testTryReadRow1_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.readRow("tableId", ByteString.copyFrom("content".getBytes())))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryReadRow1()
        })
    }

    @Test
    void testTryReadRow2() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        def row = Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))])
        when(mockBigtableDataClient.readRow("tableId", "rowKey")).thenReturn(row)

        // Run the test
        myClassUnderTest.tryReadRow2()

        // Verify the results
    }

    @Test
    void testTryReadRow2_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.readRow("tableId", "rowKey")).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryReadRow2()
        })
    }

    @Test
    void testTryReadRow3() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        def row = Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))])
        when(mockBigtableDataClient.readRow(eq("tableId"), eq("rowKey"), any(Filters.Filter.class))).thenReturn(row)

        // Run the test
        myClassUnderTest.tryReadRow3()

        // Verify the results
    }

    @Test
    void testTryReadRow3_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.readRow(eq("tableId"), eq("rowKey"), any(Filters.Filter.class)))
                .thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryReadRow3()
        })
    }

    @Test
    void testTryReadRow4() {
        // Setup
        // Configure BigtableDataClient.readRow(...).
        def row = Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))])
        when(mockBigtableDataClient.readRow(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(row)

        // Run the test
        myClassUnderTest.tryReadRow4()

        // Verify the results
    }

    @Test
    void testTryReadRow4_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.readRow(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryReadRow4()
        })
    }

    @Test
    void testTryReadRowAsync1() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFuture(Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))]))
        when(mockBigtableDataClient.readRowAsync("tableId", "rowKey")).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync1()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync1_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.readRowAsync("tableId", "rowKey")).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync1()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync2() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFuture(Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))]))
        when(mockBigtableDataClient.readRowAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync2()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync2_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.readRowAsync("tableId", ByteString.copyFrom("content".getBytes())))
                .thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync2()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync3() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFuture(Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))]))
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq("rowKey"), any(Filters.Filter.class)))
                .thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync3()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync3_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq("rowKey"), any(Filters.Filter.class)))
                .thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync3()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync4() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFuture(Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))]))
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync4()

        // Verify the results
    }

    @Test
    void testTryReadRowAsync4_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.readRowAsync(eq("tableId"), eq(ByteString.copyFrom("content".getBytes())),
                any(Filters.Filter.class))).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadRowAsync4()

        // Verify the results
    }

    @Test
    void testTryReadRowCallable1() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        def queryRowUnaryCallable = new UnaryCallable<Query, Row>() {
            @Override
            ApiFuture<Row> futureCall(final Query request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(Row.create(ByteString.copyFrom("content".getBytes()),
                        [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                                ByteString.copyFrom("content".getBytes()))]))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readRowCallable()).thenReturn(queryRowUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadRowCallable1()

        // Verify the results
    }

    @Test
    void testTryReadRowCallable1_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        def queryRowUnaryCallable = new UnaryCallable<Query, Row>() {
            @Override
            ApiFuture<Row> futureCall(final Query request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readRowCallable()).thenReturn(queryRowUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadRowCallable1()

        // Verify the results
    }

    @Test
    void testTryReadRowCallable2() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        def queryStringUnaryCallable = new UnaryCallable<Query, String>() {
            @Override
            ApiFuture<String> futureCall(final Query request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set("value")
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readRowCallable(any(RowAdapter.class))).thenReturn(queryStringUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadRowCallable2()

        // Verify the results
    }

    @Test
    void testTryReadRowCallable2_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readRowCallable(...).
        def queryStringUnaryCallable = new UnaryCallable<Query, String>() {
            @Override
            ApiFuture<String> futureCall(final Query request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readRowCallable(any(RowAdapter.class))).thenReturn(queryStringUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadRowCallable2()

        // Verify the results
    }

    @Test
    void testTryReadRows() {
        // Setup
        // Configure BigtableDataClient.readRows(...).
        def mockServerStream = mock(ServerStream.class)
        when(mockBigtableDataClient.readRows(Query.create("tableId"))).thenReturn(mockServerStream)

        // Run the test
        myClassUnderTest.tryReadRows()

        // Verify the results
    }

    @Test
    void testTryReadRows_BigtableDataClientReturnsNoItems() {
        // Setup
        // Configure BigtableDataClient.readRows(...).
        def mockServerStream = mock(ServerStream.class)
        when(mockBigtableDataClient.readRows(Query.create("tableId"))).thenReturn(mockServerStream)

        // Run the test
        myClassUnderTest.tryReadRows()

        // Verify the results
    }

    @Test
    void testTryReadRowsAsync() {
        // Setup
        // Run the test
        myClassUnderTest.tryReadRowsAsync()

        // Verify the results
        verify(mockBigtableDataClient).readRowsAsync(eq(Query.create("tableId")), any(ResponseObserver.class))
    }

    @Test
    void testTryReadRowsCallable1() {
        // Setup
        // Configure BigtableDataClient.readRowsCallable(...).
        def mockServerStreamingCallable = mock(ServerStreamingCallable.class)
        when(mockBigtableDataClient.readRowsCallable()).thenReturn(mockServerStreamingCallable)

        // Run the test
        myClassUnderTest.tryReadRowsCallable1()

        // Verify the results
    }

    @Test
    void testTryReadRowsCallable2() {
        // Setup
        // Configure BigtableDataClient.readRowsCallable(...).
        def mockServerStreamingCallable = mock(ServerStreamingCallable.class)
        when(mockBigtableDataClient.readRowsCallable(any(RowAdapter.class))).thenReturn(mockServerStreamingCallable)

        // Run the test
        myClassUnderTest.tryReadRowsCallable2()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeys() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeys(...).
        def keyOffsets = [KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L)]
        when(mockBigtableDataClient.sampleRowKeys("tableId")).thenReturn(keyOffsets)

        // Run the test
        myClassUnderTest.trySampleRowKeys()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeys_BigtableDataClientReturnsNoItems() {
        // Setup
        when(mockBigtableDataClient.sampleRowKeys("tableId")).thenReturn([])

        // Run the test
        myClassUnderTest.trySampleRowKeys()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeys_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.sampleRowKeys("tableId")).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.trySampleRowKeys()
        })
    }

    @Test
    void testTrySampleRowKeysAsync() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysAsync(...).
        def listApiFuture = ApiFutures.immediateFuture(
                [KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L)])
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(listApiFuture)

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysAsync_BigtableDataClientReturnsNoItems() {
        // Setup
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(ApiFutures.immediateFuture([]))

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysAsync_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysAsync(...).
        def listApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.sampleRowKeysAsync("tableId")).thenReturn(listApiFuture)

        // Run the test
        myClassUnderTest.trySampleRowKeysAsync()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        def stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set([KeyOffset.create(ByteString.copyFrom("content".getBytes()), 0L)])
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable)

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable_BigtableDataClientReturnsNoItems() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        def stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set([])
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable)

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable()

        // Verify the results
    }

    @Test
    void testTrySampleRowKeysCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.sampleRowKeysCallable(...).
        def stringListUnaryCallable = new UnaryCallable<String, List<KeyOffset>>() {
            @Override
            ApiFuture<List<KeyOffset>> futureCall(final String request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.sampleRowKeysCallable()).thenReturn(stringListUnaryCallable)

        // Run the test
        myClassUnderTest.trySampleRowKeysCallable()

        // Verify the results
    }

    @Test
    void testTryMutateRow() {
        // Setup
        // Run the test
        myClassUnderTest.tryMutateRow()

        // Verify the results
        verify(mockBigtableDataClient).mutateRow(any(RowMutation.class))
    }

    @Test
    void testTryMutateRow_BigtableDataClientThrowsApiException() {
        // Setup
        doThrow(ApiException.class).when(mockBigtableDataClient).mutateRow(any(RowMutation.class))

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryMutateRow()
        })
    }

    @Test
    void testTryMutateRowAsync() {
        // Setup
        when(mockBigtableDataClient.mutateRowAsync(any(RowMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(null))

        // Run the test
        myClassUnderTest.tryMutateRowAsync()

        // Verify the results
    }

    @Test
    void testTryMutateRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.mutateRowAsync(any(RowMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        myClassUnderTest.tryMutateRowAsync()

        // Verify the results
    }

    @Test
    void testTryMutateRowCallable() {
        // Setup
        // Configure BigtableDataClient.mutateRowCallable(...).
        def rowMutationVoidUnaryCallable = new UnaryCallable<RowMutation, Void>() {
            @Override
            ApiFuture<Void> futureCall(final RowMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(null)
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.mutateRowCallable()).thenReturn(rowMutationVoidUnaryCallable)

        // Run the test
        myClassUnderTest.tryMutateRowCallable()

        // Verify the results
    }

    @Test
    void testTryMutateRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.mutateRowCallable(...).
        def rowMutationVoidUnaryCallable = new UnaryCallable<RowMutation, Void>() {
            @Override
            ApiFuture<Void> futureCall(final RowMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.mutateRowCallable()).thenReturn(rowMutationVoidUnaryCallable)

        // Run the test
        myClassUnderTest.tryMutateRowCallable()

        // Verify the results
    }

    @Test
    void testTryBulkMutateRows() {
        // Setup
        // Run the test
        myClassUnderTest.tryBulkMutateRows()

        // Verify the results
        verify(mockBigtableDataClient).bulkMutateRows(any(BulkMutation.class))
    }

    @Test
    void testTryBulkMutateRows_BigtableDataClientThrowsApiException() {
        // Setup
        doThrow(ApiException.class).when(mockBigtableDataClient).bulkMutateRows(any(BulkMutation.class))

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryBulkMutateRows()
        })
    }

    @Test
    void testTryBulkMutateRows_BigtableDataClientThrowsMutateRowsException() {
        // Setup
        doThrow(MutateRowsException.class).when(mockBigtableDataClient).bulkMutateRows(any(BulkMutation.class))

        // Run the test
        assertThrows(MutateRowsException.class, {
            myClassUnderTest.tryBulkMutateRows()
        })
    }

    @Test
    void testTryNewBulkMutationBatcher() {
        // Setup
        // Configure BigtableDataClient.newBulkMutationBatcher(...).
        def mockBatcher = mock(Batcher.class)
        when(mockBigtableDataClient.newBulkMutationBatcher("tableId")).thenReturn(mockBatcher)

        // Run the test
        myClassUnderTest.tryNewBulkMutationBatcher()

        // Verify the results
        verify(mockBatcher).close()
    }

    @Test
    void testTryNewBulkReadRowsBatcher1() {
        // Setup
        // Configure BigtableDataClient.newBulkReadRowsBatcher(...).
        def mockBatcher = mock(Batcher.class)
        when(mockBigtableDataClient.newBulkReadRowsBatcher("tableId")).thenReturn(mockBatcher)

        // Run the test
        myClassUnderTest.tryNewBulkReadRowsBatcher1()

        // Verify the results
        verify(mockBatcher).close()
    }

    @Test
    void testTryNewBulkReadRowsBatcher2() {
        // Setup
        // Configure BigtableDataClient.newBulkReadRowsBatcher(...).
        def mockBatcher = mock(Batcher.class)
        when(mockBigtableDataClient.newBulkReadRowsBatcher(eq("tableId"), any(Filters.Filter.class)))
                .thenReturn(mockBatcher)

        // Run the test
        myClassUnderTest.tryNewBulkReadRowsBatcher2()

        // Verify the results
        verify(mockBatcher).close()
    }

    @Test
    void testTryBulkMutateRowsAsync() {
        // Setup
        when(mockBigtableDataClient.bulkMutateRowsAsync(any(BulkMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(null))

        // Run the test
        myClassUnderTest.tryBulkMutateRowsAsync()

        // Verify the results
    }

    @Test
    void testTryBulkMutateRowsAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.bulkMutateRowsAsync(any(BulkMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        myClassUnderTest.tryBulkMutateRowsAsync()

        // Verify the results
    }

    @Test
    void testTryBulkMutationCallable() {
        // Setup
        // Configure BigtableDataClient.bulkMutationCallable(...).
        def bulkMutationVoidUnaryCallable = new UnaryCallable<BulkMutation, Void>() {
            @Override
            ApiFuture<Void> futureCall(final BulkMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(null)
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.bulkMutationCallable()).thenReturn(bulkMutationVoidUnaryCallable)

        // Run the test
        myClassUnderTest.tryBulkMutationCallable()

        // Verify the results
    }

    @Test
    void testTryBulkMutationCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.bulkMutationCallable(...).
        def bulkMutationVoidUnaryCallable = new UnaryCallable<BulkMutation, Void>() {
            @Override
            ApiFuture<Void> futureCall(final BulkMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.bulkMutationCallable()).thenReturn(bulkMutationVoidUnaryCallable)

        // Run the test
        myClassUnderTest.tryBulkMutationCallable()

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRow() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRow(any(ConditionalRowMutation.class))).thenReturn(false)

        // Run the test
        myClassUnderTest.tryCheckAndMutateRow()

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRow_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRow(any(ConditionalRowMutation.class))).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryCheckAndMutateRow()
        })
    }

    @Test
    void testTryCheckAndMutateRowAsync() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRowAsync(any(ConditionalRowMutation.class)))
                .thenReturn(ApiFutures.immediateFuture(false))

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowAsync()

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        when(mockBigtableDataClient.checkAndMutateRowAsync(any(ConditionalRowMutation.class)))
                .thenReturn(ApiFutures.immediateFailedFuture(new Exception("message")))

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowAsync()

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowCallable() {
        // Setup
        // Configure BigtableDataClient.checkAndMutateRowCallable(...).
        def conditionalRowMutationBooleanUnaryCallable = new UnaryCallable<ConditionalRowMutation, Boolean>() {
            @Override
            ApiFuture<Boolean> futureCall(final ConditionalRowMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(false)
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.checkAndMutateRowCallable()).thenReturn(conditionalRowMutationBooleanUnaryCallable)

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowCallable()

        // Verify the results
    }

    @Test
    void testTryCheckAndMutateRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.checkAndMutateRowCallable(...).
        def conditionalRowMutationBooleanUnaryCallable = new UnaryCallable<ConditionalRowMutation, Boolean>() {
            @Override
            ApiFuture<Boolean> futureCall(final ConditionalRowMutation request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.checkAndMutateRowCallable()).thenReturn(conditionalRowMutationBooleanUnaryCallable)

        // Run the test
        myClassUnderTest.tryCheckAndMutateRowCallable()

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRow() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRow(...).
        def row = Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))])
        when(mockBigtableDataClient.readModifyWriteRow(any(ReadModifyWriteRow.class))).thenReturn(row)

        // Run the test
        myClassUnderTest.tryReadModifyWriteRow()

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRow_BigtableDataClientThrowsApiException() {
        // Setup
        when(mockBigtableDataClient.readModifyWriteRow(any(ReadModifyWriteRow.class))).thenThrow(ApiException.class)

        // Run the test
        assertThrows(ApiException.class, {
            myClassUnderTest.tryReadModifyWriteRow()
        })
    }

    @Test
    void testTryReadModifyWriteRowAsync() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFuture(Row.create(ByteString.copyFrom("content".getBytes()),
                [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                        ByteString.copyFrom("content".getBytes()))]))
        when(mockBigtableDataClient.readModifyWriteRowAsync(any(ReadModifyWriteRow.class))).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowAsync()

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowAsync_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowAsync(...).
        def rowApiFuture = ApiFutures.immediateFailedFuture(new Exception("message"))
        when(mockBigtableDataClient.readModifyWriteRowAsync(any(ReadModifyWriteRow.class))).thenReturn(rowApiFuture)

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowAsync()

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowCallable() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowCallable(...).
        def readModifyWriteRowRowUnaryCallable = new UnaryCallable<ReadModifyWriteRow, Row>() {
            @Override
            ApiFuture<Row> futureCall(final ReadModifyWriteRow request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.set(Row.create(ByteString.copyFrom("content".getBytes()),
                        [RowCell.create("family", ByteString.copyFrom("content".getBytes()), 0L, ["value"],
                                ByteString.copyFrom("content".getBytes()))]))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readModifyWriteRowCallable()).thenReturn(readModifyWriteRowRowUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowCallable()

        // Verify the results
    }

    @Test
    void testTryReadModifyWriteRowCallable_BigtableDataClientReturnsFailure() {
        // Setup
        // Configure BigtableDataClient.readModifyWriteRowCallable(...).
        def readModifyWriteRowRowUnaryCallable = new UnaryCallable<ReadModifyWriteRow, Row>() {
            @Override
            ApiFuture<Row> futureCall(final ReadModifyWriteRow request, final ApiCallContext context) {
                def settableApiFuture = SettableApiFuture.create()
                settableApiFuture.setException(new Exception("Message"))
                return settableApiFuture
            }
        }
        when(mockBigtableDataClient.readModifyWriteRowCallable()).thenReturn(readModifyWriteRowRowUnaryCallable)

        // Run the test
        myClassUnderTest.tryReadModifyWriteRowCallable()

        // Verify the results
    }

    @Test
    void testTryClose() {
        // Setup
        // Run the test
        myClassUnderTest.tryClose()

        // Verify the results
        verify(mockBigtableDataClient).close()
    }

    @Test
    void testTryCreate1() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate1()

        // Verify the results
    }

    @Test
    void testTryCreate1_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate1()
        })
    }

    @Test
    void testTryCreate2() {
        // Setup
        // Run the test
        myClassUnderTest.tryCreate2()

        // Verify the results
    }

    @Test
    void testTryCreate2_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, {
            myClassUnderTest.tryCreate2()
        })
    }
}
