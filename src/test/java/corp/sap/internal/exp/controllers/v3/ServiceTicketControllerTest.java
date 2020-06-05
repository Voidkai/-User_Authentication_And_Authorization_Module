package corp.sap.internal.exp.controllers.v3;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class ServiceTicketControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
				.build();
	}

	@Test
    public void getAllTicket() throws Exception{
	    mockMvc.perform(get("/api/v3/serviceTicket/getAllTicket").with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

	@Test
	public void getTicket() throws Exception {
		mockMvc.perform(get("/api/v3/serviceTicket/getTicket").with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Ignore
    @Test
    public void addTicket() throws Exception {
        mockMvc.perform(get("/api/v3/serviceTicket/addTicket?content=hello").with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
	@Ignore
    @Test
    public void updateTicket() throws Exception {
        mockMvc.perform(get("/api/v3/serviceTicket/updateTicket?id=1&content=nice").with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
	@Ignore
    @Test
    public void delTicket() throws Exception {
	    mockMvc.perform(get("/api/v3/serviceTicket/delTicket?id=5").with(httpBasic("admin","123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}