package corp.sap.internal.exp.controllers.v3;

import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v3/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") int id){
        return userService.getUser(id);
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public void addUser(@RequestParam(name = "username")String username,@RequestParam(name="password")String password){
        userService.addUser(username,password);
    }

}
