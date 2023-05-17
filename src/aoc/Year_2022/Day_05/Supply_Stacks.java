package aoc.Year_2022.Day_05;

import java.util.ArrayList;
import java.util.Stack;

import aoc.Conveniencer;

public class Supply_Stacks {
    private static final int MODEL_NUMBER = 9001; // 9000 for first task, 9001 for second task

    private static ArrayList<Stack<Character>> stacks;

    public static void main(String[] args) {
        ArrayList<String> crates = Conveniencer.convertTextToLines(Conveniencer.getInput(2022, 5, "input_crates.txt"));
        ArrayList<String> instructions = Conveniencer
                .convertTextToLines(Conveniencer.getInput(2022, 5, "input_instructions.txt"));

        // 1 (MODEL_NUMBER == 9000), 2 (MODEL_NUMBER == 9001)
        initializeStacks(crates);
        // printAllStacks();
        // printTopContainerOfStacks();
        followInstructions(instructions);
        // printAllStacks();
        printTopContainerOfStacks();
    }

    private static void followInstructions(ArrayList<String> instructions) {
        for (String i : instructions) {
            followInstruction(interpreteInstruction(i), MODEL_NUMBER);
        }
    }

    private static void followInstruction(int[] instructionValues, int modelNumber) {
        if (modelNumber == 9000) {
            for (int i = 0; i < instructionValues[0]; i++) {
                stacks.get(instructionValues[2] - 1).push(
                        stacks.get(instructionValues[1] - 1).pop());
            }
        }
        if (modelNumber == 9001) {
            char[] container = new char[instructionValues[0]];
            for (int i = 0; i < instructionValues[0]; i++) {
                container[i] = stacks.get(instructionValues[1] - 1).pop();
            }
            for (int i = instructionValues[0] - 1; i > -1; i--) {
                stacks.get(instructionValues[2] - 1).push(container[i]);
            }
        }
    }

    /*
     * values[].length == 3
     * [0] value of amount
     * [1] value of stackindex(+1) taken containers from
     * [2] value of stackindex(+1) taking containers to
     */
    private static int[] interpreteInstruction(String instruction) {
        instruction = instruction.replace("move ", "");
        instruction = instruction.replace("from ", "");
        instruction = instruction.replace("to ", "");
        String[] stringValues = instruction.split(" ");
        int[] values = new int[stringValues.length];
        for (int i = 0; i < stringValues.length; i++) {
            values[i] = Conveniencer.stringToInt(stringValues[i]);
        }
        return values;
    }

    private static void initializeStacks(ArrayList<String> crates) {
        stacks = new ArrayList<Stack<Character>>();
        for (int i = crates.size() - 1; i > -1; i--) {
            if (i != crates.size() - 1) {
                int crateI = 0;
                while (true) {
                    crateI = crates.get(i).indexOf("[", crateI);
                    if (crateI != -1)
                        crateI++;
                    else
                        break;
                    addToStack(crateI / 4, crates.get(i).charAt(crateI));
                }
            }
        }
    }

    private static void addToStack(int index, char c) {
        if (stacks.size() <= index) {
            stacks.add(new Stack<>());
        }
        stacks.get(index).push(c);
    }

    private static void printTopContainerOfStacks() {
        for (Stack<Character> i : stacks) {
            System.out.print(i.peek());
        }
        System.out.println();
    }

    // #region DEBUG
    /*
     * private static void printAllStacks() {
     * for (Stack<Character> i : stacks) {
     * System.out.println(i.toString());
     * }
     * System.out.println("--");
     * }
     */
    // #endregion
}
