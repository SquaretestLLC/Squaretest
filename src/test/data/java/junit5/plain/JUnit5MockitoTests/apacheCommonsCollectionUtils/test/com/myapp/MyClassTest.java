package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AuthService mockAuthService;
    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAuthService, mockFooService, mockMetricService);
    }

    @Test
    void testGetFoos1() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos1_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos1_FooServiceGetFoos1ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException1.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos1_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(FooServiceException1.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos1_FooServiceGetFoos2ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException2.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos1_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(FooServiceException2.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordListsAreTheSame("criteria");
        verify(mockMetricService).recordListsAreDifferent("criteria");
    }

    @Test
    void testGetFoos2_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos2("userId", "criteria"));
    }

    @Test
    void testGetFoos2_FooServiceGetFoos1ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(null);
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordListsAreTheSame("criteria");
        verify(mockMetricService).recordListsAreDifferent("criteria");
    }

    @Test
    void testGetFoos2_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordListsAreTheSame("criteria");
        verify(mockMetricService).recordListsAreDifferent("criteria");
    }

    @Test
    void testGetFoos2_FooServiceGetFoos2ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(null);

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordListsAreTheSame("criteria");
        verify(mockMetricService).recordListsAreDifferent("criteria");
    }

    @Test
    void testGetFoos2_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordListsAreTheSame("criteria");
        verify(mockMetricService).recordListsAreDifferent("criteria");
    }

    @Test
    void testGetFoos3() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordSomeDuplicates("criteria");
    }

    @Test
    void testGetFoos3_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos3("userId", "criteria"));
    }

    @Test
    void testGetFoos3_FooServiceGetFoos1ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(null);
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos3_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos3_FooServiceGetFoos2ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(null);

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos3_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos4() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordSomeDuplicates("criteria");
    }

    @Test
    void testGetFoos4_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos4("userId", "criteria"));
    }

    @Test
    void testGetFoos4_FooServiceGetFoos1ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(null);
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos4_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos4_FooServiceGetFoos2ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(null);

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos4_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final Collection<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoDuplicates("criteria");
    }

    @Test
    void testGetFoos5() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos5_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }

    @Test
    void testGetFoos5_FooServiceGetFoos1ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException1.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }

    @Test
    void testGetFoos5_FooServiceGetFoos1ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(FooServiceException1.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }

    @Test
    void testGetFoos5_FooServiceGetFoos2ReturnsNull() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException2.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }

    @Test
    void testGetFoos5_FooServiceGetFoos2ReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(FooServiceException2.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }
}
