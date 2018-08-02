package com.github.greengerong;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

import com.github.greengerong.cache.FeaturesCache;
import com.github.greengerong.cache.ThreadLocalFeaturesCache;
import com.github.greengerong.strategy.ToggleStrategyFactory;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ToggleService {

    private final FeaturesFetcher featuresFetcher;
    private final boolean isEnableOnEmpty;
    private FeaturesCache featuresCache;
    private ToggleStrategyFactory toggleStrategyFactory;

    public ToggleService(FeaturesFetcher featuresFetcher) {
        this(featuresFetcher, false);
    }

    public ToggleService(FeaturesFetcher featuresFetcher, boolean isEnableOnEmpty) {
        this.featuresFetcher = featuresFetcher;
        this.isEnableOnEmpty = isEnableOnEmpty;
        toggleStrategyFactory = new ToggleStrategyFactory(new ArrayList<>());
        featuresCache = new ThreadLocalFeaturesCache();
    }

    public void setFeaturesCache(FeaturesCache featuresCache) {
        this.featuresCache = featuresCache;
    }

    public void setToggleStrategyFactory(ToggleStrategyFactory toggleStrategyFactory) {
        this.toggleStrategyFactory = toggleStrategyFactory;
    }

    public boolean isActive(String feature) {
        final Object value = features().get(feature);
        return value == null ? isEnableOnEmpty : toggleStrategyFactory.isActive(feature, value);
    }

    public void toggle(String feature, Runnable enable) {
        toggle(feature, enable, () -> {
        });
    }

    public void toggle(String feature, Runnable enable, Runnable disable) {
        final Runnable runnable = this.isActive(feature) ? enable : disable;
        runnable.run();
    }

    public <T> T toggle(String feature, Supplier<T> enable) {
        return toggle(feature, enable, () -> null);
    }

    public <T> T toggle(String feature, Supplier<T> enable, Supplier<T> disable) {
        final Supplier<T> runnable = this.isActive(feature) ? enable : disable;
        return runnable.get();
    }

    public Map<String, Object> features() {
        return featuresCache.getFeatures(this::loadFeature);
    }

    public Map<String, Object> loadFeature() {
        return featuresFetcher.getFeatures();
    }

}
