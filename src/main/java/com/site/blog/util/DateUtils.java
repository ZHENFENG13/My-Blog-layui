package com.site.blog.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: my-blog
 * @classname: DateUtils
 * @description:
 * @author: 朱林
 * @create: 2019-08-28 13:02
 **/
public class DateUtils {

    /**
     * 获得本地当前时间
     * @param
     * @return java.sql.Timestamp
     * @date 2019/8/28 13:03
     */
    public static Timestamp getLocalCurrentDate(){
        return new Timestamp(new Date().getTime());
    }
}
