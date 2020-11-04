package com.site.blog.constants;

/**
 * @program: itoken
 * @classname: HttpStatusConstants
 * @description: Http状态常量
 * @author: 南街
 * @create: 2019-08-15 09:11
 **/
public enum CommentStatusEnum {

    /**
     * 允许评论
     */
    ALLOW(1,"允许评论"),
    /**
     * 不允许评论
     */
    NO_ALLOW(0,"不允许评论");

    private final int status;
    private final String note;

    CommentStatusEnum(int status, String note) {
        this.status = status;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }
}
