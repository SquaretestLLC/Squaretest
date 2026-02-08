package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableLong;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    @Test
    void testCreateNewConnection1() throws Exception {
        assertThat(MyClass.PRIMARY.createNewConnection()).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.createNewConnection()).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.createNewConnection()).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.createNewConnection()).isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection1() throws Exception {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection()).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.tryCreateNewConnection()).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.tryCreateNewConnection()).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.tryCreateNewConnection()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection1() throws Exception {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection()).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.safeCreateNewConnection()).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testCreateNewConnection2() throws Exception {
        assertThat(MyClass.PRIMARY.createNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.createNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.createNewConnection("host", 0)).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.createNewConnection("host", 0)).isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection2() throws Exception {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.tryCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.tryCreateNewConnection("host", 0))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.tryCreateNewConnection("host", 0))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection2() throws Exception {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.safeCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testCreateNewConnection3() throws Exception {
        assertThat(MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.createNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80));
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.createNewConnection("host", new MutableInt(0)))
                .isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.createNewConnection("host", new MutableInt(0)))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testTryCreateNewConnection3() throws Exception {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80));
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        assertThatThrownBy(() -> MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testSafeCreateNewConnection3() throws Exception {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80));
        assertThat(MyClass.SECONDARY.safeCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80));
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
        assertThatThrownBy(() -> MyClass.PRIMARY.createNewConnection("host",
                new MutableObject<>(new Socket("host", 80)))).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.createNewConnection("host",
                new MutableObject<>(new Socket("host", 80)))).isInstanceOf(IOException.class);
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
        assertThatThrownBy(() -> MyClass.PRIMARY.tryCreateNewConnection("host",
                new MutableObject<>(new Socket("host", 80)))).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.tryCreateNewConnection("host",
                new MutableObject<>(new Socket("host", 80)))).isInstanceOf(RuntimeException.class);
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
        assertThatThrownBy(() -> MyClass.PRIMARY.closeAllConnections()).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.closeAllConnections()).isInstanceOf(IOException.class);
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
        assertThatThrownBy(() -> MyClass.PRIMARY.closeAllConnections(
                Arrays.asList(new MutableObject<>(new Socket("host", 80))))).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.closeAllConnections(
                Arrays.asList(new MutableObject<>(new Socket("host", 80))))).isInstanceOf(IOException.class);
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
        assertThatThrownBy(() -> MyClass.PRIMARY.closeConnections("host")).isInstanceOf(IOException.class);
        assertThatThrownBy(() -> MyClass.SECONDARY.closeConnections("host")).isInstanceOf(IOException.class);
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
        assertThat(MyClass.getPrimary()).isEqualTo(MyClass.PRIMARY);
    }

    @Test
    void testSafeGetHostConnectionCount1() {
        assertThat(MyClass.safeGetHostConnectionCount("host")).isEqualTo(0L);
    }

    @Test
    void testGetHostConnectionCount1() throws Exception {
        assertThat(MyClass.getHostConnectionCount("host")).isEqualTo(0L);
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
        assertThatThrownBy(() -> MyClass.getHostConnectionCount("host", out)).isInstanceOf(IOException.class);
    }
}
