package com.github.greengerong;

import java.util.HashMap;
import java.util.Map;

import greengerong.FeaturesFetcher;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

@ConfigurationProperties("feature-toggle")
@RefreshScope
public class ToggleConfig implements FeaturesFetcher {

    private boolean enableOnEmpty = true;

    private Map<String, Boolean> features = new HashMap<>();

    @Override
    public Map<String, Boolean> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Boolean> features) {
        this.features = features;
    }

    @Override
    public boolean isEnableOnEmpty() {
        return enableOnEmpty;
    }
}
