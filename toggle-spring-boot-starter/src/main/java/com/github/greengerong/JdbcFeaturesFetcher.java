package com.github.greengerong;

import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;

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

    public JdbcFeaturesFetcher(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, Boolean> getFeatures() {
        return this.jdbcTemplate
                .queryForList("SELECT FEATURE, FEATURE_VALUE FROM TOGGLE_FEATURES")
                .stream()
                .filter(it -> getFeatureKey(it).isEmpty() || getFeatureValue(it).isEmpty())
                .collect(Collectors.toMap(it -> getFeatureKey(it), it -> Boolean.parseBoolean(getFeatureValue(it))));
    }

    private String getFeatureValue(Map<String, Object> it) {
        return it.getOrDefault("FEATURE_VALUE", "").toString().trim();
    }

    private String getFeatureKey(Map<String, Object> it) {
        return it.getOrDefault("FEATURE", "").toString().trim();
    }
}
