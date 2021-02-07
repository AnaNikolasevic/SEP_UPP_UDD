package com.project.aik_bank.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class HelloWorld {
    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String hello() {

        return "hello world";

    }

}