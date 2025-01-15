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
-- Table structure for table `medicalhistory`
--

DROP TABLE IF EXISTS `medicalhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalhistory` (
  `historyID` int NOT NULL AUTO_INCREMENT,
  `patientID` int DEFAULT NULL,
  `surgeonID` int DEFAULT NULL,
  `appointmentID` int DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  `diagnosisDate` date DEFAULT NULL,
  `treatment` varchar(255) DEFAULT NULL,
  `treatmentDate` date DEFAULT NULL,
  PRIMARY KEY (`historyID`),
  KEY `patientID` (`patientID`),
  KEY `surgeonID` (`surgeonID`),
  KEY `appointmentID` (`appointmentID`),
  KEY `treatmentDate` (`treatmentDate`),
  CONSTRAINT `medicalhistory_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE CASCADE,
  CONSTRAINT `medicalhistory_ibfk_2` FOREIGN KEY (`surgeonID`) REFERENCES `surgeon` (`surgeonID`) ON DELETE CASCADE,
  CONSTRAINT `medicalhistory_ibfk_3` FOREIGN KEY (`appointmentID`) REFERENCES `appointment` (`appointmentID`) ON DELETE CASCADE,
  CONSTRAINT `treatmentDate` FOREIGN KEY (`treatmentDate`) REFERENCES `treatment` (`treatmentDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalhistory`
--

LOCK TABLES `medicalhistory` WRITE;
/*!40000 ALTER TABLE `medicalhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalhistory` ENABLE KEYS */;
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
