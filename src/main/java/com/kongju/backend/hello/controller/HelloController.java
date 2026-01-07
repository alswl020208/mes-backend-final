package com.kongju.backend.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String helloMethod(){
        System.out.println("Hello World!");
        return "Hello World!@@@@";
    }
}
