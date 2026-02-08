package com.myapp;

import com.beans.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private BeanFixer mockBeanConverter;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockBeanConverter);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testConvertBean() {
        // Setup
        final Bean theBean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        theBean.setFooData(fooData);
        theBean.setTheString("theString");

        // Configure BeanFixer.convertBean(...).
        final OtherBean otherBean = new OtherBean();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        otherBean.setOurData(data);
        otherBean.setOurStr("ourStr");
        when(mockBeanConverter.convertBean(any(Bean.class))).thenReturn(otherBean);

        // Run the test
        final OtherBean result = myClassUnderTest.convertBean(theBean);

        // Verify the results
    }

    @Test
    void testStoreBeanWithSameFieldName() {
        // Setup
        final BeanWithSameMethodFieldNames beanWithSameMethodFieldNames = new BeanWithSameMethodFieldNames();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        beanWithSameMethodFieldNames.fooData(fooData);
        beanWithSameMethodFieldNames.theString("theString");

        // Run the test
        myClassUnderTest.storeBeanWithSameFieldName(beanWithSameMethodFieldNames);

        // Verify the results
    }

    @Test
    void testStoreBeanWithObjectMethods() {
        // Setup
        final BeanWithObjectMethods beanWithObjectMethods = new BeanWithObjectMethods();
        final FooData data = new FooData();
        data.setPurchaseId("purchaseId");
        data.setNameOnTheLicense("nameOnTheLicense");
        data.setOtherData(new OtherData("dataName"));
        beanWithObjectMethods.setOurData(data);
        beanWithObjectMethods.setOurStr("ourStr");

        // Run the test
        myClassUnderTest.storeBeanWithObjectMethods(beanWithObjectMethods);

        // Verify the results
    }

    @Test
    void testStoreBeanWithAltBooleans() {
        // Setup
        final BeanWithAltBooleans beanWithAltBooleans = new BeanWithAltBooleans();
        beanWithAltBooleans.setAlpha(false);
        beanWithAltBooleans.setBravo(false);
        beanWithAltBooleans.charlie(false);
        beanWithAltBooleans.delta(false);
        beanWithAltBooleans.setEcho(false);

        // Run the test
        myClassUnderTest.storeBeanWithAltBooleans(beanWithAltBooleans);

        // Verify the results
    }

    @Test
    void testStoreBean3() {
        // Setup
        final BeanWithDefaultConstructorInCode bean = new BeanWithDefaultConstructorInCode();
        bean.setTheStr("theStr");

        // Run the test
        myClassUnderTest.storeBean3(bean);

        // Verify the results
    }

    @Test
    void testStoreBean4() {
        // Setup
        final BeanWithOneField bean = new BeanWithOneField();
        bean.setTheStr("theStr");

        // Run the test
        myClassUnderTest.storeBean4(bean);

        // Verify the results
    }

    @Test
    void testStoreBean5() {
        // Setup
        final OuterBean.InnerBean innerBean = new OuterBean.InnerBean();
        innerBean.setTheString("theString");

        // Run the test
        myClassUnderTest.storeBean5(innerBean);

        // Verify the results
    }

    @Test
    void testStoreNotBean1() {
        // Setup
        final NotBeanExtraConstructor bean = new NotBeanExtraConstructor("theStr");

        // Run the test
        myClassUnderTest.storeNotBean1(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean2() {
        // Setup
        final NotBeanGetterOnly bean = new NotBeanGetterOnly();

        // Run the test
        myClassUnderTest.storeNotBean2(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean3() {
        // Setup
        final NotBeanWithExtraMethod bean = new NotBeanWithExtraMethod();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");

        // Run the test
        myClassUnderTest.storeNotBean3(bean);

        // Verify the results
    }

    @Test
    void testStoreNotBean4() {
        // Setup
        final NotBeanWithNoFields bean = new NotBeanWithNoFields();

        // Run the test
        myClassUnderTest.storeNotBean4(bean);

        // Verify the results
    }
}
