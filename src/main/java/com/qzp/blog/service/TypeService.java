package com.qzp.blog.service;

import com.qzp.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/8 10:37
 * @Desc 博客分类名称service接口
 */
public interface TypeService {

    /**
     * @Description 新增分类
     * @param type 新增分类名称
     * @return {@link Type}
     * @author qzp
     * @date 2020/7/8 10:41
     */
    Type saveType(Type type);

    /**
     * @Description 获取新增分类
     * @param id 新增分类的id
     * @return {@link Type}
     * @author qzp
     * @date 2020/7/8 10:42
     */
    Type getType(long id);

    /**
     * @Description 分类分页展示
     * @param pageable
     * @return {@link Page< Type>}
     * @author qzp
     * @date 2020/7/8 10:43
     */
    Page<Type> listType(Pageable pageable);

    /**
     * @Description 根据分类数据id修改对应分类信息
     * @param id
     * @param type
     * @return {@link Type}
     * @author qzp
     * @date 2020/7/8 10:45
     */
    Type updateType(long id,Type type);

    /**
     * @Description 根据分类数据的id删除对应分类
     * @param id
     * @return {@link}
     * @author qzp
     * @date 2020/7/8 10:46
     */
    void deleteType(long id);

    /**
     * @Description 根据分类的名称查找数据库中对应的分类
     * @param name
     * @return {@link Type}
     * @author qzp
     * @date 2020/7/9 9:03
     */
    Type getTypeByName(String name);

    /**
     * @Description 获取所有的type类型
     * @param
     * @return {@link List<Type>}
     * @author qzp
     * @date 2020/7/12 18:55
     */
    List<Type> getAllType();

    /**
     * @Description 获取类型
     * @param size 类型数量
     * @return {@link List<Type>}
     * @author qzp
     * @date 2020/7/15 16:22
     */
    List<Type> listTypeTop(Integer size);
}
