package org.pastore;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static void main(String args[]){
        // Entry point
        while (true) {
            try {
//                BufferedWriter write = new BufferedWriter(new FileWriter(args[0], true));
//                write.write(args[0]);
                BufferedWriter write = new BufferedWriter(new FileWriter("/home/pavel/temp", true));
                write.write("Something");
                write.flush();
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }
}
