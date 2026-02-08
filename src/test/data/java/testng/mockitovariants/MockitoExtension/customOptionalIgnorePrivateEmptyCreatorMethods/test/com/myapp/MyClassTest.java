package com.myapp;

import com.myapp.libs.Optional;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

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
    public void testGetTheStr_CustomOptionalFooProviderReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.noValue());

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
    }
}
