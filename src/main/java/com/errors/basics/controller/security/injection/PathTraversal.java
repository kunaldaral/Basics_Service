package com.errors.basics.controller.security.injection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PathTraversal {

    public String checkFileWrite(String filename){
        try{
            FileWriter fw=new FileWriter(filename); //Unsanitized filename passed
            fw.write("Just some text.");
            fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return("Unsanitized input passed as filename to write to file");
    }

    public String checkFileRead(String filename) throws IOException{
        String file ="src/test/resources/" + filename; //unsanitized input might lead to wrong file being read
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String content = reader.readLine();
        reader.close();
        return("Unsanitized input passed as filename to read from file");
    }

}
