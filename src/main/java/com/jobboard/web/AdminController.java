package com.jobboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(value = "/jobboard/presentation")
    public String accueil(){
        return "pres/list";
    }
}
