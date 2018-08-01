package com.github.greengerong.management;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public interface ManagementService {
    void saveFeatures(String feature, String value);

    void deleteFeature(String feature);
}
