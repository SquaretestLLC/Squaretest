package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetTheBoolean() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(false);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testGetTheBoolean_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(true);

        // Run the test
        final Boolean result = myClassUnderTest.getTheBoolean("id");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testGetTheByte() {
        // Setup
        when(mockFooService.getTheByte("id")).thenReturn((byte) 0b0);

        // Run the test
        final Byte result = myClassUnderTest.getTheByte("id");

        // Verify the results
        assertThat(result).isEqualTo((byte) 0b0);
    }

    @Test
    public void testGetTheByte1() {
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 5);
    }

    @Test
    public void testGetTheCharacter() {
        // Setup
        when(mockFooService.getTheCharacter("id")).thenReturn('a');

        // Run the test
        final Character result = myClassUnderTest.getTheCharacter("id");

        // Verify the results
        assertThat(result).isEqualTo('a');
    }

    @Test
    public void testGetTheFloat() {
        // Setup
        when(mockFooService.getTheFloat("id")).thenReturn(0.0f);

        // Run the test
        final Float result = myClassUnderTest.getTheFloat("id");

        // Verify the results
        assertThat(result).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testGetTheInt() {
        // Setup
        when(mockFooService.getTheInt("id")).thenReturn(0);

        // Run the test
        final Integer result = myClassUnderTest.getTheInt("id");

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testGetTheLong() {
        // Setup
        when(mockFooService.getTheLong("id")).thenReturn(0L);

        // Run the test
        final Long result = myClassUnderTest.getTheLong("id");

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void testGetTheShort() {
        // Setup
        when(mockFooService.getTheShort("id")).thenReturn((short) 0);

        // Run the test
        final Short result = myClassUnderTest.getTheShort("id");

        // Verify the results
        assertThat(result).isEqualTo((short) 0);
    }

    @Test
    public void testGetTheDouble() {
        // Setup
        when(mockFooService.getTheDouble("id")).thenReturn(0.0);

        // Run the test
        final Double result = myClassUnderTest.getTheDouble("id");

        // Verify the results
        assertThat(result).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testGetTheDouble1() {
        assertThat(myClassUnderTest.getTheDouble1(0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testGetTheObject1() {
        assertThat(myClassUnderTest.getTheObject1()).isEqualTo(5);
    }

    @Test
    public void testGetTheObject2() {
        assertThat(myClassUnderTest.getTheObject2()).isEqualTo(5L);
    }

    @Test
    public void testGetTheObject3() {
        assertThat(myClassUnderTest.getTheObject3()).isEqualTo(5.1);
    }

    @Test
    public void testGetTheObject4() {
        assertThat(myClassUnderTest.getTheObject4()).isEqualTo(5.1);
    }

    @Test
    public void testGetTheObject5() {
        assertThat(myClassUnderTest.getTheObject5()).isEqualTo(5.1f);
    }

    @Test
    public void testGetTheObject6() {
        assertThat(myClassUnderTest.getTheObject6()).isEqualTo(false);
    }

    @Test
    public void testGetTheObject7() {
        assertThat(myClassUnderTest.getTheObject7()).isEqualTo("hello");
    }

    @Test
    public void testGetTheObject8() {
        assertThat(myClassUnderTest.getTheObject8()).isEqualTo(9);
    }

    @Test
    public void testGetTheObject9() {
        assertThat(myClassUnderTest.getTheObject9()).isEqualTo(5);
    }

    @Test
    public void testGetTheObject10() {
        assertThat(myClassUnderTest.getTheObject10()).isEqualTo("result");
    }

    @Test
    public void testGetTheObject11() {
        assertThat(myClassUnderTest.getTheObject11()).isEqualTo('b');
    }

    @Test
    public void testGetTheByte2() {
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 9);
    }

    @Test
    public void testGetTheByte3() {
        assertThat(myClassUnderTest.getTheByte3()).isEqualTo((byte) 9);
    }

    @Test
    public void testGetTheByte4() {
        assertThat(myClassUnderTest.getTheByte4()).isEqualTo((byte) -9);
    }

    @Test
    public void testGetTheByte5() {
        assertThat(myClassUnderTest.getTheByte5()).isEqualTo((byte) 0);
    }

    @Test
    public void testGetTheInteger1() {
        assertThat(myClassUnderTest.getTheInteger1()).isEqualTo(9);
    }

    @Test
    public void testGetTheInteger2() {
        assertThat(myClassUnderTest.getTheInteger2()).isEqualTo(9);
    }

    @Test
    public void testGetTheLong1() {
        assertThat(myClassUnderTest.getTheLong1()).isEqualTo(-6825872339779608251L);
    }

    @Test
    public void testGetTheLong2() {
        assertThat(myClassUnderTest.getTheLong2()).isEqualTo(-6825872339779608251L);
    }

    @Test
    public void testGetTheLong3() {
        assertThat(myClassUnderTest.getTheLong3()).isEqualTo(1321140L);
    }

    @Test
    public void testGetTheLong4() {
        assertThat(myClassUnderTest.getTheLong4()).isEqualTo(291L);
    }

    @Test
    public void testGetTheInt1() {
        assertThat(myClassUnderTest.getTheInt1()).isEqualTo(291);
    }

    @Test
    public void testGetTheInt2() {
        assertThat(myClassUnderTest.getTheInt2()).isEqualTo(291);
    }

    @Test
    public void testGetTheInt3() {
        assertThat(myClassUnderTest.getTheInt3("id")).isEqualTo(0);
    }

    @Test
    public void testGetTheNumber1() {
        assertThat(myClassUnderTest.getTheNumber1()).isEqualTo(5);
    }

    @Test
    public void testGetTheNumber2() {
        assertThat(myClassUnderTest.getTheNumber2()).isEqualTo(5.5);
    }

    @Test
    public void testGetTheNumber3() {
        assertThat(myClassUnderTest.getTheNumber3()).isEqualTo(5.5f);
    }

    @Test
    public void testGetTheNumber4() {
        assertThat(myClassUnderTest.getTheNumber4()).isEqualTo(1);
    }

    @Test
    public void testGetTheNumber5() {
        assertThat(myClassUnderTest.getTheNumber5()).isEqualTo(5L);
    }

    @Test
    public void testGetTheNumber6() {
        assertThat(myClassUnderTest.getTheNumber6()).isEqualTo(4);
    }

    @Test
    public void testGetTheNumber7() {
        assertThat(myClassUnderTest.getTheNumber7()).isEqualTo('4');
    }

    @Test
    public void testGetTheNumber8() {
        assertThat(myClassUnderTest.getTheNumber8()).isEqualTo(5);
    }

    @Test
    public void testGetTheNumber9() {
        assertThat(myClassUnderTest.getTheNumber9()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    public void testGetTheNumber10() {
        assertThat(myClassUnderTest.getTheNumber10()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    public void testGetTheChar1() {
        assertThat(myClassUnderTest.getTheChar1()).isEqualTo((char) 1);
    }

    @Test
    public void testGetTheChar2() {
        assertThat(myClassUnderTest.getTheChar2()).isEqualTo('\u0000');
    }

    @Test
    public void testGetTheChar3() {
        assertThat(myClassUnderTest.getTheChar3()).isEqualTo((char) 100);
    }

    @Test
    public void testGetTheChar4() {
        assertThat(myClassUnderTest.getTheChar4()).isEqualTo('\uD83D');
    }
}
