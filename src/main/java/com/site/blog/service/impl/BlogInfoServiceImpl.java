package com.site.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.site.blog.constants.BlogStatusConstants;
import com.site.blog.controller.vo.SimpleBlogListVO;
import com.site.blog.dao.BlogCommentMapper;
import com.site.blog.dao.BlogTagRelationMapper;
import com.site.blog.entity.BlogComment;
import com.site.blog.entity.BlogInfo;
import com.site.blog.dao.BlogInfoMapper;
import com.site.blog.entity.BlogTagRelation;
import com.site.blog.service.BlogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客信息表 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-27
 */
@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements BlogInfoService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public List<SimpleBlogListVO> getNewBlog() {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        Page<BlogInfo> page = new Page<>(1,5);
        blogInfoMapper.selectPage(page,new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus, BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                .orderByDesc(BlogInfo::getCreateTime));
        for (BlogInfo blogInfo : page.getRecords()){
            SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
            BeanUtils.copyProperties(blogInfo, simpleBlogListVO);
            simpleBlogListVOS.add(simpleBlogListVO);
        }
        return simpleBlogListVOS;
    }

    @Override
    public List<SimpleBlogListVO> getHotBlog() {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        Page<BlogInfo> page = new Page<>(1,5);
        blogInfoMapper.selectPage(page,new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus, BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO)
                .orderByDesc(BlogInfo::getBlogViews));
        for (BlogInfo blogInfo : page.getRecords()){
            SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
            BeanUtils.copyProperties(blogInfo, simpleBlogListVO);
            simpleBlogListVOS.add(simpleBlogListVO);
        }
        return simpleBlogListVOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean clearBlogInfo(Long blogId) {
        if (SqlHelper.retBool(blogInfoMapper.deleteById(blogId))){
            QueryWrapper<BlogTagRelation> tagRelationWrapper = new QueryWrapper<>();
            tagRelationWrapper.lambda().eq(BlogTagRelation::getBlogId,blogId);
            blogTagRelationMapper.delete(tagRelationWrapper);
            QueryWrapper<BlogComment> commentWrapper = new QueryWrapper<>();
            commentWrapper.lambda().eq(BlogComment::getBlogId,blogId);
            blogCommentMapper.delete(commentWrapper);
            return true;
        }
        return false;
    }
}
