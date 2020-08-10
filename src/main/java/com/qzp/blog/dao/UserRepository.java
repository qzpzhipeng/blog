package com.qzp.blog.dao;

import com.qzp.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author quezhipeng
 * @date 2020/7/7 14:50
 * @Desc 用户登录的dao接口
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @Description 用户登录的接口
     * @param userName 用户名
     * @param passWord 密码
     * @return {@link User}
     * @author qzp
     * @date 2020/7/7 17:59
     */
    User findByUserNameAndPassWord(String userName,String passWord);
}
