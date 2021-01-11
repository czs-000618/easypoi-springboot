package com.hncj.service;

import com.hncj.dao.UserDAO;
import com.hncj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void saveAll(List<User> users){
        users.forEach(user->{
            user.setId(null);
            String fileName = user.getPhoto().substring(user.getPhoto().lastIndexOf("/")+1);
            user.setPhoto(fileName);
            userDAO.save(user);
        });
    }

}
