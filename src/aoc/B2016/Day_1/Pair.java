package aoc.B2016.Day_1;

public class Pair {
    private int x = 0;
    private int y = 0;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean equalPair (Pair a, Pair b) {
        if (a.getX() == b.getX() && a.getY() == b.getY()) return true;
        return false;
    }

    public String toString () { return this.x + " " + this.y;}

    //setter
    public void setPair (int x, int y) {this.x = x; this.y = y;}
    public void setX (int x) {this.x = x;}
    public void setY (int y) {this.y = y;}

    //getter
    public int getX () {return x;}
    public int getY () {return y;}
    
}
