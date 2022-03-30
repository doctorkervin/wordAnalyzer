/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lypay

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 11:20:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmmybdc65ty7ml01jaxad1oek` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'admin', '超级管理员，拥有最高权限，应该所有权限', '超级管理员');
INSERT INTO `system_role` VALUES ('2', 'system_code', '管理角色', '财务管理角色');
INSERT INTO `system_role` VALUES ('3', 'platform_member', '平台会员', '客服管理');
