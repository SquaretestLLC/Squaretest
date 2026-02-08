package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AlphaData mockAlphaData;
    @Mock
    private BetaData mockBetaData;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
    }

    @Test
    void testGetAlphaData() {
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(mockAlphaData);
    }

    @Test
    void testGetBetaData() {
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(mockBetaData);
    }
}
