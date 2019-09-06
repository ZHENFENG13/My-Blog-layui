package com.site.blog.service.impl;

import com.site.blog.entity.BlogCategory;
import com.site.blog.dao.BlogCategoryMapper;
import com.site.blog.service.BlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-30
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

}
