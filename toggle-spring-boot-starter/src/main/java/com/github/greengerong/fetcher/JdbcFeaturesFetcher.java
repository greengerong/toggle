package com.github.greengerong.fetcher;

import java.util.Map;
import java.util.stream.Collectors;

import com.github.greengerong.FeaturesFetcher;

import org.springframework.jdbc.core.JdbcTemplate;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class JdbcFeaturesFetcher implements FeaturesFetcher {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFeaturesFetcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> getFeatures() {
        return this.jdbcTemplate
                .queryForList("SELECT FEATURE, FEATURE_VALUE FROM TOGGLE_FEATURES")
                .stream()
                .filter(it -> !getFeatureKey(it).isEmpty() && !getFeatureValue(it).isEmpty())
                .collect(Collectors.toMap(it -> getFeatureKey(it), it -> Boolean.parseBoolean(getFeatureValue(it))));
    }

    private String getFeatureValue(Map<String, Object> it) {
        return it.getOrDefault("FEATURE_VALUE", "").toString().trim();
    }

    private String getFeatureKey(Map<String, Object> it) {
        return it.getOrDefault("FEATURE", "").toString().trim();
    }
}
