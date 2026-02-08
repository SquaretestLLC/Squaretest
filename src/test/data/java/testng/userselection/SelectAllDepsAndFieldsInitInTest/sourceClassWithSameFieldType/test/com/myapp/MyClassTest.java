package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyClass mockOldDataStore;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFooById() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService, mockOldDataStore);
        when(mockFooService.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }

    @Test
    public void testGetFooById_FooServiceReturnsNull() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService, mockOldDataStore);
        when(mockFooService.getFooById("id")).thenReturn(null);
        when(mockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}
