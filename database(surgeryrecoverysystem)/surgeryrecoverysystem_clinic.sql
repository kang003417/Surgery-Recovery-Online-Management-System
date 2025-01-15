-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: surgeryrecoverysystem
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic` (
  `clinicID` int NOT NULL AUTO_INCREMENT,
  `clinicName` varchar(255) DEFAULT NULL,
  `clinicAddress` text,
  `clinicPhone` varchar(20) DEFAULT NULL,
  `clinicPostcode` varchar(10) DEFAULT NULL,
  `patientPostcode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`clinicID`),
  KEY `patientpostcode` (`patientPostcode`),
  CONSTRAINT `patientpostcode` FOREIGN KEY (`patientPostcode`) REFERENCES `patient` (`postcode`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (1,'Sunway Medical Centre','Bandar Sunway, 46150 Petaling Jaya','555-1234',NULL,NULL),(2,'Subang Jaya Medical Centre','1 Jalan SS12/1A, 47500 Subang Jaya','555-5678',NULL,NULL),(3,'Mahkota Medical Centre','No 3, Jalan Merdeka, 75000 Melaka','555-8765',NULL,NULL),(4,'Prince Court Medical Centre','39 Jalan Kia Peng, Kuala Lumpur','555-3456',NULL,NULL),(5,'KPJ Specialist','570 Jalan Perda Utama, 11600 Penang','555-6543',NULL,NULL);
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-15 21:52:52
