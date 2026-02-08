package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    void testGetFoos1() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos1_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos1_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos2_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos2_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos3() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos3_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos3_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos4() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos4_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos4_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos5() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos5_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos5_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id", false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos5_FooServiceGetFoos3ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id", false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos6() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos6_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos6_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos6_FooServiceGetFoos3ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos7() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos7_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos7_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos7_FooServiceGetFoos3ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos8() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos8("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos8_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos8("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos8_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos8("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos9() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));

        // Configure FooService.filterSomething(...).
        final Stream<String> spyStream = spy(Stream.of("value"));
        when(mockFooService.filterSomething(any(Stream.class))).thenReturn(spyStream);

        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(spyStream).close();
    }

    @Test
    void testGetFoos9_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Configure FooService.filterSomething(...).
        final Stream<String> spyStream = spy(Stream.of("value"));
        when(mockFooService.filterSomething(any(Stream.class))).thenReturn(spyStream);

        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(spyStream).close();
    }

    @Test
    void testGetFoos9_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Configure FooService.filterSomething(...).
        final Stream<String> spyStream = spy(Stream.of("value"));
        when(mockFooService.filterSomething(any(Stream.class))).thenReturn(spyStream);

        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(spyStream).close();
    }

    @Test
    void testGetFoos9_FooServiceFilterSomethingReturnsNoItem() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));

        // Configure FooService.filterSomething(...).
        final Stream<String> spyStream = spy(Stream.empty());
        when(mockFooService.filterSomething(any(Stream.class))).thenReturn(spyStream);

        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(spyStream).close();
    }

    @Test
    void testGetFoos10() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos10("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos10_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos10("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos10_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos10("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos11() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos11("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos11_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos11("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos11_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getPreferredName1("name")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.getFoos11("id", false, false, false);

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos11_FooServiceGetFoos3ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos3("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos11("id", false, false, false);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
