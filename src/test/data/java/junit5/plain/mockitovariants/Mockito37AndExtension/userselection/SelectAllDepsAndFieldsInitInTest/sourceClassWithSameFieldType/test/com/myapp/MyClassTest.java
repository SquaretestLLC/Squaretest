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
    @Mock
    private MyClass mockOldDataStore;

    @Test
    void testGetFooById() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService, mockOldDataStore);
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }

    @Test
    void testGetFooById_FooServiceReturnsNull() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService, mockOldDataStore);
        when(mockFooService.getFooById("id")).thenReturn(null);
        when(mockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}
