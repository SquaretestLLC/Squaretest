package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.apache.commons.lang3.mutable.MutableLong
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertThrows

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assert new Socket("host", 80) == MyClass.PRIMARY.createNewConnection()
        assert new Socket("host", 80) == MyClass.SECONDARY.createNewConnection()
    }

    @Test
    void testCreateNewConnection1_ThrowsIOException() {
        assertThrows(IOException.class, {
            MyClass.PRIMARY.createNewConnection()
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.createNewConnection()
        })
    }

    @Test
    void testTryCreateNewConnection1() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection()
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection()
    }

    @Test
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, {
            MyClass.PRIMARY.tryCreateNewConnection()
        })
        assertThrows(RuntimeException.class, {
            MyClass.SECONDARY.tryCreateNewConnection()
        })
    }

    @Test
    void testSafeCreateNewConnection1() {
        assert new Socket("host", 80) == MyClass.PRIMARY.safeCreateNewConnection()
        assert new Socket("host", 80) == MyClass.SECONDARY.safeCreateNewConnection()
    }

    @Test
    void testCreateNewConnection2() {
        assert new Socket("host", 80) == MyClass.PRIMARY.createNewConnection("host", 0)
        assert new Socket("host", 80) == MyClass.SECONDARY.createNewConnection("host", 0)
    }

    @Test
    void testCreateNewConnection2_ThrowsIOException() {
        assertThrows(IOException.class, {
            MyClass.PRIMARY.createNewConnection("host", 0)
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.createNewConnection("host", 0)
        })
    }

    @Test
    void testTryCreateNewConnection2() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection("host", 0)
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection("host", 0)
    }

    @Test
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, {
            MyClass.PRIMARY.tryCreateNewConnection("host", 0)
        })
        assertThrows(RuntimeException.class, {
            MyClass.SECONDARY.tryCreateNewConnection("host", 0)
        })
    }

    @Test
    void testSafeCreateNewConnection2() {
        assert new Socket("host", 80) == MyClass.PRIMARY.safeCreateNewConnection("host", 0)
        assert new Socket("host", 80) == MyClass.SECONDARY.safeCreateNewConnection("host", 0)
    }

    @Test
    void testCreateNewConnection3() {
        assert new Socket("host", 80) == MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))
        assert new Socket("host", 80) == MyClass.SECONDARY.createNewConnection("host", new MutableInt(0))
    }

    @Test
    void testCreateNewConnection3_ThrowsIOException() {
        assertThrows(IOException.class, {
            MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.createNewConnection("host", new MutableInt(0))
        })
    }

    @Test
    void testTryCreateNewConnection3() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0))
    }

    @Test
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, {
            MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))
        })
        assertThrows(RuntimeException.class, {
            MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0))
        })
    }

    @Test
    void testSafeCreateNewConnection3() {
        assert new Socket("host", 80) == MyClass.PRIMARY.safeCreateNewConnection("host", new MutableInt(0))
        assert new Socket("host", 80) == MyClass.SECONDARY.safeCreateNewConnection("host", new MutableInt(0))
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
        assertThrows(IOException.class, {
            MyClass.PRIMARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        })
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
        assertThrows(RuntimeException.class, {
            MyClass.PRIMARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        })
        assertThrows(RuntimeException.class, {
            MyClass.SECONDARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)))
        })
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
        assertThrows(IOException.class, {
            MyClass.PRIMARY.closeAllConnections()
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.closeAllConnections()
        })
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
        assertThrows(IOException.class, {
            MyClass.PRIMARY.closeAllConnections([new MutableObject<>(new Socket("host", 80))])
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.closeAllConnections([new MutableObject<>(new Socket("host", 80))])
        })
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
        assertThrows(IOException.class, {
            MyClass.PRIMARY.closeConnections("host")
        })
        assertThrows(IOException.class, {
            MyClass.SECONDARY.closeConnections("host")
        })
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
        assert MyClass.PRIMARY == MyClass.getPrimary()
    }

    @Test
    void testSafeGetHostConnectionCount1() {
        assert 0L == MyClass.safeGetHostConnectionCount("host")
    }

    @Test
    void testGetHostConnectionCount1() {
        assert 0L == MyClass.getHostConnectionCount("host")
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
        assertThrows(IOException.class, {
            MyClass.getHostConnectionCount("host", out)
        })
    }

    @Test
    void testName() {
        assert "name" == MyClass.PRIMARY.name()
        assert "name" == MyClass.SECONDARY.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.PRIMARY.ordinal()
        assert 0 == MyClass.SECONDARY.ordinal()
    }

    @Test
    void testToString() {
        assert "name" == MyClass.PRIMARY.toString()
        assert "name" == MyClass.SECONDARY.toString()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.PRIMARY.equals("other"))
        assertFalse(MyClass.SECONDARY.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.PRIMARY.hashCode()
        assert 0 == MyClass.SECONDARY.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.PRIMARY.compareTo(MyClass.PRIMARY)
        assert 0 == MyClass.SECONDARY.compareTo(MyClass.PRIMARY)
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, {
            MyClass.PRIMARY.compareTo(MyClass.PRIMARY)
        })
        assertThrows(NullPointerException.class, {
            MyClass.SECONDARY.compareTo(MyClass.PRIMARY)
        })
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThrows(ClassCastException.class, {
            MyClass.PRIMARY.compareTo(MyClass.PRIMARY)
        })
        assertThrows(ClassCastException.class, {
            MyClass.SECONDARY.compareTo(MyClass.PRIMARY)
        })
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.PRIMARY.getDeclaringClass()
        assert MyClass.class == MyClass.SECONDARY.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.PRIMARY == Enum.valueOf(MyClass.class, "name")
        assertThrows(IllegalArgumentException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
        assertThrows(NullPointerException.class, {
            Enum.valueOf(MyClass.class, "name")
        })
    }
}
