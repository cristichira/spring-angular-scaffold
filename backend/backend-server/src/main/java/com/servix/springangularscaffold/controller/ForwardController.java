package com.servix.springangularscaffold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

    @GetMapping("/**/{[path:[^\\.]*}")
    public String frontend() {
        return "forward:/";
    }
}
