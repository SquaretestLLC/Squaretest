package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.foos.BooleanBuilder
import com.myapp.foos.User
import com.myapp.foos.UserAdapter
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private UserAdapter mockUserAdapter

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockUserAdapter)
    }

    @Test
    void testGetAllUsers() {
        // Setup
        given(mockUserAdapter.getUsers(new BooleanBuilder(null))).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsers()

        // Verify the results
    }

    @Test
    void testGetAllUsers_UserAdapterReturnsNoItems() {
        // Setup
        given(mockUserAdapter.getUsers(new BooleanBuilder(null))).willReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsers()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetAllSimilarUsers() {
        // Setup
        def user = new User()
        given(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null)))).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllSimilarUsers(user)

        // Verify the results
    }

    @Test
    void testGetAllSimilarUsers_UserAdapterReturnsNoItems() {
        // Setup
        def user = new User()
        given(mockUserAdapter.getSimilarUsers(any(User.class), eq(new BooleanBuilder(null)))).willReturn([])

        // Run the test
        def result = myClassUnderTest.getAllSimilarUsers(user)

        // Verify the results
        assert [] == result
    }
}
