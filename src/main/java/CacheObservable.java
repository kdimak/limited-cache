import java.util.ArrayList;
import java.util.Collection;

public class CacheObservable  {

    private Collection<CacheObserver> observers = new ArrayList<>();

    public void addObserver(CacheObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CacheObserver observer) {
        observers.remove(observer);
    }

    protected void notifyElementIsPut(final String key, final String value) {
        observers.forEach(o -> o.elementIsPut(key, value));
    }

    protected void notifyElementIsGet(String key) {
        observers.forEach(o -> o.elementIsAccessed(key));
    }
}
