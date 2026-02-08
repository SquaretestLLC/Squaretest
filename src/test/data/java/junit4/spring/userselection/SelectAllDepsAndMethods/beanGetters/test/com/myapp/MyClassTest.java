package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;

    private MyClass myClassUnderTest;

    @Before
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
