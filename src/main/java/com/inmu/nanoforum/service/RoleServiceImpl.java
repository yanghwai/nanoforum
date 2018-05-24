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

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public UserRole findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public UserRole findByType(String type){
        return roleDao.findByType(type);
    }

    @Override
    public List<UserRole> findAll() {
        return roleDao.findAll();
    }
}
