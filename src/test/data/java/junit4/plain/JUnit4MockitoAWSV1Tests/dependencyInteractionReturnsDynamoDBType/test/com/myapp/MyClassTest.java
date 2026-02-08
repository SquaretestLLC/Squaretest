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
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
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
