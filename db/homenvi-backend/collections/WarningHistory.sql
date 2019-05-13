DROP TABLE IF EXISTS `warning_history`;
CREATE TABLE `warning_history`
(
    `id`                  BIGINT AUTO_INCREMENT COMMENT '主键ID',
    collection_warn_id    BIGINT                                                         NOT NULL COMMENT '采集项警告id',
    collection_field      varchar(32)                                                    NOT NULL COMMENT '采集字段',
    collection_name       varchar(32)                                                    NOT NULL COMMENT '采集名称',
    warn_reason           varchar(32)                                                    NOT NULL COMMENT '警告原因',
    warn_content          varchar(480)                                                   NOT NULL COMMENT '内容',
    object_version_number bigint   default 1                                             null,
    created_by            bigint   default 0                                             null,
    creation_date         datetime default CURRENT_TIMESTAMP                             null,
    last_updated_by       bigint   default 0                                             null,
    last_update_date      datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT '采集警告历史';