package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.MessageDao;
import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> getInbox(AppUser appUser) {
        return messageDao.getInbox(appUser);
    }

    @Override
    public List<Message> getOutbox(AppUser appUser) {
        return messageDao.getOutbox(appUser);
    }

    @Override
    public void save(Message message) {
        messageDao.save(message);
    }
}
