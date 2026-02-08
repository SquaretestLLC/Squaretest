package com.myapp


import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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
    void testGetFooData11() {
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
    void testGetFooData31() {
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
    void testGetOtherData1() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assert "otherData" == myClassUnderTest.getOtherData()
    }

    @Test
    void testSetOtherData1() {
        // Setup
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData21() {
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
    void testGetOtherBean1() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assert mockOtherBean == myClassUnderTest.getOtherBean()
    }

    @Test
    void testSomeDataGetterAndSetter1() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        def someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assert someData == myClassUnderTest.getSomeData()
    }

    @Test
    void testGetBaseId1() {
        def myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assert "BaseId" == myClassUnderTest.getBaseId()
    }
}