TRUNCATE TABLE `permissions`;
INSERT INTO `permissions` (`id`,`code`,`description`) VALUES
(1, 100, 'service_ticket_truncate'),
(2, 101, 'service_ticket_create'),
(3, 102, 'service_ticket_read'),
(4, 103, 'service_ticket_update'),
(5, 104, 'service_ticket_delete'),
(6, 201, 'permission_create'),
(7, 202, 'permission_read'),
(8, 203, 'permission_update'),
(9, 204, 'permission_delete');
TRUNCATE TABLE `permission_role`;
INSERT INTO `permission_role` (`id`, `role_id`, `permission_code`) VALUES
(1, 1, 100),
(2, 1, 101),
(3, 1, 102),
(4, 1, 103),
(5, 1, 104),
(6, 2, 102),
(7, 2, 103),
(8, 3, 101),
(9, 3, 102),
(10,3, 103),
(11,4, 201),
(12,4, 202),
(13,4, 203),
(14,4, 204);
TRUNCATE TABLE `roles`;
INSERT INTO `roles` (`id`, `name`,`description`) VALUES
(1, 'service_ticket_manager',''),
(2, 'service_ticket_processor',''),
(3, 'customer',''),
(4, 'user_admin','');
TRUNCATE TABLE `role_user`;
INSERT INTO `role_user` (`id`, `role_id`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4);
TRUNCATE TABLE `service_ticket`;
TRUNCATE TABLE `users`;
INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', '$2a$10$08.QZS7/nNO5Z25QRM1w6euQcYyYCalxdDTwlIGvJPUuwUguYGgj2'),
(2, 'processor', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2'),
(3, 'wkx', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2');
TRUNCATE TABLE `entity_access`;
TRUNCATE TABLE `entity`;
INSERT INTO `entity` (`id`, `name`, `code`) VALUES
(1, 'service_ticket', 10001)
