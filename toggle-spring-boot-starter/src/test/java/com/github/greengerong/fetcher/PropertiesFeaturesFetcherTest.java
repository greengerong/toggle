package com.github.greengerong.fetcher;

import java.util.HashMap;
import java.util.Map;

import com.github.greengerong.config.ToggleConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
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
public class PropertiesFeaturesFetcherTest {
    @Mock
    private ToggleConfig toggleConfig;
    @InjectMocks
    private PropertiesFeaturesFetcher featuresFetcher;

    @Test
    public void should_get_features() throws Exception {
        //given
        final HashMap<String, Object> features = new HashMap<>();
        when(toggleConfig.getFeatures()).thenReturn(features);

        //when
        final Map<String, Object> result = featuresFetcher.getFeatures();
        //then
        assertThat(result).isEqualTo(features);
    }
}