package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
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
    void testDeleteFoo1() {
        // Setup
        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo1("id");

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo1_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFoo1("id");

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo2() {
        // Setup
        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo2("id");

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFoo2("id"));
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo3() {
        // Setup
        when(mockFooService.deleteFoos2("id")).thenReturn("result");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo3("id");

        // Verify the results
    }

    @Test
    void testDeleteFoo3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.deleteFoos2("id")).thenReturn("result");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFoo3("id"));
    }

    @Test
    void testDeleteFoo4() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo4("id");

        // Verify the results
    }

    @Test
    void testDeleteFoo4_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo4("id");

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFoo4("id"));
    }

    @Test
    void testDeleteFoo5() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData);

        // Run the test
        myClassUnderTest.deleteFoo5("id");

        // Verify the results
    }

    @Test
    void testDeleteFoo5_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFoo5("id");

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFoo5_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFoo5("id"));
    }

    @Test
    void testDeleteFirstFoo1() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo1(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo1_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo1(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo1_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo1(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFirstFoo1_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo1(FooType.Type1));
    }

    @Test
    void testDeleteFirstFoo2() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo2(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo2_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo2(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo2_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo2(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo2_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo2(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo3() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo3(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo3_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo3(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo3_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo3(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo3_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo3(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo4() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo4(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo4_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo4(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo4_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo4(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFirstFoo4_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo4(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo5() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo5(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo5_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo5(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo5_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo5(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo5_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo5(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo6() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo6(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo6_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo6(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo6_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo6(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo6_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo6(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo7() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo7(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
    }

    @Test
    void testDeleteFirstFoo7_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo7(FooType.Type1);

        // Verify the results
    }

    @Test
    void testDeleteFirstFoo7_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo7(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFirstFoo7_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo7(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo8() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo8(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo8_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo8(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo8_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo8(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo8_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo8(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo9() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo9(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo9_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo9(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo9_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo9(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo9_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo9(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo10() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo10(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo10_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo10(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo10_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo10(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo10_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo10(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo11() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo11(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo11_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo11(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo11_FooServiceCanDelete1ReturnsFailure() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Run the test
        myClassUnderTest.deleteFirstFoo11(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos1("id");
    }

    @Test
    void testDeleteFirstFoo11_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo11(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo12() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo12(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo12_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo12(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo12_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo12(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo12_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo12(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo12_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo12(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo13() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo13(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo13_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo13(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo13_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo13(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo13_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo13(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo13_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo13(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo14() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo14(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo14_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo14(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo14_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo14(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo14_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Run the test
        myClassUnderTest.deleteFirstFoo14(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo14_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo14(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo15() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo15(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo15_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo15(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo15_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo15(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo15_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Run the test
        myClassUnderTest.deleteFirstFoo15(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo15_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo15(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo16() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo16(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo16_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo16(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo16_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo16(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo16_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Run the test
        myClassUnderTest.deleteFirstFoo16(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo16_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo16(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo17() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo17(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo17_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo17(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo17_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo17(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo17_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Run the test
        myClassUnderTest.deleteFirstFoo17(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
    }

    @Test
    void testDeleteFirstFoo17_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo17(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }

    @Test
    void testDeleteFirstFoo18() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo18(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo18_FooServiceGetFoos2ReturnsNoItems() throws Exception {
        // Setup
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.deleteFirstFoo18(FooType.Type1);

        // Verify the results
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo18_FooServiceCanDelete1ReturnsFailure() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);

        // Configure FooService.getFooData1(...).
        final Optional<FooData> fooData1 = Optional.of(new FooData("id", "name", "otherId"));
        when(mockFooService.getFooData1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.deleteFirstFoo18(FooType.Type1);

        // Verify the results
        verify(mockFooService).deleteFoos3("id");
        verify(mockMetricService).recordBottomOfDoWhile("id");
        verify(mockMetricService).recordEndOfForeachLoop("id");
        verify(mockMetricService).recordAfterForeachLoop(FooType.Type1);
    }

    @Test
    void testDeleteFirstFoo18_FooServiceDeleteFoos3ThrowsFooServiceException() throws Exception {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).deleteFoos3("id");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.deleteFirstFoo18(FooType.Type1));
        verify(mockMetricService).recordFooServiceException(eq("id"), any(FooServiceException.class));
    }

    @Test
    void testDeleteFirstFoo18_FooServiceGetFooData1ReturnsAbsent() {
        // Setup
        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", "otherId"));
        when(mockFooService.getFoos2(FooType.Type1)).thenReturn(fooData);

        when(mockFooService.canDelete1("id")).thenReturn(false);
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.deleteFirstFoo18(FooType.Type1));
        verify(mockMetricService).recordBottomOfDoWhile("id");
    }
}
