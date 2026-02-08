package com.myapp;

import com.myapp.foos.BooleanBuilder;
import com.myapp.foos.User;
import com.myapp.foos.UserAdapter;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(mockUserAdapter);
    }

    @Test
    public void testGetAllUsers() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
    }

    @Test
    public void testGetAllUsers_UserAdapterReturnsNoItems() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsers();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetAllSimilarUsers() {
        // Setup
        final User user = new User();
        when(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null))))
                .thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllSimilarUsers(user);

        // Verify the results
    }

    @Test
    public void testGetAllSimilarUsers_UserAdapterReturnsNoItems() {
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
