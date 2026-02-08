package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.util.Streamable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private FooConverter mockFooConverter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockFooConverter);
    }

    @Test
    void testGetFooData() {
        // Setup
        when(mockFooService.getItems("query")).thenReturn(new PageImpl<>(Arrays.asList("value")));
        when(mockFooConverter.convert("data")).thenReturn(new FooData());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData("query");

        // Verify the results
    }

    @Test
    void testGetFooData_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("query")).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooData1() {
        // Setup
        when(mockFooService.getItem("query")).thenReturn(new FooData());

        // Run the test
        final Streamable<FooData> result = myClassUnderTest.getFooData1("query");

        // Verify the results
    }

    @Test
    void testGetFooData1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getItem("query")).thenReturn(null);

        // Run the test
        final Streamable<FooData> result = myClassUnderTest.getFooData1("query");

        // Verify the results
    }

    @Test
    void testGetFooData2() {
        // Setup
        when(mockFooService.getItemLazy("query")).thenReturn(() -> Stream.of(new FooData()));

        // Run the test
        final Streamable<FooData> result = myClassUnderTest.getFooData2("query");

        // Verify the results
    }

    @Test
    void testGetFooData2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getItemLazy("query")).thenReturn(null);

        // Run the test
        final Streamable<FooData> result = myClassUnderTest.getFooData2("query");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFooItems1() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems1("query");

        // Verify the results
    }

    @Test
    void testGetFooItems1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems1("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems2() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooService.isValid(any(FooData.class))).thenReturn(true);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems2("query");

        // Verify the results
    }

    @Test
    void testGetFooItems2_FooServiceGetFooItemsReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems2("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems2_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooService.isValid(any(FooData.class))).thenReturn(false);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems2("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems3() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooService.getAlternateName(any(FooData.class))).thenReturn("result");

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems3("query");

        // Verify the results
    }

    @Test
    void testGetFooItems3_FooServiceGetFooItemsReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems3("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems4() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooConverter.convert(any(FooData.class))).thenReturn(new OtherData());

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems4("query");

        // Verify the results
    }

    @Test
    void testGetFooItems4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems4("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems4_FooConverterReturnsNull() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooConverter.convert(any(FooData.class))).thenReturn(null);

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems4("query");

        // Verify the results
    }

    @Test
    void testGetFooItems5() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooConverter.convert(any(FooData.class))).thenReturn(new OtherData());

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems5("query");

        // Verify the results
    }

    @Test
    void testGetFooItems5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems5("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems5_FooConverterReturnsNull() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooConverter.convert(any(FooData.class))).thenReturn(null);

        // Run the test
        final List<OtherData> result = myClassUnderTest.getFooItems5("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems6() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooService.isValid(any(FooData.class))).thenReturn(true);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems6("query");

        // Verify the results
    }

    @Test
    void testGetFooItems6_FooServiceGetFooItemsReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems6("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems6_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));
        when(mockFooService.isValid(any(FooData.class))).thenReturn(false);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFooItems6("query"));
    }

    @Test
    void testGetFooItems7() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems7("query");

        // Verify the results
    }

    @Test
    void testGetFooItems7_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooItems7("query");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooItems8() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFooItems8("query");

        // Verify the results
    }

    @Test
    void testGetFooItems8_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final Set<FooData> result = myClassUnderTest.getFooItems8("query");

        // Verify the results
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    void testGetFooItems9() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));

        // Run the test
        final Stream<FooData> result = myClassUnderTest.getFooItems9("query");

        // Verify the results
    }

    @Test
    void testGetFooItems9_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final Stream<FooData> result = myClassUnderTest.getFooItems9("query");

        // Verify the results
    }

    @Test
    void testGetFooItems10() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Arrays.asList(new FooData()));

        // Run the test
        final boolean result = myClassUnderTest.getFooItems10("query");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testGetFooItems10_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFooItems("query")).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = myClassUnderTest.getFooItems10("query");

        // Verify the results
        assertTrue(result);
    }
}
