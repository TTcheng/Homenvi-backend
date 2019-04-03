-- auto-generated definition
create table iam_role
(
  id                             bigint auto_increment
    primary key,
  name                           varchar(64) collate utf8_bin         not null comment '角色名',
  code                           varchar(128) collate utf8_bin        not null comment '角色编码',
  permission_set_id              bigint     collate utf8_bin          not null comment '权限集ID',
  description                    varchar(255) collate utf8_bin        null comment '角色描述full description',
  inherit_role_id              bigint     default 0                 null comment '继承角色ID',
  is_enabled                     tinyint(3) default 1                 not null comment '是否启用。1启用，0未启用',
  is_modified                    tinyint(3) default 1                 not null comment '是否可以修改。1表示可以，0不可以',
  object_version_number          bigint     default 1                 null,
  created_by                     bigint     default 0                 null,
  creation_date                  datetime   default CURRENT_TIMESTAMP null,
  last_updated_by                bigint     default 0                 null,
  last_update_date               datetime   default CURRENT_TIMESTAMP null,
  constraint iam_role_u1
    unique (code)
)
  charset = utf8;

insert into iam_role (name, code, permission_set_id, description)
values ('ADMIN','ADMIN',1,'super user');

insert into iam_role (name, code, permission_set_id,inherit_role_id, description)
values ('Homenvi','HOMENVI_ADMIN',1,1,'homenvi super user');


