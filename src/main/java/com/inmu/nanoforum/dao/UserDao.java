package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.AppUser;

import java.util.List;

public interface UserDao {
    AppUser getById(int id);

    AppUser getBySsoId(String ssoId);

    void save(AppUser AppUser);

    void deleteBySsoId(String sso);

    List<AppUser> getAllUsers();

    void deleteById(int id);

    List<AppUser> searchBySsoId(String ssoId);
}
