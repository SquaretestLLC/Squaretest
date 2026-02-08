package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
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

    @Test(expected = FileNotFoundException.class)
    public void testGetFooData_FooServiceThrowsFileNotFoundException() throws Exception {
        // Setup
        when(mockFooService.getFooData(0L)).thenThrow(FileNotFoundException.class);

        // Run the test
        myClassUnderTest.getFooData(0L);
    }
}
