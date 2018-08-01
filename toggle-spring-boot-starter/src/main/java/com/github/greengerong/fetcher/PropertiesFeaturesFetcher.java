package com.github.greengerong.fetcher;

import java.util.Map;

import com.github.greengerong.FeaturesFetcher;
import com.github.greengerong.config.ToggleConfig;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

public class PropertiesFeaturesFetcher implements FeaturesFetcher {

    private final ToggleConfig toggleConfig;

    public PropertiesFeaturesFetcher(ToggleConfig toggleConfig) {
        this.toggleConfig = toggleConfig;
    }

    @Override
    public Map<String, Object> getFeatures() {
        return toggleConfig.getFeatures();
    }
}
