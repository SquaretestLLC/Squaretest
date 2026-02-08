package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;
    @Mock
    private GammaData mockGammaData;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass();
        myClassUnderTest.setAlphaData(mockAlphaData);
        myClassUnderTest.setBetaData(mockBetaData);
        myClassUnderTest.setGammaData(mockGammaData);
        myClassUnderTest.setStartDate(LocalDate.of(2020, 1, 1));
        myClassUnderTest.setSerializedValue("serializedValue");
    }

    @Test
    public void testToString() {
        assertEquals("result", myClassUnderTest.toString());
    }
}
