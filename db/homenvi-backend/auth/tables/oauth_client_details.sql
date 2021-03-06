-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
  `client_id`               varchar(255) NOT NULL,
  `resource_ids`            varchar(255)  DEFAULT NULL,
  `client_secret`           varchar(255)  DEFAULT NULL,
  `scope`                   varchar(255)  DEFAULT NULL,
  `authorized_grant_types`  varchar(255)  DEFAULT NULL,
  `web_server_redirect_uri` varchar(255)  DEFAULT NULL,
  `authorities`             varchar(255)  DEFAULT NULL,
  `access_token_validity`   int(11)       DEFAULT NULL,
  `refresh_token_validity`  int(11)       DEFAULT NULL,
  `additional_information`  varchar(480) DEFAULT NULL,
  `autoapprove`             varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE = InnoDB
  charset = utf8;
