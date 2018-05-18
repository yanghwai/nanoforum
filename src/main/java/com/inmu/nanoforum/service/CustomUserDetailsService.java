package com.inmu.nanoforum.service;

import com.inmu.nanoforum.model.AppUser;
import com.inmu.nanoforum.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        AppUser AppUser = userService.findBySSO(ssoId);
        logger.info("AppUser : {}"+ AppUser);
        if(AppUser ==null){
            logger.info("AppUser not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(AppUser.getSsoId(), AppUser.getPassword(),
                true, true, true, true, getGrantedAuthorities(AppUser));
    }


    private List<GrantedAuthority> getGrantedAuthorities(AppUser AppUser){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserRole userRole : AppUser.getUserRoles()){
            logger.info("UserRole : {}"+ userRole);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ userRole.getType()));
        }
        logger.info("authorities : {}"+ authorities);
        return authorities;
    }
}
