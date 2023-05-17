package aoc.Year_2015.Day_7;

import java.util.HashMap;

public class WireCache {
    private static HashMap<String, Integer> cache = new HashMap<>();

    public static void add(String key, int val) {
        if (containsValue(key)) {
            System.out.println("KEY IS ALREADY CONTAINED!"); // DEBUG
            return;
        }
        cache.put(key, val);
    }

    public static int get(String key) {
        return cache.get(key);
    }

    public static boolean containsValue(String key) {
        return cache.containsKey(key);
    }

    public static int getCacheValue(String key) {
        return cache.get(key);
    }
}
