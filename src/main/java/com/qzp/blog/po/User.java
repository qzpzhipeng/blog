package com.qzp.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/6 10:03
 * @Desc 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String userName;
    private String passWord;
    private String email;
    private Integer type;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

}
