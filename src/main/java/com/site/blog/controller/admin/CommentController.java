package com.site.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.blog.constants.HttpStatusConstants;
import com.site.blog.dto.AjaxPutPage;
import com.site.blog.dto.AjaxResultPage;
import com.site.blog.dto.Result;
import com.site.blog.entity.BlogComment;
import com.site.blog.service.BlogCommentService;
import com.site.blog.util.DateUtils;
import com.site.blog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @qq交流群 796794009
 * @qq 1320291471
 * @Description: 评论标签
 * @date: 2019/8/6 17:24
 */
@Controller
@RequestMapping("/admin")
public class CommentController {

    @Resource
    private BlogCommentService blogCommentService;

    @GetMapping("/v1/comment")
    public String gotoComment(){
        return "adminLayui/comment-list";
    }

    @ResponseBody
    @GetMapping("/v1/comment/paging")
    public AjaxResultPage<BlogComment> getLinkList(AjaxPutPage<BlogComment> ajaxPutPage, BlogComment condition){
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>(condition);
        Page<BlogComment> page = ajaxPutPage.putPageToPage();
        blogCommentService.page(page,queryWrapper);
        AjaxResultPage<BlogComment> result = new AjaxResultPage<>();
        result.setData(page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }

    @ResponseBody
    @PostMapping(value = {"/v1/comment/isDel","/v1/comment/commentStatus"})
    public Result updateLinkStatus(BlogComment blogComment){
        boolean flag = blogCommentService.updateById(blogComment);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @PostMapping("/v1/comment/edit")
    public Result updateBlogConfig(BlogComment blogComment){
        blogComment.setReplyCreateTime(DateUtils.getLocalCurrentDate());
        boolean flag = blogCommentService.updateById(blogComment);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }else{
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
    }

}
