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

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetAlphaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertThat(myClassUnderTest.getAlphaData()).isEqualTo(mockAlphaData);
    }

    @Test
    void testGetBetaData() {
        final MyClass myClassUnderTest = new MyClass(mockAlphaData, mockBetaData);
        assertThat(myClassUnderTest.getBetaData()).isEqualTo(mockBetaData);
    }
}
