package com.myapp

import com.myapp.foos.BooleanBuilder
import com.myapp.foos.User
import com.myapp.foos.UserAdapter
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockUserAdapter)
    }

    @Test
    void testGetAllUsers() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsers()

        // Verify the results
    }

    @Test
    void testGetAllUsers_UserAdapterReturnsNoItems() {
        // Setup
        when(mockUserAdapter.getUsers(new BooleanBuilder(null))).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsers()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetAllSimilarUsers() {
        // Setup
        def user = new User()
        when(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null)))).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllSimilarUsers(user)

        // Verify the results
    }

    @Test
    void testGetAllSimilarUsers_UserAdapterReturnsNoItems() {
        // Setup
        def user = new User()
        when(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null)))).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllSimilarUsers(user)

        // Verify the results
        assert [] == result
    }
}
