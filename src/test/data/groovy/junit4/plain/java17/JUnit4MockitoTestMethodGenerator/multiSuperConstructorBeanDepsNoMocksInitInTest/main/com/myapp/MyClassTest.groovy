package com.myapp

import org.junit.Test

import static org.junit.Assert.assertEquals

class MyClassTest {

    @Test
    void testGetFooData1() {
        // Setup
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData3() {
        // Setup
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        assertEquals("otherData", myClassUnderTest.getOtherData())
    }

    @Test
    void testSetOtherData() {
        // Setup
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        final OtherBean result = myClassUnderTest.getOtherBean()
    }

    @Test
    void testSomeDataGetterAndSetter() {
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        final String someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assertEquals(someData, myClassUnderTest.getSomeData())
    }

    @Test
    void testGetBaseId() {
        final ConfigBean configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        configBean.setThirdPath("thirdPath")
        final MyClass myClassUnderTest = new MyClass(configBean, new OtherBean(), null)
        assertEquals("BaseId", myClassUnderTest.getBaseId())
    }
}
