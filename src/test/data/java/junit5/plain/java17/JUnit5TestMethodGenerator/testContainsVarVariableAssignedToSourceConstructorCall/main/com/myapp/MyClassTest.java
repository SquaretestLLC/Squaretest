package com.myapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    void testGetFooData() {
        // Setup
        var theClass = new MyClass(mockFooService);
        when(mockFooService.getFooData("id")).thenReturn("result");

        // Run the test
        final String result = theClass.getFooData("id");

        // Verify the results
        assertEquals("result", result);
    }
}
