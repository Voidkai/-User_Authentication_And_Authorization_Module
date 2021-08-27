package corp.sap.internal.exp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

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
