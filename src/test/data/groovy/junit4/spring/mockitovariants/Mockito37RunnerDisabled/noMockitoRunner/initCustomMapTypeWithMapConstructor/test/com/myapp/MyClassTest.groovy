package com.myapp

import com.myapp.foos.Foo1
import com.myapp.foos.FooMaker
import com.myapp.foos.MyMap
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import java.time.LocalDateTime
import java.time.ZoneOffset

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooMaker mockFooMaker

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooMaker)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
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
        assert [] == result
    }
}
