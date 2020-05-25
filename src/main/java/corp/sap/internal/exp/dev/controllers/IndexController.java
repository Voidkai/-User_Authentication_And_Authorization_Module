package corp.sap.internal.exp.dev.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {


	@GetMapping("/")
	public Map<String, String> index() {
		Map<String, String> rt = new HashMap<>();
		rt.put("service", "exp");
		return rt;
	}

	
}
