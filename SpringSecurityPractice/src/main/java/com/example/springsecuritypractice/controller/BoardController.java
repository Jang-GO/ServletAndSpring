package com.example.springsecuritypractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public String registerGET(){
        return "register";
    }

    @GetMapping("/member/login")
    public void loginGET(String error, String logout){
        log.info("login get .............");
        log.info("logout" + logout);

            if(logout != null){
                log.info("user logout ........");
            }
    }
}
