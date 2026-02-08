package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFoo11() {
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
    public void testGetDefaultFoo1() {
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