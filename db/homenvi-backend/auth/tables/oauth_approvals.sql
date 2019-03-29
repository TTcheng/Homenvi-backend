DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`
(
  `userId`         varchar(255)       DEFAULT NULL,
  `clientId`       varchar(255)       DEFAULT NULL,
  `scope`          varchar(255)       DEFAULT NULL,
  `status`         varchar(10)        DEFAULT NULL,
  `expiresAt`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE = InnoDB
  CHARSET = utf8;