package com.github.greengerong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/

@Configuration
@ConditionalOnClass(ToggleService.class)
@EnableConfigurationProperties(ToggleConfig.class)
public class ToggleAutoConfigure {

    private final ToggleConfig toggleConfig;
    private ToggleService toggleService;

    @Autowired
    public ToggleAutoConfigure(ToggleConfig toggleConfig) {
        this.toggleConfig = toggleConfig;
        toggleService = new ToggleService(toggleConfig);
    }

    @Bean
    @ConditionalOnMissingBean
//    @Order(100)
    public ToggleService toggleService() {
        return toggleService;
    }

    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "feature-toggle", value = "store-way", havingValue = "properties", matchIfMissing = true)
//    @Order(10)
    public FeatureToggleAspect featureToggleAspect() {
        return new FeatureToggleAspect(toggleService);
    }

    //    @ConditionalOnBean(ToggleService.class)
//    @Order(10)
    @Bean
    public ToggleEndpoint toggleEndpoint() {
        return new ToggleEndpoint(toggleService);
    }
}
