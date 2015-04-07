package com.imad.common.security.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.imad.common.entity.EntrepriseUser;
import com.imad.common.services.UsersService;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Resource(name = "usersService")
    private UsersService userService;
    

    @SuppressWarnings("unchecked")
    @Override
    public EntrepriseUser findByUserName(String username) {

        List<EntrepriseUser> users;

        users = sessionFactory.getCurrentSession().getNamedQuery("EntrepriseUsers.findByUsername").setParameter("username", username).list();

        if (users.size() < 1 || users.size() > 1) {
            return null;
        } else {
            return users.get(0);
        }
    }
    
    @Override
    public void updateFailAttempts(String username){
        Session session = sessionFactory.getCurrentSession();
        EntrepriseUser users = userService.getUserByUsername(username);
        boolean locked = false;
        if (!StringUtils.isEmpty(users)) {
            users.setAttempts(users.getAttempts() + 1 );
            if ( users.getAttempts() >= 3 ){
                users.setLocked(""+Boolean.TRUE);
                locked = true;
            }
            session.save(users);
            if (locked)
                throw new LockedException("User Account is locked!");
        }
        
    }
    
    @Override
    public void resetFailAttempts(String username){
        Session session = sessionFactory.getCurrentSession();
        EntrepriseUser users = userService.getUserByUsername(username);
        if (!StringUtils.isEmpty(users)) {
            users.setAttempts(0);
            session.save(users);
        }    
    }
    
    @Override
    public EntrepriseUser getUserAttempts(String username){
    	EntrepriseUser users = userService.getUserByUsername(username);
        if (StringUtils.isEmpty(users)) {
            return null;
        } else {
            return users;
        }
    }

}
