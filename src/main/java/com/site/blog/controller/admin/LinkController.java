package com.site.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.blog.constants.HttpStatusConstants;
import com.site.blog.constants.LinkConstants;
import com.site.blog.dto.AjaxPutPage;
import com.site.blog.dto.AjaxResultPage;
import com.site.blog.dto.Result;
import com.site.blog.entity.BlogLink;
import com.site.blog.service.BlogLinkService;
import com.site.blog.util.DateUtils;
import com.site.blog.util.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 友链Controller
 * @date: 2019/8/6 17:23
 */
@Controller
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private BlogLinkService blogLinkService;

    @GetMapping("/v1/linkType")
    public String gotoLink(){
        return "adminLayui/link-list";
    }

    @ResponseBody
    @GetMapping("/v1/linkType/list")
    public Result linkTypeList(){
        List<BlogLink> links = new ArrayList<>();
        links.add(new BlogLink().setLinkType(LinkConstants.LINK_TYPE_FRIENDSHIP.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_FRIENDSHIP.getLinkTypeName()));
        links.add(new BlogLink().setLinkType(LinkConstants.LINK_TYPE_RECOMMEND.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_RECOMMEND.getLinkTypeName()));
        links.add(new BlogLink().setLinkType(LinkConstants.LINK_TYPE_PRIVATE.getLinkTypeId())
                .setLinkName(LinkConstants.LINK_TYPE_PRIVATE.getLinkTypeName()));
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK,links);
    }

    @ResponseBody
    @GetMapping("/v1/link/paging")
    public AjaxResultPage<BlogLink> getLinkList(AjaxPutPage<BlogLink> ajaxPutPage, BlogLink condition){
        QueryWrapper<BlogLink> queryWrapper = new QueryWrapper<>(condition);
        queryWrapper.lambda()
                .orderByAsc(BlogLink::getLinkRank);
        Page<BlogLink> page = ajaxPutPage.putPageToPage();
        blogLinkService.page(page,queryWrapper);
        AjaxResultPage<BlogLink> result = new AjaxResultPage<>();
        result.setData(page.getRecords());
        result.setCount(page.getTotal());
        return result;
    }

    @ResponseBody
    @PostMapping("/v1/link/isDel")
    public Result updateLinkStatus(BlogLink blogLink){
        boolean flag = blogLinkService.updateById(blogLink);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @PostMapping("/v1/link/clear")
    public Result clearLink(Integer linkId){
        boolean flag = blogLinkService.removeById(linkId);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/v1/link/edit")
    public String editLink(Integer linkId, Model model){
        if (linkId != null){
            BlogLink blogLink = blogLinkService.getById(linkId);
            model.addAttribute("blogLink",blogLink);
        }
        return "adminLayui/link-edit";
    }

    @ResponseBody
    @PostMapping("/v1/link/edit")
    public Result updateAndSaveLink(BlogLink blogLink){
        blogLink.setCreateTime(DateUtils.getLocalCurrentDate());
        boolean flag;
        if (blogLink.getLinkId() != null){
            flag = blogLinkService.updateById(blogLink);
        }else{
            flag = blogLinkService.save(blogLink);
        }
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }
}
