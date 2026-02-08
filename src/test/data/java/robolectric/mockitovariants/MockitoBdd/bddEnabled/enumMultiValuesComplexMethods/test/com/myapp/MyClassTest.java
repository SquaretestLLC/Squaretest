package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableLong;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection());
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection1_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.createNewConnection();
    }

    @Test
    public void testTryCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection());
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection1_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection();
    }

    @Test
    public void testSafeCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection());
    }

    @Test
    public void testCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection("host", 0));
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection2_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.createNewConnection("host", 0);
    }

    @Test
    public void testTryCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection("host", 0));
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection2_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection("host", 0);
    }

    @Test
    public void testSafeCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection("host", 0));
    }

    @Test
    public void testCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection("host", new MutableInt(0)));
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection3_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.createNewConnection("host", new MutableInt(0));
    }

    @Test
    public void testTryCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)));
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection3_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0));
    }

    @Test
    public void testSafeCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection("host", new MutableInt(0)));
    }

    @Test
    public void testCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.createNewConnection("host", outSocket);
        MyClass.SECONDARY.createNewConnection("host", outSocket);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection4_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)));
    }

    @Test
    public void testTryCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.tryCreateNewConnection("host", outSocket);
        MyClass.SECONDARY.tryCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection4_ThrowsRuntimeException() throws Exception {
        MyClass.PRIMARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)));
    }

    @Test
    public void testSafeCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.safeCreateNewConnection("host", outSocket);
        MyClass.SECONDARY.safeCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    public void testCloseAllConnections1() throws Exception {
        // Run the test
        MyClass.PRIMARY.closeAllConnections();
        MyClass.SECONDARY.closeAllConnections();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseAllConnections1_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.closeAllConnections();
    }

    @Test
    public void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections();
        MyClass.SECONDARY.safeCloseAllConnections();

        // Verify the results
    }

    @Test
    public void testCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.PRIMARY.closeAllConnections(socketsToClose);
        MyClass.SECONDARY.closeAllConnections(socketsToClose);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseAllConnections2_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.closeAllConnections(Arrays.asList(new MutableObject<>(new Socket("host", 80))));
    }

    @Test
    public void testSafeCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections(socketsToClose);
        MyClass.SECONDARY.safeCloseAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    public void testCloseConnections() throws Exception {
        // Run the test
        MyClass.PRIMARY.closeConnections("host");
        MyClass.SECONDARY.closeConnections("host");

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseConnections_ThrowsIOException() throws Exception {
        MyClass.PRIMARY.closeConnections("host");
    }

    @Test
    public void testSafeCloseConnections() {
        // Run the test
        MyClass.PRIMARY.safeCloseConnections("host");
        MyClass.SECONDARY.safeCloseConnections("host");

        // Verify the results
    }

    @Test
    public void testGetPrimary() {
        assertEquals(MyClass.PRIMARY, MyClass.getPrimary());
    }

    @Test
    public void testSafeGetHostConnectionCount1() {
        assertEquals(0L, MyClass.safeGetHostConnectionCount("host"));
    }

    @Test
    public void testGetHostConnectionCount1() throws Exception {
        assertEquals(0L, MyClass.getHostConnectionCount("host"));
    }

    @Test
    public void testSafeGetHostConnectionCount2() {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        MyClass.safeGetHostConnectionCount("host", out);

        // Verify the results
    }

    @Test
    public void testGetHostConnectionCount2() throws Exception {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        MyClass.getHostConnectionCount("host", out);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testGetHostConnectionCount2_ThrowsIOException() throws Exception {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        MyClass.getHostConnectionCount("host", out);
    }
}
