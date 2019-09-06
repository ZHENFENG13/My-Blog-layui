package com.site.blog.service.impl;

import com.site.blog.entity.BlogTagRelation;
import com.site.blog.dao.BlogTagRelationMapper;
import com.site.blog.service.BlogTagRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客跟标签的关系表 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-28
 */
@Service
public class BlogTagRelationServiceImpl extends ServiceImpl<BlogTagRelationMapper, BlogTagRelation> implements BlogTagRelationService {

}
