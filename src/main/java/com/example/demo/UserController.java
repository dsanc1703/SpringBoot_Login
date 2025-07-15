package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    
    @GetMapping("/signup")
    public String signup(){
        return "signup"; //will look for html file with name signup in resource/template
    }

    @GetMapping("/login")
    public String login(){
        return "login"; //will look for html file with name login in resource/template
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard"; //will look for html file with name dashboard in resource/template
    }

}
