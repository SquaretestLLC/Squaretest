package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id", FooType.Type1);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id", FooType.Type1);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData1_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id", FooType.Type1);

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", "otherId");
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        final FooData result = myClassUnderTest.getFooData3("id", FooType.Type1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testValidateFooData1() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        // Run the test
        myClassUnderTest.validateFooData1("id", FooType.Type1);

        // Verify the results
    }

    @Test
    void testValidateFooData1_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData1("id", FooType.Type1));
    }

    @Test
    void testValidateFooData1_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData1("id", FooType.Type1));
    }

    @Test
    void testValidateFooData1_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.validateFooData1("id", FooType.Type1));
    }

    @Test
    void testValidateFooData2() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Configure FooService.getFooData2(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData2("id")).thenReturn(fooData1);

        // Configure FooService.getFooData3(...).
        final Optional<FooData> fooData2 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData3("id")).thenReturn(fooData2);

        // Configure FooService.getFooData4(...).
        final Optional<FooData> fooData3 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData4("id")).thenReturn(fooData3);

        // Run the test
        myClassUnderTest.validateFooData2("id", FooType.Type1);

        // Verify the results
    }

    @Test
    void testValidateFooData2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData4(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData4("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData2("id", FooType.Type1);

        // Verify the results
    }

    @Test
    void testValidateFooData2_FooServiceGetFooData2ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData2("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData4(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData4("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData2("id", FooType.Type1);

        // Verify the results
    }

    @Test
    void testValidateFooData2_FooServiceGetFooData3ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData3("id")).thenReturn(Optional.empty());

        // Configure FooService.getFooData4(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData4("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.validateFooData2("id", FooType.Type1);

        // Verify the results
    }

    @Test
    void testValidateFooData2_FooServiceGetFooData4ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());
        when(mockFooService.getFooData4("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.validateFooData2("id", FooType.Type1));
    }
}
