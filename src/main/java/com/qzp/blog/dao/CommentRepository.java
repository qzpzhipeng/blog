package com.qzp.blog.dao;

import com.qzp.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/18 8:40
 * @Desc comment实体类的dao接口
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    /**
     * @Description 根据blogId查询博客当前评论按sort的方式进行排序
     * @param blogId
     * @param sort
     * @return {@link List<Comment>}
     * @author qzp
     * @date 2020/7/18 8:48
     */
//    List<Comment> findByBlogId(Long blogId, Sort sort);

    /**
     * @Description 根据blogId查询博客当前评论按sort的方式进行排序,增加了处理评论功能
     * @param blogId
     * @param sort
     * @return {@link List<Comment>}
     * @author qzp
     * @date 2020/7/19 7:19
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
