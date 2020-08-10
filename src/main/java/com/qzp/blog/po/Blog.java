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
 * @date 2020/7/6 9:24
 * @Desc 博客实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@Entity
@Table(name = "t_blog")
public class Blog {
    // 博客id
    @Id
    @GeneratedValue
    private Long id;
    // 博客标题
    private String title;
    // 博客内容
    // 解决接受数据过长的无法接受问题
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    // 首图
    private String firstPictures;
    // 标记
    private String flag;
    // 浏览次数
    private Integer views;
    // 是否开启赞赏
    private boolean appreciation;
    // 是否开启版权，转载声明
    private boolean shareStatement;
    // 是否开启评论
    private boolean commentabled;
    // 是否发布
    private boolean published;
    // 是否推荐
    private boolean recommend;
    // 发表时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    // 修改时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // 建立关系，多个blog对应一个type
    @ManyToOne
    private Type type;

    // 建立关系，多个blog对应多个tags
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    // 建立关系，多个blog对应一个user
    @ManyToOne
    private User user;

    // 建立关系，一个blog对应多个comment
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private String tagIds;

    private String description;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
