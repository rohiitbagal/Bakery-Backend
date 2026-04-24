package com.example.demo.Controller;


import com.example.demo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/Home")
    public String home(){
       // System.out.println("Hello This is Home Page ");
        return "hello this is home page";
    }
    @PostMapping("/user")
    public User getUser(){
        return new User(1, "Rohit");
    }


}
