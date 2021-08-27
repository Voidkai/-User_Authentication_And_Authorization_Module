package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.domain.Permission;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.service.PermissionService;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v3/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/getSelfPermission")
    public Object getSelfPermission(Authentication auth) throws NotSupportedException, NoPermissionException {
        Integer userId = ((User) auth.getPrincipal()).getId();
        List<Permission> permList = permissionService.getSelfPermission(userId);
        return ResponseWrapper.success(permList);
    }
}
