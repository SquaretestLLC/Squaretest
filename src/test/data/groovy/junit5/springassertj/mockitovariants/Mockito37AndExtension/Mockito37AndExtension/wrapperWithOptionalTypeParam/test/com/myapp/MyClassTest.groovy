package com.myapp

import com.myapp.wrapped.*
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetString() {
        // Setup
        when(mockFooService.getString("id")).thenReturn(Wrapper.of("value"))

        // Run the test
        def result = myClassUnderTest.getString("id")

        // Verify the results
    }

    @Test
    void testGetFoo() {
        // Setup
        // Configure FooService.getFoo(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def fooWrapper = Wrapper.of(foo)
        when(mockFooService.getFoo("id")).thenReturn(fooWrapper)

        // Run the test
        def result = myClassUnderTest.getFoo("id")

        // Verify the results
    }

    @Test
    void testGetOptionalFoo() {
        // Setup
        // Configure FooService.getOptionalFoo(...).
        def foo = new Foo()
        foo.setId(0L)
        foo.setName("name")
        foo.setDescription("description")
        def optionalWrapper = Wrapper.of(Optional.of(foo))
        when(mockFooService.getOptionalFoo("id")).thenReturn(optionalWrapper)

        // Run the test
        def result = myClassUnderTest.getOptionalFoo("id")

        // Verify the results
    }

    @Test
    void testGetOptionalFoo_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getOptionalFoo("id")).thenReturn(Wrapper.of(Optional.empty()))

        // Run the test
        def result = myClassUnderTest.getOptionalFoo("id")

        // Verify the results
    }

    @Test
    void testGetWrapperBeanWithListProp() {
        // Setup
        // Configure FooService.getWrapperBeanWithListProp(...).
        def fooWrapperBeanWithListProp = new WrapperBeanWithListProp<>()
        fooWrapperBeanWithListProp.setName("name")
        fooWrapperBeanWithListProp.setValues([])
        when(mockFooService.getWrapperBeanWithListProp("id")).thenReturn(fooWrapperBeanWithListProp)

        // Run the test
        def result = myClassUnderTest.getWrapperBeanWithListProp("id")

        // Verify the results
    }

    @Test
    void testGetTwoLevelWrapped() {
        // Setup
        // Configure FooService.getTwoLevelWrapped(...).
        def otherBeanWrappedBean = new WrappedBean<>()
        otherBeanWrappedBean.setName("name")
        def fooOtherBean = new OtherBean<>()
        fooOtherBean.setId(0L)
        def foo = new Foo()
        foo.setId(0L)
        fooOtherBean.setTheVal(foo)
        otherBeanWrappedBean.setValue(fooOtherBean)
        when(mockFooService.getTwoLevelWrapped("id")).thenReturn(otherBeanWrappedBean)

        // Run the test
        def result = myClassUnderTest.getTwoLevelWrapped("id")

        // Verify the results
    }

    @Test
    void testGetWrappedBeanUsingUnknownTypeWithGeneric() {
        // Setup
        // Configure FooService.getWrappedBeanUsingUnknownTypeWithGeneric(...).
        def fooWrappedBeanUsingUnknownTypeWithGeneric = new WrappedBeanUsingUnknownTypeWithGeneric<>()
        fooWrappedBeanUsingUnknownTypeWithGeneric.setName("name")
        def wrappedBean = new WrappedBean<>()
        wrappedBean.setName("name")
        wrappedBean.setValue(null)
        fooWrappedBeanUsingUnknownTypeWithGeneric.setWrappedBean(wrappedBean)
        when(mockFooService.getWrappedBeanUsingUnknownTypeWithGeneric("id"))
                .thenReturn(fooWrappedBeanUsingUnknownTypeWithGeneric)

        // Run the test
        def result = myClassUnderTest.getWrappedBeanUsingUnknownTypeWithGeneric("id")

        // Verify the results
    }
}
