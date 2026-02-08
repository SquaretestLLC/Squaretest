package com.myapp

import groovy.transform.CompileStatic
import org.apache.commons.lang3.mutable.MutableInt
import org.apache.commons.lang3.mutable.MutableLong
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.Test

@CompileStatic
class MyClassTest {

    @Test
    void testCreateNewConnection1() {
        assert new Socket("host", 80) == MyClass.PRIMARY.createNewConnection()
        assert new Socket("host", 80) == MyClass.SECONDARY.createNewConnection()
    }

    @Test(expected = IOException.class)
    void testCreateNewConnection1_ThrowsIOException() {
        MyClass.PRIMARY.createNewConnection()
    }

    @Test
    void testTryCreateNewConnection1() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection()
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection()
    }

    @Test(expected = RuntimeException.class)
    void testTryCreateNewConnection1_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection()
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

    @Test(expected = IOException.class)
    void testCreateNewConnection2_ThrowsIOException() {
        MyClass.PRIMARY.createNewConnection("host", 0)
    }

    @Test
    void testTryCreateNewConnection2() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection("host", 0)
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection("host", 0)
    }

    @Test(expected = RuntimeException.class)
    void testTryCreateNewConnection2_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection("host", 0)
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

    @Test(expected = IOException.class)
    void testCreateNewConnection3_ThrowsIOException() {
        MyClass.PRIMARY.createNewConnection("host", new MutableInt(0))
    }

    @Test
    void testTryCreateNewConnection3() {
        assert new Socket("host", 80) == MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))
        assert new Socket("host", 80) == MyClass.SECONDARY.tryCreateNewConnection("host", new MutableInt(0))
    }

    @Test(expected = RuntimeException.class)
    void testTryCreateNewConnection3_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection("host", new MutableInt(0))
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

    @Test(expected = IOException.class)
    void testCreateNewConnection4_ThrowsIOException() {
        MyClass.PRIMARY.createNewConnection("host", new MutableObject<>(new Socket("host", 80)))
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

    @Test(expected = RuntimeException.class)
    void testTryCreateNewConnection4_ThrowsRuntimeException() {
        MyClass.PRIMARY.tryCreateNewConnection("host", new MutableObject<>(new Socket("host", 80)))
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

    @Test(expected = IOException.class)
    void testCloseAllConnections1_ThrowsIOException() {
        MyClass.PRIMARY.closeAllConnections()
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

    @Test(expected = IOException.class)
    void testCloseAllConnections2_ThrowsIOException() {
        MyClass.PRIMARY.closeAllConnections([new MutableObject<>(new Socket("host", 80))])
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

    @Test(expected = IOException.class)
    void testCloseConnections_ThrowsIOException() {
        MyClass.PRIMARY.closeConnections("host")
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

    @Test(expected = IOException.class)
    void testGetHostConnectionCount2_ThrowsIOException() {
        // Setup
        def out = new MutableLong(0L)

        // Run the test
        MyClass.getHostConnectionCount("host", out)
    }
}
