package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //This lets Springboot know that this will control the responses to any interaction with the server, regarding templates
public class UserController {
    
    HashMap<String, String> users = new HashMap<>();

    String APIKEY = "42cde0c47c9e6b1e75515d281cc65587";

    @GetMapping("/signup") // Occurs when enter 127.0.0.1:8080/signup
    public String showSignupForm(){
        return "signup"; //will look for html file with name signup in resource/template
    }

    @PostMapping("/signup") // Occurs when submit data (your user and pass) in 127.0.0.1:8080/signup
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
            return "redirect:/zipcode";
        return "login";
    }

    @GetMapping("/dashboard")
    public String ShowDashboard(){
        return "dashboard"; //will look for html file with name dashboard in resource/template
    }

    @GetMapping("/zipcode")
    public String ShowZipcode(){
        return "zipcode"; //will look for html file with name zipcode in resource/template
    }

    @PostMapping("/zipcode")
    public String HandleZipcode(@RequestParam String zip_code, @RequestParam String country_code, RedirectAttributes redirect){
        redirect.addAttribute("zipcode",zip_code);
        redirect.addAttribute("countrycode",country_code);
        // redirectAttributes.addAttribute("zipcode",zip_code);
        // redirectAttributes.addAttribute("country",country_code);
        // System.out.println(redirectAttributes.getAttribute("zipcode"));
        // System.out.println(redirectAttributes.getAttribute("country_code"));
        // System.out.println(zipcode);
        // System.out.println(country);
        // return ("redirect:/results?zipcode={zip_code}&countrycode={country_code}");
        return ("redirect:/results");
    }

   

    @GetMapping("/results")
    public String ShowResults(@RequestParam(name = "zipcode") String zipcode, @RequestParam(name="countrycode") String countrycode){
        System.out.println(zipcode);
        HttpClient client = HttpClient.newHttpClient();
        try{
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?zip="+zipcode+","+countrycode+"&appid="+APIKEY))
                .build();
    
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return "results";
        }
        catch (IOException| InterruptedException e){
            e.printStackTrace();
            return "redirect:/zipcode";
        }
    }

}
