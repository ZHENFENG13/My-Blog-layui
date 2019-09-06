package com.site.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.constants.BlogStatusConstants;
import com.site.blog.constants.SysConfigConstants;
import com.site.blog.dao.BlogTagRelationMapper;
import com.site.blog.entity.BlogTagCount;
import com.site.blog.entity.BlogTagRelation;
import com.site.blog.service.BlogTagService;
import com.site.blog.entity.BlogTag;
import com.site.blog.dao.BlogTagMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-28
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<BlogTag>();
        queryWrapper.lambda()
                .eq(BlogTag::getIsDeleted, BlogStatusConstants.ZERO);
        List<BlogTag> list = blogTagMapper.selectList(queryWrapper);
        List<BlogTagCount> blogTagCounts = list.stream()
                .map(blogTag -> new BlogTagCount()
                        .setTagId(blogTag.getTagId())
                        .setTagName(blogTag.getTagName())
                        .setTagCount(
                                blogTagRelationMapper.selectCount(new QueryWrapper<BlogTagRelation>()
                                        .lambda().eq(BlogTagRelation::getTagId,blogTag.getTagId()))
                        )).collect(Collectors.toList());
        return blogTagCounts;
    }
}
