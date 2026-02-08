package com.myapp

import groovy.transform.CompileStatic
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockServletConfig
import org.testng.annotations.Test

import javax.servlet.ServletException

import static org.testng.Assert.assertThrows

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

    @Test
    void testDoHead_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doHead(req, resp)
        })
    }

    @Test
    void testDoHead_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doHead(req, resp)
        })
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

    @Test
    void testDoGet_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doGet(req, resp)
        })
    }

    @Test
    void testDoGet_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doGet(req, resp)
        })
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

    @Test
    void testDoPost_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doPost(req, resp)
        })
    }

    @Test
    void testDoPost_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doPost(req, resp)
        })
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

    @Test
    void testDoDelete_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doDelete(req, resp)
        })
    }

    @Test
    void testDoDelete_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doDelete(req, resp)
        })
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

    @Test
    void testDoOptions_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doOptions(req, resp)
        })
    }

    @Test
    void testDoOptions_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doOptions(req, resp)
        })
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

    @Test
    void testDoPut_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doPut(req, resp)
        })
    }

    @Test
    void testDoPut_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doPut(req, resp)
        })
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

    @Test
    void testDoTrace_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.doTrace(req, resp)
        })
    }

    @Test
    void testDoTrace_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doTrace(req, resp)
        })
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

    @Test
    void testService_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.service(req, res)
        })
    }

    @Test
    void testService_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.service(req, res)
        })
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

    @Test
    void testInit1_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def config = new MockServletConfig()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.init(config)
        })
    }

    @Test
    void testInit2() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.init()

        // Verify the results
    }

    @Test
    void testInit2_ThrowsServletException() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        assertThrows(ServletException.class, {
            myClassUnderTest.init()
        })
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
