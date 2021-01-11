package com.hncj.service;

import com.hncj.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void  saveAll(List<User> users);
}
