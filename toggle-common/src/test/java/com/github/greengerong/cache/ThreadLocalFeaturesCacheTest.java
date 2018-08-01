package com.github.greengerong.cache;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ThreadLocalFeaturesCacheTest {

    private final ThreadLocalFeaturesCache threadLocalFeaturesCache = new ThreadLocalFeaturesCache();

    @Test
    public void should_get_from_loader() throws Exception {
        //given
        final Map<String, Object> feature = new HashMap<>();
        feature.put("count", 0);
        boolean loaderCalled = false;

        //when
        final Map<String, Object> result = threadLocalFeaturesCache.getFeatures(() -> cacheLoader(feature));

        //then
        assertThat(result).isEqualTo(feature);
        assertThat(feature.get("count")).isEqualTo(1);
    }

    @Test
    public void should_get_from_cache() throws Exception {
        //given
        final Map<String, Object> feature = new HashMap<>();
        feature.put("count", 0);
        boolean loaderCalled = false;

        //when
        threadLocalFeaturesCache.getFeatures(() -> cacheLoader(feature));
        final Map<String, Object> result = threadLocalFeaturesCache.getFeatures(() -> cacheLoader(feature));

        //then
        assertThat(result).isEqualTo(feature);
        assertThat(feature.get("count")).isEqualTo(1);
    }

    private Map<String, Object> cacheLoader(Map<String, Object> feature) {
        final Integer count = (Integer) feature.getOrDefault("count", 0);
        feature.put("count", count + 1);
        return feature;
    }
}