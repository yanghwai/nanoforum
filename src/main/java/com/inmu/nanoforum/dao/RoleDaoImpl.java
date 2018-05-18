package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl  implements RoleDao {

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserRole> findAll() {
        Session session = sessionFactory.getCurrentSession();

        Query<UserRole> query = session.createQuery("from UserRole", UserRole.class);

        return query.getResultList();
    }

    @Override
    public UserRole findByType(String type) {
        Session session = sessionFactory.getCurrentSession();

        Query<UserRole> query = session.createQuery("from UserRole where type=:theType", UserRole.class);

        query.setParameter("theType", type);
        query.setMaxResults(1);

        List<UserRole> roleList = query.getResultList();
        if(roleList == null)
            return null;
        else
            return roleList.get(0);
    }

    @Override
    public UserRole findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserRole.class, id);
    }
}
