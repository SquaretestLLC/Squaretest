package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoHead() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoHead_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoHead_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doHead(mockReq, mockResp);
    }

    @Test
    public void testDoGet() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoGet_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoGet_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doGet(mockReq, mockResp);
    }

    @Test
    public void testDoPost() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoPost_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoPost_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPost(mockReq, mockResp);
    }

    @Test
    public void testDoDelete() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoDelete_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoDelete_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doDelete(mockReq, mockResp);
    }

    @Test
    public void testDoOptions() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoOptions_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoOptions_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doOptions(mockReq, mockResp);
    }

    @Test
    public void testDoPut() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoPut_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoPut_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doPut(mockReq, mockResp);
    }

    @Test
    public void testDoTrace() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp);

        // Verify the results
    }

    @Test(expectedExceptions = {ServletException.class})
    public void testDoTrace_ThrowsServletException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoTrace_ThrowsIOException() throws Exception {
        // Setup
        final HttpServletRequest mockReq = mock(HttpServletRequest.class);
        final HttpServletResponse mockResp = mock(HttpServletResponse.class);

        // Run the test
        myClassUnderTest.doTrace(mockReq, mockResp);
    }
}
