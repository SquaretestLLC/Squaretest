package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testNextState() {
        assertThat(MyClass.Submitted.nextState()).isEqualTo(MyClass.Submitted);
        assertThat(MyClass.Escalated.nextState()).isEqualTo(MyClass.Submitted);
        assertThat(MyClass.Approved.nextState()).isEqualTo(MyClass.Submitted);
    }

    @Test
    public void testResponsiblePerson() {
        assertThat(MyClass.Submitted.responsiblePerson()).isEqualTo("result");
        assertThat(MyClass.Escalated.responsiblePerson()).isEqualTo("result");
        assertThat(MyClass.Approved.responsiblePerson()).isEqualTo("result");
    }
}
