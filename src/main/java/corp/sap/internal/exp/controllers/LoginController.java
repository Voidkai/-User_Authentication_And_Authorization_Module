package corp.sap.internal.exp.controllers;

import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/failure")
    public String loginFailure(){
        return "login Error";
    }

    @PostMapping("/success")
    public String loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        User user = userService.findByUsername(username);
        return "登录成功";
    }

}
