package com.qzp.blog.service;

import com.qzp.blog.po.Comment;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/18 8:31
 * @Desc comment的Service接口
 */
public interface CommentService {

    /**
     * @Description 根据blog的id查询当前的评论
     * @param blogId
     * @return {@link List<Comment>}
     * @author qzp
     * @date 2020/7/18 8:39
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * @Description 保存评论
     * @param comment
     * @return {@link Comment}
     * @author qzp
     * @date 2020/7/18 8:41
     */
    Comment saveComment(Comment comment);
}
