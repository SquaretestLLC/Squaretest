package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetTheBoolean() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(false);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testGetTheBoolean_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(true);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetTheByte() {
        // Setup
        when(mockFooService.getTheByte("id")).thenReturn((byte) 0b0);

        // Run the test
        final Byte result = myClassUnderTest.getTheByte("id");

        // Verify the results
        assertEquals(Byte.valueOf((byte) 0b0), result);
    }

    @Test
    void testGetTheByte1() {
        assertEquals(Byte.valueOf((byte) 5), myClassUnderTest.getTheByte1());
    }

    @Test
    void testGetTheCharacter() {
        // Setup
        when(mockFooService.getTheCharacter("id")).thenReturn('a');

        // Run the test
        final Character result = myClassUnderTest.getTheCharacter("id");

        // Verify the results
        assertEquals(Character.valueOf('a'), result);
    }

    @Test
    void testGetTheFloat() {
        // Setup
        when(mockFooService.getTheFloat("id")).thenReturn(0.0f);

        // Run the test
        final Float result = myClassUnderTest.getTheFloat("id");

        // Verify the results
        assertEquals(0.0f, result, 0.0001);
    }

    @Test
    void testGetTheInt() {
        // Setup
        when(mockFooService.getTheInt("id")).thenReturn(0);

        // Run the test
        final Integer result = myClassUnderTest.getTheInt("id");

        // Verify the results
        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetTheLong() {
        // Setup
        when(mockFooService.getTheLong("id")).thenReturn(0L);

        // Run the test
        final Long result = myClassUnderTest.getTheLong("id");

        // Verify the results
        assertEquals(Long.valueOf(0L), result);
    }

    @Test
    void testGetTheShort() {
        // Setup
        when(mockFooService.getTheShort("id")).thenReturn((short) 0);

        // Run the test
        final Short result = myClassUnderTest.getTheShort("id");

        // Verify the results
        assertEquals(Short.valueOf((short) 0), result);
    }

    @Test
    void testGetTheDouble() {
        // Setup
        when(mockFooService.getTheDouble("id")).thenReturn(0.0);

        // Run the test
        final Double result = myClassUnderTest.getTheDouble("id");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    void testGetTheDouble1() {
        assertEquals(0.0, myClassUnderTest.getTheDouble1(0.0), 0.0001);
    }

    @Test
    void testGetTheObject1() {
        assertEquals(5, myClassUnderTest.getTheObject1());
    }

    @Test
    void testGetTheObject2() {
        assertEquals(5L, myClassUnderTest.getTheObject2());
    }

    @Test
    void testGetTheObject3() {
        assertEquals(5.1, myClassUnderTest.getTheObject3());
    }

    @Test
    void testGetTheObject4() {
        assertEquals(5.1, myClassUnderTest.getTheObject4());
    }

    @Test
    void testGetTheObject5() {
        assertEquals(5.1f, myClassUnderTest.getTheObject5());
    }

    @Test
    void testGetTheObject6() {
        assertEquals(false, myClassUnderTest.getTheObject6());
    }

    @Test
    void testGetTheObject7() {
        assertEquals("hello", myClassUnderTest.getTheObject7());
    }

    @Test
    void testGetTheObject8() {
        assertEquals(9, myClassUnderTest.getTheObject8());
    }

    @Test
    void testGetTheObject9() {
        assertEquals(5, myClassUnderTest.getTheObject9());
    }

    @Test
    void testGetTheObject10() {
        assertEquals("result", myClassUnderTest.getTheObject10());
    }

    @Test
    void testGetTheObject11() {
        assertEquals('b', myClassUnderTest.getTheObject11());
    }

    @Test
    void testGetTheByte2() {
        assertEquals(Byte.valueOf((byte) 9), myClassUnderTest.getTheByte2());
    }

    @Test
    void testGetTheByte3() {
        assertEquals((byte) 9, myClassUnderTest.getTheByte3());
    }

    @Test
    void testGetTheByte4() {
        assertEquals((byte) -9, myClassUnderTest.getTheByte4());
    }

    @Test
    void testGetTheByte5() {
        assertEquals((byte) 0, myClassUnderTest.getTheByte5());
    }

    @Test
    void testGetTheInteger1() {
        assertEquals(Integer.valueOf(9), myClassUnderTest.getTheInteger1());
    }

    @Test
    void testGetTheInteger2() {
        assertEquals(Integer.valueOf(9), myClassUnderTest.getTheInteger2());
    }

    @Test
    void testGetTheLong1() {
        assertEquals(Long.valueOf(-6825872339779608251L), myClassUnderTest.getTheLong1());
    }

    @Test
    void testGetTheLong2() {
        assertEquals(Long.valueOf(-6825872339779608251L), myClassUnderTest.getTheLong2());
    }

    @Test
    void testGetTheLong3() {
        assertEquals(Long.valueOf(1321140L), myClassUnderTest.getTheLong3());
    }

    @Test
    void testGetTheLong4() {
        assertEquals(Long.valueOf(291L), myClassUnderTest.getTheLong4());
    }

    @Test
    void testGetTheInt1() {
        assertEquals(Integer.valueOf(291), myClassUnderTest.getTheInt1());
    }

    @Test
    void testGetTheInt2() {
        assertEquals(291, myClassUnderTest.getTheInt2());
    }

    @Test
    void testGetTheInt3() {
        assertEquals(0, myClassUnderTest.getTheInt3("id"));
    }

    @Test
    void testGetTheNumber1() {
        assertEquals(5, myClassUnderTest.getTheNumber1());
    }

    @Test
    void testGetTheNumber2() {
        assertEquals(5.5, myClassUnderTest.getTheNumber2());
    }

    @Test
    void testGetTheNumber3() {
        assertEquals(5.5f, myClassUnderTest.getTheNumber3());
    }

    @Test
    void testGetTheNumber4() {
        assertEquals(1, myClassUnderTest.getTheNumber4());
    }

    @Test
    void testGetTheNumber5() {
        assertEquals(5L, myClassUnderTest.getTheNumber5());
    }

    @Test
    void testGetTheNumber6() {
        assertEquals(4, myClassUnderTest.getTheNumber6());
    }

    @Test
    void testGetTheNumber7() {
        assertEquals('4', myClassUnderTest.getTheNumber7());
    }

    @Test
    void testGetTheNumber8() {
        assertEquals(5, myClassUnderTest.getTheNumber8());
    }

    @Test
    void testGetTheNumber9() {
        assertEquals(new BigDecimal("0.00"), myClassUnderTest.getTheNumber9());
    }

    @Test
    void testGetTheNumber10() {
        assertEquals(new BigDecimal("0.00"), myClassUnderTest.getTheNumber10());
    }

    @Test
    void testGetTheChar1() {
        assertEquals(Character.valueOf((char) 1), myClassUnderTest.getTheChar1());
    }

    @Test
    void testGetTheChar2() {
        assertEquals(Character.valueOf('\u0000'), myClassUnderTest.getTheChar2());
    }

    @Test
    void testGetTheChar3() {
        assertEquals(Character.valueOf((char) 100), myClassUnderTest.getTheChar3());
    }

    @Test
    void testGetTheChar4() {
        assertEquals(Character.valueOf('\uD83D'), myClassUnderTest.getTheChar4());
    }
}
