package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.ResponseWrapper;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.ServiceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v3/serviceTicket")
public class ServiceTicketController {
    @Autowired
    ServiceTicketService serviceTicketService;

    @GetMapping("/getTicket")
    public void getTicket(HttpServletResponse httpServletResponse) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        int user_id = user.getId();
        //返回json数据
        List<ServiceTicket> rt = serviceTicketService.getTicketByUserID(user_id);
        ResponseWrapper result = ResponseWrapper.success(rt);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result.toString());
    }

    @GetMapping("/addTicket")
    public void addTicket(HttpServletResponse httpServletResponse, @RequestParam(value = "content") String content) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        int user_id = user.getId();

        serviceTicketService.addTicket(user_id, content);
        ResponseWrapper result = ResponseWrapper.success();
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result.toString());
    }

    @GetMapping("/updateTicket")
    public void updateTicket(HttpServletResponse httpServletResponse, @RequestParam(value = "id") int id, @RequestParam(value = "content") String content) throws IOException {

        serviceTicketService.updateTicket(id, content);

        ResponseWrapper result = ResponseWrapper.success();
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result.toString());
    }

    @GetMapping("/delTicket")
    public void delTicket(HttpServletResponse httpServletResponse, @RequestParam(value = "id") int id) throws IOException {
        serviceTicketService.delTicket(id);

        ResponseWrapper result = ResponseWrapper.success();

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result.toString());
    }
}
