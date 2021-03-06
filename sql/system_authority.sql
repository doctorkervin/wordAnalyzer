/*
Navicat MySQL Data Transfer

Source Server         : mysql_localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lypay

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-06-18 11:19:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_grd9tg294t1lrm0m9mtix5x0g` (`code`) USING BTREE,
  KEY `FKt41vdfik67f5hfqnpk7e47m7l` (`parent_id`) USING BTREE,
  CONSTRAINT `system_authority_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `system_authority` (`id`),
  CONSTRAINT `system_authority_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `system_authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_authority
-- ----------------------------
INSERT INTO `system_authority` VALUES ('1', 'manager_manage', '管理员管理', 'menus', null);
INSERT INTO `system_authority` VALUES ('2', 'authority_manage', '权限管理', 'menus', '1');
INSERT INTO `system_authority` VALUES ('3', 'manager_list', '管理员列表', 'menus', '1');
INSERT INTO `system_authority` VALUES ('4', 'role_manage', '角色管理', 'menus', '1');
INSERT INTO `system_authority` VALUES ('5', 'system_mange', '系统管理', 'menus', null);
INSERT INTO `system_authority` VALUES ('6', 'authority_add', '添加权限', 'button', '2');
INSERT INTO `system_authority` VALUES ('7', 'authority_bat_delete', '批量删除', 'button', '2');
INSERT INTO `system_authority` VALUES ('8', 'authority_delete', '删除', 'button', '2');
INSERT INTO `system_authority` VALUES ('9', 'authority_edit', '编辑', 'button', '2');
INSERT INTO `system_authority` VALUES ('10', 'add', '增加', 'button', '3');
INSERT INTO `system_authority` VALUES ('11', 'edit', '编辑', 'button', '3');
INSERT INTO `system_authority` VALUES ('12', 'delete', '删除', 'button', '3');
INSERT INTO `system_authority` VALUES ('13', 'user_bat_delete', '批量删除', 'button', '3');
INSERT INTO `system_authority` VALUES ('14', 'user_add', '添加管理员', 'button', '3');
INSERT INTO `system_authority` VALUES ('15', 'user_delete', '删除', 'button', '3');
INSERT INTO `system_authority` VALUES ('16', 'user_edit', '编辑', 'button', '3');
INSERT INTO `system_authority` VALUES ('17', 'user_status', '状态', 'button', '3');
INSERT INTO `system_authority` VALUES ('18', 'role_bat_delete', '批量删除', 'button', '4');
INSERT INTO `system_authority` VALUES ('19', 'role_add', '添加', 'button', '4');
INSERT INTO `system_authority` VALUES ('20', 'role_edit', '编辑', 'button', '4');
INSERT INTO `system_authority` VALUES ('21', 'role_delete', '删除', 'button', '4');
INSERT INTO `system_authority` VALUES ('22', 'system_log', '系统日志', 'menus', '5');
INSERT INTO `system_authority` VALUES ('23', 'order_manage', '订单管理', 'menus', null);
INSERT INTO `system_authority` VALUES ('24', 'pay_order_manage', '支付订单管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('25', 'payOrder_view', '订单查看详情', 'button', '24');
INSERT INTO `system_authority` VALUES ('26', 'pay_merchant_manage', '商户管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('27', 'pay_payChannel_manage', '商户渠道关系管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('28', 'payMerchant_view', '商户查看详情', 'button', '26');
INSERT INTO `system_authority` VALUES ('29', 'payChannel_view', '支付渠道查看详情', 'button', '27');
INSERT INTO `system_authority` VALUES ('30', 'pay_channel_manage', '渠道管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('31', 'pay_channelMchInfo_manage', '第三方渠道管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('32', 'channel_view', '渠道查看详情', 'button', '30');
INSERT INTO `system_authority` VALUES ('33', 'channelMchInfo_view', '商户渠道查看详情', 'button', '31');
INSERT INTO `system_authority` VALUES ('34', 'payMerchant_add', '商户添加', 'button', '26');
INSERT INTO `system_authority` VALUES ('35', 'payMerchant_edit', '商户编辑', 'button', '26');
INSERT INTO `system_authority` VALUES ('36', 'payMerchant_delete', '商户批量删除', 'button', '26');
INSERT INTO `system_authority` VALUES ('37', 'payChannel_add', '支付渠道添加', 'button', '27');
INSERT INTO `system_authority` VALUES ('38', 'payChannel_edit', '支付渠道修改', 'button', '27');
INSERT INTO `system_authority` VALUES ('39', 'payChannel_delete', '支付渠道删除', 'button', '27');
INSERT INTO `system_authority` VALUES ('40', 'channel_add', '渠道添加', 'button', '30');
INSERT INTO `system_authority` VALUES ('41', 'channel_edit', '渠道编辑', 'button', '30');
INSERT INTO `system_authority` VALUES ('42', 'channel_delete', '渠道批量删除', 'button', '30');
INSERT INTO `system_authority` VALUES ('43', 'channelMchInfo_add', '商户渠道添加', 'button', '31');
INSERT INTO `system_authority` VALUES ('44', 'channelMchInfo_edit', '商户渠道编辑', 'button', '31');
INSERT INTO `system_authority` VALUES ('45', 'channelMchInfo_delete', '商户渠道批量删除', 'button', '31');
INSERT INTO `system_authority` VALUES ('46', 'pay_mchNotify_manage', '业务通知管理', 'menus', '23');
INSERT INTO `system_authority` VALUES ('47', 'mchNotify_view', '查看商户业务通知信息', 'button', '46');
INSERT INTO `system_authority` VALUES ('48', 'payOrder_Resend', '重新发送通知', 'button', '24');
INSERT INTO `system_authority` VALUES ('49', 'export_order', '导出', 'button', '24');
INSERT INTO `system_authority` VALUES ('50', 'system_dict', '配置参数管理', 'menus', '5');
INSERT INTO `system_authority` VALUES ('51', 'dict_add', '配置参数添加', 'button', '50');
INSERT INTO `system_authority` VALUES ('52', 'dict_edit', '配置参数修改', 'button', '50');
INSERT INTO `system_authority` VALUES ('53', 'dict_bat_delete', '配置参数批量删除', 'button', '50');
INSERT INTO `system_authority` VALUES ('54', 'pay_whitelistIp_manage', '商户IP管理', 'menus', '5');
INSERT INTO `system_authority` VALUES ('55', 'whitelist_ip_add', '添加商户IP信息', 'button', '54');
INSERT INTO `system_authority` VALUES ('56', 'whitelist_ip_edit', '编辑商户IP信息', 'button', '54');
INSERT INTO `system_authority` VALUES ('57', 'whitelist_ip_delete', '批量删除商户IP信息', 'button', '54');

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

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('1', '127.0.0.1', 'admin', 'admin', null, '2018-12-28 18:45:42', 'code_error', null);
INSERT INTO `system_log` VALUES ('2', '127.0.0.1', 'admin', 'admin', null, '2018-12-28 18:45:46', 'password_username_error', null);
INSERT INTO `system_log` VALUES ('3', '127.0.0.1', 'admin', 'admin', null, '2018-12-28 18:48:17', 'login', null);
INSERT INTO `system_log` VALUES ('4', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 10:03:42', 'login', null);
INSERT INTO `system_log` VALUES ('5', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 10:03:45', 'login', null);
INSERT INTO `system_log` VALUES ('6', '127.0.0.1', null, '', null, '2018-12-29 10:15:22', 'logout', null);
INSERT INTO `system_log` VALUES ('7', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 10:16:43', 'login', null);
INSERT INTO `system_log` VALUES ('8', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 10:56:50', 'login', null);
INSERT INTO `system_log` VALUES ('9', '127.0.0.1', null, '', null, '2018-12-29 10:59:58', 'logout', null);
INSERT INTO `system_log` VALUES ('10', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:01:46', 'code_error', null);
INSERT INTO `system_log` VALUES ('11', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:01:50', 'login', null);
INSERT INTO `system_log` VALUES ('12', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:08:00', 'code_error', null);
INSERT INTO `system_log` VALUES ('13', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:08:04', 'login', null);
INSERT INTO `system_log` VALUES ('14', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:20:26', 'code_error', null);
INSERT INTO `system_log` VALUES ('15', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:20:31', 'code_error', null);
INSERT INTO `system_log` VALUES ('16', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 11:20:47', 'login', null);
INSERT INTO `system_log` VALUES ('17', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:10:52', 'code_error', null);
INSERT INTO `system_log` VALUES ('18', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:10:57', 'login', null);
INSERT INTO `system_log` VALUES ('19', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:16:37', 'code_error', null);
INSERT INTO `system_log` VALUES ('20', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:16:41', 'login', null);
INSERT INTO `system_log` VALUES ('21', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:37:43', 'code_error', null);
INSERT INTO `system_log` VALUES ('22', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:37:47', 'code_error', null);
INSERT INTO `system_log` VALUES ('23', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 13:37:52', 'login', null);
INSERT INTO `system_log` VALUES ('24', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 14:02:18', 'code_error', null);
INSERT INTO `system_log` VALUES ('25', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 14:02:23', 'login', null);
INSERT INTO `system_log` VALUES ('26', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 14:08:13', 'code_error', null);
INSERT INTO `system_log` VALUES ('27', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 14:08:18', 'login', null);
INSERT INTO `system_log` VALUES ('28', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 15:57:00', 'code_error', null);
INSERT INTO `system_log` VALUES ('29', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 15:57:05', 'login', null);
INSERT INTO `system_log` VALUES ('30', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 15:58:46', 'code_error', null);
INSERT INTO `system_log` VALUES ('31', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 15:58:55', 'login', null);
INSERT INTO `system_log` VALUES ('32', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:02:31', 'login', null);
INSERT INTO `system_log` VALUES ('33', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:04:13', 'login', null);
INSERT INTO `system_log` VALUES ('34', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:26:00', 'code_error', null);
INSERT INTO `system_log` VALUES ('35', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:26:01', 'login', null);
INSERT INTO `system_log` VALUES ('36', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:26:20', 'login', null);
INSERT INTO `system_log` VALUES ('37', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:26:37', 'code_error', null);
INSERT INTO `system_log` VALUES ('38', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:26:42', 'login', null);
INSERT INTO `system_log` VALUES ('39', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:38:38', 'code_error', null);
INSERT INTO `system_log` VALUES ('40', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:38:42', 'code_error', null);
INSERT INTO `system_log` VALUES ('41', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:38:48', 'login', null);
INSERT INTO `system_log` VALUES ('42', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:42:04', 'login', null);
INSERT INTO `system_log` VALUES ('43', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:42:53', 'code_error', null);
INSERT INTO `system_log` VALUES ('44', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:43:53', 'code_error', null);
INSERT INTO `system_log` VALUES ('45', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:45:22', 'code_error', null);
INSERT INTO `system_log` VALUES ('46', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:45:33', 'login', null);
INSERT INTO `system_log` VALUES ('47', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 16:59:10', 'login', null);
INSERT INTO `system_log` VALUES ('48', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 17:03:10', 'login', null);
INSERT INTO `system_log` VALUES ('49', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:14:39', 'code_error', null);
INSERT INTO `system_log` VALUES ('50', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:14:39', 'code_error', null);
INSERT INTO `system_log` VALUES ('51', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:14:47', 'login', null);
INSERT INTO `system_log` VALUES ('52', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:30:47', 'login', null);
INSERT INTO `system_log` VALUES ('53', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:30:49', 'code_error', null);
INSERT INTO `system_log` VALUES ('54', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:30:55', 'login', null);
INSERT INTO `system_log` VALUES ('55', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 18:47:27', 'login', null);
INSERT INTO `system_log` VALUES ('56', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 19:00:40', 'code_error', null);
INSERT INTO `system_log` VALUES ('57', '127.0.0.1', 'admin', 'admin', null, '2018-12-29 19:00:44', 'login', null);
INSERT INTO `system_log` VALUES ('58', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 09:35:53', 'login', null);
INSERT INTO `system_log` VALUES ('59', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 09:42:39', 'login', null);
INSERT INTO `system_log` VALUES ('60', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 09:46:07', 'code_error', null);
INSERT INTO `system_log` VALUES ('61', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 09:46:11', 'login', null);
INSERT INTO `system_log` VALUES ('62', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 09:52:08', 'login', null);
INSERT INTO `system_log` VALUES ('63', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 10:03:12', 'login', null);
INSERT INTO `system_log` VALUES ('64', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 10:09:02', 'login', null);
INSERT INTO `system_log` VALUES ('65', '127.0.0.1', 'admin', 'admin', null, '2018-12-31 18:06:09', 'login', null);
INSERT INTO `system_log` VALUES ('66', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 09:45:26', 'login', null);
INSERT INTO `system_log` VALUES ('67', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 10:04:18', 'code_error', null);
INSERT INTO `system_log` VALUES ('68', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 10:04:22', 'login', null);
INSERT INTO `system_log` VALUES ('69', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 15:25:09', 'login', null);
INSERT INTO `system_log` VALUES ('70', '127.0.0.1', null, '', null, '2019-01-01 16:57:09', 'code_error', 'admin');
INSERT INTO `system_log` VALUES ('71', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:08:14', 'login', null);
INSERT INTO `system_log` VALUES ('72', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:15:07', 'login', null);
INSERT INTO `system_log` VALUES ('73', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:23:57', 'code_error', null);
INSERT INTO `system_log` VALUES ('74', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:23:57', 'code_error', null);
INSERT INTO `system_log` VALUES ('75', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:24:03', 'login', null);
INSERT INTO `system_log` VALUES ('76', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:33:01', 'code_error', null);
INSERT INTO `system_log` VALUES ('77', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:33:03', 'code_error', null);
INSERT INTO `system_log` VALUES ('78', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:33:07', 'code_error', null);
INSERT INTO `system_log` VALUES ('79', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:33:07', 'code_error', null);
INSERT INTO `system_log` VALUES ('80', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:33:12', 'login', null);
INSERT INTO `system_log` VALUES ('81', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:37:57', 'login', null);
INSERT INTO `system_log` VALUES ('82', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:45:40', 'code_error', null);
INSERT INTO `system_log` VALUES ('83', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:45:44', 'login', null);
INSERT INTO `system_log` VALUES ('84', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:50:18', 'login', null);
INSERT INTO `system_log` VALUES ('85', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:53:14', 'login', null);
INSERT INTO `system_log` VALUES ('86', '127.0.0.1', 'admin', 'admin', null, '2019-01-01 18:59:57', 'login', null);
INSERT INTO `system_log` VALUES ('87', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 09:27:54', 'login', null);
INSERT INTO `system_log` VALUES ('88', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 09:31:47', 'login', null);
INSERT INTO `system_log` VALUES ('89', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 09:36:57', 'login', null);
INSERT INTO `system_log` VALUES ('90', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 09:40:36', 'login', null);
INSERT INTO `system_log` VALUES ('91', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 09:47:04', 'login', null);
INSERT INTO `system_log` VALUES ('92', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 10:10:07', 'login', null);
INSERT INTO `system_log` VALUES ('93', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 10:28:32', 'login', null);
INSERT INTO `system_log` VALUES ('94', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 10:40:02', 'login', null);
INSERT INTO `system_log` VALUES ('95', '169.254.248.248', 'admin', 'admin', null, '2019-01-02 11:35:29', 'login', null);
INSERT INTO `system_log` VALUES ('96', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 15:56:01', 'login', null);
INSERT INTO `system_log` VALUES ('97', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 16:42:34', 'code_error', null);
INSERT INTO `system_log` VALUES ('98', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 16:42:38', 'login', null);
INSERT INTO `system_log` VALUES ('99', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:05:17', 'login', null);
INSERT INTO `system_log` VALUES ('100', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:10:36', 'login', null);
INSERT INTO `system_log` VALUES ('101', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:14:55', 'login', null);
INSERT INTO `system_log` VALUES ('102', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:19:06', 'login', null);
INSERT INTO `system_log` VALUES ('103', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:21:58', 'login', null);
INSERT INTO `system_log` VALUES ('104', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:25:50', 'login', null);
INSERT INTO `system_log` VALUES ('105', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:45:40', 'login', null);
INSERT INTO `system_log` VALUES ('106', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 17:46:47', 'login', null);
INSERT INTO `system_log` VALUES ('107', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 18:31:04', 'login', null);
INSERT INTO `system_log` VALUES ('108', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 18:32:08', 'login', null);
INSERT INTO `system_log` VALUES ('109', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 18:33:08', 'login', null);
INSERT INTO `system_log` VALUES ('110', '127.0.0.1', 'admin', 'admin', null, '2019-01-02 18:50:50', 'login', null);
INSERT INTO `system_log` VALUES ('111', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 09:38:14', 'login', null);
INSERT INTO `system_log` VALUES ('112', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 09:40:37', 'login', null);
INSERT INTO `system_log` VALUES ('113', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 09:42:44', 'login', null);
INSERT INTO `system_log` VALUES ('114', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 10:09:23', 'login', null);
INSERT INTO `system_log` VALUES ('115', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 10:21:22', 'login', null);
INSERT INTO `system_log` VALUES ('116', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 11:44:48', 'code_error', null);
INSERT INTO `system_log` VALUES ('117', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 11:44:54', 'login', null);
INSERT INTO `system_log` VALUES ('118', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:15:08', 'login', null);
INSERT INTO `system_log` VALUES ('119', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:52:13', 'code_error', null);
INSERT INTO `system_log` VALUES ('120', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:52:19', 'code_error', null);
INSERT INTO `system_log` VALUES ('121', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:52:20', 'code_error', null);
INSERT INTO `system_log` VALUES ('122', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:52:24', 'code_error', null);
INSERT INTO `system_log` VALUES ('123', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:52:30', 'login', null);
INSERT INTO `system_log` VALUES ('124', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 13:59:37', 'login', null);
INSERT INTO `system_log` VALUES ('125', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 14:15:16', 'login', null);
INSERT INTO `system_log` VALUES ('126', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 14:24:44', 'code_error', null);
INSERT INTO `system_log` VALUES ('127', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 14:24:48', 'login', null);
INSERT INTO `system_log` VALUES ('128', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 14:29:39', 'login', null);
INSERT INTO `system_log` VALUES ('129', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 14:58:21', 'login', null);
INSERT INTO `system_log` VALUES ('130', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:08:13', 'login', null);
INSERT INTO `system_log` VALUES ('131', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:11:55', 'code_error', null);
INSERT INTO `system_log` VALUES ('132', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:12:00', 'login', null);
INSERT INTO `system_log` VALUES ('133', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:16:55', 'code_error', null);
INSERT INTO `system_log` VALUES ('134', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:17:00', 'login', null);
INSERT INTO `system_log` VALUES ('135', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:24:58', 'login', null);
INSERT INTO `system_log` VALUES ('136', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:27:32', 'login', null);
INSERT INTO `system_log` VALUES ('137', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:42:29', 'login', null);
INSERT INTO `system_log` VALUES ('138', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:56:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('139', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 15:56:54', 'login', null);
INSERT INTO `system_log` VALUES ('140', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 16:55:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('141', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 16:55:56', 'login', null);
INSERT INTO `system_log` VALUES ('142', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 17:06:49', 'login', null);
INSERT INTO `system_log` VALUES ('143', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 17:15:22', 'login', null);
INSERT INTO `system_log` VALUES ('144', '127.0.0.1', 'admin', 'admin', null, '2019-01-03 18:08:21', 'login', null);
INSERT INTO `system_log` VALUES ('145', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 10:01:37', 'login', null);
INSERT INTO `system_log` VALUES ('146', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 11:06:42', 'login', null);
INSERT INTO `system_log` VALUES ('147', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 11:21:00', 'login', null);
INSERT INTO `system_log` VALUES ('148', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 13:13:04', 'code_error', null);
INSERT INTO `system_log` VALUES ('149', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 13:13:11', 'login', null);
INSERT INTO `system_log` VALUES ('150', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 13:18:38', 'code_error', null);
INSERT INTO `system_log` VALUES ('151', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 13:18:43', 'login', null);
INSERT INTO `system_log` VALUES ('152', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 13:55:00', 'login', null);
INSERT INTO `system_log` VALUES ('153', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 14:05:28', 'login', null);
INSERT INTO `system_log` VALUES ('154', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 14:11:21', 'login', null);
INSERT INTO `system_log` VALUES ('155', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 16:55:35', 'login', null);
INSERT INTO `system_log` VALUES ('156', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 17:04:20', 'login', null);
INSERT INTO `system_log` VALUES ('157', '127.0.0.1', 'admin', 'admin', null, '2019-01-07 17:09:26', 'login', null);
INSERT INTO `system_log` VALUES ('158', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 09:49:06', 'code_error', null);
INSERT INTO `system_log` VALUES ('159', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 09:49:09', 'login', null);
INSERT INTO `system_log` VALUES ('160', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 10:23:46', 'login', null);
INSERT INTO `system_log` VALUES ('161', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 10:33:01', 'login', null);
INSERT INTO `system_log` VALUES ('162', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 11:18:51', 'login', null);
INSERT INTO `system_log` VALUES ('163', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 11:28:03', 'login', null);
INSERT INTO `system_log` VALUES ('164', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 11:36:10', 'login', null);
INSERT INTO `system_log` VALUES ('165', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 11:40:06', 'login', null);
INSERT INTO `system_log` VALUES ('166', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 13:28:55', 'code_error', null);
INSERT INTO `system_log` VALUES ('167', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 13:29:00', 'code_error', null);
INSERT INTO `system_log` VALUES ('168', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 13:29:00', 'code_error', null);
INSERT INTO `system_log` VALUES ('169', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 13:29:06', 'login', null);
INSERT INTO `system_log` VALUES ('170', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 13:58:30', 'login', null);
INSERT INTO `system_log` VALUES ('171', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:08:08', 'login', null);
INSERT INTO `system_log` VALUES ('172', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:12:01', 'login', null);
INSERT INTO `system_log` VALUES ('173', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:15:39', 'login', null);
INSERT INTO `system_log` VALUES ('174', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:19:25', 'login', null);
INSERT INTO `system_log` VALUES ('175', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:25:12', 'login', null);
INSERT INTO `system_log` VALUES ('176', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:34:31', 'code_error', null);
INSERT INTO `system_log` VALUES ('177', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:34:38', 'login', null);
INSERT INTO `system_log` VALUES ('178', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:51:46', 'login', null);
INSERT INTO `system_log` VALUES ('179', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 14:58:48', 'login', null);
INSERT INTO `system_log` VALUES ('180', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 15:17:01', 'code_error', null);
INSERT INTO `system_log` VALUES ('181', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 15:17:04', 'code_error', null);
INSERT INTO `system_log` VALUES ('182', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 15:17:10', 'login', null);
INSERT INTO `system_log` VALUES ('183', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 15:19:56', 'login', null);
INSERT INTO `system_log` VALUES ('184', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 15:27:32', 'login', null);
INSERT INTO `system_log` VALUES ('185', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:02:04', 'login', null);
INSERT INTO `system_log` VALUES ('186', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:09:41', 'code_error', null);
INSERT INTO `system_log` VALUES ('187', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:09:44', 'code_error', null);
INSERT INTO `system_log` VALUES ('188', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:09:48', 'code_error', null);
INSERT INTO `system_log` VALUES ('189', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:09:55', 'login', null);
INSERT INTO `system_log` VALUES ('190', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:40:18', 'code_error', null);
INSERT INTO `system_log` VALUES ('191', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:40:18', 'code_error', null);
INSERT INTO `system_log` VALUES ('192', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 16:40:24', 'login', null);
INSERT INTO `system_log` VALUES ('193', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 17:20:41', 'code_error', null);
INSERT INTO `system_log` VALUES ('194', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 17:20:44', 'code_error', null);
INSERT INTO `system_log` VALUES ('195', '127.0.0.1', 'admin', 'admin', null, '2019-01-11 17:20:50', 'login', null);
INSERT INTO `system_log` VALUES ('196', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 09:45:01', 'code_error', null);
INSERT INTO `system_log` VALUES ('197', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 09:45:06', 'login', null);
INSERT INTO `system_log` VALUES ('198', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 09:45:22', 'code_error', null);
INSERT INTO `system_log` VALUES ('199', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 09:45:29', 'login', null);
INSERT INTO `system_log` VALUES ('200', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 10:21:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('201', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 10:21:55', 'code_error', null);
INSERT INTO `system_log` VALUES ('202', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 10:21:59', 'code_error', null);
INSERT INTO `system_log` VALUES ('203', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 10:22:04', 'login', null);
INSERT INTO `system_log` VALUES ('204', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 10:26:36', 'login', null);
INSERT INTO `system_log` VALUES ('205', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:01:48', 'login', null);
INSERT INTO `system_log` VALUES ('206', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:15:06', 'login', null);
INSERT INTO `system_log` VALUES ('207', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:15:06', 'login', null);
INSERT INTO `system_log` VALUES ('208', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:28:49', 'login', null);
INSERT INTO `system_log` VALUES ('209', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:28:52', 'login', null);
INSERT INTO `system_log` VALUES ('210', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:59:34', 'code_error', null);
INSERT INTO `system_log` VALUES ('211', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 11:59:40', 'login', null);
INSERT INTO `system_log` VALUES ('212', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 12:05:36', 'login', null);
INSERT INTO `system_log` VALUES ('213', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 12:14:30', 'login', null);
INSERT INTO `system_log` VALUES ('214', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 12:14:35', 'login', null);
INSERT INTO `system_log` VALUES ('215', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 13:18:40', 'login', null);
INSERT INTO `system_log` VALUES ('216', '210.4.119.50', null, '', null, '2019-01-23 13:25:17', 'logout', null);
INSERT INTO `system_log` VALUES ('217', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 13:26:00', 'login', null);
INSERT INTO `system_log` VALUES ('218', '210.4.119.50', null, '', null, '2019-01-23 13:26:18', 'logout', null);
INSERT INTO `system_log` VALUES ('219', '210.4.119.50', 'ccc001', null, null, '2019-01-23 13:26:26', 'login', null);
INSERT INTO `system_log` VALUES ('220', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 13:57:03', 'login', null);
INSERT INTO `system_log` VALUES ('221', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 14:00:44', 'code_error', null);
INSERT INTO `system_log` VALUES ('222', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 14:00:51', 'login', null);
INSERT INTO `system_log` VALUES ('223', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 16:17:58', 'code_error', null);
INSERT INTO `system_log` VALUES ('224', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 16:18:06', 'code_error', null);
INSERT INTO `system_log` VALUES ('225', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 16:18:15', 'login', null);
INSERT INTO `system_log` VALUES ('226', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 17:16:32', 'login', null);
INSERT INTO `system_log` VALUES ('227', '210.4.119.50', 'admin', 'admin', null, '2019-01-23 18:09:35', 'login', null);
INSERT INTO `system_log` VALUES ('228', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 09:27:07', 'login', null);
INSERT INTO `system_log` VALUES ('229', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 09:35:02', 'login', null);
INSERT INTO `system_log` VALUES ('230', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 10:14:15', 'login', null);
INSERT INTO `system_log` VALUES ('231', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 10:14:15', 'login', null);
INSERT INTO `system_log` VALUES ('232', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 10:56:59', 'login', null);
INSERT INTO `system_log` VALUES ('233', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 11:19:11', 'code_error', null);
INSERT INTO `system_log` VALUES ('234', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 12:10:05', 'login', null);
INSERT INTO `system_log` VALUES ('235', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 12:12:21', 'login', null);
INSERT INTO `system_log` VALUES ('236', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 12:14:54', 'login', null);
INSERT INTO `system_log` VALUES ('237', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 13:14:47', 'login', null);
INSERT INTO `system_log` VALUES ('238', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 15:32:39', 'login', null);
INSERT INTO `system_log` VALUES ('239', '210.4.119.50', 'admin ', 'admin', null, '2019-01-24 15:40:03', 'login', null);
INSERT INTO `system_log` VALUES ('240', '210.4.119.50', null, '', null, '2019-01-24 16:10:05', 'logout', null);
INSERT INTO `system_log` VALUES ('241', '210.4.119.50', 'ccc001', null, null, '2019-01-24 16:10:18', 'code_error', null);
INSERT INTO `system_log` VALUES ('242', '210.4.119.50', 'ccc001', null, null, '2019-01-24 16:10:25', 'code_error', null);
INSERT INTO `system_log` VALUES ('243', '210.4.119.50', 'ccc001', null, null, '2019-01-24 16:10:33', 'code_error', null);
INSERT INTO `system_log` VALUES ('244', '210.4.119.50', 'ccc001', null, null, '2019-01-24 16:10:43', 'login', null);
INSERT INTO `system_log` VALUES ('245', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 16:20:12', 'login', null);
INSERT INTO `system_log` VALUES ('246', '210.4.119.50', null, '', null, '2019-01-24 16:25:23', 'logout', null);
INSERT INTO `system_log` VALUES ('247', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:07:44', 'code_error', null);
INSERT INTO `system_log` VALUES ('248', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:07:51', 'login', null);
INSERT INTO `system_log` VALUES ('249', '210.4.119.50', null, '', null, '2019-01-24 18:24:58', 'logout', null);
INSERT INTO `system_log` VALUES ('250', '210.4.119.50', 'finaceAdmin', null, null, '2019-01-24 18:25:11', 'login', null);
INSERT INTO `system_log` VALUES ('251', '210.4.119.50', null, '', null, '2019-01-24 18:26:01', 'logout', null);
INSERT INTO `system_log` VALUES ('252', '210.4.119.50', 'kfAdmin', null, null, '2019-01-24 18:26:14', 'login', null);
INSERT INTO `system_log` VALUES ('253', '210.4.119.50', null, '', null, '2019-01-24 18:26:29', 'logout', null);
INSERT INTO `system_log` VALUES ('254', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:26:36', 'login', null);
INSERT INTO `system_log` VALUES ('255', '210.4.119.50', null, '', null, '2019-01-24 18:27:29', 'logout', null);
INSERT INTO `system_log` VALUES ('256', '210.4.119.50', 'finaceAdmin', null, null, '2019-01-24 18:27:37', 'login', null);
INSERT INTO `system_log` VALUES ('257', '210.4.119.50', null, '', null, '2019-01-24 18:27:55', 'logout', null);
INSERT INTO `system_log` VALUES ('258', '210.4.119.50', 'kfAdmin', null, null, '2019-01-24 18:28:10', 'login', null);
INSERT INTO `system_log` VALUES ('259', '210.4.119.50', null, '', null, '2019-01-24 18:34:33', 'logout', null);
INSERT INTO `system_log` VALUES ('260', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:34:41', 'code_error', null);
INSERT INTO `system_log` VALUES ('261', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:34:52', 'code_error', null);
INSERT INTO `system_log` VALUES ('262', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:35:13', 'login', null);
INSERT INTO `system_log` VALUES ('263', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-24 18:35:33', 'code_error', null);
INSERT INTO `system_log` VALUES ('264', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-24 18:35:39', 'login', null);
INSERT INTO `system_log` VALUES ('265', '210.4.119.50', null, '', null, '2019-01-24 18:39:40', 'logout', null);
INSERT INTO `system_log` VALUES ('266', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:39:46', 'login', null);
INSERT INTO `system_log` VALUES ('267', '210.4.119.50', 'finaceAdmin', null, null, '2019-01-24 18:39:49', 'login', null);
INSERT INTO `system_log` VALUES ('268', '210.4.119.50', null, '', null, '2019-01-24 18:40:21', 'logout', null);
INSERT INTO `system_log` VALUES ('269', '210.4.119.50', 'admin', 'admin', null, '2019-01-24 18:40:30', 'login', null);
INSERT INTO `system_log` VALUES ('270', '210.4.119.50', null, '', null, '2019-01-24 18:41:28', 'logout', null);
INSERT INTO `system_log` VALUES ('271', '210.4.119.50', 'finaceAdmin', null, null, '2019-01-24 18:41:37', 'login', null);
INSERT INTO `system_log` VALUES ('272', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 09:40:50', 'login', null);
INSERT INTO `system_log` VALUES ('273', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 09:55:01', 'login', null);
INSERT INTO `system_log` VALUES ('274', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 11:12:11', 'login', null);
INSERT INTO `system_log` VALUES ('275', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 14:56:42', 'login', null);
INSERT INTO `system_log` VALUES ('276', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 15:11:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('277', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 15:11:58', 'login', null);
INSERT INTO `system_log` VALUES ('278', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 15:14:51', 'login', null);
INSERT INTO `system_log` VALUES ('279', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 15:41:26', 'login', null);
INSERT INTO `system_log` VALUES ('280', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 15:46:18', 'login', null);
INSERT INTO `system_log` VALUES ('281', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:16:28', 'login', null);
INSERT INTO `system_log` VALUES ('282', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:24:23', 'code_error', null);
INSERT INTO `system_log` VALUES ('283', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:24:29', 'login', null);
INSERT INTO `system_log` VALUES ('284', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:26:41', 'code_error', null);
INSERT INTO `system_log` VALUES ('285', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:26:46', 'login', null);
INSERT INTO `system_log` VALUES ('286', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 16:45:54', 'login', null);
INSERT INTO `system_log` VALUES ('287', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:28:20', 'code_error', null);
INSERT INTO `system_log` VALUES ('288', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:28:24', 'login', null);
INSERT INTO `system_log` VALUES ('289', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:35:24', 'code_error', null);
INSERT INTO `system_log` VALUES ('290', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:35:25', 'code_error', null);
INSERT INTO `system_log` VALUES ('291', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:35:31', 'login', null);
INSERT INTO `system_log` VALUES ('292', '210.4.119.50', null, '', null, '2019-01-25 18:35:48', 'logout', null);
INSERT INTO `system_log` VALUES ('293', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:35:54', 'login', null);
INSERT INTO `system_log` VALUES ('294', '210.4.119.50', null, '', null, '2019-01-25 18:36:17', 'logout', null);
INSERT INTO `system_log` VALUES ('295', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:42:25', 'code_error', null);
INSERT INTO `system_log` VALUES ('296', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:42:31', 'code_error', null);
INSERT INTO `system_log` VALUES ('297', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:42:39', 'login', null);
INSERT INTO `system_log` VALUES ('298', '210.4.119.50', 'admin', 'admin', null, '2019-01-25 18:43:16', 'login', null);
INSERT INTO `system_log` VALUES ('299', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 10:30:04', 'login', null);
INSERT INTO `system_log` VALUES ('300', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-26 10:31:01', 'login', null);
INSERT INTO `system_log` VALUES ('301', '103.67.22.18', 'kfAdmin', null, null, '2019-01-26 11:58:53', 'login', null);
INSERT INTO `system_log` VALUES ('302', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 12:08:25', 'login', null);
INSERT INTO `system_log` VALUES ('303', '103.67.22.18', null, '', null, '2019-01-26 12:30:17', 'logout', null);
INSERT INTO `system_log` VALUES ('304', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-26 12:30:25', 'login', null);
INSERT INTO `system_log` VALUES ('305', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 13:07:32', 'login', null);
INSERT INTO `system_log` VALUES ('306', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-26 13:29:19', 'login', null);
INSERT INTO `system_log` VALUES ('307', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 14:06:02', 'code_error', null);
INSERT INTO `system_log` VALUES ('308', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 14:06:10', 'login', null);
INSERT INTO `system_log` VALUES ('309', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 15:12:02', 'login', null);
INSERT INTO `system_log` VALUES ('310', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 15:20:38', 'code_error', null);
INSERT INTO `system_log` VALUES ('311', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 15:20:46', 'login', null);
INSERT INTO `system_log` VALUES ('312', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 16:16:36', 'code_error', null);
INSERT INTO `system_log` VALUES ('313', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 16:16:40', 'code_error', null);
INSERT INTO `system_log` VALUES ('314', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 16:16:47', 'login', null);
INSERT INTO `system_log` VALUES ('315', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 17:46:58', 'login', null);
INSERT INTO `system_log` VALUES ('316', '210.4.119.50', 'admin', 'admin', null, '2019-01-26 18:55:55', 'login', null);
INSERT INTO `system_log` VALUES ('317', '103.67.22.18', '蓝蓝', '', null, '2019-01-27 13:52:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('318', '103.67.22.18', '蓝蓝', '', null, '2019-01-27 13:52:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('319', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-27 14:53:34', 'login', null);
INSERT INTO `system_log` VALUES ('320', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 09:19:33', 'login', null);
INSERT INTO `system_log` VALUES ('321', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 10:30:49', 'login', null);
INSERT INTO `system_log` VALUES ('322', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 11:12:42', 'code_error', null);
INSERT INTO `system_log` VALUES ('323', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 11:12:50', 'login', null);
INSERT INTO `system_log` VALUES ('324', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 13:30:42', 'login', null);
INSERT INTO `system_log` VALUES ('325', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 14:38:57', 'login', null);
INSERT INTO `system_log` VALUES ('326', '210.4.119.50', 'admin', 'admin', null, '2019-01-28 16:29:42', 'login', null);
INSERT INTO `system_log` VALUES ('327', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 09:26:38', 'code_error', null);
INSERT INTO `system_log` VALUES ('328', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 09:26:44', 'login', null);
INSERT INTO `system_log` VALUES ('329', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 10:36:46', 'code_error', null);
INSERT INTO `system_log` VALUES ('330', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 10:36:52', 'login', null);
INSERT INTO `system_log` VALUES ('331', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 13:50:44', 'login', null);
INSERT INTO `system_log` VALUES ('332', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 15:58:17', 'code_error', null);
INSERT INTO `system_log` VALUES ('333', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 15:58:22', 'login', null);
INSERT INTO `system_log` VALUES ('334', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-29 17:06:57', 'login', null);
INSERT INTO `system_log` VALUES ('335', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 18:00:13', 'code_error', null);
INSERT INTO `system_log` VALUES ('336', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 18:00:20', 'code_error', null);
INSERT INTO `system_log` VALUES ('337', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 18:00:22', 'code_error', null);
INSERT INTO `system_log` VALUES ('338', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 18:00:29', 'login', null);
INSERT INTO `system_log` VALUES ('339', '210.4.119.50', 'admin', 'admin', null, '2019-01-29 18:16:04', 'login', null);
INSERT INTO `system_log` VALUES ('340', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 09:46:15', 'login', null);
INSERT INTO `system_log` VALUES ('341', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 10:33:47', 'login', null);
INSERT INTO `system_log` VALUES ('342', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 11:25:25', 'login', null);
INSERT INTO `system_log` VALUES ('343', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 14:51:30', 'login', null);
INSERT INTO `system_log` VALUES ('344', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-30 15:30:18', 'login', null);
INSERT INTO `system_log` VALUES ('345', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 15:33:18', 'login', null);
INSERT INTO `system_log` VALUES ('346', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 16:50:39', 'login', null);
INSERT INTO `system_log` VALUES ('347', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 17:45:42', 'login', null);
INSERT INTO `system_log` VALUES ('348', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-30 18:03:07', 'login', null);
INSERT INTO `system_log` VALUES ('349', '210.4.119.50', 'admin', 'admin', null, '2019-01-30 18:46:44', 'login', null);
INSERT INTO `system_log` VALUES ('350', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 09:34:03', 'login', null);
INSERT INTO `system_log` VALUES ('351', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 10:13:43', 'code_error', null);
INSERT INTO `system_log` VALUES ('352', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 10:13:47', 'login', null);
INSERT INTO `system_log` VALUES ('353', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 14:00:51', 'code_error', null);
INSERT INTO `system_log` VALUES ('354', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 14:00:57', 'login', null);
INSERT INTO `system_log` VALUES ('355', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-31 14:38:26', 'login', null);
INSERT INTO `system_log` VALUES ('356', '103.67.22.18', 'finaceAdmin', null, null, '2019-01-31 15:46:00', 'login', null);
INSERT INTO `system_log` VALUES ('357', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 16:11:49', 'login', null);
INSERT INTO `system_log` VALUES ('358', '210.4.119.50', 'admin', 'admin', null, '2019-01-31 17:13:26', 'login', null);
INSERT INTO `system_log` VALUES ('359', '103.67.23.18', 'finaceAdmin', null, null, '2019-01-31 19:27:52', 'login', null);
INSERT INTO `system_log` VALUES ('360', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 09:19:09', 'login', null);
INSERT INTO `system_log` VALUES ('361', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 13:38:48', 'login', null);
INSERT INTO `system_log` VALUES ('362', '103.67.22.18', 'finaceAdmin', null, null, '2019-02-01 14:27:53', 'code_error', null);
INSERT INTO `system_log` VALUES ('363', '103.67.22.18', 'finaceAdmin', null, null, '2019-02-01 14:27:58', 'login', null);
INSERT INTO `system_log` VALUES ('364', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 15:09:18', 'login', null);
INSERT INTO `system_log` VALUES ('365', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 17:34:57', 'code_error', null);
INSERT INTO `system_log` VALUES ('366', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 17:35:05', 'login', null);
INSERT INTO `system_log` VALUES ('367', '210.4.119.50', 'admin', 'admin', null, '2019-02-01 18:38:46', 'login', null);
INSERT INTO `system_log` VALUES ('368', '103.67.22.18', 'finaceAdmin', null, null, '2019-02-02 13:10:39', 'login', null);
INSERT INTO `system_log` VALUES ('369', '103.67.22.18', 'finaceAdmin', null, null, '2019-02-02 14:07:03', 'code_error', null);
INSERT INTO `system_log` VALUES ('370', '103.67.22.18', 'finaceAdmin', null, null, '2019-02-02 14:07:09', 'login', null);
INSERT INTO `system_log` VALUES ('371', '210.4.119.50', 'admin', 'admin', null, '2019-02-06 13:27:54', 'code_error', null);
INSERT INTO `system_log` VALUES ('372', '210.4.119.50', 'admin', 'admin', null, '2019-02-06 13:27:58', 'code_error', null);
INSERT INTO `system_log` VALUES ('373', '210.4.119.50', 'admin', 'admin', null, '2019-02-06 13:28:03', 'login', null);
INSERT INTO `system_log` VALUES ('374', '115.84.83.104', 'finaceAdmin', null, null, '2019-02-09 19:53:31', 'login', null);
INSERT INTO `system_log` VALUES ('375', '111.125.108.52', 'admin', 'admin', null, '2019-02-09 22:03:15', 'login', null);
INSERT INTO `system_log` VALUES ('376', '220.199.99.228', 'admin', 'admin', null, '2019-02-09 22:16:35', 'login', null);
INSERT INTO `system_log` VALUES ('377', '111.125.108.52', 'admin', 'admin', null, '2019-02-09 22:33:12', 'code_error', null);
INSERT INTO `system_log` VALUES ('378', '111.125.108.52', 'admin', 'admin', null, '2019-02-09 22:33:17', 'code_error', null);
INSERT INTO `system_log` VALUES ('379', '111.125.108.52', 'admin', 'admin', null, '2019-02-09 22:33:24', 'login', null);
INSERT INTO `system_log` VALUES ('380', '111.125.108.52', 'admin', 'admin', null, '2019-02-10 10:55:30', 'login', null);
INSERT INTO `system_log` VALUES ('381', '111.125.108.52', 'admin', 'admin', null, '2019-02-10 18:28:50', 'login', null);
INSERT INTO `system_log` VALUES ('382', '210.4.119.50', 'admin', 'admin', null, '2019-02-12 13:51:34', 'login', null);
INSERT INTO `system_log` VALUES ('383', '14.119.186.210', 'admin', 'admin', null, '2019-02-12 13:55:03', 'login', null);
INSERT INTO `system_log` VALUES ('384', '210.4.119.50', 'admin', 'admin', null, '2019-02-12 14:10:57', 'login', null);
INSERT INTO `system_log` VALUES ('385', '210.4.119.50', 'admin', 'admin', null, '2019-02-12 17:55:27', 'login', null);
INSERT INTO `system_log` VALUES ('386', '210.4.119.50', 'admin', 'admin', null, '2019-02-13 13:03:27', 'code_error', null);
INSERT INTO `system_log` VALUES ('387', '210.4.119.50', 'admin', 'admin', null, '2019-02-13 13:03:28', 'code_error', null);
INSERT INTO `system_log` VALUES ('388', '210.4.119.50', 'admin', 'admin', null, '2019-02-13 13:03:35', 'code_error', null);
INSERT INTO `system_log` VALUES ('389', '210.4.119.50', 'admin', 'admin', null, '2019-02-13 13:03:39', 'login', null);
INSERT INTO `system_log` VALUES ('390', '210.4.119.50', 'admin', 'admin', null, '2019-02-14 14:36:16', 'login', null);
INSERT INTO `system_log` VALUES ('391', '210.4.119.50', 'admin', 'admin', null, '2019-02-15 10:40:28', 'login', null);
INSERT INTO `system_log` VALUES ('392', '111.125.108.52', 'admin', 'admin', null, '2019-02-17 12:57:55', 'login', null);
INSERT INTO `system_log` VALUES ('393', '111.125.108.52', 'admin', 'admin', null, '2019-02-17 14:23:27', 'login', null);
INSERT INTO `system_log` VALUES ('394', '111.125.108.52', 'admin', 'admin', null, '2019-02-17 15:21:29', 'login', null);
INSERT INTO `system_log` VALUES ('395', '118.251.91.214', 'admin', 'admin', null, '2019-02-17 20:03:05', 'login', null);
INSERT INTO `system_log` VALUES ('396', '210.4.119.50', 'admin', 'admin', null, '2019-02-18 14:24:30', 'login', null);
INSERT INTO `system_log` VALUES ('397', '210.4.119.50', 'admin', 'admin', null, '2019-02-18 15:03:33', 'login', null);
INSERT INTO `system_log` VALUES ('398', '210.4.119.50', 'admin', 'admin', null, '2019-02-18 15:07:43', 'login', null);
INSERT INTO `system_log` VALUES ('399', '210.4.119.50', null, '', null, '2019-02-18 15:10:04', 'logout', null);
INSERT INTO `system_log` VALUES ('400', '210.4.119.50', 'admin', 'admin', null, '2019-02-18 15:10:12', 'login', null);
INSERT INTO `system_log` VALUES ('401', '210.4.119.55', 'admin', 'admin', null, '2019-02-25 11:03:01', 'code_error', null);
INSERT INTO `system_log` VALUES ('402', '210.4.119.55', 'admin', 'admin', null, '2019-02-25 11:03:09', 'login', null);
INSERT INTO `system_log` VALUES ('403', '210.4.119.55', 'admin', 'admin', null, '2019-02-25 12:26:54', 'login', null);
INSERT INTO `system_log` VALUES ('404', '210.4.119.55', 'admin', 'admin', null, '2019-02-25 13:21:56', 'login', null);
INSERT INTO `system_log` VALUES ('405', '210.4.119.55', 'admin', 'admin', null, '2019-02-26 10:08:54', 'code_error', null);
INSERT INTO `system_log` VALUES ('406', '210.4.119.55', 'admin', 'admin', null, '2019-02-26 10:09:01', 'login', null);
INSERT INTO `system_log` VALUES ('407', '210.4.119.55', 'admin', 'admin', null, '2019-02-26 12:28:41', 'login', null);
INSERT INTO `system_log` VALUES ('408', '210.4.119.55', 'admin', 'admin', null, '2019-02-27 13:11:15', 'login', null);
INSERT INTO `system_log` VALUES ('409', '210.4.119.55', 'admin', 'admin', null, '2019-03-04 10:07:49', 'login', null);
INSERT INTO `system_log` VALUES ('410', '223.88.197.227', 'admin', 'admin', null, '2019-03-10 11:32:31', 'password_username_error', null);
INSERT INTO `system_log` VALUES ('411', '223.88.197.227', 'admin', 'admin', null, '2019-03-10 11:32:39', 'login', null);
INSERT INTO `system_log` VALUES ('412', '103.67.22.18', 'finaceAdmin', null, null, '2019-03-11 16:24:36', 'code_error', null);
INSERT INTO `system_log` VALUES ('413', '103.67.22.18', 'finaceAdmin', null, null, '2019-03-11 16:24:43', 'code_error', null);
INSERT INTO `system_log` VALUES ('414', '103.67.22.18', 'finaceAdmin', null, null, '2019-03-11 16:24:50', 'code_error', null);
INSERT INTO `system_log` VALUES ('415', '103.67.22.18', 'finaceAdmin', null, null, '2019-03-11 16:24:59', 'login', null);
INSERT INTO `system_log` VALUES ('416', '210.4.119.55', 'admin', 'admin', null, '2019-03-23 19:00:01', 'login', null);
INSERT INTO `system_log` VALUES ('417', '210.4.119.55', 'admin', 'admin', null, '2019-03-27 16:00:55', 'code_error', null);
INSERT INTO `system_log` VALUES ('418', '210.4.119.55', 'admin', 'admin', null, '2019-03-27 16:01:00', 'login', null);
INSERT INTO `system_log` VALUES ('419', '47.74.222.157', 'finaceAdmin', null, null, '2019-03-28 09:18:53', 'login', null);
INSERT INTO `system_log` VALUES ('420', '47.74.222.157', null, '', null, '2019-03-28 09:20:46', 'logout', null);
INSERT INTO `system_log` VALUES ('421', '47.74.222.157', 'kfAdmin', null, null, '2019-03-28 09:20:58', 'code_error', null);
INSERT INTO `system_log` VALUES ('422', '47.74.222.157', 'kfAdmin', null, null, '2019-03-28 09:21:06', 'login', null);
INSERT INTO `system_log` VALUES ('423', '210.4.119.55', 'admin', 'admin', null, '2019-03-30 12:19:25', 'login', null);
INSERT INTO `system_log` VALUES ('424', '210.4.119.55', 'admin', 'admin', null, '2019-03-30 15:32:03', 'login', null);
INSERT INTO `system_log` VALUES ('425', '210.4.119.55', 'yunding', null, null, '2019-03-30 16:26:46', 'login', null);
INSERT INTO `system_log` VALUES ('426', '111.125.110.34', 'yunding', null, null, '2019-03-30 17:57:39', 'login', null);
INSERT INTO `system_log` VALUES ('427', '111.125.110.34', 'admin', 'admin', null, '2019-03-30 17:58:18', 'login', null);
INSERT INTO `system_log` VALUES ('428', '172.27.158.241', null, '', null, '2019-03-30 18:26:28', 'logout', null);
INSERT INTO `system_log` VALUES ('429', '172.27.158.241', 'yunding', null, null, '2019-03-30 18:26:49', 'login', null);
INSERT INTO `system_log` VALUES ('430', '172.27.158.241', null, '', null, '2019-03-30 18:34:02', 'logout', null);
INSERT INTO `system_log` VALUES ('431', '172.27.158.241', 'admin', 'admin', null, '2019-04-08 21:41:34', 'login', null);
INSERT INTO `system_log` VALUES ('432', '172.27.158.241', 'admin', 'admin', null, '2019-04-09 01:03:05', 'login', null);
INSERT INTO `system_log` VALUES ('433', '172.27.158.241', 'admin', 'admin', null, '2019-05-17 18:39:27', 'login', null);

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

-- ----------------------------
-- Table structure for tb_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_authority`;
CREATE TABLE `tb_role_authority` (
  `tb_role_id` bigint(20) NOT NULL,
  `tb_authority_id` bigint(20) NOT NULL,
  KEY `FK3j4gg6q9e7e0n39y4b6m23o06` (`tb_authority_id`) USING BTREE,
  KEY `FKa15gkmfc9vj85sgbpkxxt722b` (`tb_role_id`) USING BTREE,
  CONSTRAINT `tb_role_authority_ibfk_1` FOREIGN KEY (`tb_role_id`) REFERENCES `system_role` (`id`),
  CONSTRAINT `tb_role_authority_ibfk_2` FOREIGN KEY (`tb_authority_id`) REFERENCES `system_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_authority
-- ----------------------------
INSERT INTO `tb_role_authority` VALUES ('2', '23');
INSERT INTO `tb_role_authority` VALUES ('2', '24');
INSERT INTO `tb_role_authority` VALUES ('2', '25');
INSERT INTO `tb_role_authority` VALUES ('2', '26');
INSERT INTO `tb_role_authority` VALUES ('2', '27');
INSERT INTO `tb_role_authority` VALUES ('2', '28');
INSERT INTO `tb_role_authority` VALUES ('2', '29');
INSERT INTO `tb_role_authority` VALUES ('2', '30');
INSERT INTO `tb_role_authority` VALUES ('2', '31');
INSERT INTO `tb_role_authority` VALUES ('2', '32');
INSERT INTO `tb_role_authority` VALUES ('2', '33');
INSERT INTO `tb_role_authority` VALUES ('2', '34');
INSERT INTO `tb_role_authority` VALUES ('2', '35');
INSERT INTO `tb_role_authority` VALUES ('2', '37');
INSERT INTO `tb_role_authority` VALUES ('2', '38');
INSERT INTO `tb_role_authority` VALUES ('2', '39');
INSERT INTO `tb_role_authority` VALUES ('2', '40');
INSERT INTO `tb_role_authority` VALUES ('2', '41');
INSERT INTO `tb_role_authority` VALUES ('2', '43');
INSERT INTO `tb_role_authority` VALUES ('2', '44');
INSERT INTO `tb_role_authority` VALUES ('2', '46');
INSERT INTO `tb_role_authority` VALUES ('2', '47');
INSERT INTO `tb_role_authority` VALUES ('2', '48');
INSERT INTO `tb_role_authority` VALUES ('2', '49');
INSERT INTO `tb_role_authority` VALUES ('1', '1');
INSERT INTO `tb_role_authority` VALUES ('1', '2');
INSERT INTO `tb_role_authority` VALUES ('1', '3');
INSERT INTO `tb_role_authority` VALUES ('1', '4');
INSERT INTO `tb_role_authority` VALUES ('1', '5');
INSERT INTO `tb_role_authority` VALUES ('1', '6');
INSERT INTO `tb_role_authority` VALUES ('1', '7');
INSERT INTO `tb_role_authority` VALUES ('1', '8');
INSERT INTO `tb_role_authority` VALUES ('1', '9');
INSERT INTO `tb_role_authority` VALUES ('1', '10');
INSERT INTO `tb_role_authority` VALUES ('1', '11');
INSERT INTO `tb_role_authority` VALUES ('1', '12');
INSERT INTO `tb_role_authority` VALUES ('1', '13');
INSERT INTO `tb_role_authority` VALUES ('1', '14');
INSERT INTO `tb_role_authority` VALUES ('1', '15');
INSERT INTO `tb_role_authority` VALUES ('1', '16');
INSERT INTO `tb_role_authority` VALUES ('1', '17');
INSERT INTO `tb_role_authority` VALUES ('1', '18');
INSERT INTO `tb_role_authority` VALUES ('1', '19');
INSERT INTO `tb_role_authority` VALUES ('1', '20');
INSERT INTO `tb_role_authority` VALUES ('1', '21');
INSERT INTO `tb_role_authority` VALUES ('1', '22');
INSERT INTO `tb_role_authority` VALUES ('1', '23');
INSERT INTO `tb_role_authority` VALUES ('1', '24');
INSERT INTO `tb_role_authority` VALUES ('1', '25');
INSERT INTO `tb_role_authority` VALUES ('1', '26');
INSERT INTO `tb_role_authority` VALUES ('1', '27');
INSERT INTO `tb_role_authority` VALUES ('1', '28');
INSERT INTO `tb_role_authority` VALUES ('1', '29');
INSERT INTO `tb_role_authority` VALUES ('1', '30');
INSERT INTO `tb_role_authority` VALUES ('1', '31');
INSERT INTO `tb_role_authority` VALUES ('1', '32');
INSERT INTO `tb_role_authority` VALUES ('1', '33');
INSERT INTO `tb_role_authority` VALUES ('1', '34');
INSERT INTO `tb_role_authority` VALUES ('1', '35');
INSERT INTO `tb_role_authority` VALUES ('1', '36');
INSERT INTO `tb_role_authority` VALUES ('1', '37');
INSERT INTO `tb_role_authority` VALUES ('1', '38');
INSERT INTO `tb_role_authority` VALUES ('1', '39');
INSERT INTO `tb_role_authority` VALUES ('1', '40');
INSERT INTO `tb_role_authority` VALUES ('1', '41');
INSERT INTO `tb_role_authority` VALUES ('1', '42');
INSERT INTO `tb_role_authority` VALUES ('1', '43');
INSERT INTO `tb_role_authority` VALUES ('1', '44');
INSERT INTO `tb_role_authority` VALUES ('1', '45');
INSERT INTO `tb_role_authority` VALUES ('1', '46');
INSERT INTO `tb_role_authority` VALUES ('1', '47');
INSERT INTO `tb_role_authority` VALUES ('1', '48');
INSERT INTO `tb_role_authority` VALUES ('1', '49');
INSERT INTO `tb_role_authority` VALUES ('1', '50');
INSERT INTO `tb_role_authority` VALUES ('1', '51');
INSERT INTO `tb_role_authority` VALUES ('1', '52');
INSERT INTO `tb_role_authority` VALUES ('1', '53');
INSERT INTO `tb_role_authority` VALUES ('1', '54');
INSERT INTO `tb_role_authority` VALUES ('1', '55');
INSERT INTO `tb_role_authority` VALUES ('1', '56');
INSERT INTO `tb_role_authority` VALUES ('1', '57');
INSERT INTO `tb_role_authority` VALUES ('3', '23');
INSERT INTO `tb_role_authority` VALUES ('3', '24');
INSERT INTO `tb_role_authority` VALUES ('3', '25');
INSERT INTO `tb_role_authority` VALUES ('3', '26');
INSERT INTO `tb_role_authority` VALUES ('3', '28');
INSERT INTO `tb_role_authority` VALUES ('3', '46');
INSERT INTO `tb_role_authority` VALUES ('3', '47');
INSERT INTO `tb_role_authority` VALUES ('3', '48');
INSERT INTO `tb_role_authority` VALUES ('3', '49');
