package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static org.mockito.Mockito.mock

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoHead_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoGet_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoPost_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoDelete_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoOptions_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoPut_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
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

    @Test(expectedExceptions = [ServletException.class])
    void testDoTrace_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoTrace_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp)
    }
}
