package com.site.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.blog.constants.*;
import com.site.blog.dto.Result;
import com.site.blog.entity.*;
import com.site.blog.service.*;
import com.site.blog.util.MD5Utils;
import com.site.blog.util.ResultGenerator;
import com.site.blog.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @qq交流群 796794009
 * @qq 1320291471
 * @Description: 管理员controller
 * @date: 2019/8/24 9:43
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private BlogInfoService blogInfoService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogConfigService blogConfigService;
    @Autowired
    private BlogLinkService blogLinkService;


    /**
     * @Description: 跳转登录界面
     * @Param: []
     * @return: java.lang.String
     * @date: 2019/8/23 19:50
     */
    @GetMapping(value = "/v1/login")
    public String login() {
        return "adminLayui/login";
    }

    /**
     * @Description: 返回welcome界面
     * @Param: []
     * @return: java.lang.String
     * @date: 2019/8/24 9:42
     */
    @GetMapping("/v1/welcome")
    public String welcome(){
        return "adminLayui/welcome";
    }

    /**
     * @Description: 注销登录
     * @Param: [session]
     * @return: java.lang.String
     * @date: 2019/8/24 15:00
     */
    @GetMapping("/v1/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "adminLayui/login";
    }

    /**
     * @Description: 返回个人信息界面
     * @Param: []
     * @return: java.lang.String
     * @date: 2019/8/24 15:02
     */
    @GetMapping("/v1/userInfo")
    public String gotoUserInfo(){
        return "adminLayui/userInfo-edit";
    }

    /**
     * @Description: 登录验证
     * @Param: [username, password, session]
     * @return: com.zhulin.blog.util.MessageBean
     * @date: 2019/8/23 19:50
     */
    @ResponseBody
    @PostMapping(value = "/v1/login")
    public Result<String> login(String username, String password,
                        HttpSession session) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<AdminUser>(
                new AdminUser().setLoginUserName(username)
                        .setLoginPassword(MD5Utils.MD5Encode(password,"UTF-8"))
        );
        AdminUser adminUser = adminUserService.getOne(queryWrapper);
        if (adminUser != null) {
            session.setAttribute(SessionConstants.LOGIN_USER, adminUser.getNickName());
            session.setAttribute(SessionConstants.LOGIN_USER_ID, adminUser.getAdminUserId());
            session.setAttribute(SessionConstants.LOGIN_USER_NAME, adminUser.getLoginUserName());
            session.setAttribute(SessionConstants.AUTHOR_IMG, blogConfigService.getById(
                    SysConfigConstants.SYS_AUTHOR_IMG.getConfigField()));
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK,"/admin/v1/index");
        } else {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.UNAUTHORIZED);
        }
    }

    /**
     * @Description: 验证密码是否正确
     * @Param: [oldPwd, session]
     * @return: com.zhulin.blog.dto.Result
     * @date: 2019/8/25 9:15
     */
    @ResponseBody
    @GetMapping("/v1/password")
    public Result<String> validatePassword(String oldPwd,HttpSession session){
        Integer userId = (Integer) session.getAttribute(SessionConstants.LOGIN_USER_ID);
        boolean flag = adminUserService.validatePassword(userId,oldPwd);
        if (flag){
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
    }

    /**
     * @Description: 返回首页相关数据
     * @Param: [session]
     * @return: java.lang.String
     * @date: 2019/8/24 9:41
     */
    @GetMapping("/v1/index")
    public String index(HttpSession session) {
        session.setAttribute("categoryCount", blogCategoryService.count(
                new QueryWrapper<BlogCategory>().lambda().eq(BlogCategory::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("blogCount", blogInfoService.count(
                new QueryWrapper<BlogInfo>().lambda().eq(BlogInfo::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("linkCount", blogLinkService.count(
                new QueryWrapper<BlogLink>().lambda().eq(BlogLink::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("tagCount", blogTagService.count(
                new QueryWrapper<BlogTag>().lambda().eq(BlogTag::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("commentCount", blogCommentService.count(
                new QueryWrapper<BlogComment>().lambda().eq(BlogComment::getIsDeleted,
                        BlogStatusConstants.ZERO)
        ));
        session.setAttribute("sysList",blogConfigService.list());
        return "adminLayui/index";
    }

    /**
     * @Description: 修改用户信息,成功之后清空session并跳转登录页
     * @Param: [session, newPwd, nickName]
     * @return: com.zhulin.blog.dto.Result
     * @date: 2019/8/25 11:06
     */
    @ResponseBody
    @PostMapping("/v1/userInfo")
    public Result<String> userInfoUpdate(HttpSession session,String userName, String newPwd,
                                 String nickName) {
        if (StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(nickName)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        Integer loginUserId = (int) session.getAttribute(SessionConstants.LOGIN_USER_ID);
        AdminUser adminUser = new AdminUser()
                .setAdminUserId(loginUserId)
                .setLoginUserName(userName)
                .setNickName(nickName)
                .setLoginPassword(MD5Utils.MD5Encode(newPwd, "UTF-8"));
        if (adminUserService.updateUserInfo(adminUser)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK,"/admin/v1/logout");
        } else {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @GetMapping("/v1/reload")
    public boolean reload(HttpSession session){
        Integer userId = (Integer) session.getAttribute(SessionConstants.LOGIN_USER_ID);
        return userId != null && userId != 0;
    }

    /**
     * @Description: 用户头像上传
     * @Param: [httpServletRequest, file]
     * @return: com.zhulin.blog.util.Result
     * @date: 2019/8/24 15:15
     */
    @PostMapping({"/upload/authorImg"})
    @ResponseBody
    public Result<String> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String suffixName = UploadFileUtils.getSuffixName(file);
        //生成文件名称通用方法
        String newFileName = UploadFileUtils.getNewFileName(suffixName);
        File fileDirectory = new File(UploadConstants.UPLOAD_AUTHOR_IMG);
        //创建文件
        File destFile = new File(UploadConstants.UPLOAD_AUTHOR_IMG + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdirs()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            String sysAuthorImg = UploadConstants.SQL_AUTHOR_IMG + newFileName;
            BlogConfig blogConfig = new BlogConfig()
                    .setConfigField(SysConfigConstants.SYS_AUTHOR_IMG.getConfigField())
                    .setConfigValue(sysAuthorImg);
            blogConfigService.updateById(blogConfig);
            return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
