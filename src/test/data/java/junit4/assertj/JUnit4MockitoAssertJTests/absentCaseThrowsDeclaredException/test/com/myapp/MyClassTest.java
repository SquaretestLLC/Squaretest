package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    public void testGetData_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getData(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getData(0L)).isInstanceOf(FooException.class);
    }
}
