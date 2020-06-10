CREATE DATABASE IF NOT EXISTS `java_reference` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `java_reference`;
DROP TABLE IF EXISTS `privileges`;
CREATE TABLE `privileges` (
  `privilege_id` int(11) NOT NULL,
  `privilege_code` varchar(20) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `privileges`;
INSERT INTO `privileges` (`privilege_id`, `privilege_code`, `description`) VALUES
(1, 'ROLE_ADMIN', ''),
(2, 'ROLE_USER', ''),
(101, 'create_user', ''),
(102, 'delete_user', ''),
(201, 'create_ticket', ''),
(202, 'delete_ticket', ''),
(203, 'update_ticket', ''),
(204, 'query_ticket', '');
DROP TABLE IF EXISTS `privilege_role`;
CREATE TABLE `privilege_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `privilege_role`;
INSERT INTO `privilege_role` (`id`, `role_id`, `privilege_id`) VALUES
(1, 1, 1),
(2, 1, 201),
(3, 1, 202),
(4, 1, 203),
(5, 1, 204),
(6, 2, 2),
(7, 2, 201),
(8, 2, 203),
(9, 2, 204);
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `roles`;
INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'admin'),
(2, 'customer'),
(3, 'operator');
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `role_user`;
INSERT INTO `role_user` (`id`, `role_id`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2);
DROP TABLE IF EXISTS `service_ticket`;
CREATE TABLE `service_ticket` (
  `id` int(11) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `service_ticket`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
TRUNCATE TABLE `users`;
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES
(1, 'admin', '$2a$10$08.QZS7/nNO5Z25QRM1w6euQcYyYCalxdDTwlIGvJPUuwUguYGgj2'),
(2, 'wkx', '$2a$10$z/lkaBOXSe20Xhws.Vs88e4mYjgI1POaF9Tv.v8WsJdyMncDfVDv2');
ALTER TABLE `privileges`
  ADD PRIMARY KEY (`privilege_id`);
ALTER TABLE `privilege_role`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);
ALTER TABLE `role_user`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `service_ticket`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);
ALTER TABLE `privilege_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `role_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
ALTER TABLE `service_ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;