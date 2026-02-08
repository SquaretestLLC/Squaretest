package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
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

    @Test(expected = FooException.class)
    public void testGetData_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.getData(0L);
    }
}
