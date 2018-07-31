package com.github.greengerong;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureToggle {

    String value();

}
