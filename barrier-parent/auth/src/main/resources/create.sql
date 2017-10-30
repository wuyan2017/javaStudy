drop index channel_subProduct_idx on t_fake;

drop table if exists t_fake;

/*==============================================================*/
/* Table: t_fake                                                */
/*==============================================================*/
create table t_fake
(
   id                   int not null auto_increment comment 'id',
   code                 varchar(100) not null default '' comment '映射码',
   response             text comment '返回值',
   delay_millisecond    int not null default 0 comment '延迟返回时间(毫秒)',
   callback				int not null default 0 comment '1-回调，0-不回调',
   callback_url			varchar(100) not null default '' comment '回调地址',
   callback_str			varchar(1000) not null default '' comment '回调请求字符串',
   primary key (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 ;

alter table t_fake comment '模拟返回';

/*==============================================================*/
/* Index: channel_subProduct_idx                                */
/*==============================================================*/
create  index channel_subProduct_idx on t_fake
(
   code
);
