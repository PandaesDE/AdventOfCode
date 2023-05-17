package aoc.A2015.Day_7;


//Each wire can only get a signal from one source, but can provide its signal to multiple destinations. 
//A gate provides no signal until all of its inputs have a signal.

public class WireConnection {
    private Wire input1;
    private Wire input2;
    private Wire output;
    private String operator;

    public WireConnection (Wire input1, Wire input2, Wire output, String operator) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.operator = operator;
    }

    public WireConnection (Wire input, Wire output, String operator) {
        this.input1 = input;
        this.input2 = null;
        this.output = output;
        this.operator = operator;
    }


    @Override
    public String toString() {
        String ipt2 = "null";
        if (input2 != null) {
            ipt2 = this.input2.getName();
        }
        return "{" +
            "output='" + getOutput().getName() + "'" +
            ", input1='" + getInput1().getName() + "'" +
            ", input2='" + ipt2 + "'" +
            ", operator='" + getOperator() + "'" +
            "}";
    }

    public Wire getInput1() {
        return this.input1;
    }

    public void setInput1(Wire input1) {
        this.input1 = input1;
    }

    public Wire getInput2() {
        return this.input2;
    }

    public void setInput2(Wire input2) {
        this.input2 = input2;
    }

    public Wire getOutput() {
        return this.output;
    }

    public void setOutput(Wire output) {
        this.output = output;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
