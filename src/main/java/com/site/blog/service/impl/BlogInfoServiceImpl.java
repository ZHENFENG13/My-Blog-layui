package com.site.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.site.blog.constants.BlogStatusConstants;
import com.site.blog.controller.vo.SimpleBlogListVO;
import com.site.blog.entity.BlogInfo;
import com.site.blog.dao.BlogInfoMapper;
import com.site.blog.service.BlogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SimpleBlogListVO> getNewBlog() {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        Page<BlogInfo> page = new Page<>(1,5);
        blogInfoMapper.selectPage(page,new QueryWrapper<BlogInfo>()
                .lambda()
                .eq(BlogInfo::getBlogStatus, BlogStatusConstants.ONE)
                .eq(BlogInfo::getIsDeleted,BlogStatusConstants.ZERO));
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
}
