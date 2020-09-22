package com.qfedu.dao;

import com.qfedu.entity.User;

public interface UserDao {
    public User findByCode(String bankCode);
    public void update (User user);
}
