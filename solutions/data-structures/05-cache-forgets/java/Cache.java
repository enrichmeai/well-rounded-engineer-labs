// Drill 05, solved: accessOrder = true, so every get and put moves the entry to the most recent end.

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cache<K, V> extends LinkedHashMap<K, V> {
    final int capacity;
    final List<K> evicted = new ArrayList<>();

    Cache(int capacity) {
        super(16, 0.75f, true);                  // true = access order: least recently used first
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        boolean full = size() > capacity;
        if (full) evicted.add(eldest.getKey());
        return full;
    }
}
