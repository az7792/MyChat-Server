package org.mychat.mychatserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeseController {
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

}
