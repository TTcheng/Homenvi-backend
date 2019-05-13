DROP TABLE IF EXISTS `collection_specification`;
create table collection_specification
(
    id                    bigint auto_increment comment '主键ID'
        primary key,
    field                 varchar(23)                              not null comment '字段',
    name                  varchar(32)                              not null comment '名称',
    unit                  varchar(32)                              not null comment '单位',
    remark                varchar(480)                             null comment '备注',
    min                   decimal(10, 2) default 0.00              null comment '最小值',
    max                   decimal(10, 2)                           null comment '最大值',
    object_version_number bigint         default 1                 null,
    created_by            bigint         default 0                 null,
    creation_date         datetime       default CURRENT_TIMESTAMP null,
    last_updated_by       bigint         default 0                 null,
    last_update_date      datetime       default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint collection_specification_field_uindex
        unique (field)
)
    engine = InnoDB
    comment '采集项参数'
    charset = utf8mb4;



INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (8, 'humidity', '湿度', '%', '', 0.00, 100.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (9, 'celsius', '温度', '℃', '', -40.00, 80.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (10, 'fahrenheit', '华氏温度', '℉', '', 104.00, 176.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (11, 'heatIndexCelsius', '体感温度', '℃', null, -40.00, 80.00, 1, 0, '2019-05-11 14:37:33', 0,
        '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (12, 'heatIndexFahrenheit', '华氏体感温度', '℉', null, 104.00, 176.00, 1, 0, '2019-05-11 14:37:33', 0,
        '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (13, 'brightness', '光线强度', 'lux', null, 0.00, 10000.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (14, 'dustDensity', 'PM2.5浓度', 'ug/m³', null, 0.00, 500.00, 1, 0, '2019-05-11 14:37:33', 0,
        '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (15, 'gasValue', '烟雾和有毒气体', '', '', 0.00, 1023.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');
INSERT INTO iems_monitor.collection_specification (id, field, name, unit, remark, min, max, object_version_number,
                                                   created_by, creation_date, last_updated_by, last_update_date)
VALUES (16, 'sound', '噪音', '', null, 0.00, 1023.00, 1, 0, '2019-05-11 14:37:33', 0, '2019-05-11 14:37:33');