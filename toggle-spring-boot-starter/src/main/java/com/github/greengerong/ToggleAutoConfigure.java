package com.github.greengerong;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "feature-toggle", value = "store-way", havingValue = "properties", matchIfMissing = true)
    public ToggleService toggleService(@Autowired ToggleConfig toggleConfig) {
        return new ToggleService(toggleConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public FeatureToggleAspect featureToggleAspect(@Autowired ToggleService toggleService) {
        return new FeatureToggleAspect(toggleService);
    }

    @Configuration
    protected static class ToggleEndpointConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public ToggleEndpoint toggleEndpoint(@Autowired ToggleService toggleService) {
            return new ToggleEndpoint(toggleService);
        }
    }

}
