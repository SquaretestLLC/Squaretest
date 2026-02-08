package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass()
        def configBean = new ConfigBean()
        configBean.setBasePath("basePath")
        configBean.setSpecialPath("specialPath")
        myClassUnderTest.setConfigBean(configBean)
        myClassUnderTest.setFooService(mockFooService)
    }

    @Test
    void testOtherBeanGetterAndSetter() {
        def otherBean = new OtherBean()
        myClassUnderTest.setOtherBean(otherBean)
        assertThat(myClassUnderTest.getOtherBean()).isEqualTo(otherBean)
    }

    @Test
    void testGetFooData1() {
        // Setup
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
        // Configure FooService.getFooData2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFooData2("specialPath", "id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
    }
}
