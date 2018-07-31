package com.github.greengerong;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

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
    private DataSource dataSource;
    private Map<String, Boolean> features = new HashMap<>();

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Map<String, Boolean> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Boolean> features) {
        this.features = features;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isEnableOnEmpty() {
        return enableOnEmpty;
    }

    public void setEnableOnEmpty(boolean enableOnEmpty) {
        this.enableOnEmpty = enableOnEmpty;
    }
}
