package com.abhijith.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloo")
    public String hello() {
        return "hellow everyone";
    }
}
