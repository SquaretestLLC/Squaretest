package com.myapp

import com.myapp.foos.Foo1
import com.myapp.foos.FooMaker
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooMaker mockFooMaker

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooMaker)
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        def foo1 = new Foo1()
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        simpleBean1.setMyOtherId(0L)
        simpleBean1.setMyLastName("myLastName")
        foo1.setSimpleBean1(simpleBean1)
        given(mockFooMaker.makeFoo1()).willReturn(foo1)

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
    }
}
