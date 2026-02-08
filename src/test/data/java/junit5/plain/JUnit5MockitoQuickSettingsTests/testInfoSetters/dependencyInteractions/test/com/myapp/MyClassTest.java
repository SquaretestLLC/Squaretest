package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter);
    }

    // altFoo[MyKey] = MyValue (should be MyValue)
    // altFoo[otherKey] = otherValue (should be otherValue)
    // altFoo[otherKey] = otherValue (should be otherValue)
    @Test
    void testGetAllUsersSync() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
    }

    // altFoo[MyKey] = MyValue (should be MyValue)
    // altFoo[otherKey] = otherValue (should be otherValue)
    // altFoo[otherKey] = otherValue (should be otherValue)
    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
