package com.qzp.blog.dao;

import com.qzp.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/11 7:50
 * @Desc 博客文章的管理的dao接口
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    /**
     * @Description 自定义方法分页在前端展示博客
     * @param pageable
     * @return {@link List< Blog>}
     * @author qzp
     * @date 2020/7/16 14:38
     */
    @Query("select b from Blog b where b.recommend = true ")
    List<Blog> findTop(Pageable pageable);

    /**
     * @Description 根据搜索条件在搜素页分页展示对应博客
     * @param query
     * @param pageable
     * @return {@link Page<Blog>}
     * @author qzp
     * @date 2020/7/16 21:55
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

   /**
    * @Description 更新博客浏览次数
    * @param id 
    * @return {@link int}
    * @author qzp
    * @date 2020/7/19 10:49
    */
    @Modifying
    @Query("update Blog b set b.views=b.views+1 where b.id =?1")
    int updateViews(Long id);

    /**
     * @Description 自定义一个方法获取博客的年份
     * @param
     * @return {@link List<Blog>}
     * @author qzp
     * @date 2020/7/19 19:13
     */
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    /**
     * @Description 自定义一个方法来根据年份来查询对应的博客
     * @param year
     * @return {@link List<Blog>}
     * @author qzp
     * @date 2020/7/19 19:17
     */
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
