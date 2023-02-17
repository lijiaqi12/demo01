package com.shida.cn.shida1.service;


import com.shida.cn.shida1.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public interface UserService {
    //1.增加用户的业务
    void addUser(User user);
    //2.查找用户的方法
    User findUser(String username);
}
