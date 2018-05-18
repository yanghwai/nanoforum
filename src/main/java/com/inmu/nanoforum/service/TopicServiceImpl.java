package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.TopicDao;
import com.inmu.nanoforum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("topicService")
@Transactional
public class TopicServiceImpl implements TopicService {

    private TopicDao topicDao;
    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicDao.findAllTopics();
    }

    @Override
    public Topic getById(int id) {
        return topicDao.getById(id);
    }

    @Override
    public void save(Topic topic) {
        topicDao.save(topic);
    }

    @Override
    public List<Topic> findByTitle(String theTitle) {
        return topicDao.findByTitle(theTitle);
    }

    @Override
    public void delete(Topic topic) {
        topicDao.delete(topic);
    }

    @Override
    public void deleteById(int id) {
        topicDao.deleteById(id);
    }
}
