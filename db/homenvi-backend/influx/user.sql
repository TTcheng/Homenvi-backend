-- auto-generated definition
create table influx_user
(
    id                    bigint auto_increment
        primary key,
    username              varchar(128) collate utf8_bin                                  not null comment '用户名',
    password              varchar(128) collate utf8_bin                                  not null comment '加密的用户密码',
    authorities           varchar(128) collate utf8_bin                                  null comment '权限',
    object_version_number bigint   default 1                                             null,
    created_by            bigint   default 0                                             null,
    creation_date         datetime default CURRENT_TIMESTAMP                             null,
    last_updated_by       bigint   default 0                                             null,
    last_update_date      datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
    constraint iam_user_u1
        unique (username)
)
    charset = utf8 COMMENT 'Influx用户';

INSERT INTO influx_user(username, password, authorities)
VALUES ('homenvi-collector', 'homenvi', 'WRITE');

INSERT INTO influx_user(username, password, authorities)
VALUES ('homenvi-front', 'homenvi', 'READ');