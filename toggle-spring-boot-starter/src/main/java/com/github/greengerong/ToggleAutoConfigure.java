package com.github.greengerong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @Autowired
    public ToggleAutoConfigure(ToggleConfig toggleConfig) {
        this.toggleConfig = toggleConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ToggleService toggleService() {
        return new ToggleService(toggleConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "feature-toggle", value = "store-way", havingValue = "properties", matchIfMissing = true)
    public FeatureToggleAspect featureToggleAspect(@Autowired ToggleService toggleService) {
        return new FeatureToggleAspect(toggleService);
    }

    @Bean
    @ConditionalOnBean(ToggleService.class)
    public ToggleEndpoint toggleEndpoint(@Autowired ToggleService toggleService) {
        return new ToggleEndpoint(toggleService);
    }
}
