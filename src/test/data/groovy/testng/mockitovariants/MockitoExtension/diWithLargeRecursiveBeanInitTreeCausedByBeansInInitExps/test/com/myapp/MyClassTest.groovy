package com.myapp

import com.myapp.foos.Foo1
import com.myapp.foos.FooMaker
import com.myapp.foos.SimpleBean
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
    private FooMaker mockFooMaker

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockFooMaker)
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        def foo1 = new Foo1()
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        simpleBean.setMyOtherId(0L)
        simpleBean.setMyLastName("myLastName")
        foo1.setSimpleBean1([simpleBean])
        when(mockFooMaker.makeFoo1()).thenReturn(foo1)

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
    }
}
