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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceTicketControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    DataBaseOperationService dataBaseOperationService;
    @Autowired
    DataPreparationService dataPreparationService;
    @Value("${test.data.scale}")
    int len;

    @Before
    public void setupMockMvc() {
        dataBaseOperationService.truncateTable();
        dataPreparationService.DataPrepare(len);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
                .build();
    }

    // order 1
    @Test
    public void testCRUDServiceTicket() throws Exception {

        String contentTest = UUID.randomUUID().toString();
        String contentUpdated = UUID.randomUUID().toString();

        // CREATE
        MvcResult mvcResult = mockMvc
                .perform(post("/api/v3/ticket/")
                        .content(mapper.writeValueAsString(new ServiceTicket(contentTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin", "123456")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data[0].content").value(contentTest))
                .andReturn();

        Integer ticketId = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("data[0].id", Integer.class);

        // READ
        mockMvc.perform(get("/api/v3/ticket/{id}", ticketId).with(httpBasic("admin", "123456")))
                .andExpect(status().isOk()).andExpect(jsonPath("data[0].content").value(contentTest));

        // UPDATE
        mockMvc.perform(patch("/api/v3/ticket/{id}", ticketId).content(mapper.writeValueAsString(new ServiceTicket(contentUpdated))).contentType(MediaType.APPLICATION_JSON).with(httpBasic("admin", "123456")))
                .andExpect(jsonPath("data[0].content").value(contentUpdated));

        // READ again
        mockMvc.perform(get("/api/v3/ticket/{id}", ticketId).with(httpBasic("admin", "123456")))
                .andExpect(status().isOk()).andExpect(jsonPath("data[0].content").value(contentUpdated));


        // DELETE
        mockMvc.perform(delete("/api/v3/ticket/{id}", ticketId).with(httpBasic("admin", "123456")))
                .andExpect(status().isOk());

        // READ again
        // But in fact server should return 404
        mockMvc.perform(get("/api/v3/ticket/{id}", ticketId).with(httpBasic("admin", "123456")))
                .andExpect(status().isOk()).andExpect(jsonPath("data").isEmpty());

    }

}