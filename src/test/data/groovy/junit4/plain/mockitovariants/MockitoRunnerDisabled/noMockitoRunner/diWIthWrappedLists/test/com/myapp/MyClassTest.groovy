package com.myapp

import com.myapp.wrappedlists.*
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetShortWrapFoos() {
        // Setup
        // Configure FooService.getShortWrapFoos(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def listWrap = new Wrap<>([foo])
        when(mockFooService.getShortWrapFoos("fooId")).thenReturn(listWrap)

        // Run the test
        def result = myClassUnderTest.getShortWrapFoos("fooId")

        // Verify the results
    }

    @Test
    void testGetShortWrapFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getShortWrapFoos("fooId")).thenReturn(new Wrap<>([]))

        // Run the test
        def result = myClassUnderTest.getShortWrapFoos("fooId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos() {
        // Setup
        // Configure FooService.getFoos(...).
        def listWrapperBean = new WrapperBean<>()
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        listWrapperBean.setPayload([foo])
        listWrapperBean.setStatusCode(0)
        when(mockFooService.getFoos("fooId")).thenReturn(listWrapperBean)

        // Run the test
        def result = myClassUnderTest.getFoos("fooId")

        // Verify the results
    }

    @Test
    void testGetFoosWithMultiArgsConstructor() {
        // Setup
        // Configure FooService.getFoosWithMultiArgsConstructor(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def listWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(0, [foo])
        when(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .thenReturn(listWrapperWithMultipleArgsConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId")

        // Verify the results
    }

    @Test
    void testGetFoosWithMultiArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithMultiArgsConstructor(...).
        def listWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(0, [])
        when(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .thenReturn(listWrapperWithMultipleArgsConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoosWithSingleArgConstructor() {
        // Setup
        // Configure FooService.getFoosWithSingleArgConstructor(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def listWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>([foo])
        when(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .thenReturn(listWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId")

        // Verify the results
    }

    @Test
    void testGetFoosWithSingleArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithSingleArgConstructor(...).
        def listWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>([])
        when(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .thenReturn(listWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        def fooWrapperBean = new WrapperBean<>()
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        fooWrapperBean.setPayload(foo)
        fooWrapperBean.setStatusCode(0)
        when(mockFooService.getFoo("fooId")).thenReturn(fooWrapperBean)

        // Run the test
        def result = myClassUnderTest.getFoo("fooId")

        // Verify the results
    }

    @Test
    void testGetFooWithMultiArgsConstructor() {
        // Setup
        // Configure FooService.getFooWithMultiArgsConstructor(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(0, foo)
        when(mockFooService.getFooWithMultiArgsConstructor("fooId")).thenReturn(fooWrapperWithMultipleArgsConstructor)

        // Run the test
        def result = myClassUnderTest.getFooWithMultiArgsConstructor("fooId")

        // Verify the results
    }

    @Test
    void testGetFooWithSingleArgConstructor() {
        // Setup
        // Configure FooService.getFooWithSingleArgConstructor(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>(foo)
        when(mockFooService.getFooWithSingleArgConstructor("fooId"))
                .thenReturn(fooWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFooWithSingleArgConstructor("fooId")

        // Verify the results
    }
}
