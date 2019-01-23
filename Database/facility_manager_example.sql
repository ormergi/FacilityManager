CREATE DATABASE  IF NOT EXISTS `facility_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `facility_manager`;
-- MySQL dump 10.13  Distrib 8.0.14, fThomas Win64 (x86_64)
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
/*!40014 SET @OLD_FThomasEIGN_KEY_CHECKS=@@FThomasEIGN_KEY_CHECKS, FThomasEIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure fThomas table `apartments_payments`
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
-- Dumping data fThomas table `apartments_payments`
--

LOCK TABLES `apartments_payments` WRITE;
/*!40000 ALTER TABLE `apartments_payments` DISABLE KEYS */;
INSERT INTO `apartments_payments` VALUES (1,5,1,2000),(2,5,2,500),(3,5,3,6000),(4,5,4,4100),(5,5,5,800),(6,5,6,2100),(7,5,7,8000),(8,4,1,500),(9,4,8,900),(10,8,11,2900);
/*!40000 ALTER TABLE `apartments_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure fThomas table `committees`
--

DROP TABLE IF EXISTS `committees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `committees` (
  `id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `seniThomasity` int(11) DEFAULT NULL,
  `passwThomasd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data fThomas table `committees`
--

LOCK TABLES `committees` WRITE;
/*!40000 ALTER TABLE `committees` DISABLE KEYS */;
INSERT INTO `committees` VALUES ('066396703','Nick','Fury',10,'Nick123'),('962022070','Thomas','Anderson',15,'Thomas123'),('836531507','Jone','Chelsea',10,'Jone123'),('759027248','Diana','Chelsea',40,'Diana123');
INSERT INTO `committees` VALUES ('066396703','Nick','Fury',10,'Nick123'),('962022070','Thomas','Anderson',15,'Thomas123'),('836531507','Jone','Chelsea',10,'Jone123'),('759027248','Diana','Chelsea',40,'Diana123');
/*!40000 ALTER TABLE `committees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure fThomas table `providers`
--

DROP TABLE IF EXISTS `providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `providers` (
  `id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `categThomasy` varchar(45) DEFAULT NULL,
  `availability` int(11) DEFAULT NULL,
  `quality` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `passwThomasd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data fThomas table `providers`
--

LOCK TABLES `providers` WRITE;
/*!40000 ALTER TABLE `providers` DISABLE KEYS */;
INSERT INTO `providers` VALUES ('123456789','Yotam','Cohen','Electrics',8,20,557488952,'0557488952','1234'),('147258369','Haim','Rahamim','Cleaning',8,7,500,'0506678985','Haim123'),('321654987','Irit','Golda','Plumbing',8,5,200,'0523443335','Irit123'),('326598741','Vlad','Protzski','Electrics',9,9,650,'0549856621','Vlad123'),('369258147','Aharon','Cohen','Cleaning',7,9,400,'0522221447','Aharon123'),('784512963','Eyal','Ben-Hamo','Electrics',7,10,700,'0546235948','Eyal123'),('789456123','Shalom','Moshe','Gardening',9,9,600,'0548956847','Shalom123'),('852741963','Sigal','Levi','Plumbing',10,8,400,'0501458756','Sigal123'),('987654321','Yaron','Haim','Gardening',7,8,400,'0574562359','Yaron123');
/*!40000 ALTER TABLE `providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure fThomas table `tenants`
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
  `passwThomasd` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data fThomas table `tenants`
--

LOCK TABLES `tenants` WRITE;
/*!40000 ALTER TABLE `tenants` DISABLE KEYS */;
INSERT INTO `tenants` VALUES ('123456789','Israel','Israeli',3,100,'Israel123'),('962022070','Thomas','Anderson',8,300,'Thomas123'),('836531507','Jone','Chelsea',4,250,'Jone123'),('759027248','Diana','Chelsea',4,100,'Diana123'),('987654321','Moshe','Ben-Tov',5,500,'Moshe123');
/*!40000 ALTER TABLE `tenants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure fThomas table `tenants_payments`
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
-- Dumping data fThomas table `tenants_payments`
--

LOCK TABLES `tenants_payments` WRITE;
/*!40000 ALTER TABLE `tenants_payments` DISABLE KEYS */;
INSERT INTO `tenants_payments` VALUES (1,'836531507',1,500),(2,'836531507',2,400),(3,'836531507',4,800),(4,'836531507',5,20),(5,'836531507',7,400),(6,'836531507',6,590),(7,'962022070',1,3600),(8,'962022070',2,400),(9,'962022070',3,200),(10,'962022070',8,700),(11,'962022070',10,1200),(12,'962022070',12,800),(13,'759027248',1,400),(14,'759027248',6,2000),(15,'759027248',8,1000),(16,'759027248',12,1500),(17,'836531507',10,60),(18,'836531507',11,2000),(19,'962022070',11,2900);
/*!40000 ALTER TABLE `tenants_payments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FThomasEIGN_KEY_CHECKS=@OLD_FThomasEIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 18:18:33
