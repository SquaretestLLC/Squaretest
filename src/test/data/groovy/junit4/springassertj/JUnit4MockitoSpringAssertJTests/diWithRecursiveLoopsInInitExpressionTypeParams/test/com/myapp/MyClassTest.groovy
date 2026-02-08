package com.myapp

import com.myapp.foos.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.LocalDateTime
import java.time.ZoneOffset

import static org.mockito.Mockito.when

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
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        simpleBean.setMyOtherId(0L)
        simpleBean.setMyLastName("myLastName")
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        simpleBean1.setMyOtherId(0L)
        simpleBean1.setMyLastName("myLastName")
        simpleBean1.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        def simpleBean2 = new SimpleBean()
        simpleBean2.setMyId(0L)
        simpleBean2.setMyName("myName")
        simpleBean2.setMyOtherId(0L)
        simpleBean2.setMyLastName("myLastName")
        simpleBean2.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        def simpleBean3 = new SimpleBean()
        simpleBean3.setMyId(0L)
        simpleBean3.setMyName("myName")
        simpleBean3.setMyOtherId(0L)
        simpleBean3.setMyLastName("myLastName")
        simpleBean3.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        def foo1 = new Foo1([], [], new Bar([]), new HashSet<>([new Bar([])]), new OtherBar(null), [new OtherBar(null)],
                simpleBean, simpleBean1, simpleBean2, simpleBean3)
        when(mockFooMaker.makeFoo1()).thenReturn(foo1)

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
    }
}
