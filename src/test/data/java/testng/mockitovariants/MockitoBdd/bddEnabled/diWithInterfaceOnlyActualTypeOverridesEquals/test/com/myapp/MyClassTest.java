package com.myapp;

import com.myapp.foos.BooleanBuilder;
import com.myapp.foos.User;
import com.myapp.foos.UserAdapter;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserAdapter);
    }

    @Test
    public void testGetAllUsers() {
        // Setup
        given(mockUserAdapter.getUsers(new BooleanBuilder(null))).willReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
    }

    @Test
    public void testGetAllUsers_UserAdapterReturnsNoItems() {
        // Setup
        given(mockUserAdapter.getUsers(new BooleanBuilder(null))).willReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetAllSimilarUsers() {
        // Setup
        final User user = new User();
        given(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null))))
                .willReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllSimilarUsers(user);

        // Verify the results
    }

    @Test
    public void testGetAllSimilarUsers_UserAdapterReturnsNoItems() {
        // Setup
        final User user = new User();
        given(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null))))
                .willReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllSimilarUsers(user);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
