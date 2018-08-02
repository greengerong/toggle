package com.github.greengerong;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.github.greengerong.strategy.ToggleStrategy;
import com.github.greengerong.utils.RequestUtils;

import org.springframework.stereotype.Component;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@Component
public class GrayToggleStrategy implements ToggleStrategy {
    @Override
    public String keyword() {
        return "gray";
    }

    @Override
    public boolean isActive(String feature, String featureValue) {
        final List<String> allowUsers = Arrays.stream(featureValue.trim().split(","))
                .map(it -> it.trim()).collect(Collectors.toList());

        final String currentUser = RequestUtils.currentRequest()
                .map(it -> it.getParameter("currentUser"))
                .orElse("");

        return "".equals(currentUser) ? false : allowUsers.contains(currentUser);
    }

}
