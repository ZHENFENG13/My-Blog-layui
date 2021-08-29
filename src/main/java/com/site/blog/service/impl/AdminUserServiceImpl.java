package com.site.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.site.blog.dao.BlogConfigMapper;
import com.site.blog.entity.AdminUser;
import com.site.blog.dao.AdminUserMapper;
import com.site.blog.entity.BlogConfig;
import com.site.blog.service.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 后台管理员信息表 服务实现类
 * </p>
 *
 * @author: 南街
 * @since 2019-08-25
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMappe;

    /**
     * @Description: 验证密码
     * @Param: [userId, oldPwd]
     * @return: boolean
     * @date: 2019/8/26 13:27
     */
    @Override
    public boolean validatePassword(Integer userId, String oldPwd) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>(
                new AdminUser().setAdminUserId(userId)
                        .setLoginPassword(MD5Utils.MD5Encode(oldPwd, "UTF-8"))
        );
        AdminUser adminUser = adminUserMappe.selectOne(queryWrapper);
        return !StringUtils.isEmpty(adminUser);
    }

    @Transactional
    @Override
    public boolean updateUserInfo(AdminUser adminUser) {
        return SqlHelper.retBool(adminUserMappe.updateById(adminUser));
    }
}
