package VSCode_Exercises.AdventOfCode.C2017.Day_2;

import java.util.ArrayList;
import java.util.Collections;

import VSCode_Exercises.AdventOfCode.Conveniencer;

public class Corruption_Checksum {
    private static final String DIRECTORY = "VSCode_Exercises/AdventOfCode/C2017/Day_2/input.txt";

    public static void main(String[] args) {
        String input = Conveniencer.getInput(DIRECTORY);
        //Exercise 1
        System.out.println(getChecksumOfHighestAndLowest(input));
        //Exercise 2
        System.out.println(getChecksumOfDivisor(input));

    }

    private static int getChecksumOfDivisor(String input) {
        int checkSum = 0;
        String[] lines = getLines(input);
        //for every line
        for (int i = 0; i<lines.length; i++) {
            ArrayList<Integer> intArr = lineToIntArray(lines[i]);
            Collections.sort(intArr);
            int highDivisor = 0;
            int lowDivisor = 0;
            //for every index
            for (int itterator : intArr) {
                //for every index
                for (int j : intArr) {
                    if (j != itterator)
                        if (j % itterator == 0) {
                            highDivisor = j;
                            lowDivisor = itterator;
                            break;
                        }
                            

                }
            }
            checkSum += highDivisor/lowDivisor;
        }
        return checkSum;
    }

    private static int getChecksumOfHighestAndLowest(String input) {
        int checkSum = 0;
        String[] lines = getLines(input);
        for (int i = 0; i<lines.length; i++) { 
            ArrayList<Integer> intArr = lineToIntArray(lines[i]);
            int localHightest = 0;
            int localLowest = intArr.get(0);
            for (int itterator : intArr) {
                if (localHightest < itterator) localHightest = itterator;
                if (localLowest > itterator) localLowest = itterator;
            }
            checkSum += localHightest-localLowest;
        }
        return checkSum;
    }

    private static ArrayList<Integer> lineToIntArray(String line) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        String number = "";
        for (int i = 0; i<line.length(); i++) {
            if (Character.getNumericValue(line.charAt(i)) >= 0)
            {
                int index = Character.getNumericValue(line.charAt(i));
                number += index;
            } else {
                arrList.add(stringToInt(number));
                number = "";
            }
        }
        arrList.add(stringToInt(number)); //OBOB
        return arrList;
    }

    private static int stringToInt(String s){
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            System.out.println("Entered String can not be parsed to an int value, returned int = 0");
            return 0;
        }
    }

    private static String[] getLines(String input) {
        int lineCount = getLineCount(input);
        String[] lineArray = new String[lineCount];
        int breakIndex = 0;
        int counter = 0;
        while(true) {
            int nextBreak = input.indexOf("\n", breakIndex+1);
            if (nextBreak != -1) {
                lineArray[counter] = input.substring(breakIndex, nextBreak);
                breakIndex = nextBreak+1;
                counter++;
            } else break;
        }
        return lineArray;
    }
    
    private static int getLineCount(String input) {
        int breakIndex = 0;
        int lineCount = 0;
        while (true) {
            int nextBreak = input.indexOf("\n", breakIndex+1);
            if (nextBreak != -1) {
                lineCount++;
                breakIndex = nextBreak;
            } else break;
        }
        return lineCount;
    }
    
}
