package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockServletConfig

import javax.servlet.ServletException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

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
        assertThatThrownBy({
            myClassUnderTest.doHead(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoHead_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doHead(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doGet(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoGet_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doGet(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doPost(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoPost_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doPost(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doDelete(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoDelete_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doDelete(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doOptions(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoOptions_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doOptions(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doPut(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoPut_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doPut(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doTrace(req, resp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoTrace_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def resp = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doTrace(req, resp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.service(req, res)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testService_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        def req = new MockHttpServletRequest()
        def res = new MockHttpServletResponse()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.service(req, res)
        }).isInstanceOf(IOException.class)
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
        assertThat(myClassUnderTest.getInitParameter("name")).isEqualTo("result")
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
        assertThat(myClassUnderTest.getServletInfo()).isEqualTo("result")
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
        assertThatThrownBy({
            myClassUnderTest.init(config)
        }).isInstanceOf(ServletException.class)
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
        assertThatThrownBy({
            myClassUnderTest.init()
        }).isInstanceOf(ServletException.class)
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
        assertThat(myClassUnderTest.getServletName()).isEqualTo("result")
    }
}
