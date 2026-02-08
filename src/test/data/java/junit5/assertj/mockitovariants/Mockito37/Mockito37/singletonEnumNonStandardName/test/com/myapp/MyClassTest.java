package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    @Test
    void testCreateNewConnection1() throws Exception {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection();

        // Verify the results
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection()).isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection1() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection();

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection1() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection();

        // Verify the results
    }

    @Test
    void testCreateNewConnection2() throws Exception {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection("host", 0);

        // Verify the results
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection2() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", 0);

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", 0))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection2() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection("host", 0);

        // Verify the results
    }

    @Test
    void testCreateNewConnection3() throws Exception {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection("host", port);

        // Verify the results
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", port)).isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection3() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", port);

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", port))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection3() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.safeCreateNewConnection("host", port);

        // Verify the results
    }

    @Test
    void testCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testCreateNewConnection4_ThrowsIOException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", outSocket))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection4_ThrowsRuntimeException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.safeCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    void testCloseAllConnections1() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeAllConnections();

        // Verify the results
    }

    @Test
    void testCloseAllConnections1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeAllConnections()).isInstanceOf(IOException.class);
    }

    @Test
    void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections();

        // Verify the results
    }

    @Test
    void testCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.ONLYVALUE.closeAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    void testCloseAllConnections2_ThrowsIOException() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeAllConnections(socketsToClose)).isInstanceOf(IOException.class);
    }

    @Test
    void testSafeCloseAllConnections2() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections(socketsToClose);

        // Verify the results
    }

    @Test
    void testCloseConnections() throws Exception {
        // Run the test
        MyClass.ONLYVALUE.closeConnections("host");

        // Verify the results
    }

    @Test
    void testCloseConnections_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeConnections("host")).isInstanceOf(IOException.class);
    }

    @Test
    void testSafeCloseConnections() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseConnections("host");

        // Verify the results
    }
}
