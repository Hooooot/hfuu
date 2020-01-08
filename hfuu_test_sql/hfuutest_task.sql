CREATE DATABASE  IF NOT EXISTS `hfuutest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hfuutest`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host:     Database: hfuutest
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `task` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(64) DEFAULT NULL,
  `taskDesc` varchar(64) DEFAULT NULL,
  `cozId` int(11) DEFAULT NULL,
  `tcNum` char(10) DEFAULT NULL,
  `pubTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deadline` timestamp NULL DEFAULT NULL COMMENT 'DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL 7 DAY)',
  `taskFiles` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`taskId`),
  UNIQUE KEY `taskId` (`taskId`),
  KEY `task_ibfk_2` (`tcNum`),
  KEY `task_ibfk_1` (`cozId`),
  CONSTRAINT `FKdmllta9uv4lpd08bvavuemw85` FOREIGN KEY (`cozId`) REFERENCES `course` (`cozId`),
  CONSTRAINT `FKhn328xl5u9xmpyv0d3l12q7io` FOREIGN KEY (`tcNum`) REFERENCES `teacher` (`tcNum`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`cozId`) REFERENCES `course` (`cozId`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`tcNum`) REFERENCES `teacher` (`tcNum`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (8,'实验一：堆栈实验','编写算法，利用栈判断所给字符串是否具有中心对称关系，如xyzyx和abcddcba形式。要求用尽可能少的时间完成判断。',5,'1706072099','2019-10-23 12:46:54','2019-12-30 03:14:07',''),(9,'实验二：链表实验','编写算法，利用链表知识完成实验',5,'1604012011','2019-10-01 04:17:33','2019-11-23 03:14:15',NULL),(12,'实验一：计算机的组成','了解计算机的基本组成',15,'1604012003','2019-10-18 04:01:59','2019-11-23 04:01:59',NULL),(13,'实验二：了解DRAM和SRAM','了解计算机的存储器',15,'1604012003','2019-10-20 04:00:00','2019-12-28 04:01:59',NULL),(14,'实验三：CPU的执行','了解CPU的执行过程',15,'1604012003','2019-10-10 04:00:00','2019-11-28 04:00:00',NULL),(32,'实验一','richtext/b3adf381361f46c9aba130ee2920ba2b.html',17,'1604012003','2019-11-18 07:53:35','2019-11-25 07:43:16',NULL),(33,'实验一','richtext/b3adf381361f46c9aba130ee2920ba2b.html',18,'1604012003','2019-11-18 07:53:35','2019-12-28 07:43:16',NULL),(34,'实验二','richtext/81099e024bd1422088c1af60b2030776.html',17,'1604012003','2019-11-18 07:54:54','2019-12-28 07:43:16','files/62e3bdfad1563a5e2b951422c7611900:Windows10 Pro KMS.bat|files/bbc9d55498594bb8a7c825e4d8644769:SanDiskSecureAccessV3_win.exe|'),(35,'实验二','richtext/81099e024bd1422088c1af60b2030776.html',18,'1604012003','2019-11-18 07:54:54','2019-12-28 07:43:16','files/62e3bdfad1563a5e2b951422c7611900:Windows10 Pro KMS.bat|files/bbc9d55498594bb8a7c825e4d8644769:SanDiskSecureAccessV3_win.exe|'),(93,'实验10','richtext\\f806858a21594c62af7c20cb97928377.html',19,'1604012003','2019-12-20 03:11:03','2019-12-27 03:10:32','files/5dbc43bd2711be9a2b698b06e8d59c93:cloud.png|files/07111f0d2e7c1dc640aca8ad59bd26c2:add.png|'),(94,'实验10','richtext\\f806858a21594c62af7c20cb97928377.html',20,'1604012003','2019-12-20 03:11:03','2019-12-27 03:10:32','files/5dbc43bd2711be9a2b698b06e8d59c93:cloud.png|files/07111f0d2e7c1dc640aca8ad59bd26c2:add.png|');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-08 17:57:30
