package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;

import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User login(String bankCode, String password) {
        User user = userDao.findByCode(bankCode);
        System.out.println(user +"serviceUser");
        if (user == null) {
            throw new RuntimeException("账户错误");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return user;
    }

    @Override
    public void updateHeadImg(Integer id, String imgPath) {
        User u = new User();
        u.setId(id);
        u.setImgPath(imgPath);
        userDao.update(u);
    }

    @Override
    public User selectByCode(String bankCode) {
        return userDao.findByCode(bankCode);
    }
}
