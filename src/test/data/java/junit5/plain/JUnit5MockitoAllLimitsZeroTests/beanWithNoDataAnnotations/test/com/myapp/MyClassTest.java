package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");
        expectedResult.setCreateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        expectedResult.setUpdateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        expectedResult.setNotes("notes");

        // Configure FooService.getFooData1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        fooData.setCreateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        fooData.setUpdateDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        fooData.setNotes("notes");
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
