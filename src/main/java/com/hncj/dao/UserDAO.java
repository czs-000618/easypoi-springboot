package com.hncj.dao;

import com.hncj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDAO {
    List<User> findAll();

    void save(User user);
}
