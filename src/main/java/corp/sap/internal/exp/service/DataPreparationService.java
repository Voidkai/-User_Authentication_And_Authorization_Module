package corp.sap.internal.exp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataPreparationService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void DataPrepare(int len){
        //prepare user table
        List<String> sql = new ArrayList<>();
        String password = passwordEncoder.encode("123456");
        sql.add("insert into users(user_id,username,password) values("+1+",\"admin\",\""+ password+"\")");
        for(int i=2;i<len;i++){
            sql.add("insert into users(user_id,username,password) values(null,\"user"+i+"\",\""+password+"\")");
        }
        jdbcTemplate.batchUpdate(sql.toArray(new String[0]));

        //prepare role_user table
        sql = new ArrayList<>();
        for(int i=1;i<len;i++){
            sql.add("insert into role_user values (null," + ((i-1)%3+1) + ", " +i + ")");
        }
        jdbcTemplate.batchUpdate(sql.toArray(new String[0]));

        //prepare service_ticket
        sql = new ArrayList<>();
        for(int i=1;i<len;i++){
            sql.add("insert into service_ticket values ("+i+",CURRENT_TIMESTAMP," + i+ ", \"" + "content"+i + "\")");
        }
        jdbcTemplate.batchUpdate(sql.toArray(new String[0]));

    }

    public void TableTruncate(String tableName){
        jdbcTemplate.update("truncate table "+ tableName);
    }

}
