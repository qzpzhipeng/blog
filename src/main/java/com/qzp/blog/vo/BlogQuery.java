package com.qzp.blog.vo;

import lombok.Data;

/**
 * @author quezhipeng
 * @date 2020/7/12 19:05
 * @Desc 博客文章的筛选条件
 */
@Data
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
