package com.amazon.basics.controller.security;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public String checkRiskyEncryptionMD5(String password) throws NoSuchAlgorithmException {

        // MD5 Hash not strong enough
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        md5Digest.update(password.getBytes());
        byte[] MD5hashValue = md5Digest.digest();

        return "MD5 risky encryption used";
    }

    public String checkRiskyEncryptionSHA1(String password) throws NoSuchAlgorithmException {
        // SHA1 Hash not strong enough
        MessageDigest sha1Digest = MessageDigest.getInstance("SHA1");
        sha1Digest.update(password.getBytes());
        byte[] SHA1hashValue = sha1Digest.digest();

        return "SHA1 risky encryption used";
    }

    public String checkSocketEncryption() throws IOException {
        //Incorrect
        Socket soc = new Socket("www.amazon.com",80);

        //Correct - no error should be thrown
        Socket secureSoc = SSLSocketFactory.getDefault().createSocket("www.amazon.com", 443);

        try {
            soc.close();
            secureSoc.close();
        } catch (IOException e) { /* failed */ }
        return("Unencrypted communication channel opened!");
    }

}
