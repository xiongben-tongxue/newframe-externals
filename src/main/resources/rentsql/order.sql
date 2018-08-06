
-- ----------------------------
-- Table structure for order_rent
-- 租赁商订单表
-- ----------------------------
CREATE TABLE `order_renter` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_id` bigint NOT NULL COMMENT '合作方ID,1:52,2:金捷',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(30) DEFAULT NULL COMMENT '商家名字',
  `uid` varchar(30) DEFAULT NULL COMMENT '用户ID',
  `rent_user_info` varchar(256) DEFAULT NULL COMMENT '租客信息',
  `rent_product_info` varchar(256) DEFAULT NULL COMMENT '产品信息',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` varchar(512) DEFAULT NULL COMMENT '还贷月数(租期)',
  `down_payment` varchar(512) DEFAULT NULL COMMENT '首付金额',
  `accident_benefit` varchar(512) DEFAULT NULL COMMENT '意外保障金额',
  `order_status` varchar(512) DEFAULT NULL COMMENT '订单状态,1:待处理,2:待资金方审核,3:待出租方审核,4:资金方审核不通过,5:出租方审核不通过,6:待发货,7:待收货,8:已确认收货',
  `delete_status` Boolean DEFAULT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁商订单表';


-- ----------------------------
-- Table structure for financing_buy_machine
-- 融资购机表
-- ----------------------------
CREATE TABLE `financing_buy_machine` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `merchant_id` bigint NOT NULL COMMENT '租赁商家ID',
  `merchant_name` varchar(30) DEFAULT NULL COMMENT '租赁商家名字',
  `uid` varchar(30) DEFAULT NULL COMMENT '用户ID',
  `user_real_name` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(30) DEFAULT NULL COMMENT '用户身份证号码',
  `financing_amount` varchar(50) NOT NULL COMMENT '融资金额',
  `financing_deadline` varchar(50) NOT NULL COMMENT '融资期限',
  `supply_id` tinyint(1) NOT NULL COMMENT '供应商的ID',
  `supply_name` tinyint(1) NOT NULL COMMENT '供应商的名字',
  `financing_times` varchar(50) NOT NULL COMMENT '融资次数',
  `financing_status` boolean DEFAULT NULL COMMENT '融资购机状态,1:处理中,2:通过,3:拒绝,4:取消',
  `delete_status` boolean DEFAULT NULL COMMENT '存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='融资购机表';

-- ----------------------------
-- Table structure for rent_machine
-- 租机表
-- ----------------------------
CREATE TABLE `rent_machine` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `merchant_id` bigint NOT NULL COMMENT '租赁商家ID',
  `merchant_name` varchar(30) DEFAULT NULL COMMENT '租赁商家名字',
  `uid` varchar(30) DEFAULT NULL COMMENT '用户ID',
  `user_real_name` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(30) DEFAULT NULL COMMENT '用户身份证号码',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` varchar(512) DEFAULT NULL COMMENT '还贷月数(租期)',
  `down_payment` varchar(512) DEFAULT NULL COMMENT '首付金额',
  `accident_benefit` varchar(512) DEFAULT NULL COMMENT '意外保障金额',
  `mode_of_payment` varchar(50) NOT NULL COMMENT '支付方式,1:全款支付,2:分期支付',
  `rent_times` varchar(50) NOT NULL COMMENT '租机次数',
  `rent_status` boolean DEFAULT NULL COMMENT '租机状态,1:处理中,2:通过,3:拒绝,4:取消',
  `delete_status` boolean DEFAULT NULL COMMENT '存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租机表';

-- ----------------------------
-- Table structure for order_funder
-- 资金方订单表
-- ----------------------------
CREATE TABLE `order_funder` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_id` bigint NOT NULL COMMENT '合作方ID,1:52,2:金捷',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(30) DEFAULT NULL COMMENT '商家名字',
  `uid` varchar(30) DEFAULT NULL COMMENT '用户ID',
  `rent_user_info` varchar(256) DEFAULT NULL COMMENT '租客信息',
  `rent_product_info` varchar(256) DEFAULT NULL COMMENT '产品信息',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` varchar(512) DEFAULT NULL COMMENT '还贷月数(租期)',
  `down_payment` varchar(512) DEFAULT NULL COMMENT '首付金额',
  `accident_benefit` varchar(512) DEFAULT NULL COMMENT '意外保障金额',
  `order_status` varchar(512) DEFAULT NULL COMMENT '订单状态,1:待处理,2:审核不通过,3:放款中待供应商确认,4:待收货,5:还款中,6:坏账,7:已完成,8:取消',
  `delete_status` Boolean DEFAULT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁商订单表';

-- ----------------------------
-- Table structure for funder_
-- 融资购机或者租机派单表
-- ----------------------------
CREATE TABLE `order_dispatch` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `merchant_id` bigint NOT NULL COMMENT '租赁商家ID',
  `merchant_name` varchar(30) DEFAULT NULL COMMENT '租赁商家名字',
  `dispatch_type` varchar(30) DEFAULT NULL COMMENT '融资购机或者租机,1:融资购机,2:租机',
  `acceptor_id` bigint NOT NULL COMMENT '派单接受方ID',
  `acceptor_name` bigint NOT NULL COMMENT '派单接受方的名字',
  `uid` varchar(30) DEFAULT NULL COMMENT '用户ID',
  `user_real_name` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(30) DEFAULT NULL COMMENT '用户身份证号码',
  `financing_amount` varchar(50) NOT NULL COMMENT '融资金额',
  `financing_deadline` varchar(50) NOT NULL COMMENT '融资期限',
  `financing_times` varchar(50) NOT NULL COMMENT '次数',
  `financing_status` boolean DEFAULT NULL COMMENT '融资购机状态,1:处理中,2:通过,3:拒绝,4:取消',
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




