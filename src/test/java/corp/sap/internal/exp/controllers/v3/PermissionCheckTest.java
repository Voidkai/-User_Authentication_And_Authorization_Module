package corp.sap.internal.exp.controllers.v3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.DataBaseOperationService;
import corp.sap.internal.exp.service.DataPreparationService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    @Autowired
    DataPreparationService dataPreparationService;
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    DataBaseOperationService dataBaseOperationService;

    @Autowired
    private ObjectMapper mapper;

    @Value("${test.data.scale}")
    int len;

    @Before
    public void setDataPreparation() {
        dataBaseOperationService.truncateTable();
        dataPreparationService.DataPrepare(len);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
                .build();
    }

    @Test
    public void testPermissionCheckService() throws Exception {

        String contentTest = UUID.randomUUID().toString();
        String contentUpdated = UUID.randomUUID().toString();

        for (int i = 2; i < len; i++) {
            String username = "user" + i;
            String password = "123456";
            System.out.print("user"+i+":");
            // CREATE
            MvcResult mvcResult = mockMvc
                    .perform(post("/api/v3/ticket/")
                            .content(mapper.writeValueAsString(new ServiceTicket(contentTest)))
                            .contentType(MediaType.APPLICATION_JSON)
                            .with(httpBasic(username, password)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("errorCode").value(i % 3 == 2 ? "3001" : "200")) //just processor has no create permission
                    .andReturn();

            //Default Operating ticketId equals user_id For testing processor
            Integer ticketId = i;

            if(i%3 != 2) {
                ticketId = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("data[0].id", Integer.class);
            }
            // READ
            mockMvc.perform(get("/api/v3/ticket/{id}", ticketId).with(httpBasic(username, password)))
                    .andExpect(status().isOk()).andExpect(jsonPath("errorCode").value("200"));

            // UPDATE
            mockMvc.perform(patch("/api/v3/ticket/{id}", ticketId).content(mapper.writeValueAsString(new ServiceTicket(contentUpdated))).contentType(MediaType.APPLICATION_JSON).with(httpBasic(username, password)))
                    .andExpect(jsonPath("errorCode").value("200"));

            // DELETE
            mockMvc.perform(delete("/api/v3/ticket/{id}", ticketId).with(httpBasic(username, password)))
                    .andExpect(status().isOk()).andExpect(jsonPath("errorCode").value(i % 3 == 1 ? "200" : "3001"));

        }
    }
}
