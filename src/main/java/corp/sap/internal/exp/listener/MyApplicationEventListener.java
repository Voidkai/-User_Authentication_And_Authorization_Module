package corp.sap.internal.exp.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MyApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ApplicationContext applicationContext=applicationReadyEvent.getApplicationContext();
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        ClassPathResource classPathResource = new ClassPathResource("resources/java_reference.sql");
        try {
            File file = classPathResource.getFile();
            FileInputStream in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            String str = new String(buffer,"UTF-8");
            String[] strSplit=str.split(";");
            jdbcTemplate.batchUpdate(strSplit);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
