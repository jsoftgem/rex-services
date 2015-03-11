CREATE DATABASE  IF NOT EXISTS `rexcoredb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rexcoredb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: rexcoredb
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Temporary table structure for view `customer_latest_market_view`
--

DROP TABLE IF EXISTS `customer_latest_market_view`;
/*!50001 DROP VIEW IF EXISTS `customer_latest_market_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `customer_latest_market_view` (
  `market_potential_segment` tinyint NOT NULL,
  `market_potential` tinyint NOT NULL,
  `war_customer_customer_id` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `flow_group`
--

DROP TABLE IF EXISTS `flow_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `group_icon_uri` varchar(255) DEFAULT NULL,
  `group_index` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `group_title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `UK_ao6yc08108atw8ro1rum71j59` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_group`
--

LOCK TABLES `flow_group` WRITE;
/*!40000 ALTER TABLE `flow_group` DISABLE KEYS */;
INSERT INTO `flow_group` VALUES (2,'2014-11-03 03:11:33',NULL,NULL,'fa fa-wrench',1,'dev',NULL,'Development','2014-12-29 04:01:12'),(3,'2014-11-16 01:28:20',NULL,NULL,'fa fa-key',0,'admin',NULL,'Administration','2014-12-03 03:43:28'),(8,'2015-01-11 23:47:18',NULL,NULL,'fa fa-male',0,'rexmgr',NULL,'Management','2015-01-12 00:53:41'),(9,'2015-01-12 07:05:42',NULL,NULL,'fa fa-database',0,'tables',NULL,'Tables','2015-01-12 07:51:14'),(10,'2015-01-28 16:29:50',NULL,NULL,'octicon octicon-calendar',0,'activity',NULL,'Activity',NULL),(11,'2015-02-06 02:10:22',NULL,NULL,'fa  fa-tachometer',0,'dashboard_group',NULL,'Dashboard','2015-02-06 02:12:04');
/*!40000 ALTER TABLE `flow_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_group_flow_module`
--

DROP TABLE IF EXISTS `flow_group_flow_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_group_flow_module` (
  `flow_group_group_id` bigint(20) NOT NULL,
  `flowModules_module_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_group_group_id`,`flowModules_module_id`),
  UNIQUE KEY `UK_it8pd5ya3m0dukak923eb70t` (`flowModules_module_id`),
  CONSTRAINT `FK_it8pd5ya3m0dukak923eb70t` FOREIGN KEY (`flowModules_module_id`) REFERENCES `flow_module` (`module_id`),
  CONSTRAINT `FK_thjx8fp2247lijv9ut8q5woax` FOREIGN KEY (`flow_group_group_id`) REFERENCES `flow_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_group_flow_module`
--

LOCK TABLES `flow_group_flow_module` WRITE;
/*!40000 ALTER TABLE `flow_group_flow_module` DISABLE KEYS */;
INSERT INTO `flow_group_flow_module` VALUES (2,1),(2,6),(2,7),(2,8),(3,9),(3,10),(3,11),(9,13),(10,20),(10,21),(11,22),(2,23),(11,24),(8,26);
/*!40000 ALTER TABLE `flow_group_flow_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_module`
--

DROP TABLE IF EXISTS `flow_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_module` (
  `module_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `module_glyph` varchar(255) DEFAULT NULL,
  `module_icon` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) NOT NULL,
  `module_title` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `module_task_uri` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `style_uri` varchar(255) DEFAULT NULL,
  `flow_group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `UK_lxa67omnkdpn3xken0awa8unv` (`module_name`),
  KEY `FK_feopv8trfvtfr2rwir1pw2pf4` (`group_id`),
  CONSTRAINT `FK_feopv8trfvtfr2rwir1pw2pf4` FOREIGN KEY (`group_id`) REFERENCES `flow_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_module`
--

LOCK TABLES `flow_module` WRITE;
/*!40000 ALTER TABLE `flow_module` DISABLE KEYS */;
INSERT INTO `flow_module` VALUES (1,NULL,NULL,NULL,'octicon octicon-browser','','page_module','Pages',NULL,'services/flow_task_service/getTask?id=1&active=true','2015-02-26 13:04:26',NULL,NULL,'dev'),(6,'2014-11-11 21:28:18',NULL,NULL,'fa fa-tasks','','task_module','Tasks',NULL,'services/flow_task_service/getTask?id=6&active=true','2015-02-26 13:04:37',NULL,'services/flow_style_query?compId=6&compType=module','dev'),(7,'2014-11-13 08:14:37',NULL,NULL,'fa fa-puzzle-piece','','md_module','Modules',NULL,'services/flow_task_service/getTask?id=7&active=true','2015-02-26 13:04:41',NULL,'services/flow_style_query?compId=7&compType=module','dev'),(8,'2014-11-13 14:05:42',NULL,NULL,'fa fa-heart',NULL,'moduleGroup_module','Module Groups',NULL,'services/flow_task_service/getTask?id=8&active=true','2015-02-26 13:04:45',NULL,'services/flow_style_query?compId=8&compType=module','dev'),(9,'2014-11-16 01:29:41',NULL,NULL,'fa fa-user',NULL,'adm_user_module','Users',NULL,'services/flow_task_service/getTask?name=usr_mgr_task&active=true','2015-02-26 13:04:51',NULL,'services/flow_style_query?compId=9&compType=module','admin'),(10,'2014-11-16 06:33:31',NULL,NULL,'octicon octicon-gist-secret',NULL,'profile_module','Profiles',NULL,'services/flow_task_service/getTask?id=9&active=true','2015-02-26 13:04:56',NULL,'services/flow_style_query?compId=10&compType=module','admin'),(11,'2014-11-17 00:21:32',NULL,NULL,'fa fa-users',NULL,'group_module','Groups',NULL,'services/flow_task_service/getTask?id=10&active=true','2015-02-26 13:05:18',NULL,'services/flow_style_query?compId=11&compType=module','admin'),(12,'2015-01-11 22:46:31',NULL,NULL,'fa fa-cog',NULL,'agent_md','Agents',NULL,'services/flow_task_service/getTask?name=agent_task&active=true','2015-02-26 13:05:23',NULL,'services/flow_style_query?compId=12&compType=module','rexmgr'),(13,'2015-01-12 07:00:27',NULL,NULL,'fa  fa-university',NULL,'school_module','Schools',NULL,'services/flow_task_service/getTask?name=school_task&active=true','2015-02-26 13:05:31',NULL,'services/flow_style_query?compId=13&compType=module','tables'),(14,'2015-01-13 19:45:21',NULL,NULL,'fa fa-globe',NULL,'region_module','Regions',NULL,'services/flow_task_service/getTask?name=region_task&active=true&size=100','2015-02-25 02:11:46',NULL,'services/flow_style_query?compId=14&compType=module','rexmgr'),(15,'2015-01-14 03:29:18',NULL,NULL,'octicon octicon-organization',NULL,'customer_module','Customers',NULL,'services/flow_task_service/getTask?name=customer_task&active=true&size=100','2015-01-19 15:41:30',NULL,'services/flow_style_query?compId=15&compType=module','rexmgr'),(16,'2015-01-19 15:41:11',NULL,NULL,'fa fa-sitemap',NULL,'position_module','Positions',NULL,'services/flow_task_service/getTask?name=position_task&active=true&size=100','2015-01-30 02:48:57',NULL,'services/flow_style_query?compId=16&compType=module','tables'),(17,'2015-01-20 03:52:44',NULL,NULL,'fa fa-star-o',NULL,'level_module','Education Level',NULL,'services/flow_task_service/getTask?name=level_task&active=true&size=100','2015-01-30 02:49:05',NULL,'services/flow_style_query?compId=17&compType=module','tables'),(19,'2015-01-28 16:50:25',NULL,NULL,'octicon octicon-mortar-board',NULL,'school_year_module','School Year',NULL,'services/flow_task_service/getTask?name=school_year_task&active=true&size=100','2015-01-28 16:50:57',NULL,'services/flow_style_query?compId=19&compType=module','activity'),(20,'2015-01-29 03:53:46',NULL,NULL,'fa fa-calendar-o',NULL,'planner_module','Planner',NULL,'services/flow_task_service/getTask?name=planner_task&active=true&size=100&showToolBar=false','2015-01-31 05:22:29',NULL,'services/flow_style_query?compId=20&compType=module','activity'),(21,'2015-01-31 08:31:52',NULL,NULL,'fa fa-bicycle',NULL,'daily_module','Daily',NULL,'services/flow_task_service/getTask?name=daily_task&active=true&size=100','2015-01-31 08:32:10',NULL,'services/flow_style_query?compId=21&compType=module','activity'),(22,'2015-02-06 02:09:18',NULL,NULL,'fa fa-table',NULL,'reports_module_agent','Reports - Agent activity',NULL,'services/flow_task_service/getTask?name=report_task&active=true&size=100&showToolBar=false','2015-02-23 08:10:46',NULL,'services/flow_style_query?compId=22&compType=module','dashboard_group'),(23,'2015-02-19 08:48:40',NULL,NULL,'fa fa-gears',NULL,'setup_module','Setup',NULL,'services/flow_task_service/getTask?name=setup_task&active=true&size=100&showToolBar=false','2015-02-19 08:51:02',NULL,'services/flow_style_query?compId=23&compType=module','dev'),(24,'2015-02-23 01:54:28',NULL,NULL,'fa fa-table',NULL,'reports_module_customer','Reports - Customer summary',NULL,'services/flow_task_service/getTask?name=report_task_customer&active=true','2015-03-07 14:02:28',NULL,'services/flow_style_query?compId=24&compType=module','dashboard_group'),(25,'2015-02-26 12:43:14',NULL,NULL,'fa fa-globe',NULL,'region_manager_module','Region',NULL,'services/flow_task_service/getTask?name=region_manager_task&active=true','2015-02-26 13:08:55',NULL,'services/flow_style_query?compId=25&compType=module','rexmgr'),(26,'2015-03-07 09:45:20',NULL,NULL,'octicon octicon-organization',NULL,'customer_agent_module','Customers',NULL,'services/flow_task_service/getTask?name=customer_agent_task&active=true','2015-03-07 09:58:06',NULL,'services/flow_style_query?compId=26&compType=module','rexmgr');
/*!40000 ALTER TABLE `flow_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_notification`
--

DROP TABLE IF EXISTS `flow_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_notification` (
  `flow_notification_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alarm_type` varchar(15) NOT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `global` bit(1) NOT NULL,
  `group_name` varchar(25) NOT NULL,
  `message` varchar(255) NOT NULL,
  `message_type` varchar(15) NOT NULL,
  `notified` bit(1) NOT NULL,
  `page` varchar(25) DEFAULT NULL,
  `page_param` varchar(15) DEFAULT NULL,
  `profiles` varchar(255) NOT NULL,
  `session_id` bigint(20) DEFAULT NULL,
  `start_dt` datetime NOT NULL,
  `task` varchar(25) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_notification`
--

LOCK TABLES `flow_notification` WRITE;
/*!40000 ALTER TABLE `flow_notification` DISABLE KEYS */;
INSERT INTO `flow_notification` VALUES (1,'notice','2015-03-06 19:39:44',NULL,NULL,'\0','agent_regional_manager','Profile has been updated.','success','','profile_edit','14','agent_regional_manager',NULL,'2015-03-06 19:39:44','edit_profile',NULL,11),(2,'notice','2015-03-07 00:30:09',NULL,NULL,'\0','agent','Profile has been updated.','success','','profile_edit','11','agent',NULL,'2015-03-07 00:30:09','edit_profile',NULL,8),(3,'notice','2015-03-10 02:21:57',NULL,NULL,'\0','agent_general_manager','Profile has been updated.','success','','profile_edit','15','agent_general_manager',NULL,'2015-03-10 02:21:57','edit_profile',NULL,12),(4,'notice','2015-03-10 02:26:01',NULL,NULL,'\0','agent','Profile has been updated.','success','\0','profile_edit','16','agent',NULL,'2015-03-10 02:26:01','edit_profile',NULL,13);
/*!40000 ALTER TABLE `flow_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_page`
--

DROP TABLE IF EXISTS `flow_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_page` (
  `page_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `delete_uri` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `get_uri` varchar(255) DEFAULT NULL,
  `home_url` varchar(255) NOT NULL,
  `page_is_home` bit(1) NOT NULL,
  `page_name` varchar(255) NOT NULL,
  `post_uri` varchar(255) DEFAULT NULL,
  `put_uri` varchar(255) DEFAULT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `page_title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `auto_get_loader` bit(1) DEFAULT NULL,
  `style_uri` varchar(255) DEFAULT NULL,
  `page_link_enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `UK_breras8agpq232unr0vj2nfxf` (`page_name`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_page`
--

LOCK TABLES `flow_page` WRITE;
/*!40000 ALTER TABLE `flow_page` DISABLE KEYS */;
INSERT INTO `flow_page` VALUES (1,'2014-11-03 04:37:11','services/flow_page_crud/',NULL,NULL,'services/flow_page_query','app/flow/Page/page.html','','page_settings',NULL,'services/flow_page_crud/',NULL,'Page','2015-01-04 23:15:17','\0','services/flow_style_query?compId=1&compType=page','\0'),(2,'2014-11-03 13:15:02',NULL,NULL,NULL,'services/flow_module_service/session_modules','app/flow/Menu/home.html','','menu',NULL,NULL,NULL,'Menu','2014-12-05 01:33:20','','services/flow_style_query?compId=2&compType=page','\0'),(3,'2014-11-06 03:56:58','services/flow_page_crud/',NULL,NULL,'services/flow_page_query/getInstance/','app/flow/Page/page_edit.html','\0','page_edit',NULL,'services/flow_page_crud/',NULL,'Edit page','2014-11-16 06:58:55','','services/flow_style_query?compId=3&compType=page',NULL),(4,'2014-11-07 12:43:39',NULL,NULL,NULL,NULL,'app/flow/Page/page_create.html','\0','page_create',NULL,'services/flow_page_crud/',NULL,'New page','2014-11-28 06:08:43','\0','services/flow_style_query?compId=4&compType=page',NULL),(6,'2014-11-10 05:09:20','services/flow_style_crud/',NULL,NULL,'services/flow_style_query','app/flow/Styles/styles.html','','style_settings',NULL,'services/flow_style_crud/',NULL,'Style','2014-11-10 20:47:18','\0','services/flow_style_query?compId=6&compType=page',NULL),(7,'2014-11-10 20:22:32','services/flow_style_crud/',NULL,NULL,'services/flow_style_query/getInstance/','app/flow/Styles/style_edit.html','\0','style_edit',NULL,'services/flow_style_crud/',NULL,'Edit style','2014-11-16 06:59:08','','services/flow_style_query?compId=7&compType=page',NULL),(8,'2014-11-10 20:28:30',NULL,NULL,NULL,'','app/flow/Styles/style_edit.html','\0','style_create',NULL,'services/flow_style_crud/',NULL,'New style','2014-11-14 06:26:42','\0','services/flow_style_query?compId=8&compType=page',NULL),(9,'2014-11-11 13:52:57','services/flow_task_crud/',NULL,NULL,'services/flow_task_query','app/flow/Task/Task.html','','task_settings',NULL,'services/flow_task_crud/',NULL,'Task','2014-12-02 19:52:13','\0','services/flow_style_query?compId=9&compType=page',NULL),(10,'2014-11-11 13:53:54','services/flow_task_crud/',NULL,NULL,'services/flow_task_query/getInstance/','app/flow/Task/task_edit.html','\0','task_edit',NULL,'services/flow_task_crud/',NULL,'Edit task','2014-11-28 05:54:24','','services/flow_style_query?compId=10&compType=page',NULL),(11,'2014-11-11 20:53:14',NULL,NULL,NULL,'','app/flow/Task/task_create.html','\0','task_create',NULL,'services/flow_task_crud/',NULL,'New  task','2014-11-28 05:54:33','\0','services/flow_style_query?compId=11&compType=page',NULL),(13,'2014-11-12 11:51:04','services/flow_module_crud/',NULL,NULL,'services/flow_module_query','app/flow/Module/module.html','','md_settings',NULL,'services/flow_module_crud/',NULL,'Module','2014-11-15 19:50:59','\0','services/flow_style_query?compId=13&compType=page',NULL),(14,'2014-11-12 12:50:25','services/flow_module_crud/',NULL,NULL,'services/flow_module_query/getInstance/','app/flow/Module/module_edit.html','\0','md_edit',NULL,'services/flow_module_crud/',NULL,'Edit module','2014-11-13 17:33:27','','services/flow_style_query?compId=14&compType=page',NULL),(15,'2014-11-12 12:51:52','services/flow_module_crud/',NULL,NULL,'','app/flow/Module/module_create.html','\0','md_create',NULL,'services/flow_module_crud/',NULL,'New module','2014-11-28 06:19:25','\0','services/flow_style_query?compId=15&compType=page',NULL),(16,'2014-11-13 13:44:52','services/flow_task_group_crud/',NULL,NULL,'services/flow_task_group_query','app/flow/Modulegroup/module_group.html','','moduleGroup_settings',NULL,'services/flow_task_group_crud/',NULL,'Module group','2014-11-15 19:51:16','\0','services/flow_style_query?compId=16&compType=page',NULL),(17,'2014-11-13 13:54:44','services/flow_task_group_crud/',NULL,NULL,'services/flow_task_group_query/getInstance/','app/flow/Modulegroup/module_group_edit.html','\0','moduleGroup_edit',NULL,'services/flow_task_group_crud/',NULL,'Edit module group','2014-11-13 17:33:45','','services/flow_style_query?compId=17&compType=page',NULL),(18,'2014-11-13 13:56:21','services/flow_task_group_crud/',NULL,NULL,NULL,'app/flow/Modulegroup/module_group_create.html','\0','moduleGroup_create',NULL,'services/flow_task_group_crud/',NULL,'New module group','2014-11-28 06:29:44','\0','services/flow_style_query?compId=18&compType=page',NULL),(19,'2014-11-16 05:43:41','services/flow_user_crud/delete/',NULL,NULL,'services/flow_user_query','app/flow/User/usr_mgr.html','','usr_mgr_settings',NULL,'services/flow_user_crud/',NULL,'User Manager','2014-11-19 21:18:37','\0','services/flow_style_query?compId=19&compType=page',NULL),(20,'2014-11-16 06:56:48','services/flow_user_profile_crud/',NULL,NULL,'services/flow_user_profile_query','app/flow/Profile/profile.html','','profile_settings',NULL,'services/flow_user_profile_crud/',NULL,'Profiles','2014-11-16 06:56:48','\0','services/flow_style_query?compId=20&compType=page',NULL),(21,'2014-11-16 06:58:44','services/flow_user_profile_crud/',NULL,NULL,'services/flow_user_profile_query/getInstance/','app/flow/Profile/profile_edit.html','\0','profile_edit',NULL,'services/flow_user_profile_crud/',NULL,'Edit profile','2014-11-16 06:58:44','','services/flow_style_query?compId=21&compType=page',NULL),(22,'2014-11-16 07:00:25','',NULL,NULL,'','app/flow/Profile/profile_create.html','\0','profile_create',NULL,'services/flow_user_profile_crud/',NULL,'New profile','2014-11-28 05:41:09','\0','services/flow_style_query?compId=22&compType=page',NULL),(23,'2014-11-17 00:10:16','services/flow_user_group_crud/',NULL,NULL,'services/flow_user_group_query','app/flow/Group/group.html','','group_settings','services/flow_user_group_crud/','services/flow_user_group_crud/',NULL,'Group','2014-11-17 00:10:16','\0','services/flow_style_query?compId=23&compType=page',NULL),(24,'2014-11-17 00:11:17','services/flow_user_group_crud/',NULL,NULL,'services/flow_user_group_query/getInstance/','app/flow/Group/group_edit.html','\0','group_edit','services/flow_user_group_crud/','services/flow_user_group_crud/',NULL,'Edit group','2014-11-17 00:11:17','','services/flow_style_query?compId=24&compType=page',NULL),(25,'2014-11-17 00:12:30','services/flow_user_group_crud/',NULL,NULL,NULL,'app/flow/Group/group_create.html','\0','group_create','services/flow_user_group_crud/','services/flow_user_group_crud/',NULL,'New group','2014-12-05 02:52:22','\0','services/flow_style_query?compId=25&compType=page','\0'),(27,'2014-11-17 14:18:38','services/flow_user_crud/delete/',NULL,NULL,'services/flow_user_query/getInstance/','app/flow/User/usr_mgr_edit.html','\0','usr_mgr_edit','services/flow_user_crud/','services/flow_user_crud/update/',NULL,'Edit user','2014-11-19 21:19:09','','services/flow_style_query?compId=27&compType=page',NULL),(28,'2014-11-17 14:19:19','',NULL,NULL,NULL,'app/flow/User/usr_mgr_create.html','\0','usr_mgr_create','services/flow_user_crud/','services/flow_user_crud/create',NULL,'New user','2014-11-28 03:55:26','\0','services/flow_style_query?compId=28&compType=page',NULL),(29,'2014-12-15 14:59:08','',NULL,NULL,'services/flow_user_detail_query/getInstance/','app/flow/User/usr_edit_profile.html','','edit_profile','services/flow_user_detail_crud/','services/flow_user_detail_crud/',NULL,'Edit profile','2014-12-15 15:07:12','','services/flow_style_query?compId=29&compType=page','\0'),(30,'2014-12-28 16:46:42','services/flow_notification_crud/',NULL,NULL,'session/notification/all','app/flow/session/notification_center.html','','notification_center','services/flow_notification_crud/','services/flow_notification_crud/',NULL,'Notification Center','2015-01-02 01:38:54','','services/flow_style_query?compId=30&compType=page','\0'),(31,'2014-12-28 16:59:49','services/flow_notification_crud/',NULL,NULL,'session/notification/getAlert?alert=','app/flow/session/notification_view.html','\0','notification_view','services/flow_notification_crud/','services/flow_notification_crud/',NULL,'Notification','2015-01-04 23:15:07','','services/flow_style_query?compId=31&compType=page','\0'),(32,'2015-01-11 21:30:02','services/war/agent_crud/delete/',NULL,NULL,'services/war/agent_query','app/management/Agent/agent.html','','agent_home','services/war/agent_crud/','services/war/agent_crud/',NULL,'Agents','2015-01-12 07:40:01','\0','services/flow_style_query?compId=32&compType=page','\0'),(33,'2015-01-12 03:52:32','services/war/agent_crud/delete/',NULL,NULL,'services/war/agent_query/getInstance/','app/management/Agent/agent_edit.html','\0','agent_edit','services/war/agent_crud/','services/war/agent_crud/',NULL,'Edit agent','2015-01-12 07:40:18','','services/flow_style_query?compId=33&compType=page','\0'),(34,'2015-01-12 03:53:54','services/war/agent_crud/delete/',NULL,NULL,NULL,'app/management/Agent/agent_create.html','\0','agent_create','services/war/agent_crud/','services/war/agent_crud/',NULL,'New agent','2015-02-11 04:32:12','\0','services/flow_style_query?compId=34&compType=page',''),(35,'2015-01-12 07:01:50','services/war/school_crud/',NULL,NULL,'services/war/school_query/','app/data/School/school.html','','school_settings','services/war/school_crud/','services/war/school_crud/',NULL,'School','2015-01-12 07:03:44','\0','services/flow_style_query?compId=35&compType=page','\0'),(36,'2015-01-12 07:02:38','services/war/school_crud/',NULL,NULL,'services/war/school_query/getInstance/','app/data/School/school_edit.html','\0','school_edit','services/war/school_crud/','services/war/school_crud/',NULL,'Edit school','2015-01-12 07:02:38','','services/flow_style_query?compId=36&compType=page','\0'),(37,'2015-01-12 07:03:28','services/war/school_crud/',NULL,NULL,'','app/data/School/school_create.html','\0','school_create','services/war/school_crud/','services/war/school_crud/',NULL,'New school','2015-01-12 07:03:28','\0','services/flow_style_query?compId=37&compType=page','\0'),(38,'2015-01-13 15:13:10','services/war/region_crud/',NULL,NULL,'services/war/region_query/','app/data/Region/region.html','','region','services/war/region_crud/','services/war/region_crud/',NULL,'Home','2015-01-13 15:13:10','\0','services/flow_style_query?compId=38&compType=page','\0'),(39,'2015-01-13 15:14:07','services/war/region_crud/',NULL,NULL,NULL,'app/data/Region/region_create.html','\0','region_create','services/war/region_crud/','services/war/region_crud/',NULL,'New region','2015-01-30 03:16:06','\0','services/flow_style_query?compId=39&compType=page','\0'),(40,'2015-01-13 15:17:02','services/war/region_crud/',NULL,NULL,'services/war/region_query/getInstance/','app/data/Region/region_edit.html','\0','region_edit','services/war/region_crud/','services/war/region_crud/',NULL,'Edit region','2015-01-13 15:17:02','','services/flow_style_query?compId=40&compType=page','\0'),(41,'2015-01-14 03:35:44','services/war/customer_crud/',NULL,NULL,'services/war/customer_light_query/','app/management/Customer/customer.html','','customer','services/war/customer_crud/','services/war/customer_crud/',NULL,'Home','2015-01-28 01:11:29','\0','services/flow_style_query?compId=41&compType=page','\0'),(42,'2015-01-14 03:36:19','services/war/customer_crud/',NULL,NULL,'services/war/customer_query/getInstance/','app/management/Customer/customer_edit.html','\0','customer_edit','services/war/customer_crud/','services/war/customer_crud/',NULL,'Edit customer','2015-01-14 03:36:19','','services/flow_style_query?compId=42&compType=page','\0'),(43,'2015-01-14 03:36:46','services/war/customer_crud/',NULL,NULL,NULL,'app/management/Customer/customer_create.html','\0','customer_create','services/war/customer_crud/','services/war/customer_crud/',NULL,'New customer','2015-02-11 04:32:01','\0','services/flow_style_query?compId=43&compType=page',''),(44,'2015-01-19 15:36:04','services/war/position_crud/',NULL,NULL,'services/war/position_query/','app/data/Position/position.html','','position','services/war/position_crud/','services/war/position_crud/',NULL,'Home','2015-01-19 16:46:09','\0','services/flow_style_query?compId=44&compType=page','\0'),(45,'2015-01-19 15:36:58','services/war/position_crud/',NULL,NULL,'services/war/position_query/getInstance/','app/data/Position/position_edit.html','\0','position_edit','services/war/position_crud/','services/war/position_crud/',NULL,'Edit position','2015-01-19 15:36:58','','services/flow_style_query?compId=45&compType=page','\0'),(46,'2015-01-19 15:37:31','services/war/position_crud/',NULL,NULL,'','app/data/Position/position_create.html','\0','position_create','services/war/position_crud/','services/war/position_crud/',NULL,'New position','2015-01-19 15:37:31','\0','services/flow_style_query?compId=46&compType=page','\0'),(47,'2015-01-20 03:43:29','services/war/level_crud/',NULL,NULL,'services/war/level_query/','app/data/Level/level.html','','level','services/war/level_crud/','services/war/level_crud/',NULL,'Home','2015-01-20 03:43:29','\0','services/flow_style_query?compId=47&compType=page','\0'),(48,'2015-01-20 03:44:08','services/war/level_crud',NULL,NULL,NULL,'app/data/Level/level_create.html','\0','level_create','services/war/level_crud','services/war/level_crud',NULL,'New level','2015-01-20 03:44:08','\0','services/flow_style_query?compId=48&compType=page','\0'),(49,'2015-01-20 03:44:45','services/war/level_crud/',NULL,NULL,'services/war/level_query/getInstance/','app/data/Level/level_edit.html','\0','level_edit','services/war/level_crud/','services/war/level_crud/',NULL,'Edit level','2015-01-20 03:44:45','','services/flow_style_query?compId=49&compType=page','\0'),(50,'2015-01-28 16:42:22','services/war/school_year_crud/',NULL,NULL,'services/war/school_year_query/','app/activity/SchoolYear/school_year.html','','school_year','services/war/school_year_crud/','services/war/school_year_crud/',NULL,'Home','2015-01-28 17:26:02','\0','services/flow_style_query?compId=50&compType=page','\0'),(51,'2015-01-28 16:43:34','services/war/school_year_crud/',NULL,NULL,'services/war/school_year_query/getInstance/','app/activity/SchoolYear/school_year_edit.html','\0','school_year_edit','services/war/school_year_crud/','services/war/school_year_crud/',NULL,'Edit school year','2015-01-28 16:46:32','','services/flow_style_query?compId=51&compType=page','\0'),(52,'2015-01-28 16:44:26','services/war/school_year_crud/',NULL,NULL,NULL,'app/activity/SchoolYear/school_year_create.html','\0','school_year_create','services/war/school_year_crud/','services/war/school_year_crud/',NULL,'New school year','2015-01-28 16:44:26','\0','services/flow_style_query?compId=52&compType=page','\0'),(53,'2015-01-29 03:51:36','session/war/planner_service',NULL,NULL,'services/war/activity_query/events','app/activity/Planner/planner.html','','planner','session/war/planner_service','session/war/planner_service',NULL,'Home','2015-02-16 06:53:31','\0','services/flow_style_query?compId=53&compType=page','\0'),(55,'2015-01-31 08:29:24','services/war/activity_crud/',NULL,NULL,'services/war/activity_query','app/activity/Daily/daily.html','','daily','services/war/activity_crud/','services/war/activity_crud/',NULL,'Home','2015-02-09 22:58:00','\0','services/flow_style_query?compId=55&compType=page','\0'),(56,'2015-01-31 08:30:50','services/war/activity_crud/',NULL,NULL,'services/war/activity_query/getInstance/','app/activity/Daily/daily_edit.html','\0','daily_edit','services/war/activity_crud/','services/war/activity_crud/',NULL,'Update activity','2015-02-09 19:52:02','','services/flow_style_query?compId=56&compType=page','\0'),(57,'2015-02-06 02:03:20',NULL,NULL,NULL,'services/war/report_weekly_service/agents','app/report/report_weekly_agent.html','','report_weekly_agent',NULL,NULL,NULL,'Agent activity','2015-02-23 01:48:01','\0','services/flow_style_query?compId=57&compType=page','\0'),(58,'2015-02-19 08:52:01',NULL,NULL,NULL,'app/settings.json','app/flow/Setup/setup.html','','setup',NULL,NULL,NULL,'Flow','2015-02-19 09:07:58','\0','services/flow_style_query?compId=58&compType=page','\0'),(59,'2015-02-23 01:27:40',NULL,NULL,NULL,'services/war/report_monthly_service/customers','app/report/report_monthly_customer.html','','report_monthly_customer',NULL,NULL,NULL,'Customer summary','2015-02-23 04:12:13','\0','services/flow_style_query?compId=59&compType=page','\0'),(60,'2015-02-26 12:40:10','services/war/customer_tag_crud',NULL,NULL,'services/war/agent_light_query/find_managed_agents?manager=','app/management/Region-manager/region-manager.html','','region_manager_home','services/war/customer_light_query/find_by_assigned_agent?agentId=','services/war/customer_tag_crud/save_top_schools/',NULL,'Sales Manager','2015-03-07 05:40:48','\0','services/flow_style_query?compId=60&compType=page','\0'),(61,'2015-03-07 09:52:57',NULL,NULL,NULL,'services/war/agent_customer_summary_query/customer_summary/','app/management/Agent/agent_customer_summary.html','\0','customer_agent_summary',NULL,NULL,NULL,'Summary','2015-03-11 11:43:55','','services/flow_style_query?compId=61&compType=page','\0'),(62,'2015-03-10 05:12:35',NULL,NULL,NULL,'services/war/customer_light_query/find_by_assigned_agent','app/management/Agent/agent_customers.html','','customer_agent_home','services/war/activity_query/activities',NULL,NULL,'Assigned Customers','2015-03-10 16:58:12','\0','services/flow_style_query?compId=62&compType=page','\0');
/*!40000 ALTER TABLE `flow_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_profile_permission`
--

DROP TABLE IF EXISTS `flow_profile_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_profile_permission` (
  `flow_profile_permmission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `flow_permission_delete` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `flow_page_id` bigint(20) NOT NULL,
  `flow_page_name` varchar(255) NOT NULL,
  `flow_permission_get` bit(1) DEFAULT NULL,
  `flow_permission_post` bit(1) DEFAULT NULL,
  `flow_permission_put` bit(1) DEFAULT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`flow_profile_permmission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_profile_permission`
--

LOCK TABLES `flow_profile_permission` WRITE;
/*!40000 ALTER TABLE `flow_profile_permission` DISABLE KEYS */;
INSERT INTO `flow_profile_permission` VALUES (1,'2014-12-01 14:02:27','',NULL,NULL,3,'page_edit','','','',NULL,NULL),(2,'2014-12-01 14:02:27','',NULL,NULL,4,'page_create','','','',NULL,NULL),(3,'2014-12-01 14:02:27','',NULL,NULL,6,'style_settings','','','',NULL,NULL),(4,'2014-12-01 14:02:27','',NULL,NULL,7,'style_edit','','','',NULL,NULL),(5,'2014-12-01 14:02:27','',NULL,NULL,2,'menu','','','',NULL,NULL),(6,'2014-12-01 14:02:27','',NULL,NULL,8,'style_create','','','',NULL,NULL),(7,'2014-12-01 14:02:27','',NULL,NULL,9,'task_settings','','','',NULL,NULL),(8,'2014-12-01 14:02:27','',NULL,NULL,10,'task_edit','','','',NULL,NULL),(9,'2014-12-01 14:02:27','',NULL,NULL,11,'task_create','','','',NULL,NULL),(10,'2014-12-01 14:02:27','',NULL,NULL,13,'md_settings','','','',NULL,NULL),(11,'2014-12-01 14:02:27','',NULL,NULL,14,'md_edit','','','',NULL,'2014-12-01 15:14:32'),(12,'2014-12-01 14:02:27','',NULL,NULL,1,'page_settings','','','',NULL,NULL),(13,'2014-12-01 14:02:27','',NULL,NULL,15,'md_create','','','',NULL,NULL),(14,'2014-12-01 14:02:27','',NULL,NULL,16,'moduleGroup_settings','','','',NULL,NULL),(15,'2014-12-01 14:02:27','',NULL,NULL,17,'moduleGroup_edit','','','',NULL,NULL),(16,'2014-12-01 14:02:27','',NULL,NULL,18,'moduleGroup_create','','','',NULL,NULL),(17,'2014-12-01 14:02:27','',NULL,NULL,19,'usr_mgr_settings','','','',NULL,'2014-12-01 20:30:44'),(18,'2014-12-01 14:02:27','',NULL,NULL,20,'profile_settings','','','',NULL,NULL),(19,'2014-12-01 14:02:27','',NULL,NULL,21,'profile_edit','','','',NULL,NULL),(20,'2014-12-01 14:02:27','',NULL,NULL,22,'profile_create','','','',NULL,NULL),(21,'2014-12-01 14:02:27','',NULL,NULL,23,'group_settings','','','',NULL,NULL),(22,'2014-12-01 14:02:27','',NULL,NULL,24,'group_edit','','','',NULL,NULL),(23,'2014-12-01 14:02:27','',NULL,NULL,25,'group_create','','','',NULL,NULL),(24,'2014-12-01 14:02:27','',NULL,NULL,27,'usr_mgr_edit','','','',NULL,NULL),(25,'2014-12-01 14:02:27','',NULL,NULL,28,'usr_mgr_create','','','',NULL,NULL),(26,'2014-12-01 14:29:53','',NULL,NULL,1,'page_settings','','','',NULL,NULL),(27,'2014-12-01 14:29:53','',NULL,NULL,2,'menu','','','',NULL,NULL),(28,'2014-12-01 14:29:53','',NULL,NULL,3,'page_edit','','','',NULL,NULL),(29,'2014-12-01 14:29:53','',NULL,NULL,4,'page_create','','','',NULL,NULL),(30,'2014-12-01 14:29:53','',NULL,NULL,6,'style_settings','','','',NULL,NULL),(31,'2014-12-01 14:29:53','',NULL,NULL,7,'style_edit','','','',NULL,NULL),(32,'2014-12-01 14:29:53','',NULL,NULL,8,'style_create','','','',NULL,NULL),(33,'2014-12-01 14:29:53','',NULL,NULL,9,'task_settings','','','',NULL,NULL),(34,'2014-12-01 14:29:53','',NULL,NULL,10,'task_edit','','','',NULL,NULL),(35,'2014-12-01 14:29:53','',NULL,NULL,11,'task_create','','','',NULL,NULL),(36,'2014-12-01 14:29:53','',NULL,NULL,13,'md_settings','','','',NULL,NULL),(37,'2014-12-01 14:29:53','',NULL,NULL,14,'md_edit','','','',NULL,NULL),(38,'2014-12-01 14:29:53','',NULL,NULL,15,'md_create','','','',NULL,NULL),(39,'2014-12-01 14:29:53','',NULL,NULL,16,'moduleGroup_settings','','','',NULL,NULL),(40,'2014-12-01 14:29:53','',NULL,NULL,17,'moduleGroup_edit','','','',NULL,NULL),(41,'2014-12-01 14:29:53','',NULL,NULL,18,'moduleGroup_create','','','',NULL,NULL),(42,'2014-12-01 14:29:53','',NULL,NULL,19,'usr_mgr_settings','','','',NULL,NULL),(43,'2014-12-01 14:29:53','',NULL,NULL,20,'profile_settings','','','',NULL,NULL),(44,'2014-12-01 14:29:53','',NULL,NULL,21,'profile_edit','','','',NULL,NULL),(45,'2014-12-01 14:29:53','',NULL,NULL,22,'profile_create','','','',NULL,NULL),(46,'2014-12-01 14:29:53','',NULL,NULL,23,'group_settings','','','',NULL,NULL),(47,'2014-12-01 14:29:53','',NULL,NULL,24,'group_edit','','','',NULL,NULL),(48,'2014-12-01 14:29:53','',NULL,NULL,25,'group_create','','','',NULL,NULL),(49,'2014-12-01 14:29:53','',NULL,NULL,27,'usr_mgr_edit','','','',NULL,NULL),(50,'2014-12-01 14:29:53','',NULL,NULL,28,'usr_mgr_create','','','',NULL,NULL),(51,'2014-12-02 03:29:54','\0',NULL,NULL,9,'task_settings','','\0','\0',NULL,'2014-12-03 03:21:49'),(52,'2014-12-02 03:29:55','\0',NULL,NULL,13,'md_settings','','\0','\0',NULL,'2014-12-03 02:06:10'),(53,'2014-12-02 03:29:55','\0',NULL,NULL,1,'page_settings','','\0','\0',NULL,'2014-12-03 02:06:10'),(54,'2014-12-02 03:29:55','\0',NULL,NULL,16,'moduleGroup_settings','','\0','\0',NULL,'2014-12-03 02:06:10'),(55,'2014-12-15 15:50:44','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(56,'2014-12-15 15:50:52','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(57,'2015-01-02 01:59:25','',NULL,NULL,30,'notification_center','','','',NULL,NULL),(58,'2015-01-02 01:59:25','',NULL,NULL,31,'notification_view','','','',NULL,NULL),(59,'2015-01-04 05:37:22','',NULL,NULL,30,'notification_center','','','',NULL,NULL),(60,'2015-01-04 05:37:22','',NULL,NULL,31,'notification_view','','','',NULL,NULL),(61,'2015-01-11 22:38:21','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(62,'2015-01-12 00:14:48','',NULL,NULL,32,'agent_home','','','',NULL,NULL),(63,'2015-01-12 03:59:22','',NULL,NULL,33,'agent_edit','','','',NULL,NULL),(64,'2015-01-12 03:59:22','',NULL,NULL,34,'agent_create','','','',NULL,NULL),(65,'2015-01-12 03:59:22','',NULL,NULL,32,'agent_home','','','',NULL,NULL),(66,'2015-01-12 07:06:20','',NULL,NULL,35,'school_settings','','','',NULL,NULL),(67,'2015-01-12 07:06:20','',NULL,NULL,36,'school_edit','','','',NULL,NULL),(68,'2015-01-12 07:06:20','',NULL,NULL,37,'school_create','','','',NULL,NULL),(69,'2015-01-12 07:06:20','',NULL,NULL,33,'agent_edit','','','',NULL,NULL),(70,'2015-01-12 07:06:20','',NULL,NULL,34,'agent_create','','','',NULL,NULL),(71,'2015-01-13 19:46:46','',NULL,NULL,38,'region','','','',NULL,NULL),(72,'2015-01-13 19:46:46','',NULL,NULL,37,'school_create','','','',NULL,NULL),(73,'2015-01-13 19:46:46','',NULL,NULL,36,'school_edit','','','',NULL,NULL),(74,'2015-01-13 19:46:46','',NULL,NULL,35,'school_settings','','','',NULL,NULL),(75,'2015-01-13 19:46:46','',NULL,NULL,34,'agent_create','','','',NULL,NULL),(76,'2015-01-13 19:46:46','',NULL,NULL,33,'agent_edit','','','',NULL,NULL),(77,'2015-01-13 19:46:46','',NULL,NULL,32,'agent_home','','','',NULL,NULL),(78,'2015-01-13 19:46:46','',NULL,NULL,39,'region_create','','','',NULL,NULL),(79,'2015-01-13 19:46:46','',NULL,NULL,31,'notification_view','','','',NULL,NULL),(80,'2015-01-13 19:46:46','',NULL,NULL,40,'region_edit','','','',NULL,NULL),(81,'2015-01-14 05:09:34','',NULL,NULL,41,'customer','','','',NULL,NULL),(82,'2015-01-14 05:09:34','',NULL,NULL,42,'customer_edit','','','',NULL,NULL),(83,'2015-01-14 05:09:34','',NULL,NULL,43,'customer_create','','','',NULL,NULL),(84,'2015-01-14 05:09:34','',NULL,NULL,30,'notification_center','','','',NULL,NULL),(85,'2015-01-19 17:13:47','',NULL,NULL,44,'position','','','',NULL,NULL),(86,'2015-01-19 17:13:47','',NULL,NULL,45,'position_edit','','','',NULL,NULL),(87,'2015-01-19 17:13:47','',NULL,NULL,46,'position_create','','','',NULL,NULL),(88,'2015-01-20 04:49:27','',NULL,NULL,47,'level','','','',NULL,NULL),(89,'2015-01-20 04:49:27','',NULL,NULL,48,'level_create','','','',NULL,NULL),(90,'2015-01-20 04:49:27','',NULL,NULL,49,'level_edit','','','',NULL,NULL),(91,'2015-01-28 17:29:29','',NULL,NULL,50,'school_year','','','',NULL,NULL),(92,'2015-01-28 17:29:29','',NULL,NULL,51,'school_year_edit','','','',NULL,NULL),(93,'2015-01-28 17:29:29','',NULL,NULL,52,'school_year_create','','','',NULL,NULL),(94,'2015-01-30 02:58:18','',NULL,NULL,53,'planner','','','',NULL,NULL),(95,'2015-01-30 23:48:53','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(96,'2015-01-31 08:42:26','',NULL,NULL,55,'daily','','','',NULL,NULL),(97,'2015-01-31 08:42:26','',NULL,NULL,56,'daily_edit','','','',NULL,NULL),(98,'2015-01-31 08:42:26','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(99,'2015-02-01 23:30:38','',NULL,NULL,53,'planner','','','',NULL,NULL),(100,'2015-02-01 23:30:38','\0',NULL,NULL,55,'daily','','','',NULL,'2015-02-02 03:27:07'),(101,'2015-02-01 23:30:38','\0',NULL,NULL,56,'daily_edit','','','',NULL,'2015-02-02 03:27:07'),(102,'2015-02-06 07:37:17','',NULL,NULL,57,'report_weekly_agent','','','',NULL,NULL),(103,'2015-02-23 04:40:36','',NULL,NULL,59,'report_monthly_customer','','','',NULL,NULL),(104,'2015-02-25 14:18:11','',NULL,NULL,58,'setup','','','',NULL,NULL),(105,'2015-02-26 12:50:07','',NULL,NULL,60,'region_manager_home','','','',NULL,NULL),(106,'2015-02-26 12:50:07','',NULL,NULL,59,'report_monthly_customer','','','',NULL,NULL),(107,'2015-02-26 12:50:07','',NULL,NULL,57,'report_weekly_agent','','','',NULL,NULL),(108,'2015-02-26 12:50:07','',NULL,NULL,53,'planner','','','',NULL,NULL),(109,'2015-02-26 12:50:07','',NULL,NULL,52,'school_year_create','','','',NULL,NULL),(110,'2015-02-26 12:50:07','',NULL,NULL,51,'school_year_edit','','','',NULL,NULL),(111,'2015-02-26 12:50:07','',NULL,NULL,50,'school_year','','','',NULL,NULL),(112,'2015-02-26 12:50:07','',NULL,NULL,49,'level_edit','','','',NULL,NULL),(113,'2015-02-26 12:50:07','',NULL,NULL,48,'level_create','','','',NULL,NULL),(114,'2015-02-26 12:50:07','',NULL,NULL,47,'level','','','',NULL,NULL),(115,'2015-02-26 12:50:07','',NULL,NULL,46,'position_create','','','',NULL,NULL),(116,'2015-02-26 12:50:07','',NULL,NULL,45,'position_edit','','','',NULL,NULL),(117,'2015-02-26 12:50:07','',NULL,NULL,44,'position','','','',NULL,NULL),(118,'2015-02-26 12:50:08','',NULL,NULL,43,'customer_create','','','',NULL,NULL),(119,'2015-02-26 12:50:08','',NULL,NULL,42,'customer_edit','','','',NULL,NULL),(120,'2015-02-26 12:50:08','',NULL,NULL,41,'customer','','','',NULL,NULL),(121,'2015-02-26 12:50:08','',NULL,NULL,40,'region_edit','','','',NULL,NULL),(122,'2015-02-26 12:50:08','',NULL,NULL,39,'region_create','','','',NULL,NULL),(123,'2015-02-26 12:50:08','',NULL,NULL,38,'region','','','',NULL,NULL),(124,'2015-02-26 12:50:08','',NULL,NULL,37,'school_create','','','',NULL,NULL),(125,'2015-02-26 12:50:08','',NULL,NULL,36,'school_edit','','','',NULL,NULL),(126,'2015-02-26 12:50:08','',NULL,NULL,35,'school_settings','','','',NULL,NULL),(127,'2015-02-26 12:50:08','',NULL,NULL,34,'agent_create','','','',NULL,NULL),(128,'2015-02-26 12:50:08','',NULL,NULL,33,'agent_edit','','','',NULL,NULL),(129,'2015-02-26 12:50:08','',NULL,NULL,32,'agent_home','','','',NULL,NULL),(130,'2015-02-26 13:00:15','',NULL,NULL,60,'region_manager_home','','','',NULL,NULL),(131,'2015-02-26 13:00:15','',NULL,NULL,35,'school_settings','','','',NULL,NULL),(132,'2015-02-26 13:00:15','',NULL,NULL,36,'school_edit','','','',NULL,NULL),(133,'2015-02-26 13:00:15','',NULL,NULL,37,'school_create','','','',NULL,NULL),(134,'2015-02-26 13:00:15','',NULL,NULL,41,'customer','','','',NULL,NULL),(135,'2015-02-26 13:00:15','',NULL,NULL,42,'customer_edit','','','',NULL,NULL),(136,'2015-02-26 13:00:15','',NULL,NULL,43,'customer_create','','','',NULL,NULL),(137,'2015-02-26 13:00:15','',NULL,NULL,44,'position','','','',NULL,NULL),(138,'2015-02-26 13:00:15','',NULL,NULL,45,'position_edit','','','',NULL,NULL),(139,'2015-02-26 13:00:15','',NULL,NULL,46,'position_create','','','',NULL,NULL),(140,'2015-02-26 13:00:15','',NULL,NULL,47,'level','','','',NULL,NULL),(141,'2015-02-26 13:00:15','',NULL,NULL,48,'level_create','','','',NULL,NULL),(142,'2015-02-26 13:00:15','',NULL,NULL,49,'level_edit','','','',NULL,NULL),(143,'2015-02-26 13:00:15','',NULL,NULL,50,'school_year','','','',NULL,NULL),(144,'2015-02-26 13:00:15','',NULL,NULL,51,'school_year_edit','','','',NULL,NULL),(145,'2015-02-26 13:00:15','',NULL,NULL,52,'school_year_create','','','',NULL,NULL),(146,'2015-02-26 13:00:15','',NULL,NULL,53,'planner','','','',NULL,NULL),(147,'2015-02-26 13:00:15','',NULL,NULL,57,'report_weekly_agent','','','',NULL,NULL),(148,'2015-02-26 13:00:15','',NULL,NULL,59,'report_monthly_customer','','','',NULL,NULL),(149,'2015-02-26 18:31:31','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(150,'2015-02-26 18:31:31','',NULL,NULL,30,'notification_center','','','',NULL,NULL),(151,'2015-02-26 18:31:31','',NULL,NULL,31,'notification_view','','','',NULL,NULL),(152,'2015-03-09 10:48:04','',NULL,NULL,57,'report_weekly_agent','','','',NULL,NULL),(153,'2015-03-09 10:48:04','',NULL,NULL,59,'report_monthly_customer','','','',NULL,NULL),(154,'2015-03-10 02:21:28','',NULL,NULL,29,'edit_profile','','','',NULL,NULL),(155,'2015-03-10 05:29:58','',NULL,NULL,61,'customer_agent_summary','','','',NULL,NULL),(156,'2015-03-10 05:29:58','',NULL,NULL,62,'customer_agent_home','','','',NULL,NULL),(157,'2015-03-10 06:28:15','',NULL,NULL,36,'school_edit','','','',NULL,NULL),(158,'2015-03-10 06:28:15','',NULL,NULL,37,'school_create','','','',NULL,NULL),(159,'2015-03-10 06:28:15','',NULL,NULL,35,'school_settings','','','',NULL,NULL),(160,'2015-03-10 06:28:15','',NULL,NULL,42,'customer_edit','','','',NULL,NULL);
/*!40000 ALTER TABLE `flow_profile_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_session`
--

DROP TABLE IF EXISTS `flow_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_session` (
  `flow_session_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `last_access_date` datetime NOT NULL,
  `secured` bit(1) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_host` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_session_key` varchar(255) NOT NULL,
  PRIMARY KEY (`flow_session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_session`
--

LOCK TABLES `flow_session` WRITE;
/*!40000 ALTER TABLE `flow_session` DISABLE KEYS */;
INSERT INTO `flow_session` VALUES (11,'\0','2015-01-30 02:34:46',NULL,NULL,'2015-01-30 02:34:46','',NULL,'2015-01-30 02:37:38','192.168.1.4',2,''),(12,'\0','2015-01-30 02:37:40',NULL,NULL,'2015-01-30 02:37:40','',NULL,'2015-01-30 23:20:23','127.0.0.1',2,''),(13,'\0','2015-01-30 23:21:46',NULL,NULL,'2015-01-30 23:21:46','',NULL,'2015-01-31 07:01:04','192.168.1.4',2,''),(14,'\0','2015-01-31 07:01:06',NULL,NULL,'2015-01-31 07:01:06','',NULL,'2015-02-01 23:30:53','127.0.0.1',2,''),(15,'\0','2015-02-01 23:31:34',NULL,NULL,'2015-02-01 23:31:34','',NULL,'2015-02-01 23:38:12','127.0.0.1',2,''),(16,'\0','2015-02-01 23:38:23',NULL,NULL,'2015-02-01 23:38:23','',NULL,'2015-02-01 23:53:33','127.0.0.1',9,''),(17,'\0','2015-02-01 23:39:45',NULL,NULL,'2015-02-01 23:39:45','',NULL,'2015-02-01 23:39:48','127.0.0.1',2,''),(18,'\0','2015-02-01 23:51:56',NULL,NULL,'2015-02-01 23:51:56','',NULL,'2015-02-01 23:52:18','127.0.0.1',2,''),(19,'\0','2015-02-01 23:53:50',NULL,NULL,'2015-02-01 23:53:50','',NULL,'2015-02-01 23:54:03','127.0.0.1',2,''),(20,'\0','2015-02-01 23:54:53',NULL,NULL,'2015-02-01 23:54:53','',NULL,'2015-02-02 00:15:42','127.0.0.1',2,''),(21,'\0','2015-02-02 00:37:59',NULL,NULL,'2015-02-02 00:37:59','',NULL,'2015-02-25 13:50:52','127.0.0.1',9,''),(22,'\0','2015-02-02 00:38:17',NULL,NULL,'2015-02-02 00:38:17','',NULL,'2015-02-10 05:52:57','127.0.0.1',2,''),(23,'\0','2015-02-10 05:52:59',NULL,NULL,'2015-02-10 05:52:59','',NULL,'2015-02-13 11:57:27','127.0.0.1',2,''),(24,'\0','2015-02-13 11:57:28',NULL,NULL,'2015-02-13 11:57:28','',NULL,'2015-02-23 08:51:10','127.0.0.1',2,''),(25,'\0','2015-02-23 08:51:16',NULL,NULL,'2015-02-23 08:51:16','',NULL,'2015-02-23 08:59:23','127.0.0.1',2,''),(26,'\0','2015-02-23 09:01:37',NULL,NULL,'2015-02-23 09:01:37','',NULL,'2015-02-25 13:49:50','127.0.0.1',2,''),(27,'\0','2015-02-25 13:50:02',NULL,NULL,'2015-02-25 13:50:02','',NULL,'2015-02-25 13:50:27','127.0.0.1',2,''),(28,'\0','2015-02-25 13:50:57',NULL,NULL,'2015-02-25 13:50:57','',NULL,'2015-02-25 13:51:02','127.0.0.1',2,''),(29,'\0','2015-02-25 13:52:13',NULL,NULL,'2015-02-25 13:52:13','',NULL,'2015-02-25 13:52:18','127.0.0.1',2,''),(30,'\0','2015-02-25 14:00:40',NULL,NULL,'2015-02-25 14:00:40','',NULL,'2015-02-25 14:00:47','127.0.0.1',2,''),(31,'\0','2015-02-25 14:17:17',NULL,NULL,'2015-02-25 14:17:17','',NULL,'2015-02-25 14:17:22','127.0.0.1',2,''),(32,'\0','2015-02-25 14:17:25',NULL,NULL,'2015-02-25 14:17:25','',NULL,'2015-02-26 09:34:21','127.0.0.1',2,''),(33,'\0','2015-02-26 09:34:33',NULL,NULL,'2015-02-26 09:34:33','',NULL,'2015-02-26 09:35:20','127.0.0.1',2,''),(34,'\0','2015-02-26 09:35:26',NULL,NULL,'2015-02-26 09:35:26','',NULL,'2015-02-26 09:35:39','127.0.0.1',9,''),(35,'\0','2015-02-26 09:35:43',NULL,NULL,'2015-02-26 09:35:43','',NULL,'2015-02-26 09:35:55','127.0.0.1',9,''),(36,'\0','2015-02-26 09:35:59',NULL,NULL,'2015-02-26 09:35:59','',NULL,'2015-02-26 09:39:06','127.0.0.1',2,''),(37,'\0','2015-02-26 09:39:09',NULL,NULL,'2015-02-26 09:39:09','',NULL,'2015-02-26 09:39:12','127.0.0.1',10,''),(38,'\0','2015-02-26 09:39:16',NULL,NULL,'2015-02-26 09:39:16','',NULL,'2015-02-26 09:39:18','127.0.0.1',10,''),(39,'\0','2015-02-26 09:39:29',NULL,NULL,'2015-02-26 09:39:29','',NULL,'2015-02-26 09:40:50','127.0.0.1',10,''),(40,'\0','2015-02-26 09:40:55',NULL,NULL,'2015-02-26 09:40:55','',NULL,'2015-02-26 09:41:57','127.0.0.1',10,''),(41,'\0','2015-02-26 09:42:02',NULL,NULL,'2015-02-26 09:42:02','',NULL,'2015-02-26 09:42:08','127.0.0.1',2,''),(42,'\0','2015-02-26 09:42:13',NULL,NULL,'2015-02-26 09:42:13','',NULL,'2015-02-26 09:42:18','127.0.0.1',10,''),(43,'\0','2015-02-26 09:42:22',NULL,NULL,'2015-02-26 09:42:22','',NULL,'2015-02-26 09:43:46','127.0.0.1',2,''),(44,'\0','2015-02-26 10:39:19',NULL,NULL,'2015-02-26 10:39:19','',NULL,'2015-02-26 10:40:28','127.0.0.1',2,''),(45,'\0','2015-02-26 10:41:02',NULL,NULL,'2015-02-26 10:41:02','',NULL,'2015-02-26 11:50:46','127.0.0.1',2,''),(46,'\0','2015-02-26 11:50:48',NULL,NULL,'2015-02-26 11:50:48','',NULL,'2015-02-26 12:52:21','127.0.0.1',2,''),(47,'\0','2015-02-26 12:52:27',NULL,NULL,'2015-02-26 12:52:27','',NULL,'2015-02-26 12:52:46','127.0.0.1',12,''),(48,'\0','2015-02-26 12:52:50',NULL,NULL,'2015-02-26 12:52:50','',NULL,'2015-02-26 12:53:05','127.0.0.1',2,''),(49,'\0','2015-02-26 12:53:10',NULL,NULL,'2015-02-26 12:53:10','',NULL,'2015-02-26 12:54:05','127.0.0.1',12,''),(50,'\0','2015-02-26 12:54:09',NULL,NULL,'2015-02-26 12:54:09','',NULL,'2015-02-26 13:00:46','127.0.0.1',2,''),(51,'\0','2015-02-26 13:01:05',NULL,NULL,'2015-02-26 13:01:05','',NULL,'2015-02-26 13:02:53','127.0.0.1',12,''),(52,'\0','2015-02-26 13:02:59',NULL,NULL,'2015-02-26 13:02:59','',NULL,'2015-02-26 13:03:16','127.0.0.1',11,''),(53,'\0','2015-02-26 13:03:20',NULL,NULL,'2015-02-26 13:03:20','',NULL,'2015-02-26 13:07:04','127.0.0.1',2,''),(54,'\0','2015-02-26 13:07:08',NULL,NULL,'2015-02-26 13:07:08','',NULL,'2015-02-26 13:07:22','127.0.0.1',11,''),(55,'\0','2015-02-26 13:07:25',NULL,NULL,'2015-02-26 13:07:25','',NULL,'2015-02-26 13:07:28','127.0.0.1',11,''),(56,'\0','2015-02-26 13:07:30',NULL,NULL,'2015-02-26 13:07:30','',NULL,'2015-02-27 00:31:55','127.0.0.1',11,''),(57,'\0','2015-02-26 13:08:00',NULL,NULL,'2015-02-26 13:08:00','',NULL,'2015-02-27 00:30:50','127.0.0.1',2,''),(58,'\0','2015-02-27 02:06:40',NULL,NULL,'2015-02-27 02:06:40','',NULL,'2015-02-27 02:24:05','127.0.0.1',11,''),(59,'\0','2015-02-27 02:11:14',NULL,NULL,'2015-02-27 02:11:14','',NULL,'2015-02-27 02:29:34','127.0.0.1',2,''),(60,'\0','2015-02-27 02:25:06',NULL,NULL,'2015-02-27 02:25:06','',NULL,'2015-02-27 02:30:49','127.0.0.1',11,''),(61,'\0','2015-02-27 02:30:54',NULL,NULL,'2015-02-27 02:30:54','',NULL,'2015-02-27 02:31:52','127.0.0.1',11,''),(62,'\0','2015-02-27 02:32:06',NULL,NULL,'2015-02-27 02:32:06','',NULL,'2015-02-27 02:34:27','127.0.0.1',11,''),(63,'\0','2015-02-27 02:34:56',NULL,NULL,'2015-02-27 02:34:56','',NULL,'2015-02-27 02:37:33','127.0.0.1',2,''),(64,'\0','2015-02-27 02:37:56',NULL,NULL,'2015-02-27 02:37:56','',NULL,'2015-02-27 03:55:01','127.0.0.1',11,''),(65,'\0','2015-02-27 03:55:41',NULL,NULL,'2015-02-27 03:55:41','',NULL,'2015-02-27 03:59:01','127.0.0.1',2,''),(66,'','2015-02-27 03:59:36',NULL,NULL,'2015-02-27 03:59:36','',NULL,NULL,'127.0.0.1',11,''),(67,'\0','2015-02-27 04:22:15',NULL,NULL,'2015-02-27 04:22:15','',NULL,'2015-02-27 04:25:46','127.0.0.1',2,''),(68,'\0','2015-03-06 19:15:53',NULL,NULL,'2015-03-06 19:15:53','',NULL,'2015-03-06 19:16:11','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NjQwNTUyOTg2'),(69,'\0','2015-03-06 19:16:18',NULL,NULL,'2015-03-06 19:16:18','',NULL,'2015-03-06 20:03:28','127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjQwNTc3Nzgy'),(70,'\0','2015-03-06 19:51:43',NULL,NULL,'2015-03-06 19:51:43','',NULL,'2015-03-06 23:54:42','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NjQyNzAzMTIy'),(71,'','2015-03-06 20:03:30',NULL,NULL,'2015-03-06 20:03:30','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjQzNDEwMzU5'),(72,'','2015-03-06 22:40:50',NULL,NULL,'2015-03-06 22:40:50','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjUyODQ5NjIw'),(73,'','2015-03-06 22:42:52',NULL,NULL,'2015-03-06 22:42:52','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjUyOTcyMjAw'),(74,'','2015-03-06 23:00:37',NULL,NULL,'2015-03-06 23:00:37','',NULL,NULL,'127.0.0.1',11,'Uk0xOkpFUkthbGxzdGFyOTg6MTQyNTY1NDAzNzA4OQ=='),(75,'','2015-03-06 23:02:27',NULL,NULL,'2015-03-06 23:02:27','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU0MTQ3MjQy'),(76,'','2015-03-06 23:02:56',NULL,NULL,'2015-03-06 23:02:56','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU0MTc1NTMw'),(77,'','2015-03-06 23:39:06',NULL,NULL,'2015-03-06 23:39:06','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU2MzQ1OTY4'),(78,'\0','2015-03-06 23:55:25',NULL,NULL,'2015-03-06 23:55:25','',NULL,'2015-03-07 00:23:12','127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU3MzI1MDEx'),(79,'\0','2015-03-07 00:23:14',NULL,NULL,'2015-03-07 00:23:14','',NULL,'2015-03-07 00:23:45','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1NjU4OTk0MjQ1'),(80,'\0','2015-03-07 00:23:47',NULL,NULL,'2015-03-07 00:23:47','',NULL,'2015-03-07 00:24:04','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1NjU5MDI3MDQz'),(81,'\0','2015-03-07 00:24:07',NULL,NULL,'2015-03-07 00:24:07','',NULL,'2015-03-07 00:24:11','127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU5MDQ3MTAx'),(82,'\0','2015-03-07 00:24:18',NULL,NULL,'2015-03-07 00:24:18','',NULL,'2015-03-07 00:25:54','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1NjU5MDU4MzYw'),(83,'','2015-03-07 00:25:59',NULL,NULL,'2015-03-07 00:25:59','',NULL,NULL,'127.0.0.1',2,'cmlja3p4OTg6cnc6MTQyNTY1OTE1ODg3NA=='),(84,'\0','2015-03-07 00:29:55',NULL,NULL,'2015-03-07 00:29:55','',NULL,'2015-03-07 00:30:24','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1NjU5Mzk1MTQ0'),(85,'','2015-03-07 00:30:28',NULL,NULL,'2015-03-07 00:30:28','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjU5NDI4MTk0'),(86,'','2015-03-07 04:05:19',NULL,NULL,'2015-03-07 04:05:19','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjcyMzE4OTg0'),(87,'','2015-03-07 04:05:50',NULL,NULL,'2015-03-07 04:05:50','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjcyMzUwMzY2'),(88,'\0','2015-03-07 05:23:27',NULL,NULL,'2015-03-07 05:23:27','',NULL,'2015-03-07 08:59:34','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1Njc3MDA2ODE4'),(89,'\0','2015-03-07 08:59:46',NULL,NULL,'2015-03-07 08:59:46','',NULL,'2015-03-07 09:40:37','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpKRVJLYWxsc3Rhcjk4OjE0MjU2ODk5ODU4MDc='),(90,'','2015-03-07 09:34:06',NULL,NULL,'2015-03-07 09:34:06','',NULL,NULL,'127.0.0.1',11,'Uk0xOnNzZjoxNDI1NjkyMDQ1NzQ0'),(91,'','2015-03-07 09:40:39',NULL,NULL,'2015-03-07 09:40:39','',NULL,NULL,'127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NjkyNDM5MDgx'),(92,'\0','2015-03-07 09:57:12',NULL,NULL,'2015-03-07 09:57:12','',NULL,'2015-03-07 10:16:21','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpzYToxNDI1NjkzNDMyMzYz'),(93,'','2015-03-07 10:16:26',NULL,NULL,'2015-03-07 10:16:26','',NULL,NULL,'127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpmczoxNDI1Njk0NTg1NjU1'),(94,'','2015-03-07 10:40:30',NULL,NULL,'2015-03-07 10:40:30','',NULL,NULL,'127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1Njk2MDI5ODg5'),(95,'\0','2015-03-07 10:42:06',NULL,NULL,'2015-03-07 10:42:06','',NULL,'2015-03-07 10:49:32','192.168.1.10',2,'cmlja3p4OTg6ZGdqazoxNDI1Njk2MTI2MTQx'),(96,'','2015-03-07 10:50:28',NULL,NULL,'2015-03-07 10:50:28','',NULL,NULL,'192.168.1.10',8,'TUFSSUxPVV9TQUdVTjpuZ3Y6MTQyNTY5NjYyODEzOQ=='),(97,'','2015-03-07 10:52:55',NULL,NULL,'2015-03-07 10:52:55','',NULL,NULL,'192.168.1.10',8,'TUFSSUxPVV9TQUdVTjpuZ3Y6MTQyNTY5Njc3NTEyMw=='),(98,'','2015-03-07 10:55:45',NULL,NULL,'2015-03-07 10:55:45','',NULL,NULL,'192.168.1.10',8,'TUFSSUxPVV9TQUdVTjpuZ3Y6MTQyNTY5Njk0NDY3Mg=='),(99,'','2015-03-07 10:56:31',NULL,NULL,'2015-03-07 10:56:31','',NULL,NULL,'192.168.1.10',8,'TUFSSUxPVV9TQUdVTjpmeXU6MTQyNTY5Njk5MTMzNA=='),(100,'\0','2015-03-07 10:59:47',NULL,NULL,'2015-03-07 10:59:47','',NULL,'2015-03-07 16:34:40','192.168.1.10',2,'cmlja3p4OTg6bmd2OjE0MjU2OTcxODY5OTY='),(101,'\0','2015-03-07 13:41:34',NULL,NULL,'2015-03-07 13:41:34','',NULL,'2015-03-07 19:04:41','192.168.1.2',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NzA2ODk0Mjk2'),(102,'','2015-03-07 13:41:50',NULL,NULL,'2015-03-07 13:41:50','',NULL,NULL,'192.168.1.2',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NzA2OTEwMTA0'),(103,'\0','2015-03-07 13:42:44',NULL,NULL,'2015-03-07 13:42:44','',NULL,'2015-03-07 15:31:22','192.168.1.2',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NzA2OTYzNTMw'),(104,'','2015-03-07 15:31:34',NULL,NULL,'2015-03-07 15:31:34','',NULL,NULL,'192.168.1.2',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU3MTM0OTQxNTc='),(105,'','2015-03-07 18:34:56',NULL,NULL,'2015-03-07 18:34:56','',NULL,NULL,'192.168.1.12',2,'cmlja3p4OTg6MTIzNDoxNDI1NzI0NDk2Mjg1'),(106,'','2015-03-07 19:04:45',NULL,NULL,'2015-03-07 19:04:45','',NULL,NULL,'192.168.1.2',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU3MjYyODUzMzc='),(107,'\0','2015-03-08 05:48:23',NULL,NULL,'2015-03-08 05:48:23','',NULL,'2015-03-08 05:50:07','192.168.1.11',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NzY0OTAyOTg1'),(108,'','2015-03-08 09:22:56',NULL,NULL,'2015-03-08 09:22:56','',NULL,NULL,'192.168.1.2',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1Nzc3Nzc2MTU2'),(109,'','2015-03-08 10:55:28',NULL,NULL,'2015-03-08 10:55:28','',NULL,NULL,'192.168.1.11',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1NzgzMzI3OTc4'),(110,'','2015-03-08 12:14:46',NULL,NULL,'2015-03-08 12:14:46','',NULL,NULL,'192.168.1.2',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1Nzg4MDg2MjQz'),(111,'','2015-03-08 21:15:51',NULL,NULL,'2015-03-08 21:15:51','',NULL,NULL,'192.168.1.11',2,'cmlja3p4OTg6MTIzNDoxNDI1ODIwNTUwNDgx'),(112,'\0','2015-03-08 22:03:30',NULL,NULL,'2015-03-08 22:03:29','',NULL,'2015-03-08 22:03:39','192.168.1.5',2,'cmlja3p4OTg6MTIzNDoxNDI1ODIzNDA5MzUw'),(113,'','2015-03-08 22:04:07',NULL,NULL,'2015-03-08 22:04:07','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODIzNDQ2NTQ0'),(114,'','2015-03-09 01:46:39',NULL,NULL,'2015-03-09 01:46:39','',NULL,NULL,'192.168.1.11',2,'cmlja3p4OTg6MTIzNDoxNDI1ODM2Nzk4Mzc0'),(115,'\0','2015-03-09 03:57:12',NULL,NULL,'2015-03-09 03:57:12','',NULL,'2015-03-09 03:57:54','192.168.1.5',2,'cmlja3p4OTg6MTIzNDoxNDI1ODQ0NjMyMTg5'),(116,'','2015-03-09 03:57:59',NULL,NULL,'2015-03-09 03:57:59','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODQ0Njc4NTI3'),(117,'\0','2015-03-09 04:42:31',NULL,NULL,'2015-03-09 04:42:31','',NULL,'2015-03-09 05:28:35','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODQ3MzUxMzY0'),(118,'','2015-03-09 05:28:37',NULL,NULL,'2015-03-09 05:28:37','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODUwMTE2NTU0'),(119,'','2015-03-09 06:05:01',NULL,NULL,'2015-03-09 06:05:01','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODUyMzAxMjIy'),(120,'','2015-03-09 06:05:39',NULL,NULL,'2015-03-09 06:05:39','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODUyMzM5MTA0'),(121,'','2015-03-09 06:41:31',NULL,NULL,'2015-03-09 06:41:31','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODU0NDkxMDky'),(122,'','2015-03-09 07:04:03',NULL,NULL,'2015-03-09 07:04:03','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODU1ODQyMjY5'),(123,'','2015-03-09 08:12:18',NULL,NULL,'2015-03-09 08:12:18','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1ODU5OTM3NzEw'),(124,'','2015-03-09 08:35:06',NULL,NULL,'2015-03-09 08:35:06','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODYxMzA1NTUw'),(125,'','2015-03-09 08:36:45',NULL,NULL,'2015-03-09 08:36:45','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODYxNDA1MTMz'),(126,'','2015-03-09 09:46:43',NULL,NULL,'2015-03-09 09:46:43','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODY1NjAzMDc5'),(127,'','2015-03-09 10:31:12',NULL,NULL,'2015-03-09 10:31:12','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1ODY4MjcxODAy'),(128,'','2015-03-09 10:48:57',NULL,NULL,'2015-03-09 10:48:57','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODY5MzM3MjMw'),(129,'','2015-03-09 11:03:05',NULL,NULL,'2015-03-09 11:03:05','',NULL,NULL,'192.168.1.11',2,'cmlja3p4OTg6MTIzNDoxNDI1ODcwMTg1MjIy'),(130,'','2015-03-09 11:05:28',NULL,NULL,'2015-03-09 11:05:28','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1ODcwMzI4MDQy'),(131,'\0','2015-03-09 11:07:47',NULL,NULL,'2015-03-09 11:07:47','',NULL,'2015-03-09 11:08:14','192.168.1.5',2,'cmlja3p4OTg6MTIzNDoxNDI1ODcwNDY2NTE1'),(132,'','2015-03-09 11:08:19',NULL,NULL,'2015-03-09 11:08:19','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1ODcwNDk4Nzk1'),(133,'\0','2015-03-09 21:30:50',NULL,NULL,'2015-03-09 21:30:50','',NULL,'2015-03-09 23:47:26','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1OTA3ODUwMjEz'),(134,'','2015-03-09 23:43:59',NULL,NULL,'2015-03-09 23:43:59','',NULL,NULL,'192.168.1.5',11,'Uk0xOnNkOjE0MjU5MTU4MzkyNzY='),(135,'\0','2015-03-09 23:47:29',NULL,NULL,'2015-03-09 23:47:29','',NULL,'2015-03-10 00:53:58','192.168.1.5',11,'Uk0xOnNzZjoxNDI1OTE2MDQ5NDA3'),(136,'\0','2015-03-10 00:54:05',NULL,NULL,'2015-03-10 00:54:05','',NULL,'2015-03-10 00:58:22','192.168.1.5',2,'cmlja3p4OTg6MTIzNDoxNDI1OTIwMDQ0NzEz'),(137,'\0','2015-03-10 00:58:26',NULL,NULL,'2015-03-10 00:58:26','',NULL,'2015-03-10 01:30:02','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpzZjoxNDI1OTIwMzA2Mzgw'),(138,'\0','2015-03-10 01:30:21',NULL,NULL,'2015-03-10 01:30:21','',NULL,'2015-03-10 01:48:04','192.168.1.5',2,'cmlja3p4OTg6MTIzNEthbGxzdGFyOTg6MTQyNTkyMjIyMDk4MQ=='),(139,'\0','2015-03-10 01:48:05',NULL,NULL,'2015-03-10 01:48:05','',NULL,'2015-03-10 01:57:14','192.168.1.5',2,'cmlja3p4OTg6MTIzNEthbGxzdGFyOTg6MTQyNTkyMzI4NTM1MQ=='),(140,'\0','2015-03-10 01:57:18',NULL,NULL,'2015-03-10 01:57:18','',NULL,'2015-03-10 02:07:25','192.168.1.5',11,'Uk0xOnNzZjoxNDI1OTIzODM3NzYy'),(141,'\0','2015-03-10 01:57:44',NULL,NULL,'2015-03-10 01:57:44','',NULL,'2015-03-10 02:07:31','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjphc2Q6MTQyNTkyMzg2NDA1OA=='),(142,'\0','2015-03-10 02:10:32',NULL,NULL,'2015-03-10 02:10:32','',NULL,'2015-03-10 02:11:36','192.168.1.5',11,'Uk0xOlJNMToxNDI1OTI0NjMyMTIw'),(143,'\0','2015-03-10 02:11:37',NULL,NULL,'2015-03-10 02:11:37','',NULL,'2015-03-10 02:11:41','192.168.1.5',11,'Uk0xOlJNMToxNDI1OTI0Njk3MzEy'),(144,'\0','2015-03-10 02:11:53',NULL,NULL,'2015-03-10 02:11:53','',NULL,'2015-03-10 02:12:09','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTI0NzEzMTE2'),(145,'\0','2015-03-10 02:12:28',NULL,NULL,'2015-03-10 02:12:28','',NULL,'2015-03-10 02:17:52','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTI0NzQ4Mzc2'),(146,'\0','2015-03-10 02:17:56',NULL,NULL,'2015-03-10 02:17:56','',NULL,'2015-03-10 02:18:12','192.168.1.5',11,'Uk0xOlJNMToxNDI1OTI1MDc1NTA4'),(147,'\0','2015-03-10 02:18:15',NULL,NULL,'2015-03-10 02:18:15','',NULL,'2015-03-10 02:18:28','192.168.1.5',12,'amVyaWNvR006amVyaWNvR006MTQyNTkyNTA5NTI5MA=='),(148,'\0','2015-03-10 02:18:31',NULL,NULL,'2015-03-10 02:18:31','',NULL,'2015-03-10 02:21:33','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTI1MTEwNTY1'),(149,'\0','2015-03-10 02:21:41',NULL,NULL,'2015-03-10 02:21:41','',NULL,'2015-03-10 02:25:46','192.168.1.5',12,'amVyaWNvR006amVyaWNvR006MTQyNTkyNTMwMTQzMA=='),(150,'','2015-03-10 02:25:53',NULL,NULL,'2015-03-10 02:25:53','',NULL,NULL,'192.168.1.5',13,'UkVZX0NBTkNFUkFOOlJFWV9DQU5DRVJBTjoxNDI1OTI1NTUyNjky'),(151,'\0','2015-03-10 02:55:45',NULL,NULL,'2015-03-10 02:55:45','',NULL,'2015-03-10 02:55:49','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTI3MzQ1MTU5'),(152,'\0','2015-03-10 02:55:53',NULL,NULL,'2015-03-10 02:55:53','',NULL,'2015-03-10 02:55:56','192.168.1.5',11,'Uk0xOlJNMToxNDI1OTI3MzUyNzg2'),(153,'\0','2015-03-10 02:56:01',NULL,NULL,'2015-03-10 02:56:01','',NULL,'2015-03-10 02:56:04','192.168.1.5',12,'amVyaWNvR006amVyaWNvR006MTQyNTkyNzM2MTQzOQ=='),(154,'\0','2015-03-10 02:57:43',NULL,NULL,'2015-03-10 02:57:43','',NULL,'2015-03-10 02:57:48','192.168.1.5',11,'Uk0xOlJNMToxNDI1OTI3NDYzNDQ1'),(155,'\0','2015-03-10 03:06:31',NULL,NULL,'2015-03-10 03:06:31','',NULL,'2015-03-10 03:16:47','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTI3OTkwNTI1'),(156,'','2015-03-10 03:16:54',NULL,NULL,'2015-03-10 03:16:54','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5Mjg2MTM1NTE='),(157,'','2015-03-10 04:04:41',NULL,NULL,'2015-03-10 04:04:41','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzE0ODEyNTY='),(158,'','2015-03-10 04:13:31',NULL,NULL,'2015-03-10 04:13:31','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTMyMDEwNjgz'),(159,'','2015-03-10 04:38:45',NULL,NULL,'2015-03-10 04:38:45','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzM1MjUzOTk='),(160,'','2015-03-10 04:42:57',NULL,NULL,'2015-03-10 04:42:57','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzM2NzA2Njc='),(169,'','2015-03-10 04:49:31',NULL,NULL,'2015-03-10 04:49:31','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzQxNzEwNzg='),(170,'','2015-03-10 05:02:15',NULL,NULL,'2015-03-10 05:02:15','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzQ5MzU0NzY='),(171,'','2015-03-10 05:05:55',NULL,NULL,'2015-03-10 05:05:55','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzUxNTUwNDY='),(172,'','2015-03-10 05:06:15',NULL,NULL,'2015-03-10 05:06:15','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzUxNzQ3MDc='),(173,'','2015-03-10 05:06:53',NULL,NULL,'2015-03-10 05:06:53','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzUyMTI1NzI='),(174,'\0','2015-03-10 05:08:08',NULL,NULL,'2015-03-10 05:08:08','',NULL,'2015-03-10 06:29:46','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5MzUyODgzMzc='),(175,'\0','2015-03-10 06:29:48',NULL,NULL,'2015-03-10 06:29:48','',NULL,'2015-03-10 06:32:06','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NDAxODc5NDM='),(176,'','2015-03-10 06:32:07',NULL,NULL,'2015-03-10 06:32:07','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NDAzMjczOTI='),(177,'\0','2015-03-10 08:48:38',NULL,NULL,'2015-03-10 08:48:38','',NULL,'2015-03-10 08:48:49','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTQ4NTE3NTQ3'),(178,'','2015-03-10 08:48:55',NULL,NULL,'2015-03-10 08:48:55','',NULL,NULL,'127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NDg1MzUzMDM='),(179,'\0','2015-03-10 13:14:57',NULL,NULL,'2015-03-10 13:14:57','',NULL,'2015-03-10 13:45:41','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NjQ0OTcyMTY='),(180,'\0','2015-03-10 13:45:48',NULL,NULL,'2015-03-10 13:45:48','',NULL,'2015-03-10 14:44:46','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTY2MzQ3ODU0'),(181,'','2015-03-10 13:47:40',NULL,NULL,'2015-03-10 13:47:40','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTY2NDYwMzc0'),(182,'\0','2015-03-10 14:44:51',NULL,NULL,'2015-03-10 14:44:51','',NULL,'2015-03-10 14:49:56','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5Njk4OTA4NjU='),(183,'\0','2015-03-10 14:50:01',NULL,NULL,'2015-03-10 14:50:01','',NULL,'2015-03-10 15:21:35','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTcwMjAxMjIw'),(184,'\0','2015-03-10 15:23:26',NULL,NULL,'2015-03-10 15:23:26','',NULL,'2015-03-10 15:23:35','192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTcyMjA1OTYx'),(185,'\0','2015-03-10 15:23:40',NULL,NULL,'2015-03-10 15:23:40','',NULL,'2015-03-10 16:21:20','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NzIyMTk5MjQ='),(186,'\0','2015-03-10 16:21:25',NULL,NULL,'2015-03-10 16:21:25','',NULL,'2015-03-10 17:14:27','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5NzU2ODUzNjA='),(187,'','2015-03-10 16:50:19',NULL,NULL,'2015-03-10 16:50:19','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTc3NDE4NzEw'),(188,'','2015-03-10 16:53:58',NULL,NULL,'2015-03-10 16:53:58','',NULL,NULL,'192.168.1.5',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI1OTc3NjM3NjAz'),(189,'\0','2015-03-10 17:14:31',NULL,NULL,'2015-03-10 17:14:31','',NULL,'2015-03-10 17:36:27','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5Nzg4NzA3NjI='),(190,'\0','2015-03-10 17:36:29',NULL,NULL,'2015-03-10 17:36:29','',NULL,'2015-03-10 17:38:51','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5ODAxODg3MTA='),(191,'\0','2015-03-10 17:38:57',NULL,NULL,'2015-03-10 17:38:57','',NULL,'2015-03-10 17:41:56','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5ODAzMzY5MTk='),(192,'\0','2015-03-10 17:42:03',NULL,NULL,'2015-03-10 17:42:03','',NULL,'2015-03-10 17:46:47','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5ODA1MjMwODE='),(193,'\0','2015-03-10 17:46:51',NULL,NULL,'2015-03-10 17:46:51','',NULL,'2015-03-10 17:55:36','192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5ODA4MTEzODc='),(194,'','2015-03-10 17:55:38',NULL,NULL,'2015-03-10 17:55:38','',NULL,NULL,'192.168.1.5',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjU5ODEzMzc4Njc='),(195,'\0','2015-03-11 07:47:19',NULL,NULL,'2015-03-11 07:47:19','',NULL,'2015-03-11 08:03:39','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzEyMzkxNzk='),(196,'\0','2015-03-11 08:03:43',NULL,NULL,'2015-03-11 08:03:43','',NULL,'2015-03-11 08:21:09','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI2MDMyMjIzMTYz'),(197,'\0','2015-03-11 08:21:13',NULL,NULL,'2015-03-11 08:21:13','',NULL,'2015-03-11 08:22:46','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzMyNzMwMDQ='),(198,'\0','2015-03-11 08:22:50',NULL,NULL,'2015-03-11 08:22:50','',NULL,'2015-03-11 08:23:57','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI2MDMzMzY5Njky'),(199,'\0','2015-03-11 08:24:04',NULL,NULL,'2015-03-11 08:24:04','',NULL,'2015-03-11 08:59:48','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzM0NDQ0MzY='),(200,'\0','2015-03-11 08:59:51',NULL,NULL,'2015-03-11 08:59:51','',NULL,'2015-03-11 09:37:52','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzU1OTEzNDc='),(201,'\0','2015-03-11 09:37:56',NULL,NULL,'2015-03-11 09:37:56','',NULL,'2015-03-11 09:49:55','127.0.0.1',12,'amVyaWNvR006amVyaWNvR006MTQyNjAzNzg3NjQyNA=='),(202,'\0','2015-03-11 09:50:03',NULL,NULL,'2015-03-11 09:50:03','',NULL,'2015-03-11 11:33:00','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzg2MDI2Njk='),(203,'','2015-03-11 10:12:36',NULL,NULL,'2015-03-11 10:12:36','',NULL,NULL,'127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzk5NTU3NDc='),(204,'','2015-03-11 10:13:10',NULL,NULL,'2015-03-11 10:13:10','',NULL,NULL,'127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwMzk5OTAwMDc='),(205,'\0','2015-03-11 11:35:37',NULL,NULL,'2015-03-11 11:35:37','',NULL,'2015-03-11 11:45:43','127.0.0.1',2,'cmlja3p4OTg6SkVSS2FsbHN0YXI5ODoxNDI2MDQ0OTM3MjM2'),(206,'\0','2015-03-11 11:45:58',NULL,NULL,'2015-03-11 11:45:58','',NULL,'2015-03-11 11:49:10','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDU1NTc1MDk='),(207,'\0','2015-03-11 11:49:13',NULL,NULL,'2015-03-11 11:49:13','',NULL,'2015-03-11 11:53:21','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDU3NTMxNTE='),(208,'\0','2015-03-11 11:53:28',NULL,NULL,'2015-03-11 11:53:28','',NULL,'2015-03-11 11:55:38','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDYwMDg0MzA='),(209,'\0','2015-03-11 11:55:44',NULL,NULL,'2015-03-11 11:55:44','',NULL,'2015-03-11 12:10:54','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDYxNDM2NDU='),(210,'\0','2015-03-11 12:11:34',NULL,NULL,'2015-03-11 12:11:34','',NULL,'2015-03-11 12:13:13','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDcwOTM1OTA='),(211,'\0','2015-03-11 12:17:53',NULL,NULL,'2015-03-11 12:17:53','',NULL,'2015-03-11 19:03:45','127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNDc0NzI4NTM='),(212,'','2015-03-11 19:03:48',NULL,NULL,'2015-03-11 19:03:48','',NULL,NULL,'127.0.0.1',8,'TUFSSUxPVV9TQUdVTjpNQVJJTE9VX1NBR1VOOjE0MjYwNzE4MjgzMjI=');
/*!40000 ALTER TABLE `flow_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_style`
--

DROP TABLE IF EXISTS `flow_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_style` (
  `flow_style_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `flow_style_field` varchar(255) NOT NULL,
  `flow_style_name` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `flow_style_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`flow_style_id`),
  UNIQUE KEY `UK_fi2wuto6qnqux51qmimo5xdys` (`flow_style_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_style`
--

LOCK TABLES `flow_style` WRITE;
/*!40000 ALTER TABLE `flow_style` DISABLE KEYS */;
INSERT INTO `flow_style` VALUES (1,'2014-11-14 02:52:13',NULL,NULL,'opacity','translucents',NULL,'2014-11-14 13:32:31','0.8');
/*!40000 ALTER TABLE `flow_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_style_asc`
--

DROP TABLE IF EXISTS `flow_style_asc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_style_asc` (
  `flow_style_asc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `flow_comp_id` bigint(20) NOT NULL,
  `start_dn` datetime DEFAULT NULL,
  `flow_style_id` bigint(20) NOT NULL,
  `flow_comp_type` varchar(255) NOT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`flow_style_asc_id`),
  UNIQUE KEY `UK_g5qghd9rh2sq25jqnjfmbyx7q` (`flow_comp_id`,`flow_comp_type`,`flow_style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_style_asc`
--

LOCK TABLES `flow_style_asc` WRITE;
/*!40000 ALTER TABLE `flow_style_asc` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_style_asc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_summary`
--

DROP TABLE IF EXISTS `flow_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_summary` (
  `flow_summary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `execution_time` bigint(20) NOT NULL,
  `object_details` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`flow_summary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_summary`
--

LOCK TABLES `flow_summary` WRITE;
/*!40000 ALTER TABLE `flow_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_task`
--

DROP TABLE IF EXISTS `flow_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_task` (
  `flow_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `default_active` bit(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `flow_glyph` varchar(255) NOT NULL,
  `default_locked` bit(1) DEFAULT NULL,
  `flow_name` varchar(255) NOT NULL,
  `default_pinned` bit(1) DEFAULT NULL,
  `default_size` int(11) DEFAULT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `flow_title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `default_show_tool_bar` bit(1) DEFAULT NULL,
  `style_uri` varchar(255) DEFAULT NULL,
  `defalt_page` varchar(255) DEFAULT NULL,
  `default_page_param` varchar(255) DEFAULT NULL,
  `default_flow_id` varchar(255) DEFAULT NULL,
  `flow_task_lazyload` bit(1) DEFAULT NULL,
  `flow_task_module_files` text,
  `flow_task_module_js` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`flow_id`),
  UNIQUE KEY `UK_cmj2a3yj6x4l6h4nkvl19ci67` (`flow_name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_task`
--

LOCK TABLES `flow_task` WRITE;
/*!40000 ALTER TABLE `flow_task` DISABLE KEYS */;
INSERT INTO `flow_task` VALUES (1,NULL,'2014-11-03 11:02:03',NULL,NULL,'octicon octicon-browser',NULL,'page_task',NULL,NULL,'2015-02-27 04:24:05','Pages','2015-03-11 11:43:46',NULL,'services/flow_style_query?compId=1&compType=task',NULL,NULL,'2680',NULL,NULL,NULL),(6,NULL,'2014-11-11 21:25:28',NULL,NULL,'fa fa-tasks',NULL,'tp_task',NULL,NULL,'2015-02-27 04:22:16','Tasks','2015-03-10 18:07:56',NULL,'services/flow_style_query?compId=6&compType=task',NULL,NULL,'2640',NULL,NULL,NULL),(7,NULL,'2014-11-13 07:57:50',NULL,NULL,'fa fa-puzzle-piece',NULL,'module_task',NULL,NULL,'2015-02-26 23:46:32','Modules','2015-03-10 06:45:18',NULL,'services/flow_style_query?compId=7&compType=task',NULL,NULL,'2599',NULL,NULL,NULL),(8,NULL,'2014-11-13 14:03:59',NULL,NULL,'fa fa-heart',NULL,'module_group_task',NULL,NULL,'2015-02-27 04:22:16','Module Groups','2015-03-10 08:48:43',NULL,'services/flow_style_query?compId=8&compType=task',NULL,NULL,'2606',NULL,NULL,NULL),(9,NULL,'2014-11-16 07:01:54',NULL,NULL,'octicon octicon-gist-secret',NULL,'profile_task',NULL,NULL,'2015-02-27 02:36:36','Profiles','2015-03-10 14:50:06',NULL,'services/flow_style_query?compId=9&compType=task',NULL,NULL,'2626','\0',NULL,NULL),(10,NULL,'2014-11-17 00:20:28',NULL,NULL,'fa fa-users',NULL,'group_task',NULL,NULL,'2015-02-27 02:36:36','Groups','2015-03-10 06:28:16',NULL,'services/flow_style_query?compId=10&compType=task',NULL,NULL,'2589',NULL,NULL,NULL),(11,NULL,'2014-11-17 10:18:31',NULL,NULL,'fa fa-user',NULL,'usr_mgr_task',NULL,NULL,'2015-02-26 13:08:00','User','2015-03-10 02:15:13',NULL,'services/flow_style_query?compId=11&compType=task',NULL,NULL,'2565',NULL,NULL,NULL),(14,NULL,'2014-12-16 04:11:19',NULL,NULL,'octicon octicon-file-media',NULL,'edit_profile',NULL,NULL,'2015-02-27 02:07:37','Edit profile','2015-03-06 22:25:18',NULL,'services/flow_style_query?compId=14&compType=task',NULL,NULL,NULL,NULL,NULL,NULL),(15,NULL,'2014-12-28 16:32:40',NULL,NULL,'fa fa-bell',NULL,'notification',NULL,NULL,'2015-02-24 23:53:47','Notification Center','2015-03-07 00:30:13',NULL,'services/flow_style_query?compId=15&compType=task',NULL,NULL,NULL,NULL,NULL,NULL),(16,NULL,'2015-01-11 21:50:20',NULL,NULL,'fa fa-cog',NULL,'agent_task',NULL,NULL,'2015-02-26 13:08:01','Agent','2015-03-11 09:49:03',NULL,'services/flow_style_query?compId=16&compType=task',NULL,NULL,'2672','','js/controller/rex/management/agent-controller.js','agentController'),(17,NULL,'2015-01-12 06:59:55',NULL,NULL,'fa fa-university',NULL,'school_task',NULL,NULL,'2015-02-25 02:11:51','School','2015-03-11 10:14:39',NULL,'services/flow_style_query?compId=17&compType=task',NULL,NULL,'2679','','js/controller/rex/data/school-controller.js','schoolController'),(18,NULL,'2015-01-13 06:12:23',NULL,NULL,'fa fa-globe',NULL,'region_task',NULL,NULL,'2015-02-27 04:22:16','Region','2015-03-11 09:49:04',NULL,'services/flow_style_query?compId=18&compType=task',NULL,NULL,'2674','','js/controller/rex/data/region-controller.js','regionController'),(19,NULL,'2015-01-14 03:27:23',NULL,NULL,'octicon octicon-organization',NULL,'customer_task',NULL,NULL,'2015-02-27 02:28:26','Customer','2015-03-11 09:49:04',NULL,'services/flow_style_query?compId=19&compType=task',NULL,NULL,'2673','','js/controller/rex/management/customer-controller.js','customerController'),(20,NULL,'2015-01-19 15:40:01',NULL,NULL,'fa fa-sitemap',NULL,'position_task',NULL,NULL,'2015-02-25 02:11:51','Position','2015-03-10 14:11:55',NULL,'services/flow_style_query?compId=20&compType=task',NULL,NULL,'2616','','js/controller/rex/data/position-controller.js','positionController'),(21,NULL,'2015-01-20 03:51:54',NULL,NULL,'fa fa-star-o',NULL,'level_task',NULL,NULL,'2015-02-27 02:37:57','Education Level','2015-03-10 14:11:52',NULL,'services/flow_style_query?compId=21&compType=task',NULL,NULL,'2615','','js/controller/rex/data/level-controller.js','levelController'),(22,NULL,'2015-01-28 16:49:18',NULL,NULL,'octicon octicon-mortar-board',NULL,'school_year_task',NULL,NULL,'2015-02-26 13:01:07','School year','2015-03-11 09:49:07',NULL,'services/flow_style_query?compId=22&compType=task',NULL,NULL,'2676','','js/controller/rex/activity/school-year-controller.js','schoolYearController'),(23,NULL,'2015-01-29 03:52:52',NULL,NULL,'fa fa-calendar-o',NULL,'planner_task',NULL,NULL,'2015-02-27 02:28:26','Planner','2015-03-11 10:12:24',NULL,'services/flow_style_query?compId=23&compType=task',NULL,NULL,'2678','','js/controller/rex/activity/planner.js,app/activity/Planner/planner.css,app/activity/Planner/planner-helper.js','plannerModule'),(24,NULL,'2015-01-31 08:26:38',NULL,NULL,'fa fa-bicycle',NULL,'daily_task',NULL,NULL,'2015-02-26 11:41:05','Daily','2015-03-11 09:34:57',NULL,'services/flow_style_query?compId=24&compType=task',NULL,NULL,'2665','','js/controller/rex/activity/daily-controller.js','dailyController'),(25,NULL,'2015-02-06 02:08:10',NULL,NULL,'fa fa-table',NULL,'report_task',NULL,NULL,'2015-02-26 13:02:00','Reports','2015-03-11 09:49:09',NULL,'services/flow_style_query?compId=25&compType=task',NULL,NULL,NULL,'','js/controller/rex/reports/reports-controller.js,app/report/report.css','reportsController'),(26,NULL,'2015-02-19 08:48:02',NULL,NULL,'fa fa-gears',NULL,'setup_task',NULL,NULL,'2015-02-27 04:23:56','Setup','2015-03-10 18:04:58',NULL,'services/flow_style_query?compId=26&compType=task',NULL,NULL,'2635','','js/controller/flow/Setup/setup-controller.js','setup'),(27,NULL,'2015-02-23 01:53:34',NULL,NULL,'fa fa-table',NULL,'report_task_customer',NULL,NULL,'2015-02-26 13:01:07','Reports','2015-03-11 09:49:09',NULL,'services/flow_style_query?compId=27&compType=task',NULL,NULL,'2677','','js/controller/rex/reports/reports-controller.js,app/report/report.css','reportsController'),(28,NULL,'2015-02-23 08:43:21',NULL,NULL,'fa fa-book',NULL,'customer_market_summary_task',NULL,NULL,NULL,'Customer Summary','2015-02-23 08:43:21',NULL,'services/flow_style_query?compId=28&compType=task',NULL,NULL,NULL,'','js/controller/rex/activity/customer-summary-controller.js','customerSummary'),(29,NULL,'2015-02-26 12:38:08',NULL,NULL,'fa fa-globe',NULL,'region_manager_task',NULL,NULL,'2015-02-27 05:29:49','Region','2015-03-10 01:57:22',NULL,'services/flow_style_query?compId=29&compType=task',NULL,NULL,'2557','','js/controller/rex/management/region-manager-controller.js,app/management/Region-manager/region-manager.css','regionManager'),(30,NULL,'2015-03-07 09:44:00',NULL,NULL,'octicon octicon-organization',NULL,'customer_agent_task',NULL,NULL,NULL,'Customer','2015-03-11 19:07:20',NULL,'services/flow_style_query?compId=30&compType=task',NULL,NULL,'2685','','js/controller/rex/management/agent-controller.js, app/management/Agent/agent_customer.css,js/controller/rex/management/customer-controller.js,js/controller/rex/activity/daily-controller.js','agentController');
/*!40000 ALTER TABLE `flow_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_task_flow_page`
--

DROP TABLE IF EXISTS `flow_task_flow_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_task_flow_page` (
  `flow_task_flow_id` bigint(20) NOT NULL,
  `pages_page_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_task_flow_id`,`pages_page_id`),
  KEY `FK_uptlhr1qdms51uvsob964fp0` (`pages_page_id`),
  CONSTRAINT `FK_ihpht1wfn2k6mk7g9vq06x2ga` FOREIGN KEY (`flow_task_flow_id`) REFERENCES `flow_task` (`flow_id`),
  CONSTRAINT `FK_uptlhr1qdms51uvsob964fp0` FOREIGN KEY (`pages_page_id`) REFERENCES `flow_page` (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_task_flow_page`
--

LOCK TABLES `flow_task_flow_page` WRITE;
/*!40000 ALTER TABLE `flow_task_flow_page` DISABLE KEYS */;
INSERT INTO `flow_task_flow_page` VALUES (1,1),(1,3),(1,4),(6,9),(6,10),(6,11),(7,13),(7,14),(7,15),(8,16),(8,17),(8,18),(11,19),(9,20),(9,21),(9,22),(10,23),(10,24),(10,25),(11,27),(11,28),(14,29),(15,30),(15,31),(16,32),(16,33),(16,34),(17,35),(17,36),(17,37),(18,38),(18,39),(18,40),(19,41),(19,42),(19,43),(20,44),(20,45),(20,46),(21,47),(21,48),(21,49),(22,50),(22,51),(22,52),(23,53),(24,55),(24,56),(25,57),(26,58),(27,59),(29,60),(30,61),(30,62);
/*!40000 ALTER TABLE `flow_task_flow_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_task_flow_style`
--

DROP TABLE IF EXISTS `flow_task_flow_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_task_flow_style` (
  `FlowTask_flow_id` bigint(20) NOT NULL,
  `styles_flow_style_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_bmacw10d082t2vwwy433h9nrf` (`styles_flow_style_id`),
  KEY `FK_f5ck5qbk9r26yfmalyri6h43d` (`FlowTask_flow_id`),
  CONSTRAINT `FK_bmacw10d082t2vwwy433h9nrf` FOREIGN KEY (`styles_flow_style_id`) REFERENCES `flow_style` (`flow_style_id`),
  CONSTRAINT `FK_f5ck5qbk9r26yfmalyri6h43d` FOREIGN KEY (`FlowTask_flow_id`) REFERENCES `flow_task` (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_task_flow_style`
--

LOCK TABLES `flow_task_flow_style` WRITE;
/*!40000 ALTER TABLE `flow_task_flow_style` DISABLE KEYS */;
/*!40000 ALTER TABLE `flow_task_flow_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_uploaded_file`
--

DROP TABLE IF EXISTS `flow_uploaded_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_uploaded_file` (
  `flow_uploaded_file_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_uploaded_file_contentType` varchar(255) NOT NULL,
  `flow_uploaded_file_created_dt` datetime NOT NULL,
  `flow_uploaded_file_description` varchar(255) DEFAULT NULL,
  `flow_uploaded_file_end_dt` datetime DEFAULT NULL,
  `flow_uploaded_file_path` varchar(255) NOT NULL,
  `flow_uploaded_file_star_dt` datetime DEFAULT NULL,
  `flow_uploaded_file_type` varchar(255) NOT NULL,
  `flow_uploaded_file_updated_dt` datetime DEFAULT NULL,
  `flow_uploaded_file_name` varchar(255) NOT NULL,
  `flow_uploaded_file_folder` varchar(255) NOT NULL,
  `flow_uploaded_file_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`flow_uploaded_file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_uploaded_file`
--

LOCK TABLES `flow_uploaded_file` WRITE;
/*!40000 ALTER TABLE `flow_uploaded_file` DISABLE KEYS */;
INSERT INTO `flow_uploaded_file` VALUES (25,'image/jpeg','2014-12-15 02:38:47',NULL,NULL,'services/download_service/getContent/25',NULL,'emblem','2015-01-05 00:07:26','_emblemId#25','group',230914),(26,'image/png','2014-12-16 00:34:27',NULL,NULL,'services/download_service/getContent/26',NULL,'emblem','2014-12-16 00:34:27','_emblemId#26','group',8439),(27,'image/jpeg','2014-12-16 05:18:38',NULL,NULL,'services/download_service/getContent/27',NULL,'avatar','2014-12-16 05:18:38','_avatarId#27','group.user',10325),(28,'image/jpeg','2014-12-16 06:07:58',NULL,NULL,'services/download_service/getContent/28',NULL,'avatar','2014-12-16 06:07:58','_avatarId#28','group.user',10325),(29,'image/jpeg','2014-12-16 06:09:11',NULL,NULL,'services/download_service/getContent/29',NULL,'avatar','2014-12-16 06:09:11','_avatarId#29','group.user',10325),(30,'image/jpeg','2014-12-16 06:09:46',NULL,NULL,'services/download_service/getContent/30',NULL,'avatar','2014-12-16 06:09:46','_avatarId#30','group.user',10325),(31,'image/jpeg','2014-12-16 06:10:17',NULL,NULL,'services/download_service/getContent/31',NULL,'avatar','2014-12-16 06:10:17','_avatarId#31','group.user',10325),(32,'image/jpeg','2014-12-16 06:18:02',NULL,NULL,'services/download_service/getContent/32',NULL,'avatar','2014-12-16 06:18:31','_avatarId#32','group.user',10325),(33,'image/jpeg','2014-12-16 06:21:36',NULL,NULL,'services/download_service/getContent/33',NULL,'avatar','2014-12-16 06:21:44','_avatarId#33','group.user',10325),(34,'image/jpeg','2014-12-16 06:22:38',NULL,NULL,'services/download_service/getContent/34',NULL,'avatar','2014-12-16 06:24:02','_avatarId#34','group.user',10325),(35,'image/jpeg','2014-12-16 06:24:51',NULL,NULL,'services/download_service/getContent/35',NULL,'avatar','2014-12-16 06:24:55','_avatarId#35','group.user',10325),(36,'image/jpeg','2014-12-16 06:25:56',NULL,NULL,'services/download_service/getContent/36',NULL,'avatar','2014-12-16 06:26:02','_avatarId#36','group.user',10325),(37,'image/jpeg','2014-12-16 06:28:43',NULL,NULL,'services/download_service/getContent/37',NULL,'avatar','2014-12-16 06:29:32','_avatarId#37','group.user',10325),(38,'image/jpeg','2014-12-16 06:29:43',NULL,NULL,'services/download_service/getContent/38',NULL,'avatar','2014-12-16 06:29:49','_avatarId#38','group.user',10325),(39,'image/jpeg','2014-12-16 06:31:11',NULL,NULL,'services/download_service/getContent/39',NULL,'avatar','2014-12-16 06:31:16','_avatarId#39','group.user',10325),(40,'image/jpeg','2014-12-16 06:32:02',NULL,NULL,'services/download_service/getContent/40',NULL,'avatar','2014-12-16 06:32:02','_avatarId#40','group.user',10325),(41,'image/jpeg','2014-12-16 06:32:20',NULL,NULL,'services/download_service/getContent/41',NULL,'avatar','2014-12-16 06:32:20','_avatarId#41','group.user',10325),(42,'image/jpeg','2014-12-16 06:34:28',NULL,NULL,'services/download_service/getContent/42',NULL,'avatar','2015-02-10 05:51:38','_avatarId#42','group.user',1306525),(43,'image/png','2014-12-19 06:55:47',NULL,NULL,'services/download_service/getContent/43',NULL,'emblem','2014-12-19 06:55:47','_emblemId#43','group',5482),(44,'image/png','2015-01-04 23:44:27',NULL,NULL,'services/download_service/getContent/44',NULL,'emblem','2015-01-04 23:44:27','_emblemId#44','group',230914),(45,'image/png','2015-01-04 23:47:19',NULL,NULL,'services/download_service/getContent/45',NULL,'emblem','2015-01-04 23:47:19','_emblemId#45','group',230914),(46,'image/png','2015-01-05 00:57:29',NULL,NULL,'services/download_service/getContent/46',NULL,'emblem','2015-01-05 00:57:29','_emblemId#46','group',230914),(47,'image/png','2015-01-11 22:44:00',NULL,NULL,'services/download_service/getContent/47',NULL,'emblem','2015-01-11 22:44:00','_emblemId#47','group',230914),(48,'image/png','2015-01-11 23:47:46',NULL,NULL,'services/download_service/getContent/48',NULL,'emblem','2015-01-11 23:47:46','_emblemId#48','group',230914),(49,'image/png','2015-01-12 00:00:32',NULL,NULL,'services/download_service/getContent/49',NULL,'avatar','2015-01-12 00:00:32','_avatarId#49','group.user',230914),(50,'image/png','2015-01-12 00:01:02',NULL,NULL,'services/download_service/getContent/50',NULL,'avatar','2015-01-12 00:01:02','_avatarId#50','group.user',230914),(51,'image/png','2015-01-12 00:01:46',NULL,NULL,'services/download_service/getContent/51',NULL,'avatar','2015-01-12 00:01:46','_avatarId#51','group.user',230914),(52,'image/png','2015-01-12 00:01:56',NULL,NULL,'services/download_service/getContent/52',NULL,'avatar','2015-01-12 00:01:56','_avatarId#52','group.user',230914),(53,'image/jpeg','2015-01-12 05:34:45',NULL,NULL,'services/download_service/getContent/53',NULL,'avatar','2015-01-12 05:34:45','_avatarId#53','group.user',10325),(54,'image/jpeg','2015-01-12 05:35:03',NULL,NULL,'services/download_service/getContent/54',NULL,'avatar','2015-01-12 05:35:03','_avatarId#54','group.user',10325),(55,'image/png','2015-01-12 08:13:57',NULL,NULL,'services/download_service/getContent/55',NULL,'emblem','2015-01-12 08:13:57','_emblemId#55','group',8439),(56,'image/jpeg','2015-01-12 08:19:43',NULL,NULL,'services/download_service/getContent/56',NULL,'emblem','2015-01-12 08:19:43','_emblemId#56','group',22068),(57,'image/jpeg','2015-02-01 23:53:26',NULL,NULL,'services/download_service/getContent/57',NULL,'avatar','2015-02-01 23:53:26','_avatarId#57','group.user',10325),(58,'image/png','2015-02-24 23:49:44',NULL,NULL,'services/download_service/getContent/58',NULL,'emblem','2015-02-24 23:49:44','_emblemId#58','group',230914),(59,'image/png','2015-02-26 12:44:49',NULL,NULL,'services/download_service/getContent/59',NULL,'emblem','2015-02-26 12:44:49','_emblemId#59','group',230914),(60,'image/jpeg','2015-02-26 18:32:09',NULL,NULL,'services/download_service/getContent/60',NULL,'avatar','2015-02-26 18:32:09','_avatarId#60','group.user',10325),(61,'image/jpeg','2015-02-26 18:32:32',NULL,NULL,'services/download_service/getContent/61',NULL,'avatar','2015-02-26 18:32:32','_avatarId#61','group.user',10325),(62,'image/jpeg','2015-02-26 18:36:20',NULL,NULL,'services/download_service/getContent/62',NULL,'avatar','2015-02-26 18:36:20','_avatarId#62','group.user',10325),(63,'image/jpeg','2015-02-27 02:07:49',NULL,NULL,'services/download_service/getContent/63',NULL,'avatar','2015-02-27 02:07:49','_avatarId#63','group.user',777835),(64,'image/png','2015-03-06 19:16:36',NULL,NULL,'services/download_service/getContent/64',NULL,'avatar','2015-03-06 19:16:36','_avatarId#64','group.user',8439),(65,'image/png','2015-03-06 19:39:43',NULL,NULL,'services/download_service/getContent/65',NULL,'avatar','2015-03-07 00:30:41','_avatarId#65','group.user',10325),(66,'image/jpeg','2015-03-07 00:30:07',NULL,NULL,'services/download_service/getContent/66',NULL,'avatar','2015-03-07 09:00:25','_avatarId#66','group.user',448921),(132,'image/jpeg','2015-03-10 02:21:55',NULL,NULL,'services/download_service/getContent/132',NULL,'avatar','2015-03-11 09:47:57','_avatarId#132','group.user',354743),(133,'image/jpeg','2015-03-10 02:26:00',NULL,NULL,'services/download_service/getContent/133',NULL,'avatar','2015-03-10 02:26:13','_avatarId#133','group.user',340722),(134,'image/jpeg','2015-03-10 06:37:39',NULL,NULL,'services/download_service/getContent/134',NULL,'emblem','2015-03-10 06:42:21','_emblemId#134','group',448921),(136,'image/jpeg','2015-03-11 09:34:02',NULL,NULL,'services/download_service/getContent/136',NULL,'emblem','2015-03-11 09:34:02','_emblemId#136','group',354743),(137,'image/jpeg','2015-03-11 09:34:10',NULL,NULL,'services/download_service/getContent/137',NULL,'emblem','2015-03-11 09:34:10','_emblemId#137','group',354743);
/*!40000 ALTER TABLE `flow_uploaded_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user`
--

DROP TABLE IF EXISTS `flow_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user` (
  `flow_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flow_email` varchar(255) NOT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `flow_password` text NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `flow_username` varchar(255) NOT NULL,
  `flow_user_detail_id` bigint(20) DEFAULT NULL,
  `flow_user_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_user_id`),
  UNIQUE KEY `UK_48lrnqj5gd1i17bn1aw6ci7qx` (`flow_username`,`flow_email`),
  UNIQUE KEY `UK_oda6faowoupthhgvikhg53c6i` (`flow_email`),
  UNIQUE KEY `UK_j0g6ctrw7lcjxk6ft7yg531yp` (`flow_username`),
  KEY `FK_3r8rce7837lmp1x8d63fa7a5d` (`flow_user_detail_id`),
  KEY `FK_dyuuyq64i9r8a624ddo3ie2is` (`flow_user_group_id`),
  CONSTRAINT `FK_3r8rce7837lmp1x8d63fa7a5d` FOREIGN KEY (`flow_user_detail_id`) REFERENCES `flow_user_detail` (`flow_user_detail_id`),
  CONSTRAINT `FK_dyuuyq64i9r8a624ddo3ie2is` FOREIGN KEY (`flow_user_group_id`) REFERENCES `flow_user_group` (`flow_user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user`
--

LOCK TABLES `flow_user` WRITE;
/*!40000 ALTER TABLE `flow_user` DISABLE KEYS */;
INSERT INTO `flow_user` VALUES (2,'2014-11-28 05:18:22',NULL,'jerico@jsofftechnologies.com',NULL,'2F8795391DD5F60B54462BB4E2BC4365366CD08C46279E30FA655D29C9E91282',NULL,NULL,'rickzx98',5,1),(8,'2015-01-31 01:00:54',NULL,'xxx@xxxxx.xxx',NULL,'931D46BC1D65F110B0CE3ACB1D691E073E834E8AD439BD1BCBD2A957803867FF',NULL,'2015-03-10 04:42:57','MARILOU_SAGUN',11,3),(11,'2015-02-26 12:41:27',NULL,'RM1@RM1.rm',NULL,'895EB64A6AEA63769D1C7764C32399AF30778BBF7A05E380132BC9269D4D00FA',NULL,NULL,'RM1',14,4),(12,'2015-02-26 12:52:18',NULL,'jerico@jsofttechnologies.com',NULL,'069298B033CDC9BB02C18F2E619E703B81E82F51CFD444D99F582F13B21387E5',NULL,NULL,'jericoGM',15,5),(14,'2015-03-10 03:07:27',NULL,'REY_CANCERAN@rex.com',NULL,'F2F64F648C2547312494ED16F598AE3993130B93766A62E92C382D25C95CE072',NULL,NULL,'REY_CANCERAN',17,3);
/*!40000 ALTER TABLE `flow_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_app_setting`
--

DROP TABLE IF EXISTS `flow_user_app_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_app_setting` (
  `flow_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fluid_bg_color` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `fluid_menu` varchar(50) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `fluid_style` varchar(50) NOT NULL,
  `fluid_theme` varchar(50) NOT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `flow_user_id` bigint(20) NOT NULL,
  `fluid_hide_menu` bit(1) DEFAULT NULL,
  `fluid_layout` varchar(255) NOT NULL,
  PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_app_setting`
--

LOCK TABLES `flow_user_app_setting` WRITE;
/*!40000 ALTER TABLE `flow_user_app_setting` DISABLE KEYS */;
INSERT INTO `flow_user_app_setting` VALUES (1,'#CCCCFF','2015-01-02 06:24:19',NULL,NULL,'sidebar-default',NULL,'style1','blue-grey','2015-03-10 16:54:33',2,'','FLUIDSCREEN'),(2,'#FF6A6A','2015-03-06 19:15:55',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(3,'#FF6A6A','2015-03-06 19:16:19',NULL,NULL,'sidebar-default',NULL,'style2','orange-violet','2015-03-10 00:47:24',11,'','FLUIDSCREEN'),(4,'#FF6A6A','2015-03-06 19:51:44',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(5,'#FF6A6A','2015-03-06 20:00:44',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(6,'#FF6A6A','2015-03-06 20:04:01',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(7,'#FF6A6A','2015-03-07 00:23:15',NULL,NULL,'sidebar-default',NULL,'style1','pink-dark','2015-03-11 11:57:28',8,'','FLUIDSCREEN'),(8,'#FF6A6A','2015-03-07 00:26:07',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(9,'#FF6A6A','2015-03-07 00:26:10',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,2,'\0','FLUIDSCREEN'),(10,'#FF6A6A','2015-03-10 02:18:16',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,12,'\0','FLUIDSCREEN'),(11,'#FF6A6A','2015-03-10 02:25:53',NULL,NULL,'sidebar-default',NULL,'style1','yellow-blue',NULL,13,'\0','FLUIDSCREEN');
/*!40000 ALTER TABLE `flow_user_app_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_detail`
--

DROP TABLE IF EXISTS `flow_user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_detail` (
  `flow_user_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `image_uri` varchar(255) DEFAULT NULL,
  `secret_answer` varchar(255) NOT NULL,
  `secret_question` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`flow_user_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_detail`
--

LOCK TABLES `flow_user_detail` WRITE;
/*!40000 ALTER TABLE `flow_user_detail` DISABLE KEYS */;
INSERT INTO `flow_user_detail` VALUES (5,'2014-11-28 05:18:22',NULL,NULL,'Humble User',NULL,'122691','dateofbirth',NULL,'2015-01-11 18:09:12',42),(11,'2015-01-31 01:00:54',NULL,NULL,'MARILOU SAGUN',NULL,'1900-01-01','When is your birthday? (yyyy-mm-dd)',NULL,'2015-03-07 00:30:09',66),(14,'2015-02-26 12:41:27',NULL,NULL,'RegionManager1',NULL,'RM1','When is your birthday? (yyyy-mm-dd)',NULL,'2015-03-06 19:39:44',65),(15,'2015-02-26 12:52:18',NULL,NULL,'Jerico GM',NULL,'122691','when is your birthday?',NULL,'2015-03-10 02:21:57',132),(17,'2015-03-10 03:07:27',NULL,NULL,'REY CANCERAN',NULL,'REY_CANCERAN','When is your birthday? (yyyy-mm-dd)',NULL,NULL,NULL);
/*!40000 ALTER TABLE `flow_user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_flow_user_profile`
--

DROP TABLE IF EXISTS `flow_user_flow_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_flow_user_profile` (
  `flow_user_flow_user_id` bigint(20) NOT NULL,
  `flowUserProfileSet_flow_user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_user_flow_user_id`,`flowUserProfileSet_flow_user_profile_id`),
  KEY `FK_p24xhus3n2hqlt7qmt3yf6foe` (`flowUserProfileSet_flow_user_profile_id`),
  CONSTRAINT `FK_8rbvqxcx1hn5y2t7iaup0x5gt` FOREIGN KEY (`flow_user_flow_user_id`) REFERENCES `flow_user` (`flow_user_id`),
  CONSTRAINT `FK_p24xhus3n2hqlt7qmt3yf6foe` FOREIGN KEY (`flowUserProfileSet_flow_user_profile_id`) REFERENCES `flow_user_profile` (`flow_user_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_flow_user_profile`
--

LOCK TABLES `flow_user_flow_user_profile` WRITE;
/*!40000 ALTER TABLE `flow_user_flow_user_profile` DISABLE KEYS */;
INSERT INTO `flow_user_flow_user_profile` VALUES (2,1),(2,2),(8,3),(14,3),(2,4),(2,5),(11,5),(12,6);
/*!40000 ALTER TABLE `flow_user_flow_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_group`
--

DROP TABLE IF EXISTS `flow_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_group` (
  `flow_user_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `flow_user_group_name` varchar(255) NOT NULL,
  `flow_user_group_title` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `group_admin_fullname` varchar(255) NOT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `owner_user_id` bigint(20) NOT NULL,
  `emails` varchar(255) NOT NULL,
  `emblem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`flow_user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_group`
--

LOCK TABLES `flow_user_group` WRITE;
/*!40000 ALTER TABLE `flow_user_group` DISABLE KEYS */;
INSERT INTO `flow_user_group` VALUES (1,'2014-11-28 02:37:20',NULL,NULL,'admin','War Project',NULL,'2015-01-12 07:20:02','Humble User',NULL,2,'rickzx98@gmail.com',25),(3,'2015-01-11 23:48:22',NULL,NULL,'agent','Agent',NULL,NULL,'Humble User',NULL,2,'jerico@jsofttechnologies.com',48),(4,'2015-02-24 23:50:17',NULL,NULL,'agent_regional_manager','Regional Manager',NULL,NULL,'Humble User',NULL,2,'rickzx98@gmail.com',58),(5,'2015-02-26 12:48:16',NULL,NULL,'agent_general_manager','General Manager',NULL,NULL,'Humble User',NULL,2,'rickzx98@gmail.com',59);
/*!40000 ALTER TABLE `flow_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_group_flow_user`
--

DROP TABLE IF EXISTS `flow_user_group_flow_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_group_flow_user` (
  `flow_user_group_flow_user_group_id` bigint(20) NOT NULL,
  `flowUsers_flow_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_user_group_flow_user_group_id`,`flowUsers_flow_user_id`),
  UNIQUE KEY `UK_dj7xjlrshgf00xcsu1w475e60` (`flowUsers_flow_user_id`),
  CONSTRAINT `FK_cau5yxed8956n32w865mrxstp` FOREIGN KEY (`flow_user_group_flow_user_group_id`) REFERENCES `flow_user_group` (`flow_user_group_id`),
  CONSTRAINT `FK_dj7xjlrshgf00xcsu1w475e60` FOREIGN KEY (`flowUsers_flow_user_id`) REFERENCES `flow_user` (`flow_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_group_flow_user`
--

LOCK TABLES `flow_user_group_flow_user` WRITE;
/*!40000 ALTER TABLE `flow_user_group_flow_user` DISABLE KEYS */;
INSERT INTO `flow_user_group_flow_user` VALUES (1,2),(3,8),(4,11),(5,12),(3,14);
/*!40000 ALTER TABLE `flow_user_group_flow_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_group_flow_user_group_module`
--

DROP TABLE IF EXISTS `flow_user_group_flow_user_group_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_group_flow_user_group_module` (
  `flow_user_group_flow_user_group_id` bigint(20) NOT NULL,
  `flowUserGroupModules_flow_user_group_module_id` bigint(20) NOT NULL,
  PRIMARY KEY (`flow_user_group_flow_user_group_id`,`flowUserGroupModules_flow_user_group_module_id`),
  UNIQUE KEY `UK_2k5qb5f1g352487u89bxgph9m` (`flowUserGroupModules_flow_user_group_module_id`),
  CONSTRAINT `FK_2k5qb5f1g352487u89bxgph9m` FOREIGN KEY (`flowUserGroupModules_flow_user_group_module_id`) REFERENCES `flow_user_group_module` (`flow_user_group_module_id`),
  CONSTRAINT `FK_axlpws149rj78c6i45o424toc` FOREIGN KEY (`flow_user_group_flow_user_group_id`) REFERENCES `flow_user_group` (`flow_user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_group_flow_user_group_module`
--

LOCK TABLES `flow_user_group_flow_user_group_module` WRITE;
/*!40000 ALTER TABLE `flow_user_group_flow_user_group_module` DISABLE KEYS */;
INSERT INTO `flow_user_group_flow_user_group_module` VALUES (1,1),(1,2),(1,3),(1,7),(1,8),(1,9),(1,10),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(3,22),(1,23),(3,24),(1,25),(1,26),(1,27),(5,28),(5,29),(5,30),(5,31),(5,32),(5,33),(5,34),(4,39),(4,40),(4,42),(4,43),(4,44),(4,45),(4,48),(3,49),(3,51),(4,52),(4,53),(3,54),(3,55);
/*!40000 ALTER TABLE `flow_user_group_flow_user_group_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_group_module`
--

DROP TABLE IF EXISTS `flow_user_group_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_group_module` (
  `flow_user_group_module_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `flow_module_id` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `flow_user_group_task_id` bigint(20) DEFAULT NULL,
  `flow_group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`flow_user_group_module_id`),
  KEY `FK_7naw663kex6cgdd95sjlqimk1` (`flow_user_group_task_id`),
  CONSTRAINT `FK_7naw663kex6cgdd95sjlqimk1` FOREIGN KEY (`flow_user_group_task_id`) REFERENCES `flow_user_group_task` (`flow_user_group_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_group_module`
--

LOCK TABLES `flow_user_group_module` WRITE;
/*!40000 ALTER TABLE `flow_user_group_module` DISABLE KEYS */;
INSERT INTO `flow_user_group_module` VALUES (1,'2014-11-28 02:37:20',NULL,NULL,9,NULL,'2015-01-12 07:20:02',1,'admin'),(2,'2014-11-28 02:37:43',NULL,NULL,10,NULL,'2015-01-12 07:20:02',2,'admin'),(3,'2014-11-28 02:37:43',NULL,NULL,11,NULL,'2015-01-12 07:20:02',3,'admin'),(7,'2014-12-06 02:16:04',NULL,NULL,1,NULL,'2015-01-12 07:20:02',7,'admin'),(8,'2014-12-06 02:23:55',NULL,NULL,7,NULL,'2015-01-12 07:20:02',8,'admin'),(9,'2014-12-06 02:23:55',NULL,NULL,8,NULL,'2015-01-12 07:20:02',9,'admin'),(10,'2014-12-06 02:23:55',NULL,NULL,6,NULL,'2015-01-12 07:20:02',10,'admin'),(14,'2015-01-12 00:13:00',NULL,NULL,12,NULL,'2015-01-12 07:20:02',14,'admin'),(15,'2015-01-12 07:04:51',NULL,NULL,13,NULL,'2015-01-12 07:20:02',15,'admin'),(16,'2015-01-13 19:47:28',NULL,NULL,14,NULL,NULL,16,'admin'),(17,'2015-01-14 03:40:25',NULL,NULL,15,NULL,NULL,17,'admin'),(18,'2015-01-19 15:42:44',NULL,NULL,16,NULL,NULL,18,'admin'),(19,'2015-01-20 03:53:29',NULL,NULL,17,NULL,NULL,19,'admin'),(20,'2015-01-28 16:51:35',NULL,NULL,19,NULL,NULL,20,'admin'),(21,'2015-01-29 03:54:35',NULL,NULL,20,NULL,NULL,21,'admin'),(22,'2015-01-29 03:55:06',NULL,NULL,20,NULL,NULL,22,'agent'),(23,'2015-01-31 08:32:56',NULL,NULL,21,NULL,NULL,23,'admin'),(24,'2015-02-01 23:30:19',NULL,NULL,21,NULL,NULL,24,'agent'),(25,'2015-02-06 02:10:50',NULL,NULL,22,NULL,NULL,25,'admin'),(26,'2015-02-19 09:06:21',NULL,NULL,23,NULL,NULL,26,'admin'),(27,'2015-02-23 01:55:54',NULL,NULL,24,NULL,NULL,27,'admin'),(28,'2015-02-26 12:48:16',NULL,NULL,14,NULL,NULL,28,'agent_general_manager'),(29,'2015-02-26 12:48:16',NULL,NULL,20,NULL,NULL,29,'agent_general_manager'),(30,'2015-02-26 12:48:16',NULL,NULL,24,NULL,NULL,30,'agent_general_manager'),(31,'2015-02-26 12:48:16',NULL,NULL,22,NULL,NULL,31,'agent_general_manager'),(32,'2015-02-26 12:48:16',NULL,NULL,15,NULL,NULL,32,'agent_general_manager'),(33,'2015-02-26 12:48:16',NULL,NULL,19,NULL,NULL,33,'agent_general_manager'),(34,'2015-02-26 12:48:16',NULL,NULL,12,NULL,NULL,34,'agent_general_manager'),(39,'2015-02-26 12:57:40',NULL,NULL,15,NULL,NULL,39,'agent_regional_manager'),(40,'2015-02-26 12:59:34',NULL,NULL,20,NULL,NULL,40,'agent_regional_manager'),(42,'2015-02-26 12:59:34',NULL,NULL,17,NULL,NULL,42,'agent_regional_manager'),(43,'2015-02-26 12:59:34',NULL,NULL,19,NULL,NULL,43,'agent_regional_manager'),(44,'2015-02-26 12:59:34',NULL,NULL,16,NULL,NULL,44,'agent_regional_manager'),(45,'2015-02-26 12:59:34',NULL,NULL,13,NULL,NULL,45,'agent_regional_manager'),(48,'2015-03-06 20:06:16',NULL,NULL,25,NULL,NULL,48,'agent_regional_manager'),(49,'2015-03-07 09:46:01',NULL,NULL,26,NULL,NULL,49,'agent'),(51,'2015-03-09 11:08:08',NULL,NULL,22,NULL,NULL,51,'agent'),(52,'2015-03-10 00:58:00',NULL,NULL,24,NULL,NULL,52,'agent_regional_manager'),(53,'2015-03-10 00:58:00',NULL,NULL,22,NULL,NULL,53,'agent_regional_manager'),(54,'2015-03-10 00:58:20',NULL,NULL,24,NULL,NULL,54,'agent'),(55,'2015-03-10 06:28:41',NULL,NULL,13,NULL,NULL,55,'agent');
/*!40000 ALTER TABLE `flow_user_group_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_group_task`
--

DROP TABLE IF EXISTS `flow_user_group_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_group_task` (
  `flow_user_group_task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_user_group_active` bit(1) DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `flow_user_task_id` bigint(20) NOT NULL,
  `flow_user_group_locked` bit(1) DEFAULT NULL,
  `flow_user_group_pinned` bit(1) DEFAULT NULL,
  `flow_user_group_size` int(11) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `flow_task_page` varchar(255) DEFAULT NULL,
  `flow_task_param` varchar(255) DEFAULT NULL,
  `flow_user_group_closed` bit(1) DEFAULT NULL,
  `flow_user_toolbar` bit(1) DEFAULT NULL,
  PRIMARY KEY (`flow_user_group_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_group_task`
--

LOCK TABLES `flow_user_group_task` WRITE;
/*!40000 ALTER TABLE `flow_user_group_task` DISABLE KEYS */;
INSERT INTO `flow_user_group_task` VALUES (1,'\0','2014-11-28 02:37:20',NULL,NULL,11,NULL,NULL,50,NULL,'2014-12-06 02:08:07',NULL,NULL,NULL,''),(2,'\0','2014-11-28 02:37:43',NULL,NULL,9,NULL,NULL,50,NULL,'2014-12-06 02:08:07',NULL,NULL,NULL,''),(3,'\0','2014-11-28 02:37:43',NULL,NULL,10,NULL,NULL,50,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(7,NULL,'2014-12-06 02:16:04',NULL,NULL,1,NULL,NULL,50,NULL,'2014-12-21 20:58:05',NULL,NULL,NULL,''),(8,NULL,'2014-12-06 02:23:55',NULL,NULL,7,NULL,NULL,50,NULL,'2014-12-11 05:13:55',NULL,NULL,NULL,''),(9,NULL,'2014-12-06 02:23:55',NULL,NULL,8,NULL,NULL,50,NULL,'2014-12-11 05:13:55',NULL,NULL,NULL,''),(10,NULL,'2014-12-06 02:23:55',NULL,NULL,6,'\0','\0',50,NULL,'2014-12-21 20:58:05',NULL,NULL,NULL,''),(14,'\0','2015-01-12 00:12:59',NULL,NULL,16,NULL,NULL,50,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(15,'\0','2015-01-12 07:04:51',NULL,NULL,17,NULL,NULL,100,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(16,'\0','2015-01-13 19:47:28',NULL,NULL,18,NULL,NULL,100,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(17,'\0','2015-01-14 03:40:25',NULL,NULL,19,NULL,NULL,100,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(18,'\0','2015-01-19 15:42:44',NULL,NULL,20,NULL,NULL,50,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(19,'\0','2015-01-20 03:53:29',NULL,NULL,21,'\0',NULL,50,NULL,'2015-02-26 12:56:03',NULL,NULL,NULL,''),(20,NULL,'2015-01-28 16:51:35',NULL,NULL,22,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(21,'\0','2015-01-29 03:54:35',NULL,NULL,23,NULL,NULL,100,NULL,'2015-01-31 05:22:44',NULL,NULL,NULL,'\0'),(22,'\0','2015-01-29 03:55:06',NULL,NULL,23,'\0',NULL,100,NULL,'2015-03-07 10:15:03',NULL,NULL,NULL,'\0'),(23,'\0','2015-01-31 08:32:56',NULL,NULL,24,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(24,'','2015-02-01 23:30:19',NULL,NULL,24,NULL,NULL,50,NULL,NULL,NULL,NULL,NULL,''),(25,'\0','2015-02-06 02:10:50',NULL,NULL,25,NULL,NULL,100,NULL,'2015-02-06 02:11:39',NULL,NULL,NULL,'\0'),(26,NULL,'2015-02-19 09:06:21',NULL,NULL,26,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(27,'','2015-02-23 01:55:54',NULL,NULL,27,'\0',NULL,100,NULL,'2015-03-10 01:48:33',NULL,NULL,NULL,NULL),(28,NULL,'2015-02-26 12:48:16',NULL,NULL,18,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(29,NULL,'2015-02-26 12:48:16',NULL,NULL,23,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(30,NULL,'2015-02-26 12:48:16',NULL,NULL,28,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(31,NULL,'2015-02-26 12:48:16',NULL,NULL,27,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,'\0'),(32,NULL,'2015-02-26 12:48:16',NULL,NULL,19,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(33,NULL,'2015-02-26 12:48:16',NULL,NULL,22,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(34,NULL,'2015-02-26 12:48:16',NULL,NULL,16,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(39,NULL,'2015-02-26 12:57:40',NULL,NULL,19,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(40,NULL,'2015-02-26 12:59:34',NULL,NULL,23,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(42,NULL,'2015-02-26 12:59:34',NULL,NULL,21,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(43,NULL,'2015-02-26 12:59:34',NULL,NULL,22,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(44,NULL,'2015-02-26 12:59:34',NULL,NULL,20,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(45,NULL,'2015-02-26 12:59:34',NULL,NULL,17,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,''),(48,'\0','2015-03-06 20:06:16',NULL,NULL,29,'\0',NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(49,'\0','2015-03-07 09:46:01',NULL,NULL,30,NULL,NULL,100,NULL,'2015-03-07 10:10:21',NULL,NULL,NULL,NULL),(51,NULL,'2015-03-09 11:08:08',NULL,NULL,25,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(52,NULL,'2015-03-10 00:58:00',NULL,NULL,27,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(53,NULL,'2015-03-10 00:58:00',NULL,NULL,25,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(54,NULL,'2015-03-10 00:58:20',NULL,NULL,27,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,NULL),(55,NULL,'2015-03-10 06:28:41',NULL,NULL,17,NULL,NULL,100,NULL,NULL,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `flow_user_group_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_profile`
--

DROP TABLE IF EXISTS `flow_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_profile` (
  `flow_user_profile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `usage_end_date` datetime DEFAULT NULL,
  `profile_name` varchar(255) NOT NULL,
  `usage_start_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`flow_user_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_profile`
--

LOCK TABLES `flow_user_profile` WRITE;
/*!40000 ALTER TABLE `flow_user_profile` DISABLE KEYS */;
INSERT INTO `flow_user_profile` VALUES (1,'2014-11-18 16:34:29',NULL,NULL,'admin',NULL,'2015-01-04 20:04:08'),(2,'2014-11-18 16:34:31',NULL,NULL,'dev',NULL,NULL),(3,'2015-01-11 22:38:21',NULL,NULL,'agent',NULL,NULL),(4,'2015-01-13 19:46:46',NULL,NULL,'rex_group_admin',NULL,NULL),(5,'2015-02-24 23:50:55',NULL,NULL,'agent_regional_manager',NULL,NULL),(6,'2015-02-26 12:50:07',NULL,NULL,'agent_general_manager',NULL,NULL);
/*!40000 ALTER TABLE `flow_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_profile_flow_profile_permission`
--

DROP TABLE IF EXISTS `flow_user_profile_flow_profile_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_profile_flow_profile_permission` (
  `flow_user_profile_flow_user_profile_id` bigint(20) NOT NULL,
  `flowProfilePermissions_flow_profile_permmission_id` bigint(20) NOT NULL,
  KEY `FK_av5e7r721c88vo8tq4n126ksq` (`flow_user_profile_flow_user_profile_id`),
  KEY `FK_p1peth9qcnpg9ix2eqpog1xip` (`flowProfilePermissions_flow_profile_permmission_id`),
  CONSTRAINT `FK_av5e7r721c88vo8tq4n126ksq` FOREIGN KEY (`flow_user_profile_flow_user_profile_id`) REFERENCES `flow_user_profile` (`flow_user_profile_id`),
  CONSTRAINT `FK_p1peth9qcnpg9ix2eqpog1xip` FOREIGN KEY (`flowProfilePermissions_flow_profile_permmission_id`) REFERENCES `flow_profile_permission` (`flow_profile_permmission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_profile_flow_profile_permission`
--

LOCK TABLES `flow_user_profile_flow_profile_permission` WRITE;
/*!40000 ALTER TABLE `flow_user_profile_flow_profile_permission` DISABLE KEYS */;
INSERT INTO `flow_user_profile_flow_profile_permission` VALUES (2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,56),(1,5),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,51),(1,52),(1,53),(1,54),(1,55),(1,59),(4,71),(4,72),(4,73),(4,74),(4,75),(4,76),(4,77),(4,78),(4,79),(4,80),(4,81),(4,82),(4,83),(4,84),(4,85),(4,86),(4,87),(4,88),(4,89),(4,90),(4,91),(4,92),(4,93),(4,94),(4,96),(4,97),(4,98),(4,102),(4,103),(4,104),(5,130),(5,131),(5,132),(5,133),(5,134),(5,135),(5,136),(5,137),(5,138),(5,139),(5,140),(5,141),(5,142),(5,143),(5,144),(5,145),(5,146),(5,147),(5,148),(5,149),(5,150),(5,151),(6,105),(6,106),(6,107),(6,108),(6,109),(6,110),(6,111),(6,112),(6,113),(6,114),(6,115),(6,116),(6,117),(6,118),(6,119),(6,120),(6,121),(6,122),(6,123),(6,124),(6,125),(6,126),(6,127),(6,128),(6,129),(6,154),(3,95),(3,99),(3,100),(3,101),(3,152),(3,153),(3,155),(3,156),(3,157),(3,158),(3,159),(3,160);
/*!40000 ALTER TABLE `flow_user_profile_flow_profile_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flow_user_task`
--

DROP TABLE IF EXISTS `flow_user_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flow_user_task` (
  `flow_user_task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flow_task_active` bit(1) DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `flow_task_id` bigint(20) NOT NULL,
  `flow_user_id` bigint(20) NOT NULL,
  `flow_task_locked` bit(1) DEFAULT NULL,
  `flow_task_pinned` bit(1) DEFAULT NULL,
  `flow_task_size` int(11) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `flow_task_page` varchar(255) DEFAULT NULL,
  `flow_task_param` varchar(255) DEFAULT NULL,
  `flow_task_closed` bit(1) DEFAULT NULL,
  `flow_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`flow_user_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2686 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flow_user_task`
--

LOCK TABLES `flow_user_task` WRITE;
/*!40000 ALTER TABLE `flow_user_task` DISABLE KEYS */;
INSERT INTO `flow_user_task` VALUES (2575,'','2015-03-10 02:32:14',NULL,NULL,27,13,'\0','\0',25,NULL,NULL,NULL,NULL,NULL,NULL),(2685,'','2015-03-11 19:07:20',NULL,NULL,30,8,NULL,NULL,100,NULL,'2015-03-11 19:18:03',NULL,NULL,NULL,'2685');
/*!40000 ALTER TABLE `flow_user_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flowusergroup_emails`
--

DROP TABLE IF EXISTS `flowusergroup_emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flowusergroup_emails` (
  `FlowUserGroup_flow_user_group_id` bigint(20) NOT NULL,
  `emails` varchar(255) DEFAULT NULL,
  KEY `FK_epd12rngs7pmb198nac610o8r` (`FlowUserGroup_flow_user_group_id`),
  CONSTRAINT `FK_epd12rngs7pmb198nac610o8r` FOREIGN KEY (`FlowUserGroup_flow_user_group_id`) REFERENCES `flow_user_group` (`flow_user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flowusergroup_emails`
--

LOCK TABLES `flowusergroup_emails` WRITE;
/*!40000 ALTER TABLE `flowusergroup_emails` DISABLE KEYS */;
/*!40000 ALTER TABLE `flowusergroup_emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `monthly_customer_report_activtiy`
--

DROP TABLE IF EXISTS `monthly_customer_report_activtiy`;
/*!50001 DROP VIEW IF EXISTS `monthly_customer_report_activtiy`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `monthly_customer_report_activtiy` (
  `report_customer` tinyint NOT NULL,
  `report_year` tinyint NOT NULL,
  `report_month` tinyint NOT NULL,
  `report_customer_id` tinyint NOT NULL,
  `report_agent` tinyint NOT NULL,
  `report_school_year` tinyint NOT NULL,
  `report_region` tinyint NOT NULL,
  `report_market_potential_segment` tinyint NOT NULL,
  `report_market_potential` tinyint NOT NULL,
  `report_tag_index` tinyint NOT NULL,
  `report_frequency` tinyint NOT NULL,
  `report_planned` tinyint NOT NULL,
  `report_exam_copies_distribution` tinyint NOT NULL,
  `report_confirmation_of_events` tinyint NOT NULL,
  `report_delivery_of_additional_order_trm_compliance` tinyint NOT NULL,
  `report_delivery_of_incentive_donation` tinyint NOT NULL,
  `report_giveaways_distribution` tinyint NOT NULL,
  `report_implemented_ex_sem` tinyint NOT NULL,
  `report_invitation_to_events` tinyint NOT NULL,
  `report_purchase_order` tinyint NOT NULL,
  `report_updated_customer_info_sheet` tinyint NOT NULL,
  `report_follow_up_payment` tinyint NOT NULL,
  `report_book_list` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `war_activity`
--

DROP TABLE IF EXISTS `war_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_activity` (
  `war_activity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `war_activity_actual` bit(1) DEFAULT NULL,
  `war_activity_book_list` bit(1) DEFAULT NULL,
  `war_activity_confirmation_of_events` bit(1) DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `war_activity_customer_market_id` bigint(20) DEFAULT NULL,
  `war_activity_customer_specific_activity` text,
  `war_activity_delivery_of_additional_order_trm_compliance` bit(1) DEFAULT NULL,
  `description` text,
  `war_activity_delivery_of_incentive_donation` bit(1) DEFAULT NULL,
  `war_activity_exam_copies_distribution` bit(1) DEFAULT NULL,
  `end_dt` date DEFAULT NULL,
  `war_activity_giveaways_distribution` bit(1) DEFAULT NULL,
  `war_activity_implemented_ex_sem` bit(1) DEFAULT NULL,
  `war_activity_invitation_to_events` bit(1) DEFAULT NULL,
  `war_activity_purchase_order` bit(1) DEFAULT NULL,
  `war_activity_reason_for_non_coverage` text,
  `start_dt` date DEFAULT NULL,
  `war_activity_updated_customer_info_sheet` bit(1) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_activity_school_year` bigint(20) NOT NULL,
  `war_activity_editable` bit(1) DEFAULT NULL,
  `war_activity_target` int(11) DEFAULT NULL,
  `war_planner` bigint(20) NOT NULL,
  `war_planner_month` varchar(255) NOT NULL,
  `war_activity_planned` bit(1) NOT NULL,
  `war_activity_type` varchar(255) NOT NULL,
  `war_activity_agent_id` bigint(20) NOT NULL,
  `war_activity_week` int(11) NOT NULL,
  `war_activity_follow_up_payment` bit(1) DEFAULT NULL,
  `war_activity_region_code` varchar(255) NOT NULL,
  `war_activity_region_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_activity_id`),
  KEY `FK_bu2bnoyuawfgtg71dt13xdmsu` (`war_planner`,`war_planner_month`),
  CONSTRAINT `FK_bu2bnoyuawfgtg71dt13xdmsu` FOREIGN KEY (`war_planner`, `war_planner_month`) REFERENCES `war_activity_planner` (`war_activity_planner_id`, `war_activity_planner_month`),
  CONSTRAINT `FK_cgh1lqydir3jbnodrpcbqimmx` FOREIGN KEY (`war_planner`) REFERENCES `war_activity_planner` (`war_activity_planner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_activity`
--

LOCK TABLES `war_activity` WRITE;
/*!40000 ALTER TABLE `war_activity` DISABLE KEYS */;
INSERT INTO `war_activity` VALUES (206,'',NULL,'','2015-03-08 16:02:55',20,'efe','','SAVIOR CHRISTIAN ACADEMY - HMP',NULL,'',NULL,NULL,NULL,'',NULL,'gift','2014-09-01',NULL,'2015-03-11 07:49:04',12,'\0',NULL,110,'SEPTEMBER','\0','SCHOOL',5,1,'','NCR',7),(207,'',NULL,NULL,'2015-03-08 16:18:06',14,NULL,NULL,'LA CONSOLACION UNIVERSITY PHILIPPINES - HMP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-09-02',NULL,'2015-03-08 16:18:10',12,'\0',NULL,111,'SEPTEMBER','\0','SCHOOL',5,1,'','NCR',7),(208,'',NULL,NULL,'2015-03-08 16:18:27',13,NULL,NULL,'ST. PAUL SCH. (E/HS)-SAN RAFAEL - MMP1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2014-09-10','','2015-03-08 16:18:31',12,'\0',NULL,112,'SEPTEMBER','\0','SCHOOL',5,2,NULL,'NCR',7),(209,'',NULL,'','2015-03-08 16:18:50',20,NULL,NULL,'SAVIOR CHRISTIAN ACADEMY - HMP',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,'2014-09-15',NULL,'2015-03-08 16:19:02',12,'\0',NULL,113,'SEPTEMBER','\0','SCHOOL',5,3,'','NCR',7),(210,'',NULL,NULL,'2015-03-08 16:18:50',14,NULL,NULL,'LA CONSOLACION UNIVERSITY PHILIPPINES - HMP','',NULL,NULL,'',NULL,NULL,NULL,NULL,'2014-09-15',NULL,'2015-03-08 16:18:56',12,'\0',NULL,113,'SEPTEMBER','\0','SCHOOL',5,3,'','NCR',7),(211,'',NULL,NULL,'2015-03-08 16:18:50',13,NULL,NULL,'ST. PAUL SCH. (E/HS)-SAN RAFAEL - MMP1','',NULL,NULL,NULL,NULL,NULL,'',NULL,'2014-09-15',NULL,'2015-03-08 16:19:06',12,'\0',NULL,113,'SEPTEMBER','\0','SCHOOL',5,3,NULL,'NCR',7),(212,'',NULL,NULL,'2015-03-09 23:03:09',20,NULL,NULL,'SAVIOR CHRISTIAN ACADEMY - HMP','',NULL,NULL,NULL,NULL,'',NULL,NULL,'2015-03-03',NULL,'2015-03-09 23:03:36',12,'\0',NULL,106,'MARCH','\0','SCHOOL',5,1,NULL,'NCR',7),(213,'','',NULL,'2015-03-09 23:03:13',13,NULL,NULL,'ST. PAUL SCH. (E/HS)-SAN RAFAEL - MMP1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-03-19',NULL,'2015-03-09 23:03:23',12,'\0',NULL,106,'MARCH','','SCHOOL',5,3,'','NCR',7),(214,'','',NULL,'2015-03-09 23:03:17',14,NULL,NULL,'LA CONSOLACION UNIVERSITY PHILIPPINES - HMP',NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2015-03-25','','2015-03-09 23:03:30',12,'\0',NULL,106,'MARCH','','SCHOOL',5,4,NULL,'NCR',7),(215,'',NULL,NULL,'2015-03-09 23:03:46',14,NULL,NULL,'LA CONSOLACION UNIVERSITY PHILIPPINES - HMP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-03-23',NULL,'2015-03-09 23:03:50',12,'\0',NULL,106,'MARCH','','SCHOOL',5,4,'','NCR',7),(216,'',NULL,NULL,'2015-03-09 23:04:42',14,NULL,NULL,'LA CONSOLACION UNIVERSITY PHILIPPINES - HMP',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,'2015-04-13',NULL,'2015-03-11 09:31:12',12,'\0',NULL,114,'APRIL','','SCHOOL',5,2,NULL,'NCR',7),(217,NULL,NULL,NULL,'2015-03-09 23:04:42',20,NULL,NULL,'SAVIOR CHRISTIAN ACADEMY - HMP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-04-15',NULL,NULL,12,'\0',NULL,114,'APRIL','','SCHOOL',5,2,NULL,'NCR',7),(218,'',NULL,'','2015-03-09 23:04:42',13,'hi',NULL,'ST. PAUL SCH. (E/HS)-SAN RAFAEL - MMP1',NULL,'\0',NULL,'',NULL,NULL,NULL,'hello','2015-04-28',NULL,'2015-03-11 09:35:06',12,'\0',NULL,114,'APRIL','','SCHOOL',5,4,NULL,'NCR',7),(219,NULL,NULL,NULL,'2015-03-11 09:44:59',20,NULL,NULL,'SAVIOR CHRISTIAN ACADEMY - HMP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-03-26',NULL,NULL,12,'\0',NULL,106,'MARCH','','SCHOOL',5,4,NULL,'NCR',7),(220,NULL,NULL,NULL,'2015-03-11 09:45:03',20,NULL,NULL,'SAVIOR CHRISTIAN ACADEMY - HMP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-03-31',NULL,NULL,12,'\0',NULL,106,'MARCH','','SCHOOL',5,5,NULL,'NCR',7),(221,NULL,NULL,NULL,'2015-03-11 09:45:06',13,NULL,NULL,'ST. PAUL SCH. (E/HS)-SAN RAFAEL - MMP1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-04-04',NULL,NULL,12,'\0',NULL,106,'MARCH','','SCHOOL',5,5,NULL,'NCR',7);
/*!40000 ALTER TABLE `war_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_activity_note`
--

DROP TABLE IF EXISTS `war_activity_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_activity_note` (
  `war_activity_note_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `war_activity_note_agent_id` bigint(20) NOT NULL,
  `created_dt` datetime NOT NULL,
  `war_activity_note_agent_day` int(11) NOT NULL,
  `description` text,
  `end_dt` datetime DEFAULT NULL,
  `war_activity_note_agent_month` varchar(255) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_activity_note_agent_year` int(11) NOT NULL,
  PRIMARY KEY (`war_activity_note_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_activity_note`
--

LOCK TABLES `war_activity_note` WRITE;
/*!40000 ALTER TABLE `war_activity_note` DISABLE KEYS */;
INSERT INTO `war_activity_note` VALUES (1,5,'2015-03-08 14:17:10',2,'Notes',NULL,'MARCH',NULL,'2015-03-08 14:17:38',2015),(2,5,'2015-03-09 03:32:16',1,'l;',NULL,'SEPTEMBER',NULL,NULL,2014),(3,5,'2015-03-11 09:46:07',7,'Going to St. Paul and will jointheir annual basketball tournament.',NULL,'MARCH',NULL,NULL,2015),(4,5,'2015-03-11 09:46:11',7,'Going to St. Paul and will joint heir annual basketball tournament.',NULL,'MARCH',NULL,NULL,2015),(5,5,'2015-03-11 09:46:17',7,'Going to St. Paul and will join  their annual basketball tournament.',NULL,'MARCH',NULL,NULL,2015);
/*!40000 ALTER TABLE `war_activity_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_activity_planner`
--

DROP TABLE IF EXISTS `war_activity_planner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_activity_planner` (
  `war_activity_planner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `war_activity_planner_agent` bigint(20) NOT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `war_activity_planner_enabled` bit(1) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_activity_planner_month` varchar(255) NOT NULL,
  `war_activity_planner_school_year` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_activity_planner_year` int(11) NOT NULL,
  PRIMARY KEY (`war_activity_planner_id`),
  UNIQUE KEY `UK_blv94vu5rgopjylse5as0otnl` (`war_activity_planner_id`,`war_activity_planner_month`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_activity_planner`
--

LOCK TABLES `war_activity_planner` WRITE;
/*!40000 ALTER TABLE `war_activity_planner` DISABLE KEYS */;
INSERT INTO `war_activity_planner` VALUES (93,5,'2015-02-22 09:59:39',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(94,5,'2015-02-22 11:54:21',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(95,5,'2015-02-22 11:59:01',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(96,5,'2015-02-22 12:09:39',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(97,5,'2015-02-22 12:09:43',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(98,5,'2015-02-22 12:12:08',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(99,5,'2015-02-22 12:12:35',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(100,5,'2015-02-22 12:12:43',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(101,5,'2015-02-22 13:36:34',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(102,5,'2015-02-22 13:37:46',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(103,5,'2015-02-22 13:37:59',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(104,5,'2015-02-22 14:03:08',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(105,5,'2015-02-22 21:03:54',NULL,'',NULL,'SEPTEMBER',12,NULL,NULL,2014),(106,5,'2015-02-23 10:01:26',NULL,NULL,NULL,'MARCH',12,NULL,NULL,2015),(107,5,'2015-02-26 11:03:50',NULL,NULL,NULL,'FEBRUARY',12,NULL,NULL,2015),(108,5,'2015-03-08 14:30:51',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(109,5,'2015-03-08 14:37:54',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(110,5,'2015-03-08 16:02:55',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(111,5,'2015-03-08 16:18:06',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(112,5,'2015-03-08 16:18:27',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(113,5,'2015-03-08 16:18:50',NULL,NULL,NULL,'SEPTEMBER',12,NULL,NULL,2014),(114,5,'2015-03-09 23:04:42',NULL,NULL,NULL,'APRIL',12,NULL,NULL,2015);
/*!40000 ALTER TABLE `war_activity_planner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_agent`
--

DROP TABLE IF EXISTS `war_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_agent` (
  `war_agent_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `war_agent_active` bit(1) NOT NULL,
  `war_agent_online` bit(1) DEFAULT NULL,
  `war_agent_initials` varchar(255) NOT NULL,
  `manager` bit(1) NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`war_agent_id`),
  KEY `FK_okb2jkmshim2synuox2b64m9v` (`user`),
  CONSTRAINT `FK_okb2jkmshim2synuox2b64m9v` FOREIGN KEY (`user`) REFERENCES `flow_user` (`flow_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_agent`
--

LOCK TABLES `war_agent` WRITE;
/*!40000 ALTER TABLE `war_agent` DISABLE KEYS */;
INSERT INTO `war_agent` VALUES (5,'2015-01-31 01:00:54',NULL,NULL,NULL,'2015-03-11 19:03:48',8,'','','SAG','\0','NCR'),(8,'2015-02-26 12:41:27',NULL,NULL,NULL,NULL,11,'',NULL,'RM1','','NCR'),(10,'2015-03-10 03:07:27',NULL,NULL,NULL,NULL,14,'',NULL,'CAN','\0','Region X');
/*!40000 ALTER TABLE `war_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer`
--

DROP TABLE IF EXISTS `war_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `customer_code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `customer_additional_discount` double DEFAULT NULL,
  `customer_association` varchar(25) DEFAULT NULL,
  `customer_buying_process` varchar(25) DEFAULT NULL,
  `customer_congregation` varchar(25) DEFAULT NULL,
  `customer_diocese` varchar(255) DEFAULT NULL,
  `customer_incentives` double DEFAULT NULL,
  `customer_information_on_enrollment` varchar(255) DEFAULT NULL,
  `customer_journal_publisher` varchar(50) DEFAULT NULL,
  `customer_nature_of_purchase` varchar(25) DEFAULT NULL,
  `customer_ownership` varchar(25) DEFAULT NULL,
  `customer_standard_outright_discount` double DEFAULT NULL,
  `customer_using_journals` bit(1) DEFAULT NULL,
  `customer_using_sra` bit(1) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `customer_created_by_agent_id` bigint(20) DEFAULT NULL,
  `customer_owner_agent_id` bigint(20) DEFAULT NULL,
  `customer_cycle_collection` varchar(255) DEFAULT NULL,
  `customer_cycle_collection_to` varchar(255) DEFAULT NULL,
  `customer_cycle_delivery` varchar(255) DEFAULT NULL,
  `customer_cycle_delivery_to` varchar(255) DEFAULT NULL,
  `customer_cycle_evaluation` varchar(255) DEFAULT NULL,
  `customer_cycle_evaluation_to` varchar(255) DEFAULT NULL,
  `customer_cycle_ordering` varchar(255) DEFAULT NULL,
  `customer_cycle_ordering_to` varchar(255) DEFAULT NULL,
  `war_customer_school` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_code_UNIQUE` (`customer_code`),
  UNIQUE KEY `UK_shq3w3jaib3i64v8qbjvoaide` (`customer_code`),
  KEY `FK_jbmftkdcfefhftm1kja0wib3r` (`publisher`),
  KEY `FK_ji8owmg714fw3rx46655719b` (`war_customer_school`),
  CONSTRAINT `FK_jbmftkdcfefhftm1kja0wib3r` FOREIGN KEY (`publisher`) REFERENCES `war_customer_publisher` (`description`),
  CONSTRAINT `FK_ji8owmg714fw3rx46655719b` FOREIGN KEY (`war_customer_school`) REFERENCES `war_customer_school` (`war_customer_school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer`
--

LOCK TABLES `war_customer` WRITE;
/*!40000 ALTER TABLE `war_customer` DISABLE KEYS */;
INSERT INTO `war_customer` VALUES (13,'2015-01-27 21:19:06','01-CC-000482',NULL,NULL,NULL,NULL,NULL,'CEAP, BULPRISA','DECENTRALIZED','SPC','MADICSA/DIOCESE OF MALOLOS',NULL,NULL,'REX','RENTAL',NULL,0.2,'','\0',NULL,2,5,'SEPTEMBER','NOVEMBER','APRIL',NULL,'OCTOBER','NOVEMBER','APRIL',NULL,2),(14,'2015-02-02 04:54:42','01-CC-000530',NULL,NULL,NULL,NULL,NULL,NULL,'CENTRALIZED',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','\0',NULL,2,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,419),(20,'2015-03-07 04:37:32','01-CC-000234',NULL,NULL,NULL,NULL,NULL,NULL,'CENTRALIZED',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,5,NULL,NULL,NULL,NULL,'FEBRUARY',NULL,'APRIL',NULL,4);
/*!40000 ALTER TABLE `war_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_buiyng_cycle`
--

DROP TABLE IF EXISTS `war_customer_buiyng_cycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_buiyng_cycle` (
  `war_customer_buiyng_cycle_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `cycle_name` varchar(255) NOT NULL,
  `cycle_date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_customer_buiyng_cycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_buiyng_cycle`
--

LOCK TABLES `war_customer_buiyng_cycle` WRITE;
/*!40000 ALTER TABLE `war_customer_buiyng_cycle` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_buiyng_cycle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_contacts`
--

DROP TABLE IF EXISTS `war_customer_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_contacts` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact_birthdate` datetime NOT NULL,
  `created_dt` datetime NOT NULL,
  `decision_maker` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contact_detail` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `contact_position` bigint(20) NOT NULL,
  `relationship_type` varchar(255) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `contact_level` varchar(255) NOT NULL,
  `contact_type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_contacts`
--

LOCK TABLES `war_customer_contacts` WRITE;
/*!40000 ALTER TABLE `war_customer_contacts` DISABLE KEYS */;
INSERT INTO `war_customer_contacts` VALUES (10,'2015-01-22 00:00:00','2015-01-27 21:19:06','\0','SR. NORMA QUE, SPC','0',NULL,2,'NONE',NULL,NULL,'PreELEMENTARY','EMAIL');
/*!40000 ALTER TABLE `war_customer_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_contacts_level`
--

DROP TABLE IF EXISTS `war_customer_contacts_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_contacts_level` (
  `level_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `level_course` varchar(50) NOT NULL,
  `level_population` int(11) DEFAULT NULL,
  `level_tuition_fee_from` double DEFAULT NULL,
  `level_tuition_fee_to` double DEFAULT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_contacts_level`
--

LOCK TABLES `war_customer_contacts_level` WRITE;
/*!40000 ALTER TABLE `war_customer_contacts_level` DISABLE KEYS */;
INSERT INTO `war_customer_contacts_level` VALUES (1,'2015-01-25 23:59:39','PreElementary',NULL,NULL,NULL,'N',NULL,NULL,NULL),(2,'2015-01-27 18:57:25','PreELEMENTARY',NULL,NULL,NULL,'K',NULL,NULL,NULL),(3,'2015-01-27 18:57:29','PreELEMENTARY',NULL,NULL,NULL,'P',NULL,NULL,NULL),(4,'2015-01-27 18:57:33','ELEMENTARY',NULL,NULL,NULL,'Grade 1',NULL,NULL,NULL),(5,'2015-01-27 18:57:39','ELEMENTARY',NULL,NULL,NULL,'Grade 2',NULL,NULL,NULL),(6,'2015-01-27 18:57:57','ELEMENTARY',NULL,NULL,NULL,'Grade 3',NULL,NULL,NULL),(7,'2015-01-27 18:58:01','ELEMENTARY',NULL,NULL,NULL,'Grade 4',NULL,NULL,NULL),(8,'2015-01-27 18:58:07','ELEMENTARY',NULL,NULL,NULL,'Grade 5',NULL,NULL,NULL),(9,'2015-01-27 18:58:11','ELEMENTARY',NULL,NULL,NULL,'Grade 6',NULL,NULL,NULL),(10,'2015-01-27 18:59:15','SECONDARY',NULL,NULL,NULL,'Grade 7 - I',NULL,NULL,NULL),(11,'2015-01-27 18:59:22','SECONDARY',NULL,NULL,NULL,'Grade 8 - II',NULL,NULL,NULL),(12,'2015-01-27 18:59:31','SECONDARY',NULL,NULL,NULL,'Grade 9 - III',NULL,NULL,NULL),(13,'2015-01-27 18:59:45','SECONDARY',NULL,NULL,NULL,'Grade 10 - IV',NULL,NULL,NULL);
/*!40000 ALTER TABLE `war_customer_contacts_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_contacts_position`
--

DROP TABLE IF EXISTS `war_customer_contacts_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_contacts_position` (
  `position_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_contacts_position`
--

LOCK TABLES `war_customer_contacts_position` WRITE;
/*!40000 ALTER TABLE `war_customer_contacts_position` DISABLE KEYS */;
INSERT INTO `war_customer_contacts_position` VALUES (2,'2015-01-27 18:31:33','Pre-Elementary Head',NULL,NULL,NULL),(3,'2015-01-27 18:55:14','Principal',NULL,NULL,NULL),(4,'2015-01-27 18:55:21','Asst Principal',NULL,NULL,NULL),(5,'2015-01-27 18:55:27','SAC - English',NULL,NULL,NULL),(6,'2015-01-27 18:55:33','SAC - Math',NULL,NULL,NULL),(7,'2015-01-27 18:56:03','SAC - Science',NULL,NULL,NULL),(8,'2015-01-27 18:56:08','SAC - Social Studies',NULL,NULL,NULL),(10,'2015-01-27 18:56:30','SAC - Filipino',NULL,NULL,NULL),(11,'2015-01-27 18:56:38','SAC - HELE/THE',NULL,NULL,NULL),(12,'2015-01-27 18:56:44','SAC - MAPE/MAPEH',NULL,NULL,NULL),(13,'2015-01-27 18:56:50','SAC - Computer',NULL,NULL,NULL),(14,'2015-01-27 18:56:58','SAC - Religion/Values',NULL,NULL,NULL);
/*!40000 ALTER TABLE `war_customer_contacts_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_level`
--

DROP TABLE IF EXISTS `war_customer_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_level` (
  `war_customer_level_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `level_id` bigint(20) NOT NULL,
  `war_customer_level_population` int(11) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `war_level_tuition_fee_from` double DEFAULT NULL,
  `war_level_tuition_fee_to` double DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_customer_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_level`
--

LOCK TABLES `war_customer_level` WRITE;
/*!40000 ALTER TABLE `war_customer_level` DISABLE KEYS */;
INSERT INTO `war_customer_level` VALUES (1,'2015-01-27 21:19:06',NULL,NULL,7,61,NULL,15000,18000,NULL),(2,'2015-01-27 21:19:06',NULL,NULL,10,150,NULL,18000,25000,NULL),(3,'2015-01-27 21:19:06',NULL,NULL,9,58,NULL,15000,18000,NULL),(4,'2015-01-27 21:19:06',NULL,NULL,8,59,NULL,15000,18000,NULL),(5,'2015-01-27 21:19:06',NULL,NULL,2,22,NULL,12000,15000,NULL),(6,'2015-01-27 21:19:06',NULL,NULL,13,131,NULL,18000,25000,NULL),(7,'2015-01-27 21:19:06',NULL,NULL,1,0,NULL,0,NULL,NULL),(8,'2015-01-27 21:19:06',NULL,NULL,4,65,NULL,15000,18000,NULL),(9,'2015-01-27 21:19:06',NULL,NULL,11,150,NULL,18000,25000,NULL),(10,'2015-01-27 21:19:06',NULL,NULL,6,71,NULL,15000,18000,NULL),(11,'2015-01-27 21:19:06',NULL,NULL,5,79,NULL,15000,18000,NULL),(12,'2015-01-27 21:19:06',NULL,NULL,12,122,NULL,18000,25000,NULL),(13,'2015-01-27 21:19:06',NULL,NULL,3,53,NULL,12000,15000,NULL);
/*!40000 ALTER TABLE `war_customer_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_market_school_year`
--

DROP TABLE IF EXISTS `war_customer_market_school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_market_school_year` (
  `war_customer_market_school_year_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `market_potential` int(11) NOT NULL,
  `market_potential_segment` varchar(255) NOT NULL,
  `school_year` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`war_customer_market_school_year_id`),
  KEY `FK_e7lq21lu133av5psqty7hnoul` (`customer_id`),
  CONSTRAINT `FK_e7lq21lu133av5psqty7hnoul` FOREIGN KEY (`customer_id`) REFERENCES `war_customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_market_school_year`
--

LOCK TABLES `war_customer_market_school_year` WRITE;
/*!40000 ALTER TABLE `war_customer_market_school_year` DISABLE KEYS */;
INSERT INTO `war_customer_market_school_year` VALUES (1,'2015-02-25 13:14:34',NULL,NULL,NULL,17775,'MMP1',12,NULL,NULL,NULL),(2,'2015-03-07 02:53:44',NULL,NULL,NULL,17775,'MMP1',12,NULL,NULL,NULL),(3,'2015-03-07 02:53:44',NULL,NULL,NULL,3343,'LMP1',13,NULL,'2015-03-07 02:53:54',NULL),(4,'2015-03-07 02:54:08',NULL,NULL,NULL,23553,'HMP',12,NULL,NULL,NULL),(5,'2015-03-07 02:54:08',NULL,NULL,NULL,234,'LMP2',13,NULL,NULL,NULL),(11,'2015-03-07 04:37:32',NULL,NULL,NULL,123213,'HMP',13,NULL,NULL,NULL);
/*!40000 ALTER TABLE `war_customer_market_school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_publisher`
--

DROP TABLE IF EXISTS `war_customer_publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_publisher` (
  `war_customer_publisher_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_customer_publisher_id`),
  UNIQUE KEY `UK_s9nkmx0vbshqikai30fbuuaxc` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_publisher`
--

LOCK TABLES `war_customer_publisher` WRITE;
/*!40000 ALTER TABLE `war_customer_publisher` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_region`
--

DROP TABLE IF EXISTS `war_customer_region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_region` (
  `flow_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `region_code` varchar(255) NOT NULL,
  `region_name` varchar(255) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`flow_id`),
  UNIQUE KEY `UK_5a4pb6s71xrrd3rlh00n30n1k` (`region_code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_region`
--

LOCK TABLES `war_customer_region` WRITE;
/*!40000 ALTER TABLE `war_customer_region` DISABLE KEYS */;
INSERT INTO `war_customer_region` VALUES (5,'2015-01-27 18:29:27',NULL,NULL,'Region III','Central Luzon',NULL,NULL),(7,'2015-01-30 03:10:57',NULL,NULL,'NCR','National Capital Region',NULL,NULL),(8,'2015-01-30 03:13:53',NULL,NULL,'CAR','Cordillera Administrative Region',NULL,NULL),(9,'2015-01-30 03:14:29',NULL,NULL,'Region I','Ilocos Region',NULL,NULL),(10,'2015-01-30 03:14:53',NULL,NULL,'ARMM','Autonomous Region in Muslim Mindanao',NULL,NULL),(11,'2015-01-30 03:15:16',NULL,NULL,'Region II','Cagayan Valley',NULL,NULL),(12,'2015-01-30 03:15:38',NULL,NULL,'Region IV-A','CALABARZON',NULL,NULL),(13,'2015-01-30 03:15:55',NULL,NULL,'Region IV-B','MIMAROPA',NULL,NULL),(14,'2015-01-30 03:16:09',NULL,NULL,'Region V','Bicol Region',NULL,NULL),(15,'2015-01-30 03:17:16',NULL,NULL,'Region VI','Western Visayas',NULL,NULL),(16,'2015-01-30 03:17:33',NULL,NULL,'Region VII','Central Visayas',NULL,NULL),(17,'2015-01-30 03:18:04',NULL,NULL,'Region VIII','Eastern Visayas',NULL,NULL),(18,'2015-01-30 03:18:26',NULL,NULL,'Region IX','Zamboanga Peninsula',NULL,NULL),(19,'2015-01-30 03:18:42',NULL,NULL,'Region X','Northern Mindanao',NULL,NULL),(20,'2015-01-30 03:18:58',NULL,NULL,'Region XI','Davao Region',NULL,NULL),(21,'2015-01-30 03:19:24',NULL,NULL,'Region XII','SOCCSKSARGEN',NULL,NULL),(22,'2015-01-30 03:19:44',NULL,NULL,'Region XIII','Caraga',NULL,NULL),(23,'2015-01-30 05:14:52',NULL,NULL,'not specified','not specified',NULL,NULL);
/*!40000 ALTER TABLE `war_customer_region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_school`
--

DROP TABLE IF EXISTS `war_customer_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_school` (
  `war_customer_school_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `school_address_line1` varchar(255) NOT NULL,
  `school_address_line2` varchar(255) NOT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `school_email` varchar(50) NOT NULL,
  `end_dt` datetime DEFAULT NULL,
  `school_landline` varchar(150) NOT NULL,
  `school_name` varchar(50) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `school_logo` bigint(20) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`war_customer_school_id`),
  KEY `FK_64jqvnhg43s069x0g72ev21n8` (`region`),
  CONSTRAINT `FK_64jqvnhg43s069x0g72ev21n8` FOREIGN KEY (`region`) REFERENCES `war_customer_region` (`region_code`)
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_school`
--

LOCK TABLES `war_customer_school` WRITE;
/*!40000 ALTER TABLE `war_customer_school` DISABLE KEYS */;
INSERT INTO `war_customer_school` VALUES (2,'0','0','2015-01-27 18:30:09',NULL,'na@na.nan',NULL,'449020344','ST. PAUL SCH. (E/HS)-SAN RAFAEL',NULL,'2015-03-10 08:30:14',134,'NCR'),(4,'Laoag City','0','2015-01-30 03:33:47',NULL,'xxx@xxxxx.xxx',NULL,'0','SAVIOR CHRISTIAN ACADEMY',NULL,'2015-03-11 09:34:15',137,'Region I'),(5,'Laoag City','0','2015-01-30 05:26:14',NULL,'xxx@xxxxx.xxx',NULL,'772-03-91','HOLY SPIRIT ACADEMY OF LAOAG',NULL,NULL,NULL,'Region I'),(6,'# 15  Laurel St.','0','2015-01-30 05:29:09',NULL,'baguioachievers@yahoo.com',NULL,'442-2853','BAGUIO ACHIEVERS ACADEMY',NULL,NULL,NULL,'CAR'),(7,'Pob.East, Rosario, La Union','0','2015-01-30 05:30:14',NULL,'xxx@xxxxx.xxx',NULL,'072-687-0011 / 09239447543','UNION INSTITUTE OF ROSARIO',NULL,NULL,NULL,'Region I'),(8,'Brgy.13 Gen. Segundo Ave.L.C','0','2015-01-30 05:31:19',NULL,'Sbe_dwcl@yahoo.com',NULL,'722 0736','DIVINE WORD COLLEGE OF LAOAG',NULL,NULL,NULL,'Region I'),(9,'0','0','2015-01-30 05:32:15',NULL,'xxx@xxxxx.xxx',NULL,'0','STA.CRUZ INSTITUTE',NULL,NULL,NULL,'not specified'),(10,'Aspiras Road, San Antonio, Agoo, La Union','0','2015-01-30 05:32:52',NULL,'agoomontessori@yahoo.com',NULL,'(072)710-02-90','Agoo Montessori Learning Center & High School Inc.',NULL,NULL,NULL,'Region I'),(11,'Pugo La Union','0','2015-01-30 05:33:24',NULL,'xxx@xxxxx.xxx',NULL,'0','PUGO CATHOLIC SCHOOL',NULL,NULL,NULL,'Region I'),(12,'Central East # 1, Bangar, La Union','0','2015-01-30 05:34:00',NULL,'xxx@xxxxx.xxx',NULL,'(072)-607-3461','SAINT CHRISTOPHER ACADEMY- ELEM/ HS',NULL,NULL,NULL,'Region I'),(13,'Widdoes St. brgy. 2,','San Fernando City, La Union','2015-01-30 05:34:44',NULL,'lpmique@gmail.com',NULL,'(072) 888-3340','UNION CHRISTIAN COLLEGE',NULL,NULL,NULL,'Region I'),(14,'Nera St. Central East','Bauang, La Union','2015-01-30 05:35:28',NULL,'xxx@xxxxx.xxx',NULL,'072-607-3412','SACRED HEART SCHOOL',NULL,NULL,NULL,'Region I'),(15,'Central East, Baoang, La Union','0','2015-01-30 05:36:04',NULL,'xxx@xxxxx.xxx',NULL,'072-607-29-95','SAINTS PETER & PAUL LEARNING CENTER',NULL,NULL,NULL,'Region I'),(16,'Sta.Lucia,Narvacan, Ilocos Sur','0','2015-01-30 05:36:38',NULL,'ncs_narvacan@yahoo.com',NULL,'(077)7230373','NARVACAN CATHOLIC SCHOOL',NULL,NULL,NULL,'Region I'),(17,'Rizal St. Zone 6','Bangued, Abra','2015-01-30 05:37:10',NULL,'xxx@xxxxx.xxx',NULL,'(074)752-8373','DIVINE WORD COLLEGE OF BANGUED',NULL,NULL,NULL,'CAR'),(18,'I-S Valdez, City of Batac','Ilocos Norte','2015-01-30 05:37:44',NULL,'immaculatconception_academy@yahoo.com.ph',NULL,'792-3114/ 617-1320','IMMACULATE CONCEPTION ACADEMY',NULL,NULL,NULL,'Region I'),(19,'Bangued Abra','0','2015-01-30 05:38:25',NULL,'xxx@xxxxx.xxx',NULL,'074 752 8084','HOLY SPIRIT ACADEMY OF BANGUED',NULL,NULL,NULL,'CAR'),(20,'Washington St., Zone G., Bangued Abra','0','2015-01-30 05:39:00',NULL,'xxx@xxxxx.xxx',NULL,'9276214941','Sacred Heart Academy of Bangued',NULL,NULL,NULL,'CAR'),(21,'Lagangilang, Abra','0','2015-01-30 05:39:27',NULL,'HCS_Lag@yahoo.com',NULL,'0','Holy Cross School',NULL,NULL,NULL,'CAR'),(22,'Buyagan, Poblacion, La Trinidad, Benguet','0','2015-01-30 05:39:55',NULL,'ccdc@ccdc.edu.ph',NULL,'(074)-422-22-21','Cordillera Career Development College',NULL,NULL,NULL,'CAR'),(23,'Padcal, Tuba, Benguet','0','2015-01-30 05:40:21',NULL,'philex_elemschool@yahoo.com',NULL,'0','Philex Mines Elementary School',NULL,NULL,NULL,'CAR'),(24,'Virac, Itogon Benguet','0','2015-01-30 05:40:44',NULL,'xxx@xxxxx.xxx',NULL,'0','Saint Louis High School of Balatoc Inc.',NULL,NULL,NULL,'CAR'),(25,'Padcal, Tuba, Benguet','0','2015-01-30 05:41:07',NULL,'xxx@xxxxx.xxx',NULL,'0','Saint Louis High School- Philex Inc.',NULL,NULL,NULL,'CAR'),(26,'No. 15 Poblacion Itogon Benguet','0','2015-01-30 05:41:30',NULL,'xxx@xxxxx.xxx',NULL,'0','Sacred Heart High School of Itogon Inc.',NULL,NULL,NULL,'CAR'),(27,'Elementary Department Poblacion','La Trinidad, Benguet','2015-01-30 05:41:58',NULL,'xxx@xxxxx.xxx',NULL,'424-5438','San Jose School of La Trinidad Inc.',NULL,NULL,NULL,'CAR'),(28,'Poblacion, La trinidad, 2601 Benguet Philippines','0','2015-01-30 05:42:25',NULL,'sjhs50_Hb@yahoo.com',NULL,'(02)422-5408','San Jose School La Trinidad, Inc.',NULL,NULL,NULL,'CAR'),(29,'Anatmok Tram, Ucab, Itogon, Benguet','0','2015-01-30 05:42:54',NULL,'xxx@xxxxx.xxx',NULL,'0','Saint Louis High School of Antamok, Inc.',NULL,NULL,NULL,'CAR'),(30,'POB. SUR , CABA, LA UNION','0','2015-01-30 05:43:32',NULL,'remy.sabado88@yahoo.com',NULL,'6070868','ST. JOHN THE BAPTIST LEARNING CENTER',NULL,NULL,NULL,'Region I'),(31,'SAN NICOLAS, CANDON CITY, ILOCOS','0','2015-01-30 05:43:59',NULL,'xxx@xxxxx.xxx',NULL,'077-6040167','ST. CECILIA LEARNING CENTER, INC',NULL,NULL,NULL,'Region I'),(32,'0','0','2015-01-30 05:44:25',NULL,'xxx@xxxxx.xxx',NULL,'0','STA. CATALINA ACADEMY',NULL,NULL,NULL,'Region I'),(33,'SAN JOSE , CANDON CITY','0','2015-01-30 05:44:54',NULL,'shepherdkiddieschool@yahoo.com',NULL,'(077) 604-0264','THE SHEPHERD KIDDIE SCHOOL, INC',NULL,NULL,NULL,'Region I'),(34,'QUIRINO, BACNOTAN, LA UNION','0','2015-01-30 05:45:32',NULL,'xxx@xxxxx.xxx',NULL,'888-1348','STELLA MARIS ACADEMY',NULL,NULL,NULL,'Region I'),(35,'# 23 BRENT RD. , BAGUIO CITY','0','2015-01-30 05:46:01',NULL,'xxx@xxxxx.xxx',NULL,'424-1851','PHILIPPINE BELL INTERNATIONAL SCHOOL',NULL,NULL,NULL,'CAR'),(36,'Agoo, La Union','0','2015-01-30 05:46:32',NULL,'xxx@xxxxx.xxx',NULL,'0','Don Mariano Marcos State University-Agoo',NULL,NULL,NULL,'Region I'),(37,'0','0','2015-01-30 05:46:54',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. LOUIS HIGH  SCHOOL OF BOKOD',NULL,NULL,NULL,'CAR'),(38,'ABATAN, BUGUIAS.BENGUET','0','2015-01-30 05:47:18',NULL,'xxx@xxxxx.xxx',NULL,'0','SAN ISIDRO HIGH SCHOOL',NULL,NULL,NULL,'CAR'),(39,'SAYANGAN, ATOK, BENGUET','0','2015-01-30 05:47:44',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL ACADEMY OF SAYANGAN',NULL,NULL,NULL,'CAR'),(40,'KAPANGAN, BENGUET','0','2015-01-30 05:48:07',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. THERESITA\'S HIGH SCHOOL',NULL,NULL,NULL,'CAR'),(41,'SUMACAB ESTE, CABANATUAN CITY','0','2015-01-30 05:48:36',NULL,'xxx@xxxxx.xxx',NULL,'0','COLLEGE OF THE IMMAC CONCEPTION',NULL,NULL,NULL,'Region III'),(42,'MABINI EXTENSION, CABANATUAN CITY','0','2015-01-30 05:49:01',NULL,'xxx@xxxxx.xxx',NULL,'0','WESLEYAN UNIVERSITY PHILIPPINES',NULL,NULL,NULL,'Region III'),(43,'BITAS, CABANATUAN CITY','0','2015-01-30 05:49:34',NULL,'xxx@xxxxx.xxx',NULL,'0','ARAULLO UMIVERSITY',NULL,NULL,NULL,'Region III'),(44,'POBLACION','GUIMBA, NUEVA ECIJA','2015-01-30 05:50:08',NULL,'xxx@xxxxx.xxx',NULL,'0','OUR LADY OF SACRED HEART ACADEMY',NULL,NULL,NULL,'Region III'),(45,'SAN ISIDRO','NUEVA ECIJA','2015-01-30 05:50:37',NULL,'xxx@xxxxx.xxx',NULL,'0','GENERAL DE JESUS COLLEGE',NULL,NULL,NULL,'Region III'),(46,'3700 BAYOMBONG, NUEVA VIZCAYA','0','2015-01-30 05:51:13',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. MARYS UNIVERSITY',NULL,NULL,NULL,'Region II'),(47,'SAN ANTONIO, NUEVA ECIJA','0','2015-01-30 05:52:04',NULL,'xxx@xxxxx.xxx',NULL,'0','ST PAUL SCHOOL',NULL,NULL,NULL,'Region III'),(48,'BAGABAG','NUEVA VIZCAYA','2015-01-30 05:52:36',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. JEROMES ACADEMY',NULL,NULL,NULL,'Region II'),(49,'BONIFACIO ST','SAN JOSE CITY','2015-01-30 05:53:05',NULL,'xxx@xxxxx.xxx',NULL,'0','ST JOSEPH COLLEGE',NULL,NULL,NULL,'Region III'),(50,'CABIAO','NUEVA ECIJA','2015-01-30 05:53:34',NULL,'xxx@xxxxx.xxx',NULL,'0','ST JOHN NEPOMUCENE PAROCHIAL SCHOOL',NULL,NULL,NULL,'Region III'),(51,'STA ROSA','NUEVA ECIJA','2015-01-30 05:54:05',NULL,'xxx@xxxxx.xxx',NULL,'0','ST ROSE OF LIMA CATHOLIC SCHOOL',NULL,NULL,NULL,'Region III'),(52,'5085 BUNO,MATATALAIB','TARLAC CITY','2015-01-30 05:54:30',NULL,'xxx@xxxxx.xxx',NULL,'0','TARLAC CHRISTIAN COLLEGE',NULL,NULL,NULL,'Region III'),(53,'ZARAGOSA','NUEVA ECIJA','2015-01-30 05:54:54',NULL,'xxx@xxxxx.xxx',NULL,'0','VINCENTIAN CATHOLIC SCHOOL',NULL,NULL,NULL,'Region III'),(54,'Calabash Rd.,','Balic, Balic, Sampaloc, Manila','2015-01-30 05:55:20',NULL,'xxx@xxxxx.xxx',NULL,'781-6071','HOLY TRINITY ACADEMY (E/HS)',NULL,NULL,NULL,'NCR'),(55,'Cambridge Street','Cubao, Quezon City','2015-01-30 05:55:56',NULL,'xxx@xxxxx.xxx',NULL,'912-4210','STELLA MARIS COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(56,'G. Masangkay St.,','Sta. Cruz, Manila','2015-01-30 05:56:21',NULL,'xxx@xxxxx.xxx',NULL,'254-8492','ST. STEPHEN\'S HIGH SCHOOL (E/HS)',NULL,NULL,NULL,'NCR'),(57,'0','Samson Rd., Kalookan City','2015-01-30 05:56:48',NULL,'xxx@xxxxx.xxx',NULL,'735-8565','UNIV. OF THE EAST (E/HS)-KALOOKAN',NULL,NULL,NULL,'NCR'),(58,'#1259 Mayhaligue St.,','Tondo, Manila','2015-01-30 05:57:12',NULL,'xxx@xxxxx.xxx',NULL,'252-1937','UNO HIGH SCHOOL  (E/HS)',NULL,NULL,NULL,'NCR'),(59,'Leon Guinto Street,','Malate, Manila','2015-01-30 05:57:40',NULL,'xxx@xxxxx.xxx',NULL,'524-7686','ST. SCHOLASTICA\'S COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(60,'#3500 V. Mapa St. Extension','Sta. Mesa, Manila','2015-01-30 05:58:06',NULL,'xxx@xxxxx.xxx',NULL,'714-7791','DON BOSCO SCHOOL OF STA. MESA',NULL,NULL,NULL,'NCR'),(61,'Pia Donesa St.,','Bo. Canumay, Valenzuela City','2015-01-30 05:58:37',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. MICHAEL SCHOOL OF CANUMAY',NULL,NULL,NULL,'NCR'),(62,'P 9 Pkg. 6, Blk.12 Lot 1','Bagong Silang, Kalookan City','2015-01-30 06:02:52',NULL,'xxx@xxxxx.xxx',NULL,'0','ESCUELA SAN GABRIEL DE ARCHANGEL',NULL,NULL,NULL,'NCR'),(63,'551 Jocson St.,','Sampaloc, Manila','2015-01-30 06:03:21',NULL,'xxx@xxxxx.xxx',NULL,'0','NATIONAL HIGH SCHOOL',NULL,NULL,NULL,'NCR'),(64,'Rainbow Village V, Congressional Vill.,','Novaliches, Kalookan City','2015-01-30 06:03:51',NULL,'xxx@xxxxx.xxx',NULL,'983-0606','ST. JOSEPH COLL. OF KALOOKAN',NULL,NULL,NULL,'NCR'),(65,'Juan Luna St.,','Gagalangin, Tondo, Manila','2015-01-30 06:04:12',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. JOSEPH SCHOOL OF TONDO',NULL,NULL,NULL,'NCR'),(66,'SMOKEY MOUNTAIN RECLAMATION AREA(SMRA)PARADISE HEIGHTS','RD.10 .BRGY:128,BALOT TONDO MANILA','2015-01-30 06:05:22',NULL,'xxx@xxxxx.xxx',NULL,'523-9981','THE PHILIPPINE CHRISTIAN FOUNDATION INC.',NULL,NULL,NULL,'NCR'),(67,'177 TALIBA ST.SAN RAFAEL VILLAGE BALUT TONDO MANILA','0','2015-01-30 06:06:22',NULL,'xxx@xxxxx.xxx',NULL,'253-6482','SAN RAFAEL PAROCHIAL SCHOOL',NULL,NULL,NULL,'NCR'),(68,'2100 PEDRO GIL STREET, STA.ANA MANILA','0','2015-01-30 06:06:44',NULL,'xxx@xxxxx.xxx',NULL,'775-3753/227-2534','GLOBAL GLEAM MONTESSORI INC.',NULL,NULL,NULL,'NCR'),(69,'ESTRADA ST.COR CHROMLUM ST., SAN ANDRES BUKID,MANILA','0','2015-01-30 06:07:11',NULL,'xxx@xxxxx.xxx',NULL,'563-6044','HOLY FAMILY PAROCHIAL SCHOOL',NULL,NULL,NULL,'NCR'),(70,'#629 ALTURA ST.,STA MESA,MANILA','0','2015-01-30 06:07:41',NULL,'xxx@xxxxx.xxx',NULL,'0927-365-2506','ALTURA LEARNING CENTER',NULL,NULL,NULL,'NCR'),(71,'#92M.H DEL PILAR ST,TENEJERO,.MALABON METRO MANILA','0','2015-01-30 06:08:07',NULL,'xxx@xxxxx.xxx',NULL,'0906-655-3339','S.M.E CHILD DEV\'T CENTER',NULL,NULL,NULL,'NCR'),(72,'BLK50,AREA1, E2 MAYA-MAYA ST.,KAUNLARAN VILLAGE,MALABON CITY','0','2015-01-30 06:08:35',NULL,'xxx@xxxxx.xxx',NULL,'285-3352','CHRISTIAN ACADEMY OF MALABON',NULL,NULL,NULL,'NCR'),(73,'4ZINC  ST.TUGATOG MALABON,MANILA','0','2015-01-30 06:09:01',NULL,'xxx@xxxxx.xxx',NULL,'282-4823','BRIGHT BEGINNING CTR. FOR YOUNG CHILDREN',NULL,NULL,NULL,'NCR'),(74,'633 LA FRTALEZA SUBD.CAMARIN,CALOOCAN CITY','0','2015-01-30 06:09:23',NULL,'xxx@xxxxx.xxx',NULL,'961-5744','NEW LAND CENTER FOR EDUCATION.',NULL,NULL,NULL,'NCR'),(75,'B4,LT24,JASMIN VILLA MAGDALENA 3,CALOOCAN CITY','0','2015-01-30 06:09:44',NULL,'xxx@xxxxx.xxx',NULL,'0999-5808250','MOTHER TERESA PREPARATORY:AN ENGLISH SCHOOL',NULL,NULL,NULL,'NCR'),(76,'6048 AREA D., CAMARIN II., BRGY:177. CALOOCAN CITY','0','2015-01-30 06:10:12',NULL,'xxx@xxxxx.xxx',NULL,'0919-9701943','LA MARIAL CHILD DEVELOPMENT CENTER',NULL,NULL,NULL,'NCR'),(77,'PH. V.A ,PKG 1,BLK 28,LT 18,D ,BAGONG SILANG CALOOCAN CITY','0','2015-01-30 06:10:48',NULL,'xxx@xxxxx.xxx',NULL,'394-2911','RHEXANE ACADEMY INC.',NULL,NULL,NULL,'NCR'),(78,'B-8L-1 PHASE 1., SENATE VILLAGE.CALOOCAN CITY','0','2015-01-30 06:11:12',NULL,'xxx@xxxxx.xxx',NULL,'0906-5305301','GATE OF HEAVEN ACADEMY',NULL,NULL,NULL,'NCR'),(79,'200 3-CA.MABINI ST.,MAYPAJO,CALOOCAN CITY','0','2015-01-30 06:11:39',NULL,'xxx@xxxxx.xxx',NULL,'491-3358','MASTER HAND LEARNING SCHOOL',NULL,NULL,NULL,'NCR'),(80,'244 KABUTUHAN RD.,DEPARO CALOOCAN CITY','0','2015-01-30 06:12:16',NULL,'xxx@xxxxx.xxx',NULL,'0918-6277965','PLEDGE OF LOVE SCHOOL',NULL,NULL,NULL,'NCR'),(81,'COR.LANGARAY ST.DAGAT DAGATAN AVE.CALOOCAN CITY','0','2015-01-30 06:12:42',NULL,'xxx@xxxxx.xxx',NULL,'324-3846','SALAVTION ARMY',NULL,NULL,NULL,'NCR'),(82,'16 CHICAGO ST.UE HILLS SUBD.CALOOCAN CITY','0','2015-01-30 06:13:06',NULL,'xxx@xxxxx.xxx',NULL,'361-8850','KALOOKAN ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'NCR'),(83,'117 DREXEL ST.UNIV.HILLS SUBD.,CALOOCAN CITY','0','2015-01-30 06:13:35',NULL,'xxx@xxxxx.xxx',NULL,'362-4079','INSTITUTO DE STO.NINO',NULL,NULL,NULL,'NCR'),(84,'TIERAMAS EAST BAGUMBONG,CALOOCAN CITY','0','2015-01-30 06:14:31',NULL,'xxx@xxxxx.xxx',NULL,'330-1288','IMELDA SDA ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'NCR'),(85,'PH.9 PACKAGE 5, BLK 50 LOT 789 BAGONG SILANG.CALOOCAN CITY','0','2015-01-30 06:14:56',NULL,'xxx@xxxxx.xxx',NULL,'3301288','GRACE LEARNING CENTER',NULL,NULL,NULL,'NCR'),(86,'175VELASCO EXT.,SANGANDAAN,CALOOCAN CITY','0','2015-01-30 06:15:18',NULL,'xxx@xxxxx.xxx',NULL,'288-0013','CASA DE SOLEIL CHILD DEVELOPMENT CENTER',NULL,NULL,NULL,'NCR'),(87,'TONDO,MANILA','0','2015-01-30 06:15:38',NULL,'xxx@xxxxx.xxx',NULL,'256-2330','THE LORD IS MY SHEPHERD LEARNING CENTER',NULL,NULL,NULL,'NCR'),(88,'1307.DIOKNO ST.,COR BATANGAS ST,STA CRUZ MANILA','0','2015-01-30 06:15:58',NULL,'xxx@xxxxx.xxx',NULL,'255-9263','HOLY HEART CHRISTIAN ACA',NULL,NULL,NULL,'NCR'),(89,'732 INSTRUCCION ST.SAMPALOC MANILA','0','2015-01-30 06:16:24',NULL,'xxx@xxxxx.xxx',NULL,'742-3661','ST.VINCENT BLESSED SCHOOL OF MANILA',NULL,NULL,NULL,'NCR'),(90,'#1232 STA MARIA ST. TONDO.,MANILA','0','2015-01-30 06:16:44',NULL,'xxx@xxxxx.xxx',NULL,'242-4185','ST.PAUL GRADE SCHOOL',NULL,NULL,NULL,'NCR'),(91,'STO.DOMINGO ST.,INTRAMUROS,MANILA','0','2015-01-30 06:17:07',NULL,'xxx@xxxxx.xxx',NULL,'527-3797','COLEGIO DE STA.ROSA',NULL,NULL,NULL,'NCR'),(92,'504A. MABINI ST., CALOOCAN CITY','0','2015-01-30 06:17:26',NULL,'xxx@xxxxx.xxx',NULL,'288-7288','KALOOKAN BETHEL CHRISTIAN SCHOOL',NULL,NULL,NULL,'NCR'),(93,'#6. CLARA ST.ACACIA RD.,MALABON METRO MANILA','0','2015-01-30 06:17:51',NULL,'xxx@xxxxx.xxx',NULL,'288-8274','HIGHER GROUND BAPTIST ACADEMY',NULL,NULL,NULL,'NCR'),(94,'CLAMOR COMPOUND BAGOMBONG,CALOOCAN CITY','0','2015-01-30 06:18:11',NULL,'xxx@xxxxx.xxx',NULL,'936-0271','IMMACULATE MOTHER ACADEMY',NULL,NULL,NULL,'NCR'),(95,'378 GEN.LUIS ST.,MOVALICHES,CALOOCAN CITY','0','2015-01-30 06:18:31',NULL,'xxx@xxxxx.xxx',NULL,'4190510','SILVERLINKS SCHOOL',NULL,NULL,NULL,'NCR'),(96,'PAZ ST.,PACO MANILA','0','2015-01-30 06:18:52',NULL,'xxx@xxxxx.xxx',NULL,'498-0014','PACO CITIZEN ACADEMY',NULL,NULL,NULL,'NCR'),(97,'536 SANCHOPANZA ST., SAMPALOC MANILA','0','2015-01-30 06:19:13',NULL,'xxx@xxxxx.xxx',NULL,'715-5985','ST.VINCENT LEARNING CENTER-SANCHOPANZA',NULL,NULL,NULL,'NCR'),(98,'LOT 4505 EXTRA ST.,4TH ESTATE SUBD., SUCAT PARANAQUE','0','2015-01-30 06:19:34',NULL,'xxx@xxxxx.xxx',NULL,'820-4521','INTERNATIONAL CHRISTIAN ACADEMY',NULL,NULL,NULL,'NCR'),(99,'MANOTOC DRIVE,CAPITOL HILLS.,DILIMAN,Q.C','0','2015-01-30 06:19:57',NULL,'xxx@xxxxx.xxx',NULL,'951-7454','BENEDICTINE INTERNATIONAL SCHOOL',NULL,NULL,NULL,'NCR'),(100,'99 GEN.LUIS ST., NOVALICHES,QUEZON CITY','0','2015-01-30 06:20:25',NULL,'xxx@xxxxx.xxx',NULL,'937-6685','INTERNATIONAL CHRISTIAN SCHOOL',NULL,NULL,NULL,'NCR'),(101,'ILOCOS SUR ST.BAGO BANTAY ., Q.C','0','2015-01-30 06:20:45',NULL,'xxx@xxxxx.xxx',NULL,'453-3019','ST.PAUL EDUCATIONAL CENTER',NULL,NULL,NULL,'NCR'),(102,'47 P.ZAMORA ST.,CALOOCAN CITY','0','2015-01-30 06:21:07',NULL,'xxx@xxxxx.xxx',NULL,'285-3702','KALOOKAN EVANGELICAL SCHOOL',NULL,NULL,NULL,'NCR'),(103,'949/959 BASILIO ST.,SAMPALOC MANILA','0','2015-01-30 06:21:32',NULL,'xxx@xxxxx.xxx',NULL,'743-0601','OUR LADY OF DIVINE GRACE SCHOOL DE MANILA',NULL,NULL,NULL,'NCR'),(104,'MALABON','0','2015-01-30 06:21:53',NULL,'xxx@xxxxx.xxx',NULL,'0915-3092046','ST.THERESE OF CHILD JESUS',NULL,NULL,NULL,'NCR'),(105,'163 MENDIOLA ST.SAN MIGUEL,MANILA','0','2015-01-30 06:22:16',NULL,'xxx@xxxxx.xxx',NULL,'735-6031','COLLEGE OF THE HOLY SPIRIT',NULL,NULL,NULL,'NCR'),(106,'2215-17 SEVERINO REYES ST.,STA CRUZ MANILA','0','2015-01-30 06:22:40',NULL,'xxx@xxxxx.xxx',NULL,'361-8850','MANILA CENTRAL ADVENTIST ELEM.SCHOOL',NULL,NULL,NULL,'NCR'),(107,'1119 IBI RD,.COMMONWEALTH NEAR BATASAN HILL,Q,C','0','2015-01-30 06:23:12',NULL,'xxx@xxxxx.xxx',NULL,'361-8850','COMMON WEALTH ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'NCR'),(108,'516 GOV.A.PASCUAL AVE., NAVOTAS METRO MANILA','0','2015-01-30 06:24:52',NULL,'xxx@xxxxx.xxx',NULL,'361-8850','DAANG HARI ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'NCR'),(109,'MANUEL L. QUEZON ST. BRGY: C.M RECTO, ANGELES CITY','0','2015-01-30 06:25:54',NULL,'xxx@xxxxx.xxx',NULL,'0','ANGELES ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(110,'ORANI,BATAAN','0','2015-01-30 06:26:14',NULL,'xxx@xxxxx.xxx',NULL,'0','TALAMONDOK ADVENTIST ELEMENTARY SCHOOL.',NULL,NULL,NULL,'Region III'),(111,'3 SABITAN ST. SANTO ROSARIO MALOLOS BULACAN','0','2015-01-30 06:26:37',NULL,'xxx@xxxxx.xxx',NULL,'0','MALOLOS ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(112,'MALHACAN ST. MEYCAUAYAN BULACAN.','0','2015-01-30 06:26:57',NULL,'xxx@xxxxx.xxx',NULL,'0','MEYCAUAYAN ADVENTIST ELEMENTARY SCHOOL.',NULL,NULL,NULL,'Region III'),(113,'P.DELA MERCED ST.BAYUMBONG POBLACION','0','2015-01-30 06:27:18',NULL,'xxx@xxxxx.xxx',NULL,'0','NORZAGARAY ADVENTIST ELEMENTARY SCHOOL.',NULL,NULL,NULL,'Region III'),(114,'BANTUG NORTE,CABANATUAN CITY','0','2015-01-30 06:27:38',NULL,'xxx@xxxxx.xxx',NULL,'0','CABANATUAN ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(115,'SAN ROQUE, NUEVA ECIJA','0','2015-01-30 06:27:56',NULL,'xxx@xxxxx.xxx',NULL,'0','CABIAO ADVENTIST ELEMNTARY SCHOOL',NULL,NULL,NULL,'Region III'),(116,'#14, 26TH ST.OLONGAPO ZAMBALES','0','2015-01-30 06:29:06',NULL,'xxx@xxxxx.xxx',NULL,'0','OLONGAPO ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(117,'NATIVIDAD GUAGUA ,PAMPANGA','0','2015-01-30 06:29:25',NULL,'xxx@xxxxx.xxx',NULL,'0','NATIVIDAD ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(118,'STA.CATALINA SAN LUIS PAMPANGA','0','2015-01-30 06:29:43',NULL,'xxx@xxxxx.xxx',NULL,'0','STA.CATALINA ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(119,'LAZATIN SUBD.TARLAC CITY','0','2015-01-30 06:30:00',NULL,'xxx@xxxxx.xxx',NULL,'0','TARLAC ADVENTIST ELEMTARY SCHOOL',NULL,NULL,NULL,'Region III'),(120,'QUEZON ST. CASIGURAN,AURORA','0','2015-01-30 06:30:19',NULL,'xxx@xxxxx.xxx',NULL,'0','BALER ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(121,'BALER CASIGURAN RD. POBLACION','0','2015-01-30 06:30:36',NULL,'xxx@xxxxx.xxx',NULL,'0','CASIGURAN ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region III'),(122,'TARTARIA. SILANG CAVITE','0','2015-01-30 06:31:06',NULL,'xxx@xxxxx.xxx',NULL,'0','HILLTOP SDA ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region IV-A'),(123,'SAN ISIDRO,MAGALLANES CAVITE','0','2015-01-30 06:31:28',NULL,'xxx@xxxxx.xxx',NULL,'0','MAGALLANES ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region IV-A'),(124,'TUBUAN.SILANG CAVITE','0','2015-01-30 06:31:52',NULL,'xxx@xxxxx.xxx',NULL,'0','SILANG ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'Region IV-A'),(125,'Lot 7, Franville Subdivision','Camarin, Kalookan City','2015-01-30 06:33:22',NULL,'xxx@xxxxx.xxx',NULL,'0','HOLY REDEEMER SCHOOL (E/HS)',NULL,NULL,NULL,'NCR'),(126,'0','C.M. Recto Ave., Manila','2015-01-30 06:33:45',NULL,'xxx@xxxxx.xxx',NULL,'735-8565','UNIV. OF THE EAST (E/HS)',NULL,NULL,NULL,'NCR'),(127,'# 175 8th Avenue Extension','Grace Park, Kalookan City','2015-01-30 06:34:05',NULL,'xxx@xxxxx.xxx',NULL,'0','PHIL. CULTURAL HIGH SCHOOL E/H-KALOOKAN',NULL,NULL,NULL,'NCR'),(128,'EDSA','Kalookan City','2015-01-30 06:34:27',NULL,'xxx@xxxxx.xxx',NULL,'364- 1071','MANILA CENTRAL UNIVERSITY (E/HS)',NULL,NULL,NULL,'NCR'),(129,'#1253 Jose Abad Santos St.,','Tondo, Manila','2015-01-30 06:34:51',NULL,'xxx@xxxxx.xxx',NULL,'525-0501','PHIL. CULTURAL HIGH SCHOOL-TONDO',NULL,NULL,NULL,'NCR'),(130,'B-31 L-1&2 Narra St., Ph.II,','Rainbow Vill.,Bagumbong,Caloocan City','2015-01-30 06:35:14',NULL,'xxx@xxxxx.xxx',NULL,'938-4907','ESCUELA DE SOPHIA OF CALOOCAN',NULL,NULL,NULL,'NCR'),(131,'272 Plaza Sta. Teresita,','Sampaloc, Manila','2015-01-30 06:35:49',NULL,'xxx@xxxxx.xxx',NULL,'781-3633','NAZARETH SCHOOL',NULL,NULL,NULL,'NCR'),(132,'BAESA ROAD,CALOOCAN CITY','0','2015-01-30 06:36:08',NULL,'xxx@xxxxx.xxx',NULL,'361-8850','BAESA ADVENTIST ELEMENTARY SCHOOL',NULL,NULL,NULL,'NCR'),(133,'0','C.M. Recto Ave., Manila','2015-01-30 06:36:31',NULL,'xxx@xxxxx.xxx',NULL,'739-8931','SAN SEBASTIAN COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(134,'Mahinhin St., cor Mayumi St.,','UP Village, Diliman, Quezon City','2015-01-30 06:36:55',NULL,'msbluclaret@gmail.com',NULL,'921-65-84/9218136','CLARET SCHOOL OF Q.C. (E/HS)',NULL,NULL,NULL,'NCR'),(135,'Ortigas Extension','Mandaluyong City','2015-01-30 06:37:20',NULL,'lita.villacruz@lsgh.edu.ph',NULL,'7215101','LA SALLE - GREENHILLS',NULL,NULL,NULL,'NCR'),(136,'P. Gomez St.,','San Fernando City, La Union','2015-01-30 06:37:46',NULL,'rosabelaspiras@yahoo.com',NULL,'072-8442571','CHRIST THE KING COLLEGE (E/HS)',NULL,NULL,NULL,'Region I'),(137,'0','Bantay, Ilocos Sur','2015-01-30 06:38:09',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL COLLEGE - BANTAY (E/HS)',NULL,NULL,NULL,'Region I'),(138,'San Marcelino Street','Ermita, Manila','2015-01-30 06:38:31',NULL,'lucasihan@gmail.com',NULL,'524-01-11','ADAMSON UNIVERSITY (PE/E/HS)',NULL,NULL,NULL,'NCR'),(139,'Nepomuceno St., Tanduay','Quiapo, Manila','2015-01-30 06:39:14',NULL,'xanctus629@yahoo.com',NULL,'7345601','NATIONAL TEACHERS COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(140,'Maysan Road, Malinta','Valenzuela City','2015-01-30 06:39:39',NULL,'xxx@xxxxx.xxx',NULL,'292-65-84','ST. LOUIS COLL. OF VALENZUELA',NULL,NULL,NULL,'NCR'),(141,'12th Avenue,','Grace Park, Kalookan City','2015-01-30 06:40:04',NULL,'secretaryjom@yahoo.com',NULL,'3432525','ST. MARY\'S ACADEMY (OLGA)',NULL,NULL,NULL,'NCR'),(142,'Carlatan,','San Fernando City, La Union','2015-01-30 06:40:28',NULL,'dromero37@yahoo.com',NULL,'728883955','ST. LOUIS COLLEGE-(E/HS) SAN FERNANDO',NULL,NULL,NULL,'Region I'),(143,'cor Katipunan Rd.,','Aurora Blvd., Quezon City','2015-01-30 06:40:49',NULL,'xxx@xxxxx.xxx',NULL,'913-64-10','ST. BRIDGET\'S SCHOOL (E/HS)',NULL,NULL,NULL,'NCR'),(144,'(Formerly St. Anne\'s Academy) 2318','Pedro Gil St., Sta. Ana, Manila','2015-01-30 06:41:11',NULL,'sta.ana_smasa@yahoo.com',NULL,'5638372','ST. MARY\'S ACADEMY - STA. ANA',NULL,NULL,NULL,'NCR'),(145,'Sevilla Rd.,','San Fernando City, La Union','2015-01-30 06:41:37',NULL,'bhc.one@yahoo.com',NULL,'072-700-06-38','BHC EDUCATIONAL INSTITUTE',NULL,NULL,NULL,'Region I'),(146,'#1739 Pedro Gil St.,','Paco, Manila','2015-01-30 06:42:07',NULL,'emie28ph@yahoo.com',NULL,'5642001','CONCORDIA COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(147,'0','Vigan, Ilocos Sur','2015-01-30 06:42:30',NULL,'xxx@xxxxx.xxx',NULL,'777222034','DIVINE WORD COLL. OF VIGAN',NULL,NULL,NULL,'Region I'),(148,'Santolan Rd., Parada,','Malinta, Valenzuela City,','2015-01-30 06:42:53',NULL,'xxx@xxxxx.xxx',NULL,'425-83-15','DIVINE WORD LEARNING CENTER (E/H)',NULL,NULL,NULL,'NCR'),(149,'0','Agoo, La Union','2015-01-30 06:43:18',NULL,'xxx@xxxxx.xxx',NULL,'072-8882353','ST. MARY\'S ACAD.-AGOO (HS)',NULL,NULL,NULL,'Region I'),(150,'0','Candon City, Ilocos Sur','2015-01-30 06:43:46',NULL,'llanesglessie@yahoo.com',NULL,'777426012','ST. JOSEPH INSTITUTE (E/HS)',NULL,NULL,NULL,'Region I'),(151,'5031 Gen. T. De Leon,','Karuhatan, Valenzuela City','2015-01-30 06:44:17',NULL,'ollc.@yahoo.com',NULL,'922-00-77/922-0070','OUR LADY OF LOURDES COLLEGE',NULL,NULL,NULL,'NCR'),(152,'Mulawinan St.,','Lawang Bato, Valenzuela City','2015-01-30 06:44:43',NULL,'xxx@xxxxx.xxx',NULL,'524-04-55','ST. JOSEPH SCH. OF LAWANG BATO',NULL,NULL,NULL,'NCR'),(153,'0','San Juan, La Union','2015-01-30 06:45:21',NULL,'xxx@xxxxx.xxx',NULL,'072-888-26-16','LORMA GRADE SCHOOL',NULL,NULL,NULL,'Region I'),(154,'# 461 William Shaw St., Caloocan City','0','2015-01-30 06:45:41',NULL,'xxx@xxxxx.xxx',NULL,'0','WORLD CITI- COLLEGES INC.',NULL,NULL,NULL,'NCR'),(161,'# 18 MANGO ROAD ,MALABON CITY','0','2015-01-30 06:48:55',NULL,'xxx@xxxxx.xxx',NULL,'447-33-91','SACRED HEART SCHOOL',NULL,NULL,NULL,'NCR'),(162,'E. RODRIGUEZ AVENUE SR., QUEZON CITY','0','2015-01-30 06:49:21',NULL,'highschool@tua.edu.ph',NULL,'725-59-80','TRINITY UNIVERSITY OF ASIA',NULL,NULL,NULL,'NCR'),(163,'TAFT AVENUE,MANILA','0','2015-01-30 06:49:45',NULL,'santaisabelcollege_bedmanila@yahoo.com',NULL,'5259416','SANTA ISABEL COLLEGE',NULL,NULL,NULL,'NCR'),(164,'MAGSINGAL,ILOCOS SUR','0','2015-01-30 06:50:07',NULL,'xxx@xxxxx.xxx',NULL,'077-726-35-96','ST. WILLIAM\'S INSTITUTE',NULL,NULL,NULL,'Region I'),(165,'SAN NICOLAS TOLENTINO PARISH,BALAOAN LA UNION','0','2015-01-30 06:50:27',NULL,'xxx@xxxxx.xxx',NULL,'0','SAN NICOLAS ACADEMY',NULL,NULL,NULL,'Region I'),(166,'YAKAL ST., BAMBANG STA. CRUZ,MANILA','0','2015-01-30 06:50:52',NULL,'xxx@xxxxx.xxx',NULL,'253-22-43','ST. MARY\'S ACADEMY OF YAKAL',NULL,NULL,NULL,'NCR'),(167,'TAGUDIN,ILOCOS SUR','0','2015-01-30 06:51:16',NULL,'xxx@xxxxx.xxx',NULL,'077-6521745','ST. AUGUSTINE\'S SCHOOL',NULL,NULL,NULL,'Region I'),(168,'P. GOMEZ ST., FORTUNE VILLAGE VII, VALENZUELA CITY','0','2015-01-30 06:51:38',NULL,'xxx@xxxxx.xxx',NULL,'291-11-36','SAINT MARY\'S ANGELS SCHOOL',NULL,NULL,NULL,'NCR'),(169,'# 20 STO. ROSARIO ST., PAXTON VAL. CITY','0','2015-01-30 06:51:57',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. THERESE OF THE CHILD JESUS KIDZ',NULL,NULL,NULL,'NCR'),(170,'WEST AVE., PHIL. AM,QUEZON CITY','0','2015-01-30 06:52:20',NULL,'xxx@xxxxx.xxx',NULL,'414-86-69','ST. VINCENT SCHOOL',NULL,NULL,NULL,'NCR'),(171,'PISCADORES ST., BAUANG LA UNION','0','2015-01-30 06:52:43',NULL,'xxx@xxxxx.xxx',NULL,'726077286','LA UNION COLLEGE OF SCIENCE & TECHNOLOGY',NULL,NULL,NULL,'Region I'),(172,'GENERAL AVE., GSIS VILLAGE PROJ. 8,Q.C.','0','2015-01-30 06:53:03',NULL,'xxx@xxxxx.xxx',NULL,'920-34-32','ST. PATRICK SCHOOL OF Q.C.',NULL,NULL,NULL,'NCR'),(173,'# 1 ROAD 3  SAN MIGUEL HEIGHTS,MARULAS VALENZUELA CITY','0','2015-01-30 06:53:26',NULL,'xxx@xxxxx.xxx',NULL,'291-20-48','STO. ROSARIO MONTESSORI SCHOOL',NULL,NULL,NULL,'NCR'),(174,'# 24 TADEO ST., KARUHATAN VALENZUELA CITY','0','2015-01-30 06:53:49',NULL,'chiokiel@yahoo.com',NULL,'440-24-15','ST. PAUL CHRISTIAN SCHOOL',NULL,NULL,NULL,'NCR'),(175,'ABALOS ST., MARULAS VAL. CITY','0','2015-01-30 06:54:09',NULL,'xxx@xxxxx.xxx',NULL,'291-76-73','VALENZUELA CHRISTIAN SCHOOL',NULL,NULL,NULL,'NCR'),(176,'# 1  FELIPE ST., KARUHATAN VALENZUELA CITY','0','2015-01-30 06:54:29',NULL,'xxx@xxxxx.xxx',NULL,'444-72-45','ST. JOSEPH ACADEMY OF VALENZUELA',NULL,NULL,NULL,'NCR'),(177,'# 17 IGNACIO ST., BACOG NAVOTAS CITY','0','2015-01-30 06:54:57',NULL,'xxx@xxxxx.xxx',NULL,'4465702','LA NAVAL ACADEMY',NULL,NULL,NULL,'NCR'),(178,'# 127 M.H. DEL PILAR ST, MALABON CITY','0','2015-01-30 06:55:16',NULL,'xxx@xxxxx.xxx',NULL,'293-47-30','PROBEX SCHOOL',NULL,NULL,NULL,'NCR'),(179,'M. NAVAL ST., NAVOTAS CITY','0','2015-01-30 06:55:35',NULL,'xxx@xxxxx.xxx',NULL,'2829035','SAN JOSE ACADEMY',NULL,NULL,NULL,'NCR'),(180,'Fatima Ave. Pag-asa Subd. MCARTHUR HIGHWAY Valenzuela City','0','2015-01-30 06:56:04',NULL,'xxx@xxxxx.xxx',NULL,'293-10-18','OUR LADY OF FATIMA UNIVERSITY(BED)',NULL,NULL,NULL,'NCR'),(181,'P2 A-3 Kaunlaran Village, Malabon City','0','2015-01-30 06:56:27',NULL,'xxx@xxxxx.xxx',NULL,'447-81-53','PAPER AND PEN KIDDIE SCHOOL',NULL,NULL,NULL,'NCR'),(182,'525 Gov. Pascual cor. Daang Hari Navotas City','0','2015-01-30 06:56:50',NULL,'xxx@xxxxx.xxx',NULL,'282-33-47','PNSJ PRE-SCHOOL',NULL,NULL,NULL,'NCR'),(183,'# 99 M. H. DEL PILAR ST., MALANDAY VAL. CITY','0','2015-01-30 06:57:08',NULL,'xxx@xxxxx.xxx',NULL,'0','HIGH HORIZON  LEARNING CENTER',NULL,NULL,NULL,'NCR'),(184,'282 Brgy. 163 Tandang Sora Ext.Sta. Queteria Caloocan City','0','2015-01-30 06:57:30',NULL,'xxx@xxxxx.xxx',NULL,'0','LITTLE MINES LEARNING CENTER',NULL,NULL,NULL,'NCR'),(185,'JEREMIAS ST.,KARUHATAN VALENZUELA CITY','0','2015-01-30 06:57:49',NULL,'xxx@xxxxx.xxx',NULL,'292-85-92','PHILIPPINE FAITH ACADEMY',NULL,NULL,NULL,'NCR'),(186,'BRGY. CATBANGEN, PORO SAN FERNANDO CITY LA UNION','0','2015-01-30 06:58:07',NULL,'xxx@xxxxx.xxx',NULL,'722420406','ST. JUDE MONTESSORI EDUC\'L. CENTER',NULL,NULL,NULL,'Region I'),(187,'BRGY. SAPILANG,BACNOTAN LA UNION','0','2015-01-30 06:58:24',NULL,'xxx@xxxxx.xxx',NULL,'0','DON MARIANO MARCOS STATE UNIVERSITY',NULL,NULL,NULL,'Region I'),(188,'Don Asprer Road, Pagdalangan Norte,San Fernando City La Union','0','2015-01-30 06:58:41',NULL,'xxx@xxxxx.xxx',NULL,'0','VILLA CHERRY MONTESSORI SCHOOL',NULL,NULL,NULL,'Region I'),(189,'7 Gretchen Ave., cor Karen St.,Tanaada Subd. Gen. T. De Leon Val. City','0','2015-01-30 06:59:04',NULL,'xxx@xxxxx.xxx',NULL,'277-01-20','MONTESSORI ACADEMY OF VALENZUELA',NULL,NULL,NULL,'NCR'),(190,'#22 Maysan Rd., Near PLDT,Malinta Val. City','0','2015-01-30 06:59:26',NULL,'xxx@xxxxx.xxx',NULL,'2918821','CHILDREN OF MARY IMMACULATE COLLEGE',NULL,NULL,NULL,'NCR'),(191,'Phase I-C, Kaunlaran Village, Navotas City','0','2015-01-30 06:59:47',NULL,'xxx@xxxxx.xxx',NULL,'282-50-80','SAN LORENZO RUIZ PAROCHIAL SCHOOL',NULL,NULL,NULL,'NCR'),(192,'BRGY. LICIADA, BUSTOS BULACAN','0','2015-01-30 07:01:14',NULL,'xxx@xxxxx.xxx',NULL,'044-619-10-31','SAN ISIDRO PAROCHIAL SCHOOL',NULL,NULL,NULL,'Region III'),(193,'G/F #12 KH St., West Kamias,Q.C','0','2015-01-30 07:01:43',NULL,'xxx@xxxxx.xxx',NULL,'435-43-05','CLARION SCHOOL INTERNATIONAL',NULL,NULL,NULL,'NCR'),(194,'124 P. Faustino St.,Punturin,Valenzuela City','0','2015-01-30 07:02:04',NULL,'xxx@xxxxx.xxx',NULL,'456-94-81','ST. BENEDICT ACADEMY OF VALENZUELA',NULL,NULL,NULL,'NCR'),(195,'# 13 AGLIPAY ORTIZ ST., NAGUILIAN LA UNION','0','2015-01-30 07:22:18',NULL,'clu_elem@yahoo.com',NULL,'0','COLEGIO DELA UNION INC.',NULL,NULL,NULL,'Region I'),(196,'# 345 M.H. Del Pilar,Arkong Bato Val. City','0','2015-01-30 07:22:36',NULL,'xxx@xxxxx.xxx',NULL,'0','ACADEMIA DE SAN GABRIEL OF VAL.',NULL,NULL,NULL,'NCR'),(197,'#469 Gen. Luna St.,Hulong Dagat Malabon City','0','2015-01-30 07:22:56',NULL,'xxx@xxxxx.xxx',NULL,'446-23-95','UNITED METHODIST LEARNING CENTER',NULL,NULL,NULL,'NCR'),(198,'Hiyas St., Fortune VII Village,Val. City','0','2015-01-30 07:23:24',NULL,'xxx@xxxxx.xxx',NULL,'277-34-87','ROSEWOOD MONT. SCHOOL',NULL,NULL,NULL,'NCR'),(199,'Pennsylvania Ave., San Francisco,CATBANGEN SAN FERNANDO CITY LA UNION','0','2015-01-30 07:23:46',NULL,'xxx@xxxxx.xxx',NULL,'0','ASSEMBLY OF GOD LEARNING CENTER',NULL,NULL,NULL,'Region I'),(200,'Disso-Or, Bauang, La Union,Bauang La Union','0','2015-01-30 07:24:15',NULL,'xxx@xxxxx.xxx',NULL,'072-6071412','YESUAH CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region I'),(201,'# 12 AMAPOLA ST., PAGASA SUBD. MAYSAN ROAD VAL. CITY','0','2015-01-30 07:24:34',NULL,'xxx@xxxxx.xxx',NULL,'444-52-40','TOUCHED BY GOD\'S GRACE LEARNING CENTER',NULL,NULL,NULL,'NCR'),(202,'15 k10th St., Kamias,Quezon City','0','2015-01-30 07:24:55',NULL,'xxx@xxxxx.xxx',NULL,'0','ZBDGE PRE SCHOOL',NULL,NULL,NULL,'NCR'),(203,'M. NAVAL ST., TANGOS NAVOTAS CITY','0','2015-01-30 07:25:14',NULL,'xxx@xxxxx.xxx',NULL,'443-25-35','MARVIN RADER SCHOOL',NULL,NULL,NULL,'NCR'),(204,'#56 Sili St., Lawa,Meycauayan Bulacan','0','2015-01-30 07:25:37',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. MARY MONTESSORI CENTER-LAWA',NULL,NULL,NULL,'Region III'),(205,'#77 Lingunan St., Valenzuela City','0','2015-01-30 07:25:59',NULL,'xxx@xxxxx.xxx',NULL,'4437106','HEART AND MIND DAY CARE CENTER',NULL,NULL,NULL,'NCR'),(206,'46 T. Santiago ST.,Canumay Valenzuela City','0','2015-01-30 07:26:23',NULL,'xxx@xxxxx.xxx',NULL,'0','MUNTING PUSO DAY CARE CENTER',NULL,NULL,NULL,'NCR'),(207,'Metrocom Homes, Almar Subd.','Camarin Caloocan City','2015-01-30 07:26:45',NULL,'fossil282002@yahoo.com',NULL,'9615968','MYSTICAL ROSE SCHOOL OF CALOOCAN',NULL,NULL,NULL,'NCR'),(208,'Sta. Monica,','San Simon, Pampanga','2015-01-30 07:27:11',NULL,'aths_1970@yahoo.com',NULL,'9209488912','ASSUMPTA TECHNICAL HIGH SCHOOL',NULL,NULL,NULL,'Region III'),(209,'1117 Bagumbong Bulo St.,','Bagumbong, Kalookan City','2015-01-30 07:27:34',NULL,'ourlordsangelsschool@yahoo.com.ph',NULL,'9364731','OUR LORD\'S ANGEL SCHOOL',NULL,NULL,NULL,'NCR'),(210,'B-1 L-23-26 Hillcrest Village,','Camarin Rd., Caloocan City','2015-01-30 07:27:55',NULL,'hascocaloocan@gmail.com',NULL,'9621949','HOLY ANGEL SCHOOL OF CALOOCAN',NULL,NULL,NULL,'NCR'),(211,'#4 Ninang Helen, BF Homes, Ph. I & II','Novaliches, Kalookan City','2015-01-30 17:27:20',NULL,'yetreyes061904@yahoo.com.ph',NULL,'9361175','HOLY INFANT MONTESSORI SCHOOL',NULL,NULL,NULL,'NCR'),(212,'Dau,','Mabalacat, Pampanga','2015-01-30 17:27:50',NULL,'jcfc@pldtdsl.net',NULL,'045-6245205','JOSE FELICIANO COLLEGE',NULL,NULL,NULL,'Region III'),(213,'San Agustin, (near Jonafer Mall)','San Fernando City, Pampanga','2015-01-30 17:28:19',NULL,'asianmontessoricenter@yahoo.com',NULL,'045-9613193','ASIAN MONTESSORI CENTER',NULL,NULL,NULL,'Region III'),(214,'#1117 Petunia cor Chrysanthemum Sts.,','Area B, Camarin, Caloocan City','2015-01-30 17:28:55',NULL,'ollcs.2000@gmail.com',NULL,'9628584','OUR LADY OF LOURDES CATHOLIC SCHOOL',NULL,NULL,NULL,'NCR'),(215,'Pilar Shopping Arcade,','Pilar, Bataan','2015-01-30 17:29:20',NULL,'xxx@xxxxx.xxx',NULL,'047-2377030','BATAAN CHRISTIAN SCHOOL - PILAR',NULL,NULL,NULL,'Region III'),(216,'Don Angeles St., Angeles City Center,','Pandan, Angeles City','2015-01-30 17:29:46',NULL,'brightwds@yahoo.com',NULL,'045-8874789','BRIGHTWOOD SCHOOL',NULL,NULL,NULL,'Region III'),(217,'2nd Floor Fiesta Market. Bldg.,','V. Tiomico St.,San Fernando, Pampanga','2015-01-30 17:30:30',NULL,'xxx@xxxxx.xxx',NULL,'045-9637801','SURE VALUES LEARNING SCHOOL',NULL,NULL,NULL,'Region III'),(218,'Olondriz St., Poblacion,','Masinloc, Zambales','2015-01-30 17:30:53',NULL,'sasmasinloc@yahoo.com',NULL,'047-3070320','SAN ANDRES SCHOOL OF MASINLOC',NULL,NULL,NULL,'Region III'),(219,'Poblacion,','Sta. Cruz Zambales','2015-01-30 17:31:19',NULL,'santacruzacademy_scz@yahoo.com',NULL,'047-8312858','STA. CRUZ ACADEMY (HS)',NULL,NULL,NULL,'Region III'),(220,'Mabiga','Mabalacat, Pampanga','2015-01-30 17:31:41',NULL,'jhayven_24@yahoo.com',NULL,'045-3321866','MARY HELP OF CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region III'),(221,'Lacandula St.,','Mariveles, Bataan','2015-01-30 17:32:04',NULL,'xxx@xxxxx.xxx',NULL,'047-9355665','LLAMAS MEMORIAL INSTITUTE (HS)',NULL,NULL,NULL,'Region III'),(222,'Matain,','Subic, Zambales','2015-01-30 17:32:26',NULL,'st_anthony_school_of_matain_inc@yahoo.com',NULL,'047-2321075','ST. ANTHONY ACADEMY',NULL,NULL,NULL,'Region III'),(223,'737 Labitan St., Central Business','District, Subic Bay Freeport, Zambales','2015-01-30 17:32:48',NULL,'csm_subic@yahoo.com',NULL,'047-2323800','SUBIC MONTESSORI SCHOOL',NULL,NULL,NULL,'Region III'),(224,'141-143 10th Ave.,','6 & 7 St., Kalookan City','2015-01-30 17:33:18',NULL,'xxx@xxxxx.xxx',NULL,'3677502','SYSTEM PLUS COMPUTER COLL.-E/H',NULL,NULL,NULL,'NCR'),(225,'Amparo Subdivision','Novaliches, Kaloocan City','2015-01-30 17:33:45',NULL,'stanthonynovaschool@yahoo.com',NULL,'9390844','ST. ANTHONY NOVA SCHOOL (E/HS)',NULL,NULL,NULL,'NCR'),(226,'Kamias Rd., Brgy. Tucop,','Dinalupihan, Bataan','2015-01-30 17:34:08',NULL,'mothermargheritadebrincat@yahoo.com',NULL,'047-4815697','MOTHER MARGHERITA DE BRINCAT CATH. SCH.',NULL,NULL,NULL,'Region III'),(227,'Jesus Street,','Pulong-Bulo, Angeles City','2015-01-30 17:34:32',NULL,'xxx@xxxxx.xxx',NULL,'0','ANGELES SPED CENTER',NULL,NULL,NULL,'Region III'),(228,'Lot 9-10 Blk 3-13, Ramon Dizon St.,','Diamond Subd., Balibago, Angeles City','2015-01-30 17:35:40',NULL,'xxx@xxxxx.xxx',NULL,'045-6258411','ROYAL INTERNATIONAL SCHOOL',NULL,NULL,NULL,'Region III'),(229,'Cabangan','Zambales','2015-01-30 17:36:11',NULL,'xxx@xxxxx.xxx',NULL,'047-8106998','IMM. CONCEPCION ACADEMY',NULL,NULL,NULL,'Region III'),(230,'San Pedro, Mexico Boundary &','Sta. Ana, Pampanga','2015-01-30 17:36:33',NULL,'xxx@xxxxx.xxx',NULL,'045-4360499','ST. MARY\'S ANGEL SCHOOL OF PAMPANGA',NULL,NULL,NULL,'Region III'),(231,'Phase 2 Between Blocks O & P','Sta. Lucia Resettlement, Magalang, Pamp.','2015-01-30 17:36:55',NULL,'cvapampanga@gmail.com',NULL,'045-8890080','CHRISTIAN VISION ACADEMY',NULL,NULL,NULL,'Region III'),(232,'43-B Marlim Blvd.cor Debra St.,','Pulung Marugal, Angeles City','2015-01-30 17:37:18',NULL,'livingstone.is@gmail.com',NULL,'045-4362704','LIVING STONE INTERNATIONAL SCHOOL',NULL,NULL,NULL,'Region III'),(233,'Waling-Waling cor Dona Aurora St.,','Brgy. 177, Camarin, Kalookan City','2015-01-30 17:37:41',NULL,'goldenlink2002@gmail.com',NULL,'961-5836','GOLDEN LINK SCHOOL',NULL,NULL,NULL,'NCR'),(234,'Sto. Domingo','Mexico, Pampanga','2015-01-30 17:38:13',NULL,'rolandocalma@yahoo.com',NULL,'045-875-0490','DOMINICAN KIDDIE SCHOOL-MEXICO',NULL,NULL,NULL,'Region III'),(235,'Suborvia Maimpis, Sindalan,','San Fernando, Pampanga','2015-01-30 17:39:43',NULL,'filbernschool@gmail.com',NULL,'045-8775062','FILBERN SCHOOL',NULL,NULL,NULL,'Region III'),(236,'SBMA, Subic Bay Freeport','Olongapo City','2015-01-30 17:40:06',NULL,'xxx@xxxxx.xxx',NULL,'047-2526100','SBMA SPED EDUCATION CENTER',NULL,NULL,NULL,'Region III'),(237,'(United Methodist Church)Poblacion','Parian, Mexico, Pampanga','2015-01-30 17:40:28',NULL,'xxx@xxxxx.xxx',NULL,'045-966-0008','MEXICO ECUMENICAL KIDDIE SCHOOL',NULL,NULL,NULL,'Region III'),(238,'San Matias,','Sta. Rita, Pampanga','2015-01-30 17:40:54',NULL,'xxx@xxxxx.xxx',NULL,'045-900-0843','DOMINICAN SCHOOL-STA. RITA',NULL,NULL,NULL,'Region III'),(239,'Resettlement Area,','Floridablanca, Pampanga','2015-01-30 17:41:53',NULL,'divinewisdom_97@yahoo.com',NULL,'0946-6706238','DIVINE WISDOM SCHOOL',NULL,NULL,NULL,'Region III'),(240,'(Formerly Botolan High School)','Botolan, Zambales','2015-01-30 17:42:16',NULL,'castillohelen@ymail.com',NULL,'047-810-5011','STA. MONICA PAROCHIAL SCHOOL',NULL,NULL,NULL,'Region III'),(241,'(Formerly Botolan High School)','Botolan, Zambales','2015-01-30 17:42:16',NULL,'castillohelen@ymail.com',NULL,'047-810-5011','STA. MONICA PAROCHIAL SCHOOL',NULL,NULL,NULL,'Region III'),(242,'0','San Antonio, Zambales','2015-01-30 17:42:37',NULL,'xxx@xxxxx.xxx',NULL,'0921-4766721','T.R. YANGCO EDUC\'L INST.(E/HS)',NULL,NULL,NULL,'Region III'),(243,'Dominican Hills Ave.','Abucay,Bataan','2015-01-30 17:43:04',NULL,'john_2000blue@yahoo.com',NULL,'047-2379431','LETRAN SCIENCE HIGH SCHOOL',NULL,NULL,NULL,'Region III'),(244,'(back of Angeles Univ. Foundation)','Angeles City','2015-01-30 17:43:29',NULL,'xxx@xxxxx.xxx',NULL,'0942-452-7365','ANGELES CITY SCIENCE HIGH SCHOOL',NULL,NULL,NULL,'Region III'),(245,'Maligaya St. Sindalan','City of San Fernando, Pampanga','2015-01-30 17:44:10',NULL,'nasah_csfp@yahoo.com',NULL,'045-4360651','NASAH CENTER FOR LEARNING',NULL,NULL,NULL,'Region III'),(246,'Cadena de Amor, Bataan Economic Zone,','2106 Mariveles, Bataan','2015-01-30 17:44:37',NULL,'bms_mariveles@yahoo.com',NULL,'047-9355770','BEPZ MULTINATIONAL SCHOOL (E/H)',NULL,NULL,NULL,'Region III'),(247,'Poblacion','Arayat, Pampanga','2015-01-30 17:45:02',NULL,'ai.arayatinstitute@yahoo.com',NULL,'0927-5335540','ARAYAT INSTITUTE (HS)',NULL,NULL,NULL,'Region III'),(248,'San Isidro,','Apalit, Pampanga','2015-01-30 17:45:24',NULL,'xxx@xxxxx.xxx',NULL,'045-3029394','BLUERIDGE SCHOOL',NULL,NULL,NULL,'Region III'),(249,'Mabuco,','Hermosa, Bataan','2015-01-30 17:45:47',NULL,'audjorge@yahoo.com.ph',NULL,'0920-9827326','JESUS SAVES LEARNING FOUNDATION',NULL,NULL,NULL,'Region III'),(250,'0','San Narciso, Zambales','2015-01-30 17:46:06',NULL,'xxx@xxxxx.xxx',NULL,'0908-4742811','GOOD SHEPHERED ECUMENICAL LRNG. CTR.',NULL,NULL,NULL,'Region III'),(251,'Greenhills Ave., San Agustin,','San Fernando, Pampanga','2015-01-30 17:46:27',NULL,'xxx@xxxxx.xxx',NULL,'045-9614803','JIL CHRISTIAN SCHOOL-SAN FRND.',NULL,NULL,NULL,'Region III'),(252,'#5 J.L. Gordon Ave., Ext.,','East, Olongapo City','2015-01-30 17:46:51',NULL,'ccs20th@yahoo.com',NULL,'047-2231092','OLONGAPO CITY CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region III'),(253,'# 38 Tullahan Road','Sta. Quiteria, Kalookan City','2015-01-30 17:47:18',NULL,'xxx@xxxxx.xxx',NULL,'9833470','IMM. MOTHER LEARNING CTR.-STA. QUITERIA',NULL,NULL,NULL,'NCR'),(254,'dela Cruz Lim Subd.,','San Francicso,Mabalacat, Pampanga','2015-01-30 17:47:39',NULL,'xxx@xxxxx.xxx',NULL,'045-3318892/0915-7552633','NEHEMIAH CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region III'),(255,'(Charismatic Ecu\'l )1801 Miracle Ave.,','Villa Dolores Subd., Angeles City','2015-01-30 17:48:02',NULL,'xxx@xxxxx.xxx',NULL,'0922-3725239','C.C.E.M.I. ACADEMY',NULL,NULL,NULL,'Region III'),(256,'Villa Angelina Subdivision','Angeles City','2015-01-30 17:48:24',NULL,'xxx@xxxxx.xxx',NULL,'0923-2497355','SCH. OF THE HOLY CHILD',NULL,NULL,NULL,'Region III'),(257,'0','Limay, Bataan','2015-01-30 17:48:49',NULL,'xxx@xxxxx.xxx',NULL,'047-244-8020','KIDS GARDEN LEARNING CENTER',NULL,NULL,NULL,'Region III'),(258,'Emerald Coast Executive Village','Limay, Bataan','2015-01-30 17:49:09',NULL,'xxx@xxxxx.xxx',NULL,'047-2447323','DAUGHTERS OF ST. DOMINIC SCHOOL-LIMAY',NULL,NULL,NULL,'Region III'),(259,'A. Del Rosario St.,','Angeles City','2015-01-30 17:49:36',NULL,'xxx@xxxxx.xxx',NULL,'0922-9760467','ALDERSGATE ECUMENICAL SCHOOL',NULL,NULL,NULL,'Region III'),(260,'(inside Ramon Magsaysay Tech.Inst.)','Zone 6, Iba, Zambales','2015-01-30 17:49:57',NULL,'xxx@xxxxx.xxx',NULL,'0908-8636086','IBA KIDDIELAND MONTESSORI',NULL,NULL,NULL,'Region III'),(261,'Sampaguita Rd., Dona Agripina Subd.,','Angeles City','2015-01-30 17:50:18',NULL,'jeriel_agbuya@yahoo.com',NULL,'045-8887881','NAZARENE ACADEMY (E/HS)',NULL,NULL,NULL,'Region III'),(262,'San Juan Bautista,','Betis,  Guagua, Pampanga','2015-01-30 17:50:43',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. JOHN ACADEMY OF GUAGUA',NULL,NULL,NULL,'Region III'),(263,'San Vicente, Brgy. San Juan','Narciso, Zambales','2015-01-30 17:51:03',NULL,'xxx@xxxxx.xxx',NULL,'0','RIVERS OF JOY CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region III'),(264,'National Hway','Masinloc, Zambales','2015-01-30 17:51:30',NULL,'ruthlumibao@yahoo.com',NULL,',ca','MASINLOC CHRISTIAN ACADEMY',NULL,NULL,NULL,'Region III'),(265,'Gordon Heights','Olongapo City','2015-01-30 17:52:01',NULL,'xxx@xxxxx.xxx',NULL,'0922-8669014','ST. ANNE ACADEMY (E/HS)-OLONGAPO CITY',NULL,NULL,NULL,'Region III'),(266,'#9 Bernabe St.,','Libertad, San Narciso, Zambales','2015-01-30 17:52:24',NULL,'xxx@xxxxx.xxx',NULL,'0908-8984812','LIVING HOPE ZAMBALES CHRISTIAN ACADEMY',NULL,NULL,NULL,'Region III'),(267,'26-13 Amason St., Riverside Subd.,','Friendship, Balibago, Angeles City','2015-01-30 17:52:43',NULL,'xxx@xxxxx.xxx',NULL,'0908-2222754','CHRISTIAN FRIENDSHIP LEARNING CENTER',NULL,NULL,NULL,'Region III'),(268,'Victoria St., San Ignacio Subd.,','Pandan, Angeles City','2015-01-30 17:53:04',NULL,'xxx@xxxxx.xxx',NULL,'047-4457565','SHEKINAH CHRISTIAN SCHOOL',NULL,NULL,NULL,'Region III'),(269,'Brgy. Balsik,','Hermosa, Bataan','2015-01-30 17:53:43',NULL,'xxx@xxxxx.xxx',NULL,'0','SIENA COLLEGE OF HERMOSA',NULL,NULL,NULL,'Region III'),(270,'279 Argon St., Forthneville,Ph.III,','Panipuan, San Fernando City, Pampanga','2015-01-30 17:54:06',NULL,'xxx@xxxxx.xxx',NULL,'0923-1887572','BRIGHT WAYS SCHOOL SYSTEM, INC.',NULL,NULL,NULL,'Region III'),(271,'San Antonio','Zambales','2015-01-30 17:54:26',NULL,'hvbasa@gmail.com',NULL,'0928-7026620','HILDEGARDE VON BINGEN SCIENCE & ARTS HS',NULL,NULL,NULL,'Region III'),(272,'San Narciso','Zambales','2015-01-30 17:54:50',NULL,'xxx@xxxxx.xxx',NULL,'0','CHILD DEVELOPMENT CENTER',NULL,NULL,NULL,'Region III'),(273,'Mabini St. Tenejero','Orani, Bataan','2015-01-30 17:55:10',NULL,'xxx@xxxxx.xxx',NULL,'0','ACADEMY OF QUEEN MARY',NULL,NULL,NULL,'Region III'),(274,'251 Josefa St., Josefa Subd.,','Malabanias Rd., Angeles City','2015-01-30 17:55:35',NULL,'xxx@xxxxx.xxx',NULL,'045-8924880','SHEPHERD\'S BAPTIST CHRISTIAN ACADEMY',NULL,NULL,NULL,'Region III'),(275,'1st Gate, Mawaque Resettlement,','Mabalacat, Pampanga','2015-01-30 17:56:01',NULL,'xxx@xxxxx.xxx',NULL,'0932-6403264','GREAT SHEPHERD CHRISTIAN ACADEMY',NULL,NULL,NULL,'Region III'),(276,'Sta. Cruz','Lubao, Pampanga','2015-01-30 17:56:26',NULL,'xxx@xxxxx.xxx',NULL,'0928-8637046','STA. CRUZ ACADEMY (HS)',NULL,NULL,NULL,'Region III'),(277,'Roman Hiway,','Balanga, Bataan','2015-01-30 17:57:08',NULL,'xxx@xxxxx.xxx',NULL,'0','JIL CHRISTIAN SCHOOL-BALANGA',NULL,NULL,NULL,'Region III'),(278,'0','0','2015-01-30 17:57:37',NULL,'xxx@xxxxx.xxx',NULL,'0','CLSF-EMMANUEL ACADEMY INC.',NULL,NULL,NULL,'not specified'),(279,'0','0','2015-01-30 17:58:02',NULL,'xxx@xxxxx.xxx',NULL,'0','JESUS REIGN CHRISTIAN LEARNING CENTER',NULL,NULL,NULL,'not specified'),(280,'#129 University Ave.,','Kalookan City','2015-01-30 17:58:23',NULL,'xxx@xxxxx.xxx',NULL,'0','DIVINE MERCY COLLEGE FDT.(E/H)',NULL,NULL,NULL,'NCR'),(281,'0','0','2015-01-30 17:58:44',NULL,'xxx@xxxxx.xxx',NULL,'0','MONTESSORI SCHOOL OF MINALIN',NULL,NULL,NULL,'not specified'),(282,'0','0','2015-01-30 17:59:07',NULL,'xxx@xxxxx.xxx',NULL,'0','NORTHRIDGE MONTESSORI SCHOOL',NULL,NULL,NULL,'not specified'),(283,'0','0','2015-01-30 17:59:28',NULL,'xxx@xxxxx.xxx',NULL,'0','RIMBANG EXCELLENT CENTER FOR LEARNING',NULL,NULL,NULL,'not specified'),(284,'0','0','2015-01-30 17:59:52',NULL,'xxx@xxxxx.xxx',NULL,'0','AMEN MISSION CHRISTIAN SCHOOL',NULL,NULL,NULL,'not specified'),(285,'633 cor M.L. Quezon &','P. Zamora St., Angeles City','2015-01-30 18:00:16',NULL,'xxx@xxxxx.xxx',NULL,'0','PROVERBSVILLE SCHOOL',NULL,NULL,NULL,'Region III'),(286,'Zone III, Brgy. San Isidro, Tarlac City','0','2015-01-30 18:00:46',NULL,'sscs_92@yahoo.com',NULL,'(045) 982-42-32','San Sebastian Cathedral School',NULL,NULL,NULL,'Region III'),(287,'Mangga1, Matatalaib, Tarlac City','0','2015-01-30 18:01:04',NULL,'olrcs@yahoo.com',NULL,'(045) 982-06-64','Our Lady of Ransom Catholic School',NULL,NULL,NULL,'Region III'),(288,'Brgy. Samput Paniqui Tarlac','Formerly Ridgemore Academy, Inc.','2015-01-30 18:01:23',NULL,'xxx@xxxxx.xxx',NULL,'045-491-4170','Northridge Institute of Paniqui',NULL,NULL,NULL,'Region III'),(289,'Poblacion Anao Tarlac','0','2015-01-30 18:01:44',NULL,'anao_catholic@yahoo.com',NULL,'0','Anao Catholic School, Inc.',NULL,NULL,NULL,'Region III'),(290,'Sta. Rosa St. Poblacion Sur Paniqui, Tarlac','0','2015-01-30 18:02:06',NULL,'srcs_srcs@yahoo.com',NULL,'(045) 931-1122','Saint Rose Catholic School, Inc.',NULL,NULL,NULL,'Region III'),(291,'Poblacion No. 4','Moncada, Tarlac','2015-01-30 18:02:26',NULL,'MoncadaCathSch@gmail.com',NULL,'(045) 606-50-62','Moncada Catholic School',NULL,NULL,NULL,'Region III'),(292,'McArthur Highway','San Rafael, Tarlac City','2015-01-30 18:02:46',NULL,'crestonacademy@yahoo.com',NULL,'(045) 4910123','Creston Academy',NULL,NULL,NULL,'Region III'),(293,'La Puerta del Sol Hi-Land Subdivision','Tarlac City','2015-01-30 18:03:08',NULL,'tarlac_montessori@yahoo.com',NULL,'(045) 982-0598','Tarlac Montessori School Inc.',NULL,NULL,NULL,'Region III'),(294,'Sto. Cristo, Tarlac City','0','2015-01-30 18:03:27',NULL,'xxx@xxxxx.xxx',NULL,'(045) 982-5440','Don Bosco Technical Institute',NULL,NULL,NULL,'Region III'),(295,'Rizal St. Poblacion Tarlac City','0','2015-01-30 18:03:47',NULL,'xxx@xxxxx.xxx',NULL,'0459825579/9826125','Ecumenical Christian College',NULL,NULL,NULL,'Region III'),(296,'Carriedo St. Pobsur Paniqui, Tarlac','0','2015-01-30 18:04:07',NULL,'paniquichristianschool@yahoo.com',NULL,'931-0091','Paniqui Christian School Inc.',NULL,NULL,NULL,'Region III'),(297,'Legaspi St. Brgy. Poblacion Sur','Paniqui, Tarlac','2015-01-30 18:04:26',NULL,'vincent.school@yahoo.com',NULL,'(045) 326-0043','St. Vincent School Foundation, Inc.',NULL,NULL,NULL,'Region III'),(298,'Pob. 1, Gerona, Tarlac','0','2015-01-30 18:04:47',NULL,'geronacatholic@yahoo.com',NULL,'(045) 931-3656','Gerona Catholic School',NULL,NULL,NULL,'Region III'),(299,'Guadelia St. Concepcion, Tarlac','0','2015-01-30 18:05:08',NULL,'cpncatholicsch@yahoo.com',NULL,'(045) 923-0549','Concepcion Catholic School',NULL,NULL,NULL,'Region III'),(300,'Cutcut 1, Capas, Tarlac','0','2015-01-30 18:05:36',NULL,'wellspringhighschool@yahoo.com',NULL,'(045) 925-0394','Wellspring High School, Inc.',NULL,NULL,NULL,'Region III'),(301,'Romulo Highway, San Vicente','Tarlac City','2015-01-30 18:06:01',NULL,'belindamanglicmot@yahoo.com',NULL,'982-3884','St. Matthew Christian Academy',NULL,NULL,NULL,'Region III'),(302,'San Sebastian Village','Tarlac City','2015-01-30 18:06:34',NULL,'chs_tarlac2012@yahoo.com',NULL,'(045) 982-3952','College of the Holy Spirit Tarlac',NULL,NULL,NULL,'Region III'),(303,'St. Francis Village, City of San Fernando','Pampanga 2000','2015-01-30 18:06:56',NULL,'stfrancisacademyofpamp@gmail.com',NULL,'(045) 961-7768','St. Francis Academy of Pampanga',NULL,NULL,NULL,'Region III'),(304,'120 Manapat St. Poblacion Arayat','Pampanga','2015-01-30 18:07:55',NULL,'agmsi@yahoo.com',NULL,'(045) 885-0368','Adelle Grace Montessori School Inc.',NULL,NULL,NULL,'Region III'),(305,'#389 P. Tan St. Poblacion','Arayat, Pampanga','2015-01-30 18:08:19',NULL,'arayatholychild@yahoo.com',NULL,'(045) 885-1092','Arayat Holy Child Educational Foundation, Inc.',NULL,NULL,NULL,'Region III'),(306,'11th Avenue Uniside Subdivision','San Fernando, Pampanga','2015-01-30 18:08:43',NULL,'xxx@xxxxx.xxx',NULL,'961-5119','Lyndale Academy',NULL,NULL,NULL,'Region III'),(307,'No. 7 Taft St. Buas Candaba, Pampanga','0','2015-01-30 18:09:03',NULL,'xxx@xxxxx.xxx',NULL,'409-9926','Holy Family Child Formation Center',NULL,NULL,NULL,'Region III'),(308,'St. Dominic Corinthian Annex, Dolores, City of','San Fernando, Pampanga','2015-01-30 18:09:35',NULL,'slrcss@yahoo.com',NULL,'961-1091/961-1092','San Lorenzo Ruiz Center of Studies and Schools',NULL,NULL,NULL,'Region III'),(309,'322 San Pablo St.','Mt. View, Balibago','2015-01-30 18:09:58',NULL,'elliamayforto@gmail.com',NULL,'(045) 625-7169','Cogic Montessori Center Foundation, Inc.',NULL,NULL,NULL,'Region III'),(310,'San Antonio, Mexico, Pampanga','0','2015-01-30 18:10:25',NULL,'ourladyofguadalupeschool@hotmail.com',NULL,'966-01-93','Our Lady of Guadalupe School, Inc.',NULL,NULL,NULL,'Region III'),(311,'3612 First St Balibago','Angeles City','2015-01-30 18:10:45',NULL,'xxx@xxxxx.xxx',NULL,'405-0033','Clark Field Christian School',NULL,NULL,NULL,'Region III'),(312,'3-4 Arayat St, Diamond Subdivision','Balibago, Angeles City','2015-01-30 18:11:05',NULL,'xxx@xxxxx.xxx',NULL,'892-0937','Jesus Our Victory Academy Foundation',NULL,NULL,NULL,'Region III'),(313,'5-18 V.Y. Orosa St.','Diamond Subdivision, Balibago','2015-01-30 18:11:26',NULL,'springhillmontessori_philippines@yahoo.com',NULL,'(045) 625-7628/892-4399','Springhill Montessori School',NULL,NULL,NULL,'Region III'),(314,'Balibago, Angeles City','0','2015-01-30 18:12:01',NULL,'xxx@xxxxx.xxx',NULL,'892-4877/372-1848','System Plus College Foundation',NULL,NULL,NULL,'Region III'),(315,'4-4 Maya St. Sta. Maria Village','Balibago, Angeles City','2015-01-30 18:12:24',NULL,'xxx@xxxxx.xxx',NULL,'0','Narciso School Inc',NULL,NULL,NULL,'Region III'),(316,'Sto. Rosario St. Angeles City','0','2015-01-30 18:12:52',NULL,'xxx@xxxxx.xxx',NULL,'(045) 888-8691','Holy Angel University',NULL,NULL,NULL,'Region III'),(317,'Sto. Rosario St. Angeles City','0','2015-01-30 18:13:12',NULL,'HFAGSPO@yahoo.com',NULL,'887-5956/322-7597','Holy Family Academy',NULL,NULL,NULL,'Region III'),(318,'San Nicolas, Masantol, Pampanga','0','2015-01-30 18:13:31',NULL,'snamacabebe@yahoo.com',NULL,'(045) 435-3366','St. Michael School: Center of Catholic Education',NULL,NULL,NULL,'Region III'),(319,'Education, Inc.','Poblacion, Macabebe, Pampanga 2013','2015-01-30 18:13:54',NULL,'snamacabebe@yahoo.com',NULL,'(045) 921-3383','San Nicholas Academy: Center of Catholic',NULL,NULL,NULL,'Region III'),(320,'San Nicolas Masantol Pampanga','0','2015-01-30 18:14:25',NULL,'xxx@xxxxx.xxx',NULL,'9052060258','San Miguel Academy, Inc.',NULL,NULL,NULL,'Region III'),(321,'Bagong Pag-asa Subdivision','San Vicente, Apalit Pampanga','2015-01-30 18:14:48',NULL,'xxx@xxxxx.xxx',NULL,'302-76-98','Holy Child Academy',NULL,NULL,NULL,'Region III'),(322,'Unisite Subdivision, Del Pilar','City of San Fernando, Pampanga 2000','2015-01-30 18:15:10',NULL,'xxx@xxxxx.xxx',NULL,'455-32-00','University of the Assumption',NULL,NULL,NULL,'Region III'),(323,'Pasbul, San Juan','Lubao, Pampanga 2005','2015-01-30 18:15:37',NULL,'maccimroyalacademy2004@yahoo.com',NULL,'(045) 971-7040 or 971-5080','Maccim Royal Academy',NULL,NULL,NULL,'Region III'),(324,'Poblacion 3 Gerona, Tarlac 2302','0','2015-01-30 18:15:56',NULL,'fely_severino@yahoo.com',NULL,'(045) 931-3624','Marian School of Gerona Inc.',NULL,NULL,NULL,'Region III'),(325,'Clark St. Sta. Rita','Olongapo City','2015-01-30 18:16:19',NULL,'ctkcs0300456@gmail.com',NULL,'223-7564','Christ the King Catholic School',NULL,NULL,NULL,'Region III'),(326,'#31 Brill Street','West Bajac, Bajac Olongapo City','2015-01-30 18:16:38',NULL,'xxx@xxxxx.xxx',NULL,'047-224-7401','Learning Circle',NULL,NULL,NULL,'Region III'),(327,'18 St. East Bajac-Bajac','Olongapo City','2015-01-30 18:17:00',NULL,'yam_salang@yahoo.com',NULL,'223-546','St. Joseph College',NULL,NULL,NULL,'Region III'),(328,'890 Rizal Avenue Olongapo City','0','2015-01-30 18:17:20',NULL,'yam_salang@yahoo.com',NULL,'(047)222-270/ 223-2804','Olongapo Wesley School',NULL,NULL,NULL,'Region III'),(329,'32 #rd St. West Tapinac, Olongapo City','0','2015-01-30 18:17:40',NULL,'xxx@xxxxx.xxx',NULL,'224-5491','Little Angel Study Center',NULL,NULL,NULL,'Region III'),(330,'West Poblacion','Palauig, Zambales','2015-01-30 18:18:04',NULL,'arlene_cesdi@yahoo.com',NULL,'9194695165','Carmel Academy',NULL,NULL,NULL,'Region III'),(331,'National Highway, Libertad','San Narciso, Zambales','2015-01-30 18:18:27',NULL,'MMC_Zambales@yahoo.com',NULL,'(047) 913-3607','Magsaysay Memorial College',NULL,NULL,NULL,'Region III'),(332,'San Marcelino, Zambales (Town Proper)','0','2015-01-30 18:18:48',NULL,'stswilliam\'s_school1946sm2@yahoo.com',NULL,'(047) 602-0047','St. William\'s School',NULL,NULL,NULL,'Region III'),(333,'Poblacion Candelaria Zambales','0','2015-01-30 18:19:08',NULL,'sva_cand@yahoo.com',NULL,'0','Saint Vincent Academy',NULL,NULL,NULL,'Region III'),(334,'San Juan, Castillejos, Zambales','0','2015-01-30 18:19:28',NULL,'st_nicholas_academy@hotmail.com',NULL,'(047) 602 - 2261','Saint Nicholas Academy of Castillejos, Inc.',NULL,NULL,NULL,'Region III'),(335,'San Ramon Highway','Dinalupihan, Bataan','2015-01-30 18:19:47',NULL,'rosal_v@yahoo.com',NULL,'9167042338','St. Joseph School of Dinalupihan',NULL,NULL,NULL,'Region III'),(336,'Sta. Elena Orion, Bataan','0','2015-01-30 18:20:05',NULL,'sfibataan@yahoo.com.ph',NULL,'9182924422','St. Francis Catholic School of Bataan',NULL,NULL,NULL,'Region III'),(337,'AH Banzon St Ibayo Balanga City, Bataan','0','2015-01-30 18:20:26',NULL,'apcasi@yahoo.com',NULL,'(047) 2376713','Asia Pacific College of Advanced Studies',NULL,NULL,NULL,'Region III'),(338,'Capitol Drive, San Jose, Balanga City, Bataan','0','2015-01-30 18:20:49',NULL,'mvvs1977@yahoo.com',NULL,'(047) 2370841','Tomas del Rosario College',NULL,NULL,NULL,'Region III'),(339,'Mayon St., Mt. View Village Mariveles, Bataan','0','2015-01-30 18:21:08',NULL,'mvvs1977@yahoo.com',NULL,'(047) 612-01-86','Mountain View Village School',NULL,NULL,NULL,'Region III'),(340,'San Ramon, Dinalupihan, Bataan','0','2015-01-30 18:23:42',NULL,'totustuusdomine@yahoo.com',NULL,'(047) 481-5510','Holy Family Center of Studies',NULL,NULL,NULL,'Region III'),(341,'San Vicente, Orion, Bataan','0','2015-01-30 18:24:03',NULL,'xxx@xxxxx.xxx',NULL,'(047) 244-4311','Jose Rizal Institute',NULL,NULL,NULL,'Region III'),(342,'PBR Housing Compound','Limay, Bataan','2015-01-30 18:24:25',NULL,'peninsulaschool@yahoo.com',NULL,'(047) 244-6941','The Peninsula School',NULL,NULL,NULL,'Region III'),(343,'P. Burgos St, Poblacion, Mariveles, Bataan','0','2015-01-30 18:24:46',NULL,'jesusa_mdatu@yahoo.com',NULL,'(047) 935-4720','SNCCC Learning Center of Mariveles Inc.',NULL,NULL,NULL,'Region III'),(344,'Purok 6 Upper Ipag Mariveles, Bataan','0','2015-01-30 18:25:08',NULL,'sunnyhillsideschool@gmail.com',NULL,'(047) 612-0420','Sunny Hillside School',NULL,NULL,NULL,'Region III'),(345,'Poblacion Hermosa Bataan','0','2015-01-30 18:25:35',NULL,'joselitobmariano@yahoo.com',NULL,'633-13-54','St. Peter of Verona Academy',NULL,NULL,NULL,'Region III'),(346,'Bayan, Orani, Bataan','0','2015-01-30 18:25:59',NULL,'xxx@xxxxx.xxx',NULL,'(047) 431-1225/237-0357','Holy Rosary Parochial Institute',NULL,NULL,NULL,'Region III'),(347,'Mariveles, Bataan','0','2015-01-30 18:26:31',NULL,'assej_6@yahoo.com',NULL,'(047) 612 0599','Blessed Regina Protmann Catholic School',NULL,NULL,NULL,'Region III'),(348,'San Vicente, Orion, Bataan','0','2015-01-30 18:27:47',NULL,'smaa.orion@yahoo.com',NULL,'(047) 244 4512','St. Michael the Archangel Academy',NULL,NULL,NULL,'Region III'),(349,'Rizal St., Dinalupihan, Bataan','0','2015-01-30 18:28:19',NULL,'sja_60@yahoo.com',NULL,'(047) 481 1742/636-5560','St. John Academy',NULL,NULL,NULL,'Region III'),(350,'Zamora St. Brgy. Poblacion Mariveles, Bataan','0','2015-01-30 18:28:41',NULL,'xxx@xxxxx.xxx',NULL,'(047) 935 6182','St. Nicholas Catholic School',NULL,NULL,NULL,'Region III'),(351,'the Pillar Parochial School Inc.','Morong, Bataan','2015-01-30 18:29:02',NULL,'st.james_morong@yahoo.com',NULL,'0','St. James Catholic School of Morong/ Our Lady of',NULL,NULL,NULL,'Region III'),(352,'Poblacion, Samal, Bataan','0','2015-01-30 18:29:30',NULL,'nelmakatrinagarcia@yahoo.com',NULL,'633 - 0572','St. Catherine of Siena Academy',NULL,NULL,NULL,'Region III'),(353,'Wakas','Bocaue, Bulacan','2015-01-30 18:29:55',NULL,'xxx@xxxxx.xxx',NULL,'NO LANDLINE/09334776380','DR. YANGA\'S F.BALAGTAS COLLEGE',NULL,NULL,NULL,'Region III'),(354,'Poblacion','Angat, Bulacan','2015-01-30 18:30:16',NULL,'xxx@xxxxx.xxx',NULL,'044-671-10-24','COL. DE STA. MONICA (E/HS)',NULL,NULL,NULL,'Region III'),(355,'Sta. Isabel','Malolos, Bulacan','2015-01-30 18:30:40',NULL,'xxx@xxxxx.xxx',NULL,'044-791-11-73','HOLY SPIRIT ACADEMY (E/HS)-BULACAN',NULL,NULL,NULL,'Region III'),(356,'0','Malolos, Bulacan','2015-01-30 18:31:04',NULL,'xxx@xxxxx.xxx',NULL,'044-791-02-88','IMM. CONCEPCION SCH. FOR BOYS',NULL,NULL,NULL,'Region III'),(357,'Bunlo','Bocaue, Bulacan','2015-01-30 18:31:29',NULL,'xxx@xxxxx.xxx',NULL,'044-692-18-94','JIL CHRISTIAN COLL.-BOCAUE(E/H)',NULL,NULL,NULL,'Region III'),(358,'Saluysoy, Mc-Arthur','Meycauayan, Bulacan','2015-01-30 18:31:50',NULL,'xxx@xxxxx.xxx',NULL,'044-935-25-89','ST. MARY\'S COLL.-MEYCAUAYAN (E/HS)',NULL,NULL,NULL,'Region III'),(359,'0','Bocaue, Bulacan','2015-01-30 18:35:58',NULL,'xxx@xxxxx.xxx',NULL,'044-692-10-47','STO. NINO ACADEMY - BOCAUE',NULL,NULL,NULL,'Region III'),(360,'Sto. Cristo','San Jose Del Monte, Bulacan','2015-01-30 18:36:33',NULL,'xxx@xxxxx.xxx',NULL,'9175634597','EBENEZER MONTESSORI SCHOOL',NULL,NULL,NULL,'Region III'),(361,'Poblacion,','Pandi, Bulacan','2015-01-30 18:36:54',NULL,'xxx@xxxxx.xxx',NULL,'044-661-06-14','IMM. CONCEPCION CHILD DEV\'T CTR.(E/H)',NULL,NULL,NULL,'Region III'),(362,'Racelis Street','Baliuag, Bulacan','2015-01-30 18:37:15',NULL,'xxx@xxxxx.xxx',NULL,'447662265','ST. MARY\'S COLL.-BALIUAG (E/H)',NULL,NULL,NULL,'Region III'),(363,'T. Santiago St.,','Sta. Maria, Bulacan','2015-01-30 18:37:34',NULL,'xxx@xxxxx.xxx',NULL,'9257241968','SACRED HEART ACADEMY (E/H)',NULL,NULL,NULL,'Region III'),(364,'B.S. Aquino Ave., Tangos,','Baliuag, Bulacan','2015-01-30 18:37:53',NULL,'xxx@xxxxx.xxx',NULL,'9328692186','MONT. DE SAGRADA FAMILIA',NULL,NULL,NULL,'Region III'),(365,'0','Baliuag, Bulacan','2015-01-30 18:38:13',NULL,'xxx@xxxxx.xxx',NULL,'9335184616','LIVING ANGEL CHRISTIAN ACADEMY',NULL,NULL,NULL,'Region III'),(366,'Washington St., Poblacion,','Calumpit, Bulacan','2015-01-30 18:38:35',NULL,'xxx@xxxxx.xxx',NULL,'446751236','ST. JOHN THE BAPTIST CATH. SCH',NULL,NULL,NULL,'Region III'),(367,'(Frmly: Calumpit Institute)','Calumpit, Bulacan','2015-01-30 18:38:55',NULL,'xxx@xxxxx.xxx',NULL,'449130030','COLEGIO DE CALUMPIT (CALUMPIT INSTITUTE)',NULL,NULL,NULL,'Region III'),(368,'STO. ROSARIO SAPANG PALAY COLLEGE','San Jose Del Monte, Bulacan','2015-01-30 18:39:17',NULL,'xxx@xxxxx.xxx',NULL,'9156040234','STO. ROSARIO COLLEGE',NULL,NULL,NULL,'Region III'),(369,'Polo, Poblacion 1,','Valenzuela City','2015-01-30 18:39:41',NULL,'xxx@xxxxx.xxx',NULL,'24440068','SAN DIEGO PAROCHIAL SCHOOL (E/HS)',NULL,NULL,NULL,'NCR'),(370,'Liang St.,','Malolos, Bulacan','2015-01-30 18:40:00',NULL,'xxx@xxxxx.xxx',NULL,'044-791-52-56','BULACAN ECUMENICAL SCH. (E/HS)',NULL,NULL,NULL,'Region III'),(371,'Bryg. Sta. Ana,','Bulacan, Bulacan','2015-01-30 18:40:21',NULL,'xxx@xxxxx.xxx',NULL,'044-792-06-29','BULACAN MONTESSORI SCHOOL',NULL,NULL,NULL,'Region III'),(372,'0','San Rafael, Bulacan','2015-01-30 18:40:41',NULL,'xxx@xxxxx.xxx',NULL,'449020344','ST. PAUL SCH. (E/HS)-SAN RAFAEL',NULL,NULL,NULL,'Region III'),(373,'Zamora St., Dagupan City','0','2015-01-30 18:40:59',NULL,'sjcsdagupan@yahoo.com',NULL,'(075) 522 8259/(075) 515 8655','ST. JOHN\'S CATHEDRAL SCHOOL',NULL,NULL,NULL,'Region I'),(374,'Bonifacio St., Poblacion, Malasiqui, Pangasinan','0','2015-01-30 18:41:44',NULL,'mcs_officialportal@yahoo.com',NULL,'(075)632-2390','MALASIQUI CATHOLIC SCHOOL (E/HS)',NULL,NULL,NULL,'Region I'),(375,'Malong St., San Carlos City, Pangasinan','0','2015-01-30 18:43:44',NULL,'xxx@xxxxx.xxx',NULL,'(075)632-2459','ST. CHARLES ACADEMY (E/H)',NULL,NULL,NULL,'Region I'),(376,'Dr. Martin P. Posadas Ave., San Carlos City, Pangasinan','0','2015-01-30 18:56:08',NULL,'vmuf@mozcom.com',NULL,'(075)634-1111/(075)531-2222 loc. 277 /loc. 154 (HS)','VIRGEN MILAGROSA UNIV. CHILD L. C. (E./HS)',NULL,NULL,NULL,'Region I'),(377,'0','Alaminos City, Pangasinan','2015-01-30 18:56:38',NULL,'xxx@xxxxx.xxx',NULL,'(075) 532 4062','COLEGIO DE SAN JOSE DE ALAMINOS (E/HS)',NULL,NULL,NULL,'Region I'),(378,'Bari, Mangaldan, Pangasinan','0','2015-01-30 18:57:05',NULL,'cherishedmomentsschool@gmail.com',NULL,'(075)653-1066','CHERISHED MOMENTS SCHOOL',NULL,NULL,NULL,'Region I'),(379,'126 Malued District, Dagupan City','0','2015-01-30 18:57:32',NULL,'adelaidacarvajal@yahoo.com',NULL,'(075)653-7247','ST. ALBERT THE GREAT SCHOOL-DAGUPAN',NULL,NULL,NULL,'Region I'),(380,'Tapuac District, Dagupan City','0','2015-01-30 18:57:51',NULL,'xxx@xxxxx.xxx',NULL,'(075)522-2440','EDNA\'S SCHOOL (E/HS)',NULL,NULL,NULL,'Region I'),(381,'Poblacion,','Lingayen, Pangasinan','2015-01-30 18:58:17',NULL,'xxx@xxxxx.xxx',NULL,'(075) 604 1066/(075) 653 1066','ST. COLUMBAN\'S COLLEGE (E/HS)-POBLACION',NULL,NULL,NULL,'Region I'),(382,'Poblacion, Mangaldan, Pangasinan','0','2015-01-30 18:58:43',NULL,'stcsmangaldan@yahoo.com',NULL,'(075)522-5358','STO. TOMAS CATHOLIC SCHOOL',NULL,NULL,NULL,'Region I'),(383,'0','Umingan, Pangasinan','2015-01-30 19:04:50',NULL,'xxx@xxxxx.xxx',NULL,'(075) 522 2440/(075) 522 3572','QUEZON MEMORIAL ACADEMY (E/HS)',NULL,NULL,NULL,'Region I'),(384,'Castro St., Poblacion, Manaoag, Pangasinan','0','2015-01-30 19:05:12',NULL,'hra.olmc@gmail.com',NULL,'(075) 529-0121','OUR LADY OF MANAOAG COLLEGE (E/HS)',NULL,NULL,NULL,'Region I'),(385,'San Vicente Central, Urdaneta City, Pangasinan','0','2015-01-30 19:05:49',NULL,'merrylandmhi@yahoo.com.ph',NULL,'(075)568-6809','MERRYLAND MONTESSORI SCHOOL',NULL,NULL,NULL,'Region I'),(386,'W.A. Jones St., Nalsian, Calasiao, Pangasinan','0','2015-01-30 19:07:10',NULL,'mlls_registrar@yahoo.com',NULL,'(075)517-6604','MOTHER LOURDES LEARNING SCHOOL',NULL,NULL,NULL,'Region I'),(387,'Perez Blvd., Dagupan City','0','2015-01-30 19:07:33',NULL,'agcaoilipet@yahoo.com',NULL,'(075)515-5767','UNIV. OF LUZON (E/HS)',NULL,NULL,NULL,'Region I'),(388,'Salay, Mangaldan, Pangasinan','0','2015-01-30 19:07:56',NULL,'clariceangels_sch@yahoo.com',NULL,'(075)522-2220','CLARICE ANGELS SCHOOL',NULL,NULL,NULL,'Region I'),(389,'Mabini St.,','Mangatarem, Pangasinan','2015-01-30 19:08:20',NULL,'xxx@xxxxx.xxx',NULL,'(075) 513 5214/(075) 522 5358','MANGATAREM CATHOLIC SCHOOL (E/HS)',NULL,NULL,NULL,'Region I'),(390,'Tapuac District, Dagupan','0','2015-01-30 19:08:54',NULL,'dominican_school_dc@yahoo.com.ph',NULL,'(075)522-2435','DOMINICAN SCHOOL OF DAGUPAN',NULL,NULL,NULL,'Region I'),(391,'1 J. Jimenez St., Poblacion, Mangaldan, Pangasinan','0','2015-01-30 19:09:23',NULL,'umc.cinderella@yahoo.com',NULL,'(075)653-1432','CINDERELLA SCHOOL (UMC)',NULL,NULL,NULL,'Region I'),(392,'Blue Beach Village Subd., Bonuan Gueset, Dagupan City','NOTE: ST. MICHAEL SCHOOL-BY THE BAY (New Name)','2015-01-30 19:09:43',NULL,'smbs1986@yahoo.com',NULL,'(075)653-0087','COLL. OF ST. MICHAEL ARCHANGEL',NULL,NULL,NULL,'Region I'),(393,'1 J. Jimenez St., Poblacion, Mangaldan,','Pangasinan','2015-01-30 19:10:52',NULL,'umc.cinderella@yahoo.com',NULL,'(075)653-1432','UMC Cinderella School',NULL,NULL,NULL,'Region I'),(394,'Rizal St., Guiset Norte, San Manuel, Pangasinan','0','2015-01-30 19:11:14',NULL,'smds_smp2438@yahoo.com',NULL,'(075)565-3252','St. Mary\'s Dominican School',NULL,NULL,NULL,'Region I'),(395,'Poblacion, Labrador, Pangasinan','0','2015-01-30 19:11:41',NULL,'columbanlab@yahoo.com',NULL,'0','St. Columban\'s School',NULL,NULL,NULL,'Region I'),(396,'Zone 1, Poblacion, Villasis, Pangasinan','0','2015-01-30 19:12:04',NULL,'saintanthonian_saaa@yahoo.com',NULL,'(075)632-2266','St. Anthony Abbot Academy (HS)',NULL,NULL,NULL,'Region I'),(397,'Capaoay, San Jacinto, Pangasinan','0','2015-01-30 19:12:26',NULL,'sanjacintocatholicschoolsjcs@yahoo.com',NULL,'(075)653-0720','San Jacinto Catholic School',NULL,NULL,NULL,'Region I'),(398,'Consejo St., Poblacion, Urdaneta City,','Pangasinan','2015-01-30 19:12:49',NULL,'xxx@xxxxx.xxx',NULL,'(075)568-2675/0943-6251352','Our Lady of the Lilies Academy',NULL,NULL,NULL,'Region I'),(399,'W.A. Jones St., Nalsian, Calasiao, Pangasinan','0','2015-01-30 19:13:10',NULL,'mlls_registrar@yahoo.com',NULL,'(075)517-6604','Mother Lourdes Learning School',NULL,NULL,NULL,'Region I'),(400,'Camia St., Poblacion, Mapandan, Pangasinan','0','2015-01-30 19:13:33',NULL,'mapandancatholicschool_m@yahoo.com',NULL,'(075)632-1474','Mapandan Catholic School',NULL,NULL,NULL,'Region I'),(401,'October 16th St., Poblacion, Binalonan,','Pangasinan','2015-01-30 19:13:57',NULL,'comdsuholychild_binalonan58@yahoo.com',NULL,'(075)632-3049','Holy Child Academy',NULL,NULL,NULL,'Region I'),(402,'Rizal Ext., Dagupan City','0','2015-01-30 19:14:21',NULL,'dwad@pangasinan.com',NULL,'(075)522-8413','Divine Word Academy of Dagupan',NULL,NULL,NULL,'Region I'),(403,'Fenoy St., Poblacion, Pozorrubio, Pangasinan','0','2015-01-30 19:14:41',NULL,'spa_mayflor201029@yahoo.com',NULL,'(075)566-2310','St. Philomena\'s Academy',NULL,NULL,NULL,'Region I'),(404,'Poblacion, San Fabian, Pangasinan','0','2015-01-30 19:15:03',NULL,'assf_sanfabian@yahoo.com',NULL,'(075)511-4766/(075)511-3542','Archdiocesan School of San Fabian',NULL,NULL,NULL,'Region I'),(405,'Malinao St.,','Pasig City','2015-01-30 19:15:34',NULL,'xxx@xxxxx.xxx',NULL,'0','PASIG CATHOLIC COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(406,'0','Paz St., Paco, Manila','2015-01-30 19:15:59',NULL,'xxx@xxxxx.xxx',NULL,'0','PACO CATHOLIC SCHOOL (MAPSA)',NULL,NULL,NULL,'NCR'),(407,'338 B. Morcilla Street','Pateros, Metro Manila','2015-01-30 19:16:21',NULL,'xxx@xxxxx.xxx',NULL,'0','PATEROS CATHOLIC SCHOOL',NULL,NULL,NULL,'NCR'),(408,'A. Bonifacio Avenue','Cainta, Rizal','2015-01-30 19:16:43',NULL,'xxx@xxxxx.xxx',NULL,'0','CAINTA CATHOLIC COLLEGE',NULL,NULL,NULL,'Region IV-A'),(409,'0','Burgos St., Antipolo City','2015-01-30 19:17:25',NULL,'xxx@xxxxx.xxx',NULL,'0','OUR LADY OF PEACE SCHOOL',NULL,NULL,NULL,'Region IV-A'),(410,'P. Burgos Street','Makati City','2015-01-30 19:17:46',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL COLLEGE - MAKATI',NULL,NULL,NULL,'NCR'),(411,'B.F. Homes,','Capitol Hills, Quezon City','2015-01-30 19:18:04',NULL,'xxx@xxxxx.xxx',NULL,'0','SCH. OF THE HOLY SPIRIT (E/H)',NULL,NULL,NULL,'NCR'),(412,'Quirino Avenue,','La Huerta, Paranaque City','2015-01-30 19:18:37',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL COLLEGE - PARANAQUE',NULL,NULL,NULL,'NCR'),(413,'A. Mabini Street','Kalookan City','2015-01-30 19:18:55',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION COLL.-KALOOKAN-E/H',NULL,NULL,NULL,'NCR'),(414,'McArthur Hiway','Capas, Tarlac','2015-01-30 19:19:22',NULL,'xxx@xxxxx.xxx',NULL,'0','DOMINICAN SCHOOL OF TARLAC',NULL,NULL,NULL,'Region III'),(415,'(Our Lady of Fatima Academy)','Tanauan, Batangas','2015-01-30 19:19:43',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION SCHOOL-OLFA (TANAUAN)',NULL,NULL,NULL,'Region IV-A'),(416,'Poblacion','Bocaue, Bulacan','2015-01-30 19:20:08',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL COLL. - BOCAUE (E/HS)',NULL,NULL,NULL,'Region III'),(417,'Longos','Balagtas, Bulacan','2015-01-30 19:20:37',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION SCH. - BULACAN',NULL,NULL,NULL,'Region III'),(418,'(Frmly Quiapo Parochial School)','1053 Rd. Hidalgo,Quiapo, Manila','2015-01-30 19:20:59',NULL,'xxx@xxxxx.xxx',NULL,'0','THE NAZARENE CATHOLIC SCHOOL-QUIAPO',NULL,NULL,NULL,'NCR'),(419,'Catmon','Malolos, Bulacan','2015-01-30 19:21:26',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION UNIVERSITY PHILIPPINES',NULL,NULL,NULL,'Region III'),(420,'Sto. Tomas','Binan, Laguna','2015-01-30 19:21:46',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION SCH. - BINAN',NULL,NULL,NULL,'Region IV-A'),(421,'0','Bulacan, Bulacan','2015-01-30 19:22:09',NULL,'xxx@xxxxx.xxx',NULL,'0','ASSUMPTA ACADEMY (E/HS)',NULL,NULL,NULL,'Region III'),(422,'(Frmly: Holy Child Parochial School)','Pinaglabanan St.,San Juan, M.M.','2015-01-30 19:22:29',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. JOHN THE BAPTIST CATH. SCH-SAN JUAN',NULL,NULL,NULL,'NCR'),(423,'0','Sta. Maria, Bulacan','2015-01-30 19:22:51',NULL,'xxx@xxxxx.xxx',NULL,'0','ST. PAUL SCH.-STA. MARIA',NULL,NULL,NULL,'Region III'),(424,'0','Malinta, Valenzuela City','2015-01-30 19:23:25',NULL,'xxx@xxxxx.xxx',NULL,'0','LA CONSOLACION COLLEGE VALENZUELA (E/HS)',NULL,NULL,NULL,'NCR'),(425,'#3 Eisenhower St., Greenhills,','San Juan, Metro Manila','2015-01-30 19:23:45',NULL,'xxx@xxxxx.xxx',NULL,'7229720 to 27','O.B. MONTESSORI CENTER, INC.',NULL,NULL,NULL,'NCR'),(426,'#64 Xavier St.,West Greenhills','San Juan, Metro Manila','2015-01-30 19:24:05',NULL,'xxx@xxxxx.xxx',NULL,'7230481 to 96','XAVIER SCHOOL',NULL,NULL,NULL,'NCR'),(427,'Katipunan Road, Loyola Heights','Diliman, Quezon City','2015-01-30 19:24:23',NULL,'xxx@xxxxx.xxx',NULL,'4266001 loc 4053','ATENEO DE MANILA GRADE SCHOOL',NULL,NULL,NULL,'NCR'),(428,'116 D. Tuazon Ave.,','Quezon City','2015-01-30 19:24:44',NULL,'xxx@xxxxx.xxx',NULL,'7401802','ST. THERESA\'S COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(429,'Loyola Heights, Katipunan Rd.','Diliman, Quezon City','2015-01-30 19:25:03',NULL,'xxx@xxxxx.xxx',NULL,'5805400 to 29/4359231','MIRIAM COLLEGE (E/HS)',NULL,NULL,NULL,'NCR'),(430,'Kalentong St.,','Mandaluyong City','2015-01-30 19:25:28',NULL,'xxx@xxxxx.xxx',NULL,'5318081 to 83','DON BOSCO TECHNICAL COLL.-MANDALUYONG',NULL,NULL,NULL,'NCR'),(431,'Chino Roces Ave,','Makati City','2015-01-30 19:25:51',NULL,'xxx@xxxxx.xxx',NULL,'8920101 10 08','DON BOSCO TECHNICAL INST. (E/HS)',NULL,NULL,NULL,'NCR'),(432,'#10 Grant St.,','West Greenhills, San Juan, M. M.','2015-01-30 19:26:11',NULL,'xxx@xxxxx.xxx',NULL,'7237041 loc211/215','IMM. CONCEPTION ACADEMY (E/HS)',NULL,NULL,NULL,'NCR'),(433,'Jose Rizal Ave., Ext., San Agustin,','Malabon, Metro Manila','2015-01-30 19:26:32',NULL,'xxx@xxxxx.xxx',NULL,'2833697','ST. JAMES ACADEMY- E/HS',NULL,NULL,NULL,'NCR'),(434,'Katipunan Rd., Loyola Heights,','Diliman, Quezon City','2015-01-30 19:26:53',NULL,'xxx@xxxxx.xxx',NULL,'4266001 loc 4053','ATENEO DE MANILA UNIV. (HS)',NULL,NULL,NULL,'NCR'),(435,'Sumulong Highway','Antipolo City','2015-01-30 19:27:14',NULL,'xxx@xxxxx.xxx',NULL,'6972358','ASSUMPTION ANTIPOLO',NULL,NULL,NULL,'Region IV-A'),(436,'(Frmly: Poveda Learning Centre)','Edsa/Ortigas Ave.,Mandaluyong City','2015-01-30 19:27:35',NULL,'xxx@xxxxx.xxx',NULL,'6318756 to 58','ST. PEDRO POVEDA COLLEGE',NULL,NULL,NULL,'NCR'),(437,'San Lorenzo Village','Makati City','2015-01-30 19:28:02',NULL,'xxx@xxxxx.xxx',NULL,'8924043','ASSUMPTION COLLEGE, INC. (E/HS)',NULL,NULL,NULL,'NCR'),(438,'136 Elizalde St.,','BF Homes, Paranaque City','2015-01-30 19:28:22',NULL,'xxx@xxxxx.xxx',NULL,'0','SOUTHVILLE INTERNATIONAL SCHOOL',NULL,NULL,NULL,'NCR'),(439,'Rodriguez Street','Taytay, Rizal','2015-01-30 19:28:47',NULL,'xxx@xxxxx.xxx',NULL,'0','SIENA COLLEGE OF TAYTAY (E/HS)',NULL,NULL,NULL,'Region IV-A'),(440,'Retiro St., cor','Kanlaon Quezon City','2015-01-30 19:29:12',NULL,'xxx@xxxxx.xxx',NULL,'7315127/7315159/7315198','LOURDES SCHOOL OF Q.C. (E/PE/HS)',NULL,NULL,NULL,'NCR'),(441,'Hillsborough Subdivision','Alabang, Muntinlupa City','2015-01-30 19:29:38',NULL,'xxx@xxxxx.xxx',NULL,'8078080','SOUTHRIDGE SCHOOL',NULL,NULL,NULL,'NCR'),(442,'BO UGONG, PASIG CITY','0','2015-01-30 19:29:57',NULL,'xxx@xxxxx.xxx',NULL,'5715291','REEDLEY INTERNATIONAL SCHOOL',NULL,NULL,NULL,'NCR'),(443,'KATIPUNAN RD, QC','0','2015-01-30 19:30:22',NULL,'xxx@xxxxx.xxx',NULL,'9291021','MULTIPLE INTELLIGENCE INTERNATIONAL SCH.',NULL,NULL,NULL,'NCR'),(444,'Chino Roces Ave,','Makati City','2015-01-30 19:30:47',NULL,'xxx@xxxxx.xxx',NULL,'8405040','THE BEACON INTERNATIONAL SCHOOL',NULL,NULL,NULL,'NCR'),(445,'0','Taguig City','2015-01-30 19:31:08',NULL,'xxx@xxxxx.xxx',NULL,'0','AUSTRALIAN INTERNATIONAL SCHOOL',NULL,NULL,NULL,'NCR'),(446,'0','0','2015-01-30 19:31:33',NULL,'xxx@xxxxx.xxx',NULL,'0','BRITESPARKS INTERNATIONAL SCHOOL',NULL,NULL,NULL,'not specified'),(447,'0','0','2015-01-30 19:32:00',NULL,'xxx@xxxxx.xxx',NULL,'0','ROMARINDA INTERNATIONAL SCHOOL',NULL,NULL,NULL,'not specified'),(448,'0','0','2015-01-30 19:32:24',NULL,'xxx@xxxxx.xxx',NULL,'0','MULTIPLE INTELLIGENCE INTERNATIONAL SCHOOL',NULL,NULL,NULL,'not specified');
/*!40000 ALTER TABLE `war_customer_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_support_given`
--

DROP TABLE IF EXISTS `war_customer_support_given`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_support_given` (
  `war_customer_support_given_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `support_given_amount` double DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_customer_support_given_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_support_given`
--

LOCK TABLES `war_customer_support_given` WRITE;
/*!40000 ALTER TABLE `war_customer_support_given` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_support_given` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_tag`
--

DROP TABLE IF EXISTS `war_customer_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_tag` (
  `war_customer_tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `war_customer_tag_agent` bigint(20) NOT NULL,
  `created_dt` datetime NOT NULL,
  `war_customer_tag_customer` bigint(20) NOT NULL,
  `war_customer_tag_market_school_year` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_customer_tag_index` int(11) NOT NULL,
  `war_customer_tag_region_code` varchar(255) NOT NULL,
  `war_customer_tag_market_region` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_customer_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_tag`
--

LOCK TABLES `war_customer_tag` WRITE;
/*!40000 ALTER TABLE `war_customer_tag` DISABLE KEYS */;
INSERT INTO `war_customer_tag` VALUES (18,5,'2015-03-07 09:13:52',20,NULL,NULL,NULL,2,'NCR',7,NULL,'2015-03-10 01:58:17'),(19,5,'2015-03-07 09:14:13',13,NULL,NULL,NULL,3,'NCR',7,NULL,NULL),(20,5,'2015-03-07 09:14:29',14,NULL,NULL,NULL,1,'NCR',7,NULL,'2015-03-10 01:58:17');
/*!40000 ALTER TABLE `war_customer_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_customer_contacts`
--

DROP TABLE IF EXISTS `war_customer_war_customer_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_customer_contacts` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `contactDetails_contact_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`contactDetails_contact_id`),
  KEY `FK_s838fhcvdushyrhv47535fb5g` (`contactDetails_contact_id`),
  CONSTRAINT `FK_ius3wl3c2ouaamyco8vigo1g3` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`),
  CONSTRAINT `FK_s838fhcvdushyrhv47535fb5g` FOREIGN KEY (`contactDetails_contact_id`) REFERENCES `war_customer_contacts` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_customer_contacts`
--

LOCK TABLES `war_customer_war_customer_contacts` WRITE;
/*!40000 ALTER TABLE `war_customer_war_customer_contacts` DISABLE KEYS */;
INSERT INTO `war_customer_war_customer_contacts` VALUES (13,10);
/*!40000 ALTER TABLE `war_customer_war_customer_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_customer_level`
--

DROP TABLE IF EXISTS `war_customer_war_customer_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_customer_level` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `customerLevels_war_customer_level_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`customerLevels_war_customer_level_id`),
  KEY `FK_emxumyealcmvhj6qc56vl3yay` (`customerLevels_war_customer_level_id`),
  CONSTRAINT `FK_emxumyealcmvhj6qc56vl3yay` FOREIGN KEY (`customerLevels_war_customer_level_id`) REFERENCES `war_customer_level` (`war_customer_level_id`),
  CONSTRAINT `FK_ny4rgggc37c6s9uf5n1fi3c30` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_customer_level`
--

LOCK TABLES `war_customer_war_customer_level` WRITE;
/*!40000 ALTER TABLE `war_customer_war_customer_level` DISABLE KEYS */;
INSERT INTO `war_customer_war_customer_level` VALUES (13,1),(13,2),(13,3),(13,4),(13,5),(13,6),(13,7),(13,8),(13,9),(13,10),(13,11),(13,12),(13,13);
/*!40000 ALTER TABLE `war_customer_war_customer_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_customer_market_school_year`
--

DROP TABLE IF EXISTS `war_customer_war_customer_market_school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_customer_market_school_year` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `warCustomerMarketSchoolYears_war_customer_market_school_year_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`warCustomerMarketSchoolYears_war_customer_market_school_year_id`),
  UNIQUE KEY `UK_rrhub20eu27sy32365hb6qodp` (`warCustomerMarketSchoolYears_war_customer_market_school_year_id`),
  CONSTRAINT `FK_nsbs8p25o01x20y39fkndygjr` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`),
  CONSTRAINT `FK_rrhub20eu27sy32365hb6qodp` FOREIGN KEY (`warCustomerMarketSchoolYears_war_customer_market_school_year_id`) REFERENCES `war_customer_market_school_year` (`war_customer_market_school_year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_customer_market_school_year`
--

LOCK TABLES `war_customer_war_customer_market_school_year` WRITE;
/*!40000 ALTER TABLE `war_customer_war_customer_market_school_year` DISABLE KEYS */;
INSERT INTO `war_customer_war_customer_market_school_year` VALUES (13,2),(13,3),(14,4),(14,5),(20,11);
/*!40000 ALTER TABLE `war_customer_war_customer_market_school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_customer_publisher`
--

DROP TABLE IF EXISTS `war_customer_war_customer_publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_customer_publisher` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `publisher_war_customer_publisher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`publisher_war_customer_publisher_id`),
  KEY `FK_4jodu21t4gl21x3tctp4t3egm` (`publisher_war_customer_publisher_id`),
  CONSTRAINT `FK_4jodu21t4gl21x3tctp4t3egm` FOREIGN KEY (`publisher_war_customer_publisher_id`) REFERENCES `war_customer_publisher` (`war_customer_publisher_id`),
  CONSTRAINT `FK_sk75krkt7b0k785pp4uemw1l2` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_customer_publisher`
--

LOCK TABLES `war_customer_war_customer_publisher` WRITE;
/*!40000 ALTER TABLE `war_customer_war_customer_publisher` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_war_customer_publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_customer_support_given`
--

DROP TABLE IF EXISTS `war_customer_war_customer_support_given`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_customer_support_given` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `supportGivens_war_customer_support_given_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`supportGivens_war_customer_support_given_id`),
  KEY `FK_siyv6qjjhm1i631it2cb09oo2` (`supportGivens_war_customer_support_given_id`),
  CONSTRAINT `FK_qjvjmkiv4x88jks47un2yqoin` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`),
  CONSTRAINT `FK_siyv6qjjhm1i631it2cb09oo2` FOREIGN KEY (`supportGivens_war_customer_support_given_id`) REFERENCES `war_customer_support_given` (`war_customer_support_given_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_customer_support_given`
--

LOCK TABLES `war_customer_war_customer_support_given` WRITE;
/*!40000 ALTER TABLE `war_customer_war_customer_support_given` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_war_customer_support_given` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_customer_war_historical_sale`
--

DROP TABLE IF EXISTS `war_customer_war_historical_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_customer_war_historical_sale` (
  `war_customer_customer_id` bigint(20) NOT NULL,
  `historicalSales_war_historical_sale_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_customer_customer_id`,`historicalSales_war_historical_sale_id`),
  UNIQUE KEY `UK_od9drpc3vdyg0xbdpr5ff7lqh` (`historicalSales_war_historical_sale_id`),
  CONSTRAINT `FK_etj3jbgc89erse18sjfkgdsv5` FOREIGN KEY (`war_customer_customer_id`) REFERENCES `war_customer` (`customer_id`),
  CONSTRAINT `FK_od9drpc3vdyg0xbdpr5ff7lqh` FOREIGN KEY (`historicalSales_war_historical_sale_id`) REFERENCES `war_historical_sale` (`war_historical_sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_customer_war_historical_sale`
--

LOCK TABLES `war_customer_war_historical_sale` WRITE;
/*!40000 ALTER TABLE `war_customer_war_historical_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_customer_war_historical_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_historical_sale`
--

DROP TABLE IF EXISTS `war_historical_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_historical_sale` (
  `war_historical_sale_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `net_delivery` int(11) DEFAULT NULL,
  `quantity_return_to_stock` int(11) DEFAULT NULL,
  `quantity_to_ship` int(11) DEFAULT NULL,
  `slspnsn_no` varchar(25) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `customer` varchar(255) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`war_historical_sale_id`),
  KEY `FK_2b22vjllpsp43noh82wm825hh` (`customer`),
  KEY `FK_lytsu26w51ju5ugp89e0s29w7` (`item`),
  CONSTRAINT `FK_2b22vjllpsp43noh82wm825hh` FOREIGN KEY (`customer`) REFERENCES `war_customer` (`customer_code`),
  CONSTRAINT `FK_lytsu26w51ju5ugp89e0s29w7` FOREIGN KEY (`item`) REFERENCES `war_item` (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_historical_sale`
--

LOCK TABLES `war_historical_sale` WRITE;
/*!40000 ALTER TABLE `war_historical_sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_historical_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_item`
--

DROP TABLE IF EXISTS `war_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_item` (
  `war_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `item_code` varchar(255) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_item_id`),
  UNIQUE KEY `UK_oghqt29g10in860ibleg4jn3y` (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_item`
--

LOCK TABLES `war_item` WRITE;
/*!40000 ALTER TABLE `war_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_market_segment_control`
--

DROP TABLE IF EXISTS `war_market_segment_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_market_segment_control` (
  `war_market_segment_control_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `war_market_control` int(11) DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_market_control_operator` int(11) NOT NULL,
  `war_market_control_order` int(11) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_market_control_value` int(11) NOT NULL,
  `war_market_control_value2` int(11) DEFAULT NULL,
  PRIMARY KEY (`war_market_segment_control_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_market_segment_control`
--

LOCK TABLES `war_market_segment_control` WRITE;
/*!40000 ALTER TABLE `war_market_segment_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_market_segment_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_planner_attachment`
--

DROP TABLE IF EXISTS `war_planner_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_planner_attachment` (
  `war_planner_attachment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `war_planner_attachment_file_done` bit(1) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_planner_attachment_file_name` varchar(255) NOT NULL,
  `war_planner_attachment_file_type` varchar(255) DEFAULT NULL,
  `war_planner_attachment_school_year` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_planner_attachment_uploaded_file` bigint(20) NOT NULL,
  PRIMARY KEY (`war_planner_attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_planner_attachment`
--

LOCK TABLES `war_planner_attachment` WRITE;
/*!40000 ALTER TABLE `war_planner_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_planner_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_customer_market`
--

DROP TABLE IF EXISTS `war_report_customer_market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_customer_market` (
  `war_report_customer_market_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `market_potential` int(11) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `school_year` bigint(20) DEFAULT NULL,
  `market_potential_segment` varchar(255) NOT NULL,
  PRIMARY KEY (`war_report_customer_market_id`),
  KEY `FK_3kdduvllearpe7sd7jn83up2k` (`school_year`),
  CONSTRAINT `FK_3kdduvllearpe7sd7jn83up2k` FOREIGN KEY (`school_year`) REFERENCES `war_report_school_year` (`war_report_school_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_customer_market`
--

LOCK TABLES `war_report_customer_market` WRITE;
/*!40000 ALTER TABLE `war_report_customer_market` DISABLE KEYS */;
INSERT INTO `war_report_customer_market` VALUES (1,'2015-01-29 00:42:58',13,NULL,NULL,17775,NULL,NULL,NULL,12,'MMP1'),(2,'2015-02-02 04:58:22',14,NULL,NULL,7212,NULL,NULL,NULL,12,'MMP2'),(3,'2015-02-19 10:02:16',13,NULL,NULL,100,NULL,NULL,NULL,13,'LMP2');
/*!40000 ALTER TABLE `war_report_customer_market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_market_segment`
--

DROP TABLE IF EXISTS `war_report_market_segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_market_segment` (
  `war_report_market_segment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `war_report_market_segment` varchar(255) NOT NULL,
  `war_report_market_control_school_year` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`war_report_market_segment_id`),
  UNIQUE KEY `UK_sij4n4jxgw377i63l0568spnd` (`war_report_market_segment`,`war_report_market_control_school_year`),
  KEY `FK_ao3ydou3r1ijxvjpdnmbll5b0` (`war_report_market_control_school_year`),
  CONSTRAINT `FK_ao3ydou3r1ijxvjpdnmbll5b0` FOREIGN KEY (`war_report_market_control_school_year`) REFERENCES `war_report_school_year` (`war_report_school_year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_market_segment`
--

LOCK TABLES `war_report_market_segment` WRITE;
/*!40000 ALTER TABLE `war_report_market_segment` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_report_market_segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_market_segment_war_market_segment_control`
--

DROP TABLE IF EXISTS `war_report_market_segment_war_market_segment_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_market_segment_war_market_segment_control` (
  `war_report_market_segment_war_report_market_segment_id` bigint(20) NOT NULL,
  `marketSegmentControlSet_war_market_segment_control_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_report_market_segment_war_report_market_segment_id`,`marketSegmentControlSet_war_market_segment_control_id`),
  UNIQUE KEY `UK_o8w4ry9upj2xt57twbm7n3io8` (`marketSegmentControlSet_war_market_segment_control_id`),
  CONSTRAINT `FK_2u0gp50w6xd9k7cpipk3jyuam` FOREIGN KEY (`war_report_market_segment_war_report_market_segment_id`) REFERENCES `war_report_market_segment` (`war_report_market_segment_id`),
  CONSTRAINT `FK_o8w4ry9upj2xt57twbm7n3io8` FOREIGN KEY (`marketSegmentControlSet_war_market_segment_control_id`) REFERENCES `war_market_segment_control` (`war_market_segment_control_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_market_segment_war_market_segment_control`
--

LOCK TABLES `war_report_market_segment_war_market_segment_control` WRITE;
/*!40000 ALTER TABLE `war_report_market_segment_war_market_segment_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_report_market_segment_war_market_segment_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_school_year`
--

DROP TABLE IF EXISTS `war_report_school_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_school_year` (
  `war_report_school_year_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by_id` bigint(20) NOT NULL,
  `created_dt` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_report_school_year_period_month` varchar(255) DEFAULT NULL,
  `war_report_school_year_period_month_to` varchar(255) DEFAULT NULL,
  `war_report_school_year_period_year` int(11) NOT NULL,
  `war_report_school_year_period_year_to` int(11) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`war_report_school_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_school_year`
--

LOCK TABLES `war_report_school_year` WRITE;
/*!40000 ALTER TABLE `war_report_school_year` DISABLE KEYS */;
INSERT INTO `war_report_school_year` VALUES (12,2,'2015-01-29 00:42:58','SY 2014-2015',NULL,'SEPTEMBER','AUGUST',2014,2015,NULL,NULL),(13,2,'2015-02-02 17:50:50','SY 2013-2014',NULL,'SEPTEMBER','APRIL',2013,2014,NULL,NULL);
/*!40000 ALTER TABLE `war_report_school_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_school_year_war_report_customer_market`
--

DROP TABLE IF EXISTS `war_report_school_year_war_report_customer_market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_school_year_war_report_customer_market` (
  `war_report_school_year_war_report_school_year_id` bigint(20) NOT NULL,
  `warCustomerMarkets_war_report_customer_market_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_report_school_year_war_report_school_year_id`,`warCustomerMarkets_war_report_customer_market_id`),
  UNIQUE KEY `UK_3srig2yf8hp2att6dl95dw9j` (`warCustomerMarkets_war_report_customer_market_id`),
  CONSTRAINT `FK_3srig2yf8hp2att6dl95dw9j` FOREIGN KEY (`warCustomerMarkets_war_report_customer_market_id`) REFERENCES `war_report_customer_market` (`war_report_customer_market_id`),
  CONSTRAINT `FK_iar80lkd5nrtq57cqdmsd1ap9` FOREIGN KEY (`war_report_school_year_war_report_school_year_id`) REFERENCES `war_report_school_year` (`war_report_school_year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_school_year_war_report_customer_market`
--

LOCK TABLES `war_report_school_year_war_report_customer_market` WRITE;
/*!40000 ALTER TABLE `war_report_school_year_war_report_customer_market` DISABLE KEYS */;
INSERT INTO `war_report_school_year_war_report_customer_market` VALUES (12,1),(12,2),(13,3);
/*!40000 ALTER TABLE `war_report_school_year_war_report_customer_market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_school_year_war_report_market_segment`
--

DROP TABLE IF EXISTS `war_report_school_year_war_report_market_segment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_school_year_war_report_market_segment` (
  `war_report_school_year_war_report_school_year_id` bigint(20) NOT NULL,
  `warMarketSegments_war_report_market_segment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`war_report_school_year_war_report_school_year_id`,`warMarketSegments_war_report_market_segment_id`),
  UNIQUE KEY `UK_mq0h2gecx8sme51fwb48rkhjj` (`warMarketSegments_war_report_market_segment_id`),
  CONSTRAINT `FK_mq0h2gecx8sme51fwb48rkhjj` FOREIGN KEY (`warMarketSegments_war_report_market_segment_id`) REFERENCES `war_report_market_segment` (`war_report_market_segment_id`),
  CONSTRAINT `FK_qen47n6itdx1adi4uyp5xd5wy` FOREIGN KEY (`war_report_school_year_war_report_school_year_id`) REFERENCES `war_report_school_year` (`war_report_school_year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_school_year_war_report_market_segment`
--

LOCK TABLES `war_report_school_year_war_report_market_segment` WRITE;
/*!40000 ALTER TABLE `war_report_school_year_war_report_market_segment` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_report_school_year_war_report_market_segment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `war_report_weekly_agent`
--

DROP TABLE IF EXISTS `war_report_weekly_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `war_report_weekly_agent` (
  `war_report_weekly_agent_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_agent_id` bigint(20) NOT NULL,
  `war_report_book_list` int(11) DEFAULT NULL,
  `war_report_confirmation_of_events` int(11) DEFAULT NULL,
  `created_dt` datetime NOT NULL,
  `war_report_delivery_of_additional_order_trm_compliance` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `war_report_delivery_of_incentive_donation` int(11) DEFAULT NULL,
  `war_report_exam_copies_distribution` int(11) DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `war_report_giveaways_distribution` int(11) DEFAULT NULL,
  `war_report_implemented_ex_sem` int(11) DEFAULT NULL,
  `war_report_invitation_to_events` int(11) DEFAULT NULL,
  `report_materials_advisor` varchar(255) NOT NULL,
  `report_planned_call_productivity` double DEFAULT NULL,
  `report_planned_target` int(11) DEFAULT NULL,
  `war_report_purchase_order` int(11) DEFAULT NULL,
  `report_date` date NOT NULL,
  `report_month` int(11) NOT NULL,
  `report_planner_id` bigint(20) NOT NULL,
  `start_dt` datetime DEFAULT NULL,
  `report_total_activity` int(11) DEFAULT NULL,
  `report_total_call_productivity` double DEFAULT NULL,
  `war_report_updated_customer_info_sheet` int(11) DEFAULT NULL,
  `report_unplanned_call_productivity` double DEFAULT NULL,
  `report_unplanned_target` int(11) DEFAULT NULL,
  `updated_dt` datetime DEFAULT NULL,
  `report_week` int(11) NOT NULL,
  `report_year` int(11) NOT NULL,
  PRIMARY KEY (`war_report_weekly_agent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `war_report_weekly_agent`
--

LOCK TABLES `war_report_weekly_agent` WRITE;
/*!40000 ALTER TABLE `war_report_weekly_agent` DISABLE KEYS */;
/*!40000 ALTER TABLE `war_report_weekly_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waragentcustomersummary`
--

DROP TABLE IF EXISTS `waragentcustomersummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waragentcustomersummary` (
  `report_agent_id` bigint(20) NOT NULL,
  `report_customer_id` bigint(20) NOT NULL,
  `MONTH` varchar(255) NOT NULL,
  `report_school_year` bigint(20) NOT NULL,
  `WEEK` varchar(255) NOT NULL,
  `report_book_list` int(11) DEFAULT NULL,
  `report_confirmation_of_events` int(11) DEFAULT NULL,
  `report_delivery_of_additional_order_trm_compliance` int(11) DEFAULT NULL,
  `report_delivery_of_incentive_donation` int(11) DEFAULT NULL,
  `report_exam_copies_distribution` int(11) DEFAULT NULL,
  `report_follow_up_payment` int(11) DEFAULT NULL,
  `report_giveaways_distribution` int(11) DEFAULT NULL,
  `report_implemented_ex_sem` int(11) DEFAULT NULL,
  `report_invitation_to_events` int(11) DEFAULT NULL,
  `report_materials_advisor` varchar(255) NOT NULL,
  `report_planned_actual` int(11) DEFAULT NULL,
  `report_planned_call_productivity` double DEFAULT NULL,
  `report_planned_target` int(11) DEFAULT NULL,
  `report_purchase_order` int(11) DEFAULT NULL,
  `report_region` varchar(255) NOT NULL,
  `report_date` date NOT NULL,
  `report_month` varchar(255) NOT NULL,
  `report_planner_id` bigint(20) NOT NULL,
  `report_week` int(11) NOT NULL,
  `report_total_activity` int(11) DEFAULT NULL,
  `report_total_actual` int(11) DEFAULT NULL,
  `report_total_call_productivity` double DEFAULT NULL,
  `report_updated_customer_info_sheet` int(11) DEFAULT NULL,
  `report_unplanned_actual` int(11) DEFAULT NULL,
  `report_unplanned_call_productivity` double DEFAULT NULL,
  `report_unplanned_target` int(11) DEFAULT NULL,
  `report_year` int(11) NOT NULL,
  PRIMARY KEY (`report_agent_id`,`report_customer_id`,`MONTH`,`report_school_year`,`WEEK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waragentcustomersummary`
--

LOCK TABLES `waragentcustomersummary` WRITE;
/*!40000 ALTER TABLE `waragentcustomersummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `waragentcustomersummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warcustomermarketview`
--

DROP TABLE IF EXISTS `warcustomermarketview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warcustomermarketview` (
  `customer_id` bigint(20) NOT NULL,
  `customer_school_year` bigint(20) NOT NULL,
  `customer_frequency` int(11) DEFAULT NULL,
  `customer_market_potential` int(11) DEFAULT NULL,
  `customer_market_potential_segment` varchar(255) DEFAULT NULL,
  `customer_material_advisor` bigint(20) DEFAULT NULL,
  `customer_month` int(11) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_week` int(11) DEFAULT NULL,
  `customer_year` int(11) DEFAULT NULL,
  `customer_market_id` bigint(20) NOT NULL,
  `customer_index` int(11) DEFAULT NULL,
  `customer_region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`customer_school_year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warcustomermarketview`
--

LOCK TABLES `warcustomermarketview` WRITE;
/*!40000 ALTER TABLE `warcustomermarketview` DISABLE KEYS */;
/*!40000 ALTER TABLE `warcustomermarketview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warreportmonthlycustomerview`
--

DROP TABLE IF EXISTS `warreportmonthlycustomerview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warreportmonthlycustomerview` (
  `report_customer_market_id` bigint(20) NOT NULL,
  `report_agent` bigint(20) DEFAULT NULL,
  `report_book_list` int(11) DEFAULT NULL,
  `report_confirmation_of_events` int(11) DEFAULT NULL,
  `report_frequency` int(11) DEFAULT NULL,
  `report_customer_id` bigint(20) NOT NULL,
  `report_customer` varchar(255) NOT NULL,
  `report_delivery_of_additional_order_trm_compliance` int(11) DEFAULT NULL,
  `report_delivery_of_incentive_donation` int(11) DEFAULT NULL,
  `report_exam_copies_distribution` int(11) DEFAULT NULL,
  `report_follow_up_payment` int(11) DEFAULT NULL,
  `report_giveaways_distribution` int(11) DEFAULT NULL,
  `report_implemented_ex_sem` int(11) DEFAULT NULL,
  `report_invitation_to_events` int(11) DEFAULT NULL,
  `report_month` varchar(255) DEFAULT NULL,
  `report_planned` int(11) DEFAULT NULL,
  `report_purchase_order` int(11) DEFAULT NULL,
  `report_region` varchar(255) DEFAULT NULL,
  `report_school_year` bigint(20) DEFAULT NULL,
  `report_updated_customer_info_sheet` int(11) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  `report_tag_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_customer_market_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warreportmonthlycustomerview`
--

LOCK TABLES `warreportmonthlycustomerview` WRITE;
/*!40000 ALTER TABLE `warreportmonthlycustomerview` DISABLE KEYS */;
/*!40000 ALTER TABLE `warreportmonthlycustomerview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warreportweeklyagentview`
--

DROP TABLE IF EXISTS `warreportweeklyagentview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warreportweeklyagentview` (
  `report_agent_id` bigint(20) NOT NULL,
  `report_month` varchar(255) NOT NULL,
  `report_planner_id` bigint(20) NOT NULL,
  `report_week` int(11) NOT NULL,
  `report_book_list` int(11) DEFAULT NULL,
  `report_confirmation_of_events` int(11) DEFAULT NULL,
  `report_delivery_of_additional_order_trm_compliance` int(11) DEFAULT NULL,
  `report_delivery_of_incentive_donation` int(11) DEFAULT NULL,
  `report_exam_copies_distribution` int(11) DEFAULT NULL,
  `report_giveaways_distribution` int(11) DEFAULT NULL,
  `report_implemented_ex_sem` int(11) DEFAULT NULL,
  `report_invitation_to_events` int(11) DEFAULT NULL,
  `report_materials_advisor` varchar(255) NOT NULL,
  `report_planned_actual` int(11) DEFAULT NULL,
  `report_planned_call_productivity` double DEFAULT NULL,
  `report_planned_target` int(11) DEFAULT NULL,
  `report_purchase_order` int(11) DEFAULT NULL,
  `report_date` date NOT NULL,
  `report_total_activity` int(11) DEFAULT NULL,
  `report_total_call_productivity` double DEFAULT NULL,
  `report_updated_customer_info_sheet` int(11) DEFAULT NULL,
  `report_unplanned_actual` int(11) DEFAULT NULL,
  `report_unplanned_call_productivity` double DEFAULT NULL,
  `report_unplanned_target` int(11) DEFAULT NULL,
  `report_year` int(11) NOT NULL,
  `report_total_actual` int(11) DEFAULT NULL,
  `report_follow_up_payment` int(11) DEFAULT NULL,
  `report_region` varchar(255) NOT NULL,
  PRIMARY KEY (`report_agent_id`,`report_month`,`report_planner_id`,`report_week`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warreportweeklyagentview`
--

LOCK TABLES `warreportweeklyagentview` WRITE;
/*!40000 ALTER TABLE `warreportweeklyagentview` DISABLE KEYS */;
/*!40000 ALTER TABLE `warreportweeklyagentview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warreportweeklyagentviewcustomer`
--

DROP TABLE IF EXISTS `warreportweeklyagentviewcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warreportweeklyagentviewcustomer` (
  `report_agent` bigint(20) NOT NULL,
  `report_customer` varchar(255) NOT NULL,
  `report_date` date NOT NULL,
  `report_actual` int(11) DEFAULT NULL,
  `report_booklist` int(11) DEFAULT NULL,
  `report_confirmation_of_events` int(11) DEFAULT NULL,
  `report_delivery_of_additional_order_trm_compliance` int(11) DEFAULT NULL,
  `report_day` int(11) DEFAULT NULL,
  `report_delivery_of_incentive_donation` int(11) DEFAULT NULL,
  `report_exam_copies_distribution` int(11) DEFAULT NULL,
  `report_giveaways_distribution` int(11) DEFAULT NULL,
  `report_implemented_ex_sem` int(11) DEFAULT NULL,
  `report_invitation_to_events` int(11) DEFAULT NULL,
  `report_market_potential_segment` varchar(255) DEFAULT NULL,
  `report_month` varchar(255) DEFAULT NULL,
  `report_planned` bit(1) DEFAULT NULL,
  `report_purchase_order` int(11) DEFAULT NULL,
  `report_updated_customer_info_sheet` int(11) DEFAULT NULL,
  `report_week` int(11) DEFAULT NULL,
  `report_year` int(11) DEFAULT NULL,
  `report_follow_up_payment` int(11) DEFAULT NULL,
  `report_region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`report_agent`,`report_customer`,`report_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warreportweeklyagentviewcustomer`
--

LOCK TABLES `warreportweeklyagentviewcustomer` WRITE;
/*!40000 ALTER TABLE `warreportweeklyagentviewcustomer` DISABLE KEYS */;
/*!40000 ALTER TABLE `warreportweeklyagentviewcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `weekly_agent_report_activity`
--

DROP TABLE IF EXISTS `weekly_agent_report_activity`;
/*!50001 DROP VIEW IF EXISTS `weekly_agent_report_activity`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `weekly_agent_report_activity` (
  `report_school_year` tinyint NOT NULL,
  `report_planner_id` tinyint NOT NULL,
  `report_region` tinyint NOT NULL,
  `report_customer_id` tinyint NOT NULL,
  `report_market_potential_segment` tinyint NOT NULL,
  `report_market_potential` tinyint NOT NULL,
  `report_agent_id` tinyint NOT NULL,
  `report_materials_advisor` tinyint NOT NULL,
  `report_date` tinyint NOT NULL,
  `report_month` tinyint NOT NULL,
  `report_year` tinyint NOT NULL,
  `report_week` tinyint NOT NULL,
  `report_planned_target` tinyint NOT NULL,
  `report_unplanned_target` tinyint NOT NULL,
  `report_planned_actual` tinyint NOT NULL,
  `report_unplanned_actual` tinyint NOT NULL,
  `report_total_activity` tinyint NOT NULL,
  `report_total_actual` tinyint NOT NULL,
  `report_planned_call_productivity` tinyint NOT NULL,
  `report_unplanned_call_productivity` tinyint NOT NULL,
  `report_total_call_productivity` tinyint NOT NULL,
  `report_call_productivity` tinyint NOT NULL,
  `report_exam_copies_distribution` tinyint NOT NULL,
  `report_invitation_to_events` tinyint NOT NULL,
  `report_confirmation_of_events` tinyint NOT NULL,
  `report_giveaways_distribution` tinyint NOT NULL,
  `report_delivery_of_incentive_donation` tinyint NOT NULL,
  `report_purchase_order` tinyint NOT NULL,
  `report_delivery_of_additional_order_trm_compliance` tinyint NOT NULL,
  `report_book_list` tinyint NOT NULL,
  `report_updated_customer_info_sheet` tinyint NOT NULL,
  `report_implemented_ex_sem` tinyint NOT NULL,
  `report_follow_up_payment` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `weekly_agent_report_activity_customer_day`
--

DROP TABLE IF EXISTS `weekly_agent_report_activity_customer_day`;
/*!50001 DROP VIEW IF EXISTS `weekly_agent_report_activity_customer_day`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `weekly_agent_report_activity_customer_day` (
  `report_week` tinyint NOT NULL,
  `report_day` tinyint NOT NULL,
  `report_year` tinyint NOT NULL,
  `report_month` tinyint NOT NULL,
  `report_customer` tinyint NOT NULL,
  `report_region` tinyint NOT NULL,
  `report_customer_id` tinyint NOT NULL,
  `report_market_potential_segment` tinyint NOT NULL,
  `report_market_potential` tinyint NOT NULL,
  `report_school_year` tinyint NOT NULL,
  `report_agent` tinyint NOT NULL,
  `report_planned` tinyint NOT NULL,
  `report_region_name` tinyint NOT NULL,
  `report_actual` tinyint NOT NULL,
  `report_booklist` tinyint NOT NULL,
  `report_confirmation_of_events` tinyint NOT NULL,
  `report_delivery_of_additional_order_trm_compliance` tinyint NOT NULL,
  `report_delivery_of_incentive_donation` tinyint NOT NULL,
  `report_exam_copies_distribution` tinyint NOT NULL,
  `report_giveaways_distribution` tinyint NOT NULL,
  `report_implemented_ex_sem` tinyint NOT NULL,
  `report_invitation_to_events` tinyint NOT NULL,
  `report_purchase_order` tinyint NOT NULL,
  `report_reason_for_non_coverage` tinyint NOT NULL,
  `report_specific_activity` tinyint NOT NULL,
  `report_updated_customer_info_sheet` tinyint NOT NULL,
  `report_follow_up_payment` tinyint NOT NULL,
  `report_date` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'rexcoredb'
--
/*!50003 DROP PROCEDURE IF EXISTS `agent_customer_summary` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`flowAdmin`@`%` PROCEDURE `agent_customer_summary`(in customerId bigint(20),
in schoolYearId bigint(20))
begin
select m.MONTH, w.WEEK, war.* FROM (
SELECT 'January' AS
MONTH
UNION SELECT 'February' AS
MONTH
UNION SELECT 'March' AS
MONTH
UNION SELECT 'April' AS
MONTH
UNION SELECT 'May' AS
MONTH
UNION SELECT 'June' AS
MONTH
UNION SELECT 'July' AS
MONTH
UNION SELECT 'August' AS
MONTH
UNION SELECT 'September' AS
MONTH
UNION SELECT 'October' AS
MONTH
UNION SELECT 'November' AS
MONTH
UNION SELECT 'December' AS
MONTH
) AS m 
join (SELECT '1' AS WEEK union select '2' AS WEEK union select '3' as WEEK union select '4' as WEEK union select '5' as WEEK) w
left join weekly_agent_report_activity war on lcase(war.report_month) = lcase(m.MONTH) and war.report_week = w.WEEK
and war.report_school_year = schoolYearId and war.report_customer_id = customerId;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMarketDetails` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`flowAdmin`@`%` PROCEDURE `getMarketDetails`(in customerId bigint)
begin
select concat(wy.market_potential_segment,' - ', wy.market_potential) as market_details  from war_customer_market_school_year wy
inner join war_customer_war_customer_market_school_year wsy on
wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id
inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year
where wsy.war_customer_customer_id = customerId order by wrsy.war_report_school_year_period_year desc limit 1;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `school_year_customer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`flowAdmin`@`%` PROCEDURE `school_year_customer`(
in school_year bigint(20),
in agent_id bigint(20), 
in isMonth integer(1), 
in plannedMonth varchar(50),
in week integer(1),
in size integer(3),
in startAt integer(3),
in region varchar(20),
in tag varchar(3))
begin
if(tag = 'all')
 then 
if(isMonth = 1) 
then
select
wc.customer_id as customer_index,
wcs.school_name as customer_name,
wc.customer_id as customer_id, 
wc.customer_owner_agent_id as customer_material_advisor,
md.market_potential_segment as customer_market_potential_segment,
md.market_potential as customer_market_potential,
wa.region as customer_region, 
sum(v_arac.report_actual) as customer_frequency,
v_arac.report_month as customer_month,
v_arac.report_year as customer_year,
v_arac.report_week as customer_week,
v_arac.report_school_year  as customer_school_year

from war_customer wc
inner join (select wy.market_potential_segment, wy.market_potential, war_customer_customer_id from war_customer_market_school_year wy 
inner join war_customer_war_customer_market_school_year wsy on wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id 
inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year
group by war_customer_customer_id) md  on md.war_customer_customer_id = wc.customer_id
inner join war_customer_school wcs on wcs.war_customer_school_id = wc.war_customer_school
inner join war_agent wa on wa.war_agent_id =  wc.customer_owner_agent_id
left outer join weekly_agent_report_activity_customer_day v_arac on v_arac.report_region = wa.region and v_arac.report_school_year = school_year 
and v_arac.report_customer_id = wc.customer_id
and v_arac.report_agent = agent_id
and v_arac.report_month = plannedMonth
where wa.region = region and wc.customer_owner_agent_id = agent_id
group by v_arac.report_year, v_arac.report_month, customer_name
limit startAt, size;

elseif(isMonth = 0) 
then
select 
wc.customer_id as customer_index,
wcs.school_name as customer_name,
wc.customer_id as customer_id, 
wc.customer_owner_agent_id as customer_material_advisor,
md.market_potential_segment as customer_market_potential_segment,
md.market_potential as customer_market_potential,
wa.region as customer_region, 
sum(v_arac.report_actual) as customer_frequency,
v_arac.report_month as customer_month,
v_arac.report_year as customer_year,
v_arac.report_week as customer_week,
v_arac.report_school_year  as customer_school_year

from war_customer wc
inner join (select wy.market_potential_segment, wy.market_potential, war_customer_customer_id from war_customer_market_school_year wy 
inner join war_customer_war_customer_market_school_year wsy on wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id 
inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year
group by war_customer_customer_id) md  on md.war_customer_customer_id = wc.customer_id
inner join war_customer_school wcs on wcs.war_customer_school_id = wc.war_customer_school
inner join war_agent wa on wa.war_agent_id =  wc.customer_owner_agent_id
left outer join weekly_agent_report_activity_customer_day v_arac on v_arac.report_region = wa.region and v_arac.report_school_year = school_year 
and v_arac.report_customer_id = wc.customer_id
and v_arac.report_month = plannedMonth
and v_arac.report_agent = agent_id
and v_arac.report_week = week
where wa.region = region  and wc.customer_owner_agent_id = agent_id
group by v_arac.report_month,  customer_name limit startAt, size;
end if;
end if;

if(tag = '20' or tag ='50')
then
begin
if(isMonth = 1)
then 
begin
select
wct.war_customer_tag_index as customer_index,
wcs.school_name as customer_name,
wct.war_customer_tag_customer as customer_id, 
wc.customer_owner_agent_id as customer_material_advisor,
md.market_potential_segment as customer_market_potential_segment,
md.market_potential as customer_market_potential,
wa.region as customer_region, 
sum(v_arac.report_actual) as customer_frequency,
v_arac.report_month as customer_month,
v_arac.report_year as customer_year,
v_arac.report_week as customer_week,
v_arac.report_school_year  as customer_school_year

from war_customer_tag wct 
inner join war_customer wc on wc.customer_id = wct.war_customer_tag_customer
inner join (select wy.market_potential_segment, wy.market_potential, war_customer_customer_id from war_customer_market_school_year wy 
inner join war_customer_war_customer_market_school_year wsy on wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id 
inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year
group by war_customer_customer_id) md  on md.war_customer_customer_id = wc.customer_id
inner join war_customer_school wcs on wcs.war_customer_school_id = wc.war_customer_school
inner join war_agent wa on wa.war_agent_id =  wc.customer_owner_agent_id
left outer join weekly_agent_report_activity_customer_day v_arac on v_arac.report_region = wa.region and v_arac.report_school_year = school_year 
and v_arac.report_customer_id = wc.customer_id
and v_arac.report_agent = agent_id
and v_arac.report_month = plannedMonth
where wa.region = region and wc.customer_owner_agent_id = agent_id
group by v_arac.report_year, v_arac.report_month, customer_name
order by wct.war_customer_tag_index asc limit size;
end;
end if;
if(isMonth = 0) then
begin
select
wct.war_customer_tag_index as customer_index,
wcs.school_name as customer_name,
wct.war_customer_tag_customer as customer_id, 
wc.customer_owner_agent_id as customer_material_advisor,
md.market_potential_segment as customer_market_potential_segment,
md.market_potential as customer_market_potential,
wa.region as customer_region, 
sum(v_arac.report_actual) as customer_frequency,
v_arac.report_month as customer_month,
v_arac.report_year as customer_year,
v_arac.report_week as customer_week,
v_arac.report_school_year as customer_school_year

from war_customer_tag wct 
inner join war_customer wc on wc.customer_id = wct.war_customer_tag_customer
inner join (select wy.market_potential_segment, wy.market_potential, war_customer_customer_id from war_customer_market_school_year wy 
inner join war_customer_war_customer_market_school_year wsy on wsy.warCustomerMarketSchoolYears_war_customer_market_school_year_id = wy.war_customer_market_school_year_id 
inner join war_report_school_year wrsy on wrsy.war_report_school_year_id = wy.school_year
group by war_customer_customer_id) md  on md.war_customer_customer_id = wc.customer_id
inner join war_customer_school wcs on wcs.war_customer_school_id = wc.war_customer_school
inner join war_agent wa on wa.war_agent_id =  wc.customer_owner_agent_id
left outer join weekly_agent_report_activity_customer_day v_arac on v_arac.report_region = wa.region and  v_arac.report_school_year = school_year 
and v_arac.report_customer_id = wc.customer_id
and v_arac.report_agent = agent_id
and v_arac.report_month = plannedMonth
where wa.region = region  and wc.customer_owner_agent_id = agent_id
group by v_arac.report_month,  customer_name
order by wct.war_customer_tag_index asc limit size;

end;
end if;

end;
end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `customer_latest_market_view`
--

/*!50001 DROP TABLE IF EXISTS `customer_latest_market_view`*/;
/*!50001 DROP VIEW IF EXISTS `customer_latest_market_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`flowAdmin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_latest_market_view` AS select `wy`.`market_potential_segment` AS `market_potential_segment`,`wy`.`market_potential` AS `market_potential`,`wsy`.`war_customer_customer_id` AS `war_customer_customer_id` from ((`war_customer_market_school_year` `wy` join `war_customer_war_customer_market_school_year` `wsy` on((`wsy`.`warCustomerMarketSchoolYears_war_customer_market_school_year_id` = `wy`.`war_customer_market_school_year_id`))) join `war_report_school_year` `wrsy` on((`wrsy`.`war_report_school_year_id` = `wy`.`school_year`))) group by `wsy`.`war_customer_customer_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `monthly_customer_report_activtiy`
--

/*!50001 DROP TABLE IF EXISTS `monthly_customer_report_activtiy`*/;
/*!50001 DROP VIEW IF EXISTS `monthly_customer_report_activtiy`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`flowAdmin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `monthly_customer_report_activtiy` AS select `v_warac`.`report_customer` AS `report_customer`,`v_warac`.`report_year` AS `report_year`,`v_warac`.`report_month` AS `report_month`,`v_warac`.`report_customer_id` AS `report_customer_id`,`v_warac`.`report_agent` AS `report_agent`,`v_warac`.`report_school_year` AS `report_school_year`,`v_warac`.`report_region` AS `report_region`,`v_warac`.`report_market_potential_segment` AS `report_market_potential_segment`,`v_warac`.`report_market_potential` AS `report_market_potential`,`ct`.`war_customer_tag_index` AS `report_tag_index`,sum(`v_warac`.`report_actual`) AS `report_frequency`,sum(`v_warac`.`report_planned`) AS `report_planned`,sum(`v_warac`.`report_exam_copies_distribution`) AS `report_exam_copies_distribution`,sum(`v_warac`.`report_confirmation_of_events`) AS `report_confirmation_of_events`,sum(`v_warac`.`report_delivery_of_additional_order_trm_compliance`) AS `report_delivery_of_additional_order_trm_compliance`,sum(`v_warac`.`report_delivery_of_incentive_donation`) AS `report_delivery_of_incentive_donation`,sum(`v_warac`.`report_giveaways_distribution`) AS `report_giveaways_distribution`,sum(`v_warac`.`report_implemented_ex_sem`) AS `report_implemented_ex_sem`,sum(`v_warac`.`report_invitation_to_events`) AS `report_invitation_to_events`,sum(`v_warac`.`report_purchase_order`) AS `report_purchase_order`,sum(`v_warac`.`report_updated_customer_info_sheet`) AS `report_updated_customer_info_sheet`,sum(`v_warac`.`report_follow_up_payment`) AS `report_follow_up_payment`,sum(`v_warac`.`report_booklist`) AS `report_book_list` from (`weekly_agent_report_activity_customer_day` `v_warac` join `war_customer_tag` `ct` on(((`ct`.`war_customer_tag_customer` = `v_warac`.`report_customer_id`) and (`ct`.`war_customer_tag_region_code` = `v_warac`.`report_region`)))) group by `v_warac`.`report_school_year`,`v_warac`.`report_month`,`v_warac`.`report_customer`,`v_warac`.`report_agent` order by `v_warac`.`report_customer` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `weekly_agent_report_activity`
--

/*!50001 DROP TABLE IF EXISTS `weekly_agent_report_activity`*/;
/*!50001 DROP VIEW IF EXISTS `weekly_agent_report_activity`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`flowAdmin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `weekly_agent_report_activity` AS select `a`.`war_activity_school_year` AS `report_school_year`,`a`.`war_planner` AS `report_planner_id`,`a`.`war_activity_region_code` AS `report_region`,`wc`.`customer_id` AS `report_customer_id`,`wr`.`market_potential_segment` AS `report_market_potential_segment`,`wr`.`market_potential` AS `report_market_potential`,`wa`.`war_agent_id` AS `report_agent_id`,`fud`.`full_name` AS `report_materials_advisor`,`a`.`start_dt` AS `report_date`,`a`.`war_planner_month` AS `report_month`,year(`a`.`start_dt`) AS `report_year`,(floor(((dayofmonth(`a`.`start_dt`) - 1) / 7)) + 1) AS `report_week`,sum(if((`a`.`war_activity_planned` = 1),1,0)) AS `report_planned_target`,sum(if((`a`.`war_activity_planned` = 0),1,0)) AS `report_unplanned_target`,sum((if((`a`.`war_activity_planned` = 1),1,0) and if((`a`.`war_activity_actual` = 1),1,0))) AS `report_planned_actual`,sum((if((`a`.`war_activity_planned` = 0),1,0) and if((`a`.`war_activity_actual` = 1),1,0))) AS `report_unplanned_actual`,count(`a`.`war_activity_id`) AS `report_total_activity`,sum(if((`a`.`war_activity_actual` = 1),1,0)) AS `report_total_actual`,((100 / count(`a`.`war_activity_id`)) * sum((if((`a`.`war_activity_planned` = 1),1,0) and if((`a`.`war_activity_actual` = 1),1,0)))) AS `report_planned_call_productivity`,((100 / count(`a`.`war_activity_id`)) * sum((if((`a`.`war_activity_planned` = 0),1,0) and if((`a`.`war_activity_actual` = 1),1,0)))) AS `report_unplanned_call_productivity`,((100 / count(`a`.`war_activity_id`)) * sum(if((`a`.`war_activity_actual` = 1),1,0))) AS `report_total_call_productivity`,((100 * count(if((`a`.`war_activity_planned` = 1),1,0))) / sum(if((`a`.`war_activity_actual` = 1),1,0))) AS `report_call_productivity`,sum(if((`a`.`war_activity_exam_copies_distribution` = 1),1,0)) AS `report_exam_copies_distribution`,sum(if((`a`.`war_activity_invitation_to_events` = 1),1,0)) AS `report_invitation_to_events`,sum(if((`a`.`war_activity_confirmation_of_events` = 1),1,0)) AS `report_confirmation_of_events`,sum(if((`a`.`war_activity_giveaways_distribution` = 1),1,0)) AS `report_giveaways_distribution`,sum(if((`a`.`war_activity_delivery_of_incentive_donation` = 1),1,0)) AS `report_delivery_of_incentive_donation`,sum(if((`a`.`war_activity_purchase_order` = 1),1,0)) AS `report_purchase_order`,sum(if((`a`.`war_activity_delivery_of_additional_order_trm_compliance` = 1),1,0)) AS `report_delivery_of_additional_order_trm_compliance`,sum(if((`a`.`war_activity_book_list` = 1),1,0)) AS `report_book_list`,sum(if((`a`.`war_activity_updated_customer_info_sheet` = 1),1,0)) AS `report_updated_customer_info_sheet`,sum(if((`a`.`war_activity_implemented_ex_sem` = 1),1,0)) AS `report_implemented_ex_sem`,sum(if((`a`.`war_activity_follow_up_payment` = 1),1,0)) AS `report_follow_up_payment` from ((((((`war_activity` `a` join `customer_latest_market_view` `wr` on((`wr`.`war_customer_customer_id` = `a`.`war_activity_customer_market_id`))) join `war_customer` `wc` on((`wc`.`customer_id` = `wr`.`war_customer_customer_id`))) join `war_agent` `wa` on((`wa`.`war_agent_id` = `a`.`war_activity_agent_id`))) join `flow_user` `fu` on((`fu`.`flow_user_id` = `wa`.`user`))) join `flow_user_detail` `fud` on((`fud`.`flow_user_detail_id` = `fu`.`flow_user_detail_id`))) join `war_customer_school` `ws` on((`ws`.`war_customer_school_id` = `wc`.`war_customer_school`))) group by `report_week`,`report_materials_advisor`,`report_month`,`report_year` order by `a`.`start_dt` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `weekly_agent_report_activity_customer_day`
--

/*!50001 DROP TABLE IF EXISTS `weekly_agent_report_activity_customer_day`*/;
/*!50001 DROP VIEW IF EXISTS `weekly_agent_report_activity_customer_day`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`flowAdmin`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `weekly_agent_report_activity_customer_day` AS select `a`.`war_activity_week` AS `report_week`,ucase(dayofweek(`a`.`start_dt`)) AS `report_day`,year(`a`.`start_dt`) AS `report_year`,`a`.`war_planner_month` AS `report_month`,`ws`.`school_name` AS `report_customer`,`a`.`war_activity_region_code` AS `report_region`,`wc`.`customer_id` AS `report_customer_id`,`wr`.`market_potential_segment` AS `report_market_potential_segment`,`wr`.`market_potential` AS `report_market_potential`,`a`.`war_activity_school_year` AS `report_school_year`,`a`.`war_activity_agent_id` AS `report_agent`,`a`.`war_activity_planned` AS `report_planned`,`wcr`.`region_name` AS `report_region_name`,sum(if((`a`.`war_activity_actual` = 1),1,0)) AS `report_actual`,sum(if((`a`.`war_activity_book_list` = 1),1,0)) AS `report_booklist`,sum(if((`a`.`war_activity_confirmation_of_events` = 1),1,0)) AS `report_confirmation_of_events`,sum(if((`a`.`war_activity_delivery_of_additional_order_trm_compliance` = 1),1,0)) AS `report_delivery_of_additional_order_trm_compliance`,sum(if((`a`.`war_activity_delivery_of_incentive_donation` = 1),1,0)) AS `report_delivery_of_incentive_donation`,sum(if((`a`.`war_activity_exam_copies_distribution` = 1),1,0)) AS `report_exam_copies_distribution`,sum(if((`a`.`war_activity_giveaways_distribution` = 1),1,0)) AS `report_giveaways_distribution`,sum(if((`a`.`war_activity_implemented_ex_sem` = 1),1,0)) AS `report_implemented_ex_sem`,sum(if((`a`.`war_activity_invitation_to_events` = 1),1,0)) AS `report_invitation_to_events`,sum(if((`a`.`war_activity_purchase_order` = 1),1,0)) AS `report_purchase_order`,`a`.`war_activity_reason_for_non_coverage` AS `report_reason_for_non_coverage`,`a`.`war_activity_customer_specific_activity` AS `report_specific_activity`,sum(if((`a`.`war_activity_updated_customer_info_sheet` = 1),1,0)) AS `report_updated_customer_info_sheet`,sum(if((`a`.`war_activity_follow_up_payment` = 1),1,0)) AS `report_follow_up_payment`,`a`.`start_dt` AS `report_date` from ((((`war_activity` `a` join `customer_latest_market_view` `wr` on((`wr`.`war_customer_customer_id` = `a`.`war_activity_customer_market_id`))) join `war_customer` `wc` on((`wc`.`customer_id` = `wr`.`war_customer_customer_id`))) join `war_customer_school` `ws` on((`ws`.`war_customer_school_id` = `wc`.`war_customer_school`))) join `war_customer_region` `wcr` on((`wcr`.`region_code` = `a`.`war_activity_region_code`))) group by `report_month`,`report_week`,`report_agent`,`report_customer`,`report_day` order by `report_date` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-11 19:19:20
