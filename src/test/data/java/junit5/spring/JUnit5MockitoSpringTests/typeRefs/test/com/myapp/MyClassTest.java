package com.myapp;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private CustomObjectMapper mockCustomObjectMapper;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockCustomObjectMapper);
    }

    @Test
    void testGetData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getData1("id", new ParameterizedTypeReference<FooData>() {}))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {}))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {}))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData4() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {}))
                .thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {})).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getData4("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetData5() {
        // Setup
        final Map<String, List<FooData>> expectedResult = new HashMap<>();
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {})).thenReturn(new HashMap<>());

        // Run the test
        final Map<String, List<FooData>> result = myClassUnderTest.getData5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData6() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getData1("id", new ParameterizedTypeReference<>() {})).thenReturn(null);

        // Run the test
        final FooData result = myClassUnderTest.getData6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData7() {
        // Setup
        when(mockFooService.getData1("id", new ParameterizedTypeReference<String>() {})).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData7("id", new ParameterizedTypeReference<String>() {});

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData9() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockCustomObjectMapper.convertValue(eq("dataJson"), any(TypeReference.class)))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData9("dataJson");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData10() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockCustomObjectMapper.convertValue(eq("dataJson"), any(TypeReference.class)))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData10("dataJson");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData11() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockCustomObjectMapper.convertValue(eq("dataJson"), any(TypeReference.class)))
                .thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getData11("dataJson");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData12() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockCustomObjectMapper.convertValue(eq("dataJson"), any(TypeReference.class)))
                .thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getData12("dataJson");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetData12_CustomObjectMapperReturnsNoItems() {
        // Setup
        when(mockCustomObjectMapper.convertValue(eq("dataJson"), any(TypeReference.class)))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getData12("dataJson");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetData13() {
        // Setup
        when(mockCustomObjectMapper.convertValue(eq("id"), any(TypeReference.class))).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData13("id", new TypeReference<String>() {});

        // Verify the results
        assertEquals("result", result);
    }
}
