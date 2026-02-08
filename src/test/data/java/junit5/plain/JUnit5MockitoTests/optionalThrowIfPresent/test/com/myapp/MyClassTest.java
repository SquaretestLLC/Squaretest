package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
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
    void testStoreFoo1_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Configure FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.saveFoo1(fooData2)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo2_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo2_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Configure FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.saveFoo1(fooData2)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo3_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo3_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Configure FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.saveFoo1(fooData2)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo4_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo4(fooData));
    }

    @Test
    void testStoreFoo4_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Configure FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.saveFoo1(fooData2)).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.storeFoo4(fooData);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testStoreFoo5_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        // Confirm FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).saveFoo1(fooData1);
    }

    @Test
    void testStoreFoo6_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testStoreFoo6_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
        // Confirm FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).saveFoo1(fooData1);
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo7(fooData));
    }

    @Test
    void testStoreFoo7_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo7(fooData);

        // Verify the results
        // Confirm FooService.saveFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).saveFoo1(fooData1);
    }

    @Test
    void testUpdateFoo1() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.updateFoo1(fooData);

        // Verify the results
        // Confirm FooService.updateFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        verify(mockFooService).updateFoo1(fooData3);
    }

    @Test
    void testUpdateFoo1_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.updateFoo1(fooData));
    }

    @Test
    void testUpdateFoo2() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.updateFoo2(fooData);

        // Verify the results
        // Confirm FooService.updateFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        verify(mockFooService).updateFoo1(fooData3);
    }

    @Test
    void testUpdateFoo2_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.updateFoo2(fooData));
    }

    @Test
    void testUpdateFoo3() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.updateFoo3(fooData);

        // Verify the results
        // Confirm FooService.updateFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        verify(mockFooService).updateFoo1(fooData3);
    }

    @Test
    void testUpdateFoo3_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.updateFoo3(fooData));
    }

    @Test
    void testUpdateFoo4() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.getFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        final Optional<FooData> fooData1 = Optional.of(fooData2);
        when(mockFooService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        myClassUnderTest.updateFoo4(fooData);

        // Verify the results
        // Confirm FooService.updateFoo1(...).
        final FooData fooData3 = new FooData();
        fooData3.setId("id");
        fooData3.setName("name");
        verify(mockFooService).updateFoo1(fooData3);
    }

    @Test
    void testUpdateFoo4_FooServiceGetFoo1ReturnsAbsent() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.updateFoo4(fooData));
    }
}
