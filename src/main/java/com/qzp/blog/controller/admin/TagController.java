package com.qzp.blog.controller.admin;


import com.qzp.blog.po.Tag;
import com.qzp.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Description 博客后台系统标签Controller
 * @author qzp
 * @date 2020/7/10 9:28
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * @Description 博客标签分页展示
     * @param pageable
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:36
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * @Description 博客标签新增
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:36
     */
    @GetMapping("/tags/add")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
     * @Description 博客标签修改
     * @param id
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:38
     */
    @GetMapping("/tags/{id}/edit")
    public String editTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * @Description 检验添加博客标签名称是否合格后跳转
     * @param tag
     * @param result
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:38
     */
    @PostMapping("/tag")
    public String post(@Valid Tag tag,BindingResult result, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增操作失败");
        } else {
            attributes.addFlashAttribute("message", "新增操作成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * @Description 检验修改博客标签名称是否合格后跳转
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:41
     */
    @PostMapping("/tag/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * @Description 删除博客标签
     * @param id
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/10 9:43
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
