package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.UserRole;
import com.inmu.nanoforum.service.RoleService;
import com.inmu.nanoforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;
    private RoleService roleService;

    // inject services using setters
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showLoginPage(Model model){
        model.addAttribute("appUser", new AppUser());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistration(
            @Valid @ModelAttribute("appUser") AppUser appAppUser,
            BindingResult bindingResult,
            Model model){

        System.out.println(">>>User info on registration form: "+ appAppUser);

        String ssoId = appAppUser.getSsoId();

        // form validation
        if(bindingResult.hasErrors()){
            model.addAttribute("appUser", new AppUser());
            model.addAttribute("registrationError", "Username/password cannot be empty.");
            logger.warning(">>> Username/password cannot be empty.");
            return "registration-form";
        }

        // check the database if user already exists

        if(userService.isUserSSOUnique(ssoId)){
            model.addAttribute("appUser", new AppUser());
            model.addAttribute("registrationError","Username already exists.");

            logger.warning(">>> Username already exists.");

            return "registration-form";
        }

        // no errors- proceed to save user

        UserRole role = roleService.findByType("USER");

        Set<UserRole> roleSet = new HashSet<>();
        roleSet.add(role);

        appAppUser.setUserRoles(roleSet);

        userService.saveUser(appAppUser);

        logger.info(">>>Successfully created user: "+ ssoId);

        return "registration-confirmation";
    }

}
