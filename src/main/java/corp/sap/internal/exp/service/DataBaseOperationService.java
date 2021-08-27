package corp.sap.internal.exp.service;

import org.apache.commons.io.IOUtils;
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

    private ClassPathResource setPath(String path){
        return new ClassPathResource(path);
    }

    public void setupDataBase(String path) {
        ClassPathResource classPathResource = setPath(path);
        try {
           String[] strSplit = IOUtils.toString(classPathResource.getInputStream()).split(";");
           jdbcTemplate.batchUpdate(strSplit);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupData(String path) {
        ClassPathResource classPathResource = setPath(path);
        try {
            String[] strSplit = IOUtils.toString(classPathResource.getInputStream()).split(";");
            jdbcTemplate.batchUpdate(strSplit);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void truncateTable() {
        dataPreparationService.TableTruncate("users");
        dataPreparationService.TableTruncate("role_user");
        dataPreparationService.TableTruncate("service_ticket");
    }

}
