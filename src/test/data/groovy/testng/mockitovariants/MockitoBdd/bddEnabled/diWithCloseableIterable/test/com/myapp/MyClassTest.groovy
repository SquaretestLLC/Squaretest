package com.myapp

import com.myapp.multi.CloseableIterable
import com.myapp.multi.CloseableIterableWithStaticCreatorMethod
import com.myapp.multi.FooCreator
import com.myapp.multi.SimpleBean
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.time.LocalDateTime

import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
import static org.mockito.Mockito.spy
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooCreator mockFooCreator

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooCreator)
    }

    @Test
    void testMakeTheStrings() {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        given(mockFooCreator.makeTheStrings("key")).willReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheStrings("key")

        // Verify the results
        then(spyCloseableIterable).should().close()
    }

    @Test
    void testMakeTheStrings_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheStrings(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        given(mockFooCreator.makeTheStrings("key")).willReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheStrings("key")

        // Verify the results
        then(spyCloseableIterable).should().close()
    }

    @Test
    void testMakeTheStrings1() {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.of("value"))
        given(mockFooCreator.makeTheStrings1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheStrings1("key")

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close()
    }

    @Test
    void testMakeTheStrings1_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheStrings1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.empty())
        given(mockFooCreator.makeTheStrings1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheStrings1("key")

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close()
    }

    @Test
    void testMakeTheBeans() {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        given(mockFooCreator.makeTheBeans("key")).willReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheBeans("key")

        // Verify the results
        then(spyCloseableIterable).should().close()
    }

    @Test
    void testMakeTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheBeans(...).
        def spyCloseableIterable = spy(CloseableIterable.empty())
        given(mockFooCreator.makeTheBeans("key")).willReturn(spyCloseableIterable)

        // Run the test
        def result = myClassUnderTest.makeTheBeans("key")

        // Verify the results
        then(spyCloseableIterable).should().close()
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
        given(mockFooCreator.makeTheBeans1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheBeans1("key")

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close()
    }

    @Test
    void testMakeTheBeans1_FooCreatorReturnsNoItems() {
        // Setup
        // Configure FooCreator.makeTheBeans1(...).
        def spyCloseableIterableWithStaticCreatorMethod = spy(CloseableIterableWithStaticCreatorMethod.empty())
        given(mockFooCreator.makeTheBeans1("key")).willReturn(spyCloseableIterableWithStaticCreatorMethod)

        // Run the test
        def result = myClassUnderTest.makeTheBeans1("key")

        // Verify the results
        then(spyCloseableIterableWithStaticCreatorMethod).should().close()
    }
}
