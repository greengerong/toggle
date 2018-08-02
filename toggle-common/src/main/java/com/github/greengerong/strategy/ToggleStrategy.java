package com.github.greengerong.strategy;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public interface ToggleStrategy {

    String keyword();

    boolean isActive(String feature, String featureValue);

}
