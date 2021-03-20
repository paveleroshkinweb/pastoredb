package org.pastore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import com.google.gson.Gson;


public class Main {

    public static void main(String args[]){
        // Test code
        while (true) {
            try {
                Gson gson = new Gson();
                BufferedWriter write = new BufferedWriter(new FileWriter("/home/pavel/temp", true));
                write.write("Something" + gson.htmlSafe());
                write.flush();
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }
}
