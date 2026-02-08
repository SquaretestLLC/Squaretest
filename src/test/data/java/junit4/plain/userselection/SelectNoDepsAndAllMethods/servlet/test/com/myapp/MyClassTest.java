package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoHead() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doHead(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoHead_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doHead(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoHead_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doHead(req, resp);
    }

    @Test
    public void testDoGet() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doGet(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoGet_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doGet(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoGet_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doGet(req, resp);
    }

    @Test
    public void testDoPost() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPost(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoPost_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPost(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoPost_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPost(req, resp);
    }

    @Test
    public void testDoDelete() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doDelete(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoDelete_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doDelete(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoDelete_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doDelete(req, resp);
    }

    @Test
    public void testDoOptions() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doOptions(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoOptions_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doOptions(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoOptions_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doOptions(req, resp);
    }

    @Test
    public void testDoPut() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPut(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoPut_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPut(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoPut_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPut(req, resp);
    }

    @Test
    public void testDoTrace() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doTrace(req, resp);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testDoTrace_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doTrace(req, resp);
    }

    @Test(expected = IOException.class)
    public void testDoTrace_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doTrace(req, resp);
    }

    @Test
    public void testService() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.service(req, res);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testService_ThrowsServletException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.service(req, res);
    }

    @Test(expected = IOException.class)
    public void testService_ThrowsIOException() throws Exception {
        // Setup
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.service(req, res);
    }

    @Test
    public void testDestroy() {
        // Setup
        // Run the test
        myClassUnderTest.destroy();

        // Verify the results
    }

    @Test
    public void testGetInitParameter() {
        assertEquals("result", myClassUnderTest.getInitParameter("name"));
    }

    @Test
    public void testGetInitParameterNames() {
        // Setup
        // Run the test
        final Enumeration<String> result = myClassUnderTest.getInitParameterNames();

        // Verify the results
    }

    @Test
    public void testGetServletConfig() {
        // Setup
        // Run the test
        final MockServletConfig result = myClassUnderTest.getServletConfig();

        // Verify the results
    }

    @Test
    public void testGetServletContext() {
        // Setup
        // Run the test
        final ServletContext result = myClassUnderTest.getServletContext();

        // Verify the results
    }

    @Test
    public void testGetServletInfo() {
        assertEquals("result", myClassUnderTest.getServletInfo());
    }

    @Test
    public void testInit1() throws Exception {
        // Setup
        final MockServletConfig config = new MockServletConfig();

        // Run the test
        myClassUnderTest.init(config);

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testInit1_ThrowsServletException() throws Exception {
        // Setup
        final MockServletConfig config = new MockServletConfig();

        // Run the test
        myClassUnderTest.init(config);
    }

    @Test
    public void testInit2() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.init();

        // Verify the results
    }

    @Test(expected = ServletException.class)
    public void testInit2_ThrowsServletException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.init();
    }

    @Test
    public void testLog1() {
        // Setup
        // Run the test
        myClassUnderTest.log("msg");

        // Verify the results
    }

    @Test
    public void testLog2() {
        // Setup
        // Run the test
        myClassUnderTest.log("message", new Exception("message"));

        // Verify the results
    }

    @Test
    public void testGetServletName() {
        assertEquals("result", myClassUnderTest.getServletName());
    }
}
