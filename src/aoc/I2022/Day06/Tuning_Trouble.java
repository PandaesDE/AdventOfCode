package aoc.I2022.Day06;

import aoc.Conveniencer;

public class Tuning_Trouble {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/I2022/Day06/input.txt";
    private static final int MAX_CHARS_FOR_MARKER = 4;

    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);

        System.out.println(getMarker(input));
    }

    private static int getMarker(String input) {
        int marker = 0;
        int charIndex = 0;
        char[] chars = new char[MAX_CHARS_FOR_MARKER];
        boolean alreadyContained = false;

        for (int i = 0; i < input.length(); i++) {
            if (!alreadyContained) {
                chars[charIndex] = input.charAt(i);
                charIndex++;
            }

            for (int j = 0; j < charIndex; j++) {
                printCharArray(chars);
                if (chars[j] == input.charAt(i)) {
                    alreadyContained = true;
                    break;
                }
            }

            marker++;
            alreadyContained = false;

            if (charIndex + 1 == MAX_CHARS_FOR_MARKER)
                break;
        }
        return marker;
    }

    private static void printCharArray(char[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
    }
}
