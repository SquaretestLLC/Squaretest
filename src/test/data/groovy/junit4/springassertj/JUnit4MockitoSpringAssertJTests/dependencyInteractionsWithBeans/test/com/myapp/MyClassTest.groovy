package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private BeanFixer mockBeanConverter

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockBeanConverter)
    }

    @Test
    void testConvertBean() {
        // Setup
        def theBean = new Bean()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        theBean.setFooData(fooData)
        theBean.setTheString("theString")

        // Configure BeanFixer.convertBean(...).
        def otherBean = new OtherBean()
        def data = new FooData()
        data.setPurchaseId("purchaseId")
        data.setNameOnTheLicense("nameOnTheLicense")
        data.setOtherData(new OtherData("dataName"))
        otherBean.setOurData(data)
        otherBean.setOurStr("ourStr")
        when(mockBeanConverter.convertBean(any(Bean.class))).thenReturn(otherBean)

        // Run the test
        def result = myClassUnderTest.convertBean(theBean)

        // Verify the results
    }

    @Test
    void testStoreBeanWithSameFieldName() {
        // Setup
        def beanWithSameMethodFieldNames = new BeanWithSameMethodFieldNames()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        beanWithSameMethodFieldNames.fooData(fooData)
        beanWithSameMethodFieldNames.theString("theString")

        // Run the test
        myClassUnderTest.storeBeanWithSameFieldName(beanWithSameMethodFieldNames)

        // Verify the results
    }

    @Test
    void testStoreBeanWithObjectMethods() {
        // Setup
        def beanWithObjectMethods = new BeanWithObjectMethods()
        def data = new FooData()
        data.setPurchaseId("purchaseId")
        data.setNameOnTheLicense("nameOnTheLicense")
        data.setOtherData(new OtherData("dataName"))
        beanWithObjectMethods.setOurData(data)
        beanWithObjectMethods.setOurStr("ourStr")

        // Run the test
        myClassUnderTest.storeBeanWithObjectMethods(beanWithObjectMethods)

        // Verify the results
    }

    @Test
    void testStoreBean3() {
        // Setup
        def bean = new BeanWithDefaultConstructorInCode()
        bean.setTheStr("theStr")

        // Run the test
        myClassUnderTest.storeBean3(bean)

        // Verify the results
    }

    @Test
    void testStoreBean4() {
        // Setup
        def bean = new BeanWithOneField()
        bean.setTheStr("theStr")

        // Run the test
        myClassUnderTest.storeBean4(bean)

        // Verify the results
    }

    @Test
    void testStoreBean5() {
        // Setup
        def innerBean = new OuterBean.InnerBean()
        innerBean.setTheString("theString")

        // Run the test
        myClassUnderTest.storeBean5(innerBean)

        // Verify the results
    }

    @Test
    void testStoreNotBean1() {
        // Setup
        def bean = new NotBeanExtraConstructor("theStr")

        // Run the test
        myClassUnderTest.storeNotBean1(bean)

        // Verify the results
    }

    @Test
    void testStoreNotBean2() {
        // Setup
        def bean = new NotBeanGetterOnly()

        // Run the test
        myClassUnderTest.storeNotBean2(bean)

        // Verify the results
    }

    @Test
    void testStoreNotBean3() {
        // Setup
        def bean = new NotBeanWithExtraMethod()
        def fooData = new FooData()
        fooData.setPurchaseId("purchaseId")
        fooData.setNameOnTheLicense("nameOnTheLicense")
        fooData.setOtherData(new OtherData("dataName"))
        bean.setFooData(fooData)
        bean.setTheString("theString")

        // Run the test
        myClassUnderTest.storeNotBean3(bean)

        // Verify the results
    }

    @Test
    void testStoreNotBean4() {
        // Setup
        def bean = new NotBeanWithNoFields()

        // Run the test
        myClassUnderTest.storeNotBean4(bean)

        // Verify the results
    }
}
