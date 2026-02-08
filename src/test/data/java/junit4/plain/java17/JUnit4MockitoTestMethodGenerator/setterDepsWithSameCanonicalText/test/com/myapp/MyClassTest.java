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
    private FooService mockFooService;
    @Mock
    private FooService mockBarService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setBarService(mockBarService);
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockBarService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}