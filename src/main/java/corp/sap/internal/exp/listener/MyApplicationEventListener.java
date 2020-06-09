package corp.sap.internal.exp.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MyApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("--------- execute MyApplicationReadyEventListener----------");
        ApplicationContext applicationContext=applicationReadyEvent.getApplicationContext();
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
        String[] sql = new String[4];
        sql[0] = "DROP TABLE IF EXISTS `users`;";
        sql[1] ="CREATE TABLE `users` (\n" +
                "  `user_id` int(11) NOT NULL,\n" +
                "  `username` varchar(50) NOT NULL,\n" +
                "  `password` varchar(100) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
        sql[2] ="TRUNCATE TABLE `users`";
        sql[3] ="INSERT INTO `users` (`user_id`, `username`, `password`) VALUES\n" +
                "(1, 'admin', \'"+passwordEncoder.encode("123456")+"\'),\n" +
                "(2, 'wkx', \'"+passwordEncoder.encode("123456")+"\')";
        jdbcTemplate.batchUpdate(sql);
        System.out.println("--------- execute MyApplicationReadyEventListener complete----------");
    }
}
