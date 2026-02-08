package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

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
    public void testGetFooData() throws Exception {
        // Setup
        when(mockFooService.getFooData(0L)).thenReturn(new FooData("name", 0L));

        // Run the test
        final FooData result = myClassUnderTest.getFooData(0L);

        // Verify the results
    }

    @Test(expectedExceptions = {FileNotFoundException.class})
    public void testGetFooData_FooServiceThrowsFileNotFoundException() throws Exception {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class);

        // Run the test
        myClassUnderTest.getFooData(0L);
    }
}
