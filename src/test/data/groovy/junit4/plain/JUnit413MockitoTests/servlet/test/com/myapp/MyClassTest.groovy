package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static org.junit.Assert.assertThrows
import static org.mockito.Mockito.mock

@CompileStatic
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

    @Test
    void testDoHead_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doHead(mockReq, mockResp)
        })
    }

    @Test
    void testDoHead_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doHead(mockReq, mockResp)
        })
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

    @Test
    void testDoGet_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doGet(mockReq, mockResp)
        })
    }

    @Test
    void testDoGet_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doGet(mockReq, mockResp)
        })
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

    @Test
    void testDoPost_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doPost(mockReq, mockResp)
        })
    }

    @Test
    void testDoPost_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doPost(mockReq, mockResp)
        })
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

    @Test
    void testDoDelete_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doDelete(mockReq, mockResp)
        })
    }

    @Test
    void testDoDelete_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doDelete(mockReq, mockResp)
        })
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

    @Test
    void testDoOptions_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doOptions(mockReq, mockResp)
        })
    }

    @Test
    void testDoOptions_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doOptions(mockReq, mockResp)
        })
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

    @Test
    void testDoPut_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doPut(mockReq, mockResp)
        })
    }

    @Test
    void testDoPut_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doPut(mockReq, mockResp)
        })
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

    @Test
    void testDoTrace_ThrowsServletException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doTrace(mockReq, mockResp)
        })
    }

    @Test
    void testDoTrace_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doTrace(mockReq, mockResp)
        })
    }
}
