package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.Topic;

import java.util.List;

public interface TopicDao {
    List<Topic> getAllTopics();

    Topic getById(int id);

    void save(Topic topic);

    List<Topic> searchByTitle(String theTitle);

    void delete(Topic topic);

    void deleteById(int id);
}
