package com.myapp;

import com.myapp.wrapped.*;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetString() {
        // Setup
        when(mockFooService.getString("id")).thenReturn(Wrapper.of("value"));

        // Run the test
        final Wrapper<String> result = myClassUnderTest.getString("id");

        // Verify the results
    }

    @Test
    public void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Wrapper<Foo> fooWrapper = Wrapper.of(foo);
        when(mockFooService.getFoo("id")).thenReturn(fooWrapper);

        // Run the test
        final Wrapper<Foo> result = myClassUnderTest.getFoo("id");

        // Verify the results
    }

    @Test
    public void testGetOptionalFoo() {
        // Setup
        // Configure FooService.getOptionalFoo(...).
        final Foo foo = new Foo();
        foo.setId(0L);
        foo.setName("name");
        foo.setDescription("description");
        final Wrapper<Optional<Foo>> optionalWrapper = Wrapper.of(Optional.of(foo));
        when(mockFooService.getOptionalFoo("id")).thenReturn(optionalWrapper);

        // Run the test
        final Wrapper<Optional<Foo>> result = myClassUnderTest.getOptionalFoo("id");

        // Verify the results
    }

    @Test
    public void testGetOptionalFoo_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFoo("id")).thenReturn(Wrapper.of(Optional.empty()));

        // Run the test
        final Wrapper<Optional<Foo>> result = myClassUnderTest.getOptionalFoo("id");

        // Verify the results
    }

    @Test
    public void testGetWrapperBeanWithListProp() {
        // Setup
        // Configure FooService.getWrapperBeanWithListProp(...).
        final WrapperBeanWithListProp<Foo> fooWrapperBeanWithListProp = new WrapperBeanWithListProp<>();
        fooWrapperBeanWithListProp.setName("name");
        fooWrapperBeanWithListProp.setValues(Arrays.asList());
        when(mockFooService.getWrapperBeanWithListProp("id")).thenReturn(fooWrapperBeanWithListProp);

        // Run the test
        final WrapperBeanWithListProp<Foo> result = myClassUnderTest.getWrapperBeanWithListProp("id");

        // Verify the results
    }

    @Test
    public void testGetTwoLevelWrapped() {
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
        when(mockFooService.getTwoLevelWrapped("id")).thenReturn(otherBeanWrappedBean);

        // Run the test
        final WrappedBean<OtherBean<Foo>> result = myClassUnderTest.getTwoLevelWrapped("id");

        // Verify the results
    }

    @Test
    public void testGetWrappedBeanUsingUnknownTypeWithGeneric() {
        // Setup
        // Configure FooService.getWrappedBeanUsingUnknownTypeWithGeneric(...).
        final WrappedBeanUsingUnknownTypeWithGeneric<Foo> fooWrappedBeanUsingUnknownTypeWithGeneric = new WrappedBeanUsingUnknownTypeWithGeneric<>();
        fooWrappedBeanUsingUnknownTypeWithGeneric.setName("name");
        final WrappedBean<T> wrappedBean = new WrappedBean<>();
        wrappedBean.setName("name");
        wrappedBean.setValue(null);
        fooWrappedBeanUsingUnknownTypeWithGeneric.setWrappedBean(wrappedBean);
        when(mockFooService.getWrappedBeanUsingUnknownTypeWithGeneric("id"))
                .thenReturn(fooWrappedBeanUsingUnknownTypeWithGeneric);

        // Run the test
        final WrappedBeanUsingUnknownTypeWithGeneric<Foo> result = myClassUnderTest.getWrappedBeanUsingUnknownTypeWithGeneric(
                "id");

        // Verify the results
    }
}
