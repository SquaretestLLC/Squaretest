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
import com.amazonaws.services.s3.model.Region;
import com.myapp.orders.StoredOrder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyClass {

    @Nonnull
    private final DynamoDBMapper dynamoDBMapper;

    public MyClass(@Nonnull DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public int getOrderCountQuery() {
        return dynamoDBMapper.count(StoredOrder.class, new DynamoDBQueryExpression<>(), DynamoDBMapperConfig.DEFAULT);
    }

    public int getOrderCountScan() {
        return dynamoDBMapper.count(StoredOrder.class, new DynamoDBScanExpression(), DynamoDBMapperConfig.DEFAULT);
    }

    public S3Link createS3LinkWithStrings() {
        return dynamoDBMapper.createS3Link("region", "bucketName", "key");
    }

    public S3Link createS3LinkWithRegion() {
        return dynamoDBMapper.createS3Link(Region.US_West_2, "bucketName", "key");
    }

    public void deleteOrders() {
        dynamoDBMapper.delete(new StoredOrder(), new DynamoDBDeleteExpression(), DynamoDBMapperConfig.DEFAULT);
    }

    public void generateCreateTableRequest() {
        final CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    public void generateDeleteTableRequest() {
        final DeleteTableRequest deleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    public void getTableModel() {
        final DynamoDBMapperTableModel<StoredOrder> ret = dynamoDBMapper.getTableModel(StoredOrder.class, DynamoDBMapperConfig.DEFAULT);
    }

    public void marshallIntoObject() {
        final StoredOrder storedOrder = dynamoDBMapper.marshallIntoObject(StoredOrder.class, new HashMap<>(), DynamoDBMapperConfig.DEFAULT);
    }

    public void marshallIntoList() {
        final List<StoredOrder> orders = dynamoDBMapper.marshallIntoObjects(StoredOrder.class, Arrays.asList(), DynamoDBMapperConfig.DEFAULT);
    }

    public List<StoredOrder> getAllWithQueryOrders() throws OrderStoreException {
        try {
            final PaginatedQueryList<StoredOrder> orders = dynamoDBMapper.query(StoredOrder.class, new DynamoDBQueryExpression<>(), new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.APPEND_SET));
            return new ArrayList<>(orders);
        } catch(final Exception e) {
            throw new OrderStoreException(e);
        }
    }

    public List<StoredOrder> getAllOrdersWithParallelScan() {
        final PaginatedParallelScanList<StoredOrder> orders = dynamoDBMapper.parallelScan(StoredOrder.class, new DynamoDBScanExpression(), 100, DynamoDBMapperConfig.DEFAULT);
        return new ArrayList<>(orders);
    }

    public List<StoredOrder> getOrdersInPageWithQuery() {
        final QueryResultPage<StoredOrder> orders = dynamoDBMapper.queryPage(StoredOrder.class, new DynamoDBQueryExpression<>(), DynamoDBMapperConfig.DEFAULT);
        return orders.getResults();
    }

    public List<StoredOrder> getAllOrdersWithScan() {
        final PaginatedScanList<StoredOrder> orders = dynamoDBMapper.scan(StoredOrder.class, new DynamoDBScanExpression(), DynamoDBMapperConfig.DEFAULT);
        return new ArrayList<>(orders);
    }

    public List<StoredOrder> getAllOrdersInpageWithScan() {
        final ScanResultPage<StoredOrder> orders = dynamoDBMapper.scanPage(StoredOrder.class, new DynamoDBScanExpression(), DynamoDBMapperConfig.DEFAULT);
        return orders.getResults();
    }

    public void getObjectsInTransaction() {
        final List<Object> items = dynamoDBMapper.transactionLoad(new TransactionLoadRequest(), DynamoDBMapperConfig.DEFAULT);
    }

    public void writeObjectsInTransaction() {
        dynamoDBMapper.transactionWrite(new TransactionWriteRequest(), DynamoDBMapperConfig.DEFAULT);
    }

    public void batchDeleteObjects() {
        dynamoDBMapper.batchDelete(new StoredOrder());
    }

    public void batchDeleteObjects1() {
        final List<DynamoDBMapper.FailedBatch> ret = dynamoDBMapper.batchDelete(new StoredOrder());
    }

    public void batchDeleteObjectsWithList() {
        dynamoDBMapper.batchDelete(Arrays.asList(new StoredOrder()));
    }

    public void batchDeleteObjectsWithList1() {
        final List<DynamoDBMapper.FailedBatch> ret = dynamoDBMapper.batchDelete(Arrays.asList(new StoredOrder()));
    }
}
