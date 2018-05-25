package com.inmu.nanoforum.controller;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Message;
import com.inmu.nanoforum.service.MessageService;
import com.inmu.nanoforum.service.UserService;
import com.inmu.nanoforum.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/message")
@PreAuthorize("hasRole('USER')")
@SessionAttributes("theMessage")
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class.getName());

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, trimmerEditor);

        dataBinder.setDisallowedFields("id","senderId","receiverId");

    }


    @GetMapping("/list")
    public String showMyMessages(Model model, Principal principal){

        AppUser currentUser = userService.findBySSO(principal.getName());

        List<Message> inbox = messageService.getInbox(currentUser);

        List<Message> outbox = messageService.getOutbox(currentUser);

        model.addAttribute("inbox", inbox);

        model.addAttribute("outbox", outbox);

        return "message/list-messages";
    }

    @GetMapping("/sendNewMessage")
    public String showNewMessagePage(@RequestParam("userId") int uid, Model model, Principal principal){
        Message message = new Message();

        AppUser theReceiver = userService.findById(uid);

        if(theReceiver==null){
            model.addAttribute("errorMessage","Receiver does not exist!");
            return "error";
        }

        message.setReceiverId(theReceiver.getId());
        message.setReceiverSsoId(theReceiver.getSsoId());

        AppUser currentUser = userService.findBySSO(principal.getName());
        message.setSenderId(currentUser.getId());
        message.setSenderSsoId(currentUser.getSsoId());

        model.addAttribute("theMessage", message);

        return "message/message-form";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("theMessage") Message message,
                              BindingResult bindingResult,
                              SessionStatus sessionStatus,
                              Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("theMessage", message);
            model.addAttribute("errorMessage", "Message cannot be empty");

            return "message/message-form";
        }

        message.setSendTime(DateTimeUtil.getCurrentDateTime());
        message.setDeleted(false);
        message.setRead(false);

        logger.info(">>>Message to be sent:"+ message);

        messageService.save(message);

        sessionStatus.setComplete();

        return "redirect:/message/list";
    }

    @PostMapping("/setRead")
    public String setMessageReadFlag(@RequestParam("msgId") int msgId){

        Message theMsg = messageService.getById(msgId);

        theMsg.setRead(true);

        messageService.save(theMsg);

        return "redirect:/message/list";
    }

}
