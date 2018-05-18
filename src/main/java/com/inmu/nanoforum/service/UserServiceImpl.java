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
    public AppUser findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public AppUser findBySSO(String sso) {
        return userDao.findBySSO(sso);
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
        AppUser entity = userDao.findById(appUser.getId());
        if(entity!=null){
            entity.setSsoId(appUser.getSsoId());
            if(!appUser.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(appUser.getPassword()));
            }
            entity.setFirstName(appUser.getFirstName());
            entity.setLastName(appUser.getLastName());
            entity.setEmail(appUser.getEmail());
            entity.setUserRoles(appUser.getUserRoles());
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
        return userDao.findAllUsers();
    }

    @Override
    public boolean isUserSSOUnique(String sso) {
        AppUser AppUser = findBySSO(sso);
        return (AppUser != null);
    }
}
