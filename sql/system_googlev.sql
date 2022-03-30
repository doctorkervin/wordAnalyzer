/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lypay

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 11:19:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_googlev
-- ----------------------------
DROP TABLE IF EXISTS `system_googlev`;
CREATE TABLE `system_googlev` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qrcode_str` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `qrcodeStr` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2xujhv9mckgln15ewmuci231e` (`user_name`) USING BTREE,
  UNIQUE KEY `UK_1v7n3cuabo2x0vlxluiovlady` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_googlev
-- ----------------------------
INSERT INTO `system_googlev` VALUES ('1', 'otpauth://totp/admin?secret=ME55NQD66TMLU27C', 'ME55NQD66TMLU27C', 'enable', 'admin', null, null);
INSERT INTO `system_googlev` VALUES ('2', 'otpauth://totp/ccc001?secret=DVYSM27MXT4QEJP7', 'DVYSM27MXT4QEJP7', 'enable', 'ccc001', null, null);
