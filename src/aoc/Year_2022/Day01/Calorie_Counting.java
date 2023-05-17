package aoc.Year_2022.Day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.Conveniencer;

public class Calorie_Counting {
    private static HashMap<Integer, Integer> cals = new HashMap<Integer, Integer>();

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2022, 1);
        initializeCals(Conveniencer.convertTextToLines(input));
        sortCals();
        // 1
        System.out.println(getCaloriesOfElvesWithMostSnacks(1));
        // 2
        System.out.println(getCaloriesOfElvesWithMostSnacks(3));
    }

    public static void sortCals() {
        cals = cals.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
    }

    private static void initializeCals(ArrayList<String> lines) {
        int elve = 0;
        for (String i : lines) {
            if (i.length() == 0) {
                elve++;
                continue;
            }
            if (cals.containsKey(elve)) {
                cals.replace(elve, cals.get(elve) + Conveniencer.stringToInt(i));
            } else {
                cals.put(elve, Conveniencer.stringToInt(i));
            }
        }
    }

    private static int getCaloriesOfElvesWithMostSnacks(int elves) {
        int rCals = 0;
        int iteration = 0;
        for (int i : cals.values()) {
            if (iteration == elves)
                return rCals;
            iteration++;
            rCals += i;
        }

        return rCals;
    }

}
