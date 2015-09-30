package com.smalaca.oophowtodoit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooTest {

    @Test
    public void shouldFoo() {
        assertThat(new Foo().foo()).isEqualTo("Hello");
    }
}