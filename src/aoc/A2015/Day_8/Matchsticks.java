package aoc.A2015.Day_8;

import aoc.Conveniencer;

public class Matchsticks {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/A2015/Day_8/input.txt";
    private static int character_literals = 0;
    private static int character_in_memory = 0;

    public static void main(String[] args) {
        System.out.println(FILE_PATH);
        String input = Conveniencer.getInput(FILE_PATH);
        //Exercise 1
        calculateCharAmounts(input);
        printCharAmounts();

    }

    private static void printCharAmounts() {
        System.out.println( "literals: " + character_literals + "\n" +
                            "memory: " + character_in_memory + "\n" +
                            "Subtracted: " + (character_literals - character_in_memory)
        );
    }

    private static void calculateCharAmounts(String s) {
        character_literals = s.length();
        for (int cur = 0; cur < character_literals; cur++) {
            if (cur < character_literals -1 && s.charAt(cur) == '\\' && s.charAt(cur+1) == '\\') {
                character_in_memory++;
                cur++;
                continue;
            }
            if (cur < character_literals -1 && s.charAt(cur) == '\\' && s.charAt(cur+1) == '"') {
                character_in_memory++;
                cur++;
                continue;
            }
            if (cur < character_literals -3 && s.charAt(cur) == '\\' && s.charAt(cur+1) == 'x') {
                character_in_memory++;
                cur += 3;
                continue;
            }
            if (s.charAt(cur) == '"') continue;
            character_in_memory++;
        }
    }

}
