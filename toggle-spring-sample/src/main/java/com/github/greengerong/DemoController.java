package com.github.greengerong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
