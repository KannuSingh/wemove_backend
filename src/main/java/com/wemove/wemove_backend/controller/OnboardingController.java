package com.wemove.wemove_backend.controller;

import com.wemove.wemove_backend.entities.UserDetails;
import com.wemove.wemove_backend.services.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class OnboardingController {

    private final UserDetailsService userDetailsService;

    public OnboardingController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/userLogin")
    public boolean login(){
        return true;
    }

    @PostMapping("/register")
    public UserDetails register(@RequestBody UserDetails userDetails){
        System.out.println("OnboardingController : "+userDetails);
        return userDetailsService.registerUser(userDetails);
    }


}
