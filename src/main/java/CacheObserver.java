public interface CacheObserver {
    void elementIsPut(String key, String value);
    void elementIsAccessed(String key);
}
