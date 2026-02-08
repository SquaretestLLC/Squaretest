package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testGetFooData() {
        // Setup
        // Configure Foo.getData(...).
        final FooData fooData = new FooData("purchaseId", "licenseName", new OtherData("dataName"));
        when(mockFoo.getData("name")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData("name");

        // Verify the results
    }
}
