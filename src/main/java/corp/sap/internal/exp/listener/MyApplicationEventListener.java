package corp.sap.internal.exp.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MyApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ApplicationContext applicationContext=applicationReadyEvent.getApplicationContext();
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);

        //Prepare table
        String[] sql = new String[2];
        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='users'";
        Integer num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `users` (\n" +
                    "  `user_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `username` varchar(50) NOT NULL,\n" +
                    "  `password` varchar(100) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] ="INSERT INTO `users` (`user_id`, `username`, `password`) VALUES\n" +
                    "(1, 'admin', '" +passwordEncoder.encode("123456")+"'),\n" +
                    "(2, 'wkx', '"+passwordEncoder.encode("123456")+"')";
            jdbcTemplate.batchUpdate(sql);

        }

        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='privileges'";
        num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `privileges` (\n" +
                    "  `privilege_id` int(11) NOT NULL PRIMARY KEY,\n" +
                    "  `privilege_code` varchar(20) NOT NULL,\n" +
                    "  `description` varchar(50) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] ="INSERT INTO `privileges` (`privilege_id`, `privilege_code`, `description`) VALUES\n" +
                    "(101, 'create_user', 'register'),\n" +
                    "(102, 'delete_user', 'delete_user'),\n" +
                    "(201, 'create_ticket', ''),\n" +
                    "(202, 'delete_ticket', ''),\n" +
                    "(203, 'update_ticket', ''),\n" +
                    "(204, 'query_ticket', ''),\n" +
                    "(214, 'query_all_ticket', '')";
            jdbcTemplate.batchUpdate(sql);
        }

        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='privilege_role'";
        num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `privilege_role` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `role_id` int(11) NOT NULL,\n" +
                    "  `privilege_id` int(11) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] ="INSERT INTO `privilege_role` (`id`, `role_id`, `privilege_id`) VALUES\n" +
                    "(1, 1, 201),\n" +
                    "(2, 1, 202),\n" +
                    "(3, 1, 203),\n" +
                    "(4, 1, 204),\n" +
                    "(5, 1, 214),\n" +
                    "(6, 2, 201),\n" +
                    "(7, 2, 203),\n" +
                    "(8, 2, 204)";
            jdbcTemplate.batchUpdate(sql);
        }

        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='roles'";
        num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `roles` (\n" +
                    "  `role_id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `name` varchar(50) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] ="INSERT INTO `roles` (`role_id`, `name`) VALUES\n" +
                    "(1, 'admin'),\n" +
                    "(2, 'customer')";
            jdbcTemplate.batchUpdate(sql);
        }

        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='role_user'";
        num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `role_user` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `role_id` int(11) NOT NULL,\n" +
                    "  `user_id` int(11) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] ="INSERT INTO `role_user` (`id`, `role_id`, `user_id`) VALUES\n" +
                    "(1, 1, 1),\n" +
                    "(2, 2, 2)";
            jdbcTemplate.batchUpdate(sql);
        }

        sql[0] = "select COUNT(*) from information_schema.TABLES where TABLE_SCHEMA='java_reference' and TABLE_NAME ='service_ticket'";
        num = jdbcTemplate.queryForObject(sql[0],Integer.class);
        if(num == 0){
            sql[0] ="CREATE TABLE `service_ticket` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                    "  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),\n" +
                    "  `user_id` int(11) NOT NULL,\n" +
                    "  `content` varchar(255) DEFAULT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
            sql[1] = "ALTER TABLE `service_ticket`\n" +
                    "  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT";
            jdbcTemplate.batchUpdate(sql);
        }
    }
}
