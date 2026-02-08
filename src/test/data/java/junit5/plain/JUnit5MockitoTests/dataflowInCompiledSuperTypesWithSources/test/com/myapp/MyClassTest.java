package com.myapp;

import com.squaretest.supertypes.base.df.BarService;
import com.squaretest.supertypes.base.df.data1.BarDTO1;
import com.squaretest.supertypes.base.df.data1.BarResponse1;
import com.squaretest.supertypes.base.df.data2.BarResponse2;
import com.squaretest.supertypes.base.df.data3.BarDTO3;
import com.squaretest.supertypes.base.df.data3.BarResponse3;
import com.squaretest.supertypes.base.df.data4.BarDTO4;
import com.squaretest.supertypes.base.df.data4.BarResponse4;
import com.squaretest.supertypes.base.df.data5.BarDTO5;
import com.squaretest.supertypes.base.df.data5.BarResponse5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private BarService mockBarService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockBarService);
    }

    @Test
    void testGetBar1() {
        // Setup
        final BarDTO1 expectedResult = new BarDTO1();
        expectedResult.setId(0L);
        expectedResult.setBarDTO1Name("barDTO1Name");

        // Configure BarService.getBar1(...).
        final BarResponse1 barResponse1 = new BarResponse1();
        barResponse1.setId(0L);
        barResponse1.setBarResponse1Name("barDTO1Name");
        when(mockBarService.getBar1("myClassGetBarParam1")).thenReturn(barResponse1);

        // Run the test
        final BarDTO1 result = myClassUnderTest.getBar1("myClassGetBarParam1");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBar2Name() {
        // Setup
        // Configure BarService.getBar2(...).
        final BarResponse2 barResponse2 = new BarResponse2();
        barResponse2.setId(0L);
        barResponse2.setBarResponse2Name("DefaultBar2ResponseName");
        when(mockBarService.getBar2("myClassGetBar2NameParam")).thenReturn(barResponse2);

        // Run the test
        final String result = myClassUnderTest.getBar2Name("myClassGetBar2NameParam");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetBar3() {
        // Setup
        final BarDTO3 expectedResult = new BarDTO3(0L, "barDTO3Name");

        // Configure BarService.getBar3(...).
        final BarResponse3 barResponse3 = new BarResponse3();
        barResponse3.setId(0L);
        barResponse3.setBarResponse3Name("barDTO3Name");
        when(mockBarService.getBar3("myClassGetBar3NameParam")).thenReturn(barResponse3);

        // Run the test
        final BarDTO3 result = myClassUnderTest.getBar3("myClassGetBar3NameParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBar4() {
        // Setup
        final BarDTO4 expectedResult = new BarDTO4(0L, "barDTO4Name");

        // Configure BarService.getBar4(...).
        final BarResponse4 barResponse4 = new BarResponse4();
        barResponse4.setId(0L);
        barResponse4.setBarResponse4Name("barDTO4Name");
        when(mockBarService.getBar4("myClassGetBar4NameParam")).thenReturn(barResponse4);

        // Run the test
        final BarDTO4 result = myClassUnderTest.getBar4("myClassGetBar4NameParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetBar5() {
        // Setup
        final BarDTO5 expectedResult = new BarDTO5(0L, "barResponse5Name");
        when(mockBarService.getBar5("myClassGetBar5NameParam")).thenReturn(new BarResponse5(0L, "barResponse5Name"));

        // Run the test
        final BarDTO5 result = myClassUnderTest.getBar5("myClassGetBar5NameParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetMyClassId() {
        assertEquals("result", myClassUnderTest.getMyClassId());
    }

    @Test
    void testGetTheString() {
        assertEquals("result", myClassUnderTest.getTheString("myClassGetTheStringParam"));
    }
}
