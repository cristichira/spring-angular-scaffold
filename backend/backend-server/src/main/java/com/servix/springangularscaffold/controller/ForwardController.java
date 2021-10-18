package com.servix.springangularscaffold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {
    @GetMapping(value = {"/home", "/login", "/register", "/logout", "/..."})
    public String frontend() {
        return "forward:/";
    }
}
