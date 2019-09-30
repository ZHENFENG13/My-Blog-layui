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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * <p>
 * 评论信息表
 * </p>
 *
 * @author: 南街
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogComment implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 关联的blog主键
     */
    @TableField("blog_id")
    @NotNull(message = "非法请求")
    @Min(value = 0,message = "非法请求")
    private Long blogId;

    /**
     * 评论者名称
     */
    @TableField("commentator")
    @NotBlank(message = "请输入称呼")
    private String commentator;

    /**
     * 评论人的邮箱
     */
    @TableField("email")
    @Email
    private String email;

    /**
     * 网址
     */
    @TableField("website_url")
    private String websiteUrl;

    /**
     * 评论内容
     */
    @TableField("comment_body")
    @NotBlank(message = "请输入评论内容")
    @Length(min = 1,max = 200,message = "评论内容过长或过短")
    private String commentBody;

    /**
     * 评论提交时间
     */
    @TableField("comment_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date commentCreateTime;

    /**
     * 评论时的ip地址
     */
    @TableField("commentator_ip")
    private String commentatorIp;

    /**
     * 回复内容
     */
    @TableField("reply_body")
    private String replyBody;

    /**
     * 回复时间
     */
    @TableField("reply_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyCreateTime;

    /**
     * 是否审核通过 0-未审核 1-审核通过
     */
    @TableField("comment_status")
    private Integer commentStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}
