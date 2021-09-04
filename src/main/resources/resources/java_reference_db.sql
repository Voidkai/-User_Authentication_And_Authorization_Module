CREATE DATABASE IF NOT EXISTS `java_reference` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `java_reference`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id` int(11) NOT NULL,
`username` varchar(50) NOT NULL,
`password` varchar(100) NOT NULL,
`create_time` timestamp NOT NULL DEFAULT current_timestamp(),
`expired` BOOLEAN NOT NULL DEFAULT FALSE,
`locked` BOOLEAN NOT NULL DEFAULT FALSE,
`email` varchar(50) ,
`phone` varchar(50) ,
`telephone` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
`id` int(11) NOT NULL,
`name` varchar(50) NOT NULL,
`base_role` int(11),
`description` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
`id` int(11) NOT NULL,
`name` varchar(50) NOT NULL,
`resource_id` int(11) not null,
`resource_type` varchar(20),
`operation` varchar(20),
`parent_id` int(11),
`description` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`(
    `id` int(11) NOT NULL,
    `name` Varchar(50) NOT NULL,
    `parent_id` int(11),
    `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
    `description` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
`id` int(11) NOT NULL,
`name` varchar(50) NOT NULL,
`description` varchar(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `relation_role_privilege`;
CREATE TABLE `relation_role_privilege` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `relation_user_role`;
CREATE TABLE `relation_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `relation_user_user_group`;
CREATE TABLE `relation_user_user_group` (
`id` int(11) NOT NULL,
`user_id` int(11) NOT NULL,
`group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `service_ticket`;
CREATE TABLE `service_ticket` (
  `id` int(11) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `creator` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `entity_access`;
CREATE TABLE IF NOT EXISTS `entity_access` (
  `id` int(11) NOT NULL,
  `entity_code` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `entity`;
CREATE TABLE IF NOT EXISTS `entity` (
  `id` int(11) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `resource`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `relation_role_privilege`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `relation_user_role`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `relation_user_user_group`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `service_ticket`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `user_group`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `entity_access`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `entity`
  ADD PRIMARY KEY (`id`)
