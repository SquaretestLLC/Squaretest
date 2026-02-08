package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private ConfigBean mockConfigBean
    @Mock
    private FooService mockFooService
    @Mock
    private OtherBean mockOtherBean

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

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
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(mockOtherBean)
    }
}
