package com.amazon.basics.controller.security.improperUse;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImproperUse {

    public String checkBadHexaDecimalCOncatenation(String password)throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(password.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        for(byte b :resultBytes) {
            stringBuilder.append( Integer.toHexString( b & 0xFF ) ); // Better code: stringBuilder.append( String.format( "%02X", b ) );
            // Integer.toHexString() which will trim any leading zeroes from each byte of the computed hash value.
            // This mistake weakens the hash value computed since it introduces more collisions.
            // For example, the hash values "0x0679" and "0x6709" would both output as "679" for the above function.
            // In this situation, the method Integer.toHexString() should be replaced with String.format().
        }

        return stringBuilder.toString();
    }

    public String checkUnsafeHashEquals (String value) {
        String actualHash = "hsknd(*$Y$DHNdjknuJ#(*@(DENjnne";

        if(value.equals(actualHash)) {
            // unsafe - instead use:
            // MessageDigest.isEqual(userInput.getBytes(),actualHash.getBytes()) for comparison
        }

        return "Unsafe hash comparison made";
    }


}
