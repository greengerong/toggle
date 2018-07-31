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


    @Configuration
    @ConditionalOnProperty(prefix = "feature-toggle", value = "store-way", havingValue = "properties", matchIfMissing = true)
    protected static class PropertiesToggleServiceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FeaturesFetcher propertiesFeaturesFetcher(@Autowired ToggleConfig toggleConfig) {
            return new PropertiesFeaturesFetcher(toggleConfig);
        }

        @Bean
        @ConditionalOnMissingBean
        public ToggleService propertiesToggleService(@Autowired ToggleConfig toggleConfig, @Autowired FeaturesFetcher propertiesFeaturesFetcher) {
            return new ToggleService(propertiesFeaturesFetcher, toggleConfig.isEnableOnEmpty());
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "feature-toggle", value = "store-way", havingValue = "jdbc")
    protected static class JdbcToggleServiceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public JdbcFeaturesFetcher jdbcFeaturesFetcher() {
            return new JdbcFeaturesFetcher();
        }

        @Bean
        @ConditionalOnMissingBean
        public ToggleService jdbcToggleService(@Autowired ToggleConfig toggleConfig, @Autowired FeaturesFetcher jdbcFeaturesFetcher) {
            return new ToggleService(jdbcFeaturesFetcher, toggleConfig.isEnableOnEmpty());
        }
    }


    @Configuration
    protected static class FeatureToggleAspectConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FeatureToggleAspect featureToggleAspect(@Autowired ToggleService toggleService) {
            return new FeatureToggleAspect(toggleService);
        }

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
