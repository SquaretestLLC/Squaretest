package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    void testGetFoo1() {
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
    void testGetDefaultFoo() {
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
