package com.myapp

import com.myapp.wrappedlists.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
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
        given(mockFooService.getShortWrapFoos("fooId")).willReturn(listWrap)

        // Run the test
        def result = myClassUnderTest.getShortWrapFoos("fooId")

        // Verify the results
    }

    @Test
    void testGetShortWrapFoos_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.getShortWrapFoos("fooId")).willReturn(new Wrap<>([]))

        // Run the test
        def result = myClassUnderTest.getShortWrapFoos("fooId")

        // Verify the results
        assertThat(result).isEqualTo([])
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
        given(mockFooService.getFoos("fooId")).willReturn(listWrapperBean)

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
        given(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .willReturn(listWrapperWithMultipleArgsConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId")

        // Verify the results
    }

    @Test
    void testGetFoosWithMultiArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithMultiArgsConstructor(...).
        def listWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(0, [])
        given(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .willReturn(listWrapperWithMultipleArgsConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId")

        // Verify the results
        assertThat(result).isEqualTo([])
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
        given(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .willReturn(listWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId")

        // Verify the results
    }

    @Test
    void testGetFoosWithSingleArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithSingleArgConstructor(...).
        def listWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>([])
        given(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .willReturn(listWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId")

        // Verify the results
        assertThat(result).isEqualTo([])
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
        given(mockFooService.getFoo("fooId")).willReturn(fooWrapperBean)

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
        given(mockFooService.getFooWithMultiArgsConstructor("fooId")).willReturn(fooWrapperWithMultipleArgsConstructor)

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
        given(mockFooService.getFooWithSingleArgConstructor("fooId"))
                .willReturn(fooWrapperWithSingleTypeParamConstructor)

        // Run the test
        def result = myClassUnderTest.getFooWithSingleArgConstructor("fooId")

        // Verify the results
    }
}
