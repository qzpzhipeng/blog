package com.qzp.blog.service.impl;


import com.qzp.blog.dao.TagRepository;
import com.qzp.blog.exceptions.NotFoundException;
import com.qzp.blog.po.Tag;
import com.qzp.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/9 10:47
 * @Desc 博客标签名称Service接口实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getAllTag(String ids) { //前台给后台传入的所有的id 1,2,3
        return tagRepository.findAllById(convertToListId(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToListId(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    private List<String> convertToListName(String tagName){
        List<String> list = new ArrayList<>();
        if (!"".equals(tagName) && tagName != null) {
            String[] idarray = tagName.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new String(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
