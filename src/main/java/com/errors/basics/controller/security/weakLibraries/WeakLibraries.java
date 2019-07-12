package com.errors.basics.controller.security.weakLibraries;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class WeakLibraries {

    public String checkUseOfNullCipher(String value) throws IllegalBlockSizeException, BadPaddingException {
        Cipher doNothingCihper = new NullCipher();
        //The ciphertext produced will be identical to the plaintext.
        byte[] cipherText = doNothingCihper.doFinal(value.getBytes());

        return "Null Cipher Used";
    }

    public String checkHardCodedPasswordSetting(String value) {

        String SECRET_PASSWORD = "letMeIn!";

        Properties props = new Properties();
        props.put(Context.SECURITY_CREDENTIALS, "p@ssw0rd");

        return "Hard coded password set";
    }

    public String checkHardCodedKey(String value) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8};
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.ENCRYPT_MODE, spec);
        aes.doFinal(value.getBytes());
        return "Har Coded Key used for cipher";
    }



}
