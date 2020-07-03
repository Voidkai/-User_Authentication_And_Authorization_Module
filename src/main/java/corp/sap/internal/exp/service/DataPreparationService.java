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

    /**
     * @param len
     */
    public void prepareUser(int len) {
        //prepare user table
        List<String> sqls = new ArrayList<>();
        String password = passwordEncoder.encode("123456");
        sqls.add("insert into users(user_id,username,password) values(" + 1 + ",\"admin\",\"" + password + "\")");
        for (int i = 2; i < len; i++) {
            sqls.add("insert into users(user_id,username,password) values(null,\"user" + i + "\",\"" + password + "\")");
        }
        jdbcTemplate.batchUpdate(sqls.toArray(new String[0]));

        //prepare role_user table
        sqls = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            sqls.add("insert into role_user values (null," + ((i - 1) % 3 + 1) + ", " + i + ")");
        }

        jdbcTemplate.batchUpdate(sqls.toArray(new String[0]));

    }

    /**
     * prepare service ticket with given number
     *
     * @param len
     */
    public void prepareServiceTicket(Integer len) {

        // prepare service_ticket
        List<String> sqls = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            sqls.add("insert into service_ticket values (" + i + ",CURRENT_TIMESTAMP," + i + ", \"" + "content" + i + "\")");
        }
        jdbcTemplate.batchUpdate(sqls.toArray(new String[0]));

    }

    public void prepareDataAccess(Integer len){

        List<String> sqls = new ArrayList<>();
        for(int i = 1;i<len;i++){
            if(i % 3 == 2){
                sqls.add("INSERT INTO data_access VALUES(" + i+", 10001,"+i+","+i+")");
            }
        }
        jdbcTemplate.batchUpdate(sqls.toArray(new String[0]));
    }


    public void TableTruncate(String tableName) {
        jdbcTemplate.update("truncate table " + tableName);
    }

}
