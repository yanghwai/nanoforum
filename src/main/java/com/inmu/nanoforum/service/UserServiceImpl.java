package com.inmu.nanoforum.service;

import com.inmu.nanoforum.dao.UserDao;
import com.inmu.nanoforum.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public AppUser getById(int id) {

        return userDao.getById(id);
    }

    @Override
    public AppUser getBySsoId(String ssoId) {

        return userDao.getBySsoId(ssoId);
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
        AppUser theUser = userDao.getById(appUser.getId());
        if(theUser!=null){
            theUser.setSsoId(appUser.getSsoId());
            if(!appUser.getPassword().equals(theUser.getPassword())){
                theUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            }
            theUser.setFirstName(appUser.getFirstName());
            theUser.setLastName(appUser.getLastName());
            theUser.setEmail(appUser.getEmail());
            theUser.setUserRoles(appUser.getUserRoles());
        }
    }

    @Override
    public void deleteUserBySsoId(String ssoId) {
        userDao.deleteBySsoId(ssoId);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public List<AppUser> findAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(String sso) {
        AppUser AppUser = getBySsoId(sso);
        return (AppUser == null);
    }

    @Override
    public List<AppUser> searchBySsoId(String ssoId) {
        return userDao.searchBySsoId(ssoId);
    }

}
