package com.site.blog.util;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * @program: my-blog
 * @classname: UploadFileUtils
 * @description: 上传文件工具类
 * @author: 朱林
 * @create: 2019-08-24 15:24
 **/
public class UploadFileUtils {

    /**
     * @Description: 获取图片后缀
     * @Param: [file]
     * @return: java.lang.String
     * @date: 2019/8/24 15:27
     */
    public static String getSuffixName(MultipartFile file){
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**
     * @Description: 生成文件名称通用方法
     * @Param: [suffixName] 图片后缀
     * @return: java.lang.String
     * @date: 2019/8/24 15:29 
     */
    public static String getNewFileName(String suffixName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        int random = new Random().nextInt(100);
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(random).append(suffixName);
        return tempName.toString();
    }
}
