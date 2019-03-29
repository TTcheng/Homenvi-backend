-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails`
(
  `appId`                  varchar(255) NOT NULL,
  `resourceIds`            varchar(255) DEFAULT NULL,
  `appSecret`              varchar(255) DEFAULT NULL,
  `scope`                  varchar(255) DEFAULT NULL,
  `grantTypes`             varchar(255) DEFAULT NULL,
  `redirectUrl`            varchar(255) DEFAULT NULL,
  `authorities`            varchar(255) DEFAULT NULL,
  `access_token_validity`  int(11)      DEFAULT NULL,
  `refresh_token_validity` int(11)      DEFAULT NULL,
  `additionalInformation`  varchar(480) DEFAULT NULL,
  `autoApproveScopes`      varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE = InnoDB
  CHARSET = utf8;