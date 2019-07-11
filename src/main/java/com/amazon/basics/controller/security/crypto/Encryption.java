package com.amazon.basics.controller.security.crypto;

import javax.crypto.*;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public String checkRiskyEncryptionMD5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // MD5 Hash not strong enough
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        md5Digest.update(password.getBytes("UTF-16"));
        byte[] MD5hashValue = md5Digest.digest();
        return "MD5 risky encryption used";
    }

    public String checkRiskyEncryptionSHA1(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // SHA1 Hash not strong enough
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA1");
        sha1Digest.update(password.getBytes("UTF-16"));
        byte[] SHA1hashValue = sha1Digest.digest();

        return "SHA1 risky encryption used";
    }

    public String checkSocketEncryption() throws IOException {
        //Insecure
        Socket soc = new Socket("www.amazon.com",80);

        //Secure - no error should be thrown
        Socket secureSoc = SSLSocketFactory.getDefault().createSocket("www.amazon.com", 443);

        // Insecure way
        ServerSocket soc1 = new ServerSocket(1234);

        //Secure way
        ServerSocket soc1secure = SSLServerSocketFactory.getDefault().createServerSocket(1234);

        try {
            soc.close();
            secureSoc.close();
            soc1.close();
            soc1secure.close();
        } catch (IOException e) { /* failed */ }
        return("Unencrypted communication channel opened!");
    }

    public String checkWeakCipher(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("AES"); // Weak Cipher
//        c.init(Cipher.ENCRYPT_MODE, k, iv);
        c.doFinal(value.getBytes());

        return "Weak AES Cipher used";
    }

    public String checkUseOfDESCipher(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
//        c.init(Cipher.ENCRYPT_MODE, k, iv);
        c.doFinal(value.getBytes());

        return "Weak DES Cipher was used. AES cipher should be recommended by tool.";
    }

    public String checkUseOfDESedeCipher(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//        c.init(Cipher.ENCRYPT_MODE, k, iv);
        c.doFinal(value.getBytes());

        return "Weak DESede Cipher was used. It is good for modern applications but NIST recommends AES cipher.";
    }

    public String checkCipherPadding(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("RSA/NONE/NoPadding"); // Insecure instead use "RSA/ECB/OAEPWithMD5AndMGF1Padding"
        c.doFinal(value.getBytes());

        return "A no padding cipher was used";
    }

    public String checkCipherMode(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("AES/ECB/NoPadding"); // ECB Mode is insecure
//        c.init(Cipher.ENCRYPT_MODE, k, iv);
        c.doFinal(value.getBytes());

        return "A no padding cipher was used";
    }

    public String checkCipherIntegrity(String value) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding"); // Instead use AES/GCM/NoPadding
        Cipher c1 = Cipher.getInstance("AES/CBC/PKCS5Padding");  // Instead use AES/GCM/NoPadding
        Cipher c2 = Cipher.getInstance("DESede/ECB/PKCS5Padding");  // Instead use AES/GCM/NoPadding
//        c.init(Cipher.ENCRYPT_MODE, k, iv);
        c.doFinal(value.getBytes());
        c1.doFinal(value.getBytes());
        c2.doFinal(value.getBytes());

        return "A no padding cipher was used";
    }

    public String checkSmallBlowfishKey (String value) throws NoSuchAlgorithmException{
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
        keyGen.init(64); // Should be at least 128

        return "Small Key for Blowfish Cipher used.";
    }

    public String checkSmallRSAKeySize (String value) throws NoSuchAlgorithmException{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);// Should be at least 2048

        return "Small Key for Blowfish Cipher used.";
    }



}

