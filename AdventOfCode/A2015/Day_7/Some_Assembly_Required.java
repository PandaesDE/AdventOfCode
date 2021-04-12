package VSCode_Exercises.AdventOfCode.A2015.Day_7;

import java.util.ArrayList;
import java.util.HashMap;

import VSCode_Exercises.AdventOfCode.Conveniencer;

public class Some_Assembly_Required {
    private static final String DIRECTORY = "VSCode_Exercises/AdventOfCode/A2015/Day_7/input.txt";
    private static final int MAX_BIX = 16;
    public static void main(String[] args) {
        String input = Conveniencer.getInput(DIRECTORY);
        //Exercise 1
        System.out.println(getSignalOfWire(input, "a"));
        //testAllBitOperations(1, 1);
        

    }

    private static int getSignalOfWire(String signals, String wire) {
        HashMap<String,Integer> valueStorage = new HashMap<String, Integer>();
        ArrayList<String> lines = Conveniencer.convertTextToLines(signals);
        for (String iString : lines) {
            String operator = getOperator(iString);
            String[] variables = getVariables(iString, operator);
            valueStorage = storeValues(operator, variables, valueStorage);
            valueStorage = makeOperation(operator, variables, valueStorage);
        }
        System.out.println(valueStorage);

        try {return valueStorage.get(wire);}
        catch(Exception e) {return -1;}
    }

    private static HashMap<String,Integer> storeValues(String operator, String[] variables, HashMap<String, Integer> storage) {
        for (int i = 0; i<variables.length; i++) {
            if (!storage.containsKey(variables[i])) 
                if (!stringIsInt(variables[i])) storage.put(variables[i], 0);
        }
        return storage;
    }

    private static HashMap<String,Integer> makeOperation(String operator, String[] variables, HashMap<String, Integer> storage) {
        String output = variables[variables.length-1];
        switch (operator.toUpperCase()) {
            case "AND":
                if (!stringIsInt(variables[0]) && !stringIsInt(variables[1]))
                    storage.replace(output, bitwiseAnd(storage.get(variables[0]), storage.get(variables[1])));
                break;
            case "OR":
                if (!stringIsInt(variables[0]) && !stringIsInt(variables[1]))
                    storage.replace(output, bitwiseOr(storage.get(variables[0]), storage.get(variables[1])));
                break;
            case "RSHIFT":
                if (!stringIsInt(variables[0]) && !stringIsInt(variables[1]))
                    storage.replace(output, rShift(storage.get(variables[0]), storage.get(variables[1])));
                break;
            case "LSHIFT":
                if (!stringIsInt(variables[0]) && !stringIsInt(variables[1]))
                    storage.replace(output, lShift(storage.get(variables[0]), storage.get(variables[1])));
                break;
            case "NOT":
                if (!stringIsInt(variables[0]))
                    storage.replace(output, not(storage.get(variables[0]), MAX_BIX));
                break;
            case "EQUALS":
                if (!stringIsInt(variables[0]))
                    storage.replace(output, storage.get(variables[0]));
                break;
        }
        return storage;
    }

    private static boolean stringIsInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String[] getVariables(String line, String operator) {
        line = line.replaceAll(operator, ",");
        line = line.replaceAll(" ", "");
        line = line.replaceAll("->", ",");
        if (line.charAt(0) == ',') line = line.substring(1, line.length());
        return line.split(",");
    } 

    private static String getOperator(String line) {
        String operator = "";
        for (int i = 0; i<line.length(); i++) {
            if (line.charAt(i) >= 'A' && line.charAt(i) <= 'Z') {
                operator += line.charAt(i);
            }
        }
        if (operator.equals("")) operator = "EQUALS";
        return operator;
    }
 
    private static void testAllBitOperations(int x, int y) {
        int maxBits = MAX_BIX;
        System.out.println("x: " + x + " y: " + y
                         + "\nAnd: " + bitwiseAnd(x, y) 
                         + "\nOr: " + bitwiseOr(x, y) 
                         + "\nrShift: " + rShift(x, y)
                         + "\nlShift: " + lShift(x, y)
                         + "\nnotX: " + not(x, maxBits)
                         + "\nnotY: " + not(y, maxBits));
    }

    private static int not(int a, int maxBit) {return (int) (Math.pow(2, maxBit) - 1) - a;}

    private static int lShift(int a, int shift) {return a << shift;}

    private static int rShift(int a, int shift) {return a >> shift;}

    private static int bitwiseOr(int a, int b) {return a | b;}

    private static int bitwiseAnd(int a, int b) {return a & b;}


}
