package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetTheBoolean() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(false);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testGetTheBoolean_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(true);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetTheByte() {
        // Setup
        when(mockFooService.getTheByte("id")).thenReturn((byte) 0b0);

        // Run the test
        final Byte result = myClassUnderTest.getTheByte("id");

        // Verify the results
        assertEquals(Byte.valueOf((byte) 0b0), result);
    }

    @Test
    public void testGetTheByte1() {
        assertEquals(Byte.valueOf((byte) 5), myClassUnderTest.getTheByte1());
    }

    @Test
    public void testGetTheCharacter() {
        // Setup
        when(mockFooService.getTheCharacter("id")).thenReturn('a');

        // Run the test
        final Character result = myClassUnderTest.getTheCharacter("id");

        // Verify the results
        assertEquals(Character.valueOf('a'), result);
    }

    @Test
    public void testGetTheFloat() {
        // Setup
        when(mockFooService.getTheFloat("id")).thenReturn(0.0f);

        // Run the test
        final Float result = myClassUnderTest.getTheFloat("id");

        // Verify the results
        assertEquals(0.0f, result, 0.0001);
    }

    @Test
    public void testGetTheInt() {
        // Setup
        when(mockFooService.getTheInt("id")).thenReturn(0);

        // Run the test
        final Integer result = myClassUnderTest.getTheInt("id");

        // Verify the results
        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void testGetTheLong() {
        // Setup
        when(mockFooService.getTheLong("id")).thenReturn(0L);

        // Run the test
        final Long result = myClassUnderTest.getTheLong("id");

        // Verify the results
        assertEquals(Long.valueOf(0L), result);
    }

    @Test
    public void testGetTheShort() {
        // Setup
        when(mockFooService.getTheShort("id")).thenReturn((short) 0);

        // Run the test
        final Short result = myClassUnderTest.getTheShort("id");

        // Verify the results
        assertEquals(Short.valueOf((short) 0), result);
    }

    @Test
    public void testGetTheDouble() {
        // Setup
        when(mockFooService.getTheDouble("id")).thenReturn(0.0);

        // Run the test
        final Double result = myClassUnderTest.getTheDouble("id");

        // Verify the results
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testGetTheDouble1() {
        assertEquals(0.0, myClassUnderTest.getTheDouble1(0.0), 0.0001);
    }

    @Test
    public void testGetTheObject1() {
        assertEquals(5, myClassUnderTest.getTheObject1());
    }

    @Test
    public void testGetTheObject2() {
        assertEquals(5L, myClassUnderTest.getTheObject2());
    }

    @Test
    public void testGetTheObject3() {
        assertEquals(5.1, myClassUnderTest.getTheObject3());
    }

    @Test
    public void testGetTheObject4() {
        assertEquals(5.1, myClassUnderTest.getTheObject4());
    }

    @Test
    public void testGetTheObject5() {
        assertEquals(5.1f, myClassUnderTest.getTheObject5());
    }

    @Test
    public void testGetTheObject6() {
        assertEquals(false, myClassUnderTest.getTheObject6());
    }

    @Test
    public void testGetTheObject7() {
        assertEquals("hello", myClassUnderTest.getTheObject7());
    }

    @Test
    public void testGetTheObject8() {
        assertEquals(9, myClassUnderTest.getTheObject8());
    }

    @Test
    public void testGetTheObject9() {
        assertEquals(5, myClassUnderTest.getTheObject9());
    }

    @Test
    public void testGetTheObject10() {
        assertEquals("result", myClassUnderTest.getTheObject10());
    }

    @Test
    public void testGetTheObject11() {
        assertEquals('b', myClassUnderTest.getTheObject11());
    }

    @Test
    public void testGetTheByte2() {
        assertEquals(Byte.valueOf((byte) 9), myClassUnderTest.getTheByte2());
    }

    @Test
    public void testGetTheByte3() {
        assertEquals((byte) 9, myClassUnderTest.getTheByte3());
    }

    @Test
    public void testGetTheByte4() {
        assertEquals((byte) -9, myClassUnderTest.getTheByte4());
    }

    @Test
    public void testGetTheByte5() {
        assertEquals((byte) 0, myClassUnderTest.getTheByte5());
    }

    @Test
    public void testGetTheInteger1() {
        assertEquals(Integer.valueOf(9), myClassUnderTest.getTheInteger1());
    }

    @Test
    public void testGetTheInteger2() {
        assertEquals(Integer.valueOf(9), myClassUnderTest.getTheInteger2());
    }

    @Test
    public void testGetTheLong1() {
        assertEquals(Long.valueOf(-6825872339779608251L), myClassUnderTest.getTheLong1());
    }

    @Test
    public void testGetTheLong2() {
        assertEquals(Long.valueOf(-6825872339779608251L), myClassUnderTest.getTheLong2());
    }

    @Test
    public void testGetTheLong3() {
        assertEquals(Long.valueOf(1321140L), myClassUnderTest.getTheLong3());
    }

    @Test
    public void testGetTheLong4() {
        assertEquals(Long.valueOf(291L), myClassUnderTest.getTheLong4());
    }

    @Test
    public void testGetTheInt1() {
        assertEquals(Integer.valueOf(291), myClassUnderTest.getTheInt1());
    }

    @Test
    public void testGetTheInt2() {
        assertEquals(291, myClassUnderTest.getTheInt2());
    }

    @Test
    public void testGetTheInt3() {
        assertEquals(0, myClassUnderTest.getTheInt3("id"));
    }

    @Test
    public void testGetTheNumber1() {
        assertEquals(5, myClassUnderTest.getTheNumber1());
    }

    @Test
    public void testGetTheNumber2() {
        assertEquals(5.5, myClassUnderTest.getTheNumber2());
    }

    @Test
    public void testGetTheNumber3() {
        assertEquals(5.5f, myClassUnderTest.getTheNumber3());
    }

    @Test
    public void testGetTheNumber4() {
        assertEquals(1, myClassUnderTest.getTheNumber4());
    }

    @Test
    public void testGetTheNumber5() {
        assertEquals(5L, myClassUnderTest.getTheNumber5());
    }

    @Test
    public void testGetTheNumber6() {
        assertEquals(4, myClassUnderTest.getTheNumber6());
    }

    @Test
    public void testGetTheNumber7() {
        assertEquals('4', myClassUnderTest.getTheNumber7());
    }

    @Test
    public void testGetTheNumber8() {
        assertEquals(5, myClassUnderTest.getTheNumber8());
    }

    @Test
    public void testGetTheNumber9() {
        assertEquals(new BigDecimal("0.00"), myClassUnderTest.getTheNumber9());
    }

    @Test
    public void testGetTheNumber10() {
        assertEquals(new BigDecimal("0.00"), myClassUnderTest.getTheNumber10());
    }

    @Test
    public void testGetTheChar1() {
        assertEquals(Character.valueOf((char) 1), myClassUnderTest.getTheChar1());
    }

    @Test
    public void testGetTheChar2() {
        assertEquals(Character.valueOf('\u0000'), myClassUnderTest.getTheChar2());
    }

    @Test
    public void testGetTheChar3() {
        assertEquals(Character.valueOf((char) 100), myClassUnderTest.getTheChar3());
    }

    @Test
    public void testGetTheChar4() {
        assertEquals(Character.valueOf('\uD83D'), myClassUnderTest.getTheChar4());
    }
}
