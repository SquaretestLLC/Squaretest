package com.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    void testGetFoo1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }

    @Test
    void testGetDefaultFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);

        // Configure FooService.getDefaultFoo(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getDefaultFoo("defaultFooId")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getDefaultFoo();

        // Verify the results
    }
}
