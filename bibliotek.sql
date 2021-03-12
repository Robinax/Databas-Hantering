-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotek
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anställda`
--

DROP TABLE IF EXISTS `anställda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anställda` (
  `AnställningsID` int NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `Hemnummer` varchar(255) DEFAULT NULL,
  `Mobilnummer` varchar(255) DEFAULT NULL,
  `Månadslön` varchar(255) DEFAULT NULL,
  `Semesterdagar_kvar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AnställningsID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anställda`
--

LOCK TABLES `anställda` WRITE;
/*!40000 ALTER TABLE `anställda` DISABLE KEYS */;
INSERT INTO `anställda` VALUES (1,'Asta Kask','Vägen 2,Nollberga','0703475','073-2458','123000','24'),(2,'Ebba Grön','Vägen 4,Nollberga','365868','6789','83000','21');
/*!40000 ALTER TABLE `anställda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `böcker`
--

DROP TABLE IF EXISTS `böcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `böcker` (
  `BokID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Författare` varchar(255) DEFAULT NULL,
  `Antal_Sidor` int DEFAULT NULL,
  `Klassifikation` varchar(255) DEFAULT NULL,
  `utlånad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BokID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `böcker`
--

LOCK TABLES `böcker` WRITE;
/*!40000 ALTER TABLE `böcker` DISABLE KEYS */;
INSERT INTO `böcker` VALUES (1,'Den ensamma Katten','Rudolf Ruskprick',123,'hce','Ja'),(2,'Vägen till Västerås','Kenny Surströmning',244,'Hce','Nej'),(3,'Testbok',NULL,NULL,NULL,NULL),(4,'Testbok',NULL,NULL,NULL,NULL),(5,'Testbok',NULL,NULL,NULL,NULL),(6,'Testbok',NULL,NULL,NULL,NULL),(7,'Testbok',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `böcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lånadeböcker`
--

DROP TABLE IF EXISTS `lånadeböcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lånadeböcker` (
  `LånID` int NOT NULL,
  `Titel` varchar(255) NOT NULL,
  PRIMARY KEY (`LånID`,`Titel`),
  CONSTRAINT `lånadeböcker_ibfk_1` FOREIGN KEY (`LånID`) REFERENCES `låntagare` (`LånID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lånadeböcker`
--

LOCK TABLES `lånadeböcker` WRITE;
/*!40000 ALTER TABLE `lånadeböcker` DISABLE KEYS */;
INSERT INTO `lånadeböcker` VALUES (1,'Den ensamma Katten'),(2,'');
/*!40000 ALTER TABLE `lånadeböcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `låntagare`
--

DROP TABLE IF EXISTS `låntagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `låntagare` (
  `LånID` int NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `Telefonnummer` varchar(255) DEFAULT NULL,
  `Lånekortsnummer` int DEFAULT NULL,
  PRIMARY KEY (`LånID`),
  KEY `lånIndex` (`LånID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `låntagare`
--

LOCK TABLES `låntagare` WRITE;
/*!40000 ALTER TABLE `låntagare` DISABLE KEYS */;
INSERT INTO `låntagare` VALUES (1,'Viggo_Filtner','Vägen 1,Nollberga','1111',1234),(2,'Pelle Pälsänger','Vägen 20,Nollberga','2222',4536);
/*!40000 ALTER TABLE `låntagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tidsskrifter`
--

DROP TABLE IF EXISTS `tidsskrifter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tidsskrifter` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) DEFAULT NULL,
  `Utgivningsdatum` date DEFAULT NULL,
  `Lagerplats` varchar(255) DEFAULT NULL,
  `Utlånad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tidsskrifter`
--

LOCK TABLES `tidsskrifter` WRITE;
/*!40000 ALTER TABLE `tidsskrifter` DISABLE KEYS */;
INSERT INTO `tidsskrifter` VALUES (1,'Illustrerad Ångest','2020-12-12','Hylla A','Nej'),(2,'Veckans Tråkigaste','2012-11-11','Hylla A','Nej');
/*!40000 ALTER TABLE `tidsskrifter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-12  9:57:59
