package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.DTO.ResponseWrapper;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.ServiceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v3/serviceTicket", produces = "application/json")
public class ServiceTicketController {
    @Autowired
    ServiceTicketService serviceTicketService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() throws IOException {
        return ResponseWrapper.success(serviceTicketService.getAllTicket());
    }

    @GetMapping("/getTicket")
    public Object getTicket(Authentication auth) throws IOException {
        Integer userId = ((User) auth.getPrincipal()).getId();

        return ResponseWrapper.success(serviceTicketService.getTicketByUserID(userId));
    }

    @GetMapping("/addTicket")
    public Object addTicket( Authentication auth,@RequestParam(value = "content") String content) throws IOException {
        Integer userId = ((User)auth.getPrincipal()).getId();

        return ResponseWrapper.success(serviceTicketService.addTicket(userId, content));
    }

    @GetMapping("/updateTicket")
    public Object updateTicket(Authentication auth,@RequestParam(value = "id") Integer id, @RequestParam(value = "content") String content) throws IOException {
        Integer userId = ((User)auth.getPrincipal()).getId();

        return serviceTicketService.updateTicket(id, userId, content);

    }

    @GetMapping("/delTicket")
    public Object delTicket(@RequestParam(value = "id") Integer id) throws IOException {
        return serviceTicketService.delTicket(id);
    }
}
