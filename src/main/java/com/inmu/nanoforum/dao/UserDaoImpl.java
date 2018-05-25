package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.AppUser;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AppUser getById(int id) {

        Session session = sessionFactory.getCurrentSession();

        AppUser appUser = session.get(AppUser.class, id);
        if(appUser !=null){
            Hibernate.initialize(appUser.getUserRoles());
        }
        return appUser;
    }

    @Override
    public AppUser getBySsoId(String ssoId) {
        logger.info("SSO : {}"+ ssoId);
        Session session = sessionFactory.getCurrentSession();
        Query<AppUser> query = session.createQuery("from AppUser where ssoId= :theSsoId", AppUser.class);
        query.setParameter("theSsoId", ssoId);

        query.setMaxResults(1);
        List<AppUser> users = query.getResultList();

        if(users !=null && users.size()>0){
            Hibernate.initialize(users.get(0).getUserRoles());
            return users.get(0);
        }
        return null;
    }

    @Override
    public void save(AppUser AppUser) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(AppUser);
    }

    @Override
    public void deleteBySsoId(String sso) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from AppUser where ssoId=: theSSoId", AppUser.class);
        query.setParameter("theSSoId", sso);

        query.executeUpdate();
    }

    @Override
    public List<AppUser> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();

        Query<AppUser> query = session.createQuery("from AppUser order by ssoId", AppUser.class);

        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();

        AppUser theUser = session.get(AppUser.class, id);
        session.delete(theUser);
    }

    @Override
    public List<AppUser> searchBySsoId(String ssoId) {
        Session session = sessionFactory.getCurrentSession();

        Query<AppUser> query = session.createQuery("from AppUser where lower(ssoId) like :theSsoId order by ssoId", AppUser.class);
        query.setParameter("theSsoId", ssoId.toLowerCase()+"%");
        query.setMaxResults(100);

        return query.getResultList();
    }
}
