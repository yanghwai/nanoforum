package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("topicDao")
public class TopicDaoImpl implements TopicDao {

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Topic> findAllTopics() {
        Session session = sessionFactory.getCurrentSession();

        Query<Topic> query = session.createQuery("from Topic order by postTime", Topic.class);

        return query.getResultList();
    }

    @Override
    public Topic getById(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Topic.class, id);
    }

    @Override
    public void save(Topic topic) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(topic);
    }

    @Override
    public List<Topic> findByTitle(String theTitle) {
        Session session = sessionFactory.getCurrentSession();

        Query<Topic> query = session.createQuery("from Topic where lower(title) like :title order by postTime", Topic.class);
        query.setParameter("title", "%" + theTitle.toLowerCase() + "%");
        query.setMaxResults(100);

        return query.getResultList();
    }

    @Override
    public void delete(Topic topic) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(topic);
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Topic topic = session.get(Topic.class, id);

        session.delete(topic);

    }
}
