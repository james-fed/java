package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {


    public String read(String fileName) {

        File file = new File(fileName);
        Scanner scanner = null;
        String content = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine())
                content += scanner.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
        return content;
    }


    public boolean isExist(String fileName){
        File file = new File(fileName);
        return file.exists();
    }


}
