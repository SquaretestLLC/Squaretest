package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
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
    void testGetData() throws Exception {
        // Setup
        given(mockFooService.getData(0L)).willReturn(Optional.of(new FooData(0L, "name")));

        // Run the test
        final FooData result = myClassUnderTest.getData(0L);

        // Verify the results
    }

    @Test
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        given(mockFooService.getData(0L)).willReturn(Optional.empty());

        // Run the test
        assertThrows(FooException.class, () -> myClassUnderTest.getData(0L));
    }
}
