package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.foos.BooleanBuilder
import com.myapp.foos.User
import com.myapp.foos.UserAdapter
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockUserAdapter)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
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
