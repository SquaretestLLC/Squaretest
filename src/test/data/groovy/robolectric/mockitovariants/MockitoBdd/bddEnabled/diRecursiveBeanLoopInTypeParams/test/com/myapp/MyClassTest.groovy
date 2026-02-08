package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testLoadBean1() {
        // Setup
        // Configure FooCreator.loadBean1(...).
        def bean1 = new Bean1()
        bean1.setName("name")
        def bean2 = new Bean2()
        bean2.setBean2Name("bean2Name")
        bean2.setBean1List([new Bean1()])
        bean1.setBean2List([bean2])
        given(mockFooCreator.loadBean1()).willReturn(bean1)

        // Run the test
        def result = myClassUnderTest.loadBean1()

        // Verify the results
    }

    @Test
    void testLoadAllBean1s() {
        // Setup
        // Configure FooCreator.loadAllBean1s(...).
        def bean1 = new Bean1()
        bean1.setName("name")
        def bean2 = new Bean2()
        bean2.setBean2Name("bean2Name")
        bean2.setBean1List([new Bean1()])
        bean1.setBean2List([bean2])
        def bean1s = [bean1]
        given(mockFooCreator.loadAllBean1s()).willReturn(bean1s)

        // Run the test
        def result = myClassUnderTest.loadAllBean1s()

        // Verify the results
    }

    @Test
    void testLoadAllBean1s_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.loadAllBean1s()).willReturn([])

        // Run the test
        def result = myClassUnderTest.loadAllBean1s()

        // Verify the results
        assert [] == result
    }
}
