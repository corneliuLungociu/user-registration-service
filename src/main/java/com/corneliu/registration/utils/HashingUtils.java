package com.corneliu.registration.utils;

public class HashingUtils {

    private HashingUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static String hashPassword(String password) {
        try {
//            SecureRandom random = new SecureRandom();
//            byte[] salt = new byte[16];
//            random.nextBytes(salt);
//
//            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//
//            byte[] hash = factory.generateSecret(spec).getEncoded();
//            return new String(hash);

            // TODO:
            return password;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
