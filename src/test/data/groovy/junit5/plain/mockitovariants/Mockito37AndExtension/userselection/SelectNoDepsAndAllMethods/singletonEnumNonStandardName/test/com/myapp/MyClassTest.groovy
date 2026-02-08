package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows

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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.createNewConnection()
        })
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
        assertThrows(RuntimeException.class, {
            MyClass.ONLYVALUE.tryCreateNewConnection()
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.createNewConnection("host", 0)
        })
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
        assertThrows(RuntimeException.class, {
            MyClass.ONLYVALUE.tryCreateNewConnection("host", 0)
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.createNewConnection("host", port)
        })
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
        assertThrows(RuntimeException.class, {
            MyClass.ONLYVALUE.tryCreateNewConnection("host", port)
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.createNewConnection("host", outSocket)
        })
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
        assertThrows(RuntimeException.class, {
            MyClass.ONLYVALUE.tryCreateNewConnection("host", outSocket)
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.closeAllConnections()
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.closeAllConnections(socketsToClose)
        })
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
        assertThrows(IOException.class, {
            MyClass.ONLYVALUE.closeConnections("host")
        })
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
        assert "name" == result
    }

    @Test
    void testOrdinal() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.ordinal()

        // Verify the results
        assert 0 == result
    }

    @Test
    void testToString() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.toString()

        // Verify the results
        assert "name" == result
    }

    @Test
    void testEquals() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.equals("other")

        // Verify the results
        assertFalse(result)
    }

    @Test
    void testHashCode() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.hashCode()

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCompareTo() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)

        // Verify the results
        assert 0 == result
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        assertThrows(NullPointerException.class, {
            MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)
        })
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        // Run the test
        assertThrows(ClassCastException.class, {
            MyClass.ONLYVALUE.compareTo(MyClass.ONLYVALUE)
        })
    }

    @Test
    void testGetDeclaringClass() {
        // Setup
        // Run the test
        def result = MyClass.ONLYVALUE.getDeclaringClass()

        // Verify the results
        assert MyClass.class == result
    }

    @Test
    void testValueOf() {
        assert MyClass.ONLYVALUE == Enum.valueOf(MyClass.class, "name")
        assertThrows(IllegalArgumentException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
        assertThrows(NullPointerException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
    }
}
