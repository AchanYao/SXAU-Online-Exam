package com.achan.exam.auth.client.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Achan
 * @date 2020/3/8
 */
public class PasswordEncoderTest {

    @Test
    public void encodeTest() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Test
    public void matchTest() {
        System.out.println(new BCryptPasswordEncoder().matches("20171611101", "$2a$10$tVnlcn3CYW5TaDcroR7pGO/f3CAlSANXo3f3WFJNZCFPbyX2yrIi."));
    }
}
