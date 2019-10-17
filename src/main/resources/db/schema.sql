CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lista` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `fecha_de_creacion` datetime NOT NULL,
  `fecha_de_terminacion` datetime NOT NULL,
  `estado` boolean NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_list_usuario` (`id_usuario`),
  CONSTRAINT `FK_list_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_de_creacion` datetime NOT NULL,
  `fecha_de_terminacion` datetime NOT NULL,
  `estado` boolean,
  `id_lista` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tarea_lista` (`id_lista`),
  CONSTRAINT `FK_tarea_lista` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
