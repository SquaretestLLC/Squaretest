package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.apache.commons.lang3.mutable.MutableLong
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assertThat(MyClass.PRIMARY.createNewConnection()).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.createNewConnection()).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.createNewConnection()
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.createNewConnection()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection1() {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection()).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.tryCreateNewConnection()).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.PRIMARY.tryCreateNewConnection()
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.tryCreateNewConnection()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection1() {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection()).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.safeCreateNewConnection()).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection2() {
        assertThat(MyClass.PRIMARY.createNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.createNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.createNewConnection("host", 0)
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.createNewConnection("host", 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection2() {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.tryCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.PRIMARY.tryCreateNewConnection("host", 0)
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.tryCreateNewConnection("host", 0)
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection2() {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.safeCreateNewConnection("host", 0)).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection3() {
        assertThat(MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.createNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.createNewConnection("host", new MutableInt(0))
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection3() {
        assertThat(MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))).isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80))
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0))
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection3() {
        assertThat(MyClass.PRIMARY.safeCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80))
        assertThat(MyClass.SECONDARY.safeCreateNewConnection("host", new MutableInt(0)))
                .isEqualTo(new Socket("host", 80))
    }

    @Test
    void testCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.PRIMARY.createNewConnection("host", outSocket)
        MyClass.SECONDARY.createNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testCreateNewConnection4_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.PRIMARY.tryCreateNewConnection("host", outSocket)
        MyClass.SECONDARY.tryCreateNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection4_ThrowsRuntimeException() {
        assertThatThrownBy({
            MyClass.PRIMARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        }).isInstanceOf(RuntimeException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.PRIMARY.safeCreateNewConnection("host", outSocket)
        MyClass.SECONDARY.safeCreateNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testCloseAllConnections1() {
        // Run the test
        MyClass.PRIMARY.closeAllConnections()
        MyClass.SECONDARY.closeAllConnections()

        // Verify the results
    }

    @Test
    void testCloseAllConnections1_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.closeAllConnections()
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.closeAllConnections()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections()
        MyClass.SECONDARY.safeCloseAllConnections()

        // Verify the results
    }

    @Test
    void testCloseAllConnections2() {
        // Setup
        def socketsToClose = [new MutableObject<>(new Socket("host", 80))]

        // Run the test
        MyClass.PRIMARY.closeAllConnections(socketsToClose)
        MyClass.SECONDARY.closeAllConnections(socketsToClose)

        // Verify the results
    }

    @Test
    void testCloseAllConnections2_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.closeAllConnections([new MutableObject<>(new Socket("host", 80))])
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.closeAllConnections([new MutableObject<>(new Socket("host", 80))])
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseAllConnections2() {
        // Setup
        def socketsToClose = [new MutableObject<>(new Socket("host", 80))]

        // Run the test
        MyClass.PRIMARY.safeCloseAllConnections(socketsToClose)
        MyClass.SECONDARY.safeCloseAllConnections(socketsToClose)

        // Verify the results
    }

    @Test
    void testCloseConnections() {
        // Run the test
        MyClass.PRIMARY.closeConnections("host")
        MyClass.SECONDARY.closeConnections("host")

        // Verify the results
    }

    @Test
    void testCloseConnections_ThrowsIOException() {
        assertThatThrownBy({
            MyClass.PRIMARY.closeConnections("host")
        }).isInstanceOf(IOException.class)
        assertThatThrownBy({
            MyClass.SECONDARY.closeConnections("host")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseConnections() {
        // Run the test
        MyClass.PRIMARY.safeCloseConnections("host")
        MyClass.SECONDARY.safeCloseConnections("host")

        // Verify the results
    }

    @Test
    void testGetPrimary() {
        assertThat(MyClass.getPrimary()).isEqualTo(MyClass.PRIMARY)
    }

    @Test
    void testSafeGetHostConnectionCount1() {
        assertThat(MyClass.safeGetHostConnectionCount("host")).isEqualTo(0L)
    }

    @Test
    void testGetHostConnectionCount1() {
        assertThat(MyClass.getHostConnectionCount("host")).isEqualTo(0L)
    }

    @Test
    void testSafeGetHostConnectionCount2() {
        // Setup
        def out = new MutableLong(0L)

        // Run the test
        MyClass.safeGetHostConnectionCount("host", out)

        // Verify the results
    }

    @Test
    void testGetHostConnectionCount2() {
        // Setup
        def out = new MutableLong(0L)

        // Run the test
        MyClass.getHostConnectionCount("host", out)

        // Verify the results
    }

    @Test
    void testGetHostConnectionCount2_ThrowsIOException() {
        // Setup
        def out = new MutableLong(0L)

        // Run the test
        assertThatThrownBy({
            MyClass.getHostConnectionCount("host", out)
        }).isInstanceOf(IOException.class)
    }
}
