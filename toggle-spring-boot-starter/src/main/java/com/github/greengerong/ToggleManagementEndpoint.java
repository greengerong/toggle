package com.github.greengerong;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.greengerong.management.ManagementService;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
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

    private final ManagementService managementService;

    public ToggleManagementEndpoint(ManagementService managementService) {
        this.managementService = managementService;
    }

    @ReadOperation(produces = {"text/html; charset=UTF-8"})
    public String features() throws Exception {
        return new String(Files.readAllBytes(Paths.get(getClass().getResource("/feature-toggle-mgt.html").toURI())));
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
