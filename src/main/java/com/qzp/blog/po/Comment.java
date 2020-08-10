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
 * @date 2020/7/6 9:57
 * @Desc 评论实体类
 */
@SuppressWarnings("all")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String email;
    // 头像
    private String avatar;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;
    // 建立自关联
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;
}
