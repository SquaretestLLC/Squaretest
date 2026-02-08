package com.myapp;

import com.myapp.libs.Optional;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private CustomOptionalFooProvider mockOptionalFooProvider;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockOptionalFooProvider);
    }

    @Test
    public void testGetTheStr() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
    }

    @Test
    public void testSubmitString() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }
}
