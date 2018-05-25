package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.RoleDao;
import com.inmu.nanoforum.dao.UserDao;
import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public AppUser findById(int id) {
        AppUser user = userDao.findById(id);
        setRoleList(user);
        return user;
    }

    @Override
    public AppUser findBySSO(String sso) {

        AppUser user = userDao.findBySSO(sso);
        setRoleList(user);
        return user;
    }

    @Override
    public void saveUser(AppUser appUser) {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userDao.save(appUser);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    @Override
    public void updateUser(AppUser appUser) {
        AppUser theUser = userDao.findById(appUser.getId());
        if(theUser!=null){
            theUser.setSsoId(appUser.getSsoId());
            if(!appUser.getPassword().equals(theUser.getPassword())){
                theUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            }
            theUser.setFirstName(appUser.getFirstName());
            theUser.setLastName(appUser.getLastName());
            theUser.setEmail(appUser.getEmail());
            theUser.setRoleList(appUser.getRoleList());
            saveUserRoles(theUser);
        }
    }

    @Override
    public void deleteUserBySSO(String sso) {
        userDao.deleteBySSO(sso);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public List<AppUser> findAllUsers() {
        List<AppUser> userList = userDao.findAllUsers();
        for(AppUser user : userList){
            setRoleList(user);
        }

        return userList;
    }

    @Override
    public boolean isUserSSOUnique(String sso) {
        AppUser AppUser = findBySSO(sso);
        return (AppUser != null);
    }

    private void setRoleList(AppUser appUser){
        List<String> roleList = new ArrayList<>();
        for(UserRole role: appUser.getUserRoles()){
            roleList.add(role.getType());
        }

        appUser.setRoleList(roleList);
    }

    private void saveUserRoles(AppUser appUser){
        Set<UserRole> roleSet = new HashSet<>();
        for(String roleType: appUser.getRoleList()){
            roleSet.add(roleDao.findByType(roleType));
        }

        appUser.setUserRoles(roleSet);
    }

}
