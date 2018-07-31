package com.github.greengerong;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
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
public class FeatureToggleAspectTest {
    @Mock
    private MethodSignature methodSignature;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private ToggleService toggleService;

    @InjectMocks
    private FeatureToggleAspect featureToggleAspect;

    private Method method = stubMethod();

    @Test
    public void should_toggle_with_feature_enable() throws Exception {
        //given
        final String toggleValue = "toggle";
        final String feature = "feature";
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(method);

        when(toggleService.toggle(eq(feature), any(Supplier.class))).thenReturn(toggleValue);

        //when
        final Object toggle = featureToggleAspect.toggle(joinPoint);

        //then

        assertThat(toggle).isEqualTo(toggleValue);
        verify(toggleService).toggle(eq(feature), any(Supplier.class));
    }

    private Method stubMethod() {
        try {
            return this.getClass().getMethod("featureToggle", new Class[]{});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    @FeatureToggle("feature")
    public String featureToggle() {
        return "toggle";
    }
}