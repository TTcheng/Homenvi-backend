DROP TABLE IF EXISTS collector;
CREATE TABLE collector
(
    `id`                  BIGINT AUTO_INCREMENT COMMENT '主键ID',
    `identifier`          VARCHAR(32) NOT NULL COMMENT '唯一标识码',
    `name`                VARCHAR(32) NOT NULL COMMENT '名称',
    `password`            VARCHAR(48) NOT NULL COMMENT '密匙',
    `description`         VARCHAR(480) DEFAULT NULL COMMENT '描述',
    `ip_address`          VARCHAR(40)  DEFAULT NULL COMMENT 'ip地址',
    `mac_address`         VARCHAR(40)  DEFAULT NULL COMMENT 'mac地址',
    `dns_address`         VARCHAR(40)  DEFAULT NULL COMMENT 'dns地址',
    `gateway_address`     VARCHAR(40)  DEFAULT NULL COMMENT '网关地址',
    `subnet_mask_address` VARCHAR(40)  DEFAULT NULL COMMENT '子网掩码',
    `last_online_time`    DATETIME     DEFAULT NULL COMMENT '最后上线时间',
    PRIMARY KEY (`id`),
    CONSTRAINT endpoint_u1 UNIQUE (identifier)
) ENGINE = InnoDB
  CHARSET = utf8 COMMENT '终端采集节点';

INSERT INTO collector(identifier, name, password)
VALUES ('HomenviCollectorAlpha', 'Alpha', 'o/2FPLOIbSojNVOOllhA1x/2/StL54d5DnjuQK9LfvI=');



