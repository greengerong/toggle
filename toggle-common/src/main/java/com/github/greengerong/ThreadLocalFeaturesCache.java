package com.github.greengerong;

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
    private static final ThreadLocal<Map<String, Boolean>> threadLocal = new ThreadLocal<>();

    @Override
    public Map<String, Boolean> getFeatures(Supplier<Map<String, Boolean>> loader) {
        Map<String, Boolean> features = threadLocal.get();
        if (features == null) {
            features = loader.get();
            threadLocal.set(features);
        }
        return features;
    }
}
