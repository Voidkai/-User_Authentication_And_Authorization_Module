package corp.sap.internal.exp.controllers.v3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DataPreparation {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Ignore
    @Test
    public void UsersPrepare(){
        int len = 10000;
        jdbcTemplate.update("delete from users");
        List<String> sql = new ArrayList<>();
        String password = passwordEncoder.encode("123456");
        sql.add("insert into users(user_id,username,password) values("+0+",\"admin\",\""+ password+"\")");
        for(int i=1;i<len;i++){
            sql.add("insert into users(user_id,username,password) values("+i+",\"user"+i+"\",\""+password+"\")");
        }
        jdbcTemplate.batchUpdate(sql.toArray(new String[0]));

    }

    @Ignore
    @Test
    public void ServiceTicketPrepare(){
        int len = 10000;
        try {
            jdbcTemplate.update("delete from service_ticket");
            List<String> sql = new ArrayList<>();
            for(int i=0;i<len;i++){
                sql.add("insert into service_ticket values ("+(i+1)+",CURRENT_TIMESTAMP," + (i+1)+ ", \"" + "content"+(i+1) + "\")");
            }
            jdbcTemplate.batchUpdate(sql.toArray(new String[0]));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void UserRolePrepare(){
        int len = 10000;
        jdbcTemplate.update("delete from role_user");
        List<String> sql = new ArrayList<>();
        for(int i=0;i<len;i++){
                sql.add("insert into role_user values ("+i+"," + i + ", " +(i%3+1) + ")");
        }
        jdbcTemplate.batchUpdate(sql.toArray(new String[0]));
    }
}
