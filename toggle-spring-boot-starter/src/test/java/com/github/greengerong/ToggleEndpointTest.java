package com.github.greengerong;

import java.util.HashMap;
import java.util.Map;

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
public class ToggleEndpointTest {
    @Mock
    private ToggleService toggleService;
    @InjectMocks
    private ToggleEndpoint endpoint;

    @Test
    public void should_get_features() throws Exception {
        //given
        final HashMap<String, Object> features = new HashMap<>();
        when(toggleService.features()).thenReturn(features);

        //when
        final Map<String, Object> result = endpoint.features();

        //then
        assertThat(result).isEqualTo(features);
    }
}