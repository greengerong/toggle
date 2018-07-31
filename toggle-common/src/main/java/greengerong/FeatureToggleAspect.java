package greengerong;

import java.util.Optional;

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

    @Around("@annotation(com.github.greengerong.FeatureToggle)")
    public Object toggle(ProceedingJoinPoint jp) {

        return Optional.ofNullable((MethodSignature) jp.getSignature())
                .map(signature -> signature.getMethod().getAnnotation(FeatureToggle.class))
                .map(toggle -> toggle.value())
                .map(feature -> toggleService.toggle(feature, () -> proceed(jp)))
                .orElseGet(() -> proceed(jp));
    }

    private Object proceed(ProceedingJoinPoint jp) {
        try {
            return jp.proceed();
        } catch (Throwable throwable) {
            throw new FeatureToggleAspectException(throwable);
        }
    }
}
