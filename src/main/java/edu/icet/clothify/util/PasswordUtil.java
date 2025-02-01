package edu.icet.clothify.util;

import org.jasypt.util.text.BasicTextEncryptor;

public class PasswordUtil {
    private PasswordUtil() {
    }
    private String key="1234";
    private static PasswordUtil instance;

    public static PasswordUtil getInstance() {
        return instance == null ? instance = new PasswordUtil() : instance;
    }

    public String encryptPassword(String password){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.encrypt(password);
    }

    public String decryptPassword(String password){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.decrypt(password);
    }
}
