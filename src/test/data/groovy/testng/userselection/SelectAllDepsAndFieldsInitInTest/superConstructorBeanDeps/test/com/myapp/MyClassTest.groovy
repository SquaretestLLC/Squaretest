package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean
    @Mock
    private OtherBean mockOtherBean
    @Mock
    private FooService mockFooService

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

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
        assert "otherData" == myClassUnderTest.getOtherData()
    }

    @Test
    void testSetOtherData() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }
}
