package com.myapp

import com.myapp.foos.Foo1
import com.myapp.foos.FooMaker
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooMaker mockFooMaker

    private MyClass myClassUnderTest

    @BeforeEach
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
