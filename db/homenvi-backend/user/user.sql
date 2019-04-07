-- auto-generated definition
create table iam_user
(
  id                       bigint auto_increment
    primary key,
  login_name               varchar(128) collate utf8_bin                          not null comment '用户名',
  email                    varchar(128) collate utf8_bin                          not null comment '电子邮箱地址',
  password                 varchar(128) collate utf8_bin                          not null comment '加密的用户密码',
  real_name                varchar(128) collate utf8_bin                          null comment '用户真实姓名',
  phone                    varchar(32) collate utf8_bin                           null comment '手机号',
  image_url                varchar(480) collate utf8_bin                          null comment '用户头像地址',
  profile_photo            mediumtext                                             null comment '用户二进制头像',
  language                 varchar(16) collate utf8_bin default 'zh_CN'           not null comment '语言',
  time_zone                varchar(16) collate utf8_bin default 'GMT+8'           not null comment '时区',
  last_password_updated_at datetime                     default CURRENT_TIMESTAMP not null comment '上一次密码更新时间',
  last_login_at            datetime                                               null comment '上一次登陆时间',
  enabled                  tinyint(3)                   default 1                 not null comment '用户是否启用。1启用，0未启用',
  locked                   tinyint(3)                   default 0                 not null comment '是否锁定账户',
  is_admin                 tinyint(3)                   default 0                 null comment '是否为管理员用户。1表示是，0表示不是',
  locked_until_at          datetime                                               null comment '锁定账户截止时间',
  password_attempt         tinyint(3)                   default 0                 null comment '密码输错累积次数',
  object_version_number    bigint                       default 1                 null,
  created_by               bigint                       default 0                 null,
  creation_date            datetime                     default CURRENT_TIMESTAMP null,
  last_updated_by          bigint                       default 0                 null,
  last_update_date         datetime                     default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  null,
  constraint iam_user_u1
    unique (login_name),
  constraint iam_user_u2
    unique (email),
  constraint iam_user_u3
    unique (phone)
)
  charset = utf8;

INSERT INTO iam_user(login_name, email, password, real_name, phone)
VALUES ('admin', 'ttchengwang@foxmail.com', 'uIAIjt40rnQmHMW8zfVbEQ==', 'Jesse', '15884955091');