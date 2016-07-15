import java.util.HashMap;
import java.util.Map;

public class CacheAccessStatistics implements CacheObserver {

    private Map<String, Integer> access = new HashMap<>();

    public void elementIsPut(String key, String value) {
        Integer count = access.get(key);
        if (count == null) {
            access.put(key, 0);
        }
    }

    public void elementIsAccessed(String key) {
        Integer count = access.get(key);
        if (count != null) {
            access.put(key, count + 1);
        }
    }

    public String getFirstLessAccessedKey() {
        String keyToRemove = null;
        int minimumVisits = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> visitedEntry : access.entrySet()) {
            int visitsCount = visitedEntry.getValue();
            if (minimumVisits > visitsCount) {
                minimumVisits = visitsCount;
                keyToRemove = visitedEntry.getKey();
            }
        }
        return keyToRemove;
    }

}
