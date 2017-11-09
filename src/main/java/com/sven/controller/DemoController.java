package com.sven.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class DemoController
{

    @GetMapping("/demo")
    public String demo() {
        return "redirect:https://localhost:8009/demo2";
    }

    @GetMapping("/demo2")
    public String demo2(@RequestHeader(required=false) final String referer, final HttpServletResponse response) {
        
        response.setHeader("Referer", referer);
        return "redirect:http://localhost:8080/landing";
    }

}
