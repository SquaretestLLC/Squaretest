package com.myapp


import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertFalse

@CompileStatic
class MyClassTest {

    @Test
    void testNextState() {
        assert MyClass.Submitted == MyClass.Submitted.nextState()
        assert MyClass.Submitted == MyClass.Escalated.nextState()
        assert MyClass.Submitted == MyClass.Approved.nextState()
    }

    @Test
    void testResponsiblePerson() {
        assert "result" == MyClass.Submitted.responsiblePerson()
        assert "result" == MyClass.Escalated.responsiblePerson()
        assert "result" == MyClass.Approved.responsiblePerson()
    }

    @Test
    void testName() {
        assert "name" == MyClass.Submitted.name()
        assert "name" == MyClass.Escalated.name()
        assert "name" == MyClass.Approved.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.Submitted.ordinal()
        assert 0 == MyClass.Escalated.ordinal()
        assert 0 == MyClass.Approved.ordinal()
    }

    @Test
    void testToString() {
        assert "name" == MyClass.Submitted.toString()
        assert "name" == MyClass.Escalated.toString()
        assert "name" == MyClass.Approved.toString()
    }

    @Test
    void testEquals() {
        assertFalse(MyClass.Submitted.equals("other"))
        assertFalse(MyClass.Escalated.equals("other"))
        assertFalse(MyClass.Approved.equals("other"))
    }

    @Test
    void testHashCode() {
        assert 0 == MyClass.Submitted.hashCode()
        assert 0 == MyClass.Escalated.hashCode()
        assert 0 == MyClass.Approved.hashCode()
    }

    @Test
    void testCompareTo() {
        assert 0 == MyClass.Submitted.compareTo(MyClass.Submitted)
        assert 0 == MyClass.Escalated.compareTo(MyClass.Submitted)
        assert 0 == MyClass.Approved.compareTo(MyClass.Submitted)
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testCompareTo_ThrowsNullPointerException() {
        MyClass.Submitted.compareTo(MyClass.Submitted)
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testCompareTo_ThrowsClassCastException() {
        MyClass.Submitted.compareTo(MyClass.Submitted)
    }

    @Test
    void testGetDeclaringClass() {
        assert MyClass.class == MyClass.Submitted.getDeclaringClass()
        assert MyClass.class == MyClass.Escalated.getDeclaringClass()
        assert MyClass.class == MyClass.Approved.getDeclaringClass()
    }

    @Test
    void testValueOf() {
        assert MyClass.Submitted == Enum.valueOf(MyClass.class, "name")
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testValueOf_ThrowsIllegalArgumentException() {
        Enum.valueOf(MyClass.class, "name")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testValueOf_ThrowsNullPointerException() {
        Enum.valueOf(MyClass.class, "name")
    }
}
