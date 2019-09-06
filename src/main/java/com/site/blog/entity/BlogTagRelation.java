package com.site.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 博客跟标签的关系表
 * </p>
 *
 * @author: 南街
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTagRelation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 关系表id
     */
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Long relationId;

    /**
     * 博客id
     */
    @TableField("blog_id")
    private Long blogId;

    /**
     * 标签id
     */
    @TableField("tag_id")
    private Integer tagId;

    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;


}
