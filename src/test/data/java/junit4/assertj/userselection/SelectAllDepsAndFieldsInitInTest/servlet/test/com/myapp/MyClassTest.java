package com.myapp;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

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
}
