package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void saveUser(AppUser appUser);

    void updateUser(AppUser appUser);

    void deleteUserBySSO(String sso);

    void deleteById(int id);

    List<AppUser> findAllUsers();

    boolean isUserSSOUnique(String sso);
}
