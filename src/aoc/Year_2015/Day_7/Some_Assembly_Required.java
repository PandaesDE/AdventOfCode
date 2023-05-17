package aoc.Year_2015.Day_7;

import java.util.ArrayList;
import java.util.HashMap;

import aoc.Conveniencer;

public class Some_Assembly_Required {
    private static final int MAX_BIX = 16;

    private static HashMap<String, WireConnection> wireConnections = new HashMap<>();

    public static void main(String[] args) {
        String input = Conveniencer.getInput(2015, 7);
        ArrayList<String> opterations = Conveniencer.convertTextToLines(input); // tree mby better?
        initializeWireConnections(opterations);
        // 1
        // System.out.println(getWireValue(new Wire("a")));
        // printWireConnections(); //DEBUG
        // 2
        addWireConnection("b", new String[] { "3176" }, "EQUALS");
        System.out.println(getWireValue(new Wire("a")));

    }

    /*
     * private static void printWireConnections() { //DEBUG
     * for (String key : wireConnections.keySet()) {
     * System.out.println(wireConnections.get(key).toString());
     * }
     * }
     */

    private static int getWireValue(Wire wire) {
        if (wireConnections.containsKey(wire.getName())) {
            if (WireCache.containsValue(wire.getName())) {
                return WireCache.get(wire.getName());
            }
            WireCache.add(wire.getName(), getOperationValue(wireConnections.get(wire.getName())));
            return getOperationValue(wireConnections.get(wire.getName()));
        }
        return wire.getValue();
    }

    private static int getOperationValue(WireConnection wc) {
        System.out.println(wc.toString()); // DEBUG

        if (wc.getOperator() == "AND") {
            return getWireValue(wc.getInput1()) & getWireValue(wc.getInput2());
        }
        if (wc.getOperator() == "OR") {
            return getWireValue(wc.getInput1()) | getWireValue(wc.getInput2());
        }
        if (wc.getOperator() == "NOT") {
            return (int) (Math.pow(2, MAX_BIX) - 1) - getWireValue(wc.getInput1());
        }
        if (wc.getOperator() == "RSHIFT") {
            return getWireValue(wc.getInput1()) >> getWireValue(wc.getInput2());
        }
        if (wc.getOperator() == "LSHIFT") {
            return getWireValue(wc.getInput1()) << getWireValue(wc.getInput2());
        }
        // EQUALS (destinction between wire and value)
        if (wireConnections.containsKey(wc.getInput1().getName())) {
            return getWireValue(wc.getInput1());
        }
        return wc.getInput1().getValue();
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
            wireConnections.get(output).setOutput(new Wire(output));
            wireConnections.get(output).setInput1(new Wire(inputs[0]));
            wireConnections.get(output).setInput2(null);
            wireConnections.get(output).setOperator(operator);

            if (inputs.length == 2) {
                wireConnections.get(output).setInput2(new Wire(inputs[1]));
            }
            return;
        }
        addWireConnection(output, inputs, operator);
        /*
         * System.out.print(instruction + " | ");
         * 
         * for (int i = 0; i<inputs.length; i++) {
         * System.out.print(inputs[i] + " ");
         * }
         * 
         * System.out.println("| " + operator + " | " + output);
         */
    }

    private static void addWireConnection(String output, String[] inputs, String operator) {
        if (inputs.length == 1) {
            wireConnections.put(output, new WireConnection(
                    new Wire(inputs[0]),
                    new Wire(output),
                    operator));
        } else {
            wireConnections.put(output, new WireConnection(
                    new Wire(inputs[0]),
                    new Wire(inputs[1]),
                    new Wire(output),
                    operator));
        }
    }

    private static String getOutputWire(String instruction) {
        instruction = instruction.replaceAll(" ", "");
        return instruction.substring(instruction.indexOf(">") + 1, instruction.length());
    }

    /*
     * returns stringarray with wires and values of the left hand side of the
     * operation
     */
    private static String[] getInputWires(String instruction, String operator) {
        instruction = instruction.substring(0, instruction.indexOf("-"));
        instruction = instruction.replaceAll(" ", "");
        if (operator.equals("NOT") || operator.equals("EQUALS")) {
            return new String[] { instruction.replaceAll(operator, "") };
        }
        return instruction.split(operator);
    }

    /*
     * only one operator possible per instruction!
     */
    private static String getOperator(String instruction) {
        if (instruction.contains("AND"))
            return "AND";
        if (instruction.contains("OR"))
            return "OR";
        if (instruction.contains("NOT"))
            return "NOT";
        if (instruction.contains("RSHIFT"))
            return "RSHIFT";
        if (instruction.contains("LSHIFT"))
            return "LSHIFT";
        return "EQUALS";
    }
}
