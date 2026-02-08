package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(mockAlphaData);
    }

    @Test
    public void testGetBetaData() {
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(mockBetaData);
    }
}
