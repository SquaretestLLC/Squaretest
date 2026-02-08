package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricService);
    }

    @Test
    void testGetFoos1() {
        // Setup
        when(mockFooService.getStartingValue1("id")).thenReturn(0);
        when(mockFooService.getEndingValue1("id")).thenReturn(0);
        when(mockFooService.getNextValue1("id")).thenReturn(0);

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos2_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos2_FooServiceGetEndingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos2_FooServiceGetNextValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos3() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos3("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos3_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos3("id"));
    }

    @Test
    void testGetFoos3_FooServiceGetEndingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos3("id"));
    }

    @Test
    void testGetFoos3_FooServiceGetNextValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos3("id"));
    }

    @Test
    void testGetFoos4() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos4_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos4_FooServiceGetEndingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos4_FooServiceGetNextValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos5() throws Exception {
        // Setup
        when(mockFooService.getStartingValue3("id")).thenReturn(0);
        when(mockFooService.getEndingValue3("id")).thenReturn(0);
        when(mockFooService.getNextValue3("id")).thenReturn(0);

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos5_FooServiceGetStartingValue3ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue3("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos5("id"));
    }

    @Test
    void testGetFoos5_FooServiceGetEndingValue3ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue3("id")).thenReturn(0);
        when(mockFooService.getEndingValue3("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos5("id"));
    }

    @Test
    void testGetFoos5_FooServiceGetNextValue3ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue3("id")).thenReturn(0);
        when(mockFooService.getEndingValue3("id")).thenReturn(0);
        when(mockFooService.getNextValue3("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos5("id"));
    }

    @Test
    void testGetFoos6() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue4("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos6_FooServiceGetStartingValue4ReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.empty());
        when(mockFooService.getEndingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue4("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos6_FooServiceGetStartingValue4ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos6("id"));
    }

    @Test
    void testGetFoos6_FooServiceGetEndingValue4ReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue4("id")).thenReturn(Optional.empty());
        when(mockFooService.getNextValue4("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos6_FooServiceGetEndingValue4ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue4("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos6("id"));
    }

    @Test
    void testGetFoos6_FooServiceGetNextValue4ReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue4("id")).thenReturn(Optional.empty());

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos6_FooServiceGetNextValue4ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue4("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue4("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoos6("id"));
    }

    @Test
    void testGetFoos7() {
        // Setup
        when(mockFooService.getStartingValue1("id")).thenReturn(0);
        when(mockFooService.getNextValue1("id")).thenReturn(0);

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos7("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos8() {
        // Setup
        when(mockFooService.getStartingValue1("id")).thenReturn(0);

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos8("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos9() {
        // Setup
        when(mockFooService.getStartingValue1("id")).thenReturn(0);

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos9("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos10() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos10("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos10_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getStartingValue5("id")).thenReturn(0);
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos10("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos10_FooServiceGetEndingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.empty());
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos10("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }

    @Test
    void testGetFoos10_FooServiceGetNextValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getEndingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        final List<Integer> result = myClassUnderTest.getFoos10("id");

        // Verify the results
        assertEquals(List.of(0), result);
        verify(mockMetricService).recordAddingValue(0);
        verify(mockMetricService).recordFinishedAddingValues("id");
    }
}
