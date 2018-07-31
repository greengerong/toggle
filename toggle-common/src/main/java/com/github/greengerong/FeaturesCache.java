package com.github.greengerong;

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
public interface FeaturesCache {

    Map<String, Boolean> getFeatures(Supplier<Map<String, Boolean>> loader);

}
