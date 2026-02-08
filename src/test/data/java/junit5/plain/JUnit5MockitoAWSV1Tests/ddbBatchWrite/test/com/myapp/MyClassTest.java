package com.myapp;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AmazonDynamoDB mockAmazonDynamoDB;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAmazonDynamoDB);
    }

    @Test
    void testPutOrders1() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenReturn(new BatchWriteItemResult());

        // Run the test
        myClassUnderTest.putOrders1(orders);

        // Verify the results
    }

    @Test
    void testPutOrders1_AmazonDynamoDBReturnsFailure() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenReturn(new BatchWriteItemResult().addUnprocessedItemsEntry("Key", Arrays.asList(new WriteRequest())));

        // Run the test
        assertThrows(BatchWriteException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_AmazonDynamoDBThrowsProvisionedThroughputExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenThrow(ProvisionedThroughputExceededException.class);

        // Run the test
        assertThrows(ProvisionedThroughputExceededException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_AmazonDynamoDBThrowsResourceNotFoundException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenThrow(ResourceNotFoundException.class);

        // Run the test
        assertThrows(ResourceNotFoundException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_AmazonDynamoDBThrowsItemCollectionSizeLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenThrow(ItemCollectionSizeLimitExceededException.class);

        // Run the test
        assertThrows(ItemCollectionSizeLimitExceededException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_AmazonDynamoDBThrowsRequestLimitExceededException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenThrow(RequestLimitExceededException.class);

        // Run the test
        assertThrows(RequestLimitExceededException.class, () -> myClassUnderTest.putOrders1(orders));
    }

    @Test
    void testPutOrders1_AmazonDynamoDBThrowsInternalServerErrorException() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(Arrays.asList(product));
        order.setOtherId("otherId");
        final List<Order> orders = Arrays.asList(order);
        when(mockAmazonDynamoDB.batchWriteItem(new BatchWriteItemRequest(new HashMap<String, List<WriteRequest>>() {{
            put("TableName", Arrays.asList(
                    new WriteRequest().withPutRequest(new PutRequest().withItem(
                            new HashMap<String, AttributeValue>() {{
                                put("PrimaryKeyName", new AttributeValue().withS("PrimaryKeyValue"));
                            }}
                    ))
            ));
        }}))).thenThrow(InternalServerErrorException.class);

        // Run the test
        assertThrows(InternalServerErrorException.class, () -> myClassUnderTest.putOrders1(orders));
    }
}
