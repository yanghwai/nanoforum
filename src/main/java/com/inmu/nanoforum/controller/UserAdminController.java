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
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/systems/user")
@SessionAttributes({"theUser", "roles"})

public class UserAdminController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;
    private RoleService roleService;
    // inject user service
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    // Mappings

    @GetMapping("/list")
    public String listUsers(Model model){
        // get user list from service
        List<AppUser> userList = userService.findAllUsers();

        // add attribute to model
        model.addAttribute("users", userList);
        return "system/list-users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/systems/user/list";
    }

    @GetMapping("/editInfo")
    public String editUserInfo(@RequestParam("userId") int uid, Model model){

        AppUser appUser = userService.findById(uid);

        model.addAttribute("theUser", appUser);

        return "system/user-info-edit";

    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
        dataBinder.setDisallowedFields("id", "password");
//        dataBinder.registerCustomEditor(UserRole.class, new RoleEditor());

    }


    @ModelAttribute("roles")
    public List<UserRole> initializeUserRoles(){
        return roleService.findAll();
    }

    @PostMapping("/processUserInfoEdit")
    public String processEditUserInfo(@Valid @ModelAttribute("theUser") AppUser appUser,
                                      BindingResult bindingResult,
                                      Model model,
                                      SessionStatus sessionStatus){
        logger.info(">>>Edit user info "+appUser);


        String ssoId = appUser.getSsoId();

        if(bindingResult.hasErrors()){
            logger.warning(bindingResult.toString());
            model.addAttribute("theUser", userService.findBySSO(ssoId));
            model.addAttribute("errorMessage","cannot be empty");

            return "system/user-info-edit";
        }

        if(!userService.isUserSSOUnique(ssoId)){
            model.addAttribute("theUser", userService.findBySSO(ssoId));
            model.addAttribute("errorMessage","Username already exists");

            return "system/user-info-edit";
        }

        if(appUser.getRoleList().size()==0){
            model.addAttribute("theUser", userService.findBySSO(ssoId));
            model.addAttribute("errorMessage", "Must have at least 1 role");

            return "system/user-info-edit";
        }


        userService.updateUser(appUser);
        logger.info(">>>Successfully saved changed" + ssoId);

        sessionStatus.setComplete();

        return "redirect:/systems/user/list";
    }

}
