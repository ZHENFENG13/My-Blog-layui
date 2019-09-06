package com.site.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客信息表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogInfo implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 博客表主键id
     */
    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;

    /**
     * 博客标题
     */
    @TableField("blog_title")
    private String blogTitle;

    /**
     * 博客自定义路径url
     */
    @TableField("blog_sub_url")
    private String blogSubUrl;

    /**
     * 博客前言
     */
    @TableField("blog_preface")
    private String blogPreface;

    /**
     * 博客内容
     */
    @TableField("blog_content")
    private String blogContent;

    /**
     * 博客分类id
     */
    @TableField("blog_category_id")
    private Integer blogCategoryId;

    /**
     * 博客分类(冗余字段)
     */
    @TableField("blog_category_name")
    private String blogCategoryName;

    /**
     * 博客标签(冗余字段)
     */
    @TableField("blog_tags")
    private String blogTags;

    /**
     * 0-草稿 1-发布
     */
    @TableField("blog_status")
    private Integer blogStatus;

    /**
     * 阅读量
     */
    @TableField("blog_views")
    private Long blogViews;

    /**
     * 0-允许评论 1-不允许评论
     */
    @TableField("enable_comment")
    private Integer enableComment;

    /**
     * 是否删除 0=否 1=是
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    @TableField("update_time")
    private Date updateTime;


}
