package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
    }

    @Test
    public void testGetAlphaData() {
        assertEquals(mockAlphaData, myClassUnderTest.getAlphaData());
    }

    @Test
    public void testGetBetaData() {
        assertEquals(mockBetaData, myClassUnderTest.getBetaData());
    }
}
