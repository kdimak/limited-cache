import java.util.HashMap;
import java.util.Map;

public class LimitedCache {

    private int capacity;
    private Map<String, String> values = new HashMap<String, String>();
    private Map<String, Integer> visited = new HashMap<String, Integer>();

    private CacheClearanceStrategy strategy = new CacheClearanceStrategy() {
        public String guessElementToDrop() {
            String keyToRemove = null;
            int minimumVisits = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> visitedEntry : visited.entrySet()) {
                int visitsCount = visitedEntry.getValue();
                if (minimumVisits > visitsCount) {
                    minimumVisits = visitsCount;
                    keyToRemove = visitedEntry.getKey();
                }
            }
            return keyToRemove;
        }
    };

    public LimitedCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(String key, String value) {
        if (!values.containsKey(key)) {
            if (isFull()) {
                dropLessVisitedElement();
            }
        }
        values.put(key, value);
        visited.put(key, 0);
    }

    public String get(String key) {
        if (values.containsKey(key)) {
            visited(key);
            return values.get(key);
        }
        return null;
    }

    private void visited(String key) {
        Integer visitsCount = visited.get(key);
        visited.put(key, visitsCount + 1);
    }

    public boolean isFull() {
        return values.size() == capacity;
    }

    private void dropLessVisitedElement() {
        String keyToRemove = strategy.guessElementToDrop();
        values.remove(keyToRemove);
        visited.remove(keyToRemove);
    }

}
