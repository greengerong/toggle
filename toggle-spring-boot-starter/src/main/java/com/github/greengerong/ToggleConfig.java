package com.github.greengerong;

import java.util.HashMap;
import java.util.Map;

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
public class ToggleConfig {

    private boolean enableOnEmpty = false;

    private Map<String, Boolean> features = new HashMap<>();

    public Map<String, Boolean> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Boolean> features) {
        this.features = features;
    }

    public boolean isEnableOnEmpty() {
        return enableOnEmpty;
    }
}
