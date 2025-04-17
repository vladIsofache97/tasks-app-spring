package com.iso.tasks.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String sayHello() {
        return "Hello!";
    }

    @GetMapping("/private")
    public String sayHelloBack(Authentication authentication) {
        return "Hello " + authentication.getName() + "!";
    }
}
