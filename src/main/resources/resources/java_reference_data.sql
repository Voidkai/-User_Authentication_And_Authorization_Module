TRUNCATE TABLE `user`;
INSERT INTO `user` (`id`, `username`, `password`,`create_time`) VALUES
(1, 'admin', '$2a$10$08.QZS7/nNO5Z25QRM1w6euQcYyYCalxdDTwlIGvJPUuwUguYGgj2', current_timestamp()),
(2, 'processor', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2',current_timestamp()),
(3, 'wkx', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2',current_timestamp()),
(4, 'useradmin', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2',current_timestamp());
TRUNCATE TABLE `role`;
INSERT INTO `role` (`id`, `name`,`description`) VALUES
(1, 'service_ticket_manager',''),
(2, 'service_ticket_processor',''),
(3, 'customer',''),
(4, 'user_admin','');
TRUNCATE TABLE `privilege`;
INSERT INTO `privilege` (`id`,`name`,`resource_id`,`operation`,`description`) VALUES
(1, 'service_ticket_truncate',6,'truncate',''),
(2,  'service_ticket_create',6,'create',''),
(3,  'service_ticket_read',6,'read',''),
(4,  'service_ticket_update',6,'update',''),
(5,  'service_ticket_delete',6,'delete',''),
(6,  'privilege_create',3,'create',''),
(7,  'privilege_read',3,'read',''),
(8, 'privilege_update',3,'update',''),
(9, 'privilege_delete',3,'delete','');
TRUNCATE TABLE `user_group`;
TRUNCATE TABLE `resource`;
INSERT INTO `resource` (`id`, `name`,`description`) VALUES
(1, 'user',''),
(2, 'role',''),
(3, 'privilege',''),
(4, 'user_group',''),
(5, 'resource',''),
(6, 'service_ticket','');
TRUNCATE TABLE `relation_role_privilege`;
INSERT INTO `relation_role_privilege` (`id`, `role_id`, `privilege_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 3),
(7, 2, 4),
(8, 3, 2),
(9, 3, 3),
(10,3, 4),
(11,4, 2),
(12,4, 3),
(13,4, 4),
(14,4, 5);
TRUNCATE TABLE `relation_user_role`;
INSERT INTO `relation_user_role` (`id`, `role_id`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4);
TRUNCATE TABLE `service_ticket`;
TRUNCATE TABLE `entity_access`;
TRUNCATE TABLE `entity`;
INSERT INTO `entity` (`id`, `name`, `code`) VALUES
(1, 'service_ticket', 10001)
