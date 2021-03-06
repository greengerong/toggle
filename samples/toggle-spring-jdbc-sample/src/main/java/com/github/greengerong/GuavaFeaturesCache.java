package com.github.greengerong;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.github.greengerong.cache.FeaturesCache;
import com.github.greengerong.exception.GuavaFeaturesCacheException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Component
public class GuavaFeaturesCache implements FeaturesCache {

    private final Cache cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();

    @Override
    public Map<String, Object> getFeatures(Supplier<Map<String, Object>> loader) {
        try {
            return (Map<String, Object>) cache.get("features", () -> loader.get());
        } catch (ExecutionException e) {
            throw new GuavaFeaturesCacheException(e);
        }
    }
}
