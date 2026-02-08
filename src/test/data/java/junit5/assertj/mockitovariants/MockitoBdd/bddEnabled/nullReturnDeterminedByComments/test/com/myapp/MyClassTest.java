package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private NullFooProvider mockNullFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockNullFooProvider);
    }

    @Test
    void testGetFoo() {
        // Setup
        given(mockNullFooProvider.getFoo()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo1() {
        // Setup
        given(mockNullFooProvider.getFoo1()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo1_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo1()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo2() {
        // Setup
        given(mockNullFooProvider.getFoo2()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo2_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo2()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo2();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo3() {
        // Setup
        given(mockNullFooProvider.getFoo3()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo3_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo3()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo3();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo4() {
        // Setup
        given(mockNullFooProvider.getFoo4()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo4_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo4()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo4();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo5() {
        // Setup
        given(mockNullFooProvider.getFoo5()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo5_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo5()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo5();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo6() {
        // Setup
        given(mockNullFooProvider.getFoo6()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo6_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo6()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo6();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo7() {
        // Setup
        given(mockNullFooProvider.getFoo7()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo7_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo7()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo7();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo8() {
        // Setup
        given(mockNullFooProvider.getFoo8()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo8_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo8()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo8();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo9() {
        // Setup
        given(mockNullFooProvider.getFoo9()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo9();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo9_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo9()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo9();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo10() {
        // Setup
        given(mockNullFooProvider.getFoo10()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo10();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo10_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo10()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo10();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo11() {
        // Setup
        given(mockNullFooProvider.getFoo11()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo11();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo11_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo11()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo11();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo12() {
        // Setup
        given(mockNullFooProvider.getFoo12()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo12();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo12_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo12()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo12();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo13() {
        // Setup
        given(mockNullFooProvider.getFoo13()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo13();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo13_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo13()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo13();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo14() {
        // Setup
        given(mockNullFooProvider.getFoo14()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo14();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo14_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo14()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo14();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo15() {
        // Setup
        given(mockNullFooProvider.getFoo15()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo15();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo15_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo15()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo15();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo16() {
        // Setup
        given(mockNullFooProvider.getFoo16()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo16();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo16_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo16()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo16();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo17() {
        // Setup
        given(mockNullFooProvider.getFoo17()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo17();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo17_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo17()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo17();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo18() {
        // Setup
        given(mockNullFooProvider.getFoo18()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo18();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo18_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo18()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo18();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo19() {
        // Setup
        given(mockNullFooProvider.getFoo19()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo19();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo19_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo19()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo19();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo20() {
        // Setup
        given(mockNullFooProvider.getFoo20()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo20();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo20_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo20()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo20();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo21() {
        // Setup
        given(mockNullFooProvider.getFoo21()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo21();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo21_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo21()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo21();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo22() {
        // Setup
        given(mockNullFooProvider.getFoo22()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo22();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo22_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo22()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo22();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo23() {
        // Setup
        given(mockNullFooProvider.getFoo23()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo23();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo23_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo23()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo23();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo24() {
        // Setup
        given(mockNullFooProvider.getFoo24()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo24();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo24_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo24()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo24();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo25() {
        // Setup
        given(mockNullFooProvider.getFoo25()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo25();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo25_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo25()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo25();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo26() {
        // Setup
        given(mockNullFooProvider.getFoo26()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo26();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo26_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo26()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo26();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo27() {
        // Setup
        given(mockNullFooProvider.getFoo27()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo27();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo27_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo27()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo27();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo28() {
        // Setup
        given(mockNullFooProvider.getFoo28()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo28();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo28_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo28()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo28();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo29() {
        // Setup
        given(mockNullFooProvider.getFoo29()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo29();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo29_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo29()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo29();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo30() {
        // Setup
        given(mockNullFooProvider.getFoo30()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo30();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo30_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo30()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo30();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo31() {
        // Setup
        given(mockNullFooProvider.getFoo31()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo31();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo31_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo31()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo31();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo32() {
        // Setup
        given(mockNullFooProvider.getFoo32()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo32();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo32_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo32()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo32();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo33() {
        // Setup
        given(mockNullFooProvider.getFoo33()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo33();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo33_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo33()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo33();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo34() {
        // Setup
        given(mockNullFooProvider.getFoo34()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo34();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo34_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo34()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo34();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo35() {
        // Setup
        given(mockNullFooProvider.getFoo35()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo35();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo35_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo35()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo35();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo36() {
        // Setup
        given(mockNullFooProvider.getFoo36()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo36();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo36_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo36()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo36();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo37() {
        // Setup
        given(mockNullFooProvider.getFoo37()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo37();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo37_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo37()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo37();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo38() {
        // Setup
        given(mockNullFooProvider.getFoo38()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo38();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo38_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo38()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo38();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo39() {
        // Setup
        given(mockNullFooProvider.getFoo39()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo39();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo39_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo39()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo39();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo40() {
        // Setup
        given(mockNullFooProvider.getFoo40()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo40();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo40_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo40()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo40();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo41() {
        // Setup
        given(mockNullFooProvider.getFoo41()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo41();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo41_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo41()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo41();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo42() {
        // Setup
        given(mockNullFooProvider.getFoo42()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo42();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo42_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo42()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo42();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo44() {
        // Setup
        given(mockNullFooProvider.getFoo44()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo44();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo44_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo44()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo44();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo45() {
        // Setup
        given(mockNullFooProvider.getFoo45()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo45();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo45_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo45()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo45();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo46() {
        // Setup
        given(mockNullFooProvider.getFoo46()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo46();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo46_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo46()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo46();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo47() {
        // Setup
        given(mockNullFooProvider.getFoo47()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo47();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo47_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo47()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo47();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo48() {
        // Setup
        given(mockNullFooProvider.getFoo48()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo48();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo48_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo48()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo48();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo49() {
        // Setup
        given(mockNullFooProvider.getFoo49()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo49();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo49_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo49()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo49();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo50() {
        // Setup
        given(mockNullFooProvider.getFoo50()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo50();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo50_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo50()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo50();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo51() {
        // Setup
        given(mockNullFooProvider.getFoo51()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo51();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo51_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo51()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo51();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo52() {
        // Setup
        given(mockNullFooProvider.getFoo52()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo52();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo52_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo52()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo52();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo53() {
        // Setup
        given(mockNullFooProvider.getFoo53()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo53();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo53_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo53()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo53();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo54() {
        // Setup
        given(mockNullFooProvider.getFoo54()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo54();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo54_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo54()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo54();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo55() {
        // Setup
        given(mockNullFooProvider.getFoo55()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo55();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo55_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo55()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo55();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo56() {
        // Setup
        given(mockNullFooProvider.getFoo56()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo56();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo56_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo56()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo56();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo57() {
        // Setup
        given(mockNullFooProvider.getFoo57()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo57();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo57_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo57()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo57();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo58() {
        // Setup
        given(mockNullFooProvider.getFoo58()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo58();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo58_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo58()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo58();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo59() {
        // Setup
        given(mockNullFooProvider.getFoo59()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo59();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo59_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo59()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo59();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo60() {
        // Setup
        given(mockNullFooProvider.getFoo60()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo60();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo60_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo60()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo60();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo61() {
        // Setup
        given(mockNullFooProvider.getFoo61()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo61();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo61_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo61()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo61();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo62() {
        // Setup
        given(mockNullFooProvider.getFoo62()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo62();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo62_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo62()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo62();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo63() {
        // Setup
        given(mockNullFooProvider.getFoo63()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo63();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo63_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo63()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo63();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo64() {
        // Setup
        given(mockNullFooProvider.getFoo64()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo64();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo64_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo64()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo64();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo65() {
        // Setup
        given(mockNullFooProvider.getFoo65()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo65();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo65_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo65()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo65();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo66() {
        // Setup
        given(mockNullFooProvider.getFoo66()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo66();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo66_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo66()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo66();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo67() {
        // Setup
        given(mockNullFooProvider.getFoo67()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo67();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo67_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo67()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo67();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo68() {
        // Setup
        given(mockNullFooProvider.getFoo68()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo68();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo68_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo68()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo68();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo69() {
        // Setup
        given(mockNullFooProvider.getFoo69()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo69();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo69_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo69()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo69();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo70() {
        // Setup
        given(mockNullFooProvider.getFoo70()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo70();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo70_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo70()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo70();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo71() {
        // Setup
        given(mockNullFooProvider.getFoo71()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo71();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo71_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo71()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo71();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo72() {
        // Setup
        given(mockNullFooProvider.getFoo72()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo72();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo72_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo72()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo72();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo73() {
        // Setup
        given(mockNullFooProvider.getFoo73()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo73();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo73_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo73()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo73();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo74() {
        // Setup
        given(mockNullFooProvider.getFoo74()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo74();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo74_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo74()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo74();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo75() {
        // Setup
        given(mockNullFooProvider.getFoo75()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo75();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo75_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo75()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo75();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo76() {
        // Setup
        given(mockNullFooProvider.getFoo76()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo76();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo76_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo76()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo76();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo77() {
        // Setup
        given(mockNullFooProvider.getFoo77()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo77();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo77_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo77()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo77();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo78() {
        // Setup
        given(mockNullFooProvider.getFoo78()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo78();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo78_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo78()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo78();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo79() {
        // Setup
        given(mockNullFooProvider.getFoo79()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo79();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo79_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo79()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo79();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo80() {
        // Setup
        given(mockNullFooProvider.getFoo80()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo80();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo80_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo80()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo80();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo81() {
        // Setup
        given(mockNullFooProvider.getFoo81()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo81();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo81_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo81()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo81();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo82() {
        // Setup
        given(mockNullFooProvider.getFoo82()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo82();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo82_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo82()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo82();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo83() {
        // Setup
        given(mockNullFooProvider.getFoo83()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo83();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo83_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo83()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo83();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo84() {
        // Setup
        given(mockNullFooProvider.getFoo84()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo84();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo84_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo84()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo84();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo85() {
        // Setup
        given(mockNullFooProvider.getFoo85()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo85();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo85_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo85()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo85();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo86() {
        // Setup
        given(mockNullFooProvider.getFoo86()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo86();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo86_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo86()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo86();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo87() {
        // Setup
        given(mockNullFooProvider.getFoo87()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo87();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo87_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo87()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo87();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo88() {
        // Setup
        given(mockNullFooProvider.getFoo88()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo88();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo88_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo88()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo88();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo89() throws Exception {
        // Setup
        given(mockNullFooProvider.getFoo89(any(byte[].class))).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo89();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo89_NullFooProviderThrowsIOException() throws Exception {
        // Setup
        given(mockNullFooProvider.getFoo89(any(byte[].class))).willThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo89()).isInstanceOf(IOException.class);
    }

    @Test
    void testGetFoo90() {
        // Setup
        given(mockNullFooProvider.getFoo90()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo90();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo90_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo90()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo90();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo91() {
        // Setup
        given(mockNullFooProvider.getFoo91()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo91();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo91_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo91()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo91();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo92() {
        // Setup
        given(mockNullFooProvider.getFoo92()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo92();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo92_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo92()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo92();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo93() {
        // Setup
        given(mockNullFooProvider.getFoo93()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo93();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo93_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo93()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo93();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo94() {
        // Setup
        given(mockNullFooProvider.getFoo94()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo94();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo94_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo94()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo94();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo95() {
        // Setup
        given(mockNullFooProvider.getFoo95()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo95();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo95_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo95()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo95();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo96() {
        // Setup
        given(mockNullFooProvider.getFoo96()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo96();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo96_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo96()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo96();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo97() {
        // Setup
        given(mockNullFooProvider.getFoo97()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo97();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo97_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo97()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo97();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetFoo98() {
        // Setup
        given(mockNullFooProvider.getFoo98()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo98();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo98_NullFooProviderReturnsNull() {
        // Setup
        given(mockNullFooProvider.getFoo98()).willReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo98();

        // Verify the results
        assertThat(result).isNull();
    }
}
