package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean
    @Mock
    private OtherBean mockOtherBean
    @Mock
    private FooService mockFooService

    @Before
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetFooData1() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
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
        assert mockOtherBean == myClassUnderTest.getOtherBean()
    }
}
