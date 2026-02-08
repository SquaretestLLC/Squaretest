package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, "fooBucketName");
    }

    @Test
    void testGetFooById() {
        // Setup
        when(mockFooService.getFooById("literalId")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById();

        // Verify the results
    }

    @Test
    void testGetFooById2() {
        // Setup
        when(mockFooService.getFooById2("FooParamValue")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById2();

        // Verify the results
    }

    @Test
    void testGetFooById3() {
        // Setup
        when(mockFooService.getFooById3("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById3("id");

        // Verify the results
    }

    @Test
    void testGetFooById4() {
        // Setup
        when(mockFooService.getFooById4("FooParamValue4other")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById4();

        // Verify the results
    }

    @Test
    void testGetFooById5() {
        // Setup
        when(mockFooService.getFooById5("AltParamConstant")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById5();

        // Verify the results
    }

    @Test
    void testGetFooById6() {
        // Setup
        when(mockFooService.getFooById6("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById6();

        // Verify the results
    }

    @Test
    void testGetFooById7() {
        // Setup
        when(mockFooService.getFooById7("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById7("id");

        // Verify the results
    }

    @Test
    void testGetFooById8() {
        // Setup
        when(mockFooService.getFooById8("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById8("id");

        // Verify the results
    }

    @Test
    void testGetFooById9() {
        // Setup
        when(mockFooService.getFooById9("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById9("id");

        // Verify the results
    }

    @Test
    void testGetFooById10() {
        // Setup
        when(mockFooService.getFooById10("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById10("id");

        // Verify the results
    }

    @Test
    void testGetFooById11() {
        // Setup
        when(mockFooService.getFooById11("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById11("id");

        // Verify the results
    }

    @Test
    void testGetFooById12() {
        // Setup
        when(mockFooService.getFooById12("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById12("id");

        // Verify the results
    }

    @Test
    void testGetFooById13() {
        // Setup
        when(mockFooService.getFooById13("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById13("id");

        // Verify the results
    }

    @Test
    void testGetFooById14() {
        // Setup
        when(mockFooService.getFooById14("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById14("id");

        // Verify the results
    }

    @Test
    void testGetFooById16() {
        // Setup
        when(mockFooService.getFooById13("id")).thenReturn(new Foo("id", "name"));
        when(mockFooService.getFooById16("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById16("id");

        // Verify the results
    }

    @Test
    void testGetFooById17() {
        // Setup
        when(mockFooService.getFooById17("fooBucketName")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById17("id");

        // Verify the results
    }

    @Test
    void testGetFooById18() {
        // Setup
        when(mockFooService.getFooById18("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById18("id");

        // Verify the results
    }

    @Test
    void testGetFooById19() {
        // Setup
        when(mockFooService.getFooById19("OtherClassConstantValue")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById19();

        // Verify the results
    }

    @Test
    void testGetFooById20() {
        // Setup
        when(mockFooService.getFooById20("id")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById20();

        // Verify the results
    }

    @Test
    void testGetFooById21() {
        // Setup
        when(mockFooService.getFooById21("OtherClassConstantValue")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById21();

        // Verify the results
    }

    @Test
    void testGetFooById22() {
        // Setup
        when(mockFooService.getFooById22("OtherClassCircular2")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById22();

        // Verify the results
    }

    @Test
    void testGetFooById23() {
        // Setup
        when(mockFooService.getFooById23("<?xml version=\"1.0\"?>")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById23();

        // Verify the results
    }

    @Test
    void testGetFooById24() {
        // Setup
        when(mockFooService.getFooById24("<?xml version=\"1.0\"?>")).thenReturn(new Foo("id", "name"));

        // Run the test
        final Foo result = myClassUnderTest.getFooById24();

        // Verify the results
    }
}
