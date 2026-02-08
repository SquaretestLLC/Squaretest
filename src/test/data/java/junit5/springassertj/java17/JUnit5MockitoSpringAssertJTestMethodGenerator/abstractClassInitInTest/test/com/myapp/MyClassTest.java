package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetFoo11() {
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
    void testGetDefaultFoo1() {
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