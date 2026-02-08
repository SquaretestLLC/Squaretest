package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private GenericStore<StoredOrder> mockGenericStore;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockGenericStore);
    }

    @Test
    public void testStoreObjs() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjs("serializedObjs");

        // Verify the results
        // Confirm GenericStore.storeObjs(...).
        final StoredOrder storedOrder = new StoredOrder();
        storedOrder.setPurchaseId("purchaseId");
        storedOrder.setNameOnTheLicense("nameOnTheLicense");
        storedOrder.setLicenseType(LicenseType.SQT1_IND);
        storedOrder.setNumberOfUsers(0);
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress");
        final List<StoredOrder> objs = Arrays.asList(storedOrder);
        verify(mockGenericStore).storeObjs(objs);
    }

    @Test
    public void testStoreObjsWithPublicStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(new MyClass.MyPublicStaticList<>());
    }

    @Test
    public void testStoreObjsWithPublicInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicInstanceList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(Arrays.asList());
    }

    @Test
    public void testStoreObjsWithPackageStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPackageStaticList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(new MyClass.MyPackageStaticList<>());
    }

    @Test
    public void testStoreObjsWithPackageInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPackageInstanceList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(Arrays.asList());
    }

    @Test
    public void testStoreObjsWithPrivateStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPrivateStaticList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(Arrays.asList());
    }

    @Test
    public void testStoreObjsWithPrivateInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPrivateInstanceList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(Arrays.asList());
    }

    @Test
    public void testStoreObjsWithAnonList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithAnonList("serializedObjs");

        // Verify the results
        verify(mockGenericStore).storeObjs(Arrays.asList());
    }

    @Test
    public void testStoreObjsWithBuilderConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithBuilderConsumer("key");

        // Verify the results
        verify(mockGenericStore).putObj(any(Consumer.class));
    }

    @Test
    public void testStoreObjsWithMethodRefConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithMethodRefConsumer("key");

        // Verify the results
        verify(mockGenericStore).putObj(any(Consumer.class));
    }

    @Test
    public void testStoreObjsWithPublicStaticBuilderConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticBuilderConsumer("key");

        // Verify the results
        verify(mockGenericStore).putObj(any(MyClass.MyPublicStaticConsumer.class));
    }

    @Test
    public void testStoreObjsWithPublicStaticBuilderConsumerWithEquals() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticBuilderConsumerWithEquals("key");

        // Verify the results
        verify(mockGenericStore).putObj(new MyClass.MyPublicStaticConsumerWithEquals());
    }

    @Test
    public void testStoreObjWithPrivateInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPrivateInstanceSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithPrivateStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPrivateStaticSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithPublicInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPublicInstanceSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithPublicStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPublicStaticSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(MyClass.MyPublicStaticSupplier.class));
    }

    @Test
    public void testStoreObjWithPackageInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPackageInstanceSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithPackageStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPackageStaticSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(MyClass.MyPackageStaticSupplier.class));
    }

    @Test
    public void testStoreObjWithAnonSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithAnonSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithLambdaSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithLambdaSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }

    @Test
    public void testStoreObjWithMethodRefSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithMethodRefSupplier("key");

        // Verify the results
        verify(mockGenericStore).putObjWithSupplier(any(Supplier.class));
    }
}
