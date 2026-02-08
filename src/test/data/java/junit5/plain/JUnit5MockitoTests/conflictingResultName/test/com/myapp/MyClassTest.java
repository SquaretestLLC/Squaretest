package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetSearchResult() {
        // Setup
        final Result expectedResult = new Result();
        expectedResult.setId("id");
        expectedResult.setContent("content");

        // Configure FooService.getResult(...).
        final Result result1 = new Result();
        result1.setId("id");
        result1.setContent("content");
        when(mockFooService.getResult("id")).thenReturn(result1);

        // Run the test
        final Result result = myClassUnderTest.getSearchResult("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testResultMatchedOnEmail() {
        // Setup
        final SearchResult result1 = new SearchResult();

        // Run the test
        final boolean result = MyClass.resultMatchedOnEmail("query", result1);

        // Verify the results
        assertFalse(result);
    }
}
