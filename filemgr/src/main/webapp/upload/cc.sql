-- MySQL dump 10.10
--
-- Host: localhost    Database: cc
-- ------------------------------------------------------
-- Server version	5.1.6-alpha-max

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_manager`
--

DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `id` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `pwd` varchar(20) default NULL,
  `type` varchar(20) default NULL,
  `createTime` varchar(25) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_manager`
--


/*!40000 ALTER TABLE `sys_manager` DISABLE KEYS */;
LOCK TABLES `sys_manager` WRITE;
INSERT INTO `sys_manager` VALUES ('SysManager0000000035','','2014-07-01 21:44:59','1','admin','admin','1','1','1'),('SysManager0000000036','','2014-07-01 21:07:10','1','cc','cc','1','2','2'),('SysManager0000000039','','2014-07-01 21:30:46','1','111','1111','1','1','1'),('SysManager0000000040','','2014-07-01 21:31:30','1','111','1111','1','1','1'),('SysManager0000000041','','2014-07-02 11:14:33','1','11','1234','1','1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_manager` ENABLE KEYS */;

--
-- Table structure for table `sys_managerrole`
--

DROP TABLE IF EXISTS `sys_managerrole`;
CREATE TABLE `sys_managerrole` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `managerUuid` varchar(20) default NULL,
  `roleUuid` varchar(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_managerrole`
--


/*!40000 ALTER TABLE `sys_managerrole` DISABLE KEYS */;
LOCK TABLES `sys_managerrole` WRITE;
INSERT INTO `sys_managerrole` VALUES ('mr1','1','1','1','SysManager0000000035','r1'),('mr2','1','1','1','SysManager0000000036','r2');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_managerrole` ENABLE KEYS */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `id` varchar(20) default NULL,
  `name` varchar(30) default NULL,
  `toUrl` varchar(200) default NULL,
  `showIconUrl` varchar(200) default NULL,
  `showIndex` int(11) default NULL,
  `parentMenuUuid` varchar(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_menu`
--


/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
LOCK TABLES `sys_menu` WRITE;
INSERT INTO `sys_menu` VALUES ('SysMenu0000000061','SysManager0000000035','2014-07-03 19:12:40','1','0111','系统管理11','','Xht_tag6.gif',1,'-1'),('SysMenu0000000062','SysManager0000000036','2014-07-02 16:34:58','1','0101','系统管理','','Xht_tb04.gif',1,'SysMenu0000000061'),('SysMenu0000000063','SysManager0000000035','2014-07-03 13:51:45','1','010101','菜单列表2','sysmenu/toList/1/2','Xht_tb05.gif',1,'SysMenu0000000062'),('SysMenu0000000064','SysManager0000000036','2014-07-02 16:37:12','1','010102','系统工作人员列表','sysmanager/toList/1/2','Xht_tb05.gif',2,'SysMenu0000000062'),('SysMenu0000000065','SysManager0000000036','2014-07-02 16:40:03','1','010103','系统角色列表','sysrole/toList/1/2','Xht_tb05.gif',3,'SysMenu0000000062'),('SysMenu0000000066','SysManager0000000036','2014-07-02 16:41:33','1','010104','系统权限列表','syspermit/toList/1/2','Xht_tb05.gif',4,'SysMenu0000000062'),('SysMenu0000000071','SysManager0000000035','2014-07-03 15:18:09','1','5111','5222','5','5',5,'5'),('SysMenu0000000081','SysManager0000000035','2014-07-03 14:51:18','1','666','66','6','6',6,'6');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

--
-- Table structure for table `sys_modules`
--

DROP TABLE IF EXISTS `sys_modules`;
CREATE TABLE `sys_modules` (
  `uuid` varchar(20) NOT NULL default '',
  `moduleId` varchar(100) default NULL,
  `moduleName` varchar(100) default NULL,
  `description` varchar(1000) default NULL,
  `deployIp` varchar(100) default NULL,
  `deployPort` varchar(100) default NULL,
  `interactiveUrl` varchar(100) default NULL,
  `state` varchar(100) default NULL,
  `dependsModules` varchar(1000) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_modules`
--


/*!40000 ALTER TABLE `sys_modules` DISABLE KEYS */;
LOCK TABLES `sys_modules` WRITE;
INSERT INTO `sys_modules` VALUES ('moduls0000000111',NULL,NULL,NULL,'localhost1113','8080111','/sysUser/interactive',NULL,NULL),('moduls0000000161','SysUser',NULL,NULL,'localhost','9080','/sysUser/call',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_modules` ENABLE KEYS */;

--
-- Table structure for table `sys_permit`
--

DROP TABLE IF EXISTS `sys_permit`;
CREATE TABLE `sys_permit` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `name` varchar(30) default NULL,
  `expression` varchar(50) default NULL,
  `businessType` varchar(2) default NULL,
  `businessUuid` varchar(20) default NULL,
  `belongToMenuUuid` varchar(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_permit`
--


/*!40000 ALTER TABLE `sys_permit` DISABLE KEYS */;
LOCK TABLES `sys_permit` WRITE;
INSERT INTO `sys_permit` VALUES ('p2',NULL,'1','1','察看菜单列表','sysback:menu:view','1','SysMenu0000000063','1'),('p3',NULL,'1','1','察看系统管理1','sysback:menu:view','1','SysMenu0000000061','1'),('p4',NULL,'1','1','察看系统管理2','sysback:menu:view','1','SysMenu0000000062','1'),('SysPermit0000000001','','2014-07-02 01:12:00','1','系统菜单的全部操作','sysback:menu:*','1','1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_permit` ENABLE KEYS */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `id` varchar(20) default NULL,
  `name` varchar(50) default NULL,
  `orgUuid` varchar(20) default NULL,
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_role`
--


/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
LOCK TABLES `sys_role` WRITE;
INSERT INTO `sys_role` VALUES ('r1',NULL,'1','1','admin','admin','1','1'),('r2',NULL,'1','1','sys1','sys1','1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

--
-- Table structure for table `sys_rolepermit`
--

DROP TABLE IF EXISTS `sys_rolepermit`;
CREATE TABLE `sys_rolepermit` (
  `uuid` varchar(20) NOT NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(25) default NULL,
  `delFlag` varchar(2) default NULL,
  `roleUuid` varchar(20) default NULL,
  `permitUuid` varchar(20) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sys_rolepermit`
--


/*!40000 ALTER TABLE `sys_rolepermit` DISABLE KEYS */;
LOCK TABLES `sys_rolepermit` WRITE;
INSERT INTO `sys_rolepermit` VALUES ('rp1',NULL,'1','1','r1','SysPermit0000000001'),('rp2',NULL,'1','1','r2','p2'),('rp3',NULL,'1','1','r2','p3'),('rp4',NULL,'1','1','r2','p4');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_rolepermit` ENABLE KEYS */;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `uuid` varchar(20) NOT NULL,
  `userId` varchar(20) default NULL,
  `userName` varchar(50) default NULL,
  `pwd` varchar(20) default NULL,
  `userType` varchar(20) default NULL,
  `oper` varchar(20) default NULL,
  `opeTime` varchar(30) default NULL,
  `delFlag` int(11) default NULL,
  `pc1` varchar(200) default NULL,
  `pc2` varchar(200) default NULL,
  `pc3` varchar(200) default NULL,
  `pc4` varchar(200) default NULL,
  `pc5` varchar(200) default NULL,
  `pc6` varchar(200) default NULL,
  `pc7` varchar(200) default NULL,
  `pc8` varchar(200) default NULL,
  `pc9` varchar(200) default NULL,
  `pc10` varchar(200) default NULL,
  PRIMARY KEY  (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `system_user`
--


/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
LOCK TABLES `system_user` WRITE;
INSERT INTO `system_user` VALUES ('1','admin','12','1',NULL,NULL,NULL,1,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SysUser0000000016','m1','里斯','1',NULL,'','2014-06-12 11:21:12',1,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('SysUser0000000015','cc','张三','1',NULL,'','2014-06-12 11:20:54',1,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `uuid` varchar(20) default NULL,
  `name` varchar(20) default NULL,
  `pwd` varchar(20) default NULL,
  `tel` varchar(20) default NULL,
  `userId` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_user`
--


/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
LOCK TABLES `tbl_user` WRITE;
INSERT INTO `tbl_user` VALUES ('1','12','12','1','12'),('2','2','2','2','2'),('3','3','3','3','3'),('SysUser0000000012','ttt名字',NULL,NULL,NULL),('SysUser0000000021','ttt名字',NULL,NULL,NULL),('SysUser0000000022','5','5','5','155'),('SysUser0000000023','','','','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

--
-- Table structure for table `tbl_uuid`
--

DROP TABLE IF EXISTS `tbl_uuid`;
CREATE TABLE `tbl_uuid` (
  `preKey` varchar(10) NOT NULL default '',
  `uuid` int(11) default NULL,
  PRIMARY KEY  (`preKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_uuid`
--


/*!40000 ALTER TABLE `tbl_uuid` DISABLE KEYS */;
LOCK TABLES `tbl_uuid` WRITE;
INSERT INTO `tbl_uuid` VALUES ('moduls',130),('SysManager',50),('SysMenu',90),('SysPermit',10),('SysUser',30);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbl_uuid` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

