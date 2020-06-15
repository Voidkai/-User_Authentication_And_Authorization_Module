package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.DTO.ResponseWrapper;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.ServiceTicketService;
import corp.sap.internal.exp.utils.ProcessingStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v3/ticket")
public class ServiceTicketController {
    @Autowired
    private ServiceTicketService serviceTicketService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() {
        return ResponseWrapper.success(serviceTicketService.getAllTicket());
    }

    @GetMapping("/getOwnTicket")
    public Object getOwnTicket(Authentication auth) {
        Integer userId = ((User) auth.getPrincipal()).getId();

        return ResponseWrapper.success(serviceTicketService.getTicketByUserID(userId));
    }

    @GetMapping("/{id}")
    public Object getTicket(Authentication auth, @PathVariable(value = "id") Integer id) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        List<ServiceTicket> rt = serviceTicketService.getTicketByTicketId(userId, id);
        if(rt == null) return ResponseWrapper.fail(ProcessingStatusCode.NO_PERMISSION);
        return ResponseWrapper.success(rt);
    }

    @PostMapping("/")
    public Object addTicket(Authentication auth, @RequestBody ServiceTicket serviceTicket) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        List<ServiceTicket> rt = serviceTicketService.addTicket(userId, serviceTicket.getContent());
        if (rt == null) return ResponseWrapper.fail(ProcessingStatusCode.NO_PERMISSION);
        return ResponseWrapper.success(rt);
    }

    @PatchMapping("/{id}")
    public Object updateTicket(Authentication auth, @PathVariable(value = "id") Integer id, @RequestBody ServiceTicket serviceTicket) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        Object rt = serviceTicketService.updateTicket(id, userId, serviceTicket.getContent());
        if(rt == null) return ResponseWrapper.fail(ProcessingStatusCode.NO_PERMISSION);
        if (((List<ServiceTicket>)rt).isEmpty()) {
            return ResponseWrapper.fail(ProcessingStatusCode.PARAM_NOT_VALID);
        }
        return ResponseWrapper.success(rt);

    }

    @DeleteMapping("/{id}")
    public Object delTicket(Authentication auth, @PathVariable(value = "id") Integer id) {
        Integer userId = ((User) auth.getPrincipal()).getId();
        Object rt = serviceTicketService.delTicket(id, userId);
        if (rt == null) return ResponseWrapper.fail(ProcessingStatusCode.NO_PERMISSION);
        if ((Integer) rt == 0) {
            return ResponseWrapper.fail(ProcessingStatusCode.PARAM_NOT_VALID);
        }

        return ResponseWrapper.success(rt);
    }
}
