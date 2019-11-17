INSERT INTO `checklist`.`usuario`
(`id`,
`apellido`,
`nombre`,
`password`,
`user_name`)
VALUES
(1,
'Cipriani',
'Gustavo',
'gustavo123',
'gcipriani');

INSERT INTO `checklist`.`usuario`
(`id`,
`apellido`,
`nombre`,
`password`,
`user_name`)
VALUES
(2,
'Cipriani',
'Gabriela',
'gabriela123',
'gacipriani');


INSERT INTO `checklist`.`lista`
(`id`,
`estado`,
`fecha_de_creacion`,
`fecha_de_terminacion`,
`titulo`,
`id_usuario`)
VALUES
(1,
true,
NOW(),
null,
'Compra de supermercado',
1);

INSERT INTO `checklist`.`lista`
(`id`,
`estado`,
`fecha_de_creacion`,
`fecha_de_terminacion`,
`titulo`,
`id_usuario`)
VALUES
(2,
true,
NOW(),
null,
'Tareas del hogar',
1);

