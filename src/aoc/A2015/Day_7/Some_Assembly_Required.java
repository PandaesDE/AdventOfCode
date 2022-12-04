package aoc.A2015.Day_7;

import java.util.ArrayList;
import java.util.HashMap;

import aoc.Conveniencer;

public class Some_Assembly_Required {
    private static final String FILE_PATH = Conveniencer.getProjectPath() + "/src/aoc/A2015/Day_7/input.txt";
    private static final int MAX_BIX = 16;

    private static HashMap<String, WireConnection> wireConnections = new HashMap<>();

    public static void main(String[] args) {
        String input = Conveniencer.getInput(FILE_PATH);
        ArrayList<String> opterations = Conveniencer.convertTextToLines(input); //tree mby better?
        initializeWireConnections(opterations);
        //1
        System.out.println(getWireValue(new Wire("a")));
        //testAllBitOperations(1, 1);
        

    }

    private static int getWireValue(Wire wire) {
        int val = 0;

        if (wireConnections.containsKey(wire.getName())) {
            val = wireConnections.get(wire.getName()).getOutput().getValue();
        } else {
            val = wire.getValue();
        }

        //System.out.println(val); //DEBUG
        if (val != -1) {
            return val; 
        }

        return getOperationValue(wireConnections.get(wire.getName()));
    }

    private static int getOperationValue(WireConnection wc) {
        //System.out.println(wc.toString()); //DEBUG

        if (wc.getOperator() == "AND") {
            return bitwiseAnd(getWireValue(wc.getInput1()), getWireValue(wc.getInput2()));
        }
        if (wc.getOperator() == "OR") {
            return bitwiseOr(getWireValue(wc.getInput1()), getWireValue(wc.getInput2()));
        }
        if (wc.getOperator() == "NOT") {
            return not(getWireValue(wc.getInput1()));
        }
        if (wc.getOperator() == "RSHIFT") {
            return lShift(getWireValue(wc.getInput1()), getWireValue(wc.getInput2()));
        }
        if (wc.getOperator() == "LSHIFT") {
            return rShift(getWireValue(wc.getInput1()), getWireValue(wc.getInput2()));
        }
        return getWireValue(wc.getInput1());
    }

    private static void initializeWireConnections(ArrayList<String> operations) {
        for (String i : operations) {
            initializeWireConnection(i);
        }
    }

    private static void initializeWireConnection(String instruction) {
        String operator = getOperator(instruction);
        String[] inputs = getInputWires(instruction, operator);
        String output = getOutputWire(instruction);

        if (wireConnections.containsKey(output)) {
            if (inputs.length == 1) {
                wireConnections.get(output).setOutput(new Wire(output));
                wireConnections.get(output).setInput1(new Wire(inputs[0]));
                wireConnections.get(output).setOperator(operator);
            } else {
                wireConnections.get(output).setOutput(new Wire(output));
                wireConnections.get(output).setInput1(new Wire(inputs[0]));
                wireConnections.get(output).setInput2(new Wire(inputs[1]));
                wireConnections.get(output).setOperator(operator);
            }
        } else {
            if (inputs.length == 1) {
                wireConnections.put(output, new WireConnection(
                    new Wire(inputs[0]),
                    new Wire(output),
                    operator
                ));
            } else {
                wireConnections.put(output, new WireConnection(
                    new Wire(inputs[0]),
                    new Wire(inputs[1]),
                    new Wire(output),
                    operator
                ));
            }
        }

        //System.out.print(instruction + " | ");
        //
        //for (int i = 0; i<inputs.length; i++) {
        //    System.out.print(inputs[i] + " ");
        //}
        //
        //System.out.println("| " + operator + " | " + output);
    }

    private static String getOutputWire(String instruction) {
        instruction = instruction.replaceAll(" ", "");
        return instruction.substring(instruction.indexOf(">")+1, instruction.length());
    }

    /*
     * returns stringarray with wires and values of the left hand side of the operation
     */
    private static String[] getInputWires(String instruction, String operator) {
        instruction = instruction.substring(0, instruction.indexOf("-"));
        instruction = instruction.replaceAll(" ", "");
        if (operator == "NOT" || operator == "EQUALS") {
            return new String[] {instruction.replaceAll(operator, "")};
        }
        return instruction.split(operator);
    }


    /*
     * only one operator possible per instruction!
     */
    private static String getOperator(String instruction) {
        if (instruction.contains("AND")) return "AND";
        if (instruction.contains("OR")) return "OR";
        if (instruction.contains("NOT")) return "NOT";
        if (instruction.contains("RSHIFT")) return "RSHIFT";
        if (instruction.contains("LSHIFT")) return "LSHIFT";
        return "EQUALS";
    }


    private static int not(int a) {return (int) (Math.pow(2, MAX_BIX) - 1) - a;}

    private static int lShift(int a, int shift) {return a << shift;}

    private static int rShift(int a, int shift) {return a >> shift;}

    private static int bitwiseOr(int a, int b) {return a | b;}

    private static int bitwiseAnd(int a, int b) {return a & b;}
}
