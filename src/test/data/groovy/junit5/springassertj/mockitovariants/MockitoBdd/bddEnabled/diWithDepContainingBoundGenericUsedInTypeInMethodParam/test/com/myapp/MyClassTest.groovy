package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.function.Consumer
import java.util.function.Supplier

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.then
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private GenericStore<StoredOrder> mockGenericStore

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockGenericStore)
    }

    @Test
    void testStoreObjs() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjs("serializedObjs")

        // Verify the results
        // Confirm GenericStore.storeObjs(...).
        def storedOrder = new StoredOrder()
        storedOrder.setPurchaseId("purchaseId")
        storedOrder.setNameOnTheLicense("nameOnTheLicense")
        storedOrder.setLicenseType(LicenseType.SQT1_IND)
        storedOrder.setNumberOfUsers(0)
        storedOrder.setDeliveryEmailAddress("deliveryEmailAddress")
        def objs = [storedOrder]
        then(mockGenericStore).should().storeObjs(objs)
    }

    @Test
    void testStoreObjsWithPublicStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs(new MyClass.MyPublicStaticList<>())
    }

    @Test
    void testStoreObjsWithPublicInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicInstanceList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs([])
    }

    @Test
    void testStoreObjsWithPackageStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPackageStaticList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs(new MyClass.MyPackageStaticList<>())
    }

    @Test
    void testStoreObjsWithPackageInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPackageInstanceList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs([])
    }

    @Test
    void testStoreObjsWithPrivateStaticList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPrivateStaticList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs([])
    }

    @Test
    void testStoreObjsWithPrivateInstanceList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPrivateInstanceList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs([])
    }

    @Test
    void testStoreObjsWithAnonList() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithAnonList("serializedObjs")

        // Verify the results
        then(mockGenericStore).should().storeObjs([])
    }

    @Test
    void testStoreObjsWithBuilderConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithBuilderConsumer("key")

        // Verify the results
        then(mockGenericStore).should().putObj(any(Consumer.class))
    }

    @Test
    void testStoreObjsWithMethodRefConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithMethodRefConsumer("key")

        // Verify the results
        then(mockGenericStore).should().putObj(any(Consumer.class))
    }

    @Test
    void testStoreObjsWithPublicStaticBuilderConsumer() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticBuilderConsumer("key")

        // Verify the results
        then(mockGenericStore).should().putObj(any(MyClass.MyPublicStaticConsumer.class))
    }

    @Test
    void testStoreObjsWithPublicStaticBuilderConsumerWithEquals() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjsWithPublicStaticBuilderConsumerWithEquals("key")

        // Verify the results
        then(mockGenericStore).should().putObj(new MyClass.MyPublicStaticConsumerWithEquals())
    }

    @Test
    void testStoreObjWithPrivateInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPrivateInstanceSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithPrivateStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPrivateStaticSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithPublicInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPublicInstanceSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithPublicStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPublicStaticSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(MyClass.MyPublicStaticSupplier.class))
    }

    @Test
    void testStoreObjWithPackageInstanceSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPackageInstanceSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithPackageStaticSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithPackageStaticSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(MyClass.MyPackageStaticSupplier.class))
    }

    @Test
    void testStoreObjWithAnonSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithAnonSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithLambdaSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithLambdaSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }

    @Test
    void testStoreObjWithMethodRefSupplier() {
        // Setup
        // Run the test
        myClassUnderTest.storeObjWithMethodRefSupplier("key")

        // Verify the results
        then(mockGenericStore).should().putObjWithSupplier(any(Supplier.class))
    }
}
