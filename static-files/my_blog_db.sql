-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台管理员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_user
-- ----------------------------
INSERT INTO `tb_admin_user` VALUES (1, 'admin', '670b14728ad9902aecba32e22fa4f6bd', '南街', 0);

-- ----------------------------
-- Table structure for tb_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_category`;
CREATE TABLE `tb_blog_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类的图标',
  `category_rank` int(11) NOT NULL DEFAULT 1 COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_category
-- ----------------------------
INSERT INTO `tb_blog_category` VALUES (1, '默认分类', '', 1, 0, '2019-08-30 15:07:02');
INSERT INTO `tb_blog_category` VALUES (20, 'About', '', 9, 0, '2018-11-12 00:28:49');
INSERT INTO `tb_blog_category` VALUES (22, 'Java进阶', '', 22, 0, '2018-11-12 10:42:25');
INSERT INTO `tb_blog_category` VALUES (24, '日常随笔', '', 23, 0, '2018-11-12 10:43:21');
INSERT INTO `tb_blog_category` VALUES (25, 'About2', NULL, 100, 0, '2019-09-02 01:58:53');

-- ----------------------------
-- Table structure for tb_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_comment`;
CREATE TABLE `tb_blog_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `website_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '网址',
  `comment_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论提交时间',
  `commentator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime(0) NULL DEFAULT NULL COMMENT '回复时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_comment
-- ----------------------------
INSERT INTO `tb_blog_comment` VALUES (1, 7, '测试评论', '1320291471@qq.com', '', '感觉还可以', '2019-08-06 20:39:41', '', '谢谢夸奖', '2019-08-06 12:40:10', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (2, 7, '测试评论2', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', 'nihao', '2019-08-30 02:19:50', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (3, 7, '测试评论31', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', '测试回复1', '2019-09-04 00:38:36', 0, 0);
INSERT INTO `tb_blog_comment` VALUES (4, 7, '测试评论32', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', 'okok', '2019-09-04 00:38:56', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (5, 7, '测试评论33', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', 'okok', '2019-09-04 00:38:56', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (6, 7, '测试评论34', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', 'okok', '2019-09-04 00:38:56', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (7, 7, '测试评论35', '1320291471@qq.com', '', '还可以啊', '2019-08-06 20:40:40', '', 'okok', '2019-09-04 00:38:56', 1, 0);
INSERT INTO `tb_blog_comment` VALUES (10, 1, '测试着', '1320291471@qq.com', '', '11111121312412', '2019-09-06 09:45:32', '', '你好啊，谢谢你的评论', '2019-09-06 09:46:26', 0, 0);

-- ----------------------------
-- Table structure for tb_blog_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_config`;
CREATE TABLE `tb_blog_config`  (
  `config_field` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字段名',
  `config_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置名',
  `config_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_field`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_config
-- ----------------------------
INSERT INTO `tb_blog_config` VALUES ('sysAuthor', '开发者', '南街', '2019-08-24 20:33:17', '2019-08-30 03:27:35');
INSERT INTO `tb_blog_config` VALUES ('sysAuthorImg', '开发者头像', 'http://localhost/authorImg/20190906_18162846.jpg', '2019-08-24 20:33:14', '2019-08-24 21:56:23');
INSERT INTO `tb_blog_config` VALUES ('sysCopyRight', '版权所有', 'xuebingsi(xuebingsi) 访问官网', '2019-08-24 20:33:31', '2019-08-24 11:58:06');
INSERT INTO `tb_blog_config` VALUES ('sysEmail', '开发者邮箱', '1320291471@qq.com', '2019-08-24 14:06:48', '2019-08-24 14:06:51');
INSERT INTO `tb_blog_config` VALUES ('sysUpdateTime', '最后修改时间', '2019-08-24 20:33:23', '2019-08-24 20:33:20', '2019-08-24 21:56:23');
INSERT INTO `tb_blog_config` VALUES ('sysUrl', '服务器url', 'localhost:80', '2019-08-24 14:03:23', '2019-08-24 14:03:26');
INSERT INTO `tb_blog_config` VALUES ('sysVersion', '当前版本号', '1.1.0', '2019-08-24 20:33:23', '2019-08-24 11:58:06');
INSERT INTO `tb_blog_config` VALUES ('websiteName', '博客名', 'v1 blog', '2018-11-11 20:33:01', '2018-11-11 22:05:14');

-- ----------------------------
-- Table structure for tb_blog_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_info`;
CREATE TABLE `tb_blog_info`  (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `blog_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标题',
  `blog_sub_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客自定义路径url',
  `blog_preface` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客前言',
  `blog_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客内容',
  `blog_category_id` int(11) NOT NULL COMMENT '博客分类id',
  `blog_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客标签(冗余字段)',
  `blog_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-草稿 1-发布',
  `blog_views` bigint(20) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `enable_comment` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_info
-- ----------------------------
INSERT INTO `tb_blog_info` VALUES (1, '我是南街', 'about', '关于自己的简短介绍', '## About me\n\n我是南街，一名普通的大三学生，反观我的大学生涯，大一玩了一年很嗨，大二才开始认识到自己，开始努力自学，期间经历了很多痛苦，技术一般且经历平平。虽然现实很残酷，但梦想永在，你不努力，谁也给不了你想要的生活。反问自己没有梦想，何必远方？\n\n相信浏览这段话的你也知道，学习是一件极其枯燥而无聊的过程，甚至有时候显得很无助，我也想告诉你，成长就是这样一件残酷的事情，任何成功都不是一蹴而就，需要坚持、需要付出、需要你的毅力，短期可能看不到收获，因为破茧成蝶所耗费的时间不是一天。\n\n## Contact\n\n- 我的邮箱：1320291471@qq.com\n- QQ：1320291471\n- 欢迎加我交流', 24, '日常随笔', '世界上有一个很可爱的人,现在这个人就在看这句话', 1, 270, 1, 0, '2019-09-06 10:01:50', '2019-09-06 10:01:50');
INSERT INTO `tb_blog_info` VALUES (7, 'SSM整合Redis做缓存', 'redis', '测试介绍', '前面我已经写过一篇博客简单介绍Redis的Java客户端和Redis的一整合操作，这里我们深入将Redis配置SpringCache注解来实现缓存，这样效率更高更快捷\n\n话不多说，直接晒代码\n\n一、redis配置文件\n```java\nredis.host=192.168.3.143\nredis.port=6379\nredis.dbIndex=1\nredis.expiration=3000\nredis.maxIdle=300\nredis.maxTotal=600\nredis.maxWaitMillis=1000\nredis.testOnBorrow=true\nredis.password=有密码就配，没有就不用\n```\n\n二、Spring-redis.xml文件\n\n\n```xml\n    <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n    <beans xmlns=\"http://www.springframework.org/schema/beans\"\n           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n           xmlns:context=\"http://www.springframework.org/schema/context\"\n           xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\">\n    \n        <context:property-placeholder location=\"classpath:redis.properties\" ignore-unresolvable=\"true\" order=\"2\"></context:property-placeholder>\n        <!-- 扫描RedisCacheConfig  -->\n        <context:component-scan base-package=\"com.zl.config\" ></context:component-scan>\n    \n        <bean id=\"jedisPoolConfig\" class=\"redis.clients.jedis.JedisPoolConfig\">\n            <property name=\"maxIdle\" value=\"${redis.maxIdle}\"></property>\n            <property name=\"maxTotal\" value=\"${redis.maxTotal}\"></property>\n            <property name=\"maxWaitMillis\" value=\"${redis.maxWaitMillis}\"></property>\n            <property name=\"testOnBorrow\" value=\"${redis.testOnBorrow}\"></property>\n        </bean>\n    \n        <bean id=\"jedisConnectionFactory\" class=\"org.springframework.data.redis.connection.jedis.JedisConnectionFactory\">\n            <property name=\"hostName\" value=\"${redis.host}\"></property>\n            <property name=\"port\" value=\"${redis.port}\"></property>\n            <property name=\"database\" value=\"${redis.dbIndex}\"></property>\n            <property name=\"password\" value=\"${redis.password}\"></property>\n            <property name=\"poolConfig\" ref=\"jedisPoolConfig\"></property>\n        </bean>\n    \n        <bean id=\"redisTemplate\" class=\"org.springframework.data.redis.core.RedisTemplate\">\n            <property name=\"connectionFactory\" ref=\"jedisConnectionFactory\"></property>\n            <!--   key进行序列化配置，默认JDK改为String     -->\n            <property name=\"keySerializer\">\n                <bean class=\"org.springframework.data.redis.serializer.StringRedisSerializer\"></bean>\n            </property>\n            <property name=\"valueSerializer\">\n                <bean class=\"org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer\"></bean>\n            </property>\n            <property name=\"hashKeySerializer\">\n                <bean class=\"org.springframework.data.redis.serializer.StringRedisSerializer\"></bean>\n            </property>\n        </bean>\n    \n        <!-- 配置RedisCacheManager -->\n        <bean id=\"redisCacheManager\" class=\"org.springframework.data.redis.cache.RedisCacheManager\">\n            <constructor-arg name=\"redisOperations\" ref=\"redisTemplate\"></constructor-arg>\n            <!-- 配置过期时间-->\n            <property name=\"defaultExpiration\" value=\"3000\"></property>\n        </bean>\n    \n        <!-- 配置RedisCacheConfig -->\n        <bean id=\"redisCacheConfig\" class=\"com.zl.config.RedisCacheConfig\">\n            <constructor-arg ref=\"jedisConnectionFactory\"></constructor-arg>\n            <constructor-arg ref=\"redisTemplate\"></constructor-arg>\n            <constructor-arg ref=\"redisCacheManager\"></constructor-arg>\n        </bean>\n    </beans>\n```\n\n这里要注意valueSerializer的序列化方式，建议使用GenericJackson2JsonRedisSerializer，因为使用StringRedisSerializer在存入对象时会报类型转换异常，且使用GenericJackson2JsonRedisSerializer在Redis可视化工具RedisDesktopManager也可以看到不是乱码而是Json格式。效果如下：\n\n\n\n三、RedisCacheConfig配置类\n```java\npackage com.zl.config;\n\nimport org.springframework.cache.annotation.CachingConfigurerSupport;\nimport org.springframework.cache.annotation.EnableCaching;\nimport org.springframework.cache.interceptor.KeyGenerator;\nimport org.springframework.context.annotation.Bean;\nimport org.springframework.context.annotation.Configuration;\nimport org.springframework.data.redis.cache.RedisCacheManager;\nimport org.springframework.data.redis.connection.jedis.JedisConnectionFactory;\nimport org.springframework.data.redis.core.RedisTemplate;\n\nimport java.lang.reflect.Method;\n\n/**\n * @program: FruitSales\n * @classname: RedisCacheConfig\n * @description: Redis缓存配置\n * @create: 2019-07-01 18:56\n **/\n@Configuration\n@EnableCaching\npublic class RedisCacheConfig extends CachingConfigurerSupport {\n\n    private volatile JedisConnectionFactory jedisConnectionFactory;\n    private volatile RedisTemplate<String, String> redisTemplate;\n    private volatile RedisCacheManager redisCacheManager;\n\n    public RedisCacheConfig() {\n        super();\n    }\n\n    /**\n     * 带参数的构造方法 初始化所有的成员变量\n     *\n     * @param jedisConnectionFactory\n     * @param redisTemplate\n     * @param redisCacheManager\n     */\n    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,\n                            RedisCacheManager redisCacheManager) {\n        this.jedisConnectionFactory = jedisConnectionFactory;\n        this.redisTemplate = redisTemplate;\n        this.redisCacheManager = redisCacheManager;\n    }\n\n    public JedisConnectionFactory getJedisConnecionFactory() {\n        return jedisConnectionFactory;\n    }\n\n    public RedisTemplate<String, String> getRedisTemplate() {\n        return redisTemplate;\n    }\n\n    public RedisCacheManager getRedisCacheManager() {\n        return redisCacheManager;\n    }\n\n    @Bean\n    public KeyGenerator keyGenerator() {\n        return new KeyGenerator() {\n            @Override\n            public Object generate(Object target, Method method,\n                                   Object... params) {\n                //规定  本类名+方法名+参数名 为key\n                StringBuilder sb = new StringBuilder();\n                sb.append(target.getClass().getName()+\"_\");\n                sb.append(method.getName()+\"_\");\n                for (Object obj : params) {\n                    sb.append(obj.toString()+\",\");\n                }\n                return sb.toString();\n            }\n        };\n    }\n}\n\n```\n四、接下来只要在需要缓存的方式上加注释啦\n如这样:在service方法上加@Cacheable注解\n\n\n\n常用注解：\n\n@Cacheable、@CachePut、@CacheEvict 注释介绍\n通过上面的例子，我们可以看到 spring cache 主要使用两个注释标签，即 @Cacheable、@CachePut 和 @CacheEvict，我们总结一下其作用和配置方法。', 22, 'Java进阶', 'NoSQL', 1, 24, 1, 0, '2019-09-07 03:25:28', '2019-09-07 03:25:28');

-- ----------------------------
-- Table structure for tb_blog_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_link`;
CREATE TABLE `tb_blog_link`  (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `link_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站链接',
  `link_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站描述',
  `link_rank` int(11) NOT NULL DEFAULT 0 COMMENT '用于列表排序',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友情链接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_link
-- ----------------------------
INSERT INTO `tb_blog_link` VALUES (1, 0, '测试1', 'xxxxxx', '这是测试', 0, 0, '2019-09-07 02:21:03');
INSERT INTO `tb_blog_link` VALUES (2, 0, '测试2', 'xxxxx', '测试哦', 0, 0, '2019-09-02 21:24:44');
INSERT INTO `tb_blog_link` VALUES (4, 1, '测试', '111111', '222222', 123, 0, '2019-09-03 14:47:21');

-- ----------------------------
-- Table structure for tb_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_tag`;
CREATE TABLE `tb_blog_tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_tag
-- ----------------------------
INSERT INTO `tb_blog_tag` VALUES (1, '默认标题', 0, '2019-09-01 11:19:47');
INSERT INTO `tb_blog_tag` VALUES (57, '世界上有一个很可爱的人', 0, '2018-11-12 00:31:15');
INSERT INTO `tb_blog_tag` VALUES (58, '现在这个人就在看这句话', 0, '2018-11-12 00:31:15');
INSERT INTO `tb_blog_tag` VALUES (139, 'NoSQL', 0, '2019-08-06 21:23:38');

-- ----------------------------
-- Table structure for tb_blog_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_tag_relation`;
CREATE TABLE `tb_blog_tag_relation`  (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 331 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客跟标签的关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog_tag_relation
-- ----------------------------
INSERT INTO `tb_blog_tag_relation` VALUES (325, 1, 57, '2019-09-06 18:01:50');
INSERT INTO `tb_blog_tag_relation` VALUES (326, 1, 58, '2019-09-06 18:01:50');
INSERT INTO `tb_blog_tag_relation` VALUES (330, 7, 139, '2019-09-07 11:25:28');

SET FOREIGN_KEY_CHECKS = 1;
