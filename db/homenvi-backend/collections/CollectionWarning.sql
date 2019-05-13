DROP TABLE IF EXISTS `collection_warning`;
CREATE TABLE `collection_warning`
(
    `id`                  BIGINT AUTO_INCREMENT COMMENT '主键ID',
    collection_spec_id    BIGINT                                                               NOT NULL COMMENT '采集项id',
    collection_field      varchar(32)                                                          NOT NULL COMMENT '字段',
    collection_name       varchar(32)                                                          NOT NULL COMMENT '名称',
    reason                varchar(32)                                                          NOT NULL COMMENT '警告原因',
    value_type            varchar(32)                                                          NOT NULL COMMENT '取值类型',
    upper_bound           decimal(10, 2) DEFAULT 99999999                                      NOT NULL COMMENT '上届值',
    lower_bound           decimal(10, 2) DEFAULT 0                                             NOT NULL COMMENT '下界值',
    remark                varchar(480)                                                         NOT NULL COMMENT '备注',
    advice                varchar(480)                                                         NOT NULL COMMENT '建议',
    object_version_number bigint         default 1                                             null,
    created_by            bigint         default 0                                             null,
    creation_date         datetime       default CURRENT_TIMESTAMP                             null,
    last_updated_by       bigint         default 0                                             null,
    last_update_date      datetime       default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT '采集警告';


# TRUNCATE TABLE collection_warning;
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (14, '严重污染', 99999999.00, 250.01, 'PM2.5浓度', 'dustDensity', '建议您打开空气净化器，出门时尽量佩戴防霾防尘口罩',
        '国家标准规定，PM2.5浓度>250ug/m³时为轻度污染。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (14, '重度污染', 250.00, 150.01, 'PM2.5浓度', 'dustDensity', '建议您打开空气净化器，出门时尽量佩戴防霾防尘口罩',
        '国家标准规定，PM2.5浓度>150ug/m³时为轻度污染。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (14, '中度污染', 150.00, 115.01, 'PM2.5浓度', 'dustDensity', '建议您打开空气净化器，出门时尽量佩戴防霾防尘口罩',
        '国家标准规定，PM2.5浓度>115ug/m³时为轻度污染。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (14, '轻度污染', 115.00, 75.01, 'PM2.5浓度', 'dustDensity', '建议您打开空气净化器，出门时尽量佩戴防霾防尘口罩',
        '国家标准规定，PM2.5浓度>75ug/m³时为轻度污染。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (16, '嘈杂', 99999999.00, 501.00, '声音', 'sound', '当前环境较为嘈杂，可能不适合休息，甚至会对您的生活和工作产生影响',
        '声音检测值大于500时，说明当前室内噪音已经非常严重。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (15, '火灾危险或有毒气体泄漏', 99999999.00, 400.01, '烟雾和有毒气体', 'gasValue', '请马上进行确认，若已经产生火灾或发生泄漏，请立即逃离现场，并联系消防人员。',
        '烟雾和有毒气体检测值大于400时，有较大可能已经发生火灾或有毒气体泄漏。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (8, '潮湿', 100.00, 80.00, '湿度', 'humidity', '建议您打开门窗多通风或采取其他有效除湿措施',
        '夏季制冷时，相对湿度以40%—80%为宜，冬季采暖时，应控制在30%——60%。老人和小孩适合的室内湿度为45%—50%，哮喘等呼吸道系统疾病的患者适宜的室内湿度在40%—50%之间。');
INSERT INTO iems_monitor.collection_warning (collection_spec_id, reason, upper_bound, lower_bound,
                                             collection_name, collection_field, advice, remark)
VALUES (8, '干燥', 29.99, 0.00, '湿度', 'humidity', '建议采取一些加湿措施',
        '夏季制冷时，相对湿度以40%—80%为宜，冬季采暖时，应控制在30%——60%。老人和小孩适合的室内湿度为45%—50%，哮喘等呼吸道系统疾病的患者适宜的室内湿度在40%—50%之间。');