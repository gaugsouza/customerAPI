package com.mentorizacao.customer.services;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 */
@Service
public class EncryptPasswordService {

    /**
     * The encryptPassword method is responsible to make the password secure using PBKDF2WithHmacSHA1.
     *
     * @param password - the password that will be encrypted
     *
     * @return String  - the password already encrypted
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public String encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    /**
     * The validatePassword method is responsible to check if the password provided is valid in
     * comparison to stored password.
     *
     * @param originalPassword - the password informed
     * @param storedPassword   - the password stored in database
     *
     * @return boolean         - the response of password validation (true = valid, false = invalid)
     *
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    /**
     * The fromHex method is responsible to convert String to byte[]
     *
     * @param hex     - the String that will be converted
     * @return byte[] - the String converted to byte[]
     */
    private byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * The toHex method is responsible to convert byte[] to String
     *
     * @param array   - the byte[] that will be converted
     * @return String - the byte[] converted to String
     */
    private String toHex(byte[] array){
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%i%i", 0, paddingLength) + hex;
        }else{
            return hex;
        }
    }

    /**
     * The getSalt method is responsible to generate the salt used in password encryption
     *
     * @return byte[] - the generated salt
     * @throws NoSuchAlgorithmException
     */
    private byte[] getSalt() throws NoSuchAlgorithmException{
        byte[] salt = new byte[16];
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.nextBytes(salt);
        return salt;
    }
}
