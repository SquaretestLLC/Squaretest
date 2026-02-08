/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import com.google.api.core.ApiFuture;
import com.google.api.gax.batching.Batcher;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStream;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.BigtableDataSettings;
import com.google.cloud.bigtable.data.v2.models.*;
import com.google.cloud.bigtable.data.v2.stub.EnhancedBigtableStub;
import com.google.protobuf.ByteString;

import java.util.List;

public class MyClass {

    private BigtableDataClient bigtableDataClient;

    public MyClass(final BigtableDataClient bigtableDataClient) {
        this.bigtableDataClient = bigtableDataClient;
    }

    public void tryExists1() {
        final boolean result = bigtableDataClient.exists("tableId", "rowKey");
    }

    public void tryExists2() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final boolean result = bigtableDataClient.exists("tableId", rowKey);
    }

    public void tryExistsAsync1() {
        final ApiFuture<Boolean> result = bigtableDataClient.existsAsync("tableId", "rowKey");
    }

    public void tryExistsAsync2() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final ApiFuture<Boolean> result = bigtableDataClient.existsAsync("tableId", rowKey);
    }

    public void tryReadRow1() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final Row result = bigtableDataClient.readRow("tableId", rowKey);
    }

    public void tryReadRow2() {
        final Row result = bigtableDataClient.readRow("tableId", "rowKey");
    }

    public void tryReadRow3() {
        final Filters.Filter filter = null;
        final Row result = bigtableDataClient.readRow("tableId", "rowKey", filter);
    }

    public void tryReadRow4() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final Filters.Filter filter = null;
        final Row result = bigtableDataClient.readRow("tableId", rowKey, filter);
    }

    public void tryReadRowAsync1() {
        final ApiFuture<Row> result = bigtableDataClient.readRowAsync("tableId", "rowKey");
    }

    public void tryReadRowAsync2() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final ApiFuture<Row> result = bigtableDataClient.readRowAsync("tableId", rowKey);
    }

    public void tryReadRowAsync3() {
        final Filters.Filter filter = null;
        final ApiFuture<Row> result = bigtableDataClient.readRowAsync("tableId", "rowKey", filter);
    }

    public void tryReadRowAsync4() {
        final ByteString rowKey = ByteString.copyFrom("content".getBytes());
        final Filters.Filter filter = null;
        final ApiFuture<Row> result = bigtableDataClient.readRowAsync("tableId", rowKey, filter);
    }

    public void tryReadRowCallable1() {
        final UnaryCallable<Query, Row> result = bigtableDataClient.readRowCallable();
    }

    public void tryReadRowCallable2() {
        final RowAdapter<String> rowAdapter = null;
        final UnaryCallable<Query, String> result = bigtableDataClient.readRowCallable(rowAdapter);
    }

    public void tryReadRows() {
        final Query query = Query.create("tableId");
        final ServerStream<Row> result = bigtableDataClient.readRows(query);
    }

    public void tryReadRowsAsync() {
        final Query query = Query.create("tableId");
        final ResponseObserver<Row> observer = null;
        bigtableDataClient.readRowsAsync(query, observer);
    }

    public void tryReadRowsCallable1() {
        final ServerStreamingCallable<Query, Row> result = bigtableDataClient.readRowsCallable();
    }

    public void tryReadRowsCallable2() {
        final RowAdapter<String> rowAdapter = null;
        final ServerStreamingCallable<Query, String> result = bigtableDataClient.readRowsCallable(rowAdapter);
    }

    public void trySampleRowKeys() {
        final List<KeyOffset> result = bigtableDataClient.sampleRowKeys("tableId");
    }

    public void trySampleRowKeysAsync() {
        final ApiFuture<List<KeyOffset>> result = bigtableDataClient.sampleRowKeysAsync("tableId");
    }

    public void trySampleRowKeysCallable() {
        final UnaryCallable<String, List<KeyOffset>> result = bigtableDataClient.sampleRowKeysCallable();
    }

    public void tryMutateRow() {
        final RowMutation rowMutation = RowMutation.create("tableId", "key");
        bigtableDataClient.mutateRow(rowMutation);
    }

    public void tryMutateRowAsync() {
        final RowMutation rowMutation = RowMutation.create("tableId", "key");
        final ApiFuture<Void> result = bigtableDataClient.mutateRowAsync(rowMutation);
    }

    public void tryMutateRowCallable() {
        final UnaryCallable<RowMutation, Void> result = bigtableDataClient.mutateRowCallable();
    }

    public void tryBulkMutateRows() {
        final BulkMutation mutation = BulkMutation.create("tableId");
        bigtableDataClient.bulkMutateRows(mutation);
    }

    public void tryNewBulkMutationBatcher() {
        final Batcher<RowMutationEntry, Void> result = bigtableDataClient.newBulkMutationBatcher("tableId");
    }

    public void tryNewBulkReadRowsBatcher1() {
        final Batcher<ByteString, Row> result = bigtableDataClient.newBulkReadRowsBatcher("tableId");
    }

    public void tryNewBulkReadRowsBatcher2() {
        final Filters.Filter filter = null;
        final Batcher<ByteString, Row> result = bigtableDataClient.newBulkReadRowsBatcher("tableId", filter);
    }

    public void tryBulkMutateRowsAsync() {
        final BulkMutation mutation = BulkMutation.create("tableId");
        final ApiFuture<Void> result = bigtableDataClient.bulkMutateRowsAsync(mutation);
    }

    public void tryBulkMutationCallable() {
        final UnaryCallable<BulkMutation, Void> result = bigtableDataClient.bulkMutationCallable();
    }

    public void tryCheckAndMutateRow() {
        final ConditionalRowMutation mutation = ConditionalRowMutation.create("tableId", "rowKey");
        final Boolean result = bigtableDataClient.checkAndMutateRow(mutation);
    }

    public void tryCheckAndMutateRowAsync() {
        final ConditionalRowMutation mutation = ConditionalRowMutation.create("tableId", "rowKey");
        final ApiFuture<Boolean> result = bigtableDataClient.checkAndMutateRowAsync(mutation);
    }

    public void tryCheckAndMutateRowCallable() {
        final UnaryCallable<ConditionalRowMutation, Boolean> result = bigtableDataClient.checkAndMutateRowCallable();
    }

    public void tryReadModifyWriteRow() {
        final ReadModifyWriteRow mutation = ReadModifyWriteRow.create("tableId", "key");
        final Row result = bigtableDataClient.readModifyWriteRow(mutation);
    }

    public void tryReadModifyWriteRowAsync() {
        final ReadModifyWriteRow mutation = ReadModifyWriteRow.create("tableId", "key");
        final ApiFuture<Row> result = bigtableDataClient.readModifyWriteRowAsync(mutation);
    }

    public void tryReadModifyWriteRowCallable() {
        final UnaryCallable<ReadModifyWriteRow, Row> result = bigtableDataClient.readModifyWriteRowCallable();
    }

    public void tryClose() {
        bigtableDataClient.close();
    }

    public void tryCreate1() throws Exception {
        final BigtableDataClient result = BigtableDataClient.create("projectId", "instanceId");
    }

    public void tryCreate2() throws Exception {
        final BigtableDataSettings settings = null;
        final BigtableDataClient result = BigtableDataClient.create(settings);
    }
}
