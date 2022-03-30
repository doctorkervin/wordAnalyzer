/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lypay

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 11:20:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` tinyint(1) DEFAULT '0',
  `account_locked` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `credentials_expired` tinyint(1) DEFAULT '0',
  `enabled` int(11) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `accountExpired` tinyint(1) DEFAULT '0',
  `accountLocked` tinyint(1) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `credentialsExpired` tinyint(1) DEFAULT '0',
  `loginName` varchar(255) DEFAULT NULL,
  `operaterTime` datetime DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gxrdsloct47dckt2xxy8cm23p` (`login_name`) USING BTREE,
  UNIQUE KEY `UK_saeyd2ycketdh0rbkjmcb176e` (`loginName`) USING BTREE,
  KEY `FKq7b8naqqe0v3h65j9emkqseeo` (`role_id`) USING BTREE,
  KEY `FKdmdwniw0q6kfe15trbjwgr484` (`parent_id`),
  CONSTRAINT `FKdmdwniw0q6kfe15trbjwgr484` FOREIGN KEY (`parent_id`) REFERENCES `system_user` (`id`),
  CONSTRAINT `system_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`),
  CONSTRAINT `system_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', '0', '0', '2018-12-28 18:40:38', '0', '1', 'admin', 'admin', '$2a$11$Rk9rsUVIYdHEI0GD933a2u1Y.delZ3K/aLo9FYHqClHE2HIIZgwda', 'admin', 'F', null, '1', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('2', '0', '0', '2019-01-17 11:17:14', '0', '1', 'ccc001', null, '$2a$11$pkd7Y9TVn4tulkbT6yYHVuIk19wXoXdbH77DqnKadqy6wf.zcIQtu', '', 'M', '2019-01-17 11:17:14', '2', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('3', '0', '0', '2019-01-17 11:18:01', '0', '1', 'ccc002', null, '$2a$11$wJcLdqNB54D5QGWTzoOphOvsZMiZNyeHZI0iO8t5qQ/5AMdt/zi.W', '', 'M', '2019-01-17 11:18:01', '2', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('4', '0', '0', '2019-01-17 13:58:21', '0', '1', 'ly0001', null, '$2a$11$ACNDgaNHtAe0ahsKy0LMgeFNX.In6KZhJ2/cQmUpsF00spV5sEd6G', '', 'M', '2019-01-17 13:58:21', '3', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('5', '0', '0', '2019-01-24 18:20:28', '0', '1', 'finaceAdmin', null, '$2a$11$ecpWgFE9IU/.stRRjwkkdeb77ydypAfkCSNG/6hV4J1vPcXPmgm4S', 'qqqqq', 'M', '2019-01-24 18:20:28', '2', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('6', '0', '0', '2019-01-24 18:20:50', '0', '1', 'kfAdmin', null, '$2a$11$LKN12VwTTfUfIn4Y0IigcudY8U8oLh4nzaHxluipTptTZoVtuSz/u', 'qqqqq', 'M', '2019-01-24 18:20:50', '3', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('7', '0', '0', '2019-03-23 19:02:14', '0', '1', 'quannengzhifu', null, '$2a$11$hOwzuTIsc22vMg/D1bymbexSL2P7ab3YST2MQnYdjZ5KQNt/6Ia0G', '', 'M', '2019-03-23 19:02:14', '3', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('8', '0', '0', '2019-03-30 16:16:50', '0', '1', 'yunding', null, '$2a$11$Va90S0GOBzEBHeanz/FpBOKmGvBUoz/vGpf/UehnFkfbMoPQq/.Se', '', 'M', '2019-03-30 16:16:50', '3', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('9', '0', '0', '2019-04-08 23:11:17', '0', '1', 'test', null, '$2a$11$SKvq0pJ13eX0d2uRcAmax.w61fUwiXUkSfhl2kGYJqZHn1FHy8l6C', '1111', 'M', '2019-04-08 23:11:17', '2', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('10', '0', '0', '2019-04-08 23:17:31', '0', '1', 'test1', null, '$2a$11$SSUe1eBgG3713rtb8.tS8egLpQIyUbSAeIo5ty./v.7ViaIk2z/G6', '444', 'M', '2019-04-08 23:17:31', '1', '0', '0', null, '0', null, null, null);
INSERT INTO `system_user` VALUES ('11', '0', '0', '2019-04-09 01:04:08', '0', '1', 'test2', null, '$2a$11$MEfr8dcbOB.x9/jJCrmDj.WwjyLQvQ4LJer9/sN0coT80f2nMgSXS', '', 'M', '2019-04-09 01:04:08', '1', '0', '0', null, '0', null, null, '2');
