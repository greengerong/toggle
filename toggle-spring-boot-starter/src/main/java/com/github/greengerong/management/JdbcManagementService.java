package com.github.greengerong.management;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

public class JdbcManagementService implements ManagementService {
    private JdbcTemplate jdbcTemplate;

    public JdbcManagementService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveFeatures(String feature, String value) {
        final List<Integer> ids = jdbcTemplate
                .queryForList("SELECT ID FROM TOGGLE_FEATURES WHERE FEATURE = ?", new Object[]{feature}, Integer.class);
        if (!CollectionUtils.isEmpty(ids)) {
            jdbcTemplate.update(
                    "UPDATE TOGGLE_FEATURES SET FEATURE_VALUE = ? WHERE ID = ?", value, ids.get(0));
            return;
        }

        jdbcTemplate.update("INSERT INTO TOGGLE_FEATURES (FEATURE, FEATURE_VALUE) VALUES (?, ?)", feature, value);
    }

    @Override
    public void deleteFeature(String feature) {
        jdbcTemplate.update("DELETE FROM TOGGLE_FEATURES WHERE FEATURE = ?", feature);
    }
}
