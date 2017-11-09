package com.sven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController
{
    
    @GetMapping("/landing")
    public String langding(@RequestHeader(required=false) final String referer) {
        return "referer: " + referer;
    }
}
