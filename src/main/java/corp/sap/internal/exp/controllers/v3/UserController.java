package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.DTO.ResponseWrapper;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.UserService;
import corp.sap.internal.exp.utils.ProcessingStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v3/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/delUser")
    public Object delUser(@RequestParam(value = "userId",defaultValue = "0")int user_id, Principal principal){
        if(user_id == 0)
            user_id = ((User)principal).getId();

        Object rt = userService.delUser(user_id);
        if((int) rt == 0)
            return ResponseWrapper.fail(ProcessingStatusCode.PARAM_NOT_VALID);

        return ResponseWrapper.success(rt);
    }

}
