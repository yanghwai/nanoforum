package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> findAllTopics();

    Topic getById(int id);

    void save(Topic topic);

    List<Topic> findByTitle(String theTitle);

    void delete(Topic topic);

    void deleteById(int id);
}
