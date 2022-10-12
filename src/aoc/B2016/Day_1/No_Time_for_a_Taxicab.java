package aoc.B2016.Day_1;

import java.util.ArrayList;

import aoc.Conveniencer;

public class No_Time_for_a_Taxicab {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/B2016/Day_1/input.txt";
    private static int X_POSITION = 0;
    private static int Y_POSITION = 0;
    private static char DIRECTION = 'N';
    private static ArrayList<Pair> VISITED_LOCATIONS = new ArrayList<Pair>();
    private static boolean NOT_EQUAL_PAIR = true;

    
    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);
        ArrayList<String> instructions = storeInput(trimInput(input));
        //Exercise 1
        calculateXAndYPosition(instructions);
        System.out.println(getShortestGridPath(X_POSITION,Y_POSITION));
        //Exercise 2 (111)
        setDefaultValuesToVariables();
        followInstructions(instructions);
        System.out.println(getShortestGridPath(X_POSITION,Y_POSITION));
    }

    private static void followInstructions(ArrayList<String> instructions) {
        VISITED_LOCATIONS.add(new Pair(0,0));
        for (String iterator : instructions) {
            if (NOT_EQUAL_PAIR) operateNewInstruction(iterator);
            else return;
        }
    }

    private static void operateNewInstruction(String instruction) {
        int steps = Conveniencer.stringToInt(instruction.substring(1, instruction.length()));
        setDirection(instruction.toUpperCase().charAt(0));
        switch (DIRECTION) {
            case 'N':
                for (int i = 1; i<=steps; i++) {
                    Pair comparison = new Pair(X_POSITION, Y_POSITION + i);
                    checkForEqualPair(comparison);
                    VISITED_LOCATIONS.add(new Pair(X_POSITION, Y_POSITION + i));
                }
                if (NOT_EQUAL_PAIR) Y_POSITION += steps;
                break;
            case 'O':
                for (int i = 1; i<=steps; i++) {
                    Pair comparison = new Pair(X_POSITION + i, Y_POSITION);
                    checkForEqualPair(comparison);
                    VISITED_LOCATIONS.add(new Pair(X_POSITION + i, Y_POSITION));
                }
                if (NOT_EQUAL_PAIR) X_POSITION += steps;
                break;
            case 'S':
                for (int i = 1; i<=steps; i++) {
                    Pair comparison = new Pair(X_POSITION, Y_POSITION - i);
                    checkForEqualPair(comparison);
                    VISITED_LOCATIONS.add(new Pair(X_POSITION, Y_POSITION - i));
                }
                if (NOT_EQUAL_PAIR) Y_POSITION -= steps;
                break;
            case 'W':
                for (int i = 1; i<=steps; i++) {
                    Pair comparison = new Pair(X_POSITION - i, Y_POSITION);
                    checkForEqualPair(comparison);
                    VISITED_LOCATIONS.add(new Pair(X_POSITION - i, Y_POSITION));
                }
                if (NOT_EQUAL_PAIR) X_POSITION -= steps;
                break;
            default:
                break;
        }
    }

    private static void checkForEqualPair(Pair comparison) {
        for (Pair iterator : VISITED_LOCATIONS) {
            if (Pair.equalPair(iterator, comparison)) {
                NOT_EQUAL_PAIR = false;
                X_POSITION = iterator.getX();
                Y_POSITION = iterator.getY();
                return;
            }
        }
    }

    private static void setDefaultValuesToVariables() {
        X_POSITION = 0;
        Y_POSITION = 0;
        DIRECTION = 'N';
    }

    private static void calculateXAndYPosition(ArrayList<String> instructions) {
        for (String iterator : instructions) {
            operateInstruction(iterator);
        }
    }

    private static void operateInstruction(String instruction) {
        int steps = Conveniencer.stringToInt(instruction.substring(1, instruction.length()));
        setDirection(instruction.toUpperCase().charAt(0));
        switch (DIRECTION) {
            case 'N':
                Y_POSITION += steps;
                break;
            case 'O':
                X_POSITION += steps;
                break;
            case 'S':
                Y_POSITION -= steps;
                break;
            case 'W':
                X_POSITION -= steps;
                break;
            default:
                break;
        }
    }

    private static void setDirection(char rotation) {
        switch (DIRECTION) {
            case 'N':
                if (rotation == 'R') DIRECTION = 'O';
                else DIRECTION = 'W';
                break;
            case 'O':
                if (rotation == 'R') DIRECTION = 'S';
                else DIRECTION = 'N';
                break;
            case 'S':
                if (rotation == 'R') DIRECTION = 'W';
                else DIRECTION = 'O';
                break;
            case 'W':
                if (rotation == 'R') DIRECTION = 'N';
                 else DIRECTION = 'S';
                break;
            default:
                break;
        }

    }

    private static ArrayList<String> storeInput(String input) {
        ArrayList<String> instructions = new ArrayList<String>();
        int startIndex = 0;
        for (int i = 0; i<input.length(); i++) {
            if (input.toUpperCase().charAt(i) == 'L' || input.toUpperCase().charAt(i) == 'R') 
            {
                startIndex = i;
                while (true) {
                    i++;
                    if (i == input.length()) break;
                    if (input.charAt(i) == 'R' || input.charAt(i) == 'L') break;
                }
                instructions.add(input.substring(startIndex, i));
                i--;
            }
        }
        return instructions;
    }

    private static String trimInput(String input) {
        input = input.replaceAll(" ", "");
        input = input.replaceAll(",", "");
        input = input.replaceAll("\n", "");
        return input;
    }

    private static int getShortestGridPath(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }
    
}
