package com.site.blog.constants;

/**
 * @program: itoken
 * @classname: HttpStatusConstants
 * @description: Http状态常量
 * @author: 南街
 * @create: 2019-08-15 09:11
 **/
public enum BlogStatusEnum {

    /**
     * 已发布
     */
    RELEASE(1, "已发布"),
    /**
     * 未发布
     */
    NO_RELEASE(0, "未发布");

    private final int status;
    private final String note;

    BlogStatusEnum(int status, String note) {
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
