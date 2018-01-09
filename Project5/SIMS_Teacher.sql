-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: SIMS_Teacher
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `Courseid` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `advanced placement` varchar(45) DEFAULT NULL,
  `academic credit` varchar(45) DEFAULT NULL,
  `elective` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `school hours` int(11) DEFAULT NULL,
  PRIMARY KEY (`Courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'Java OOP Programming','Java Basic Learning','2','elective','Professional',32),(2,'Matrix theory','Advanced Mathematics','1','compulsory','public',48);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Offering`
--

DROP TABLE IF EXISTS `Offering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Offering` (
  `Offeringid` int(11) NOT NULL,
  `Courseid` int(11) DEFAULT NULL,
  `Studentid` int(11) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Offeringid`),
  KEY `fk_Courseid_idx` (`Courseid`),
  KEY `fk_Studentid_idx` (`Studentid`),
  CONSTRAINT `fk_Courseid` FOREIGN KEY (`Courseid`) REFERENCES `Course` (`Courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Studentid2` FOREIGN KEY (`Studentid`) REFERENCES `Student` (`Studentid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offering`
--

LOCK TABLES `Offering` WRITE;
/*!40000 ALTER TABLE `Offering` DISABLE KEYS */;
INSERT INTO `Offering` VALUES (1,1,1,'2018','Spring'),(2,2,2,'2018','Fall');
/*!40000 ALTER TABLE `Offering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `Studentid` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`Studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (1,'Atom','male',23),(2,'Jane','female',22);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Take`
--

DROP TABLE IF EXISTS `Take`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Take` (
  `Takeid` int(11) NOT NULL,
  `Studentid` int(11) DEFAULT NULL,
  `Offeringid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`Takeid`),
  KEY `fk_Studentid_idx` (`Studentid`),
  KEY `fk_Offeringid_idx` (`Offeringid`),
  CONSTRAINT `fk_Offeringid` FOREIGN KEY (`Offeringid`) REFERENCES `Offering` (`Offeringid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Studentid` FOREIGN KEY (`Studentid`) REFERENCES `Student` (`Studentid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Take`
--

LOCK TABLES `Take` WRITE;
/*!40000 ALTER TABLE `Take` DISABLE KEYS */;
INSERT INTO `Take` VALUES (1,1,1,'2018-06-22 00:00:00',90),(2,2,2,'2018-12-31 00:00:00',88);
/*!40000 ALTER TABLE `Take` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teacher` (
  `Teacherid` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`Teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (1,'Bob','male',44),(2,'Selina','female',34);
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-09 21:46:29
