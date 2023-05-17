package aoc.Year_2015.Day_02;

import java.util.ArrayList;

import aoc.Conveniencer;

public class I_Was_Told_There_Would_Be_No_Math {

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2015, 2);
        // Exercise 1
        System.out.println(getTotalDimension(Conveniencer.convertTextToLines(input)));
        // Exercise 2
        System.out.println(getTotalRibonLength(Conveniencer.convertTextToLines(input)));
    }

    private static int getTotalRibonLength(ArrayList<String> input) {
        int totalLength = 0;
        for (String iterator : input) {
            int[] arr = convertStringDimensionToIntArray(iterator);
            totalLength += getRibonLengthPerPresent(arr[0], arr[1], arr[2]);
        }
        return totalLength;
    }

    private static int getRibonLengthPerPresent(int w, int h, int l) {
        int shortestDistanceWrap = 0;
        if (w >= h && w >= l)
            shortestDistanceWrap = 2 * h + 2 * l;
        else if (h >= w && h >= l)
            shortestDistanceWrap = 2 * w + 2 * l;
        else
            shortestDistanceWrap = 2 * w + 2 * h;
        int bowLength = w * h * l;
        return shortestDistanceWrap + bowLength;
    }

    private static int getTotalDimension(ArrayList<String> input) {
        int totalDimension = 0;
        for (String iterator : input) {
            int[] arr = convertStringDimensionToIntArray(iterator);
            totalDimension += getSurfaceArea(arr[0], arr[1], arr[2]) + getSlack(arr[0], arr[1], arr[2]);
        }

        return totalDimension;
    }

    private static int[] convertStringDimensionToIntArray(String dimensions) {
        int[] arr = new int[3];
        int x1 = dimensions.indexOf("x");
        int x2 = dimensions.indexOf("x", x1 + 1);
        arr[0] = stringToInt(dimensions.substring(0, x1));
        arr[1] = stringToInt(dimensions.substring(x1 + 1, x2));
        arr[2] = stringToInt(dimensions.substring(x2 + 1, dimensions.length()));
        return arr;
    }

    private static int stringToInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            System.out.println("Entered String can not be parsed to an int value, returned int = 0");
            return 0;
        }
    }

    private static int getSlack(int w, int h, int l) {
        int smallestSide = l * w;
        if (smallestSide > w * h)
            smallestSide = w * h;
        if (smallestSide > h * l)
            smallestSide = h * l;
        return smallestSide;
    }

    private static int getSurfaceArea(int w, int h, int l) {
        return (2 * l * w) + (2 * w * h) + (2 * h * l);

    }
}
