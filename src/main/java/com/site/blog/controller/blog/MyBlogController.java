package com.site.blog.controller.blog;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.blog.constants.BlogStatusConstants;
import com.site.blog.constants.HttpStatusConstants;
import com.site.blog.constants.LinkConstants;
import com.site.blog.controller.vo.BlogDetailVO;
import com.site.blog.dto.AjaxResultPage;
import com.site.blog.dto.Result;
import com.site.blog.entity.*;
import com.site.blog.service.*;
import com.site.blog.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Description: 博客Controller
 * @date: 2019/9/1 20:57
 */

@Controller
public class MyBlogController {


    public static String theme = "amaze";

    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private BlogConfigService blogConfigService;

    @Autowired
    private BlogTagRelationService blogTagRelationService;

    @Autowired
    private BlogCommentService blogCommentService;

    @Autowired
    private BlogLinkService blogLinkService;

    /**
     * 博客首页
     *
     * @param request
     * @return java.lang.String
     * @date 2019/9/6 7:03
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    /**
     * 博客分页
     *
     * @param request
     * @param pageNum
     * @return java.lang.String
     * @date 2019/9/6 7:03
     */
    @GetMapping({"/page/{pageNum}"})
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        Page<BlogInfo> page = new Page<BlogInfo>(pageNum, 8);
        blogInfoService.page(page, new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus,BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                .orderByDesc(BlogInfo::getCreateTime));
        PageResult blogPageResult = new PageResult
                (page.getRecords(), page.getTotal(), 8, pageNum);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("newBlogs", blogInfoService.getNewBlog());
        request.setAttribute("hotBlogs", blogInfoService.getHotBlog());
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("pageName", "首页");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/index";
    }

    /**
     * 搜索
     *
     * @param request
     * @param keyword
     * @return java.lang.String
     * @date 2019/9/6 7:03
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    @GetMapping({"/search/{keyword}/{pageNum}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword, @PathVariable("pageNum") Integer pageNum) {

        Page<BlogInfo> page = new Page<BlogInfo>(pageNum, 8);
        blogInfoService.page(page, new QueryWrapper<BlogInfo>()
                .lambda().like(BlogInfo::getBlogTitle, keyword)
                .eq(BlogInfo::getBlogStatus,BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                .orderByDesc(BlogInfo::getCreateTime));
        PageResult blogPageResult = new PageResult
                (page.getRecords(), page.getTotal(), 8, pageNum);

        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "搜索");
        request.setAttribute("pageUrl", "search");
        request.setAttribute("keyword", keyword);
        request.setAttribute("newBlogs", blogInfoService.getNewBlog());
        request.setAttribute("hotBlogs", blogInfoService.getHotBlog());
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    /**
     * 标签
     *
     * @param request
     * @param tagId
     * @return java.lang.String
     * @date 2019/9/6 7:04
     */
    @GetMapping({"/tag/{tagId}"})
    public String tag(HttpServletRequest request, @PathVariable("tagId") String tagId) {
        return tag(request, tagId, 1);
    }

    /**
     * 标签分类
     *
     * @param request
     * @param tagId
     * @param pageNum
     * @return java.lang.String
     * @date 2019/9/6 7:04
     */
    @GetMapping({"/tag/{tagId}/{pageNum}"})
    public String tag(HttpServletRequest request, @PathVariable("tagId") String tagId, @PathVariable("pageNum") Integer pageNum) {
        List<BlogTagRelation> list = blogTagRelationService.list(new QueryWrapper<BlogTagRelation>()
                .lambda().eq(BlogTagRelation::getTagId, tagId));
        PageResult blogPageResult = null;
        if (!list.isEmpty()){
            Page<BlogInfo> page = new Page<BlogInfo>(pageNum, 8);
            blogInfoService.page(page, new QueryWrapper<BlogInfo>()
                    .lambda()
                    .eq(BlogInfo::getBlogStatus,BlogStatusConstants.ONE)
                    .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                    .in(BlogInfo::getBlogId, list.stream().map(BlogTagRelation::getBlogId).toArray())
                    .orderByDesc(BlogInfo::getCreateTime));
            blogPageResult = new PageResult
                    (page.getRecords(), page.getTotal(), 8, pageNum);
        }
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "标签");
        request.setAttribute("pageUrl", "tag");
        request.setAttribute("keyword", tagId);
        request.setAttribute("newBlogs", blogInfoService.getNewBlog());
        request.setAttribute("hotBlogs", blogInfoService.getHotBlog());
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    @GetMapping({"/category/{categoryName}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName) {
        return category(request, categoryName, 1);
    }

    /**
     * 分类列表
     *
     * @param request
     * @param categoryName
     * @param pageNum
     * @return java.lang.String
     * @date 2019/9/6 13:04
     */
    @GetMapping({"/category/{categoryName}/{pageNum}"})
    public String category(HttpServletRequest request, @PathVariable("categoryName") String categoryName, @PathVariable("pageNum") Integer pageNum) {
        Page<BlogInfo> page = new Page<BlogInfo>(pageNum, 8);
        blogInfoService.page(page, new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus,BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                .eq(BlogInfo::getBlogCategoryName, categoryName)
                .orderByDesc(BlogInfo::getCreateTime));
        PageResult blogPageResult = new PageResult
                (page.getRecords(), page.getTotal(), 8, pageNum);

        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "分类");
        request.setAttribute("pageUrl", "category");
        request.setAttribute("keyword", categoryName);
        request.setAttribute("newBlogs", blogInfoService.getNewBlog());
        request.setAttribute("hotBlogs", blogInfoService.getHotBlog());
        request.setAttribute("hotTags", blogTagService.getBlogTagCountForIndex());
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    /**
     * 文章详情
     *
     * @param request
     * @param blogId
     * @param commentPage
     * @return java.lang.String
     * @date 2019/9/6 13:09
     */
    @GetMapping({"/blog/{blogId}", "/article/{blogId}"})
    public String detail(HttpServletRequest request, @PathVariable("blogId") Long blogId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
        // 获得文章info
        BlogInfo blogInfo = blogInfoService.getById(blogId);
        List<BlogTagRelation> blogTagRelations = blogTagRelationService.list(new QueryWrapper<BlogTagRelation>()
                .lambda()
                .eq(BlogTagRelation::getBlogId, blogId));
        blogInfo.setBlogViews(blogInfo.getBlogViews() + 1);
        blogInfoService.updateById(blogInfo);

        // 获得关联的标签列表
        List<Integer> tagIds = new ArrayList<>();
        List<BlogTag> tagList = new ArrayList<>();
        if (!blogTagRelations.isEmpty()) {
            tagIds = blogTagRelations.stream()
                    .map(BlogTagRelation::getTagId).collect(Collectors.toList());
            tagList = blogTagService.list(new QueryWrapper<BlogTag>().lambda().in(BlogTag::getTagId, tagIds));
        }

        // 获得关联的评论
        Page<BlogComment> page = new Page<BlogComment>(commentPage, 5);
        blogCommentService.page(page, new QueryWrapper<BlogComment>()
                .lambda()
                .eq(BlogComment::getBlogId, blogId)
                .eq(BlogComment::getCommentStatus, BlogStatusConstants.ONE)
                .eq(BlogComment::getIsDeleted,BlogStatusConstants.ZERO)
                .orderByDesc(BlogComment::getCommentCreateTime));
        PageResult blogPageResult = new PageResult
                (page.getRecords(), page.getTotal(), 5, commentPage);

        // 关联评论的Count
        Integer blogCommentCount = blogCommentService.count(new QueryWrapper<BlogComment>()
                .lambda()
                .eq(BlogComment::getBlogId, blogId)
                .eq(BlogComment::getCommentStatus, BlogStatusConstants.ONE));

        BlogDetailVO blogDetailVO = new BlogDetailVO();
        BeanUtils.copyProperties(blogInfo,blogDetailVO);
        blogDetailVO.setBlogContent(MarkDownUtils.mdToHtml(blogDetailVO.getBlogContent()));
        blogDetailVO.setCommentCount(blogCommentCount);
        request.setAttribute("blogDetailVO", blogDetailVO);
        request.setAttribute("tagList", tagList);
        if (!page.getRecords().isEmpty()){
            request.setAttribute("commentPageResult", blogPageResult);
        }
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/detail";
    }

    /**
     * 友链界面
     * @param request
     * @return java.lang.String
     * @date 2019/9/6 17:26
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        List<BlogLink> favoriteLinks = blogLinkService.list(new QueryWrapper<BlogLink>()
                .lambda().eq(BlogLink::getLinkType, LinkConstants.LINK_TYPE_FRIENDSHIP.getLinkTypeId())
        );
        List<BlogLink> recommendLinks = blogLinkService.list(new QueryWrapper<BlogLink>()
                .lambda().eq(BlogLink::getLinkType, LinkConstants.LINK_TYPE_RECOMMEND.getLinkTypeId())
        );
        List<BlogLink> personalLinks = blogLinkService.list(new QueryWrapper<BlogLink>()
                .lambda().eq(BlogLink::getLinkType, LinkConstants.LINK_TYPE_PRIVATE.getLinkTypeId())
        );
        //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
        request.setAttribute("favoriteLinks", favoriteLinks);
        request.setAttribute("recommendLinks", recommendLinks);
        request.setAttribute("personalLinks", personalLinks);
        request.setAttribute("configurations", blogConfigService.getAllConfigs());
        return "blog/" + theme + "/link";
    }

    /**
     * 提交评论
     * @return com.site.blog.dto.Result
     * @date 2019/9/6 17:40
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request,
                          HttpSession session,
                          @Validated BlogComment blogComment) {
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        boolean flag = blogCommentService.save(blogComment);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

}
