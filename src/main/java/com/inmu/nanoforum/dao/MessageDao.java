package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getInbox(AppUser appUser);

    List<Message> getOutbox(AppUser appUser);

    void save(Message message);

    Message getById(int msgId);
}
