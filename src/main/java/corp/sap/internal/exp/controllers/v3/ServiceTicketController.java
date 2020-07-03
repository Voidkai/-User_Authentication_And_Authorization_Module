package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.ServiceTicketService;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.service.exceptions.NoDataAccessException;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import corp.sap.internal.exp.service.exceptions.ParamNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public Object getTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException, NoPermissionException, NoDataAccessException, ParamNotValidException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = serviceTicketService.getTicketByTicketId(userId, id);
        return ResponseWrapper.success(rt);
    }

    @PostMapping("/")
    public Object addTicket(Authentication auth, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPermissionException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = serviceTicketService.addTicket(userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);
    }

    @PatchMapping("/{id}")
    public Object updateTicket(Authentication auth, @PathVariable(value = "id") Integer id, @RequestBody ServiceTicket serviceTicket) throws NotSupportedException, NoPermissionException, NoDataAccessException, ParamNotValidException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        ServiceTicket rt = serviceTicketService.updateTicket(id, userId, serviceTicket.getContent());
        return ResponseWrapper.success(rt);

    }

    @DeleteMapping("/{id}")
    public Object delTicket(Authentication auth, @PathVariable(value = "id") Integer id) throws NotSupportedException, ParamNotValidException, NoPermissionException, NoDataAccessException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        Integer rt = serviceTicketService.delTicket(id, userId);
        if ( rt == 0) {
            throw new ParamNotValidException("");
        }

        return ResponseWrapper.success(rt);
    }
}
