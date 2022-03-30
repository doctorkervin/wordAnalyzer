/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lypay

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 11:19:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `d_key` varchar(255) DEFAULT NULL,
  `d_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dpg4vx0lmfu3r7xlh6nd3ypt6` (`d_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_dict
-- ----------------------------
INSERT INTO `system_dict` VALUES ('1', 'clear_switch_day', 'enable');
