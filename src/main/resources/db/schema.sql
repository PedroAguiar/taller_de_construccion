DROP DATABASE IF EXISTS checklist;
CREATE DATABASE checklist;
USE checklist;
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `nombre`    varchar(75)  NOT NULL,
    `apellido`  varchar(150) NOT NULL,
    `user_name` varchar(75)  NOT NULL,
    `password`  char(32)     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_user_name` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `lista`;
CREATE TABLE `lista`
(
    `id`                   int          NOT NULL AUTO_INCREMENT,
    `titulo`               varchar(150) NOT NULL,
    `id_usuario`           int          NOT NULL,
    `fecha_de_creacion`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `fecha_de_terminacion` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `estado`               varchar(75)  NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_lista_usuario` (`id_usuario`),
    CONSTRAINT `FK_list_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `tarea`;
CREATE TABLE `tarea`
(
    `id`                   int       NOT NULL AUTO_INCREMENT,
    `descripcion`          TEXT      NOT NULL,
    `fecha_de_creacion`    timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `fecha_de_terminacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `estado`               varchar(75),
    `id_lista`             int       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_tarea_lista` (`id_lista`),
    CONSTRAINT `FK_tarea_lista` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
