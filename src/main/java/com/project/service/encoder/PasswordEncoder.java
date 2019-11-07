package com.project.service.encoder;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public class PasswordEncoder {
    private static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class);

    public String encode(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toHexString(aByte & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Error while encoding password ", e);
            throw new NoSuchElementException("Encoding method was not found");
        }
    }
}
