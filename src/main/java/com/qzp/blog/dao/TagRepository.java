package com.qzp.blog.dao;

import com.qzp.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/9 10:47
 * @Desc 博客标签名称的接口
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    /**
     * @Description 根据标签的名称来查询数据库中的是否存在相同的标签名称
     * @param name
     * @return {@link Tag}
     * @author qzp
     * @date 2020/7/10 10:27
     */
    Tag findByName(String name);

    /**
     * @Description 自定义方式根据标签在首页展示标签名称
     * @param pageable
     * @return {@link List<Tag>}
     * @author qzp
     * @date 2020/7/15 19:48
     */
    @Query("select t from t_tag t")
    List<Tag> findTop(Pageable pageable);
}
