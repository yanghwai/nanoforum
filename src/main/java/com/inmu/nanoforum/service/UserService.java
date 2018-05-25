package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser getById(int id);

    AppUser getBySsoId(String ssoId);

    void saveUser(AppUser appUser);

    void updateUser(AppUser appUser);

    void deleteUserBySsoId(String ssoId);

    void deleteById(int id);

    List<AppUser> findAllUsers();

    boolean isUserSSOUnique(String ssoId);

    List<AppUser> searchBySsoId(String ssoId);
}
