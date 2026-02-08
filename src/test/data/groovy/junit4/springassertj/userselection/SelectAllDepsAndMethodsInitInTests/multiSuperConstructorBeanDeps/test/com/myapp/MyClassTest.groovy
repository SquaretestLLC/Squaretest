package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean
    @Mock
    private OtherBean mockOtherBean
    @Mock
    private FooService mockFooService

    @Test
    void testGetFooData1() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Configure FooService.getFooData1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData1("newPath", "id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData3() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        when(mockConfigBean.getThirdPath()).thenReturn("thirdPath")

        // Configure FooService.getFooData3(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData3("thirdPath", "id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertThat(myClassUnderTest.getOtherData()).isEqualTo("otherData")
    }

    @Test
    void testSetOtherData() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        when(mockConfigBean.getSpecialPath()).thenReturn("specialPath")

        // Configure FooService.getFooData2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData2("specialPath", "id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(mockOtherBean)
    }

    @Test
    void testSomeDataGetterAndSetter() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assertThat(myClassUnderTest.getSomeData()).isEqualTo(someData)
    }

    @Test
    void testGetBaseId() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertThat(myClassUnderTest.getBaseId()).isEqualTo("BaseId")
    }
}
