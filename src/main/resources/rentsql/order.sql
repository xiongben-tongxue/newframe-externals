
-- ----------------------------
-- Table structure for order_rent
-- 租赁商订单表
-- ----------------------------
CREATE TABLE `order_renter` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` varchar(256) NOT NULL COMMENT '订单ID',
  `partner_id` tinyint(1) NOT NULL COMMENT '合作方ID,1:52,2:金捷,3:有多的再补充',
  `uid` varchar(256) NOT NULL COMMENT '用户ID',
  `user_realname` varchar(256) NOT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(256) NOT NULL COMMENT '用户身份证号码',
  `user_mobile` varchar(256) NOT NULL COMMENT '用户手机号',
  `user_credit_score` int NOT NULL COMMENT '用户信用分',
  `user_credit_line` tinyint(1) NOT NULL COMMENT '用户的信用度评级，1:信用极好,2:信用中等,3:信用较差',
  `product_brand` varchar(256) NOT NULL COMMENT '产品品牌',
  `product_name` varchar(256) NOT NULL COMMENT '产品名字',
  `product_color` varchar(256) NOT NULL COMMENT '产品颜色',
  `product_storage` int NOT NULL COMMENT '产品内存GB',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` int NOT NULL COMMENT '还贷月数(租期)',
  `down_payment` decimal(10,2) NOT NULL COMMENT '首付金额',
  `accident_benefit` decimal(10,2) NOT NULL COMMENT '意外保障金额',
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态,1:待处理,2:待资金方审核,3:待出租方审核,4:资金方审核不通过,5:出租方审核不通过,6:待发货,7:待收货,8:已确认收货',
  `delete_status` boolean NOT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁商订单表';


-- ----------------------------
-- Table structure for financing_buy_machine
-- 融资购机表
-- ----------------------------
CREATE TABLE `financing_buy_machine` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `merchant_id` bigint NOT NULL COMMENT '租赁商家ID',
  `merchant_name` varchar(256) NOT NULL COMMENT '租赁商家名字',
  `funder_id` bigint NOT NULL COMMENT '资金方ID',
  `funder_name` varchar(256) NOT NULL COMMENT '资金方名字',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `user_realname` varchar(256) NOT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(256) NOT NULL COMMENT '用户身份证号码',
  `user_credit` tinyint(1) NOT NULL COMMENT '用户的信用度评级，1:信用极好,2:信用中等,3:信用较差',
  `financing_amount` decimal(10,2) NOT NULL COMMENT '融资金额',
  `financing_deadline` decimal(10,2) NOT NULL COMMENT '融资期限',
  `supply_id` tinyint(1) NOT NULL COMMENT '供应商的ID',
  `supply_name` tinyint(1) NOT NULL COMMENT '供应商的名字',
  `dispatch_times` tinyint(1) NOT NULL COMMENT '派单次数',
  `financing_status` tinyint(1) NOT NULL COMMENT '融资购机状态,1:处理中,2:通过,3:拒绝,4:取消',
  `delete_status` boolean NOT NULL COMMENT '存在状态,0:false(正常状态),1:true(删除态)',
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
  `merchant_name` varchar(256) NOT NULL COMMENT '租赁商家名字',
  `hirer_id` bigint NOT NULL COMMENT '出租方ID',
  `hirer_name` varchar(256) NOT NULL COMMENT '出租方名字',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `user_realname` varchar(256) DEFAULT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(256) DEFAULT NULL COMMENT '用户身份证号码',
  `user_credit` tinyint(1) NOT NULL COMMENT '用户的信用度评级，1:信用极好,2:信用中等,3:信用较差',
  `financing_amount` varchar(50) NOT NULL COMMENT '融资金额',
  `financing_deadline` varchar(50) NOT NULL COMMENT '融资期限',
  `supply_id` tinyint(1) NOT NULL COMMENT '供应商的ID',
  `supply_name` tinyint(1) NOT NULL COMMENT '供应商的名字',
  `dispatch_times` tinyint(1) NOT NULL COMMENT '派单次数,最多三次',
  `mode_of_payment` tinyint(1) NOT NULL COMMENT '支付方式,1:全额支付,2:分期支付',
  `rent_status` tinyint(1) NOT NULL COMMENT '租机状态,1:处理中,2:通过,3:拒绝,4:取消',
  `delete_status` boolean NOT NULL COMMENT '存在状态,0:false(正常状态),1:true(删除态)',
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
  `partner_order_id` varchar(256)  NOT NULL COMMENT '订单ID',
  `partner_id` tinyint(1) NOT NULL COMMENT '合作方ID,1:52,2:金捷',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(256) DEFAULT NULL COMMENT '商家名字',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `user_realname` varchar(256) NOT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(256) NOT NULL COMMENT '用户身份证号码',
  `user_mobile` varchar(256) NOT NULL COMMENT '用户手机号',
  `user_credit_score` int NOT NULL COMMENT '用户信用分',
  `user_credit_line` tinyint(1) NOT NULL COMMENT '用户的信用度评级，1:信用极好,2:信用中等,3:信用较差',
  `product_brand` varchar(256) NOT NULL COMMENT '产品品牌',
  `product_name` varchar(256) NOT NULL COMMENT '产品名字',
  `product_color` varchar(256) NOT NULL COMMENT '产品颜色',
  `product_storage` int NOT NULL COMMENT '产品内存GB',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` int NOT NULL COMMENT '还贷月数(租期)',
  `down_payment` decimal(10,2) NOT NULL COMMENT '首付金额',
  `accident_benefit` decimal(10,2) NOT NULL COMMENT '意外保障金额',
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态,1:待处理,2:待资金方审核,3:待出租方审核,4:资金方审核不通过,5:出租方审核不通过,6:待发货,7:待收货,8:已确认收货',
  `delete_status` boolean NOT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金方订单表';

-- ----------------------------
-- Table structure for order_hirer
-- 出租方订单表
-- ----------------------------
CREATE TABLE `order_hirer` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` varchar(256)  NOT NULL COMMENT '订单ID',
  `partner_id` tinyint(1) NOT NULL COMMENT '合作方ID,1:52,2:金捷',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(256) DEFAULT NULL COMMENT '商家名字',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `user_realname` varchar(256) NOT NULL COMMENT '用户真实姓名',
  `user_id_number` varchar(256) NOT NULL COMMENT '用户身份证号码',
  `user_mobile` varchar(256) NOT NULL COMMENT '用户手机号',
  `user_credit_score` int NOT NULL COMMENT '用户信用分',
  `user_credit_line` tinyint(1) NOT NULL COMMENT '用户的信用度评级，1:信用极好,2:信用中等,3:信用较差',
  `product_brand` varchar(256) NOT NULL COMMENT '产品品牌',
  `product_name` varchar(256) NOT NULL COMMENT '产品名字',
  `product_color` varchar(256) NOT NULL COMMENT '产品颜色',
  `product_storage` int NOT NULL COMMENT '产品内存GB',
  `monthly_payment` decimal(10,2) NOT NULL COMMENT '月租金金额(月还款数)',
  `number_of_payments` int NOT NULL COMMENT '还贷月数(租期)',
  `down_payment` decimal(10,2) NOT NULL COMMENT '首付金额',
  `accident_benefit` decimal(10,2) NOT NULL COMMENT '意外保障金额',
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态,1:待处理,2:待资金方审核,3:待出租方审核,4:资金方审核不通过,5:出租方审核不通过,6:待发货,7:待收货,8:已确认收货',
  `delete_status` boolean NOT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出租方订单表';

-- ----------------------------
-- Table structure for order_supplier
-- 供应商订单表
-- ----------------------------
CREATE TABLE `order_supplier` (
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` varchar(256)  NOT NULL COMMENT '订单ID',
  `partner_id` bigint NOT NULL COMMENT '合作方ID,1:52,2:金捷',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `merchant_name` varchar(256) NOT NULL  COMMENT '商家名字',
  `uid` bigint DEFAULT NULL COMMENT '用户ID',
  `receiver_name` varchar(256) NOT NULL COMMENT '收货人姓名',
  `receiver_mobile` varchar(256) NOT NULL COMMENT '用户手机号',
  `receiver_address` varchar(256) NOT NULL COMMENT '快递地址',
  `product_brand` varchar(256) NOT NULL COMMENT '产品品牌',
  `product_name` varchar(256) NOT NULL COMMENT '产品名字',
  `product_color` varchar(256) NOT NULL COMMENT '产品颜色',
  `product_storage` int NOT NULL COMMENT '产品内存GB',
  `express_company` varchar(256) NOT NULL COMMENT '快递公司',
  `express_number` varchar(256) NOT NULL COMMENT '快递单号',
  `express_tracking` varchar(256) NOT NULL COMMENT '物流跟踪',
  `order_status` tinyint(1)  NOT NULL COMMENT '订单状态,1:未发货,2:已发货,3:已确认收货',
  `delete_status` boolean NOT NULL COMMENT '订单的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商订单表';

-- ----------------------------
-- Table structure for order_supplier
-- 物流信息实时更新表
-- ----------------------------
CREATE TABLE `order_express` (
  `express_number` bigint NOT NULL COMMENT '快递单号',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `partner_order_id` bigint NOT NULL COMMENT '合作方的订单ID',
  `express_company` varchar(256) NOT NULL COMMENT '快递公司',
  `express_tracking` varchar(256) NOT NULL COMMENT '物流跟踪',
  `order_status` tinyint(1) NOT NULL COMMENT '订单状态,1:未发货,2:已发货,3:已确认收货',
  `express_status` boolean NOT NULL COMMENT '运单号的存在状态,0:false(正常状态),1:true(删除态)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`express_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流信息实时更新表';





