package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.RoleDao;
import com.inmu.nanoforum.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao dao;

    @Override
    public UserRole findById(int id) {
        return dao.findById(id);
    }

    @Override
    public UserRole findByType(String type){
        return dao.findByType(type);
    }

    @Override
    public List<UserRole> findAll() {
        return dao.findAll();
    }
}
