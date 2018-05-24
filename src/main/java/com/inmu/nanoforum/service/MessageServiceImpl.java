package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.MessageDao;
import com.inmu.nanoforum.dao.UserDao;
import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    private UserDao userDao;
    private MessageDao messageDao;

    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }




    @Override
    public List<Message> getInbox(AppUser appUser) {
        List<Message> messages = messageDao.getInbox(appUser);

        for(Message msg: messages){
            setUserSsoId(msg);
        }

        return messages;
    }

    @Override
    public List<Message> getOutbox(AppUser appUser) {

        List<Message> messages = messageDao.getOutbox(appUser);

        for(Message msg: messages){
            setUserSsoId(msg);
        }

        return messages;
    }

    @Override
    public void save(Message message) {
        messageDao.save(message);
    }

    @Override
    public Message getById(int msgId) {
        Message message = messageDao.getById(msgId);

        setUserSsoId(message);

        return message;
    }


    private void setUserSsoId(Message message){
        String senderSsoId = null;
        String receiverSsoId = null;

        AppUser sender = userDao.findById(message.getSenderId());
        if(sender!=null)
        senderSsoId = sender.getSsoId();


        AppUser receiver = userDao.findById(message.getReceiverId());
        if(receiver!=null)
        receiverSsoId = receiver.getSsoId();

        message.setSenderSsoId(senderSsoId);
        message.setReceiverSsoId(receiverSsoId);
    }


}
