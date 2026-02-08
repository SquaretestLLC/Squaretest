package com.myapp

import groovy.transform.CompileStatic
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import javax.servlet.ServletException

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
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoHead_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoHead_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)
    }

    @Test
    void testDoGet() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoGet_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoGet_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)
    }

    @Test
    void testDoPost() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoPost_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoPost_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)
    }

    @Test
    void testDoDelete() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoDelete_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoDelete_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)
    }

    @Test
    void testDoOptions() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoOptions_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoOptions_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)
    }

    @Test
    void testDoPut() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoPut_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoPut_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)
    }

    @Test
    void testDoTrace() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoTrace_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoTrace_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }
}
