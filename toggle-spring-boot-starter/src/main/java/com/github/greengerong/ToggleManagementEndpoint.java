package com.github.greengerong;

import com.github.greengerong.management.JdbcManagementService;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Endpoint(id = "feature-toggle-mgt")
public class ToggleManagementEndpoint {

    private final JdbcManagementService managementService;

    public ToggleManagementEndpoint(JdbcManagementService managementService) {
        this.managementService = managementService;
    }

    @WriteOperation
    public void saveFeatures(String feature, String value) {
        managementService.saveFeatures(feature.trim(), value.trim());
    }

    @DeleteOperation
    public void deleteFeature(String feature) {
        managementService.deleteFeature(feature.trim());
    }
}
