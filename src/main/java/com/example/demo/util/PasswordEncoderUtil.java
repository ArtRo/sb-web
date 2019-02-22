package com.example.demo.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by dhf_ndm on 2019/2/22
 * the previous bug derived from the previous
 */

public class PasswordEncoderUtil implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return HashUtil.md5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
