import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LimitedCacheTest {

    private LimitedCache cache;

    @Before
    public void before() {
        cache = new LimitedCache(2);
    }

    @Test
    public void keepsSingleElement() {
        cache.put("key", "value");

        String value = cache.get("key");
        assertThat(value).isEqualTo("value");
    }

    @Test
    public void keepsSeveralElements() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertThat(cache.get("key1")).isEqualTo("value1");
        assertThat(cache.get("key2")).isEqualTo("value2");
    }

    @Test
    public void dropsLessAccessedElementInCaseOfOverloading() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        cache.get("key1");
        cache.get("key2");
        cache.get("key2");

        cache.put("key3", "value3");

        assertThat(cache.get("key1")).isNull();
        assertThat(cache.get("key2")).isEqualTo("value2");
        assertThat(cache.get("key3")).isEqualTo("value3");
    }

    @Test
    public void updatesValueOfTheSameKey() {
        cache.put("key", "value1");
        cache.put("key", "value2");

        assertThat(cache.get("key")).isEqualTo("value2");
    }

}