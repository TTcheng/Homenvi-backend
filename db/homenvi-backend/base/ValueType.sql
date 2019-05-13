DROP TABLE IF EXISTS `base_value_type`;
CREATE TABLE `base_value_type`
(
    `id`                  BIGINT AUTO_INCREMENT COMMENT '主键ID',
    type                  varchar(32)                                                    NOT NULL COMMENT '类型',
    name                  varchar(32)                                                    NOT NULL COMMENT '名称',
    object_version_number bigint   default 1                                             null,
    created_by            bigint   default 0                                             null,
    creation_date         datetime default CURRENT_TIMESTAMP                             null,
    last_updated_by       bigint   default 0                                             null,
    last_update_date      datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT '取值类型';

INSERT INTO iems_monitor.base_value_type (id, type, name, object_version_number, created_by, creation_date, last_updated_by, last_update_date) VALUES (1, 'min', '最小值', 1, 0, '2019-05-11 16:51:14', 0, '2019-05-11 16:51:14');
INSERT INTO iems_monitor.base_value_type (id, type, name, object_version_number, created_by, creation_date, last_updated_by, last_update_date) VALUES (2, 'max', '最大值', 1, 0, '2019-05-11 16:51:15', 0, '2019-05-11 16:51:15');
INSERT INTO iems_monitor.base_value_type (id, type, name, object_version_number, created_by, creation_date, last_updated_by, last_update_date) VALUES (3, 'mean', '平均值', 1, 0, '2019-05-11 16:51:15', 0, '2019-05-11 16:51:15');
INSERT INTO iems_monitor.base_value_type (id, type, name, object_version_number, created_by, creation_date, last_updated_by, last_update_date) VALUES (4, 'none', '无', 1, 0, '2019-05-11 16:51:15', 0, '2019-05-11 16:51:15');