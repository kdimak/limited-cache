class DropLessUsedCacheClearanceStrategy implements CacheClearanceStrategy, CacheObserver {

    private CacheAccessStatistics statistics = new CacheAccessStatistics();

    public DropLessUsedCacheClearanceStrategy(LimitedCache limitedCache) {
        limitedCache.addObserver(this);
    }

    public String guessElementToDrop() {
        return statistics.getFirstLessAccessedKey();
    }


    public void elementIsPut(String key, String value) {
        statistics.elementIsPut(key, value);
    }

    public void elementIsAccessed(String key) {
        statistics.elementIsAccessed(key);
    }

}
