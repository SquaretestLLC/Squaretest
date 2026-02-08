package com.myapp

import com.google.common.base.Optional
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeMethod
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
