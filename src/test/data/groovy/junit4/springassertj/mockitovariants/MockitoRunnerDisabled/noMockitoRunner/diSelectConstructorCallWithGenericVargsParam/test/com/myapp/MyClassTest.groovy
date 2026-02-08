package com.myapp

import com.myapp.foos.Foo
import com.myapp.foos.FooMaker
import com.myapp.foos.SimpleBean
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import java.time.LocalDateTime
import java.time.ZoneOffset

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooMaker mockFooMaker

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooMaker)
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        def simpleBeans = new SimpleBean()
        simpleBeans.setMyId(0L)
        simpleBeans.setMyName("myName")
        simpleBeans.setMyOtherId(0L)
        simpleBeans.setMyLastName("myLastName")
        simpleBeans.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
        def simpleBeanFoo = new Foo<>(0, simpleBeans)
        when(mockFooMaker.makeFoo1()).thenReturn(simpleBeanFoo)

        // Run the test
        def result = myClassUnderTest.makeFoo1()

        // Verify the results
    }
}
