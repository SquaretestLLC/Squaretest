package com.myapp


import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testNextState() {
        assertThat(MyClass.Submitted.nextState()).isEqualTo(MyClass.Submitted)
        assertThat(MyClass.Escalated.nextState()).isEqualTo(MyClass.Submitted)
        assertThat(MyClass.Approved.nextState()).isEqualTo(MyClass.Submitted)
    }

    @Test
    void testResponsiblePerson() {
        assertThat(MyClass.Submitted.responsiblePerson()).isEqualTo("result")
        assertThat(MyClass.Escalated.responsiblePerson()).isEqualTo("result")
        assertThat(MyClass.Approved.responsiblePerson()).isEqualTo("result")
    }

    @Test
    void testName() {
        assertThat(MyClass.Submitted.name()).isEqualTo("name")
        assertThat(MyClass.Escalated.name()).isEqualTo("name")
        assertThat(MyClass.Approved.name()).isEqualTo("name")
    }

    @Test
    void testOrdinal() {
        assertThat(MyClass.Submitted.ordinal()).isEqualTo(0)
        assertThat(MyClass.Escalated.ordinal()).isEqualTo(0)
        assertThat(MyClass.Approved.ordinal()).isEqualTo(0)
    }

    @Test
    void testToString() {
        assertThat(MyClass.Submitted.toString()).isEqualTo("name")
        assertThat(MyClass.Escalated.toString()).isEqualTo("name")
        assertThat(MyClass.Approved.toString()).isEqualTo("name")
    }

    @Test
    void testEquals() {
        assertThat(MyClass.Submitted.equals("other")).isFalse()
        assertThat(MyClass.Escalated.equals("other")).isFalse()
        assertThat(MyClass.Approved.equals("other")).isFalse()
    }

    @Test
    void testHashCode() {
        assertThat(MyClass.Submitted.hashCode()).isEqualTo(0)
        assertThat(MyClass.Escalated.hashCode()).isEqualTo(0)
        assertThat(MyClass.Approved.hashCode()).isEqualTo(0)
    }

    @Test
    void testCompareTo() {
        assertThat(MyClass.Submitted.compareTo(MyClass.Submitted)).isEqualTo(0)
        assertThat(MyClass.Escalated.compareTo(MyClass.Submitted)).isEqualTo(0)
        assertThat(MyClass.Approved.compareTo(MyClass.Submitted)).isEqualTo(0)
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThatThrownBy({
            MyClass.Submitted.compareTo(MyClass.Submitted)
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            MyClass.Escalated.compareTo(MyClass.Submitted)
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            MyClass.Approved.compareTo(MyClass.Submitted)
        }).isInstanceOf(NullPointerException.class)
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThatThrownBy({
            MyClass.Submitted.compareTo(MyClass.Submitted)
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            MyClass.Escalated.compareTo(MyClass.Submitted)
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            MyClass.Approved.compareTo(MyClass.Submitted)
        }).isInstanceOf(ClassCastException.class)
    }

    @Test
    void testGetDeclaringClass() {
        assertThat(MyClass.Submitted.getDeclaringClass()).isEqualTo(MyClass.class)
        assertThat(MyClass.Escalated.getDeclaringClass()).isEqualTo(MyClass.class)
        assertThat(MyClass.Approved.getDeclaringClass()).isEqualTo(MyClass.class)
    }

    @Test
    void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.Submitted)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            Enum.valueOf(MyClass.class, "name")
        }).isInstanceOf(NullPointerException.class)
    }
}
