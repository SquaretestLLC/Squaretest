package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private GroupService mockGroupService;
    @Mock
    private PermissionService mockPermissionService;
    @Mock
    private DataService mockDataService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockGroupService, mockPermissionService, mockDataService);
    }

    @Test
    void testGetData1() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData1("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData1_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData1("userId"));
    }

    @Test
    void testGetData2() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData2("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData2_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData2("userId"));
    }

    @Test
    void testGetData3() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData3("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData3_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData3("userId"));
    }

    @Test
    void testGetData4() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData4("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData4_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData4("userId"));
    }

    @Test
    void testGetData5() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData5("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData5_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData5("userId"));
    }

    @Test
    void testGetData6() {
        // Setup
        when(mockPermissionService.getAllowedGroups()).thenReturn(List.of("value"));
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");
        when(mockDataService.getData()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData6("userId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData6_PermissionServiceReturnsNoItems() {
        // Setup
        when(mockPermissionService.getAllowedGroups()).thenReturn(Collections.emptyList());
        when(mockGroupService.getGroupIdForUser("userId")).thenReturn("result");

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getData6("userId"));
    }
}
