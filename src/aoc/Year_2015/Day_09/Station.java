package aoc.Year_2015.Day_09;

import java.util.HashMap;

public class Station {
    private String name;
    private HashMap<String, Integer> destion_distances;

    public Station(String name) {
        this.name = name;
    }

    public void addDestination(String name, int distance) {
        if (this.name.equals(name))
            return;
        destion_distances.put(name, distance);
    }

    public int getDistanceOf(String name) {
        return destion_distances.get(name);
    }

    public HashMap<String, Integer> getDistances() {
        return destion_distances;
    }
}
