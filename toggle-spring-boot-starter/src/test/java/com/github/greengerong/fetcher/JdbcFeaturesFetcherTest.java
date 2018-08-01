package com.github.greengerong.fetcher;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@RunWith(MockitoJUnitRunner.class)
public class JdbcFeaturesFetcherTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcFeaturesFetcher featuresFetcher;

    @Test
    public void should_get_features() throws Exception {
        //given
        final Map featureRecord = new HashMap();
        featureRecord.put("FEATURE", "foo");
        featureRecord.put("FEATURE_VALUE", "TRUE");

        when(jdbcTemplate.queryForList(anyString())).thenReturn(Lists.list(featureRecord));

        //when
        final Map<String, Object> features = featuresFetcher.getFeatures();

        //then
        assertThat(features.get("foo")).isEqualTo("TRUE");
    }
}