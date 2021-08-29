package com.site.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.constants.DeleteStatusEnum;
import com.site.blog.constants.SysConfigConstants;
import com.site.blog.dao.BlogInfoMapper;
import com.site.blog.dao.BlogTagMapper;
import com.site.blog.entity.BlogInfo;
import com.site.blog.entity.BlogTag;
import com.site.blog.entity.BlogTagCount;
import com.site.blog.entity.BlogTagRelation;
import com.site.blog.service.BlogInfoService;
import com.site.blog.service.BlogTagRelationService;
import com.site.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BlogTagRelationService blogTagRelationService;

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Autowired
    private BlogInfoService blogInfoService;

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(BlogTag::getIsDeleted, DeleteStatusEnum.NO_DELETED.getStatus());
        List<BlogTag> list = baseMapper.selectList(queryWrapper);
        return list.stream()
                .map(blogTag -> new BlogTagCount()
                        .setTagId(blogTag.getTagId())
                        .setTagName(blogTag.getTagName())
                        .setTagCount(
                                blogTagRelationService.count(new QueryWrapper<BlogTagRelation>()
                                        .lambda()
                                        .eq(BlogTagRelation::getTagId,blogTag.getTagId()))
                        ))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean clearTag(Integer tagId) {
        LambdaQueryWrapper<BlogTagRelation> queryWrapper = Wrappers.<BlogTagRelation>lambdaQuery()
                .eq(BlogTagRelation::getTagId,tagId);
        List<BlogTagRelation> tagRelationList = blogTagRelationService.list(queryWrapper);
        // 批量更新的BlogInfo信息
        List<BlogInfo> infoList = tagRelationList.stream()
                .map(tagRelation -> new BlogInfo()
                        .setBlogId(tagRelation.getBlogId())
                        .setBlogTags(SysConfigConstants.DEFAULT_TAG.getConfigName())).collect(Collectors.toList());
        List<Long> blogIds = infoList.stream().map(BlogInfo::getBlogId).collect(Collectors.toList());
        // 批量更新的tagRelation信息
        List<BlogTagRelation> tagRelations = tagRelationList.stream()
                .map(tagRelation -> new BlogTagRelation()
                        .setBlogId(tagRelation.getBlogId())
                        .setTagId(Integer.valueOf(SysConfigConstants.DEFAULT_CATEGORY.getConfigField())))
                .collect(Collectors.toList());
        blogInfoService.updateBatchById(infoList);
        blogTagRelationService.remove(new QueryWrapper<BlogTagRelation>()
                .lambda()
                .in(BlogTagRelation::getBlogId,blogIds));
        blogTagRelationService.saveBatch(tagRelations);
        return retBool(baseMapper.deleteById(tagId));
    }
}
