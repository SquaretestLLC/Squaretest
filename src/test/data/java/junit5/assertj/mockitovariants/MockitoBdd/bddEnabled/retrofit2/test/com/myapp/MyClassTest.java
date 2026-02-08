package com.myapp;

import com.myapp.retro.Foo;
import com.myapp.retro.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testListFoos() throws Exception {
        // Setup
        // Configure FooService.listFoos(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<List<Foo>> listCall = Calls.response(Arrays.asList(foo));
        given(mockFooService.listFoos("prefix")).willReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
    }

    @Test
    void testListFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        given(mockFooService.listFoos("prefix")).willReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testListFoos_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        given(mockFooService.listFoos("prefix")).willReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
    }

    @Test
    void testSafeListFoos() {
        // Setup
        // Configure FooService.listFoos(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<List<Foo>> listCall = Calls.response(Arrays.asList(foo));
        given(mockFooService.listFoos("prefix")).willReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
    }

    @Test
    void testSafeListFoos_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.listFoos("prefix")).willReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSafeListFoos_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        given(mockFooService.listFoos("prefix")).willReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
    }

    @Test
    void testGetFooWithId() throws Exception {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<Foo> fooCall = Calls.response(foo);
        given(mockFooService.getFooWithId("fooId")).willReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testGetFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        given(mockFooService.getFooWithId("fooId")).willReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetFooWithId() {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<Foo> fooCall = Calls.response(foo);
        given(mockFooService.getFooWithId("fooId")).willReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.safeGetFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        given(mockFooService.getFooWithId("fooId")).willReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.safeGetFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testGetOptionalFooWithId() throws Exception {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<Optional<Foo>> optionalCall = Calls.response(Optional.of(foo));
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsNoItem() throws Exception {
        // Setup
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetOptionalFooWithId() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Call<Optional<Foo>> optionalCall = Calls.response(Optional.of(foo));
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsNoItem() {
        // Setup
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        given(mockFooService.getOptionalFooWithId("fooId")).willReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
    }
}
