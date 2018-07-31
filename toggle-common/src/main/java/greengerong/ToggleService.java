package greengerong;

import java.util.Map;
import java.util.function.Supplier;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ToggleService {

    private final FeaturesFetcher featuresFetcher;

    public ToggleService(FeaturesFetcher featuresFetcher) {
        this.featuresFetcher = featuresFetcher;
    }

    public boolean isActive(String feature) {
        return features().getOrDefault(feature, featuresFetcher.isEnableOnEmpty());
    }

    public void toggle(String feature, Runnable enable) {
        toggle(feature, enable, () -> {
        });
    }

    public void toggle(String feature, Runnable enable, Runnable disable) {
        final Runnable runnable = this.isActive(feature) ? enable : disable;
        runnable.run();
    }

    public <T> T toggle(String feature, Supplier<T> enable) {
        return toggle(feature, enable, () -> null);
    }

    public <T> T toggle(String feature, Supplier<T> enable, Supplier<T> disable) {
        final Supplier<T> runnable = this.isActive(feature) ? enable : disable;
        return runnable.get();
    }

    public Map<String, Boolean> features() {
        return this.featuresFetcher.getFeatures();
    }

}
