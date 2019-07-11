package com.amazon.basics.controller.security.inputSanitization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InputSanitization {

    String checkUnsanitizedInputFromBufferedReader() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println(name);

        PrintWriter writer = new PrintWriter("testFile.txt", "UTF-8");
        writer.println(name);
        writer.close();

        return("Unsanitized input from buffered reader read and written to file");
    }

}
