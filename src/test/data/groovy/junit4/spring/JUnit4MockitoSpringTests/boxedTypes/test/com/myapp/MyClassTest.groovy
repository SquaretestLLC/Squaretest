package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.*
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetTheBoolean() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(false)

        // Run the test
        def result = myClassUnderTest.getTheBoolean("id")

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testGetTheBoolean_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.getTheBoolean("id")).thenReturn(true)

        // Run the test
        def result = myClassUnderTest.getTheBoolean("id")

        // Verify the results
        assertTrue(result)
    }

    @Test
    void testGetTheByte() {
        // Setup
        when(mockFooService.getTheByte("id")).thenReturn((byte) 0b0)

        // Run the test
        def result = myClassUnderTest.getTheByte("id")

        // Verify the results
        assert (byte) 0b0 == result
    }

    @Test
    void testGetTheByte1() {
        assert (byte) 5 == myClassUnderTest.getTheByte1()
    }

    @Test
    void testGetTheCharacter() {
        // Setup
        when(mockFooService.getTheCharacter("id")).thenReturn('a')

        // Run the test
        def result = myClassUnderTest.getTheCharacter("id")

        // Verify the results
        assert 'a' == result
    }

    @Test
    void testGetTheFloat() {
        // Setup
        when(mockFooService.getTheFloat("id")).thenReturn(0.0f)

        // Run the test
        def result = myClassUnderTest.getTheFloat("id")

        // Verify the results
        assertEquals(0.0f, result, 0.0001f)
    }

    @Test
    void testGetTheInt() {
        // Setup
        when(mockFooService.getTheInt("id")).thenReturn(0)

        // Run the test
        def result = myClassUnderTest.getTheInt("id")

        // Verify the results
        assert 0 == result
    }

    @Test
    void testGetTheLong() {
        // Setup
        when(mockFooService.getTheLong("id")).thenReturn(0L)

        // Run the test
        def result = myClassUnderTest.getTheLong("id")

        // Verify the results
        assert 0L == result
    }

    @Test
    void testGetTheShort() {
        // Setup
        when(mockFooService.getTheShort("id")).thenReturn((short) 0)

        // Run the test
        def result = myClassUnderTest.getTheShort("id")

        // Verify the results
        assert (short) 0 == result
    }

    @Test
    void testGetTheDouble() {
        // Setup
        when(mockFooService.getTheDouble("id")).thenReturn(0.0d)

        // Run the test
        def result = myClassUnderTest.getTheDouble("id")

        // Verify the results
        assertEquals(0.0d, result, 0.0001d)
    }

    @Test
    void testGetTheDouble1() {
        assertEquals(0.0d, myClassUnderTest.getTheDouble1(0.0d), 0.0001d)
    }

    @Test
    void testGetTheObject1() {
        assert 5 == myClassUnderTest.getTheObject1()
    }

    @Test
    void testGetTheObject2() {
        assert 5L == myClassUnderTest.getTheObject2()
    }

    @Test
    void testGetTheObject3() {
        assert 5.1d == myClassUnderTest.getTheObject3()
    }

    @Test
    void testGetTheObject4() {
        assert 5.1d == myClassUnderTest.getTheObject4()
    }

    @Test
    void testGetTheObject5() {
        assert 5.1f == myClassUnderTest.getTheObject5()
    }

    @Test
    void testGetTheObject6() {
        assert false == myClassUnderTest.getTheObject6()
    }

    @Test
    void testGetTheObject7() {
        assert "hello" == myClassUnderTest.getTheObject7()
    }

    @Test
    void testGetTheObject8() {
        assert 9 == myClassUnderTest.getTheObject8()
    }

    @Test
    void testGetTheObject9() {
        assert 5 == myClassUnderTest.getTheObject9()
    }

    @Test
    void testGetTheObject10() {
        assert "result" == myClassUnderTest.getTheObject10()
    }

    @Test
    void testGetTheObject11() {
        assert 'b' == myClassUnderTest.getTheObject11()
    }

    @Test
    void testGetTheByte2() {
        assert (byte) 9 == myClassUnderTest.getTheByte2()
    }

    @Test
    void testGetTheByte3() {
        assert (byte) 9 == myClassUnderTest.getTheByte3()
    }

    @Test
    void testGetTheByte4() {
        assert (byte) -9 == myClassUnderTest.getTheByte4()
    }

    @Test
    void testGetTheByte5() {
        assert (byte) 0 == myClassUnderTest.getTheByte5()
    }

    @Test
    void testGetTheInteger1() {
        assert 9 == myClassUnderTest.getTheInteger1()
    }

    @Test
    void testGetTheInteger2() {
        assert 9 == myClassUnderTest.getTheInteger2()
    }

    @Test
    void testGetTheLong1() {
        assert -6825872339779608251L == myClassUnderTest.getTheLong1()
    }

    @Test
    void testGetTheLong2() {
        assert -6825872339779608251L == myClassUnderTest.getTheLong2()
    }

    @Test
    void testGetTheLong3() {
        assert 1321140L == myClassUnderTest.getTheLong3()
    }

    @Test
    void testGetTheLong4() {
        assert 291L == myClassUnderTest.getTheLong4()
    }

    @Test
    void testGetTheInt1() {
        assert 291 == myClassUnderTest.getTheInt1()
    }

    @Test
    void testGetTheInt2() {
        assert 291 == myClassUnderTest.getTheInt2()
    }

    @Test
    void testGetTheInt3() {
        assert 0 == myClassUnderTest.getTheInt3("id")
    }

    @Test
    void testGetTheNumber1() {
        assert 5 == myClassUnderTest.getTheNumber1()
    }

    @Test
    void testGetTheNumber2() {
        assert 5.5d == myClassUnderTest.getTheNumber2()
    }

    @Test
    void testGetTheNumber3() {
        assert 5.5f == myClassUnderTest.getTheNumber3()
    }

    @Test
    void testGetTheNumber4() {
        assert 1 == myClassUnderTest.getTheNumber4()
    }

    @Test
    void testGetTheNumber5() {
        assert 5L == myClassUnderTest.getTheNumber5()
    }

    @Test
    void testGetTheNumber6() {
        assert 4 == myClassUnderTest.getTheNumber6()
    }

    @Test
    void testGetTheNumber7() {
        assert '4' == myClassUnderTest.getTheNumber7()
    }

    @Test
    void testGetTheNumber8() {
        assert 5 == myClassUnderTest.getTheNumber8()
    }

    @Test
    void testGetTheNumber9() {
        assert new BigDecimal("0.00") == myClassUnderTest.getTheNumber9()
    }

    @Test
    void testGetTheNumber10() {
        assert new BigDecimal("0.00") == myClassUnderTest.getTheNumber10()
    }

    @Test
    void testGetTheChar1() {
        assert (char) 1 == myClassUnderTest.getTheChar1()
    }

    @Test
    void testGetTheChar2() {
        assert '\u0000' == myClassUnderTest.getTheChar2()
    }

    @Test
    void testGetTheChar3() {
        assert (char) 100 == myClassUnderTest.getTheChar3()
    }

    @Test
    void testGetTheChar4() {
        assert '\uD83D' == myClassUnderTest.getTheChar4()
    }
}
