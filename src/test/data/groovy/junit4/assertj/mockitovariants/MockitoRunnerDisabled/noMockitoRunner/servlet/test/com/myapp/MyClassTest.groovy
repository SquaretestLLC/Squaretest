package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static org.assertj.core.api.Assertions.assertThatThrownBy
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
        assertThatThrownBy({
            myClassUnderTest.doHead(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoHead_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doHead(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doGet(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoGet_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doGet(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doPost(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoPost_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doPost(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doDelete(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoDelete_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doDelete(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doOptions(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoOptions_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doOptions(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doPut(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoPut_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doPut(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
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
        assertThatThrownBy({
            myClassUnderTest.doTrace(mockReq, mockResp)
        }).isInstanceOf(ServletException.class)
    }

    @Test
    void testDoTrace_ThrowsIOException() {
        // Setup
        def mockReq = mock(HttpServletRequest.class)
        def mockResp = mock(HttpServletResponse.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doTrace(mockReq, mockResp)
        }).isInstanceOf(IOException.class)
    }
}
