package com.github.greengerong.strategy;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class SimpleToggleStrategy implements ToggleStrategy {

    @Override
    public boolean isActive(String feature, Object featureValue) {
        return Boolean.parseBoolean(featureValue.toString());
    }
}
