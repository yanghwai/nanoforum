package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Topic;
import com.inmu.nanoforum.service.TopicService;
import com.inmu.nanoforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    private TopicService topicService;
    private UserService userService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/topics")
    public List<Topic> getTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AppUser> getAppUsers(){
        return userService.findAllUsers();
    }

}
