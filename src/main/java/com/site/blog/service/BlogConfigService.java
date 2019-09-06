package com.site.blog.service;

import com.site.blog.entity.BlogConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-30
 */
public interface BlogConfigService extends IService<BlogConfig> {

    Map<String, String> getAllConfigs();

}
