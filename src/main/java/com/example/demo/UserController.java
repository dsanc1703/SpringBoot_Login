package com.example.demo;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    
    HashMap<String, String> users = new HashMap<>();

    @GetMapping("/signup")
    public String showSignupForm(){
        return "signup"; //will look for html file with name signup in resource/template
    }

    @PostMapping("/signup")
    public String handleSignupForm(@RequestParam String username, @RequestParam String password){
        users.put(username,password);
        return "login";

    }

    @GetMapping("/login")
    public String ShowLoginForm(){
        return "login"; //will look for html file with name login in resource/template
    }

    @PostMapping("/login")
    public String HandleLoginForm(@RequestParam String username, @RequestParam String password){
        if (users.containsKey(username) && users.get(username).equals(password))
            return "dashboard";
        return "login";
    }

    @GetMapping("/dashboard")
    public String ShowDashboard(){
        return "dashboard"; //will look for html file with name dashboard in resource/template
    }

}
