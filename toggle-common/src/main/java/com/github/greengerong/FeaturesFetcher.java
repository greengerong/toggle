package com.github.greengerong;

import java.util.Map;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public interface FeaturesFetcher {

    boolean isEnableOnEmpty();

    Map<String, Boolean> getFeatures();
}
