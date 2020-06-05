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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class dataPrepare {
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
                .build();
    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Ignore
    @Test
    public void UsersPrepare(){
        int len = 10000;
        try {
            jdbcTemplate.update("delete from users");
            String[] sql = new String[len];
            sql[0] = "insert into users(user_id,username,password) values("+1+",\"admin\",\""+ passwordEncoder.encode("123456")+"\")";
            sql[1] = "insert into users(user_id,username,password) values("+2+",\"wkx\",\""+ passwordEncoder.encode("123456")+"\")";
            for(int i=2;i<len;i++){
                sql[i] = "insert into users(user_id,username,password) values("+(i+1)+",\""+RandomStringUtils.randomAlphanumeric(10)+"\",\""+passwordEncoder.encode("123456")+"\")";
            }
            jdbcTemplate.batchUpdate(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void ServiceTicketPrepare(){
        int len = 10000;
        try {
            jdbcTemplate.update("delete from service_ticket");
            String[] sql = new String[len];
            sql[0] = "insert into service_ticket values ("+1+",CURRENT_TIMESTAMP," + 1 + ", \"" + "content"+1 + "\")";
            sql[1] = "insert into service_ticket values ("+2+",CURRENT_TIMESTAMP," + 2 + ", \"" + "content"+2 + "\")";
            for(int i=2;i<len;i++){
                sql[i] ="insert into service_ticket values ("+(i+1)+",CURRENT_TIMESTAMP," + (i+1)+ ", \"" + "content"+(i+1) + "\")";
            }
            jdbcTemplate.batchUpdate(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void UserRolePrepare(){
        int len = 10000;
        try {
            jdbcTemplate.update("delete from role_user");
            String[] sql = new String[len];
            sql[0] = "insert into role_user values ("+1+"," + 1 + ", " +1 + ")";
            sql[1] = "insert into role_user values ("+2+"," + 2 + ","+2 +")";
            for(int i=2;i<len;i++){
                sql[i] ="insert into role_user values ("+(i+1)+"," + 2 + ","+(i+1) +")";
            }
            jdbcTemplate.batchUpdate(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
