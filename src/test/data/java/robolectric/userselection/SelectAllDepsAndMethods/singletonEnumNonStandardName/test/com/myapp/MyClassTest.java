package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testCreateNewConnection1() throws Exception {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection1_ThrowsIOException() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.createNewConnection();
    }

    @Test
    public void testTryCreateNewConnection1() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection();

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection1_ThrowsRuntimeException() {
        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection();
    }

    @Test
    public void testSafeCreateNewConnection1() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection2() throws Exception {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection("host", 0);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection2_ThrowsIOException() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", 0);
    }

    @Test
    public void testTryCreateNewConnection2() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", 0);

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection2_ThrowsRuntimeException() {
        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", 0);
    }

    @Test
    public void testSafeCreateNewConnection2() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection("host", 0);

        // Verify the results
    }

    @Test
    public void testCreateNewConnection3() throws Exception {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection("host", port);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection3_ThrowsIOException() throws Exception {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", port);
    }

    @Test
    public void testTryCreateNewConnection3() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", port);

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection3_ThrowsRuntimeException() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", port);
    }

    @Test
    public void testSafeCreateNewConnection3() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection("host", port);

        // Verify the results
    }

    @Test
    public void testCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", outSocket);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateNewConnection4_ThrowsIOException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", outSocket);
    }

    @Test
    public void testTryCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    public void testTryCreateNewConnection4_ThrowsRuntimeException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket);
    }

    @Test
    public void testSafeCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.safeCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    public void testCloseAllConnections1() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeAllConnections();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseAllConnections1_ThrowsIOException() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeAllConnections();
    }

    @Test
    public void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections();

        // Verify the results
    }

    @Test
    public void testCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.ONLYVALUE.closeAllConnections(socketsToClose);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseAllConnections2_ThrowsIOException() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.ONLYVALUE.closeAllConnections(socketsToClose);
    }

    @Test
    public void testSafeCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    public void testCloseConnections() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeConnections("host");

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCloseConnections_ThrowsIOException() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeConnections("host");
    }

    @Test
    public void testSafeCloseConnections() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseConnections("host");

        // Verify the results
    }

    @Test
    public void testName() {
        // Setup
        // Run the test
        final String result = MyClass.ONLYVALUE.name();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testOrdinal() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.ordinal();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testToString() {
        // Setup
        // Run the test
        final String result = MyClass.ONLYVALUE.toString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testEquals() {
        // Setup
        // Run the test
        final boolean result = MyClass.ONLYVALUE.equals("other");

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testHashCode() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.hashCode();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testCompareTo() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetDeclaringClass() {
        // Setup
        // Run the test
        final Class<MyClass> result = MyClass.ONLYVALUE.getDeclaringClass();

        // Verify the results
        assertEquals(MyClass.class, result);
    }

    @Test
    public void testValueOf() {
        assertEquals(MyClass.ONLYVALUE, Enum.valueOf(MyClass.class, "name"));
    }
}
