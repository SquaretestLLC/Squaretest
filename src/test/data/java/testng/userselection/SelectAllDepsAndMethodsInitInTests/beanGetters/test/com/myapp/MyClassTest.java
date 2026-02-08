package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertEquals(mockAlphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    public void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertEquals(mockBetaData, myClassUnderTest.getBetaData());
    }
}
