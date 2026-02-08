package com.myapp

import com.google.common.base.Optional
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanOptional = Optional.of(simpleBean)
        when(mockFoo.tryMakeSimpleBean("name")).thenReturn(simpleBeanOptional)

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        when(mockFoo.tryMakeSimpleBean("name")).thenReturn(Optional.absent())

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
        assert Optional.absent() == result
    }
}
