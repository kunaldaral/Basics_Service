package com.amazon.basics.controller.security;

import java.util.Random;

public class PredictableRandom {

    String checkWeakRandomGenerator() {
        Random r = new Random();
        System.out.println(Long.toHexString(r.nextLong()));
        return("Weak random number generated");
    }

}
