package com.devsu.bankclient.application.port.output;

public interface IPasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
