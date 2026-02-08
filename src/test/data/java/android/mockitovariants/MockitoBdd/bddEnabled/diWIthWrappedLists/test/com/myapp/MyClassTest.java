package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.wrappedlists.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
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
    public void testGetShortWrapFoos() {
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
    public void testGetShortWrapFoos_FooServiceReturnsNoItems() {
        // Setup
        given(mockFooService.getShortWrapFoos("fooId")).willReturn(new Wrap<>(Collections.emptyList()));

        // Run the test
        final List<Foo> result = myClassUnderTest.getShortWrapFoos("fooId");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetFoos() {
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
    public void testGetFoosWithMultiArgsConstructor() {
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
    public void testGetFoosWithMultiArgsConstructor_FooServiceReturnsNoItems() {
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
    public void testGetFoosWithSingleArgConstructor() {
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
    public void testGetFoosWithSingleArgConstructor_FooServiceReturnsNoItems() {
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
    public void testGetFoo() {
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
    public void testGetFooWithMultiArgsConstructor() {
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
    public void testGetFooWithSingleArgConstructor() {
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
