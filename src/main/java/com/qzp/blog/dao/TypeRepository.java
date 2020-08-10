package com.qzp.blog.dao;

import com.qzp.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/8 11:14
 * @Desc 博客分类名称的接口
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    /**
     * @Description 根据分类的名称来查询数据库中的是否存在相同的分类名称
     * @param name
     * @return {@link Type}
     * @author qzp
     * @date 2020/7/9 9:06
     */
    Type findByName(String name);

    /**
     * @Description 自定义方式根据分页在首页展示分类名称
     * @param pageable
     * @return {@link List< Type>}
     * @author qzp
     * @date 2020/7/15 19:23
     */
    @Query("select t from  t_type t")
    List<Type> findTopByBlogsAndSize(Pageable pageable);
}
