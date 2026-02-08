package com.myapp

import com.myapp.multi.CloseableIterable
import com.myapp.multi.CloseableIterableWithStaticCreatorMethod
import com.myapp.multi.FooCreator
import com.myapp.multi.SimpleBean
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.time.LocalDateTime

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testMakeTheStrings() {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        when(mockFooCreator.makeTheStrings("key")).thenReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheStrings("key")

        // Verify the results
        verify(spyCloseableIterable).close()
    }

    @Test
    void testMakeTheStrings_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        when(mockFooCreator.makeTheStrings("key")).thenReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheStrings("key")

        // Verify the results
        verify(spyCloseableIterable).close()
    }

    @Test
    void testMakeTheStrings1() {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.of("value"))
        when(mockFooCreator.makeTheStrings1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheStrings1("key")

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close()
    }

    @Test
    void testMakeTheStrings1_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.empty())
        when(mockFooCreator.makeTheStrings1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheStrings1("key")

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close()
    }

    @Test
    void testMakeTheBeans() {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        when(mockFooCreator.makeTheBeans("key")).thenReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheBeans("key")

        // Verify the results
        verify(spyCloseableIterable).close()
    }

    @Test
    void testMakeTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        when(mockFooCreator.makeTheBeans("key")).thenReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheBeans("key")

        // Verify the results
        verify(spyCloseableIterable).close()
    }

    @Test
    void testMakeTheBeans1() {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        def simpleBean = new SimpleBean()
        simpleBean.setId(0L)
        simpleBean.setName("name")
        simpleBean.setDescription("description")
        simpleBean.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.of(simpleBean))
        when(mockFooCreator.makeTheBeans1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheBeans1("key")

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close()
    }

    @Test
    void testMakeTheBeans1_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.empty())
        when(mockFooCreator.makeTheBeans1("key")).thenReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheBeans1("key")

        // Verify the results
        verify(spyCloseableIterableWithStaticCreatorMethod).close()
    }
}
