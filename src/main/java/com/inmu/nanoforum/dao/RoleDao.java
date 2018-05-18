package com.inmu.nanoforum.dao;

import com.inmu.nanoforum.model.UserRole;

import java.util.List;

public interface RoleDao {
    List<UserRole> findAll();

    UserRole findByType(String type);

    UserRole findById(int id);
}
