package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.ServiceTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v3/serviceTicket")
public class ServiceTicketController {
    @Autowired
    ServiceTicketService serviceTicketService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() throws IOException {
        return serviceTicketService.getAllTicket();
    }

    @GetMapping("/getTicket")
    public Object getTicket(Authentication auth) throws IOException {
        int userId = ((User) auth.getPrincipal()).getId();

        return serviceTicketService.getTicketByUserID(userId);
    }

    @GetMapping("/addTicket")
    public Object addTicket( Authentication auth,@RequestParam(value = "content") String content) throws IOException {
        int userId = ((User)auth.getPrincipal()).getId();

        return serviceTicketService.addTicket(userId, content);
    }

    @GetMapping("/updateTicket")
    public Object updateTicket(@RequestParam(value = "id") int id, @RequestParam(value = "content") String content) throws IOException {

        return serviceTicketService.updateTicket(id, content);

    }

    @GetMapping("/delTicket")
    public Object delTicket(@RequestParam(value = "id") int id) throws IOException {
        return serviceTicketService.delTicket(id);
    }
}
