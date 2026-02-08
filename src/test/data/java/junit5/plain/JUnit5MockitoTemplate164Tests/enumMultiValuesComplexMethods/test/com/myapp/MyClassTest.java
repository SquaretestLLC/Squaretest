package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableLong;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    @Test
    void testCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection());
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        assertThrows(IOException.class, () -> MyClass.PRIMARY.createNewConnection());
        assertThrows(IOException.class, () -> MyClass.SECONDARY.createNewConnection());
    }

    @Test
    void testTryCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection());
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MyClass.PRIMARY.tryCreateNewConnection());
        assertThrows(RuntimeException.class, () -> MyClass.SECONDARY.tryCreateNewConnection());
    }

    @Test
    void testSafeCreateNewConnection1() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection());
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection());
    }

    @Test
    void testCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection("host", 0));
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        assertThrows(IOException.class, () -> MyClass.PRIMARY.createNewConnection("host", 0));
        assertThrows(IOException.class, () -> MyClass.SECONDARY.createNewConnection("host", 0));
    }

    @Test
    void testTryCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection("host", 0));
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MyClass.PRIMARY.tryCreateNewConnection("host", 0));
        assertThrows(RuntimeException.class, () -> MyClass.SECONDARY.tryCreateNewConnection("host", 0));
    }

    @Test
    void testSafeCreateNewConnection2() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection("host", 0));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection("host", 0));
    }

    @Test
    void testCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.createNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.createNewConnection("host", new MutableInt(0)));
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        assertThrows(IOException.class, () -> MyClass.PRIMARY.createNewConnection("host", new MutableInt(0)));
        assertThrows(IOException.class, () -> MyClass.SECONDARY.createNewConnection("host", new MutableInt(0)));
    }

    @Test
    void testTryCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)));
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0)));
        assertThrows(RuntimeException.class, () -> MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)));
    }

    @Test
    void testSafeCreateNewConnection3() throws Exception {
        assertEquals(new Socket("host", 80), MyClass.PRIMARY.safeCreateNewConnection("host", new MutableInt(0)));
        assertEquals(new Socket("host", 80), MyClass.SECONDARY.safeCreateNewConnection("host", new MutableInt(0)));
    }

    @Test
    void testCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.createNewConnection("host", outSocket);
        MyClass.SECONDARY.createNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testCreateNewConnection4_ThrowsIOException() {
        assertThrows(IOException.class,
                () -> MyClass.PRIMARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80))));
        assertThrows(IOException.class,
                () -> MyClass.SECONDARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80))));
    }

    @Test
    void testTryCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.tryCreateNewConnection("host", outSocket);
        MyClass.SECONDARY.tryCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection4_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class,
                () -> MyClass.PRIMARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80))));
        assertThrows(RuntimeException.class,
                () -> MyClass.SECONDARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80))));
    }

    @Test
    void testSafeCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.PRIMARY.safeCreateNewConnection("host", outSocket);
        MyClass.SECONDARY.safeCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testCloseAllConnections1() throws Exception {
        // Run the test
        MyClass.PRIMARY.closeAllConnections();
        MyClass.SECONDARY.closeAllConnections();

        // Verify the results
    }

    @Test
    void testCloseAllConnections1_ThrowsIOException() {
        assertThrows(IOException.class, () -> MyClass.PRIMARY.closeAllConnections());
        assertThrows(IOException.class, () -> MyClass.SECONDARY.closeAllConnections());
    }

    @Test
    void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections();
        MyClass.SECONDARY.safeCloseAllConnections();

        // Verify the results
    }

    @Test
    void testCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.PRIMARY.closeAllConnections(socketsToClose);
        MyClass.SECONDARY.closeAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    void testCloseAllConnections2_ThrowsIOException() {
        assertThrows(IOException.class,
                () -> MyClass.PRIMARY.closeAllConnections(Arrays.asList(new MutableObject<>(new Socket("host", 80)))));
        assertThrows(IOException.class, () -> MyClass.SECONDARY.closeAllConnections(
                Arrays.asList(new MutableObject<>(new Socket("host", 80)))));
    }

    @Test
    void testSafeCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections(socketsToClose);
        MyClass.SECONDARY.safeCloseAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    void testCloseConnections() throws Exception {
        // Run the test
        MyClass.PRIMARY.closeConnections("host");
        MyClass.SECONDARY.closeConnections("host");

        // Verify the results
    }

    @Test
    void testCloseConnections_ThrowsIOException() {
        assertThrows(IOException.class, () -> MyClass.PRIMARY.closeConnections("host"));
        assertThrows(IOException.class, () -> MyClass.SECONDARY.closeConnections("host"));
    }

    @Test
    void testSafeCloseConnections() {
        // Run the test
        MyClass.PRIMARY.safeCloseConnections("host");
        MyClass.SECONDARY.safeCloseConnections("host");

        // Verify the results
    }

    @Test
    void testGetPrimary() {
        assertEquals(MyClass.PRIMARY, MyClass.getPrimary());
    }

    @Test
    void testSafeGetHostConnectionCount1() {
        assertEquals(0L, MyClass.safeGetHostConnectionCount("host"));
    }

    @Test
    void testGetHostConnectionCount1() throws Exception {
        assertEquals(0L, MyClass.getHostConnectionCount("host"));
        assertThrows(IOException.class, () -> MyClass.getHostConnectionCount("host"));
    }

    @Test
    void testSafeGetHostConnectionCount2() {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        MyClass.safeGetHostConnectionCount("host", out);

        // Verify the results
    }

    @Test
    void testGetHostConnectionCount2() throws Exception {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        MyClass.getHostConnectionCount("host", out);

        // Verify the results
    }

    @Test
    void testGetHostConnectionCount2_ThrowsIOException() {
        // Setup
        final MutableLong out = new MutableLong(0L);

        // Run the test
        assertThrows(IOException.class, () -> MyClass.getHostConnectionCount("host", out));
    }
}
