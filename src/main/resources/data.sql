INSERT INTO "role" (role_name)
VALUES ('ROLE_JOURNALIST'),
       ('ROLE_CLIENT'),
       ('ROLE_BOSS');
       
INSERT INTO "privilege" (privilege_name)
VALUES ('CAN_GET_ARTICLES'),    /*1*/
       ('CAN_POST_ARTICLES'),   /*2*/
       ('CAN_PUT_ARTICLES'),    /*3*/
       ('CAN_DELETE_ARTICLES'), /*4*/
       ('CAN_GET_THEMES'),      /*5*/
       ('CAN_POST_THEMES'),     /*6*/
       ('CAN_DELETE_THEMES'),   /*7*/
       ('CAN_GET_JOURNALISTS'), /*8*/
       ('CAN_POST_JOURNALISTS'),/*9*/
       ('CAN_DELETE_JOURNALISTS')/*10*/;
       
INSERT INTO role_privilege (role_id, privilege_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7),
       (2, 1), (2, 5),
       (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10);

INSERT INTO "theme" (theme_name)
VALUES ('Экономика'), ('Образование'), ('Спорт'), ('ЧС');

INSERT into "my_user" (login, password, role_id)
VALUES ('big_boss', '$2a$10$d1gzNoL.yaZdRtRxmid7yegbcrf8k.EtDRTPzk6tFDBJ8Rj/9ZXC6', 3);

INSERT into "my_user" (login, password, role_id)
VALUES ('главный_журналюга', '$2a$10$SuNKjnILwduyP4zKrJca2.HvEHcIZwOOy0C/9i4F/bdra21LSsMNa', 1);

INSERT into "my_user" (login, password, role_id)
VALUES ('reader1', '$2a$10$FUi5YqIn0DKMT6uU4l4yeOYuSKz.j/irpAneo..HkRmM1/xuIABd2', 2);