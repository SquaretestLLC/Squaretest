package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse

import javax.servlet.ServletException

import static org.junit.jupiter.api.Assertions.assertThrows

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
}
