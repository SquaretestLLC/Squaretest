package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void testGetData() throws Exception {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.of(new FooData(0L, "name")));

        // Run the test
        final FooData result = myClassUnderTest.getData(0L);

        // Verify the results
    }

    @Test
    void testGetData_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData(0L)).isInstanceOf(FooException.class);
    }
}
