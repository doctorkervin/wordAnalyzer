-- MariaDB dump 10.19  Distrib 10.6.7-MariaDB, for osx10.15 (x86_64)
--
-- Host: localhost    Database: lybank
-- ------------------------------------------------------
-- Server version	10.6.7-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `system_authority`
--

DROP TABLE IF EXISTS `system_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_authority`
--

LOCK TABLES `system_authority` WRITE;
/*!40000 ALTER TABLE `system_authority` DISABLE KEYS */;
INSERT INTO `system_authority` VALUES (1,'manager_manage','管理员管理','menus',NULL),(2,'authority_manage','权限管理','menus',1),(3,'manager_list','管理员列表','menus',1),(4,'role_manage','角色管理','menus',1),(5,'system_mange','系统管理','menus',NULL),(6,'authority_add','添加权限','button',2),(7,'authority_bat_delete','批量删除','button',2),(8,'authority_delete','删除','button',2),(9,'authority_edit','编辑','button',2),(10,'add','增加','button',3),(11,'edit','编辑','button',3),(12,'delete','删除','button',3),(13,'user_bat_delete','批量删除','button',3),(14,'user_add','添加管理员','button',3),(15,'user_delete','删除','button',3),(16,'user_edit','编辑','button',3),(17,'user_status','状态','button',3),(18,'role_bat_delete','批量删除','button',4),(19,'role_add','添加','button',4),(20,'role_edit','编辑','button',4),(21,'role_delete','删除','button',4),(22,'system_log','系统日志','menus',5),(23,'order_manage','订单管理','menus',NULL),(24,'pay_order_manage','支付订单管理','menus',23),(25,'payOrder_view','订单查看详情','button',24),(26,'pay_merchant_manage','商户管理','menus',23),(27,'pay_payChannel_manage','商户渠道关系管理','menus',23),(28,'payMerchant_view','商户查看详情','button',26),(29,'payChannel_view','支付渠道查看详情','button',27),(30,'pay_channel_manage','渠道管理','menus',23),(31,'pay_channelMchInfo_manage','第三方渠道管理','menus',23),(32,'channel_view','渠道查看详情','button',30),(33,'channelMchInfo_view','商户渠道查看详情','button',31),(34,'payMerchant_add','商户添加','button',26),(35,'payMerchant_edit','商户编辑','button',26),(36,'payMerchant_delete','商户批量删除','button',26),(37,'payChannel_add','支付渠道添加','button',27),(38,'payChannel_edit','支付渠道修改','button',27),(39,'payChannel_delete','支付渠道删除','button',27),(40,'channel_add','渠道添加','button',30),(41,'channel_edit','渠道编辑','button',30),(42,'channel_delete','渠道批量删除','button',30),(43,'channelMchInfo_add','商户渠道添加','button',31),(44,'channelMchInfo_edit','商户渠道编辑','button',31),(45,'channelMchInfo_delete','商户渠道批量删除','button',31),(46,'pay_mchNotify_manage','业务通知管理','menus',23),(47,'mchNotify_view','查看商户业务通知信息','button',46),(48,'payOrder_Resend','重新发送通知','button',24),(49,'export_order','导出','button',24),(50,'system_dict','配置参数管理','menus',5),(51,'dict_add','配置参数添加','button',50),(52,'dict_edit','配置参数修改','button',50),(53,'dict_bat_delete','配置参数批量删除','button',50),(54,'pay_whitelistIp_manage','商户IP管理','menus',5),(55,'whitelist_ip_add','添加商户IP信息','button',54),(56,'whitelist_ip_edit','编辑商户IP信息','button',54),(57,'whitelist_ip_delete','批量删除商户IP信息','button',54);
/*!40000 ALTER TABLE `system_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dict`
--

DROP TABLE IF EXISTS `system_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `d_key` varchar(255) DEFAULT NULL,
  `d_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dpg4vx0lmfu3r7xlh6nd3ypt6` (`d_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dict`
--

LOCK TABLES `system_dict` WRITE;
/*!40000 ALTER TABLE `system_dict` DISABLE KEYS */;
INSERT INTO `system_dict` VALUES (1,'clear_switch_day','enable');
/*!40000 ALTER TABLE `system_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_googlev`
--

DROP TABLE IF EXISTS `system_googlev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_googlev`
--

LOCK TABLES `system_googlev` WRITE;
/*!40000 ALTER TABLE `system_googlev` DISABLE KEYS */;
INSERT INTO `system_googlev` VALUES (1,'otpauth://totp/admin?secret=ME55NQD66TMLU27C','ME55NQD66TMLU27C','enable','admin',NULL,NULL),(2,'otpauth://totp/ccc001?secret=DVYSM27MXT4QEJP7','DVYSM27MXT4QEJP7','enable','ccc001',NULL,NULL);
/*!40000 ALTER TABLE `system_googlev` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_log`
--

DROP TABLE IF EXISTS `system_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=520 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_log`
--

LOCK TABLES `system_log` WRITE;
/*!40000 ALTER TABLE `system_log` DISABLE KEYS */;
INSERT INTO `system_log` VALUES (1,'127.0.0.1','admin','admin',NULL,'2018-12-28 18:45:42','code_error',NULL),(2,'127.0.0.1','admin','admin',NULL,'2018-12-28 18:45:46','password_username_error',NULL),(3,'127.0.0.1','admin','admin',NULL,'2018-12-28 18:48:17','login',NULL),(4,'127.0.0.1','admin','admin',NULL,'2018-12-29 10:03:42','login',NULL),(5,'127.0.0.1','admin','admin',NULL,'2018-12-29 10:03:45','login',NULL),(6,'127.0.0.1',NULL,'',NULL,'2018-12-29 10:15:22','logout',NULL),(7,'127.0.0.1','admin','admin',NULL,'2018-12-29 10:16:43','login',NULL),(8,'127.0.0.1','admin','admin',NULL,'2018-12-29 10:56:50','login',NULL),(9,'127.0.0.1',NULL,'',NULL,'2018-12-29 10:59:58','logout',NULL),(10,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:01:46','code_error',NULL),(11,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:01:50','login',NULL),(12,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:08:00','code_error',NULL),(13,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:08:04','login',NULL),(14,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:20:26','code_error',NULL),(15,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:20:31','code_error',NULL),(16,'127.0.0.1','admin','admin',NULL,'2018-12-29 11:20:47','login',NULL),(17,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:10:52','code_error',NULL),(18,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:10:57','login',NULL),(19,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:16:37','code_error',NULL),(20,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:16:41','login',NULL),(21,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:37:43','code_error',NULL),(22,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:37:47','code_error',NULL),(23,'127.0.0.1','admin','admin',NULL,'2018-12-29 13:37:52','login',NULL),(24,'127.0.0.1','admin','admin',NULL,'2018-12-29 14:02:18','code_error',NULL),(25,'127.0.0.1','admin','admin',NULL,'2018-12-29 14:02:23','login',NULL),(26,'127.0.0.1','admin','admin',NULL,'2018-12-29 14:08:13','code_error',NULL),(27,'127.0.0.1','admin','admin',NULL,'2018-12-29 14:08:18','login',NULL),(28,'127.0.0.1','admin','admin',NULL,'2018-12-29 15:57:00','code_error',NULL),(29,'127.0.0.1','admin','admin',NULL,'2018-12-29 15:57:05','login',NULL),(30,'127.0.0.1','admin','admin',NULL,'2018-12-29 15:58:46','code_error',NULL),(31,'127.0.0.1','admin','admin',NULL,'2018-12-29 15:58:55','login',NULL),(32,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:02:31','login',NULL),(33,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:04:13','login',NULL),(34,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:26:00','code_error',NULL),(35,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:26:01','login',NULL),(36,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:26:20','login',NULL),(37,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:26:37','code_error',NULL),(38,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:26:42','login',NULL),(39,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:38:38','code_error',NULL),(40,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:38:42','code_error',NULL),(41,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:38:48','login',NULL),(42,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:42:04','login',NULL),(43,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:42:53','code_error',NULL),(44,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:43:53','code_error',NULL),(45,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:45:22','code_error',NULL),(46,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:45:33','login',NULL),(47,'127.0.0.1','admin','admin',NULL,'2018-12-29 16:59:10','login',NULL),(48,'127.0.0.1','admin','admin',NULL,'2018-12-29 17:03:10','login',NULL),(49,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:14:39','code_error',NULL),(50,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:14:39','code_error',NULL),(51,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:14:47','login',NULL),(52,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:30:47','login',NULL),(53,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:30:49','code_error',NULL),(54,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:30:55','login',NULL),(55,'127.0.0.1','admin','admin',NULL,'2018-12-29 18:47:27','login',NULL),(56,'127.0.0.1','admin','admin',NULL,'2018-12-29 19:00:40','code_error',NULL),(57,'127.0.0.1','admin','admin',NULL,'2018-12-29 19:00:44','login',NULL),(58,'127.0.0.1','admin','admin',NULL,'2018-12-31 09:35:53','login',NULL),(59,'127.0.0.1','admin','admin',NULL,'2018-12-31 09:42:39','login',NULL),(60,'127.0.0.1','admin','admin',NULL,'2018-12-31 09:46:07','code_error',NULL),(61,'127.0.0.1','admin','admin',NULL,'2018-12-31 09:46:11','login',NULL),(62,'127.0.0.1','admin','admin',NULL,'2018-12-31 09:52:08','login',NULL),(63,'127.0.0.1','admin','admin',NULL,'2018-12-31 10:03:12','login',NULL),(64,'127.0.0.1','admin','admin',NULL,'2018-12-31 10:09:02','login',NULL),(65,'127.0.0.1','admin','admin',NULL,'2018-12-31 18:06:09','login',NULL),(66,'127.0.0.1','admin','admin',NULL,'2019-01-01 09:45:26','login',NULL),(67,'127.0.0.1','admin','admin',NULL,'2019-01-01 10:04:18','code_error',NULL),(68,'127.0.0.1','admin','admin',NULL,'2019-01-01 10:04:22','login',NULL),(69,'127.0.0.1','admin','admin',NULL,'2019-01-01 15:25:09','login',NULL),(70,'127.0.0.1',NULL,'',NULL,'2019-01-01 16:57:09','code_error','admin'),(71,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:08:14','login',NULL),(72,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:15:07','login',NULL),(73,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:23:57','code_error',NULL),(74,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:23:57','code_error',NULL),(75,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:24:03','login',NULL),(76,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:33:01','code_error',NULL),(77,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:33:03','code_error',NULL),(78,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:33:07','code_error',NULL),(79,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:33:07','code_error',NULL),(80,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:33:12','login',NULL),(81,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:37:57','login',NULL),(82,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:45:40','code_error',NULL),(83,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:45:44','login',NULL),(84,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:50:18','login',NULL),(85,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:53:14','login',NULL),(86,'127.0.0.1','admin','admin',NULL,'2019-01-01 18:59:57','login',NULL),(87,'127.0.0.1','admin','admin',NULL,'2019-01-02 09:27:54','login',NULL),(88,'127.0.0.1','admin','admin',NULL,'2019-01-02 09:31:47','login',NULL),(89,'127.0.0.1','admin','admin',NULL,'2019-01-02 09:36:57','login',NULL),(90,'127.0.0.1','admin','admin',NULL,'2019-01-02 09:40:36','login',NULL),(91,'127.0.0.1','admin','admin',NULL,'2019-01-02 09:47:04','login',NULL),(92,'127.0.0.1','admin','admin',NULL,'2019-01-02 10:10:07','login',NULL),(93,'127.0.0.1','admin','admin',NULL,'2019-01-02 10:28:32','login',NULL),(94,'127.0.0.1','admin','admin',NULL,'2019-01-02 10:40:02','login',NULL),(95,'169.254.248.248','admin','admin',NULL,'2019-01-02 11:35:29','login',NULL),(96,'127.0.0.1','admin','admin',NULL,'2019-01-02 15:56:01','login',NULL),(97,'127.0.0.1','admin','admin',NULL,'2019-01-02 16:42:34','code_error',NULL),(98,'127.0.0.1','admin','admin',NULL,'2019-01-02 16:42:38','login',NULL),(99,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:05:17','login',NULL),(100,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:10:36','login',NULL),(101,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:14:55','login',NULL),(102,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:19:06','login',NULL),(103,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:21:58','login',NULL),(104,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:25:50','login',NULL),(105,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:45:40','login',NULL),(106,'127.0.0.1','admin','admin',NULL,'2019-01-02 17:46:47','login',NULL),(107,'127.0.0.1','admin','admin',NULL,'2019-01-02 18:31:04','login',NULL),(108,'127.0.0.1','admin','admin',NULL,'2019-01-02 18:32:08','login',NULL),(109,'127.0.0.1','admin','admin',NULL,'2019-01-02 18:33:08','login',NULL),(110,'127.0.0.1','admin','admin',NULL,'2019-01-02 18:50:50','login',NULL),(111,'127.0.0.1','admin','admin',NULL,'2019-01-03 09:38:14','login',NULL),(112,'127.0.0.1','admin','admin',NULL,'2019-01-03 09:40:37','login',NULL),(113,'127.0.0.1','admin','admin',NULL,'2019-01-03 09:42:44','login',NULL),(114,'127.0.0.1','admin','admin',NULL,'2019-01-03 10:09:23','login',NULL),(115,'127.0.0.1','admin','admin',NULL,'2019-01-03 10:21:22','login',NULL),(116,'127.0.0.1','admin','admin',NULL,'2019-01-03 11:44:48','code_error',NULL),(117,'127.0.0.1','admin','admin',NULL,'2019-01-03 11:44:54','login',NULL),(118,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:15:08','login',NULL),(119,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:52:13','code_error',NULL),(120,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:52:19','code_error',NULL),(121,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:52:20','code_error',NULL),(122,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:52:24','code_error',NULL),(123,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:52:30','login',NULL),(124,'127.0.0.1','admin','admin',NULL,'2019-01-03 13:59:37','login',NULL),(125,'127.0.0.1','admin','admin',NULL,'2019-01-03 14:15:16','login',NULL),(126,'127.0.0.1','admin','admin',NULL,'2019-01-03 14:24:44','code_error',NULL),(127,'127.0.0.1','admin','admin',NULL,'2019-01-03 14:24:48','login',NULL),(128,'127.0.0.1','admin','admin',NULL,'2019-01-03 14:29:39','login',NULL),(129,'127.0.0.1','admin','admin',NULL,'2019-01-03 14:58:21','login',NULL),(130,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:08:13','login',NULL),(131,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:11:55','code_error',NULL),(132,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:12:00','login',NULL),(133,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:16:55','code_error',NULL),(134,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:17:00','login',NULL),(135,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:24:58','login',NULL),(136,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:27:32','login',NULL),(137,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:42:29','login',NULL),(138,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:56:51','code_error',NULL),(139,'127.0.0.1','admin','admin',NULL,'2019-01-03 15:56:54','login',NULL),(140,'127.0.0.1','admin','admin',NULL,'2019-01-03 16:55:51','code_error',NULL),(141,'127.0.0.1','admin','admin',NULL,'2019-01-03 16:55:56','login',NULL),(142,'127.0.0.1','admin','admin',NULL,'2019-01-03 17:06:49','login',NULL),(143,'127.0.0.1','admin','admin',NULL,'2019-01-03 17:15:22','login',NULL),(144,'127.0.0.1','admin','admin',NULL,'2019-01-03 18:08:21','login',NULL),(145,'127.0.0.1','admin','admin',NULL,'2019-01-07 10:01:37','login',NULL),(146,'127.0.0.1','admin','admin',NULL,'2019-01-07 11:06:42','login',NULL),(147,'127.0.0.1','admin','admin',NULL,'2019-01-07 11:21:00','login',NULL),(148,'127.0.0.1','admin','admin',NULL,'2019-01-07 13:13:04','code_error',NULL),(149,'127.0.0.1','admin','admin',NULL,'2019-01-07 13:13:11','login',NULL),(150,'127.0.0.1','admin','admin',NULL,'2019-01-07 13:18:38','code_error',NULL),(151,'127.0.0.1','admin','admin',NULL,'2019-01-07 13:18:43','login',NULL),(152,'127.0.0.1','admin','admin',NULL,'2019-01-07 13:55:00','login',NULL),(153,'127.0.0.1','admin','admin',NULL,'2019-01-07 14:05:28','login',NULL),(154,'127.0.0.1','admin','admin',NULL,'2019-01-07 14:11:21','login',NULL),(155,'127.0.0.1','admin','admin',NULL,'2019-01-07 16:55:35','login',NULL),(156,'127.0.0.1','admin','admin',NULL,'2019-01-07 17:04:20','login',NULL),(157,'127.0.0.1','admin','admin',NULL,'2019-01-07 17:09:26','login',NULL),(158,'127.0.0.1','admin','admin',NULL,'2019-01-11 09:49:06','code_error',NULL),(159,'127.0.0.1','admin','admin',NULL,'2019-01-11 09:49:09','login',NULL),(160,'127.0.0.1','admin','admin',NULL,'2019-01-11 10:23:46','login',NULL),(161,'127.0.0.1','admin','admin',NULL,'2019-01-11 10:33:01','login',NULL),(162,'127.0.0.1','admin','admin',NULL,'2019-01-11 11:18:51','login',NULL),(163,'127.0.0.1','admin','admin',NULL,'2019-01-11 11:28:03','login',NULL),(164,'127.0.0.1','admin','admin',NULL,'2019-01-11 11:36:10','login',NULL),(165,'127.0.0.1','admin','admin',NULL,'2019-01-11 11:40:06','login',NULL),(166,'127.0.0.1','admin','admin',NULL,'2019-01-11 13:28:55','code_error',NULL),(167,'127.0.0.1','admin','admin',NULL,'2019-01-11 13:29:00','code_error',NULL),(168,'127.0.0.1','admin','admin',NULL,'2019-01-11 13:29:00','code_error',NULL),(169,'127.0.0.1','admin','admin',NULL,'2019-01-11 13:29:06','login',NULL),(170,'127.0.0.1','admin','admin',NULL,'2019-01-11 13:58:30','login',NULL),(171,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:08:08','login',NULL),(172,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:12:01','login',NULL),(173,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:15:39','login',NULL),(174,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:19:25','login',NULL),(175,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:25:12','login',NULL),(176,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:34:31','code_error',NULL),(177,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:34:38','login',NULL),(178,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:51:46','login',NULL),(179,'127.0.0.1','admin','admin',NULL,'2019-01-11 14:58:48','login',NULL),(180,'127.0.0.1','admin','admin',NULL,'2019-01-11 15:17:01','code_error',NULL),(181,'127.0.0.1','admin','admin',NULL,'2019-01-11 15:17:04','code_error',NULL),(182,'127.0.0.1','admin','admin',NULL,'2019-01-11 15:17:10','login',NULL),(183,'127.0.0.1','admin','admin',NULL,'2019-01-11 15:19:56','login',NULL),(184,'127.0.0.1','admin','admin',NULL,'2019-01-11 15:27:32','login',NULL),(185,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:02:04','login',NULL),(186,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:09:41','code_error',NULL),(187,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:09:44','code_error',NULL),(188,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:09:48','code_error',NULL),(189,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:09:55','login',NULL),(190,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:40:18','code_error',NULL),(191,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:40:18','code_error',NULL),(192,'127.0.0.1','admin','admin',NULL,'2019-01-11 16:40:24','login',NULL),(193,'127.0.0.1','admin','admin',NULL,'2019-01-11 17:20:41','code_error',NULL),(194,'127.0.0.1','admin','admin',NULL,'2019-01-11 17:20:44','code_error',NULL),(195,'127.0.0.1','admin','admin',NULL,'2019-01-11 17:20:50','login',NULL),(196,'210.4.119.50','admin','admin',NULL,'2019-01-23 09:45:01','code_error',NULL),(197,'210.4.119.50','admin','admin',NULL,'2019-01-23 09:45:06','login',NULL),(198,'210.4.119.50','admin','admin',NULL,'2019-01-23 09:45:22','code_error',NULL),(199,'210.4.119.50','admin','admin',NULL,'2019-01-23 09:45:29','login',NULL),(200,'210.4.119.50','admin','admin',NULL,'2019-01-23 10:21:51','code_error',NULL),(201,'210.4.119.50','admin','admin',NULL,'2019-01-23 10:21:55','code_error',NULL),(202,'210.4.119.50','admin','admin',NULL,'2019-01-23 10:21:59','code_error',NULL),(203,'210.4.119.50','admin','admin',NULL,'2019-01-23 10:22:04','login',NULL),(204,'210.4.119.50','admin','admin',NULL,'2019-01-23 10:26:36','login',NULL),(205,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:01:48','login',NULL),(206,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:15:06','login',NULL),(207,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:15:06','login',NULL),(208,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:28:49','login',NULL),(209,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:28:52','login',NULL),(210,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:59:34','code_error',NULL),(211,'210.4.119.50','admin','admin',NULL,'2019-01-23 11:59:40','login',NULL),(212,'210.4.119.50','admin','admin',NULL,'2019-01-23 12:05:36','login',NULL),(213,'210.4.119.50','admin','admin',NULL,'2019-01-23 12:14:30','login',NULL),(214,'210.4.119.50','admin','admin',NULL,'2019-01-23 12:14:35','login',NULL),(215,'210.4.119.50','admin','admin',NULL,'2019-01-23 13:18:40','login',NULL),(216,'210.4.119.50',NULL,'',NULL,'2019-01-23 13:25:17','logout',NULL),(217,'210.4.119.50','admin','admin',NULL,'2019-01-23 13:26:00','login',NULL),(218,'210.4.119.50',NULL,'',NULL,'2019-01-23 13:26:18','logout',NULL),(219,'210.4.119.50','ccc001',NULL,NULL,'2019-01-23 13:26:26','login',NULL),(220,'210.4.119.50','admin','admin',NULL,'2019-01-23 13:57:03','login',NULL),(221,'210.4.119.50','admin','admin',NULL,'2019-01-23 14:00:44','code_error',NULL),(222,'210.4.119.50','admin','admin',NULL,'2019-01-23 14:00:51','login',NULL),(223,'210.4.119.50','admin','admin',NULL,'2019-01-23 16:17:58','code_error',NULL),(224,'210.4.119.50','admin','admin',NULL,'2019-01-23 16:18:06','code_error',NULL),(225,'210.4.119.50','admin','admin',NULL,'2019-01-23 16:18:15','login',NULL),(226,'210.4.119.50','admin','admin',NULL,'2019-01-23 17:16:32','login',NULL),(227,'210.4.119.50','admin','admin',NULL,'2019-01-23 18:09:35','login',NULL),(228,'210.4.119.50','admin','admin',NULL,'2019-01-24 09:27:07','login',NULL),(229,'210.4.119.50','admin','admin',NULL,'2019-01-24 09:35:02','login',NULL),(230,'210.4.119.50','admin','admin',NULL,'2019-01-24 10:14:15','login',NULL),(231,'210.4.119.50','admin','admin',NULL,'2019-01-24 10:14:15','login',NULL),(232,'210.4.119.50','admin','admin',NULL,'2019-01-24 10:56:59','login',NULL),(233,'210.4.119.50','admin','admin',NULL,'2019-01-24 11:19:11','code_error',NULL),(234,'210.4.119.50','admin','admin',NULL,'2019-01-24 12:10:05','login',NULL),(235,'210.4.119.50','admin','admin',NULL,'2019-01-24 12:12:21','login',NULL),(236,'210.4.119.50','admin','admin',NULL,'2019-01-24 12:14:54','login',NULL),(237,'210.4.119.50','admin','admin',NULL,'2019-01-24 13:14:47','login',NULL),(238,'210.4.119.50','admin','admin',NULL,'2019-01-24 15:32:39','login',NULL),(239,'210.4.119.50','admin ','admin',NULL,'2019-01-24 15:40:03','login',NULL),(240,'210.4.119.50',NULL,'',NULL,'2019-01-24 16:10:05','logout',NULL),(241,'210.4.119.50','ccc001',NULL,NULL,'2019-01-24 16:10:18','code_error',NULL),(242,'210.4.119.50','ccc001',NULL,NULL,'2019-01-24 16:10:25','code_error',NULL),(243,'210.4.119.50','ccc001',NULL,NULL,'2019-01-24 16:10:33','code_error',NULL),(244,'210.4.119.50','ccc001',NULL,NULL,'2019-01-24 16:10:43','login',NULL),(245,'210.4.119.50','admin','admin',NULL,'2019-01-24 16:20:12','login',NULL),(246,'210.4.119.50',NULL,'',NULL,'2019-01-24 16:25:23','logout',NULL),(247,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:07:44','code_error',NULL),(248,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:07:51','login',NULL),(249,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:24:58','logout',NULL),(250,'210.4.119.50','finaceAdmin',NULL,NULL,'2019-01-24 18:25:11','login',NULL),(251,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:26:01','logout',NULL),(252,'210.4.119.50','kfAdmin',NULL,NULL,'2019-01-24 18:26:14','login',NULL),(253,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:26:29','logout',NULL),(254,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:26:36','login',NULL),(255,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:27:29','logout',NULL),(256,'210.4.119.50','finaceAdmin',NULL,NULL,'2019-01-24 18:27:37','login',NULL),(257,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:27:55','logout',NULL),(258,'210.4.119.50','kfAdmin',NULL,NULL,'2019-01-24 18:28:10','login',NULL),(259,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:34:33','logout',NULL),(260,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:34:41','code_error',NULL),(261,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:34:52','code_error',NULL),(262,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:35:13','login',NULL),(263,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-24 18:35:33','code_error',NULL),(264,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-24 18:35:39','login',NULL),(265,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:39:40','logout',NULL),(266,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:39:46','login',NULL),(267,'210.4.119.50','finaceAdmin',NULL,NULL,'2019-01-24 18:39:49','login',NULL),(268,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:40:21','logout',NULL),(269,'210.4.119.50','admin','admin',NULL,'2019-01-24 18:40:30','login',NULL),(270,'210.4.119.50',NULL,'',NULL,'2019-01-24 18:41:28','logout',NULL),(271,'210.4.119.50','finaceAdmin',NULL,NULL,'2019-01-24 18:41:37','login',NULL),(272,'210.4.119.50','admin','admin',NULL,'2019-01-25 09:40:50','login',NULL),(273,'210.4.119.50','admin','admin',NULL,'2019-01-25 09:55:01','login',NULL),(274,'210.4.119.50','admin','admin',NULL,'2019-01-25 11:12:11','login',NULL),(275,'210.4.119.50','admin','admin',NULL,'2019-01-25 14:56:42','login',NULL),(276,'210.4.119.50','admin','admin',NULL,'2019-01-25 15:11:51','code_error',NULL),(277,'210.4.119.50','admin','admin',NULL,'2019-01-25 15:11:58','login',NULL),(278,'210.4.119.50','admin','admin',NULL,'2019-01-25 15:14:51','login',NULL),(279,'210.4.119.50','admin','admin',NULL,'2019-01-25 15:41:26','login',NULL),(280,'210.4.119.50','admin','admin',NULL,'2019-01-25 15:46:18','login',NULL),(281,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:16:28','login',NULL),(282,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:24:23','code_error',NULL),(283,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:24:29','login',NULL),(284,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:26:41','code_error',NULL),(285,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:26:46','login',NULL),(286,'210.4.119.50','admin','admin',NULL,'2019-01-25 16:45:54','login',NULL),(287,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:28:20','code_error',NULL),(288,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:28:24','login',NULL),(289,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:35:24','code_error',NULL),(290,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:35:25','code_error',NULL),(291,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:35:31','login',NULL),(292,'210.4.119.50',NULL,'',NULL,'2019-01-25 18:35:48','logout',NULL),(293,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:35:54','login',NULL),(294,'210.4.119.50',NULL,'',NULL,'2019-01-25 18:36:17','logout',NULL),(295,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:42:25','code_error',NULL),(296,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:42:31','code_error',NULL),(297,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:42:39','login',NULL),(298,'210.4.119.50','admin','admin',NULL,'2019-01-25 18:43:16','login',NULL),(299,'210.4.119.50','admin','admin',NULL,'2019-01-26 10:30:04','login',NULL),(300,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-26 10:31:01','login',NULL),(301,'103.67.22.18','kfAdmin',NULL,NULL,'2019-01-26 11:58:53','login',NULL),(302,'210.4.119.50','admin','admin',NULL,'2019-01-26 12:08:25','login',NULL),(303,'103.67.22.18',NULL,'',NULL,'2019-01-26 12:30:17','logout',NULL),(304,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-26 12:30:25','login',NULL),(305,'210.4.119.50','admin','admin',NULL,'2019-01-26 13:07:32','login',NULL),(306,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-26 13:29:19','login',NULL),(307,'210.4.119.50','admin','admin',NULL,'2019-01-26 14:06:02','code_error',NULL),(308,'210.4.119.50','admin','admin',NULL,'2019-01-26 14:06:10','login',NULL),(309,'210.4.119.50','admin','admin',NULL,'2019-01-26 15:12:02','login',NULL),(310,'210.4.119.50','admin','admin',NULL,'2019-01-26 15:20:38','code_error',NULL),(311,'210.4.119.50','admin','admin',NULL,'2019-01-26 15:20:46','login',NULL),(312,'210.4.119.50','admin','admin',NULL,'2019-01-26 16:16:36','code_error',NULL),(313,'210.4.119.50','admin','admin',NULL,'2019-01-26 16:16:40','code_error',NULL),(314,'210.4.119.50','admin','admin',NULL,'2019-01-26 16:16:47','login',NULL),(315,'210.4.119.50','admin','admin',NULL,'2019-01-26 17:46:58','login',NULL),(316,'210.4.119.50','admin','admin',NULL,'2019-01-26 18:55:55','login',NULL),(317,'103.67.22.18','蓝蓝','',NULL,'2019-01-27 13:52:51','code_error',NULL),(318,'103.67.22.18','蓝蓝','',NULL,'2019-01-27 13:52:51','code_error',NULL),(319,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-27 14:53:34','login',NULL),(320,'210.4.119.50','admin','admin',NULL,'2019-01-28 09:19:33','login',NULL),(321,'210.4.119.50','admin','admin',NULL,'2019-01-28 10:30:49','login',NULL),(322,'210.4.119.50','admin','admin',NULL,'2019-01-28 11:12:42','code_error',NULL),(323,'210.4.119.50','admin','admin',NULL,'2019-01-28 11:12:50','login',NULL),(324,'210.4.119.50','admin','admin',NULL,'2019-01-28 13:30:42','login',NULL),(325,'210.4.119.50','admin','admin',NULL,'2019-01-28 14:38:57','login',NULL),(326,'210.4.119.50','admin','admin',NULL,'2019-01-28 16:29:42','login',NULL),(327,'210.4.119.50','admin','admin',NULL,'2019-01-29 09:26:38','code_error',NULL),(328,'210.4.119.50','admin','admin',NULL,'2019-01-29 09:26:44','login',NULL),(329,'210.4.119.50','admin','admin',NULL,'2019-01-29 10:36:46','code_error',NULL),(330,'210.4.119.50','admin','admin',NULL,'2019-01-29 10:36:52','login',NULL),(331,'210.4.119.50','admin','admin',NULL,'2019-01-29 13:50:44','login',NULL),(332,'210.4.119.50','admin','admin',NULL,'2019-01-29 15:58:17','code_error',NULL),(333,'210.4.119.50','admin','admin',NULL,'2019-01-29 15:58:22','login',NULL),(334,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-29 17:06:57','login',NULL),(335,'210.4.119.50','admin','admin',NULL,'2019-01-29 18:00:13','code_error',NULL),(336,'210.4.119.50','admin','admin',NULL,'2019-01-29 18:00:20','code_error',NULL),(337,'210.4.119.50','admin','admin',NULL,'2019-01-29 18:00:22','code_error',NULL),(338,'210.4.119.50','admin','admin',NULL,'2019-01-29 18:00:29','login',NULL),(339,'210.4.119.50','admin','admin',NULL,'2019-01-29 18:16:04','login',NULL),(340,'210.4.119.50','admin','admin',NULL,'2019-01-30 09:46:15','login',NULL),(341,'210.4.119.50','admin','admin',NULL,'2019-01-30 10:33:47','login',NULL),(342,'210.4.119.50','admin','admin',NULL,'2019-01-30 11:25:25','login',NULL),(343,'210.4.119.50','admin','admin',NULL,'2019-01-30 14:51:30','login',NULL),(344,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-30 15:30:18','login',NULL),(345,'210.4.119.50','admin','admin',NULL,'2019-01-30 15:33:18','login',NULL),(346,'210.4.119.50','admin','admin',NULL,'2019-01-30 16:50:39','login',NULL),(347,'210.4.119.50','admin','admin',NULL,'2019-01-30 17:45:42','login',NULL),(348,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-30 18:03:07','login',NULL),(349,'210.4.119.50','admin','admin',NULL,'2019-01-30 18:46:44','login',NULL),(350,'210.4.119.50','admin','admin',NULL,'2019-01-31 09:34:03','login',NULL),(351,'210.4.119.50','admin','admin',NULL,'2019-01-31 10:13:43','code_error',NULL),(352,'210.4.119.50','admin','admin',NULL,'2019-01-31 10:13:47','login',NULL),(353,'210.4.119.50','admin','admin',NULL,'2019-01-31 14:00:51','code_error',NULL),(354,'210.4.119.50','admin','admin',NULL,'2019-01-31 14:00:57','login',NULL),(355,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-31 14:38:26','login',NULL),(356,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-01-31 15:46:00','login',NULL),(357,'210.4.119.50','admin','admin',NULL,'2019-01-31 16:11:49','login',NULL),(358,'210.4.119.50','admin','admin',NULL,'2019-01-31 17:13:26','login',NULL),(359,'103.67.23.18','finaceAdmin',NULL,NULL,'2019-01-31 19:27:52','login',NULL),(360,'210.4.119.50','admin','admin',NULL,'2019-02-01 09:19:09','login',NULL),(361,'210.4.119.50','admin','admin',NULL,'2019-02-01 13:38:48','login',NULL),(362,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-02-01 14:27:53','code_error',NULL),(363,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-02-01 14:27:58','login',NULL),(364,'210.4.119.50','admin','admin',NULL,'2019-02-01 15:09:18','login',NULL),(365,'210.4.119.50','admin','admin',NULL,'2019-02-01 17:34:57','code_error',NULL),(366,'210.4.119.50','admin','admin',NULL,'2019-02-01 17:35:05','login',NULL),(367,'210.4.119.50','admin','admin',NULL,'2019-02-01 18:38:46','login',NULL),(368,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-02-02 13:10:39','login',NULL),(369,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-02-02 14:07:03','code_error',NULL),(370,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-02-02 14:07:09','login',NULL),(371,'210.4.119.50','admin','admin',NULL,'2019-02-06 13:27:54','code_error',NULL),(372,'210.4.119.50','admin','admin',NULL,'2019-02-06 13:27:58','code_error',NULL),(373,'210.4.119.50','admin','admin',NULL,'2019-02-06 13:28:03','login',NULL),(374,'115.84.83.104','finaceAdmin',NULL,NULL,'2019-02-09 19:53:31','login',NULL),(375,'111.125.108.52','admin','admin',NULL,'2019-02-09 22:03:15','login',NULL),(376,'220.199.99.228','admin','admin',NULL,'2019-02-09 22:16:35','login',NULL),(377,'111.125.108.52','admin','admin',NULL,'2019-02-09 22:33:12','code_error',NULL),(378,'111.125.108.52','admin','admin',NULL,'2019-02-09 22:33:17','code_error',NULL),(379,'111.125.108.52','admin','admin',NULL,'2019-02-09 22:33:24','login',NULL),(380,'111.125.108.52','admin','admin',NULL,'2019-02-10 10:55:30','login',NULL),(381,'111.125.108.52','admin','admin',NULL,'2019-02-10 18:28:50','login',NULL),(382,'210.4.119.50','admin','admin',NULL,'2019-02-12 13:51:34','login',NULL),(383,'14.119.186.210','admin','admin',NULL,'2019-02-12 13:55:03','login',NULL),(384,'210.4.119.50','admin','admin',NULL,'2019-02-12 14:10:57','login',NULL),(385,'210.4.119.50','admin','admin',NULL,'2019-02-12 17:55:27','login',NULL),(386,'210.4.119.50','admin','admin',NULL,'2019-02-13 13:03:27','code_error',NULL),(387,'210.4.119.50','admin','admin',NULL,'2019-02-13 13:03:28','code_error',NULL),(388,'210.4.119.50','admin','admin',NULL,'2019-02-13 13:03:35','code_error',NULL),(389,'210.4.119.50','admin','admin',NULL,'2019-02-13 13:03:39','login',NULL),(390,'210.4.119.50','admin','admin',NULL,'2019-02-14 14:36:16','login',NULL),(391,'210.4.119.50','admin','admin',NULL,'2019-02-15 10:40:28','login',NULL),(392,'111.125.108.52','admin','admin',NULL,'2019-02-17 12:57:55','login',NULL),(393,'111.125.108.52','admin','admin',NULL,'2019-02-17 14:23:27','login',NULL),(394,'111.125.108.52','admin','admin',NULL,'2019-02-17 15:21:29','login',NULL),(395,'118.251.91.214','admin','admin',NULL,'2019-02-17 20:03:05','login',NULL),(396,'210.4.119.50','admin','admin',NULL,'2019-02-18 14:24:30','login',NULL),(397,'210.4.119.50','admin','admin',NULL,'2019-02-18 15:03:33','login',NULL),(398,'210.4.119.50','admin','admin',NULL,'2019-02-18 15:07:43','login',NULL),(399,'210.4.119.50',NULL,'',NULL,'2019-02-18 15:10:04','logout',NULL),(400,'210.4.119.50','admin','admin',NULL,'2019-02-18 15:10:12','login',NULL),(401,'210.4.119.55','admin','admin',NULL,'2019-02-25 11:03:01','code_error',NULL),(402,'210.4.119.55','admin','admin',NULL,'2019-02-25 11:03:09','login',NULL),(403,'210.4.119.55','admin','admin',NULL,'2019-02-25 12:26:54','login',NULL),(404,'210.4.119.55','admin','admin',NULL,'2019-02-25 13:21:56','login',NULL),(405,'210.4.119.55','admin','admin',NULL,'2019-02-26 10:08:54','code_error',NULL),(406,'210.4.119.55','admin','admin',NULL,'2019-02-26 10:09:01','login',NULL),(407,'210.4.119.55','admin','admin',NULL,'2019-02-26 12:28:41','login',NULL),(408,'210.4.119.55','admin','admin',NULL,'2019-02-27 13:11:15','login',NULL),(409,'210.4.119.55','admin','admin',NULL,'2019-03-04 10:07:49','login',NULL),(410,'223.88.197.227','admin','admin',NULL,'2019-03-10 11:32:31','password_username_error',NULL),(411,'223.88.197.227','admin','admin',NULL,'2019-03-10 11:32:39','login',NULL),(412,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-03-11 16:24:36','code_error',NULL),(413,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-03-11 16:24:43','code_error',NULL),(414,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-03-11 16:24:50','code_error',NULL),(415,'103.67.22.18','finaceAdmin',NULL,NULL,'2019-03-11 16:24:59','login',NULL),(416,'210.4.119.55','admin','admin',NULL,'2019-03-23 19:00:01','login',NULL),(417,'210.4.119.55','admin','admin',NULL,'2019-03-27 16:00:55','code_error',NULL),(418,'210.4.119.55','admin','admin',NULL,'2019-03-27 16:01:00','login',NULL),(419,'47.74.222.157','finaceAdmin',NULL,NULL,'2019-03-28 09:18:53','login',NULL),(420,'47.74.222.157',NULL,'',NULL,'2019-03-28 09:20:46','logout',NULL),(421,'47.74.222.157','kfAdmin',NULL,NULL,'2019-03-28 09:20:58','code_error',NULL),(422,'47.74.222.157','kfAdmin',NULL,NULL,'2019-03-28 09:21:06','login',NULL),(423,'210.4.119.55','admin','admin',NULL,'2019-03-30 12:19:25','login',NULL),(424,'210.4.119.55','admin','admin',NULL,'2019-03-30 15:32:03','login',NULL),(425,'210.4.119.55','yunding',NULL,NULL,'2019-03-30 16:26:46','login',NULL),(426,'111.125.110.34','yunding',NULL,NULL,'2019-03-30 17:57:39','login',NULL),(427,'111.125.110.34','admin','admin',NULL,'2019-03-30 17:58:18','login',NULL),(428,'172.27.158.241',NULL,'',NULL,'2019-03-30 18:26:28','logout',NULL),(429,'172.27.158.241','yunding',NULL,NULL,'2019-03-30 18:26:49','login',NULL),(430,'172.27.158.241',NULL,'',NULL,'2019-03-30 18:34:02','logout',NULL),(431,'172.27.158.241','admin','admin',NULL,'2019-04-08 21:41:34','login',NULL),(432,'172.27.158.241','admin','admin',NULL,'2019-04-09 01:03:05','login',NULL),(433,'172.27.158.241','admin','admin',NULL,'2019-05-17 18:39:27','login',NULL),(434,'fe80:0:0:0:6f08:caf3:cadc:ccdf%11','admin','admin',NULL,'2022-02-20 14:19:46','login',NULL),(435,'192.168.43.211','admin','admin',NULL,'2022-02-22 22:39:31','login',NULL),(436,'192.168.43.211','admin','admin',NULL,'2022-02-23 18:45:11','code_error',NULL),(437,'192.168.43.211','admin','admin',NULL,'2022-02-23 18:45:23','login',NULL),(438,'192.168.43.211','admin','admin',NULL,'2022-02-23 19:06:53','login',NULL),(439,'192.168.43.211','admin','admin',NULL,'2022-02-23 22:09:37','login',NULL),(440,'192.168.43.211','admin','admin',NULL,'2022-02-23 22:25:29','login',NULL),(441,'192.168.43.211','admin','admin',NULL,'2022-02-24 21:03:14','login',NULL),(442,'192.168.43.211','admin','admin',NULL,'2022-02-24 23:15:50','login',NULL),(443,'192.168.43.211','admin','admin',NULL,'2022-02-25 19:41:33','login',NULL),(444,'192.168.43.211','admin','admin',NULL,'2022-02-25 23:31:03','password_username_error',NULL),(445,'192.168.43.211','admin','admin',NULL,'2022-02-25 23:31:22','code_error',NULL),(446,'192.168.43.211','admin','admin',NULL,'2022-02-25 23:31:32','login',NULL),(447,'192.168.43.211','admin','admin',NULL,'2022-02-26 14:50:53','code_error',NULL),(448,'192.168.43.211','admin','admin',NULL,'2022-02-26 14:51:02','login',NULL),(449,'192.168.43.211','admin','admin',NULL,'2022-02-26 19:34:08','login',NULL),(450,'192.168.43.211','admin','admin',NULL,'2022-02-27 21:11:23','code_error',NULL),(451,'192.168.43.211','admin','admin',NULL,'2022-02-27 21:11:33','login',NULL),(452,'fe80:0:0:0:c5f8:6fec:ee3a:49ff%11','admin','admin',NULL,'2022-03-06 09:26:03','login',NULL),(453,'192.168.43.211','admin','admin',NULL,'2022-03-06 19:46:35','code_error',NULL),(454,'192.168.43.211','admin','admin',NULL,'2022-03-06 19:46:43','login',NULL),(455,'192.168.43.211','admin','admin',NULL,'2022-03-06 20:11:07','login',NULL),(456,'192.168.43.211','admin','admin',NULL,'2022-03-07 20:13:25','login',NULL),(457,'192.168.43.211','admin','admin',NULL,'2022-03-09 21:51:09','code_error',NULL),(458,'192.168.43.211','admin','admin',NULL,'2022-03-09 21:51:29','login',NULL),(459,'192.168.43.211','admin','admin',NULL,'2022-03-09 22:14:35','login',NULL),(460,'192.168.43.211','admin','admin',NULL,'2022-03-09 22:37:39','login',NULL),(461,'192.168.43.211','admin','admin',NULL,'2022-03-09 23:08:06','login',NULL),(462,'192.168.43.211','admin','admin',NULL,'2022-03-09 23:11:19','login',NULL),(463,'192.168.43.211','admin','admin',NULL,'2022-03-10 21:49:00','login',NULL),(464,'192.168.43.211','admin','admin',NULL,'2022-03-10 22:53:09','code_error',NULL),(465,'192.168.43.211','admin','admin',NULL,'2022-03-10 22:53:19','login',NULL),(466,'192.168.43.211','admin','admin',NULL,'2022-03-11 20:14:08','password_username_error',NULL),(467,'192.168.43.211','admin','admin',NULL,'2022-03-11 20:14:20','login',NULL),(468,'192.168.43.211','admin','admin',NULL,'2022-03-11 20:14:20','code_error',NULL),(469,'192.168.43.211','admin','admin',NULL,'2022-03-11 20:48:31','login',NULL),(470,'192.168.43.211','admin','admin',NULL,'2022-03-11 21:09:08','password_username_error',NULL),(471,'192.168.43.211','admin','admin',NULL,'2022-03-11 21:09:22','login',NULL),(472,'172.16.89.57','admin','admin',NULL,'2022-03-12 07:26:20','login',NULL),(473,'192.168.43.211','admin','admin',NULL,'2022-03-12 08:38:23','login',NULL),(474,'192.168.43.211','admin','admin',NULL,'2022-03-12 09:43:30','login',NULL),(475,'192.168.43.211','admin','admin',NULL,'2022-03-12 10:05:22','login',NULL),(476,'172.16.89.57','admin','admin',NULL,'2022-03-12 12:11:03','login',NULL),(477,'192.168.43.211','admin','admin',NULL,'2022-03-12 13:30:20','login',NULL),(478,'192.168.43.211','admin','admin',NULL,'2022-03-12 14:34:43','login',NULL),(479,'192.168.43.211','admin','admin',NULL,'2022-03-12 15:52:15','login',NULL),(480,'192.168.43.211','admin','admin',NULL,'2022-03-12 19:02:05','login',NULL),(481,'192.168.43.211','admin','admin',NULL,'2022-03-13 09:34:49','login',NULL),(482,'fe80:0:0:0:e38a:b317:7b08:b5db%11','admin','admin',NULL,'2022-03-13 13:39:56','login',NULL),(483,'192.168.43.211','admin','admin',NULL,'2022-03-14 20:31:36','login',NULL),(484,'192.168.43.211','admin','admin',NULL,'2022-03-14 20:31:55','logout',NULL),(485,'192.168.43.211','admin','admin',NULL,'2022-03-14 21:09:24','login',NULL),(486,'fe80:0:0:0:e38a:b317:7b08:b5db%11','admin','admin',NULL,'2022-03-17 20:16:23','login',NULL),(487,'fe80:0:0:0:e38a:b317:7b08:b5db%11','admin','admin',NULL,'2022-03-17 20:29:47','login',NULL),(488,'fe80:0:0:0:e38a:b317:7b08:b5db%11','admin','admin',NULL,'2022-03-17 20:51:22','login',NULL),(489,'192.168.43.211','admin','admin',NULL,'2022-03-19 18:22:29','login',NULL),(490,'192.168.43.211','admin','admin',NULL,'2022-03-19 20:07:39','login',NULL),(491,'192.168.43.211','admin','admin',NULL,'2022-03-19 20:52:24','login',NULL),(492,'fe80:0:0:0:850d:45ef:e2d:2aa2%11','admin','admin',NULL,'2022-03-23 13:44:43','login',NULL),(493,'192.168.43.211','admin','admin',NULL,'2022-03-25 15:08:15','login',NULL),(494,'192.168.43.211','admin','admin',NULL,'2022-03-25 15:32:37','login',NULL),(495,'192.168.43.211','admin','admin',NULL,'2022-03-25 16:17:23','login',NULL),(496,'192.168.43.211','admin','admin',NULL,'2022-03-25 16:27:49','login',NULL),(497,'192.168.43.211','admin','admin',NULL,'2022-03-25 16:54:12','login',NULL),(498,'fe80:0:0:0:850d:45ef:e2d:2aa2%11','admin','admin',NULL,'2022-03-25 17:56:44','login',NULL),(499,'192.168.43.211','admin','admin',NULL,'2022-03-25 20:09:27','login',NULL),(500,'192.168.43.211','admin','admin',NULL,'2022-03-25 20:27:18','login',NULL),(501,'192.168.43.211','admin','admin',NULL,'2022-03-25 20:32:08','code_error',NULL),(502,'192.168.43.211','admin','admin',NULL,'2022-03-25 20:32:08','code_error',NULL),(503,'192.168.43.211','admin','admin',NULL,'2022-03-25 20:32:14','login',NULL),(504,'192.168.43.211','admin','admin',NULL,'2022-03-26 10:24:03','login',NULL),(505,'192.168.43.211','admin','admin',NULL,'2022-03-26 10:34:19','login',NULL),(506,'192.168.43.211','admin','admin',NULL,'2022-03-26 13:03:30','password_username_error',NULL),(507,'192.168.43.211','admin','admin',NULL,'2022-03-26 13:03:38','password_username_error',NULL),(508,'192.168.43.211','admin','admin',NULL,'2022-03-26 13:03:39','code_error',NULL),(509,'192.168.43.211','admin','admin',NULL,'2022-03-26 13:03:50','login',NULL),(510,'192.168.43.211','admin','admin',NULL,'2022-03-26 15:44:56','login',NULL),(511,'192.168.43.211','admin','admin',NULL,'2022-03-26 15:47:04','login',NULL),(512,'192.168.43.211','admin','admin',NULL,'2022-03-26 16:30:30','login',NULL),(513,'192.168.43.211','admin','admin',NULL,'2022-03-26 20:06:10','login',NULL),(514,'192.168.43.211','admin','admin',NULL,'2022-03-27 12:56:15','login',NULL),(515,'192.168.43.211','admin','admin',NULL,'2022-03-27 13:14:55','login',NULL),(516,'192.168.43.211','admin','admin',NULL,'2022-03-27 14:23:14','login',NULL),(517,'192.168.43.211','admin','admin',NULL,'2022-03-27 15:25:47','login',NULL),(518,'192.168.43.211','admin','admin',NULL,'2022-03-27 15:31:10','login',NULL),(519,'192.168.43.211','admin','admin',NULL,'2022-03-27 15:44:38','login',NULL);
/*!40000 ALTER TABLE `system_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role`
--

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmmybdc65ty7ml01jaxad1oek` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role`
--

LOCK TABLES `system_role` WRITE;
/*!40000 ALTER TABLE `system_role` DISABLE KEYS */;
INSERT INTO `system_role` VALUES (1,'admin','超级管理员，拥有最高权限，应该所有权限','超级管理员'),(2,'system_code','管理角色','财务管理角色'),(3,'platform_member','平台会员','客服管理');
/*!40000 ALTER TABLE `system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` tinyint(1) DEFAULT 0,
  `account_locked` tinyint(1) DEFAULT 0,
  `create_time` datetime DEFAULT NULL,
  `credentials_expired` tinyint(1) DEFAULT 0,
  `enabled` int(11) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `accountExpired` tinyint(1) DEFAULT 0,
  `accountLocked` tinyint(1) DEFAULT 0,
  `createTime` datetime DEFAULT NULL,
  `credentialsExpired` tinyint(1) DEFAULT 0,
  `loginName` varchar(255) DEFAULT NULL,
  `operaterTime` datetime DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `create_id` bigint(20) DEFAULT NULL,
  `operater_id` bigint(20) DEFAULT NULL,
  `operater_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gxrdsloct47dckt2xxy8cm23p` (`login_name`) USING BTREE,
  UNIQUE KEY `UK_saeyd2ycketdh0rbkjmcb176e` (`loginName`) USING BTREE,
  KEY `FKq7b8naqqe0v3h65j9emkqseeo` (`role_id`) USING BTREE,
  KEY `FKdmdwniw0q6kfe15trbjwgr484` (`parent_id`),
  CONSTRAINT `FKdmdwniw0q6kfe15trbjwgr484` FOREIGN KEY (`parent_id`) REFERENCES `system_user` (`id`),
  CONSTRAINT `system_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`),
  CONSTRAINT `system_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,0,0,'2018-12-28 18:40:38',0,1,'admin','admin','admin','admin','F',NULL,1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(2,0,0,'2019-01-17 11:17:14',0,1,'ccc001',NULL,'$2a$11$pkd7Y9TVn4tulkbT6yYHVuIk19wXoXdbH77DqnKadqy6wf.zcIQtu','','M','2019-01-17 11:17:14',2,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(3,0,0,'2019-01-17 11:18:01',0,1,'ccc002',NULL,'$2a$11$wJcLdqNB54D5QGWTzoOphOvsZMiZNyeHZI0iO8t5qQ/5AMdt/zi.W','','M','2019-01-17 11:18:01',2,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(4,0,0,'2019-01-17 13:58:21',0,1,'ly0001',NULL,'$2a$11$ACNDgaNHtAe0ahsKy0LMgeFNX.In6KZhJ2/cQmUpsF00spV5sEd6G','','M','2019-01-17 13:58:21',3,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(5,0,0,'2019-01-24 18:20:28',0,1,'finaceAdmin',NULL,'$2a$11$ecpWgFE9IU/.stRRjwkkdeb77ydypAfkCSNG/6hV4J1vPcXPmgm4S','qqqqq','M','2019-01-24 18:20:28',2,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(6,0,0,'2019-01-24 18:20:50',0,1,'kfAdmin',NULL,'$2a$11$LKN12VwTTfUfIn4Y0IigcudY8U8oLh4nzaHxluipTptTZoVtuSz/u','qqqqq','M','2019-01-24 18:20:50',3,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,0,0,'2019-03-23 19:02:14',0,1,'quannengzhifu',NULL,'$2a$11$hOwzuTIsc22vMg/D1bymbexSL2P7ab3YST2MQnYdjZ5KQNt/6Ia0G','','M','2019-03-23 19:02:14',3,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(8,0,0,'2019-03-30 16:16:50',0,1,'yunding',NULL,'$2a$11$Va90S0GOBzEBHeanz/FpBOKmGvBUoz/vGpf/UehnFkfbMoPQq/.Se','','M','2019-03-30 16:16:50',3,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(9,0,0,'2019-04-08 23:11:17',0,1,'test',NULL,'$2a$11$SKvq0pJ13eX0d2uRcAmax.w61fUwiXUkSfhl2kGYJqZHn1FHy8l6C','1111','M','2019-04-08 23:11:17',2,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(10,0,0,'2019-04-08 23:17:31',0,1,'test1',NULL,'$2a$11$SSUe1eBgG3713rtb8.tS8egLpQIyUbSAeIo5ty./v.7ViaIk2z/G6','444','M','2019-04-08 23:17:31',1,0,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(11,0,0,'2019-04-09 01:04:08',0,1,'test2',NULL,'$2a$11$MEfr8dcbOB.x9/jJCrmDj.WwjyLQvQ4LJer9/sN0coT80f2nMgSXS','','M','2019-04-09 01:04:08',1,0,0,NULL,0,NULL,NULL,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_whitelist_ip`
--

DROP TABLE IF EXISTS `system_whitelist_ip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_whitelist_ip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `merchant_code` varchar(255) DEFAULT NULL,
  `operater_id` bigint(20) DEFAULT NULL,
  `operater_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_whitelist_ip`
--

LOCK TABLES `system_whitelist_ip` WRITE;
/*!40000 ALTER TABLE `system_whitelist_ip` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_whitelist_ip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_regular_info`
--

DROP TABLE IF EXISTS `t_regular_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_regular_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operater_id` bigint(20) DEFAULT NULL,
  `operater_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_regular_info`
--

LOCK TABLES `t_regular_info` WRITE;
/*!40000 ALTER TABLE `t_regular_info` DISABLE KEYS */;
INSERT INTO `t_regular_info` VALUES (1,NULL,NULL,'IPV4',1,'2022-03-23 14:00:40','match ipv4','1','(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))','IPV4'),(2,1,'2022-03-06 11:01:59','组织机构代码',1,'2022-03-23 14:00:24','组织机构代码','1','^[A-Z0-9]{8}-[A-Z0-9]$','ORGCODE'),(3,1,'2022-03-06 11:07:47','手机',1,'2022-03-23 13:59:40','匹配国内手机号码','1','^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$','MOBILE'),(4,1,'2022-03-12 19:06:57','邮箱',1,'2022-03-23 13:59:24','邮箱模糊匹配','1','^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$','EMAIL'),(5,1,'2022-03-12 19:08:07','国内邮编',1,'2022-03-23 13:59:06','邮编匹配','1','[0-9]\\d{5}(?!\\d)','YOUBIAN'),(6,1,'2022-03-12 19:09:15','身份证',1,'2022-03-23 13:57:40','身份证匹配','1','((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))','IDCARD'),(7,1,'2022-03-12 19:09:56','机构代码',1,'2022-03-23 13:57:09','机构代码匹配','1','^[A-Z0-9]{8}-[A-Z0-9]$','ORGCODE'),(8,1,'2022-03-12 19:12:35','银行卡号',1,'2022-03-23 13:56:25','银行卡号匹配','1','^([1-9]{1})(\\d{14}|\\d{18})$','BANKCARD'),(9,1,'2022-03-12 19:16:35','港澳通行证1',1,'2022-03-23 13:56:04','港澳通行证匹配','1','^[CW]\\d{8}$','HKMAKAO'),(10,1,'2022-03-19 19:13:21','地名',1,'2022-03-19 19:13:21','hanlp自然语言','2','ns',NULL),(11,1,'2022-03-19 19:14:27','中国人名',1,'2022-03-19 19:14:27','hanlp自然人名','2','nr',NULL),(12,1,'2022-03-23 14:02:00','IPV6',1,'2022-03-23 14:02:00','IPV6匹配值','1','^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$','IPV6'),(13,1,'2022-03-23 14:02:58','电话',1,'2022-03-23 14:02:58','电话号码匹配','1','(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)','PHONE'),(14,1,'2022-03-23 14:03:40','性别',1,'2022-03-23 14:03:40','性别','1','^[男|女]{1}$','SEX'),(15,1,'2022-03-23 14:05:13','域名',1,'2022-03-23 14:05:13','地址域名','1','(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]','URL'),(16,1,'2022-03-23 14:05:43','MAC地址',1,'2022-03-23 14:05:43','MAC地址','1','^([0-9a-fA-F]{2})(([/\\s:-][0-9a-fA-F]{2}){5})$','MAC'),(17,1,'2022-03-23 14:06:40','汽车驾照',1,'2022-03-23 14:06:40','汽车驾照','1','([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})','CAR_ID'),(18,1,'2022-03-23 14:07:22','车辆Vin码',1,'2022-03-23 14:07:22','汽车VIN码','1','^[1234567890WERTYUPASDFGHJKLZXCVBNM]{13}[0-9]{4}$','VIN'),(19,1,'2022-03-23 14:08:10','18位营业执照号码',1,'2022-03-23 14:08:10','营业执照号码','1','^(?:(?![IOZSV])[\\dA-Z]){2}\\d{6}(?:(?![IOZSV])[\\dA-Z]){10}$','BUSINESSCODE'),(20,1,'2022-03-23 14:08:48','统一社会信用代码',1,'2022-03-23 14:08:48','统一社会信用代码','1','^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-90-9ABCDEFGHJKLMNPQRTUWXY])$','SOCIETYCODE'),(21,1,'2022-03-23 14:09:37','民族',1,'2022-03-23 14:09:37','民族','2','地方撒嘎嘎可能考虑自然语言','NATION'),(22,1,'2022-03-23 14:10:12','省份',1,'2022-03-23 14:10:12','省份','2','使用自然语言处理','PROVINCE'),(23,1,'2022-03-23 14:11:06','军官证',1,'2022-03-23 14:11:06','军官证件号码','1','[\\u4E00-\\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$','OFFICER_CARD'),(24,1,'2022-03-23 14:12:51','日期',1,'2022-03-23 14:12:51','日期匹配','1','太复杂，直接使用内置的value','DATEALL');
/*!40000 ALTER TABLE `t_regular_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task_info`
--

DROP TABLE IF EXISTS `t_task_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `operater_id` bigint(20) DEFAULT NULL,
  `operater_time` datetime DEFAULT NULL,
  `regular_ids` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `result` longtext DEFAULT NULL,
  `scanner` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task_info`
--

LOCK TABLES `t_task_info` WRITE;
/*!40000 ALTER TABLE `t_task_info` DISABLE KEYS */;
INSERT INTO `t_task_info` VALUES (1,1,'2022-03-26 16:31:44','/var/empty','2','cesfsg',1,'2022-03-26 16:31:48','1,2,15,23','df','扫描文件开始，文件名称：12334.txt>>>>> 开始>>>>>>>>>>扫描规则名：IPV4 结果：123.234.23.23 12.32.11.54 123.23.12.67 121.32.111.54 123.23.12.67 >>>>>>>>>>扫描规则名：域名 结果：https://www.baidu.com http://123.23.12.67:9089/index.html https://123.23.12.67:9000/index.html 扫描文件开始，文件名称：ziranren.txt>>>>> 开始扫描文件开始，文件名称：123.jk>>>>> 开始','pdf',2),(2,1,'2022-03-26 20:08:12','/var/empty','2','ceshi324',1,'2022-03-26 20:10:27','1,3,4,15,23,24','df','扫描文件开始，文件名称：3454.txt>>>>> 开始>>>>>>>>>>扫描规则名：手机 结果：17610098765 15498765555 >>>>>>>>>>扫描规则名：域名 结果：http://www.baidu.com 扫描文件开始，文件名称：12334.txt>>>>> 开始>>>>>>>>>>扫描规则名：IPV4 结果：123.234.23.23 12.32.11.54 123.23.12.67 121.32.111.54 123.23.12.67 >>>>>>>>>>扫描规则名：域名 结果：https://www.baidu.com http://123.23.12.67:9089/index.html https://123.23.12.67:9000/index.html 扫描文件开始，文件名称：ziranren.txt>>>>> 开始扫描文件开始，文件名称：123.jk>>>>> 开始','dis',2),(3,1,'2022-03-26 20:13:38','/var/empty','3','fdsafg',1,'2022-03-26 20:13:59','1,24','df','发现密钥文件：/var/empty/123.jk发现密钥文件：/var/empty/123.jk','23243',2),(4,1,'2022-03-27 12:56:53','/var/empty','3','gdsgfg',1,'2022-03-27 12:59:47','1,23','did','发现密钥文件：/var/empty/123.jk','rew',2),(5,1,'2022-03-27 13:15:49','/var/empty','2','fgsdgd',1,'2022-03-27 13:16:00','1,2,3,4,5,15,23','dis','>>>>>>>>>>扫描规则名：手机 结果：17610098765 15498765555 >>>>>>>>>>扫描规则名：国内邮编 结果：431700 620098 098765 765555 >>>>>>>>>>扫描规则名：域名 结果：http://www.baidu.com >>>>>>>>>>扫描规则名：IPV4 结果：123.234.23.23 12.32.11.54 123.23.12.67 121.32.111.54 123.23.12.67 >>>>>>>>>>扫描规则名：国内邮编 结果：823369 011816 823369 118232 >>>>>>>>>>扫描规则名：域名 结果：https://www.baidu.com http://123.23.12.67:9089/index.html https://123.23.12.67:9000/index.html >>>>>>>>>>扫描规则名：国内邮编 结果：322434 121213 ','342',2);
/*!40000 ALTER TABLE `t_task_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_authority`
--

DROP TABLE IF EXISTS `tb_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_authority` (
  `tb_role_id` bigint(20) NOT NULL,
  `tb_authority_id` bigint(20) NOT NULL,
  KEY `FK3j4gg6q9e7e0n39y4b6m23o06` (`tb_authority_id`) USING BTREE,
  KEY `FKa15gkmfc9vj85sgbpkxxt722b` (`tb_role_id`) USING BTREE,
  CONSTRAINT `tb_role_authority_ibfk_1` FOREIGN KEY (`tb_role_id`) REFERENCES `system_role` (`id`),
  CONSTRAINT `tb_role_authority_ibfk_2` FOREIGN KEY (`tb_authority_id`) REFERENCES `system_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_authority`
--

LOCK TABLES `tb_role_authority` WRITE;
/*!40000 ALTER TABLE `tb_role_authority` DISABLE KEYS */;
INSERT INTO `tb_role_authority` VALUES (2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,37),(2,38),(2,39),(2,40),(2,41),(2,43),(2,44),(2,46),(2,47),(2,48),(2,49),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(1,57),(3,23),(3,24),(3,25),(3,26),(3,28),(3,46),(3,47),(3,48),(3,49);
/*!40000 ALTER TABLE `tb_role_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-04 21:49:20
