package aoc.Year_2015.Day_08;

import aoc.Conveniencer;

public class Matchsticks {
    private static int chars_literals = 0;
    private static int chars_escaped = 0;
    private static int chars_encoded = 0;

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2015, 8);
        // Exercise 1
        calculateAllChars(input);
        calculateEscapedChars(input);
        // Exercise 2
        calculateEncodedChars(input);
        printCharAmounts();

    }

    private static void printCharAmounts() {
        System.out.println("literals: " + chars_literals + "\n" +
                "escaped: " + chars_escaped + "\n" +
                "encoded: " + chars_encoded + "\n" +
                "(1): literals - escaped: " + (chars_literals - chars_escaped) + "\n" +
                "(2): encoded - literals: " + (chars_encoded - chars_literals));
    }

    private static void calculateEncodedChars(String s) {
        chars_encoded = 0;
        chars_encoded = Conveniencer.findOccurences(s, "\n") * 2; // including the surrounding double quotes
        System.out.println(chars_encoded);
        for (int cur = 0; cur < chars_literals; cur++) {
            // case: \
            if (s.charAt(cur) == '\\') {
                chars_encoded += 2;
                continue;
            }
            // case: "
            if (s.charAt(cur) == '"') {
                chars_encoded += 2;
                continue;
            }
            chars_encoded++;
        }
    }

    private static void calculateAllChars(String s) {
        chars_literals = s.length();
    }

    private static void calculateEscapedChars(String s) {
        chars_escaped = 0;
        for (int cur = 0; cur < chars_literals; cur++) {
            // case: \" - lone double-quote character
            if (cur < chars_literals - 1 && s.charAt(cur) == '\\' && s.charAt(cur + 1) == '\\') {
                chars_escaped++;
                cur++;
                continue;
            }
            // case: \\ - single backslash
            if (cur < chars_literals - 1 && s.charAt(cur) == '\\' && s.charAt(cur + 1) == '"') {
                chars_escaped++;
                cur++;
                continue;
            }
            // case: \x00 - single character with that ASCII code
            if (cur < chars_literals - 3 && s.charAt(cur) == '\\' && s.charAt(cur + 1) == 'x') {
                chars_escaped++;
                cur += 3;
                continue;
            }
            // case: "
            if (s.charAt(cur) == '"')
                continue;
            chars_escaped++;
        }
    }

}
