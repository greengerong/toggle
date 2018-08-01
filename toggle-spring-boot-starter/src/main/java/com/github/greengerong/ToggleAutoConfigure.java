package com.github.greengerong;

import com.github.greengerong.aspect.FeatureToggleAspect;
import com.github.greengerong.cache.FeaturesCache;
import com.github.greengerong.cache.ThreadLocalFeaturesCache;
import com.github.greengerong.config.ToggleConfig;
import com.github.greengerong.fetcher.JdbcFeaturesFetcher;
import com.github.greengerong.fetcher.PropertiesFeaturesFetcher;
import com.github.greengerong.management.JdbcManagementService;
import com.github.greengerong.management.ManagementService;
import com.github.greengerong.strategy.SimpleToggleStrategy;
import com.github.greengerong.strategy.ToggleStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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
        public FeaturesCache threadLocalFeaturesCache() {
            return new ThreadLocalFeaturesCache();
        }

    }

    @Configuration
    protected static class ToggleStrategyConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ToggleStrategy simpleToggleStrategy() {
            return new SimpleToggleStrategy();
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
        public FeaturesFetcher jdbcFeaturesFetcher(@Autowired JdbcTemplate jdbcTemplate) {
            return new JdbcFeaturesFetcher(jdbcTemplate);
        }

        @Bean
        @ConditionalOnMissingBean
        public ManagementService jdbcManagementService(@Autowired JdbcTemplate jdbcTemplate) {
            return new JdbcManagementService(jdbcTemplate);
        }
    }

    @Configuration
    protected static class ToggleManagementEndpointConfiguration {

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(ManagementService.class)
        public ToggleManagementEndpoint toggleManagementEndpoint(@Autowired ManagementService managementService) {
            return new ToggleManagementEndpoint(managementService);
        }
    }


    @Configuration
    protected static class ToggleServiceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public ToggleService toggleService(@Autowired ToggleConfig toggleConfig,
                                           @Autowired FeaturesFetcher featuresFetcher,
                                           @Autowired FeaturesCache featuresCache,
                                           @Autowired ToggleStrategy toggleStrategy) {
            final ToggleService toggleService = new ToggleService(featuresFetcher, toggleConfig.isEnableOnEmpty());
            toggleService.setFeaturesCache(featuresCache);
            toggleService.setToggleStrategy(toggleStrategy);
            return toggleService;
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
