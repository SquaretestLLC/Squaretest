package com.myapp;

import com.myapp.retro.Foo;
import com.myapp.retro.FooService;
import org.junit.jupiter.api.AfterEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
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
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
    }

    @Test
    void testListFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testListFoos_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

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
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
    }

    @Test
    void testSafeListFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSafeListFoos_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

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
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testGetFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

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
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.safeGetFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

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
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsNoItem() throws Exception {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetOptionalFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

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
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testSafeGetOptionalFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
    }
}
