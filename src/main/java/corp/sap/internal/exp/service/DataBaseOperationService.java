package corp.sap.internal.exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class DataBaseOperationService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataPreparationService dataPreparationService;

    public void setDataBase(String path){
        ClassPathResource classPathResource = new ClassPathResource(path);
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

    public void truncateTable(){
        dataPreparationService.TableTruncate("users");
        dataPreparationService.TableTruncate("role_user");
        dataPreparationService.TableTruncate("service_ticket");
    }
}
