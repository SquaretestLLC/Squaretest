package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testNextState() {
        assertThat(MyClass.Submitted.nextState()).isEqualTo(MyClass.Submitted);
        assertThat(MyClass.Escalated.nextState()).isEqualTo(MyClass.Submitted);
        assertThat(MyClass.Approved.nextState()).isEqualTo(MyClass.Submitted);
    }

    @Test
    void testResponsiblePerson() {
        assertThat(MyClass.Submitted.responsiblePerson()).isEqualTo("result");
        assertThat(MyClass.Escalated.responsiblePerson()).isEqualTo("result");
        assertThat(MyClass.Approved.responsiblePerson()).isEqualTo("result");
    }
}
