package com.github.greengerong.cache;

import java.util.Map;
import java.util.function.Supplier;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ThreadLocalFeaturesCache implements FeaturesCache {
    private final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    @Override
    public Map<String, Object> getFeatures(Supplier<Map<String, Object>> loader) {
        Map<String, Object> features = threadLocal.get();
        if (features == null) {
            features = loader.get();
            threadLocal.set(features);
        }
        return features;
    }
}
