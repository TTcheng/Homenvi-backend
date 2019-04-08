DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`
(
    `id`                  BIGINT AUTO_INCREMENT COMMENT '主键ID',
    userid                BIGINT                                                             NOT NULL COMMENT '所属用户ID',
    `unread`              TINYINT(1)   DEFAULT 1 COMMENT '是否未读',
    title                 varchar(60)                                                        NOT NULL COMMENT '标题',
    content               varchar(480) DEFAULT NULL COMMENT '内容',
    object_version_number bigint       default 1                                             null,
    created_by            bigint       default 0                                             null,
    creation_date         datetime     default CURRENT_TIMESTAMP                             null,
    last_updated_by       bigint       default 0                                             null,
    last_update_date      datetime     default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8 COMMENT '通知';

INSERT INTO iems_monitor.notification (id, userid, unread, title, content, object_version_number, created_by,
                                       creation_date, last_updated_by, last_update_date)
VALUES (1, 5, 1, '测试消息', '测试消息内容', 1, 0, '2019-04-08 17:18:39', 0, '2019-04-08 17:43:39');