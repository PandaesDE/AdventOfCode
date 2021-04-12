package VSCode_Exercises.AdventOfCode.A2015.Day_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import VSCode_Exercises.AdventOfCode.Conveniencer;

public class Not_Quite_Lisp {
    private static final String DIRECTORY = "VSCode_Exercises/AdventOfCode/A2015/Day_1/input.txt";

    public static void main(String[] args) {
        String input = Conveniencer.getInput(DIRECTORY);
        //Exercise 1
        System.out.println(parenthesisCount(input));
        //Exercise 2
        System.out.println(parenthesisIndexForFirstNegativeFloor(input));

    }

    private static int parenthesisIndexForFirstNegativeFloor(String p) {
        int par = 0;
        for (int i = 0; i<p.length(); i++) {
            if (p.charAt(i) == '(') par++;
            if (p.charAt(i) == ')') par--;
            if (par == -1) return i+1;
        }
        return par;
    }

    private static int parenthesisCount(String p) {
        int par = 0;
        for (int i = 0; i<p.length(); i++) {
            if (p.charAt(i) == '(') par++;
            if (p.charAt(i) == ')') par--;
        }
        return par;
    }
    
}
