DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
  `token_id`       varchar(255) DEFAULT NULL,
  `token`          blob,
  `authentication` blob
) ENGINE = InnoDB
  CHARSET = utf8;