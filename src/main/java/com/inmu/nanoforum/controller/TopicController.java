package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Topic;
import com.inmu.nanoforum.service.TopicService;
import com.inmu.nanoforum.service.UserService;
import com.inmu.nanoforum.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private static final Logger logger = Logger.getLogger(TopicController.class.getName());

    private TopicService topicService;
    private UserService userService;

    // inject services using setters
    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // Request Mappings

    @GetMapping("/list")
    public String listTopics(Model model) {
        List<Topic> topicList = topicService.findAllTopics();
        model.addAttribute("topics", topicList);

        return "list-topics";
    }


    @PostMapping("/search")
    public String searchTopics(@RequestParam("theTitle") String theTitle, Model model) {
        List<Topic> topicList = topicService.findByTitle(theTitle);

        model.addAttribute("topics", topicList);

        return "list-topics";
    }


    @GetMapping("/postNewTopic")
    @PreAuthorize("hasRole('USER')")
    public String postNewTopic(Model model) {
        Topic topic = new Topic();
        model.addAttribute("topic", topic);

        return "topic-form";
    }

    @PostMapping("/saveTopic")
    public String saveTopic(@ModelAttribute("topic") Topic topic, Principal principal) {

        String currentUsername = principal.getName();

        logger.info(">>>Current username is:" + currentUsername);

        AppUser currentUser = userService.findBySSO(currentUsername);

        topic.setAuthor(currentUser);

        if (topic.getPostTime() == null) {
            topic.setPostTime(DateTimeUtil.getCurrentDateTime());
        }
        topicService.save(topic);
        return "redirect:/topic/list";
    }

    @GetMapping("/showTopicPage")
    public String showTopicPage(@RequestParam("topicId") int theId, Model model) {
        // get topic using service using the id
        Topic topic = topicService.getById(theId);

        model.addAttribute("topic", topic);

        return "topic-page";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('DBA')")
    public String deleteTopic(@RequestParam("topicId") int id){

        logger.info(">>>Deleting topic:"+id);

        topicService.deleteById(id);

        return "redirect:/topic/list";
    }
}
