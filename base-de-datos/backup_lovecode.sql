/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.8.6-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: LoveCode
-- ------------------------------------------------------
-- Server version	11.8.6-MariaDB-0+deb13u1 from Debian

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id_like` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_manda` int(11) NOT NULL,
  `usuario_recibe` int(11) NOT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_like`),
  UNIQUE KEY `uq_like` (`usuario_manda`,`usuario_recibe`),
  KEY `usuario_recibe` (`usuario_recibe`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`usuario_manda`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`usuario_recibe`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES
(6,13,8,'2026-05-14 11:51:34'),
(7,8,13,'2026-05-14 11:52:00'),
(8,14,10,'2026-05-14 12:16:36'),
(9,10,14,'2026-05-14 12:17:22'),
(10,10,13,'2026-05-14 12:20:22'),
(11,13,10,'2026-05-14 12:20:46'),
(12,13,12,'2026-05-14 12:30:17'),
(13,12,10,'2026-05-14 12:30:39'),
(14,12,13,'2026-05-14 12:33:47'),
(16,13,14,'2026-05-15 11:42:30'),
(17,11,10,'2026-05-18 08:28:36'),
(18,11,9,'2026-05-18 08:28:58'),
(19,9,11,'2026-05-18 08:30:00');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_uca1400_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`sergio`@`%`*/ /*!50003 TRIGGER trigger_match
AFTER INSERT ON likes
FOR EACH ROW 
BEGIN
IF EXISTS (
SELECT 1 FROM likes
WHERE usuario_manda = NEW.usuario_recibe
AND usuario_recibe = NEW.usuario_manda
)
THEN 
INSERT INTO matches (usuario_manda, usuario_recibe)
VALUES (NEW.usuario_manda, NEW.usuario_recibe);
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id_match` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_manda` int(11) NOT NULL,
  `usuario_recibe` int(11) NOT NULL,
  `fecha` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_match`),
  KEY `usuario_manda` (`usuario_manda`),
  KEY `usuario_recibe` (`usuario_recibe`),
  CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`usuario_manda`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`usuario_recibe`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES
(2,8,13,'2026-05-14 11:52:00'),
(3,10,14,'2026-05-14 12:17:22'),
(4,13,10,'2026-05-14 12:20:46'),
(5,12,13,'2026-05-14 12:33:47'),
(6,9,11,'2026-05-18 08:30:00');
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `tecnologia`
--

DROP TABLE IF EXISTS `tecnologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnologia` (
  `id_tecnologia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_tecnologia`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnologia`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `tecnologia` WRITE;
/*!40000 ALTER TABLE `tecnologia` DISABLE KEYS */;
INSERT INTO `tecnologia` VALUES
(1,'Java','Backend','Lenguaje de programación','2026-05-11 12:15:06'),
(2,'MySQL','Base de datos','Sistema gestor de bases de datos','2026-05-11 12:15:06'),
(3,'HTML','Frontend','Lenguaje de marcas','2026-05-11 12:15:06'),
(4,'CSS','Frontend','Hojas de estilo','2026-05-11 12:15:06'),
(5,'JavaScript','Frontend','Lenguaje de programación web','2026-05-11 12:15:06');
/*!40000 ALTER TABLE `tecnologia` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_registro` datetime DEFAULT current_timestamp(),
  `ciudad` varchar(100) DEFAULT NULL,
  `estado_cuenta` enum('activo','inactivo') DEFAULT 'activo',
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES
(8,'Hugo','hugo@gmail.com','1234','Apasionado a las bases de datos y a la programación con 5 años de experiencia.','2026-05-13 08:42:36','Valencia','activo','Garcia','fosjnfsih','Hugocan'),
(9,'Dani','dani@gmail.com','1234','Me encanta hacer paginas web y darle estilo','2026-05-13 08:49:16','Valencia','activo','Gomez','fsjfninvoj','dani_vlc'),
(10,'Miguel ','guti@gmail.com','1234','Estudiando para ser un futuro DBA y conocimientos en Java','2026-05-13 08:52:45','Valencia','activo','Gutiérrez','fskjnfsojn','elbarto'),
(11,'Pablo ','periko69@gmail.com','1234','10 años de experiencia como desarrollador full stack','2026-05-13 09:14:25','Requena','activo','Pedron ','jsngojsnvoin','periko:)'),
(12,'Oscar','reguera@gmail.com','1234','Dos años de experiencia como programador ','2026-05-13 09:15:59','Valencia','activo','Reguera','fsjonvsj','Roket_League'),
(13,'Ibai','ibaim@gmail.com','1234','Grandes conocimientos en programación en creación de base de datos y estructura de paginas web','2026-05-13 09:19:48','Valencia','activo','Montero','fsjgsivs','Mr_Furby'),
(14,'Maria ','mari@gmail.com','1234','Amante del código limpio y los proyectos que hacen la vida más fácil.','2026-05-14 12:08:16','Madrid','activo','Hernández ','ojsdfnsjn','maria_');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `usuario_tecnologia`
--

DROP TABLE IF EXISTS `usuario_tecnologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_tecnologia` (
  `id_usuario_tecnologia` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_tecnologia` int(11) DEFAULT NULL,
  `nivel` enum('basico','intermedio','avanzado') DEFAULT 'basico',
  `fecha_asociacion` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_usuario_tecnologia`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_tecnologia` (`id_tecnologia`),
  CONSTRAINT `usuario_tecnologia_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE,
  CONSTRAINT `usuario_tecnologia_ibfk_2` FOREIGN KEY (`id_tecnologia`) REFERENCES `tecnologia` (`id_tecnologia`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_tecnologia`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `usuario_tecnologia` WRITE;
/*!40000 ALTER TABLE `usuario_tecnologia` DISABLE KEYS */;
INSERT INTO `usuario_tecnologia` VALUES
(12,8,1,'basico','2026-05-13 08:42:36'),
(13,8,2,'basico','2026-05-13 08:42:36'),
(14,9,3,'basico','2026-05-13 08:49:16'),
(15,9,4,'basico','2026-05-13 08:49:16'),
(16,9,5,'basico','2026-05-13 08:49:16'),
(17,10,1,'basico','2026-05-13 08:52:45'),
(18,10,2,'basico','2026-05-13 08:52:45'),
(19,11,1,'basico','2026-05-13 09:14:25'),
(20,11,2,'basico','2026-05-13 09:14:25'),
(21,11,3,'basico','2026-05-13 09:14:25'),
(22,11,4,'basico','2026-05-13 09:14:25'),
(23,11,5,'basico','2026-05-13 09:14:25'),
(24,12,1,'basico','2026-05-13 09:15:59'),
(25,12,3,'basico','2026-05-13 09:15:59'),
(26,12,5,'basico','2026-05-13 09:15:59'),
(27,13,1,'basico','2026-05-13 09:19:48'),
(28,13,2,'basico','2026-05-13 09:19:48'),
(29,13,3,'basico','2026-05-13 09:19:48'),
(30,14,1,'basico','2026-05-14 12:08:16'),
(31,14,2,'basico','2026-05-14 12:08:16');
/*!40000 ALTER TABLE `usuario_tecnologia` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-05-21 10:17:05
