package corp.sap.internal.exp.controllers.v1;

import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.service.UserService;
import corp.sap.internal.exp.service.UserWithPermissionCheckService;
import corp.sap.internal.exp.service.exceptions.NoPrivilegeException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserWithPermissionCheckService userWithPermissionCheckService;

    @PostMapping("/register")
    public Object addUser(@RequestBody User newUser) throws NotSupportedException, NoPrivilegeException {
        User user = userService.register(newUser);
        return ResponseWrapper.success(user);
    }

    @PatchMapping("/changePassword")
    public Object updatePassword(Authentication auth, @RequestBody User newUser){
        Integer userId = ((User) auth.getPrincipal()).getId();

        User user = userWithPermissionCheckService.updatePassword(userId, newUser);
        return ResponseWrapper.success(user);
    }

    @PatchMapping("/changeUsername")
    public Object changeUsername(Authentication auth, @RequestBody User newUser){
        Integer userId = ((User) auth.getPrincipal()).getId();

        User user = userWithPermissionCheckService.updatePassword(userId, newUser);
        return ResponseWrapper.success(user);
    }

}
