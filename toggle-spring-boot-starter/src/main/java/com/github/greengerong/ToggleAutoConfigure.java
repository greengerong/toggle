package com.github.greengerong;

import greengerong.FeatureToggleAspect;
import greengerong.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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
    public FeatureToggleAspect toggleService(@Autowired ToggleService toggleService) {
        return new FeatureToggleAspect(toggleService);
    }

    @Configuration
    @ConditionalOnBean(ToggleService.class)
    protected static class ToggleEndpointConfiguration {
        private final ToggleService toggleService;

        @Autowired
        public ToggleEndpointConfiguration(ToggleService toggleService) {
            this.toggleService = toggleService;
        }

        @Bean
        public ToggleEndpoint toggleEndpoint() {
            return new ToggleEndpoint(toggleService);
        }
    }

}
