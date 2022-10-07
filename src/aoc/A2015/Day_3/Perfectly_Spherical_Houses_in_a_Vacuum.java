package VSCode_Exercises.AdventOfCode.A2015.Day_3;


import java.util.ArrayList;

import VSCode_Exercises.AdventOfCode.Conveniencer;

public class Perfectly_Spherical_Houses_in_a_Vacuum {
    private static final String DIRECTORY = "VSCode_Exercises/AdventOfCode/A2015/Day_3/input.txt";
    
    public static void main(String[] args) {
        String input = Conveniencer.getInput(DIRECTORY);
        //Exercise 1
        System.out.println(getTotalHouseAmount(input));
        //Exercise 2
        System.out.println(getTotalHouseAmountWithRoboSanta(input));
    }

    private static int getTotalHouseAmountWithRoboSanta(String instructions) {
        ArrayList<Pair> housesWithPresent = new ArrayList<Pair>();
        int santaX = 0;
        int santaY = 0;
        int roboX = 0;
        int roboY = 0;
        for (int i = 0; i<instructions.length()-1; i++) {
            if (i % 2 == 0) {
                santaX += getXInstruction(instructions.charAt(i));
                santaY += getYInstruction(instructions.charAt(i));
                housesWithPresent = addNonDuplicatePair(housesWithPresent, santaX, santaY);
            } else {
                roboX += getXInstruction(instructions.charAt(i));
                roboY += getYInstruction(instructions.charAt(i));
                housesWithPresent = addNonDuplicatePair(housesWithPresent, roboX, roboY);
            }
        }
        return housesWithPresent.size();
    }

    private static ArrayList<Pair> addNonDuplicatePair(ArrayList<Pair> ndl, int x, int y) {
        boolean add = true;
        Pair comparison = new Pair(x, y);
        for (Pair iterator : ndl) {
            if (Pair.equalPair(iterator, comparison))
            add = false;
        }
        if (add) ndl.add(new Pair(x, y));
        return ndl;
    }

    private static int getTotalHouseAmount(String instructions) {
        ArrayList<Pair> housesWithPresent = new ArrayList<Pair>();
        housesWithPresent.add(new Pair(0,0)); //starting value
        int x = 0;
        int y = 0;

        for (int i = 0; i<instructions.length()-1; i++) {
            x += getXInstruction(instructions.charAt(i));
            y += getYInstruction(instructions.charAt(i));
            boolean add = true;
            Pair comparison = new Pair(x, y);
            for (Pair iterator : housesWithPresent) {
                if (Pair.equalPair(iterator, comparison))
                add = false;
            }
            if (add) housesWithPresent.add(new Pair(x, y));
            
        }
        return housesWithPresent.size();
    }

    private static int getYInstruction(char instruction) {
        switch(instruction) {
            case '^':
                return 1;
            case 'v':
                return -1;
            default:
                return 0;
        }
    }

    private static int getXInstruction(char instruction) {
        switch(instruction) {
            case '>':
                return 1;
            case '<':
                return -1;
            default:
                return 0;
        }
    }

}
