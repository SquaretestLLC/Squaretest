package com.myapp;

import com.myapp.wrapped.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

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
    void testGetString() {
        // Setup
        given(mockFooService.getString("id")).willReturn(Wrapper.of("value"));

        // Run the test
        final Wrapper<String> result = myClassUnderTest.getString("id");

        // Verify the results
    }

    @Test
    void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Wrapper<Foo> fooWrapper = Wrapper.of(foo);
        given(mockFooService.getFoo("id")).willReturn(fooWrapper);

        // Run the test
        final Wrapper<Foo> result = myClassUnderTest.getFoo("id");

        // Verify the results
    }

    @Test
    void testGetOptionalFoo() {
        // Setup
        // Configure FooService.getOptionalFoo(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Wrapper<Optional<Foo>> optionalWrapper = Wrapper.of(Optional.of(foo));
        given(mockFooService.getOptionalFoo("id")).willReturn(optionalWrapper);

        // Run the test
        final Wrapper<Optional<Foo>> result = myClassUnderTest.getOptionalFoo("id");

        // Verify the results
    }

    @Test
    void testGetOptionalFoo_FooServiceReturnsNoItem() {
        // Setup
        given(mockFooService.getOptionalFoo("id")).willReturn(Wrapper.of(Optional.empty()));

        // Run the test
        final Wrapper<Optional<Foo>> result = myClassUnderTest.getOptionalFoo("id");

        // Verify the results
    }

    @Test
    void testGetWrapperBeanWithListProp() {
        // Setup
        // Configure FooService.getWrapperBeanWithListProp(...).
        final WrapperBeanWithListProp<Foo> fooWrapperBeanWithListProp = new WrapperBeanWithListProp<>();
        fooWrapperBeanWithListProp.setName("name");
        fooWrapperBeanWithListProp.setValues(Arrays.asList());
        given(mockFooService.getWrapperBeanWithListProp("id")).willReturn(fooWrapperBeanWithListProp);

        // Run the test
        final WrapperBeanWithListProp<Foo> result = myClassUnderTest.getWrapperBeanWithListProp("id");

        // Verify the results
    }

    @Test
    void testGetTwoLevelWrapped() {
        // Setup
        // Configure FooService.getTwoLevelWrapped(...).
        final WrappedBean<OtherBean<Foo>> otherBeanWrappedBean = new WrappedBean<>();
        otherBeanWrappedBean.setName("name");
        final OtherBean<Foo> fooOtherBean = new OtherBean<>();
        fooOtherBean.setId(0L);
        final Foo foo = new Foo();
        foo.setId(0L);
        fooOtherBean.setTheVal(foo);
        otherBeanWrappedBean.setValue(fooOtherBean);
        given(mockFooService.getTwoLevelWrapped("id")).willReturn(otherBeanWrappedBean);

        // Run the test
        final WrappedBean<OtherBean<Foo>> result = myClassUnderTest.getTwoLevelWrapped("id");

        // Verify the results
    }

    @Test
    void testGetWrappedBeanUsingUnknownTypeWithGeneric() {
        // Setup
        // Configure FooService.getWrappedBeanUsingUnknownTypeWithGeneric(...).
        final WrappedBeanUsingUnknownTypeWithGeneric<Foo> fooWrappedBeanUsingUnknownTypeWithGeneric = new WrappedBeanUsingUnknownTypeWithGeneric<>();
        fooWrappedBeanUsingUnknownTypeWithGeneric.setName("name");
        final WrappedBean<T> wrappedBean = new WrappedBean<>();
        wrappedBean.setName("name");
        wrappedBean.setValue(null);
        fooWrappedBeanUsingUnknownTypeWithGeneric.setWrappedBean(wrappedBean);
        given(mockFooService.getWrappedBeanUsingUnknownTypeWithGeneric("id"))
                .willReturn(fooWrappedBeanUsingUnknownTypeWithGeneric);

        // Run the test
        final WrappedBeanUsingUnknownTypeWithGeneric<Foo> result = myClassUnderTest.getWrappedBeanUsingUnknownTypeWithGeneric(
                "id");

        // Verify the results
    }
}
