package com.myapp;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.s3.model.Region;
import com.myapp.orders.LicenseType;
import com.myapp.orders.StoredOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AbstractDynamoDBMapper mockDynamoDBMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockDynamoDBMapper);
    }

    @Test
    void testTryGetTableModel() {
        // Setup
        when(mockDynamoDBMapper.getTableModel(StoredOrder.class)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetTableModel();

        // Verify the results
    }

    @Test
    void testTryGetTableModel1() {
        // Setup
        when(mockDynamoDBMapper.getTableModel(eq(StoredOrder.class), any(DynamoDBMapperConfig.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetTableModel1();

        // Verify the results
    }

    @Test
    void testTryLoad() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), any(DynamoDBMapperConfig.class)))
                .thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad();

        // Verify the results
    }

    @Test
    void testTryLoad_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), any(DynamoDBMapperConfig.class)))
                .thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad();

        // Verify the results
    }

    @Test
    void testTryLoad_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad());
    }

    @Test
    void testTryLoad1() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad1();

        // Verify the results
    }

    @Test
    void testTryLoad1_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad1();

        // Verify the results
    }

    @Test
    void testTryLoad1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad1());
    }

    @Test
    void testTryLoad2() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey", "rangeKey")).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad2();

        // Verify the results
    }

    @Test
    void testTryLoad2_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey", "rangeKey")).thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad2();

        // Verify the results
    }

    @Test
    void testTryLoad2_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(StoredOrder.class, "hashKey", "rangeKey"))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad2());
    }

    @Test
    void testTryLoad3() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad3();

        // Verify the results
    }

    @Test
    void testTryLoad3_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad3();

        // Verify the results
    }

    @Test
    void testTryLoad3_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.load(eq(StoredOrder.class), eq("hashKey"), eq("rangeKey"),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad3());
    }

    @Test
    void testTryLoad4() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(keyObject)).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad4();

        // Verify the results
    }

    @Test
    void testTryLoad4_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(keyObject)).thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad4();

        // Verify the results
    }

    @Test
    void testTryLoad4_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(keyObject)).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad4());
    }

    @Test
    void testTryLoad5() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryLoad5();

        // Verify the results
    }

    @Test
    void testTryLoad5_AbstractDynamoDBMapperReturnsNull() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class))).thenReturn(null);

        // Run the test
        myClassUnderTest.tryLoad5();

        // Verify the results
    }

    @Test
    void testTryLoad5_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.load(...).
        final StoredOrder keyObject = new StoredOrder();
        keyObject.setPurchaseId("purchaseId");
        keyObject.setNameOnTheLicense("nameOnTheLicense");
        keyObject.setLicenseType(LicenseType.SQT1_IND);
        keyObject.setNumberOfUsers(0);
        keyObject.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.load(eq(keyObject), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryLoad5());
    }

    @Test
    void testTryMarshallIntoObject() {
        // Setup
        // Configure AbstractDynamoDBMapper.marshallIntoObject(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.marshallIntoObject(StoredOrder.class, new HashMap<>())).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryMarshallIntoObject();

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObject1() {
        // Setup
        // Configure AbstractDynamoDBMapper.marshallIntoObject(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        when(mockDynamoDBMapper.marshallIntoObject(eq(StoredOrder.class), eq(new HashMap<>()),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrder);

        // Run the test
        myClassUnderTest.tryMarshallIntoObject1();

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects() {
        // Setup
        // Configure AbstractDynamoDBMapper.marshallIntoObjects(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> storedOrders = Arrays.asList(storedOrder);
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, Arrays.asList(new HashMap<>())))
                .thenReturn(storedOrders);

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects();

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.marshallIntoObjects(StoredOrder.class, Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects();

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects1() {
        // Setup
        // Configure AbstractDynamoDBMapper.marshallIntoObjects(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> storedOrders = Arrays.asList(storedOrder);
        when(mockDynamoDBMapper.marshallIntoObjects(eq(StoredOrder.class), eq(Arrays.asList(new HashMap<>())),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrders);

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects1();

        // Verify the results
    }

    @Test
    void testTryMarshallIntoObjects1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.marshallIntoObjects(eq(StoredOrder.class), eq(Arrays.asList(new HashMap<>())),
                any(DynamoDBMapperConfig.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryMarshallIntoObjects1();

        // Verify the results
    }

    @Test
    void testTrySave() {
        // Setup
        // Run the test
        myClassUnderTest.trySave();

        // Verify the results
        // Confirm AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).save(object);
    }

    @Test
    void testTrySave_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(object);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave());
    }

    @Test
    void testTrySave1() {
        // Setup
        // Run the test
        myClassUnderTest.trySave1();

        // Verify the results
        // Confirm AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).save(eq(object), any(DynamoDBSaveExpression.class));
    }

    @Test
    void testTrySave1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(eq(object),
                any(DynamoDBSaveExpression.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave1());
    }

    @Test
    void testTrySave2() {
        // Setup
        // Run the test
        myClassUnderTest.trySave2();

        // Verify the results
        // Confirm AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).save(eq(object), any(DynamoDBMapperConfig.class));
    }

    @Test
    void testTrySave2_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(eq(object),
                any(DynamoDBMapperConfig.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave2());
    }

    @Test
    void testTrySave3() {
        // Setup
        // Run the test
        myClassUnderTest.trySave3();

        // Verify the results
        // Confirm AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).save(eq(object), any(DynamoDBSaveExpression.class), any(DynamoDBMapperConfig.class));
    }

    @Test
    void testTrySave3_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.save(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).save(eq(object),
                any(DynamoDBSaveExpression.class), any(DynamoDBMapperConfig.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.trySave3());
    }

    @Test
    void testTryDelete() {
        // Setup
        // Run the test
        myClassUnderTest.tryDelete();

        // Verify the results
        verify(mockDynamoDBMapper).delete("object");
    }

    @Test
    void testTryDelete_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete("object");

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryDelete());
    }

    @Test
    void testTryDelete1() {
        // Setup
        // Run the test
        myClassUnderTest.tryDelete1();

        // Verify the results
        verify(mockDynamoDBMapper).delete(eq("object"), any(DynamoDBDeleteExpression.class));
    }

    @Test
    void testTryDelete1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq("object"),
                any(DynamoDBDeleteExpression.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryDelete1());
    }

    @Test
    void testTryDelete2() {
        // Setup
        // Run the test
        myClassUnderTest.tryDelete2();

        // Verify the results
        verify(mockDynamoDBMapper).delete(eq("object"), any(DynamoDBMapperConfig.class));
    }

    @Test
    void testTryDelete2_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq("object"),
                any(DynamoDBMapperConfig.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryDelete2());
    }

    @Test
    void testTryDelete3() {
        // Setup
        // Run the test
        myClassUnderTest.tryDelete3();

        // Verify the results
        // Confirm AbstractDynamoDBMapper.delete(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        verify(mockDynamoDBMapper).delete(eq(object), any(DynamoDBDeleteExpression.class),
                any(DynamoDBMapperConfig.class));
    }

    @Test
    void testTryDelete3_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        // Configure AbstractDynamoDBMapper.delete(...).
        final StoredOrder object = new StoredOrder();
        object.setPurchaseId("purchaseId");
        object.setNameOnTheLicense("nameOnTheLicense");
        object.setLicenseType(LicenseType.SQT1_IND);
        object.setNumberOfUsers(0);
        object.setDeliveryEmailAddress("deliveryEmailAddress");
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).delete(eq(object),
                any(DynamoDBDeleteExpression.class), any(DynamoDBMapperConfig.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryDelete3());
    }

    @Test
    void testTryTransactionWrite() {
        // Setup
        // Run the test
        myClassUnderTest.tryTransactionWrite();

        // Verify the results
        verify(mockDynamoDBMapper).transactionWrite(any(TransactionWriteRequest.class));
    }

    @Test
    void testTryTransactionWrite_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).transactionWrite(
                any(TransactionWriteRequest.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryTransactionWrite());
    }

    @Test
    void testTryTransactionWrite1() {
        // Setup
        // Run the test
        myClassUnderTest.tryTransactionWrite1();

        // Verify the results
        verify(mockDynamoDBMapper).transactionWrite(any(TransactionWriteRequest.class),
                any(DynamoDBMapperConfig.class));
    }

    @Test
    void testTryTransactionWrite1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        doThrow(AmazonDynamoDBException.class).when(mockDynamoDBMapper).transactionWrite(
                any(TransactionWriteRequest.class), any(DynamoDBMapperConfig.class));

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryTransactionWrite1());
    }

    @Test
    void testTryTransactionLoad() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class))).thenReturn(Arrays.asList("value"));

        // Run the test
        myClassUnderTest.tryTransactionLoad();

        // Verify the results
    }

    @Test
    void testTryTransactionLoad_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryTransactionLoad();

        // Verify the results
    }

    @Test
    void testTryTransactionLoad_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryTransactionLoad());
    }

    @Test
    void testTryTransactionLoad1() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenReturn(Arrays.asList("value"));

        // Run the test
        myClassUnderTest.tryTransactionLoad1();

        // Verify the results
    }

    @Test
    void testTryTransactionLoad1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryTransactionLoad1();

        // Verify the results
    }

    @Test
    void testTryTransactionLoad1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.transactionLoad(any(TransactionLoadRequest.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryTransactionLoad1());
    }

    @Test
    void testTryBatchDelete() {
        // Setup
        when(mockDynamoDBMapper.batchDelete(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchDelete();

        // Verify the results
    }

    @Test
    void testTryBatchDelete_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchDelete(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchDelete(any(Iterable.class))).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchDelete();

        // Verify the results
    }

    @Test
    void testTryBatchDelete_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchDelete(any(Iterable.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchDelete());
    }

    @Test
    void testTryBatchDelete1() {
        // Setup
        when(mockDynamoDBMapper.batchDelete("objectsToDelete")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchDelete1();

        // Verify the results
    }

    @Test
    void testTryBatchDelete1_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchDelete(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchDelete("objectsToDelete")).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchDelete1();

        // Verify the results
    }

    @Test
    void testTryBatchDelete1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchDelete("objectsToDelete")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchDelete1());
    }

    @Test
    void testTryBatchSave() {
        // Setup
        when(mockDynamoDBMapper.batchSave(any(Iterable.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchSave();

        // Verify the results
    }

    @Test
    void testTryBatchSave_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchSave(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchSave(any(Iterable.class))).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchSave();

        // Verify the results
    }

    @Test
    void testTryBatchSave_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchSave(any(Iterable.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchSave());
    }

    @Test
    void testTryBatchSave1() {
        // Setup
        when(mockDynamoDBMapper.batchSave("objectsToSave")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchSave1();

        // Verify the results
    }

    @Test
    void testTryBatchSave1_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchSave(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchSave("objectsToSave")).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchSave1();

        // Verify the results
    }

    @Test
    void testTryBatchSave1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchSave("objectsToSave")).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchSave1());
    }

    @Test
    void testTryBatchWrite() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class)))
                .thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchWrite();

        // Verify the results
    }

    @Test
    void testTryBatchWrite_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchWrite(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class))).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchWrite();

        // Verify the results
    }

    @Test
    void testTryBatchWrite_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchWrite());
    }

    @Test
    void testTryBatchWrite1() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.tryBatchWrite1();

        // Verify the results
    }

    @Test
    void testTryBatchWrite1_AbstractDynamoDBMapperReturnsFailure() {
        // Setup
        // Configure AbstractDynamoDBMapper.batchWrite(...).
        final DynamoDBMapper.FailedBatch failedBatch = new DynamoDBMapper.FailedBatch();
        final List<DynamoDBMapper.FailedBatch> failedBatches = Arrays.asList(failedBatch);
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenReturn(failedBatches);

        // Run the test
        myClassUnderTest.tryBatchWrite1();

        // Verify the results
    }

    @Test
    void testTryBatchWrite1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchWrite(any(Iterable.class), any(Iterable.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchWrite1());
    }

    @Test
    void testTryBatchLoad() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class))).thenReturn(new HashMap<>());

        // Run the test
        myClassUnderTest.tryBatchLoad();

        // Verify the results
    }

    @Test
    void testTryBatchLoad_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchLoad());
    }

    @Test
    void testTryBatchLoad1() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class), any(DynamoDBMapperConfig.class)))
                .thenReturn(new HashMap<>());

        // Run the test
        myClassUnderTest.tryBatchLoad1();

        // Verify the results
    }

    @Test
    void testTryBatchLoad1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(any(Iterable.class), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchLoad1());
    }

    @Test
    void testTryBatchLoad2() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(new HashMap<>())).thenReturn(new HashMap<>());

        // Run the test
        myClassUnderTest.tryBatchLoad2();

        // Verify the results
    }

    @Test
    void testTryBatchLoad2_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(new HashMap<>())).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchLoad2());
    }

    @Test
    void testTryBatchLoad3() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(eq(new HashMap<>()), any(DynamoDBMapperConfig.class)))
                .thenReturn(new HashMap<>());

        // Run the test
        myClassUnderTest.tryBatchLoad3();

        // Verify the results
    }

    @Test
    void testTryBatchLoad3_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.batchLoad(eq(new HashMap<>()), any(DynamoDBMapperConfig.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryBatchLoad3());
    }

    @Test
    void testTryScan() {
        // Setup
        // Configure AbstractDynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenReturn(mockPaginatedScanList);

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test
    void testTryScan_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenReturn(mockPaginatedScanList);

        // Run the test
        myClassUnderTest.tryScan();

        // Verify the results
    }

    @Test
    void testTryScan_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryScan());
    }

    @Test
    void testTryScan1() {
        // Setup
        // Configure AbstractDynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedScanList);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.scan(...).
        final PaginatedScanList<StoredOrder> mockPaginatedScanList = mock(PaginatedScanList.class);
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedScanList);

        // Run the test
        myClassUnderTest.tryScan1();

        // Verify the results
    }

    @Test
    void testTryScan1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryScan1());
    }

    @Test
    void testTryParallelScan() {
        // Setup
        // Configure AbstractDynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(0))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        myClassUnderTest.tryParallelScan();

        // Verify the results
    }

    @Test
    void testTryParallelScan_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(0))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        myClassUnderTest.tryParallelScan();

        // Verify the results
    }

    @Test
    void testTryParallelScan_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                eq(0))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryParallelScan());
    }

    @Test
    void testTryParallelScan1() {
        // Setup
        // Configure AbstractDynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        myClassUnderTest.tryParallelScan1();

        // Verify the results
    }

    @Test
    void testTryParallelScan1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.parallelScan(...).
        final PaginatedParallelScanList<StoredOrder> mockPaginatedParallelScanList = mock(
                PaginatedParallelScanList.class);
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedParallelScanList);

        // Run the test
        myClassUnderTest.tryParallelScan1();

        // Verify the results
    }

    @Test
    void testTryParallelScan1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.parallelScan(eq(StoredOrder.class), any(DynamoDBScanExpression.class), eq(0),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryParallelScan1());
    }

    @Test
    void testTryScanPage() {
        // Setup
        // Configure AbstractDynamoDBMapper.scanPage(...).
        final ScanResultPage<StoredOrder> storedOrderScanResultPage = new ScanResultPage<>();
        storedOrderScanResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenReturn(storedOrderScanResultPage);

        // Run the test
        myClassUnderTest.tryScanPage();

        // Verify the results
    }

    @Test
    void testTryScanPage_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenReturn(new ScanResultPage<>());

        // Run the test
        myClassUnderTest.tryScanPage();

        // Verify the results
    }

    @Test
    void testTryScanPage_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryScanPage());
    }

    @Test
    void testTryScanPage1() {
        // Setup
        // Configure AbstractDynamoDBMapper.scanPage(...).
        final ScanResultPage<StoredOrder> storedOrderScanResultPage = new ScanResultPage<>();
        storedOrderScanResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrderScanResultPage);

        // Run the test
        myClassUnderTest.tryScanPage1();

        // Verify the results
    }

    @Test
    void testTryScanPage1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new ScanResultPage<>());

        // Run the test
        myClassUnderTest.tryScanPage1();

        // Verify the results
    }

    @Test
    void testTryScanPage1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.scanPage(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryScanPage1());
    }

    @Test
    void testTryCount() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class))).thenReturn(0);

        // Run the test
        myClassUnderTest.tryCount();

        // Verify the results
    }

    @Test
    void testTryCount_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryCount());
    }

    @Test
    void testTryCount1() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(0);

        // Run the test
        myClassUnderTest.tryCount1();

        // Verify the results
    }

    @Test
    void testTryCount1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBScanExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryCount1());
    }

    @Test
    void testTryQuery() {
        // Setup
        // Configure AbstractDynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(mockPaginatedQueryList);

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test
    void testTryQuery_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(mockPaginatedQueryList);

        // Run the test
        myClassUnderTest.tryQuery();

        // Verify the results
    }

    @Test
    void testTryQuery_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryQuery());
    }

    @Test
    void testTryQuery1() {
        // Setup
        // Configure AbstractDynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        // Configure AbstractDynamoDBMapper.query(...).
        final PaginatedQueryList<StoredOrder> mockPaginatedQueryList = mock(PaginatedQueryList.class);
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(mockPaginatedQueryList);

        // Run the test
        myClassUnderTest.tryQuery1();

        // Verify the results
    }

    @Test
    void testTryQuery1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.query(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryQuery1());
    }

    @Test
    void testTryQueryPage() {
        // Setup
        // Configure AbstractDynamoDBMapper.queryPage(...).
        final QueryResultPage<StoredOrder> storedOrderQueryResultPage = new QueryResultPage<>();
        storedOrderQueryResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(storedOrderQueryResultPage);

        // Run the test
        myClassUnderTest.tryQueryPage();

        // Verify the results
    }

    @Test
    void testTryQueryPage_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(new QueryResultPage<>());

        // Run the test
        myClassUnderTest.tryQueryPage();

        // Verify the results
    }

    @Test
    void testTryQueryPage_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryQueryPage());
    }

    @Test
    void testTryQueryPage1() {
        // Setup
        // Configure AbstractDynamoDBMapper.queryPage(...).
        final QueryResultPage<StoredOrder> storedOrderQueryResultPage = new QueryResultPage<>();
        storedOrderQueryResultPage.setResults(Arrays.asList());
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(storedOrderQueryResultPage);

        // Run the test
        myClassUnderTest.tryQueryPage1();

        // Verify the results
    }

    @Test
    void testTryQueryPage1_AbstractDynamoDBMapperReturnsNoItems() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new QueryResultPage<>());

        // Run the test
        myClassUnderTest.tryQueryPage1();

        // Verify the results
    }

    @Test
    void testTryQueryPage1_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.queryPage(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryQueryPage1());
    }

    @Test
    void testTryCount2() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class))).thenReturn(0);

        // Run the test
        myClassUnderTest.tryCount2();

        // Verify the results
    }

    @Test
    void testTryCount2_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class)))
                .thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryCount2());
    }

    @Test
    void testTryCount3() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenReturn(0);

        // Run the test
        myClassUnderTest.tryCount3();

        // Verify the results
    }

    @Test
    void testTryCount3_AbstractDynamoDBMapperThrowsAmazonDynamoDBException() {
        // Setup
        when(mockDynamoDBMapper.count(eq(StoredOrder.class), any(DynamoDBQueryExpression.class),
                any(DynamoDBMapperConfig.class))).thenThrow(AmazonDynamoDBException.class);

        // Run the test
        assertThrows(AmazonDynamoDBException.class, () -> myClassUnderTest.tryCount3());
    }

    @Test
    void testTryGetS3ClientCache() {
        // Setup
        when(mockDynamoDBMapper.getS3ClientCache()).thenReturn(null);

        // Run the test
        myClassUnderTest.tryGetS3ClientCache();

        // Verify the results
    }

    @Test
    void testTryCreateS3Link() {
        // Setup
        when(mockDynamoDBMapper.createS3Link("bucketName", "key")).thenReturn(S3Link.fromJson(null, "json"));

        // Run the test
        myClassUnderTest.tryCreateS3Link();

        // Verify the results
    }

    @Test
    void testTryCreateS3Link1() {
        // Setup
        when(mockDynamoDBMapper.createS3Link(Region.US_Standard, "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"));

        // Run the test
        myClassUnderTest.tryCreateS3Link1();

        // Verify the results
    }

    @Test
    void testTryCreateS3Link2() {
        // Setup
        when(mockDynamoDBMapper.createS3Link("s3region", "bucketName", "key"))
                .thenReturn(S3Link.fromJson(null, "json"));

        // Run the test
        myClassUnderTest.tryCreateS3Link2();

        // Verify the results
    }

    @Test
    void testTryGenerateCreateTableRequest() {
        // Setup
        // Configure AbstractDynamoDBMapper.generateCreateTableRequest(...).
        final CreateTableRequest createTableRequest = new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")));
        when(mockDynamoDBMapper.generateCreateTableRequest(StoredOrder.class)).thenReturn(createTableRequest);

        // Run the test
        myClassUnderTest.tryGenerateCreateTableRequest();

        // Verify the results
    }

    @Test
    void testTryGenerateCreateTableRequest1() {
        // Setup
        // Configure AbstractDynamoDBMapper.generateCreateTableRequest(...).
        final CreateTableRequest createTableRequest = new CreateTableRequest("tableName",
                Arrays.asList(new KeySchemaElement("attributeName", "keyType")));
        when(mockDynamoDBMapper.generateCreateTableRequest(eq(StoredOrder.class),
                any(DynamoDBMapperConfig.class))).thenReturn(createTableRequest);

        // Run the test
        myClassUnderTest.tryGenerateCreateTableRequest1();

        // Verify the results
    }

    @Test
    void testTryGenerateDeleteTableRequest() {
        // Setup
        when(mockDynamoDBMapper.generateDeleteTableRequest(StoredOrder.class))
                .thenReturn(new DeleteTableRequest("tableName"));

        // Run the test
        myClassUnderTest.tryGenerateDeleteTableRequest();

        // Verify the results
    }

    @Test
    void testTryGenerateDeleteTableRequest1() {
        // Setup
        when(mockDynamoDBMapper.generateDeleteTableRequest(eq(StoredOrder.class),
                any(DynamoDBMapperConfig.class))).thenReturn(new DeleteTableRequest("tableName"));

        // Run the test
        myClassUnderTest.tryGenerateDeleteTableRequest1();

        // Verify the results
    }
}
