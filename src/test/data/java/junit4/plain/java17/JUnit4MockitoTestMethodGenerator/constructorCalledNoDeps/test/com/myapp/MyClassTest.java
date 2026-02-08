package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Logger theMockLogger;

    private MyClass myClass;

    @Before
    public void setUp() {
        myClass = new MyClass();
        myClass.logger = theMockLogger;
    }

    @Test
    public void testGetAllUsersSync1() {
        // Setup
        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync1() {
        // Setup
        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        final User result = myClass.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync3() {
        // Setup
        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync()");
    }

    @Test
    public void testGetUserWithIdAsync11() {
        // Setup
        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()");
    }

    @Test
    public void testGetUserWithIdAsync21() {
        // Setup
        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()");
    }
}