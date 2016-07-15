import java.util.HashMap;
import java.util.Map;

public class LimitedCache extends CacheObservable {
    private int capacity;
    private Map<String, String> content = new HashMap<>();
    private CacheClearanceStrategy strategy = new DropLessUsedCacheClearanceStrategy(this);

    public LimitedCache(int capacity) {
        this.capacity = capacity;
    }

    public void setStrategy(CacheClearanceStrategy strategy) {
        this.strategy = strategy;
    }

    public void put(String key, String value) {
        if (isNewElement(key) && isFull()) {
            dropTheLeastVisitedElement();
        }
        content.put(key, value);
        notifyElementIsPut(key, value);
    }

    public String get(String key) {
        if (content.containsKey(key)) {
            notifyElementIsGet(key);
            return content.get(key);
        }
        return null;
    }

    private boolean isNewElement(String key) {
        return !content.containsKey(key);
    }

    private boolean isFull() {
        return content.size() == capacity;
    }

    private void dropTheLeastVisitedElement() {
        String keyToRemove = strategy.guessElementToDrop();
        content.remove(keyToRemove);
    }
}
