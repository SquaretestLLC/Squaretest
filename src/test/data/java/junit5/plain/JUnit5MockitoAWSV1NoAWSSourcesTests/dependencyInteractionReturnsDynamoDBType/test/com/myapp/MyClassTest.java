package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testGetFooData() {
        // Setup
        // Configure Foo.getData(...).
        final FooData fooData = new FooData("purchaseId", "licenseName", new OtherData("dataName"));
        when(mockFoo.getData("name")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData("name");

        // Verify the results
    }
}
