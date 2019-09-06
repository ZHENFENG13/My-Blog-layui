package com.site.blog.constants;

/**
 * @program: itoken
 * @classname: HttpStatusConstants
 * @description: Http状态常量
 * @author: 南街
 * @create: 2019-08-15 09:11
 **/
public enum  HttpStatusConstants {

    OK(200,"成功"),
    BAD_REQUEST(400,"请求参数有误"),
    UNAUTHORIZED(401,"登录失败"),
    INTERNAL_SERVER_ERROR(500,"服务器遇到了一个未曾预料的状况"),
    BAD_GATEWAY(502,"从上游服务器接收到无效的响应");

    private final int status;
    private final String content;

    HttpStatusConstants(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
