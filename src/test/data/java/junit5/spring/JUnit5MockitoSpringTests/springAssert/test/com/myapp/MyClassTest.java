package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private ErrorMessageProvider mockErrorMessageProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockErrorMessageProvider);
    }

    @Test
    void testStoreFoo1_ThrowsIllegalArgumentException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo1_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo2_ThrowsIllegalArgumentException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo2_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo2_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo3_ThrowsIllegalArgumentException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo3_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo3_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo3(fooData));
    }

    @Test
    void testStoreFoo4_ThrowsIllegalStateException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.storeFoo4(fooData));
    }

    @Test
    void testStoreFoo4_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo4_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo4(fooData));
    }

    @Test
    void testStoreFoo5_ThrowsIllegalStateException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo5_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo6_ThrowsIllegalStateException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getAlreadyExistsMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalStateException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testStoreFoo6_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
        verify(mockFooService).storeFoo(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo6_FooServiceStoreFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo1("id"));
    }

    @Test
    void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo3_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo3("id"));
    }

    @Test
    void testGetFoo4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo4_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoo4("id"));
    }

    @Test
    void testGetFoo5() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        final FooData result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo5_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoo5("id"));
    }

    @Test
    void testGetFoo6() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));

        // Run the test
        final FooData result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo6_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockErrorMessageProvider.getFooNotFoundMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoo6("id"));
    }

    @Test
    void testGetFoos2() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(null);

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos2("id"));
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos2("id"));
    }

    @Test
    void testGetFoos3() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList(new FooData("id", "name")));
        when(mockErrorMessageProvider.getEmptyFoosMessage("id")).thenReturn("result");

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos3_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(null);
        when(mockErrorMessageProvider.getEmptyFoosMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos3("id"));
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getEmptyFoosMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos3("id"));
    }

    @Test
    void testGetFoos4() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList(new FooData("id", "name")));

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoos4_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(null);
        when(mockErrorMessageProvider.getEmptyFoosMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos4("id"));
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());
        when(mockErrorMessageProvider.getEmptyFoosMessage("id")).thenReturn("result");

        // Run the test
        assertThrows(IllegalArgumentException.class, () -> myClassUnderTest.getFoos4("id"));
    }
}
