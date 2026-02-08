package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    private FooService mockAltFooService;
    @Mock
    private OtherDataService mockOtherDataService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockAltFooService, mockOtherDataService, mockMetricService);
    }

    @Test
    void testGetFoosInCat1() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat1(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat1(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat2() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat2(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat2(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat3() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory2(...).
        final Optional<List<FooData>> fooData = Optional.of(
                Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId")));
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat3(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat3_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(Optional.empty());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat3(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat3(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat4() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory2(...).
        final Optional<List<FooData>> fooData = Optional.of(
                Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId")));
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(fooData);

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData1 = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData1);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat4(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat4_FooServiceReturnsAbsent() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(Optional.empty());

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat4(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat4_FooServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(Optional.of(Collections.emptyList()));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat4(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat4_AltFooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoosInCategory2(...).
        final Optional<List<FooData>> fooData = Optional.of(
                Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId")));
        when(mockFooService.getFoosInCategory2(Category.Cat1)).thenReturn(fooData);

        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat4(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat5() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData1 = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData1);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat5(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat5_FooServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat5(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat5_AltFooServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat5(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat6() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat6(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat6(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat7() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat7(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat7_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat7(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat8() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat8(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat8_FooServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat8(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat8_AltFooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat8(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat9() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat9(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat9_FooServiceReturnsNoItems() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat9(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat9_AltFooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());
        when(mockAltFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat9(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat10() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat10(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat10_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat10(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat11() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Configure OtherDataService.getOtherDataById(...).
        final OtherData otherData = new OtherData("id", "content".getBytes());
        when(mockOtherDataService.getOtherDataById("otherDataId")).thenReturn(otherData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat11(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetFoosInCat11_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoosInCat11(Category.Cat1);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetValidFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", Category.Cat1, "otherDataId");

        // Configure FooService.getFoosInCategory1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", Category.Cat1, "otherDataId"));
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getValidFoo1(Category.Cat1);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockMetricService).recordEndOfMethod();
    }

    @Test
    void testGetValidFoo1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoosInCategory1(Category.Cat1)).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getValidFoo1(Category.Cat1));
    }
}
