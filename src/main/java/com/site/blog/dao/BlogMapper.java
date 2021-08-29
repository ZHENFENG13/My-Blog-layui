package com.site.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.entity.BlogInfo;
import com.site.blog.util.PageQueryUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper extends BaseMapper<BlogInfo>{

    List<BlogInfo> findBlogList(PageQueryUtils pageUtil);

    List<BlogInfo> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

    int getTotalBlogs(PageQueryUtils pageUtil);

    int removeBatch(Integer[] ids);

    List<BlogInfo> getBlogsPageByTagId(PageQueryUtils pageUtil);

    int getTotalBlogsByTagId(PageQueryUtils pageUtil);

    BlogInfo selectBySubUrl(String subUrl);

    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

}