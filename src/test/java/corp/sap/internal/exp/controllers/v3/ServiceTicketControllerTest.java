package corp.sap.internal.exp.controllers.v3;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
	    mockMvc.perform(get("/api/v3/ticket/getAllTicket").with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

	@Test
	public void delTicket() throws Exception {
		mockMvc.perform(delete("/api/v3/ticket/5").with(httpBasic("admin","123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getTicket() throws Exception {
		mockMvc.perform(get("/api/v3/ticket/getOwnTicket").with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

    @Test
    public void addTicket() throws Exception {
		String content = "{\"content\": \"nicetry\"}";
        mockMvc.perform(post("/api/v3/ticket").content(content).contentType(MediaType.APPLICATION_JSON).with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTicket() throws Exception {
		String content = "{\"content\": \"nicetry\"}";
        mockMvc.perform(patch("/api/v3/ticket/1").content(content).contentType(MediaType.APPLICATION_JSON).with(httpBasic("admin", "123456")))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}