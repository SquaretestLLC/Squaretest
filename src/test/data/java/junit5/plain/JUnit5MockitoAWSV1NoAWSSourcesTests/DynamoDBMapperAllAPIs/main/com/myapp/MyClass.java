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

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.s3.model.Region;
import com.myapp.orders.StoredOrder;

import java.util.List;
import java.util.Map;

public class MyClass {

    private DynamoDBMapperConfig defaults;

    private AbstractDynamoDBMapper dynamoDBMapper;

    public MyClass(final AbstractDynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void tryGetTableModel() {
        final DynamoDBMapperTableModel<StoredOrder> result = dynamoDBMapper.getTableModel(StoredOrder.class);
    }

    public void tryGetTableModel1() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final DynamoDBMapperTableModel<StoredOrder> result = dynamoDBMapper.getTableModel(StoredOrder.class, config);
    }

    public void tryLoad() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final StoredOrder result = dynamoDBMapper.load(StoredOrder.class, "hashKey", config);
    }

    public void tryLoad1() {
        final StoredOrder result = dynamoDBMapper.load(StoredOrder.class, "hashKey");
    }

    public void tryLoad2() {
        final StoredOrder result = dynamoDBMapper.load(StoredOrder.class, "hashKey", "rangeKey");
    }

    public void tryLoad3() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final StoredOrder result = dynamoDBMapper.load(StoredOrder.class, "hashKey", "rangeKey", config);
    }

    public void tryLoad4() {
        final StoredOrder keyObject = null;
        final StoredOrder result = dynamoDBMapper.load(keyObject);
    }

    public void tryLoad5() {
        final StoredOrder keyObject = null;
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final StoredOrder result = dynamoDBMapper.load(keyObject, config);
    }

    public void tryMarshallIntoObject() {
        final Map<String, AttributeValue> itemAttributes = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        final StoredOrder result = dynamoDBMapper.marshallIntoObject(StoredOrder.class, itemAttributes);
    }

    public void tryMarshallIntoObject1() {
        final Map<String, AttributeValue> itemAttributes = Map.ofEntries(Map.entry("value", new AttributeValue("s")));
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final StoredOrder result = dynamoDBMapper.marshallIntoObject(StoredOrder.class, itemAttributes, config);
    }

    public void tryMarshallIntoObjects() {
        final List<Map<String, AttributeValue>> itemAttributes = List.of(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        final List<StoredOrder> result = dynamoDBMapper.marshallIntoObjects(StoredOrder.class, itemAttributes);
    }

    public void tryMarshallIntoObjects1() {
        final List<Map<String, AttributeValue>> itemAttributes = List.of(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final List<StoredOrder> result = dynamoDBMapper.marshallIntoObjects(StoredOrder.class, itemAttributes, config);
    }

    public void trySave() {
        final StoredOrder object = null;
        dynamoDBMapper.save(object);
    }

    public void trySave1() {
        final StoredOrder object = null;
        final DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        dynamoDBMapper.save(object, saveExpression);
    }

    public void trySave2() {
        final StoredOrder object = null;
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        dynamoDBMapper.save(object, config);
    }

    public void trySave3() {
        final StoredOrder object = null;
        final DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        dynamoDBMapper.save(object, saveExpression, config);
    }

    public void tryDelete() {
        dynamoDBMapper.delete("object");
    }

    public void tryDelete1() {
        final DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression();
        dynamoDBMapper.delete("object", deleteExpression);
    }

    public void tryDelete2() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        dynamoDBMapper.delete("object", config);
    }

    public void tryDelete3() {
        final StoredOrder object = null;
        final DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        dynamoDBMapper.delete(object, deleteExpression, config);
    }

    public void tryTransactionWrite() {
        final TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
        dynamoDBMapper.transactionWrite(transactionWriteRequest);
    }

    public void tryTransactionWrite1() {
        final TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        dynamoDBMapper.transactionWrite(transactionWriteRequest, config);
    }

    public void tryTransactionLoad() {
        final TransactionLoadRequest transactionLoadRequest = new TransactionLoadRequest();
        final List<Object> result = dynamoDBMapper.transactionLoad(transactionLoadRequest);
    }

    public void tryTransactionLoad1() {
        final TransactionLoadRequest transactionLoadRequest = new TransactionLoadRequest();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final List<Object> result = dynamoDBMapper.transactionLoad(transactionLoadRequest, config);
    }

    public void tryBatchDelete() {
        final Iterable<? extends Object> objectsToDelete = List.of("value");
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchDelete(objectsToDelete);
    }

    public void tryBatchDelete1() {
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchDelete("objectsToDelete");
    }

    public void tryBatchSave() {
        final Iterable<? extends Object> objectsToSave = List.of("value");
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchSave(objectsToSave);
    }

    public void tryBatchSave1() {
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchSave("objectsToSave");
    }

    public void tryBatchWrite() {
        final Iterable<? extends Object> objectsToWrite = List.of("value");
        final Iterable<? extends Object> objectsToDelete = List.of("value");
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchWrite(objectsToWrite, objectsToDelete);
    }

    public void tryBatchWrite1() {
        final Iterable<? extends Object> objectsToWrite = List.of("value");
        final Iterable<? extends Object> objectsToDelete = List.of("value");
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final List<DynamoDBMapper.FailedBatch> result = dynamoDBMapper.batchWrite(objectsToWrite, objectsToDelete, config);
    }

    public void tryBatchLoad() {
        final Iterable<? extends Object> itemsToGet = List.of("value");
        final Map<String, List<Object>> result = dynamoDBMapper.batchLoad(itemsToGet);
    }

    public void tryBatchLoad1() {
        final Iterable<? extends Object> itemsToGet = List.of("value");
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final Map<String, List<Object>> result = dynamoDBMapper.batchLoad(itemsToGet, config);
    }

    public void tryBatchLoad2() {
        final Map<Class<?>, List<KeyPair>> itemsToGet = Map.ofEntries(Map.entry(StoredOrder.class, List.of(new KeyPair().withHashKey("hashKey").withRangeKey("rangeKey"))));
        final Map<String, List<Object>> result = dynamoDBMapper.batchLoad(itemsToGet);
    }

    public void tryBatchLoad3() {
        final Map<Class<?>, List<KeyPair>> itemsToGet = Map.ofEntries(Map.entry(StoredOrder.class, List.of(new KeyPair().withHashKey("hashKey").withRangeKey("rangeKey"))));
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final Map<String, List<Object>> result = dynamoDBMapper.batchLoad(itemsToGet, config);
    }

    public void tryScan() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final PaginatedScanList<StoredOrder> result = dynamoDBMapper.scan(StoredOrder.class, scanExpression);
    }

    public void tryScan1() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final PaginatedScanList<StoredOrder> result = dynamoDBMapper.scan(StoredOrder.class, scanExpression, config);
    }

    public void tryParallelScan() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final PaginatedParallelScanList<StoredOrder> result = dynamoDBMapper.parallelScan(StoredOrder.class, scanExpression, 0);
    }

    public void tryParallelScan1() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final PaginatedParallelScanList<StoredOrder> result = dynamoDBMapper.parallelScan(StoredOrder.class, scanExpression, 0, config);
    }

    public void tryScanPage() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final ScanResultPage<StoredOrder> result = dynamoDBMapper.scanPage(StoredOrder.class, scanExpression);
    }

    public void tryScanPage1() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final ScanResultPage<StoredOrder> result = dynamoDBMapper.scanPage(StoredOrder.class, scanExpression, config);
    }

    public void tryCount() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final int result = dynamoDBMapper.count(StoredOrder.class, scanExpression);
    }

    public void tryCount1() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final int result = dynamoDBMapper.count(StoredOrder.class, scanExpression, config);
    }

    public void tryQuery() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final PaginatedQueryList<StoredOrder> result = dynamoDBMapper.query(StoredOrder.class, queryExpression);
    }

    public void tryQuery1() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final PaginatedQueryList<StoredOrder> result = dynamoDBMapper.query(StoredOrder.class, queryExpression, config);
    }

    public void tryQueryPage() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final QueryResultPage<StoredOrder> result = dynamoDBMapper.queryPage(StoredOrder.class, queryExpression);
    }

    public void tryQueryPage1() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final QueryResultPage<StoredOrder> result = dynamoDBMapper.queryPage(StoredOrder.class, queryExpression, config);
    }

    public void tryCount2() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final int result = dynamoDBMapper.count(StoredOrder.class, queryExpression);
    }

    public void tryCount3() {
        final DynamoDBQueryExpression<StoredOrder> queryExpression = new DynamoDBQueryExpression<>();
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final int result = dynamoDBMapper.count(StoredOrder.class, queryExpression, config);
    }

    public void tryGetS3ClientCache() {
        final S3ClientCache result = dynamoDBMapper.getS3ClientCache();
    }

    public void tryCreateS3Link() {
        final S3Link result = dynamoDBMapper.createS3Link("bucketName", "key");
    }

    public void tryCreateS3Link1() {
        final Region s3region = Region.US_Standard;
        final S3Link result = dynamoDBMapper.createS3Link(s3region, "bucketName", "key");
    }

    public void tryCreateS3Link2() {
        final S3Link result = dynamoDBMapper.createS3Link("s3region", "bucketName", "key");
    }

    public void tryGenerateCreateTableRequest() {
        final CreateTableRequest result = dynamoDBMapper.generateCreateTableRequest(StoredOrder.class);
    }

    public void tryGenerateCreateTableRequest1() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final CreateTableRequest result = dynamoDBMapper.generateCreateTableRequest(StoredOrder.class, config);
    }

    public void tryGenerateDeleteTableRequest() {
        final DeleteTableRequest result = dynamoDBMapper.generateDeleteTableRequest(StoredOrder.class);
    }

    public void tryGenerateDeleteTableRequest1() {
        final DynamoDBMapperConfig config = DynamoDBMapperConfig.builder().build();
        final DeleteTableRequest result = dynamoDBMapper.generateDeleteTableRequest(StoredOrder.class, config);
    }
}
