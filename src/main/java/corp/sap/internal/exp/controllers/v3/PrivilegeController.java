package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.service.PrivilegeService;
import corp.sap.internal.exp.service.exceptions.NoPrivilegeException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v3/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping("/getSelfPrivilege")
    public Object getSelfPermission(Authentication auth) throws NotSupportedException, NoPrivilegeException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        List<Privilege> privList = privilegeService.getSelfPermission(userId);
        return ResponseWrapper.success(privList);
    }
}
