package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetTheBoolean() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(false)

        // Run the test
        def result = myClassUnderTest.getTheBoolean("id")

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testGetTheBoolean_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(true)

        // Run the test
        def result = myClassUnderTest.getTheBoolean("id")

        // Verify the results
        assertThat(result).isTrue()
    }

    @Test
    void testGetTheByte() {
        // Setup
        when(mockFooService.getTheByte("id")).thenReturn((byte) 0b0)

        // Run the test
        def result = myClassUnderTest.getTheByte("id")

        // Verify the results
        assertThat(result).isEqualTo((byte) 0b0)
    }

    @Test
    void testGetTheByte1() {
        assertThat(myClassUnderTest.getTheByte1()).isEqualTo((byte) 5)
    }

    @Test
    void testGetTheCharacter() {
        // Setup
        when(mockFooService.getTheCharacter("id")).thenReturn('a')

        // Run the test
        def result = myClassUnderTest.getTheCharacter("id")

        // Verify the results
        assertThat(result).isEqualTo('a')
    }

    @Test
    void testGetTheFloat() {
        // Setup
        when(mockFooService.getTheFloat("id")).thenReturn(0.0f)

        // Run the test
        def result = myClassUnderTest.getTheFloat("id")

        // Verify the results
        assertThat(result).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testGetTheInt() {
        // Setup
        when(mockFooService.getTheInt("id")).thenReturn(0)

        // Run the test
        def result = myClassUnderTest.getTheInt("id")

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testGetTheLong() {
        // Setup
        when(mockFooService.getTheLong("id")).thenReturn(0L)

        // Run the test
        def result = myClassUnderTest.getTheLong("id")

        // Verify the results
        assertThat(result).isEqualTo(0L)
    }

    @Test
    void testGetTheShort() {
        // Setup
        when(mockFooService.getTheShort("id")).thenReturn((short) 0)

        // Run the test
        def result = myClassUnderTest.getTheShort("id")

        // Verify the results
        assertThat(result).isEqualTo((short) 0)
    }

    @Test
    void testGetTheDouble() {
        // Setup
        when(mockFooService.getTheDouble("id")).thenReturn(0.0d)

        // Run the test
        def result = myClassUnderTest.getTheDouble("id")

        // Verify the results
        assertThat(result).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testGetTheDouble1() {
        assertThat(myClassUnderTest.getTheDouble1(0.0d)).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testGetTheObject1() {
        assertThat(myClassUnderTest.getTheObject1()).isEqualTo(5)
    }

    @Test
    void testGetTheObject2() {
        assertThat(myClassUnderTest.getTheObject2()).isEqualTo(5L)
    }

    @Test
    void testGetTheObject3() {
        assertThat(myClassUnderTest.getTheObject3()).isEqualTo(5.1d)
    }

    @Test
    void testGetTheObject4() {
        assertThat(myClassUnderTest.getTheObject4()).isEqualTo(5.1d)
    }

    @Test
    void testGetTheObject5() {
        assertThat(myClassUnderTest.getTheObject5()).isEqualTo(5.1f)
    }

    @Test
    void testGetTheObject6() {
        assertThat(myClassUnderTest.getTheObject6()).isEqualTo(false)
    }

    @Test
    void testGetTheObject7() {
        assertThat(myClassUnderTest.getTheObject7()).isEqualTo("hello")
    }

    @Test
    void testGetTheObject8() {
        assertThat(myClassUnderTest.getTheObject8()).isEqualTo(9)
    }

    @Test
    void testGetTheObject9() {
        assertThat(myClassUnderTest.getTheObject9()).isEqualTo(5)
    }

    @Test
    void testGetTheObject10() {
        assertThat(myClassUnderTest.getTheObject10()).isEqualTo("result")
    }

    @Test
    void testGetTheObject11() {
        assertThat(myClassUnderTest.getTheObject11()).isEqualTo('b')
    }

    @Test
    void testGetTheByte2() {
        assertThat(myClassUnderTest.getTheByte2()).isEqualTo((byte) 9)
    }

    @Test
    void testGetTheByte3() {
        assertThat(myClassUnderTest.getTheByte3()).isEqualTo((byte) 9)
    }

    @Test
    void testGetTheByte4() {
        assertThat(myClassUnderTest.getTheByte4()).isEqualTo((byte) -9)
    }

    @Test
    void testGetTheByte5() {
        assertThat(myClassUnderTest.getTheByte5()).isEqualTo((byte) 0)
    }

    @Test
    void testGetTheInteger1() {
        assertThat(myClassUnderTest.getTheInteger1()).isEqualTo(9)
    }

    @Test
    void testGetTheInteger2() {
        assertThat(myClassUnderTest.getTheInteger2()).isEqualTo(9)
    }

    @Test
    void testGetTheLong1() {
        assertThat(myClassUnderTest.getTheLong1()).isEqualTo(-6825872339779608251L)
    }

    @Test
    void testGetTheLong2() {
        assertThat(myClassUnderTest.getTheLong2()).isEqualTo(-6825872339779608251L)
    }

    @Test
    void testGetTheLong3() {
        assertThat(myClassUnderTest.getTheLong3()).isEqualTo(1321140L)
    }

    @Test
    void testGetTheLong4() {
        assertThat(myClassUnderTest.getTheLong4()).isEqualTo(291L)
    }

    @Test
    void testGetTheInt1() {
        assertThat(myClassUnderTest.getTheInt1()).isEqualTo(291)
    }

    @Test
    void testGetTheInt2() {
        assertThat(myClassUnderTest.getTheInt2()).isEqualTo(291)
    }

    @Test
    void testGetTheInt3() {
        assertThat(myClassUnderTest.getTheInt3("id")).isEqualTo(0)
    }

    @Test
    void testGetTheNumber1() {
        assertThat(myClassUnderTest.getTheNumber1()).isEqualTo(5)
    }

    @Test
    void testGetTheNumber2() {
        assertThat(myClassUnderTest.getTheNumber2()).isEqualTo(5.5d)
    }

    @Test
    void testGetTheNumber3() {
        assertThat(myClassUnderTest.getTheNumber3()).isEqualTo(5.5f)
    }

    @Test
    void testGetTheNumber4() {
        assertThat(myClassUnderTest.getTheNumber4()).isEqualTo(1)
    }

    @Test
    void testGetTheNumber5() {
        assertThat(myClassUnderTest.getTheNumber5()).isEqualTo(5L)
    }

    @Test
    void testGetTheNumber6() {
        assertThat(myClassUnderTest.getTheNumber6()).isEqualTo(4)
    }

    @Test
    void testGetTheNumber7() {
        assertThat(myClassUnderTest.getTheNumber7()).isEqualTo('4')
    }

    @Test
    void testGetTheNumber8() {
        assertThat(myClassUnderTest.getTheNumber8()).isEqualTo(5)
    }

    @Test
    void testGetTheNumber9() {
        assertThat(myClassUnderTest.getTheNumber9()).isEqualTo(new BigDecimal("0.00"))
    }

    @Test
    void testGetTheNumber10() {
        assertThat(myClassUnderTest.getTheNumber10()).isEqualTo(new BigDecimal("0.00"))
    }

    @Test
    void testGetTheChar1() {
        assertThat(myClassUnderTest.getTheChar1()).isEqualTo((char) 1)
    }

    @Test
    void testGetTheChar2() {
        assertThat(myClassUnderTest.getTheChar2()).isEqualTo('\u0000')
    }

    @Test
    void testGetTheChar3() {
        assertThat(myClassUnderTest.getTheChar3()).isEqualTo((char) 100)
    }

    @Test
    void testGetTheChar4() {
        assertThat(myClassUnderTest.getTheChar4()).isEqualTo('\uD83D')
    }
}
