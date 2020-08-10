package com.qzp.blog.service.impl;

import com.qzp.blog.dao.UserRepository;
import com.qzp.blog.po.User;
import com.qzp.blog.service.UserService;
import com.qzp.blog.util.MD5Untils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author quezhipeng
 * @date 2020/7/7 14:46
 * @Desc 用户登录的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String passWord) {
        User user = userRepository.findByUserNameAndPassWord(userName, MD5Untils.code(passWord));
        return user;
    }
}
