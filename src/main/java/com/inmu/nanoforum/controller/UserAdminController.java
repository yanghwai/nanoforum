package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/systems/user")
public class UserAdminController {

    private UserService userService;
    // inject user service
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model){
        // get user list from service
        List<AppUser> userList = userService.findAllUsers();

        // add attribute to model
        model.addAttribute("users", userList);
        return "list-users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/systems/user/list";
    }

}
