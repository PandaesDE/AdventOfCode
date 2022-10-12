package aoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Conveniencer {

    public static String getProjectPath() {
        return new File("").getAbsolutePath();
    }

    public static String getInput(String dir) {
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(dir));
            while (br.ready()){
                input += br.readLine() + "\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return("");
        }
        return input;
    }

    public static ArrayList<String> convertTextToLines(String text) {
        ArrayList<String> lines = new ArrayList<>();
        //Exception if no breakLine
        if (text.indexOf("\n") == -1) {
            lines.add(text);
            return lines;
        }
        int breakLineIndex = 0;
        int nextBreakLineIndex = 0;
        while (true) {
            nextBreakLineIndex = text.indexOf("\n", breakLineIndex);
            if (nextBreakLineIndex == -1) break;
            lines.add(text.substring(breakLineIndex, nextBreakLineIndex));
            breakLineIndex = nextBreakLineIndex+1;
        }
        return lines;
    }

    public static int stringToInt(String s){
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            System.out.println("Entered String can not be parsed to an int value, returned int = 0");
            return 0;
        }
    }
}
