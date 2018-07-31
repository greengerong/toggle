package com.github.greengerong;

import java.util.Map;

import greengerong.ToggleService;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Endpoint(id = "feature-toggle")
public class ToggleEndpoint {

    private final ToggleService toggleService;

    public ToggleEndpoint(ToggleService toggleService) {
        this.toggleService = toggleService;
    }

    @ReadOperation
    public Map<String, Boolean> features() {
        return toggleService.features();
    }
}
