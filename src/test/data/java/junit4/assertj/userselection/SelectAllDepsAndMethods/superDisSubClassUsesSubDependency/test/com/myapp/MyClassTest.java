package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private SubFooService mockSubFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockSubFooService);
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        when(mockSubFooService.getData("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo_SubFooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockSubFooService.getData("key")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoo2() throws Exception {
        // Setup
        when(mockSubFooService.getOtherData("key")).thenReturn("result");
        when(mockSubFooService.doSomething("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo2_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockSubFooService.getOtherData("key")).thenThrow(IOException.class);
        when(mockSubFooService.doSomething("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
