package com.github.greengerong.strategy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@RunWith(MockitoJUnitRunner.class)
public class SimpleToggleStrategyTest {
    @InjectMocks
    private SimpleToggleStrategy strategy;

    @Test
    public void should_get_feature_enable() throws Exception {
        assertThat(strategy.isActive("", "TRUE")).isTrue();

    }
}