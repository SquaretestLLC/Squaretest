package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockServletConfig

import javax.servlet.ServletException

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
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testDoHead_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoGet_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoPost_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoDelete_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoOptions_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoPut_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)
    }

    @Test(expected = IOException.class)
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

    @Test(expected = ServletException.class)
    void testDoTrace_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }

    @Test(expected = IOException.class)
    void testDoTrace_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }

    @Test
    void testService() {
        // Setup
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testService_ThrowsServletException() {
        // Setup
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)
    }

    @Test(expected = IOException.class)
    void testService_ThrowsIOException() {
        // Setup
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)
    }

    @Test
    void testDestroy() {
        // Setup
        // Run the test
        myClassUnderTest.destroy()

        // Verify the results
    }

    @Test
    void testGetInitParameter() {
        assert "result" == myClassUnderTest.getInitParameter("name")
    }

    @Test
    void testGetInitParameterNames() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getInitParameterNames()

        // Verify the results
    }

    @Test
    void testGetServletConfig() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getServletConfig()

        // Verify the results
    }

    @Test
    void testGetServletContext() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getServletContext()

        // Verify the results
    }

    @Test
    void testGetServletInfo() {
        assert "result" == myClassUnderTest.getServletInfo()
    }

    @Test
    void testInit1() {
        // Setup
        def config = new MockServletConfig()

        // Run the test
        myClassUnderTest.init(config)

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testInit1_ThrowsServletException() {
        // Setup
        def config = new MockServletConfig()

        // Run the test
        myClassUnderTest.init(config)
    }

    @Test
    void testInit2() {
        // Setup
        // Run the test
        myClassUnderTest.init()

        // Verify the results
    }

    @Test(expected = ServletException.class)
    void testInit2_ThrowsServletException() {
        // Setup
        // Run the test
        myClassUnderTest.init()
    }

    @Test
    void testLog1() {
        // Setup
        // Run the test
        myClassUnderTest.log("msg")

        // Verify the results
    }

    @Test
    void testLog2() {
        // Setup
        // Run the test
        myClassUnderTest.log("message", new Exception("message"))

        // Verify the results
    }

    @Test
    void testGetServletName() {
        assert "result" == myClassUnderTest.getServletName()
    }
}
