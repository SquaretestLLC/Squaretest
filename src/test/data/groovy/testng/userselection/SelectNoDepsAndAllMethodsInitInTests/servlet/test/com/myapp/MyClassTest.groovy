package com.myapp

import groovy.transform.CompileStatic
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockServletConfig
import org.testng.annotations.Test

import javax.servlet.ServletException

@CompileStatic
class MyClassTest {

    @Test
    void testDoHead() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoHead_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoHead_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doHead(req, resp)
    }

    @Test
    void testDoGet() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoGet_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoGet_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doGet(req, resp)
    }

    @Test
    void testDoPost() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoPost_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoPost_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPost(req, resp)
    }

    @Test
    void testDoDelete() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoDelete_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoDelete_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doDelete(req, resp)
    }

    @Test
    void testDoOptions() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoOptions_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoOptions_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doOptions(req, resp)
    }

    @Test
    void testDoPut() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoPut_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoPut_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doPut(req, resp)
    }

    @Test
    void testDoTrace() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testDoTrace_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoTrace_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.doTrace(req, resp)
    }

    @Test
    void testService() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testService_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)
    }

    @Test(expectedExceptions = [IOException.class])
    void testService_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        myClassUnderTest.service(req, res)
    }

    @Test
    void testDestroy() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.destroy()

        // Verify the results
    }

    @Test
    void testGetInitParameter() {
        def myClassUnderTest = new MyClass()
        assert "result" == myClassUnderTest.getInitParameter("name")
    }

    @Test
    void testGetInitParameterNames() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getInitParameterNames()

        // Verify the results
    }

    @Test
    void testGetServletConfig() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getServletConfig()

        // Verify the results
    }

    @Test
    void testGetServletContext() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getServletContext()

        // Verify the results
    }

    @Test
    void testGetServletInfo() {
        def myClassUnderTest = new MyClass()
        assert "result" == myClassUnderTest.getServletInfo()
    }

    @Test
    void testInit1() {
        // Setup
        def myClassUnderTest = new MyClass()
        def config = new MockServletConfig()

        // Run the test
        myClassUnderTest.init(config)

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testInit1_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def config = new MockServletConfig()

        // Run the test
        myClassUnderTest.init(config)
    }

    @Test
    void testInit2() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.init()

        // Verify the results
    }

    @Test(expectedExceptions = [ServletException.class])
    void testInit2_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.init()
    }

    @Test
    void testLog1() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.log("msg")

        // Verify the results
    }

    @Test
    void testLog2() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.log("message", new Exception("message"))

        // Verify the results
    }

    @Test
    void testGetServletName() {
        def myClassUnderTest = new MyClass()
        assert "result" == myClassUnderTest.getServletName()
    }
}
