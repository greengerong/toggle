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
    protected static class FeaturesCacheConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FeaturesCache featuresCache() {
            return new ThreadLocalFeaturesCache();
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "feature-toggle", value = "storage", havingValue = "properties", matchIfMissing = true)
    protected static class PropertiesToggleServiceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FeaturesFetcher propertiesFeaturesFetcher(@Autowired ToggleConfig toggleConfig) {
            return new PropertiesFeaturesFetcher(toggleConfig);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "feature-toggle", value = "storage", havingValue = "jdbc")
    protected static class JdbcToggleServiceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public FeaturesFetcher jdbcFeaturesFetcher(@Autowired ToggleConfig toggleConfig) {
            return new JdbcFeaturesFetcher(toggleConfig.getDataSource());
        }
    }


    @Configuration
    protected static class ToggleServiceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ToggleService toggleService(@Autowired ToggleConfig toggleConfig,
                                           @Autowired FeaturesFetcher featuresFetcher,
                                           @Autowired FeaturesCache featuresCache) {
            return new ToggleService(featuresFetcher, featuresCache, toggleConfig.isEnableOnEmpty());
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
