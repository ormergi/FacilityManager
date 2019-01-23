CREATE DATABASE  IF NOT EXISTS `facility_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `facility_manager`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: facility_manager
-- ------------------------------------------------------
-- Server version	8.0.14

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
-- Table structure for table `apartments_payments`
--

DROP TABLE IF EXISTS `apartments_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `apartments_payments` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `apartment_id` int(11) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartments_payments`
--

LOCK TABLES `apartments_payments` WRITE;
/*!40000 ALTER TABLE `apartments_payments` DISABLE KEYS */;
INSERT INTO `apartments_payments` VALUES (1,5,1,2000),(2,5,2,500),(3,5,3,6000),(4,5,4,4100),(5,5,5,800),(6,5,6,2100),(7,5,7,8000),(8,4,1,500),(9,4,8,900),(10,8,11,2900);
/*!40000 ALTER TABLE `apartments_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `committees`
--

DROP TABLE IF EXISTS `committees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `committees` (
  `id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `seniority` int(11) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `committees`
--

LOCK TABLES `committees` WRITE;
/*!40000 ALTER TABLE `committees` DISABLE KEYS */;
INSERT INTO `committees` VALUES ('159875322','Tair','Oren',10,'Tair123'),('203777776','Or','Mergi',15,'Or123'),('307933093','Oded','Radi',10,'Oded123'),('308405273','Sapir','Radi',40,'Sapir123');
/*!40000 ALTER TABLE `committees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `providers`
--

DROP TABLE IF EXISTS `providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `providers` (
  `id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `availability` int(11) DEFAULT NULL,
  `quality` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providers`
--

LOCK TABLES `providers` WRITE;
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` VALUES ('123456789','Yotam','Cohen','Electrics',8,20,557488952,'0557488952','1234'),('147258369','Haim','Rahamim','Cleaning',8,7,500,'0506678985','Haim123'),('321654987','Irit','Golda','Plumbing',8,5,200,'0523443335','Irit123'),('326598741','Vlad','Protzski','Electrics',9,9,650,'0549856621','Vlad123'),('369258147','Aharon','Cohen','Cleaning',7,9,400,'0522221447','Aharon123'),('784512963','Eyal','Ben-Hamo','Electrics',7,10,700,'0546235948','Eyal123'),('789456123','Shalom','Moshe','Gardening',9,9,600,'0548956847','Shalom123'),('852741963','Sigal','Levi','Plumbing',10,8,400,'0501458756','Sigal123'),('987654321','Yaron','Haim','Gardening',7,8,400,'0574562359','Yaron123');
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenants`
--

DROP TABLE IF EXISTS `tenants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tenants` (
  `id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `apartment_number` int(11) DEFAULT NULL,
  `monthly_payment_amount` int(11) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenants`
--

LOCK TABLES `tenants` WRITE;
/*!40000 ALTER TABLE `tenants` DISABLE KEYS */;
INSERT INTO `tenants` VALUES ('123456789','Israel','Israeli',3,100,'Israel123'),('203777776','Or','Mergi',8,300,'Or123'),('307933093','Oded','Radi',4,250,'Oded123'),('308405273','Sapir','Radi',4,100,'Sapir123'),('987654321','Moshe','Ben-Tov',5,500,'Moshe123');
/*!40000 ALTER TABLE `tenants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenants_payments`
--

DROP TABLE IF EXISTS `tenants_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tenants_payments` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(45) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenants_payments`
--

LOCK TABLES `tenants_payments` WRITE;
/*!40000 ALTER TABLE `tenants_payments` DISABLE KEYS */;
INSERT INTO `tenants_payments` VALUES (1,'307933093',1,500),(2,'307933093',2,400),(3,'307933093',4,800),(4,'307933093',5,20),(5,'307933093',7,400),(6,'307933093',6,590),(7,'203777776',1,3600),(8,'203777776',2,400),(9,'203777776',3,200),(10,'203777776',8,700),(11,'203777776',10,1200),(12,'203777776',12,800),(13,'308405273',1,400),(14,'308405273',6,2000),(15,'308405273',8,1000),(16,'308405273',12,1500),(17,'307933093',10,60),(18,'307933093',11,2000),(19,'203777776',11,2900);
/*!40000 ALTER TABLE `tenants_payments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-22 18:29:51
