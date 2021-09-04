package corp.sap.internal.exp.controllers.v2;

import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.service.TicketWithPrivilegeCheckService;
import corp.sap.internal.exp.service.exceptions.NoPrivilegeException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v2/ticket")
public class ServiceTicketController {

    @Autowired
    private TicketWithPrivilegeCheckService ticketWithPrivilegeCheckService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() {
        return ResponseWrapper.success(ticketWithPrivilegeCheckService.getAllTicket());
    }

    @GetMapping("/getOwnTicket")
    public Object getOwnTicket(Authentication auth) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        return ResponseWrapper.success(ticketWithPrivilegeCheckService.getTicketByUserID(userId));
    }

    @GetMapping("/{id}")
    public Object getTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException, NoPrivilegeException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPrivilegeCheckService.getTicketByTicketId(userId, id);
        return ResponseWrapper.success(rt);
    }

    @PostMapping("/")
    public Object addTicket(Authentication auth, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPrivilegeException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPrivilegeCheckService.addTicket(userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);
    }

    @PatchMapping("/{id}")
    public Object updateTicket(Authentication auth, @PathVariable(value = "id") Integer id, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPrivilegeException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPrivilegeCheckService.updateTicket(id, userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);

    }

    @DeleteMapping("/{id}")
    public Object delTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException, NoPrivilegeException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        Integer rt = ticketWithPrivilegeCheckService.delTicket(id, userId);
        return ResponseWrapper.success(rt);
    }
}
