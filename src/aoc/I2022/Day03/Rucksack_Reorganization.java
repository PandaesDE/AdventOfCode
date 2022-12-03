package aoc.I2022.Day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.plaf.basic.BasicTextUI.BasicCaret;

import aoc.Conveniencer;

public class Rucksack_Reorganization {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/I2022/Day03/input.txt";

    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);
        ArrayList<String> backpacks = Conveniencer.convertTextToLines(input);
        //1
        System.out.println(getSumOfPriorities(backpacks));
        //2
        System.out.println(getGroupPrioritySum(backpacks));

    }

    private static int getGroupPrioritySum(ArrayList<String> backpacks) {
        int prioritysum = 0;
        int iteration = 0;
        String bp1 = "";
        String bp2 = "";
        String bp3 = "";
        for (String i : backpacks) {
            if (iteration % 3 == 0) {
                bp1 = i;
            } else if (iteration % 3 == 1) {
                bp2 = i;    
            } else if (iteration % 3 == 2) {
                bp3 = i;
                prioritysum += evaluateCharPriority(getCommonCharacter(bp1, bp2, bp3));
            }

            iteration++;
        }
        return prioritysum;
    }

    private static int getSumOfPriorities(ArrayList<String> backpacks) {
        int prioritySum = 0;
        ArrayList<String> compartments1 = new ArrayList<>();
        ArrayList<String> compartments2 = new ArrayList<>();
        for (String i : backpacks) {
            String a = i.substring(0, i.length()/2);
            String b = i.substring(i.length()/2, i.length());
            compartments1.add(a);
            compartments2.add(b);
            Set<Character> commonCharacters = getCommonCharacter(a, b);
            for (char c : commonCharacters) {
                prioritySum += evaluateCharPriority(c);
            }
        }
        return prioritySum;
    }

    private static int evaluateCharPriority(char c) {
        int priority = 0;
        if (Character.isUpperCase(c)) {
            priority = Character.getNumericValue(c) + 17;
        } else {
            priority = Character.getNumericValue(c) - 9;
        }
        return priority;
    }

    private static Set<Character> getCommonCharacter(String a, String b) {
        Set<Character> commonCharacters = new HashSet<Character>() {
        };
        for (int i = 0; i<a.length(); i++) {
            for (int j = 0; j<b.length(); j++) {
                if (a.charAt(i) == b.charAt(j))  {
                    commonCharacters.add(a.charAt(i));
                }
            }
        }
        return commonCharacters;
    }

    private static char getCommonCharacter(String a, String b, String c) {
        char cc = ' ';
        Set<Character> commonCharacters = getCommonCharacter(a, b);
        for (char d : commonCharacters) {
            if (c.contains(d + "")) {
                return c.charAt(c.indexOf(d));
            }
        }
        return cc;
    }
}
