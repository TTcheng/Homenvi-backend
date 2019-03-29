DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
  `token_id`          varchar(255) DEFAULT NULL,
  `token`             blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name`         varchar(255) DEFAULT NULL,
  `client_id`         varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE = InnoDB
  CHARSET = utf8;