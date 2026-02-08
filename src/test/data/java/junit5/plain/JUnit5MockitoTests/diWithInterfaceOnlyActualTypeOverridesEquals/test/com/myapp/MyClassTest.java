package com.myapp;

import com.myapp.foos.BooleanBuilder;
import com.myapp.foos.User;
import com.myapp.foos.UserAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserAdapter);
    }

    @Test
    void testGetAllUsers() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
    }

    @Test
    void testGetAllUsers_UserAdapterReturnsNoItems() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetAllSimilarUsers() {
        // Setup
        final User user = new User();
        when(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null))))
                .thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllSimilarUsers(user);

        // Verify the results
    }

    @Test
    void testGetAllSimilarUsers_UserAdapterReturnsNoItems() {
        // Setup
        final User user = new User();
        when(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null))))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllSimilarUsers(user);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
