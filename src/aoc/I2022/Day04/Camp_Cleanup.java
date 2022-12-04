package aoc.I2022.Day04;

import java.util.ArrayList;

import aoc.Conveniencer;

public class Camp_Cleanup {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/I2022/Day04/input.txt";

    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);
        ArrayList<String> pairs = Conveniencer.convertTextToLines(input);
        //1
        System.out.println(getAmountOfFullyContainedPairs(pairs));
        //2
        System.out.println(getAmountOfOverlappingPairs(pairs));
    }

    private static int getAmountOfOverlappingPairs(ArrayList<String> pairs) {
        int counter = 0;
        for (String i : pairs) {
            if (isPairAlreadyOverlapping(pairStringToIntArray(i))) {
                counter++;
            }
        }

        return counter;
    }

    private static int getAmountOfFullyContainedPairs(ArrayList<String> pairs) {
        int counter = 0;
        for (String i : pairs) {
            if (isPairAlreadyContained(pairStringToIntArray(i))) {
                counter++;
            }
        }

        return counter;
    }

    /*  int[] ranges:
     *      [0]: start of first range
     *      [1]: end of first range
     *      [2]: start of second range
     *      [3]: end of second range
     * 
     *  following statements are true for all ranges:
     *      ranges.length == 4
     *      [0] <= [1]
     *      [2] <= [3]
     */

    private static int[] pairStringToIntArray(String p) {
        String[] sRanges = p.split("[,\\-]");
        int[]  ranges = new int[sRanges.length];

        for (int i = 0; i<sRanges.length; i++) {
            ranges[i] = Integer.parseInt(sRanges[i]);
        }

        return ranges;
    }

    private static boolean isPairAlreadyOverlapping(int[] ranges) {
        if (ranges[0] >= ranges[2] && ranges[0] <= ranges[3]) return true;
        if (ranges[2] >= ranges[0] && ranges[2] <= ranges[1]) return true;

        return false;
    }

    private static boolean isPairAlreadyContained(int[] ranges) {
        int rangeSize1 = Math.abs(ranges[0] - ranges[1]);
        int rangeSize2 = Math.abs(ranges[2] - ranges[3]);

        if (rangeSize1 > rangeSize2) {
            if (ranges[0] <= ranges[2] && ranges[2] + rangeSize2 <= ranges[1])  return true;
            else return false;
        }

        if (rangeSize1 < rangeSize2) {
            if (ranges[2] <= ranges[0] && ranges[0] + rangeSize1 <= ranges[3]) return true;
            else return false;
        }

        if (ranges[0] == ranges[2]) return true;
        else return false;
        
    }
    
}