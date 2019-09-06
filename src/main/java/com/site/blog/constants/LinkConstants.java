package com.site.blog.constants;

/**
 * @program: my-blog
 * @classname: LinkConstants
 * @description:
 * @author: 南街
 * @create: 2019-09-02 21:18
 **/
public enum LinkConstants {
    LINK_TYPE_FRIENDSHIP(0,"友链"),
    LINK_TYPE_RECOMMEND(1,"推荐"),
    LINK_TYPE_PRIVATE(2,"个人网站");


    private final Integer linkTypeId;
    private final String linkTypeName;

    LinkConstants(Integer linkTypeId, String linkTypeName) {
        this.linkTypeId = linkTypeId;
        this.linkTypeName = linkTypeName;
    }

    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public String getLinkTypeName() {
        return linkTypeName;
    }
}
