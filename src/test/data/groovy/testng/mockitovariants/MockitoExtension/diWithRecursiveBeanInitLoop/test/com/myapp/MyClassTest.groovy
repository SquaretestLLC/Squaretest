package com.myapp

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
    void testMakeBean1() {
        // Setup
        // Configure Foo.makeBean1(...).
        def bean1 = new Bean1()
        bean1.setName("name")
        def bean2 = new Bean2()
        bean2.setName("name")
        bean1.setBean2(bean2)
        when(mockFoo.makeBean1()).thenReturn(bean1)

        // Run the test
        def result = myClassUnderTest.makeBean1()

        // Verify the results
    }
}
