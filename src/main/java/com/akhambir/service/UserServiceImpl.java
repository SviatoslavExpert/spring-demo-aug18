package com.akhambir.service;

import com.akhambir.dao.UserDao;
import com.akhambir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailService mailService;


    @Override
    public User addUser(User user) {
        user.setToken(getToken());
        User u = userDao.addUser(user);
        mailService.notify(user);
        return u;
    }

    @Override
    public User getByEmail(User user) {
        return userDao.getByEmail(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
