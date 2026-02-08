package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.createNewConnection()

        // Verify the results
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.createNewConnection()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection1() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.tryCreateNewConnection()

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.tryCreateNewConnection()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection1() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.safeCreateNewConnection()

        // Verify the results
    }

    @Test
    void testCreateNewConnection2() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.createNewConnection("host", 0)

        // Verify the results
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.createNewConnection("host", 0)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection2() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.tryCreateNewConnection("host", 0)

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.tryCreateNewConnection("host", 0)
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection2() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.safeCreateNewConnection("host", 0)

        // Verify the results
    }

    @Test
    void testCreateNewConnection3() {
        // Setup
        def port = new MutableInt(0)

        // Run the test
        def result = MyClass.ONLYVALUE.createNewConnection("host", port)

        // Verify the results
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        // Setup
        def port = new MutableInt(0)

        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.createNewConnection("host", port)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection3() {
        // Setup
        def port = new MutableInt(0)

        // Run the test
        def result = MyClass.ONLYVALUE.tryCreateNewConnection("host", port)

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        // Setup
        def port = new MutableInt(0)

        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.tryCreateNewConnection("host", port)
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection3() {
        // Setup
        def port = new MutableInt(0)

        // Run the test
        def result = MyClass.ONLYVALUE.safeCreateNewConnection("host", port)

        // Verify the results
    }

    @Test
    void testCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.ONLYVALUE.createNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testCreateNewConnection4_ThrowsIOException() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.createNewConnection("host", outSocket)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testTryCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testTryCreateNewConnection4_ThrowsRuntimeException() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket)
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCreateNewConnection4() {
        // Setup
        def outSocket = new MutableObject<>(new Socket("host", 80))

        // Run the test
        MyClass.ONLYVALUE.safeCreateNewConnection("host", outSocket)

        // Verify the results
    }

    @Test
    void testCloseAllConnections1() {
        // Run the test
        MyClass.ONLYVALUE.closeAllConnections()

        // Verify the results
    }

    @Test
    void testCloseAllConnections1_ThrowsIOException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.closeAllConnections()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseAllConnections1() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections()

        // Verify the results
    }

    @Test
    void testCloseAllConnections2() {
        // Setup
        def socketsToClose = [new MutableObject<>(new Socket("host", 80))]

        // Run the test
        MyClass.ONLYVALUE.closeAllConnections(socketsToClose)

        // Verify the results
    }

    @Test
    void testCloseAllConnections2_ThrowsIOException() {
        // Setup
        def socketsToClose = [new MutableObject<>(new Socket("host", 80))]

        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.closeAllConnections(socketsToClose)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseAllConnections2() {
        // Setup
        def socketsToClose = [new MutableObject<>(new Socket("host", 80))]

        // Run the test
        MyClass.ONLYVALUE.safeCloseAllConnections(socketsToClose)

        // Verify the results
    }

    @Test
    void testCloseConnections() {
        // Run the test
        MyClass.ONLYVALUE.closeConnections("host")

        // Verify the results
    }

    @Test
    void testCloseConnections_ThrowsIOException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.closeConnections("host")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testSafeCloseConnections() {
        // Run the test
        MyClass.ONLYVALUE.safeCloseConnections("host")

        // Verify the results
    }

    @Test
    void testName() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.name()

        // Verify the results
        assertThat(result).isEqualTo("name")
    }

    @Test
    void testOrdinal() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.ordinal()

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testToString() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.toString()

        // Verify the results
        assertThat(result).isEqualTo("name")
    }

    @Test
    void testEquals() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.equals("other")

        // Verify the results
        assertThat(result).isFalse()
    }

    @Test
    void testHashCode() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.hashCode()

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCompareTo() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        // Run the test
        assertThatThrownBy({
            MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testGetDeclaringClass() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.getDeclaringClass()

        // Verify the results
        assertThat(result).isEqualTo(MyClass.class)
    }

    @Test
    void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.ONLYVALUE)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(NullPointerException.class)
    }
}
