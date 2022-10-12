package aoc.B2016.Day_2;

import java.util.ArrayList;

import aoc.Conveniencer;

public class Bathroom_Security {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/B2016/Day_2/input.txt";

    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);
        //Exercise 1
        System.out.println(getCode(input));
        //Exercise 2
        System.out.println(getNewCode(input));

    }

    private static String getNewCode(String instructions) {
        String code = "";
        ArrayList<String> codeLines = Conveniencer.convertTextToLines(instructions);
        for (String iterator : codeLines) {
            code += getNewDialNumber(iterator);
        }
        return code;
    }

    private static char getNewDialNumber(String instruction) {
        char[][] dial = {{'0', '0', '5', '0', '0'},{'0', '2', '6', 'A', '0'},{'1', '3', '7', 'B', 'D'},{'0', '4', '8', 'C', '0'},{'0', '0', '9', '0', '0'}};
        int dialDown = 1;
        int dialRight = 1;
        for (int i = 0; i<instruction.length(); i++) {
            switch (instruction.toUpperCase().charAt(i)) {
                case 'U':
                    if (dialDown > 0)
                        if (dial[dialRight][dialDown-1] != '0')
                            dialDown--;
                    break;
                case 'R':
                    if (dialRight < 4)
                        if (dial[dialRight+1][dialDown] != '0')
                            dialRight++;
                    break;
                case 'D':
                    if (dialDown < 4)
                        if (dial[dialRight][dialDown+1] != '0')
                            dialDown++;
                    break;
                case 'L':
                    if (dialRight > 0)
                        if (dial[dialRight-1][dialDown] != '0')
                            dialRight--;
                    break;
                default:
                    break;
            }
        }
        return dial[dialRight][dialDown];
    }

    private static String getCode(String instructions) {
        String code = "";
        ArrayList<String> codeLines = Conveniencer.convertTextToLines(instructions);
        for (String iterator : codeLines) {
            code += getDialNumber(iterator);
        }
        return code;
    }

    private static int getDialNumber(String instruction) {
        int[][] dial = {{1, 4, 7},{2, 5, 8},{3, 6, 9}};
        int dialDown = 1;
        int dialRight = 1;
        for (int i = 0; i<instruction.length(); i++) {
            switch (instruction.toUpperCase().charAt(i)) {
                case 'U':
                    if (dialDown > 0)
                        dialDown--;
                    break;
                case 'R':
                    if (dialRight < 2)
                        dialRight++;
                    break;
                case 'D':
                    if (dialDown < 2)
                        dialDown++;
                    break;
                case 'L':
                    if (dialRight > 0)
                        dialRight--;
                    break;
                default:
                    break;
            }
        }
        return dial[dialRight][dialDown];
    }


    
}
