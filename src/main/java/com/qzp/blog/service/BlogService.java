package com.qzp.blog.service;

import com.qzp.blog.po.Blog;
import com.qzp.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author quezhipeng
 * @date 2020/7/11 7:36
 * @Desc 博客文章管理Service接口
 */
public interface BlogService {

    /**
     * @Description 新增博客文章
     * @param blog
     * @return {@link Blog}
     * @author qzp
     * @date 2020/7/11 7:43
     */
    Blog saveBlog(Blog blog);

    /**
     * @Description 获取最新博客文章
     * @param id 最新博客文章的id
     * @return {@link Blog}
     * @author qzp
     * @date 2020/7/11 7:44
     */
    Blog getBlog(long id);

    /**
     * @Description 按照查询条件来将分页展示博客文章
     * @param pageable
     * @param blog 将筛选条件全部保存到Blog对象中
     * @return {@link Page<Blog>}
     * @author qzp
     * @date 2020/7/11 7:45
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * @Description 根据博客文章的id来修改对应的博客文章信息
     * @param id
     * @param blog
     * @return {@link Blog}
     * @author qzp
     * @date 2020/7/11 7:47
     */
    Blog updateBlog(long id,Blog blog);

    /**
     * @Description 根据博客文章的id来删除对应的博客文章
     * @param id
     * @return {@link}
     * @author qzp
     * @date 2020/7/11 7:47
     */
    void deleteBlog(long id);

    /**
     * @Description 查询所有的blog
     * @param  pageable
     * @return {@link List< Blog>}
     * @author qzp
     * @date 2020/7/15 16:08
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * @Description 前端页面展示的文章
     * @param size
     * @return {@link List< Blog>}
     * @author qzp
     * @date 2020/7/16 14:31
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * @Description 根据搜索条件在搜素页分页展示对应博客
     * @param query:搜索条件
     * @param pageable
     * @return {@link Page<Blog>}
     * @author qzp
     * @date 2020/7/16 21:52
     */
    Page<Blog> listBlog(String query, Pageable pageable);

    /**
     * @Description 将博客内容转化成markdown模式
     * @param id
     * @return {@link Blog}
     * @author qzp
     * @date 2020/7/17 9:49
     */
    Blog getAndConvert(Long id);

    /**
     * @Description 根据tagId查询对应博客
     * @param tagId
     * @param pageable
     * @return {@link Page< Blog>}
     * @author qzp
     * @date 2020/7/19 17:03
     */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * @Description 对所有博客进行归档
     * @param
     * @return {@link Map<String,List<Blog>>}
     * @author qzp
     * @date 2020/7/19 19:08
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * @Description 统计博客总数
     * @param
     * @return {@link Long}
     * @author qzp
     * @date 2020/7/19 19:41
     */
    Long countBlog();
}
