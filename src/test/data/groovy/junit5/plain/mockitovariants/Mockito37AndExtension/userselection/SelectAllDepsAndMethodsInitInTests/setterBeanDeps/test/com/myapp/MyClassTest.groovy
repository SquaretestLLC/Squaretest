package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean
    @Mock
    private FooService mockFooService
    @Mock
    private OtherBean mockOtherBean

    @Test
    void testGetFooData1() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setConfigBean(mockConfigBean)
        myClassUnderTest.setFooService(mockFooService)
        myClassUnderTest.setOtherBean(mockOtherBean)
        when(mockConfigBean.getBasePath()).thenReturn("basePath")

        // Configure FooService.getFooData1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData1("basePath", "id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setConfigBean(mockConfigBean)
        myClassUnderTest.setFooService(mockFooService)
        myClassUnderTest.setOtherBean(mockOtherBean)
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
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setConfigBean(mockConfigBean)
        myClassUnderTest.setFooService(mockFooService)
        myClassUnderTest.setOtherBean(mockOtherBean)
        assert mockOtherBean == myClassUnderTest.getOtherBean()
    }
}
