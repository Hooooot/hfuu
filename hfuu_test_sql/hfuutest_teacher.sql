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
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `tcId` int(11) NOT NULL AUTO_INCREMENT,
  `tcNum` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tcName` varchar(8) NOT NULL,
  `tcPw` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `depNum` char(2) DEFAULT NULL,
  `tcSex` char(2) DEFAULT NULL,
  `tcPhone` char(11) DEFAULT NULL,
  `tcAvatar` varchar(64) DEFAULT NULL,
  `tcEmail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tcId`),
  UNIQUE KEY `UK_fcogtq8aguwiw9ebeksp2m9nj` (`tcNum`),
  KEY `depNum` (`depNum`),
  CONSTRAINT `FK43s0dx2km7axeug7pesptgpl4` FOREIGN KEY (`depNum`) REFERENCES `department` (`depNum`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`depNum`) REFERENCES `department` (`depNum`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'1604012011','whh','1','04','男','12345678910',NULL,NULL),(7,'1706072099','李红','2','04','女','222',NULL,NULL),(8,'1706012024','张艳明','admin','02','男','111',NULL,NULL),(11,'1604012001','张弛','admin','04','男','123456',NULL,NULL),(12,'1604012002','高玲玲','123456','04','女','12345678910',NULL,NULL),(13,'1604012003','李四','123456','04','女','12345678970','avatars/3a7ae7d9cae8fb5d7a7032aaf7519f21','2446926687@qq.com');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-08 17:57:45
