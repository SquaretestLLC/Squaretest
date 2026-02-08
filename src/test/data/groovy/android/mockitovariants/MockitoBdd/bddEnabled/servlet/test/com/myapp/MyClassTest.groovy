package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static org.mockito.Mockito.mock

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoHead() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoHead_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoHead_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp)
    }

    @Test
    void testDoGet() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoGet_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoGet_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp)
    }

    @Test
    void testDoPost() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoPost_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoPost_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp)
    }

    @Test
    void testDoDelete() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoDelete_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoDelete_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp)
    }

    @Test
    void testDoOptions() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoOptions_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoOptions_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp)
    }

    @Test
    void testDoPut() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoPut_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoPut_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp)
    }

    @Test
    void testDoTrace() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoTrace_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp)
    }

    @Test(expected = IOException.class)
    void testDoTrace_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp)
    }
}
