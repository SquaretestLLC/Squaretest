package com.myapp;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Enumeration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testDoHead() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doHead(req, resp);

        // Verify the results
    }

    @Test
    public void testDoHead_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doHead(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoHead_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doHead(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoGet() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doGet(req, resp);

        // Verify the results
    }

    @Test
    public void testDoGet_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doGet(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoGet_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doGet(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoPost() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPost(req, resp);

        // Verify the results
    }

    @Test
    public void testDoPost_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doPost(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoPost_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doPost(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoDelete() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doDelete(req, resp);

        // Verify the results
    }

    @Test
    public void testDoDelete_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doDelete(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoDelete_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doDelete(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoOptions() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doOptions(req, resp);

        // Verify the results
    }

    @Test
    public void testDoOptions_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doOptions(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoOptions_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doOptions(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoPut() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doPut(req, resp);

        // Verify the results
    }

    @Test
    public void testDoPut_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doPut(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoPut_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doPut(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDoTrace() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.doTrace(req, resp);

        // Verify the results
    }

    @Test
    public void testDoTrace_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doTrace(req, resp)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testDoTrace_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse resp = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doTrace(req, resp)).isInstanceOf(IOException.class);
    }

    @Test
    public void testService() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        myClassUnderTest.service(req, res);

        // Verify the results
    }

    @Test
    public void testService_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.service(req, res)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testService_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockHttpServletRequest req = new MockHttpServletRequest();
        final MockHttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.service(req, res)).isInstanceOf(IOException.class);
    }

    @Test
    public void testDestroy() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.destroy();

        // Verify the results
    }

    @Test
    public void testGetInitParameter() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getInitParameter("name")).isEqualTo("result");
    }

    @Test
    public void testGetInitParameterNames() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final Enumeration<String> result = myClassUnderTest.getInitParameterNames();

        // Verify the results
    }

    @Test
    public void testGetServletConfig() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final MockServletConfig result = myClassUnderTest.getServletConfig();

        // Verify the results
    }

    @Test
    public void testGetServletContext() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final ServletContext result = myClassUnderTest.getServletContext();

        // Verify the results
    }

    @Test
    public void testGetServletInfo() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getServletInfo()).isEqualTo("result");
    }

    @Test
    public void testInit1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockServletConfig config = new MockServletConfig();

        // Run the test
        myClassUnderTest.init(config);

        // Verify the results
    }

    @Test
    public void testInit1_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MockServletConfig config = new MockServletConfig();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.init(config)).isInstanceOf(ServletException.class);
    }

    @Test
    public void testInit2() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.init();

        // Verify the results
    }

    @Test
    public void testInit2_ThrowsServletException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.init()).isInstanceOf(ServletException.class);
    }

    @Test
    public void testLog1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.log("msg");

        // Verify the results
    }

    @Test
    public void testLog2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.log("message", new Exception("message"));

        // Verify the results
    }

    @Test
    public void testGetServletName() {
        final MyClass myClassUnderTest = new MyClass();
        assertThat(myClassUnderTest.getServletName()).isEqualTo("result");
    }
}
