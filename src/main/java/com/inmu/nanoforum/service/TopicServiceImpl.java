package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.TopicDao;
import com.inmu.nanoforum.dao.UserDao;
import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("topicService")
@Transactional
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;
    private UserDao userDao;

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Topic> findAllTopics() {
        List<Topic> topicList= topicDao.findAllTopics();

        for(Topic topic: topicList){
            setAuthorName(topic);
        }

        return topicList;
    }

    @Override
    public Topic getById(int id) {
        Topic topic = topicDao.getById(id);
        setAuthorName(topic);
        return topic;
    }

    @Override
    public void save(Topic topic) {
        topicDao.save(topic);
    }

    @Override
    public List<Topic> findByTitle(String theTitle) {
        List<Topic> topicList = topicDao.findByTitle(theTitle);
        for(Topic topic: topicList){
            setAuthorName(topic);
        }
        return topicList;
    }

    @Override
    public void delete(Topic topic) {
        topicDao.delete(topic);
    }

    @Override
    public void deleteById(int id) {
        topicDao.deleteById(id);
    }


    private void setAuthorName(Topic topic){
        String authorName = null;
        try {
            AppUser appUser = userDao.findById(topic.getAuthorId());
            authorName = appUser.getSsoId();
        }
        catch (Exception exc){
            exc.printStackTrace();
            authorName = "User doesn't exist";
        }
        finally {
            topic.setAuthorName(authorName);
        }
    }
}
