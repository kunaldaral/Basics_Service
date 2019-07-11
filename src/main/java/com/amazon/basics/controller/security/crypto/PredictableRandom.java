package com.amazon.basics.controller.security.crypto;

import java.util.Random;

public class PredictableRandom {

    public String checkWeakRandomGenerator() {
        Random r = new Random();
        System.out.println(Long.toHexString(r.nextLong()));
        return("Weak random number generated");
    }

}
