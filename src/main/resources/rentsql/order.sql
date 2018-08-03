
-- ----------------------------
-- Table structure for 订单数据库sql
-- 租赁商订单表
-- ----------------------------
CREATE TABLE `order_rent_merchant` (
  `order_id` bigint NOT NULL COMMENT '用户ID',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `merchant_name` varchar(256) DEFAULT NULL COMMENT '商家名字',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `gender` tinyint(1) DEFAULT 1 COMMENT '性别(1-男,2-女)',
  `phone_number` varchar(512) DEFAULT NULL COMMENT '电话号码',
  `email_address` varchar(512) DEFAULT NULL COMMENT '邮件地址',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0-正常,1-冻结)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本资料信息表';


-- ----------------------------
-- Table structure for user_identity
-- 用户认证表
-- ----------------------------
CREATE TABLE `user_identity` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `real_name` varchar(50) NOT NULL COMMENT '用户实名',
  `nationality` tinyint(1) NOT NULL COMMENT '用户国籍：1、中国，2、马来西亚，3、新加坡',
  `card_type` tinyint(1) NOT NULL COMMENT '证件类型：1、中国身份证，2、外国证件',
  `card_number` varchar(50) NOT NULL COMMENT '证件号码',
  `front_photo` varchar(512) DEFAULT NULL COMMENT '正面照，存的是一个url地址',
  `back_photo` varchar(512) DEFAULT NULL COMMENT '反面照，存的是一个url地址',
  `hand_photo` varchar(512) DEFAULT NULL COMMENT '手持证件照，存的是一个url地址',
  `user_video` varchar(512) DEFAULT NULL COMMENT '存的是一个url，用户认证时候的一段录音',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户身份信息表';

-- ----------------------------
-- Table structure for user_pwd
-- 用户的密码表
-- ----------------------------
CREATE TABLE `user_pwd` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `login_pwd` varchar(50) NOT NULL COMMENT '登陆密码',
  `pay_pwd` varchar(50) NOT NULL COMMENT '支付密码',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';


-- ----------------------------
-- Table structure for user_app_token
-- 用户的APPToken表
-- ----------------------------
CREATE TABLE `user_app_token` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `token` varchar(50) DEFAULT NULL COMMENT 'app用户Token',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app token信息表';

-- ----------------------------
-- Table structure for user_web_token
-- 用户web端的Token
-- ----------------------------
CREATE TABLE `user_web_token` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `token` varchar(50) DEFAULT NULL COMMENT 'web用户Token',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='web token信息表';

-- ----------------------------
-- Table structure for user_login_history
-- 用户最近的登陆历史
-- ----------------------------
CREATE TABLE `user_login_history` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `login_type` tinyint(1) NOT NULL COMMENT '登陆方式：1、web，2、app',
  `ip` varchar(50) NOT NULL COMMENT 'ip地址',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的登陆信息表';

-- ----------------------------
-- Table structure for user_changeinfo_apply
-- 用户个人信息的修改申请
-- ----------------------------
CREATE TABLE `user_changeinfo_apply` (
  `uid` bigint NOT NULL COMMENT '用户id',
  `change_type` tinyint(1) NOT NULL COMMENT '修改的内容：1、手机，2、邮箱',
  `oldinfo` varchar(50) NOT NULL COMMENT '旧的信息',
  `newinfo` varchar(50) NOT NULL COMMENT '新的信息',
  `phone_number` varchar(50) NOT NULL COMMENT '常用的联系电话',
  `apply_statue` tinyint(1) NOT NULL COMMENT '修改的内容：1、待审核，2、通过，3、拒绝',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户修改手机和邮箱的申请表';




