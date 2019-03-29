DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
  `code`           varchar(255) DEFAULT NULL,
  `authentication` blob
) ENGINE = InnoDB
  CHARSET = utf8;