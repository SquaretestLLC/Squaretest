package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private DynamoDBMapper mockDynamoDBMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper);
    }

    @Test
    void testGetInfo() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Run the test
        final OrderAndCustomer result = myClassUnderTest.getInfo("orderId", "customerId");

        // Verify the results
    }

    @Test
    void testGetInfo_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(null);

        // Run the test
        final OrderAndCustomer result = myClassUnderTest.getInfo("orderId", "customerId");

        // Verify the results
    }

    @Test
    void testGetInfo_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getInfo("orderId", "customerId"));
    }

    @Test
    void testGetCustomer() {
        // Setup
        // Configure DynamoDBMapper.load(...).
        final Customer customer = new Customer();
        customer.setCustomerId("customerId");
        customer.setCustomerOtherValue("customerOtherValue");
        when(mockDynamoDBMapper.load(Customer.class, "customerId")).thenReturn(customer);

        // Run the test
        final Customer result = myClassUnderTest.getCustomer("customerId");

        // Verify the results
    }

    @Test
    void testGetCustomer_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(Customer.class, "customerId")).thenReturn(null);

        // Run the test
        final Customer result = myClassUnderTest.getCustomer("customerId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetCustomer_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(Customer.class, "customerId")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getCustomer("customerId"));
    }

    @Test
    void testGetOrder() {
        // Setup
        final StoredOrder expectedResult = new StoredOrder();
        expectedResult.setPurchaseId("purchaseId");
        expectedResult.setNameOnTheLicense("nameOnTheLicense");
        expectedResult.setLicenseType(LicenseType.SQT1_IND);
        expectedResult.setNumberOfUsers(0);
        expectedResult.setDeliveryEmailAddress("deliveryEmailAddress");

        // Configure DynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(storedOrder);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetOrder_DynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenReturn(null);

        // Run the test
        final StoredOrder result = myClassUnderTest.getOrder("orderId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOrder_DynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "orderId")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.getOrder("orderId"));
    }
}
