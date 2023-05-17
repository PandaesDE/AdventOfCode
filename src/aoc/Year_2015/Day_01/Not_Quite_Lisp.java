package aoc.Year_2015.Day_01;

import aoc.Conveniencer;

public class Not_Quite_Lisp {

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2015, 1);
        // Exercise 1
        System.out.println(parenthesisCount(input));
        // Exercise 2
        System.out.println(parenthesisIndexForFirstNegativeFloor(input));

    }

    private static int parenthesisIndexForFirstNegativeFloor(String p) {
        int par = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(')
                par++;
            if (p.charAt(i) == ')')
                par--;
            if (par == -1)
                return i + 1;
        }
        return par;
    }

    private static int parenthesisCount(String p) {
        int par = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(')
                par++;
            if (p.charAt(i) == ')')
                par--;
        }
        return par;
    }

}
