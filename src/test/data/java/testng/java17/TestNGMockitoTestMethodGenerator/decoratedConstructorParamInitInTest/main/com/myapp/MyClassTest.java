package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {
    @Mock
    private FooService mockFooService;
    @BeforeMethod
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetFoo1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        // TODO: Set the following fields: fooService.
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService1.getFoo1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}
