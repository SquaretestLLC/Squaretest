package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.retro.Foo;
import com.myapp.retro.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testListFoos() throws Exception {
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
    public void testListFoos_FooServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testListFoos_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.listFoos("prefix");

        // Verify the results
    }

    @Test
    public void testSafeListFoos() {
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
    public void testSafeListFoos_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.listFoos("prefix")).thenReturn(Calls.response(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testSafeListFoos_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.listFoos(...).
        final Call<List<Foo>> listCall = Calls.failure(new IOException("message"));
        when(mockFooService.listFoos("prefix")).thenReturn(listCall);

        // Run the test
        final List<Foo> result = myClassUnderTest.safeListFoos("prefix");

        // Verify the results
    }

    @Test
    public void testGetFooWithId() throws Exception {
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
    public void testGetFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.getFooWithId("fooId");

        // Verify the results
    }

    @Test
    public void testSafeGetFooWithId() {
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
    public void testSafeGetFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getFooWithId(...).
        final Call<Foo> fooCall = Calls.failure(new IOException("message"));
        when(mockFooService.getFooWithId("fooId")).thenReturn(fooCall);

        // Run the test
        final Foo result = myClassUnderTest.safeGetFooWithId("fooId");

        // Verify the results
    }

    @Test
    public void testGetOptionalFooWithId() throws Exception {
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
    public void testGetOptionalFooWithId_FooServiceReturnsNoItem() throws Exception {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetOptionalFooWithId_FooServiceReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.getOptionalFooWithId("fooId");

        // Verify the results
    }

    @Test
    public void testSafeGetOptionalFooWithId() {
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
    public void testSafeGetOptionalFooWithId_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(Calls.response(Optional.empty()));

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testSafeGetOptionalFooWithId_FooServiceReturnsFailure() {
        // Setup
        // Configure FooService.getOptionalFooWithId(...).
        final Call<Optional<Foo>> optionalCall = Calls.failure(new IOException("message"));
        when(mockFooService.getOptionalFooWithId("fooId")).thenReturn(optionalCall);

        // Run the test
        final Optional<Foo> result = myClassUnderTest.safeGetOptionalFooWithId("fooId");

        // Verify the results
    }
}
