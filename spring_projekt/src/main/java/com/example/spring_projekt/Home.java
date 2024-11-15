package com.example.spring_projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Home {

    /*@RequestMapping("/")
    String home() {
        return"Hello World!";
    }*/

    @GetMapping("/")
    public String homeForm() {
        return "home-view";
    }

    public static void main(String[] args) {
        SpringApplication.run(Home.class, args);
    }

}


