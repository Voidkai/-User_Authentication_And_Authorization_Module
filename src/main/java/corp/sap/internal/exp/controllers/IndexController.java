package corp.sap.internal.exp.controllers;

import java.util.HashMap;
import java.util.Map;

import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
    UserService userService;


	@GetMapping("/")
	public Map<String, String> index() {
		Map<String, String> rt = new HashMap<>();
		rt.put("service", "exp");
		return rt;
	}
	//@GetMapping(value = "/login")
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "/login.html";
	}
	
}
