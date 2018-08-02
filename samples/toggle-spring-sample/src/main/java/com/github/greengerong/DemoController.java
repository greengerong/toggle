package com.github.greengerong;

import com.github.greengerong.aspect.FeatureToggle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@RestController()
@RequestMapping("demo")
public class DemoController {

    @FeatureToggle("foo")
    @GetMapping("foo")
    public String foo() {
        return "foo call";
    }

    @FeatureToggle("bar")
    @GetMapping("bar")
    public String bar() {
        return "bar call";
    }

    @FeatureToggle("gray")
    @GetMapping("gray")
    public String gray(@RequestParam("currentUser") String currentUser) {
        return String.format("gray call: user %s", currentUser);
    }
}
