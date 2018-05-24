package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String showUserInfoPage(@RequestParam("userId") int uid, Model model){
        AppUser appUser = userService.findById(uid);
        if(appUser == null){
            model.addAttribute("errorMessage", "User does not exists.");
            return "error";
        }

        model.addAttribute("user", appUser);
        return "user/user-info";
    }

}
