package com.qzp.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/6 9:50
 * @Desc 博客分类实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
@Entity(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    // 一个类型对应多个blog, mappedBy 单向关系不需要设置该属性，双向关系必须设置，避免双方都建立外键字段，且由多的一方来维护
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
