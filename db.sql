-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: comicsstore
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `usersa_fk_idx` (`user`),
  CONSTRAINT `usersa_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin1',2);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderr` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `issue` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cart_id_uindex` (`id`),
  KEY `cart_orderr_id_fk` (`orderr`),
  KEY `cart_issue_id_fk` (`issue`),
  CONSTRAINT `cart_issue_id_fk` FOREIGN KEY (`issue`) REFERENCES `issue` (`id`) ON DELETE SET NULL,
  CONSTRAINT `cart_orderr_id_fk` FOREIGN KEY (`orderr`) REFERENCES `orderr` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,1,9,NULL),(3,1,1,18),(4,1,4,8),(5,1,2,10),(28,27,3,NULL),(29,27,2,10),(31,30,3,NULL),(43,42,2,NULL),(44,42,1,14);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` int NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clients_ID_uindex` (`id`),
  UNIQUE KEY `clients_Email_uindex` (`email`),
  KEY `users_fk_idx` (`user`),
  CONSTRAINT `users_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Aaaa',22,NULL,13,1),('bbb',13,'bbbbb',26,25);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (45);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `series` int NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `linkPreview` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `link_preview` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `products_ID_uindex` (`id`),
  KEY `issues_series_ID_fk` (`series`),
  CONSTRAINT `issues_series_ID_fk` FOREIGN KEY (`series`) REFERENCES `series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'Infinity Gauntlet #2',3,2.79,NULL,NULL),(1,'Infinity Gauntlet #3',4,3.99,NULL,NULL),(1,'Infinity Gauntlet #4',5,2.99,NULL,NULL),(1,'Infinity Gauntlet #5',6,3.99,NULL,NULL),(1,'Infinity Gauntlet #6',7,3.99,NULL,NULL),(2,'Eternals #1',8,2.79,NULL,NULL),(2,'Eternals #2',9,2.79,NULL,NULL),(2,'Eternals #3',10,3.99,NULL,NULL),(2,'Eternals #4',11,2.99,NULL,NULL),(3,'Dark nights: Metal',12,4.99,'https://www.dccomics.com/reader/#/comics/431715','https://www.dccomics.com/reader/#/comics/431715'),(3,'Dark nights: Metal #2',13,4.99,NULL,NULL),(3,'Dark nights: THE BATMAN WHO LAUGHS',14,3.99,NULL,NULL),(4,'Episode 1: God of destruction',15,1.99,'https://mangaplus.shueisha.co.jp/viewer/1000317','https://mangaplus.shueisha.co.jp/viewer/1000317'),(4,'Episode 2: Goku defeated',16,1.99,'https://mangaplus.shueisha.co.jp/viewer/1000318','https://mangaplus.shueisha.co.jp/viewer/1000318'),(4,'Episode 3: The rage of Beerus',17,1.99,'https://mangaplus.shueisha.co.jp/viewer/1000319','https://mangaplus.shueisha.co.jp/viewer/1000319'),(5,'Future state: Justice League',18,5.99,'https://www.dccomics.com/reader/#/comics/467089','https://www.dccomics.com/reader/#/comics/467089'),(5,'Future state: Harley Quinn',19,3.99,NULL,NULL),(4,'Shitasd',21,6.6969,NULL,NULL),(35,'ms marvel#1',39,22,NULL,'no');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderr`
--

DROP TABLE IF EXISTS `orderr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderr` (
  `customer` int DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `price` int DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` enum('PENDING','ACCEPTED','IN_DELIVERY','DELIVERED','DECLINED') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `clients_fk` (`customer`),
  CONSTRAINT `clients_fk` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderr`
--

LOCK TABLES `orderr` WRITE;
/*!40000 ALTER TABLE `orderr` DISABLE KEYS */;
INSERT INTO `orderr` VALUES (13,NULL,1,37,'15-05-2022','PENDING'),(13,NULL,23,5,'15-05-2022','PENDING'),(26,NULL,27,12,'15-05-2022','DELIVERED'),(26,NULL,30,6,'15-05-2022','DECLINED'),(13,NULL,42,7,'17-05-2022','PENDING');
/*!40000 ALTER TABLE `orderr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `picture` longtext COLLATE utf8mb4_unicode_ci,
  `website` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Publishers_ID_uindex` (`id`),
  UNIQUE KEY `publishers_Name_uindex` (`name`),
  KEY `usersp_fk_idx` (`user`),
  CONSTRAINT `usersp_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Marvel Comics','images/publishers/1.png','https://www.marvel.com/comics',3),(2,'DC Comics','images/publishers/2.jpg','/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgo',4),(3,'Shueisha','images/publishers/3.png','https://mangaplus.shueisha.co.jp/updates',5);
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `series` (
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int NOT NULL,
  `publisher` int NOT NULL,
  `writer` int DEFAULT NULL,
  `ongoing` bit(1) NOT NULL,
  `cover` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `rating` enum('MATURE','TEEN','EVERYONE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `series_ID_uindex` (`id`),
  KEY `series_publishers_ID_fk` (`publisher`),
  KEY `series_writers_ID_fk` (`writer`),
  CONSTRAINT `series_publishers_ID_fk` FOREIGN KEY (`publisher`) REFERENCES `publisher` (`id`),
  CONSTRAINT `series_writers_ID_fk` FOREIGN KEY (`writer`) REFERENCES `writer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES ('Infinity Gauntlet',1,1,1,_binary '\0','images/series/1.jpg','TEEN'),('Eternals',2,1,1,_binary '','images/series/2.jpg','TEEN'),('Dark nights',3,2,5,_binary '','images/series/3.jpg','MATURE'),('Dragon Ball Super',4,3,6,_binary '','images/series/4.jpg','EVERYONE'),('Future state',5,2,7,_binary '','images/series/5.jpg','EVERYONE'),('ms marvel',35,1,34,_binary '',NULL,'TEEN'),('joker',37,2,34,_binary '',NULL,'EVERYONE'),('inhumans',38,1,1,_binary '',NULL,'MATURE'),('fantastic4',41,1,1,_binary '',NULL,NULL);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` enum('CUSTOMER','ADMIN','PUBLISHER') DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'CUSTOMER','$s0$e0801$0YoV7eI4bkVAZYtfLaSflg==$ekC0QuWs2djMdTPtV9qUsr+FNcUumRnFSlH74DHHrpU=','aaa'),(2,'ADMIN','$s0$e0801$3SA/CrM+hWCzvimp8Q8Ywg==$EgPG+hKj+7x3aKA91u8AEAXYh/MqwALBIAuFsssbjJA=','admin1'),(3,'PUBLISHER','$s0$e0801$3SA/CrM+hWCzvimp8Q8Ywg==$EgPG+hKj+7x3aKA91u8AEAXYh/MqwALBIAuFsssbjJA=','marvel'),(4,'PUBLISHER','$s0$e0801$3SA/CrM+hWCzvimp8Q8Ywg==$EgPG+hKj+7x3aKA91u8AEAXYh/MqwALBIAuFsssbjJA=','dc'),(5,'PUBLISHER','$s0$e0801$3SA/CrM+hWCzvimp8Q8Ywg==$EgPG+hKj+7x3aKA91u8AEAXYh/MqwALBIAuFsssbjJA=','manga'),(25,'CUSTOMER','$s0$e0801$PPyk0CIC7L0gTseU3n8lBw==$crBRdfzQC/7rEEPF+bepmGfa0WTmw+0Heeupnp/xNm8=','bbb');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `writer`
--

DROP TABLE IF EXISTS `writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `writer` (
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int NOT NULL,
  `picture` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Writers_ID_uindex` (`id`),
  UNIQUE KEY `writer_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writer`
--

LOCK TABLES `writer` WRITE;
/*!40000 ALTER TABLE `writer` DISABLE KEYS */;
INSERT INTO `writer` VALUES ('Stan Lee',1,'images/writers/1.jpg'),('Jim Starlin',2,'images/writers/2.jpg'),('George Perez',3,'images/writers/3.jpg'),('Neil Gaiman',4,'images/writers/4.jpg'),('Scott Snyder',5,'images/writers/5.jpg'),('Akira Toriyama',6,'images/writers/6.png'),('Joshua Williamson',7,'images/writers/7.jpg'),('idk idk',34,NULL);
/*!40000 ALTER TABLE `writer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17  2:12:56
