package com.github.greengerong.aspect;

import java.util.Optional;

import com.github.greengerong.exception.FeatureToggleAspectException;
import com.github.greengerong.ToggleService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Aspect
public class FeatureToggleAspect {
    private final ToggleService toggleService;

    public FeatureToggleAspect(ToggleService toggleService) {
        this.toggleService = toggleService;
    }

    @Around("@annotation(com.github.greengerong.aspect.FeatureToggle)")
    public Object toggle(ProceedingJoinPoint jp) {

        final Optional<String> feature = Optional.ofNullable((MethodSignature) jp.getSignature())
                .map(signature -> signature.getMethod().getAnnotation(FeatureToggle.class))
                .map(toggle -> toggle.value());

        return feature.isPresent() ? toggleService.toggle(feature.get(), () -> proceed(jp)) : proceed(jp);
    }

    private Object proceed(ProceedingJoinPoint jp) {
        try {
            return jp.proceed();
        } catch (Throwable throwable) {
            throw new FeatureToggleAspectException(throwable);
        }
    }
}
