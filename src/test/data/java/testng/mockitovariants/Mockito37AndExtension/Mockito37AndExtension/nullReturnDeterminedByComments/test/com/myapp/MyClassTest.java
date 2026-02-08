package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private NullFooProvider mockNullFooProvider;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockNullFooProvider);
    }

    @Test
    public void testGetFoo() {
        // Setup
        when(mockNullFooProvider.getFoo()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo1() {
        // Setup
        when(mockNullFooProvider.getFoo1()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo1_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo1()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        when(mockNullFooProvider.getFoo2()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo2()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo3() {
        // Setup
        when(mockNullFooProvider.getFoo3()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo3_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo3()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo4() {
        // Setup
        when(mockNullFooProvider.getFoo4()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo4_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo4()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo5() {
        // Setup
        when(mockNullFooProvider.getFoo5()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo5_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo5()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo5();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo6() {
        // Setup
        when(mockNullFooProvider.getFoo6()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo6_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo6()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo6();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo7() {
        // Setup
        when(mockNullFooProvider.getFoo7()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo7_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo7()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo7();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo8() {
        // Setup
        when(mockNullFooProvider.getFoo8()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo8_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo8()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo8();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo9() {
        // Setup
        when(mockNullFooProvider.getFoo9()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo9();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo9_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo9()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo9();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo10() {
        // Setup
        when(mockNullFooProvider.getFoo10()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo10();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo10_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo10()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo10();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo11() {
        // Setup
        when(mockNullFooProvider.getFoo11()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo11();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo11_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo11()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo11();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo12() {
        // Setup
        when(mockNullFooProvider.getFoo12()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo12();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo12_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo12()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo12();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo13() {
        // Setup
        when(mockNullFooProvider.getFoo13()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo13();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo13_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo13()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo13();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo14() {
        // Setup
        when(mockNullFooProvider.getFoo14()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo14();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo14_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo14()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo14();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo15() {
        // Setup
        when(mockNullFooProvider.getFoo15()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo15();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo15_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo15()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo15();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo16() {
        // Setup
        when(mockNullFooProvider.getFoo16()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo16();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo16_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo16()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo16();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo17() {
        // Setup
        when(mockNullFooProvider.getFoo17()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo17();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo17_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo17()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo17();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo18() {
        // Setup
        when(mockNullFooProvider.getFoo18()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo18();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo18_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo18()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo18();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo19() {
        // Setup
        when(mockNullFooProvider.getFoo19()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo19();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo19_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo19()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo19();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo20() {
        // Setup
        when(mockNullFooProvider.getFoo20()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo20();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo20_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo20()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo20();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo21() {
        // Setup
        when(mockNullFooProvider.getFoo21()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo21();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo21_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo21()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo21();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo22() {
        // Setup
        when(mockNullFooProvider.getFoo22()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo22();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo22_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo22()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo22();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo23() {
        // Setup
        when(mockNullFooProvider.getFoo23()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo23();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo23_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo23()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo23();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo24() {
        // Setup
        when(mockNullFooProvider.getFoo24()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo24();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo24_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo24()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo24();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo25() {
        // Setup
        when(mockNullFooProvider.getFoo25()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo25();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo25_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo25()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo25();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo26() {
        // Setup
        when(mockNullFooProvider.getFoo26()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo26();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo26_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo26()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo26();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo27() {
        // Setup
        when(mockNullFooProvider.getFoo27()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo27();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo27_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo27()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo27();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo28() {
        // Setup
        when(mockNullFooProvider.getFoo28()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo28();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo28_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo28()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo28();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo29() {
        // Setup
        when(mockNullFooProvider.getFoo29()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo29();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo29_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo29()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo29();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo30() {
        // Setup
        when(mockNullFooProvider.getFoo30()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo30();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo30_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo30()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo30();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo31() {
        // Setup
        when(mockNullFooProvider.getFoo31()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo31();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo31_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo31()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo31();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo32() {
        // Setup
        when(mockNullFooProvider.getFoo32()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo32();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo32_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo32()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo32();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo33() {
        // Setup
        when(mockNullFooProvider.getFoo33()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo33();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo33_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo33()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo33();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo34() {
        // Setup
        when(mockNullFooProvider.getFoo34()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo34();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo34_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo34()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo34();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo35() {
        // Setup
        when(mockNullFooProvider.getFoo35()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo35();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo35_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo35()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo35();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo36() {
        // Setup
        when(mockNullFooProvider.getFoo36()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo36();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo36_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo36()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo36();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo37() {
        // Setup
        when(mockNullFooProvider.getFoo37()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo37();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo37_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo37()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo37();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo38() {
        // Setup
        when(mockNullFooProvider.getFoo38()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo38();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo38_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo38()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo38();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo39() {
        // Setup
        when(mockNullFooProvider.getFoo39()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo39();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo39_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo39()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo39();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo40() {
        // Setup
        when(mockNullFooProvider.getFoo40()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo40();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo40_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo40()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo40();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo41() {
        // Setup
        when(mockNullFooProvider.getFoo41()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo41();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo41_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo41()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo41();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo42() {
        // Setup
        when(mockNullFooProvider.getFoo42()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo42();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo42_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo42()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo42();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo44() {
        // Setup
        when(mockNullFooProvider.getFoo44()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo44();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo44_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo44()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo44();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo45() {
        // Setup
        when(mockNullFooProvider.getFoo45()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo45();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo45_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo45()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo45();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo46() {
        // Setup
        when(mockNullFooProvider.getFoo46()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo46();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo46_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo46()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo46();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo47() {
        // Setup
        when(mockNullFooProvider.getFoo47()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo47();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo47_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo47()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo47();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo48() {
        // Setup
        when(mockNullFooProvider.getFoo48()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo48();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo48_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo48()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo48();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo49() {
        // Setup
        when(mockNullFooProvider.getFoo49()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo49();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo49_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo49()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo49();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo50() {
        // Setup
        when(mockNullFooProvider.getFoo50()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo50();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo50_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo50()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo50();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo51() {
        // Setup
        when(mockNullFooProvider.getFoo51()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo51();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo51_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo51()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo51();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo52() {
        // Setup
        when(mockNullFooProvider.getFoo52()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo52();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo52_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo52()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo52();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo53() {
        // Setup
        when(mockNullFooProvider.getFoo53()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo53();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo53_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo53()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo53();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo54() {
        // Setup
        when(mockNullFooProvider.getFoo54()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo54();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo54_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo54()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo54();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo55() {
        // Setup
        when(mockNullFooProvider.getFoo55()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo55();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo55_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo55()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo55();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo56() {
        // Setup
        when(mockNullFooProvider.getFoo56()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo56();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo56_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo56()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo56();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo57() {
        // Setup
        when(mockNullFooProvider.getFoo57()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo57();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo57_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo57()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo57();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo58() {
        // Setup
        when(mockNullFooProvider.getFoo58()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo58();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo58_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo58()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo58();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo59() {
        // Setup
        when(mockNullFooProvider.getFoo59()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo59();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo59_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo59()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo59();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo60() {
        // Setup
        when(mockNullFooProvider.getFoo60()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo60();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo60_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo60()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo60();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo61() {
        // Setup
        when(mockNullFooProvider.getFoo61()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo61();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo61_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo61()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo61();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo62() {
        // Setup
        when(mockNullFooProvider.getFoo62()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo62();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo62_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo62()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo62();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo63() {
        // Setup
        when(mockNullFooProvider.getFoo63()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo63();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo63_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo63()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo63();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo64() {
        // Setup
        when(mockNullFooProvider.getFoo64()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo64();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo64_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo64()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo64();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo65() {
        // Setup
        when(mockNullFooProvider.getFoo65()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo65();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo65_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo65()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo65();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo66() {
        // Setup
        when(mockNullFooProvider.getFoo66()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo66();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo66_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo66()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo66();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo67() {
        // Setup
        when(mockNullFooProvider.getFoo67()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo67();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo67_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo67()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo67();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo68() {
        // Setup
        when(mockNullFooProvider.getFoo68()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo68();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo68_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo68()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo68();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo69() {
        // Setup
        when(mockNullFooProvider.getFoo69()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo69();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo69_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo69()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo69();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo70() {
        // Setup
        when(mockNullFooProvider.getFoo70()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo70();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo70_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo70()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo70();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo71() {
        // Setup
        when(mockNullFooProvider.getFoo71()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo71();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo71_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo71()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo71();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo72() {
        // Setup
        when(mockNullFooProvider.getFoo72()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo72();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo72_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo72()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo72();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo73() {
        // Setup
        when(mockNullFooProvider.getFoo73()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo73();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo73_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo73()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo73();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo74() {
        // Setup
        when(mockNullFooProvider.getFoo74()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo74();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo74_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo74()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo74();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo75() {
        // Setup
        when(mockNullFooProvider.getFoo75()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo75();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo75_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo75()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo75();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo76() {
        // Setup
        when(mockNullFooProvider.getFoo76()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo76();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo76_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo76()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo76();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo77() {
        // Setup
        when(mockNullFooProvider.getFoo77()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo77();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo77_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo77()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo77();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo78() {
        // Setup
        when(mockNullFooProvider.getFoo78()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo78();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo78_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo78()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo78();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo79() {
        // Setup
        when(mockNullFooProvider.getFoo79()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo79();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo79_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo79()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo79();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo80() {
        // Setup
        when(mockNullFooProvider.getFoo80()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo80();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo80_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo80()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo80();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo81() {
        // Setup
        when(mockNullFooProvider.getFoo81()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo81();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo81_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo81()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo81();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo82() {
        // Setup
        when(mockNullFooProvider.getFoo82()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo82();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo82_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo82()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo82();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo83() {
        // Setup
        when(mockNullFooProvider.getFoo83()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo83();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo83_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo83()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo83();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo84() {
        // Setup
        when(mockNullFooProvider.getFoo84()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo84();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo84_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo84()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo84();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo85() {
        // Setup
        when(mockNullFooProvider.getFoo85()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo85();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo85_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo85()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo85();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo86() {
        // Setup
        when(mockNullFooProvider.getFoo86()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo86();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo86_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo86()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo86();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo87() {
        // Setup
        when(mockNullFooProvider.getFoo87()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo87();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo87_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo87()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo87();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo88() {
        // Setup
        when(mockNullFooProvider.getFoo88()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo88();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo88_NullFooProviderReturnsNull() {
        // Setup
        when(mockNullFooProvider.getFoo88()).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo88();

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo89() throws Exception {
        // Setup
        when(mockNullFooProvider.getFoo89(any(byte[].class))).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo89();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo89_NullFooProviderThrowsIOException() throws Exception {
        // Setup
        when(mockNullFooProvider.getFoo89(any(byte[].class))).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getFoo89());
    }
}
