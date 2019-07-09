package com.amazon.basics.controller.security;

import com.amazon.basics.domain.User;

public class NullPointer {

    public String checkNullPointer(User um) {
        um = null;
        try{
            System.out.println(um.getId());
        }catch (Exception e){
            System.out.println("Null error");
            return("Null Pointer Error Thrown");
        }
        return("No Null Pointer Error Thrown");
    }
}
