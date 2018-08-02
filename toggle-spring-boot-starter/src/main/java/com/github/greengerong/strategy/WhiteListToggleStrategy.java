package com.github.greengerong.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.greengerong.utils.RequestUtils;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
public class WhiteListToggleStrategy implements ToggleStrategy {
    @Override
    public String keyword() {
        return "white-list";
    }

    @Override
    public boolean isActive(String feature, String featureValue) {
        final List<String> ips = Arrays.stream(featureValue.trim().split(","))
                .map(it -> it.trim()).collect(Collectors.toList());

        final String clientIp = RequestUtils.currentRequest()
                .map(it -> RequestUtils.getClientIp(it))
                .orElse("");

        return "".equals(clientIp) ? false : ips.contains(clientIp);
    }

}
