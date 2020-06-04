package corp.sap.internal.exp.controllers.v3;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    //@Ignore
    @Test
    public void dataPrepare() throws Exception{
        String[] username = new String[100];
        String[] password = new String[100];
        for(int i=0;i<100;i++){
            username[i] = RandomStringUtils.randomAlphanumeric(10)+i;
            password[i] = RandomStringUtils.randomAlphanumeric(10)+i;
        }

        for(int i=0;i<100;i++){

            String url1 = "/register?username="+ username[i]+"&password="+password[i];
            mockMvc.perform(get(url1).with(httpBasic("admin", "123456")))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
            String url2 = "/api/v3/serviceTicket/addTicket?content=hello"+i;
            mockMvc.perform(get(url2).with(httpBasic("admin", "123456")))
                    .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        }
    }
}
