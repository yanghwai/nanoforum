package com.inmu.nanoforum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
    }


    @GetMapping(value = "/login")
    public String showLoginPage(){
        if(isCurrentAuthenticationAnonymous())
            return "user/login";
        else
            return "redirect:/";
    }

    // add request mapping for /access-denied
    @GetMapping(value = "/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
