package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getInbox(AppUser appUser);

    List<Message> getOutbox(AppUser appUser);

    void save(Message message);

    Message getById(int msgId);
}
