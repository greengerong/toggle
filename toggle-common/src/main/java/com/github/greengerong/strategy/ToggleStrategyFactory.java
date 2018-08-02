package com.github.greengerong.strategy;

import java.util.List;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class ToggleStrategyFactory {

    public static final String STRATEGY_SEPARATOR = "=";
    private List<ToggleStrategy> strategies;

    public ToggleStrategyFactory(List<ToggleStrategy> strategies) {

        this.strategies = strategies;
    }

    public boolean isActive(String feature, Object featureValue) {
        if (isUseStrategy(featureValue)) {
            final String[] sp = featureValue.toString().split(STRATEGY_SEPARATOR);
            final String keyword = sp[0];
            final String param = sp[1];

            return strategies
                    .stream()
                    .filter(it -> it.keyword().equalsIgnoreCase(keyword))
                    .findFirst()
                    .map(it -> it.isActive(feature, param))
                    .orElse(false);

        }
        return Boolean.parseBoolean(featureValue.toString());
    }

    private boolean isUseStrategy(Object featureValue) {
        return featureValue.getClass().isAssignableFrom(String.class)
                && featureValue.toString().contains(STRATEGY_SEPARATOR);
    }
}
