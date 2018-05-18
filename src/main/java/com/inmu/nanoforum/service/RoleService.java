package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.UserRole;

import java.util.List;

public interface RoleService {
    UserRole findById(int id);

    UserRole findByType(String type);

    List<UserRole> findAll();
}
