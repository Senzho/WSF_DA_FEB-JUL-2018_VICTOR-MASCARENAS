-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: wsn
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `estudio`
--

DROP TABLE IF EXISTS `estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudio` (
  `idEstudio` int(11) NOT NULL AUTO_INCREMENT,
  `estudio` text,
  `idPrestador` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEstudio`),
  KEY `idPrestador` (`idPrestador`),
  CONSTRAINT `estudio_ibfk_1` FOREIGN KEY (`idPrestador`) REFERENCES `prestadorservicios` (`idPrestador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudio`
--

LOCK TABLES `estudio` WRITE;
/*!40000 ALTER TABLE `estudio` DISABLE KEYS */;
INSERT INTO `estudio` VALUES (1,'Lic. En Ingeniería de software, Universidad Veracruzana',1),(2,'Lic. en Diseño grafíco, UDAL',2);
/*!40000 ALTER TABLE `estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posicion` (
  `idPosicion` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `idTipo` int(11) DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  PRIMARY KEY (`idPosicion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion`
--

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestadorservicios`
--

DROP TABLE IF EXISTS `prestadorservicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestadorservicios` (
  `idPrestador` int(11) NOT NULL AUTO_INCREMENT,
  `nombrePrestador` varchar(100) DEFAULT NULL,
  `telefonoPrestador` varchar(10) DEFAULT NULL,
  `correoPrestador` varchar(100) DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `descripcionPrestador` text,
  `fechaNacimiento` date DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `direccionPrestador` text,
  `generoPrestador` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPrestador`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `prestadorservicios_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestadorservicios`
--

LOCK TABLES `prestadorservicios` WRITE;
/*!40000 ALTER TABLE `prestadorservicios` DISABLE KEYS */;
INSERT INTO `prestadorservicios` VALUES (1,'Prestador número 1','2281917765','prest1@gmail.com',0,'Solo soy un prestadore de servicios que hace trabajos','1988-05-11',4,'Una calle en algún lugar con algún número',1),(2,'Prestador número 2','2281917756','prest2@gmail.com',0,'Solo soy un prestador de servicios que hace trabajos','1997-11-04',5,'Una calle en algún lugar con algún número',0);
/*!40000 ALTER TABLE `prestadorservicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitante`
--

DROP TABLE IF EXISTS `solicitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitante` (
  `idSolicitante` int(11) NOT NULL AUTO_INCREMENT,
  `nombreSolicitante` varchar(100) DEFAULT NULL,
  `correoSolicitante` varchar(100) DEFAULT NULL,
  `telefonoSolicitante` varchar(10) DEFAULT NULL,
  `direccionSolicitante` text,
  `fechaNacimiento` date DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `generoSolicitante` int(11) DEFAULT NULL,
  PRIMARY KEY (`idSolicitante`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `solicitante_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitante`
--

LOCK TABLES `solicitante` WRITE;
/*!40000 ALTER TABLE `solicitante` DISABLE KEYS */;
INSERT INTO `solicitante` VALUES (1,'Víctor Javier García Mascareñas','vijagama@outlook.es','2281843459','Camino Mata Obscura S/N Col. El Lencero','1997-04-23',2,1,1),(2,'Rosa María Mascareñas Garciapiña','r67mariamas@hotmail.com','2281469482','Camino Mata Obscura S/N, Col. El Lencero','1967-07-06',3,1,0);
/*!40000 ALTER TABLE `solicitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitud` (
  `idSolicitud` int(11) NOT NULL AUTO_INCREMENT,
  `fechaRealizacion` date DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaInicial` date DEFAULT NULL,
  `estrellas` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `comentario` text,
  `respuesta` text,
  `idSolicitante` int(11) DEFAULT NULL,
  `idPrestador` int(11) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`idSolicitud`),
  KEY `idSolicitante` (`idSolicitante`),
  KEY `idPrestador` (`idPrestador`),
  CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`idSolicitante`) REFERENCES `solicitante` (`idSolicitante`),
  CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`idPrestador`) REFERENCES `prestadorservicios` (`idPrestador`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (1,'2018-05-21','2018-05-21','2018-05-21',4,0,'Buen trabajo','Estoy en eso',2,1,NULL),(3,'2018-05-21','2018-05-21','2018-05-21',5,0,'Buen trabajo','Estoy en eso',1,1,NULL),(4,'2018-05-21','2018-05-21','2018-05-21',2,0,'Buen trabajo','Estoy en eso',2,1,NULL),(5,'2018-05-21','2018-05-21','2018-05-21',-1,0,'Buen trabajo','Estoy en eso',2,2,NULL),(6,'2018-05-21','2018-05-21','2018-05-21',-1,0,'Buen trabajo','Estoy en eso',1,2,NULL),(7,'2018-05-21','2018-05-21','2018-05-21',-1,0,'Buen trabajo','Estoy en eso',2,2,NULL);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) DEFAULT NULL,
  `contrasena` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Victor','victor'),(3,'Rosa','rosa'),(4,'p1','p1'),(5,'p2','p2');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-22 15:01:18
