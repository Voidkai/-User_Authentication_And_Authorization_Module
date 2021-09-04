CREATE DATABASE IF NOT EXISTS `java_reference` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `java_reference`;
DROP TABLE IF EXISTS `privileges`;
CREATE TABLE `privileges` (
    `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `privileges`;
INSERT INTO `privileges` (`id`,`code`,`description`) VALUES
(1, 100, 'service_ticket_truncate'),
(2, 101, 'service_ticket_create'),
(3, 102, 'service_ticket_read'),
(4, 103, 'service_ticket_update'),
(5, 104, 'service_ticket_delete');
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `permission_code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
(10,3, 103);
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `roles`;
INSERT INTO `roles` (`id`, `name`,`description`) VALUES
(1, 'service_ticket_manager',''),
(2, 'service_ticket_processor',''),
(3, 'customer',''),
(4, 'system_admin','');
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `role_user`;
INSERT INTO `role_user` (`id`, `role_id`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4);
DROP TABLE IF EXISTS `service_ticket`;
CREATE TABLE `service_ticket` (
  `id` int(11) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `creator` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `service_ticket`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `users`;
INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', '$2a$10$08.QZS7/nNO5Z25QRM1w6euQcYyYCalxdDTwlIGvJPUuwUguYGgj2'),
(2, 'processor', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2'),
(3, 'wkx', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2');
DROP TABLE IF EXISTS `entity_access`;
CREATE TABLE IF NOT EXISTS `entity_access` (
  `id` int(11) NOT NULL,
  `entity_code` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `entity_access`;
DROP TABLE IF EXISTS `entity`;
CREATE TABLE IF NOT EXISTS `entity` (
  `id` int(11) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `entity`;
INSERT INTO `entity` (`id`, `name`, `code`) VALUES
(1, 'service_ticket', 10001);
ALTER TABLE `privileges`
  ADD PRIMARY KEY (`code`);
ALTER TABLE `permission_role`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `role_user`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `service_ticket`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `entity_access`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `entity`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `permission_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `role_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `service_ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `entity_access`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;
ALTER TABLE `entity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2
