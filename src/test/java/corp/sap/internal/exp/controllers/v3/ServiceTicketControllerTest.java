package corp.sap.internal.exp.controllers.v3;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import corp.sap.internal.exp.DTO.ResponseWrapper;
import corp.sap.internal.exp.domain.ServiceTicket;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceTicketControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	private static Integer testTicketId;

	@Before
	public void setupMockMvc() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()) // apply spring security
				.build();
	}

	// order 1
	@Test
	public void test001addTicket() throws Exception {
		String content = "{\"content\": \"ContentTest\"}";
		MvcResult mvcResult = mockMvc.perform(post("/api/v3/ticket").content(content).contentType(MediaType.APPLICATION_JSON).with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString());
		JSONArray jsonArray = (JSONArray) jsonObject.get("data");
		JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
		this.testTicketId = (Integer)jsonObject1.get("id");
		System.out.println(this.testTicketId);

	}

	// order 2
	@Test
	public void test002getTicket()	throws Exception{
		mockMvc.perform(get("/api/v3/ticket/{id}", testTicketId).with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	// order 3
	@Test
	public void test003updateTicket() throws Exception {
		String content = "{\"content\": \"nicetry\"}";
		mockMvc.perform(patch("/api/v3/ticket/{id}", this.testTicketId).content(content).contentType(MediaType.APPLICATION_JSON).with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	// order 4
	@Test
	public void test004delTicket() throws Exception {
		mockMvc.perform(delete("/api/v3/ticket/{id}", this.testTicketId).with(httpBasic("admin","123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void test005getAllTicket() throws Exception{
		mockMvc.perform(get("/api/v3/ticket/getAllTicket").with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void test006getOwnTicket() throws Exception {
		mockMvc.perform(get("/api/v3/ticket/getOwnTicket").with(httpBasic("admin", "123456")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}