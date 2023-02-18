package aoc.A2015.Day_7;

public class Wire {
    private String name;
    private int value;

    public Wire (String name) {
        this.name = name;
        if (name.matches("-?\\d+")) {
            this.value = Integer.parseInt(name);
        } else {
            this.value = -1;
        }
    }


    //getter
    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }
}
