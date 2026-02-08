package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertFalse

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
        assert "result" == MyClass.Submitted.name()
        assert "result" == MyClass.Escalated.name()
        assert "result" == MyClass.Approved.name()
    }

    @Test
    void testOrdinal() {
        assert 0 == MyClass.Submitted.ordinal()
        assert 0 == MyClass.Escalated.ordinal()
        assert 0 == MyClass.Approved.ordinal()
    }

    @Test
    void testToString() {
        assert "result" == MyClass.Submitted.toString()
        assert "result" == MyClass.Escalated.toString()
        assert "result" == MyClass.Approved.toString()
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
}
