
-- ----------------------------
-- Table structure for 订单数据库sql
-- 租赁商订单表
-- ----------------------------
CREATE TABLE `user_base_info` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `name` varchar(256) DEFAULT NULL COMMENT '商家名字',
  `avatar` varchar(255) COMMENT '头像路径',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `gender` tinyint(1) DEFAULT 1 COMMENT '性别(1-男,2-女)',
  `phone_number` varchar(512) DEFAULT NULL COMMENT '电话号码',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0-正常,1-冻结)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
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
  `change_type` tinyint(1) NOT NULL default 1 COMMENT '修改的内容：1、手机，2、邮箱',
  `oldinfo` varchar(50) NOT NULL COMMENT '旧的信息',
  `newinfo` varchar(50) NOT NULL COMMENT '新的信息',
  `phone_number` varchar(50) NOT NULL COMMENT '常用的联系电话',
  `apply_statue` tinyint(1) NOT NULL COMMENT '修改的内容：1、待审核，2、通过，3、拒绝',
  `ctime` int NOT NULL COMMENT '创建时间',
  `utime` int NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户修改手机和邮箱的申请表';

-- ----------------------------
-- Table structure for user_changeinfo_apply
-- 用户公私钥
-- ----------------------------
CREATE TABLE `user_contract` (
`uid` bigint(20) NOT NULL COMMENT '用户ID',
  `privatekey` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户私钥',
  `publickey` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户公钥',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户公私钥';

-- ----------------------------
-- Table structure for user_role_apply
-- 用户的角色申请表
-- ----------------------------
CREATE TABLE `user_role_apply` (
  `id` bigint(20) not  null comment '用户角色申请id',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` tinyint(1) not null COMMENT '角色id',
  `merchant_name` varchar(100) not null COMMENT '商家名称',
  `legal_entity` varchar(30) not null COMMENT '法人名称',
  `legal_entity_id_number` varchar(18) not null COMMENT '法人身份证号',
  `top_contacts` varchar(30)  COMMENT '紧急联系人',
  `top_contacts_phone_number` varchar(11) COMMENT '紧急联系人手机号',
  `relationship` tinyint(1) COMMENT '与紧急联系人关系',
  `business_license_number` bigint(15) not null COMMENT '营业职业编号',
  `business_license_file` varchar(512) not null COMMENT '营业执照文件',
  `letter_of_attorney_file` varchar(512)  COMMENT '授权委托书文件  ,拼接的字符串',
  `business_qualification_file` varchar(512) COMMENT '经营资质文件   ,拼接的字符串' ,
  `highest_degree_diploma_file` varchar(255) comment '最高学历毕业证文件',
  `driving_license_file` varchar(255) comment '行驶证文件',
  `house_proprietary_certificate_file` varchar(512) comment '房产证或者租房合同文件',
  `apply_status` tinyint(1) COMMENT '申请状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的角色申请表';

-- ----------------------------
-- Table structure for user_role
-- 用户角色关联表
-- ----------------------------
CREATE TABLE `user_role` (
  `id` bigint(20) not null comment '用户角色关联id',
  `uid` bigint(20) not null comment '用户id',
  `role_id` tinyint(1) not null comment '角色id',
  `role_status` tinyint(1) not null comment '角色状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for user_rent_merchant
-- 租赁商表
-- ----------------------------
CREATE TABLE `user_rent_merchant` (
  `uid` bigint(20) not null comment '用户id',
  `merchant_name` varchar(100) not null COMMENT '商家名称',
  `legal_entity` varchar(30) not null COMMENT '法人名称',
  `legal_entity_id_number` varchar(18) not null COMMENT '法人身份证号',
  `business_license_number` bigint(15) not null COMMENT '营业职业编号',
  `business_license_file` varchar(512) not null COMMENT '营业执照文件',
  `highest_degree_diploma_file` varchar(255) comment '最高学历毕业证文件',
  `driving_license_file` varchar(255) comment '行驶证文件',
  `house_proprietary_certificate_file` varchar(512) comment '房产证或者租房合同文件',
  `role_status` tinyint(1) not null comment '角色状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁商表';

-- ----------------------------
-- Table structure for user_capitalist
-- 资金方表
-- ----------------------------
CREATE TABLE `user_capitalist` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_name` varchar(100) not null COMMENT '商家名称',
  `legal_entity` varchar(30) not null COMMENT '法人名称',
  `legal_entity_id_number` varchar(18) not null COMMENT '法人身份证号',
  `top_contacts` varchar(30) not null COMMENT '紧急联系人',
  `top_contacts_phone_number` varchar(11) COMMENT '紧急联系人手机号',
  `relationship` tinyint(1) COMMENT '与紧急联系人关系',
  `business_license_number` bigint(15) not null COMMENT '营业职业编号',
  `business_license_file` varchar(512) not null COMMENT '营业执照文件',
  `letter_of_attorney_file` varchar(512)  COMMENT '授权委托书文件  ,拼接的字符串',
  `business_qualification_file` varchar(512) COMMENT '经营资质文件   ,拼接的字符串',
  `role_status` tinyint(1) not null comment '角色状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金方表';

-- ----------------------------
-- Table structure for user_lessor
-- 出租方表
-- ----------------------------
CREATE TABLE `user_lessor` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_name` varchar(100) not null COMMENT '商家名称',
  `legal_entity` varchar(30) not null COMMENT '法人名称',
  `legal_entity_id_number` varchar(18) not null COMMENT '法人身份证号',
  `top_contacts` varchar(30) not null COMMENT '紧急联系人',
  `top_contacts_phone_number` varchar(11) COMMENT '紧急联系人手机号',
  `relationship` tinyint(1) COMMENT '与紧急联系人关系',
  `business_license_number` bigint(15) not null COMMENT '营业职业编号',
  `business_license_file` varchar(512) not null COMMENT '营业执照文件',
  `role_status` tinyint(1) not null comment '角色状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出租方表';

-- ----------------------------
-- Table structure for user_provider
-- 供应商表
-- ----------------------------
CREATE TABLE `user_provider` (
   `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `merchant_name` varchar(100) not null COMMENT '商家名称',
  `legal_entity` varchar(30) not null COMMENT '法人名称',
  `legal_entity_id_number` varchar(18) not null COMMENT '法人身份证号',
  `business_license_number` bigint(15) not null COMMENT '营业职业编号',
  `business_license_file` varchar(512) not null COMMENT '营业执照文件',
  `role_status` tinyint(1) not null comment '角色状态',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  PRIMARY KEY (`uid`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';