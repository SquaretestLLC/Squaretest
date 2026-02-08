package com.myapp;

import com.myapp.wrappedlists.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetShortWrapFoos() {
        // Setup
        // Configure FooService.getShortWrapFoos(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Wrap<List<Foo>> listWrap = new Wrap<>(Arrays.asList(foo));
        given(mockFooService.getShortWrapFoos("fooId")).willReturn(listWrap);

        // Run the test
        final List<Foo> result = myClassUnderTest.getShortWrapFoos("fooId");

        // Verify the results
    }

    @Test
    void testGetShortWrapFoos_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.getShortWrapFoos("fooId")).willReturn(new Wrap<>(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.getShortWrapFoos("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos() {
        // Setup
        // Configure FooService.getFoos(...).
        final WrapperBean<List<Foo>> listWrapperBean = new WrapperBean<>();
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        listWrapperBean.setPayload(Arrays.asList(foo));
        listWrapperBean.setStatusCode(0);
        given(mockFooService.getFoos("fooId")).willReturn(listWrapperBean);

        // Run the test
        final List<Foo> result = myClassUnderTest.getFoos("fooId");

        // Verify the results
    }

    @Test
    void testGetFoosWithMultiArgsConstructor() {
        // Setup
        // Configure FooService.getFoosWithMultiArgsConstructor(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final WrapperWithMultipleArgsConstructor<List<Foo>> listWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(
                0, Arrays.asList(foo));
        given(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .willReturn(listWrapperWithMultipleArgsConstructor);

        // Run the test
        final List<Foo> result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId");

        // Verify the results
    }

    @Test
    void testGetFoosWithMultiArgsConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithMultiArgsConstructor(...).
        final WrapperWithMultipleArgsConstructor<List<Foo>> listWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(
                0, Collections.emptyList());
        given(mockFooService.getFoosWithMultiArgsConstructor("fooId"))
                .willReturn(listWrapperWithMultipleArgsConstructor);

        // Run the test
        final List<Foo> result = myClassUnderTest.getFoosWithMultiArgsConstructor("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoosWithSingleArgConstructor() {
        // Setup
        // Configure FooService.getFoosWithSingleArgConstructor(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final WrapperWithSingleTypeParamConstructor<List<Foo>> listWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>(
                Arrays.asList(foo));
        given(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .willReturn(listWrapperWithSingleTypeParamConstructor);

        // Run the test
        final List<Foo> result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId");

        // Verify the results
    }

    @Test
    void testGetFoosWithSingleArgConstructor_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosWithSingleArgConstructor(...).
        final WrapperWithSingleTypeParamConstructor<List<Foo>> listWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>(
                Collections.emptyList());
        given(mockFooService.getFoosWithSingleArgConstructor("fooId"))
                .willReturn(listWrapperWithSingleTypeParamConstructor);

        // Run the test
        final List<Foo> result = myClassUnderTest.getFoosWithSingleArgConstructor("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        final WrapperBean<Foo> fooWrapperBean = new WrapperBean<>();
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        fooWrapperBean.setPayload(foo);
        fooWrapperBean.setStatusCode(0);
        given(mockFooService.getFoo("fooId")).willReturn(fooWrapperBean);

        // Run the test
        final Foo result = myClassUnderTest.getFoo("fooId");

        // Verify the results
    }

    @Test
    void testGetFooWithMultiArgsConstructor() {
        // Setup
        // Configure FooService.getFooWithMultiArgsConstructor(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final WrapperWithMultipleArgsConstructor<Foo> fooWrapperWithMultipleArgsConstructor = new WrapperWithMultipleArgsConstructor<>(
                0, foo);
        given(mockFooService.getFooWithMultiArgsConstructor("fooId")).willReturn(fooWrapperWithMultipleArgsConstructor);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithMultiArgsConstructor("fooId");

        // Verify the results
    }

    @Test
    void testGetFooWithSingleArgConstructor() {
        // Setup
        // Configure FooService.getFooWithSingleArgConstructor(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final WrapperWithSingleTypeParamConstructor<Foo> fooWrapperWithSingleTypeParamConstructor = new WrapperWithSingleTypeParamConstructor<>(
                foo);
        given(mockFooService.getFooWithSingleArgConstructor("fooId"))
                .willReturn(fooWrapperWithSingleTypeParamConstructor);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithSingleArgConstructor("fooId");

        // Verify the results
    }
}
