package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {

    private final Map<String, Map<Integer, String>> timeMap;

    /**
     * 1. Approach
     * TreeMap
     * 
     * 2. Complexity
     * - Time O(1) for constructor, O(logN) for set and O(logN) for get
     * - Space O(N)
     *
     * 3. Improvement
     * - One small improvement is to use List<Pair<Integer, String>> as the timestamp is guaranteed to come in increasing
     *   order and we could improve the time of set to O(1)
     */
    public TimeBasedKeyValueStore() {
        this.timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        final Map<Integer, String> timeMapWithKey = this.timeMap.getOrDefault(key, new TreeMap<>());
        timeMapWithKey.put(timestamp, value);
        this.timeMap.put(key, timeMapWithKey);
    }

    public String get(String key, int timestamp) {
        final TreeMap<Integer, String> timeMapWithKey = (TreeMap<Integer, String>) this.timeMap.getOrDefault(key, new TreeMap<>());
        final Integer latestTimestamp = timeMapWithKey.floorKey(timestamp);
        if (latestTimestamp == null) return "";
        return timeMapWithKey.get(latestTimestamp);
    }
}
