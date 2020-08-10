package com.qzp.blog.service;

import com.qzp.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/9 10:37
 * @Desc 博客标签名称service接口
 */
public interface TagService {

    /**
     * @Description 新增标签
     * @param type 新增标签名称
     * @return {@link Tag}
     * @author qzp
     * @date 2020/7/10 10:09
     */
    Tag saveTag(Tag type);

    /**
     * @Description 获取新增标签
     * @param id 新增标签的id
     * @return {@link Tag}
     * @author qzp
     * @date 2020/7/10 10:16
     */
    Tag getTag(Long id);

    /**
     * @Description 标签分页展示
     * @param pageable
     * @return {@link Page< Tag>}
     * @author qzp
     * @date 2020/7/10 10:18
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * @Description 根据标签数据id修改对应标签
     * @param id
     * @param type
     * @return {@link Tag}
     * @author qzp
     * @date 2020/7/10 10:24
     */
    Tag updateTag(Long id, Tag type);

    /**
     * @Description 根据标签数据的id删除对应分类名称
     * @param id
     * @return {@link}
     * @author qzp
     * @date 2020/7/10 10:23
     */
    void deleteTag(Long id);

    /**
     * @Description 根据标签的名称查找数据库中对应的标签
     * @param name
     * @return {@link Tag}
     * @author qzp
     * @date 2020/7/10 10:16
     */
    Tag getTagByName(String name);

    /**
     * @Description 获取所有的标签
     * @param
     * @return {@link List<Tag>}
     * @author qzp
     * @date 2020/7/13 16:14
     */
    List<Tag> getAllTag();

    /**
     * @Description 获取前端传给后台的所有标签的ids
     * @param ids
     * @return {@link List<Tag>}
     * @author qzp
     * @date 2020/7/13 16:57
     */
    List<Tag> getAllTag(String ids);

    /**
     * @Description 获取后台给前台展示的标签
     * @param size
     * @return {@link List<Tag>}
     * @author qzp
     * @date 2020/7/15 19:31
     */
    List<Tag> listTagTop(Integer size);
}
