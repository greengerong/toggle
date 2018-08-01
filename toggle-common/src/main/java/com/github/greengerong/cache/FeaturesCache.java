package com.github.greengerong.cache;

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

    Map<String, Object> getFeatures(Supplier<Map<String, Object>> loader);

}
