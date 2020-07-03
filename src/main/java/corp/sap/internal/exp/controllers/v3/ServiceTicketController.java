package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.TicketWithPermissionCheckService;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v3/ticket")
public class ServiceTicketController {

    @Autowired
    private TicketWithPermissionCheckService ticketWithPermissionCheckService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() {
        return ResponseWrapper.success(ticketWithPermissionCheckService.getAllTicket());
    }

    @GetMapping("/getOwnTicket")
    public Object getOwnTicket(Authentication auth) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        return ResponseWrapper.success(ticketWithPermissionCheckService.getTicketByUserID(userId));
    }

    @GetMapping("/{id}")
    public Object getTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException, NoPermissionException{
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPermissionCheckService.getTicketByTicketId(userId, id);
        return ResponseWrapper.success(rt);
    }

    @PostMapping("/")
    public Object addTicket(Authentication auth, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPermissionException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPermissionCheckService.addTicket(userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);
    }

    @PatchMapping("/{id}")
    public Object updateTicket(Authentication auth, @PathVariable(value = "id") Integer id, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPermissionException{
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = ticketWithPermissionCheckService.updateTicket(id, userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);

    }

    @DeleteMapping("/{id}")
    public Object delTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException,NoPermissionException{
        Integer userId = ((User) auth.getPrincipal()).getId();
        Integer rt = ticketWithPermissionCheckService.delTicket(id, userId);
        return ResponseWrapper.success(rt);
    }
}
