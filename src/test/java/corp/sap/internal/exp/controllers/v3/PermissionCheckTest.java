package corp.sap.internal.exp.controllers.v3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.DataBaseOperationService;
import corp.sap.internal.exp.service.DataPreparationService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PermissionCheckTest {

    public static final String CODE_NO_PERMISSION = "3001";

    public static final String CODE_NO_DATA_ACCESS = "3002";

    public static final String CODE_SUCCESS = "200";

    @Autowired
    DataPreparationService dataPreparationService;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataBaseOperationService dataBaseOperationService;

    @Autowired
    private ObjectMapper mapper;

    @Value("${test.data.scale}")
    Integer len;

    @Before
    public void beforeTest() {

        // clean table
        dataBaseOperationService.truncateTable();
        dataPreparationService.prepareUser(len);
        dataPreparationService.prepareServiceTicket(len);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
                .build();
    }

    @Test
    public void testPermissionCheckService() throws Exception {

        String contentTest = UUID.randomUUID().toString();
        String contentUpdated = UUID.randomUUID().toString();
        String password = "123456";

        for (Integer userId = 2; userId < len; userId++) {

            String username = "user" + userId;

            logger.info("test with" + username);

            // CREATE
            MvcResult mvcResult = mockMvc
                    .perform(post("/api/v3/ticket/")
                            .content(mapper.writeValueAsString(new ServiceTicket(contentTest)))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(httpBasic(username, password)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("errorCode").value(userId % 3 == 2 ? CODE_NO_PERMISSION : CODE_SUCCESS))//just processor has no create permission
                    .andReturn();

            // Default Operating ticketId equals user_id For testing processor
            Integer ticketId = userId;

            if (userId % 3 != 2) {
                ticketId = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("data.id", Integer.class);
            }

            // READ
            mockMvc.perform(get("/api/v3/ticket/{id}", ticketId).with(httpBasic(username, password)))
                    .andExpect(status().isOk()).andExpect(jsonPath("errorCode").value(CODE_SUCCESS));

            // UPDATE
            mockMvc.perform(patch("/api/v3/ticket/{id}", ticketId).content(mapper.writeValueAsString(new ServiceTicket(contentUpdated))).contentType(MediaType.APPLICATION_JSON).with(httpBasic(username, password)))
                    .andExpect(jsonPath("errorCode").value(CODE_SUCCESS));

            // DELETE
            mockMvc.perform(delete("/api/v3/ticket/{id}", ticketId).with(httpBasic(username, password)))
                    .andExpect(status().isOk()).andExpect(jsonPath("errorCode").value(userId % 3 == 1 ? CODE_SUCCESS : CODE_NO_PERMISSION));

        }

    }
}
