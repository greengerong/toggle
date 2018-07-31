package com.github.greengerong;

import java.util.HashMap;
import java.util.Map;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class JdbcFeaturesFetcher implements FeaturesFetcher {

    @Override
    public Map<String, Boolean> getFeatures() {
        final HashMap<String, Boolean> map = new HashMap<>();
        map.put("foo", true);
        return map;
    }
}
