package com.site.blog.constants;

/**
 * @program: my-blog
 * @classname: SysConfigContants
 * @description: blog配置项枚举
 * @author: 南街
 * @create: 2019-08-24 13:57
 **/
public enum SysConfigConstants {
    SYS_VERSION("sysVersion","当前版本号"),
    SYS_URL("sysUrl","服务器url"),
    SYS_AUTHOR("sysAuthor","开发者"),
    SYS_AUTHOR_IMG("sysAuthorImg","开发者头像"),
    SYS_EMAIL("sysEmail","开发者邮箱"),
    SYS_COPY_RIGHT("sysCopyRight","版权所有"),
    SYS_UPDATE_TIME("sysUpdateTime","最后修改时间"),
    DEFAULT_CATEGORY("1","默认分类"),
    DEFAULT_TAG("1","默认标题");

    private final String configField;
    private final String configName;

    SysConfigConstants(String configField, String configName) {
        this.configField = configField;
        this.configName = configName;
    }

    public String getConfigField() {
        return configField;
    }

    public String getConfigName() {
        return configName;
    }
}
