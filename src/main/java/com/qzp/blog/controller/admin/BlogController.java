package com.qzp.blog.controller.admin;

import com.qzp.blog.po.Blog;
import com.qzp.blog.po.Tag;
import com.qzp.blog.po.Type;
import com.qzp.blog.po.User;
import com.qzp.blog.service.BlogService;
import com.qzp.blog.service.TagService;
import com.qzp.blog.service.TypeService;
import com.qzp.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author quezhipeng
 * @date 2020/7/7 22:13
 * @Desc 博客后台controller
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * @Description 后台博客页面分页展示
     * @param
     * @return {@link String}
     * @author qzp
     * @date 2020/7/8 10:54
     */
    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                       BlogQuery blog, Model model, HttpServletRequest request){
        model.addAttribute("types",typeService.getAllType());
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("path",request.getContextPath());
       return "admin/blogs";
    }

    /**
     * @Description 后台博客管理页面根据博客筛选分页表格局部展示
     * @param pageable
     * @param blog
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/12 18:41
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model){
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    /**
     * @Description 新增博客页面
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/13 16:33
     */
    @GetMapping("/blogs/add")
    public String addBlog(Model model){
        List<Type> types = typeService.getAllType();
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        return "admin/blogs-input";
    }

    /**
     * @Description 修改博客页面内容
     * @param id
     * @param model
     * @return {@link String}
     * @author qzp
     * @date 2020/7/14 14:48
     */
    @GetMapping("/blogs/{id}/edit")
    public String editBlog(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagService.getAllTag());
        model.addAttribute("types",typeService.getAllType());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    /**
     * @Description 检查新增和更新博客是否合格
     * @param blog
     * @param session
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/14 7:54
     */
    @PostMapping("/blog")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.getAllTag(blog.getTagIds()));
        Blog t;
        if (blog.getId() == null) {
            t  =  blogService.saveBlog(blog);
        } else {
            t = blogService.updateBlog(blog.getId(), blog);
        }
        if(t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * @Description 删除博客文章
     * @param id
     * @param attributes
     * @return {@link String}
     * @author qzp
     * @date 2020/7/15 16:02
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
