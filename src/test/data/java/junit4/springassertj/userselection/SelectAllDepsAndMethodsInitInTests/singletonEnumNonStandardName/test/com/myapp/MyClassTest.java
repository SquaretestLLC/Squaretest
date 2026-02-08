package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testCreateNewConnection1() throws Exception {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.createNewConnection();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection()).isInstanceOf(IOException.class);
    }

    @Test
    public void testTryCreateNewConnection1() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection();

        // Verify the results
    }

    @Test
    public void testTryCreateNewConnection1_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection()).isInstanceOf(RuntimeException.class);
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

    @Test
    public void testCreateNewConnection2_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", 0)).isInstanceOf(IOException.class);
    }

    @Test
    public void testTryCreateNewConnection2() {
        // Setup
        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", 0);

        // Verify the results
    }

    @Test
    public void testTryCreateNewConnection2_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", 0))
                .isInstanceOf(RuntimeException.class);
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

    @Test
    public void testCreateNewConnection3_ThrowsIOException() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", port)).isInstanceOf(IOException.class);
    }

    @Test
    public void testTryCreateNewConnection3() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        final Socket result = MyClass.ONLYVALUE.tryCreateNewConnection("host", port);

        // Verify the results
    }

    @Test
    public void testTryCreateNewConnection3_ThrowsRuntimeException() {
        // Setup
        final MutableInt port = new MutableInt(0);

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", port))
                .isInstanceOf(RuntimeException.class);
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

    @Test
    public void testCreateNewConnection4_ThrowsIOException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.createNewConnection("host", outSocket))
                .isInstanceOf(IOException.class);
    }

    @Test
    public void testTryCreateNewConnection4() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket);

        // Verify the results
    }

    @Test
    public void testTryCreateNewConnection4_ThrowsRuntimeException() throws Exception {
        // Setup
        final MutableObject<Socket> outSocket = new MutableObject<>(new Socket("host", 80));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket))
                .isInstanceOf(RuntimeException.class);
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

    @Test
    public void testCloseAllConnections1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeAllConnections()).isInstanceOf(IOException.class);
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

    @Test
    public void testCloseAllConnections2_ThrowsIOException() throws Exception {
        // Setup
        final List<MutableObject<Socket>> socketsToClose = Arrays.asList(new MutableObject<>(new Socket("host", 80)));

        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeAllConnections(socketsToClose)).isInstanceOf(IOException.class);
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

    @Test
    public void testCloseConnections_ThrowsIOException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.closeConnections("host")).isInstanceOf(IOException.class);
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
        assertThat(result).isEqualTo("name");
    }

    @Test
    public void testOrdinal() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.ordinal();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup
        // Run the test
        final String result = MyClass.ONLYVALUE.toString();

        // Verify the results
        assertThat(result).isEqualTo("name");
    }

    @Test
    public void testEquals() {
        // Setup
        // Run the test
        final boolean result = MyClass.ONLYVALUE.equals("other");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testHashCode() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCompareTo() {
        // Setup
        // Run the test
        final int result = MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testGetDeclaringClass() {
        // Setup
        // Run the test
        final Class<MyClass> result = MyClass.ONLYVALUE.getDeclaringClass();

        // Verify the results
        assertThat(result).isEqualTo(MyClass.class);
    }

    @Test
    public void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.ONLYVALUE);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(NullPointerException.class);
    }
}
