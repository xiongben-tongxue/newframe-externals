/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018.08.06 9:43:31                           */
/*==============================================================*/


drop table if exists account_customer;

drop table if exists account_funding;

drop table if exists account_funding_finance_asset;

drop table if exists account_funding_overdue_asset;

drop table if exists account_lessor;

drop table if exists account_lessor_matter_asset;

drop table if exists account_lessor_overdue_asset;

drop table if exists account_renter;

drop table if exists account_renter_financing;

drop table if exists account_renter_overdue_order;

drop table if exists account_renter_rent;

drop table if exists account_supplier;

drop table if exists account_supplier_sell;

drop table if exists funding_gathering_schedule;

drop table if exists lessor_gathering_schedule;

drop table if exists renter_repay;

/*==============================================================*/
/* Table: account_customer                                      */
/* C端租客账户表                                                */
/*==============================================================*/
create table account_customer
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment 'uid',
   useable_amount       decimal(30,10) not null comment '可用余额',
   frozen_amount        decimal(30,10) not null comment '冻结金额',
   due_amount           decimal(30,10) not null comment '待还金额',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_customer comment 'C端租客账户表';

/*==============================================================*/
/* Table: account_funding                                       */
/* 资金方账户表                                                 */
/*==============================================================*/
create table account_funding
(
   id                   bigint not null comment 'id',
   useable_amount       decimal(30,10) not null comment '可用金额',
   total_assets         decimal(30,10) not null comment '资产总额',
   frozen_assets        decimal(30,10) not null comment '冻结资产',
   cash_deposit         decimal(30,10) not null comment '保证金',
   due_amount           decimal(30,10) not null comment '待收金额',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_funding comment '资金方账户表';

/*==============================================================*/
/* Table: account_funding_finance_asset                         */
/* 资金方金融资产表                                             */
/*==============================================================*/
create table account_funding_finance_asset
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '资金方uid',
   order_id             bigint not null comment '关联订单id',
   order_time           bigint not null comment '订单时间',
   invest_amount        decimal(30,10) not null comment '投资金额',
   invest_deadline      int not null comment '投资期限',
   invest_way           int not null default 1 comment '投资方式(1：融资购机)',
   yield_rate           decimal(7,3) not null comment '收益率',
   renter_id            bigint not null comment '融资人-租赁商ID',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_funding_finance_asset comment '资金方金融资产表';

/*==============================================================*/
/* Table: account_funding_overdue_asset                         */
/* 资金方逾期资产                                               */
/*==============================================================*/
create table account_funding_overdue_asset
(
   id                   bigint not null comment 'id',
   invest_time          bigint not null comment '投资时间',
   order_id             bigint not null comment '订单id',
   invest_way           int not null comment '投资方式（1：融资购机，2：租机）',
   invest_amount        decimal(30,10) comment '投资金额(融资购机没有此项)',
   invest_deadline      decimal(30,10) not null comment '投资期限',
   repay_way            int not null comment '还款方式（1：押金贷，2：按月支付）',
   due_amount           decimal(30,10) not null comment '待还金额',
   repay_amount         decimal(30,10) not null comment '已还金额',
   return_time          bigint not null comment '应还时间',
   actual_return_time   bigint comment '实际还款时间',
   overdue_day          int comment '逾期天数',
   overdue_status       int comment '逾期状态（1：逾期，2：催收成功，3：催收中，4：坏账）',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_funding_overdue_asset comment '资金方逾期资产';

/*==============================================================*/
/* Table: account_lessor                                        */
/* 出租方账户表                                                 */
/*==============================================================*/
create table account_lessor
(
   id                   bigint not null comment 'id',
   useable_amount       decimal(30,10) not null comment '可用金额',
   total_assets         decimal(30,10) not null comment '资产总额',
   frozen_assets        decimal(30,10) not null comment '冻结资产',
   cash_deposit         decimal(30,10) not null comment '保证金',
   due_amount           decimal(30,10) not null comment '待收金额',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_lessor comment '出租方账户表';

/*==============================================================*/
/* Table: account_lessor_matter_asset                           */
/* 出租方实物资产                                               */
/*==============================================================*/
create table account_lessor_matter_asset
(
   id                   bigint not null comment 'id',
   rent_time            bigint not null comment '出租时间',
   order_id             bigint not null comment '关联订单id',
   product_info         varchar(256) not null comment '租赁产品信息',
   total_amount         decimal(30,10) not null comment '租金总额',
   rent_deadline        decimal(30,10) not null comment '租机期限',
   matter_price         decimal(30,10) not null comment '租赁物购买价款',
   renter_id            bigint comment '承租人ID',
   renter_name          varchar(30) comment '承租人店铺名称',
   final_renter         bigint comment '终端承租人',
   final_renter_name    varchar(30) comment '终端承租人姓名',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_lessor_matter_asset comment '出租方实物资产';

/*==============================================================*/
/* Table: account_lessor_overdue_asset                          */
/* 出租方逾期资产                                               */
/*==============================================================*/
create table account_lessor_overdue_asset
(
   id                   bigint not null comment 'id',
   invest_time          bigint not null comment '投资时间',
   order_id             bigint not null comment '订单id',
   invest_way           int not null comment '投资方式（1：融资购机，2：租机）',
   invest_amount        decimal(30,10) comment '投资金额(融资购机没有此项)',
   invest_deadline      decimal(30,10) not null comment '投资期限',
   repay_way            int not null comment '还款方式（1：押金贷，2：按月支付）',
   due_amount           decimal(30,10) not null comment '待还金额',
   repay_amount         decimal(30,10) not null comment '已还金额',
   return_time          bigint not null comment '应还时间',
   actual_return_time   bigint comment '实际还款时间',
   overdue_day          int comment '逾期天数',
   overdue_status       int comment '逾期状态（1：逾期，2：催收成功，3：催收中，4：坏账）',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_lessor_overdue_asset comment '出租方逾期资产';

/*==============================================================*/
/* Table: account_renter                                        */
/* 租赁商账户表                                                 */
/*==============================================================*/
create table account_renter
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '用户uid',
   useable_amount       decimal(30,10) not null default 0 comment '可用余额',
   total_assets         decimal(30,10) not null default 0 comment '总资产',
   frozen_assets        decimal(30,10) not null default 0 comment '总资产',
   margin_balance       decimal(30,10) not null default 0 comment '保证金余额',
   margin_advances      decimal(30,10) not null default 0 comment '保证金垫付金额',
   due_amount           decimal(30,10) not null default 0 comment '待收金额',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_renter comment '租赁商账户表';

/*==============================================================*/
/* Table: account_renter_financing                              */
/* 租赁商订单融资表                                             */
/*==============================================================*/
create table account_renter_financing
(
   id                   bigint not null comment 'id',
   financing_time       bigint not null comment '融资时间',
   order_id             bigint not null comment '关联订单id',
   funder_id            bigint not null comment '资金方id',
   financing_amount     decimal(30,10) not null comment '融资金额',
   financing_deadline   int not null comment '融资期限',
   financing_principle  decimal(30,10) not null comment '融资本金',
   total_interest       decimal(30,10) not null comment '融资总利息',
   repay_principal      decimal(30,10) not null default 0 comment '已清偿本金',
   repay_interest       decimal(30,10) not null default 0 comment '已清偿利息',
   repay_status         int not null comment '还款状态（0：正常，1：结清，2：逾期，3：逾期已结清）',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_renter_financing comment '租赁商订单融资表';

/*==============================================================*/
/* Table: account_renter_overdue_order                          */
/* 租赁商订单逾期资产账户表                                     */
/*==============================================================*/
create table account_renter_overdue_order
(
   id                   bigint not null comment 'id',
   invest_time          bigint not null comment '投资时间',
   order_id             bigint not null comment '订单id',
   invest_way           int not null comment '投资方式（1：融资购机，2：租机）',
   invest_amount        decimal(30,10) comment '投资金额(押金贷没有此项)',
   invest_deadline      decimal(30,10) not null comment '投资期限',
   margin_advances      decimal(30,10) not null comment '保证金垫付金额',
   repay_way            int not null comment '还款方式（1：押金贷，2：按月支付）',
   return_time          bigint comment '应还时间',
   repay_amount         decimal(30,10) not null comment '已还金额',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_renter_overdue_order comment '租赁商订单逾期资产账户表';

/*==============================================================*/
/* Table: account_renter_rent                                   */
/* 租赁商租赁账户                                               */
/*==============================================================*/
create table account_renter_rent
(
   id                   bigint not null comment 'id',
   rent_time            bigint not null comment '租机时间',
   order_id             bigint not null comment '关联订单id',
   product_into         varchar(255) not null comment '产品信息',
   rent_amount          decimal(30,10) not null comment '租机总金额',
   rent_deadline        int not null comment '租机期限',
   repay_amount         decimal(30,10) not null comment '已付租金',
   repay_status         int not null comment '还款状态（0：正常，1：已结清，2：逾期未催收，3：逾期已催收，4：已转让）',
   ctime                bigint not null comment 'ctime',
   utime                bigint not null comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_renter_rent comment '租赁商租赁账户';

/*==============================================================*/
/* Table: account_supplier                                      */
/* 供应商账户表                                                 */
/*==============================================================*/
create table account_supplier
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment 'uid',
   useable_amount       decimal(30,10) not null comment '可用金额',
   total_asset          decimal(30,10) not null comment '资产总额',
   frozen_asset         decimal(30,10) not null comment '冻结资产',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_supplier comment '供应商账户表';

/*==============================================================*/
/* Table: account_supplier_sell                                 */
/* 供应商销售账户                                               */
/*==============================================================*/
create table account_supplier_sell
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '供应商uid',
   order_id             bigint not null comment '关联订单ID',
   product_info         varchar(256) not null comment '产品信息',
   price                decimal(30,10) not null comment '购买价款',
   renter_id            bigint not null comment '购机方',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table account_supplier_sell comment '供应商销售账户';

/*==============================================================*/
/* Table: funding_gathering_schedule                            */
/* 资金方收款计划表                                             */
/*==============================================================*/
create table funding_gathering_schedule
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '资金方uid',
   order_id             bigint not null comment '关联订单id',
   renter_id            bigint not null comment '租赁商uid',
   repay_amount         decimal(10,2) not null comment '应还总金额',
   last_repay_time      bigint not null comment '最迟还款日期',
   repay_status         int not null comment '还款状态(1：未扣款，2：已扣款，3：已逾期，4：逾期已结清)',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table funding_gathering_schedule comment '资金方收款计划表';

/*==============================================================*/
/* Table: lessor_gathering_schedule                             */
/* 出租方还款计划表                                             */
/*==============================================================*/
create table lessor_gathering_schedule
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '出租方uid',
   order_id             bigint not null comment '关联订单id',
   renter_id            bigint not null comment '租赁商uid',
   repay_amount         decimal(30,10) not null comment '应还总金额',
   last_repay_time      bigint not null comment '最迟还款日期',
   repay_status         int not null comment '还款状态(1：未扣款，2：已扣款，3：已逾期，4：逾期已结清)',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table lessor_gathering_schedule comment '出租方还款计划表';

/*==============================================================*/
/* Table: renter_repay                                          */
/* 租赁商还款计划表                                             */
/*==============================================================*/
create table renter_repay
(
   id                   bigint not null comment 'id',
   uid                  bigint not null comment '用户uid',
   order_id             bigint not null comment '关联订单id',
   repay_mode           int not null comment '还款方式（1：押金贷，资金方，2：按月，出租方）',
   receive_account      bigint not null comment '收款方账户',
   repay_amount         decimal(30,10) not null comment '应还总金额',
   repay_principle      decimal(30,10) not null comment '应还本金',
   repay_interest       decimal(30,10) comment '应还利息(押金贷有此项)',
   last_repay_time      bigint not null comment '最迟还款日期',
   repay_status         int not null comment '还款状态(1：未扣款，2：已扣款，3：已逾期，4：逾期已结清)',
   ctime                bigint not null comment 'ctime',
   utime                bigint comment 'utime',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table renter_repay comment '租赁商还款计划表';
