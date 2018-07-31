package com.github.greengerong;

import java.util.Map;

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
    public Map<String, Boolean> getFeatures() {
        return toggleConfig.getFeatures();
    }
}
