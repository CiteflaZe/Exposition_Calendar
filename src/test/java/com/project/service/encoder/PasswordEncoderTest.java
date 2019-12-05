package com.project.service.encoder;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PasswordEncoderTest {

    @Test
    public void encodeShouldReturnEncodedPassword() {
        String actual = passwordEncoder.encode("1111");
        String expected = "ffe1abd1a8215353c233d6e09613e95eec4253832a761af28ff37ac5a15c";

        assertThat(actual, is(expected));
    }

    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

}