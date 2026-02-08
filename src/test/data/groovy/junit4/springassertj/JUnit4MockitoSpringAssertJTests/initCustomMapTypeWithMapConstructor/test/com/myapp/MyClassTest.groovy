package com.myapp

import com.myapp.foos.Foo1
import com.myapp.foos.FooMaker
import com.myapp.foos.MyMap
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.time.LocalDateTime
import java.time.ZoneOffset

import static org.assertj.core.api.Assertions.assertThat
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
        def foo1s = [new Foo1(new MyMap<>(["value": simpleBean]))]
        when(mockFooMaker.makeFoo1()).thenReturn(foo1s)

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
    }

    @Test
    void testMakeFoo1_FooMakerReturnsNoItems() {
        // Setup
        when(mockFooMaker.makeFoo1()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
