package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFoo11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService) {
            @Override
            public String getDefaultFooId() {
                return null;
            }
        };

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
        final MyClass myClassUnderTest = new MyClass(mockFooService) {
            @Override
            public String getDefaultFooId() {
                return null;
            }
        };

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