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
    ServiceTicketService serviceTicketService;

    @GetMapping("/getAllTicket")
    public Object getAllTicket() throws IOException {
        return ResponseWrapper.success(serviceTicketService.getAllTicket());
    }

    @GetMapping("/getOwnTicket")
    public Object getOwnTicket(Authentication auth) throws IOException {
        Integer userId = ((User) auth.getPrincipal()).getId();

        return ResponseWrapper.success(serviceTicketService.getTicketByUserID(userId));
    }

    @GetMapping("/{id}")
    public Object getOwnTicket(Authentication auth, @PathVariable(value="id")Integer id ) throws IOException {

        return ResponseWrapper.success(serviceTicketService.getTicketByTicketId(id));
    }

    @PostMapping("/")
    public Object addTicket( Authentication auth,@RequestParam(value = "content") String content) throws IOException {
        Integer userId = ((User)auth.getPrincipal()).getId();

        return ResponseWrapper.success(serviceTicketService.addTicket(userId, content));
    }

    @PatchMapping("/{id}")
    public Object updateTicket(Authentication auth,@PathVariable(value = "id") Integer id, @RequestParam(value = "content") String content) throws IOException {
        Integer userId = ((User)auth.getPrincipal()).getId();
        List<ServiceTicket> rt = serviceTicketService.updateTicket(id, userId, content);
        if(rt.isEmpty()){
            return ResponseWrapper.fail(ProcessingStatusCode.PARAM_NOT_VALID);
        }
        return ResponseWrapper.success(rt);

    }

    @DeleteMapping("/{id}")
    public Object delTicket(Authentication auth,@PathVariable(value = "id") Integer id) throws IOException {
        Integer userId = ((User)auth.getPrincipal()).getId();
        int rt = serviceTicketService.delTicket(id,userId);
        if(rt == 0){
            return ResponseWrapper.fail(ProcessingStatusCode.PARAM_NOT_VALID);
        }

        return ResponseWrapper.success(rt);
    }
}
