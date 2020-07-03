package corp.sap.internal.exp.controllers.v3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.service.DataBaseOperationService;
import corp.sap.internal.exp.service.DataPreparationService;
import corp.sap.internal.exp.service.TicketWithDataAccessCheckService;
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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataAccessCheckTest {

    @Autowired
    TicketWithDataAccessCheckService ticketWithDataAccessCheckService;

    @Autowired
    DataBaseOperationService dataBaseOperationService;

    @Autowired
    DataPreparationService dataPreparationService;

    @Value("${test.data.scale}")
    Integer len;

    @Before
    public void setupMockMvc() {
        dataBaseOperationService.setupDataBase("resources/java_reference_test_data_access.sql");
        dataPreparationService.prepareServiceTicket(len);
        dataPreparationService.prepareDataAccess(len);
    }

    // order 1
    @Test
    public void testDataAccessCheckService() throws Exception {

        for (int i = 1; i < len; i++) {
            ServiceTicket serviceTicket = ticketWithDataAccessCheckService.addTicket(i, "content");
            ticketWithDataAccessCheckService.getTicketByTicketId(serviceTicket.getUserId(),serviceTicket.getId());
            ticketWithDataAccessCheckService.updateTicket(serviceTicket.getId(),serviceTicket.getUserId(), "New content");
            ticketWithDataAccessCheckService.delTicket(serviceTicket.getId(),serviceTicket.getUserId());
        }
    }
}
