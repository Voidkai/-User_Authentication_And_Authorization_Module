package corp.sap.internal.exp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import corp.sap.internal.exp.DTO.ResponseWrapper;
import corp.sap.internal.exp.service.UserService;
import corp.sap.internal.exp.utils.ProcessingStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping("/register")
	public Object addUser(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password){
		List<String> usernameList = userService.getAllUsername();
		if(usernameList.contains(username)){
			return ResponseWrapper.fail(ProcessingStatusCode.COMMON_FAIL);
		}

		return ResponseWrapper.success(userService.addUser(username,password));
	}
	
}
