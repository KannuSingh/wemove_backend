package com.wemove.wemove_backend.controller;

import com.wemove.wemove_backend.entities.Credentials;
import com.wemove.wemove_backend.entities.ResetPassword;
import com.wemove.wemove_backend.entities.SecurityQuestionDto;
import com.wemove.wemove_backend.entities.UserDetails;
import com.wemove.wemove_backend.exceptions.InvalidCredentials;
import com.wemove.wemove_backend.services.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController()
public class OnboardingController {

    private final UserDetailsService userDetailsService;

    public OnboardingController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/userLogin")
    public UserDetails login(@RequestBody Credentials userCredentials){
        UserDetails userDetails = userDetailsService.checkCredentials(userCredentials);
        if (userDetails == null) throw new InvalidCredentials("Invalid Credentials");
        return userDetails;

    }

    @PostMapping("/register")
    public UserDetails register(@RequestBody UserDetails userDetails){
        System.out.println("OnboardingController : "+userDetails);
        return userDetailsService.registerUser(userDetails);
    }

    @PostMapping("/forgotPassword")
    public SecurityQuestionDto getSecurityQuestion(@RequestParam String email){
        SecurityQuestionDto result= userDetailsService.getSecurityQuestion(email);
        if(result!= null) {
            return result;
        }
        else{
            throw new RuntimeException("User Not Found");
        }
    }

    @PostMapping("/forgotPassword/resetPassword")
    public boolean forgotPassword(@RequestBody ResetPassword resetPasswordBody){
        return userDetailsService.forgotPassword(resetPasswordBody);
    }

    @GetMapping("/getUser")
    public UserDetails forgotPassword(@RequestParam String email){
        return userDetailsService.getUser(email);
    }


}
