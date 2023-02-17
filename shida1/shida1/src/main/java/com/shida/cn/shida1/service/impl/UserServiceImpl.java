package com.shida.cn.shida1.service.impl;


import com.shida.cn.shida1.dao.UserDao;
import com.shida.cn.shida1.entity.User;
import com.shida.cn.shida1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User findUser(String username) {
        User user = userDao.findByName(username);
        return user;
    }
}
