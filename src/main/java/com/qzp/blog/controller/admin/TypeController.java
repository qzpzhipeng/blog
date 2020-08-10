package com.qzp.blog.controller.admin;

import com.qzp.blog.po.Type;
import com.qzp.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author quezhipeng
 * @date 2020/7/8 12:00
 * @Desc 博客后台分类管理
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("qzp")
    public String qzp(){
        return "admin/types";
    }
    /**
     * @Description 分页展示全部分类名称
     * @param pageable
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 9:20
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);
        return "admin/types";
    }
    /**
     * @Description 添加分类名称
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 9:21
     */
    @GetMapping("/types/add")
    public String addType(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }
    /**
     * @Description 修改分类名称
     * @param id
    * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 10:49
     */
    @GetMapping("types/{id}/edit")
    public String editType(@PathVariable long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }

    /**
     * @Description 删除分类名称
     * @param id
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 10:51
     */
    @GetMapping("types/{id}/delete")
    public String deleteType(@PathVariable long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
    /**
     * @Description 检验添加分类名称是否合格后跳转
     * @param type
     * @param result
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 9:22
     */
    @PostMapping("/type")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            result.rejectValue("name","name-error","分类名称已存在，不可重复添加");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","新增操作失败");
        }else {
            attributes.addFlashAttribute("message","新增操作成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * @Description 检验修改分类名称是否合格后跳转
     * @param type
     * @param result
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/9 9:22
     */
    @PostMapping("/type/{id}")
    public String editpost(@Valid Type type, BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            result.rejectValue("name","name-error","分类名称已存在，不可重复");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        Type t = typeService.updateType(id,type);
        if(t == null){
            attributes.addFlashAttribute("message","修改操作失败");
        }else {
            attributes.addFlashAttribute("message","修改操作成功");
        }
        return "redirect:/admin/types";
    }
}
