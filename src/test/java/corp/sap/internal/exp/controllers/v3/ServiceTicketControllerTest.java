package corp.sap.internal.exp.controllers.v3;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;



import corp.sap.internal.exp.domain.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTicketControllerTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        mockHttpSession = new MockHttpSession();
//        User user = new User("admin","123456");
//        user.setId(1);
//        mockHttpSession.setAttribute("user",user);
    }


//    @Test
//    public void getAllTicket() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/serviceTicket/getAllTicket")
//                //.session(mockHttpSession))
//        ).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void getTicket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/serviceTicket/getTicket").with(httpBasic("admin","123456"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void addTicket() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/serviceTicket/addTicket?content=hello")
//                .session(mockHttpSession))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void updateTicket() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/serviceTicket/updateTicket?id=1&content=nice")
//                .session(mockHttpSession))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Ignore
//    @Test
//    public void delTicket() {
//    }
}