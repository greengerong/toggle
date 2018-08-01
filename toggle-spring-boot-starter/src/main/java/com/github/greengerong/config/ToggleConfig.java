package com.github.greengerong.config;

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
    private String storage = "properties";
    private boolean enableOnEmpty = false;
    private Map<String, Object> features = new HashMap<>();

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Object> features) {
        this.features = features;
    }

    public boolean isEnableOnEmpty() {
        return enableOnEmpty;
    }

    public void setEnableOnEmpty(boolean enableOnEmpty) {
        this.enableOnEmpty = enableOnEmpty;
    }
}
