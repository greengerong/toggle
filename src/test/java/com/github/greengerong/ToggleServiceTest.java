package com.github.greengerong;

import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
public class ToggleServiceTest {

    @Mock
    private FeaturesFetcher featuresFetcher;
    private ToggleService toggleService;

    @Before
    public void setUp() throws Exception {
        toggleService = new ToggleService(featuresFetcher);
    }

    @Test
    public void should_is_active_when_feature_is_enable() throws Exception {
        //given
        final String feature = "order";
        when(featuresFetcher.features()).thenReturn(Maps.newHashMap(feature, true));

        //when
        final boolean active = toggleService.isActive(feature);
        //then
        assertThat(active).isTrue();
    }
}