package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService theMockFooService;
    @Mock
    private MyClass theMockOldDataStore;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(theMockFooService, theMockOldDataStore);
    }

    @Test
    public void testGetFooById1() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }

    @Test
    public void testGetFooById_FooServiceReturnsNull1() {
        // Setup
        when(theMockFooService.getFooById("id")).thenReturn(null);
        when(theMockOldDataStore.getFooById("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}