package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> getAllTopics();

    Topic getById(int id);

    void save(Topic topic);

    List<Topic> searchByTitle(String theTitle);

    void delete(Topic topic);

    void deleteById(int id);
}
