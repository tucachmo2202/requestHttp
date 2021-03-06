-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Intern_1
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `table_1`
--

DROP TABLE IF EXISTS `table_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_1` (
  `http_response_code` int(11) NOT NULL DEFAULT '0',
  `url` varchar(100) NOT NULL,
  `time_response` int(11) NOT NULL DEFAULT '0',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_1`
--

LOCK TABLES `table_1` WRITE;
/*!40000 ALTER TABLE `table_1` DISABLE KEYS */;
INSERT INTO `table_1` VALUES (200,'http://mpitutorial.com/',1624,'2019-03-31 10:01:13'),(200,'https://vietjack.com/java_jdbc/preparestatement_interface_trong_jdbc.jsp',263,'2019-03-31 10:01:14'),(200,'https://vnexpress.net/',250,'2019-03-31 10:01:14'),(200,'https://www.google.com/',207,'2019-03-31 10:01:15');
/*!40000 ALTER TABLE `table_1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-31 17:19:34
