package com.qzp.blog.service;

import com.qzp.blog.po.User;

/**
 * @author quezhipeng
 * @date 2020/7/7 14:43
 * @Desc 用户登录service接口类
 */
public interface UserService {

    /**
     * @Description 检查登录用户
     * @param userName :用户名
     * @param passWord :密码
     * @return {@link User}
     * @author qzp
     * @date 2020/7/7 14:47
     */
    User checkUser(String userName,String passWord);
}
