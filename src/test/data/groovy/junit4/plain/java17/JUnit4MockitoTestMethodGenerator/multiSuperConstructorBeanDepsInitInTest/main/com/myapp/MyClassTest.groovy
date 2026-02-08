package com.myapp

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

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
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData1("newPath", "id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id")

        // Verify the results
    }

    @Test
    void testGetFooData3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        when(mockConfigBean.getThirdPath()).thenReturn("thirdPath")

        // Configure FooService.getFooData3(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData3("thirdPath", "id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id")

        // Verify the results
    }

    @Test
    void testGetOtherData() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertEquals("otherData", myClassUnderTest.getOtherData())
    }

    @Test
    void testSetOtherData() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)

        // Run the test
        myClassUnderTest.setOtherData("otherData")

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        when(mockConfigBean.getSpecialPath()).thenReturn("specialPath")

        // Configure FooService.getFooData2(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData2("specialPath", "id")).thenReturn(fooData)

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }

    @Test
    void testGetOtherBean() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertEquals(mockOtherBean, myClassUnderTest.getOtherBean())
    }

    @Test
    void testSomeDataGetterAndSetter() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        final String someData = "someData"
        myClassUnderTest.setSomeData(someData)
        assertEquals(someData, myClassUnderTest.getSomeData())
    }

    @Test
    void testGetBaseId() {
        final MyClass myClassUnderTest = new MyClass(mockConfigBean, mockOtherBean, mockFooService)
        assertEquals("BaseId", myClassUnderTest.getBaseId())
    }
}
