package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;
    @Mock
    private MetricService mockMetricService;
    @Mock
    private OtherService mockOtherService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper, mockMetricService, mockOtherService);
    }

    @Test
    void testGetOrders1() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("criteria");

        // Verify the results
    }

    @Test
    void testGetOrders1_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<Order> result = myClassUnderTest.getOrders1("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders1_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders1("criteria"));
    }

    @Test
    void testGetOrders2() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders2("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders2_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders2("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders2_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders2("criteria"));
    }

    @Test
    void testGetOrders2_OtherServiceReturnsNull() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders2("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders3() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders3("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders3_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders3("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders3_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders3("criteria"));
    }

    @Test
    void testGetOrders3_OtherServiceReturnsNull() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders3("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders4() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders4("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders4_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders4("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders4_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders4("criteria"));
    }

    @Test
    void testGetOrders4_OtherServiceReturnsNull() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<Order> mockPaginatedParallelScanList = mock(PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(Order.class), any(DynamoDBScanExpression.class), eq(10)))
                .thenReturn(mockPaginatedParallelScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders4("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders5() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.scan(...).
        final PaginatedScanList<Order> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(mockPaginatedScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders5("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders5_DynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure DynamoDBMapper.scan(...).
        final PaginatedScanList<Order> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(mockPaginatedScanList);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders5("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders5_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders5("criteria"));
    }

    @Test
    void testGetOrders5_OtherServiceReturnsNull() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.scan(...).
        final PaginatedScanList<Order> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(mockPaginatedScanList);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders5("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrders6() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.scanPage(...).
        final ScanResultPage<Order> orderScanResultPage = new ScanResultPage<>();
        orderScanResultPage.setResults(List.of());
        orderScanResultPage.setLastEvaluatedKey(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        when(mockDynamoDBMapper.scanPage(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(orderScanResultPage);

        when(mockOtherService.getOtherData("otherId")).thenReturn("otherData");

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders6("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordPaginatedResults("criteria");
    }

    @Test
    void testGetOrders6_DynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(new ScanResultPage<>());

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders6("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetOrders6_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrders6("criteria"));
    }

    @Test
    void testGetOrders6_OtherServiceReturnsNull() {
        // Setup
        final Order order = new Order();
        order.setId("id");
        order.setShipAddress("shipAddress");
        order.setShipDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Product product = new Product();
        order.setProducts(List.of(product));
        order.setOtherId("otherId");
        final List<OrderAndOther> expectedResult = List.of(new OrderAndOther(order, "otherData"));

        // Configure DynamoDBMapper.scanPage(...).
        final ScanResultPage<Order> orderScanResultPage = new ScanResultPage<>();
        orderScanResultPage.setResults(List.of());
        orderScanResultPage.setLastEvaluatedKey(Map.ofEntries(Map.entry("value", new AttributeValue("s"))));
        when(mockDynamoDBMapper.scanPage(eq(Order.class), any(DynamoDBScanExpression.class)))
                .thenReturn(orderScanResultPage);

        when(mockOtherService.getOtherData("otherId")).thenReturn(null);

        // Run the test
        final List<OrderAndOther> result = myClassUnderTest.getOrders6("criteria");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordPaginatedResults("criteria");
    }
}
