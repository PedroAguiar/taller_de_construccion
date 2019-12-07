/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table lista
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lista`;

CREATE TABLE `lista` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `estado` bit(1) NOT NULL,
                         `fecha_de_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `fecha_de_terminacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `titulo` varchar(150) NOT NULL,
                         `id_usuario` int(11) NOT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK_lista_usuario` (`id_usuario`),
                         CONSTRAINT `FK_lista_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `lista` WRITE;
/*!40000 ALTER TABLE `lista` DISABLE KEYS */;

INSERT INTO `lista` (`id`, `estado`, `fecha_de_creacion`, `fecha_de_terminacion`, `titulo`, `id_usuario`)
VALUES
(2,b'1','2019-12-06 20:38:46','2019-12-06 20:38:46','Tareas del hogar',1);

/*!40000 ALTER TABLE `lista` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tarea
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tarea`;

CREATE TABLE `tarea` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `descripcion` varchar(255) NOT NULL,
                         `estado` bit(1) NOT NULL,
                         `fecha_de_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `fecha_de_terminacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `id_lista` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK_tarea_lista` (`id_lista`),
                         CONSTRAINT `FK_tarea_lista` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;

INSERT INTO `tarea` (`id`, `descripcion`, `estado`, `fecha_de_creacion`, `fecha_de_terminacion`, `id_lista`)
VALUES
(4,'limpiar',b'0','2019-12-06 21:23:37','2019-12-06 21:23:37',2),
(5,'planchar',b'0','2019-12-06 21:23:43','2019-12-06 21:23:43',2),
(6,'lavar',b'0','2019-12-06 21:24:33','2019-12-06 21:24:33',2);

/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `apellido` varchar(150) NOT NULL,
                           `nombre` varchar(75) NOT NULL,
                           `password` char(72) NOT NULL,
                           `user_name` varchar(75) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`id`, `apellido`, `nombre`, `password`, `user_name`)
VALUES
(1,'Cipriani','Gustavo','gustavo123','gcipriani'),
(2,'Cipriani','Gabriela','gabriela123','gacipriani');

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
