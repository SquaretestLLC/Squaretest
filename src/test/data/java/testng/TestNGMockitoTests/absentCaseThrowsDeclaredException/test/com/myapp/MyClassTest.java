package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetData() throws Exception {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.of(new FooData(0L, "name")));

        // Run the test
        final FooData result = myClassUnderTest.getData(0L);

        // Verify the results
    }

    @Test(expectedExceptions = {FooException.class})
    public void testGetData_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.getData(0L);
    }
}
