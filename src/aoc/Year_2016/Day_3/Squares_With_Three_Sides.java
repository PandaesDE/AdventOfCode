package aoc.Year_2016.Day_3;

import java.util.ArrayList;

import aoc.Conveniencer;

public class Squares_With_Three_Sides {

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2016, 3);
        // Exercise 1
        System.out.println(getTriangleAmount(input));
        // Exercise 2
        System.out.println(getVerticalTriangleAmount(input));

    }

    private static int getVerticalTriangleAmount(String input) {
        int counter = 0;
        ArrayList<String> triangles = Conveniencer.convertTextToLines(input);
        for (int i = 0; i < triangles.size() / 3; i++) {
            int[][] threeSides = rearangeLines(getTriangleSides(triangles.get(i * 3)),
                    getTriangleSides(triangles.get((i * 3) + 1)), getTriangleSides(triangles.get((i * 3) + 2)));
            for (int j = 0; j < 3; j++) {
                int[] sides = threeSides[j];
                if (checkForValidTriangle(sides))
                    counter++;
            }
        }

        return counter;
    }

    private static int[][] rearangeLines(int[] lineOne, int[] lineTwo, int[] lineThree) {
        int[][] rearangedLines = new int[3][3];
        // 0-2: line_1, 3-5: line_2, 6-7: line_3
        int[] newLines = new int[9];
        // line one
        newLines[0] = lineOne[0];
        newLines[1] = lineTwo[0];
        newLines[2] = lineThree[0];
        // line two
        newLines[3] = lineOne[1];
        newLines[4] = lineTwo[1];
        newLines[5] = lineThree[1];
        // line three
        newLines[6] = lineOne[2];
        newLines[7] = lineTwo[2];
        newLines[8] = lineThree[2];
        // safe in 2dArray
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                rearangedLines[i][j] = newLines[(i * 3) + j];
        return rearangedLines;
    }

    private static int getTriangleAmount(String input) {
        int counter = 0;
        ArrayList<String> triangles = Conveniencer.convertTextToLines(input);
        for (String iterator : triangles) {
            int[] sides = getTriangleSides(iterator);
            if (checkForValidTriangle(sides))
                counter++;
        }
        return counter;
    }

    private static int[] getTriangleSides(String input) {
        int[] sides = new int[3];
        int side = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                int startIndex = i;
                while (true) {
                    i++;
                    if (i == input.length())
                        break;
                    if (input.charAt(i) == ' ')
                        break;
                }
                sides[side] = Conveniencer.stringToInt(input.substring(startIndex, i));
                side++;
                i--;
            }
        }
        return sides;
    }

    private static boolean checkForValidTriangle(int[] sides) {
        if (sides[0] + sides[1] <= sides[2])
            return false;
        if (sides[1] + sides[2] <= sides[0])
            return false;
        if (sides[2] + sides[0] <= sides[1])
            return false;
        return true;
    }
}
