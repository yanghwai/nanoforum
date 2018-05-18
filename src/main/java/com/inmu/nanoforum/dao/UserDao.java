package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.AppUser;

import java.util.List;

public interface UserDao {
    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void save(AppUser AppUser);

    void deleteBySSO(String sso);

    List<AppUser> findAllUsers();

    void deleteById(int id);
}
