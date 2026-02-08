package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private BigDataService mockBigDataService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockBigDataService);
    }

    @Test
    void testGetFooWithId1() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId1_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId1("id"));
    }

    @Test
    void testGetFooWithId1_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId1("id"));
    }

    @Test
    void testGetFooWithId2() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId2_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId2("id"));
    }

    @Test
    void testGetFooWithId2_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId2("id"));
    }

    @Test
    void testGetFooWithId3() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId3_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId3("id"));
    }

    @Test
    void testGetFooWithId3_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId3("id"));
    }

    @Test
    void testGetFooWithId4() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId4_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId4("id"));
    }

    @Test
    void testGetFooWithId4_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId4("id"));
    }

    @Test
    void testGetFooWithId5() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId5_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId5("id"));
    }

    @Test
    void testGetFooWithId5_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId5("id"));
    }

    @Test
    void testGetFooWithId6() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId6_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId6("id"));
    }

    @Test
    void testGetFooWithId6_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId6("id"));
    }

    @Test
    void testGetFooWithId7() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId7("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId7_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId7("id"));
    }

    @Test
    void testGetFooWithId7_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId7("id"));
    }

    @Test
    void testGetFooWithId8() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId8("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId8_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId8("id"));
    }

    @Test
    void testGetFooWithId8_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId8("id"));
    }

    @Test
    void testGetFooWithId9() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId9("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId9_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId9("id"));
    }

    @Test
    void testGetFooWithId9_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId9("id"));
    }

    @Test
    void testGetFooWithId10() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId10("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId10_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId10("id"));
    }

    @Test
    void testGetFooWithId10_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId10("id"));
    }

    @Test
    void testGetFooWithId11() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId11("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId11_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId11("id"));
    }

    @Test
    void testGetFooWithId11_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId11("id"));
    }

    @Test
    void testGetFooWithId12() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("bigData");

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId12("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooWithId12_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId12("id"));
    }

    @Test
    void testGetFooWithId12_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId12("id"));
    }

    @Test
    void testGetFooWithId13() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId13("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockBigDataService).getBigData1("id");
    }

    @Test
    void testGetFooWithId13_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId13("id"));
    }

    @Test
    void testGetFooWithId13_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId13("id"));
    }

    @Test
    void testGetFooWithId14() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId14("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockBigDataService).getBigData1("id");
    }

    @Test
    void testGetFooWithId14_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId14("id"));
    }

    @Test
    void testGetFooWithId14_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId14("id"));
    }

    @Test
    void testGetFooWithId15() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId15("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockBigDataService).getBigData1("id");
    }

    @Test
    void testGetFooWithId15_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId15("id"));
    }

    @Test
    void testGetFooWithId15_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId15("id"));
    }

    @Test
    void testGetFooWithId16() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFooWithId16("id");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockBigDataService).getBigData1("id");
    }

    @Test
    void testGetFooWithId16_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId16("id"));
    }

    @Test
    void testGetFooWithId16_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId16("id"));
    }

    @Test
    void testGetFooWithId17() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooWithId17("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFooWithId17_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId17("id"));
    }

    @Test
    void testGetFooWithId17_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId17("id"));
    }

    @Test
    void testGetFooWithId18() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooWithId18("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFooWithId18_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooWithId18("id"));
    }

    @Test
    void testGetFooWithId18_BigDataServiceThrowsBigDataServiceException() throws Exception {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(new FooData("id", "name"));
        when(mockBigDataService.getBigData1("id")).thenThrow(BigDataServiceException.class);

        // Run the test
        assertThrows(BigDataServiceException.class, () -> myClassUnderTest.getFooWithId18("id"));
    }
}
